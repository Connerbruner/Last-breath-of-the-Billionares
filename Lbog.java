class Lbog extends Game {

    //Items
    ItemClass   staplers    = new ItemClass( "staplers" , 5 , 10 , 50 , 100 );
    ItemClass   clean       = new ItemClass( "Cleaner" , 3 , 5 , 100 , 200 );
    ItemClass   ruler       = new ItemClass( "ruler" , 8 , 12 , 4 , 5 );
    ItemClass   binder      = new ItemClass( "binder" , 5 , 20 , 10 , 20 );
    ItemClass   scissors    = new ItemClass( "scissors" , 7 , 20 , 3 , 10 );
    ItemClass   waterBottle = new ItemClass( "Water Bottle (attack)" , 1 , 20 , 2 , 12 );
    ItemClass   chromeBook  = new ItemClass( "labTop" , 10 , 20 , 1 , 2 );
    ItemClass   metalPiece  = new ItemClass( "metalPiece" , 1 , 20 , 1 , 50 );
    ItemClass   basketBall  = new ItemClass( "basketBall" , 10 , 20 , 1 , 5 );
    ItemClass   footBall    = new ItemClass( "FootBall" , 5 , 10 , 3 , 7 );
    ItemClass   tennisBall  = new ItemClass( "tennisBall" , 1 , 5 , 8 , 20 );
    //healing
    ItemClass   soda        = new ItemClass( "Soda" , 5 , 10 );
    ItemClass   chip        = new ItemClass( "Chip bag" , 7 , 12 );
    ItemClass   water       = new ItemClass( "Water bottle (heal)" , 6 , 10 );
    ItemClass   lunch       = new ItemClass( "lunch box" , 1 , 20 );
    //Item-class array
    ItemClass[] classroom   = { water , waterBottle , clean , ruler , binder , scissors , chromeBook , staplers };
    ItemClass[] gym         = { water , waterBottle , chromeBook };
    ItemClass[] metal       = { water , waterBottle , chromeBook , metalPiece };
    ItemClass[] court       = { water , waterBottle };
    ItemClass[] main        = { soda , lunch , chip , water , waterBottle };
    ItemClass[] outSide     = { soda , chip , lunch , water , waterBottle , chromeBook , basketBall , footBall , tennisBall , };


    //Hallways
    Hallway hallway200  = new Hallway( "200s" , classroom );
    Hallway hallway100  = new Hallway( "100s" , classroom );
    Hallway hallway700  = new Hallway( "700s" , classroom );
    Hallway hallwayD200 = new Hallway( "D200s" , classroom );
    Hallway hallwayD100 = new Hallway( "D100s" , classroom );
    Hallway hallway500  = new Hallway( "500" , gym );
    Hallway commons     = new Hallway( "Commons" , main );
    Hallway courtYard   = new Hallway( "courtYard" , court );

    Hallway robotics         = new Hallway( "Robotics Room" , metal , false );
    Hallway swim             = new Hallway( "swim hallway" , gym , false );
    Hallway field            = new Hallway( "Football field" , outSide , false );
    Hallway track            = new Hallway( "Track field" , outSide , false );
    Hallway parkingLotBus    = new Hallway( "Parking lot (Bus)" , outSide , false );
    Hallway parkingLotSenior = new Hallway( "Parking lot (Senior)" , outSide , false );
    Hallway parkingLotBack   = new Hallway( "Parking lot (Back)" , outSide , false );

    //hallway[]
    Area hazenHighSchool = new Area( "Hazen high school" , new Hallway[] { hallway200 , hallway100 , hallway700 , hallwayD200 , hallwayD100 , hallway500 , commons , courtYard , robotics , swim , field , track , parkingLotBus , parkingLotSenior , parkingLotBack , } );


    ItemClass[] allItems = {
            staplers , clean , ruler , binder , scissors , waterBottle , chromeBook , metalPiece , basketBall , footBall , tennisBall , soda , chip , water , lunch
    };

    //Gordy
    int   gordyHP    = 100;
    int   gordyLevel = 3;
    Gordy gordy      = new Gordy( field , gordyHP , gordyLevel );
    int   score      = 0;

    //you
    Hallway current  = commons;
    Item[]  backpack = new Item[ 3 ];
    int     HP       = 50;
    int     HPM      = 50;
    int     story    = 0;


    //Goal[]
    Goal[] allGoals = {
            new Goal( "Get the robotics key" , hallway200 ) ,
            new Goal( "Find Tippy" , robotics ) ,
            new Goal( "exit the building" , swim ) ,
            new Goal( "Kill Gordy" )
    };


    public Lbog( ) {
        super( "Last breath of Gordy" , null , null );
    }

    public void game( ) {
        hazenHighSchool.setAreaNeighbors( new Hallway[][] {
                { hallwayD100 , hallway100 , hallway200 , hallway500 , parkingLotSenior } ,
                { hallwayD100 , hallway200 } ,
                { hallwayD200 , commons , hallway700 , courtYard , parkingLotBack } ,
                { hallway100 , commons , hallwayD200 } ,
                { courtYard , hallway200 , commons , parkingLotSenior } ,
                { swim , commons , parkingLotBus } ,
                { courtYard , hallwayD100 , robotics , parkingLotBack } ,
                { hallway500 , hallwayD100 , parkingLotBus } ,
                { hallway700 } ,
                { hallway100 , hallway700 , parkingLotBack } ,
                { parkingLotBack } ,
                { field , track , parkingLotSenior } ,
                { parkingLotBack , parkingLotBus , hallway100 , commons } ,
                { hallway500 , track , parkingLotSenior } ,
                { parkingLotBack , parkingLotBus } ,
        } );

        for ( int turn = 1 ; HP > 0 ; turn++ ) {
            nbes.sPrintln( "Turn " + turn );
            nbes.sPrint( allGoals[ story ].toString( ) );
            nbes.sPrint( "Current hallway: " + current.hallwayName );
            nbes.sPrint( "HP: " + HP );
            int action1 = nbes.inputInt( "What would you like to do?\n1) loot\n2) use item\n3) move" );


            if ( action1 == 1 ) {
                find( );
            } else if ( action1 == 2 ) {
                useItem( );
            } else {
                move( );
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
            useItem( );
            score++;
        }
    }

    public String packToString( ) {
        return "Current items\nSlot 0: " + backpack[ 0 ] + "\nSlot 1: " + backpack[ 1 ] + "\nSlot 2: (Storage): " + backpack[ 2 ] + "\n";
    }

    public int useItem( ) {
        nbes.sPrint( packToString( ) );
        int i = 2;
        while ( i > 1 ) {
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
                    move( );
                }
            } else if ( gordy.hallway.hallwayName.equals( current.hallwayName ) ) {
                gordy.HP -= backpack[ i ].useItem( );
                if ( backpack[ i ].dur <= 0 ) {
                    backpack[ i ] = null;
                    move( );
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

    public void move( ) {
        Hallway temp = current.move( );
        if ( temp != null ) {
            current = temp;
        }
        nbes.sPrintln( "You are in the " + current.hallwayName );
    }

}
class Gordy extends FileRead {
    Hallway hallway;
    int     HP;
    boolean charge = false;
    int     level;

    public Gordy( Hallway start , int health , int power ) {
        hallway = start;
        HP      = health;
        level   = power;
    }

    public int Attack( ) {
        int r = 4;
        if ( ! charge ) {
            r = nbes.random( 1 , ( level / 3 ) + 1 );
        }
        if ( r == 1 ) {
            nbes.sPrintln( "DARK RUSH" );
            int damage = 3 * nbes.random( 0 , 5 ) + ( level / 2 );
            nbes.sPrintln( "Gordy deals " + damage + " damage" );
            return damage;
        } else if ( r == 2 ) {
            nbes.sPrintln( "DOUBLE BLADE" );
            int damage = 8 * nbes.random( 0 , 2 ) + ( level / 2 );
            nbes.sPrintln( "Gordy deals " + damage + " damage" );
            return damage;
        } else if ( r == 3 ) {
            nbes.sPrintln( "FIRE BLADE OF DARKNESS" );
            nbes.sPrintln( "Gordy is charging" );
            charge = true;
        } else {
            nbes.sPrintln( "FIRE BLADE OF DARKNESS" );
            int damage = 2 * nbes.random( 7 , 10 ) + ( level / 2 );
            nbes.sPrintln( "Gordy deals " + damage + " damage" );
            charge = false;
            return damage;
        }
        return 0;
    }

    public void move( ) {
        if ( hallway.hallUnlocked ) {
            Hallway temp = hallway.neighbors[ nbes.random( 0 , hallway.neighbors.length - 1 ) ];
            while ( ! temp.hallUnlocked ) {
                temp = hallway.neighbors[ nbes.random( 0 , hallway.neighbors.length - 1 ) ];
            }
            hallway = temp;
        } else {
            hallway = hallway.neighbors[ nbes.random( 0 , hallway.neighbors.length - 1 ) ];
        }


    }


}

