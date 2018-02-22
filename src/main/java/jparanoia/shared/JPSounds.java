package jparanoia.shared;
import static java.lang.invoke.MethodHandles.lookup;

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
    int deathCounter = 0;
    ClassLoader loader = lookup().lookupClass().getClassLoader();

    public JPSounds() {
        System.out.println( "JPSoundPlayer started\n\n" );
        String[] arrayOfFile = new String[23];
        arrayOfFile[0] = "sounds/programLaunch.wav";
        arrayOfFile[1] = "sounds/connected.wav";
        arrayOfFile[2] = "sounds/disconnected.wav";
        arrayOfFile[3] = "sounds/loggedIn.wav";
        arrayOfFile[4] = "sounds/badLogin.wav";
        arrayOfFile[5] = "sounds/playerJoin.wav";
        arrayOfFile[6] = "sounds/playerLeave.wav";
        arrayOfFile[7] = "sounds/newText.wav";
        arrayOfFile[8] = "sounds/muted.wav";
        arrayOfFile[9] = "sounds/unmuted.wav";
        arrayOfFile[10] = "sounds/freeze.wav";
        arrayOfFile[11] = "sounds/unfreeze.wav";
        arrayOfFile[12] = "sounds/promoted.wav";
        arrayOfFile[13] = "sounds/demoted.wav";
        arrayOfFile[14] = "sounds/scream0.wav";
        arrayOfFile[15] = "sounds/scream1.wav";
        arrayOfFile[16] = "sounds/scream2.wav";
        arrayOfFile[17] = "sounds/scream3.wav";
        arrayOfFile[18] = "sounds/scream4.wav";
        arrayOfFile[19] = "sounds/newPrivateMessage.wav";
        arrayOfFile[20] = "sounds/charsheetUpdate.wav";
        arrayOfFile[21] = "sounds/combatAlert.wav";
        arrayOfFile[22] = "sounds/combatMusic.wav";
        this.manager = new SoundManager( arrayOfFile );
    }
//    private File getFile( final String filename ) {
//        return new File( Objects.requireNonNull( loader.getResource( filename ) ).getFile());
//    }

    public void play( int paramInt ) {
        if ( paramInt == 14 ) {
            if ( this.deathCounter > 4 ) {
                this.deathCounter = 0;
            }
            this.manager.play( 14 + this.deathCounter++ );
        } else {
            this.manager.play( paramInt );
        }
    }

    public void startCombatMusic() {
        this.manager.loopPlay( 22 );
    }

    public void stopCombatMusic() {
        SoundManager.stopLoop( true );
    }

    public void close() {
        this.manager.terminate();
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\shared\JPSounds.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
