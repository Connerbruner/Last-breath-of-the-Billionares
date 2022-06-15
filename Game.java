import java.util.ArrayList;

class Game extends Tools {
    Public static final int MAX_EMMI=8;
    String savePath;
    String user;
    int HP2069;
    int attackNum = 0;
    boolean attackType;
    int attackTime = 0;
    int attackStun = 0;
    int stars = 0;
    int speed = 0;
    int stun = 0;
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
    //2069 attacks
    Attack aqua = new Attack("Aqua", 7, 12, 6, 0);
    Attack lasershot = new Attack("Lasershot", 3, 10, 6, 8);
    Attack ember = new Attack("Ember", 17, 30, 15, 10);
    //misc
    int num = 0;

    //Setup
    public Game(String name, int speed) {
        user = name;
        setTSpeed(speed);
    }
    //fights giga mech
    public void fightMech() {
        Emmi giga = new Emmi(level2069 + 5);
        battle(giga);
        giga = null;
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
                    current.loseHP(choseAttack(1));
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
                battle();
            }
        }
        save();
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

            sPrint("Chose Out (Power) or Out (Speed)");
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
        save();
    }

    //how much damage you did
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
        }

        return num;
    }

    //cure method
    public void cure(double power) {
        if (attackType) {
            sPrintln("dodging Cure");
            num = (int) (random(10, 12) * ((cureTier / 4) + 0.75) * power);
        } else {
            sPrintln("Cure shield");
            num = (int) (random(5, 20) * ((cureTier / 4) + 0.75) * power);
        }
        HP2069 += num;
        sPrintln("2069 heals " + num + " damage");
        if (HP2069 > HPmax) {
            HP2069 = HPmax;
        }
    }


    //return surports damage dealt
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
        total += i * (hit / 2);
        sPrintln("2077 Deals " + (i * (hit / 2)) + " Damage");

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
        int health = HP;
        int damage = 0;
        int speed = 15;
        int mul = 1;
        boolean overkill = false;
        sPrintln("2051: I SEE AN OPENING");
        sPrintln("CHAIN ATTACK START");
        for (int round = 1; speed < 30; round++) {
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
                emmi.emmi_HP -= choseAttack(1);
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
        emmi = null;
        levelUp();
        save();
    }
    
    public void battleGroup() {
        num = level2069;
        if (num > 5) {
            num = 5;
        }
        ArrayList<Emmi> group = new ArrayList<Emmi>();
        for(int i=0; i>num; i+=random(0,2))
        {
            group.add(new Emmi(random(1,num),1));
            sendToBot(user + " just found a " + group.get(i).emmi_type);
        }
        
        while (group.isEmpty() > 0) {
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
        emmi = null;
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
                emmi.emmi_HP -= choseAttack(1);
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
                sendToBot("2048 just joined " + user + "'s party");
            } else if (!is2051joined && random(missionNum * 2, 30) == 30) {
                HP2069 = HPmax;
                sPrintln("2051: Playtime is over");
                sPrintln("2069: 2051!");
                sPrintln("*2051 has joined the team*");
                is2051joined = true;
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
        Object[] arrList = new Object[]{missionNum, HPmax, level2069, levelR1, exp1, aqua.attackTier, lasershot.attackTier, cureTier, ember.attackTier, maxHit, is2048joined, is2051joined};
        Edit(savePath, arrList);
        System.gc();
    }
    //Grabs Data
    public void grabSave() {

        if (choice("Would you like to overwrite a save file? (Returns the file to the start of the game)")) {
            if (choice("Are you sure?")) {
                sPrint("Which save would you like to overwrite?");
                int saveOverwrite = scanner.nextInt();
                if (saveOverwrite == 1) {
                    Edit("Lbob.txt", Read("SaveTemplate.txt"));
                } else if (saveOverwrite == 3) {
                    Edit("Save2.txt", Read("SaveTemplate.txt"));
                }
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

            }
        }
        System.out.print(SCREEN_CLEAR);
        System.out.flush();
    }
    //
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
                int[] odds = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 5, 6, 7};
                int tier = odds[random(0, odds.length - 1)];
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
                            ember.setAttackTier(tier);
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
                            aqua.setAttackTier(tier);
                        }
                    }
                    //Laser level up
                    if (num == 2) {
                        if (tier > lasershot.attackTier) {
                            sPrintln("Laser leveled up");
                            sPrintln(lasershot.attackTier + " --> " + tier);
                            sendToBot(user + "'s Laser leveled up " + lasershot.attackTier + " --> " + tier);
                            lasershot.setAttackTier(tier);

                        }
                    }
                } else if (tier == 6) {
                    maxHit += 1;
                    sPrintln("The power of supporting members increased by 1");
                    sendToBot(user + "'s power of supporting members increased by 1");
                } else if (tier == 7) {
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
}