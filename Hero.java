import java.io.*;

public class Hero extends FileRead{
    boolean isHealing;
    boolean isUnlocked;
    String  heroName;
    static Hero[] allHeros = {
            new Hero( "2077" , false ) {
                @Override
                public int attack( ) {
                    nbes.sPrintln( "2077's turn" );
                    long startTime = System.currentTimeMillis( );
                    int  i         = 0;
                    while ( startTime + 10000 > System.currentTimeMillis( ) ) {
                        if ( nbes.quickTime( "Kick" , 5000 ) ) {
                            i++;
                        }
                        if ( startTime + 10000 > System.currentTimeMillis( ) ) {
                            if ( nbes.quickTime( "Punch" , 5000 ) ) {
                                i++;
                            }
                        }
                    }
                    return i;
                }
            } ,
            new Hero( "2069" , true ) {
                @Override
                public int attack( ) {
                        nbes.sPrintln( "2069's turn" );
                        int num = Nbes.random( 0 , 20 );
                        nbes.sPrintln( "2051 heals " + num + " damage" );
                        return num;
                    
                }
            } ,
            new Hero( "2048" , false ) {
                @Override
                public int attack( ) {
                    nbes.sPrintln( "2048's turn" );
                    if ( nbes.inputBool( "Go for a rush attack?" ) ) {
                        this.isHealing = true;
                        if ( Nbes.random( 0 , 1 ) == 1 ) {
                            this.isHealing = false;
                            nbes.sPrintln( "2048 deals 10 damage" );
                            return 10;
                        } else {
                            nbes.sPrintln( "2048 hits 2069 for 10 damage" );
                            return - 10;
                        }
                    }
                    return 0;
                }
            } ,
    };

    private Hero( String name , boolean healing ) {
        isHealing  = healing;
        heroName   = name;
        isUnlocked = false;
    }

    public static int[] readTeam( ) {
        try {
            File           txt      = new File( "Files/Team.txt" );
            FileReader     fileRead = new FileReader( txt );
            BufferedReader reader   = new BufferedReader( fileRead );
            int            size     = 0;
            while ( ( reader.readLine( ) ) != null ) size++;
            int[] arr = new int[ size ];
            reader.close( );
            fileRead = new FileReader( txt );
            reader   = new BufferedReader( fileRead );
            for ( int i = 0 ; i < size ; i++ ) {
                arr[ i ] = Integer.parseInt( reader.readLine( ) );
            }
            reader.close( );
            return arr;
        } catch ( IOException e ) {
            e.printStackTrace( );
            return new int[ 0 ];
        }
    }

    public static void writeTeam( int Timeline ) {
        int[] team = readTeam( );
        try {
            File       fileToBeModified = new File( "Files/Team.txt" );
            FileWriter writer           = new FileWriter( fileToBeModified );
            for ( int i = 0 ; i < allHeros.length ; i++ ) {
                if ( team[ i ] > Timeline ) {
                    writer.write( Timeline + "\n" );
                } else {
                    writer.write( team[ i ] + "\n" );
                }
            }

            writer.close( );
        } catch ( IOException e ) {
            e.printStackTrace( );
        }
    }

    public boolean isUnlocked( int Timeline ) {
        if ( readTeam( ).length == allHeros.length ) {
            int i = 0;
            while ( ! allHeros[ i ].equals( this ) ) i++;
            if ( i < readTeam( ).length ) {
                return isUnlocked = readTeam( )[ i ] < Timeline;
            }
        }
        return isUnlocked;
    }

    public int attack( ) {
        return 0;
    }
}
