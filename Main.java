class Main extends FileRead{
    static Game[] games = {
            new Lbop( "Files/Lbop.txt" , 8 , new Object[] { 0 , 50 , 1 , 20 , 0 , 1 , 1 , 1 , 1 , 5 } ) ,
            new Lbob( "Files/Lbob.txt" , 9 , new Object[] { 1 , 50 , 1 , 20 , 0 , 1 , 1 , 1 , 1 , 5 } ) ,
            new Lboe( 10 ),
            new Lbog( ) ,
            new Speedrun( ),
            new Sucp()
    };

    public static void main( String[] args ) {
        for ( Cheat cheat : Cheat.cheats )

        for ( int i = 0 ; i < games.length - 1 ; i++ ) {
            if ( games[ i ].savePath != null && games[ i + 1 ].savePath != null ) {
                Object[] save1 = Read( games[ i ].savePath );
                Object[] save2 = Read( games[ i + 1 ].savePath );
                for ( int j = 1 ; j < save2.length && j < save1.length; j++ ) {
                    if(save1[j]!=null && save2[j]!=null)
                    {
                        if ( Integer.parseInt( save1[ j ]+"" ) > Integer.parseInt( save2[ j ] + "" ) ) {
                            save2[ j ] = save1[ j ];
                        }
                    }
                }
                Edit( games[ i ].savePath , save1 );
                Edit( games[ i + 1 ].savePath , save2 );
            }
        }
        
        
        nbes.sPrintln( "???: Hello, Welcome to C1nner's game collection" );
        nbes.sPrintln( "The text is currently at a speed of "+nbes.tSpeed+", if you lower that number it will speed up the text, if you raise the number it will move slower" );
        nbes.tSpeed = nbes.inputInt( "what should the text speed be?" );
        nbes.sPrintln( "This game does not condone any violence. This is a work of fiction, and any resemblance to characters, real or fictional, is a coincidence." );

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
        
        int[] team = readTeam( );
        games[ choice ].is2051joined = team[ 0 ] <= games[ choice ].Timeline;
        games[ choice ].is2048joined = team[ 1 ] <= games[ choice ].Timeline;
        games[ choice ].game( );
    }
}