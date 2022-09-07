
class Main extends FileRead {
    static Game[] games = {
            new Lbob("Files/player.txt"),
            new Lbog( ) ,
            new Speedrun( ) ,
            new Sucp( ) ,
            new NG(  ) ,
    };


    public static void main( String[] args )  {

        for ( int i = 0 ; i < games.length - 1 ; i++ ) {
            if ( games[ i ].savePath != null && games[ i + 1 ].savePath != null ) {
                Object[] save1 = Read( games[ i ].savePath );
                Object[] save2 = Read( games[ i + 1 ].savePath );
                for ( int j = 1 ; j < save2.length && j < save1.length ; j++ ) {
                    if ( save1[ j ] != null && save2[ j ] != null ) {
                        if ( Integer.parseInt( save1[ j ] + "" ) > Integer.parseInt( save2[ j ] + "" ) ) {
                            save2[ j ] = save1[ j ];
                        }
                    }
                }
                Edit( games[ i ].savePath , save1 );
                Edit( games[ i + 1 ].savePath , save2 );
            }
        }
        nbes.sPrintln( "???: Hello, Welcome to C1nner's game collection ");
        nbes.sPrintln( "The text is currently at a speed of " + nbes.tSpeed + ", if you lower that number it will speed up the text, if you raise the number it will move slower" );
        nbes.tSpeed = nbes.inputInt( "what should the text speed be?" )+1;
        nbes.sPrintln( "This game does not condone any violence. This is a work of fiction, and any resemblance to characters, real or fictional, is a coincidence." );
        while (true)
        {
            for ( int i = 0 ; i < games.length ; i++ ) {
                nbes.sPrint( i + ") " + games[ i ].name );
            }
            int choice = nbes.inputInt( "???: Which game? (0-" + ( games.length - 1 ) + ")" );
            while ( choice > games.length - 1 ) {
                choice = nbes.inputInt( "???: Which game? (0-" + ( games.length - 1 ) );
            }
            if ( games[ choice ].resetArr != null && games[ choice ].savePath != null ) {
                games[ choice ].grabSave( );
            }


            games[ choice ].game( );
        }
    }
}