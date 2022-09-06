public class Lbob extends Game {
    public Lbob( String file ) {
        super( "Last breath of the Billionaires" , file , new Object[] { 0 , 100 , 50 , 1 , 20 , 0 , 1 , 1 , 1 , 5 } );
    }

    Attack[] attacks2077 = new Attack[] {
            new Attack( "2077" , "Kick" , 10 , 20 , 10 ) ,
            new Attack( "2077" , "Punch" , 5 , 15 , 7 ) ,
    };
    Attack[] attacks2048 = {
            new Attack( "2048" , "RUSH" , 0 , 1 , 1 ) ,
            new Attack( "2048" , "METEOR PORTAL" , 15 , 40 , 40 ) ,
            new Attack( "2048" , "TIME WARP" , - 5 , 10 , - 5 )
    };
    Boss[]   playable    = {
            new Boss( new Phase[] {
                    new Phase( attacks2048 , 20 , "2048" ) ,
                    new Phase( attacks2048 , 80 , "2048" ) ,
                    new Phase( attacks2048 , 75 , "2048 (Last breath)" )
            } ) ,
            new Boss( new Phase[] {
                    new Phase( allAttacks , 50 , "2069" ) ,
                    new Phase( attacks2048 , 50 , "2069" ) ,
                    new Phase( attacks2077 , 50 , "2069(Last breath)" ) ,
            } ) ,
            new Boss( new Phase[] {
                    new Phase( attacks2077 , 25 , "2077" ) ,
                    new Phase( attacks2077 , 75 , "2077" ) ,
                    new Phase( attacks2077 , 75 , "2077 (Last breath)" )
            } ) ,
    };

    @Override
    public void game( ) {
        if ( missionNum == 0 ) {
            nbes.sPrintln( "UNKNOWN_PERSON: send 100 M.E.T.A s after them" );
            nbes.sPrintln( "random person: YES SIR" );
            nbes.sPrintln( "UNKNOWN_PERSON: They will never live past this" );
            metaleft = 100;
            missionNum++;
            save( );
        }
        while ( true ) {

        }
    }

    @Override
    public void restart( ) {
        HP=HPmax;
        Boss per1 = playable[Nbes.random( 0,playable.length-1 )];
        Boss per2;
        while ( (per2=playable[Nbes.random( 0,playable.length-1 )])!=null );

        nbes.sPrintln( per1.name+":" );
        nbes.sPrintln( per2.name+":" );
    }
}