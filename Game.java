class Game extends FileRead {
    String   savePath;
    int      Timeline;
    Object[] resetArr;
    String   name;

    // Array vars (placed in Lbob.txt)
    int HPmax        = 50;
    int missionNum   = 10;
    int level2051    = 1;
    int exp1         = 0;
    int levelR1      = 20;
    int supportPower = 5;

    //non saved vars
    int lastAttack = 5;
    int HP         = HPmax;
    int attackNum  = 5;
    int stars      = 0;
    static Hero[] unlocked = new Hero[ Hero.allHeros.length ];


    Attack[] attackGates = { new Attack( "Bill Gates" , "TRIPLE SLASH" , 10 , 12 , 15 ) , new Attack( "Bill Gates" , "ULTRA SLASH" , 7 , 15 , 20 ) , new Attack( "Bill Gates" , "LASER BLAST" , 5 , 20 , 30 ) };
    Attack[] attacksElon = { new Attack( "Elon musk" , "SPEAR RUSH" , 5 , 20 , 10 ) , new Attack( "Elon musk" , "REVENGE OF THE SPEAR" , 12 , 10 , 20 ) , new Attack( "Elon musk" , "ULTRA SPEAR" , 1 , 30 , 20 ) };
    Attack[] attacksJeff = { new Attack( "Jeff bezos" , "ROOMBA INVASION" , 7 , 15 , 25 ) , new Attack( "Jeff bezos" , "MECH CANNON" , 5 , 20 , 30 ) , new Attack( "Jeff bezos" , "DUAL LASER" , 12 , 20 , 15 ) };
    Attack[] attacksMark = { new Attack( "Mark Zuckerberg" , "FINAL SLASH" , 1 , 100 , 30 ) , new Attack( "Mark Zuckerberg" , "DUAL SLASH" , 25 , 50 , 15 ) , new Attack( "Mark Zuckerberg" , "ZERO SLASH" , 5 , 10 , 1 ) };
    Phase    Elon        = new Phase( attacksElon , 250 , "Elon Musk" );
    Phase    Gates       = new Phase( attackGates , 250 , "Bill Gates" );
    Phase    Jeff        = new Phase( attacksJeff , 250 , "Jeff bezos" );
    Phase[]  arrTri1     = { Elon , Gates , Jeff };
    Phase    ElonP       = new Phase( attacksElon , 500 , "Elon Musk" );
    Phase    GatesP      = new Phase( attackGates , 500 , "Bill Gates" );
    Phase    JeffP       = new Phase( attacksJeff , 500 , "Bill Gates" );
    Phase[]  arrTri2     = { ElonP , GatesP , JeffP };
    Phase    mark        = new Phase( attacksMark , 1000 , "Mark Zuckerberg" );
    Boss     Tri         = new Boss( arrTri1 );
    Boss     Tri2        = new Boss( arrTri2 );
    Boss     jeff        = new Boss( Jeff );
    Boss     jeffP       = new Boss( JeffP );
    Boss     elon        = new Boss( Elon );
    Boss     elonP       = new Boss( ElonP );
    Boss     gates       = new Boss( Gates );
    Boss     gatesP      = new Boss( GatesP );
    Boss     Zuckerberg  = new Boss( mark );

    Area city = new Area( "Huge City",new Hallway[]{
        new Hallway( "Huge Skysraper" )
    } );

    //2051 attacks
    static Attack   stab       = new Attack( "Quick Stab" , 3 , 7 , 2 ) {
        @Override
        public int attack( Object[] args ) {
            nbes.lastsPrint = "";
            num             = this.calcDamage( );
            if ( nbes.quickTime( "2051" , speed * 1000 ) ) {
                num += 10;
            }
            nbes.sPrintln( "2051 deals " + num + " damage" );
            return num;
        }
    };
    static Attack   potion     = new Attack( "Potion" , 1 , 10 , 5 );
    static Attack   shot       = new Attack( "Sniper Shot" , 15 , 40 , 20 );
    static Attack[] allAttacks = { stab , potion , shot };


    //Setup
    public Game( String gameName , String file , int placement , Object[] arr ) {
        savePath = file;
        Timeline = placement;
        resetArr = arr;
        name     = gameName;
        for ( int i = 0 ; i < unlocked.length ; i++ ) {
            if ( Hero.allHeros[ i ].isUnlocked( placement ) ) {
                unlocked[ i ] = Hero.allHeros[ i ];
            }
        }
        Hero.writeTeam( 11 );
    }

    //placeHolder for other games
    public void game( ) {
        nbes.sPrintln( "NOTHING" );
    }

    //shows you what attacks you can use
    public int attack( ) {
        if ( Nbes.wavFile.isOpen() ) {
            lastAttack = attackNum;
            int speed = Nbes.tSpeed;
            Nbes.tSpeed = 1;
            Nbes.INPUT.setText( "" );
            Nbes.INPUT.setEditable( true );
            Nbes.INPUT.requestFocus( );

            attackNum = allAttacks.length;
            nbes.sPrint( "Multiplier: " + Nbes.musicMultiplier + "\n" );
            for ( int i = 0 ; i < allAttacks.length ; i++ ) {
                nbes.sPrint( i + "( " + allAttacks[ i ].toString( ) );
            }
            nbes.sPrint( "Which attack" );
            Nbes.tSpeed = speed;

            while ( attackNum >= allAttacks.length ) {
                if ( Nbes.strIsInt( Nbes.INPUT.getText( ) ) ) {
                    attackNum = Integer.parseInt( Nbes.INPUT.getText( ) );
                }
            }
            double power = 1;
            if ( attackNum == lastAttack ) {
                power = 0.5;
            }
            return allAttacks[ attackNum ].attack( new Object[] { power , Math.abs( power - 1 ) } );
        }
        return 0;


    }


    //return supports damage dealt
    public int attackSupport( ) {
        int total = 0;
        for ( Hero hero : unlocked ) {
            if ( hero != null && Nbes.wavFile.isOpen() ) {
                int attack = hero.attack( ) * supportPower / 2;
                if ( hero.isHealing ) {
                    HP += attack;
                } else {
                    total += attack;
                }
            }

        }
        return total;
    }


    //fight enemies
    public void battle( Emmi emmi ) {
        Nbes.overcomeMe( ).start();
        while ( Nbes.wavFile.isOpen() && emmi.emmi_HP > 0 ) {
            nbes.sPrintln( emmi.emmi_type + " health " + emmi.emmi_HP );
            nbes.sPrintln( "2051 health " + HP );
            emmi.emmi_HP -= ( attack( ) + attackSupport( ) );
            if ( Nbes.random( 0 , emmi.emmi_HP / 10 ) == emmi.emmi_HP / 10 && emmi.emmi_HP > 0 ) {
                nbes.sPrintln( emmi.emmi_type + " Is going for a attack" );
                if ( ! nbes.quickTime( "Counter" , 7500 - emmi.emmi_HP * 50 ) ) {
                    HP -= emmi.attack( );
                }
            }
        }
        if ( ! Nbes.wavFile.isOpen() || HP < 0 ) {
            restart( );
        }
        Nbes.wavFile.close();
        exp1 += (( emmi.emmi_level+1) * emmi.emmi_num ) * 2;
        nbes.sPrintln( "You gain " + ( emmi.emmi_level * emmi.emmi_num ) * 2 + " exp" );
        levelUp( );
        save( );
    }

    public void battle( Boss boss ) {

        Nbes.overcomeMe( ).start();

        while ( Nbes.wavFile.isOpen() && ! boss.differentPhases.isEmpty( ) ) {
            nbes.sPrint( boss.name + " health " + boss.differentPhases.get( 0 ).HP );
            boss.differentPhases.get( 0 ).HP -= attack( ) + attackSupport( );
            boss.checkArray( );
            if ( Nbes.random( 0 , boss.differentPhases.get( 0 ).HP / 10 ) == boss.differentPhases.get( 0 ).HP / 10 && boss.differentPhases.get( 0 ).HP > 0 ) {
                nbes.sPrintln( boss.name + " Is going for a attack" );
                if ( ! nbes.quickTime( "Counter" , 2000 ) ) {
                    HP -= boss.differentPhases.get( 0 ).attack( );
                }
            }
        }
        if ( !Nbes.wavFile.isOpen() || HP < 0 ) {
            restart( );
        }
        Nbes.wavFile.close();
        exp1 += 500;
        nbes.sPrintln( "You gain 500 exp" );
        levelUp( );
        save( );
    }


    //level up
    public void levelUp( ) {
        while ( exp1 >= levelR1 ) {
            nbes.sPrintln( "LEVEL UP" );
            nbes.sPrintln( level2051 + " --> " + ( level2051 + 1 ) );
            nbes.sPrintln( "2051: max health +1" );
            level2051++;
            levelR1 = 20 * ( level2051 * level2051 ) / 2;
            nbes.sPrintln( "2051 has " + ( levelR1 - exp1 ) + " exp till leveling up" );
            save( );
        }


    }

    //Game Over
    public void restart( ) {
        HP = HPmax;
        while ( Nbes.random( 0 , 10 ) == 10 ) {
            int i = Nbes.random( 0 , Hero.allHeros.length );
            if ( ! Hero.allHeros[ i ].isUnlocked ) {
                Hero.allHeros[ i ].isUnlocked = true;
                Hero.writeTeam( Timeline );
                nbes.sPrintln( Hero.allHeros[ i ].heroName + " is here to save you" );
                return;
            }
        }
        nbes.sPrintln( "The world around you begins to fade to black" );
        nbes.sPrintln( "???: Hello friend its been a bit " );
        nbes.sPrintln( "2051: You Gotta be kidding\n\nYou showing up at my death bed" );
        nbes.sPrintln( "???: HA I would be the type to taunt you before you die" );
        nbes.sPrintln( "???: I wish\n\nNaw I want to see you suffer more" );
        nbes.sPrintln( "???: Have fun..." );
        String choice = "How are you doing";
        while ( ! choice.equals( "START" ) ) {
            choice = nbes.inputString( "Type ¨START¨ to continue" );
        }
        save( );
        game( );
    }


    //Saves
    public void save( ) {
        Object[] arrList = new Object[] { missionNum , HPmax , level2051 , levelR1 , exp1 , stab.attackTier , potion.attackTier , shot.attackTier , supportPower };
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
        for ( int s = 0 ; s < Save.length ; s++ ) {

            if ( Save[ s ] != null ) {
                int val = 0;
                if ( Nbes.strIsInt( Save[ s ].toString( ) ) ) {
                    val = Integer.parseInt( Save[ s ].toString( ) );
                }
                if ( s == 0 ) {
                    missionNum = val;
                }
                if ( s == 1 ) {
                    HPmax = val;
                }
                if ( s == 2 ) {
                    level2051 = val;
                }
                if ( s == 3 ) {
                    levelR1 = val;
                }
                if ( s == 4 ) {
                    exp1 = val;
                }
                if ( s == 5 ) {
                    stab.attackTier = val;
                }
                if ( s == 6 ) {
                    potion.attackTier = val;
                }
                if ( s == 7 ) {
                    shot.attackTier = val;
                }
                if ( s == 8 ) {
                    supportPower = val;
                }

            }
        }

    }


}