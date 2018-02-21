package jparanoia.shared;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;

class SoundPlayer extends Thread {
    Clip currentClip;
    AudioInputStream audioStream;

    public SoundPlayer( Clip paramClip, AudioInputStream paramAudioInputStream ) {

        this.currentClip = paramClip;

        this.audioStream = paramAudioInputStream;
    }

    public void run() {

        this.currentClip.start();




        while ( this.currentClip.isRunning() ) {
            try {

                sleep( 99L );
            } catch ( Exception localException ) {

                localException.printStackTrace();
            }
        }

        this.currentClip.drain();

        this.currentClip.stop();
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\shared\SoundPlayer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
