import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

abstract class Game extends FileRead {
    String   savePath;
    Object[] resetArr;
    String   name;
    // Array vars (placed in Lbob.txt)
    int      HPmax        = 50;
    int      missionNum   = 0;
    int      level2051    = 1;
    int      exp1         = 0;
    int      levelR1      = 20;
    int      supportPower = 5;
    //non saved vars
    int      lastAttack   = 5;
    int      HP           = HPmax;
    int      attackNum    = 5;
    static final int BATTLE_TIME = 180;
    static ArrayList < Hero > unlocked        = new ArrayList <>( );



    //2051 attacks
    static Attack               stab       = new Attack( "Quick Stab" , 3 , 7 , 2 ) {
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
    static Attack               potion     = new Attack( "Potion" , 1 , 10 , 5 );
    static Attack               shot       = new Attack( "Sniper Shot" , 15 , 40 , 20 );
    static ArrayList < Attack > allAttacks = new ArrayList <>( );

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
    }

    abstract void game( );
    //Game Over
    public void restart( ) {
    }
    //Saves
    public void save( ) {
        Object[] arrList = new Object[] { missionNum  , HPmax , level2051 , levelR1 , exp1 , stab.attackTier , potion.attackTier , shot.attackTier , supportPower };
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
                for ( int i = 0 ; i < missionNum && i < Lbob.allUnlock.length ; i++ ) {
                    allAttacks.add( Lbob.allUnlock[ i ] );
                }
            }
        }
    }
}

interface Mvoid {
    void go( );
}
class Mission {
    String name;
    Mvoid mission;
    public Mission(String n,Mvoid m) {
        name=n;
        mission=m;
    }}

class Sucp extends Game {
    static final JFrame extra = new JFrame( "" );
    int points      = 0;
    int round       = 1;
    int roundPoints = 0;

    public Sucp( ) {
        super( "SUPER ULTRA COLOR PICKER" , null , null );
    }

    public void game( ) {
        nbes.sPrintln( "HELLO EVERYONE MY NAME IS GUY FIERI" );
        nbes.sPrintln( "YOUR WATCHING SUPER ULTRA COLOR PICKER CHALLENGE" );
        nbes.sPrintln( "Our challenger will start by picking their name" );
        nbes.sPrintln( "Hello " + getName( ) );
        while ( ( roundPoints > 0 ) || ( round == 1 ) ) {
            nbes.sPrintln( "Round: " + round );
            round++;
            roundPoints = playGame( );
            points += roundPoints;
        }
        nbes.sPrintln( "G A M E    O V E R" );
        nbes.sPrintln( "You played " + round + " rounds\n You earned: " + points + " points" );
    }

    // methods
    // color picker
    public int[] getColor( ) {
        Color newColor = JColorChooser.showDialog( extra , "SUPER ULTRA COLOR PICKER CHALLENGE" , extra.getBackground( ) );
        return new int[] { newColor.getRed( ) , newColor.getGreen( ) , newColor.getBlue( ) };
    }


    // gamer
    public int playGame( ) {
        int   R   = Nbes.random( 0 , 255 );
        int   G   = Nbes.random( 0 , 255 );
        int   B   = Nbes.random( 0 , 255 );
        Color guy = new Color( R , G , B );
        nbes.sPrintln( "Your goal is to find a color closest to this color" );
        showColor( guy );
        long  startTime = System.currentTimeMillis( );
        int[] gotcolor  = getColor( );
        int   time      = (int) ( ( System.currentTimeMillis( ) - startTime ) / 30 );
        nbes.sPrintln( "Lets see how you did" );
        int off = 400 - ( round * 5 );
        off -= Math.abs( gotcolor[ 0 ] - R );
        off -= Math.abs( gotcolor[ 1 ] - G );
        off -= Math.abs( gotcolor[ 2 ] - B );
        int score = (int) ( ( off - time ) * ( ( round / 10 ) + 0.9 ) );
        if ( score < 0 ) {
            nbes.sPrintln( "You did not score any points :(" );
            return 0;
        } else {
            nbes.sPrintln( "You scored: " + score + " points" );
        }
        return score;
    }

    public String getName( ) {
        nbes.sPrintln( "Use this color picker to tell us your name" );
        int[]  gotcolor = getColor( );
        String name;
        if ( ( gotcolor[ 0 ] == 255 ) && ( gotcolor[ 1 ] == 255 ) && ( gotcolor[ 2 ] == 255 ) ) {
            name = "C1nner aka LORD WHITE BREAD";
        } else if ( ( gotcolor[ 0 ] == 255 ) && ( gotcolor[ 1 ] > 150 ) && ( gotcolor[ 2 ] > 51 ) ) {
            name = "vector from despicable me";
        } else if ( ( gotcolor[ 0 ] < 20 ) && ( gotcolor[ 1 ] < 20 ) && ( gotcolor[ 2 ] == 255 ) ) {
            name = "Actually just thomas the tank engine";
        } else if ( ( gotcolor[ 0 ] < 51 ) && ( gotcolor[ 1 ] < 102 ) && ( gotcolor[ 2 ] == 255 ) ) {
            name = "Discord Mod";
        } else if ( ( gotcolor[ 0 ] == 255 ) && ( gotcolor[ 1 ] < 203 ) && ( gotcolor[ 2 ] == 255 ) ) {
            name = "Gamer girl with a side of bath water";
        } else if ( ( gotcolor[ 0 ] < 1 ) || ( gotcolor[ 1 ] < 100 ) && ( gotcolor[ 2 ] > 100 ) ) {
            name = "2069 from the hit indie nbes.sPrintln based RPG last breath";
        } else if ( ( gotcolor[ 0 ] > 254 ) || ( ( gotcolor[ 1 ] > 50 ) && ( gotcolor[ 2 ] < 50 ) ) ) {
            name = "Little timmy having a meltdown over subpar candy";
        } else {
            name = "Dount earther";
        }
        return name;
    }

    public void showColor( Color color ) {
        extra.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        extra.setSize( 500 , 500 );
        extra.getContentPane( ).setBackground( color );
        extra.setVisible( true );
        Nbes.wait( 1000 - ( round * 10 ) );
        extra.setVisible( false );
    }
}

class NG extends Game {

    public NG( ) {
        super( "Number guesser" , null , null );
    }

    @Override
    public void game( ) {
        int highscore = Integer.parseInt( Read( "Files/number.txt" )[ 0 ].toString( ) );
        while ( nbes.inputBool( "You want to play?" ) ) {
            nbes.sPrintln( "Current highscore: " + highscore );

            int     goal  = Nbes.random( 0 , 100 );
            int     score = 100;
            boolean win   = false;
            for ( int i = 7 ; i > 0 ; i-- ) {
                int guess = nbes.inputInt( "what is favorite number 1,100" );
                if ( guess > goal ) {
                    nbes.sPrintln( "Too Big" );
                } else if ( guess < goal ) {
                    nbes.sPrintln( "Too Small" );
                } else {
                    win = true;
                    break;
                }
                score -= ( Math.abs( goal - guess ) / 2 );
                nbes.sPrintln( "You have " + i + " Guesses left" );
            }
            if ( win ) {
                nbes.sPrintln( "YOU WON\nWOOOOOOOOOOOOOOOOOOOOO" );
                if ( score > highscore ) {
                    highscore = score;
                    nbes.sPrintln( "NEW HIGHSCORE: " + highscore );
                    FileRead.Edit( "Files/number.txt" , new Object[] { highscore } );
                }
            } else {
                nbes.sPrintln( "GAME OVER\nThe number was: " + goal );
            }

        }


    }
}


class Speedrun extends Lbob{
    int    time;
    Race[] allRaces = {
            new Race( "VS random emmi" , new Emmi( Emmi.OTHERS[ 0 ] ) ) ,
            new Race( "VS Random boss" , new Emmi( Emmi.MINI_BOSSES[ 0 ] ) ) ,
            new Race( "VS Bill gates" , gates ) ,
    };

    public Speedrun( ) {
        super( null,"SpeedRunnning" );
        HPmax        = 60;
        supportPower = 7;

    }

    public void game( ) {
        for ( int i = 0 ; i < allRaces.length ; i++ ) {
            nbes.sPrint( i + ") " + allRaces[ i ].mission + " Current record: " + readTime( i ) + " sec" );
        }
        int  choice    = nbes.inputInt( "Which one?" );
        long startTime = System.currentTimeMillis( );
        if ( allRaces[ choice ].isEmmi ) {
            battle( allRaces[ choice ].vsEmmi );
        } else {
            battle( allRaces[ choice ].vsBoss );
            allRaces[ choice ].vsBoss.resetPhases( );
        }
        int time = (int) ( System.currentTimeMillis( ) - startTime ) / 1000;
        if ( time < readTime( choice ) ) {
            writeScores( choice );
            nbes.sPrintln( "NEW RECORD" );
        }
        nbes.sPrintln( "RUN COMPLETE TIME: " + time );
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
                    arr.add( " ; " + time );
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
            if ( Nbes.strIsInt( info.substring( i + 2 ) ) ) {
                return Integer.parseInt( info.substring( i + 2 ) );
            }
            return 0;
        } catch ( IOException e ) {
            e.printStackTrace( );
            return 0;
        }
    }

}


class Race {
    Emmi    vsEmmi;
    Boss    vsBoss;
    String  mission;
    boolean isEmmi = false;
    boolean isBoss = false;

    public Race( String name , Emmi emmi ) {
        vsEmmi  = new Emmi( emmi );
        mission = name;
        isEmmi  = true;
    }

    public Race( String name , Boss boss ) {
        vsBoss  = boss;
        mission = name;
        isBoss  = true;
    }


}


