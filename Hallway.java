class Hallway extends Tools{
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
        return hallwayItems[random(0,hallwayItems.length-1)].createRandomItem();
    }
    public Hallway move()
    {
        sPrint("Current hallway: "+hallwayName);
        int l=0;
        for(int i=0; i<neighbors.length; i++)
        {
            if(neighbors[i].hallUnlocked)
            {
                sPrint(i+": "+neighbors[i].hallwayName);
                l++;
            }
        }
        sPrint("Where would you like to go 0-"+(l-1));
        Hallway chose = neighbors[scanner.nextInt()];
        if( chose.hallUnlocked )
        {
            return chose;
        }
        return null;
    }
}