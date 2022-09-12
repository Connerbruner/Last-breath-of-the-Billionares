class Emmi extends FileRead {
    String emmi_type;
    int emmi_HP;
    int emmi_level;
    int emmi_num;
    int damageBoost=0;
    Attack[] emmi_attacks;
    Attack curAttack;

    final Attack[] BASE_ATTACKS = {
        new Attack("","CHARGE UP",0,0,0, ()->{
                emmi_HP-=15;
                damageBoost=10+emmi_level;
                nbes.sPrintln("CHARGE UP");
                nbes.sPrintln(emmi_type+" Loses 15 health");
                nbes.sPrintln(emmi_type+" Will do "+damageBoost+" extra damage");
                return 0;
        }),
        new Attack("","RECHARGE",0,0,0, ()->{
                int num = Nbes.random(0,10)+emmi_level+damageBoost;
                emmi_HP+=num;
                nbes.sPrintln(emmi_type+" gains "+num+" HP");
                return 0;
        })
    };
    static Emmi[] METAS = {
            new Emmi("Sword bot", 25, new Attack[]{
                    new Attack("Sword bot", "TRIPLE SLASH", 5, 15, 17),
                    new Attack("Sword bot", "BLADE OF DESTRUCTION", 1, 20, 30),
                    new Attack("Sword bot", "ZERO RUSH", 7, 10, 10)
            }),
            new Emmi("Blaster bot", 40, new Attack[]{
                    new Attack("Blaster bot", "BULLET STORM", 10, 15, 30),
                    new Attack("Blaster bot", "ZERO RUSH", 5, 11, 10),
                    new Attack("Blaster bot", "ZERO RUSH", 5, 11, 10)
            }),
            new Emmi("Spear bot", 25, new Attack[]{
                    new Attack("Spear bot", "SPEAR RUSH", 5, 10, 7),
                    new Attack("Spear bot", "ZERO RUSH", 5, 11, 10),
                    new Attack("Spear bot", "ULTIMATE SPEAR", 5, 20, 20)
            }),
            new Emmi("Cyborg", 125, new Attack[]{
                    new Attack("Cyborg", "POWER BURST", 10, 30, 20),
                    new Attack("Cyborg", "LASER SLASH", 15, 25, 10),
                    new Attack("Cyborg", "CHARGE BEAM", 5, 40, 30),
            }),

    };
    static Emmi[] MINI_BOSSES = {
            new Emmi("GIGA MECH", 200, new Attack[]{
                    new Attack("GIGA MECH", "LASER RAIN", 10, 25, 15),
                    new Attack("GIGA MECH", "POWER BURST", 15, 30, 17),
                    new Attack("GIGA MECH", "ULTIMATE RUSH", 5, 50, 25)
            }),
            new Emmi("Mini Mech", 150, new Attack[]{
                    new Attack("Mini Mech", "LASER RAIN", 5, 25, 20),
                    new Attack("Mini Mech", "POWER BURST", 10, 20, 30),
                    new Attack("Mini Mech", "TRIPLE SLASH", 15, 17, 10)
            }),
            new Emmi("Mech", 175, new Attack[]{
                    new Attack("Mech", "LASER RAIN", 5, 25, 20),
                    new Attack("Mech", "POWER BURST", 10, 30, 20),
                    new Attack("Mech", "ULTIMATE RUSH", 1, 50, 30)
            }),
            new Emmi("Tesla", 150, new Attack[]{
                    new Attack("Tesla", "SPEED RUSH", 5, 10, 5),
                    new Attack("Tesla", "POWER BURST", 10, 20, 15),
                    new Attack("Tesla", "DUMP TRUCK SLAM", 20, 30, 35)
            }),
    };
    static Emmi[] OTHERS = {
            new Emmi("Roomba", 35, new Attack[]{
                    new Attack("Roomba", "ROOMBA RUSH", 10, 30, 30),
                    new Attack("Roomba", "DARK RUSH", 1, 10, 15)
            }),
            new Emmi("Dog bot", 35, new Attack[]{
                    new Attack("Dog bot", "SPEED TACKLE", 1, 3, 5),
                    new Attack("Dog bot", "BITE FURY", 1, 7, 10),
            }),
            new Emmi("Fridge", 50, new Attack[]{
                    new Attack("Fridge", "BLIZZARD", 30, 50, 50),
                    new Attack("Fridge", "FROST BREATH", 10, 20, 20),
            }),
            new Emmi("Lawn mower", 40, new Attack[]{
                    new Attack("Lawn mower", "MOWER RUSH", 10, 25, 15),
                    new Attack("Lawn mower", "GRASS BREATH", 1, 7, 7),
            }),
            new Emmi("10 9v batteries powering an arduino nano", 10, new Attack[]{
                    new Attack("10 9v batteries powering an arduino nano", "ACID SHOT", 10, 20, 15),
                    new Attack("10 9v batteries powering an arduino nano", "SHOCK", 1, 5, 1),
            }),
            new Emmi("Chromebook Cart", 70,new Attack[]{
                    new Attack("Chromebook Cart", "TRUCK SMASH", 35, 40, 50),
                    new Attack("Chromebook Cart", "ROLLING CART RUSH", 20, 30, 40),
            }),
    };


    public Emmi(Emmi emmi) {
        emmi_type = emmi.emmi_type;
        emmi_HP = emmi.emmi_HP;
        emmi_level = emmi.emmi_level;
        emmi_num = emmi.emmi_num;
        emmi_attacks = emmi.emmi_attacks;
        curAttack = null;
    }

    public Emmi(boolean isMeta, int level) {
        emmi_level = level;
        int num = level;
        if (num > METAS.length - 1) {
            num = METAS.length - 1;
        }
        if (isMeta || Nbes.random(0, METAS.length - (num - 1)) == 0) {

            int emmi_index = Nbes.random(0, num);
            emmi_type = METAS[emmi_index].emmi_type;
            emmi_HP = METAS[emmi_index].emmi_HP + emmi_level * 3;
            emmi_attacks = METAS[emmi_index].emmi_attacks;
            emmi_num = emmi_index;
        } else {
            if (num > OTHERS.length - 1) {
                num = OTHERS.length - 1;
            }
            int emmi_index = Nbes.random(0, num);
            emmi_type = OTHERS[emmi_index].emmi_type;
            emmi_HP = OTHERS[emmi_index].emmi_HP + emmi_level * 2;
            emmi_attacks = OTHERS[emmi_index].emmi_attacks;
            emmi_num = emmi_index;
        }
        nbes.sPrintln("2051: " + emmi_type + "!");
    }


    public Emmi(int level) {
        emmi_level = level;
        int num = level;
        if (num > MINI_BOSSES.length - 1) {
            num = MINI_BOSSES.length - 1;
        }
        int emmi_index = Nbes.random(0, num);
        emmi_type = MINI_BOSSES[emmi_index].emmi_type;
        emmi_HP = MINI_BOSSES[emmi_index].emmi_HP + level * 5;
        emmi_attacks = MINI_BOSSES[emmi_index].emmi_attacks;
        emmi_num = emmi_index;
        nbes.sPrintln("2051: " + emmi_type + "!");
    }

    private Emmi(String name, int base_HP, Attack[] attacks) {
        emmi_type = name;
        emmi_HP = base_HP;
        emmi_attacks = Nbes.combineArray(attacks,Attack.copyToNewUser(BASE_ATTACKS,name));
    }

    public int attack() {
        return emmi_attacks[Nbes.random(0, emmi_attacks.length - 1)].attack(new Object[]{emmi_level+damageBoost,});
    }

}