import java.util.Scanner;

//Main class
class Game extends Tools {


    int pin = 0;
    Object[] Save = null;
    String savePath = null;
    int HP2069;
    int attackNum = 0;
    int C = 0;
    boolean attackType;
    int lastAttack = 0;
    int attackTime = 0;
    int attackStun = 0;
    int stars = 0;
    int speed = 0;
    int stun = 0;
    int login = 0;
    double block;
    // Array vars (placed in Save.txt)
    int missionNum = 10;
    int HPmax = 50;
    int cureTier = 1;
    int level2069 = 1;
    int exp1 = 0;
    int levelR1 = 20;
    int maxHit = 5;
    boolean is2051joined = false;
    boolean is2048joined = false;
    boolean cupsUnlock = false;
    //misc
    int num = 0;
    //obj
    ///dungeons
    Dungeon subway = new Dungeon("Underground subway", 20);
    Dungeon local6_11 = new Dungeon("Rubble filled 6-11", 10);
    Dungeon factory = new Dungeon("Run down Factory", 30);
    Dungeon city = new Dungeon("Rubble filled City", 35);
    Dungeon highway = new Dungeon("Highway 101", 30);

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
    Phase mark = new Phase(attacksMark, 1000, "Mark Zuckerberg");
    Boss Zuckerberg = new Boss(mark);
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
    //2069 attacks
    Attack aqua = new Attack("Aqua", 7, 12, 6, 0);
    Attack lasershot = new Attack("Lasershot", 3, 10, 6, 8);
    Attack ember = new Attack("Ember", 17, 30, 15, 10);

    //other obj
    Text text = new Text();
    Scanner scanner = new Scanner(System.in);

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
                if (isDiscord) {
                    sPrint("Type 12 to leave and claim C");

                }
            }

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
            if (choice == 11 && cupsUnlock) {
                //to be implemented
            }
            if (choice == 12) {
                sPrint("ScreenShot for " + C + "C");
                run.exit(69420);
            }

            save();
//Edits txt

        }

    }

    public void save() {
        String time = System.currentTimeMillis() / 3600000 + "";
        Object[] arrList = new Object[]{missionNum, HPmax, level2069, levelR1, exp1, aqua.attackTier, lasershot.attackTier, cureTier, ember.attackTier, maxHit, is2048joined, is2051joined, cupsUnlock, time};
        if (savePath.equals("Save1.txt")) {
            Edit("Save1.txt", encrypt(arrList, "Save1.txt", pin));
        } else {
            Edit(savePath, arrList);
        }
        Object[] templateTxt = new Object[]{1, 50, 1, 20, 0, 1, 1, 1, 1, 5, false, false, false, time};
        Edit("SaveTemplate.txt", templateTxt);
        System.gc();

    }

    //fights giga mech
    public void fightMech() {
        Emmi giga = new Emmi(level2069 + 5);
        battle(giga);
        giga = null;
    }

    //Mission Rewards
    public void missionComplete(int mission) {
        sPrintln("MISSION " + mission + " COMPLETE");
        if (mission == missionNum) {
            num = random(mission * 10, mission * 25);
            if (mission < 10) {
                missionNum++;
                sPrintln("MISSION " + missionNum + " UNLOCKED");

            } else {
                cupsUnlock = true;
                sPrintln("ARENA OF SUFFERING UNLOCKED");
            }

        } else {
            if (!Tri.differntPhases.isEmpty()) {
                Tri.differntPhases.get(0).loseHP(50);
                Tri.checkArray();
            } else if (!Tri2.differntPhases.isEmpty()) {
                Tri2.differntPhases.get(0).loseHP(50);
                Tri2.checkArray();
            }
            num = random(mission * 10, mission * 30);
        }
        sPrintln("REWARDS:");
        sPrintln("2069 gains " + num + " exp");
        sendToBot(user+" Completed mission"+mission);
        exp1 += num;
        C += num;
        levelUp();
        System.out.println(SCREEN_CLEAR);
    }

    //boss fight
    public void bossFight(Boss boss) {
        if (!boss.differntPhases.isEmpty()) {
            HP2069 = HPmax;
            sendToBot(user+" begins to fight "+boss.differntPhases.get(0).name);
            while (!boss.differntPhases.isEmpty()) {
                Phase current = boss.differntPhases.get(0);
                sPrint(current.name + "'s Health " + current.getHP());
                sPrintln("2069's Health " + HP2069);

                current.pickAttack();
                Attack bossAttack = current.attacks[current.curAttack];
                attack();
                if (attackTime <= bossAttack.speed) {
                    current.loseHP(choseAttack(1));
                    current.loseHP(attackSupport());
                }
                if (current.HP > 1 && (attackTime > bossAttack.speed || attackStun < 10)) {
                    if ((attackStun - 10) > 0) {
                        block -= (attackStun - 10) / 10;
                    }
                    HP2069 -= bossAttack.attack(block);

                }
                boss.checkArray();
                restart();
                sendToBot(user+"defeated a Boss");
            }

            exp1 += 100;
            C += 100;
            levelUp();
        }

    }

    public void dungeon(Dungeon dungeon) {
        dungeon.start();
        while (dungeon.dungeonLength > dungeon.amountMoved) {
            dungeon.move();
            if (dungeon.dungeonLength > dungeon.amountMoved) {
                sPrintln((dungeon.dungeonLength - dungeon.amountMoved) + " left to go");
                battle();
            }

        }
        sPrintln("Cleared " + dungeon.dungeonName);
    }

    //shows you what attacks you can use
    public void attack() {

        attackNum = 0;
        sPrint("2069's turn");

        sPrint("1: " + aqua.attackName);

        sPrint("2: " + lasershot.attackName);

        sPrint("3: Cure");

        sPrint("4: " + ember.attackName);
        System.out.println();
        //This while loop just
        long start_Time = System.currentTimeMillis();

        sPrint("Which attack? (1-4)   ");
        attackNum = scanner.nextInt();

        System.out.println();

        String tackType = "";

        boolean typeDetermined = false;
        //loop that determines the type without making you want to break a glass jar
        while (!typeDetermined) {

            sPrint("Out Power or Out Speed");
            scanner.nextLine();
            tackType = scanner.nextLine();

            if (tackType.equals("power") || tackType.equals("Power")) {
                attackType = false;
                typeDetermined = true;
            } else if (tackType.equals("speed") || tackType.equals("Speed")) {
                attackType = true;
                typeDetermined = true;
            }

            System.out.println();

        }
        typeDetermined = false;
        tackType = "";

        long end_Time = System.currentTimeMillis();
        attackTime = (int) ((end_Time - start_Time) / 1000);
        if (attackNum == 1) {
            attackTime += aqua.getSpeed(attackType);
            attackStun = aqua.getStun(attackType);
        }
        if (attackNum == 2) {
            attackTime += lasershot.getSpeed(attackType);
            attackStun = lasershot.getStun(attackType);
        }
        if (attackNum == 4) {
            attackTime += ember.getSpeed(attackType);
            attackStun = ember.getStun(attackType);
        }
        if (attackNum == 3) {
            attackStun = 0;
        }
        attackStun += stun;
        attackTime -= speed;
        stun = 0;
        speed = 0;
    }

    /**
     * @return how much damage you did
     */
    public int choseAttack(double power) {
        num = 0;
        if (attackNum == 1) {
            num = aqua.attack(power, attackType);
        }
        if (attackNum == 2) {
            num = lasershot.attack(power, attackType);
        }
        if (attackNum == 3) {
            cure(power);
            num = 0;
        }
        if (attackNum == 4) {
            num = ember.attack(power, attackType);
            block = 1.1;
        }

        return num;
    }

    /**
     * @param power
     */
    //cure method
    public void cure(double power) {
        if (attackType) {
            sPrintln("dodging Cure");
            num = (int) (random(10, 12) * ((cureTier / 4) + 0.75) * power);
            block = random(0, random(1, 2));
        } else {
            sPrintln("Cure shield");
            num = (int) (random(5, 20) * ((cureTier / 4) + 0.75) * power);
            block = 0.5;
        }
        HP2069 += num;
        sPrintln("2069 heals " + num + " damage");
        if (HP2069 > HPmax) {
            HP2069 = HPmax;
        }
    }


    /**
     * @return surports damage dealt
     */
    public int attackSupport() {
        int total = 0;

        sPrintln("2077's turn");
//starts quicktime event
        long startTime = System.currentTimeMillis();
        int i = 0;
        while (startTime + 10000 > System.currentTimeMillis()) {
            sPrint("Type Kick");
            while (startTime + 10000 > System.currentTimeMillis()) {
                if (scanner.nextLine().equals("Kick")) {
                    i++;
                    break;

                }

            }
            sPrint("Type Punch");
            while (startTime + 10000 > System.currentTimeMillis()) {
                if (scanner.nextLine().equals("Punch")) {
                    i++;
                    break;

                }

            }

        }
        total += i * (maxHit / 2);
        sPrintln("2077 Deals " + (i * (maxHit / 2)) + " Damage");

        if (is2048joined) {
            sPrintln("2048's turn");
            num = random(1, 2);
            if (num == 1) {
                sPrintln("TIME WARP");
                speed = 3;
                sPrintln("You attacks are 3 seocnds faster");
            }
            if (num == 2) {
                sPrintln("STUN RUSH");
                stun = 3;
                sPrintln("You attacks are now more likely to stun someone");

            }
        }
        if (is2051joined) {
            if (choice("2051: need some healing? ")) {
                num = random(-10, 20);
                sPrintln("2069 heals " + num + " damage");
                HP2069 += num;
            }


        }
        return total;
    }


    public int chainAttack(int HP) {
        int health = HP;
        int damage = 0;
        int speed = 15;
        int mul = 1;
        boolean overkill = false;
        sPrintln("2051: I SEE AN OPENING");
        sPrintln("CHAIN ATTACK START");
        for (int round = 1; speed > 30; round++) {
            sPrint("Round: " + round);
            if (!overkill) {
                sPrint("2051: You have " + speed + " time left before the opening is gone");
            }
            attack();
            if (speed < attackTime || overkill) {
                damage += choseAttack(mul);
                mul += attackStun / 10;
                damage += attackSupport();

            }
            sPrintln("DAMAGE: " + damage);
            if (HP - damage <= 0 && !overkill) {
                sPrintln("ENEMY DOWN");
                sPrintln("OVERKILL START");
                overkill = true;
                mul *= 2;
            }
        }
        sPrintln("CHAIN ATTACK FINISH");
        if (overkill) {
            exp1 += Math.abs(HP - damage);
            sPrintln("BONUS EXP: " + Math.abs(HP - damage));
            C += Math.abs(HP - damage);
        }
        return damage;
    }

    //fight enemies

    public void battle() {
        num = level2069;
        if (num > 8) {
            num = 8;
        }
        Emmi emmi;
        if (isRoomba) {
            emmi = new Emmi(1, level2069 + stars);
        } else {
            emmi = new Emmi(random(1, num), level2069 + stars);
        }
        sendToBot(user+" just found a "+emmi.emmi_type);
        while (emmi.emmi_HP > 0) {
            sPrint("2069 health " + HP2069);
            sPrint(emmi.emmi_type + " health " + emmi.emmi_HP);
            System.out.println();
            emmi.emmi_prep();
            attack();
            if (attackTime < emmi.emmi_attack.speed) {
                emmi.emmi_HP -= choseAttack(1);
                if (attackNum != 3) {
                    emmi.emmi_HP -= attackSupport();
                }
            }
            if (emmi.emmi_HP > 1 && (attackTime > emmi.emmi_attack.speed || attackStun < (emmi.emmi_num + 5))) {
                if ((attackStun - (emmi.emmi_num + 5)) > 0) {
                    block -= (attackStun - (emmi.emmi_num + 5)) / 10;
                }
                HP2069 -= emmi.emmi_attack.attack(block);

            } else if (is2051joined && (emmi.emmi_HPM / 3) < emmi.emmi_HP) {
                emmi.emmi_HP -= chainAttack(emmi.emmi_HP);
            }
            restart();
        }
        exp1 += (emmi.emmi_level * emmi.emmi_num) * 2;
        sPrintln("You gain " + (emmi.emmi_level * emmi.emmi_num) * 2 + " exp");
        C += (emmi.emmi_level * emmi.emmi_num) * 2;
        sendToBot(user+" gains " + (emmi.emmi_level * emmi.emmi_num) * 2 + " exp");
        emmi = null;
        levelUp();
        save();
    }

    public void battle(Emmi emmi) {
        sendToBot(user+" gains " + (emmi.emmi_level * emmi.emmi_num) * 2 + " exp");
        while (emmi.emmi_HP > 0) {
            sPrint("2069 health " + HP2069);
            sPrint(emmi.emmi_type + " health " + emmi.emmi_HP);
            System.out.println();
            emmi.emmi_prep();
            attack();
            if (attackTime < emmi.emmi_attack.speed) {
                emmi.emmi_HP -= choseAttack(1);
                if (attackNum != 3) {
                    emmi.emmi_HP -= attackSupport();
                }

            }
            if (emmi.emmi_HP > 1 && (attackTime > emmi.emmi_attack.speed || attackStun < (emmi.emmi_num + 5))) {
                if ((attackStun - (emmi.emmi_num + 5)) > 0) {
                    block -= (attackStun - (emmi.emmi_num + 5)) / 20;
                }
                HP2069 -= emmi.emmi_attack.attack(block);
            } else if (is2051joined && (emmi.emmi_HPM / 3) < emmi.emmi_HP) {
                emmi.emmi_HP -= chainAttack(emmi.emmi_HP);
            }
            restart();
        }
        exp1 += (emmi.emmi_level * emmi.emmi_num) * 2;
        sPrintln("You gain " + (emmi.emmi_level * emmi.emmi_num) * 2 + " exp");
        sendToBot(user+" gains " + (emmi.emmi_level * emmi.emmi_num) * 2 + " exp");
        C += (emmi.emmi_level * emmi.emmi_num) * 2;
        levelUp();
        save();
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
                int[] odds = new int[]{1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4, 5, 6, 7};
                int tier = odds[random(0, odds.length - 1)];
                sPrintln("Tier " + tier + " pull");
                if (tier == 1) {
                    HPmax += 2;
                    sPrintln("2069's max Hp increased by 2");
                    sendToBot(user+"'s Max HP increased by 2");
                } else if (tier < 6) {
                    sPrint("One of your moves is leveling up");
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

    public void levelUp() {
        while (exp1 >= levelR1) {
            sPrintln("LEVEL UP");
            sPrintln(level2069 + " --> " + (level2069 + 1));
            sPrintln("2069: max health +1");
            HPmax++;
            level2069++;
            levelR1 = 20 * (level2069 * level2069) / 2;
            sPrintln("2069 has " + (levelR1 - exp1) + " exp till leveling up");
            sendToBot(user+" Just leveled up. They are now level "+level2069+". They got "+levelR1+" till leveling up");
            C += 30;
        }


    }

    //Game Over
    public void restart() {
        if (HP2069 < 0) {
            if (!is2048joined && random(missionNum * 2, 30) == 30) {
                HP2069 = HPmax;
                sPrintln("2048: can't handle things on your own");
                sPrintln("2069: 2048!");
                sPrintln("*2048 has joined the team*");
                is2048joined = true;
                sendToBot("2048 just joined "+user+"'s party");
            } else if (!is2051joined && random(missionNum * 2, 30) == 30) {
                HP2069 = HPmax;
                sPrintln("2051: Playtime is over");
                sPrintln("2069: 2051!");
                sPrintln("*2051 has joined the team*");
                is2051joined = true;
                sendToBot("2051 just joined "+user+"'s party");
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
                save();
                sendToBot(user+" has fallen. Good thing ??? is here");
                game();
            }

        }

    }

    //uses READ to update save
    public void grabSave() {

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
                if (strIsInt(checksum[13].toString())) {
                    Save = Read("tempsave.txt");
                    savePath = "Save1.txt";
                    Edit("tempsave.txt", Read("SaveTemplate.txt"));
                    for (int i = 0; i < checksum.length; i++) {
                        checksum[i] = 0;
                    }
                    sPrintln("???: That is the correct pin. Hello Floof or C1nner");
                } else { //exits on bad pin
                    sPrintln("???: You're not a dev! GET OUT! Don't try to access this file again !");
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
                if (s == 12) {
                    cupsUnlock = Boolean.parseBoolean(Save[s].toString());
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
