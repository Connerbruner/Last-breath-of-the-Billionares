//Main class
class Lbob extends Game {


    //obj


    public Lbob( String file , String name , int speed , int placement,Object[] arr ) {
        super( file , name , speed , placement,arr);
    }


    //Starts up 2069
    @Override
    public void game( ) {

        //Runs mission forever
        while ( true ) {
            levelUp( );
            HP2069 = HPmax;
            nbes.sPrint( "Type 1 -> " + missionNum + " to try that Mission" );
            //Tells you how to roll the gotcha
            if ( missionNum > 1 ) {
                nbes.sPrint( "Type 0 to trade exp for new moves" );
            }
            int choice = nbes.inputInt( "what would you like to do?" );

            if ( choice < missionNum && choice > 0 ) {
                stars = nbes.inputInt( "How many stars would like to add (makes mission harder)" );
            } else {
                stars = 0;
            }
            System.out.println( );
//mission 1
            if ( choice == 1 ) {
                nbes.sPrintln( "Mission 1: The Awakening of The Revolution" );
                nbes.sPrintln( "A Roomba appears " );

                nbes.sPrintln( "2069's turn" );
                nbes.sPrint( "Fusion charm status: " + lastAttack );
                for ( int i = 0 ; i < allAttacks.length ; i++ ) {
                    nbes.sPrint( i + ") " + allAttacks[ i ].toString( ) );
                }
                nbes.sPrint( allAttacks.length + ") Fusion charm with Item (Speed: 3)" );
                System.out.println( );

                attackNum = nbes.inputInt( "Chose a number corresponding to the attack you want to use (be fast) (0-" + allAttacks.length + ")" );

                String style = nbes.inputString( "Chose Out (Power) or Out (Speed) (Speed is weaker and faster and Power is Powerful but slow" ).toLowerCase( );
                while ( ! style.equals( "power" ) && ! style.equals( "speed" ) ) {
                    style = nbes.inputString( "Chose Out (Power) or Out (Speed) (Speed is weaker and faster and Power is Powerful but slow" ).toLowerCase( );
                }
                System.out.println( );
                nbes.quickTime( "Kick" , 3000 );
                nbes.quickTime( "Punch" , 3000 );
                nbes.sPrintln( "Roomba fades away" );


                dungeon( subway );
                if ( stars > 5 ) {
                    dungeon( local6_11 );
                    fightMech( );
                }
                missionComplete( 1 );
            }

//Mission 2
            if ( ( choice == 2 ) && ( missionNum >= 2 ) ) {
                nbes.sPrintln( "Mission 2: First Encounters" );
                if ( stars > 5 ) {
                    dungeon( city );
                }
                if ( stars > 2 ) {
                    dungeon( local6_11 );
                }
                bossFight( gates );
                missionComplete( 2 );

            }
//Mission 3
            if ( ( missionNum >= 3 ) && ( choice == 3 ) ) {
                nbes.sPrintln( "MISSION 3: Rest In The Rubble" );
                dungeon( local6_11 );
                bossFight( elon );
                dungeon( subway );
                if ( stars > 2 ) {
                    dungeon( local6_11 );
                }
                missionComplete( 3 );

            }
//Mission 4
            if ( ( missionNum >= 4 ) && ( choice == 4 ) ) {
                nbes.sPrintln( "Mission 4: 101 battles" );
                dungeon( highway );
                if ( stars > 2 ) {
                    dungeon( local6_11 );
                }
                if ( stars > 5 ) {
                    dungeon( subway );
                }
                bossFight( jeff );
                missionComplete( 4 );

            }
//Mission 5
            if ( ( missionNum >= 5 ) && ( choice == 5 ) ) {
                nbes.sPrintln( "Mission 5: Rematch Cubed" );
                bossFight( Tri );
                missionComplete( 5 );

            }
//Mission 6
            if ( ( missionNum >= 6 ) && ( choice == 6 ) ) {
                nbes.sPrintln( "Mission 6: Battle on the highway" );

                dungeon( highway );
                if ( stars > 2 ) {
                    dungeon( local6_11 );
                }
                bossFight( jeffP );
                missionComplete( 6 );

            }
//Mission 7
            if ( missionNum >= 7 && choice == 6 ) {
                nbes.sPrintln( "Mission 7: Face off in the factory" );

                dungeon( factory );
                if ( stars > 2 ) {
                    dungeon( local6_11 );
                }
                bossFight( elonP );
                missionComplete( 7 );

            }
//Mission 8
            if ( ( missionNum >= 8 ) && ( choice == 8 ) ) {
                nbes.sPrintln( "MISSION 8: Highway to the future" );
                dungeon( highway );
                if ( stars > 2 ) {
                    dungeon( local6_11 );
                }
                bossFight( gatesP );
                missionComplete( 8 );

            }
//Mission 9
            if ( ( missionNum >= 9 ) && ( choice == 9 ) ) {
                nbes.sPrintln( "Mission 9: Face-Off On The Grand Tower" );
                bossFight( Tri2 );
                missionComplete( 9 );
            }
//Mission 10
            if ( ( missionNum >= 10 ) && ( choice == 10 ) ) {
                nbes.sPrintln( "Mission 10: 2 Sides Of The Same Coin" );
                bossFight( Zuckerberg );
                missionComplete( 10 );
            }

//Gotcha system
            if ( ( choice == 0 ) && ( missionNum > 1 ) ) {
                pull( );
            }
            save( );
        }

    }

    //don't pass this comment
}
