import java.util.Scanner;

//Main class
class Lbob extends Game {


    //obj

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
    Boss[] fuel = {Tri2,Zuckerberg};

    public Lbob(String name,int speed) {
        super(name,speed);
    }


    //Starts up 2069
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
                run.exit(69420);
            }

            save();
//Edits txt

        }

    }

    public void save() {
        String time = System.currentTimeMillis() / 3600000 + "";
        Object[] arrList = new Object[]{missionNum, HPmax, level2069, levelR1, exp1, aqua.attackTier, lasershot.attackTier, cureTier, ember.attackTier, maxHit, is2048joined, is2051joined, time};
        if (savePath.equals("Save1.txt")) {
            Edit("Save1.txt", encrypt(arrList, "Save1.txt", pin));
        } else {
            Edit(savePath, arrList);
        }
        Object[] templateTxt = new Object[]{1, 50, 1, 20, 0, 1, 1, 1, 1, 5, false, false, false, time};
        Edit("SaveTemplate.txt", templateTxt);
        System.gc();

    }




    public void pull() {

        if (exp1 >= 25) {
            sPrintln("2069 exp " + exp1);
            sPrint("how much exp would you like to use? ");
            num = scanner.nextInt();
            int pull_num = num / 25;
            exp1 -= num;
            if (exp1 < num) {
                num = exp1;
            }
            num -= exp1;

            while (pull_num > 0) {
                int[] odds = new int[]{1,1,1,1,1,1, 1, 1, 1, 1, 1,2,2,2,2,2, 2, 2, 2, 3, 3, 3,3,3,3,3,3,3 ,4,4, 4, 5, 6, 7};
                int tier = odds[random(0, odds.length - 1)];
                sPrintln("Tier " + tier + " pull");
                if (tier == 1) {
                    HPmax += 2;
                    sPrintln("2069's max Hp increased by 2");
                    sendToBot(user+"'s Max HP increased by 2");
                } else if (tier < 6) {
                    num = random(1, 4);
                    //Ember level up
                    if (num == 4) {
                        if (tier > ember.attackTier) {
                            sPrintln("ember leveled up");
                            sPrintln(ember.attackTier + " --> " + tier);
                            sendToBot(user+"'s ember leveled up "+ember.attackTier + " --> " + tier);
                            ember.setAttackTier(tier);
                        }
                    }
                    // Cure level up
                    if (num == 3) {
                        if (tier > cureTier) {
                            sPrintln("Cure leveled up");
                            sPrintln(cureTier + " -->" + tier);
                            sendToBot(user+"'s Cure leveled up "+cureTier + " --> " + tier);
                            cureTier = tier;
                        }

                    }
                    //aqua level up
                    if (num == 1) {
                        if (tier > aqua.attackTier) {
                            sPrintln("Aqua leveled up");
                            sPrintln(aqua.attackTier + " --> " + tier);
                            sendToBot(user+"'s Aqua leveled up "+aqua.attackTier + " --> " + tier);
                            aqua.setAttackTier(tier);
                        }
                    }
                    //Laser level up
                    if (num == 2) {
                        if (tier > lasershot.attackTier) {
                            sPrintln("Laser leveled up");
                            sPrintln(lasershot.attackTier + " --> " + tier);
                            sendToBot(user+"'s Laser leveled up "+lasershot.attackTier + " --> " + tier);
                            lasershot.setAttackTier(tier);

                        }
                    }
                } else if (tier == 6) {
                    maxHit += 1;
                    sPrintln("The power of supporting members increased by 1");
                    sendToBot(user+"'s power of supporting members increased by 1");
                } else if (tier == 7) {
                    maxHit += 2;
                    sPrintln("The power of supporting members increased by 2");
                    sendToBot(user+"'s power of supporting members increased by 2");
                }
                pull_num -= 1;
                save();
            }
        } else {
            sPrintln("YOU NEED MORE EXP POOR PERSON");
        }

    }





    //uses READ to update save
    public void grabSave()
    {

        if (choice("Would you like to overwrite a save file? (Returns the file to the start of the game)")) {
            if (choice("Are you sure?")) {
                sPrint("Which save would you like to overwrite?");
                int saveOverwrite = scanner.nextInt();
                if (saveOverwrite == 1) {
                    Edit("Save.txt", Read("SaveTemplate.txt"));
                } else if (saveOverwrite == 3) {
                    Edit("Save2.txt", Read("SaveTemplate.txt"));
                }
            }
        }


        sPrint("Which save file would you like to access? 1-3 ( 2 is dev only and needs a pin )");
        boolean isSaveSelected = false;

        while (!isSaveSelected) {
            int saveFile = scanner.nextInt();

            if (saveFile == 1) {
                Save = Read("Save.txt");
                savePath = "Save.txt";
                isSaveSelected = true;
            } else if (saveFile == 2) {
                //special procedure for the dev file
                Edit("tempsave.txt", Read("Save1.txt"));
                sPrint("???: This is a developer test file, which requires a pin to access, please input the pin");
                pin = scanner.nextInt();
                //attempt at decrypting
                Edit("tempsave.txt", decrypt(Read("tempsave.txt"), "tempsave.txt", pin));
                //checksum
                Object[] checksum = Read("tempsave.txt");
                if ((checksum[11].toString()).equals("true") || (checksum[11].toString()).equals("false")) {
                    Save = Read("tempsave.txt");
                    savePath = "Save1.txt";
                    Edit("tempsave.txt", Read("SaveTemplate.txt"));
                    sendToBot("???: turns out "+user+" is a dev that is testing whoooooooooo");
                    for (int i = 0; i < checksum.length; i++) {
                        checksum[i] = 0;
                    }
                    sPrintln("???: That is the correct pin. Hello Floof or C1nner");


                } else { //exits on bad pin
                    sPrintln("???: You're not a dev! GET OUT! Don't try to access this file again !");
                    sendToBot("???: Hey "+user+" GET OUT OF THE DEV SAVE");
                    System.exit(46);
                }
                isSaveSelected = true;
            } else if (saveFile == 3) {
                Save = Read("Save2.txt");
                savePath = "Save2.txt";
                isSaveSelected = true;
            }
        }
        sendToBot(user+" Just loaded up the game using "+savePath);
        for (int s = 0; s < Save.length; s++) {

            if (Save[s] != null) {
                int val = 0;
                if (strIsInt(Save[s].toString())) {
                    val = Integer.parseInt(Save[s].toString());
                }

                if (s == 0) {
                    missionNum = val;
                }
                if (s == 1) {
                    HPmax = val;
                }
                if (s == 2) {
                    level2069 = val;
                }
                if (s == 3) {
                    levelR1 = val;
                }
                if (s == 4) {
                    exp1 = val;
                }
                if (s == 5) {
                    aqua.attackTier = val;
                }
                if (s == 6) {
                    lasershot.attackTier = val;
                }
                if (s == 7) {
                    cureTier = val;
                }
                if (s == 8) {
                    ember.attackTier = val;
                }
                if (s == 9) {
                    maxHit = val;
                }
                if (s == 10) {
                    is2048joined = Boolean.parseBoolean(Save[s].toString());
                }
                if (s == 11) {
                    is2051joined = Boolean.parseBoolean(Save[s].toString());
                }
                if (s == 13) {
                    login = val;
                }
            }
        }
        sPrint("Save grabbed");

    }


//don't pass this comment
}
