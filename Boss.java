import java.util.ArrayList;
import java.util.Collections;

class Boss {
    ArrayList < Phase > differentPhases = new ArrayList <>( );
    ArrayList < Phase > storedPhases    = new ArrayList <>( );
    String name;

    public Boss( Phase boss ) {
        differentPhases.add( boss );
        storedPhases.add( boss );
        name=differentPhases.get( 0 ).name;
    }

    public Boss( Phase[] arr ) {
        Collections.addAll( storedPhases , arr );
        Collections.addAll( differentPhases , arr );
        name=differentPhases.get( 0 ).name;
    }


    public void checkArray( ) {
        if(!differentPhases.isEmpty())
        {
            if ( differentPhases.get( 0 ).HP <= 0 ) {
                differentPhases.remove( 0 );
            }
            if(!differentPhases.isEmpty())
            {
                name=differentPhases.get( 0 ).name;
            }
        }
    }
    public void resetPhases()
    {
        differentPhases=storedPhases;
    }
}
class Phase extends FileRead {
    int      HP;
    Attack[] attacks;
    String   name;
    int      curAttack;

    public Phase( Attack[] arr , int health , String n ) {
        attacks = Attack.copyToNewUser(arr,n);
        HP      = health;
        name    = n;
    }
    public void loseHP( int HP ) {
        this.HP -= HP;
    }
    public void pickAttack( ) {
        curAttack = nbes.random( 0 , attacks.length - 1 );
        nbes.sPrintln( attacks[ curAttack ].attackName );
    }
    public void pickAttack(boolean text ) {
        curAttack = nbes.random( 0 , attacks.length - 1 );
        if(text) {
            nbes.sPrintln( attacks[ curAttack ].attackName );
        }

    }

}


    