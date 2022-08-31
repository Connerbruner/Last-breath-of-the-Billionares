public class Lbob extends Game {
    public Lbob( String file ) {
        super( "Last breath of the Billionaires" , file , 1 , new Object[] { 0 , 50 , 1 , 20 , 0 , 1 , 1 , 1 , 5 } );
    }

    @Override
    public void game( ) {
        stars=nbes.inputInt("Add stars to Make the game harder (1 star = 1 extra enemy level");
        if ( missionNum == 0 ) {
            Nbes.playSound( "Files/DROPOUT.wav" );
            int speed = Nbes.tSpeed;
            Nbes.tSpeed = 7;
            chatText( "2051: What you want?" , 750 );
            chatText( "???: Nothing" , 750 );
            chatText( "???: Your life It just seams pointless" , 1200 );
            chatText( "2051: Your not wrong" , 300 );
            chatText( "2051: Everyone's out to get me" , 1100 );
            chatText( "???: You think I am any different?" , 1200 );
            chatText( "2051: No. Not at all" , 300 );
            chatText( "???: Killing all these people. Kinda Boring" , 1250 );
            chatText( "???: Lets mix it up\n\nshall we?" , 750 );
            chatText( "???: I hold the power to EVERYTHING with a motherboard" , 1250 );
            chatText( "???: Lets see how you hold up against a roomba" , 1000 );
            chatText( "2051: Easy as pie" , 0 );
            Nbes.tSpeed = speed;
            battle( Emmi.OTHERS[ 0 ] );
            if(HP==HPmax)
            {
                nbes.sPrintln("???: Wow\n\nZero damage taken\n\nMakes me look a loser");
                nbes.sPrintln("???: Have 100xp\n\nYou will need it");
                exp1+=100;
            }
            else {
                nbes.sPrintln("???: Seems like you are overrated");
                nbes.sPrintln("???: Roombas are not that strong");
            }
            missionNum++;
        }
        if(missionNum==1)
        {
            nbes.sPrintln("???: Hey wish I could stay and chat but I got a meeting at the top of that Tower");
            nbes.sPrintln("2051: Just because I have nothing to do I'll chase you");
            nbes.sPrintln("???: Sounds fun\n\nRace Your there");
            nbes.sPrintln("2051: I know that tower like the back off my hand");
            int move=0;
            while(AreaStroage.skyscraper.current!=AreaStroage.floor10) {
                AreaStroage.skyscraper.move();
                move++;
                battle(new Emmi(false,level2051+stars));
            }
            if(move<5) {
                nbes.sPrintln("???: I see my minions did not stop you at all");
            } else {
                nbes.sPrintln("2051: Geez\n\nYour Fast. I am a little rusty");
                nbes.sPrintln("???: Thats obvious");
            }
            missionNum++;

        }
        if(missionNum==2) {
            nbes.sPrintln("???: Sorry to leave you but\n\nHopefully this will be FUN");
            nbes.sPrintln("*A MECH CRASHES DOWN*");
            nbes.sPrintln("???: This SHOULD BE FUN");
            nbes.sPrintln("2051: It Should");
            battle(new Emmi(Emmi.MINI_BOSSES[1]));
            nbes.sPrintln("???: I see you are done\n\nGood Good");
            nbes.sPrintln("2051: Was not that bad\n\nBattled worse");
            nbes.sPrintln("2051: You done playing with me?");
            nbes.sPrintln("???: NOPE never");
            nbes.sPrintln("2051: ");
        }
    }


    public void chatText( String str , int wait ) {
        nbes.sPrint( str );
        nbes.lastsPrint = "";
        Nbes.wait( wait );
    }


}