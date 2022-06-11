import java.util.Scanner;

class Main extends Tools{
    
    public static void main ( String[] args ) {
      
        Main main = new Main();
        main.play();
    }
    public void play()
    {
        Scanner scanner = new Scanner( System.in );
        setTSpeed( 10 );
        sPrintln("???: Hello, Welcome to Last breath of the Billionaires");
        sPrintln("???: Let me get you ready for this world");
        sPrintln("???: Lets start with Text speed");
        sPrintln( GREEN + "The text is currently at a speed of 10, if you lower that number it will speed up the text, if you raise the number it will move slower" );
        sPrint( "what should the text speed be?" );
        setTSpeed( scanner.nextInt( ) );
        System.out.println( );
        //main starts here
        sPrintln("???: Next enter a cheat code");
        sPrint( "ENTER CHEAT CODE (or not)" );
        //cheat code start
        String cheatCode;
        scanner.nextLine();
        cheatCode = scanner.nextLine( );
        if ( cheatCode.equals( "gay" ) ) {
            isGay = true;
        } else if (cheatCode.equals("backwards")) {
            isBackwards = true;
        } else if (cheatCode.equals("Roomba")) {
            isRoomba = true;
        } else if (cheatCode.equals("or not")) {
            run.exit(69420);
        }
        //cheat code end
        sPrintln("???: Quick warnings");
        sPrintln( RED + "This game does not condone any violence. This is a work of fiction, and any resemblance to characters, real or fictional, is a coincidence." );
        sPrintln(RED+"Your gameplay is being reported to a disord server");
        sPrint("Please enter a username that will be shown to the server");
        String user = scanner.nextLine();
        sPrint("???: Which game do you want to play?");
        sPrint("1) Last breath of the Billionares");
        sPrint("2) Last Breath of Gordy");
        int choice = scanner.nextInt();
        while(choice!=1 && choice!=2)
        {
            choice = scanner.nextInt();
        }
        if(choice==1)
        {
            Game game = new Game(user);
            game.grabSave( );
            System.out.print(SCREEN_CLEAR);
            System.out.flush();
            game.game( );
        }
        else {
            Dsoh game = new Dsoh(user);
            game.game();
        }
        
        


        
    }
    
}