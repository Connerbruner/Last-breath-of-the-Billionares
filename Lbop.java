import java.util.ArrayList;


public class Lbop extends Game {

    public Lbop( String file , String name , int speed , int placement , Object[] arr ) {
        super( file , name , speed , placement , arr );
    }

    public void game( ) {
        if ( missionNum == 0 ) {
            nbes.sPrintln( "UNKNOWN_PERSON: send 1000 M.E.T.A s after them" );
            nbes.sPrintln( "random person: YES SIR" );
            nbes.sPrintln( "UNKNOWN_PERSON: They will never live past this" );
            missionNum += 1001;
        }
        nbes.sPrintln( "M.E.T.As remaining: " + missionNum );
        nbes.sPrint( "1) Explore the city" );
        nbes.sPrint( "2) Upgrade skills" );
        int choice = nbes.inputInt( "What would you like to do?" );
        if(choice==1)
        {
            missionNum-=battle(nbes.random( 1,5 ));
        }
        if(choice==2)
        {
            pull();
        }

    }

    public int battle( int targets ) {
        nbes.sPrintln( "2069: We have " + targets + " M.E.T.As on us" );
        nbes.sPrintln( "2077: Soon its gonna be zero" );
        int distance = nbes.random( 30 , 100 );

        ArrayList < Emmi > group = new ArrayList <>( );
        for ( int i = 0 ; i < targets ; i++ ) {
            group.add( new Emmi( true , level2069 / 3 ) );
        }
        int deadTargets = 0;
        while ( ! group.isEmpty( ) ) {
            nbes.sPrintln( "2069: They are only " + distance + " Feet away" );
            nbes.sPrint( "2069 health " + HP2069 );
            nbes.sPrint( group.get( 0 ).emmi_type + " health " + group.get( 0 ).emmi_HP );
            attack( );
            group.get( 0 ).emmi_HP -= choseAttack( );
            distance -= attackTime;

            if ( distance > 0 ) {
                distance += attackStun / 2;
                if ( group.get( 0 ).emmi_HP < 1 ) {
                    nbes.sPrintln( "2069: good news: " + group.get( 0 ).emmi_type + " is down" );
                    group.remove( 0 );
                    deadTargets++;
                } else if ( distance % 10 == 0 || distance > 100 ) {
                    targets--;
                    nbes.sPrintln( "2069: good news: we lost the " + group.get( 0 ).emmi_type );
                    group.remove( 0 );
                    if ( distance > 100 ) {
                        nbes.sPrintln( "2077: Watch out they are making a push to catch up" );
                        distance /= 1.5;
                    }
                } else {
                    HP2069 -= group.get( nbes.random( 0 , group.size( ) - 1 ) ).attack( );
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
                    i++;
                    if ( i > group.get( 0 ).emmi_num ) {
                        i = 0;
                        nbes.sPrintln( "2077: The " + group.get( 0 ).emmi_type + " is down" );
                        group.remove( 0 );
                        deadTargets++;
                    }
                    word = group.get( nbes.random( 0 , group.size( ) - 1 ) ).emmi_type;
                }
                if ( group.isEmpty( ) ) {
                    return deadTargets;
                }
                restart( );
            }
        }
        return deadTargets;
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
            nbes.sPrintln( "???: Its just sad to see you fall" );
            nbes.sPrintln( "???: Sadly I cant put you out of your suffering" );

            String choice = "How are you doing";
            while ( ! choice.equals( "START" ) ) {
                choice = nbes.inputString( "Type ¨START¨ to continue " );
            }
            System.out.println( );
            sendToBot( user + " has fallen. Good thing ??? is here" );
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
            int power = nbes.inputInt( "What tier? A number (" + choice.attackTier + "-5 )" );
            while ( power < 6 || power < choice.attackTier) {
                power = nbes.inputInt( "What tier? A number (" + choice.attackTier + "-5 )" );
            }
            int min = ((power-choice.attackTier)*power)*(power* nbes.random(20,30));
            int offer = ((power-choice.attackTier)*power)*(power*50);
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
