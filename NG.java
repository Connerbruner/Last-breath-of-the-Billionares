public class NG extends Game {

    public NG() {
        super( "Number guesser" , null  , null );
    }

    @Override
    public void game( ) {
        int highscore = Integer.parseInt( Read( "Files/number.txt" )[ 0 ].toString( ) );
        while ( nbes.inputBool( "You want to play?" ) ) {
            nbes.sPrintln("Current highscore: "+highscore);

            int     goal  = Nbes.random( 0 , 100 );
            int     score = 100;
            boolean win   = false;
            for ( int i = 7 ; i > 0 ; i-- ) {
                int guess = nbes.inputInt( "what is favorite number 1,100" );
                if ( guess > goal ) {
                    nbes.sPrintln( "Too Big" );
                } else if ( guess < goal ) {
                    nbes.sPrintln( "Too Small" );
                } else {
                    win = true;
                    break;
                }
                score -= ( Math.abs( goal - guess ) / 2 );
                nbes.sPrintln( "You have " + i + " Guesses left" );
            }
            if ( win ) {
                nbes.sPrintln( "YOU WON\nWOOOOOOOOOOOOOOOOOOOOO" );
                if ( score > highscore ) {
                    highscore = score;
                    nbes.sPrintln( "NEW HIGHSCORE: "+highscore );
                    FileRead.Edit( "Files/number.txt" , new Object[] { highscore } );
                }
            } else {
                nbes.sPrintln( "GAME OVER\nThe number was: " + goal );
            }

        }


    }
}
