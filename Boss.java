import java.util.ArrayList;
import java.util.Collections;

class Boss extends Tools {
    public ArrayList < Phase > differntPhases = new ArrayList <>( );

    public Boss (Phase boss ) {
        differntPhases.add(boss);
    }

    public Boss ( Phase[] arr ) {
        Collections.addAll(differntPhases, arr);
    }


    public void checkArray ( ) {
        if ( differntPhases.get( 0 ).getHP( ) <= 0 ) {
            differntPhases.remove( 0 );
        }
    }


}

    