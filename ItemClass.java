class ItemClass extends FileRead {
    int damageLow, damageHigh, durLow, durHigh;
    String itemName;
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

    public Item createRandomItem() {
        if(isHeal)
        {
            return new Item(itemName, nbes.random(damageLow, damageHigh));
        }
        if(isShield)
        {
            return new Item(itemName,damageHigh,1.0);
        }
        int damage = nbes.random(damageLow, damageHigh);
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
        return new Item(itemName, damage, nbes.random(minDur, maxDur));
    }


}