import java.util.ArrayList;

class Game extends Tools {
    public static final int MAX_EMMI = 8;
    String savePath;
    String user;
    int HP2069;
    int lastAttack = 5;
    int attackNum = 5;
    boolean attackType;
    int attackTime = 0;
    int attackStun = 0;
    int stars = 0;
    int speed = 0;
    int stun = 0;
    int Timeline;
    // Array vars (placed in Lbob.txt)
    int missionNum = 10;
    int HPmax = 50;
    int cureTier = 1;
    int level2069 = 1;
    int exp1 = 0;
    int levelR1 = 20;
    int maxHit = 5;
    boolean is2051joined = false;
    boolean is2048joined = false;
    Object[] resetArr = {1, 50, 1, 20, 0, 1, 1, 1, 1, 5};

    ItemClass[] allItems = {new ItemClass("staplers", 5, 10, 50, 100), new ItemClass("Cleaner", 3, 5, 100, 200), new ItemClass("ruler", 8, 12, 4, 5), new ItemClass("binder", 5, 20, 10, 20), new ItemClass("scissors", 7, 20, 3, 10), new ItemClass("Water Bottle (attack)", 1, 20, 2, 12), new ItemClass("labTop", 10, 20, 1, 2), new ItemClass("metalPiece", 1, 20, 1, 50), new ItemClass("basketBall", 10, 20, 1, 5), new ItemClass("FootBall", 5, 10, 3, 7), new ItemClass("tennisBall", 1, 5, 8, 20), new ItemClass("Soda", 5, 10), new ItemClass("Chip bag", 7, 12), new ItemClass("Water bottle (heal)", 6, 10), new ItemClass("lunch box", 1, 20)};
    Item backpack = allItems[random(0, allItems.length - 1)].createRandomItem();
    //2069 attacks
    Attack aqua = new Attack("Aqua", 5, 10, 6, 5);
    Attack freeze = new Attack("freeze", 17, 30, 15, 10);
    Attack ember = new Attack("Ember", 10, 20, 7, 4);
    Attack[] allAttacks = {aqua, freeze, ember};
    //misc
    int num = 0;

    //Setup
    public Game(String file, String name, int speed, int placement) {
        savePath = file;
        user = name;
        setTSpeed(speed);
        Timeline = placement;
        grabSave();
    }


    //fights giga mech
    public void fightMech() {
        Emmi giga = new Emmi(level2069 + 5);
        battle(giga);
    }

    //placeHolder for other games
    public void game() {
        sPrintln("NOTHING");
    }


    //Mission Rewards
    public void missionComplete(int mission) {
        sPrintln("MISSION " + mission + " COMPLETE");
        if (mission == missionNum) {
            num = random(mission * 10, mission * 25);
            missionNum++;
            sPrintln("MISSION " + missionNum + " UNLOCKED");
        } else {
            num = random(mission * 10, mission * 30);
        }
        sPrintln("2069 gains " + num + " exp");
        sendToBot(user + " Completed mission" + mission);
        exp1 += num;
        levelUp();
        System.out.println(SCREEN_CLEAR);
        save();
    }

    //boss fight
    public void bossFight(Boss boss) {
        if (!boss.differntPhases.isEmpty()) {
            HP2069 = HPmax;
            sendToBot(user + " begins to fight " + boss.differntPhases.get(0).name);
            while (!boss.differntPhases.isEmpty()) {
                Phase current = boss.differntPhases.get(0);
                sPrint(current.name + "'s Health " + current.getHP());
                sPrintln("2069's Health " + HP2069);

                current.pickAttack();
                Attack bossAttack = current.attacks[current.curAttack];
                attack();
                if (attackTime <= bossAttack.speed) {
                    current.loseHP(choseAttack());
                    current.loseHP(attackSupport());
                }
                if (current.HP > 1 && (attackTime > bossAttack.speed || attackStun < 10)) {
                    HP2069 -= bossAttack.attack();

                }
                boss.checkArray();
                restart();
                sendToBot(user + "defeated a Boss");
            }
            save();
            exp1 += 100;
            levelUp();
        }

    }

    //using dungeons
    public void dungeon(Dungeon dungeon) {
        dungeon.start();
        while (dungeon.dungeonLength > dungeon.amountMoved) {
            dungeon.move();
            if (dungeon.dungeonLength > dungeon.amountMoved) {
                sPrintln((dungeon.dungeonLength - dungeon.amountMoved) + " left to go");
                if (random(1, 10) == 10) {
                    Item place = allItems[random(0, allItems.length - 1)].createRandomItem();
                    if (backpack == null) {
                        sPrintln("You just found a " + place.toString());
                        backpack = place;
                    } else if (choice("Current Item: " + backpack + "\nNew Item: " + place + "\nDo you want this item ")) {
                        backpack = place;
                    }
                }
                if (random(missionNum, 15) == 15) {
                    battleGroup();
                } else {
                    battle();
                }
            }
        }
        save();
        sPrintln("Cleared " + dungeon.dungeonName);
    }

    //shows you what attacks you can use
    public void attack() {

        lastAttack = attackNum;
        attackNum = 0;

        sPrintln("2069's turn");
        sPrint("Fusion charm status: " + lastAttack);
        for (int i = 0; i < allAttacks.length; i++) {
            sPrint(i + ") " + allAttacks[i].toString());
        }
        sPrint(allAttacks.length + ") Fusion charm with Item (Speed: 3)");
        System.out.println();
        //This while loop just
        long start_Time = System.currentTimeMillis();

        sPrint("Which attack? A number (1-" + allAttacks.length + ")");
        attackNum = scanner.nextInt();
        System.out.println();
        String tackType;
        boolean typeDetermined = false;
        //loop that determines the type without making you want to break a .jar
        while (!typeDetermined) {
            sPrint("Chose (Power) or (Speed)");
            scanner.nextLine();
            tackType = scanner.nextLine().toLowerCase();
            if (tackType.equals("power")) {
                attackType = false;
                typeDetermined = true;
            } else if (tackType.equals("speed")) {
                attackType = true;
                typeDetermined = true;
            }
            System.out.println();
        }


        long end_Time = System.currentTimeMillis();
        attackTime = (int) ((end_Time - start_Time) / 1000);

        if (attackNum < allAttacks.length) {
            attackStun += allAttacks[attackNum].getStun(attackType);
            attackTime += allAttacks[attackNum].getSpeed(attackType);
        } else if (lastAttack < allAttacks.length) {
            attackTime += (allAttacks[lastAttack].getSpeed(attackType) / 2) + 3;
        } else {
            attackTime += 5;
        }

        attackStun += stun;
        attackTime -= speed + (tSpeed / 10);
        stun = 0;
        speed = 0;
        save();
    }

    //how much damage you did
    public int choseAttack() {
        num = 0;
        if (attackNum < allAttacks.length) {
            if (lastAttack != attackNum) {
                return allAttacks[attackNum].attack(1, attackType);
            } else {
                return allAttacks[attackNum].attack(0.5, attackType);
            }
        } else {
            return FusionMenu();
        }
    }

    //cure method
    public void cure(int bonus) {
        if (attackType) {
            sPrintln("dodging Cure");
            num = (int) (random(10, 12) * ((cureTier / 4) + 0.75) + bonus);
        } else {
            sPrintln("Cure shield");
            num = (int) (random(7, 20) * ((cureTier / 4) + 0.75) + bonus);
        }
        HP2069 += num;
        sPrintln("2069 heals " + num + " damage");
        if (HP2069 > HPmax) {
            HP2069 = HPmax;
        }
    }

    public int FusionMenu() {
        int bonus;
        if (lastAttack < allAttacks.length) {
            Attack cur = allAttacks[lastAttack];
            bonus = random(cur.low, cur.high) / 3;
            sPrint(cur.attackName + " is charged into this attack");
        } else {
            bonus = -10;
        }
        int stuff = 2;
        sPrint("1) Lasershot");
        sPrint("2) Healing Pad");
        if (backpack != null) {
            sPrint("3) " + backpack);
            stuff++;
        }
        sPrint("Which item? (1-" + stuff + ")");
        int attack = scanner.nextInt();
        if (attack == 1) {
            return laserShot(bonus);
        } else if (attack == 2) {
            cure(bonus);
            return 0;
        } else if (backpack != null) {
            return backpack.useItem(bonus);
        }
        return 0;
    }

    public int laserShot(int bonus) {
        sPrintln("LASERSHOT");
        num = random(7, 10) + (bonus * 2);
        sPrintln("Lasershot deals " + num + " damage");
        return num;
    }

    //return supports damage dealt
    public int attackSupport() {
        int total = 0;
        int hit = maxHit;
        if (hit > 10) {
            hit = 10;
        }
        sPrintln("2077's turn");
//starts quicktime event
        long startTime = System.currentTimeMillis();
        int i = 0;
        while (startTime + 10000 > System.currentTimeMillis()) {
            if (quickTime("Kick", 3000)) {
                i++;
            }
            if (startTime + 10000 > System.currentTimeMillis()) {
                if (quickTime("Punch", 3000)) {
                    i++;
                }
            }
        }
        total += i * (hit / 2);
        sPrintln("2077 Deals " + (i * (hit / 2)) + " Damage");

        if (is2048joined) {
            sPrintln("2048's turn");
            num = random(1, 2);
            if (num == 1) {
                sPrintln("TIME WARP");
                speed = 3;
                sPrintln("You attacks are 3 seconds faster");
            }
            if (num == 2) {
                sPrintln("STUN RUSH");
                stun = 3;
                sPrintln("You attacks are now more likely to stun someone");
            }
        }
        if (is2051joined) {
            if (choice("2051: need some healing? ")) {
                num = random(-10, 20 + hit);
                sPrintln("2069 heals " + num + " damage");
                HP2069 += num;
                if (HP2069 > HPmax) {
                    HP2069 = HPmax;
                }
            }


        }
        save();
        return total;
    }

    //chainAttack
    public int chainAttack(int HP) {
        int damage = 0;
        int speed = 15;
        boolean overkill = false;
        sPrintln("2051: I SEE AN OPENING");
        sPrintln("CHAIN ATTACK START");
        for (int round = 1; speed < attackTime; round++) {
            sPrint("Round: " + round);
            if (!overkill) {
                sPrint("2051: You have " + speed + " time left before the opening is gone");
            }
            attack();
            if (speed < attackTime || overkill) {
                damage += choseAttack();
                damage += attackSupport();
                speed += 5;
            }
            sPrintln("DAMAGE: " + damage);
            if (HP - damage <= 0 && !overkill) {
                sPrintln("ENEMY DOWN");
                sPrintln("OVERKILL START");
                overkill = true;
            }

        }
        sPrintln("CHAIN ATTACK FINISH");
        if (overkill) {
            exp1 += Math.abs(HP - damage);
            sPrintln("BONUS EXP: " + Math.abs(HP - damage));
        }
        save();
        return damage;
    }

    //fight enemies
    public void battle() {
        num = level2069;
        if (num > MAX_EMMI) {
            num = MAX_EMMI;
        }
        Emmi emmi = new Emmi(random(1, num), level2069 + stars);
        sendToBot(user + " just found a " + emmi.emmi_type);
        while (emmi.emmi_HP > 0) {
            sPrint("2069 health " + HP2069);
            sPrint(emmi.emmi_type + " health " + emmi.emmi_HP);
            System.out.println();
            emmi.emmi_prep();
            attack();
            if (attackTime < emmi.emmi_attack.speed) {
                emmi.emmi_HP -= choseAttack();
                if (attackNum != 3) {
                    emmi.emmi_HP -= attackSupport();
                }
            } else {
                sPrintln("Too slow. Pick a faster attack");
            }
            if (emmi.emmi_HP > 0 && (attackTime > emmi.emmi_attack.speed || attackStun < emmi.emmi_num + 5)) {
                HP2069 -= emmi.emmi_attack.attack();
            }
            if (is2051joined && (emmi.emmi_HPM / 3) < emmi.emmi_HP && attackStun < emmi.emmi_num + 2) {
                emmi.emmi_HP -= chainAttack(emmi.emmi_HP);
            }
            restart();
        }
        exp1 += (emmi.emmi_level * emmi.emmi_num) * 2;
        sPrintln("You gain " + (emmi.emmi_level * emmi.emmi_num) * 2 + " exp");
        sendToBot(user + " gains " + (emmi.emmi_level * emmi.emmi_num) * 2 + " exp");
        levelUp();
        save();
    }

    //group battle
    public void battleGroup() {
        num = level2069;
        if (num > 5) {
            num = 5;
        }
        ArrayList<Emmi> group = new ArrayList<>();
        for (int i = 0; i < num; i += random(0, 2)) {
            group.add(new Emmi(random(1, num), 1));
            sendToBot(user + " just found a " + group.get(i).emmi_type);
        }

        while (!group.isEmpty()) {
            Emmi cur = group.get(0);
            sPrint("2069 health " + HP2069);
            sPrint(cur.emmi_type + " health " + cur.emmi_HP);
            System.out.println();
            cur.emmi_prep();
            attack();
            if (attackTime < cur.emmi_attack.speed) {
                cur.emmi_HP -= choseAttack();
                if (attackNum != 3) {
                    cur.emmi_HP -= attackSupport();
                }
            } else {
                sPrintln("Too slow. Pick a faster attack");
            }
            if (cur.emmi_HP < 1) {
                group.remove(0);
            }
            for (int i = group.size() - 1; i > 0; i--) {
                HP2069 -= group.get(i).attack_emmi();
            }
            if (is2051joined && (cur.emmi_HPM / 3) < cur.emmi_HP && attackStun < cur.emmi_num + 2) {
                cur.emmi_HP -= chainAttack(cur.emmi_HP);
            }
            restart();
        }
        exp1 += 200;
        sPrintln("You gain 200 exp");
        sendToBot(user + " gains 200 exp");
        levelUp();
        save();
    }

    //Fights emmi
    public void battle(Emmi emmi) {
        sendToBot(user + " gains " + (emmi.emmi_level * emmi.emmi_num) * 2 + " exp");
        while (emmi.emmi_HP > 0) {
            sPrint("2069 health " + HP2069);
            sPrint(emmi.emmi_type + " health " + emmi.emmi_HP);
            System.out.println();
            emmi.emmi_prep();
            attack();
            if (attackTime < emmi.emmi_attack.speed) {
                emmi.emmi_HP -= choseAttack();
                if (attackNum != 3) {
                    emmi.emmi_HP -= attackSupport();
                }

            } else {
                sPrintln("Too slow. Pick a faster attack");
            }
            if (emmi.emmi_HP > 1 && (attackTime > emmi.emmi_attack.speed || attackStun < (emmi.emmi_num + 5))) {
                HP2069 -= emmi.emmi_attack.attack();
            } else if (is2051joined && (emmi.emmi_HPM / 3) < emmi.emmi_HP) {
                emmi.emmi_HP -= chainAttack(emmi.emmi_HP);
            }
        }

        exp1 += (emmi.emmi_level * emmi.emmi_num) * 2;
        sPrintln("You gain " + (emmi.emmi_level * emmi.emmi_num) * 2 + " exp");
        sendToBot(user + " gains " + (emmi.emmi_level * emmi.emmi_num) * 2 + " exp");
        levelUp();
        save();
    }

    //level up
    public void levelUp() {
        while (exp1 >= levelR1) {
            sPrintln("LEVEL UP");
            sPrintln(level2069 + " --> " + (level2069 + 1));
            sPrintln("2069: max health +1");
            HPmax++;
            level2069++;
            levelR1 = 20 * (level2069 * level2069) / 2;
            sPrintln("2069 has " + (levelR1 - exp1) + " exp till leveling up");
            sendToBot(user + " Just leveled up. They are now level " + level2069 + ". They got " + levelR1 + " till leveling up");
            save();
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

    //Saves
    public void save() {
        Object[] arrList = new Object[]{missionNum, HPmax, level2069, levelR1, exp1, aqua.attackTier, freeze.attackTier, cureTier, ember.attackTier, maxHit};
        Edit(savePath, arrList);
        System.gc();
    }

    //Grabs Data
    public void grabSave() {

        if (choice("Would you like to overwrite a save file? (Returns the file to the start of the game)")) {
            if (choice("Are you sure?")) {
                    Edit("Lbob.txt", resetArr);
            }
        }
        Object[] Save = Read(savePath);
        sendToBot(user + " Just loaded up the game using " + savePath);
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
                    freeze.attackTier = val;
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
                int[] team = readTeam();
                is2051joined = team[0] <= Timeline;
                is2048joined = team[1] <= Timeline;
            }

        }
        System.out.print(SCREEN_CLEAR);
        System.out.flush();
    }

    //gotcha
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
                int tier = random(1, random(2,random(3,random(4,5))));
                sPrintln("Tier " + tier + " pull");
                if (tier == 1) {
                    HPmax += 2;
                    sPrintln("2069's max Hp increased by 2");
                    sendToBot(user + "'s Max HP increased by 2");
                } else if (tier < 6) {
                    num = random(1, 4);
                    //Ember level up
                    if (num == 4) {
                        if (tier > ember.attackTier) {
                            sPrintln("ember leveled up");
                            sPrintln(ember.attackTier + " --> " + tier);
                            sendToBot(user + "'s ember leveled up " + ember.attackTier + " --> " + tier);
                            ember.attackTier = tier;
                        }
                    }
                    // Cure level up
                    if (num == 3) {
                        if (tier > cureTier) {
                            sPrintln("Cure leveled up");
                            sPrintln(cureTier + " -->" + tier);
                            sendToBot(user + "'s Cure leveled up " + cureTier + " --> " + tier);
                            cureTier = tier;
                        }

                    }
                    //aqua level up
                    if (num == 1) {
                        if (tier > aqua.attackTier) {
                            sPrintln("Aqua leveled up");
                            sPrintln(aqua.attackTier + " --> " + tier);
                            sendToBot(user + "'s Aqua leveled up " + aqua.attackTier + " --> " + tier);
                            aqua.attackTier = tier;
                        }
                    }
                    //Laser level up
                    if (num == 2) {
                        if (tier > freeze.attackTier) {
                            sPrintln("Laser leveled up");
                            sPrintln(freeze.attackTier + " --> " + tier);
                            sendToBot(user + "'s Laser leveled up " + freeze.attackTier + " --> " + tier);
                            freeze.attackTier = tier;

                        }
                    }
                } else if (tier == 6) {
                    maxHit += 1;
                    sPrintln("The power of supporting members increased by 1");
                    sendToBot(user + "'s power of supporting members increased by 1");
                } else {
                    maxHit += 2;
                    sPrintln("The power of supporting members increased by 2");
                    sendToBot(user + "'s power of supporting members increased by 2");
                }
                pull_num -= 1;
                save();
            }
        } else {
            sPrintln("YOU NEED MORE EXP POOR PERSON");
        }

    }

    public int useItem() {
        if (backpack.dur > 0) {
            if (backpack.isHeal) {
                HP2069 += useItem();
                return 0;
            } else {
                return useItem();
            }
        }
        return 0;
    }


}