import java.io.*;


class FileRead {
    final static Nbes nbes = new Nbes( );
    Runtime run = Runtime.getRuntime( );
    int     tSpeed;

    public static void Edit( String filePath , Object[] arr ) {
        File       fileToBeModified = new File( filePath );
        FileWriter writer           = null;
        try {
            writer = new FileWriter( fileToBeModified );
            for ( Object o : arr ) {
                if(o!=null)
                {
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


    public static int[] readTeam( ) {
        try {
            File           txt      = new File( "Files/Team.txt" );
            FileReader     fileRead = new FileReader( txt );
            BufferedReader reader   = new BufferedReader( fileRead );
            int[] arr = new int[] { Integer.parseInt( reader.readLine( ) ) , Integer.parseInt( reader.readLine( ) ) };
            reader.close();
            return arr;
        } catch ( IOException e ) {
            e.printStackTrace( );
            return new int[ 0 ];
        }
    }

    public static void writeTeam( boolean b2051 , boolean b2048 , int Timeline ) {
        int[] team = readTeam( );
        try {
            File       fileToBeModified = new File( "Files/Team.txt" );
            FileWriter writer           = new FileWriter( fileToBeModified );
            if ( b2051 ) {
                writer.write( Timeline+"\n" );
            } else {
                writer.write( team[ 0 ]+"\n" );
            }
            if ( b2048 ) {
                writer.write( Timeline+"\n" );
            } else {
                writer.write( team[ 1 ]+"\n" );
            }
            writer.close();
        } catch ( IOException e ) {
            e.printStackTrace( );
        }
    }


}