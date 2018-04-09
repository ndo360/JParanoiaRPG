package jparanoia.shared;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.exit;
import java.lang.invoke.MethodHandles;
import java.util.StringTokenizer;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

public class Prefs {
    private final static Logger logger = getLogger( MethodHandles.lookup().lookupClass());

    public static final int PLAY_SOUNDS = 0;
    public static final int PLAY_STARTUP = 1;
    public static final int PLAY_JOIN_LEAVE = 2;
    public static final int PLAY_NEW_TEXT = 3;
    public static final int PLAY_NEW_OBSERVER_TEXT = 4;
    public static final int PLAY_MUTED_UNMUTED = 5;
    public static final int PLAY_FROZEN_UNFROZEN = 6;
    public static final int PLAY_PROMOTED_DEMOTED = 7;
    public static final int PLAY_LOGIN_BADLOGIN = 8;
    public static final int PLAY_CONNECTED_DISCONNECTED = 9;
    public static final int PLAY_COMBAT_ALERT = 10;
    public static final int PLAY_COMBAT_MUSIC = 11;
    public static final int PLAY_CHARSHEET = 12;
    public static final int PLAY_NEW_PM = 13;
    public static final int PLAY_DEATH = 14;
    public static final int FONT_SIZE = 15;
    public static final int FONT_FAMILY = 16;
    public static final int FONT_BOLD = 17;
    public static final int CLOBBER_AQUA = 18;
    public static final int PM_CHAT_NOTIFICATION = 19;
    public static final int KEEP_LOG = 20;
    public static final int HTML_LOG = 21;
    public static final int REAL_NAME = 22;
    public static final int MAX_NUM_CLONES = 23;
    public static final int SHOW_TIMESTAMPS = 24;
    public static final int COMPUTER_BIG_FONT = 25;
    public static final int COMPUTER_FONT_INCREASE = 26;
    public static final int COMPUTER_ALL_CAPS = 27;
    public static final int ALLOW_OBSERVERS = 28;
    public static final int HEAR_OBSERVERS = 29;
    public static final int ANNOUNCE_OBSERVERS = 30;
    public static final int REGISTER_GAME = 31;
    public static final int BEHIND_ROUTER = 32;
    public static final int QUICK_CHARSHEET = 33;
    public static final int PXP_GAME = 34;
    public static final int GM_NAME_NAG = 35;
    public static final int SINGLE_USE_SPOOF = 36;
    public static final int USE_ANNOUNCEMENT = 37;
    String[][] prefStrings = new String[40][2];
    Object[] prefsArray = new Object[40];

    public Prefs() {
        parsePrefs( "conf/jpConfig.ini" );
    }

    public void parsePrefs( String paramString ) {
//        logger.info( "location is {}", new File(".").getAbsolutePath() );
        try ( BufferedReader localBufferedReader = new BufferedReader( new FileReader( paramString ) ) ){
                String str1 = localBufferedReader.readLine();
                int i = 0;
                String key;
                String value;
                while ( str1 != null ) {
                    if ( !str1.startsWith( "#" ) ) {
                        StringTokenizer localStringTokenizer = new StringTokenizer( str1, "=" );
                        while ( localStringTokenizer.hasMoreTokens() ) {
                            String type = "";
                            key = localStringTokenizer.nextToken();
                            value = localStringTokenizer.nextToken();
                            if ( key.startsWith( "b" ) ) {
                                this.prefsArray[i] = value.equals( "true" );
                                type = "BOOLEAN";
                            } else if ( key.startsWith( "i" ) ) {
                                this.prefsArray[i] = Integer.parseInt( value );
                                type = "INTEGER";
                            } else if ( key.startsWith( "s" ) ) {
                                this.prefsArray[i] = value;
                                type = "STRING";
                            }
                            this.prefStrings[i][0] = key;
                            this.prefStrings[i][1] = value;
                            i++;
                        }
                    }
                    str1 = localBufferedReader.readLine();
                }
        } catch ( IOException localIOException ) {
            logger.info( "* Error reading jpConfig.ini" );
            localIOException.printStackTrace();
            exit( -1 );
        }
    }

    public Object getPref( int paramInt ) {
        return this.prefsArray[paramInt];
    }

    public void setPref( int paramInt, Object paramObject ) {
        this.prefsArray[paramInt] = paramObject;
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\shared\Prefs.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
