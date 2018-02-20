package jparanoia.shared;
import java.io.File;

public class JPSounds {
    public static final int STARTUP = 0;
    public static final int CONNECTED = 1;
    public static final int DISCONNECTED = 2;
    public static final int LOGGED_IN = 3;
    public static final int BAD_LOGIN = 4;
    public static final int PLAYER_JOIN = 5;
    public static final int PLAYER_LEAVE = 6;
    public static final int NEW_TEXT = 7;
    public static final int MUTED = 8;
    public static final int UNMUTED = 9;
    public static final int FREEZE = 10;
    public static final int UNFREEZE = 11;
    public static final int PROMOTED = 12;
    public static final int DEMOTED = 13;
    public static final int DEATH_ALERT = 14;
    public static final int SCREAM_0 = 14;
    public static final int SCREAM_1 = 15;
    public static final int SCREAM_2 = 16;
    public static final int SCREAM_3 = 17;
    public static final int SCREAM_4 = 18;
    public static final int NEW_PM_ALERT = 19;
    public static final int CHARSHEET_ALERT = 20;
    public static final int COMBAT_ALERT = 21;
    public static final int COMBAT_MUSIC = 22;
    SoundManager manager;
    /*  36 */ int deathCounter = 0;

    public JPSounds() {
        /*  44 */
        System.out.println( "JPSoundPlayer started\n\n" );

        /*  46 */
        File[] arrayOfFile = new File[23];

        /*  48 */
        arrayOfFile[0] = new File( "sounds/programLaunch.wav" );
        /*  49 */
        arrayOfFile[1] = new File( "sounds/connected.wav" );
        /*  50 */
        arrayOfFile[2] = new File( "sounds/disconnected.wav" );
        /*  51 */
        arrayOfFile[3] = new File( "sounds/loggedIn.wav" );
        /*  52 */
        arrayOfFile[4] = new File( "sounds/badLogin.wav" );
        /*  53 */
        arrayOfFile[5] = new File( "sounds/playerJoin.wav" );
        /*  54 */
        arrayOfFile[6] = new File( "sounds/playerLeave.wav" );
        /*  55 */
        arrayOfFile[7] = new File( "sounds/newText.wav" );
        /*  56 */
        arrayOfFile[8] = new File( "sounds/muted.wav" );
        /*  57 */
        arrayOfFile[9] = new File( "sounds/unmuted.wav" );
        /*  58 */
        arrayOfFile[10] = new File( "sounds/freeze.wav" );
        /*  59 */
        arrayOfFile[11] = new File( "sounds/unfreeze.wav" );
        /*  60 */
        arrayOfFile[12] = new File( "sounds/promoted.wav" );
        /*  61 */
        arrayOfFile[13] = new File( "sounds/demoted.wav" );
        /*  62 */
        arrayOfFile[14] = new File( "sounds/scream0.wav" );
        /*  63 */
        arrayOfFile[15] = new File( "sounds/scream1.wav" );
        /*  64 */
        arrayOfFile[16] = new File( "sounds/scream2.wav" );
        /*  65 */
        arrayOfFile[17] = new File( "sounds/scream3.wav" );
        /*  66 */
        arrayOfFile[18] = new File( "sounds/scream4.wav" );
        /*  67 */
        arrayOfFile[19] = new File( "sounds/newPrivateMessage.wav" );
        /*  68 */
        arrayOfFile[20] = new File( "sounds/charsheetUpdate.wav" );
        /*  69 */
        arrayOfFile[21] = new File( "sounds/combatAlert.wav" );
        /*  70 */
        arrayOfFile[22] = new File( "sounds/combatMusic.wav" );

        /*  72 */
        this.manager = new SoundManager( arrayOfFile );
    }

    public void play( int paramInt ) {
        /*  86 */
        if ( paramInt == 14 ) {
            /*  87 */
            if ( this.deathCounter > 4 )
                /*  88 */ {
                this.deathCounter = 0;
            }
            /*  89 */
            this.manager.play( 14 + this.deathCounter++ );
        } else {
            /*  92 */
            this.manager.play( paramInt );
        }
    }

    public void startCombatMusic() {
        /*  97 */
        this.manager.loopPlay( 22 );
    }

    public void stopCombatMusic() {
        /* 102 */
        SoundManager.stopLoop( true );
    }

    public void close() {
        /* 107 */
        this.manager.terminate();
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\shared\JPSounds.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
