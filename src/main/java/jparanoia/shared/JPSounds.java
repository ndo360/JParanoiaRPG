package jparanoia.shared;
import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import kuusisto.tinysound.Music;
import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

public class JPSounds {
    private final static Logger logger = getLogger( MethodHandles.lookup().lookupClass());

    public static final String STARTUP = "sounds/programLaunch.wav";
    public static final String CONNECTED = "sounds/connected.wav";
    public static final String DISCONNECTED = "sounds/disconnected.wav";
    public static final String LOGGED_IN = "sounds/loggedIn.wav";
    public static final String BAD_LOGIN = "sounds/badLogin.wav";
    public static final String PLAYER_JOIN = "sounds/playerJoin.wav";
    public static final String PLAYER_LEAVE = "sounds/playerLeave.wav";
    public static final String NEW_TEXT = "sounds/newText.wav";
    public static final String MUTED = "sounds/muted.wav";
    public static final String UNMUTED = "sounds/unmuted.wav";
    public static final String FREEZE = "sounds/freeze.wav";
    public static final String UNFREEZE = "sounds/unfreeze.wav";
    public static final String PROMOTED = "sounds/promoted.wav";
    public static final String DEMOTED = "sounds/demoted.wav";
    public static final String DEATH_ALERT = "sounds/scream0.wav";
    public static final String SCREAM_0 = "sounds/scream0.wav";
    public static final String SCREAM_1 = "sounds/scream1.wav";
    public static final String SCREAM_2 = "sounds/scream2.wav";
    public static final String SCREAM_3 = "sounds/scream3.wav";
    public static final String SCREAM_4 = "sounds/scream4.wav";
    public static final String NEW_PM_ALERT = "sounds/newPrivateMessage.wav";
    public static final String CHARSHEET_ALERT = "sounds/charsheetUpdate.wav";
    public static final String COMBAT_ALERT = "sounds/combatAlert.wav";

    public static final String COMBAT_MUSIC = "sounds/combatMusic.wav";

    private int deathCounter = 0;

    private final Map<String, Sound> soundMap = new HashMap<>();
    private final Map<String, Music> musicMap = new HashMap<>();

    public JPSounds() {
        logger.info( "JPSoundPlayer started\n\n" );
        TinySound.init();
        Stream.of(STARTUP,
                CONNECTED,DISCONNECTED,
                LOGGED_IN,BAD_LOGIN,
                PLAYER_JOIN,PLAYER_LEAVE,
                NEW_TEXT,
                MUTED,UNMUTED,
                FREEZE,UNFREEZE,
                PROMOTED,DEMOTED,
                DEATH_ALERT,SCREAM_0,SCREAM_1,SCREAM_2,SCREAM_3,SCREAM_4,
                NEW_PM_ALERT,CHARSHEET_ALERT,COMBAT_ALERT )
              .forEach( s -> soundMap.put( s, TinySound.loadSound( s ) ) );//?
        Stream.of( COMBAT_MUSIC ).forEach( s -> musicMap.put( s, TinySound.loadMusic( s ) ) );

    }

    public void play( String soundName ) {
        if ( soundName.equals( DEATH_ALERT ) ) {
            getNextScream().play();
        } else {
            soundMap.get( soundName ).play();
        }
    }

    private Sound getNextScream(){
        if ( deathCounter > 4 ) {
            deathCounter = 0;
        }
        return soundMap.get( "sounds/scream"+ deathCounter++ +".wav" );
    }

    public void startCombatMusic() {
        musicMap.get( COMBAT_MUSIC ).play( true );
    }

    public void stopCombatMusic() {
        musicMap.get( COMBAT_MUSIC ).stop();
    }

    public void close() {
        TinySound.shutdown();
    }
}
