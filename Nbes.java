import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.Thread.sleep;

public class Nbes {

    static Clip wavFile;

    static {
        try {
            wavFile = (Clip) AudioSystem.getLine(new Line.Info(Clip.class));
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
     Thread print;
    static int tSpeed = 5;
    static double musicMultiplier = 1;
    static final int SYSTEM_WIDTH = 400;
    static final int SYSTEM_HEIGHT = 640;
    static final int SYSTEM_BORDER = SYSTEM_WIDTH / 10;
    static final int MAX_CHAR = SYSTEM_WIDTH / 10;
    static final JLabel LABEL = new JLabel();
    static final JTextPane TEXT1 = new JTextPane();
    static final JTextField INPUT = new JTextField(10);
    static final JFrame SYSTEM = new JFrame("NBES (Non Binary Entertainment System)");

    static ArrayList<Color> PLATE_COLORS = new ArrayList<>();
    static Color SCREEN_COLOR = Color.WHITE;

    static final Color GREEN = new Color(0, 102, 0);
    static final Color CYAN = new Color(102, 153, 255);
    static final Color PURPLE = new Color(102, 0, 102);
    MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            keyButton = true;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
            keyButton = false;
        }
    };

    String lastsPrint = "";
    volatile boolean keyButton = false;


    public Nbes() {

        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Files/Retro Gaming.ttf")));
            TEXT1.setFont(new Font("Retro Gaming", Font.BOLD, 12));
        } catch (IOException | FontFormatException ignored) {
        }
        for (Cheat cheat : Cheat.cheats) ;
        LABEL.setLayout(new FlowLayout(FlowLayout.CENTER));
        StyledDocument style = TEXT1.getStyledDocument();
        SimpleAttributeSet align = new SimpleAttributeSet();
        StyleConstants.setAlignment(align, StyleConstants.ALIGN_CENTER);
        style.setParagraphAttributes(0, style.getLength(), align, false);
        TEXT1.setEditable(false);
        TEXT1.setBorder(null);
        TEXT1.setOpaque(false);
        TEXT1.setForeground(Color.BLACK);
        TEXT1.setSize(SYSTEM_WIDTH - SYSTEM_BORDER * 2, SYSTEM_HEIGHT);

        LABEL.add(TEXT1);
        INPUT.setEditable(false);
        SYSTEM.addMouseListener(mouseListener);
        SYSTEM.setResizable(false);
        SYSTEM.add(INPUT, BorderLayout.SOUTH);
        SYSTEM.add(LABEL);
        SYSTEM.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        SYSTEM.setVisible(true);
        LABEL.setIcon(new ImageIcon(new BufferedImage(SYSTEM_WIDTH, SYSTEM_HEIGHT, BufferedImage.TYPE_INT_ARGB)));
        SYSTEM.pack();
        LABEL.setIcon(new ImageIcon(createPlate()));
    }


    public BufferedImage createPlate() {

        if (PLATE_COLORS.isEmpty()) {
            Color color;
            if (inputBool("Would you like a multi color system?")) {

                while ((color = JColorChooser.showDialog(SYSTEM, "Chose a color to add to the system (Close stop choosing colors)", SYSTEM.getBackground())) != null)
                    PLATE_COLORS.add(color);
            } else {
                while ((color = JColorChooser.showDialog(SYSTEM, "What color should the system be", SYSTEM.getBackground())) == null)
                    ;
                PLATE_COLORS.add(color);
            }
        }
        while ((SCREEN_COLOR = JColorChooser.showDialog(SYSTEM, "What color should the screen be", SYSTEM.getBackground())) == null)
            ;
        BufferedImage plate = new BufferedImage(SYSTEM_WIDTH, SYSTEM_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < SYSTEM_HEIGHT; i++) {
            for (int j = 0; j < SYSTEM_WIDTH; j++) {
                if (i > SYSTEM_BORDER && i < SYSTEM_HEIGHT / 1.5 && j > SYSTEM_BORDER && j < SYSTEM_WIDTH - SYSTEM_BORDER) {
                    plate.setRGB(j, i, SCREEN_COLOR.getRGB());
                } else {
                    plate.setRGB(j, i, PLATE_COLORS.get(i / (SYSTEM_HEIGHT / PLATE_COLORS.size() + 1)).getRGB());
                }
            }
        }
        return plate;
    }


    public void sPrintln(String str) {
        TEXT1.setText("");
        AtomicReference<String> text = new AtomicReference<>("");
        str = textFormat(str);
        String finalStr = str;
        print = new Thread(() -> {
            for (int i = 0; i < finalStr.length(); i++) {
                int finalI = i;
                text.updateAndGet(v -> v + finalStr.charAt(finalI));
                setText1(text.get());
            }
            setText1(text + "\n>Click<");
        });

        SYSTEM.requestFocusInWindow();
        keyButton = false;
        print.start();
        while (!keyButton) ;
        print.stop();

        keyButton = false;
        TEXT1.setText("");
        lastsPrint = "";
    }

    public void sPrint(String str) {
        StringBuilder text = new StringBuilder();
        str = textFormat(str);
        for (int i = 0; i < str.length(); i++) {
            text.append(str.charAt(i));
            setText1(lastsPrint + text);
        }
        lastsPrint += str + "\n";
    }


    public static boolean strIsInt(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int random(int low, int high) {
        int range = high - low + 1;
        return (int) (Math.random() * range) + low;
    }

    public boolean quickTime(String word, int millis) {
        long startTime = System.currentTimeMillis();
        INPUT.setText("");
        sPrint("Type " + word);
        INPUT.setEditable(true);
        INPUT.requestFocus();
        while (!INPUT.getText().equals(word) && startTime + millis > System.currentTimeMillis() && wavFile.isOpen())
            ;
        TEXT1.setText("");
        INPUT.setEditable(false);
        return INPUT.getText().equals(word);
    }

    public int quickTime(int millis, String word) {
        long startTime = System.currentTimeMillis();
        int hits = 0;
        sPrint("Keeping Typing " + word + " until time is up");
        INPUT.setText("");
        INPUT.setEditable(true);
        INPUT.requestFocus();
        while (startTime + millis > System.currentTimeMillis() && wavFile.isOpen()) {
            if (INPUT.getText().equals(word)) {
                INPUT.setText("");
                hits++;
            }
        }
        INPUT.setEditable(false);
        return hits;
    }

    public String textFormat(String str) {
        StringBuilder string = new StringBuilder();
        char[] charArr = str.toCharArray();
        int reset = 0;
        for (char c : charArr) {
            if ((c + "").equals("\n")) {
                reset = 0;
            } else if (reset == MAX_CHAR) {
                string.append("\n");
                reset = 0;
            }
            string.append(c);
            reset++;
        }
        if (str.contains("2069")) {
            TEXT1.setForeground(Color.blue);
        } else if (str.contains("2077")) {
            TEXT1.setForeground(CYAN);
        } else if (str.contains("*")) {
            TEXT1.setForeground(GREEN);
        } else if (str.contains("???")) {
            TEXT1.setForeground(PURPLE);
        } else if (str.contains("Bill Gates") || str.contains("Elon musk") || str.contains("Jeff bezos") || str.contains("Mark Zuckerberg") || str.contains("UNKNOWN_PERSON") || str.contains("Gordy")) {
            TEXT1.setForeground(Color.red);
        } else {
            TEXT1.setForeground(Color.black);
        }
        return string.toString();
    }

    public String inputString(String str) {
        INPUT.setText("");
        INPUT.setEditable(true);
        INPUT.requestFocus();
        keyButton = false;
        print = new Thread(() -> {
            sPrint(str);
            sPrint("(Type in the text box then click)");
        });

        print.start();
        while (INPUT.getText().equals("") || !keyButton) ;
        print.stop();

        SYSTEM.requestFocusInWindow();
        INPUT.setEditable(false);
        lastsPrint = "";
        keyButton = false;
        return INPUT.getText();
    }

    public int inputInt(String str) {
        INPUT.setText("");
        INPUT.setEditable(true);
        INPUT.requestFocus();
        keyButton = false;
        print = new Thread(() -> {
            sPrint(str);
            sPrint("(Type in the text box then click)");
        });

        print.start();
        while (INPUT.getText().equals("") || !keyButton) ;
        print.stop();

        SYSTEM.requestFocusInWindow();
        INPUT.setEditable(false);
        lastsPrint = "";
        keyButton = false;
        if (strIsInt(INPUT.getText())) {
            return Integer.parseInt(INPUT.getText());
        }
        return 0;
    }

    public boolean inputBool(String str) {
        INPUT.setText("");
        INPUT.setEditable(true);
        INPUT.requestFocus();
        keyButton = false;
        print = new Thread(() -> {
            sPrint(str);
            sPrint("(Type in the text box then click)");
        });

        print.start();
        while (INPUT.getText().equals("") || !keyButton) ;
        print.stop();

        SYSTEM.requestFocusInWindow();
        INPUT.setEditable(false);
        lastsPrint = "";
        keyButton = false;
        return INPUT.getText().equalsIgnoreCase("yes") || INPUT.getText().equalsIgnoreCase("y");
    }

    public static void wait(int time) {
        long startTime = System.currentTimeMillis();
        while (startTime + time > System.currentTimeMillis()) ;
    }


    public void setText1(String str) {
        TEXT1.setText("\n\n\n\n\n" + str);
        try {
            if (!wavFile.isOpen()) {
                new Tone(tSpeed * random(100, 500), tSpeed).play();
            } else {
                new Tone(0, tSpeed).play();
            }
        } catch (LineUnavailableException ignored) {
        }
    }

    public static void playSound(String file) {
        try {
            wavFile.close();
            wavFile.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP)
                    wavFile.close();

            });
            wavFile.open(AudioSystem.getAudioInputStream(new File(file)));
            wavFile.start();

        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }

    public static Thread overcomeMe() {
        return new Thread(() -> {
            try {
                playSound("Files/OvercomeMe.wav");
                musicMultiplier = 0.5;
                sleep(15000);
                musicMultiplier = 1;
                sleep(10000);
                musicMultiplier = 1.25;
                sleep(9000);
                musicMultiplier = 1.4;
                sleep(16000);
                musicMultiplier = 1;
                sleep(7000);
                musicMultiplier = 1.25;
                sleep(9000);
                musicMultiplier = 1.5;
                sleep(15000);
                musicMultiplier = 1.75;
                sleep(24000);
                musicMultiplier = 0.5;
                sleep(10000);
                musicMultiplier = 1;
                sleep(18000);
                musicMultiplier = 1.5;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

    }
}


class Tone {
    public static float SAMPLE_RATE = 8000f;
    public static double VOLUME = 0.8;
    int hz;
    int millis = 100;

    public Tone(int HZ, int msecs) {
        hz = HZ;
        millis = msecs;
    }

    public Tone(int HZ) {
        hz = HZ;
    }

    public Tone() {
        hz = Nbes.random(0, 1000);
    }


    public void play() throws LineUnavailableException {

        byte[] buf = new byte[(int) SAMPLE_RATE * millis / 1000];
        for (int i = 0; i < buf.length; i++) {
            double angle = i / (SAMPLE_RATE / hz) * 2.0 * Math.PI;
            buf[i] = (byte) (Math.sin(angle) * 127.0 * VOLUME);
        }
// shape the front and back 10ms of the wave form
        for (int i = 0; i < SAMPLE_RATE / 100.0 && i < buf.length / 2; i++) {
            buf[i] = (byte) (buf[i] * i / (SAMPLE_RATE / 100.0));
            buf[buf.length - 1 - i] = (byte) (buf[buf.length - 1 - i] * i / (SAMPLE_RATE / 100.0));
        }

        AudioFormat af = new AudioFormat(SAMPLE_RATE, 8, 1, true, false);
        SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
        sdl.open(af);
        sdl.start();
        sdl.write(buf, 0, buf.length);
        sdl.drain();
        sdl.close();
    }

    public static void playAll(Tone[] arr) throws LineUnavailableException {
        for (Tone tone : arr) {
            tone.play();
        }
    }

    public static void playAll(int count) throws LineUnavailableException {
        for (; count > 0; count--) {
            new Tone().play();
        }
    }
}