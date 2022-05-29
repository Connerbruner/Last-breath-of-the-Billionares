import java.util.ArrayList;

class Boss extends Tools {
    public ArrayList < Phase > differntPhases = new ArrayList <>( );

    public Boss (Phase boss ) {
        differntPhases.clear();
        differntPhases.add(boss);
    }

    public Boss ( Phase[] arr ) {
        differntPhases.clear();
        for ( int i = 0; i < arr.length; i++ )
            differntPhases.add( arr[ i ] );
    }


    public void checkArray ( ) {
        if ( differntPhases.get( 0 ).getHP( ) <= 0 ) {
            differntPhases.remove( 0 );
        }
    }


}

    