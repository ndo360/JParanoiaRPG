package jparanoia.shared;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;

class SoundLooper extends Thread {
    Clip currentClip;
    AudioInputStream audioStream;

    public SoundLooper( Clip paramClip, AudioInputStream paramAudioInputStream ) {
        this.currentClip = paramClip;
        this.audioStream = paramAudioInputStream;
    }

    public void run() {
        this.currentClip.loop( -1 );
        while ( this.currentClip.isRunning() && !SoundManager.stopLoop ) {
            try {
                sleep( 30L );
                if ( SoundManager.stopLoop ) {
                    this.currentClip.stop();
                    this.currentClip.flush();
                }
            } catch ( Exception localException ) {
                localException.printStackTrace();
            }
        }
        this.currentClip.setFramePosition( 0 );
        SoundManager.stopLoop( false );
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\shared\SoundLooper.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
