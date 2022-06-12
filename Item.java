class Item extends Tools {
    int damage, dur;
    String itemName;
    boolean isHeal = false;
    boolean isShield;
    public Item(String name,int power, int dura)
    {
        itemName = name;
        damage = power;
        dur = dura;
    }
    public Item(String name,int power)
    {
        itemName = name;
        damage = power;
        dur=1;
        isHeal = true;
    }
    public Item(String name,int power,double per)
    {
        itemName = name;
        dur=power;
        isShield = true;
    }
    public String toString()
    {
        if(isHeal)
        {
            return itemName+" Healing: "+damage;
        }
        if(isShield)
        {
            return  itemName+" Amount blockable: "+dur;
        }
        return itemName+" Power: "+damage+" durability: "+dur;

    }
    public int useItem()
    {
        if(isHeal && dur>0)
        {
            sPrintln("You heal "+damage+" damage");
            dur--;
            return damage;

        }
        else if (dur>0)
        {
            int hit=damage;
            sPrintln("You deal "+hit+" damage");
            dur--;
            sPrintln(itemName+" just lost one durability\ndurability left: "+dur);
            return hit;
        }
        else
        {
            sPrintln("Cant use this Item no durability");
            return 0;
        }
    }

}