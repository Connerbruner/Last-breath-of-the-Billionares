class Attack extends FileRead {

    String attackName;
    int    speed;
    int    high;
    int    low;
    int    attackTier;
    int    stun;
    int    num;
    String attackUser;

    public Attack( String name , int lo , int hi , int spd , int stn ) {
        attackName = name;
        low        = lo;
        high       = hi;
        speed      = spd;
        stun       = stn;
        attackTier = 1;
    }

    public Attack( String user , String name , int l , int h , int spd ) {
        attackName = name;
        low        = l;
        high       = h;
        speed      = spd;
        attackUser = user;
        attackTier = 1;
        stun       = 2;
    }

    public String toString( ) {
        return attackName + " Speed: " + speed + " Max Damage: " + high;
    }

    public int getSpeed( boolean style ) {
        if ( style ) {
            return speed;
        } else {
            return (int) ( speed * 1.5 );
        }
    }

    public int getStun( boolean style ) {
        if ( style ) {
            return stun / 2;
        } else {
            return (int) ( stun * ( ( attackTier / 4 ) + 0.75 ) );
        }
    }

    public int attack( double power , boolean style ) {

        num = low + nbes.quickTime( (int) ( 20000 * (0.5 +  (attackTier / 2)) ) , attackName );
        if ( num > high ) {
            num = high;
            if ( style )
            {
                num = (int) ( high * (0.5 +  (attackTier / 2)) );
            }
        }
        if ( style ) {
            nbes.sPrint( "Fast " + attackName );
        } else {
            nbes.sPrint( "Powerful " + attackName );
        }
        nbes.sPrintln( "2069 deals " + (int) ( num * power ) + " damage" );
        return (int) ( num * power );
    }

    public int attack( ) {
        nbes.sPrint( attackName );
        num = nbes.random( low , high );
        nbes.sPrintln( attackUser + " deals " + num + " damage" );
        return num;
    }

    public int attack( int level ) {
        nbes.sPrint( attackName );
        num = nbes.random( low , high ) + level;
        nbes.sPrintln( attackUser + " deals " + num + " damage" );
        return num;
    }

}

