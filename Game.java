import java.util.ArrayList;

class Game extends FileRead {
    public static final int MAX_EMMI = 8;
    String   savePath;
    String   user;
    int      HP2069;
    int      Timeline;
    Object[] resetArr;

    int      lastAttack   = 5;
    int      attackNum    = 5;
    boolean  attackType;
    int      attackTime   = 0;
    int      attackStun   = 0;
    int      stars        = 0;
    int      speed        = 0;
    int      stun         = 0;

    // Array vars (placed in Lbob.txt)
    int      missionNum   = 10;
    int      HPmax        = 50;
    int      cureTier     = 1;
    int      level2069    = 1;
    int      exp1         = 0;
    int      levelR1      = 20;
    int      maxHit       = 5;
    boolean  is2051joined = false;
    boolean  is2048joined = false;


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
    Hallway[] neighborCommon = { hallwayD100 , hallway100 , hallway200 , hallway500 , parkingLotSenior };
    Hallway[] neighborD200   = { hallwayD100 , hallway200 };
    Hallway[] neighborD100   = { hallwayD200 , commons , hallway700 , courtYard , parkingLotBack };
    Hallway[] neighbor200    = { hallway100 , commons , hallwayD200 };
    Hallway[] neighbor100    = { courtYard , hallway200 , commons , parkingLotSenior };
    Hallway[] neighbor500    = { swim , commons , parkingLotBus };
    Hallway[] neighbor700    = { courtYard , hallwayD100 , robotics , parkingLotBack };
    Hallway[] neighborSwim   = { hallway500 , hallwayD100 , parkingLotBus };
    Hallway[] neighborRobot  = { hallway700 };
    Hallway[] neighborCourt  = { hallway100 , hallway700 , parkingLotBack };

    Hallway[]   neighborFootBall = { parkingLotBack };
    Hallway[]   neighborBack     = { field , track , parkingLotSenior };
    Hallway[]   neighborSenior   = { parkingLotBack , parkingLotBus , hallway100 , commons };
    Hallway[]   neighborBus      = { hallway500 , track , parkingLotSenior };
    Hallway[]   neighborTrack    = { parkingLotBack , parkingLotBus };
    ItemClass[] allItems         = {
            staplers , clean , ruler , binder , scissors , waterBottle , chromeBook , metalPiece , basketBall , footBall , tennisBall , soda , chip , water , lunch
    };
    //dungeons
    Dungeon     local6_11        = new Dungeon( "Rubble filled 6-11" , 10 );
    Dungeon     factory          = new Dungeon( "Run down Factory" , 30 );
    Dungeon     city             = new Dungeon( "Rubble filled City" , 35 );
    Dungeon     highway          = new Dungeon( "Highway 101" , 30 );
    Dungeon     subway           = new Dungeon( "Underground subway" , 20 );
    //bosses
    //attacks and attack[]
    //bill gates
    Attack[]    attackGates      = { new Attack( "Bill Gates" , "TRIPLE SLASH" , 10 , 12 , 15 ) , new Attack( "Bill Gates" , "ULTRA SLASH" , 7 , 15 , 20 ) , new Attack( "Bill Gates" , "LASER BLAST" , 5 , 20 , 30 ) };
    //Elon musk
    Attack[]    attacksElon      = { new Attack( "Elon musk" , "SPEAR RUSH" , 5 , 20 , 10 ) , new Attack( "Elon musk" , "REVENGE OF THE SPEAR" , 12 , 10 , 20 ) , new Attack( "Elon musk" , "ULTRA SPEAR" , 1 , 30 , 20 ) };
    //jeff bezos
    Attack[]    attacksJeff      = { new Attack( "Jeff bezos" , "ROOMBA INVASION" , 7 , 15 , 25 ) , new Attack( "Jeff bezos" , "MECH CANNON" , 5 , 20 , 30 ) , new Attack( "Jeff bezos" , "DUAL LASER" , 12 , 20 , 15 ) };
    //Mark Zuckerberg
    Attack[]    attacksMark      = { new Attack( "Mark Zuckerberg" , "FINAL SLASH" , 1 , 100 , 30 ) , new Attack( "Mark Zuckerberg" , "DUAL SLASH" , 25 , 50 , 15 ) , new Attack( "Mark Zuckerberg" , "ZERO SLASH" , 5 , 10 , 1 ) };
    //Phases and Phase[]
    Phase       Elon             = new Phase( attacksElon , 250 , "Elon Musk" );
    //boss
    //single phase
    Boss        elon             = new Boss( Elon );
    Phase       Gates            = new Phase( attackGates , 250 , "Bill Gates" );
    Boss        gates            = new Boss( Gates );
    Phase       Jeff             = new Phase( attacksJeff , 250 , "Jeff bezos" );
    Phase[]     arrTri1          = { Elon , Gates , Jeff };
    //trio
    Boss        Tri              = new Boss( arrTri1 );
    Boss        jeff             = new Boss( Jeff );
    Phase       ElonP            = new Phase( attacksElon , 500 , "Elon Musk" );
    Boss        elonP            = new Boss( ElonP );
    Phase       GatesP           = new Phase( attackGates , 500 , "Bill Gates" );
    Boss        gatesP           = new Boss( GatesP );
    Phase       JeffP            = new Phase( attacksJeff , 500 , "Bill Gates" );
    Phase[]     arrTri2          = { ElonP , GatesP , JeffP };
    Boss        Tri2             = new Boss( arrTri2 );
    Boss        jeffP            = new Boss( JeffP );

    Phase mark       = new Phase( attacksMark , 1000 , "Mark Zuckerberg" );
    Boss  Zuckerberg = new Boss( mark );

    Item     backpack   = allItems[ nbes.random( 0 , allItems.length - 1 ) ].createRandomItem( );
    //2069 attacks
    Attack   aqua       = new Attack( "Aqua" , 5 , 10 , 6 , 5 );
    Attack   freeze     = new Attack( "freeze" , 17 , 30 , 15 , 10 );
    Attack   ember      = new Attack( "Ember" , 10 , 20 , 7 , 4 );
    Attack[] allAttacks = { aqua , freeze , ember };
    //misc
    int      num        = 0;

    //Setup
    public Game( String file , String name , int speed , int placement , Object[] arr ) {
        savePath = file;
        user     = name;
        tSpeed   = speed;
        Timeline = placement;
        resetArr = arr;
        if ( resetArr != null ) {
            grabSave( );
        }

    }


    //fights giga mech
    public void fightMech( ) {
        Emmi giga = new Emmi( level2069 + 5 );
        battle( giga );
    }

    //placeHolder for other games
    public void game( ) {
        nbes.sPrintln( "NOTHING" );
    }


    //Mission Rewards
    public void missionComplete( int mission ) {
        nbes.sPrintln( "MISSION " + mission + " COMPLETE" );
        if ( mission == missionNum ) {
            num = nbes.random( mission * 10 , mission * 25 );
            missionNum++;
        } else {
            num = nbes.random( mission * 10 , mission * 30 );
        }
        nbes.sPrintln( "2069 gains " + num + " exp" );
        sendToBot( user + " Completed mission" + mission );
        exp1 += num;
        levelUp( );
        save( );
    }

    //boss fight
    public void bossFight( Boss boss ) {
        if ( ! boss.differntPhases.isEmpty( ) ) {
            HP2069 = HPmax;
            sendToBot( user + " begins to fight " + boss.differntPhases.get( 0 ).name );
            while ( ! boss.differntPhases.isEmpty( ) ) {
                Phase current = boss.differntPhases.get( 0 );
                nbes.sPrint( current.name + "'s Health " + current.getHP( ) );
                nbes.sPrintln( "2069's Health " + HP2069 );

                current.pickAttack( );
                Attack bossAttack = current.attacks[ current.curAttack ];
                attack( );
                if ( attackTime <= bossAttack.speed ) {
                    current.loseHP( choseAttack( ) );
                    current.loseHP( attackSupport( ) );
                }
                if ( current.HP > 1 && ( attackTime > bossAttack.speed || attackStun < 10 ) ) {
                    HP2069 -= bossAttack.attack( );

                }
                boss.checkArray( );
                restart( );
                sendToBot( user + "defeated a Boss" );
            }
            save( );
            exp1 += 100;
            levelUp( );
        }

    }

    //using dungeons
    public void dungeon( Dungeon dungeon ) {
        dungeon.start( );
        while ( dungeon.dungeonLength > dungeon.amountMoved ) {
            dungeon.move( );
            if ( dungeon.dungeonLength > dungeon.amountMoved ) {
                nbes.sPrintln( ( dungeon.dungeonLength - dungeon.amountMoved ) + " left to go" );
                if ( nbes.random( 1 , 10 ) == 10 ) {
                    Item place = allItems[ nbes.random( 0 , allItems.length - 1 ) ].createRandomItem( );
                    if ( backpack == null ) {
                        nbes.sPrintln( "You just found a " + place.toString( ) );
                        backpack = place;
                    } else if ( nbes.inputBool( "Current Item: " + backpack + "\nNew Item: " + place + "\nDo you want this item " ) ) {
                        backpack = place;
                    }
                }
                if ( nbes.random( missionNum , 15 ) == 15 ) {
                    battleGroup( );
                } else {
                    battle( );
                }
            }
        }
        save( );
        nbes.sPrintln( "Cleared " + dungeon.dungeonName );
    }

    //shows you what attacks you can use
    public void attack( ) {

        lastAttack = attackNum;
        attackNum  = 0;

        nbes.sPrint( "Fusion charm status: " + lastAttack );
        for ( int i = 0 ; i < allAttacks.length ; i++ ) {
            nbes.sPrint( i + "( " + allAttacks[ i ] );
        }
        nbes.sPrint( allAttacks.length + ") Fusion charm with Item (Speed: 3)" );
        System.out.println( );
        //This while loop just
        long start_Time = System.currentTimeMillis( );

        attackNum = nbes.inputInt( "Which attack? A number (0-" + ( allAttacks.length - 1 ) + ")" );
        System.out.println( );
        String  tackType;
        boolean typeDetermined = false;
        //loop that determines the type without making you want to break a .jar
        while ( ! typeDetermined ) {
            tackType = nbes.inputString( "Chose (Power) or (Speed)" ).toLowerCase( );
            if ( tackType.equals( "power" ) ) {
                attackType     = false;
                typeDetermined = true;
            } else if ( tackType.equals( "speed" ) ) {
                attackType     = true;
                typeDetermined = true;
            }
            System.out.println( );
        }


        long end_Time = System.currentTimeMillis( );
        attackTime = (int) ( ( end_Time - start_Time ) / 1000 );

        if ( attackNum < allAttacks.length ) {
            attackStun += allAttacks[ attackNum ].getStun( attackType );
            attackTime += allAttacks[ attackNum ].getSpeed( attackType );
        } else if ( lastAttack < allAttacks.length ) {
            attackTime += ( allAttacks[ lastAttack ].getSpeed( attackType ) / 2 ) + 3;
        } else {
            attackTime += 5;
        }

        attackStun += stun;
        attackTime -= speed + ( tSpeed * 10 );
        stun  = 0;
        speed = 0;
        save( );
    }

    //how much damage you did
    public int choseAttack( ) {
        num = 0;
        if ( attackNum < allAttacks.length ) {
            if ( lastAttack != attackNum ) {
                return allAttacks[ attackNum ].attack( 1 , attackType );
            } else {
                return allAttacks[ attackNum ].attack( 0.5 , attackType );
            }
        } else {
            return FusionMenu( );
        }
    }

    //cure method
    public void cure( int bonus ) {
        if ( attackType ) {
            nbes.sPrintln( "dodging Cure" );
            num = (int) ( nbes.random( 10 , 12 ) * ( ( cureTier / 4 ) + 0.75 ) + bonus );
        } else {
            nbes.sPrintln( "Cure shield" );
            num = (int) ( nbes.random( 7 , 20 ) * ( ( cureTier / 4 ) + 0.75 ) + bonus );
        }
        HP2069 += num;
        nbes.sPrintln( "2069 heals " + num + " damage" );
        if ( HP2069 > HPmax ) {
            HP2069 = HPmax;
        }
    }

    public int FusionMenu( ) {
        int bonus = 0;
        if ( lastAttack < allAttacks.length ) {
            Attack cur = allAttacks[ lastAttack ];
            bonus = nbes.random( cur.low , cur.high ) / 3;
            nbes.sPrint( cur.attackName + " is charged into this attack" );
        }
        int stuff = 2;
        nbes.sPrint( "1) Lasershot" );
        nbes.sPrint( "2) Healing Pad" );
        if ( backpack != null ) {
            nbes.sPrint( "3) " + backpack );
            stuff++;
        }
        int attack = nbes.inputInt( "Which item? (1-" + stuff + ")" );
        if ( attack == 1 ) {
            return laserShot( bonus );
        } else if ( attack == 2 ) {
            cure( bonus );
            return 0;
        } else if ( backpack != null ) {
            return backpack.useItem( bonus );
        }
        return 0;
    }

    public int laserShot( int bonus ) {
        nbes.sPrintln( "LASERSHOT" );
        num = nbes.random( 7 , 10 ) + ( bonus * 2 );
        nbes.sPrintln( "Lasershot deals " + num + " damage" );
        return num;
    }

    //return supports damage dealt
    public int attackSupport( ) {
        int total = 0;
        int hit   = maxHit;
        if ( hit > 10 ) {
            hit = 10;
        }
        nbes.sPrintln( "2077's turn" );
        long startTime = System.currentTimeMillis( );
        int  i         = 0;
        while ( startTime + 10000 > System.currentTimeMillis( ) ) {
            if ( nbes.quickTime( "Kick" , 5000 ) ) {
                i++;
            }
            if ( startTime + 10000 > System.currentTimeMillis( ) ) {
                if ( nbes.quickTime( "Punch" , 5000 ) ) {
                    i++;
                }
            }
        }
        total += ( i * hit ) / 2;
        nbes.sPrintln( "2077 Deals " + ( ( i * hit ) / 2 ) + " Damage" );

        if ( is2048joined ) {
            nbes.sPrintln( "2048's turn" );
            num = nbes.random( 1 , 2 );
            if ( num == 1 ) {
                nbes.sPrintln( "TIME WARP" );
                speed = 3;
                nbes.sPrintln( "You attacks are 3 seconds faster" );
            }
            if ( num == 2 ) {
                nbes.sPrintln( "STUN RUSH" );
                stun = 3;
                nbes.sPrintln( "You attacks are now more likely to stun someone" );
            }
        }
        if ( is2051joined ) {
            if ( nbes.inputBool( "2051: need some healing? " ) ) {
                num = nbes.random( - 10 , 20 + hit );
                nbes.sPrintln( "2069 heals " + num + " damage" );
                HP2069 += num;
                if ( HP2069 > HPmax ) {
                    HP2069 = HPmax;
                }
            }


        }
        save( );
        return total;
    }

    //chainAttack
    public int chainAttack( int HP ) {
        int     damage   = 0;
        int     speed    = 15;
        boolean overkill = false;
        nbes.sPrintln( "2051: I SEE AN OPENING" );
        nbes.sPrintln( "CHAIN ATTACK START" );
        for ( int round = 1 ; speed < attackTime ; round++ ) {
            nbes.sPrint( "Round: " + round );
            if ( ! overkill ) {
                nbes.sPrint( "2051: You have " + speed + " time left before the opening is gone" );
            }
            attack( );
            if ( speed < attackTime || overkill ) {
                damage += choseAttack( );
                damage += attackSupport( );
                speed += 5;
            }
            nbes.sPrintln( "DAMAGE: " + damage );
            if ( HP - damage <= 0 && ! overkill ) {
                nbes.sPrintln( "ENEMY DOWN" );
                nbes.sPrintln( "OVERKILL START" );
                overkill = true;
            }

        }
        nbes.sPrintln( "CHAIN ATTACK FINISH" );
        if ( overkill ) {
            exp1 += Math.abs( HP - damage );
            nbes.sPrintln( "BONUS EXP: " + Math.abs( HP - damage ) );
        }
        save( );
        return damage;
    }

    //fight enemies
    public void battle( ) {
        num = level2069;
        if ( num > MAX_EMMI ) {
            num = MAX_EMMI;
        }
        Emmi emmi = new Emmi( false , level2069 + stars );
        sendToBot( user + " just found a " + emmi.emmi_type );
        while ( emmi.emmi_HP > 0 ) {
            nbes.sPrint( "2069 health " + HP2069 );
            nbes.sPrint( emmi.emmi_type + " health " + emmi.emmi_HP );
            System.out.println( );
            emmi.emmi_prep( );
            attack( );
            if ( attackTime < emmi.curAttack.speed ) {
                emmi.emmi_HP -= choseAttack( );
                if ( attackNum != 3 ) {
                    emmi.emmi_HP -= attackSupport( );
                }
            } else {
                nbes.sPrintln( "Too slow. Pick a faster attack" );
            }
            if ( emmi.emmi_HP > 0 && ( attackTime > emmi.curAttack.speed || attackStun < emmi.emmi_num + 5 ) ) {
                HP2069 -= emmi.curAttack.attack( );
            }
            if ( is2051joined && ( attackStun < emmi.emmi_num + 2 ) ) {
                emmi.emmi_HP -= chainAttack( emmi.emmi_HP );
            }
            restart( );
        }
        exp1 += ( emmi.emmi_level * emmi.emmi_num ) * 2;
        nbes.sPrintln( "You gain " + ( emmi.emmi_level * emmi.emmi_num ) * 2 + " exp" );
        sendToBot( user + " gains " + ( emmi.emmi_level * emmi.emmi_num ) * 2 + " exp" );
        levelUp( );
        save( );
    }

    //group battle
    public void battleGroup( ) {
        num = level2069;
        if ( num > 5 ) {
            num = 5;
        }
        ArrayList < Emmi > group = new ArrayList <>( );
        for ( int i = 0 ; i < num ; i += nbes.random( 0 , 2 ) ) {
            group.add( new Emmi( false , 1 ) );
            sendToBot( user + " just found a " + group.get( i ).emmi_type );
        }

        while ( ! group.isEmpty( ) ) {
            Emmi cur = group.get( 0 );
            nbes.sPrint( "2069 health " + HP2069 );
            nbes.sPrint( cur.emmi_type + " health " + cur.emmi_HP );
            System.out.println( );
            cur.emmi_prep( );
            attack( );
            if ( attackTime < cur.curAttack.speed ) {
                cur.emmi_HP -= choseAttack( );
                if ( attackNum != 3 ) {
                    cur.emmi_HP -= attackSupport( );
                }
            } else {
                nbes.sPrintln( "Too slow. Pick a faster attack" );
            }
            if ( cur.emmi_HP < 1 ) {
                group.remove( 0 );
            }
            for ( int i = group.size( ) - 1 ; i > 0 ; i-- ) {
                HP2069 -= group.get( i ).attack( );
            }
            if ( is2051joined && attackStun < cur.emmi_num + 2 ) {
                cur.emmi_HP -= chainAttack( cur.emmi_HP );
            }
            restart( );
        }
        exp1 += 200;
        nbes.sPrintln( "You gain 200 exp" );
        sendToBot( user + " gains 200 exp" );
        levelUp( );
        save( );
    }

    //Fights emmi
    public void battle( Emmi emmi ) {
        while ( emmi.emmi_HP > 0 ) {
            nbes.sPrint( "2069 health " + HP2069 );
            nbes.sPrint( emmi.emmi_type + " health " + emmi.emmi_HP );
            System.out.println( );
            emmi.emmi_prep( );
            attack( );
            if ( attackTime < emmi.curAttack.speed ) {
                emmi.emmi_HP -= choseAttack( );
                if ( attackNum != 3 ) {
                    emmi.emmi_HP -= attackSupport( );
                }

            } else {
                nbes.sPrintln( "Too slow. Pick a faster attack" );
            }
            if ( emmi.emmi_HP > 1 && ( attackTime > emmi.curAttack.speed || attackStun < ( emmi.emmi_num + 5 ) ) ) {
                HP2069 -= emmi.attack( );
            } else if ( is2051joined && ( attackStun < emmi.emmi_num + 2 ) ) {
                emmi.emmi_HP -= chainAttack( emmi.emmi_HP );
            }
        }

        exp1 += ( emmi.emmi_level * emmi.emmi_num ) * 2;
        nbes.sPrintln( "You gain " + ( emmi.emmi_level * emmi.emmi_num ) * 2 + " exp" );
        sendToBot( user + " gains " + ( emmi.emmi_level * emmi.emmi_num ) * 2 + " exp" );
        levelUp( );
        save( );
    }

    //level up
    public void levelUp( ) {
        while ( exp1 >= levelR1 ) {
            nbes.sPrintln( "LEVEL UP" );
            nbes.sPrintln( level2069 + " --> " + ( level2069 + 1 ) );
            nbes.sPrintln( "2069: max health +1" );
            HPmax++;
            level2069++;
            levelR1 = 20 * ( level2069 * level2069 ) / 2;
            nbes.sPrintln( "2069 has " + ( levelR1 - exp1 ) + " exp till leveling up" );
            sendToBot( user + " Just leveled up. They are now level " + level2069 + ". They got " + levelR1 + " till leveling up" );
            save( );
        }


    }

    //Game Over
    public void restart( ) {
        if ( HP2069 < 0 ) {
            if ( ! is2048joined && nbes.random( missionNum * 2 , 30 ) == 30 ) {
                HP2069 = HPmax;
                nbes.sPrintln( "2048: can't handle things on your own" );
                nbes.sPrintln( "2069: 2048!" );
                nbes.sPrintln( "*2048 has joined the team*" );
                is2048joined = true;
                writeTeam( is2051joined , true , Timeline );
                sendToBot( "2048 just joined " + user + "'s party" );
            } else if ( ! is2051joined && nbes.random( missionNum * 2 , 30 ) == 30 ) {
                HP2069 = HPmax;
                nbes.sPrintln( "2051: Playtime is over" );
                nbes.sPrintln( "2069: 2051!" );
                nbes.sPrintln( "*2051 has joined the team*" );
                is2051joined = true;
                writeTeam( true , is2048joined , Timeline );
                sendToBot( "2051 just joined " + user + "'s party" );
            } else {
                nbes.sPrintln( "The world around you begins to fade to black" );
                nbes.sPrintln( "???: Welcome back to this world of nothingness " );
                nbes.sPrintln( "2069: no..." );
                nbes.sPrintln( "???: I will help you get out of this" );
                nbes.sPrintln( "2077:I see nothing wrong with that" );
                nbes.sPrintln( "2069: Please help us" );
                String choice = "How are you doing";
                while ( ! choice.equals( "START" ) ) {
                    choice = nbes.inputString( "Type ¨START¨ to continue" );
                }
                System.out.println( );
                sendToBot( user + " has fallen. Good thing ??? is here" );
                save( );
                game( );
            }

        }
    }

    //Saves
    public void save( ) {
        Object[] arrList = new Object[] { missionNum , HPmax , level2069 , levelR1 , exp1 , aqua.attackTier , freeze.attackTier , cureTier , ember.attackTier , maxHit };
        FileRead.Edit( savePath , arrList );
        System.gc( );
    }

    //Grabs Data
    public void grabSave( ) {

        if ( nbes.inputBool( "Would you like to overwrite a save file? (Returns the file to the start of the game)" ) ) {
            if ( nbes.inputBool( "Are you sure?" ) ) {
                FileRead.Edit( savePath , resetArr );
            }
        }
        Object[] Save = FileRead.Read( savePath );
        sendToBot( user + " Just loaded up the game using " + savePath );
        for ( int s = 0 ; s < Save.length ; s++ ) {

            if ( Save[ s ] != null ) {
                int val = 0;
                if ( nbes.strIsInt( Save[ s ].toString( ) ) ) {
                    val = Integer.parseInt( Save[ s ].toString( ) );
                }
                if ( s == 0 ) {
                    missionNum = val;
                }
                if ( s == 1 ) {
                    HPmax = val;
                }
                if ( s == 2 ) {
                    level2069 = val;
                }
                if ( s == 3 ) {
                    levelR1 = val;
                }
                if ( s == 4 ) {
                    exp1 = val;
                }
                if ( s == 5 ) {
                    aqua.attackTier = val;
                }
                if ( s == 6 ) {
                    freeze.attackTier = val;
                }
                if ( s == 7 ) {
                    cureTier = val;
                }
                if ( s == 8 ) {
                    ember.attackTier = val;
                }
                if ( s == 9 ) {
                    maxHit = val;
                }
                int[] team = readTeam( );
                is2051joined = team[ 0 ] <= Timeline;
                is2048joined = team[ 1 ] <= Timeline;
            }

        }
        System.out.flush( );
    }

    //gotcha
    public void pull( ) {

        if ( exp1 >= 25 ) {
            nbes.sPrint( "how much exp would you like to use? " );
            num = nbes.inputInt( "how much exp would you like to use? (Current Exp:" + exp1 + " )" );

            if ( exp1 < num ) {
                num = exp1;
            }
            int pull_num = num / 25;
            num -= exp1;

            while ( pull_num > 0 ) {
                int tier = nbes.random( 1 , nbes.random( 2 , nbes.random( 3 , nbes.random( 4 , nbes.random( 5 , nbes.random( 6 , 7 ) ) ) ) ) );
                nbes.sPrintln( "Tier " + tier + " pull" );
                if ( tier == 1 ) {
                    HPmax += 2;
                    nbes.sPrintln( "2069's max Hp increased by 2" );
                    sendToBot( user + "'s Max HP increased by 2" );
                } else if ( tier < 6 ) {
                    num = nbes.random( 1 , 4 );
                    //Ember level up
                    if ( num == 4 ) {
                        if ( tier > ember.attackTier ) {
                            nbes.sPrintln( "ember leveled up" );
                            nbes.sPrintln( ember.attackTier + " --> " + tier );
                            sendToBot( user + "'s ember leveled up " + ember.attackTier + " --> " + tier );
                            ember.attackTier = tier;
                        }
                    }
                    // Cure level up
                    if ( num == 3 ) {
                        if ( tier > cureTier ) {
                            nbes.sPrintln( "Cure leveled up" );
                            nbes.sPrintln( cureTier + " -->" + tier );
                            sendToBot( user + "'s Cure leveled up " + cureTier + " --> " + tier );
                            cureTier = tier;
                        }

                    }
                    //aqua level up
                    if ( num == 1 ) {
                        if ( tier > aqua.attackTier ) {
                            nbes.sPrintln( "Aqua leveled up" );
                            nbes.sPrintln( aqua.attackTier + " --> " + tier );
                            sendToBot( user + "'s Aqua leveled up " + aqua.attackTier + " --> " + tier );
                            aqua.attackTier = tier;
                        }
                    }
                    //Laser level up
                    if ( num == 2 ) {
                        if ( tier > freeze.attackTier ) {
                            nbes.sPrintln( "Freeze leveled up" );
                            nbes.sPrintln( freeze.attackTier + " --> " + tier );
                            sendToBot( user + "'s Laser leveled up " + freeze.attackTier + " --> " + tier );
                            freeze.attackTier = tier;

                        }
                    }
                } else if ( tier == 6 ) {
                    maxHit += 1;
                    nbes.sPrintln( "The power of supporting members increased by 1" );
                    sendToBot( user + "'s power of supporting members increased by 1" );
                } else {
                    maxHit += 2;
                    nbes.sPrintln( "The power of supporting members increased by 2" );
                    sendToBot( user + "'s power of supporting members increased by 2" );
                }
                pull_num -= 1;
                save( );
            }
        } else {
            nbes.sPrintln( "YOU NEED MORE EXP POOR PERSON" );
        }

    }

    public int useItem( ) {
        if ( backpack.dur > 0 ) {
            if ( backpack.isHeal ) {
                HP2069 += useItem( );
                return 0;
            } else {
                return useItem( );
            }
        }
        return 0;
    }


}