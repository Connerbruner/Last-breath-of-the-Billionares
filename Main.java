import java.util.Scanner;

class Main extends Game {
    
    public static void main ( String[] args ) {
      
        Main main = new Main();
        main.play();
    }
    public void play()
    {
        Scanner scanner = new Scanner( System.in );
        setTSpeed( 20 );
        sendToBot("Hello, I am online");
        sPrintln("???: Hello, Welcome to Last breath of the Billionaires");
        sPrintln("???: Let me get you ready for this world");
        sPrintln("???: Lets start with Text speed");
        sPrintln( GREEN + "The text is currently at a speed of 20, if you lower that number it will speed up the text, if you raise the number it will move slower" );
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

        isDiscord = choice("Are you a part of the discord server? ");
        sPrintln("???: Quick warnings");
        sPrintln( RED + "This game does not condone any violence. This is a work of fiction, and any resemblance to characters, real or fictional, is a coincidence." );
        sPrintln(RED+"Your gameplay");
        sPrint("Please enter a username that will be shown to the server");
        user = scanner.nextLine();
        grabSave( );
        sPrintln("LOADING BEGINNING");
        loading( );
        sPrintln("LOAD COMPLETE");
        wait(1000);
        System.out.println(SCREEN_CLEAR);
        System.out.flush();
        double passedTime = (double)(System.currentTimeMillis()/3600000)-login;
        if(passedTime>0)
        {
            int total = (int)(passedTime*missionNum*2);
            sPrintln("Last login was "+passedTime+" Hours ago Earned: "+total+" exp");
            exp1+=total;
            save();
            if(isDiscord)
            {
                C+=total;
            }
        }
        game( );
    }
    
}