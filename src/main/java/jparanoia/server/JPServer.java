package jparanoia.server;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandles;
import static java.lang.invoke.MethodHandles.lookup;
import java.util.Objects;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import jparanoia.shared.JParanoia;

public class JPServer extends JParanoia {
       public static final jparanoia.shared.JPVersionNumber VERSION_NUMBER = new jparanoia.shared.JPVersionNumber( 1, 31, 1 );
       public static final String VERSION_NAME = VERSION_NUMBER.toString();
       public static final jparanoia.shared.JPVersionNumber MIN_COMPATIBLE_VERSION_NUMBER = new jparanoia.shared.JPVersionNumber( 1, 31, 0 );
    static final int IDEAL_WIDTH = 770;
    static final int IDEAL_HEIGHT = 540;
    static final String MY_PLAYER_ID = "00";
    static final int MY_PLAYER_NUMBER = 0;
       public static boolean keepLog = false;
       public static boolean htmlLog = true;
       public static boolean registerGame = false;
       public static boolean gameRegistered = false;
       static java.net.Socket someSock = null;
    static java.io.PrintWriter someWriter;
    static java.io.OutputStream someOuputStream;
    static ServerSocketThread servSocketThread;
    static ServerChatThread thisThread;
       static Vector chatThreads = new Vector();
       static int numberOfConnectedClients = 0;
       static int numberOfConnectedObservers = 0;
       static ThreadGroup chatThreadGroup = new ThreadGroup( "my group of chat threads" );
    static ServerPlayer[] players;
    static ServerPlayer myPlayer;
       static int numberOfPlayers = 0;
       static int numberOfPCs = 0;
    static int maxNumClones;
    static int computerFontIncrease;
       static Integer mainFontSize = new Integer( 99 );
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
       static Vector spareNpcs = new Vector( 10 );
    static ServerPlayer playerToSpoof;
    static ServerPlayer pmTargetPlayer;
    static javax.swing.JScrollPane inputScrollPane;
       static String titleMessage = "";
       static String announcement = "";
    static String styleBegin;
       static String styleEnd = "";
       static jparanoia.shared.TitleClass myTitle = new jparanoia.shared.TitleClass( "JParanoia Server", VERSION_NAME, false );
    static java.awt.Container contentPane;
    static JPanel inputPanel;
    static JPanel PMContainer;
    static JPanel mainPanel;
    static JPanel freezePanel;
    static JPanel spoofPanel;
    static JPanel spoofAndFreezePanel;
    static javax.swing.JOptionPane connectOPane;
    static javax.swing.JOptionPane errorPane;
    static javax.swing.JMenuBar menuBar;
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
    static javax.swing.JRadioButtonMenuItem whiteOnBlackButton;
       static javax.swing.JRadioButtonMenuItem blackOnWhiteButton;
    static javax.swing.JRadioButtonMenuItem serifButton;
    static javax.swing.JRadioButtonMenuItem sansSerifButton;
    static javax.swing.JRadioButtonMenuItem monospacedButton;
    static javax.swing.JRadioButtonMenuItem size10Button;
    static javax.swing.JRadioButtonMenuItem size12Button;
    static javax.swing.JRadioButtonMenuItem size14Button;
    static javax.swing.JRadioButtonMenuItem size16Button;
    static javax.swing.JRadioButtonMenuItem size18Button;
    static javax.swing.JRadioButtonMenuItem size24Button;
    static JCheckBoxMenuItem autoScrollMenuItem;
    static JCheckBoxMenuItem showTimeStampsMenuItem;
    static JCheckBoxMenuItem fontBoldMenuItem;
    static JCheckBoxMenuItem registerGameMenuItem;
    static JCheckBoxMenuItem hearObserversMenuItem;
    static javax.swing.JCheckBox spoofCheckBox;
    static javax.swing.JComboBox spoofComboBox;
    static JButton freezeButton;
    static JButton unfreezeButton;
    static JButton combatButton;
    static javax.swing.JLabel ipLabel;
    static javax.swing.JScrollPane scrollPane;
    static javax.swing.JSplitPane splitPane;
    static javax.swing.JTable lipTable;
    static JTextArea inputLine;
    static CombatFrame combatFrame;
    static CharsheetPanel charsheetPanel;
    static ImageDataParser idp;
    static java.awt.Dimension lipTablePreferredSize = new java.awt.Dimension( 130, 160 );
    static javax.swing.text.PlainDocument inputDocument;
    static SimpleAttributeSet spaceAttributes;
       static SimpleAttributeSet systemTextAttributes = new SimpleAttributeSet();
    static Color[] brightColors;
    static Color[] darkColors;
    static Color newColor;
    static java.util.Date timeStamp;
       static java.util.Random rand = new java.util.Random();
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
       static String newColorScheme = "White on Black";
       static boolean serverRunning = false;
       static String addressToTry = null;
       static java.net.InetAddress localIP = null;
    static PrivateMessagePane[] PMPane;
    static PMAndStatusPanel[] pmstatus;
    static KillMenuItem[] killMenuItemArray;
    static UnkillMenuItem[] unkillMenuItemArray;
    static RenamePlayerMenuItem[] renameMenuItemArray;
    static KickMenuItem[] kickMenuItemArray;
       static java.awt.Font normalFont = new java.awt.Font( null, 0, 12 );
       static java.awt.Font spoofFont = new java.awt.Font( null, 1, 16 );
    java.awt.Component componentHolder;

    public JPServer() {

        clobberAqua = ( (Boolean) prefs.getPref( 18 ) ).booleanValue();






        if ( clobberAqua ) {
            try {

                javax.swing.UIManager.setLookAndFeel( javax.swing.UIManager.getCrossPlatformLookAndFeelClassName() );
            } catch ( Exception localException ) {

                System.out.println( "Exception while setting L&F." );
            }
        }

        JParanoia.appInfo = "JParanoia Server " + VERSION_NAME;


        allowObservers = ( (Boolean) prefs.getPref( 28 ) ).booleanValue();

        registerGame = ( (Boolean) prefs.getPref( 31 ) ).booleanValue();

        behindRouter = ( (Boolean) prefs.getPref( 32 ) ).booleanValue();

        isPXPGame = ( (Boolean) prefs.getPref( 34 ) ).booleanValue();

        if ( isPXPGame ) {
            maxNumClones = 999;
        } else
             {
            maxNumClones = ( (Integer) prefs.getPref( 23 ) ).intValue();
        }

        gmNameNag = ( (Boolean) prefs.getPref( 35 ) ).booleanValue();






        computerFontIncrease = ( (Integer) prefs.getPref( 26 ) ).intValue();


        frame.setTitle( myTitle.get() );




        frame.setIconImage( java.awt.Toolkit.getDefaultToolkit()
                .getImage( lookup().lookupClass().getClassLoader().getResource( "graphics/jparanoiaIcon.jpg" ) ) );




        frame.setDefaultCloseOperation( 0 );




        frame.addWindowListener( new java.awt.event.WindowAdapter() {
            public void windowClosing( java.awt.event.WindowEvent paramAnonymousWindowEvent ) {
            }
        } );
        try {

            localIP = java.net.InetAddress.getLocalHost();
        } catch ( java.net.UnknownHostException localUnknownHostException ) {

            System.out.println( "Error: Unable to get local host address" );
        }


        charsheetAttributes = new SimpleAttributeSet();

        charsheetAttributes.addAttribute( javax.swing.text.StyleConstants.Bold, new Boolean( true ) );

        charsheetAttributes.addAttribute( StyleConstants.CharacterConstants.Family, "SansSerif" );

        charsheetAttributes.addAttribute( StyleConstants.CharacterConstants.Size, new Integer( 12 ) );


        DataParser localDataParser = new DataParser();


        players = localDataParser.parsePlayerList( "playerData/playerList.txt" );


        System.out.println( "Processing imageData.txt:" );


        idp = new ImageDataParser();

        idp.parseImageURLs( "imageData.txt" );



        numberOfPlayers = players.length;

        for ( int i = 0; i < players.length; i++ ) {
            if ( players[i].isAnActualPlayer() ) {
                numberOfPCs += 1;
            }
        }

        troubleshooters = new ServerPlayer[numberOfPCs - 1];

        for ( int i = 1; i < numberOfPCs; i++ ) {
            troubleshooters[i - 1] = players[i];
        }

        for ( int i = 0; i < troubleshooters.length; i++ ) {

            sortedNames.add( troubleshooters[i] );


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


        frame.setSize( 770, 540 );


        displayArea = new JTextPane();

        displayArea.setEditable( false );

        displayArea.setEnabled( true );


        displayArea.setBackground( Color.black );





        scrollPane = new javax.swing.JScrollPane( displayArea, 22, 31 );



        chatDocument = new javax.swing.text.DefaultStyledDocument();


        brightColors = new jparanoia.shared.BrightColorArray().getColors();


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
                    System.out.println( "Error: Out of colors! (Try using White on Black scheme)" );
            }


            darkColors[i] = newColor;
        }


        textAttributes = new SimpleAttributeSet();

        textAttributes.addAttribute( javax.swing.text.StyleConstants.FontConstants.Foreground, Color.white );

        textAttributes.addAttribute( javax.swing.text.StyleConstants.FontConstants.Size, prefs.getPref( 15 ) );

        textAttributes.addAttribute( javax.swing.text.StyleConstants.FontConstants.Family, prefs.getPref( 16 ) );


        systemTextAttributes.addAttribute( javax.swing.text.StyleConstants.FontConstants.Foreground, Color.gray );


        setColorScheme();


        inputDocument = new javax.swing.text.PlainDocument();


        inputLine = new JTextArea( 3, 44 );

        inputLine.setLineWrap( true );

        inputLine.setWrapStyleWord( true );

        inputLine.addKeyListener( new java.awt.event.KeyListener() {
            public void keyTyped( java.awt.event.KeyEvent paramAnonymousKeyEvent ) {
            }

            public void keyPressed( java.awt.event.KeyEvent paramAnonymousKeyEvent ) {

                JParanoia.previousKey = JParanoia.thisKey;


                JParanoia.thisKey = paramAnonymousKeyEvent.getKeyCode();
                String str;

                if ( JParanoia.thisKey == 10 && JPServer.sendMainText ) {

                    str = JPServer.inputLine.getText();

                    if ( str.length() > 0 ) {

                        JPServer.inputLine.setCaretPosition( str.length() );
                    }
                }


                if ( JParanoia.thisKey == 9 ) {

                    paramAnonymousKeyEvent.consume();


                    if ( JPServer.inputLine.getText().length() > 0 ) {

                        str = JPServer.inputLine.getText();

                        if ( str.startsWith( "'" ) && str.length() > 1 ) {

                            JPServer.inputLine.setText( "'" +
                                    JPServer.nameCompletion( str.substring( 1 ), JParanoia.thisKey ==
                                            JParanoia.previousKey ) );

                        } else if ( !str.startsWith( "'" ) ) {

                            JPServer.inputLine.setText( JPServer.nameCompletion( str, JParanoia.thisKey ==
                                    JParanoia.previousKey ) );
                        }
                    }
                }
            }

            public void keyReleased( java.awt.event.KeyEvent paramAnonymousKeyEvent ) {

                if ( paramAnonymousKeyEvent.getKeyCode() == 10 && JPServer.sendMainText ) {

                    String str = JPServer.inputLine.getText();

                    if ( str.length() > 0 ) {
                        JPServer.sendChat( str.substring( 0, str.length() - 1 ) );
                    }
                }

                else if ( paramAnonymousKeyEvent.getKeyCode() == 10 && !JPServer.sendMainText ) {
                    JPServer.setSendMainText( true );
                }

            }
        } );

        inputLine.setEnabled( false );

        inputLine.setFont( normalFont );


        inputScrollPane = new javax.swing.JScrollPane( inputLine, 22, 31 );



        ServerPlayer[] arrayOfServerPlayer = new ServerPlayer[players.length - 1];


        for ( int j = 1; j < players.length; j++ ) {

            arrayOfServerPlayer[j - 1] = players[j];
        }


        spoofComboBox = new javax.swing.JComboBox( arrayOfServerPlayer );


        spoofComboBox.addActionListener(paramAnonymousActionEvent -> {

            JPServer.playerToSpoof = (ServerPlayer) JPServer.spoofComboBox.getSelectedItem();

            if ( JPServer.spoofCheckBox.isSelected() ) {
                JPServer.currentPlayerID = JPServer.playerToSpoof.getID();
            }

            JPServer.inputLine.requestFocus();
        });

        playerToSpoof = (ServerPlayer) spoofComboBox.getSelectedItem();


        spoofComboBox.setMaximumSize( new java.awt.Dimension( 130, 20 ) );

        spoofComboBox.setPreferredSize( new java.awt.Dimension( 130, 20 ) );

        spoofComboBox.setMinimumSize( new java.awt.Dimension( 130, 20 ) );


        spoofCheckBox = new javax.swing.JCheckBox();


        spoofCheckBox.addActionListener(paramAnonymousActionEvent -> {

            if ( JPServer.spoofCheckBox.isSelected() ) {

                JPServer.currentPlayerID = JPServer.playerToSpoof.getID();

                JPServer.inputLine.setFont( JPServer.spoofFont );
            } else {

                JPServer.currentPlayerID = "00";

                JPServer.inputLine.setFont( JPServer.normalFont );
            }

            JPServer.inputLine.requestFocus();
        });

        spoofPanel = new JPanel();

        spoofPanel.setLayout( new javax.swing.BoxLayout( spoofPanel, 0 ) );

        spoofPanel.add( javax.swing.Box.createRigidArea( new java.awt.Dimension( 2, 0 ) ) );

        spoofPanel.add( spoofComboBox );

        spoofPanel.add( javax.swing.Box.createRigidArea( new java.awt.Dimension( 5, 0 ) ) );

        spoofPanel.add( spoofCheckBox );

        spoofPanel.add( javax.swing.Box.createRigidArea( new java.awt.Dimension( 2, 0 ) ) );


        spoofPanel.setBorder( javax.swing.BorderFactory.createTitledBorder( "Spoof" ) );


        freezePanel = new JPanel();

        freezePanel.setLayout( new java.awt.GridLayout( 1, 2 ) );


        freezeButton = new JButton( "Freeze" );


        freezeButton.addActionListener(paramAnonymousActionEvent -> {

            if ( !JPServer.frozen ) {

                JPServer.freezePlayers();
            } else {

                JPServer.unfreezePlayers();
            }
        });

        combatButton = new JButton( "Combat!" );


        combatButton.addActionListener(paramAnonymousActionEvent -> {
        });

        combatButton.setEnabled( false );



        freezePanel.add( combatButton );

        freezePanel.add( freezeButton );



        spoofAndFreezePanel = new JPanel();

        spoofAndFreezePanel.setLayout( new javax.swing.BoxLayout( spoofAndFreezePanel, 1 ) );


        spoofAndFreezePanel.add( spoofPanel );

        spoofAndFreezePanel.add( javax.swing.Box.createRigidArea( new java.awt.Dimension( 0, 5 ) ) );

        spoofAndFreezePanel.add( freezePanel );





        inputPanel = new JPanel();


        inputPanel.setLayout( new javax.swing.BoxLayout( inputPanel, 0 ) );


        inputPanel.add( spoofAndFreezePanel );


        inputPanel.add( javax.swing.Box.createRigidArea( new java.awt.Dimension( 5, 0 ) ) );


        inputPanel.add( inputScrollPane );


        charsheetPanel = new CharsheetPanel();



        menuBar = new javax.swing.JMenuBar();

        frame.setJMenuBar( menuBar );


        serverMenu = new JMenu( "Server" );

        startServerMenuItem = new JMenuItem( "Start server" );

        startServerMenuItem.setToolTipText( "Opens your computer for new connections." );




        startServerMenuItem.addActionListener(paramAnonymousActionEvent -> startServer());

        stopServerMenuItem = new JMenuItem( "Stop server" );

        stopServerMenuItem.setToolTipText( "Stops your computer from accepting new connections." );




        stopServerMenuItem.addActionListener(paramAnonymousActionEvent -> stopServer());

        registerGameMenuItem = new JCheckBoxMenuItem( "Register Game" );

        registerGameMenuItem.setToolTipText( "<HTML>When checked, your server will be made available<BR>to players via the JParanoia Game Registry so they<BR>will not need the IP address of your server.</HTML>" );





        registerGameMenuItem.setSelected( prefs.getPref( 31 ).equals( new Boolean( true ) ) );


        registerGameMenuItem.addActionListener(paramAnonymousActionEvent -> {

            if ( JPServer.registerGame ) {

                JPServer.registerGame = false;

                if ( JPServer.gameRegistered ) {
                    jparanoia.shared.GameRegistrar.removeGame();
                }
            } else {

                JPServer.registerGame = true;

                if ( JPServer.serverRunning ) {

                    if ( JPServer.gameDescription.equals( JPServer.defaultGameDescription ) )
                         {
                        JPServer.setGameDescriptionMenuItem.doClick();
                    }

                    jparanoia.shared.GameRegistrar.addGame( JPServer.gameDescription );
                }
            }
        });

        setGameDescriptionMenuItem = new JMenuItem( "Set Game Description..." );

        setGameDescriptionMenuItem.setToolTipText( "<HTML>This changes the name of your game<BR> on the JParanoia Game Registry.</HTML>" );





        setGameDescriptionMenuItem.addActionListener(paramAnonymousActionEvent -> {

            String str = (String) javax.swing.JOptionPane.showInputDialog( null, "Enter a description for your game:", "Set Game Description...", -1, null, null, JPServer.gameDescription );



            if ( str != null && !str.equals( "" ) && !str.equals( JPServer.gameDescription ) ) {

                JPServer.gameDescription = str;

                if ( JPServer.gameRegistered ) {
                    jparanoia.shared.GameRegistrar.addGame( JPServer.gameDescription );
                }
            }
        });

        serverMenu.add( startServerMenuItem );

        serverMenu.add( stopServerMenuItem );


        serverMenu.addSeparator();


        serverMenu.add( registerGameMenuItem );

        serverMenu.add( setGameDescriptionMenuItem );


        stopServerMenuItem.setEnabled( false );


        serverMenu.addSeparator();


        ipLabel = new javax.swing.JLabel( "  Local IP :   " + localIP.getHostAddress() );


        serverMenu.add( ipLabel );


        menuBar.add( serverMenu );


        fontMenu = new FontMenu( "Font" );


        menuBar.add( fontMenu );


        optionsMenu = new ServerOptionsMenu( "Options" );


        menuBar.add( optionsMenu );


        playerMenu = new JMenu( "Player" );



        for ( int j = 0; j < troubleshooters.length; j++ ) {

            playerMenu.add( troubleshooters[j].getPlayerMenu() );
        }

        menuBar.add( playerMenu );


        npcMenu = new JMenu( "Spare NPCs" );











        for ( int j = 0; j < spareNpcs.size(); j++ ) {

            npcMenu.add( ( (ServerPlayer) spareNpcs.get( j ) ).getNpcMenu() );
        }

        menuBar.add( npcMenu );


        globalPMMenu = new JMenu( "Global PM" );


        sendGlobalPMMenuItem = new JMenuItem( "Send Global PM..." );


        sendGlobalPMMenuItem.addActionListener(paramAnonymousActionEvent -> {

            new GlobalPMDialog().setVisible( true );
        });

        globalPMMenu.add( sendGlobalPMMenuItem );


        menuBar.add( globalPMMenu );


        sendImageMenu = new ServerImageMenu();


        menuBar.add( sendImageMenu );


        observersMenu = new JMenu( "Observers" );


        hearObserversMenuItem = new JCheckBoxMenuItem( "Hear Observers" );

        hearObserversMenuItem.setSelected( ( (Boolean) prefs.getPref( 29 ) ).booleanValue() );


        hearObserversMenuItem.addActionListener(paramAnonymousActionEvent -> {
        });

        announceObserversMenuItem = new JCheckBoxMenuItem( "Announce Observers" );

        announceObserversMenuItem.setSelected( ( (Boolean) prefs.getPref( 30 ) ).booleanValue() );


        announceObserversMenuItem.addActionListener(paramAnonymousActionEvent -> {
        });

        showObserversListMenuItem = new JMenuItem( "Show Observers List" );


        showObserversListMenuItem.addActionListener(paramAnonymousActionEvent -> JParanoia.obsFrame.setVisible( true ));

        observersMenu.add( hearObserversMenuItem );

        observersMenu.add( announceObserversMenuItem );

        observersMenu.add( showObserversListMenuItem );


        menuBar.add( observersMenu );


        java.awt.Container localContainer = frame.getContentPane();



        mainPanel = new JPanel();


        java.awt.GridBagLayout localGridBagLayout1 = new java.awt.GridBagLayout();

        java.awt.GridBagConstraints localGridBagConstraints1 = new java.awt.GridBagConstraints();


        splitPane = new javax.swing.JSplitPane( 0, true, charsheetPanel, scrollPane );



        splitPane.setDividerLocation( 122 );

        splitPane.setOneTouchExpandable( true );





        localGridBagConstraints1.gridx = 0;

        localGridBagConstraints1.gridy = 0;

        localGridBagConstraints1.weighty = 1.0D;

        localGridBagConstraints1.weightx = 1.0D;

        localGridBagConstraints1.fill = 1;

        localGridBagConstraints1.anchor = 15;

        localGridBagConstraints1.insets = new java.awt.Insets( 2, 2, 2, 2 );


        localGridBagLayout1.setConstraints( splitPane, localGridBagConstraints1 );


        PMPane = new PrivateMessagePane[numberOfPCs];


        for ( int m = 1; m < numberOfPCs; m++ ) {

            PMPane[m] = new PrivateMessagePane( players[m] );
        }


        System.out.println( "\nServer's local IP Address: " + localIP.getHostAddress() + "\n" );


        pmstatus = new PMAndStatusPanel[numberOfPCs];


        System.out.println( "PM & status array initialized." );


        for ( int m = 1; m < numberOfPCs; m++ ) {

            pmstatus[m] = new PMAndStatusPanel( PMPane[m], PMPane[m].statusPanel );
        }


        System.out.println( "PM & status panels created." );


        PMContainer = new JPanel();


        PMContainer.setLayout( new javax.swing.BoxLayout( PMContainer, 1 ) );


        for ( int m = 1; m < numberOfPCs; m++ ) {

            PMContainer.add( javax.swing.Box.createRigidArea( new java.awt.Dimension( 0, 2 ) ) );

            PMContainer.add( pmstatus[m] );
        }


        System.out.println( "PM & status pane populated." );


        java.awt.GridBagLayout localGridBagLayout2 = new java.awt.GridBagLayout();

        java.awt.GridBagConstraints localGridBagConstraints2 = new java.awt.GridBagConstraints();


        localContainer.setLayout( localGridBagLayout2 );




        localGridBagConstraints2.gridx = 0;

        localGridBagConstraints2.gridy = 0;

        localGridBagConstraints2.weighty = 1.0D;

        localGridBagConstraints2.weightx = 1.0D;

        localGridBagConstraints2.fill = 1;

        localGridBagConstraints2.anchor = 15;

        localGridBagConstraints2.insets = new java.awt.Insets( 2, 2, 2, 2 );


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



        connectOPane = new javax.swing.JOptionPane();

        errorPane = new javax.swing.JOptionPane();


        myPlayer = players[0];





        displayWrite( Color.green, "JParanoia Server " + VERSION_NAME );



        displayWrite( Color.orange, "      http://www.byronbarry.com/jparanoia/\n" );



        displayWrite( Color.cyan, "New in this release:\n\n" );




        displayWrite( Color.white, "- Quick Charsheet option (see README).\n- The Computer's text is now large in the logs.\n- The GM's text is now bold in the logs.\n- Unplanned images now appear in the logs.\n- Current passwords appear in Player menu.\n- Other miscellaneous bug fixes.\n\nRead the README.TXT for full details.\nFor a complete version history, visit the JParanoia website.\n\n" );












        displayWrite( Color.white, "If you are new to running a JParanoia server, or find yourself wondering how to do something, " );




        displayWrite( Color.yellow, "READ THE README.\n" );



        keepLog = ( (Boolean) prefs.getPref( 20 ) ).booleanValue();

        htmlLog = ( (Boolean) prefs.getPref( 21 ) ).booleanValue();

        if ( keepLog ) {

            if ( htmlLog ) {
                logger = new jparanoia.shared.GameLogger( players );
            } else {

                logger = new jparanoia.shared.GameLogger();
            }
        }


        System.out.println( "JPServer.frame constructed.\n" );




        System.out.print( "Attempting to load CombatFrame class... " );
        try {

            combatFrame = new CombatFrame();

            combatFrame = null;

            System.out.println( "loaded." );
        } catch ( NoClassDefFoundError localNoClassDefFoundError ) {

            System.out.println( "FAILED" );

            errorMessage( "CombatFrame - class definition not found", "The CombatFrame class failed to load. The combat manager\nis not available. Please notify the author. You will need\nto exit and relaunch the server to correct this problem." );
        }







//        net.roydesign.mac.MRJAdapter.addQuitApplicationListener(paramAnonymousActionEvent -> {
//        });


//        net.roydesign.mac.MRJAdapter.addAboutListener(paramAnonymousActionEvent -> {
//
//            JParanoia.aboutBoxMenuItem.doClick();
//        });

        mainFontSize = (Integer) textAttributes.getAttribute( javax.swing.text.StyleConstants.FontConstants.Size );



        if ( isPXPGame ) {
            frame.setSize( 855, frame.getHeight() );
        }

        frame.setVisible( true );
    }

    public static void main( String[] paramArrayOfString ) {

        System.out.println( "\nThis is the JParanoia Server console.\n" );

        System.out.println( "Running under Java Runtime Environment version " + System.getProperty( "java.version" ) );

        System.out.println( "\n" );

        JPServer localJPServer = new JPServer();

        splitPane.repaint();
    }

    public static void exit() {

        if ( numberOfConnectedClients - numberOfConnectedObservers > 0 ) {

            if ( javax.swing.JOptionPane.showConfirmDialog( frame, "WARNING: Players are still connected!\nAre you SURE you want to quit?", "Quit confirmation...", 0, 2 ) ==
                    0 ) {





                spamString( "086SERVER TERMINATED" );
            } else
                 {
                return;
            }
        }

        if ( keepLog ) {

            logger.closeLog();

            if ( htmlLog ) {
                logger.sanitize();
            }
        }


        if ( ServerPlayer.numUnsavedCharsheets > 0 ) {

            if ( javax.swing.JOptionPane.showConfirmDialog( frame, "Some character sheets contain unsaved changes.\nSave before exiting?", "Unsaved Changes", 0, 3 ) ==
                    0 ) {






                soundMenu.charSheetAlertMenuItem.setSelected( false );

                for ( int i = 0; i < troubleshooters.length; i++ ) {

                    if ( troubleshooters[i].hasUnsavedCharsheet() ) {

                        troubleshooters[i].saveCharsheet( false );
                    }
                }
            }
        }

        if ( gameRegistered ) {
            jparanoia.shared.GameRegistrar.removeGame();
        }

        frame.dispose();

        System.exit( 0 );
    }

    public static synchronized void spamString( String paramString ) {

        for ( int i = 0; i < chatThreads.size(); i++ ) {

            thisThread = (ServerChatThread) chatThreads.elementAt( i );


            someWriter = thisThread.out;

            someWriter.println( paramString );
        }
    }

    public static void setColorScheme() {

        if ( !currentColorScheme.equals( newColorScheme ) ) {

            if ( newColorScheme.equals( "White on Black" ) ) {

                textColor = Color.white;

                displayArea.setBackground( Color.black );
            }

            else if ( newColorScheme.equals( "Black on White" ) ) {

                textColor = Color.black;

                displayArea.setBackground( Color.white );
            } else {

                System.out.println( "Error: invalid color logic." );
            }

            currentColorScheme = newColorScheme;

            assignColorsToCharacters();
        }
    }

    public static void assignColorsToCharacters() {
        int i;

        if ( currentColorScheme.equals( "White on Black" ) ) {

            for ( i = 0; i < numberOfPlayers; i++ ) {


                players[i].setChatColor( brightColors[i] );
            }
        }

        if ( currentColorScheme.equals( "Black on White" ) ) {

            for ( i = 0; i < numberOfPlayers; i++ ) {

                players[i].setChatColor( darkColors[i] );
            }
        }

        System.out.println( "Error: invalid color logic" );
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
                } else
                     {
                    str = paramString;
                }

                logger.logEntry( str );
            }
        } catch ( BadLocationException localBadLocationException ) {

            System.err.println( "Unhandled exception. (Bad Location)" );
        }
    }

    public static void displayTimeStamp() {

        timeStamp = new java.util.Date();

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

        Boolean localBoolean = new Boolean( paramBoolean );

        textAttributes.addAttribute( javax.swing.text.StyleConstants.FontConstants.Bold, localBoolean );
    }

    public static void useComputerFont() {

        styleBegin = "<span class=\"computer\">";

        styleEnd = "</span>";

        mainFontSize = (Integer) textAttributes.getAttribute( javax.swing.text.StyleConstants.FontConstants.Size );


        int i = mainFontSize.intValue() + computerFontIncrease;

        textAttributes.addAttribute( javax.swing.text.StyleConstants.FontConstants.Size, new Integer( i ) );

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

        textAttributes.addAttribute( javax.swing.text.StyleConstants.FontConstants.Size, mainFontSize );

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
        }

        else if ( i == 0 ) {
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
                } else
                     {
                    str = players[i].toString() + ": " + paramString;
                }

                logger.logEntry( str );
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
                } else
                     {
                    str = "* " + players[i].toString() + " " + paramString + " *";
                }

                logger.logEntry( str );
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
                } else
                     {
                    str2 = players[i].toString() + " " + str1 + "\"" + paramString + "\"";
                }

                logger.logEntry( str2 );
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
                } else
                     {
                    str = players[i].toString() + " . o O ( " + paramString + " )";
                }

                logger.logEntry( str );
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
                } else
                     {
                    str2 = str1 + ": " + paramString;
                }

                logger.logEntry( str2 );
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
                    } else
                         {
                        str2 = "(" + players[j].toString() + " -> " + myPlayer.toString() + "): " + paramString;
                    }

                    logger.logEntry( str2 );
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
                }

                else if ( str1.startsWith( "'" ) ) {

                    str2 = "120";

                    if ( Integer.parseInt( currentPlayerID ) == numberOfPCs && computerAllCaps ) {
                        str1 = str1.toUpperCase();
                    }

                    spamString( str2 + currentPlayerID + str1.substring( 1 ) );

                    speechChat( currentPlayerID + str1.substring( 1 ) );
                }

                else if ( str1.startsWith( "\\" ) ) {

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

        String str2 = "";


        if ( paramBoolean ) {
            paramString = paramString.substring( 0, paramString.length() - lastNameCompleted.length() + 1 );
        }

        st = new java.util.StringTokenizer( paramString );


        str1 = st.nextToken();


        while ( st.hasMoreTokens() ) {

            str2 = str2 + str1 + " ";

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
            } else
                 {
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


        String str = (String) javax.swing.JOptionPane.showInputDialog( null, "Enter your global private message:", "Global PM", -1, null, null, null );



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

                errLog = new jparanoia.shared.ErrorLogger( "cmbt", localNoClassDefFoundError.toString() +
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

        for ( int i = 0; i < troubleshooters.length; i++ ) {

            troubleshooters[i].statusPanel.freeze();
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

        new javax.swing.JOptionPane();
        announcement = (String) javax.swing.JOptionPane.showInputDialog( null, "Enter announcement:", "Set Announcement...", -1, null, null, announcement );
    }

    public static String getAnnouncement() {

        String str1 = "";
        try {

            final ClassLoader classLoader = MethodHandles.lookup().lookupClass().getClassLoader();
            final File file = new File( Objects.requireNonNull( classLoader.getResource( "conf/announcement.txt" ) )
                    .getFile() );
            BufferedReader localBufferedReader = new BufferedReader( new InputStreamReader(new FileInputStream( file )) );


            String str2 = null;


            while ( ( str2 = localBufferedReader.readLine() ) != null ) {

                str1 = str1 + "199" + str2 + "\n";
            }
        } catch ( java.io.FileNotFoundException localFileNotFoundException ) {

            System.out.println( "FileNotFoundException: Can not locate announcement.txt" );
        } catch ( Exception localException ) {

            localException.printStackTrace();
        }


        return str1;
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

        for ( int i = 0; i < troubleshooters.length; i++ ) {

            troubleshooters[i].statusPanel.unfreeze();
        }

        if ( soundIsOn && soundMenu.freezeUnfreezeMenuItem.isSelected() ) {
            soundPlayer.play( 11 );
        }
    }

    public static String stripComments( String paramString ) {

        StringBuffer localStringBuffer = new StringBuffer( paramString );




        for ( int j = 0; j < localStringBuffer.length(); j++ ) {
            int i;

            if ( localStringBuffer.charAt( j ) == '/' && localStringBuffer.charAt( j + 1 ) == '*' ) {

                i = j + 2;


                while ( i < localStringBuffer.length() &&
                        ( localStringBuffer.charAt( i ) != '*' || localStringBuffer.charAt( i + 1 ) != '/' ) ) {

                    i++;
                }

                localStringBuffer.delete( j, i + 2 );
            }

            else if ( localStringBuffer.charAt( j ) == '/' && localStringBuffer.charAt( j + 1 ) == '/' ) {

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

            thisThread = (ServerChatThread) chatThreads.elementAt( i );

            thisThread.threadNumber = i;
        }
    }

    public static void startServer() {

        if ( registerGame && gameDescription.equals( defaultGameDescription ) )
             {
            setGameDescriptionMenuItem.doClick();
        }

        inputLine.setEnabled( true );

        inputLine.requestFocus();


        servSocketThread = new ServerSocketThread();

        servSocketThread.start();

        if ( registerGame ) {

            jparanoia.shared.GameRegistrar.addGame( gameDescription );

            String str = jparanoia.shared.GameRegistrar.getIP();

            if ( str != null && !str.equals( "fail" ) ) {

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
                jparanoia.shared.GameRegistrar.removeGame();
            }

            if ( soundIsOn && soundMenu.connectedDisconnectedMenuItem.isSelected() ) {
                soundPlayer.play( 2 );
            }
        } catch ( java.net.SocketException localSocketException ) {

            System.out.println( "Socket Exception while closing serversocket" );

            localSocketException.printStackTrace();
        } catch ( java.io.IOException localIOException ) {

            System.out.println( "I/O Exception while closing serversocket" );

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
