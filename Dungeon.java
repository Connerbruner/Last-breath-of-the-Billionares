class Dungeon extends FileRead {
    String dungeonName;
    int dungeonLength;
    int amountMoved;


    public Dungeon(String name, int length) {

        dungeonName = name;
        dungeonLength = length;
        amountMoved=0;
    }



    public void start() {
        nbes.sPrint("Welcome to the " + dungeonName + " (length: " + dungeonLength + " )");
        amountMoved = 0;
    }

    public void move() {
        int steps = nbes.random(3, 5);
        amountMoved += steps;
        nbes.sPrintln("You move " + steps + " feet");
    }

}
