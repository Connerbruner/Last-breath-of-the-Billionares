import java.util.ArrayList;

public class EmmiTraining extends Game {

    static boolean isTraining = false;
    int highScore = 0;

    public EmmiTraining(String file, Object[] arr) {
        super("AI TRAINING (COMING SOON", file, arr);
    }

    @Override
    void game() {
        massTrainEmmi(0);
    }

    public void massTrainEmmi(int count) {
        Nbes.tSpeed = 0;
        isTraining = true;
        for (int i = count; i > 0; i--) {
            ArrayList<Integer> data = battle();
            if (data.size() > 0) {
                int score = data.get(data.size() - 1);
                if (score > highScore) {
                    data.remove(data.size() - 1);
                    //turn attacks into weigths
                } else {
                    highScore--;
                }
            }
        }

        isTraining = false;
    }

    @Override
    public int attack() {
        return allAttacks.get(Nbes.random(0, allAttacks.size() - 1)).attack(null);
    }

    public ArrayList<Integer> battle() {
        HP = 50;
        Emmi emmi = new Emmi(false, 0);
        int emmiMaxHP = emmi.emmi_HP;
        ArrayList<Integer> attacks = new ArrayList<>();
        while (emmi.emmi_HP > 0 && HP > 0) {
            emmi.emmi_HP -= attack();
            attacks.add(1);//Weighted attack
            Attack attack = emmi.emmi_attacks[attacks.get(attacks.size() - 1)];
            if (Nbes.random(50 - attack.speed, 50) > 40) {
                HP -= attack.attack(new Object[]{emmi.emmi_level + emmi.damageBoost,});
            }

        }
        if (emmi.emmi_HP < 0) {
            return new ArrayList<>();
        } else {
            int healthScore = 100 - (emmi.emmi_HP % (emmiMaxHP / 2));
            int damageScore = (50 - Math.abs(HP)) + ((50 + Math.abs(HP)) / attacks.size());
            attacks.add(damageScore + healthScore);
        }
        return attacks;
    }

    public static void writeWeights(int index, String file, double[] arr) {
        Object[] saveData = FileRead.Read(file);
        StringBuilder data = new StringBuilder();
        for (double v : arr) {
            data.append(";").append(v);
        }
        saveData[index] = data.toString();
        FileRead.Edit(file, saveData);
    }

    public static double[] readWeights(int index, String file) {
        String data = Read(file)[index].toString();
        double[] arr = new double[data.length()];
        int j = 0;
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == 59) {
                int k = i;
                for (; data.charAt(k) != 59; k++) ;
                arr[j] = Double.parseDouble(data.substring(i, k));
                i = k;
                j++;
            }
        }
        return arr;
    }

    @Override
    public void grabSave() {
        if (nbes.inputBool("Reset weights?")) {
            Emmi[] fullArr = (Nbes.combineArray(Nbes.combineArray(Emmi.METAS, Emmi.OTHERS), Emmi.MINI_BOSSES));
            for (int i = 0; i < fullArr.length; i++) {
                double[] arr = new double[fullArr[i].emmi_attacks.length];
                for (int j = fullArr[i].emmi_attacks.length; j > 0; j--) {
                    arr[j] = 0.0;
                }
                writeWeights(i, savePath, arr);
            }
        }
    }
}
/*
  Get you back in the loop with lbob
  Give the emmis HEAL
  <p>
  Stuff we need to make:
  <p>
  Create Ai file import most stuff from the old project
  Create a training ver of battle that takes in an emmi or boss
 */
