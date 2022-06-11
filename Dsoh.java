class Dsoh extends Tools {

    String user;
    //items in loot tables
    //non-healing
    ItemClass staplers = new ItemClass("staplers", 5, 10, 50, 100);
    ItemClass clean = new ItemClass("Cleaner", 3, 5, 100, 200);
    ItemClass ruler = new ItemClass("ruler", 8, 12, 4, 5);
    ItemClass binder = new ItemClass("binder", 5, 20, 10, 20);
    ItemClass scissors = new ItemClass("scissors", 7, 20, 3, 10);
    ItemClass waterBottle = new ItemClass("Water Bottle (attack)", 1, 20, 2, 12);
    ItemClass chromeBook = new ItemClass("chromeBook", 10, 20, 1, 2);
    ItemClass metalPiece = new ItemClass("metalPiece", 1, 20, 1, 50);
    ItemClass basketBall = new ItemClass("basketBall",10,20,1,5);
    ItemClass footBall = new ItemClass("FootBall",5,10,3,7);
    ItemClass tennisBall = new ItemClass("tennisBall",1,5,8,20);
    //healing
    ItemClass soda = new ItemClass("Soda", 5, 10);
    ItemClass chip = new ItemClass("Chip bag", 7, 12);
    ItemClass water = new ItemClass("Water bottle (heal)", 6, 10);
    ItemClass lunch = new ItemClass("School lunch", 1, 20);


    //Item-class array
    ItemClass[] classroom = {water, waterBottle, clean, ruler, binder, scissors, chromeBook, staplers};
    ItemClass[] gym = {water, waterBottle, chromeBook};
    ItemClass[] metal = {water, waterBottle, chromeBook, metalPiece};
    ItemClass[] court = {water, waterBottle};
    ItemClass[] main = {soda, lunch, chip, water, waterBottle};
    ItemClass[] outSide = {soda, chip, lunch, water, waterBottle, chromeBook,basketBall,footBall,tennisBall,};


    //Hallways
    Hallway hallway200 = new Hallway("200s", classroom);
    Hallway hallway100 = new Hallway("100s", classroom);
    Hallway hallway700 = new Hallway("700s", classroom);
    Hallway hallwayD200 = new Hallway("D200s", classroom);
    Hallway hallwayD100 = new Hallway("D100s", classroom);
    Hallway hallway500 = new Hallway("500", gym);
    Hallway commons = new Hallway("Commons", main);
    Hallway courtYard = new Hallway("courtYard", court);
    
    Hallway robotics = new Hallway("Robotics Room", metal, false);
    Hallway swim = new Hallway("swim hallway", gym, false);
    Hallway field = new Hallway("Football field", outSide, false);
    Hallway track = new Hallway("Track field", outSide, false);
    Hallway parkingLotBus = new Hallway("Parking lot (Bus)", outSide, false);
    Hallway parkingLotSenior = new Hallway("Parking lot (Senior)", outSide, false);
    Hallway parkingLotBack = new Hallway("Parking lot (Back)", outSide, false);

    //hallway[]
    Hallway[] neighborCommon = {hallwayD100, hallway100, hallway200, hallway500, parkingLotSenior};
    Hallway[] neighborD200 = {hallwayD100, hallway200};
    Hallway[] neighborD100 = {hallwayD200,commons,hallway700, courtYard, parkingLotBack};
    Hallway[] neighbor200 = {hallway100, commons, hallwayD200};
    Hallway[] neighbor100 = {courtYard, hallway200, commons, parkingLotSenior};
    Hallway[] neighbor500 = {swim, commons, parkingLotBus};
    Hallway[] neighbor700 = {courtYard, hallwayD100, robotics, parkingLotBack};
    Hallway[] neighborSwim = {hallway500, hallwayD100, parkingLotBus};
    Hallway[] neighborRobot = {hallway700};
    Hallway[] neighborCourt = {hallway100, hallway700, parkingLotBack};

    Hallway[] neighborFootBall = {parkingLotBack};
    Hallway[] neighborBack = {field, track, parkingLotSenior};
    Hallway[] neighborSenior = {parkingLotBack, parkingLotBus, hallway100, commons};
    Hallway[] neighborBus = {hallway500, track, parkingLotSenior};
    Hallway[] neighborTrack = {parkingLotBack, parkingLotBus};
    //Gordy
    int gordyHP=100;
    int gordyLevel=3;
    Gordy gordy = new Gordy(field, gordyHP, gordyLevel);
    int score=0;
    
    //Goal[]
    Goal[] allGoals = {
            new Goal("Get the robotics key", hallway200),
            new Goal("Find Tippy", robotics),
            new Goal("exit the building", swim),
            new Goal("Kill Gordy")
    };

    //you
    Hallway current = commons;
    Item[] backpack = new Item[3];
    int HP = 50;
    int HPM = 50;
    int story = 0;
    
    public Dsoh(String name)
    {
        user=name;
    }
    
    public void game() {
        commons.setNeighbors(neighborCommon);
        hallwayD200.setNeighbors(neighborD200);
        hallwayD100.setNeighbors(neighborD100);
        hallway200.setNeighbors(neighbor200);
        hallway100.setNeighbors(neighbor100);
        hallway500.setNeighbors(neighbor500);
        hallway700.setNeighbors(neighbor700);
        swim.setNeighbors(neighborSwim);
        robotics.setNeighbors(neighborRobot);
        courtYard.setNeighbors(neighborCourt);
        field.setNeighbors(neighborFootBall);
        parkingLotBack.setNeighbors(neighborBack);
        parkingLotSenior.setNeighbors(neighborSenior);
        parkingLotBus.setNeighbors(neighborBus);
        track.setNeighbors(neighborTrack);
        
        for (int turn = 1; HP > 0; turn++) {
            System.out.print(SCREEN_CLEAR);
            sPrintln("Turn " + turn);
            sPrint(allGoals[story].toString());
            sPrint("Current hallway: "+current.hallwayName);
            sPrint("HP: "+HP);
            System.out.println();
            sPrint("What would you like to do? ");
            sPrint("1) loot\n2) use item\n3) move");
            int action1 = scanner.nextInt();
            sPrint("What would you like to do? ");
            sPrint("1) loot\n2) use item\n3) move");
            int action2 = scanner.nextInt();
            while (action2 == action1) {
                action2 = scanner.nextInt();
            }
            scanner.nextLine();
            System.out.println();

            
            if (action1 == 1 || action2 == 1) {
                find();
            }
            if (action1 == 2 || action2 == 2) {
                useItem();
            }
            if (action1 == 3 || action2 == 3) {
                Hallway temp = current.move();
                if (temp != null) {
                    current = temp;
                }
                sPrintln("You are in the "+current.hallwayName);
            }
            
            if(gordy.HP<0 ) {
                sPrintln("Gordy fades away");
                sPrintln("Gordy is back at the footBall feild");
                gordyHP*=1.5;
                gordyLevel*=1.5;
                gordy = new Gordy(field, gordyHP, gordyLevel);
                lootBackpack(3,current);
                score+=gordyLevel*1000;
                sendToBot("???: FOOL GORDY NEVER FALLS");
            }
            if (gordy.hallway.hallwayName.equals(current.hallwayName)) {
                    HP -= gordy.Attack();
            }  
            else if(!gordy.charge)
            {
                for(int i=(turn/2); i>0 && !gordy.hallway.hallwayName.equals(current.hallwayName); i--)
                {
                    gordy.move();   
                }
                sPrintln("Gordy is in the "+gordy.hallway.hallwayName);
                if (gordy.hallway.hallwayName.equals(current.hallwayName)) {
                    HP -= gordy.Attack();
            }  
            }

            if(story==0 && allGoals[0].check(current))
            {
                robotics.hallUnlocked=true;
                sendToBot(user+" just got the robotics key");
                score+=100;
                story++;
            }
            if(story==1 && allGoals[1].check(current))
            {
                swim.hallUnlocked=true;
                lootBackpack(2,robotics);
                score+=300;
                story++;
            }
            if(story==2 && allGoals[2].check(current))
            {
                track.hallUnlocked=true;
                field.hallUnlocked=true;
                parkingLotBus.hallUnlocked=true;
                parkingLotSenior.hallUnlocked=true;
                parkingLotBack.hallUnlocked=true;
                sendToBot(user+" unlocked the parking lots");
                score+=1000;
                lootBackpack(3,hallway200);
                story++;
                
            }
            score++;
            
    }
    sendToBot(user+" has fallen");
    sPrintln("GAME OVER");                           run.exit(69420);

    }
    public void lootBackpack(int size,Hallway lootTable)
    {
        sPrintln("You find a Backpack Size: "+size);
        for(size=size; size>0; size--) {
            Item temp = lootTable.loot();
            sPrint(packToString());
            sPrint(temp.toString());
            if (choice("Want this item")) {
                sPrint("What slot 0-2");
                backpack[scanner.nextInt()] = temp;
                scanner.nextLine();
            }
            score+=10;
            
        }
    }
    public void find() {
        Item temp = current.loot();
        sPrint(packToString());
        sPrint(temp.toString());
        if (choice("Want this item")) {
            sPrint("What slot 0-2");
            backpack[scanner.nextInt()] = temp;
            scanner.nextLine();
            score++;
            sendToBot(user+" Just found "+temp.toString());
        }
    }

    public String packToString() {
        return "Current items\nSlot 0: " + backpack[0] + "\nSlot 1: " + backpack[1] + "\nSlot 2: (Storage): " + backpack[2] + "\n";
    }

    public void useItem() {
        sPrint(packToString());
        sPrint("What slot 0-1");
        int i = 3;
        while (i == 3) {
            i = scanner.nextInt();
        }
        Item use = backpack[i];
        scanner.nextLine();
        if (use != null) {
            if (use.isHeal) {
                if (HP != HPM) {
                    HP += use.useItem();
                    if (HP > HPM) {
                        HP = HPM;
                    }
                    backpack[i] = null;
                }
            } else if (gordy.hallway.hallwayName.equals(current.hallwayName)) {
                gordy.HP -= backpack[i].useItem();
                if(backpack[i].dur<=0)
                {
                    backpack[i]=null;
                }
            }
        } else {
            sPrint("No item to use");
        }
        if (backpack[0] == null && backpack[2] != null) {
            if (choice("Pull out " + backpack[2].itemName + " of storage")) {
                backpack[0] = backpack[2];
                backpack[2] = null;
            }
        }
        if (backpack[1] == null && backpack[2] != null) {
            if (choice("Pull out " + backpack[2].itemName + " of storage")) {
                backpack[1] = backpack[2];
                backpack[2] = null;
            }
        }

    }

}