import java.util.ArrayList;
import java.util.Collections;


public class Lbop extends Game {

    Main main = new Main();

    Dungeon[] dungeons = {
            new Dungeon("6-11", 5),
    new Dungeon("SafeCost", 10),
    new Dungeon("Busy street", 20),
    new Dungeon("Busy mall", 25),
    new Dungeon("Underground subway",30),
    new Dungeon("Storage center", 35),
    new Dungeon("Toll Bridge", 40),
    new Dungeon("Skyscraper", 50),
    new Dungeon("Huge Park", 60),
    new Dungeon("Funland", 70),
    new Dungeon("City roofTops", 75)
    };
    ArrayList<Dungeon> missionDungeons = new ArrayList<>();



    public Lbop(String file, String name, int speed, int placement) {
        super(file, name, speed, placement);
        resetArr = new Object[]{0, 50, 1, 20, 0, 1, 1, 1, 1, 5};

        int metaLeft=missionNum-1;
        ArrayList<Dungeon> dungeonsLeft = new ArrayList<>();
        Collections.addAll(dungeonsLeft, dungeons);

        while (metaLeft!=0)
        {
            for(int i=0; i<dungeonsLeft.size(); i++)
            {
                if(dungeonsLeft.get(i).dungeonLength>metaLeft)
                {
                    dungeonsLeft.remove(i);
                }
            }
            while (metaLeft<0)
            {
                metaLeft+=missionDungeons.get(0).dungeonLength;
                missionDungeons.remove(0);
            }
            missionDungeons.add(dungeonsLeft.get(random(0,random(1,dungeonsLeft.size()))));
        }
    }

    public void game() {
        if (missionNum == 0) {
            sPrintln("UNKNOWN_PERSON: send 1000 M.E.T.A s after them");
            sPrintln("Random person: YES SIR");
            sPrintln("UNKNOWN_PERSON: They will never live past this");
            missionNum+=1001;
        }
        sPrintln("M.E.T.As remaining: "+missionNum);
        sPrint("1) Explore the city");
        sPrint("2) ");
        sPrint("3) Quit");
    }

    public int battle(int targets) {
        sPrintln("2069: We have " + targets + " M.E.T.As on us");
        sPrintln("2077: Soon its gonna be zero");
        int distance = random(30, 100);

        ArrayList<Emmi> group = new ArrayList<>();
        for (int i = 0; i < targets; i++) {
            group.add(new Emmi(random(2, targets), level2069 / 3));
        }
        int deadTargets = 0;
        while (!group.isEmpty()) {
            sPrintln("2069: They are only " + distance + " Feet away");
            sPrint("2069 health " + HP2069);
            sPrint(group.get(0).emmi_type + " health " + group.get(0).emmi_HP);
            attack();
            group.get(0).emmi_HP -= choseAttack();
            distance -= attackTime;

            if (distance > 0) {
                distance += attackStun / 2;
                if (group.get(0).emmi_HP < 1) {
                    sPrintln("2069: good news: " + group.get(0).emmi_type + " is down");
                    group.remove(0);
                    deadTargets++;
                } else if (distance % 10 == 0 || distance > 100) {
                    targets--;
                    sPrintln("2069: good news: we lost the " + group.get(0).emmi_type);
                    group.remove(0);
                    if (distance > 100) {
                        sPrintln("2077: Watch out they are making a push to catch up");
                        distance /= 1.5;
                    }
                } else {
                    HP2069 -= group.get(random(0, group.size() - 1)).attack_emmi();
                    if (HP2069 < 1) {
                        restart();
                    }
                }
            } else {
                sPrintln("2069: The M.E.T.A's have caught up");
                sPrintln("2077: Let me take care of them");
                String word = group.get(random(0, group.size() - 1)).emmi_type;
                int i = 0;
                while (!group.isEmpty() && quickTime(word, group.size() * 1000)) {
                    i++;
                    if (i > group.get(0).emmi_num) {
                        i = 0;
                        sPrintln("2077: The " + group.get(0).emmi_type + " is down");
                        group.remove(0);
                        deadTargets++;
                    }
                    word = group.get(random(0, group.size() - 1)).emmi_type;
                }
                if (group.isEmpty()) {
                    return deadTargets;
                }
                restart();
            }
        }
        return deadTargets;
    }

    @Override
    public void dungeon(Dungeon dungeon) {
        sPrintln("UNKNOWN_PERSON: send " + dungeon.dungeonLength + " M.E.T.A s after them");
        while (dungeon.dungeonLength > dungeon.amountMoved) {
            num = ((dungeon.dungeonLength - dungeon.amountMoved) / 5) + 1;
            if (num > 5) {
                num = 5;
            }
            if (num < 2) {
                num = (dungeon.dungeonLength - dungeon.amountMoved);
            }
            dungeon.amountMoved += battle(num);
        }
    }

    @Override
    public void restart() {
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
            sPrintln("???: Its just sad to see you fall");
            sPrintln("???: Sadly I cant put you out of your suffering");

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
