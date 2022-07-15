import java.util.ArrayList;


public class Lbop extends Game {

    public Lbop( String file, int placement , Object[] arr) {
        super("Last breath of the People", file , placement ,arr);
    }

    public void game( ) {
        HP2069=HPmax;
        levelUp();
        save();
        if ( missionNum == 0 ) {
            nbes.sPrintln( "UNKNOWN_PERSON: send 100 M.E.T.A s after them" );
            nbes.sPrintln( "random person: YES SIR" );
            nbes.sPrintln( "UNKNOWN_PERSON: They will never live past this" );
            missionNum += nbes.random(90,110);

        }
        else if( missionNum < 10) {
            nbes.sPrintln( "random person: sir sir they lived past all the M.E.T.As" );
            nbes.sPrintln( "UNKNOWN_PERSON: NO ISSUE HERE" );
            nbes.sPrintln( "UNKNOWN_PERSON: they simply were made stall time" );
            nbes.sPrintln( "UNKNOWN_PERSON: NOW ITS ALL MINE" );
            nbes.sPrintln( "*UNKNOWN_PERSON pushes a lever*" );
            nbes.sPrintln( "*A blackout sweeps the earth*" );
            nbes.sPrintln( "THE END??????" );
        }
        nbes.sPrintln( "M.E.T.As remaining: " + missionNum );
        nbes.sPrint( "1) Explore the city" );
        nbes.sPrint( "2) Upgrade skills" );
        int choice = nbes.inputInt( "What would you like to do?" );
        if(choice==1)
        {
            missionNum-=battle(nbes.random( 1,3));
            if(missionNum%10==0)
            {
                battle();
                missionNum--;
            }
        }
        if(choice==2)
        {
            pull();
        }
        game();

    }

    public int battle( int targets ) {
        nbes.sPrintln( "2069: We have " + targets + " M.E.T.As on us" );
        nbes.sPrintln( "2077: Soon its gonna be zero" );
        int distance = nbes.random( 100 , 200 );

        ArrayList < Emmi > group = new ArrayList <>( );
        for ( int i = 0 ; i < targets ; i++ ) {
            group.add( new Emmi( true , level2069 / 3 ) );
        }
        int deadTargets = 0;
        while ( ! group.isEmpty( ) ) {
            nbes.sPrintln( "2069: They("+group.size()+") are only " + distance + " Feet away" );
            nbes.sPrintln( "2069 health " + HP2069+"\n"+group.get( 0 ).emmi_type + " health " + group.get( 0 ).emmi_HP );
            attack( );
            group.get( 0 ).emmi_HP -= choseAttack( );
            distance -= attackTime/1.5;
            if ( distance > 0 ) {
                distance += attackStun / 2;
                if ( group.get( 0 ).emmi_HP < 1 ) {
                    nbes.sPrintln( "2069: good news: " + group.get( 0 ).emmi_type + " is down" );
                    group.remove( 0 );
                    deadTargets++;
                } else if ( distance % 10 == 0 ) {
                    targets--;
                    nbes.sPrintln( "2069: good news: we lost the " + group.get( 0 ).emmi_type );
                    group.remove( 0 );
                    if ( distance > 100 && !group.isEmpty( ) ) {
                        nbes.sPrintln( "2077: Watch out they are making a push to catch up" );
                        distance /= 1.5;
                    }
                } else {
                    if(distance<group.size()*40 && nbes.random( 0,3 )==3)
                    {
                        HP2069 -= group.get( nbes.random( 0 , group.size( ) - 1 ) ).attack( );
                    }
                    if ( HP2069 < 1 ) {
                        restart( );
                    }
                }
            } else {
                nbes.sPrintln( "2069: The M.E.T.A's have caught up" );
                nbes.sPrintln( "2077: Let me take care of them" );
                String word = group.get( nbes.random( 0 , group.size( ) - 1 ) ).emmi_type;
                int    i    = 0;
                while ( ! group.isEmpty( ) && nbes.quickTime( word , group.size( ) * 1000 ) ) {
                    i+=maxHit/3;
                    if ( i > group.get( 0 ).emmi_num ) {
                        i = 0;
                        nbes.sPrintln( "2077: The " + group.get( 0 ).emmi_type + " is down" );
                        group.remove( 0 );
                        deadTargets++;
                    }
                    word = group.get( nbes.random( 0 , group.size( ) - 1 ) ).emmi_type;
                }
                if ( !group.isEmpty( ) ) {
                    restart( );
                }
            }
        }
        exp1+=((deadTargets*50)*level2069)/3;
        nbes.sPrintln( "2069 gains "+((deadTargets*50)*level2069)/3+" exp" );
        return deadTargets;
    }
    public void battle() {
        Emmi emmi = new Emmi( level2069 );
        nbes.sPrintln( "2069: We have a" + emmi.emmi_type + " on us" );
        nbes.sPrintln( "2077: Soon its gonna be zero" );
        int distance = nbes.random( 30 , 100 );
        while ( emmi.emmi_HP>0) {
            nbes.sPrintln( "2069: They are only " + distance + " Feet away" );
            nbes.sPrintln( "2069 health " + HP2069+"\n"+emmi.emmi_type + " health " + emmi.emmi_HP );
            attack( );
            emmi.emmi_HP -= choseAttack( );
            distance -= attackTime;

            if ( distance > 0 ) {
                    distance += attackStun / 2;
                    HP2069 -= emmi.attack();
                    if ( HP2069 < 1 ) {
                        restart( );
                    }
                } else {
                nbes.sPrintln( "2069: The M.E.T.A has caught up" );
                nbes.sPrintln( "2077: Let me deal with them" );
                while ( emmi.emmi_HP>0 && nbes.quickTime( emmi.emmi_type ,3000 ) ) {
                    emmi.emmi_HP-=maxHit*2;
                }
                restart( );
            }
        }
        exp1+=300;
        nbes.sPrintln( "2069 gains 300 exp" );
    }

    @Override
    public void restart( ) {
        if ( ! is2048joined && nbes.random( missionNum * 2 , 30 ) == 30 ) {
            HP2069 = HPmax;
            nbes.sPrintln( "2048: can't handle things on your own" );
            nbes.sPrintln( "2069: 2048!" );
            nbes.sPrintln( "*2048 has joined the team*" );
            is2048joined = true;
            writeTeam( is2051joined , true , Timeline );
        } else if ( ! is2051joined && nbes.random( missionNum * 2 , 30 ) == 30 ) {
            HP2069 = HPmax;
            nbes.sPrintln( "2051: Playtime is over" );
            nbes.sPrintln( "2069: 2051!" );
            nbes.sPrintln( "*2051 has joined the team*" );
            is2051joined = true;
            writeTeam( true , is2048joined , Timeline );
        } else {
            nbes.sPrintln( "The world around you begins to fade to black" );
            nbes.sPrintln( "???: Its just sad to see you fall" );
            nbes.sPrintln( "???: Sadly I cant put you out of your suffering" );

            String choice = "How are you doing";
            while ( ! choice.equals( "START" ) ) {
                choice = nbes.inputString( "Type ¨START¨ to continue " );
            }
            System.out.println( );
            save( );
            game( );
        }
    }

    @Override
    public void pull( ) {
        nbes.sPrintln( "???: So you are tried of losing? huh" );
        if ( ! allMaxedOut( ) ) {
            nbes.sPrintln( "???: What do you want upgraded" );
            for ( int i = 0 ; i < allAttacks.length ; i++ ) {
                nbes.sPrint( i + "( " + allAttacks[ i ] );
            }
            int index=nbes.inputInt( "Which attack? A number (0-" + ( allAttacks.length - 1 ) + ")" );
            Attack choice = allAttacks[index];
            while ( choice.attackTier == 5 ) {
                nbes.sPrintln( "???: Sorry that move is already maxed out" );
                index=nbes.inputInt( "Which attack? A number (0-" + ( allAttacks.length - 1 ) + ")" );
                choice = allAttacks[index];
            }
            int power = nbes.inputInt( "What tier? A number (" + choice.attackTier+1 + "-5 )" );
            while ( power > 6 || power < choice.attackTier) {
                power = nbes.inputInt( "What tier? A number (" + choice.attackTier+1 + "-5 )" );
            }
            int min = ((power-choice.attackTier)^2)*nbes.random(20,30);
            int offer = ((power-choice.attackTier)^2)*50;
            int yourOffer = nbes.inputInt("What is your offer? (max: "+exp1+")");
            if(yourOffer>exp1)
            {
                yourOffer=exp1;
            }
            if(yourOffer>=min)
            {
                nbes.sPrintln("???: Deal");
                allAttacks[index].attackTier=power;
                save();
            }
            else if(exp1>offer){
                if(nbes.inputBool("???: Nope, but how about "+offer))
                {
                    nbes.sPrintln("???: Nice");
                    allAttacks[index].attackTier=power;
                    save();
                }
            }
            else {
                nbes.sPrintln("???: Your too poor for this kid");
            }

        } else {
            nbes.sPrintln( "???: Sounds like a skill issue" );
        }

    }

    public boolean allMaxedOut( ) {
        boolean maxed = true;
        for ( int i = 0 ; i < allAttacks.length && maxed ; i++ ) {
            maxed = allAttacks[ i ].attackTier == 5;
        }
        return maxed;
    }
}
