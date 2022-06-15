class Dungeon extends Tools {
    String dungeonName;
    int dungeonLength;
    int amountMoved;


    public Dungeon(String name, int length) {

        dungeonName = name;
        dungeonLength = length;
        amountMoved=0;
    }



    public void start() {
        sPrint("Welcome to the " + dungeonName + " (length: " + dungeonLength + " )");
        amountMoved = 0;
    }

    public void move() {
        int steps = random(3, 5);
        amountMoved += steps;
        sPrintln("You move " + steps + " feet");
    }

}
