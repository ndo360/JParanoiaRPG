package jparanoia.shared;
import java.awt.Color;
import java.net.URL;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.ToolTipManager;
import javax.swing.text.StyledDocument;

import static java.lang.invoke.MethodHandles.lookup;

public abstract class JParanoia {
    public static final String JPARANOIA_WEBSITE = "http://www.byronbarry.com/jparanoia/";
    /*  17 */   public static JFrame frame = new JFrame();
    /*  19 */   public static Prefs prefs = new Prefs();
    public static JPSounds soundPlayer;
    public static SoundMenu soundMenu;
    public static boolean soundIsOn;
    public static boolean combatMusicIsPlaying;
    /*  25 */   public static boolean logBroken = false;
    /*  26 */   public static boolean autoScroll = true;
    /*  27 */   public static boolean announceObservers = true;
    /*  29 */   public static int previousKey = 0;
    /*  30 */   public static int thisKey = 100;
    /*  32 */   public static String lastNameCompleted = "";
    /*  33 */   public static int lastCompletionPlayer = 99;
    /*  34 */   public static java.util.ArrayList sortedNames = new java.util.ArrayList( 8 );
    /*  36 */   public static Vector obsNames = new Vector( 40 );
    public static GameLogger logger;
    public static ErrorLogger errLog;
    public static JMenuItem aboutBoxMenuItem;
    public static java.util.StringTokenizer st;
    /*  48 */   public static Color textColor = Color.white;
    public static JTextPane displayArea;
    public static javax.swing.text.SimpleAttributeSet textAttributes;
    public static StyledDocument chatDocument;
    /*  54 */   public static ObserversFrame obsFrame = new ObserversFrame();
    /*  56 */   public static ToolTipManager ttm = ToolTipManager.sharedInstance();
    protected static URL aboutIconURL;
    protected static ImageIcon aboutIcon;
    protected static String appInfo;
    /*  60 */   static NoTabFocusManager ntfm = new NoTabFocusManager();

    public JParanoia() {
        /*  64 */
        if ( ( (Boolean) prefs.getPref( 0 ) ).booleanValue() ) {
            soundPlayer = new JPSounds();
            soundIsOn = true;
        }
        /*  65 */
        soundMenu = new SoundMenu( "Sounds", prefs, this );
        /*  66 */
        if ( soundIsOn && soundMenu.startupMenuItem.isSelected() ) {
            soundPlayer.play( 0 );
        }
        try {
            /*  68 */
            aboutIconURL = new URL( "http://www.byronbarry.com/jparanoia/aboutIcon.jpg" );
            /*  69 */
        } catch ( Exception localException ) {
            localException.printStackTrace();
        }
        /*  71 */
        aboutIcon = new ImageIcon( aboutIconURL );

        /*  73 */
        aboutBoxMenuItem = new JMenuItem( "About JParanoia..." );
        /*  74 */
        /*  97 */
        aboutBoxMenuItem.addActionListener(paramAnonymousActionEvent -> {
            /*  76 */
            String str = "\n\nCoded by Byron Barry\nIcons by Andy Fitzpatrick\n\nJParanoia is Freeware\n(Donations accepted.)\nPermission required to re-distribute\nConsult included LICENSE.TXT for details\n\nThe JParanoia website is\nhttp://www.byronbarry.com/jparanoia/\n\nSearch for JParanoia on http://dmoz.org/\nif the above URL is no longer in use.";















            /*  92 */
            new JOptionPane();
            JOptionPane.showMessageDialog( null, JParanoia.appInfo +
                    "\n© 2002-2004 Byron Barry" +
                    str, "About JParanoia", 1, JParanoia.aboutIcon );
        });
        /*  98 */
        javax.swing.FocusManager.setCurrentManager( ntfm );

        /* 100 */
        ttm.setDismissDelay( 100000000 );
    }

    static void toggleSoundEngine() {
        /* 118 */
        if ( soundPlayer != null ) {
            soundPlayer.close();
            soundPlayer = null;
            soundIsOn = false;
            combatMusicIsPlaying = false;
            /* 119 */
        } else {
            try {
                soundPlayer = new JPSounds();
                soundIsOn = true;
            } catch ( NoClassDefFoundError localNoClassDefFoundError ) {
                /* 122 */
                errLog = new ErrorLogger( "cmbt", localNoClassDefFoundError.toString() + " in JPServer.startCombat()" );
                /* 123 */
                localNoClassDefFoundError.printStackTrace( errLog.out );
                /* 124 */
                errLog.closeLog();
                /* 125 */
                errLog = null;
                /* 126 */
                errorMessage( "CombatFrame - class definition not found", "The CombatFrame class failed to load. The combat manager\nis not available. An error log has been created in your logs\ndirectory. Please notify the author. You will need to exit\nand relaunch the server to correct this problem." );
            }
        }
    }

    public static void errorMessage( String paramString1, String paramString2 ) {
        /* 198 */
        JOptionPane.showMessageDialog( frame, paramString2, paramString1, 0 );
    }

    public static void displayImage( String paramString ) {
        try {
            /* 139 */
            String str = paramString.substring( 0, paramString.indexOf( "http://" ) );

            /* 141 */
            URL localURL = new URL( paramString.substring( paramString.indexOf( "http://" ) ) );

            /* 143 */
            JTextPane localJTextPane = new JTextPane();
            /* 144 */
            localJTextPane.setEnabled( false );
            /* 145 */
            JFrame localJFrame = new JFrame();
            /* 146 */
            localJFrame.setResizable( false );
            /* 147 */
            localJFrame.setSize( 200, 200 );

            /* 149 */
            ImageIcon localImageIcon = new ImageIcon( localURL );
            /* 150 */
            localJTextPane.insertIcon( localImageIcon );


















            /* 169 */
            localJFrame.setSize( localImageIcon.getIconWidth() + 8 + 6, localImageIcon.getIconHeight() + 26 + 6 );
            /* 170 */
            java.awt.Container localContainer = localJFrame.getContentPane();
            /* 171 */
            localContainer.add( localJTextPane );

            /* 173 */
            localJFrame.setTitle( str );
            /* 174 */
            localJTextPane.setToolTipText( str );
            try {
                /* 177 */
                localJFrame.setIconImage( java.awt.Toolkit.getDefaultToolkit()
                        .getImage( lookup().lookupClass().getClassLoader().getResource( "graphics/jparanoiaIcon.jpg" ) ) );
                /* 178 */
            } catch ( Exception localException2 ) {
                System.out.println( "Error getting icon." );
                localException2.printStackTrace();
                System.exit( -1 );
            }
            /* 180 */
            localJFrame.setVisible( true );
        } catch ( Exception localException1 ) {
            /* 184 */
            errLog = new ErrorLogger( "img", localException1.toString() + " in JParanoia.displayImage()" );
            /* 185 */
            localException1.printStackTrace( errLog.out );
            /* 186 */
            errLog.closeLog();
            /* 187 */
            errLog = null;
            /* 188 */
            errorMessage( "Error displaying image", "There was an error displaying the image.\nAn error log has been created in the logs\ndirectory." );
        }
    }

    public static void warningMessage( String paramString1, String paramString2 ) {
        /* 203 */
        JOptionPane.showMessageDialog( frame, paramString2, paramString1, 2 );
    }

    public static void displayWrite( Color paramColor, String paramString ) {
        try {
            /* 215 */
            textAttributes.addAttribute( javax.swing.text.StyleConstants.CharacterConstants.Foreground, paramColor );

            /* 217 */
            chatDocument.insertString( chatDocument.getLength(), paramString, textAttributes );


            /* 220 */
            displayArea.setDocument( chatDocument );
            /* 221 */
            if ( autoScroll ) {
                displayArea.setCaretPosition( chatDocument.getLength() );
            }
        } catch ( javax.swing.text.BadLocationException localBadLocationException ) {
            /* 226 */
            System.err.println( "Unhandled exception. (Bad Location)" );
        }

        /* 229 */
        textAttributes.addAttribute( javax.swing.text.StyleConstants.CharacterConstants.Foreground, textColor );
    }

    public static void addObsName( String paramString ) {
        /* 235 */
        Vector localVector = new Vector( 1 );
        /* 236 */
        localVector.add( paramString );
        /* 237 */
        obsNames.add( localVector );
        /* 238 */
        obsFrame.jt.repaint();
    }

    public static void removeObsName( String paramString ) {
        /* 254 */
        for ( int i = 0; i < obsNames.size(); i++ ) {
            /* 256 */
            Vector localVector = (Vector) obsNames.elementAt( i );
            /* 257 */
            String str = (String) localVector.elementAt( 0 );

            /* 259 */
            if ( str.equals( paramString ) ) {
                /* 261 */
                obsNames.remove( i );
                /* 262 */
                obsFrame.jt.repaint();

                /* 264 */
                return;
            }
        }
    }

    public static void observerHasJoined( String paramString ) {
        /* 271 */
        System.out.println( "WRONG METHOD CALLED. (joined)" );
    }

    public static void observerHasLeft( String paramString ) {
        /* 276 */
        System.out.println( "WRONG METHOD CALLED. (disconnected)" );
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\shared\JParanoia.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
