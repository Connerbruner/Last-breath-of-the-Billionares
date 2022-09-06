class Hallway extends FileRead {
    Hallway[]   neighbors;
    String      hallwayName;
    ItemClass[] hallwayItems;
    boolean     hallUnlocked;

    public Hallway( String name , ItemClass[] items ) {
        hallwayName  = name;
        hallwayItems = items;
        hallUnlocked = true;
    }

    public Hallway( String name , ItemClass[] items , boolean unlocked ) {
        hallwayName  = name;
        hallwayItems = items;
        hallUnlocked = unlocked;
    }
    public Hallway( String name ) {
        hallwayName  = name;
    }

    public void setNeighbors( Hallway[] hallway ) {
        neighbors = hallway;
    }

    public Item loot( ) {
        return hallwayItems[ Nbes.random( 0 , hallwayItems.length - 1 ) ].createRandomItem( );
    }

    public Hallway move( ) {
        nbes.sPrint( "Current hallway: " + hallwayName );
        int l = 0;
        for ( int i = 0 ; i < neighbors.length ; i++ ) {
            if ( neighbors[ i ].hallUnlocked ) {
                nbes.sPrint( i + ": " + neighbors[ i ].hallwayName );
                l++;
            }
        }
        Hallway chose = neighbors[ nbes.inputInt( "Where would you like to go 0-" + ( l - 1 ) ) ];
        if ( chose.hallUnlocked ) {
            return chose;
        }
        return null;
    }
}

class Goal extends FileRead {

    String  goal;
    Hallway ending;
    boolean complete = false;

    public Goal( String name , Hallway hallway ) {
        goal   = name;
        ending = hallway;
    }

    public Goal( String name ) {
        goal = name;
    }

    public String toString( ) {
        if ( ending != null ) {
            return "Current Goal: " + goal + " go to " + ending.hallwayName;
        }
        return "Current Goal: " + goal;
    }

    public boolean check( Hallway hallway ) {
        if ( ending != null ) {
            if ( hallway.hallwayName.equals( ending.hallwayName ) && ! complete ) {
                nbes.sPrintln( this + " COMPLETE" );
                complete = true;
                return true;
            }
        }
        return false;
    }
}

class Area extends FileRead {
    String    areaName;
    Hallway current;
    Hallway[] areaHallways;

    public Area( String name , Hallway[] hallways ) {
        areaName     = name;
        areaHallways = hallways;
        current = hallways[0];
    }

    public void setAreaNeighbors( Hallway[][] neighbors ) {
        for ( int i = 0 ; i < areaHallways.length ; i++ ) {
            areaHallways[ i ].setNeighbors( neighbors[ i ] );
        }
    }
    public void move() {
        current.move();
    }
}
class AreaStroage {
    static Hallway floor1 = new Hallway("Floor 1");
    static Hallway floor2 = new Hallway("Floor 2");
    static Hallway floor3 = new Hallway("Floor 3");
    static Hallway floor4 = new Hallway("Floor 4");
    static Hallway floor5 = new Hallway("Floor 5");
    static Hallway floor6 = new Hallway("Floor 6");
    static Hallway floor7 = new Hallway("Floor 7");
    static Hallway floor8 = new Hallway("Floor 8");
    static Hallway floor9 = new Hallway("Floor 9");
    static Hallway floor10 = new Hallway("Floor 10");
    static Hallway floorBase = new Hallway("Base Floor");
    static Area skyscraper = new Area("Skyscraper", {
            floorBase, floor1, floor2, floor3, floor4, floor5, floor6, floor7, floor8, floor9, floor10
    });

    static {
        skyscraper.setAreaNeighbors(
                []{
                        {//base
                                floor1
                        },
                        {//1
                                floorBase, floor2, floor3
                        },
                        {//2
                                floor1, floor3, floor5
                        },
                        {//3
                                floor2, floor6, floor4
                        },
                        {//4
                                floor3, floor7, floor5
                        },
                        {//5
                                floor4, floor9, floor3
                        },
                        {//6
                                floor5, floor7, floor9
                        },
                        {//7
                                floor6, floor9, floor2
                        },
                        {//8
                                floor7, floor10, floor3
                        },
                        {//9
                                floor8, floor1, floor4
                        },
                        {//10
                                floor9, floor1, floor7
                        },
                }
        );
    }
    static Hallway store1 = new Hallway("Shoe store");
    static Hallway store2 = new Hallway("Toy store");
    static Hallway store3 = new Hallway("Clothing store");
    static Hallway store4 = new Hallway("Video Game store");
    static Hallway store5 = new Hallway("Home store");
    static Hallway store6 = new Hallway("Phone repair Area");
    static Hallway store7 = new Hallway("Kitchen store");
    static Hallway store8 = new Hallway("Beauty Store");
    static Hallway foodCourtA = new Hallway("Food court A");
    static Hallway foodCourtB = new Hallway("Food court B");
    
    static Area mall =  new Area("MEGA MALL", {
        foodCourtA,store1,store2,store3,store4,store5,store6,store7,store8,foodCourtB
    });

    static {
        mall.setAreaNeighbors(
                [] {
                    {//food A
                        store2,store1,store6
                    },
                     {// store1
                        foodCourtA,store2
                    },
                    {// store2
                        store1,store3
                    },
                    {// store3
                      store2,store4 
                    },
                    {// store4
                        store3,store5
                    },
                    {// store5
                        strote4
                    },
                    {// store6
                        store7,store1
                    },
                    {// store7
                        store6,store8
                    },
                    {// store8
                      store7,foodCourtB 
                    },
                }
        );
    }


}