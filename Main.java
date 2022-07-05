
class Main extends FileRead{
    public static void main ( String[] args ) {
      
        Main main = new Main();
        main.play();
    }
    public void play()
    {
        nbes.tSpeed=10;
        nbes.sPrintln("???: Hello, Welcome to Last breath of the Billionaires");
        nbes.sPrintln("The text is currently at a speed of 10, if you lower that number it will speed up the text, if you raise the number it will move slower" );
        nbes.tSpeed=nbes.inputInt("what should the text speed be?");
        String user = nbes.inputString("Please enter a username that will be shown to the server");
        
        System.out.println( );

        //cheat code end
        nbes.sPrintln("???: Quick warnings");
        nbes.sPrintln( "This game does not condone any violence. This is a work of fiction, and any resemblance to characters, real or fictional, is a coincidence." );
        nbes.sPrintln("Your gameplay is being reported to a disord server");

        nbes.sPrint("1) Last breath of the Billionaires");
        nbes.sPrint("2) Last Breath of Gordy");
        nbes.sPrint("3) Speedrunning");
        int choice = nbes.inputInt("???: Which game do you want to play?");
        while(choice>3)
        {
            choice = nbes.inputInt("???: Which game do you want to play?");
        }
        System.out.println("testing");
        if(choice==1)
        {
            Lbob game = new Lbob("Lbob.txt",user,nbes.tSpeed,9);
            game.game( );
        }
        else if(choice==2) {
            Lbog game = new Lbog("",user,nbes.tSpeed,-3);
            game.game();
        }
        else {
            Speedrun game = new Speedrun("lbob.txt",user,nbes.tSpeed,9);
            game.menu();
        }
    }
    
}