class Main {
    public static void main( String[] args ) {
        FileRead.nbes.tSpeed = 10;
        FileRead.nbes.sPrintln( "???: Hello, Welcome to Last breath of the Billionaires" );
        FileRead.nbes.sPrintln( "The text is currently at a speed of 10, if you lower that number it will speed up the text, if you raise the number it will move slower" );
        FileRead.nbes.tSpeed = FileRead.nbes.inputInt( "what should the text speed be?" );
        String user = FileRead.nbes.inputString( "Please enter a username that will be shown to the server" );

        System.out.println( );

        //cheat code end
        FileRead.nbes.sPrintln( "???: Quick warnings" );
        FileRead.nbes.sPrintln( "This game does not condone any violence. This is a work of fiction, and any resemblance to characters, real or fictional, is a coincidence." );
        FileRead.nbes.sPrintln( "Your gameplay is being reported to a disord server" );

        FileRead.nbes.sPrint( "1) Last breath of the Billionaires" );
        FileRead.nbes.sPrint( "2) Last Breath of Gordy" );
        FileRead.nbes.sPrint( "3) Speedrunning" );
        int choice = FileRead.nbes.inputInt( "???: Which game do you want to play?" );
        while ( choice > 3 ) {
            choice = FileRead.nbes.inputInt( "???: Which game do you want to play?" );
        }
        System.out.println( "testing" );
        if ( choice == 1 ) {
            Lbob game = new Lbob( "Files/Lbob.txt" , user , FileRead.nbes.tSpeed , 9 ,new Object[] { 0 , 50 , 1 , 20 , 0 , 1 , 1 , 1 , 1 , 5 });
            game.game( );
        } else if ( choice == 2 ) {
            Lbog game = new Lbog( null , user , FileRead.nbes.tSpeed , - 3 );
            game.game( );
        } else {
            Speedrun game = new Speedrun( null , user , FileRead.nbes.tSpeed , 9 );
            game.menu( );
        }
    }
}