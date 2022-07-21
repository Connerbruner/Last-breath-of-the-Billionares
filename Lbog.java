class Lbog extends Game {

    String user;
    //items in loot tables


    //Gordy
    int   gordyHP    = 100;
    int   gordyLevel = 3;
    Gordy gordy      = new Gordy( field , gordyHP , gordyLevel );
    int   score      = 0;

    //Goal[]
    Goal[] allGoals = {
            new Goal( "Get the robotics key" , hallway200 ) ,
            new Goal( "Find Tippy" , robotics ) ,
            new Goal( "exit the building" , swim ) ,
            new Goal( "Kill Gordy" )
    };

    //you
    Hallway current  = commons;
    Item[]  backpack = new Item[ 3 ];
    int     HP       = 50;
    int     HPM      = 50;
    int     story    = 0;

    public Lbog( ) {
        super( "Last breath of Gordy" , null , 6 , null );
    }

    public void game( ) {
        commons.setNeighbors( neighborCommon );
        hallwayD200.setNeighbors( neighborD200 );
        hallwayD100.setNeighbors( neighborD100 );
        hallway200.setNeighbors( neighbor200 );
        hallway100.setNeighbors( neighbor100 );
        hallway500.setNeighbors( neighbor500 );
        hallway700.setNeighbors( neighbor700 );
        swim.setNeighbors( neighborSwim );
        robotics.setNeighbors( neighborRobot );
        courtYard.setNeighbors( neighborCourt );
        field.setNeighbors( neighborFootBall );
        parkingLotBack.setNeighbors( neighborBack );
        parkingLotSenior.setNeighbors( neighborSenior );
        parkingLotBus.setNeighbors( neighborBus );
        track.setNeighbors( neighborTrack );

        for ( int turn = 1 ; HP > 0 ; turn++ ) {
            nbes.sPrintln( "Turn " + turn );
            nbes.sPrint( allGoals[ story ].toString( ) );
            nbes.sPrint( "Current hallway: " + current.hallwayName );
            nbes.sPrint( "HP: " + HP );
            int action1 = nbes.inputInt( "What would you like to do?\n1) loot\n2) use item\n3) move" );
            int action2 = nbes.inputInt( "\nWhat would you like to do?\n1) loot\n2) use item\n3) move" );
            while ( action2 == action1 ) {
                action2 = nbes.inputInt( "\nWhat would you like to do?\n1) loot\n2) use item\n3) move" );
            }


            if ( action1 == 1 || action2 == 1 ) {
                find( );
            }
            if ( action1 == 2 || action2 == 2 ) {
                useItem( );
            }
            if ( action1 == 3 || action2 == 3 ) {
                Hallway temp = current.move( );
                if ( temp != null ) {
                    current = temp;
                }
                nbes.sPrintln( "You are in the " + current.hallwayName );
            }

            if ( gordy.HP < 0 ) {
                nbes.sPrintln( "Gordy fades away" );
                nbes.sPrintln( "Gordy is back at the footBall feild" );
                gordyHP *= 1.5;
                gordyLevel *= 1.5;
                gordy = new Gordy( field , gordyHP , gordyLevel );
                lootBackpack( 3 );
                score += gordyLevel * 1000;
            }
            if ( gordy.hallway.hallwayName.equals( current.hallwayName ) ) {
                HP -= gordy.Attack( );
            } else if ( ! gordy.charge ) {
                for ( int i = ( turn / 2 ) ; i > 0 && ! gordy.hallway.hallwayName.equals( current.hallwayName ) ; i-- ) {
                    gordy.move( );
                }
                nbes.sPrintln( "Gordy is in the " + gordy.hallway.hallwayName );
                if ( gordy.hallway.hallwayName.equals( current.hallwayName ) ) {
                    HP -= gordy.Attack( );
                }
            }

            if ( story == 0 && allGoals[ 0 ].check( current ) ) {
                robotics.hallUnlocked = true;
                score += 100;
                story++;
            }
            if ( story == 1 && allGoals[ 1 ].check( current ) ) {
                swim.hallUnlocked = true;
                lootBackpack( 2 );
                score += 300;
                story++;
            }
            if ( story == 2 && allGoals[ 2 ].check( current ) ) {
                track.hallUnlocked            = true;
                field.hallUnlocked            = true;
                parkingLotBus.hallUnlocked    = true;
                parkingLotSenior.hallUnlocked = true;
                parkingLotBack.hallUnlocked   = true;
                score += 1000;
                lootBackpack( 3 );
                story++;

            }
            score++;

        }
        nbes.sPrintln( "GAME OVER" );
        run.exit( 69420 );

    }

    public void lootBackpack( int size ) {
        nbes.sPrintln( "You find a Backpack Size: " + size );
        for ( ; size > 0 ; size-- ) {
            find( );
            score += 10;

        }
    }

    public void find( ) {
        Item temp = current.loot( );
        nbes.sPrint( packToString( ) );
        nbes.sPrint( temp.toString( ) );
        if ( nbes.inputBool( "Want this item" ) ) {
            backpack[ nbes.inputInt( "What slot 0-2" ) ] = temp;
            score++;
        }
    }

    public String packToString( ) {
        return "Current items\nSlot 0: " + backpack[ 0 ] + "\nSlot 1: " + backpack[ 1 ] + "\nSlot 2: (Storage): " + backpack[ 2 ] + "\n";
    }

    @Override
    public int useItem( ) {
        nbes.sPrint( packToString( ) );
        int i = 2;
        while ( i == 2 ) {
            i = nbes.inputInt( "What slot 0-1" );
        }
        Item use = backpack[ i ];
        if ( use != null ) {
            if ( use.isHeal ) {
                if ( HP != HPM ) {
                    HP += use.useItem( );
                    if ( HP > HPM ) {
                        HP = HPM;
                    }
                    backpack[ i ] = null;
                }
            } else if ( gordy.hallway.hallwayName.equals( current.hallwayName ) ) {
                gordy.HP -= backpack[ i ].useItem( );
                if ( backpack[ i ].dur <= 0 ) {
                    backpack[ i ] = null;
                }
            }
        } else {
            nbes.sPrint( "No item to use" );
        }
        if ( backpack[ 0 ] == null && backpack[ 2 ] != null ) {
            if ( nbes.inputBool( "Pull out " + backpack[ 2 ].itemName + " of storage" ) ) {
                backpack[ 0 ] = backpack[ 2 ];
                backpack[ 2 ] = null;
            }
        }
        if ( backpack[ 1 ] == null && backpack[ 2 ] != null ) {
            if ( nbes.inputBool( "Pull out " + backpack[ 2 ].itemName + " of storage" ) ) {
                backpack[ 1 ] = backpack[ 2 ];
                backpack[ 2 ] = null;
            }
        }
        return 0;
    }

}