public class Hero extends FileRead {
    boolean isHealing;
    boolean isUnlocked;
    String heroName;
    int levelUnlocked;
    HeroAttack attack;
    static Hero[] allHeros = {
            new Hero("2077", false, 0 ,() -> {
                    long startTime = System.currentTimeMillis();
                    int i = 0;
                    while (startTime + 10000 > System.currentTimeMillis()) {
                        if (nbes.quickTime("Kick", 5000)) {
                            i++;
                        }
                        if (startTime + 10000 > System.currentTimeMillis()) {
                            if (nbes.quickTime("Punch", 5000)) {
                                i++;
                            }
                        }
                    }
                    return i;
                }),

            new Hero("2069", false, 0, ()-> Hero.allHeros[Nbes.random(0, Hero.allHeros.length)].attack.attack()),
            new Hero("2048", false, 0, ()-> {
                if (nbes.inputBool("Go for a rush attack?")) {

                    if (Nbes.random(0, 1) == 1) {
                        Hero.allHeros[2].isHealing = false;
                        nbes.sPrintln("2048 deals 10 damage");
                        return 10;
                    } else {
                        Hero.allHeros[2].isHealing = true;
                        nbes.sPrintln("2048 hits 2051 for 10 damage");
                        nbes.sPrintln("2051: WHAT THE HECK 2051");
                        return -10;
                    }
                }
                return 0;
            }),
    };

    private Hero(String name, boolean healing, int unlocked, HeroAttack heroAttack) {
        isHealing = healing;
        heroName = name;
        levelUnlocked = unlocked;
        isUnlocked = false;
        attack = heroAttack;
    }


    public boolean isUnlocked(int Timeline) {
        return isUnlocked = levelUnlocked < Timeline;

    }

}

interface HeroAttack {
    int attack();
}
