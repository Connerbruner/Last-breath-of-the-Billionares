import java.awt.*;
import java.io.*;
import java.util.Arrays;


class FileRead {
    final static Nbes nbes = new Nbes( );
    Runtime run = Runtime.getRuntime( );

    public static void Edit( String filePath , Object[] arr ) {
        File       fileToBeModified = new File( filePath );
        FileWriter writer           = null;
        try {
            writer = new FileWriter( fileToBeModified );
            for ( Object o : arr ) {
                if ( o != null ) {
                    String print = o + "\n";
                    writer.write( print );
                }
            }

        } catch ( IOException e ) {
            e.printStackTrace( );
        } finally {
            try {
                //Closing the resources
                assert writer != null;
                writer.close( );
            } catch ( IOException e ) {
                e.printStackTrace( );
            }
        }
    }

    /**
     * @return array of saved vars
     */
    public static Object[] Read( String file ) {

        try {
            File           txt      = new File( file );
            FileReader     fileRead = new FileReader( txt );
            BufferedReader reader   = new BufferedReader( fileRead );
            Object[]       arr      = new Object[ 13 ];

            for ( int r = 0 ; r < arr.length ; r++ ) {
                Object var = reader.readLine( );
                arr[ r ] = var;
            }
            reader.close( );
            return arr;
        } catch ( IOException e ) {
            e.printStackTrace( );
            return new Object[ 0 ];
        }
    }





    public long getUsedMem( ) {
        return run.maxMemory( ) - run.freeMemory( );
    }

    public long getFreeMem( ) {
        return run.freeMemory( );
    }

    public long getMaxMem( ) {
        return run.maxMemory( );
    }

}

class Cheat {
    String cheatCode;
    String filePath;
    String txtOverride;

    boolean isOn;
    Emmi    emmiOverride;
    Color[] systemColors;
    static Cheat[] cheats;

    static {
        try {
            cheats = new Cheat[] {
                    new Cheat( "overplayed dev version" , Emmi.OTHERS[ 0 ] ) ,
                    new Cheat( "ha ha giga mech" , Emmi.MINI_BOSSES[ 0 ] ) ,
                    new Cheat( "ace" , new Color[] {
                            new Color( 0 , 0 , 0 ) ,
                            new Color( 163 , 163 , 163 ) ,
                            new Color( 255 , 255 , 255 ) ,
                            new Color( 128 , 0 , 128 ) , } ) ,
                    new Cheat( "pride" , new Color[] {
                            new Color( 209 , 34 , 41 ) ,
                            new Color( 246 , 138 , 30 ) ,
                            new Color( 253 , 224 , 26 ) ,
                            new Color( 0 , 121 , 64 ) ,
                            new Color( 36 , 64 , 142 ) ,
                            new Color( 115 , 41 , 130 ) ,
                    } ) ,


            };
        } catch ( IOException e ) {
            throw new RuntimeException( e );
        }
    }


    private Cheat( String name , Emmi emmi ) throws IOException {
        cheatCode    = name;
        emmiOverride = emmi;
        useCode( );
    }

    private Cheat( String name , Color[] colors ) throws IOException {
        cheatCode    = name;
        systemColors = colors;
        useCode( );
    }

    private Cheat( String name , String file , String txt ) throws IOException {
        cheatCode   = name;
        filePath    = file;
        txtOverride = txt;
        useCode( );
    }

    public static boolean checkCode( Cheat code ) throws IOException {
        code.isOn = getCodeString( ).contains( code.cheatCode );
        return code.isOn;
    }

    public static String getCodeString( ) throws IOException {
        File           txt        = new File( "Files/Cheats.txt" );
        FileReader     fileRead   = new FileReader( txt );
        BufferedReader reader     = new BufferedReader( fileRead );
        String         file;
        StringBuilder  cheatCodes = new StringBuilder( );
        while ( ( file = reader.readLine( ) ) != null ) {
            cheatCodes.append( file );
        }
        reader.close( );
        return cheatCodes.toString( );
    }

    public void useCode( ) throws IOException {
        if ( checkCode( this ) ) {
            if ( emmiOverride != null ) {
                Emmi.MINI_BOSSES = new Emmi[] { emmiOverride };
                Emmi.OTHERS      = new Emmi[] { emmiOverride };
                Emmi.METAS       = new Emmi[] { emmiOverride };
            } else if ( systemColors != null ) {
                Nbes.PLATE_COLORS.addAll( Arrays.asList( systemColors ) );
            } else if ( filePath != null ) {
                File       fileToBeModified = new File( filePath );
                FileWriter writer           = new FileWriter( fileToBeModified );
                writer.write( txtOverride );
                writer.close( );
            }
        }

    }

}