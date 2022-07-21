class ItemClass extends FileRead {
    int damageLow, damageHigh, durLow, durHigh;
    String  itemName;
    boolean isHeal   = false;
    boolean isShield = false;

    public ItemClass( String name , int damageL , int damageH , int durL , int durH ) {
        itemName   = name;
        damageLow  = damageL;
        damageHigh = damageH;
        durLow     = durL;
        durHigh    = durH;
    }

    public ItemClass( String name , int damageL , int damageH ) {
        itemName   = name;
        damageLow  = damageL;
        damageHigh = damageH;
        isHeal     = true;
    }

    public Item createRandomItem( ) {
        if ( isHeal ) {
            return new Item( itemName , nbes.random( damageLow , damageHigh ) );
        }
        if ( isShield ) {
            return new Item( itemName , damageHigh , 1.0 );
        }
        int damage = nbes.random( damageLow , damageHigh );
        int dPower = damageHigh - damage;

        int maxDur = durLow + dPower;
        int minDur = durLow + ( dPower / 2 );

        if ( maxDur > durHigh ) {
            maxDur = durHigh;
        }
        if ( minDur > durHigh ) {
            minDur = durHigh;
        }
        return new Item( itemName , damage , nbes.random( minDur , maxDur ) );
    }


}
class Item extends FileRead {
    int damage, dur;
    String  itemName;
    boolean isHeal = false;
    boolean isShield;

    public Item( String name , int power , int dura ) {
        itemName = name;
        damage   = power;
        dur      = dura;
    }

    public Item( String name , int power ) {
        itemName = name;
        damage   = power;
        dur      = 1;
        isHeal   = true;
    }

    public Item( String name , int power , double per ) {
        itemName = name;
        dur      = power;
        isShield = true;
    }

    public String toString( ) {
        if ( isHeal ) {
            return itemName + " Healing: " + damage;
        }
        if ( isShield ) {
            return itemName + " Amount blockable: " + dur;
        }
        return itemName + " Power: " + damage + " durability: " + dur;

    }

    public int useItem( ) {
        if ( isHeal && dur > 0 ) {
            nbes.sPrintln( "You heal " + damage + " damage" );
            dur--;
            return damage;

        } else if ( dur > 0 ) {
            int hit = damage;
            nbes.sPrintln( "You deal " + hit + " damage" );
            dur--;
            nbes.sPrintln( itemName + " just lost one durability\ndurability left: " + dur );
            return hit;
        } else {
            nbes.sPrintln( "Cant use this Item no durability" );
            return 0;
        }

    }

    public int useItem( int bonus ) {
        if ( isHeal && dur > 0 ) {
            nbes.sPrintln( "You heal " + ( damage + bonus ) + " damage" );
            dur--;
            return damage + bonus;

        } else if ( dur > 0 ) {
            int hit = damage + bonus;
            nbes.sPrintln( "You deal " + hit + " damage" );
            dur--;
            nbes.sPrintln( itemName + " just lost one durability\ndurability left: " + dur );
            return hit;
        } else {
            nbes.sPrintln( "Cant use this Item no durability" );
            return 0;
        }
    }

}