import java.util.Locale;
import java.util.Scanner;

//Main class
class Lbob extends Game {


    //obj
    Main main = new Main();
    Scanner scanner = new Scanner(System.in);
    Dungeon local6_11 = new Dungeon("Rubble filled 6-11", 10);
    Dungeon factory = new Dungeon("Run down Factory", 30);
    Dungeon city = new Dungeon("Rubble filled City", 35);
    Dungeon highway = new Dungeon("Highway 101", 30);
    Dungeon subway = new Dungeon("Underground subway", 20);
    //bosses
    //attacks and attack[]
    //bill gates
    Attack[] attackGates = {new Attack("Bill Gates", "TRIPLE SLASH", 10, 12, 15), new Attack("Bill Gates", "ULTRA SLASH", 7, 15, 20), new Attack("Bill Gates", "LASER BLAST", 5, 20, 30)};
    //Elon musk
    Attack[] attacksElon = {new Attack("Elon musk", "SPEAR RUSH", 5, 20, 10), new Attack("Elon musk", "REVENGE OF THE SPEAR", 12, 10, 20), new Attack("Elon musk", "ULTRA SPEAR", 1, 30, 20)};
    //jeff bezos
    Attack[] attacksJeff = {new Attack("Jeff bezos", "ROOMBA INVASION", 7, 15, 25), new Attack("Jeff bezos", "MECH CANNON", 5, 20, 30), new Attack("Jeff bezos", "DUAL LASER", 12, 20, 15)};
    //Mark Zuckerberg
    Attack[] attacksMark = {new Attack("Mark Zuckerberg", "FINAL SLASH", 1, 100, 30), new Attack("Mark Zuckerberg", "DUAL SLASH", 25, 50, 15), new Attack("Mark Zuckerberg", "ZERO SLASH", 5, 10, 1)};
    //Phases and Phase[]
    Phase Elon = new Phase(attacksElon, 250, "Elon Musk");
    //boss
    //single phase
    Boss elon = new Boss(Elon);
    Phase Gates = new Phase(attackGates, 250, "Bill Gates");
    Boss gates = new Boss(Gates);
    Phase Jeff = new Phase(attacksJeff, 250, "Jeff bezos");
    Phase[] arrTri1 = {Elon, Gates, Jeff};
    //trio
    Boss Tri = new Boss(arrTri1);
    Boss jeff = new Boss(Jeff);
    Phase ElonP = new Phase(attacksElon, 500, "Elon Musk");
    Boss elonP = new Boss(ElonP);
    Phase GatesP = new Phase(attackGates, 500, "Bill Gates");
    Boss gatesP = new Boss(GatesP);
    Phase JeffP = new Phase(attacksJeff, 500, "Bill Gates");
    Phase[] arrTri2 = {ElonP, GatesP, JeffP};
    Boss Tri2 = new Boss(arrTri2);
    Boss jeffP = new Boss(JeffP);

    Phase mark = new Phase(attacksMark, 1000, "Mark Zuckerberg");
    Boss Zuckerberg = new Boss(mark);

    public Lbob(String file,String name, int speed,int placement) {
        super(file,name,speed,placement);
    }


    //Starts up 2069
    @Override
    public void game() {

        //Runs mission forever
        while (true) {
            levelUp();
            HP2069 = HPmax;
            sPrint("Type 1 -> " + missionNum + " to try that Mission");
            //Tells you how to roll the gotcha
            if (missionNum > 1) {
                sPrint("Type 0 to trade exp for new moves");
            }
            sPrint("Type 12 to leave (DONT STOP REPL.IT)");


            int choice = scanner.nextInt();
            if (choice < missionNum && choice > 0) {
                sPrint("How many stars would like to add (makes mission harder)");
                stars = scanner.nextInt();
            } else {
                stars = 0;
            }
            System.out.println();
//mission 1
            if (choice == 1) {
                sPrintln("Mission 1: The Awakening of The Revolution");
                sPrintln("A Roomba appears ");

                sPrint("2069's turn");
                sPrint("1: " + aqua.attackName);
                sPrint("2: " + lasershot.attackName);
                sPrint("3: Cure");
                sPrint("4: " + ember.attackName);
                sPrint("5: " + backpack.toString());
                System.out.println();

                sPrint("Chose a number corresponding to the attack you want to use (be fast) (1-5)");
                attackNum = scanner.nextInt();

                sPrint("Chose Out (Power) or Out (Speed) (Speed is weaker and faster and Power is Powerful but slow");
                String style = scanner.nextLine().toLowerCase();
                while (style.equals("power") || style.equals("speed")) {
                    style = scanner.nextLine();
                }
                System.out.println();
                sPrint("Type Kick");
                style = scanner.nextLine();
                while (style.equals("Kick")) {
                    style = scanner.nextLine();
                }
                sPrint("Type Punch");
                style = scanner.nextLine();
                while (style.equals("Punch")) {
                    style = scanner.nextLine();
                }
                sPrintln("Roomba fades away");
                dungeon(subway);
                if (stars > 10) {
                    dungeon(city);
                }
                if (stars > 5) {
                    dungeon(local6_11);
                    fightMech();
                }
                missionComplete(1);
            }

//Mission 2
            if ((choice == 2) && (missionNum >= 2)) {
                sPrintln("Mission 2: First Encounters");
                if (stars > 5) {
                    dungeon(city);
                }
                bossFight(gates);
                missionComplete(2);

            }
//Mission 3
            if ((missionNum >= 3) && (choice == 3)) {
                sPrintln("MISSION 3: Rest In The Rubble");

                dungeon(local6_11);
                bossFight(elon);
                dungeon(subway);
                missionComplete(3);

            }
//Mission 4
            if ((missionNum >= 4) && (choice == 4)) {
                sPrintln("Mission 4: 101 battles");


                dungeon(highway);
                if (stars > 5) {
                    dungeon(local6_11);
                }
                bossFight(jeff);
                missionComplete(4);

            }
//Mission 5
            if ((missionNum >= 5) && (choice == 5)) {
                sPrintln("Mission 5: Rematch Cubed");


                bossFight(Tri);
                missionComplete(5);

            }
//Mission 6
            if ((missionNum >= 6) && (choice == 6)) {
                sPrintln("Mission 6: Battle on the highway");

                dungeon(highway);
                bossFight(jeffP);
                missionComplete(6);

            }
//Mission 7
            if (missionNum >= 7 && choice == 6) {
                sPrintln("Mission 7: Face off in the factory");

                dungeon(factory);
                bossFight(elonP);
                missionComplete(7);

            }
//Mission 8
            if ((missionNum >= 8) && (choice == 8)) {
                sPrintln("MISSION 8: Highway to the future");
                dungeon(highway);
                bossFight(gatesP);
                missionComplete(8);

            }
//Mission 9
            if ((missionNum >= 9) && (choice == 9)) {
                sPrintln("Mission 9: Face-Off On The Grand Tower");
                bossFight(Tri2);
                missionComplete(9);


            }
//Mission 10
            if ((missionNum >= 10) && (choice == 10)) {
                sPrintln("Mission 10: 2 Sides Of The Same Coin");
                bossFight(Zuckerberg);
                missionComplete(10);
            }

//Gotcha system
            if ((choice == 0) && (missionNum > 1)) {
                pull();
            }
            if (choice == 12) {
                main.play();
            }

            save();
        }

    }

    @Override
    public void restart() {
        if (HP2069 < 0) {
            if (!is2048joined && random(missionNum * 2, 30) == 30) {
                HP2069 = HPmax;
                sPrintln("2048: can't handle things on your own");
                sPrintln("2069: 2048!");
                sPrintln("*2048 has joined the team*");
                is2048joined = true;
                writeTeam(is2051joined, true, Timeline);
                sendToBot("2048 just joined " + user + "'s party");
            } else if (!is2051joined && random(missionNum * 2, 30) == 30) {
                HP2069 = HPmax;
                sPrintln("2051: Playtime is over");
                sPrintln("2069: 2051!");
                sPrintln("*2051 has joined the team*");
                is2051joined = true;
                writeTeam(true, is2048joined, Timeline);
                sendToBot("2051 just joined " + user + "'s party");
            } else {
                sPrintln("The world around you begins to fade to black");
                sPrintln("???: Welcome back to this world of nothingness ");
                sPrintln("2069: no...");
                sPrintln("???: I will help you get out of this");
                sPrintln("2077:I see nothing wrong with that");
                sPrintln("2069: Please help us");
                String choice = "How are you doing";
                while (!choice.equals("START")) {
                    sPrint("Type ¨START¨ to continue     ");
                    choice = scanner.nextLine();
                }
                System.out.println();
                sendToBot(user + " has fallen. Good thing ??? is here");
                save();
                game();
            }

        }
    }
    //don't pass this comment
}
