package jparanoia.shared;
import java.io.File;
import java.io.FileNotFoundException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Mixer;

public class SoundManager {
    /*  27 */   static boolean stopLoop = false;
    String fileString;
    /*  18 */ int mixerToUse = 0;
    AudioFormat format;
    DataLine.Info info;
    AudioInputStream[] audioStreams;
    Clip[] clipList;
    SoundPlayer player;
    SoundLooper looper;

    public SoundManager( File[] paramArrayOfFile ) {
        /*  31 */
        if ( paramArrayOfFile.length > 32 ) {
            System.out.println( "WARNING: Preparing to attempt acquisition of more than 32 voices!" );
        }
        try {
            /*  35 */
            Mixer.Info[] arrayOfInfo = AudioSystem.getMixerInfo();

            /*  37 */
            int i = 0;
            /*  38 */
            System.out.println( arrayOfInfo[i] );
            /*  39 */
            Mixer localMixer = AudioSystem.getMixer( arrayOfInfo[i] );

            /*  41 */
            this.clipList = new Clip[paramArrayOfFile.length];
            /*  42 */
            this.audioStreams = new AudioInputStream[paramArrayOfFile.length];

            /*  44 */
            for ( int j = 0; j < paramArrayOfFile.length; j++ ) {

                /*  47 */
                this.fileString = paramArrayOfFile[j].toString();
                try {
                    /*  55 */
                    this.audioStreams[j] = AudioSystem.getAudioInputStream( paramArrayOfFile[j] );
                } catch ( FileNotFoundException localFileNotFoundException ) {
                    /*  58 */
                    JParanoia.errorMessage( "Sound not found", "JParanoia was unable to locate:\n" +
                            paramArrayOfFile[j].toString() +
                            "\n\n" +
                            "Sound files should not be renamed or moved.\n\n" +
                            "If you have downloaded a JParanoia zip archive\n" +
                            "that did not include a sounds directory, you will\n" +
                            "need to copy one over from a previous installation\n" +
                            "or acquire one from the JParanoia download page or\n" +
                            "a fellow player.\n\n" +
                            "Alternately, you can play without sounds\n" +
                            "by setting bPlaySounds to false in the\n" +
                            "jpConfig.ini file and prevent this error\n" +
                            "from appearing again.\n\n" +
                            "JParanoia will now terminate." );


















                    /*  77 */
                    System.exit( 0 );
                }

                /*  80 */
                this.format = this.audioStreams[j].getFormat();


                /*  83 */
                this.info = new DataLine.Info( Clip.class, this.format );
                /*  84 */
                this.clipList[j] = (Clip) localMixer.getLine( this.info );
                /*  85 */
                this.clipList[j].open( this.audioStreams[j] );
            }
            /*  87 */
            System.out.println( "SoundManager finished acquiring resources for audio playback." );
        } catch ( Exception localException ) {
            /*  90 */
            localException.printStackTrace();
        }
    }

    public static void stopLoop( boolean paramBoolean ) {
        /* 108 */
        stopLoop = paramBoolean;
    }

    public void play( int paramInt ) {
        /*  95 */
        this.player = new SoundPlayer( this.clipList[paramInt], this.audioStreams[paramInt] );
        /*  96 */
        this.player.start();
    }

    public void loopPlay( int paramInt ) {
        /* 101 */
        this.looper = new SoundLooper( this.clipList[paramInt], this.audioStreams[paramInt] );
        /* 102 */
        this.looper.start();
    }

    public void terminate() {
        /* 113 */
        for ( int i = 0; i < this.clipList.length; i++ )
            /* 114 */ {
            this.clipList[i].close();
        }
        /* 115 */
        System.out.println( "Sound engine terminated." );
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\shared\SoundManager.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
