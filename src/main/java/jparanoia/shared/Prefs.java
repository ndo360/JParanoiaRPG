package jparanoia.shared;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandles;
import java.util.StringTokenizer;

public class Prefs {
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
        parsePrefs( "jpConfig.ini" );
    }

    public void parsePrefs( String paramString ) {
        try {
            final Class cl = MethodHandles.lookup().lookupClass();
            BufferedReader localBufferedReader = new BufferedReader( new InputStreamReader( cl.getResourceAsStream( "/" +
                    paramString ) ) );
            String str1 = localBufferedReader.readLine();
            int i = 0;
            String str2 = "uninitialized";
            String str3 = "uninitialized";
            while ( str1 != null ) {
                if ( !str1.startsWith( "#" ) ) {
                    StringTokenizer localStringTokenizer = new StringTokenizer( str1, "=" );
                    while ( localStringTokenizer.hasMoreTokens() ) {
                        String str4 = "";
                        str2 = localStringTokenizer.nextToken();
                        str3 = localStringTokenizer.nextToken();
                        if ( str2.startsWith( "b" ) ) {
                            this.prefsArray[i] = new Boolean( str3.equals( "true" ) );
                            str4 = "BOOLEAN";
                        } else if ( str2.startsWith( "i" ) ) {
                            this.prefsArray[i] = new Integer( Integer.parseInt( str3 ) );
                            str4 = "INTEGER";
                        } else if ( str2.startsWith( "s" ) ) {
                            this.prefsArray[i] = str3;
                            str4 = "STRING";
                        }
                        this.prefStrings[i][0] = str2;
                        this.prefStrings[i][1] = str3;
                        i++;
                    }
                }
                str1 = localBufferedReader.readLine();
            }
        } catch ( IOException localIOException ) {
            System.out.println( "* Error reading jpConfig.ini" );
            localIOException.printStackTrace();
            System.exit( -1 );
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
