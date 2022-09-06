import java.io.*;
import java.util.ArrayList;

class Speedrun extends Game {
    int    time;
    Race[] allRaces = {
            new Race( "VS random emmi" , new Emmi( Emmi.OTHERS[ 0 ] ) ) ,
            new Race( "VS Random boss" , new Emmi( Emmi.MINI_BOSSES[ 0 ] ) ) ,
            new Race( "VS Bill gates" , gates ) ,
    };


    public Speedrun( ) {
        super( "Speedruning" , null , null );
        HPmax        = 60;
        supportPower = 7;

    }

    public void game( ) {
        for ( int i = 0 ; i < allRaces.length ; i++ ) {
            nbes.sPrint( i + ") " + allRaces[ i ].mission + " Current record: " + readTime( i ) + " sec" );
        }
        int  choice    = nbes.inputInt( "Which one?" );
        long startTime = System.currentTimeMillis( );
        if ( allRaces[ choice ].isEmmi ) {
            battle( allRaces[ choice ].vsEmmi );
        } else {
            battle( allRaces[ choice ].vsBoss );
        }
        int time = (int) ( System.currentTimeMillis( ) - startTime ) / 1000;
        if ( time < readTime( choice ) ) {
            writeScores( choice );
            nbes.sPrintln( "New RECORD" );
        }
        nbes.sPrintln( "RUN COMPLETE TIME: " + time );
        if ( nbes.inputBool( "continue?" ) ) {
            game( );
        }
    }

    public void writeScores( int line ) {
        try {
            File                 txt      = new File( "Files/Race scores.txt" );
            FileReader           fileRead = new FileReader( txt );
            BufferedReader       reader   = new BufferedReader( fileRead );
            ArrayList < String > arr      = new ArrayList <>( );
            for ( int i = 0 ; i < 4 ; i++ ) {
                if ( i == line ) {
                    arr.add( " ; " + time );
                    reader.readLine( );
                } else {
                    arr.add( reader.readLine( ) );
                }
            }
            reader.close( );
            File       fileToBeModified = new File( "Files/Race scores.txt" );
            FileWriter writer           = new FileWriter( fileToBeModified );
            for ( String s : arr ) {
                writer.write( s + "\n" );
            }
            writer.close( );
        } catch ( IOException e ) {
            e.printStackTrace( );
        }

    }

    public int readTime( int line ) {
        try {
            File           txt      = new File( "Files/Race scores.txt" );
            FileReader     fileRead = new FileReader( txt );
            BufferedReader reader   = new BufferedReader( fileRead );

            for ( int r = 0 ; r < line ; r++ ) {
                reader.readLine( );
            }
            String info = reader.readLine( );
            int    i;
            for ( i = 0; (int) ( info.charAt( i ) ) != 59 ; i++ ) ;
            reader.close( );
            if ( Nbes.strIsInt( info.substring( i + 2 ) ) ) {
                return Integer.parseInt( info.substring( i + 2 ) );
            }
            return 0;
        } catch ( IOException e ) {
            e.printStackTrace( );
            return 0;
        }
    }
}


class Race {
    Emmi    vsEmmi;
    Boss    vsBoss;
    String  mission;
    boolean isEmmi = false;
    boolean isBoss = false;

    public Race( String name , Emmi emmi ) {
        vsEmmi  = new Emmi( emmi );
        mission = name;
        isEmmi  = true;
    }

    public Race( String name , Boss boss ) {
        vsBoss  = boss;
        mission = name;
        isBoss  = true;
    }


}

