class Attack extends Tools {

    String attackName;
    int speed;
    int high;
    int low;
    int attackTier;
    int stun;
    int num;
    String attackUser;

    /**
     * @param name
     * @param lo
     * @param hi
     * @param spd
     * @param stn
     */
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

    public String getAttackName ( ) {
        return attackName;
    }

    /**
     * @param style
     *
     * @return
     */
    public int getSpeed ( boolean style ) {
        if ( style ) {
            return speed;
        } else {
            return (int)(speed * 1.5);
        }
    }

    public int getAttackTier ( ) {
        return attackTier;
    }
    

    /**
     * @param attackTier
     */
    public void setAttackTier ( int attackTier ) {
        this.attackTier = attackTier;
    }

    /**
     * @param style
     *
     * @return
     */

    public int getStun ( boolean style ) {
        if ( style ) {
            return stun / 2;
        } else {
            return (int)(stun*((attackTier/4)+0.75));
        }
    }

    public int attack ( double power , boolean style ) {
        if ( style ) {
            sPrint( "Fast " + attackName );
            num = ( int ) ( ( random( low , high ) * ((attackTier/2)+0.5) ) * power );
        } else {
            sPrint( "Powerful " + attackName );
            num = ( int ) (( ( random( low , high ) * ((attackTier/2)+0.5) ) * 1.5) * power);

        }
        sPrintln( "2069 deals " + num + " damage" );
        return num;
    }

    public int attack ( ) {

        sPrint( attackName );
        num = random( low , high );
        sPrintln( attackUser + " deals " + num + " damage" );
        return num;
    }
    public int attack ( double power ) {

        sPrint( attackName );
        num = (int)(random( low , high )*power);
        sPrintln( attackUser + " deals " + num + " damage" );
        return num;
    }
}

