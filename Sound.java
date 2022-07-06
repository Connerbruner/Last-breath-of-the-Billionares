import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {
    Clip sound;
    AudioInputStream ais;
    public Sound(String url) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        File f = new File(url);
        sound = AudioSystem.getClip();
        ais = AudioSystem.getAudioInputStream(f);
        sound.open(ais);
        sound.start();
        sound.stop();
    }
    public void play() {
        sound.start();
    }
    public void stop()
    {
        sound.stop();
    }
    public void loopOn()
    {
        sound.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void loopCount(int count)
    {
        sound.loop(count);
    }
}
