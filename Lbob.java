public class Lbob extends Game {
    public Lbob( String file ) {
        super( "Last breath of the Billionaires" , file , new Object[] { 0 , 50 , 1 , 20 , 0 , 1 , 1 , 1 , 5 } );

    }

    public Lbob( String file , String name ) {
        super( name , file , new Object[] { 0 , 50 , 1 , 20 , 0 , 1 , 1 , 1 , 5 } );

    }

    Attack[] attackGates = { new Attack( "Bill Gates" , "TRIPLE SLASH" , 10 , 12 , 15 ) , new Attack( "Bill Gates" , "ULTRA SLASH" , 7 , 15 , 20 ) , new Attack( "Bill Gates" , "LASER BLAST" , 5 , 20 , 30 ) };
    Attack[] attacksElon = { new Attack( "Elon musk" , "SPEAR RUSH" , 5 , 20 , 10 ) , new Attack( "Elon musk" , "REVENGE OF THE SPEAR" , 12 , 10 , 20 ) , new Attack( "Elon musk" , "ULTRA SPEAR" , 1 , 30 , 20 ) };
    Attack[] attacksJeff = { new Attack( "Jeff bezos" , "ROOMBA INVASION" , 7 , 15 , 25 ) , new Attack( "Jeff bezos" , "MECH CANNON" , 5 , 20 , 30 ) , new Attack( "Jeff bezos" , "DUAL LASER" , 12 , 20 , 15 ) };
    Attack[] attacksMark = { new Attack( "Mark Zuckerberg" , "FINAL SLASH" , 1 , 100 , 30 ) , new Attack( "Mark Zuckerberg" , "DUAL SLASH" , 25 , 50 , 15 ) , new Attack( "Mark Zuckerberg" , "ZERO SLASH" , 5 , 10 , 1 ) };
    Phase    Elon        = new Phase( attacksElon , 250 , "Elon Musk" );
    Phase    Gates       = new Phase( attackGates , 250 , "Bill Gates" );
    Phase    Jeff        = new Phase( attacksJeff , 250 , "Jeff bezos" );
    Phase    mark        = new Phase( attacksMark , 1000 , "Mark Zuckerberg" );

    Boss jeff       = new Boss( Jeff );
    Boss elon       = new Boss( Elon );
    Boss gates      = new Boss( Gates );
    Boss zuckerberg = new Boss( mark );

    final Mission[][] missions    = new Mission[][] {
            new Mission[] {
                    new Mission( "Mission 0: Just us Three" , ( ) -> {
                        nbes.sPrintln( "Mission 0: Just us Three" );
                        for ( int i = 3 ; i > 0 ; i-- ) {
                            battle( new Emmi( false , level2051 ) );
                        }
                        missionComplete( 0 , true );
                    } ) } ,
            new Mission[] {
                    new Mission( "Mission 1: Roomba Ambush" , ( ) -> {
                        nbes.sPrintln( "Mission 1: Roomba Ambush" );
                        for ( int i = 3 ; i > 0 ; i-- ) {
                            battle( new Emmi( Emmi.OTHERS[ 0 ] ) );
                        }
                        missionComplete( 1 , false );
                    } ) ,
                    new Mission( "Mission 2: Screaming at the Data Center" , ( ) -> {
                        nbes.sPrintln( "Mission 2: Screaming at the Data Center" );
                        for ( int i = missionNum ; i > 0 ; i-- ) {
                            battle( new Emmi( Emmi.OTHERS[ Nbes.random( 4 , 5 ) ] ) );
                        }
                        missionComplete( 1 , false );
                    } ) ,
                    new Mission( "Mission 3: Not a Mini Problem" , ( ) -> {
                        nbes.sPrintln( "Mission 3: Not a Mini Problem" );
                        battle( new Emmi( Emmi.MINI_BOSSES[ 1 ] ) );
                        missionComplete( 1 , false );
                    } ) ,
                    new Mission( "Mission 4: Brawl in the Mall" , ( ) -> {
                        nbes.sPrintln( "Mission 4: Brawl in the Mall" );
                        vist( AreaStorage.mall );
                        missionComplete( 1 , false );
                    } ) ,
                    new Mission( "Mission 5: Contact with the known" , ( ) -> {
                        nbes.sPrintln( "Mission 5: Contact with the known" );
                        battle( gates );
                        missionComplete( 1 , true );
                    } ) ,
            } ,
            new Mission[] {
                    new Mission( "Mission 6: Who is a good boy? Not them" , ( ) -> {
                        nbes.sPrintln( "Mission 6: Who is a good boy? Not them" );
                        for ( int i = missionNum ; i > 0 ; i-- ) {
                            battle( new Emmi( Emmi.OTHERS[ 1 ] ) );
                        }
                        missionComplete( 2 , false );
                    } ) ,
                    new Mission( "Mission 7: mini mech's big brother" , ( ) -> {
                        nbes.sPrintln( "Mission 7: mini mech's big brother" );
                        battle( new Emmi( Emmi.MINI_BOSSES[ 2 ] ) );
                        missionComplete( 2 , false );
                    } ) ,
                    new Mission( "Mission 8: Double Trouble" , ( ) -> {
                        nbes.sPrintln( "Mission 8: Double Trouble" );
                        battle( new Emmi( Emmi.MINI_BOSSES[ Nbes.random( 1 , Emmi.MINI_BOSSES.length - 1 ) ] ) );
                        battle( new Emmi( Emmi.MINI_BOSSES[ 1 ] ) );
                        missionComplete( 2 , false );
                    } ) ,
                    new Mission( "Mission 9: Tallest Tower" , ( ) -> {
                        nbes.sPrintln( "Mission 9: Tallest Tower" );
                        vist( AreaStorage.skyscraper );
                        missionComplete( 2 , false );

                    } ) ,
                    new Mission( "Mission 10: No ones Story has a Happy Ending" , ( ) -> {
                        nbes.sPrintln( "Mission 10: No ones Story has a Happy Ending" );
                        battle( jeff );
                        missionComplete( 2 , true );

                    } ) ,
            } ,
            new Mission[] {
                    new Mission( "Mission 11: Who's turf is this?" , ( ) -> {
                        nbes.sPrintln( "Mission 11: Who's turf is this?" );
                        for ( int i = missionNum ; i > 0 ; i-- ) {
                            battle( new Emmi( Emmi.OTHERS[ 1 ] ) );
                        }
                        missionComplete( 3 , false );
                    } ) ,
                    new Mission( "Mission 12: These Paper Weights Pack a Punch" , ( ) -> {
                        nbes.sPrintln( "Mission 12: These Paper Weights Pack a Punch" );
                        for ( int i = missionNum ; i > 0 ; i-- ) {
                            battle( new Emmi( Emmi.OTHERS[ Nbes.random( 5 , 4 ) ] ) );
                        }
                        missionComplete( 3 , false );
                    } ) ,
                    new Mission( "Mission 13: Biggest brother" , ( ) -> {
                        nbes.sPrintln( "Mission 13: Biggest brother" );
                        battle( new Emmi( Emmi.MINI_BOSSES[ 0 ] ) );
                        missionComplete( 3 , false );
                    } ) ,
                    new Mission( "Mission 14: Factory tour" , ( ) -> {
                        nbes.sPrintln( "Mission 14: Factory tour" );
                        vist( AreaStorage.factory );
                        missionComplete( 3 , false );
                    } ) ,
                    new Mission( "Mission 15: All this way with Nothing to Show" , ( ) -> {
                        nbes.sPrintln( "Mission 15: All this way with Nothing to Show" );
                        battle( elon );
                        missionComplete( 3 , true );
                    } ) ,
            } ,
            new Mission[] {
                    new Mission( "Mission 16: Fridge Frenzy" , ( ) -> {
                        nbes.sPrintln( "Mission 16: Fridge Frenzy" );
                        for ( int i = missionNum ; i > 0 ; i-- ) {
                            battle( new Emmi( Emmi.OTHERS[ 3 ] ) );
                        }
                        missionComplete( 4 , false );
                    } ) ,
                    new Mission( "Mission 17: Drag race" , ( ) -> {
                        nbes.sPrintln( "Mission 17: Drag race" );
                        for ( int i = missionNum / 2 ; i > 0 ; i-- ) {
                            battle( new Emmi( Emmi.MINI_BOSSES[ 3 ] ) );
                        }
                        missionComplete( 4 , false );
                    } ) ,
                    new Mission( "Mission 18: Battle of the Bots" , ( ) -> {
                        nbes.sPrintln( "Mission 18: Battle of the Bots" );
                        for ( int i = missionNum ; i > 0 ; i-- ) {
                            battle( new Emmi( false , level2051 ) );
                        }
                        missionComplete( 4 , false );
                    } ) ,
                    new Mission( "Mission 19: Raid on the Rollercoaster" , ( ) -> {
                        nbes.sPrintln( "Mission 19: Raid on the Rollercoaster" );
                        missionComplete( 4 , false );
                    } ) ,
                    new Mission( "Mission 20: Last Breath of the Billionaires" , ( ) -> {
                        nbes.sPrintln( "Mission 20: Last Breath of the Billionaires" );
                        battle( zuckerberg );
                        missionComplete( 4 , true );
                    } ) ,
            } ,

    };
    final static Attack[]    allUnlock   = new Attack[] {
            new Attack( "2051" , "Kick" , 10 , 20 , 10 ) ,
            new Attack( "2051" , "Punch" , 5 , 15 , 7 ) ,
            new Attack( "2051" , "RUSH" , 0 , 1 , 1 ) ,
            new Attack( "2051" , "METEOR PORTAL" , 15 , 40 , 40 ) ,
            new Attack( "2051" , "TIME WARP" , - 5 , 10 , - 5 ) ,
    };
    final Attack[]    attacks2077 = new Attack[] {
            new Attack( "2077" , "Kick" , 10 , 20 , 10 ) ,
            new Attack( "2077" , "Punch" , 5 , 15 , 7 ) ,
    };
    final Attack[]    attacks2048 = {
            new Attack( "2048" , "RUSH" , 0 , 1 , 1 ) ,
            new Attack( "2048" , "METEOR PORTAL" , 15 , 40 , 40 ) ,
            new Attack( "2048" , "TIME WARP" , - 5 , 10 , - 5 )
    };
    final Boss[]      playable    = {
            new Boss( new Phase[] {
                    new Phase( attacks2048 , 20 , "2048" ) ,
                    new Phase( attacks2048 , 80 , "2048" ) ,
                    new Phase( attacks2048 , 75 , "2048 (Last breath)" )
            } ) ,
            new Boss( new Phase[] {
                    new Phase( allAttacks , 50 , "2069" ) ,
                    new Phase( attacks2048 , 50 , "2069" ) ,
                    new Phase( attacks2077 , 50 , "2069 (Last breath)" ) ,
            } ) ,
            new Boss( new Phase[] {
                    new Phase( attacks2077 , 25 , "2077" ) ,
                    new Phase( attacks2077 , 75 , "2077" ) ,
                    new Phase( attacks2077 , 75 , "2077 (Last breath)" )
            } ) ,
    };

    public void missionComplete( int mission , boolean boss ) {
        if ( missionNum == mission ) {
            if ( boss ) {
                nbes.sPrintln( "NEW MISSIONS UNLOCKED" );
                missionNum++;
            }
            if ( allAttacks.size( ) <= allUnlock.length ) {
                allAttacks.add( allUnlock[ missionNum ] );
                nbes.sPrintln( "NEW ATTACK " + allUnlock[ missionNum ].toString( ) );
            }
        }
        exp1 += mission * 100;
        nbes.sPrintln( "You gain " + ( mission * 100 ) + " exp" );
    }

    //shows you what attacks you can use
    public int attack( ) {
        lastAttack = attackNum;
        int speed = Nbes.tSpeed;
        Nbes.tSpeed = 1;
        Nbes.INPUT.setText( "" );
        Nbes.INPUT.setEditable( true );
        Nbes.INPUT.requestFocus( );

        attackNum = allAttacks.size( );
        Thread thread = new Thread( ()-> {
            for ( int i = 0 ; i < allAttacks.size( ) ; i++ ) {
                nbes.sPrint( i + "( " + allAttacks.get( i ).toString( ) );
            }
            nbes.sPrint( "Which attack" );
        } );
        thread.start();
        while ( attackNum >= allAttacks.size( ) ) {
            if ( Nbes.strIsInt( Nbes.INPUT.getText( ) ) ) {
                attackNum = Integer.parseInt( Nbes.INPUT.getText( ) );
            }
        }
        Nbes.tSpeed = speed;
        thread.stop();
        double power = 1;
        if ( attackNum == lastAttack ) {
            power = 0.5;
        }
        return allAttacks.get( attackNum ).attack( new Object[] { power , Math.abs( power - 1 ) } );
    }

    //return supports damage dealt
    public int attackSupport( ) {
        int total = 0;
        for ( Hero hero : unlocked ) {
            if ( hero != null && Nbes.random( 0 , 1 ) == 1 ) {
                nbes.sPrintln( hero.heroName + "'s Turn" );
                int attack = hero.attack.attack( ) * supportPower / 2;
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
        long startTime = System.currentTimeMillis( );
        while ( startTime + BATTLE_TIME * 1000 > System.currentTimeMillis( ) && emmi.emmi_HP > 0 && HP > 0 ) {
            nbes.sPrintln( emmi.emmi_type + " health " + emmi.emmi_HP + "\n2051 health " + HP + "\nTIME LEFT " + ( startTime + BATTLE_TIME * 1000 - System.currentTimeMillis( ) ) / 1000);
            emmi.emmi_HP -= ( attack( ) + attackSupport( ) );
            nbes.sPrintln( emmi.emmi_type + " Is going for a attack" );
            emmi.curAttack=emmi.emmi_attacks[Nbes.random(0,emmi.emmi_attacks.length-1)];
            if(!nbes.quickTime( "CounterAttack",emmi.curAttack.speed )) {
                HP -= emmi.attack( );
            }


        }
        if ( startTime + BATTLE_TIME * 1000 < System.currentTimeMillis( ) || HP < 0 ) {
            restart( );
        }
        exp1 += ( ( emmi.emmi_level + 1 ) * emmi.emmi_num ) * 2;
        nbes.sPrintln( "You gain " + ( emmi.emmi_level * emmi.emmi_num ) * 2 + " exp" );
        levelUp( );
        save( );
    }

    public void battle( Boss boss ) {
        boss.resetPhases( );
        long startTime = System.currentTimeMillis( );
        while ( startTime + BATTLE_TIME * 1000 > System.currentTimeMillis( ) && ! boss.differentPhases.isEmpty( ) && HP > 0 ) {

            nbes.sPrintln( boss.name + " health " + boss.getHP() + "\n2051 health " + HP + "\nTIME LEFT " + ( startTime + BATTLE_TIME * 1000 - System.currentTimeMillis( ) ) / 1000);

            boss.differentPhases.get( 0 ).HP -= attack( ) + attackSupport( );
            boss.checkArray( );
            nbes.sPrintln( boss.name + " Is going for a attack" );
            if ( ! nbes.quickTime( "CounterAttack" , 2000 ) ) {
                HP -= boss.differentPhases.get( 0 ).attack( );
            }
            boss.checkArray( );
        }
        if ( startTime + BATTLE_TIME * 1000 < System.currentTimeMillis( ) || HP < 0 ) {
            restart( );
        }
        exp1 += 500;
        nbes.sPrintln( "You gain 500 exp" );
        levelUp( );
        save( );
    }

    public void battle( Boss player , Boss boss ) {
        boss.resetPhases( );
        player.resetPhases( );
        long startTime = System.currentTimeMillis( );

        while ( startTime + BATTLE_TIME * 1000 > System.currentTimeMillis( ) && ! boss.differentPhases.isEmpty( ) ) {

            nbes.sPrint( boss.name + " health " + boss.differentPhases.get( 0 ).HP );
            nbes.sPrint( player.name + " health " + player.differentPhases.get( 0 ).HP );
            nbes.sPrintln( "TIME LEFT " + ( startTime + BATTLE_TIME * 1000 - System.currentTimeMillis( ) ) / 1000 );

            boss.differentPhases.get( 0 ).HP -= player.differentPhases.get( 0 ).attack( );
            boss.checkArray( );
            nbes.sPrintln( boss.name + " Is going for a attack" );
            if ( ! nbes.quickTime( "CounterAttack" , 2000 ) ) {
                player.differentPhases.get( 0 ).HP -= boss.differentPhases.get( 0 ).attack( );
            }
        }
        if ( startTime + BATTLE_TIME * 1000 < System.currentTimeMillis( ) || HP < 0 ) {
            restart( );
        }
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

    @Override
    public void game( ) {
        grabSave( );
        while ( true ) {
            for ( int i = 0 ; i < Hero.allHeros.length ; i++ ) {
                Hero.allHeros[ i ].isUnlocked( missionNum );
            }
            for ( int i = 0 ; i <= missionNum && i < missions.length ; i++ ) {
                nbes.sPrint( "Area " + i );
            }
            int choice1 = nbes.inputInt( "Which area are you heading to?" );
            while ( choice1>missionNum ) {
                choice1 = nbes.inputInt( "Which area are you heading to?" );
            }
            for ( int i = 0 ; i < missions[ choice1 ].length ; i++ ) {
                nbes.sPrint( i + ") " + missions[ choice1 ][ i ].name );
            }
            int choice2 = nbes.inputInt( "What mission do you want to do?" );
            missions[ choice1 ][ choice2 ].mission.go( );
        }

    }

    @Override
    public void grabSave( ) {
        super.grabSave( );
        for ( int i = 0 ; i < missionNum && i < allUnlock.length ; i++ ) {
            allAttacks.add( allUnlock[ i ] );
        }
    }

    @Override
    public void restart( ) {
        nbes.sPrintln( "GAME OVER" );
        game( );
    }

    public void train( ) {
        HP = HPmax;
        Boss per1 = playable[ Nbes.random( 0 , playable.length - 1 ) ];
        Boss per2;
        while ( ( per2 = playable[ Nbes.random( 0 , playable.length - 1 ) ] ) != per1 ) ;
        nbes.sPrintln( "2051: TRAINING TIME" );
        nbes.sPrintln( per1.name + ": You ready?" );
        nbes.sPrintln( per2.name + ": Yup, Lets go" );
        battle( per1 , per2 );
        for ( int i = per1.differentPhases.size( ) ; i > - 1 ; i-- ) {
            if ( Nbes.random( 0 , 3 ) == 3 ) {
                for ( int j = 0 ; j < allAttacks.size( ) ; j += Nbes.random( 1 , 3 ) ) {
                    nbes.sPrintln( allAttacks.get( j ).attackName + " Tier +1" );
                    allAttacks.get( j ).attackTier++;
                }
            } else if ( Nbes.random( 0 , 3 ) == 3 ) {
                nbes.sPrintln( "support power +3" );
                nbes.sPrintln( "Max HP +3" );
                supportPower += 3;
                HPmax += 3;
            } else if ( Nbes.random( 0 , 3 ) == 3 ) {
                nbes.sPrintln( "support power +1" );
                supportPower++;
            } else {
                nbes.sPrintln( "Max HP +1" );
                HPmax++;
            }
        }
    }

    public void vist( Area area ) {
        while ( area.current != area.areaHallways[ area.areaHallways.length - 1 ] ) {
            area.move( );
            battle( new Emmi( false , level2051 ) );
        }
    }
}