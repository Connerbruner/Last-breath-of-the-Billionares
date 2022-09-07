public class Lbob extends Game {
    public Lbob(String file) {
        super("Last breath of the Billionaires", file, new Object[]{0, 100, 50, 1, 20, 0, 1, 1, 1, 5});
    }

    Mission[] missions = new Mission[]{
            () -> {
                nbes.sPrintln("Mission 0: The beginning");
                vist(AreaStorage.allAreas[0]);
                missionNum++;
            },
            () -> {
                nbes.sPrintln("Mission 1: First Encounter");
                vist(AreaStorage.allAreas[1]);
                battle(gates);
                missionNum++;
            },
            () -> {
                nbes.sPrintln("Mission 2: Double Trouble ");
                battle(elon);
                battle(jeff);
                missionNum++;
            },
            () -> {

            },
            () -> {

            },
            () -> {

            },
            () -> {

            },
            () -> {

            },
            () -> {

            },
    };
    Attack[] allUnlock = new Attack[]{
            new Attack("2051", "Kick", 10, 20, 10),
            new Attack("2051", "Punch", 5, 15, 7),
            new Attack("2051", "RUSH", 0, 1, 1),
            new Attack("2051", "METEOR PORTAL", 15, 40, 40),
            new Attack("2051", "TIME WARP", -5, 10, -5),
    };
    Attack[] attacks2077 = new Attack[]{
            new Attack("2077", "Kick", 10, 20, 10),
            new Attack("2077", "Punch", 5, 15, 7),
    };
    Attack[] attacks2048 = {
            new Attack("2048", "RUSH", 0, 1, 1),
            new Attack("2048", "METEOR PORTAL", 15, 40, 40),
            new Attack("2048", "TIME WARP", -5, 10, -5)
    };
    Boss[] playable = {
            new Boss(new Phase[]{
                    new Phase(attacks2048, 20, "2048"),
                    new Phase(attacks2048, 80, "2048"),
                    new Phase(attacks2048, 75, "2048 (Last breath)")
            }),
            new Boss(new Phase[]{
                    new Phase(allAttacks, 50, "2069"),
                    new Phase(attacks2048, 50, "2069"),
                    new Phase(attacks2077, 50, "2069(Last breath)"),
            }),
            new Boss(new Phase[]{
                    new Phase(attacks2077, 25, "2077"),
                    new Phase(attacks2077, 75, "2077"),
                    new Phase(attacks2077, 75, "2077 (Last breath)")
            }),
    };

    @Override
    public void game() {
        if (missionNum == 0) {
            nbes.sPrintln("UNKNOWN_PERSON: send 100 M.E.T.A s after them");
            nbes.sPrintln("random person: YES SIR");
            nbes.sPrintln("UNKNOWN_PERSON: They will never live past this");
            metaleft = 100;
            missionNum++;
            save();
        }
        while (true) {
            for (int i = 0; i < unlocked.size(); i++) {
                Hero.allHeros[i].isUnlocked(missionNum);
            }
        }
    }

    @Override
    public void grabSave() {
        super.grabSave();
        for (int i = 0; i < missionNum && i < allUnlock.length; i++) {
            allAttacks.add(allUnlock[i]);
        }
    }

    @Override
    public void restart() {
        nbes.sPrintln("GAME OVER");
        game();
    }

    public void train() {
        HP = HPmax;
        Boss per1 = playable[Nbes.random(0, playable.length - 1)];
        Boss per2;
        while ((per2 = playable[Nbes.random(0, playable.length - 1)]) != per1) ;
        nbes.sPrintln("2051: TRAINING TIME");
        nbes.sPrintln(per1.name + ": You ready?");
        nbes.sPrintln(per2.name + ": Yup, Lets go");
        battle(per1, per2);
        for (int i = per1.differentPhases.size(); i > -1; i--) {
            if (Nbes.random(0, 3) == 3) {
                for (int j = 0; j < allAttacks.size(); j += nbes.random(1, 3)) {
                    nbes.sPrintln(allAttacks.get(j).attackName + " Tier +1");
                    allAttacks.get(j).attackTier++;
                }
            } else if (Nbes.random(0, 3) == 3) {
                nbes.sPrintln("support power +3");
                nbes.sPrintln("Max HP +3");
                supportPower += 3;
                HPmax += 3;
            } else if (Nbes.random(0, 3) == 3) {
                nbes.sPrintln("support power +1");
                supportPower++;
            } else {
                nbes.sPrintln("Max HP +1");
                HPmax++;
            }
        }
    }

    public void vist(Area area) {
        while (area.current != area.areaHallways[area.areaHallways.length - 1]) {
            area.move();
            battle(new Emmi(false, level2051));
        }
    }
}