import java.io.*;
import java.util.Scanner;

class Tools {
            //the Colors of gay
    public static final String SCREEN_CLEAR = "\033[H\033[2J";
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    //start of cheat codes
    Runtime run = Runtime.getRuntime();
    //end of cheat codes
    int tSpeed;
    String[] colors = new String[]{RED, GREEN, BLUE, PURPLE, CYAN};

    int pin = 0;
    Object[] Save = null;
    String savePath = null;
    Scanner scanner = new Scanner(System.in);
    public static void Edit(String filePath, Object[] arr) {
        File fileToBeModified = new File(filePath);
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileToBeModified);
            for (int i = 0; i < arr.length; i++) {
                String print = arr[i].toString() + "\n";
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

    /**
     * @param num
     */
    public void setTSpeed(int num) {
        tSpeed = num;
    }

    /**
     * @return RANDOM NUMBER between low and high iirc LETS GO
     */
    public int random(int low, int high) {
        int range = high - low + 1;
        return (int) (Math.random() * range) + low;
    }

    /**
     *
     */
    //print method
    public void sPrintln(String str) {
        //if 2069 is talking BLUE
        if (str.contains("2069")) {
            for (int i = 0; i < str.length(); i++) {
                System.out.print(BLUE + str.charAt(i));
                wait(tSpeed);
            }
        }

        //if 2077 is talking CYAN
        else if (str.contains("2077")) {
            for (int i = 0; i < str.length(); i++) {
                System.out.print(CYAN + str.charAt(i));
                wait(tSpeed);
            }

        }
        //Tech giant talking RED
        else if (str.contains("Mark Zuckerberg") || str.contains("Elon musk") || str.contains("Bill Gates") || str.contains("Jeff bezos")) {
            for (int i = 0; i < str.length(); i++) {
                System.out.print(RED + str.charAt(i));
                wait(tSpeed);
            }
        }
        //??? talks
        else if (str.contains("???")) {
            for (int i = 0; i < str.length(); i++) {
                if (random(0, 15) == 10 &&  str.charAt( i )!=63) {
                    System.out.print((char) (random(0, 3000)));
                } else {
                    System.out.print(PURPLE + str.charAt(i));
                }
                wait(tSpeed);
            }
        }
        //if sounds then
        else if (str.contains("*")) {
            for (int i = 0; i < str.length(); i++) {
                System.out.print(GREEN + str.charAt(i));
                wait(tSpeed);
            }
        }

        //Anything else
        else {
            for (int i = 0; i < str.length(); i++) {
                System.out.print(str.charAt(i));
                wait(tSpeed);
            }
        }
        System.out.println();
        System.out.println(RESET);
        System.out.println("> enter <");
        scanner.nextLine();
    }

    /**
     *
     */
    public void wait(int time) {

        long startTime = System.currentTimeMillis();
        while (startTime + time > System.currentTimeMillis());
    }

    /**
     *
     */
    public void sPrint(String str) {
        for (int i = 0; i < str.length(); i++) {
            System.out.print(str.charAt(i));
            wait(tSpeed);
        }
        System.out.println();


    }

    /**
     * @return Is that string castable
     */
    public boolean strIsInt(String string) {
        try {
            int value = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns false on "Yes, yes, y, or Y"
     */
    public boolean skip() {
        sPrint("Skip cutscene?   ");
        String skip = scanner.nextLine();
        boolean yesSkip = skip.equals("yes") || skip.equals("Yes") || skip.equals("Y") || skip.equals("y");
        return !yesSkip;
    }

    public boolean choice(String str) {
        sPrint(str);
        String skip = scanner.nextLine();
        boolean yesSkip = skip.equals("yes") || skip.equals("Yes") || skip.equals("Y") || skip.equals("y");
        return yesSkip;
    }

    public void loading() {
        int i = 176;
        long startTime = System.currentTimeMillis();
        while (startTime + 2000 > System.currentTimeMillis()) {
            System.out.print((char) (i));
            i++;
            if (i == 2000) {
                i = 176;
            }
        }
        System.out.println("\033[H\033[2J");
        System.out.flush();

    }
    public void sendToBot(String str)
    {
        File fileToBeModified = new File("bot.txt");
        FileWriter wr = null;
        try {
            wr = new FileWriter(fileToBeModified);
            wr.write(str);
            wr.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

   
}