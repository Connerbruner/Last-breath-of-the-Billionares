import java.util.ArrayList;
import java.util.Collections;

class Game extends FileRead {
    String   savePath;
    int      Timeline;
    Object[] resetArr;
    String   name;

    // Array vars (placed in Lbob.txt)
    int HPmax        = 50;
    int missionNum   = 10;
    int metaleft = 100;
    int level2051    = 1;
    int exp1         = 0;
    int levelR1      = 20;
    int supportPower = 5;

    //non saved vars
    int lastAttack = 5;
    int HP         = HPmax;
    int attackNum  = 5;

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
    static ArrayList<Attack> allAttacks = new ArrayList<>( 3);
    static {
        allAttacks.add( shot );
        allAttacks.add( stab );
        allAttacks.add( potion );
    }


    //Setup
    public Game( String gameName , String file , Object[] arr ) {
        savePath = file;
        resetArr = arr;
        name     = gameName;
        for ( int i = 0 ; i < unlocked.length ; i++ ) {
            if ( Hero.allHeros[ i ].isUnlocked( missionNum ) ) {
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

            attackNum = allAttacks.size();
            nbes.sPrint( "Multiplier: " + Nbes.musicMultiplier + "\n" );
            for ( int i = 0 ; i < allAttacks.size() ; i++ ) {
                nbes.sPrint( i + "( " + allAttacks.get( i ).toString( ) );
            }
            nbes.sPrint( "Which attack" );
            Nbes.tSpeed = speed;

            while ( attackNum >= allAttacks.size() ) {
                if ( Nbes.strIsInt( Nbes.INPUT.getText( ) ) ) {
                    attackNum = Integer.parseInt( Nbes.INPUT.getText( ) );
                }
            }
            double power = 1;
            if ( attackNum == lastAttack ) {
                power = 0.5;
            }
            return allAttacks.get( attackNum ).attack( new Object[] { power , Math.abs( power - 1 ) } );
        }
        return 0;


    }


    //return supports damage dealt
    public int attackSupport( ) {
        int total = 0;
        for ( Hero hero : unlocked ) {
            if ( hero != null && Nbes.wavFile.isOpen() && Nbes.random( 0,1 )==1) {
                nbes.sPrintln( hero.heroName+"'s Turn" );
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
            nbes.sPrintln( emmi.emmi_type + " Is going for a attack" );
            HP -= emmi.attack( );
            
        }
        if ( ! Nbes.wavFile.isOpen() || HP < 0 ) {
            restart( );
        }
        Nbes.wavFile.close();
        exp1 += (( emmi.emmi_level+1) * emmi.emmi_num ) * 2;
        nbes.sPrintln( "You gain " + ( emmi.emmi_level * emmi.emmi_num ) * 2 + " exp" );
        metaleft--;
        levelUp( );
        save( );
    }

    public void battle( Boss boss ) {

        Nbes.overcomeMe( ).start();

        while ( Nbes.wavFile.isOpen() && ! boss.differentPhases.isEmpty( ) ) {
            nbes.sPrint( boss.name + " health " + boss.differentPhases.get( 0 ).HP );
            boss.differentPhases.get( 0 ).HP -= attack( ) + attackSupport( );
            boss.checkArray( );
                nbes.sPrintln( boss.name + " Is going for a attack" );
                if ( ! nbes.quickTime( "CounterAttack" , 2000 ) ) {
                    HP -= boss.differentPhases.get( 0 ).attack( );
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
    public int battle(int bouns) {
        nbes.sPrintln("Combo This enemy till the song is up");
        int damage=0;
        while( Nbes.wavFile.isOpen() )  {
            damage+=attack( ) + attackSupport( );
        }
        double ratio = (damage+bouns)/100;
        nbes.sPrintln("TOTAL DAMAGE: "+damage);
        nbes.sPrintln("TOTAL POINTS: "+(damage+bouns)*ratio);
        return (int) ((damage+bouns)*ratio);
    }


    //level up
    public void levelUp( ) {
        while ( exp1 >= levelR1 ) {
            nbes.sPrintln( "LEVEL UP" );
            nbes.sPrintln( level2051 + " --> " + ( level2051 + 1 ) );
            nbes.sPrintln( "2051: max health +1" );
            HPmax++;
            level2051++;
            levelR1 = 20 * ( level2051 * level2051 ) / 2;
            nbes.sPrintln( "2051 has " + ( levelR1 - exp1 ) + " exp till leveling up" );
            save( );
        }


    }

    //Game Over
    public void restart( ) {

    }


    //Saves
    public void save( ) {
        Object[] arrList = new Object[] { missionNum ,metaleft, HPmax , level2051 , levelR1 , exp1 , stab.attackTier , potion.attackTier , shot.attackTier , supportPower };
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
                if( s == 1 ) {
                    metaleft= val;
                }
                if ( s == 2 ) {
                    HPmax = val;
                }
                if ( s == 3 ) {
                    level2051 = val;
                }
                if ( s == 4 ) {
                    levelR1 = val;
                }
                if ( s == 5 ) {
                    exp1 = val;
                }
                if ( s == 6 ) {
                    stab.attackTier = val;
                }
                if ( s == 7 ) {
                    potion.attackTier = val;
                }
                if ( s == 8 ) {
                    shot.attackTier = val;
                }
                if ( s == 9 ) {
                    supportPower = val;
                }


            }
        }

    }


}