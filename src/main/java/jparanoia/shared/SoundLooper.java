package jparanoia.shared;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;

class SoundLooper extends Thread {
    Clip currentClip;
    AudioInputStream audioStream;

    public SoundLooper( Clip paramClip, AudioInputStream paramAudioInputStream ) {
        /* 163 */
        this.currentClip = paramClip;
        /* 164 */
        this.audioStream = paramAudioInputStream;
    }

    public void run() {
        /* 174 */
        this.currentClip.loop( -1 );



        /* 178 */
        while ( this.currentClip.isRunning() && !SoundManager.stopLoop ) {
            try {
                /* 181 */
                sleep( 30L );
                /* 182 */
                if ( SoundManager.stopLoop == true ) {
                    /* 183 */
                    this.currentClip.stop();
                    /* 184 */
                    this.currentClip.flush();
                }
            } catch ( Exception localException ) {
                /* 188 */
                localException.printStackTrace();
            }
        }



        /* 193 */
        this.currentClip.setFramePosition( 0 );

        /* 195 */
        SoundManager.stopLoop( false );
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\shared\SoundLooper.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
