package jparanoia.client;
import java.awt.Color;
import static java.awt.Color.black;
import static java.awt.Color.white;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.lang.System.getProperty;
import java.lang.invoke.MethodHandles;
import static java.lang.invoke.MethodHandles.lookup;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import jparanoia.shared.BrightColorArray;
import jparanoia.shared.GameLogger;
import jparanoia.shared.GameRegistrar;
import jparanoia.shared.JPVersionNumber;
import jparanoia.shared.JParanoia;
import jparanoia.shared.TitleClass;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;
import org.slf4j.profiler.Profiler;

public class JPClient extends JParanoia {
    private final static Logger logger = getLogger( MethodHandles.lookup().lookupClass());
    public static final JPVersionNumber VERSION_NUMBER = new JPVersionNumber( 1, 31, 1 );
    public static final String VERSION_NAME = VERSION_NUMBER.toString();
    public static final JPVersionNumber MIN_COMPATIBLE_VERSION_NUMBER = new JPVersionNumber( 1, 31, 0 );
    static Integer mainFontSize = 99;
    static int computerFontIncrease = 0;
    static int maxNumClones;
    static boolean observer = false;
    static boolean disconnectCalled = false;
    static boolean keepLog;
    static boolean htmlLog;
    static boolean hearObservers = false;
    static JPanel mainPanel;
    static JPanel inputLinePanel;
    static JMenuItem showAllPMWindowsMenuItem;
    static Random rand = new Random();
    static String addressToTry = "127.0.0.1";
    static TitleClass myTitle;
    static ChatListenerThread myListener;
    static PrintWriter out;
    static BufferedReader in;
    static Socket mySock = null;
    static JTextPane charsheetArea;
    static Container contentPane;
    static JPanel inputPanel;
    static JPanel infoPanel;
    static JPanel statusPanel;
    static JFrame charsheetFrame;
    static JOptionPane connectOPane;
    static JOptionPane errorPane;
    static JMenuBar menuBar;
    static JMenu connectionMenu;
    static JMenu fontMenu;
    static JMenu fontFamilyMenu;
    static JMenu fontSizeMenu;
    static JMenu optionsMenu;
    static JMenu pmWindowsMenu;
    static JMenu charsheetMenu;
    static JMenuItem connectMenuItem;
    static JMenuItem disconnectMenuItem;
    static JMenuItem showCharsheetMenuItem;
    static JMenuItem[] pmWindowMenuItem;
    static JRadioButtonMenuItem whiteOnBlackButton;
    static JRadioButtonMenuItem blackOnWhiteButton;
    static JRadioButtonMenuItem serifButton;
    static JRadioButtonMenuItem sansSerifButton;
    static JRadioButtonMenuItem monospacedButton;
    static JRadioButtonMenuItem size10Button;
    static JRadioButtonMenuItem size12Button;
    static JRadioButtonMenuItem size14Button;
    static JRadioButtonMenuItem size16Button;
    static JRadioButtonMenuItem size18Button;
    static JRadioButtonMenuItem size24Button;
    static JCheckBoxMenuItem autoScrollMenuItem;
    static JCheckBoxMenuItem fontBoldMenuItem;
    static JCheckBoxMenuItem chatNotifyNewPMMenuItem;
    static JScrollPane scrollPane;
    static JScrollPane charScrollPane;
    static JTable lipTable;
    static JTextField inputLine;
    static JLabel connectionStatusLabel;
    static JLabel messageStatusLabel;
    static ImageIcon notConnectedIcon;
    static ImageIcon connectedIcon;
    static ImageIcon mutedIcon;
    static ImageIcon incomingMsgIcon;
    static ImageIcon nullMsgIcon;
    static ImageIcon gmSendingMsgIcon;
    static ImageIcon frozenIcon;
    static ImageIcon combatIcon;
    static Dimension lipTablePreferredSize = new Dimension( 130, 160 );
    static CombatTurnFrame turnFrame;
    static SimpleAttributeSet charsheetAttributes;
    static SimpleAttributeSet systemTextAttributes = new SimpleAttributeSet();
    static Color[] brightColors;
    static Color[] darkColors;
    static Color newColor;
    static boolean fontIsBold = false;
    static boolean muted = false;
    static boolean frozen = false;
    static boolean inCombat = false;
    static String[] nonConnectedPlayers;
    static String tempPlayerID;
    static String MY_PLAYER_ID;
    static int MY_PLAYER_NUMBER;
    static String currentColorScheme = "";
    static String newColorScheme = "White on Black";
    static String styleBegin;
    static String styleEnd = "";
    static String realName = "default";
    static boolean stayConnected = false;
    static boolean connected = false;
    static boolean loggedIn = false;
    static boolean firstCharsheetUpdate = true;
    static boolean clobberAqua;
    static Object[][] lipArray = new Object[40][1];
    static Object[] columnNameArray = {"Players"};
    static Vector onlinePlayers;
    static Vector lipRowVector = new Vector();
    static Vector columnNameVector = new Vector();
    static int numberOfPlayers;
    static int numberOfPCs;
    static ClientPlayer[] playerList;
    static ClientPlayer myPlayer;
    static PrivateMessageFrame[] PMFrame;

    public JPClient() {
        Profiler profiler = new Profiler("JPClient");

        clobberAqua = (Boolean) prefs.getPref( 18 );
        if ( clobberAqua ) {
            try {
                UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
            } catch ( Exception localException ) {
                logger.info( "Exception while setting L&F." );
            }
        }

        profiler.start( "frame init" );
        JParanoia.appInfo = "JParanoia Client " + VERSION_NAME;
        frame.setTitle( "JParanoia Client " + VERSION_NAME );
        myTitle = new TitleClass( "JParanoia Client", VERSION_NAME, true );
        frame.setIconImage( Toolkit.getDefaultToolkit()
                .getImage( lookup().lookupClass().getClassLoader().getResource( "graphics/jparanoiaIcon.jpg" ) ) );
        frame.addWindowListener( new WindowAdapter() {
            public void windowClosing( WindowEvent paramAnonymousWindowEvent ) {
                JPClient.exit();
            }
        } );
        frame.setSize( 700, 500 );
        displayArea = new JTextPane();
        displayArea.setEditable( false );
        displayArea.setEnabled( true );
        displayArea.setDisabledTextColor( Color.white );
        displayArea.setBackground( Color.black );
        scrollPane = new JScrollPane( displayArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
        chatDocument = new DefaultStyledDocument();
        brightColors = new BrightColorArray().getColors();
        darkColors = new Color[10];
        for ( int i = 0; i < 10; i++ ) {
            switch ( i ) {
                case 0:
                    newColor = new Color( 0.1F, 0.1F, 0.1F );
                    break;
                case 1:
                    newColor = new Color( 0.6F, 0.3F, 0.0F );
                    break;
                case 2:
                    newColor = new Color( 0.0F, 0.4F, 0.0F );
                    break;
                case 3:
                    newColor = new Color( 0.2F, 0.6F, 0.4F );
                    break;
                case 4:
                    newColor = new Color( 0.3F, 0.1F, 0.8F );
                    break;
                case 5:
                    newColor = new Color( 0.5F, 0.1F, 0.5F );
                    break;
                case 6:
                    newColor = new Color( 0.3F, 0.3F, 0.3F );
                    break;
                case 7:
                    newColor = new Color( 0.0F, 0.0F, 0.4F );
                    break;
                case 8:
                    newColor = new Color( 0.4F, 0.0F, 0.0F );
                    break;
                case 9:
                    newColor = new Color( 0.1F, 0.4F, 0.4F );
                    break;
                default:
                    logger.info( "Error: Out of colors!" );
            }
            darkColors[i] = newColor;
        }
        textAttributes = new SimpleAttributeSet();
        textAttributes.addAttribute( StyleConstants.FontConstants.Size, prefs.getPref( 15 ) );
        textAttributes.addAttribute( StyleConstants.FontConstants.Family, prefs.getPref( 16 ) );
        setColorScheme();
        inputLine = new JTextField( 35 );
        inputLine.setEnabled( false );
        inputLine.addActionListener( paramAnonymousActionEvent -> {
            if ( !JPClient.muted && !JPClient.frozen ) {
                JPClient.sendChat( JPClient.inputLine.getText() );
            }
        } );
        inputLine.addKeyListener( new KeyListener() {
            public void keyTyped( KeyEvent paramAnonymousKeyEvent ) {}

            public void keyPressed( KeyEvent paramAnonymousKeyEvent ) {
                JParanoia.previousKey = JParanoia.thisKey;
                JParanoia.thisKey = paramAnonymousKeyEvent.getKeyCode();
                if ( JParanoia.thisKey == 9 ) {
                    paramAnonymousKeyEvent.consume();
                    if ( JPClient.inputLine.getText().length() > 0 ) {
                        String str = JPClient.inputLine.getText();
                        if ( str.startsWith( "'" ) && str.length() > 1 ) {
                            JPClient.inputLine.setText( "'" +
                                    JPClient.nameCompletion( str.substring( 1 ), JParanoia.thisKey ==
                                            JParanoia.previousKey ) );
                        } else if ( !str.startsWith( "'" ) ) {
                            JPClient.inputLine.setText( JPClient.nameCompletion( str, JParanoia.thisKey ==
                                    JParanoia.previousKey ) );
                        }
                    }
                }
            }

            public void keyReleased( KeyEvent paramAnonymousKeyEvent ) {}
        } );
        inputPanel = new JPanel();
        inputPanel.add( inputLine, "Center" );
        menuBar = new JMenuBar();
        frame.setJMenuBar( menuBar );
        connectionMenu = new JMenu( "Connection" );
        connectMenuItem = new JMenuItem( "Connect..." );
        connectMenuItem.addActionListener( paramAnonymousActionEvent -> { ConnectManager.activate(); } );
        disconnectMenuItem = new JMenuItem( "Disconnect" );
        disconnectMenuItem.addActionListener( paramAnonymousActionEvent -> JPClient.disconnect( false ) );
        connectionMenu.add( connectMenuItem );
        connectionMenu.add( disconnectMenuItem );
        disconnectMenuItem.setEnabled( false );
        menuBar.add( connectionMenu );
        fontMenu = new FontMenu( "Font" );
        menuBar.add( fontMenu );
        optionsMenu = new JMenu( "Options" );
        autoScrollMenuItem = new JCheckBoxMenuItem( "Autoscroll" );
        autoScrollMenuItem.setSelected( true );
        autoScrollMenuItem.addActionListener( paramAnonymousActionEvent -> {
            JParanoia.autoScroll = !JParanoia.autoScroll;
        } );
        chatNotifyNewPMMenuItem = new JCheckBoxMenuItem( "New PM alert in chat" );
        chatNotifyNewPMMenuItem.setSelected( (Boolean) prefs.getPref( 19 ) );
        optionsMenu.add( soundMenu );
        optionsMenu.addSeparator();
        optionsMenu.add( autoScrollMenuItem );
        optionsMenu.add( chatNotifyNewPMMenuItem );
        optionsMenu.addSeparator();
        optionsMenu.add( aboutBoxMenuItem );
        menuBar.add( optionsMenu );
        pmWindowsMenu = new JMenu( "PM windows" );
        showAllPMWindowsMenuItem = new JMenuItem( "Show all" );
        showAllPMWindowsMenuItem.addActionListener( paramAnonymousActionEvent -> {
            for ( int i = 0; i < JPClient.numberOfPCs; i++ ) {
                if ( i != JPClient.MY_PLAYER_NUMBER ) {
                    JPClient.PMFrame[i].setVisible( true );
                }
            }
        } );
        showAllPMWindowsMenuItem.setEnabled( false );
        pmWindowsMenu.add( showAllPMWindowsMenuItem );
        pmWindowsMenu.addSeparator();
        menuBar.add( pmWindowsMenu );
        infoPanel = new JPanel();
        infoPanel.setLayout( new BoxLayout( infoPanel, BoxLayout.Y_AXIS ) );
        lipArray[0][0] = "Player List";
        lipTable = new JTable( lipArray, columnNameArray );
        lipTable.setMinimumSize( lipTablePreferredSize );
        lipTable.setPreferredSize( lipTablePreferredSize );
        lipTable.setShowGrid( false );
        lipTable.setEnabled( false );
        statusPanel = new JPanel();
        statusPanel.setLayout( new BoxLayout( statusPanel, BoxLayout.X_AXIS ) );
        notConnectedIcon = new ImageIcon( lookup().lookupClass()
                .getClassLoader()
                .getResource( "graphics/notConnectedIcon.jpg" ) );
        connectedIcon = new ImageIcon( lookup().lookupClass()
                .getClassLoader()
                .getResource( "graphics/connectedIcon.jpg" ) );
        mutedIcon = new ImageIcon( lookup().lookupClass()
                .getClassLoader()
                .getResource( "graphics/mutedIcon.jpg" ) );
        frozenIcon = new ImageIcon( lookup().lookupClass()
                .getClassLoader()
                .getResource( "graphics/frozenIcon.jpg" ) );
        nullMsgIcon = new ImageIcon( lookup().lookupClass()
                .getClassLoader()
                .getResource( "graphics/nullMsgIcon.jpg" ) );
        incomingMsgIcon = new ImageIcon( lookup().lookupClass()
                .getClassLoader()
                .getResource( "graphics/incomingMsgIcon.jpg" ) );
        gmSendingMsgIcon = new ImageIcon( lookup().lookupClass()
                .getClassLoader()
                .getResource( "graphics/gmSendingMsgIcon.jpg" ) );
        combatIcon = new ImageIcon( lookup().lookupClass()
                .getClassLoader()
                .getResource( "graphics/combatIcon.jpg" ) );
        connectionStatusLabel = new JLabel( notConnectedIcon );
        messageStatusLabel = new JLabel( nullMsgIcon );
        statusPanel.add( connectionStatusLabel );
        statusPanel.add( Box.createRigidArea( new Dimension( 15, 0 ) ) );
        statusPanel.add( messageStatusLabel );
        statusPanel.setMinimumSize( new Dimension( 100, 30 ) );
        statusPanel.setPreferredSize( new Dimension( 100, 30 ) );
        statusPanel.setMaximumSize( new Dimension( 100, 30 ) );
        infoPanel.add( lipTable );
        infoPanel.add( Box.createRigidArea( new Dimension( 0, 5 ) ) );
        infoPanel.add( statusPanel );
        inputLine.setMinimumSize( new Dimension( 100, 22 ) );
        inputLine.setMaximumSize( new Dimension( 999, 22 ) );
        inputLine.setPreferredSize( new Dimension( 997, 22 ) );
        inputLinePanel = new JPanel();
        inputLinePanel.setLayout( new BoxLayout( inputLinePanel, BoxLayout.X_AXIS ) );
        inputLinePanel.add( Box.createRigidArea( new Dimension( 5, 0 ) ) );
        inputLinePanel.add( inputLine );
        inputLinePanel.add( Box.createRigidArea( new Dimension( 5, 0 ) ) );
        mainPanel = new JPanel();
        mainPanel.setLayout( new BoxLayout( mainPanel, BoxLayout.Y_AXIS ) );
        mainPanel.add( scrollPane );
        mainPanel.add( Box.createRigidArea( new Dimension( 0, 5 ) ) );
        mainPanel.add( inputLinePanel );
        mainPanel.add( Box.createRigidArea( new Dimension( 0, 5 ) ) );
        Container localContainer = frame.getContentPane();
        localContainer.add( mainPanel, "Center" );
        localContainer.add( infoPanel, "East" );
        connectOPane = new JOptionPane();
        errorPane = new JOptionPane();
        textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, Color.white );
        systemTextAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, Color.gray );
        charsheetFrame = new JFrame( "Character Sheet" );
        charsheetFrame.setSize( 500, 300 );
        charsheetFrame.setIconImage( Toolkit.getDefaultToolkit()
                .getImage( lookup().lookupClass().getClassLoader().getResource( "graphics/jparanoiaIcon.jpg" ) ) );
        charsheetArea = new JTextPane();
        charsheetArea.setEditable( false );
        charsheetArea.setEnabled( true );
        charScrollPane = new JScrollPane( charsheetArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
        charsheetFrame.getContentPane().add( charScrollPane );
        charsheetAttributes = new SimpleAttributeSet();
        charsheetAttributes.addAttribute( StyleConstants.Bold, Boolean.TRUE );
        charsheetAttributes.addAttribute( StyleConstants.CharacterConstants.Family, "SansSerif" );
        charsheetAttributes.addAttribute( StyleConstants.CharacterConstants.Size, 12 );
        charsheetMenu = new JMenu( "Character Sheet" );
        showCharsheetMenuItem = new JMenuItem( "Show character sheet" );
        showCharsheetMenuItem.addActionListener( paramAnonymousActionEvent -> {
            if ( !JPClient.charsheetFrame.isVisible() ) {
                JPClient.charsheetFrame.setVisible( true );
            }
        } );
        charsheetMenu.add( showCharsheetMenuItem );
        menuBar.add( charsheetMenu );
        displayWrite( Color.green, "JParanoia Client " + VERSION_NAME + "\n\n" );
        displayWrite( Color.cyan, "New in this release:\n\n" );
        displayWrite( Color.white, "- The Computer's text is now large in the log. GM text is bold.\n- Images are now logged.\n- Blank secret combat turns no longer appear in GM PM window.\n- Other miscellaneous bug fixes.\n\n" );
        displayWrite( Color.yellow, "Read the README.TXT" );
        displayWrite( Color.white, " for full details.\n\n" );
        displayWrite( Color.cyan, "Name Completion:\n" );
        displayWrite( Color.white, "Tab acts as a name completion key. See README for details.\n\n" );
        displayWrite( Color.cyan, "Expression Keys:\n" );
        displayWrite( Color.white, "Single quote ( ' ) = speech     " );
        displayWrite( Color.red, "Nory-R-BEE" );
        displayWrite( Color.white, " says, \"Welcome to JParanoia.\"\nBackslash ( \\ ) = thought bubble     " );
        displayWrite( Color.red, "Nory-R-BEE . o O ( need... food... )\n" );
        displayWrite( Color.white, "Slash ( / ) = action     " );
        displayWrite( Color.red, "* Nory-R-BEE points to the Donate link and sneers menacingly. *\n\n" );
        displayWrite( Color.cyan, "Passwords:\n" );
        displayWrite( Color.white, "If you find you can't login because the GM didn't inform you of a password, try " );
        displayWrite( Color.yellow, "asdf" );
        displayWrite( Color.white, ". This is the default password for all players. Good GMs assign passwords for enhanced security.\n\n" );
        displayWrite( Color.white, "For news, bug report forms, a complete version history, or to make a financial contribution, visit the " );
        displayWrite( Color.cyan, "JParanoia website: " );
        displayWrite( Color.orange, "http://www.byronbarry.com/jparanoia/\n" );
        keepLog = (Boolean) prefs.getPref( 20 );
        htmlLog = (Boolean) prefs.getPref( 21 );
        realName = (String) prefs.getPref( 22 );
//        net.roydesign.mac.MRJAdapter.addQuitApplicationListener(paramAnonymousActionEvent -> {
//        });
//        net.roydesign.mac.MRJAdapter.addAboutListener(paramAnonymousActionEvent -> {
//
//            jparanoia.shared.JParanoia.aboutBoxMenuItem.doClick();
//        });
        mainFontSize = (Integer) textAttributes.getAttribute( StyleConstants.FontConstants.Size );

        profiler.stop().print();
    }

    public static void setColorScheme() {
        if ( !currentColorScheme.equals( newColorScheme ) ) {
            switch ( newColorScheme ) {
                case "White on Black":
                    textColor = white;
                    displayArea.setBackground( black );
                    break;
                case "Black on White":
                    textColor = black;
                    displayArea.setBackground( white );
                    break;
                default:
                    logger.info( "Error: invalid color logic." );
                    break;
            }
            currentColorScheme = newColorScheme;
            assignColorsToCharacters();
        }
    }

    public static void assignColorsToCharacters() {
        if ( loggedIn ) {
            if ( currentColorScheme.equals( "White on Black" ) ) {
                for ( int i = 0; i < numberOfPlayers; i++ ) {
                    playerList[i].setChatColor( brightColors[i] );
                }
            }
            logger.info( "Error: invalid color logic" );
        }
    }

    public static void disconnect( boolean paramBoolean ) {
        System.out.print( "disconnect() initiated by " );
        if ( paramBoolean ) {
            logger.info( "server" );
        } else {
            logger.info( "client" );
        }
        disconnectCalled = true;
        if ( stayConnected ) {
            int i;
            if ( loggedIn && !observer ) {
                if ( !paramBoolean ) {
                    out.println( "086" + MY_PLAYER_ID );
                }
                for ( i = 0; i < numberOfPCs; ) {
                    if ( i != MY_PLAYER_NUMBER ) {
                        PMFrame[i].dispose();
                    }
                    i++;
                    if ( !paramBoolean ) {
                        out.println( "086" );
                    }
                }
            }
            drop();
            if ( keepLog ) {
                log.closeLog();
            }
            loggedIn = false;
            stayConnected = false;
            connectMenuItem.setEnabled( true );
            disconnectMenuItem.setEnabled( false );
            inputLine.setText( "" );
            inputLine.setEnabled( false );
            connectionStatusLabel.setIcon( notConnectedIcon );
            muted = false;
            frozen = false;
            pmWindowsMenu.removeAll();
            pmWindowsMenu.add( showAllPMWindowsMenuItem );
            pmWindowsMenu.addSeparator();
            showAllPMWindowsMenuItem.setEnabled( false );
            numberOfPCs = 0;
            firstCharsheetUpdate = true;
            if ( soundIsOn && soundMenu.connectedDisconnectedMenuItem.isSelected() ) {
                soundPlayer.play( 2 );
            }
            if ( combatMusicIsPlaying ) {
                soundPlayer.stopCombatMusic();
            }
        } else {
            logger.info( "Already disconnected." );
        }
    }

    public static void drop() {
        try {
            in.close();
            out.close();
            mySock.close();
        } catch ( IOException localIOException ) {
            System.err.println( "Error: unable to close outgoing streams/writers/sockets" );
            localIOException.printStackTrace();
        }
        connected = false;
        stayConnected = false;
        connectMenuItem.setEnabled( true );
        disconnectMenuItem.setEnabled( false );
        inputLine.setText( "" );
        inputLine.setEnabled( false );
    }

    public static void sendChat( String paramString ) {
        if ( stayConnected ) {
            String str1 = paramString;
            if ( str1.length() > 1000 ) {
                absoluteChat( "Can't paste that many characters." );
                inputLine.setText( "" );
                return;
            }
            str1 = str1.replace( '\n', ' ' );
            if ( !observer ) {
                if ( str1.startsWith( "`" ) ) {
                    sendPrivateMessage( "00" + MY_PLAYER_ID + str1.substring( 1 ) );
                    PMFrame[0].addMyMessage( str1.substring( 1 ) );
                    inputLine.setText( "" );
                    return;
                }
                st = new StringTokenizer( str1, "`" );
                if ( st.hasMoreTokens() ) {
                    str1 = st.nextToken();
                }
                if ( st.hasMoreTokens() ) {
                    String str3 = st.nextToken();
                    sendPrivateMessage( "00" + MY_PLAYER_ID + str3 );
                    PMFrame[0].addMyMessage( str3 );
                }
            }
            if ( !str1.equals( "" ) ) {
                String str2;
                if ( loggedIn ) {
                    if ( observer ) {
                        str2 = "099";
                        out.println( str2 + realName + "@~!~~" + str1 );
                    } else if ( str1.startsWith( "/" ) ) {
                        str2 = "110";
                        out.println( str2 + MY_PLAYER_ID + str1.substring( 1 ) );
                    } else if ( str1.startsWith( "'" ) ) {
                        str2 = "120";
                        out.println( str2 + MY_PLAYER_ID + str1.substring( 1 ) );
                    } else if ( str1.startsWith( "\\" ) ) {
                        str2 = "130";
                        out.println( str2 + MY_PLAYER_ID + str1.substring( 1 ) );
                    } else {
                        str2 = "100";
                        out.println( str2 + MY_PLAYER_ID + str1 );
                    }
                } else {
                    str2 = "901";
                    out.println( str2 + str1 );
                }
                inputLine.setText( "" );
            }
        }
    }

    public static void sendPrivateMessage( String paramString ) {
        String str2 = "200";
        if ( loggedIn && stayConnected ) {
            out.println( str2 + paramString );
        }
    }

    public static void absoluteChat( String paramString ) {
        SimpleAttributeSet localSimpleAttributeSet;
        if ( loggedIn ) {
            localSimpleAttributeSet = systemTextAttributes;
        } else {
            localSimpleAttributeSet = textAttributes;
        }
        try {
            chatDocument.insertString( chatDocument.getLength(), paramString + "\n", localSimpleAttributeSet );
            displayArea.setDocument( chatDocument );
            if ( autoScroll ) {
                displayArea.setCaretPosition( chatDocument.getLength() );
            }
            if ( log != null && loggedIn && keepLog ) {
                String str;
                if ( htmlLog ) {
                    str = "<span class=\"gray\">" + paramString + "</span><br/>";
                } else {
                    str = paramString;
                }
                log.logEntry( str );
            }
        } catch ( BadLocationException localBadLocationException ) {
            System.err.println( "Unhandled exception. (Bad Location)" );
        }
    }

    public static String nameCompletion( String paramString, boolean paramBoolean ) {
        String str1 = "";
        StringBuilder str2 = new StringBuilder();
        if ( paramBoolean ) {
            paramString = paramString.substring( 0, paramString.length() - lastNameCompleted.length() + 1 );
        }
        st = new StringTokenizer( paramString );
        str1 = st.nextToken();
        while ( st.hasMoreTokens() ) {
            str2.append( str1 ).append( " " );
            str1 = st.nextToken();
        }
        if ( paramBoolean ) {
            if ( lastCompletionPlayer == sortedNames.size() - 1 ) {
                lastCompletionPlayer = 0;
                lastNameCompleted = ( (ClientPlayer) sortedNames.get( lastCompletionPlayer ) ).getName();
                return str2 + lastNameCompleted;
            }
            lastNameCompleted = ( (ClientPlayer) sortedNames.get( ++lastCompletionPlayer ) ).getName();
            return str2 + lastNameCompleted;
        }
        for ( int i = 0;
              i < sortedNames.size() &&
                      str1.compareToIgnoreCase( ( (ClientPlayer) sortedNames.get( i ) ).getName() ) > 0;
              i++ ) {
            if ( i < sortedNames.size() ) {
                lastCompletionPlayer = i;
            } else {
                lastCompletionPlayer = sortedNames.size() - 1;
            }
            lastNameCompleted = ( (ClientPlayer) sortedNames.get( lastCompletionPlayer ) ).getName();
        }
        return str2 + lastNameCompleted;
    }

    public static void main( String[] paramArrayOfString ) {
        logger.info( "\n\nThis is the JParanoia client console.\n" );
        logger.info( "Running under Java Runtime Environment version " + getProperty( "java.version" ) );
        JPClient localJPClient = new JPClient();
        frame.setLocation( new Point( 20, 20 ) );
        frame.setVisible( true );
    }

    public static void connect( String paramString1, boolean paramBoolean, String paramString2 ) {
        if ( paramString1 == null ) {
            return;
        }
        if ( paramString1.equals( "" ) ) {
            return;
        }
        for ( ;
              realName.trim().equals( "default" ) /*|| realName == null*/ || realName.trim().equals( "" );
              realName = (String) JOptionPane.showInputDialog( null, "You have not provided your \"real\" name in the\njpConfig.ini file. The sooner you do, the sooner\nthis message will stop appearing. Your \"real\" name\nis announced when you join and logged for posterity.\nMost likely the best choice rather than your actual\nname would be the name you are known by in your RPG\ncircles or your Internet persona. For example, your\nuser name on Paranoia-Live.net would be an excellent\nname to provide here.\n\n\"Real\" name:", "\"Real\" name...", JOptionPane.PLAIN_MESSAGE, null, null, realName ) ) {
            logger.info( "realName == \"" + realName + "\"" );
            new JOptionPane();
        }
        connectMenuItem.setEnabled( false );
        disconnectMenuItem.setEnabled( true );
        disconnectCalled = false;
        String str = "hey";
        try {
            mySock = new Socket( paramString1, 11777 );
            logger.info( "Connected to server: " + paramString2 );
            System.out.println();
            if ( soundIsOn && soundMenu.connectedDisconnectedMenuItem.isSelected() ) {
                soundPlayer.play( 1 );
            }
            connected = true;
            stayConnected = true;
            out = new PrintWriter( mySock.getOutputStream(), true );
            in = new BufferedReader( new InputStreamReader( mySock.getInputStream() ) );
            myListener = new ChatListenerThread();
            myListener.setDaemon( true );
            myListener.start();
        } catch ( UnknownHostException localUnknownHostException ) {
            JOptionPane.showMessageDialog( null, paramString2 + "\nUnknown host.", "Uknown host", JOptionPane.ERROR_MESSAGE );
            connectMenuItem.setEnabled( true );
            disconnectMenuItem.setEnabled( false );
        } catch ( ConnectException localConnectException ) {
            JOptionPane.showMessageDialog( null, paramString2 +
                    "\nConnection at this host failed.\n" +
                    "Host may not be running a server.", "Connect failed", JOptionPane.ERROR_MESSAGE );
            if ( paramBoolean ) {
                GameRegistrar.deleteUnreachableGame( paramString1 );
            }
            connectMenuItem.setEnabled( true );
            disconnectMenuItem.setEnabled( false );
        } catch ( IOException localIOException ) {
            logger.info( "ERROR: Unhandled IO Exception..." );
            localIOException.printStackTrace();
        }
    }

    public static void checkVersion( String paramString ) {
        if ( new JPVersionNumber( paramString ).compareTo( MIN_COMPATIBLE_VERSION_NUMBER ) < 0 ) {
            out.println( "961Someone using client version " +
                    VERSION_NUMBER.toString() +
                    " has attempted (and failed) to connect." +
                    " You must upgrade to server version " +
                    MIN_COMPATIBLE_VERSION_NUMBER.toString() +
                    " to support this client." +
                    " The current version of JParanoia may be found at " +
                    "http://www.byronbarry.com/jparanoia/" );
            disconnect( false );
            absoluteChat( "Connection failure: Server version is too old to work properly with your client." );
            absoluteChat( "Alert GM to locate a current version of JParanoia." );
            absoluteChat( "The JParanoia website is:" );
            absoluteChat( "http://www.byronbarry.com/jparanoia/" );
        } else {
            out.println( "960" );
        }
    }

    public static void respond( String paramString ) {
        Random localRandom = new Random();
        int i = Integer.parseInt( paramString.substring( 2 ) );
        i *= localRandom.nextInt( 845 ) + 154;
        out.println( i );
    }

    public static void initializePlayerList( String paramString ) {
        numberOfPlayers = Integer.parseInt( paramString );
        playerList = new ClientPlayer[numberOfPlayers];
        out.println( "numberOfPlayers received" );
    }

    public static void loginError() {
        if ( soundIsOn && soundMenu.loginBadLoginMenuItem.isSelected() ) {
            soundPlayer.play( 4 );
        }
    }

    public static void receivePlayerUpdate( String paramString ) {
        boolean bool1 = false;
        boolean bool2 = true;
        Color localColor = null;
        int i = Integer.parseInt( paramString.substring( 0, 2 ) );
        if ( paramString.substring( 2, 3 ).equals( "p" ) ) {
            bool1 = true;
        }
        if ( paramString.substring( 3, 4 ).equals( "n" ) ) {
            bool2 = false;
        }
        String str = paramString.substring( 4 );
        if ( playerList[i] != null ) {
            localColor = playerList[i].getChatColor();
        }
        sortedNames.remove( playerList[i] );
        playerList[i] = new ClientPlayer( i, str, bool1, bool2 );
        if ( playerList[i].isAPlayer() ) {
            sortedNames.add( playerList[i] );
        }
        sortNames();
        if ( localColor != null ) {
            playerList[i].setChatColor( localColor );
        }
        updateLipArray();
        if ( i == MY_PLAYER_NUMBER && loggedIn && !observer ) {
            myPlayer = playerList[MY_PLAYER_NUMBER];
            myTitle.setPlayerName( myPlayer.toString() );
            frame.setTitle( myTitle.get() );
        } else if ( i < numberOfPCs && loggedIn && !observer ) {
            playerList[i].setPMFrame( PMFrame[i] );
            PMFrame[i].setTitle( playerList[i].getName() );
            pmWindowsMenu.removeAll();
            pmWindowsMenu.add( showAllPMWindowsMenuItem );
            pmWindowsMenu.addSeparator();
            for ( int j = 0; j < numberOfPCs; j++ ) {
                if ( j != MY_PLAYER_NUMBER ) {
                    PMFrame[j].addItemToMenu();
                }
            }
        }
    }

    public static void updateLipArray() {
        Vector localVector = new Vector();
        int i = 0;
        for ( int j = 0; j < numberOfPCs; j++ ) {
            if ( playerList[j].isLoggedIn() && playerList[j].isAPlayer() ) {
                localVector.addElement( playerList[j] );
            } else {
                i++;
            }
        }
        for ( int j = 0; j < i; j++ ) {
            localVector.addElement( "" );
        }
        if ( localVector.size() != numberOfPCs ) {
            logger.info( "Error: lipNames size not equal to number of player characters." );
        }
        Object[][] arrayOfObject = new Object[numberOfPCs][1];
        for ( int k = 0; k < localVector.size(); k++ ) {
            arrayOfObject[k][0] = localVector.elementAt( k );
        }
        for ( int k = 0; k < numberOfPCs; k++ ) {
            lipArray[k][0] = arrayOfObject[k][0];
        }
        lipTable.repaint();
    }

    private static void sortNames() {
        if ( sortedNames.size() > 1 ) {
            int i = sortedNames.size() - 1;
            int j = i - 1;
            while ( j >= 0 &&
                    ( (ClientPlayer) sortedNames.get( i ) ).getName()
                            .compareToIgnoreCase( ( (ClientPlayer) sortedNames.get( j ) ).getName() ) < 0 ) {
                sortedNames.add( i, sortedNames.remove( j ) );
                i = j;
                j--;
            }
        }
    }

    public static void playerHasJoined( String paramString ) {
        int i = Integer.parseInt( paramString );
        playerList[i].setLoggedIn( true );
        if ( !observer ) {
            playerList[i].pmFrame.enableInput();
        }
        absoluteChat( "--- " + playerList[i].toString() + " has joined ---" );
        updateLipArray();
        assignColorsToCharacters();
        if ( soundIsOn && soundMenu.joinLeaveMenuItem.isSelected() ) {
            soundPlayer.play( 5 );
        }
    }

    public static void playerHasLeft( String paramString ) {
        int i = Integer.parseInt( paramString );
        playerList[i].setLoggedIn( false );
        if ( !observer ) {
            playerList[i].pmFrame.disableInput();
        }
        absoluteChat( "--- " + playerList[i].toString() + " has left ---" );
        updateLipArray();
        if ( soundIsOn && soundMenu.joinLeaveMenuItem.isSelected() ) {
            soundPlayer.play( 6 );
        }
    }

    public static void playerHasDied( String paramString ) {
        int i = Integer.parseInt( paramString );
        ClientPlayer localClientPlayer = playerList[i];
        localClientPlayer.die();
        updateLipArray();
        if ( i == MY_PLAYER_NUMBER && !observer ) {
            myTitle.setPlayerName( myPlayer.toString() );
            frame.setTitle( myTitle.get() );
        }
        if ( soundIsOn && soundMenu.deathAlertMenuItem.isSelected() ) {
            soundPlayer.play( 14 );
        }
    }

    public static void playerHasUndied( String paramString ) {
        int i = Integer.parseInt( paramString );
        ClientPlayer localClientPlayer = playerList[i];
        localClientPlayer.unDie();
        updateLipArray();
        if ( i == MY_PLAYER_NUMBER && !observer ) {
            myTitle.setPlayerName( myPlayer.toString() );
            frame.setTitle( myTitle.get() );
        }
    }

    public static void receiveMyPlayerID( String paramString ) {
        MY_PLAYER_NUMBER = Integer.parseInt( paramString );
        if ( MY_PLAYER_NUMBER < 10 ) {
            MY_PLAYER_ID = "0" + MY_PLAYER_NUMBER;
        } else {
            MY_PLAYER_ID = "" + MY_PLAYER_NUMBER;
        }
        if ( MY_PLAYER_NUMBER != 99 || !observer ) {
            playerList[MY_PLAYER_NUMBER].setLoggedIn( true );
            myPlayer = playerList[MY_PLAYER_NUMBER];
        }
        int i = 0;
        for ( int j = 0; j < numberOfPlayers; j++ ) {
            if ( playerList[j].isAPlayer() ) {
                i++;
            }
        }
        numberOfPCs = i;
        loggedIn = true;
        updateLipArray();
        assignColorsToCharacters();
        if ( !observer ) {
            PMFrame = new PrivateMessageFrame[numberOfPCs];
            for ( int j = 0; j < numberOfPCs; j++ ) {
                if ( j != MY_PLAYER_NUMBER ) {
                    PMFrame[j] = new PrivateMessageFrame( playerList[j] );
                }
            }
            showAllPMWindowsMenuItem.setEnabled( true );
            myTitle.setPlayerName( myPlayer.toString() );
            frame.setTitle( myTitle.get() );
        }
        connectionStatusLabel.setIcon( connectedIcon );
        if ( keepLog ) {
            if ( htmlLog ) {
                log = new GameLogger( playerList );
            } else {
                log = new GameLogger();
            }
        }
        if ( soundIsOn && soundMenu.loginBadLoginMenuItem.isSelected() ) {
            soundPlayer.play( 3 );
        }
        if ( observer ) {
            out.println( "400" + realName );
        }
    }

    public static void receiveCharacterSheet() {
        synchronized ( in ) {
            try {
                myPlayer.characterSheet = new DefaultStyledDocument();
                String str = in.readLine();
                while ( !str.equals( "402" ) ) {
                    myPlayer.characterSheet.insertString( myPlayer.characterSheet.getLength(), str +
                            "\n", charsheetAttributes );
                    str = in.readLine();
                }
                charsheetArea.setDocument( myPlayer.characterSheet );
                charsheetArea.repaint();
                charsheetFrame.repaint();
                absoluteChat( "(Your character sheet has been updated by the GM.)" );
            } catch ( Exception localException ) {
                logger.info( "Error receiving character sheet." );
                localException.printStackTrace();
            }
        }
        if ( !firstCharsheetUpdate && soundIsOn && soundMenu.charSheetAlertMenuItem.isSelected() ) {
            soundPlayer.play( 20 );
        } else if ( firstCharsheetUpdate ) {
            firstCharsheetUpdate = false;
        }
    }

    public static void generalChat( String paramString ) {
        int i = Integer.parseInt( paramString.substring( 0, 2 ) );
        paramString = paramString.substring( 2 );
        styleBegin = styleEnd = "";
        if ( i == numberOfPCs ) {
            useComputerFont();
        } else if ( i == 0 ) {
            useGmFont();
        }
        try {
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, playerList[i].getChatColor() );
            chatDocument.insertString( chatDocument.getLength(), "   " +
                    playerList[i].toString() +
                    ": ", textAttributes );
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, textColor );
            chatDocument.insertString( chatDocument.getLength(), paramString + "\n", textAttributes );
            displayArea.setDocument( chatDocument );
            if ( autoScroll ) {
                displayArea.setCaretPosition( chatDocument.getLength() );
            }
            if ( keepLog ) {
                String str;
                if ( htmlLog ) {
                    str = styleBegin +
                            "<span class=\"player" +
                            i +
                            "\">" +
                            playerList[i].toString() +
                            ":</span> " +
                            paramString +
                            styleEnd +
                            "<br/>";
                } else {
                    str = playerList[i].toString() + ": " + paramString;
                }
                log.logEntry( str );
            }
        } catch ( BadLocationException localBadLocationException ) {
            System.err.println( "Unhandled exception. (Bad Location)" );
        }
        if ( i == numberOfPCs ) {
            restoreOriginalFont();
        } else if ( i == 0 ) {
            restoreOriginalFont();
        }
        if ( soundIsOn && soundMenu.newTextMenuItem.isSelected() ) {
            soundPlayer.play( 7 );
        }
    }

    public static void useComputerFont() {
        styleBegin = "<span class=\"computer\">";
        styleEnd = "</span>";
        mainFontSize = (Integer) textAttributes.getAttribute( StyleConstants.FontConstants.Size );
        int i = mainFontSize + computerFontIncrease;
        textAttributes.addAttribute( StyleConstants.FontConstants.Size, i );
        if ( !fontIsBold ) {
            setFontBold( true );
        }
    }

    public static void setFontBold( boolean paramBoolean ) {
        Boolean localBoolean = paramBoolean;
        textAttributes.addAttribute( StyleConstants.FontConstants.Bold, localBoolean );
    }

    public static void useGmFont() {
        styleBegin = "<span class=\"gmText\">";
        styleEnd = "</span>";
        if ( !fontIsBold ) {
            setFontBold( true );
        }
    }

    public static void restoreOriginalFont() {
        styleBegin = styleEnd = "";
        textAttributes.addAttribute( StyleConstants.FontConstants.Size, mainFontSize );
        if ( !fontIsBold ) {
            setFontBold( false );
        }
    }

    public static void actionChat( String paramString ) {
        int i = Integer.parseInt( paramString.substring( 0, 2 ) );
        paramString = paramString.substring( 2 );
        styleBegin = styleEnd = "";
        if ( i == numberOfPCs ) {
            useComputerFont();
        } else if ( i == 0 ) {
            useGmFont();
        }
        try {
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, playerList[i].getChatColor() );
            chatDocument.insertString( chatDocument.getLength(), "* " +
                    playerList[i].toString() +
                    " " +
                    paramString +
                    " *\n", textAttributes );
            displayArea.setDocument( chatDocument );
            if ( autoScroll ) {
                displayArea.setCaretPosition( chatDocument.getLength() );
            }
            if ( keepLog ) {
                String str;
                if ( htmlLog ) {
                    str = styleBegin +
                            "<span class=\"player" +
                            i +
                            "\">* " +
                            playerList[i].toString() +
                            " " +
                            paramString +
                            " *</span>" +
                            styleEnd +
                            "<br/>";
                } else {
                    str = "* " + playerList[i].toString() + " " + paramString + " *";
                }
                log.logEntry( str );
            }
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, textColor );
        } catch ( BadLocationException localBadLocationException ) {
            System.err.println( "Unhandled exception. (Bad Location)" );
        }
        if ( i == numberOfPCs ) {
            restoreOriginalFont();
        } else if ( i == 0 ) {
            restoreOriginalFont();
        }
        if ( soundIsOn && soundMenu.newTextMenuItem.isSelected() ) {
            soundPlayer.play( 7 );
        }
    }

    public static void speechChat( String paramString ) {
        int i = Integer.parseInt( paramString.substring( 0, 2 ) );
        paramString = paramString.substring( 2 );
        styleBegin = styleEnd = "";
        if ( i == numberOfPCs ) {
            useComputerFont();
        } else if ( i == 0 ) {
            useGmFont();
        }
        String str1;
        if ( paramString.endsWith( "!!" ) ) {
            str1 = "screams, ";
        } else if ( paramString.endsWith( "!" ) ) {
            str1 = "shouts, ";
        } else {
            str1 = "says, ";
        }
        try {
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, playerList[i].getChatColor() );
            chatDocument.insertString( chatDocument.getLength(), playerList[i].toString() + " ", textAttributes );
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, textColor );
            chatDocument.insertString( chatDocument.getLength(), str1 + "\"" + paramString + "\"\n", textAttributes );
            displayArea.setDocument( chatDocument );
            if ( autoScroll ) {
                displayArea.setCaretPosition( chatDocument.getLength() );
            }
            if ( keepLog ) {
                String str2;
                if ( htmlLog ) {
                    str2 = styleBegin +
                            "<span class=\"player" +
                            i +
                            "\">" +
                            playerList[i].toString() +
                            "</span> " +
                            str1 +
                            "\"" +
                            paramString +
                            "\"" +
                            styleEnd +
                            "<br/>";
                } else {
                    str2 = playerList[i].toString() + " " + str1 + "\"" + paramString + "\"";
                }
                log.logEntry( str2 );
            }
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, textColor );
        } catch ( BadLocationException localBadLocationException ) {
            System.err.println( "Unhandled exception. (Bad Location)" );
        }
        if ( i == numberOfPCs ) {
            restoreOriginalFont();
        } else if ( i == 0 ) {
            restoreOriginalFont();
        }
        if ( soundIsOn && soundMenu.newTextMenuItem.isSelected() ) {
            soundPlayer.play( 7 );
        }
    }

    public static void thoughtChat( String paramString ) {
        int i = Integer.parseInt( paramString.substring( 0, 2 ) );
        paramString = paramString.substring( 2 );
        styleBegin = styleEnd = "";
        if ( i == numberOfPCs ) {
            useComputerFont();
        } else if ( i == 0 ) {
            useGmFont();
        }
        try {
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, playerList[i].getChatColor() );
            chatDocument.insertString( chatDocument.getLength(), playerList[i].toString() +
                    " . o O ( " +
                    paramString +
                    " )\n", textAttributes );
            displayArea.setDocument( chatDocument );
            if ( autoScroll ) {
                displayArea.setCaretPosition( chatDocument.getLength() );
            }
            if ( keepLog ) {
                String str;
                if ( htmlLog ) {
                    str = styleBegin +
                            "<span class=\"player" +
                            i +
                            "\">" +
                            playerList[i].toString() +
                            " . o O ( " +
                            paramString +
                            " )</span>" +
                            styleEnd +
                            "<br/>";
                } else {
                    str = playerList[i].toString() + " . o O ( " + paramString + " )";
                }
                log.logEntry( str );
            }
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, textColor );
        } catch ( BadLocationException localBadLocationException ) {
            System.err.println( "Unhandled exception. (Bad Location)" );
        }
        if ( i == numberOfPCs ) {
            restoreOriginalFont();
        } else if ( i == 0 ) {
            restoreOriginalFont();
        }
        if ( soundIsOn && soundMenu.newTextMenuItem.isSelected() ) {
            soundPlayer.play( 7 );
        }
    }

    public static void observerChat( String paramString ) {
        String str1 = paramString.substring( 0, paramString.indexOf( "@~!~~" ) );
        paramString = paramString.substring( paramString.indexOf( "@~!~~" ) + 5 );
        try {
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, Color.gray );
            chatDocument.insertString( chatDocument.getLength(), "   " + str1 + ": ", textAttributes );
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, textColor.darker() );
            chatDocument.insertString( chatDocument.getLength(), paramString + "\n", textAttributes );
            displayArea.setDocument( chatDocument );
            if ( autoScroll ) {
                displayArea.setCaretPosition( chatDocument.getLength() );
            }
            if ( keepLog ) {
                String str2;
                if ( htmlLog ) {
                    str2 = "<span class=\"observer\">" +
                            str1 +
                            ": </span><span class=\"gray\">" +
                            paramString +
                            "</span><br/>";
                } else {
                    str2 = str1 + ": " + paramString;
                }
                log.logEntry( str2 );
            }
        } catch ( BadLocationException localBadLocationException ) {
            System.err.println( "Unhandled exception. (Bad Location)" );
        }
        if ( soundIsOn && soundMenu.newObserverTextMenuItem.isSelected() ) {
            soundPlayer.play( 7 );
        }
    }

    public static void receivePrivateMessage( String paramString ) {
        int i = Integer.parseInt( paramString.substring( 0, 2 ) );
        if ( i == MY_PLAYER_NUMBER ) {
            int j = Integer.parseInt( paramString.substring( 2, 4 ) );
            paramString = paramString.substring( 4 );
            try {
                if ( chatNotifyNewPMMenuItem.isSelected() ) {
                    absoluteChat( "(New private message from " + playerList[j].getName() + ")" );
                }
                if ( soundIsOn && soundMenu.newPMAlertMenuItem.isSelected() ) {
                    soundPlayer.play( 19 );
                }
                textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, playerList[j].getChatColor() );
                PMFrame[j].privateMessageDocument.insertString( PMFrame[j].privateMessageDocument.getLength(), " " +
                        playerList[j].getName() +
                        ": ", textAttributes );
                textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, textColor );
                PMFrame[j].privateMessageDocument.insertString( PMFrame[j].privateMessageDocument.getLength(),
                        paramString +
                                "\n", textAttributes );
                PMFrame[j].displayArea.setDocument( PMFrame[j].privateMessageDocument );
                if ( autoScroll ) {
                    PMFrame[j].displayArea.setCaretPosition( PMFrame[j].privateMessageDocument.getLength() );
                }
                if ( keepLog ) {
                    String str;
                    if ( htmlLog ) {
                        str = "<span class=\"pm\"><span class=\"player" +
                                j +
                                "\">(" +
                                playerList[j].toString() +
                                "</span> -> <span class=\"player0\">" +
                                myPlayer.toString() +
                                ")</span>: " +
                                paramString +
                                "</span><br/>";
                    } else {
                        str = "(" + playerList[j].toString() + " -> " + myPlayer.toString() + "): " + paramString;
                    }
                    log.logEntry( str );
                }
                if ( !PMFrame[j].isShowing() ) {
                    PMFrame[j].setVisible( true );
                }
            } catch ( BadLocationException localBadLocationException ) {
                System.err.println( "Unhandled exception. (Bad Location)" );
            }
        }
    }

    public static void startCombat() {
        inCombat = true;
        if ( !observer ) {
            connectionStatusLabel.setIcon( frozenIcon );
            turnFrame = new CombatTurnFrame();
            turnFrame.setVisible( true );
        }
        if ( soundIsOn && soundMenu.combatAlertMenuItem.isSelected() ) {
            soundPlayer.play( 21 );
        }
        if ( soundIsOn && soundMenu.combatMusicMenuItem.isSelected() ) {
            soundPlayer.startCombatMusic();
            combatMusicIsPlaying = true;
        }
    }

    public static void takeCombatTurn() {
        connectionStatusLabel.setIcon( combatIcon );
        Toolkit.getDefaultToolkit().beep();
        long l = System.currentTimeMillis();
        while ( System.currentTimeMillis() - l < 300L ) {
            //lol, looks like this is how sleep works here
        }
        Toolkit.getDefaultToolkit().beep();
        turnFrame.sendButton.setEnabled( true );
    }

    public static void abortCombatTurn() {
        connectionStatusLabel.setIcon( frozenIcon );
        if ( turnFrame.isVisible() ) {
            turnFrame.dispose();
        }
    }

    public static void combatComplete() {
        inCombat = false;
        if ( combatMusicIsPlaying ) {
            soundPlayer.stopCombatMusic();
        }
    }

    public static void gmSendingPrivateMessage( String paramString ) {
        int i = Integer.parseInt( paramString );
        if ( i == MY_PLAYER_NUMBER || i == 999 ) {
            messageStatusLabel.setIcon( incomingMsgIcon );
        } else {
            messageStatusLabel.setIcon( gmSendingMsgIcon );
        }
    }

    public static void clearMessageStatusLabel() {
        messageStatusLabel.setIcon( nullMsgIcon );
    }

    public static void checkMuted( String paramString ) {
        int i = Integer.parseInt( paramString );
        if ( i == MY_PLAYER_NUMBER ) {
            muted = true;
            if ( !frozen ) {
                connectionStatusLabel.setIcon( mutedIcon );
            }
            if ( soundIsOn && soundMenu.mutedUnmutedMenuItem.isSelected() ) {
                soundPlayer.play( 8 );
            }
        }
    }

    public static void checkUnmuted( String paramString ) {
        int i = Integer.parseInt( paramString );
        if ( i == MY_PLAYER_NUMBER ) {
            muted = false;
            if ( frozen ) {
                connectionStatusLabel.setIcon( frozenIcon );
            } else {
                connectionStatusLabel.setIcon( connectedIcon );
            }
            if ( soundIsOn && soundMenu.mutedUnmutedMenuItem.isSelected() ) {
                soundPlayer.play( 9 );
            }
        }
    }

    public static void listenObservers() {
        if ( !hearObservers ) {
            hearObservers = true;
        }
        absoluteChat( "Observers are currently heard." );
    }

    public static void muteObservers() {
        if ( hearObservers ) {
            hearObservers = false;
        }
        absoluteChat( "Observers are currently NOT heard." );
    }

    public static void freezeMe() {
        if ( !frozen ) {
            frozen = true;
            connectionStatusLabel.setIcon( frozenIcon );
            if ( soundIsOn &&
                    soundMenu.freezeUnfreezeMenuItem.isSelected() &&
                    ( !inCombat ||
                            !soundMenu.combatMusicMenuItem.isSelected() &&
                                    !soundMenu.combatAlertMenuItem.isSelected() ) ) {
                soundPlayer.play( 10 );
            }
        }
    }

    public static void unfreezeMe() {
        if ( frozen ) {
            frozen = false;
            if ( muted ) {
                connectionStatusLabel.setIcon( mutedIcon );
            } else {
                connectionStatusLabel.setIcon( connectedIcon );
            }
            if ( soundIsOn && soundMenu.freezeUnfreezeMenuItem.isSelected() ) {
                soundPlayer.play( 11 );
            }
        }
    }

    public static void updateTitle( String paramString ) {
        myTitle.setExtra( paramString );
        frame.setTitle( myTitle.get() );
    }

    public static void updateComputerFontIncrease( String paramString ) {
        computerFontIncrease = Integer.parseInt( paramString );
    }

    public static void updateMaxCloneNumber( String paramString ) {
        maxNumClones = Integer.parseInt( paramString );
    }

    public static void setObserver( boolean paramBoolean ) {
        observer = paramBoolean;
        if ( paramBoolean ) {
            for ( ;
                  realName.trim().equals( "default" ) /*|| realName == null*/ || realName.trim().equals( "" );
                  realName = (String) JOptionPane.showInputDialog( null, "Enter your observer name:", "Observer name...", JOptionPane.PLAIN_MESSAGE, null, null, realName ) ) {
                new JOptionPane();
            }
            myTitle.setPlayerName( realName );
            frame.setTitle( myTitle.get() );
        } else {
            out.println( realName );
        }
    }

    public static String getVersionName() {
        return VERSION_NAME;
    }

    public static void playerPromoted() {
        if ( soundIsOn && soundMenu.promotedDemotedMenuItem.isSelected() ) {
            soundPlayer.play( 12 );
        }
    }

    public static void playerDemoted() {
        if ( soundIsOn && soundMenu.promotedDemotedMenuItem.isSelected() ) {
            soundPlayer.play( 13 );
        }
    }

    public static void displayImage( String paramString ) {
        JParanoia.displayImage( paramString );
        String str1 = paramString.substring( 0, paramString.indexOf( "http://" ) );
        String str2 = paramString.substring( paramString.indexOf( "http://" ) );
        if ( keepLog ) {
            String str3;
            if ( htmlLog ) {
                str3 = "IMAGE: \"" +
                        str1 +
                        "\" URL: <a href=\"" +
                        str2 +
                        "\">" +
                        str2 +
                        "</a><br/>\n<img src=\"" +
                        str2 +
                        "\"><br/>";
            } else {
                str3 = "IMAGE: \"" + str1 + "\" URL: " + str2;
            }
            log.logEntry( str3 );
        }
    }

    public static void exit() {
        disconnect( false );
        frame.dispose();
        System.exit( 0 );
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\client\JPClient.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */
