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

public class Nbes {


    static final int        SYSTEM_WIDTH  = 400;
    static final int        SYSTEM_HEIGHT = 640;
    static final int        SYSTEM_BORDER = SYSTEM_WIDTH / 10;
    static final int        MAX_CHAR      = SYSTEM_WIDTH / 10;
    static final JLabel     LABEL         = new JLabel( );
    static final JTextPane  TEXT1         = new JTextPane( );
    static final JTextField INPUT         = new JTextField( 10 );
    static final JFrame     SYSTEM        = new JFrame( "NBES (Non Binary Entertainment System)" );

    static final Color PLATE_COLOR  = JColorChooser.showDialog( SYSTEM , "What color would you like the system?" , SYSTEM.getBackground( ) );
    static final Color SCREEN_COLOR = JColorChooser.showDialog( SYSTEM , "What color would you like the screen?" , SYSTEM.getBackground( ) );

    static final Color GREEN  = new Color( 0 , 102 , 0 );
    static final Color CYAN   = new Color( 102 , 153 , 255 );
    static final Color PURPLE = new Color( 102 , 0 , 102 );
    MouseListener mouseListener = new MouseListener( ) {
        @Override
        public void mouseClicked( MouseEvent e ) {}

        @Override
        public void mousePressed( MouseEvent e ) {
            keyButton = true;
        }

        @Override
        public void mouseReleased( MouseEvent e ) {}

        @Override
        public void mouseEntered( MouseEvent e ) {}

        @Override
        public void mouseExited( MouseEvent e ) {
            keyButton = false;
        }
    };

    String lastsPrint = "";
    volatile boolean keyButton = false;
    int tSpeed = 5;

    public Nbes( ) {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment( );
            ge.registerFont( Font.createFont( Font.TRUETYPE_FONT , new File( "Files/Retro Gaming.ttf" ) ) );
            TEXT1.setFont( new Font( "Retro Gaming" , Font.BOLD , 12 ) );
        } catch ( IOException | FontFormatException ignored ) {
        }


        LABEL.setLayout( new FlowLayout( FlowLayout.CENTER ) );
        StyledDocument     style = TEXT1.getStyledDocument( );
        SimpleAttributeSet align = new SimpleAttributeSet( );
        StyleConstants.setAlignment( align , StyleConstants.ALIGN_CENTER );
        style.setParagraphAttributes( 0 , style.getLength( ) , align , false );
        TEXT1.setEditable( false );
        TEXT1.setBorder( null );
        TEXT1.setOpaque( false );
        TEXT1.setForeground( Color.BLACK );
        TEXT1.setSize( SYSTEM_WIDTH - SYSTEM_BORDER * 2 , SYSTEM_HEIGHT );
        LABEL.add( TEXT1 );
        INPUT.setEditable( false );

        LABEL.setIcon( new ImageIcon( createPlate( ) ) );
        SYSTEM.addMouseListener( mouseListener );


        SYSTEM.setResizable( false );
        SYSTEM.add( INPUT , BorderLayout.SOUTH );
        SYSTEM.add( LABEL );
        SYSTEM.pack( );
        SYSTEM.setVisible( true );
        SYSTEM.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );


    }

    public static BufferedImage createPlate( ) {
        BufferedImage plate = new BufferedImage( SYSTEM_WIDTH , SYSTEM_HEIGHT , BufferedImage.TYPE_INT_ARGB );
        for ( int i = 0 ; i < SYSTEM_HEIGHT ; i++ ) {
            for ( int j = 0 ; j < SYSTEM_WIDTH ; j++ ) {
                if ( i > SYSTEM_BORDER && i < SYSTEM_HEIGHT / 1.5 && j > SYSTEM_BORDER && j < SYSTEM_WIDTH - SYSTEM_BORDER ) {
                    plate.setRGB( j , i , SCREEN_COLOR.getRGB( ) );

                } else {
                    plate.setRGB( j , i , PLATE_COLOR.getRGB( ) );
                }
            }
        }
        return plate;
    }

    public static BufferedImage createPlate( Color[] plateColors ) {
        BufferedImage plate = new BufferedImage( SYSTEM_WIDTH , SYSTEM_HEIGHT , BufferedImage.TYPE_INT_ARGB );
        for ( int i = 0 ; i < SYSTEM_HEIGHT ; i++ ) {
            for ( int j = 0 ; j < SYSTEM_WIDTH ; j++ ) {
                if ( i > SYSTEM_BORDER && i < SYSTEM_HEIGHT / 1.5 && j > SYSTEM_BORDER && j < SYSTEM_WIDTH - SYSTEM_BORDER ) {
                    plate.setRGB( j , i , SCREEN_COLOR.getRGB( ) );
                } else {
                    plate.setRGB( j , i , plateColors[ i / ( SYSTEM_HEIGHT / plateColors.length+1 ) ].getRGB( ) );
                }
            }
        }
        return plate;
    }

    public String inputString( String str ) {
        sPrint( str );
        sPrint( "(Type in the text box then click)" );
        INPUT.setText( "" );
        INPUT.setEditable( true );
        INPUT.requestFocus( );
        keyButton = false;
        wait( 100 );
        while ( INPUT.getText( ).equals( "" ) || ! keyButton ) ;
        SYSTEM.requestFocusInWindow( );
        INPUT.setEditable( false );
        lastsPrint = "";
        keyButton  = false;
        return INPUT.getText( );
    }

    public int inputInt( String str ) {
        sPrint( str );
        sPrint( "(Type in the text box then click)" );
        INPUT.setText( "" );
        INPUT.setEditable( true );
        INPUT.requestFocus( );
        keyButton = false;
        wait( 100 );
        while ( INPUT.getText( ).equals( "" ) || ! keyButton ) ;
        SYSTEM.requestFocusInWindow( );
        INPUT.setEditable( false );
        lastsPrint = "";
        keyButton  = false;
        if ( strIsInt( INPUT.getText( ) ) ) {
            return Integer.parseInt( INPUT.getText( ) );
        }
        return 0;
    }

    public boolean inputBool( String str ) {
        sPrint( str );
        sPrint( "(Type in the text box then click)" );
        INPUT.setText( "" );
        INPUT.setEditable( true );
        INPUT.requestFocus( );
        keyButton = false;
        wait( 100 );
        while ( INPUT.getText( ).equals( "" ) || ! keyButton ) ;
        SYSTEM.requestFocusInWindow( );
        INPUT.setEditable( false );
        String input = INPUT.getText( ).toLowerCase( );
        lastsPrint = "";
        keyButton  = false;
        return input.equals( "yes" ) || input.equals( "y" );
    }

    public void sPrintln( String str ) {
        TEXT1.setText( "" );
        String text = "";
        str = textFormat( str );
        for ( int i = 0 ; i < str.length( ) ; i++ ) {
            text += str.charAt( i );
            setText1( text );
        }
        setText1( text + "\n>Click<" );
        SYSTEM.requestFocusInWindow( );
        keyButton = false;
        wait( 100 );
        while ( ! keyButton ) ;
        keyButton = false;
        TEXT1.setText( "" );
        lastsPrint = "";
    }

    public void sPrint( String str ) {
        StringBuilder text = new StringBuilder( );
        str = textFormat( str );
        for ( int i = 0 ; i < str.length( ) ; i++ ) {
            text.append( str.charAt( i ) );
            setText1( lastsPrint + text );
        }
        lastsPrint += str + "\n";
    }

    public void wait( int time ) {
        long startTime = System.currentTimeMillis( );
        while ( startTime + time > System.currentTimeMillis( ) ) ;
        System.gc( );
        SYSTEM.requestFocusInWindow( );
    }

    public void setText1( String str ) {
        TEXT1.setText( "\n\n\n\n\n" + str );
        wait( tSpeed * 5 );
    }


    public boolean strIsInt( String string ) {
        try {
            Integer.parseInt( string );
            return true;
        } catch ( NumberFormatException e ) {
            return false;
        }
    }

    public int random( int low , int high ) {
        int range = high - low + 1;
        return (int) ( Math.random( ) * range ) + low;
    }

    public boolean quickTime( String word , int millis ) {
        long startTime = System.currentTimeMillis( );
        INPUT.setText( "" );
        sPrint( "Type " + word );
        INPUT.setEditable( true );
        INPUT.requestFocus( );

        while ( ! INPUT.getText( ).equals( word ) && startTime + millis > System.currentTimeMillis( ) ) ;
        TEXT1.setText( "" );
        INPUT.setEditable( false );
        return INPUT.getText( ).equals( word );
    }

    public int quickTime( int millis , String word ) {
        long startTime = System.currentTimeMillis( );
        int  hits      = 0;
        sPrint( "Keeping Typing " + word + " until time is up" );
        INPUT.setText( "" );
        INPUT.setEditable( true );
        INPUT.requestFocus( );
        while ( startTime + millis > System.currentTimeMillis( ) ) {
            if ( INPUT.getText( ).equals( word ) ) {
                INPUT.setText( "" );
                hits++;
            }
        }
        INPUT.setEditable( false );
        return hits;
    }

    public String textFormat( String str ) {
        StringBuilder string  = new StringBuilder( );
        char[]        charArr = str.toCharArray( );
        int           reset   = 0;
        for ( char c : charArr ) {
            if ( ( c + "" ).equals( "\n" ) ) {
                reset = 0;
            } else if ( reset == MAX_CHAR ) {
                string.append( "\n" );
                reset = 0;
            }
            string.append( c );
            reset++;
        }
        if ( str.contains( "2069" ) ) {
            TEXT1.setForeground( Color.blue );
        } else if ( str.contains( "2077" ) ) {
            TEXT1.setForeground( CYAN );
        } else if ( str.contains( "*" ) ) {
            TEXT1.setForeground( GREEN );
        } else if ( str.contains( "???" ) ) {
            TEXT1.setForeground( PURPLE );
        } else if ( str.contains( "Bill Gates" ) || str.contains( "Elon musk" ) || str.contains( "Jeff bezos" ) || str.contains( "Mark Zuckerberg" ) || str.contains( "UNKNOWN_PERSON" ) || str.contains( "Gordy" ) ) {
            TEXT1.setForeground( Color.red );
        } else {
            TEXT1.setForeground( Color.black );
        }
        return string.toString( );
    }


}