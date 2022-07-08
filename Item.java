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