package jparanoia.server;
import java.awt.Color;
import static java.awt.Color.black;
import static java.awt.Color.cyan;
import static java.awt.Color.gray;
import static java.awt.Color.green;
import static java.awt.Color.orange;
import static java.awt.Color.white;
import static java.awt.Color.yellow;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import static java.awt.Toolkit.getDefaultToolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import static java.lang.Boolean.TRUE;
import static java.lang.System.arraycopy;
import static java.lang.System.getProperty;
import static java.lang.System.out;
import java.lang.invoke.MethodHandles;
import static java.lang.invoke.MethodHandles.lookup;
import java.net.InetAddress;
import static java.net.InetAddress.getLocalHost;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;
import static javax.swing.BorderFactory.createTitledBorder;
import static javax.swing.Box.createRigidArea;
import javax.swing.BoxLayout;
import static javax.swing.BoxLayout.X_AXIS;
import static javax.swing.BoxLayout.Y_AXIS;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.PLAIN_MESSAGE;
import static javax.swing.JOptionPane.showInputDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import static javax.swing.JSplitPane.VERTICAL_SPLIT;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
import static javax.swing.UIManager.getCrossPlatformLookAndFeelClassName;
import static javax.swing.UIManager.setLookAndFeel;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.PlainDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import static javax.swing.text.StyleConstants.Bold;
import static javax.swing.text.StyleConstants.Family;
import static javax.swing.text.StyleConstants.Foreground;
import static javax.swing.text.StyleConstants.Size;
import jparanoia.shared.BrightColorArray;
import jparanoia.shared.ErrorLogger;
import jparanoia.shared.GameLogger;
import jparanoia.shared.GameRegistrar;
import static jparanoia.shared.GameRegistrar.addGame;
import static jparanoia.shared.GameRegistrar.removeGame;
import jparanoia.shared.JPVersionNumber;
import jparanoia.shared.JParanoia;
import jparanoia.shared.TitleClass;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;
import org.slf4j.profiler.Profiler;

public class JPServer extends JParanoia {
    private final static Logger logger = getLogger( MethodHandles.lookup().lookupClass());

    public static final JPVersionNumber VERSION_NUMBER = new JPVersionNumber( 1, 31, 1 );
    public static final String VERSION_NAME = VERSION_NUMBER.toString();
    public static final JPVersionNumber MIN_COMPATIBLE_VERSION_NUMBER = new JPVersionNumber( 1, 31, 0 );
    static final int IDEAL_WIDTH = 770;
    static final int IDEAL_HEIGHT = 540;
    static final String MY_PLAYER_ID = "00";
    static final int MY_PLAYER_NUMBER = 0;
    public static boolean keepLog = false;
    public static boolean htmlLog = true;
    public static boolean registerGame = false;
    public static boolean gameRegistered = false;
    static Socket someSock = null;
    static PrintWriter someWriter;
    static OutputStream someOuputStream;
    static ServerSocketThread servSocketThread;
    static ServerChatThread thisThread;
    static final Vector<ServerChatThread> chatThreads = new Vector<>();
    static int numberOfConnectedClients = 0;
    static int numberOfConnectedObservers = 0;
    static ThreadGroup chatThreadGroup = new ThreadGroup( "my group of chat threads" );
    static ServerPlayer[] players;
    static ServerPlayer myPlayer;
    static int numberOfPlayers = 0;
    static int numberOfPCs = 0;
    static int maxNumClones;
    static int computerFontIncrease;
    static Integer mainFontSize = 99;
    static SimpleAttributeSet charsheetAttributes;
    static boolean jvm140 = System.getProperty( "java.version" ).startsWith( "1.4.0" );
    static boolean bigComputerFont = true;
    static boolean computerAllCaps;
    static boolean sendMainText = true;
    static boolean quickNamesToggle = false;
    static boolean behindRouter = false;
    static boolean allowGMEmotes = true;
    static boolean singleUseSpoof = false;
    static boolean isPXPGame;
    static ServerPlayer[] troubleshooters;
    static Vector<ServerPlayer> spareNpcs = new Vector<>( 10 );
    static ServerPlayer playerToSpoof;
    static ServerPlayer pmTargetPlayer;
    static JScrollPane inputScrollPane;
    static String titleMessage = "";
    static String announcement = "";
    static String styleBegin;
    static String styleEnd = "";
    static TitleClass myTitle = new TitleClass( "JParanoia Server", VERSION_NAME, false );
    static Container contentPane;
    static JPanel inputPanel;
    static JPanel PMContainer;
    static JPanel mainPanel;
    static JPanel freezePanel;
    static JPanel spoofPanel;
    static JPanel spoofAndFreezePanel;
    static JOptionPane connectOPane;
    static JOptionPane errorPane;
    static JMenuBar menuBar;
    static ServerOptionsMenu optionsMenu;
    static JMenu serverMenu;
    static JMenu fontMenu;
    static JMenu fontFamilyMenu;
    static JMenu fontSizeMenu;
    static JMenu playerMenu;
    static JMenu killMenu;
    static JMenu unkillMenu;
    static JMenu npcMenu;
    static JMenu renameMenu;
    static JMenu kickMenu;
    static JMenu globalPMMenu;
    static JMenu combatMenu;
    static JMenu sendImageMenu;
    static JMenu observersMenu;
    static JMenuItem startServerMenuItem;
    static JMenuItem stopServerMenuItem;
    static JMenuItem sendGlobalPMMenuItem;
    static JMenuItem combatMenuItem;
    static JMenuItem localIPMenuItem;
    static JMenuItem setGameDescriptionMenuItem;
    static JMenuItem showObserversListMenuItem;
    static JMenuItem announceObserversMenuItem;
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
    static JCheckBoxMenuItem showTimeStampsMenuItem;
    static JCheckBoxMenuItem fontBoldMenuItem;
    static JCheckBoxMenuItem registerGameMenuItem;
    static JCheckBoxMenuItem hearObserversMenuItem;
    static JCheckBox spoofCheckBox;
    static JComboBox<? extends ServerPlayer> spoofComboBox;
    static JButton freezeButton;
    static JButton unfreezeButton;
    static JButton combatButton;
    static JLabel ipLabel;
    static JScrollPane scrollPane;
    static JSplitPane splitPane;
    static JTable lipTable;
    static JTextArea inputLine;
    static CombatFrame combatFrame;
    static CharsheetPanel charsheetPanel;
    static ImageDataParser idp;
    static Dimension lipTablePreferredSize = new Dimension( 130, 160 );
    static PlainDocument inputDocument;
    static SimpleAttributeSet spaceAttributes;
    static SimpleAttributeSet systemTextAttributes = new SimpleAttributeSet();
    static Color[] brightColors;
    static Color[] darkColors;
    static Color newColor;
    static Date timeStamp;
    static Random rand = new Random();
    static int randInt = rand.nextInt( 1000 );
    static String defaultGameDescription = "JParanoia " + VERSION_NAME + " (" + randInt + ")";
    static String gameDescription = defaultGameDescription;
    static boolean fontIsBold = false;
    static boolean showTimeStamps = true;
    static boolean allowObservers;
    static boolean frozen = false;
    static boolean clobberAqua;
    static boolean gmNameNag;
    static String[] nonConnectedPlayers;
    static String currentPlayerID = "00";
    static String currentColorScheme = "";
    static String newColorScheme = WHITE_ON_BLACK;
    static boolean serverRunning = false;
    static String addressToTry = null;
    static InetAddress localIP = null;
    static PrivateMessagePane[] PMPane;
    static PMAndStatusPanel[] pmstatus;
    static KillMenuItem[] killMenuItemArray;
    static UnkillMenuItem[] unkillMenuItemArray;
    static RenamePlayerMenuItem[] renameMenuItemArray;
    static KickMenuItem[] kickMenuItemArray;
    static Font normalFont = new Font( null, Font.PLAIN, 12 );
    static Font spoofFont = new Font( null, Font.BOLD, 16 );
    Component componentHolder;

    public JPServer() {
        Profiler profiler = new Profiler("JPServer");

        clobberAqua = (Boolean) prefs.getPref( 18 );
        if ( clobberAqua ) {
            try {
                setLookAndFeel( getCrossPlatformLookAndFeelClassName() );
            } catch ( Exception localException ) {
                logger.info( "Exception while setting L&F." );
            }
        }
        appInfo = "JParanoia Server " + VERSION_NAME;
        allowObservers = (Boolean) prefs.getPref( 28 );
        registerGame = (Boolean) prefs.getPref( 31 );
        behindRouter = (Boolean) prefs.getPref( 32 );
        isPXPGame = (Boolean) prefs.getPref( 34 );
        if ( isPXPGame ) {
            maxNumClones = 999;
        } else {
            maxNumClones = (Integer) prefs.getPref( 23 );
        }
        gmNameNag = (Boolean) prefs.getPref( 35 );
        computerFontIncrease = (Integer) prefs.getPref( 26 );

        profiler.start( "frame init" );
        frame.setTitle( myTitle.get() );
        frame.setIconImage( getDefaultToolkit().getImage( lookup().lookupClass().getClassLoader().getResource( "graphics/jparanoiaIcon.jpg" ) ) );
        frame.setDefaultCloseOperation( DO_NOTHING_ON_CLOSE );
        frame.addWindowListener( new WindowAdapter() {
            public void windowClosing( WindowEvent paramAnonymousWindowEvent ) { JPServer.exit(); }
        } );
        try {
            localIP = getLocalHost();
        } catch ( UnknownHostException localUnknownHostException ) {
            logger.info( "Error: Unable to get local host address" );
        }
        charsheetAttributes = new SimpleAttributeSet();
        charsheetAttributes.addAttribute( Bold, TRUE );
        charsheetAttributes.addAttribute( Family, "SansSerif" );
        charsheetAttributes.addAttribute( Size, 12 );

        profiler.start( "player list init" );
        DataParser localDataParser = new DataParser();
        players = localDataParser.parsePlayerList( "playerList.txt" );

        profiler.start( "image data init" );
        logger.info( "Processing imageData.txt:" );
        idp = new ImageDataParser();
        idp.parseImageURLs( "imageData.txt" );
        numberOfPlayers = players.length;
        for ( final ServerPlayer player : players ) {
            if ( player.isAnActualPlayer() ) {
                numberOfPCs += 1;
            }
        }
        troubleshooters = new ServerPlayer[numberOfPCs - 1];
        arraycopy( players, 1, troubleshooters, 0, numberOfPCs - 1 );
        for ( final ServerPlayer troubleshooter : troubleshooters ) {
            sortedNames.add( troubleshooter );
            if ( sortedNames.size() > 1 ) {
                int j = sortedNames.size() - 1;
                int k = j - 1;
                while ( k >= 0 &&
                        ( (ServerPlayer) sortedNames.get( j ) ).getName()
                                .compareToIgnoreCase( ( (ServerPlayer) sortedNames.get( k ) ).getName() ) < 0 ) {
                    sortedNames.add( j, sortedNames.remove( k ) );
                    j = k;
                    k--;
                }
            }
        }
        players[0].setLoggedIn( true );
        myPlayer = players[0];

        profiler.start( "further frame init" );
        frame.setSize( 770, 540 );
        displayArea = new JTextPane();
        displayArea.setEditable( false );
        displayArea.setEnabled( true );
        displayArea.setBackground( black );
        scrollPane = new JScrollPane( displayArea, VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_NEVER );
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
                    logger.info( "Error: Out of colors! (Try using White on Black scheme)" );
            }
            darkColors[i] = newColor;
        }
        textAttributes = new SimpleAttributeSet();
        textAttributes.addAttribute( Foreground, white );
        textAttributes.addAttribute( Size, prefs.getPref( 15 ) );
        textAttributes.addAttribute( Family, prefs.getPref( 16 ) );
        systemTextAttributes.addAttribute( Foreground, gray );
        setColorScheme();
        inputDocument = new PlainDocument();
        inputLine = new JTextArea( 3, 44 );
        inputLine.setLineWrap( true );
        inputLine.setWrapStyleWord( true );
        inputLine.addKeyListener( new KeyListener() {
            public void keyTyped( KeyEvent paramAnonymousKeyEvent ) {}

            public void keyPressed( KeyEvent paramAnonymousKeyEvent ) {
                previousKey = thisKey;
                thisKey = paramAnonymousKeyEvent.getKeyCode();
                String str;
                if ( thisKey == 10 && sendMainText ) {
                    str = inputLine.getText();
                    if ( str.length() > 0 ) {
                        inputLine.setCaretPosition( str.length() );
                    }
                }
                if ( thisKey == 9 ) {
                    paramAnonymousKeyEvent.consume();
                    if ( inputLine.getText().length() > 0 ) {
                        str = inputLine.getText();
                        if ( str.startsWith( "'" ) && str.length() > 1 ) {
                            inputLine.setText( "'" + nameCompletion( str.substring( 1 ), thisKey == previousKey ) );
                        } else if ( !str.startsWith( "'" ) ) {
                            inputLine.setText( nameCompletion( str, thisKey == previousKey ) );
                        }
                    }
                }
            }

            public void keyReleased( KeyEvent paramAnonymousKeyEvent ) {
                if ( paramAnonymousKeyEvent.getKeyCode() == 10 && sendMainText ) {
                    String str = inputLine.getText();
                    if ( str.length() > 0 ) {
                        sendChat( str.substring( 0, str.length() - 1 ) );
                    }
                } else if ( paramAnonymousKeyEvent.getKeyCode() == 10 && !sendMainText ) {
                    setSendMainText( true );
                }
            }
        } );
        inputLine.setEnabled( false );
        inputLine.setFont( normalFont );
        inputScrollPane = new JScrollPane( inputLine, VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_NEVER );
        ServerPlayer[] arrayOfServerPlayer = new ServerPlayer[players.length - 1];
        arraycopy( players, 1, arrayOfServerPlayer, 0, players.length - 1 );
        spoofComboBox = new JComboBox<>( arrayOfServerPlayer );
        spoofComboBox.addActionListener( paramAnonymousActionEvent -> {
            playerToSpoof = (ServerPlayer) spoofComboBox.getSelectedItem();
            if ( spoofCheckBox.isSelected() ) {
                currentPlayerID = playerToSpoof.getID();
            }
            inputLine.requestFocus();
        } );
        playerToSpoof = (ServerPlayer) spoofComboBox.getSelectedItem();
        spoofComboBox.setMaximumSize( new Dimension( 130, 20 ) );
        spoofComboBox.setPreferredSize( new Dimension( 130, 20 ) );
        spoofComboBox.setMinimumSize( new Dimension( 130, 20 ) );
        spoofCheckBox = new JCheckBox();
        spoofCheckBox.addActionListener( paramAnonymousActionEvent -> {
            if ( spoofCheckBox.isSelected() ) {
                currentPlayerID = playerToSpoof.getID();
                inputLine.setFont( spoofFont );
            } else {
                currentPlayerID = "00";
                inputLine.setFont( normalFont );
            }
            inputLine.requestFocus();
        } );
        spoofPanel = new JPanel();
        spoofPanel.setLayout( new BoxLayout( spoofPanel, X_AXIS ) );
        spoofPanel.add( createRigidArea( new Dimension( 2, 0 ) ) );
        spoofPanel.add( spoofComboBox );
        spoofPanel.add( createRigidArea( new Dimension( 5, 0 ) ) );
        spoofPanel.add( spoofCheckBox );
        spoofPanel.add( createRigidArea( new Dimension( 2, 0 ) ) );
        spoofPanel.setBorder( createTitledBorder( "Spoof" ) );
        freezePanel = new JPanel();
        freezePanel.setLayout( new GridLayout( 1, 2 ) );
        freezeButton = new JButton( "Freeze" );
        freezeButton.addActionListener( paramAnonymousActionEvent -> {
            if ( !frozen ) {
                freezePlayers();
            } else {
                unfreezePlayers();
            }
        } );
        combatButton = new JButton( "Combat!" );
        combatButton.addActionListener( paramAnonymousActionEvent -> JPServer.startCombat());
        combatButton.setEnabled( false );
        freezePanel.add( combatButton );
        freezePanel.add( freezeButton );
        spoofAndFreezePanel = new JPanel();
        spoofAndFreezePanel.setLayout( new BoxLayout( spoofAndFreezePanel, Y_AXIS ) );
        spoofAndFreezePanel.add( spoofPanel );
        spoofAndFreezePanel.add( createRigidArea( new Dimension( 0, 5 ) ) );
        spoofAndFreezePanel.add( freezePanel );
        inputPanel = new JPanel();
        inputPanel.setLayout( new BoxLayout( inputPanel, X_AXIS ) );
        inputPanel.add( spoofAndFreezePanel );
        inputPanel.add( createRigidArea( new Dimension( 5, 0 ) ) );
        inputPanel.add( inputScrollPane );
        charsheetPanel = new CharsheetPanel();
        menuBar = new JMenuBar();
        frame.setJMenuBar( menuBar );
        serverMenu = new JMenu( "Server" );
        startServerMenuItem = new JMenuItem( "Start server" );
        startServerMenuItem.setToolTipText( "Opens your computer for new connections." );
        startServerMenuItem.addActionListener( paramAnonymousActionEvent -> startServer() );
        stopServerMenuItem = new JMenuItem( "Stop server" );
        stopServerMenuItem.setToolTipText( "Stops your computer from accepting new connections." );
        stopServerMenuItem.addActionListener( paramAnonymousActionEvent -> stopServer() );
        registerGameMenuItem = new JCheckBoxMenuItem( "Register Game" );
        registerGameMenuItem.setToolTipText( "<HTML>When checked, your server will be made available<BR>to players via the JParanoia Game Registry so they<BR>will not need the IP address of your server.</HTML>" );
        registerGameMenuItem.setSelected( prefs.getPref( 31 ).equals( TRUE ) );
        registerGameMenuItem.addActionListener( paramAnonymousActionEvent -> {
            if ( registerGame ) {
                registerGame = false;
                if ( gameRegistered ) {
                    removeGame();
                }
            } else {
                registerGame = true;
                if ( serverRunning ) {
                    if ( gameDescription.equals( defaultGameDescription ) ) {
                        setGameDescriptionMenuItem.doClick();
                    }
                    addGame( gameDescription );
                }
            }
        } );
        setGameDescriptionMenuItem = new JMenuItem( "Set Game Description..." );
        setGameDescriptionMenuItem.setToolTipText( "<HTML>This changes the name of your game<BR> on the JParanoia Game Registry.</HTML>" );
        setGameDescriptionMenuItem.addActionListener( paramAnonymousActionEvent -> {
            String str = (String) showInputDialog( null, "Enter a description for your game:", "Set Game Description...", PLAIN_MESSAGE, null, null, gameDescription );
            if ( str != null && !str.equals( "" ) && !str.equals( gameDescription ) ) {
                gameDescription = str;
                if ( gameRegistered ) {
                    addGame( gameDescription );
                }
            }
        } );
        serverMenu.add( startServerMenuItem );
        serverMenu.add( stopServerMenuItem );
        serverMenu.addSeparator();
        serverMenu.add( registerGameMenuItem );
        serverMenu.add( setGameDescriptionMenuItem );
        stopServerMenuItem.setEnabled( false );
        serverMenu.addSeparator();
        ipLabel = new JLabel( "  Local IP :   " + localIP.getHostAddress() );
        serverMenu.add( ipLabel );
        menuBar.add( serverMenu );
        fontMenu = new FontMenu( "Font" );
        menuBar.add( fontMenu );
        optionsMenu = new ServerOptionsMenu( "Options" );
        menuBar.add( optionsMenu );
        playerMenu = new JMenu( "Player" );
        for ( final ServerPlayer troubleshooter : troubleshooters ) {
            playerMenu.add( troubleshooter.getPlayerMenu() );
        }
        menuBar.add( playerMenu );
        npcMenu = new JMenu( "Spare NPCs" );
        for ( ServerPlayer spareNpc : spareNpcs ) {
            npcMenu.add( spareNpc.getNpcMenu() );
        }
        menuBar.add( npcMenu );
        globalPMMenu = new JMenu( "Global PM" );
        sendGlobalPMMenuItem = new JMenuItem( "Send Global PM..." );
        sendGlobalPMMenuItem.addActionListener( paramAnonymousActionEvent -> {
            new GlobalPMDialog().setVisible( true );
        } );
        globalPMMenu.add( sendGlobalPMMenuItem );
        menuBar.add( globalPMMenu );
        sendImageMenu = new ServerImageMenu();
        menuBar.add( sendImageMenu );
        observersMenu = new JMenu( "Observers" );
        hearObserversMenuItem = new JCheckBoxMenuItem( "Hear Observers" );
        hearObserversMenuItem.setSelected( (Boolean) prefs.getPref( 29 ) );
        hearObserversMenuItem.addActionListener( paramAnonymousActionEvent -> JPServer.toggleHearObservers());
        announceObserversMenuItem = new JCheckBoxMenuItem( "Announce Observers" );
        announceObserversMenuItem.setSelected( (Boolean) prefs.getPref( 30 ) );
        announceObserversMenuItem.addActionListener( paramAnonymousActionEvent -> JPServer.toggleAnnounceObservers());
        showObserversListMenuItem = new JMenuItem( "Show Observers List" );
        showObserversListMenuItem.addActionListener( paramAnonymousActionEvent -> obsFrame.setVisible( true ) );
        observersMenu.add( hearObserversMenuItem );
        observersMenu.add( announceObserversMenuItem );
        observersMenu.add( showObserversListMenuItem );
        menuBar.add( observersMenu );
        Container localContainer = frame.getContentPane();
        mainPanel = new JPanel();
        GridBagLayout localGridBagLayout1 = new GridBagLayout();
        GridBagConstraints localGridBagConstraints1 = new GridBagConstraints();
        splitPane = new JSplitPane( VERTICAL_SPLIT, true, charsheetPanel, scrollPane );
        splitPane.setDividerLocation( 122 );
        splitPane.setOneTouchExpandable( true );
        localGridBagConstraints1.gridx = 0;
        localGridBagConstraints1.gridy = 0;
        localGridBagConstraints1.weighty = 1.0D;
        localGridBagConstraints1.weightx = 1.0D;
        localGridBagConstraints1.fill = 1;
        localGridBagConstraints1.anchor = 15;
        localGridBagConstraints1.insets = new Insets( 2, 2, 2, 2 );
        localGridBagLayout1.setConstraints( splitPane, localGridBagConstraints1 );
        PMPane = new PrivateMessagePane[numberOfPCs];
        for ( int m = 1; m < numberOfPCs; m++ ) {
            PMPane[m] = new PrivateMessagePane( players[m] );
        }

        profiler.start( "status init" );
        logger.info( "\nServer's local IP Address: " + localIP.getHostAddress() + "\n" );
        pmstatus = new PMAndStatusPanel[numberOfPCs];
        logger.info( "PM & status array initialized." );
        for ( int m = 1; m < numberOfPCs; m++ ) {
            pmstatus[m] = new PMAndStatusPanel( PMPane[m], PMPane[m].statusPanel );
        }
        logger.info( "PM & status panels created." );
        PMContainer = new JPanel();
        PMContainer.setLayout( new BoxLayout( PMContainer, Y_AXIS ) );
        for ( int m = 1; m < numberOfPCs; m++ ) {
            PMContainer.add( createRigidArea( new Dimension( 0, 2 ) ) );
            PMContainer.add( pmstatus[m] );
        }
        logger.info( "PM & status pane populated." );
        GridBagLayout localGridBagLayout2 = new GridBagLayout();
        GridBagConstraints localGridBagConstraints2 = new GridBagConstraints();
        localContainer.setLayout( localGridBagLayout2 );
        localGridBagConstraints2.gridx = 0;
        localGridBagConstraints2.gridy = 0;
        localGridBagConstraints2.weighty = 1.0D;
        localGridBagConstraints2.weightx = 1.0D;
        localGridBagConstraints2.fill = 1;
        localGridBagConstraints2.anchor = 15;
        localGridBagConstraints2.insets = new Insets( 2, 2, 2, 2 );
        localGridBagLayout2.setConstraints( splitPane, localGridBagConstraints2 );
        localContainer.add( splitPane );
        localGridBagConstraints2.weighty = 0.0D;
        localGridBagConstraints2.gridy = 5;
        localGridBagLayout2.setConstraints( inputPanel, localGridBagConstraints2 );
        localContainer.add( inputPanel );
        localGridBagConstraints2.gridx = 4;
        localGridBagConstraints2.gridy = 0;
        localGridBagConstraints2.gridheight = 6;
        localGridBagConstraints2.gridwidth = 1;
        localGridBagConstraints2.weightx = 0.0D;
        localGridBagLayout2.setConstraints( PMContainer, localGridBagConstraints2 );
        localContainer.add( PMContainer );
        connectOPane = new JOptionPane();
        errorPane = new JOptionPane();
        myPlayer = players[0];
        displayWrite( green, "JParanoia Server " + VERSION_NAME );
        displayWrite( orange, "      http://www.byronbarry.com/jparanoia/\n" );
        displayWrite( cyan, "New in this release:\n\n" );
        displayWrite( white, "- Quick Charsheet option (see README).\n- The Computer's text is now large in the logs.\n- The GM's text is now bold in the logs.\n- Unplanned images now appear in the logs.\n- Current passwords appear in Player menu.\n- Other miscellaneous bug fixes.\n\nRead the README.TXT for full details.\nFor a complete version history, visit the JParanoia website.\n\n" );
        displayWrite( white, "If you are new to running a JParanoia server, or find yourself wondering how to do something, " );
        displayWrite( yellow, "READ THE README.\n" );

        profiler.start( "log init" );
        keepLog = (Boolean) prefs.getPref( 20 );
        htmlLog = (Boolean) prefs.getPref( 21 );
        if ( keepLog ) {
            if ( htmlLog ) {
                log = new GameLogger( players );
            } else {
                log = new GameLogger();
            }
        }
        logger.info( "JPServer.frame constructed.\n" );

        profiler.start( "combat init" );
        out.print( "Attempting to load CombatFrame class... " );
        try {
            combatFrame = new CombatFrame();
            combatFrame = null;
            logger.info( "loaded." );
        } catch ( NoClassDefFoundError localNoClassDefFoundError ) {
            logger.info( "FAILED" );
            errorMessage( "CombatFrame - class definition not found", "The CombatFrame class failed to load. The combat manager\nis not available. Please notify the author. You will need\nto exit and relaunch the server to correct this problem." );
        }
//        net.roydesign.mac.MRJAdapter.addQuitApplicationListener(paramAnonymousActionEvent -> {
//        });
//        net.roydesign.mac.MRJAdapter.addAboutListener(paramAnonymousActionEvent -> {
//
//            JParanoia.aboutBoxMenuItem.doClick();
//        });
        mainFontSize = (Integer) textAttributes.getAttribute( Size );
        if ( isPXPGame ) {
            frame.setSize( 855, frame.getHeight() );
        }
        frame.setVisible( true );
        profiler.stop().print();
    }

    public static void main( String[] paramArrayOfString ) {
        logger.info( "\nThis is the JParanoia Server console.\n" );
        logger.info( "Running under Java Runtime Environment version " + getProperty( "java.version" ) );
        logger.info( "\n" );
        JPServer localJPServer = new JPServer();
        splitPane.repaint();
    }

    public static void exit() {
        if ( numberOfConnectedClients - numberOfConnectedObservers > 0 ) {
            if ( JOptionPane.showConfirmDialog( frame, "WARNING: Players are still connected!\nAre you SURE you want to quit?", "Quit confirmation...", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE ) ==
                    0 ) {
                spamString( "086SERVER TERMINATED" );
            } else {
                return;
            }
        }
        if ( keepLog ) {
            log.closeLog();
            if ( htmlLog ) {
                log.sanitize();
            }
        }
        if ( ServerPlayer.numUnsavedCharsheets > 0 ) {
            if ( JOptionPane.showConfirmDialog( frame, "Some character sheets contain unsaved changes.\nSave before exiting?", "Unsaved Changes", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE ) ==
                    0 ) {
                soundMenu.charSheetAlertMenuItem.setSelected( false );
                for ( final ServerPlayer troubleshooter : troubleshooters ) {
                    if ( troubleshooter.hasUnsavedCharsheet() ) {
                        troubleshooter.saveCharsheet( false );
                    }
                }
            }
        }
        if ( gameRegistered ) {
            GameRegistrar.removeGame();
        }
        frame.dispose();
        System.exit( 0 );
    }

    public static synchronized void spamString( String paramString ) {
        for ( int i = 0; i < chatThreads.size(); i++ ) {
            thisThread = chatThreads.elementAt( i );
            someWriter = thisThread.out;
            someWriter.println( paramString );
        }
    }

    public static void setColorScheme() {
        if ( !currentColorScheme.equals( newColorScheme ) ) {
            switch ( newColorScheme ) {
                case WHITE_ON_BLACK:
                    textColor = white;
                    displayArea.setBackground( black );
                    break;
                case BLACK_ON_WHITE:
                    textColor = black;
                    displayArea.setBackground( white );
                    break;
                default:
                    logger.error( "Error: invalid color logic." );
                    break;
            }
            currentColorScheme = newColorScheme;
            assignColorsToCharacters();
        }
    }

    public static void assignColorsToCharacters() {
        int i;
        switch ( currentColorScheme ) {
            case WHITE_ON_BLACK:
                for ( i = 0; i < numberOfPlayers; i++ ) {
                    players[i].setChatColor( brightColors[i] );
                }
                break;
            case BLACK_ON_WHITE:
                for ( i = 0; i < numberOfPlayers; i++ ) {
                    players[i].setChatColor( darkColors[i] );
                }
                break;
            default:
                logger.error( "Error: invalid color logic" );
                break;
        }
    }

    public static void playerHasJoined( int paramInt ) {
        ServerPlayer localServerPlayer = players[paramInt];
        localServerPlayer.setLoggedIn( true );
        localServerPlayer.pmPane.enableInput();
        absoluteChat( "--- " +
                localServerPlayer.toString() +
                " (" +
                localServerPlayer.getRealName() +
                ") has joined ---" );
        if ( showTimeStamps ) {
            displayTimeStamp();
        }
        localServerPlayer.statusPanel.statusLoggedIn( true );
        if ( soundIsOn && soundMenu.joinLeaveMenuItem.isSelected() ) {
            soundPlayer.play( 5 );
        }
    }

    public static synchronized void absoluteChat( String paramString ) {
        try {
            chatDocument.insertString( chatDocument.getLength(), paramString + "\n", systemTextAttributes );
            displayArea.setDocument( chatDocument );
            if ( autoScroll ) {
                displayArea.setCaretPosition( chatDocument.getLength() );
            }
            if ( keepLog ) {
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

    public static void displayTimeStamp() {
        timeStamp = new Date();
        absoluteChat( "(" + timeStamp.toString() + ")" );
    }

    public static void playerHasLeft( int paramInt ) {
        ServerPlayer localServerPlayer = players[paramInt];
        localServerPlayer.setLoggedIn( false );
        localServerPlayer.pmPane.disableInput();
        absoluteChat( "--- " +
                localServerPlayer.toString() +
                " (" +
                localServerPlayer.getRealName() +
                ") has left ---" );
        if ( showTimeStamps ) {
            displayTimeStamp();
        }
        localServerPlayer.statusPanel.statusLoggedIn( false );
        localServerPlayer.setRealName( "???" );
        if ( soundIsOn && soundMenu.joinLeaveMenuItem.isSelected() ) {
            soundPlayer.play( 6 );
        }
    }

    public static void toggleHearObservers() {
        if ( hearObserversMenuItem.isSelected() ) {
            spamString( "097" );
            absoluteChat( "Observers are currently heard." );
        } else {
            spamString( "098" );
            absoluteChat( "Observers are currently NOT heard." );
        }
    }

    public static void toggleAnnounceObservers() {
        if ( announceObserversMenuItem.isSelected() ) {
            JParanoia.announceObservers = true;
            absoluteSpam( "Observers are currently announced." );
        } else {
            JParanoia.announceObservers = false;
            absoluteSpam( "Observers are currently NOT announced." );
        }
    }

    public static void absoluteSpam( String paramString ) {
        spamString( "199" + paramString );
        absoluteChat( paramString );
    }

    public static void setFontBold( boolean paramBoolean ) {
        Boolean localBoolean = paramBoolean;
        textAttributes.addAttribute( StyleConstants.FontConstants.Bold, localBoolean );
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

    public static synchronized void generalChat( String paramString ) {
        int i = Integer.parseInt( paramString.substring( 0, 2 ) );
        paramString = paramString.substring( 2 );
        styleBegin = styleEnd = "";
        if ( i == numberOfPCs ) {
            if ( bigComputerFont ) {
                useComputerFont();
            }
        } else if ( i == 0 ) {
            useGmFont();
        }
        try {
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, players[i].getChatColor() );
            chatDocument.insertString( chatDocument.getLength(), "   " + players[i].toString() + ": ", textAttributes );
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
                            players[i].toString() +
                            ":</span> " +
                            paramString +
                            styleEnd +
                            "<br/>";
                } else {
                    str = players[i].toString() + ": " + paramString;
                }
                log.logEntry( str );
            }
        } catch ( BadLocationException localBadLocationException ) {
            System.err.println( "Unhandled exception. (Bad Location)" );
        }
        if ( i == numberOfPCs && bigComputerFont ) {
            restoreOriginalFont();
        } else if ( i == 0 ) {
            restoreOriginalFont();
        }
        if ( soundIsOn && soundMenu.newTextMenuItem.isSelected() ) {
            soundPlayer.play( 7 );
        }
    }

    public static synchronized void actionChat( String paramString ) {
        int i = Integer.parseInt( paramString.substring( 0, 2 ) );
        paramString = paramString.substring( 2 );
        styleBegin = styleEnd = "";
        if ( i == numberOfPCs && bigComputerFont ) {
            useComputerFont();
        } else if ( i == 0 ) {
            useGmFont();
        }
        try {
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, players[i].getChatColor() );
            chatDocument.insertString( chatDocument.getLength(), "* " +
                    players[i].toString() +
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
                            players[i].toString() +
                            " " +
                            paramString +
                            " *</span>" +
                            styleEnd +
                            "<br/>";
                } else {
                    str = "* " + players[i].toString() + " " + paramString + " *";
                }
                log.logEntry( str );
            }
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, textColor );
        } catch ( BadLocationException localBadLocationException ) {
            System.err.println( "Unhandled exception. (Bad Location)" );
        }
        if ( i == numberOfPCs && bigComputerFont ) {
            restoreOriginalFont();
        } else if ( i == 0 ) {
            restoreOriginalFont();
        }
        if ( soundIsOn && soundMenu.newTextMenuItem.isSelected() ) {
            soundPlayer.play( 7 );
        }
    }

    public static synchronized void speechChat( String paramString ) {
        int i = Integer.parseInt( paramString.substring( 0, 2 ) );
        paramString = paramString.substring( 2 );
        styleBegin = styleEnd = "";
        if ( i == numberOfPCs && bigComputerFont ) {
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
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, players[i].getChatColor() );
            chatDocument.insertString( chatDocument.getLength(), players[i].toString() + " ", textAttributes );
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
                            players[i].toString() +
                            "</span> " +
                            str1 +
                            "\"" +
                            paramString +
                            "\"" +
                            styleEnd +
                            "<br/>";
                } else {
                    str2 = players[i].toString() + " " + str1 + "\"" + paramString + "\"";
                }
                log.logEntry( str2 );
            }
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, textColor );
        } catch ( BadLocationException localBadLocationException ) {
            System.err.println( "Unhandled exception. (Bad Location)" );
        }
        if ( i == numberOfPCs && bigComputerFont ) {
            restoreOriginalFont();
        } else if ( i == 0 ) {
            restoreOriginalFont();
        }
        if ( soundIsOn && soundMenu.newTextMenuItem.isSelected() ) {
            soundPlayer.play( 7 );
        }
    }

    public static synchronized void thoughtChat( String paramString ) {
        int i = Integer.parseInt( paramString.substring( 0, 2 ) );
        paramString = paramString.substring( 2 );
        styleBegin = styleEnd = "";
        if ( i == numberOfPCs && bigComputerFont ) {
            useComputerFont();
        } else if ( i == 0 ) {
            useGmFont();
        }
        try {
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, players[i].getChatColor() );
            chatDocument.insertString( chatDocument.getLength(), players[i].toString() +
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
                            players[i].toString() +
                            " . o O ( " +
                            paramString +
                            " )</span>" +
                            styleEnd +
                            "<br/>";
                } else {
                    str = players[i].toString() + " . o O ( " + paramString + " )";
                }
                log.logEntry( str );
            }
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, textColor );
        } catch ( BadLocationException localBadLocationException ) {
            System.err.println( "Unhandled exception. (Bad Location)" );
        }
        if ( i == numberOfPCs && bigComputerFont ) {
            restoreOriginalFont();
        } else if ( i == 0 ) {
            restoreOriginalFont();
        }
        if ( soundIsOn && soundMenu.newTextMenuItem.isSelected() ) {
            soundPlayer.play( 7 );
        }
    }

    public static synchronized void observerChat( String paramString ) {
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

    public static void privateMessageHandler( String paramString, boolean paramBoolean ) {
        int i = Integer.parseInt( paramString.substring( 0, 2 ) );
        pmTargetPlayer = players[i];
        if ( pmTargetPlayer == myPlayer ) {
            if ( paramBoolean && soundIsOn && soundMenu.newPMAlertMenuItem.isSelected() ) {
                soundPlayer.play( 19 );
            }
            int j = Integer.parseInt( paramString.substring( 2, 4 ) );
            paramString = paramString.substring( 4 );
            players[j].statusPanel.statusNewMessage( true );
            String str1 = players[j].getName().substring( 0, players[j].getName().indexOf( "-" ) );
            try {
                PrivateMessagePane.pmAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, players[j].getChatColor() );
                PMPane[j].privateMessageDocument.insertString( PMPane[j].privateMessageDocument.getLength(), "  " +
                        str1 +
                        ": ", PrivateMessagePane.pmAttributes );
                PrivateMessagePane.pmAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, textColor );
                PMPane[j].privateMessageDocument.insertString( PMPane[j].privateMessageDocument.getLength(), paramString +
                        "\n", PrivateMessagePane.pmAttributes );
                PMPane[j].displayArea.setDocument( PMPane[j].privateMessageDocument );
                if ( autoScroll ) {
                    PMPane[j].displayArea.setCaretPosition( PMPane[j].privateMessageDocument.getLength() );
                }
                if ( keepLog ) {
                    String str2;
                    if ( htmlLog ) {
                        str2 = "<span class=\"pm\"><span class=\"player" +
                                j +
                                "\">(" +
                                players[j].toString() +
                                "</span> -> <span class=\"player0\">" +
                                myPlayer.toString() +
                                ")</span>: " +
                                paramString +
                                "</span><br/>";
                    } else {
                        str2 = "(" + players[j].toString() + " -> " + myPlayer.toString() + "): " + paramString;
                    }
                    log.logEntry( str2 );
                }
            } catch ( BadLocationException localBadLocationException ) {
                System.err.println( "Unhandled exception. (Bad Location)" );
            }
        } else {
            pmTargetPlayer.specificSend( "200" + paramString );
        }
    }

    public static void sendChat( String paramString ) {
        String str1 = paramString;
        str1 = str1.replace( '\n', ' ' );
        if ( !str1.equals( "" ) && !str1.equals( " " ) && !str1.endsWith( "\n" ) ) {
            if ( ( str1.startsWith( "/" ) || str1.startsWith( "'" ) ) &&
                    !spoofCheckBox.isSelected() &&
                    !allowGMEmotes ) {
                JParanoia.warningMessage( "GM Emotes not allowed", "You have attempted to use the speech or action\nkey while not spoofing a character.\n\nTo permit this, go to the Options menu and\nenable \"Allow GM Emotes\"." );
                spoofCheckBox.requestFocus();
                inputLine.setText( inputLine.getText().substring( 0, inputLine.getText().length() - 1 ) );
            } else {
                String str2;
                if ( str1.startsWith( "/" ) ) {
                    str2 = "110";
                    spamString( str2 + currentPlayerID + str1.substring( 1 ) );
                    actionChat( currentPlayerID + str1.substring( 1 ) );
                } else if ( str1.startsWith( "'" ) ) {
                    str2 = "120";
                    if ( Integer.parseInt( currentPlayerID ) == numberOfPCs && computerAllCaps ) {
                        str1 = str1.toUpperCase();
                    }
                    spamString( str2 + currentPlayerID + str1.substring( 1 ) );
                    speechChat( currentPlayerID + str1.substring( 1 ) );
                } else if ( str1.startsWith( "\\" ) ) {
                    str2 = "130";
                    spamString( str2 + currentPlayerID + str1.substring( 1 ) );
                    thoughtChat( currentPlayerID + str1.substring( 1 ) );
                } else {
                    str2 = "100";
                    spamString( str2 + currentPlayerID + str1 );
                    generalChat( currentPlayerID + str1 );
                }
                if ( spoofCheckBox.isSelected() && optionsMenu.singleUseSpoofMenuItem.isSelected() ) {
                    spoofCheckBox.doClick();
                }
                inputLine.setText( "" );
            }
        }
        if ( str1.endsWith( "\n" ) || str1.equals( "" ) ) {
            inputLine.setText( "" );
        }
    }

    static String nameCompletion( String paramString, boolean paramBoolean ) {
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
                lastNameCompleted = ( (ServerPlayer) sortedNames.get( lastCompletionPlayer ) ).getName();
                return str2 + lastNameCompleted;
            }
            lastNameCompleted = ( (ServerPlayer) sortedNames.get( ++lastCompletionPlayer ) ).getName();
            return str2 + lastNameCompleted;
        }
        for ( int i = 0;
              i < sortedNames.size() &&
                      str1.compareToIgnoreCase( ( (ServerPlayer) sortedNames.get( i ) ).getName() ) > 0;
              i++ ) {
            if ( i < sortedNames.size() ) {
                lastCompletionPlayer = i;
            } else {
                lastCompletionPlayer = sortedNames.size() - 1;
            }
            lastNameCompleted = ( (ServerPlayer) sortedNames.get( lastCompletionPlayer ) ).getName();
        }
        return str2 + lastNameCompleted;
    }

    public static void clearInputLine() {
        inputLine.selectAll();
        inputLine.replaceSelection( "" );
    }

    public static void setSendMainText( boolean paramBoolean ) {
        sendMainText = paramBoolean;
    }

    public static void globalPM() {
        spamString( "210999" );
        String str = (String) JOptionPane.showInputDialog( null, "Enter your global private message:", "Global PM", JOptionPane.PLAIN_MESSAGE, null, null, null );
        if ( str != null && !str.equals( "" ) ) {
            for ( int i = 1; i < numberOfPCs; i++ ) {
                if ( players[i].isLoggedIn() ) {
                    players[i].specificSend( "200" + players[i].getID() + myPlayer.getID() + str );
                    PMPane[i].addMyMessage( str );
                }
            }
        }
        spamString( "211" );
    }

    private static void startCombat() {
        if ( chatThreads.size() == 0 ) {
            absoluteChat( "Kind of hard to have combat without combatants." );
        } else {
            try {
                combatFrame = new CombatFrame();
                combatFrame.setVisible( true );
                freezeButton.setEnabled( false );
                combatButton.setEnabled( false );
                if ( soundIsOn && soundMenu.combatAlertMenuItem.isSelected() ) {
                    soundPlayer.play( 21 );
                }
                if ( soundIsOn && soundMenu.combatMusicMenuItem.isSelected() ) {
                    soundPlayer.startCombatMusic();
                    combatMusicIsPlaying = true;
                }
                spamString( "199*** COMBAT ROUND BEGINS ***" );
                absoluteChat( "*** COMBAT ROUND BEGINS ***" );
                spamString( "599" );
                freezePlayers();
            } catch ( NoClassDefFoundError localNoClassDefFoundError ) {
                errLog = new ErrorLogger( "cmbt", localNoClassDefFoundError.toString() +
                        " in JPServer.startCombat()" );
                localNoClassDefFoundError.printStackTrace( errLog.out );
                errLog.closeLog();
                errLog = null;
                errorMessage( "CombatFrame - class definition not found", "The CombatFrame class failed to load. The combat manager\nis not available. An error log has been created in your logs\ndirectory. Please notify the author. You will need to exit\nand relaunch the server to correct this problem." );
            }
        }
    }

    public static void freezePlayers() {
        frozen = true;
        freezeButton.setText( "Unfreeze" );
        spamString( "052" );
        for ( final ServerPlayer troubleshooter : troubleshooters ) {
            troubleshooter.statusPanel.freeze();
        }
        if ( soundIsOn && soundMenu.freezeUnfreezeMenuItem.isSelected() ) {
            soundPlayer.play( 10 );
        }
    }

    public static void notifyPlayersOfDeath( ServerPlayer paramServerPlayer ) {
        spamString( "060" + paramServerPlayer.getID() );
        if ( soundIsOn && soundMenu.deathAlertMenuItem.isSelected() ) {
            soundPlayer.play( 14 );
        }
    }

    public static void notifyPlayersOfUndeath( ServerPlayer paramServerPlayer ) {
        spamString( "061" + paramServerPlayer.getID() );
    }

    public static void sendingPrivateMessage( ServerPlayer paramServerPlayer ) {
        spamString( "210" + paramServerPlayer.getID() );
    }

    public static void setTitleMessage( String paramString ) {
        myTitle.setExtra( paramString );
        frame.setTitle( myTitle.get() );
        spamString( "013" + paramString );
    }

    public static void clearTitleMessage() {
        myTitle.clearExtra();
        frame.setTitle( myTitle.get() );
        spamString( "013" );
    }

    public static void setAnnouncement() {
        new JOptionPane();
        announcement = (String) JOptionPane.showInputDialog( null, "Enter announcement:", "Set Announcement...", JOptionPane.PLAIN_MESSAGE, null, null, announcement );
    }

    public static String getAnnouncement() {
        StringBuilder str1 = new StringBuilder();
        try ( BufferedReader localBufferedReader = new BufferedReader( new FileReader( "conf/announcement.txt" ) ) ) {
            String str2 = null;
            while ( ( str2 = localBufferedReader.readLine() ) != null ) {
                str1.append( "199" ).append( str2 ).append( "\n" );
            }
        } catch ( FileNotFoundException localFileNotFoundException ) {
            logger.info( "FileNotFoundException: Can not locate announcement.txt" );
        } catch ( Exception localException ) {
            localException.printStackTrace();
        }
        return str1.toString();
    }

    public static void clearAnnouncement() {
        announcement = "";
    }

    public static void mute( String paramString ) {
        spamString( "051" + paramString );
        if ( soundIsOn && soundMenu.mutedUnmutedMenuItem.isSelected() ) {
            soundPlayer.play( 8 );
        }
    }

    public static void unmute( String paramString ) {
        spamString( "050" + paramString );
        if ( soundIsOn && soundMenu.mutedUnmutedMenuItem.isSelected() ) {
            soundPlayer.play( 9 );
        }
    }

    public static void unfreezePlayers() {
        frozen = false;
        freezeButton.setText( "Freeze" );
        spamString( "053" );
        for ( final ServerPlayer troubleshooter : troubleshooters ) {
            troubleshooter.statusPanel.unfreeze();
        }
        if ( soundIsOn && soundMenu.freezeUnfreezeMenuItem.isSelected() ) {
            soundPlayer.play( 11 );
        }
    }

    public static String stripComments( String paramString ) {
        StringBuilder localStringBuffer = new StringBuilder( paramString );
        for ( int j = 0; j < localStringBuffer.length(); j++ ) {
            int i;
            if ( localStringBuffer.charAt( j ) == '/' && localStringBuffer.charAt( j + 1 ) == '*' ) {
                i = j + 2;
                while ( i < localStringBuffer.length() &&
                        ( localStringBuffer.charAt( i ) != '*' || localStringBuffer.charAt( i + 1 ) != '/' ) ) {
                    i++;
                }
                localStringBuffer.delete( j, i + 2 );
            } else if ( localStringBuffer.charAt( j ) == '/' && localStringBuffer.charAt( j + 1 ) == '/' ) {
                i = j + 2;
                while ( i < localStringBuffer.length() && localStringBuffer.charAt( i ) != '\n' ) {
                    i++;
                }
                localStringBuffer.delete( j, i );
            }
        }
        return localStringBuffer.toString();
    }

    public static void observerHasJoined( String paramString ) {
        absoluteSpam( "Observer " + paramString + " has joined." );
    }

    public static void observerHasLeft( String paramString ) {
        absoluteSpam( "Observer " + paramString + " has disconnected." );
    }

    public static void repaintMenus() {
        spoofComboBox.repaint();
        charsheetPanel.playerComboBox.repaint();
    }

    public static void reassignThreadNumbers() {
        for ( int i = 0; i < chatThreads.size(); i++ ) {
            thisThread = chatThreads.elementAt( i );
            thisThread.threadNumber = i;
        }
    }

    public static void startServer() {
        if ( registerGame && gameDescription.equals( defaultGameDescription ) ) {
            setGameDescriptionMenuItem.doClick();
        }
        inputLine.setEnabled( true );
        inputLine.requestFocus();
        servSocketThread = new ServerSocketThread();
        servSocketThread.start();
        if ( registerGame ) {
            GameRegistrar.addGame( gameDescription );
            String str = GameRegistrar.getIP();
            if ( !str.equals( "fail" ) ) {
                ipLabel.setText( "  IP: " + str );
                if ( !behindRouter && !str.equals( localIP.getHostAddress() ) ) {
                    behindRouter = true;
                    JParanoia.warningMessage( "Behind a router",
                            "The JParanoia Game Registry has determined\nthat your computer is behind a router.\n\nPlayers should be able to connect without\nany problems if you have forwarded port 11777\nto your local IP. (" +
                                    localIP.getHostAddress() +
                                    ")\n" +
                                    "\n" +
                                    "Consult the included README for details on\n" +
                                    "running a server from behind a router.\n" +
                                    "\n" +
                                    "This notice will only appear once each time\n" +
                                    "you run this program. To disable this reminder\n" +
                                    "completely, edit the jpConfig.ini file such that\n" +
                                    "bBehindRouter=true" );
                }
            }
        }
        if ( soundIsOn && soundMenu.connectedDisconnectedMenuItem.isSelected() ) {
            soundPlayer.play( 1 );
        }
    }

    public static void stopServer() {
        try {
            serverRunning = false;
            servSocketThread.listening = false;
            servSocketThread.serverSocket.close();
            if ( gameRegistered ) {
                GameRegistrar.removeGame();
            }
            if ( soundIsOn && soundMenu.connectedDisconnectedMenuItem.isSelected() ) {
                soundPlayer.play( 2 );
            }
        } catch ( SocketException localSocketException ) {
            logger.info( "Socket Exception while closing serversocket" );
            localSocketException.printStackTrace();
        } catch ( IOException localIOException ) {
            logger.info( "I/O Exception while closing serversocket" );
            localIOException.printStackTrace();
        }
    }

    public static String getVersionName() {
        return VERSION_NAME;
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\JPServer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */
