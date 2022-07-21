import javax.swing.*;
import java.awt.*;

class Sucp extends Game {
    static final JFrame extra = new JFrame( "" );
    int points      = 0;
    int round       = 1;
    int roundPoints = 0;

    public Sucp( ) {
        super( "SUPER ULTRA COLOR PICKER" , null , 0 , null );
    }

    public void game( ) {
        nbes.sPrintln( "HELLO EVERYONE MY NAME IS GUY FIERI" );
        nbes.sPrintln( "YOUR WATCHING SUPER ULTRA COLOR PICKER CHALLENGE" );
        nbes.sPrintln( "Our challenger will start by picking their name" );
        nbes.sPrintln( "Hello " + getName( ) );
        while ( ( roundPoints > 0 ) || ( round == 1 ) ) {
            nbes.sPrintln( "Round: " + round );
            round++;
            roundPoints = playGame( );
            points += roundPoints;
        }
        nbes.sPrintln( "G A M E    O V E R" );
        nbes.sPrintln( "You played " + round + " rounds\n You earned: " + points + " points" );
    }

    // methods
    // color picker
    public int[] getColor( ) {
        Color newColor = JColorChooser.showDialog( extra , "SUPER ULTRA COLOR PICKER CHALLENGE" , extra.getBackground( ) );
        return new int[] { newColor.getRed( ) , newColor.getGreen( ) , newColor.getBlue( ) };
    }


    // gamer
    public int playGame( ) {
        int   R   = nbes.random( 0 , 255 );
        int   G   = nbes.random( 0 , 255 );
        int   B   = nbes.random( 0 , 255 );
        Color guy = new Color( R , G , B );
        nbes.sPrintln( "Your goal is to find a color closest to this color" );
        showColor( guy );
        long  startTime = System.currentTimeMillis( );
        int[] gotcolor  = getColor( );
        int   time      = (int) ( ( System.currentTimeMillis( ) - startTime ) / 30 );
        nbes.sPrintln( "Lets see how you did" );
        int off = 400 - ( round * 5 );
        off -= Math.abs( gotcolor[ 0 ] - R );
        off -= Math.abs( gotcolor[ 1 ] - G );
        off -= Math.abs( gotcolor[ 2 ] - B );
        int score = (int) ( ( off - time ) * ( ( round / 10 ) + 0.9 ) );
        if ( score < 0 ) {
            nbes.sPrintln( "You did not score any points :(" );
            return 0;
        } else {
            nbes.sPrintln( "You scored: " + score + " points" );
        }
        return score;
    }

    public String getName( ) {
        nbes.sPrintln( "Use this color picker to tell us your name" );
        int[]  gotcolor = getColor( );
        String name;
        if ( ( gotcolor[ 0 ] == 255 ) && ( gotcolor[ 1 ] == 255 ) && ( gotcolor[ 2 ] == 255 ) ) {
            name = "C1nner aka LORD WHITE BREAD";
        } else if ( ( gotcolor[ 0 ] == 255 ) && ( gotcolor[ 1 ] > 150 ) && ( gotcolor[ 2 ] > 51 ) ) {
            name = "vector from despicable me";
        } else if ( ( gotcolor[ 0 ] < 20 ) && ( gotcolor[ 1 ] < 20 ) && ( gotcolor[ 2 ] == 255 ) ) {
            name = "Actually just thomas the tank engine";
        } else if ( ( gotcolor[ 0 ] < 51 ) && ( gotcolor[ 1 ] < 102 ) && ( gotcolor[ 2 ] == 255 ) ) {
            name = "Discord Mod";
        } else if ( ( gotcolor[ 0 ] == 255 ) && ( gotcolor[ 1 ] < 203 ) && ( gotcolor[ 2 ] == 255 ) ) {
            name = "Gamer girl with a side of bath water";
        } else if ( ( gotcolor[ 0 ] < 1 ) || ( gotcolor[ 1 ] < 100 ) && ( gotcolor[ 2 ] > 100 ) ) {
            name = "2069 from the hit indie nbes.sPrintln based RPG last breath";
        } else if ( ( gotcolor[ 0 ] > 254 ) || ( ( gotcolor[ 1 ] > 50 ) && ( gotcolor[ 2 ] < 50 ) ) ) {
            name = "Little timmy having a meltdown over subpar candy";
        } else {
            name = "Dount earther";
        }
        return name;
    }

    public void showColor( Color color ) {
        extra.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        extra.setSize( 500 , 500 );
        extra.getContentPane( ).setBackground( color );
        extra.setVisible( true );
        nbes.wait( 1000 - ( round * 10 ) );
        extra.setVisible( false );
    }
}