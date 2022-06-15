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
        sPrintln( GREEN + "The text is currently at a speed of 10, if you lower that number it will speed up the text, if you raise the number it will move slower" );
        sPrint( "what should the text speed be?" );
        sPrint("Please enter a username that will be shown to the server");
        String user = scanner.nextLine();

        setTSpeed( scanner.nextInt( ) );
        System.out.println( );

        //cheat code end
        sPrintln("???: Quick warnings");
        sPrintln( RED + "This game does not condone any violence. This is a work of fiction, and any resemblance to characters, real or fictional, is a coincidence." );
        sPrintln(RED+"Your gameplay is being reported to a disord server");
        scanner.nextLine();

        sPrint("???: Which game do you want to play?");
        sPrint("1) Last breath of the Billionaires");
        sPrint("2) Last Breath of Gordy");
        sPrint("3) Speedrunning");
        int choice = scanner.nextInt();
        while(choice>3)
        {
            choice = scanner.nextInt();
        }
        if(choice==1)
        {
            Lbob game = new Lbob(user,tSpeed);
            game.savePath="Lbob.txt";
            game.grabSave( );
            game.game( );
        }
        else if(choice==3) {
            Lbog game = new Lbog(user,tSpeed);;
            game.game();
        }
        else {
            Speedrun game = new Speedrun(user,tSpeed);;
            game.menu();
        }
        
        


        
    }
    
}