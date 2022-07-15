class Main {
    static Game[] games = {
            new Lbob( "Files/Lbob.txt"  , 9 , new Object[] { 1 , 50 , 1 , 20 , 0 , 1 , 1 , 1 , 1 , 5 }),
            new Lbop( "Files/Lbop.txt",8, new Object[] { 0 , 50 , 1 , 20 , 0 , 1 , 1 , 1 , 1 , 5 }),
            new Lbog(),
            new Speedrun()
    };
    public static void main( String[] args ) {
        FileRead.nbes.tSpeed = 10;
        FileRead.nbes.sPrintln( "???: Hello, Welcome to Last breath of the Billionaires" );
        FileRead.nbes.sPrintln( "The text is currently at a speed of 10, if you lower that number it will speed up the text, if you raise the number it will move slower" );
        FileRead.nbes.tSpeed = FileRead.nbes.inputInt( "what should the text speed be?" );
        FileRead.nbes.sPrintln( "This game does not condone any violence. This is a work of fiction, and any resemblance to characters, real or fictional, is a coincidence." );

        for(int i=0; i< games.length; i++)
        {
            FileRead.nbes.sPrint(i+") "+games[i].name);
        }
        int choice = FileRead.nbes.inputInt( "???: Which game? (0-"+(games.length-1)+")" );
        while ( choice > games.length-1 ) {
            choice = FileRead.nbes.inputInt( "???: Which game? (0-"+(games.length-1) );
        }
        if ( games[choice].resetArr != null && games[choice].savePath != null ) {
            games[choice].grabSave( );
        }
        games[choice].game();
    }
}