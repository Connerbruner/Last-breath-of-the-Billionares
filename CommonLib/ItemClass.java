class ItemClass extends Tools {
    int damageLow, damageHigh, durLow, durHigh;
    String itemName;
    double block;
    boolean isHeal=false;
    boolean isShield=false;

    public ItemClass(String name, int damageL, int damageH, int durL, int durH) {
        itemName = name;
        damageLow = damageL;
        damageHigh = damageH;
        durLow = durL;
        durHigh = durH;
    }

    public ItemClass(String name, int damageL, int damageH) {
        itemName = name;
        damageLow = damageL;
        damageHigh = damageH;
        isHeal = true;
    }
    public ItemClass(String name , int power)
    {
        itemName = name;
        damageHigh = power;

        isShield=true;
    }

    public Item createRandomItem() {
        if(isHeal)
        {
            return new Item(itemName, random(damageLow, damageHigh));
        }
        if(isShield)
        {
            return new Item(itemName,damageHigh,1.0);
        }
        int damage = random(damageLow, damageHigh);
        int dPower = damageHigh-damage;
        
        int maxDur = durLow+dPower;
        int minDur = durLow+(dPower/2);
        
        if(maxDur>durHigh)
        {
            maxDur=durHigh;
        }
        if(minDur>durHigh)
        {
            minDur=durHigh;
        }
        return new Item(itemName, damage, random(minDur, maxDur));
    }


}