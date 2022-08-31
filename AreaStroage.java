class AreaStroage {
        static Hallway floor1 = new Hallway( "Floor 1" );
        static Hallway floor2 = new Hallway( "Floor 2" );
        static Hallway floor3 = new Hallway( "Floor 3" );
        static Hallway floor4 = new Hallway( "Floor 4" );
        static Hallway floor5 = new Hallway( "Floor 5" );
        static Hallway floor6 = new Hallway( "Floor 6" );
        static Hallway floor7 = new Hallway( "Floor 7" );
        static Hallway floor8 = new Hallway( "Floor 8" );
        static Hallway floor9 = new Hallway( "Floor 9" );
        static Hallway floor10 = new Hallway( "Floor 10" );
        static Hallway floorBase = new Hallway( "Base Floor" );
        static Area skyscraper = new Area ("Skyscraper",new Hallway[] {
            floorBase,floor1,floor2,floor3,floor4,floor5,floor6,floor7,floor8,floor9,floor10
        });
        static {
        city.setAreaNeighbors(
            new Hallway[][] {
                new Hallway[] {//base
                    floor1
                },
                new Hallway[] {//1
                   floorBase,floor2,floor3 
                },
                new Hallway[] {//2
                  floor1,floor3,floor5  
                },
                new Hallway[] {//3
                  floor2,floor6,floor4
                },
                new Hallway[] {//4
                  floor3,floor7,floor5  
                },
                new Hallway[] {//5
                  floor4,floor9,floor3
                },
                new Hallway[] {//6
                    floor5,floor7,floor9
                },
                new Hallway[] {//7
                    floor6,floor9,floor2
                },
                new Hallway[] {//8
                    floor7,floor10,floor3
                },
                new Hallway[] {//9
                    floor8,floor1,floor4
                },
                new Hallway[] {//10
                    floor9,floor1,floor7
                },
            }
        )
    }
    
        
}