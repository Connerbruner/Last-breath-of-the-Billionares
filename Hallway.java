class Hallway extends FileRead {
    Hallway[] neighbors;
    String hallwayName;
    ItemClass[] hallwayItems;
    boolean hallUnlocked;

    public void setNeighbors(Hallway[] hallway)
    {
        neighbors=hallway;
    }
    public Hallway(String name,ItemClass[] items)
    {
        hallwayName=name;
        hallwayItems=items;
        hallUnlocked=true;
    }
    public Hallway(String name,ItemClass[] items,boolean unlocked)
    {
        hallwayName=name;
        hallwayItems=items;
        hallUnlocked=unlocked;
    }
    public Item loot()
    {
        return hallwayItems[nbes.random(0,hallwayItems.length-1)].createRandomItem();
    }
    public Hallway move()
    {
        nbes.sPrint("Current hallway: "+hallwayName);
        int l=0;
        for(int i=0; i<neighbors.length; i++)
        {
            if(neighbors[i].hallUnlocked)
            {
                nbes.sPrint(i+": "+neighbors[i].hallwayName);
                l++;
            }
        }
        Hallway chose = neighbors[nbes.inputInt("Where would you like to go 0-"+(l-1))];
        if( chose.hallUnlocked )
        {
            return chose;
        }
        return null;
    }
}