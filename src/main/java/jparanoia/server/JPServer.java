package jparanoia.server;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    /*   44 */   public static final jparanoia.shared.JPVersionNumber VERSION_NUMBER = new jparanoia.shared.JPVersionNumber( 1, 31, 1 );
    /*   46 */   public static final String VERSION_NAME = VERSION_NUMBER.toString();
    /*   47 */   public static final jparanoia.shared.JPVersionNumber MIN_COMPATIBLE_VERSION_NUMBER = new jparanoia.shared.JPVersionNumber( 1, 31, 0 );
    static final int IDEAL_WIDTH = 770;
    static final int IDEAL_HEIGHT = 540;
    static final String MY_PLAYER_ID = "00";
    static final int MY_PLAYER_NUMBER = 0;
    /*   61 */   public static boolean keepLog = false;
    /*   62 */   public static boolean htmlLog = true;
    /*   63 */   public static boolean registerGame = false;
    /*   64 */   public static boolean gameRegistered = false;
    /*   28 */   static java.net.Socket someSock = null;
    static java.io.PrintWriter someWriter;
    static java.io.OutputStream someOuputStream;
    static ServerSocketThread servSocketThread;
    static ServerChatThread thisThread;
    /*   34 */   static Vector chatThreads = new Vector();
    /*   35 */   static int numberOfConnectedClients = 0;
    /*   36 */   static int numberOfConnectedObservers = 0;
    /*   49 */   static ThreadGroup chatThreadGroup = new ThreadGroup( "my group of chat threads" );
    static ServerPlayer[] players;
    static ServerPlayer myPlayer;
    /*   52 */   static int numberOfPlayers = 0;
    /*   53 */   static int numberOfPCs = 0;
    static int maxNumClones;
    static int computerFontIncrease;
    /*   56 */   static Integer mainFontSize = new Integer( 99 );
    static SimpleAttributeSet charsheetAttributes;
    /*   59 */   static boolean jvm140 = System.getProperty( "java.version" ).startsWith( "1.4.0" );
    /*   79 */   static boolean bigComputerFont = true;
    static boolean computerAllCaps;
    /*   81 */   static boolean sendMainText = true;
    /*   82 */   static boolean quickNamesToggle = false;
    /*   83 */   static boolean behindRouter = false;
    /*   84 */   static boolean allowGMEmotes = true;
    /*   85 */   static boolean singleUseSpoof = false;
    static boolean isPXPGame;
    static ServerPlayer[] troubleshooters;
    /*   89 */   static Vector spareNpcs = new Vector( 10 );
    static ServerPlayer playerToSpoof;
    static ServerPlayer pmTargetPlayer;
    static javax.swing.JScrollPane inputScrollPane;
    /*   96 */   static String titleMessage = "";
    /*   97 */   static String announcement = "";
    static String styleBegin;
    /*   99 */   static String styleEnd = "";
    /*  101 */   static jparanoia.shared.TitleClass myTitle = new jparanoia.shared.TitleClass( "JParanoia Server", VERSION_NAME, false );
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
    /*  136 */   static javax.swing.JRadioButtonMenuItem blackOnWhiteButton;
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
    /*  145 */   static SimpleAttributeSet systemTextAttributes = new SimpleAttributeSet();
    static Color[] brightColors;
    static Color[] darkColors;
    static Color newColor;
    static java.util.Date timeStamp;
    /*  155 */   static java.util.Random rand = new java.util.Random();
    /*  156 */   static int randInt = rand.nextInt( 1000 );
    /*  158 */   static String defaultGameDescription = "JParanoia " + VERSION_NAME + " (" + randInt + ")";
    /*  160 */   static String gameDescription = defaultGameDescription;
    /*  163 */   static boolean fontIsBold = false;
    /*  164 */   static boolean showTimeStamps = true;
    static boolean allowObservers;
    /*  166 */   static boolean frozen = false;
    static boolean clobberAqua;
    static boolean gmNameNag;
    static String[] nonConnectedPlayers;
    /*  174 */   static String currentPlayerID = "00";
    /*  176 */   static String currentColorScheme = "";
    /*  177 */   static String newColorScheme = "White on Black";
    /*  179 */   static boolean serverRunning = false;
    /*  180 */   static String addressToTry = null;
    /*  181 */   static java.net.InetAddress localIP = null;
    static PrivateMessagePane[] PMPane;
    static PMAndStatusPanel[] pmstatus;
    static KillMenuItem[] killMenuItemArray;
    static UnkillMenuItem[] unkillMenuItemArray;
    static RenamePlayerMenuItem[] renameMenuItemArray;
    static KickMenuItem[] kickMenuItemArray;
    /*  197 */   static java.awt.Font normalFont = new java.awt.Font( null, 0, 12 );
    /*  198 */   static java.awt.Font spoofFont = new java.awt.Font( null, 1, 16 );
    java.awt.Component componentHolder;

    public JPServer() {
        /*  224 */
        clobberAqua = ( (Boolean) prefs.getPref( 18 ) ).booleanValue();





        /*  230 */
        if ( clobberAqua ) {
            try {
                /*  234 */
                javax.swing.UIManager.setLookAndFeel( javax.swing.UIManager.getCrossPlatformLookAndFeelClassName() );
            } catch ( Exception localException ) {
                /*  237 */
                System.out.println( "Exception while setting L&F." );
            }
        }
        /*  239 */
        JParanoia.appInfo = "JParanoia Server " + VERSION_NAME;

        /*  241 */
        allowObservers = ( (Boolean) prefs.getPref( 28 ) ).booleanValue();
        /*  242 */
        registerGame = ( (Boolean) prefs.getPref( 31 ) ).booleanValue();
        /*  243 */
        behindRouter = ( (Boolean) prefs.getPref( 32 ) ).booleanValue();
        /*  244 */
        isPXPGame = ( (Boolean) prefs.getPref( 34 ) ).booleanValue();
        /*  245 */
        if ( isPXPGame ) {
            maxNumClones = 999;
        } else
            /*  246 */ {
            maxNumClones = ( (Integer) prefs.getPref( 23 ) ).intValue();
        }
        /*  247 */
        gmNameNag = ( (Boolean) prefs.getPref( 35 ) ).booleanValue();





        /*  253 */
        computerFontIncrease = ( (Integer) prefs.getPref( 26 ) ).intValue();

        /*  255 */
        frame.setTitle( myTitle.get() );



        /*  259 */
        frame.setIconImage( java.awt.Toolkit.getDefaultToolkit()
                .getImage( getClass().getResource( "graphics/jparanoiaIcon.jpg" ) ) );



        /*  263 */
        frame.setDefaultCloseOperation( 0 );



        /*  267 */
        frame.addWindowListener( new java.awt.event.WindowAdapter() {
            public void windowClosing( java.awt.event.WindowEvent paramAnonymousWindowEvent ) {
            }
        } );
        try {
            /*  276 */
            localIP = java.net.InetAddress.getLocalHost();
        } catch ( java.net.UnknownHostException localUnknownHostException ) {
            /*  278 */
            System.out.println( "Error: Unable to get local host address" );
        }

        /*  281 */
        charsheetAttributes = new SimpleAttributeSet();
        /*  282 */
        charsheetAttributes.addAttribute( javax.swing.text.StyleConstants.Bold, new Boolean( true ) );
        /*  283 */
        charsheetAttributes.addAttribute( StyleConstants.CharacterConstants.Family, "SansSerif" );
        /*  284 */
        charsheetAttributes.addAttribute( StyleConstants.CharacterConstants.Size, new Integer( 12 ) );

        /*  286 */
        DataParser localDataParser = new DataParser();

        /*  288 */
        players = localDataParser.parsePlayerList( "playerData/playerList.txt" );

        /*  290 */
        System.out.println( "Processing imageData.txt:" );

        /*  292 */
        idp = new ImageDataParser();
        /*  293 */
        idp.parseImageURLs( "imageData.txt" );


        /*  296 */
        numberOfPlayers = players.length;
        /*  297 */
        for ( int i = 0; i < players.length; i++ ) {
            if ( players[i].isAnActualPlayer() ) {
                numberOfPCs += 1;
            }
        }
        /*  299 */
        troubleshooters = new ServerPlayer[numberOfPCs - 1];
        /*  300 */
        for ( int i = 1; i < numberOfPCs; i++ ) {
            troubleshooters[i - 1] = players[i];
        }
        /*  302 */
        for ( int i = 0; i < troubleshooters.length; i++ ) {
            /*  304 */
            sortedNames.add( troubleshooters[i] );

            /*  306 */
            if ( sortedNames.size() > 1 ) {
                /*  307 */
                int j = sortedNames.size() - 1;
                /*  308 */
                int k = j - 1;
                /*  309 */
                while ( k >= 0 &&
                        ( (ServerPlayer) sortedNames.get( j ) ).getName()
                                .compareToIgnoreCase( ( (ServerPlayer) sortedNames.get( k ) ).getName() ) < 0 ) {

                    /*  312 */
                    sortedNames.add( j, sortedNames.remove( k ) );

                    /*  314 */
                    j = k;
                    /*  315 */
                    k--;
                }
            }
        }


        /*  321 */
        players[0].setLoggedIn( true );
        /*  322 */
        myPlayer = players[0];

        /*  324 */
        frame.setSize( 770, 540 );

        /*  326 */
        displayArea = new JTextPane();
        /*  327 */
        displayArea.setEditable( false );
        /*  328 */
        displayArea.setEnabled( true );

        /*  330 */
        displayArea.setBackground( Color.black );




        /*  335 */
        scrollPane = new javax.swing.JScrollPane( displayArea, 22, 31 );


        /*  338 */
        chatDocument = new javax.swing.text.DefaultStyledDocument();

        /*  340 */
        brightColors = new jparanoia.shared.BrightColorArray().getColors();

        /*  342 */
        darkColors = new Color[10];

        /*  344 */
        for ( int i = 0; i < 10; i++ ) {
            /*  346 */
            switch ( i ) {
                case 0:
                    /*  348 */
                    newColor = new Color( 0.1F, 0.1F, 0.1F );
                    break;
                /*  349 */
                case 1:
                    newColor = new Color( 0.6F, 0.3F, 0.0F );
                    break;
                /*  350 */
                case 2:
                    newColor = new Color( 0.0F, 0.4F, 0.0F );
                    break;
                /*  351 */
                case 3:
                    newColor = new Color( 0.2F, 0.6F, 0.4F );
                    break;
                /*  352 */
                case 4:
                    newColor = new Color( 0.3F, 0.1F, 0.8F );
                    break;
                /*  353 */
                case 5:
                    newColor = new Color( 0.5F, 0.1F, 0.5F );
                    break;
                /*  354 */
                case 6:
                    newColor = new Color( 0.3F, 0.3F, 0.3F );
                    break;
                /*  355 */
                case 7:
                    newColor = new Color( 0.0F, 0.0F, 0.4F );
                    break;
                /*  356 */
                case 8:
                    newColor = new Color( 0.4F, 0.0F, 0.0F );
                    break;
                /*  357 */
                case 9:
                    newColor = new Color( 0.1F, 0.4F, 0.4F );
                    break;
                /*  358 */
                default:
                    System.out.println( "Error: Out of colors! (Try using White on Black scheme)" );
            }

            /*  361 */
            darkColors[i] = newColor;
        }

        /*  364 */
        textAttributes = new SimpleAttributeSet();
        /*  365 */
        textAttributes.addAttribute( javax.swing.text.StyleConstants.FontConstants.Foreground, Color.white );
        /*  366 */
        textAttributes.addAttribute( javax.swing.text.StyleConstants.FontConstants.Size, prefs.getPref( 15 ) );
        /*  367 */
        textAttributes.addAttribute( javax.swing.text.StyleConstants.FontConstants.Family, prefs.getPref( 16 ) );

        /*  369 */
        systemTextAttributes.addAttribute( javax.swing.text.StyleConstants.FontConstants.Foreground, Color.gray );

        /*  371 */
        setColorScheme();

        /*  373 */
        inputDocument = new javax.swing.text.PlainDocument();

        /*  375 */
        inputLine = new JTextArea( 3, 44 );
        /*  376 */
        inputLine.setLineWrap( true );
        /*  377 */
        inputLine.setWrapStyleWord( true );
        /*  378 */
        inputLine.addKeyListener( new java.awt.event.KeyListener() {
            public void keyTyped( java.awt.event.KeyEvent paramAnonymousKeyEvent ) {
            }

            public void keyPressed( java.awt.event.KeyEvent paramAnonymousKeyEvent ) {
                /*  382 */
                JParanoia.previousKey = JParanoia.thisKey;

                /*  384 */
                JParanoia.thisKey = paramAnonymousKeyEvent.getKeyCode();
                String str;
                /*  386 */
                if ( JParanoia.thisKey == 10 && JPServer.sendMainText ) {
                    /*  388 */
                    str = JPServer.inputLine.getText();
                    /*  389 */
                    if ( str.length() > 0 ) {
                        /*  391 */
                        JPServer.inputLine.setCaretPosition( str.length() );
                    }
                }

                /*  395 */
                if ( JParanoia.thisKey == 9 ) {
                    /*  397 */
                    paramAnonymousKeyEvent.consume();

                    /*  399 */
                    if ( JPServer.inputLine.getText().length() > 0 ) {
                        /*  401 */
                        str = JPServer.inputLine.getText();
                        /*  402 */
                        if ( str.startsWith( "'" ) && str.length() > 1 ) {
                            /*  403 */
                            JPServer.inputLine.setText( "'" +
                                    JPServer.nameCompletion( str.substring( 1 ), JParanoia.thisKey ==
                                            JParanoia.previousKey ) );
                            /*  404 */
                        } else if ( !str.startsWith( "'" ) ) {
                            /*  405 */
                            JPServer.inputLine.setText( JPServer.nameCompletion( str, JParanoia.thisKey ==
                                    JParanoia.previousKey ) );
                        }
                    }
                }
            }

            public void keyReleased( java.awt.event.KeyEvent paramAnonymousKeyEvent ) {
                /*  412 */
                if ( paramAnonymousKeyEvent.getKeyCode() == 10 && JPServer.sendMainText ) {
                    /*  414 */
                    String str = JPServer.inputLine.getText();
                    /*  415 */
                    if ( str.length() > 0 ) {
                        JPServer.sendChat( str.substring( 0, str.length() - 1 ) );
                    }
                }
                /*  418 */
                else if ( paramAnonymousKeyEvent.getKeyCode() == 10 && !JPServer.sendMainText ) {
                    JPServer.setSendMainText( true );
                }
                /*  420 */
            }
        } );
        /*  421 */
        inputLine.setEnabled( false );
        /*  422 */
        inputLine.setFont( normalFont );

        /*  424 */
        inputScrollPane = new javax.swing.JScrollPane( inputLine, 22, 31 );


        /*  427 */
        ServerPlayer[] arrayOfServerPlayer = new ServerPlayer[players.length - 1];

        /*  429 */
        for ( int j = 1; j < players.length; j++ ) {
            /*  431 */
            arrayOfServerPlayer[j - 1] = players[j];
        }

        /*  434 */
        spoofComboBox = new javax.swing.JComboBox( arrayOfServerPlayer );
        /*  435 */
        spoofComboBox.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent paramAnonymousActionEvent ) {
                /*  437 */
                JPServer.playerToSpoof = (ServerPlayer) JPServer.spoofComboBox.getSelectedItem();
                /*  438 */
                if ( JPServer.spoofCheckBox.isSelected() ) {
                    JPServer.currentPlayerID = JPServer.playerToSpoof.getID();
                }
                /*  439 */
                JPServer.inputLine.requestFocus();
            }
            /*  441 */
        } );
        /*  442 */
        playerToSpoof = (ServerPlayer) spoofComboBox.getSelectedItem();

        /*  444 */
        spoofComboBox.setMaximumSize( new java.awt.Dimension( 130, 20 ) );
        /*  445 */
        spoofComboBox.setPreferredSize( new java.awt.Dimension( 130, 20 ) );
        /*  446 */
        spoofComboBox.setMinimumSize( new java.awt.Dimension( 130, 20 ) );

        /*  448 */
        spoofCheckBox = new javax.swing.JCheckBox();
        /*  449 */
        spoofCheckBox.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent paramAnonymousActionEvent ) {
                /*  451 */
                if ( JPServer.spoofCheckBox.isSelected() ) {
                    /*  452 */
                    JPServer.currentPlayerID = JPServer.playerToSpoof.getID();
                    /*  453 */
                    JPServer.inputLine.setFont( JPServer.spoofFont );
                } else {
                    /*  456 */
                    JPServer.currentPlayerID = "00";
                    /*  457 */
                    JPServer.inputLine.setFont( JPServer.normalFont );
                }
                /*  459 */
                JPServer.inputLine.requestFocus();
            }
            /*  461 */
        } );
        /*  462 */
        spoofPanel = new JPanel();
        /*  463 */
        spoofPanel.setLayout( new javax.swing.BoxLayout( spoofPanel, 0 ) );
        /*  464 */
        spoofPanel.add( javax.swing.Box.createRigidArea( new java.awt.Dimension( 2, 0 ) ) );
        /*  465 */
        spoofPanel.add( spoofComboBox );
        /*  466 */
        spoofPanel.add( javax.swing.Box.createRigidArea( new java.awt.Dimension( 5, 0 ) ) );
        /*  467 */
        spoofPanel.add( spoofCheckBox );
        /*  468 */
        spoofPanel.add( javax.swing.Box.createRigidArea( new java.awt.Dimension( 2, 0 ) ) );

        /*  470 */
        spoofPanel.setBorder( javax.swing.BorderFactory.createTitledBorder( "Spoof" ) );

        /*  472 */
        freezePanel = new JPanel();
        /*  473 */
        freezePanel.setLayout( new java.awt.GridLayout( 1, 2 ) );

        /*  475 */
        freezeButton = new JButton( "Freeze" );
        /*  476 */
        freezeButton.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent paramAnonymousActionEvent ) {
                /*  478 */
                if ( !JPServer.frozen ) {
                    /*  479 */
                    JPServer.freezePlayers();
                } else {
                    /*  482 */
                    JPServer.unfreezePlayers();
                }
            }
            /*  486 */
        } );
        /*  487 */
        combatButton = new JButton( "Combat!" );
        /*  488 */
        combatButton.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent paramAnonymousActionEvent ) {
            }

            /*  492 */
        } );
        /*  493 */
        combatButton.setEnabled( false );


        /*  496 */
        freezePanel.add( combatButton );
        /*  497 */
        freezePanel.add( freezeButton );


        /*  500 */
        spoofAndFreezePanel = new JPanel();
        /*  501 */
        spoofAndFreezePanel.setLayout( new javax.swing.BoxLayout( spoofAndFreezePanel, 1 ) );

        /*  503 */
        spoofAndFreezePanel.add( spoofPanel );
        /*  504 */
        spoofAndFreezePanel.add( javax.swing.Box.createRigidArea( new java.awt.Dimension( 0, 5 ) ) );
        /*  505 */
        spoofAndFreezePanel.add( freezePanel );




        /*  510 */
        inputPanel = new JPanel();

        /*  512 */
        inputPanel.setLayout( new javax.swing.BoxLayout( inputPanel, 0 ) );

        /*  514 */
        inputPanel.add( spoofAndFreezePanel );

        /*  516 */
        inputPanel.add( javax.swing.Box.createRigidArea( new java.awt.Dimension( 5, 0 ) ) );

        /*  518 */
        inputPanel.add( inputScrollPane );

        /*  520 */
        charsheetPanel = new CharsheetPanel();


        /*  523 */
        menuBar = new javax.swing.JMenuBar();
        /*  524 */
        frame.setJMenuBar( menuBar );

        /*  526 */
        serverMenu = new JMenu( "Server" );
        /*  527 */
        startServerMenuItem = new JMenuItem( "Start server" );
        /*  528 */
        startServerMenuItem.setToolTipText( "Opens your computer for new connections." );


        /*  531 */
        startServerMenuItem.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent paramAnonymousActionEvent ) {
            }
            /*  534 */
        } );
        /*  535 */
        stopServerMenuItem = new JMenuItem( "Stop server" );
        /*  536 */
        stopServerMenuItem.setToolTipText( "Stops your computer from accepting new connections." );


        /*  539 */
        stopServerMenuItem.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent paramAnonymousActionEvent ) {
            }
            /*  542 */
        } );
        /*  543 */
        registerGameMenuItem = new JCheckBoxMenuItem( "Register Game" );
        /*  544 */
        registerGameMenuItem.setToolTipText( "<HTML>When checked, your server will be made available<BR>to players via the JParanoia Game Registry so they<BR>will not need the IP address of your server.</HTML>" );




        /*  549 */
        registerGameMenuItem.setSelected( prefs.getPref( 31 ).equals( new Boolean( true ) ) );
        /*  550 */
        registerGameMenuItem.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent paramAnonymousActionEvent ) {
                /*  552 */
                if ( JPServer.registerGame ) {
                    /*  554 */
                    JPServer.registerGame = false;
                    /*  555 */
                    if ( JPServer.gameRegistered ) {
                        jparanoia.shared.GameRegistrar.removeGame();
                    }
                } else {
                    /*  560 */
                    JPServer.registerGame = true;
                    /*  561 */
                    if ( JPServer.serverRunning ) {
                        /*  563 */
                        if ( JPServer.gameDescription.equals( JPServer.defaultGameDescription ) )
                            /*  564 */ {
                            JPServer.setGameDescriptionMenuItem.doClick();
                        }
                        /*  565 */
                        jparanoia.shared.GameRegistrar.addGame( JPServer.gameDescription );
                    }
                }
            }
            /*  569 */
        } );
        /*  570 */
        setGameDescriptionMenuItem = new JMenuItem( "Set Game Description..." );
        /*  571 */
        setGameDescriptionMenuItem.setToolTipText( "<HTML>This changes the name of your game<BR> on the JParanoia Game Registry.</HTML>" );



        /*  575 */
        setGameDescriptionMenuItem.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent paramAnonymousActionEvent ) {
                /*  577 */
                String str = (String) javax.swing.JOptionPane.showInputDialog( null, "Enter a description for your game:", "Set Game Description...", -1, null, null, JPServer.gameDescription );


                /*  580 */
                if ( str != null && !str.equals( "" ) && !str.equals( JPServer.gameDescription ) ) {
                    /*  582 */
                    JPServer.gameDescription = str;
                    /*  583 */
                    if ( JPServer.gameRegistered ) {
                        jparanoia.shared.GameRegistrar.addGame( JPServer.gameDescription );
                    }
                }
            }
            /*  587 */
        } );
        /*  588 */
        serverMenu.add( startServerMenuItem );
        /*  589 */
        serverMenu.add( stopServerMenuItem );

        /*  591 */
        serverMenu.addSeparator();

        /*  593 */
        serverMenu.add( registerGameMenuItem );
        /*  594 */
        serverMenu.add( setGameDescriptionMenuItem );

        /*  596 */
        stopServerMenuItem.setEnabled( false );

        /*  598 */
        serverMenu.addSeparator();

        /*  600 */
        ipLabel = new javax.swing.JLabel( "  Local IP :   " + localIP.getHostAddress() );

        /*  602 */
        serverMenu.add( ipLabel );

        /*  604 */
        menuBar.add( serverMenu );

        /*  606 */
        fontMenu = new FontMenu( "Font" );

        /*  608 */
        menuBar.add( fontMenu );

        /*  610 */
        optionsMenu = new ServerOptionsMenu( "Options" );

        /*  612 */
        menuBar.add( optionsMenu );

        /*  614 */
        playerMenu = new JMenu( "Player" );


        /*  617 */
        for ( int j = 0; j < troubleshooters.length; j++ ) {
            /*  618 */
            playerMenu.add( troubleshooters[j].getPlayerMenu() );
        }
        /*  620 */
        menuBar.add( playerMenu );

        /*  622 */
        npcMenu = new JMenu( "Spare NPCs" );










        /*  633 */
        for ( int j = 0; j < spareNpcs.size(); j++ ) {
            /*  634 */
            npcMenu.add( ( (ServerPlayer) spareNpcs.get( j ) ).getNpcMenu() );
        }
        /*  636 */
        menuBar.add( npcMenu );

        /*  638 */
        globalPMMenu = new JMenu( "Global PM" );

        /*  640 */
        sendGlobalPMMenuItem = new JMenuItem( "Send Global PM..." );
        /*  641 */
        sendGlobalPMMenuItem.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent paramAnonymousActionEvent ) {
                /*  643 */
                new GlobalPMDialog().setVisible( true );
            }
            /*  645 */
        } );
        /*  646 */
        globalPMMenu.add( sendGlobalPMMenuItem );

        /*  648 */
        menuBar.add( globalPMMenu );

        /*  650 */
        sendImageMenu = new ServerImageMenu();

        /*  652 */
        menuBar.add( sendImageMenu );

        /*  654 */
        observersMenu = new JMenu( "Observers" );

        /*  656 */
        hearObserversMenuItem = new JCheckBoxMenuItem( "Hear Observers" );
        /*  657 */
        hearObserversMenuItem.setSelected( ( (Boolean) prefs.getPref( 29 ) ).booleanValue() );
        /*  658 */
        hearObserversMenuItem.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent paramAnonymousActionEvent ) {
            }
            /*  661 */
        } );
        /*  662 */
        announceObserversMenuItem = new JCheckBoxMenuItem( "Announce Observers" );
        /*  663 */
        announceObserversMenuItem.setSelected( ( (Boolean) prefs.getPref( 30 ) ).booleanValue() );
        /*  664 */
        announceObserversMenuItem.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent paramAnonymousActionEvent ) {
            }
            /*  667 */
        } );
        /*  668 */
        showObserversListMenuItem = new JMenuItem( "Show Observers List" );
        /*  669 */
        showObserversListMenuItem.addActionListener( new ActionListener() {
            /*  671 */
            public void actionPerformed( ActionEvent paramAnonymousActionEvent ) {
                JParanoia.obsFrame.setVisible( true );
            }
            /*  672 */
        } );
        /*  673 */
        observersMenu.add( hearObserversMenuItem );
        /*  674 */
        observersMenu.add( announceObserversMenuItem );
        /*  675 */
        observersMenu.add( showObserversListMenuItem );

        /*  677 */
        menuBar.add( observersMenu );

        /*  679 */
        java.awt.Container localContainer = frame.getContentPane();


        /*  682 */
        mainPanel = new JPanel();

        /*  684 */
        java.awt.GridBagLayout localGridBagLayout1 = new java.awt.GridBagLayout();
        /*  685 */
        java.awt.GridBagConstraints localGridBagConstraints1 = new java.awt.GridBagConstraints();

        /*  687 */
        splitPane = new javax.swing.JSplitPane( 0, true, charsheetPanel, scrollPane );


        /*  690 */
        splitPane.setDividerLocation( 122 );
        /*  691 */
        splitPane.setOneTouchExpandable( true );




        /*  696 */
        localGridBagConstraints1.gridx = 0;
        /*  697 */
        localGridBagConstraints1.gridy = 0;
        /*  698 */
        localGridBagConstraints1.weighty = 1.0D;
        /*  699 */
        localGridBagConstraints1.weightx = 1.0D;
        /*  700 */
        localGridBagConstraints1.fill = 1;
        /*  701 */
        localGridBagConstraints1.anchor = 15;
        /*  702 */
        localGridBagConstraints1.insets = new java.awt.Insets( 2, 2, 2, 2 );

        /*  704 */
        localGridBagLayout1.setConstraints( splitPane, localGridBagConstraints1 );

        /*  706 */
        PMPane = new PrivateMessagePane[numberOfPCs];

        /*  708 */
        for ( int m = 1; m < numberOfPCs; m++ ) {
            /*  710 */
            PMPane[m] = new PrivateMessagePane( players[m] );
        }

        /*  713 */
        System.out.println( "\nServer's local IP Address: " + localIP.getHostAddress() + "\n" );

        /*  715 */
        pmstatus = new PMAndStatusPanel[numberOfPCs];

        /*  717 */
        System.out.println( "PM & status array initialized." );

        /*  719 */
        for ( int m = 1; m < numberOfPCs; m++ ) {
            /*  721 */
            pmstatus[m] = new PMAndStatusPanel( PMPane[m], PMPane[m].statusPanel );
        }

        /*  724 */
        System.out.println( "PM & status panels created." );

        /*  726 */
        PMContainer = new JPanel();

        /*  728 */
        PMContainer.setLayout( new javax.swing.BoxLayout( PMContainer, 1 ) );

        /*  730 */
        for ( int m = 1; m < numberOfPCs; m++ ) {
            /*  732 */
            PMContainer.add( javax.swing.Box.createRigidArea( new java.awt.Dimension( 0, 2 ) ) );
            /*  733 */
            PMContainer.add( pmstatus[m] );
        }

        /*  736 */
        System.out.println( "PM & status pane populated." );

        /*  738 */
        java.awt.GridBagLayout localGridBagLayout2 = new java.awt.GridBagLayout();
        /*  739 */
        java.awt.GridBagConstraints localGridBagConstraints2 = new java.awt.GridBagConstraints();

        /*  741 */
        localContainer.setLayout( localGridBagLayout2 );



        /*  745 */
        localGridBagConstraints2.gridx = 0;
        /*  746 */
        localGridBagConstraints2.gridy = 0;
        /*  747 */
        localGridBagConstraints2.weighty = 1.0D;
        /*  748 */
        localGridBagConstraints2.weightx = 1.0D;
        /*  749 */
        localGridBagConstraints2.fill = 1;
        /*  750 */
        localGridBagConstraints2.anchor = 15;
        /*  751 */
        localGridBagConstraints2.insets = new java.awt.Insets( 2, 2, 2, 2 );

        /*  753 */
        localGridBagLayout2.setConstraints( splitPane, localGridBagConstraints2 );
        /*  754 */
        localContainer.add( splitPane );

        /*  756 */
        localGridBagConstraints2.weighty = 0.0D;
        /*  757 */
        localGridBagConstraints2.gridy = 5;
        /*  758 */
        localGridBagLayout2.setConstraints( inputPanel, localGridBagConstraints2 );
        /*  759 */
        localContainer.add( inputPanel );

        /*  761 */
        localGridBagConstraints2.gridx = 4;
        /*  762 */
        localGridBagConstraints2.gridy = 0;
        /*  763 */
        localGridBagConstraints2.gridheight = 6;
        /*  764 */
        localGridBagConstraints2.gridwidth = 1;
        /*  765 */
        localGridBagConstraints2.weightx = 0.0D;
        /*  766 */
        localGridBagLayout2.setConstraints( PMContainer, localGridBagConstraints2 );
        /*  767 */
        localContainer.add( PMContainer );


        /*  770 */
        connectOPane = new javax.swing.JOptionPane();
        /*  771 */
        errorPane = new javax.swing.JOptionPane();

        /*  773 */
        myPlayer = players[0];




        /*  778 */
        displayWrite( Color.green, "JParanoia Server " + VERSION_NAME );


        /*  781 */
        displayWrite( Color.orange, "      http://www.byronbarry.com/jparanoia/\n" );


        /*  784 */
        displayWrite( Color.cyan, "New in this release:\n\n" );



        /*  788 */
        displayWrite( Color.white, "- Quick Charsheet option (see README).\n- The Computer's text is now large in the logs.\n- The GM's text is now bold in the logs.\n- Unplanned images now appear in the logs.\n- Current passwords appear in Player menu.\n- Other miscellaneous bug fixes.\n\nRead the README.TXT for full details.\nFor a complete version history, visit the JParanoia website.\n\n" );











        /*  800 */
        displayWrite( Color.white, "If you are new to running a JParanoia server, or find yourself wondering how to do something, " );



        /*  804 */
        displayWrite( Color.yellow, "READ THE README.\n" );


        /*  807 */
        keepLog = ( (Boolean) prefs.getPref( 20 ) ).booleanValue();
        /*  808 */
        htmlLog = ( (Boolean) prefs.getPref( 21 ) ).booleanValue();
        /*  809 */
        if ( keepLog ) {
            /*  810 */
            if ( htmlLog ) {
                logger = new jparanoia.shared.GameLogger( players );
            } else {
                /*  811 */
                logger = new jparanoia.shared.GameLogger();
            }
        }

        /*  815 */
        System.out.println( "JPServer.frame constructed.\n" );



        /*  819 */
        System.out.print( "Attempting to load CombatFrame class... " );
        try {
            /*  822 */
            combatFrame = new CombatFrame();
            /*  823 */
            combatFrame = null;
            /*  824 */
            System.out.println( "loaded." );
        } catch ( NoClassDefFoundError localNoClassDefFoundError ) {
            /*  829 */
            System.out.println( "FAILED" );
            /*  830 */
            errorMessage( "CombatFrame - class definition not found", "The CombatFrame class failed to load. The combat manager\nis not available. Please notify the author. You will need\nto exit and relaunch the server to correct this problem." );
        }





        /*  837 */
        net.roydesign.mac.MRJAdapter.addQuitApplicationListener( new ActionListener() {
            public void actionPerformed( ActionEvent paramAnonymousActionEvent ) {
            }

            /*  841 */
        } );
        /*  842 */
        net.roydesign.mac.MRJAdapter.addAboutListener( new ActionListener() {
            public void actionPerformed( ActionEvent paramAnonymousActionEvent ) {
                /*  844 */
                JParanoia.aboutBoxMenuItem.doClick();
            }
            /*  846 */
        } );
        /*  847 */
        mainFontSize = (Integer) textAttributes.getAttribute( javax.swing.text.StyleConstants.FontConstants.Size );


        /*  850 */
        if ( isPXPGame ) {
            frame.setSize( 855, frame.getHeight() );
        }
        /*  852 */
        frame.setVisible( true );
    }

    public static void main( String[] paramArrayOfString ) {
        /*  202 */
        System.out.println( "\nThis is the JParanoia Server console.\n" );
        /*  203 */
        System.out.println( "Running under Java Runtime Environment version " + System.getProperty( "java.version" ) );
        /*  204 */
        System.out.println( "\n" );
        /*  205 */
        JPServer localJPServer = new JPServer();
        /*  206 */
        splitPane.repaint();
    }

    public static void exit() {
        /*  862 */
        if ( numberOfConnectedClients - numberOfConnectedObservers > 0 ) {
            /*  863 */
            if ( javax.swing.JOptionPane.showConfirmDialog( frame, "WARNING: Players are still connected!\nAre you SURE you want to quit?", "Quit confirmation...", 0, 2 ) ==
                    0 ) {




                /*  869 */
                spamString( "086SERVER TERMINATED" );
            } else
                /*  870 */ {
                return;
            }
        }
        /*  872 */
        if ( keepLog ) {
            /*  874 */
            logger.closeLog();
            /*  875 */
            if ( htmlLog ) {
                logger.sanitize();
            }
        }

        /*  879 */
        if ( ServerPlayer.numUnsavedCharsheets > 0 ) {
            /*  881 */
            if ( javax.swing.JOptionPane.showConfirmDialog( frame, "Some character sheets contain unsaved changes.\nSave before exiting?", "Unsaved Changes", 0, 3 ) ==
                    0 ) {





                /*  888 */
                soundMenu.charSheetAlertMenuItem.setSelected( false );
                /*  889 */
                for ( int i = 0; i < troubleshooters.length; i++ ) {
                    /*  891 */
                    if ( troubleshooters[i].hasUnsavedCharsheet() ) {
                        /*  892 */
                        troubleshooters[i].saveCharsheet( false );
                    }
                }
            }
        }
        /*  897 */
        if ( gameRegistered ) {
            jparanoia.shared.GameRegistrar.removeGame();
        }
        /*  899 */
        frame.dispose();
        /*  900 */
        System.exit( 0 );
    }

    public static synchronized void spamString( String paramString ) {
        /* 1482 */
        for ( int i = 0; i < chatThreads.size(); i++ ) {
            /* 1484 */
            thisThread = (ServerChatThread) chatThreads.elementAt( i );

            /* 1486 */
            someWriter = thisThread.out;
            /* 1487 */
            someWriter.println( paramString );
        }
    }

    public static void setColorScheme() {
        /*  906 */
        if ( !currentColorScheme.equals( newColorScheme ) ) {
            /*  908 */
            if ( newColorScheme.equals( "White on Black" ) ) {
                /*  910 */
                textColor = Color.white;
                /*  911 */
                displayArea.setBackground( Color.black );
            }
            /*  914 */
            else if ( newColorScheme.equals( "Black on White" ) ) {
                /*  916 */
                textColor = Color.black;
                /*  917 */
                displayArea.setBackground( Color.white );
            } else {
                /*  920 */
                System.out.println( "Error: invalid color logic." );
            }
            /*  922 */
            currentColorScheme = newColorScheme;
            /*  923 */
            assignColorsToCharacters();
        }
    }

    public static void assignColorsToCharacters() {
        int i;
        /*  930 */
        if ( currentColorScheme.equals( "White on Black" ) ) {
            /*  932 */
            for ( i = 0; i < numberOfPlayers; i++ ) {

                /*  935 */
                players[i].setChatColor( brightColors[i] );
            }
        }
        /*  938 */
        if ( currentColorScheme.equals( "Black on White" ) ) {
            /*  940 */
            for ( i = 0; i < numberOfPlayers; i++ ) {
                /*  942 */
                players[i].setChatColor( darkColors[i] );
            }
        }
        /*  945 */
        System.out.println( "Error: invalid color logic" );
    }

    public static void playerHasJoined( int paramInt ) {
        /*  956 */
        ServerPlayer localServerPlayer = players[paramInt];

        /*  958 */
        localServerPlayer.setLoggedIn( true );
        /*  959 */
        localServerPlayer.pmPane.enableInput();
        /*  960 */
        absoluteChat( "--- " +
                localServerPlayer.toString() +
                " (" +
                localServerPlayer.getRealName() +
                ") has joined ---" );
        /*  961 */
        if ( showTimeStamps ) {
            displayTimeStamp();
        }
        /*  962 */
        localServerPlayer.statusPanel.statusLoggedIn( true );
        /*  963 */
        if ( soundIsOn && soundMenu.joinLeaveMenuItem.isSelected() ) {
            soundPlayer.play( 5 );
        }
    }

    public static synchronized void absoluteChat( String paramString ) {
        try {
            /* 1240 */
            chatDocument.insertString( chatDocument.getLength(), paramString + "\n", systemTextAttributes );


            /* 1243 */
            displayArea.setDocument( chatDocument );
            /* 1244 */
            if ( autoScroll ) {
                displayArea.setCaretPosition( chatDocument.getLength() );
            }
            /* 1245 */
            if ( keepLog ) {
                String str;
                /* 1248 */
                if ( htmlLog ) {
                    str = "<span class=\"gray\">" + paramString + "</span><br/>";
                } else
                    /* 1249 */ {
                    str = paramString;
                }
                /* 1250 */
                logger.logEntry( str );
            }
        } catch ( BadLocationException localBadLocationException ) {
            /* 1256 */
            System.err.println( "Unhandled exception. (Bad Location)" );
        }
    }

    public static void displayTimeStamp() {
        /* 1471 */
        timeStamp = new java.util.Date();
        /* 1472 */
        absoluteChat( "(" + timeStamp.toString() + ")" );
    }

    public static void playerHasLeft( int paramInt ) {
        /*  972 */
        ServerPlayer localServerPlayer = players[paramInt];

        /*  974 */
        localServerPlayer.setLoggedIn( false );
        /*  975 */
        localServerPlayer.pmPane.disableInput();
        /*  976 */
        absoluteChat( "--- " +
                localServerPlayer.toString() +
                " (" +
                localServerPlayer.getRealName() +
                ") has left ---" );
        /*  977 */
        if ( showTimeStamps ) {
            displayTimeStamp();
        }
        /*  978 */
        localServerPlayer.statusPanel.statusLoggedIn( false );
        /*  979 */
        localServerPlayer.setRealName( "???" );
        /*  980 */
        if ( soundIsOn && soundMenu.joinLeaveMenuItem.isSelected() ) {
            soundPlayer.play( 6 );
        }
    }

    public static void toggleHearObservers() {
        /*  987 */
        if ( hearObserversMenuItem.isSelected() ) {
            /*  989 */
            spamString( "097" );
            /*  990 */
            absoluteChat( "Observers are currently heard." );
        } else {
            /*  995 */
            spamString( "098" );
            /*  996 */
            absoluteChat( "Observers are currently NOT heard." );
        }
    }

    public static void toggleAnnounceObservers() {
        /* 1002 */
        if ( announceObserversMenuItem.isSelected() ) {
            /* 1004 */
            JParanoia.announceObservers = true;

            /* 1006 */
            absoluteSpam( "Observers are currently announced." );
        } else {
            /* 1010 */
            JParanoia.announceObservers = false;

            /* 1012 */
            absoluteSpam( "Observers are currently NOT announced." );
        }
    }

    public static void absoluteSpam( String paramString ) {
        /* 1591 */
        spamString( "199" + paramString );
        /* 1592 */
        absoluteChat( paramString );
    }

    public static void setFontBold( boolean paramBoolean ) {
        /* 1018 */
        Boolean localBoolean = new Boolean( paramBoolean );
        /* 1019 */
        textAttributes.addAttribute( javax.swing.text.StyleConstants.FontConstants.Bold, localBoolean );
    }

    public static void useComputerFont() {
        /* 1024 */
        styleBegin = "<span class=\"computer\">";
        /* 1025 */
        styleEnd = "</span>";
        /* 1026 */
        mainFontSize = (Integer) textAttributes.getAttribute( javax.swing.text.StyleConstants.FontConstants.Size );

        /* 1028 */
        int i = mainFontSize.intValue() + computerFontIncrease;
        /* 1029 */
        textAttributes.addAttribute( javax.swing.text.StyleConstants.FontConstants.Size, new Integer( i ) );
        /* 1030 */
        if ( !fontIsBold ) {
            setFontBold( true );
        }
    }

    public static void useGmFont() {
        /* 1035 */
        styleBegin = "<span class=\"gmText\">";
        /* 1036 */
        styleEnd = "</span>";

        /* 1038 */
        if ( !fontIsBold ) {
            setFontBold( true );
        }
    }

    public static void restoreOriginalFont() {
        /* 1044 */
        styleBegin = styleEnd = "";
        /* 1045 */
        textAttributes.addAttribute( javax.swing.text.StyleConstants.FontConstants.Size, mainFontSize );
        /* 1046 */
        if ( !fontIsBold ) {
            setFontBold( false );
        }
    }

    public static synchronized void generalChat( String paramString ) {
        /* 1051 */
        int i = Integer.parseInt( paramString.substring( 0, 2 ) );
        /* 1052 */
        paramString = paramString.substring( 2 );

        /* 1054 */
        styleBegin = styleEnd = "";

        /* 1056 */
        if ( i == numberOfPCs ) {
            /* 1058 */
            if ( bigComputerFont ) {
                useComputerFont();
            }
        }
        /* 1062 */
        else if ( i == 0 ) {
            useGmFont();
        }
        try {
            /* 1067 */
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, players[i].getChatColor() );

            /* 1069 */
            chatDocument.insertString( chatDocument.getLength(), "   " + players[i].toString() + ": ", textAttributes );


            /* 1072 */
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, textColor );

            /* 1074 */
            chatDocument.insertString( chatDocument.getLength(), paramString + "\n", textAttributes );




            /* 1079 */
            displayArea.setDocument( chatDocument );
            /* 1080 */
            if ( autoScroll ) {
                displayArea.setCaretPosition( chatDocument.getLength() );
            }
            /* 1081 */
            if ( keepLog ) {
                String str;
                /* 1084 */
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
                    /* 1085 */ {
                    str = players[i].toString() + ": " + paramString;
                }
                /* 1086 */
                logger.logEntry( str );
            }
        } catch ( BadLocationException localBadLocationException ) {
            /* 1092 */
            System.err.println( "Unhandled exception. (Bad Location)" );
        }

        /* 1095 */
        if ( i == numberOfPCs && bigComputerFont ) {
            restoreOriginalFont();
            /* 1096 */
        } else if ( i == 0 ) {
            restoreOriginalFont();
        }
        /* 1098 */
        if ( soundIsOn && soundMenu.newTextMenuItem.isSelected() ) {
            soundPlayer.play( 7 );
        }
    }

    public static synchronized void actionChat( String paramString ) {
        /* 1104 */
        int i = Integer.parseInt( paramString.substring( 0, 2 ) );
        /* 1105 */
        paramString = paramString.substring( 2 );

        /* 1107 */
        styleBegin = styleEnd = "";

        /* 1109 */
        if ( i == numberOfPCs && bigComputerFont ) {
            useComputerFont();
            /* 1110 */
        } else if ( i == 0 ) {
            useGmFont();
        }
        try {
            /* 1114 */
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, players[i].getChatColor() );

            /* 1116 */
            chatDocument.insertString( chatDocument.getLength(), "* " +
                    players[i].toString() +
                    " " +
                    paramString +
                    " *\n", textAttributes );

            /* 1118 */
            displayArea.setDocument( chatDocument );
            /* 1119 */
            if ( autoScroll ) {
                displayArea.setCaretPosition( chatDocument.getLength() );
            }
            /* 1120 */
            if ( keepLog ) {
                String str;
                /* 1123 */
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
                    /* 1124 */ {
                    str = "* " + players[i].toString() + " " + paramString + " *";
                }
                /* 1125 */
                logger.logEntry( str );
            }

            /* 1128 */
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, textColor );
        } catch ( BadLocationException localBadLocationException ) {
            /* 1133 */
            System.err.println( "Unhandled exception. (Bad Location)" );
        }

        /* 1136 */
        if ( i == numberOfPCs && bigComputerFont ) {
            restoreOriginalFont();
            /* 1137 */
        } else if ( i == 0 ) {
            restoreOriginalFont();
        }
        /* 1139 */
        if ( soundIsOn && soundMenu.newTextMenuItem.isSelected() ) {
            soundPlayer.play( 7 );
        }
    }

    public static synchronized void speechChat( String paramString ) {
        /* 1145 */
        int i = Integer.parseInt( paramString.substring( 0, 2 ) );
        /* 1146 */
        paramString = paramString.substring( 2 );

        /* 1148 */
        styleBegin = styleEnd = "";



        /* 1152 */
        if ( i == numberOfPCs && bigComputerFont ) {
            useComputerFont();
            /* 1153 */
        } else if ( i == 0 ) {
            useGmFont();
        }
        String str1;
        /* 1156 */
        if ( paramString.endsWith( "!!" ) ) {
            str1 = "screams, ";
            /* 1157 */
        } else if ( paramString.endsWith( "!" ) ) {
            str1 = "shouts, ";
        } else {
            /* 1158 */
            str1 = "says, ";
        }
        try {
            /* 1162 */
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, players[i].getChatColor() );



            /* 1166 */
            chatDocument.insertString( chatDocument.getLength(), players[i].toString() + " ", textAttributes );
            /* 1167 */
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, textColor );
            /* 1168 */
            chatDocument.insertString( chatDocument.getLength(), str1 + "\"" + paramString + "\"\n", textAttributes );

            /* 1170 */
            displayArea.setDocument( chatDocument );
            /* 1171 */
            if ( autoScroll ) {
                displayArea.setCaretPosition( chatDocument.getLength() );
            }
            /* 1172 */
            if ( keepLog ) {
                String str2;
                /* 1175 */
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
                    /* 1176 */ {
                    str2 = players[i].toString() + " " + str1 + "\"" + paramString + "\"";
                }
                /* 1177 */
                logger.logEntry( str2 );
            }
            /* 1179 */
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, textColor );
        } catch ( BadLocationException localBadLocationException ) {
            /* 1184 */
            System.err.println( "Unhandled exception. (Bad Location)" );
        }

        /* 1187 */
        if ( i == numberOfPCs && bigComputerFont ) {
            restoreOriginalFont();
            /* 1188 */
        } else if ( i == 0 ) {
            restoreOriginalFont();
        }
        /* 1190 */
        if ( soundIsOn && soundMenu.newTextMenuItem.isSelected() ) {
            soundPlayer.play( 7 );
        }
    }

    public static synchronized void thoughtChat( String paramString ) {
        /* 1196 */
        int i = Integer.parseInt( paramString.substring( 0, 2 ) );
        /* 1197 */
        paramString = paramString.substring( 2 );

        /* 1199 */
        styleBegin = styleEnd = "";

        /* 1201 */
        if ( i == numberOfPCs && bigComputerFont ) {
            useComputerFont();
            /* 1202 */
        } else if ( i == 0 ) {
            useGmFont();
        }
        try {
            /* 1206 */
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, players[i].getChatColor() );

            /* 1208 */
            chatDocument.insertString( chatDocument.getLength(), players[i].toString() +
                    " . o O ( " +
                    paramString +
                    " )\n", textAttributes );

            /* 1210 */
            displayArea.setDocument( chatDocument );
            /* 1211 */
            if ( autoScroll ) {
                displayArea.setCaretPosition( chatDocument.getLength() );
            }
            /* 1212 */
            if ( keepLog ) {
                String str;
                /* 1215 */
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
                    /* 1216 */ {
                    str = players[i].toString() + " . o O ( " + paramString + " )";
                }
                /* 1217 */
                logger.logEntry( str );
            }

            /* 1220 */
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, textColor );
        } catch ( BadLocationException localBadLocationException ) {
            /* 1225 */
            System.err.println( "Unhandled exception. (Bad Location)" );
        }

        /* 1228 */
        if ( i == numberOfPCs && bigComputerFont ) {
            restoreOriginalFont();
            /* 1229 */
        } else if ( i == 0 ) {
            restoreOriginalFont();
        }

        /* 1232 */
        if ( soundIsOn && soundMenu.newTextMenuItem.isSelected() ) {
            soundPlayer.play( 7 );
        }
    }

    public static synchronized void observerChat( String paramString ) {
        /* 1262 */
        String str1 = paramString.substring( 0, paramString.indexOf( "@~!~~" ) );
        /* 1263 */
        paramString = paramString.substring( paramString.indexOf( "@~!~~" ) + 5 );
        try {
            /* 1267 */
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, Color.gray );

            /* 1269 */
            chatDocument.insertString( chatDocument.getLength(), "   " + str1 + ": ", textAttributes );

            /* 1271 */
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, textColor.darker() );

            /* 1273 */
            chatDocument.insertString( chatDocument.getLength(), paramString + "\n", textAttributes );


            /* 1276 */
            displayArea.setDocument( chatDocument );
            /* 1277 */
            if ( autoScroll ) {
                displayArea.setCaretPosition( chatDocument.getLength() );
            }
            /* 1278 */
            if ( keepLog ) {
                String str2;
                /* 1281 */
                if ( htmlLog ) {
                    str2 = "<span class=\"observer\">" +
                            str1 +
                            ": </span><span class=\"gray\">" +
                            paramString +
                            "</span><br/>";
                } else
                    /* 1282 */ {
                    str2 = str1 + ": " + paramString;
                }
                /* 1283 */
                logger.logEntry( str2 );
            }
        } catch ( BadLocationException localBadLocationException ) {
            /* 1289 */
            System.err.println( "Unhandled exception. (Bad Location)" );
        }

        /* 1292 */
        if ( soundIsOn && soundMenu.newObserverTextMenuItem.isSelected() ) {
            soundPlayer.play( 7 );
        }
    }

    public static void privateMessageHandler( String paramString, boolean paramBoolean ) {
        /* 1298 */
        int i = Integer.parseInt( paramString.substring( 0, 2 ) );
        /* 1299 */
        pmTargetPlayer = players[i];
        /* 1300 */
        if ( pmTargetPlayer == myPlayer ) {
            /* 1302 */
            if ( paramBoolean && soundIsOn && soundMenu.newPMAlertMenuItem.isSelected() ) {
                soundPlayer.play( 19 );
            }
            /* 1303 */
            int j = Integer.parseInt( paramString.substring( 2, 4 ) );
            /* 1304 */
            paramString = paramString.substring( 4 );
            /* 1305 */
            players[j].statusPanel.statusNewMessage( true );
            /* 1306 */
            String str1 = players[j].getName().substring( 0, players[j].getName().indexOf( "-" ) );
            try {
                /* 1310 */
                PrivateMessagePane.pmAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, players[j].getChatColor() );

                /* 1312 */
                PMPane[j].privateMessageDocument.insertString( PMPane[j].privateMessageDocument.getLength(), "  " +
                        str1 +
                        ": ", PrivateMessagePane.pmAttributes );


                /* 1315 */
                PrivateMessagePane.pmAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, textColor );

                /* 1317 */
                PMPane[j].privateMessageDocument.insertString( PMPane[j].privateMessageDocument.getLength(), paramString +
                        "\n", PrivateMessagePane.pmAttributes );


                /* 1320 */
                PMPane[j].displayArea.setDocument( PMPane[j].privateMessageDocument );
                /* 1321 */
                if ( autoScroll ) {
                    PMPane[j].displayArea.setCaretPosition( PMPane[j].privateMessageDocument.getLength() );
                }
                /* 1323 */
                if ( keepLog ) {
                    String str2;
                    /* 1326 */
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
                        /* 1327 */ {
                        str2 = "(" + players[j].toString() + " -> " + myPlayer.toString() + "): " + paramString;
                    }
                    /* 1328 */
                    logger.logEntry( str2 );
                }
            } catch ( BadLocationException localBadLocationException ) {
                /* 1335 */
                System.err.println( "Unhandled exception. (Bad Location)" );
            }
        } else {
            /* 1341 */
            pmTargetPlayer.specificSend( "200" + paramString );
        }
    }

    public static void sendChat( String paramString ) {
        /* 1348 */
        String str1 = paramString;
        /* 1349 */
        str1 = str1.replace( '\n', ' ' );


        /* 1352 */
        if ( !str1.equals( "" ) && !str1.equals( " " ) && !str1.endsWith( "\n" ) ) {

            /* 1355 */
            if ( ( str1.startsWith( "/" ) || str1.startsWith( "'" ) ) &&
                    !spoofCheckBox.isSelected() &&
                    !allowGMEmotes ) {

                /* 1358 */
                JParanoia.warningMessage( "GM Emotes not allowed", "You have attempted to use the speech or action\nkey while not spoofing a character.\n\nTo permit this, go to the Options menu and\nenable \"Allow GM Emotes\"." );








                /* 1367 */
                spoofCheckBox.requestFocus();
                /* 1368 */
                inputLine.setText( inputLine.getText().substring( 0, inputLine.getText().length() - 1 ) );
            } else {
                String str2;

                /* 1374 */
                if ( str1.startsWith( "/" ) ) {
                    /* 1376 */
                    str2 = "110";
                    /* 1377 */
                    spamString( str2 + currentPlayerID + str1.substring( 1 ) );
                    /* 1378 */
                    actionChat( currentPlayerID + str1.substring( 1 ) );
                }
                /* 1381 */
                else if ( str1.startsWith( "'" ) ) {
                    /* 1383 */
                    str2 = "120";
                    /* 1384 */
                    if ( Integer.parseInt( currentPlayerID ) == numberOfPCs && computerAllCaps ) {
                        str1 = str1.toUpperCase();
                    }
                    /* 1385 */
                    spamString( str2 + currentPlayerID + str1.substring( 1 ) );
                    /* 1386 */
                    speechChat( currentPlayerID + str1.substring( 1 ) );
                }
                /* 1389 */
                else if ( str1.startsWith( "\\" ) ) {
                    /* 1391 */
                    str2 = "130";
                    /* 1392 */
                    spamString( str2 + currentPlayerID + str1.substring( 1 ) );
                    /* 1393 */
                    thoughtChat( currentPlayerID + str1.substring( 1 ) );
                } else {
                    /* 1397 */
                    str2 = "100";
                    /* 1398 */
                    spamString( str2 + currentPlayerID + str1 );
                    /* 1399 */
                    generalChat( currentPlayerID + str1 );
                }

                /* 1402 */
                if ( spoofCheckBox.isSelected() && optionsMenu.singleUseSpoofMenuItem.isSelected() ) {
                    spoofCheckBox.doClick();
                }
                /* 1404 */
                inputLine.setText( "" );
            }
        }
        /* 1407 */
        if ( str1.endsWith( "\n" ) || str1.equals( "" ) ) {

            /* 1410 */
            inputLine.setText( "" );
        }
    }

    static String nameCompletion( String paramString, boolean paramBoolean ) {
        /* 1418 */
        String str1 = "";
        /* 1419 */
        String str2 = "";

        /* 1421 */
        if ( paramBoolean ) {
            paramString = paramString.substring( 0, paramString.length() - lastNameCompleted.length() + 1 );
        }
        /* 1423 */
        st = new java.util.StringTokenizer( paramString );

        /* 1425 */
        str1 = st.nextToken();

        /* 1427 */
        while ( st.hasMoreTokens() ) {
            /* 1428 */
            str2 = str2 + str1 + " ";
            /* 1429 */
            str1 = st.nextToken();
        }

        /* 1432 */
        if ( paramBoolean ) {
            /* 1433 */
            if ( lastCompletionPlayer == sortedNames.size() - 1 ) {
                /* 1434 */
                lastCompletionPlayer = 0;
                /* 1435 */
                lastNameCompleted = ( (ServerPlayer) sortedNames.get( lastCompletionPlayer ) ).getName();
                /* 1436 */
                return str2 + lastNameCompleted;
            }

            /* 1439 */
            lastNameCompleted = ( (ServerPlayer) sortedNames.get( ++lastCompletionPlayer ) ).getName();
            /* 1440 */
            return str2 + lastNameCompleted;
        }




        /* 1446 */
        for ( int i = 0;
              i < sortedNames.size() &&
                      str1.compareToIgnoreCase( ( (ServerPlayer) sortedNames.get( i ) ).getName() ) > 0;
              i++ ) {

            /* 1447 */
            if ( i < sortedNames.size() ) {
                lastCompletionPlayer = i;
            } else
                /* 1448 */ {
                lastCompletionPlayer = sortedNames.size() - 1;
            }
            /* 1449 */
            lastNameCompleted = ( (ServerPlayer) sortedNames.get( lastCompletionPlayer ) ).getName();
        }
        /* 1450 */
        return str2 + lastNameCompleted;
    }

    public static void clearInputLine() {
        /* 1464 */
        inputLine.selectAll();
        /* 1465 */
        inputLine.replaceSelection( "" );
    }

    public static void setSendMainText( boolean paramBoolean ) {
        /* 1494 */
        sendMainText = paramBoolean;
    }

    public static void globalPM() {
        /* 1501 */
        spamString( "210999" );

        /* 1503 */
        String str = (String) javax.swing.JOptionPane.showInputDialog( null, "Enter your global private message:", "Global PM", -1, null, null, null );


        /* 1506 */
        if ( str != null && !str.equals( "" ) ) {

            /* 1509 */
            for ( int i = 1; i < numberOfPCs; i++ ) {
                /* 1511 */
                if ( players[i].isLoggedIn() ) {
                    /* 1513 */
                    players[i].specificSend( "200" + players[i].getID() + myPlayer.getID() + str );
                    /* 1514 */
                    PMPane[i].addMyMessage( str );
                }
            }
        }
        /* 1517 */
        spamString( "211" );
    }

    private static void startCombat() {
        /* 1524 */
        if ( chatThreads.size() == 0 ) {
            absoluteChat( "Kind of hard to have combat without combatants." );
        } else {
            try {
                /* 1527 */
                combatFrame = new CombatFrame();

                /* 1529 */
                combatFrame.setVisible( true );
                /* 1530 */
                freezeButton.setEnabled( false );
                /* 1531 */
                combatButton.setEnabled( false );
                /* 1532 */
                if ( soundIsOn && soundMenu.combatAlertMenuItem.isSelected() ) {
                    soundPlayer.play( 21 );
                }
                /* 1533 */
                if ( soundIsOn && soundMenu.combatMusicMenuItem.isSelected() ) {
                    soundPlayer.startCombatMusic();
                    combatMusicIsPlaying = true;
                }
                /* 1534 */
                spamString( "199*** COMBAT ROUND BEGINS ***" );
                /* 1535 */
                absoluteChat( "*** COMBAT ROUND BEGINS ***" );
                /* 1536 */
                spamString( "599" );
                /* 1537 */
                freezePlayers();
            } catch ( NoClassDefFoundError localNoClassDefFoundError ) {
                /* 1542 */
                errLog = new jparanoia.shared.ErrorLogger( "cmbt", localNoClassDefFoundError.toString() +
                        " in JPServer.startCombat()" );
                /* 1543 */
                localNoClassDefFoundError.printStackTrace( errLog.out );
                /* 1544 */
                errLog.closeLog();
                /* 1545 */
                errLog = null;
                /* 1546 */
                errorMessage( "CombatFrame - class definition not found", "The CombatFrame class failed to load. The combat manager\nis not available. An error log has been created in your logs\ndirectory. Please notify the author. You will need to exit\nand relaunch the server to correct this problem." );
            }
        }
    }

    public static void freezePlayers() {
        /* 1671 */
        frozen = true;
        /* 1672 */
        freezeButton.setText( "Unfreeze" );
        /* 1673 */
        spamString( "052" );
        /* 1674 */
        for ( int i = 0; i < troubleshooters.length; i++ ) {
            /* 1676 */
            troubleshooters[i].statusPanel.freeze();
        }
        /* 1678 */
        if ( soundIsOn && soundMenu.freezeUnfreezeMenuItem.isSelected() ) {
            soundPlayer.play( 10 );
        }
    }

    public static void notifyPlayersOfDeath( ServerPlayer paramServerPlayer ) {
        /* 1557 */
        spamString( "060" + paramServerPlayer.getID() );
        /* 1558 */
        if ( soundIsOn && soundMenu.deathAlertMenuItem.isSelected() ) {
            soundPlayer.play( 14 );
        }
    }

    public static void notifyPlayersOfUndeath( ServerPlayer paramServerPlayer ) {
        /* 1563 */
        spamString( "061" + paramServerPlayer.getID() );
    }

    public static void sendingPrivateMessage( ServerPlayer paramServerPlayer ) {
        /* 1598 */
        spamString( "210" + paramServerPlayer.getID() );
    }

    public static void setTitleMessage( String paramString ) {
        /* 1603 */
        myTitle.setExtra( paramString );
        /* 1604 */
        frame.setTitle( myTitle.get() );
        /* 1605 */
        spamString( "013" + paramString );
    }

    public static void clearTitleMessage() {
        /* 1610 */
        myTitle.clearExtra();
        /* 1611 */
        frame.setTitle( myTitle.get() );
        /* 1612 */
        spamString( "013" );
    }

    public static void setAnnouncement() {
        /* 1617 */
        new javax.swing.JOptionPane();
        announcement = (String) javax.swing.JOptionPane.showInputDialog( null, "Enter announcement:", "Set Announcement...", -1, null, null, announcement );
    }

    public static String getAnnouncement() {
        /* 1623 */
        String str1 = "";
        try {
            /* 1628 */
            java.io.BufferedReader localBufferedReader = new java.io.BufferedReader( new java.io.FileReader( "announcement.txt" ) );

            /* 1630 */
            String str2 = null;

            /* 1632 */
            while ( ( str2 = localBufferedReader.readLine() ) != null ) {
                /* 1633 */
                str1 = str1 + "199" + str2 + "\n";
            }
        } catch ( java.io.FileNotFoundException localFileNotFoundException ) {
            /* 1639 */
            System.out.println( "FileNotFoundException: Can not locate announcement.txt" );
        } catch ( Exception localException ) {
            /* 1644 */
            localException.printStackTrace();
        }

        /* 1647 */
        return str1;
    }

    public static void clearAnnouncement() {
        /* 1652 */
        announcement = "";
    }

    public static void mute( String paramString ) {
        /* 1657 */
        spamString( "051" + paramString );
        /* 1658 */
        if ( soundIsOn && soundMenu.mutedUnmutedMenuItem.isSelected() ) {
            soundPlayer.play( 8 );
        }
    }

    public static void unmute( String paramString ) {
        /* 1664 */
        spamString( "050" + paramString );
        /* 1665 */
        if ( soundIsOn && soundMenu.mutedUnmutedMenuItem.isSelected() ) {
            soundPlayer.play( 9 );
        }
    }

    public static void unfreezePlayers() {
        /* 1683 */
        frozen = false;
        /* 1684 */
        freezeButton.setText( "Freeze" );
        /* 1685 */
        spamString( "053" );
        /* 1686 */
        for ( int i = 0; i < troubleshooters.length; i++ ) {
            /* 1688 */
            troubleshooters[i].statusPanel.unfreeze();
        }
        /* 1690 */
        if ( soundIsOn && soundMenu.freezeUnfreezeMenuItem.isSelected() ) {
            soundPlayer.play( 11 );
        }
    }

    public static String stripComments( String paramString ) {
        /* 1699 */
        StringBuffer localStringBuffer = new StringBuffer( paramString );



        /* 1703 */
        for ( int j = 0; j < localStringBuffer.length(); j++ ) {
            int i;
            /* 1705 */
            if ( localStringBuffer.charAt( j ) == '/' && localStringBuffer.charAt( j + 1 ) == '*' ) {
                /* 1707 */
                i = j + 2;

                /* 1709 */
                while ( i < localStringBuffer.length() &&
                        ( localStringBuffer.charAt( i ) != '*' || localStringBuffer.charAt( i + 1 ) != '/' ) ) {
                    /* 1710 */
                    i++;
                }
                /* 1712 */
                localStringBuffer.delete( j, i + 2 );
            }
            /* 1715 */
            else if ( localStringBuffer.charAt( j ) == '/' && localStringBuffer.charAt( j + 1 ) == '/' ) {
                /* 1717 */
                i = j + 2;

                /* 1719 */
                while ( i < localStringBuffer.length() && localStringBuffer.charAt( i ) != '\n' ) {
                    /* 1720 */
                    i++;
                }
                /* 1722 */
                localStringBuffer.delete( j, i );
            }
        }


        /* 1727 */
        return localStringBuffer.toString();
    }

    public static void observerHasJoined( String paramString ) {
        /* 1734 */
        absoluteSpam( "Observer " + paramString + " has joined." );
    }

    public static void observerHasLeft( String paramString ) {
        /* 1740 */
        absoluteSpam( "Observer " + paramString + " has disconnected." );
    }

    public static void repaintMenus() {
        /* 1792 */
        spoofComboBox.repaint();
        /* 1793 */
        charsheetPanel.playerComboBox.repaint();
    }

    public static void reassignThreadNumbers() {
        /* 1799 */
        for ( int i = 0; i < chatThreads.size(); i++ ) {
            /* 1801 */
            thisThread = (ServerChatThread) chatThreads.elementAt( i );
            /* 1802 */
            thisThread.threadNumber = i;
        }
    }

    public static void startServer() {
        /* 1809 */
        if ( registerGame && gameDescription.equals( defaultGameDescription ) )
            /* 1810 */ {
            setGameDescriptionMenuItem.doClick();
        }
        /* 1811 */
        inputLine.setEnabled( true );
        /* 1812 */
        inputLine.requestFocus();

        /* 1814 */
        servSocketThread = new ServerSocketThread();
        /* 1815 */
        servSocketThread.start();
        /* 1816 */
        if ( registerGame ) {
            /* 1818 */
            jparanoia.shared.GameRegistrar.addGame( gameDescription );
            /* 1819 */
            String str = jparanoia.shared.GameRegistrar.getIP();
            /* 1820 */
            if ( str != null && !str.equals( "fail" ) ) {
                /* 1822 */
                ipLabel.setText( "  IP: " + str );
                /* 1823 */
                if ( !behindRouter && !str.equals( localIP.getHostAddress() ) ) {
                    /* 1825 */
                    behindRouter = true;

                    /* 1827 */
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
















        /* 1847 */
        if ( soundIsOn && soundMenu.connectedDisconnectedMenuItem.isSelected() ) {
            soundPlayer.play( 1 );
        }
    }

    public static void stopServer() {
        try {
            /* 1855 */
            serverRunning = false;
            /* 1856 */
            servSocketThread.listening = false;
            /* 1857 */
            servSocketThread.serverSocket.close();
            /* 1858 */
            if ( gameRegistered ) {
                jparanoia.shared.GameRegistrar.removeGame();
            }
            /* 1859 */
            if ( soundIsOn && soundMenu.connectedDisconnectedMenuItem.isSelected() ) {
                soundPlayer.play( 2 );
            }
        } catch ( java.net.SocketException localSocketException ) {
            /* 1864 */
            System.out.println( "Socket Exception while closing serversocket" );
            /* 1865 */
            localSocketException.printStackTrace();
        } catch ( java.io.IOException localIOException ) {
            /* 1870 */
            System.out.println( "I/O Exception while closing serversocket" );
            /* 1871 */
            localIOException.printStackTrace();
        }
    }

    public static String getVersionName() {
        /* 1877 */
        return VERSION_NAME;
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\JPServer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */
