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