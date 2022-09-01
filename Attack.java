import java.util.ArrayList;

class Attack extends FileRead {

    String attackName;
    int    speed;
    int    high;
    int    low;
    int    attackTier;
    int    num;
    String attackUser;
    static ArrayList < Attack > combo = new ArrayList <>( );

    public Attack( String name , int lo , int hi , int spd ) {
        attackName = name;
        low        = lo;
        high       = hi;
        speed      = spd;
        attackTier = 1;
        attackUser = "2051";
    }

    public Attack( String user , String name , int l , int h , int spd ) {
        attackName = name;
        low        = l;
        high       = h;
        speed      = spd;
        attackUser = user;
        attackTier = 1;
    }

    public String toString( ) {
        return attackName + " Speed: " + speed + " Max Damage: " + high;
    }


    public int attack( Object[] args ) {
        nbes.lastsPrint = "";
        if ( args == null ) {
            return calcDamage( );
        } else if ( args.length == 0 ) {
            num = calcDamage( );
            nbes.sPrintln( attackName + "\n" + attackUser + " deals " + num + " damage" );
            return num;
        } else if ( args.length == 1 ) {
            num = calcDamage( ) + Integer.parseInt( args[ 0 ].toString( ) );
            nbes.sPrintln( attackName + "\n" + attackUser + " deals " + num + " damage" );
            return num;
        } else if ( args.length == 2 ) {
            double power = Double.parseDouble( args[ 0 ].toString( ) );
            num = low + nbes.quickTime( speed * 1000 , attackUser ) + attackTier ^ ( ( attackTier / 5 ) + 1 );
            if ( num > high * power ) {
                num = (int) ( high * power );
            }
            Nbes.wait( (int) ( Double.parseDouble( args[ 1 ].toString( ) ) * 1000 ) );
            nbes.sPrintln( attackName + "\n" + attackUser + " deals " + num + " damage" );
            return num;
        }
        return 0;
    }

    public int calcDamage( ) {
        return (int) ( ( Nbes.random( low , high ) + comboBonus( ) ) * Nbes.musicMultiplier * attackTierBuff( ) );
    }

    public static Attack[] copyToNewUser( Attack[] attacks , String user ) {
        Attack[] arr = new Attack[ attacks.length ];
        for ( int i = 0 ; i < attacks.length ; i++ ) {
            arr[ i ] = new Attack( user , attacks[ i ].attackName , attacks[ i ].low , attacks[ i ].high , attacks[ i ].speed );
        }
        return arr;
    }

    public double attackTierBuff( ) {
        return ( 9 + attackTier ) / 10;
    }

    public int comboBonus( ) {
        combo.add( this );
        if ( this.attackUser.equals( "2051" ) ) {
            if ( this.equals( Game.shot ) && combo.size( ) > 5 ) {
                num = combo.size( ) * 2;
                nbes.sPrintln( "COMBO BONUS: " + num );
                combo.clear( );
                return num;
            } else if ( this.equals( Game.stab ) && combo.size( ) == 1 ) {
                nbes.sPrintln( "COMBO BONUS: 10" );
                return 10;
            } else if ( this.equals( Game.potion ) ) {
                if ( combo.size( ) / 3 == 1 ) {
                    nbes.sPrintln( "COMBO BONUS: COMBO ITEMS +"+combo.size()/3 );
                    for ( int i=combo.size()/3; i>0; i-- ) combo.add( this );
                }

            }
        }
        return 0;
    }

}

