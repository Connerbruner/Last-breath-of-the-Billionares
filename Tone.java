import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Tone {
    public static float  SAMPLE_RATE = 8000f;
    public static double VOLUME      = 0.8;
    int hz;
    int millis = 100;

    public Tone( int HZ , int msecs ) {
        hz     = HZ;
        millis = msecs;
    }

    public Tone( int HZ ) {
        hz = HZ;
    }

    public Tone( ) {
        hz = Nbes.random( 0 , 1000 );
    }


    public void play( ) throws LineUnavailableException {

        byte[] buf = new byte[ (int) SAMPLE_RATE * millis / 1000 ];
        for ( int i = 0 ; i < buf.length ; i++ ) {
            double angle = i / ( SAMPLE_RATE / hz ) * 2.0 * Math.PI;
            buf[ i ] = (byte) ( Math.sin( angle ) * 127.0 * VOLUME );
        }
// shape the front and back 10ms of the wave form
        for ( int i = 0 ; i < SAMPLE_RATE / 100.0 && i < buf.length / 2 ; i++ ) {
            buf[ i ]                  = (byte) ( buf[ i ] * i / ( SAMPLE_RATE / 100.0 ) );
            buf[ buf.length - 1 - i ] = (byte) ( buf[ buf.length - 1 - i ] * i / ( SAMPLE_RATE / 100.0 ) );
        }

        AudioFormat    af  = new AudioFormat( SAMPLE_RATE , 8 , 1 , true , false );
        SourceDataLine sdl = AudioSystem.getSourceDataLine( af );
        sdl.open( af );
        sdl.start( );
        sdl.write( buf , 0 , buf.length );
        sdl.drain( );
        sdl.close( );
    }

    public static void playAll( Tone[] arr ) throws LineUnavailableException {
        for ( Tone tone : arr ) {
            tone.play( );
        }
    }

    public static void playAll( int count ) throws LineUnavailableException {
        for ( ; count > 0 ; count-- ) {
            new Tone( ).play( );
        }
    }


    public static void main( String[] args ) {

    }

}