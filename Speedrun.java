import java.io.*;
import java.util.ArrayList;

class Speedrun extends Game {
    int    time;
    Race[] allRaces = {
            new Race( "VS random emmi" , new Emmi( Emmi.OTHERS[ 0 ] ) ) ,
            new Race( "VS Random boss" , new Emmi( Emmi.MINI_BOSSES[ 0 ] ) ) ,
            new Race( "Underground subway" , subway ) ,
            new Race( "VS Bill gates" , gates ) ,
    };


    public Speedrun( ) {
        super( "Speedruning",null  , 0 ,null);
        HPmax        = 60;
        maxHit       = 7;
        is2051joined = true;
        is2048joined = true;
    }

    public void game( ) {
        for ( int i = 0 ; i < allRaces.length ; i++ ) {
            nbes.sPrint( i + ") " + allRaces[ i ].mission + " Current record: " + readTime( i ) + " sec" );
        }
        int  index = nbes.inputInt( "Which race?" );
        Race race  = allRaces[ index ];
        HP2069 = HPmax;
        long startTime = System.currentTimeMillis( );
        if ( race.isEmmi ) {
            Emmi emmi = new Emmi( race.vsEmmi );

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
                }
                if ( emmi.emmi_HP > 1 && ( attackTime > emmi.curAttack.speed || attackStun < ( emmi.emmi_num + 5 ) ) ) {
                    HP2069 -= emmi.attack();
                } else if ( ( attackStun < emmi.emmi_num + 2 )) {
                    emmi.emmi_HP -= chainAttack( emmi.emmi_HP );
                }
                if ( HP2069 <= 0 ) {
                    nbes.sPrintln( "You have fallen" );
                    nbes.sPrintln( "Returning to menu" );
                    game();
                }
            }

        }
        if ( race.isBoss ) {
            Boss boss = race.vsBoss;
            if ( ! boss.differntPhases.isEmpty( ) ) {
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
                }
            } else {
                nbes.sPrintln( "Boss not available" );
                game( );
            }
        }
        if ( race.isDungeon ) {
            Dungeon dungeon = race.vsDungeon;
            dungeon.start( );
            while ( dungeon.dungeonLength > dungeon.amountMoved ) {
                dungeon.move( );
                if ( dungeon.dungeonLength > dungeon.amountMoved ) {
                    nbes.sPrintln( ( dungeon.dungeonLength - dungeon.amountMoved ) + " left to go" );
                    battle( );
                }

            }
        }
        time = (int) ( System.currentTimeMillis( ) - startTime ) / 1000;
        if ( time < readTime( index ) ) {
            writeScores( index );
        } else {
            nbes.sPrintln( "RUN COMPLETE TIME: " + time );
        }
        if ( nbes.inputBool( "continue?" ) ) {
            game( );
        }


    }

    public void writeScores( int line ) {
        try {
            File                 txt      = new File( "Files/Race scores.txt" );
            FileReader           fileRead = new FileReader( txt );
            BufferedReader       reader   = new BufferedReader( fileRead );
            ArrayList < String > arr      = new ArrayList <>( );
            for ( int i = 0 ; i < 4 ; i++ ) {
                if ( i == line ) {
                    arr.add(  " ; " + time );
                    reader.readLine( );
                } else {
                    arr.add( reader.readLine( ) );
                }
            }
            reader.close( );
            File       fileToBeModified = new File( "Files/Race scores.txt" );
            FileWriter writer           = new FileWriter( fileToBeModified );
            for ( String s : arr ) {
                writer.write( s + "\n" );
            }
            writer.close( );
        } catch ( IOException e ) {
            e.printStackTrace( );
        }

    }

    public int readTime( int line ) {
        try {
            File           txt      = new File( "Files/Race scores.txt" );
            FileReader     fileRead = new FileReader( txt );
            BufferedReader reader   = new BufferedReader( fileRead );

            for ( int r = 0 ; r < line ; r++ ) {
                reader.readLine( );
            }
            String info = reader.readLine( );
            int    i;
            for ( i = 0; (int) ( info.charAt( i ) ) != 59 ; i++ ) ;
            reader.close( );
            if ( nbes.strIsInt( info.substring( i + 2 ) ) ) {
                return Integer.parseInt( info.substring( i + 2 ) );
            }
            return 0;
        } catch ( IOException e ) {
            e.printStackTrace( );
            return 0;
        }
    }

    public void battle( ) {
        Emmi emmi = new Emmi( false , 5 );
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

            }
            if ( emmi.emmi_HP > 1 && ( attackTime > emmi.curAttack.speed || attackStun < ( emmi.emmi_num + 5 ) ) ) {
                HP2069 -= emmi.attack();
            } else if ( is2051joined &&  ( attackStun < emmi.emmi_num + 2 ) ) {
                emmi.emmi_HP -= chainAttack( emmi.emmi_HP );
            }
            if ( HP2069 <= 0 ) {
                nbes.sPrintln( "You have fallen" );
                nbes.sPrintln( "Returning to menu" );
                game( );
            }
        }

    }

}