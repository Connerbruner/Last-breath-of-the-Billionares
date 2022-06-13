import java.io.*;
import java.util.ArrayList;

class Lbor extends Game
{
    int time;
    Race[] allRaces = {
            new Race("VS roomba",new Emmi(1,5)),
            new Race("VS GIGA MECH",new Emmi(5)),
            new Race("Underground subway",subway),
            new Race("VS Bill gates",gates),
    };



    public Lbor(String name) {
        super(name);
        HPmax = 60;
        maxHit = 7;
        is2051joined = true;
        is2048joined = true;
    }
    public void menu()
    {
        for(int i=0; i<allRaces.length; i++)
        {
            sPrint();
        }
    }
    public void writeScores(int line) {
        try {
            File txt = new File("Race scores.txt");
            FileReader fileRead = new FileReader(txt);
            BufferedReader reader = new BufferedReader(fileRead);
            ArrayList<String> arr = new ArrayList<>();
            for (int i = 4; i > 0; i--) {
                if (i != line) {
                    arr.add(reader.readLine());
                } else {
                    arr.add(user+" ; "+time);
                }
            }
            reader.close();
            File fileToBeModified = new File("Race scores.txt");
            FileWriter writer = new FileWriter(fileToBeModified);
            for(int i = 0; i>arr.size(); i++)
            {
                writer.write(arr.get(i));
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }

    }
    public int readTime(int line)
    {
        try {
            File txt = new File("Race scores.txt");
            FileReader fileRead = new FileReader(txt);
            BufferedReader reader = new BufferedReader(fileRead);

            for (int r = 0; r < line; r++) {
                reader.readLine();
            }
            String info=reader.readLine();
            int i = 0;
            for (i=0; (int)(info.charAt(i))==59; i++);
            reader.close();
            if(strIsInt(info.substring(i+2)))
            {
                return Integer.parseInt(info.substring(i+2));
            }
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public String readName(int line)
    {
        try {
            File txt = new File("Race scores.txt");
            FileReader fileRead = new FileReader(txt);
            BufferedReader reader = new BufferedReader(fileRead);

            for (int r = 0; r < line; r++) {
                reader.readLine();
            }
            String info=reader.readLine();
            int i = 0;
            for (i=0; (int)(info.charAt(i))==59; i++);
            reader.close();
            return info.substring(0,i-2);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

}