import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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

public class Nbes extends JFrame {

    static final int SYSTEM_WIDTH = 400;
    static final int SYSTEM_HEIGHT = 640;
    static final int SYSTEM_BORDER = SYSTEM_WIDTH / 10;
    static final int MAX_CHAR = SYSTEM_WIDTH/10;

    static final JTextPane TEXT1 = new JTextPane();
    static final JTextField INPUT = new JTextField(10);
    static final JFrame SYSTEM = new JFrame("NBES (Non Binary Entertainment System)");
    volatile boolean keyButton = false;
    static final int PLATERGB = JColorChooser.showDialog(SYSTEM, "What color would you like the system?", SYSTEM.getBackground()).getRGB();
    static final Color SCREEN_COLOR = JColorChooser.showDialog(SYSTEM, "What color would you like the screen?", SYSTEM.getBackground());

    String lastsPrint="";
    MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            keyButton=true;
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {
            keyButton=false;
        }
    };


    int tSpeed = 20;

    public Nbes() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Retro Gaming.ttf")));
            TEXT1.setFont(new Font("Retro Gaming",Font.BOLD,12));
        } catch (IOException|FontFormatException e) {
            //Handle exception
        }


        JLabel label = new JLabel();

        label.setLayout(new FlowLayout(FlowLayout.CENTER));
        StyledDocument style = TEXT1.getStyledDocument();
        SimpleAttributeSet align = new SimpleAttributeSet();
        StyleConstants.setAlignment(align, StyleConstants.ALIGN_CENTER);
        style.setParagraphAttributes(0, style.getLength(), align, false);
        TEXT1.setEditable(false);
        TEXT1.setBorder(null);
        TEXT1.setOpaque(false);
        TEXT1.setForeground(Color.BLACK);
        TEXT1.setSize(SYSTEM_WIDTH-SYSTEM_BORDER*2,SYSTEM_HEIGHT);
        label.add(TEXT1);
        INPUT.setEditable(false);

        label.setIcon(new ImageIcon(createPlate()));
        SYSTEM.addMouseListener(mouseListener);


        SYSTEM.setResizable(false);
        SYSTEM.add(INPUT, BorderLayout.SOUTH);
        SYSTEM.add(label);
        SYSTEM.pack();
        SYSTEM.setVisible(true);
        SystemMove(1000,2000,0,0);
        Sound sound = new Sound("sound.wav");
        sound.play();
        SYSTEM.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public void SystemMove(int x1,int y1,int x2,int y2)
    {

        int x=x1;
        int y=y1;
        while(!SYSTEM.getLocation().equals(new Point(x2, y2)))
        {
            if(x>x2)
            {
                x--;
            }
            else if(SYSTEM.getLocation().x!=x2){
                x++;
            }
            if(y>y2)
            {
                y--;
            }
            else if(SYSTEM.getLocation().y!=y2){
                y++;
            }
            SYSTEM.setLocation(x,y);
            wait(5);
        }
        System.out.println("done");
    }
    public BufferedImage createPlate() {
        BufferedImage plate = new BufferedImage(SYSTEM_WIDTH, SYSTEM_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < SYSTEM_HEIGHT; i++) {
            for (int j = 0; j < SYSTEM_WIDTH; j++) {
                if (i > SYSTEM_BORDER && i < SYSTEM_HEIGHT / 1.5 && j > SYSTEM_BORDER && j < SYSTEM_WIDTH - SYSTEM_BORDER) {
                    plate.setRGB(j, i, SCREEN_COLOR.getRGB());

                } else {
                    plate.setRGB(j, i, PLATERGB);
                }

            }
        }
        return plate;
    }

    public String inputString(String str) {
        sPrint(str + "\n(Type in the text box then click)");
        INPUT.setText("");
        INPUT.setEditable(true);
        while (INPUT.getText().equals("") || !keyButton);
        SYSTEM.requestFocusInWindow();
        INPUT.setEditable(false);
        lastsPrint="";
        keyButton = false;
        return INPUT.getText();
    }

    public int inputInt(String str) {
        sPrint(str + "\n(Type in the text box then click)");
        INPUT.setText("");
        INPUT.setEditable(true);
        while (INPUT.getText().equals("") || !keyButton);
        SYSTEM.requestFocusInWindow();
        INPUT.setEditable(false);
        lastsPrint="";
        keyButton = false;
        if (strIsInt(INPUT.getText())) {
            return Integer.parseInt(INPUT.getText());
        }
        return 0;
    }

    public boolean inputBool(String str) {
        sPrint(str + "\n(Type in the text box then click)");
        INPUT.setText("");
        INPUT.setEditable(true);
        while (INPUT.getText().equals("") || !keyButton);
        SYSTEM.requestFocusInWindow();
        INPUT.setEditable(false);
        String input = INPUT.getText().toLowerCase();
        lastsPrint="";
        keyButton = false;
        return input.equals("yes") || input.equals("y");
    }

    public void sPrintln(String str) {
        TEXT1.setText("");
        String text = "";
        for (int i = 0; i < str.length(); i++) {
            text += str.charAt(i);
            setText1(text);
        }
        setText1(text + "\n>Click<");
        while (!keyButton ) {
            SYSTEM.requestFocusInWindow();
        }
        keyButton = false;
        TEXT1.setText("");
        lastsPrint="";
    }

    public void sPrint(String str) {
        String text = "";
        String starting = lastsPrint;
        for (int i = 0; i < str.length(); i++) {
            text += str.charAt(i);
            addText1(starting,text);
        }
        lastsPrint+=str+"\n";

    }

    public void wait(int time) {
        long startTime = System.currentTimeMillis();
        while (startTime + time > System.currentTimeMillis()) ;
        SYSTEM.requestFocusInWindow();
    }

    public void setText1(String str) {
        StringBuilder text= new StringBuilder("\n\n\n\n\n");
        char[] charArr = str.toCharArray();
        for(int i=0; i<charArr.length; i++)
        {
            if(i%MAX_CHAR==0 && i>MAX_CHAR-1)
            {
                text.append("\n");
            }
            text.append(charArr[i]);
        }
        TEXT1.setText(text.toString());
        wait(tSpeed * 5);
    }
    public void addText1(String Starting,String str) {

        StringBuilder text = new StringBuilder("\n\n\n\n\n" + Starting);
        char[] charArr = str.toCharArray();
        for(int i=0; i<charArr.length; i++)
        {
            if(i%MAX_CHAR==0 && i>MAX_CHAR-1)
            {
                text.append("\n");
            }
            text.append(charArr[i]);
        }
        TEXT1.setText(text.toString());
        wait(tSpeed * 5);
    }

    public boolean strIsInt(String string) {
        try {
            int value = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public int random(int low, int high) {
        int range = high - low + 1;
        return (int) (Math.random() * range) + low;
    }
    public boolean quickTime(String word,int millis)
    {
        long startTime = System.currentTimeMillis();
        INPUT.setText("");
        sPrint("Type "+word);
        while(!INPUT.getText().equals(word) && startTime+ millis>System.currentTimeMillis());
        TEXT1.setText("");
        return INPUT.getText().equals(word);
    }


}