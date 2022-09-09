public class EmmiTraining extends Game {
    
    static boolean isTraining = false;
    int highScore = 0;

    public void massTrainEmmi( int count ) {
        Nbes.tSpeed=0;
        isTraining=true;
        for(int i=count; i>0; i--) {
            ArrayList<Integer> data = battle(new Emmi(false,0));
        if(data.size()>0) {
            int score = data.get(data.size()-1);
            if(score>highScore) {
                data.remove(data.size()-1);
            }
            else {
                highScore--;
            }
        }
        }
        
        isTraining=false;
    }

    public ArrayList<Integer> battle(Emmi emmi) {
        HP=50;
        int emmiMaxHP = emmi.emmi_HP;
        ArrayList<Integer> attacks = new ArrayList<Integer>();
        while (emmi.emmi_HP > 0 && HP > 0 ) {
            emmi.emmi_HP -=  attack();
            attacks.add(//weighted choice);
            //CounterAttack if here
            HP -= emmi.emmi_attacks[attacks.get(attacks.size()-1)].attack(new Object[]{emmi.emmi_level,});
        }
        if(emmi.emmi_HP<0) {
            return new ArrayList<Integer>();
        } else {
            int healthScore = 100-(emmi.emmi_HP%(emmiMaxHP/2));
            int damageScore = (50-math.abs(HP))+((50+Math.abs(HP))/attacks.size());
            attacks.add(damageScore + healthScore);
        }
        return attacks;
    }
    public writeWeights(int index,double[] arr) {
        FileRead.Read()
    }

    
}
/**
 * Get you back in the loop with lbob
 * Give the emmis HEAL
 *
 * Stuff we need to make:
 * 
 * Create Ai file import most stuff from the old project
 * Create a training ver of battle that takes in a emmi or boss
 * Create a read write weights method FOR EACH EMMI AND BOSS WHOOOOOO
 * Create battle score ratio
 **/