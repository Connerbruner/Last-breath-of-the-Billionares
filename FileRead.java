import java.io.*;

@SuppressWarnings("unused")
class FileRead {
    final static Nbes nbes = new Nbes();
    Runtime run = Runtime.getRuntime();
    int tSpeed;

    public static void Edit(String filePath, Object[] arr) {
        File fileToBeModified = new File(filePath);
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileToBeModified);
            for (Object o : arr) {
                String print = o.toString() + "\n";
                writer.write(print);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //Closing the resources
                assert writer != null;
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @return array of saved vars
     */
    public static Object[] Read(String file) {

        try {
            File txt = new File(file);
            FileReader fileRead = new FileReader(txt);
            BufferedReader reader = new BufferedReader(fileRead);
            int i = 0;
            Object[] arr = new Object[13];

            for (int r = 0; r < arr.length; r++) {
                Object var = reader.readLine();
                arr[r] = var;
            }
            reader.close();
            return arr;
        } catch (IOException e) {
            e.printStackTrace();
            return new Object[0];
        }
    }

    public void sendToBot(String str) {
        File fileToBeModified = new File("bot.txt");
        FileWriter wr;
        try {
            wr = new FileWriter(fileToBeModified);
            wr.write(str);
            wr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int[] readTeam() {
        try {
            File txt = new File("Team.txt");
            FileReader fileRead = new FileReader(txt);
            BufferedReader reader = new BufferedReader(fileRead);
            return new int[]{Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine())};

        } catch (IOException e) {
            e.printStackTrace();
            return new int[0];
        }
    }

    public void writeTeam(boolean b2051, boolean b2048, int Timeline) {
        int[] team = readTeam();
        try {
            File fileToBeModified = new File("Team.txt");
            FileWriter writer = new FileWriter(fileToBeModified);
            writer.write(Math.min(team[0], Timeline));
            writer.write(Math.min(team[1], Timeline));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}