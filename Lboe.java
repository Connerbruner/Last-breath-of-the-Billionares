import java.util.ArrayList;

public class Lboe extends Game {
    public Lboe( int placement ) {
        super( "Last breath of Everything" , null , placement , null );
    }

    Attack[] attacks2077 = new Attack[] {
            new Attack( "2077" , "Kick" , 10 , 20 , 10 ) ,
            new Attack( "2077" , "Punch" , 5 , 15 , 7 ) ,
    };
    Attack[] attacks2051 = {
            new Attack( "2051" , "SNIPE SHOT" , 30 , 35 , 40 ) ,
            new Attack( "2051" , "RUSH" , 5 , 10 , 5 ) ,
            new Attack( "2051" , "POTION" , - 5 , 15 , 7 )
    };
    Attack[] attacks2048 = {
            new Attack( "2048" , "RUSH" , 0 , 1 , 1 ) ,
            new Attack( "2048" , "METEOR PORTAL" , 15 , 40 , 40 ) ,
            new Attack( "2048" , "TIME WARP" , - 5 , 10 , - 5 )
    };

    Boss boss2069 = new Boss( new Phase[] {
            new Phase( allAttacks , 50 , "2069" ) ,
            new Phase( allAttacks , 50 , "2069" ) ,
            new Phase( allAttacks , 75 , "2069 (Last breath)" )
    } );
    Boss boss2077 = new Boss( new Phase[] {
            new Phase( attacks2077 , 25 , "2077" ) ,
            new Phase( attacks2077 , 75 , "2077" ) ,
            new Phase( attacks2077 , 75 , "2077 (Last breath)" )
    } );
    Boss boss2051 = new Boss( new Phase[] {
            new Phase( attacks2077 , 10 , "2051" ) ,
            new Phase( allAttacks , 20 , "2051" ) ,
            new Phase( attacks2048 , 30 , "2051" ) ,
            new Phase( attacks2051 , 40 , "2051" ) ,
            new Phase( attacks2051 , 75 , "2051 (Last breath)" )
    } );
    Boss boss2048 = new Boss( new Phase[] {
            new Phase( attacks2048 , 20 , "2048" ) ,
            new Phase( attacks2048 , 80 , "2048" ) ,
            new Phase( attacks2048 , 75 , "2048 (Last breath)" )
    } );

    ArrayList < Boss > tournament = new ArrayList <>( );
    Boss[]             playable   = { boss2077 , boss2069 , boss2048 , boss2051 };
    Boss               player;

    public void game( ) {
        if ( is2051joined && is2048joined ) {
            /*
            nbes.sPrintln( "The world around you begins to fade to black" );
            nbes.sPrintln( "???: Welcome back to this world of nothingness " );
            nbes.sPrintln( "2069: no..." );
            nbes.sPrintln( "???: I will help you get out of this" );
            nbes.sPrintln( "2077:I see nothing wrong with that" );
            nbes.sPrintln( "2069: Please help us" );
            nbes.sPrintln( "???: However...\nI am done being a free get out of trouble card" );
            nbes.sPrintln( "???: I will only spare 1 of you" );
            nbes.sPrintln( "???: You will all fight in a tournament" );
            nbes.sPrintln( "???: 4 of you and an army of Phantom warriors" );
            nbes.sPrintln( "???: You challenge anyone you want" );
            nbes.sPrintln( "???: Good luck" );
             */
            tournament.add( boss2077 );
            tournament.add( boss2069 );
            tournament.add( boss2048 );
            tournament.add( boss2051 );
            for ( int i = 4 ; i > 0 ; i-- ) {
                tournament.add( new Boss( new Phase[] {
                        new Phase( generateAttacks( ) , nbes.random( 0 , 50 ) , "Phantom warrior" ) ,
                        new Phase( generateAttacks( ) , nbes.random( 0 , 50 ) , "Phantom warrior" ) ,
                        new Phase( generateAttacks( ) , nbes.random( 0 , 50 ) , "Phantom warrior (Last breath)" )
                } ) );
            }
            for ( int i = 0 ; i < playable.length ; i++ ) {
                nbes.sPrint( i + ") " + playable[ i ].name );
            }
            player = playable[ nbes.inputInt( "Who would you like to play as?" ) ];
            while ( tournamentCheck( ) ) {
                for ( int i = 0 ; i < tournament.size( ) ; i++ ) {
                    if ( ! ( tournament.get( i ).name.equals( player.name ) ) ) {
                        nbes.sPrint( i + ") " + tournament.get( i ).name );
                    }
                }
                int challenge = nbes.inputInt( "Who would you like to challenge?" );
                while ( tournament.get( challenge ).name.equals( player.name ) ) {
                    challenge = nbes.inputInt( "Who would you like to challenge?" );
                }
                //battles happen
                battle( tournament.get( challenge ) );
                randomBattles( );
            }
        } else {
            nbes.sPrintln( "You need to unlock both 2048 and 2051 to play" );
        }
    }

    public void battle( Boss vs ) {
        while ( ! vs.differentPhases.isEmpty( ) ) {
            Phase youPhase = player.differentPhases.get( 0 );
            Phase vsPhase  = vs.differentPhases.get( 0 );

            nbes.sPrintln( youPhase.name + " health " + youPhase.HP + "\n" + vsPhase.name + " health " + vsPhase.HP );
            vsPhase.pickAttack( true );
            attack( youPhase );
            if ( attackTime < vsPhase.attacks[ vsPhase.curAttack ].speed ) {
                vsPhase.loseHP( choseAttack( youPhase ) );
                if ( vsPhase.HP > 0 ) {
                    youPhase.loseHP( vsPhase.attacks[ vsPhase.curAttack ].attack( ) );
                }
            } else {
                youPhase.loseHP( vsPhase.attacks[ vsPhase.curAttack ].attack( ) );
                if ( youPhase.HP > 0 ) {
                    vsPhase.loseHP( choseAttack( youPhase ) );
                }
            }
            if ( player.differentPhases.isEmpty( ) ) {
                restart( );
            }
            vs.checkArray( );
            player.checkArray( );
        }
        nbes.sPrintln( "???: Your opponent has fallen" );
    }

    //false if B won
    //true if A won
    public void battle( Boss a , Boss b ) {

        while ( ! a.differentPhases.isEmpty( ) && ! b.differentPhases.isEmpty( ) ) {

            Phase phaseA = a.differentPhases.get( 0 );
            Phase phaseB = b.differentPhases.get( 0 );

            phaseA.pickAttack( false );
            phaseB.pickAttack( false );

            if ( phaseA.attacks[ phaseA.curAttack ].speed < phaseB.attacks[ phaseB.curAttack ].speed ) {
                phaseB.loseHP( phaseA.attacks[ phaseA.curAttack ].attackTextFree( ) );
                if ( phaseB.HP > 0 ) {
                    phaseA.loseHP( phaseB.attacks[ phaseB.curAttack ].attackTextFree( ) );
                }
            } else {
                phaseA.loseHP( phaseB.attacks[ phaseB.curAttack ].attackTextFree( ) );
                if ( phaseA.HP > 0 ) {
                    phaseB.loseHP( phaseA.attacks[ phaseA.curAttack ].attackTextFree( ) );
                }
            }
            a.checkArray( );
            b.checkArray( );
        }
    }

    @Override
    public void restart( ) {
        nbes.sPrint( "???: Looks like its " );
        nbes.wait( 1000 );
        nbes.sPrint( "GAME OVER" );
        nbes.wait( 2000 );
        nbes.sPrintln( "???: You see I may have been dishonest about the tournament" );
        nbes.sPrint( "???: ITS GAME OVER" );
        nbes.sPrint( "FOR EVERYTHING" );
        nbes.sPrintln( "2077: YOU-" );
        nbes.sPrintln( "2051: NEVER-" );
        nbes.sPrintln( "???: Goodbye" );
        StringBuilder str = new StringBuilder( );
        for ( int i = Nbes.MAX_CHAR * 500 ; i > 0 ; i-- ) {
            str.append( (char) ( nbes.random( 0 , 256 ) ) );
        }
        nbes.sPrintln( str.toString( ) );
        System.exit( - 1 );
    }

    public void attack( Phase phase ) {
        for ( int i = 0 ; i < phase.attacks.length ; i++ ) {
            nbes.sPrint( i + "( " + phase.attacks[ i ] );
        }
        long start_Time = System.currentTimeMillis( );
        attackNum = nbes.inputInt( "Which attack? A number (0-" + ( phase.attacks.length - 1 ) + ")" );
        long end_Time = System.currentTimeMillis( );
        attackTime = (int) ( ( end_Time - start_Time ) / 1000 + phase.attacks[ attackNum ].speed ) - nbes.tSpeed;

    }

    public int choseAttack( Phase phase ) {
        return phase.attacks[ attackNum ].attack( 1 , false );
    }

    public Attack[] generateAttacks( ) {
        if ( nbes.random( 0 , 4 ) == 4 ) {
            return attacks2048;
        } else if ( nbes.random( 0 , 4 ) == 4 ) {
            return attacks2051;
        } else if ( nbes.random( 0 , 4 ) == 4 ) {
            return attacks2077;
        } else {
            return allAttacks;
        }
    }


    public int findInTournament( String str ) {
        for ( int i = 0 ; i < tournament.size( ) ; i++ ) {
            if ( str.contains( tournament.get( i ).name ) ) {
                return i;
            }
        }
        return - 1;
    }

    public void randomBattles( ) {
        int i;
        for ( i = 3; i > 0 ; i -= nbes.random( 0 , 2 ) ) {
            int skip = findInTournament( player.name );

            int indexA = nbes.random( 0 , tournament.size( ) - 1 );
            while ( indexA == skip ) {
                indexA = nbes.random( 0 , tournament.size( ) - 1 );
            }

            int indexB = nbes.random( 0 , tournament.size( ) - 1 );
            while ( indexB == skip || indexB == indexA ) {
                indexB = nbes.random( 0 , tournament.size( ) - 1 );
            }

            battle( tournament.get( indexA ) , tournament.get( indexB ) );
        }
        nbes.sPrintln( "Random battles have occurred" );
    }

    public boolean tournamentCheck( ) {
        boolean partyLeft   = false;
        for ( int i = 0 ; i < tournament.size( ) ; i++ ) {
            if ( tournament.get( i ).differentPhases.isEmpty( ) ) {
                tournament.remove( i );
            } else {
                for ( Boss boss : playable ) {
                    if ( tournament.get( i ).name.equals( boss.name ) && ! tournament.get( i ).name.equals( player.name ) ) {
                        partyLeft = true;
                    } else if ( tournament.get( i ).name.equals( player.name ) ) {
                        tournament.get( i ).resetPhases( );
                    }
                }
            }
        }
        return partyLeft;
    }
}
