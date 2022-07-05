class Attack extends FileRead {

    String attackName;
    int speed;
    int high;
    int low;
    int attackTier;
    int stun;
    int num;
    String attackUser;

    public Attack ( String name , int lo , int hi , int spd , int stn ) {
        attackName = name;
        low = lo;
        high = hi;
        speed = spd;
        stun = stn;
        attackTier = 1;
    }

    public Attack ( String user , String name , int l , int h , int spd ) {
        attackName = name;
        low = l;
        high = h;
        speed = spd;
        attackUser = user;
        attackTier = 1;
        stun=2;
    }
    public String toString()
    {
        return attackName+" Speed: "+speed+" Max Damage: "+high;
    }

    public int getSpeed (boolean style ) {
        if ( style ) {
            return speed;
        } else {
            return (int)(speed * 1.5);
        }
    }

    public int getStun ( boolean style ) {
        if ( style ) {
            return stun / 2;
        } else {
            return (int)(stun*((attackTier/4)+0.75));
        }
    }

    public int attack ( double power , boolean style ) {
        if ( style ) {
            nbes.sPrint( "Fast " + attackName );
            num = ( int ) ( ( nbes.random( low , high ) * ((attackTier/2)+0.5) ) * power );
        } else {
            nbes.sPrint( "Powerful " + attackName );
            num = ( int ) (( ( nbes.random( low , high ) * ((attackTier/2)+0.5) ) * 1.5) * power);

        }
        nbes.sPrintln( "2069 deals " + num + " damage" );
        return num;
    }

    public int attack ( ) {
        nbes.sPrint( attackName );
        num = nbes.random( low , high );
        nbes.sPrintln( attackUser + " deals " + num + " damage" );
        return num;
    }
}

