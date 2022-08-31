public class Lbob extends Game {
    public Lbob( String file ) {
        super( "Last breath of the Billionaires" , file , 1 , new Object[] { 0 , 50 , 1 , 20 , 0 , 1 , 1 , 1 , 5 } );
    }

    @Override
    public void game( ) {
        if ( missionNum == 0 ) {
            Nbes.playSound( "Files/DROPOUT.wav" );
            int speed = Nbes.tSpeed;
            Nbes.tSpeed = 7;
            chatText( "2051: What you want?" , 750 );
            chatText( "???: Nothing" , 750 );
            chatText( "???: Your life It just seams pointless" , 1200 );
            chatText( "2051: Your not wrong" , 300 );
            chatText( "2051: Everyone's out to get me" , 1100 );
            chatText( "???: You think I am any different?" , 1200 );
            chatText( "2051: No. Not at all" , 300 );
            chatText( "???: Killing all these people. Kinda Boring" , 1250 );
            chatText( "???: Lets mix it up\n\nshall we?" , 750 );
            chatText( "???: I hold the power to EVERYTHING with a motherboard" , 1250 );
            chatText( "???: Lets see how you hold up against a roomba" , 1000 );
            chatText( "2051: Easy as pie" , 0 );
            Nbes.tSpeed = speed;
            while ( Nbes.wavFile.isOpen( ) );
            battle( Emmi.OTHERS[ 0 ] );
            missionNum++;
        }
    }


    public void chatText( String str , int wait ) {
        nbes.sPrint( str );
        nbes.lastsPrint = "";
        Nbes.wait( wait );
    }


}