package jparanoia.client;
import java.awt.Color;
import java.io.PrintWriter;
import static java.lang.invoke.MethodHandles.lookup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class JPClient extends jparanoia.shared.JParanoia {
    /*   52 */   public static final jparanoia.shared.JPVersionNumber VERSION_NUMBER = new jparanoia.shared.JPVersionNumber( 1, 31, 1 );
    /*   53 */   public static final String VERSION_NAME = VERSION_NUMBER.toString();
    /*   54 */   public static final jparanoia.shared.JPVersionNumber MIN_COMPATIBLE_VERSION_NUMBER = new jparanoia.shared.JPVersionNumber( 1, 31, 0 );
    /*   22 */   static Integer mainFontSize = new Integer( 99 );
    /*   24 */   static int computerFontIncrease = 0;
    static int maxNumClones;
    /*   27 */   static boolean observer = false;
    /*   28 */   static boolean disconnectCalled = false;
    static boolean keepLog;
    /*   30 */   static boolean htmlLog;
    static boolean hearObservers = false;
    static JPanel mainPanel;
    static JPanel inputLinePanel;
    static JMenuItem showAllPMWindowsMenuItem;
    /*   36 */   static java.util.Random rand = new java.util.Random();
    /*   38 */   static String addressToTry = "127.0.0.1";
    static jparanoia.shared.TitleClass myTitle;
    static ChatListenerThread myListener;
    static PrintWriter out;
    static java.io.BufferedReader in;
    /*   44 */   static java.net.Socket mySock = null;
    static JTextPane charsheetArea;
    static java.awt.Container contentPane;
    static JPanel inputPanel;
    static JPanel infoPanel;
    static JPanel statusPanel;
    static JFrame charsheetFrame;
    static javax.swing.JOptionPane connectOPane;
    static javax.swing.JOptionPane errorPane;
    static javax.swing.JMenuBar menuBar;
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
    static javax.swing.JRadioButtonMenuItem whiteOnBlackButton;
    static javax.swing.JRadioButtonMenuItem blackOnWhiteButton;
    static javax.swing.JRadioButtonMenuItem serifButton;
    static javax.swing.JRadioButtonMenuItem sansSerifButton;
    static javax.swing.JRadioButtonMenuItem monospacedButton;
    /*   80 */   static javax.swing.JRadioButtonMenuItem size10Button;
    static javax.swing.JRadioButtonMenuItem size12Button;
    static javax.swing.JRadioButtonMenuItem size14Button;
    static javax.swing.JRadioButtonMenuItem size16Button;
    static javax.swing.JRadioButtonMenuItem size18Button;
    static javax.swing.JRadioButtonMenuItem size24Button;
    static JCheckBoxMenuItem autoScrollMenuItem;
    static JCheckBoxMenuItem fontBoldMenuItem;
    static JCheckBoxMenuItem chatNotifyNewPMMenuItem;
    static javax.swing.JScrollPane scrollPane;
    static javax.swing.JScrollPane charScrollPane;
    static javax.swing.JTable lipTable;
    static JTextField inputLine;
    static JLabel connectionStatusLabel;
    static JLabel messageStatusLabel;
    static javax.swing.ImageIcon notConnectedIcon;
    static javax.swing.ImageIcon connectedIcon;
    static javax.swing.ImageIcon mutedIcon;
    static javax.swing.ImageIcon incomingMsgIcon;
    static javax.swing.ImageIcon nullMsgIcon;
    static javax.swing.ImageIcon gmSendingMsgIcon;
    static javax.swing.ImageIcon frozenIcon;
    static javax.swing.ImageIcon combatIcon;
    static java.awt.Dimension lipTablePreferredSize = new java.awt.Dimension( 130, 160 );
    static CombatTurnFrame turnFrame;
    static SimpleAttributeSet charsheetAttributes;
    /*   88 */   static SimpleAttributeSet systemTextAttributes = new SimpleAttributeSet();
    static Color[] brightColors;
    static Color[] darkColors;
    static Color newColor;
    /*   99 */   static boolean fontIsBold = false;
    /*  100 */   static boolean muted = false;
    /*  101 */   static boolean frozen = false;
    /*  102 */   static boolean inCombat = false;
    static String[] nonConnectedPlayers;
    static String tempPlayerID;
    static String MY_PLAYER_ID;
    static int MY_PLAYER_NUMBER;
    /*  108 */   static String currentColorScheme = "";
    /*  109 */   static String newColorScheme = "White on Black";
    static String styleBegin;
    /*  111 */   static String styleEnd = "";
    /*  113 */   static String realName = "default";
    /*  115 */   static boolean stayConnected = false;
    /*  116 */   static boolean connected = false;
    /*  117 */   static boolean loggedIn = false;
    /*  118 */   static boolean firstCharsheetUpdate = true;
    static boolean clobberAqua;
    /*  125 */   static Object[][] lipArray = new Object[40][1];
    /*  126 */   static Object[] columnNameArray = {"Players"};
    static java.util.Vector onlinePlayers;
    /*  129 */   static java.util.Vector lipRowVector = new java.util.Vector();
    /*  130 */   static java.util.Vector columnNameVector = new java.util.Vector();
    static int numberOfPlayers;
    static int numberOfPCs;
    static ClientPlayer[] playerList;
    static ClientPlayer myPlayer;
    static PrivateMessageFrame[] PMFrame;

    public JPClient() {
        /*  166 */
        clobberAqua = ( (Boolean) prefs.getPref( 18 ) ).booleanValue();

        /*  168 */
        if ( clobberAqua ) {
            try {
                /*  172 */
                javax.swing.UIManager.setLookAndFeel( javax.swing.UIManager.getCrossPlatformLookAndFeelClassName() );
            } catch ( Exception localException ) {
                /*  174 */
                System.out.println( "Exception while setting L&F." );
            }
        }
        /*  176 */
        jparanoia.shared.JParanoia.appInfo = "JParanoia Client " + VERSION_NAME;

        /*  178 */
        frame.setTitle( "JParanoia Client " + VERSION_NAME );
        /*  179 */
        myTitle = new jparanoia.shared.TitleClass( "JParanoia Client", VERSION_NAME, true );
        /*  180 */
        frame.setIconImage( java.awt.Toolkit.getDefaultToolkit()
                .getImage( lookup().lookupClass().getClassLoader().getResource( "graphics/jparanoiaIcon.jpg" ) ) );

        /*  182 */
        frame.addWindowListener( new java.awt.event.WindowAdapter() {
            public void windowClosing( java.awt.event.WindowEvent paramAnonymousWindowEvent ) {
            }


            /*  188 */
        } );
        /*  189 */
        frame.setSize( 700, 500 );

        /*  191 */
        displayArea = new JTextPane();
        /*  192 */
        displayArea.setEditable( false );
        /*  193 */
        displayArea.setEnabled( true );
        /*  194 */
        displayArea.setDisabledTextColor( Color.white );
        /*  195 */
        displayArea.setBackground( Color.black );




        /*  200 */
        scrollPane = new javax.swing.JScrollPane( displayArea, 22, 31 );


        /*  203 */
        chatDocument = new javax.swing.text.DefaultStyledDocument();




        /*  208 */
        brightColors = new jparanoia.shared.BrightColorArray().getColors();

        /*  210 */
        darkColors = new Color[10];

        /*  212 */
        for ( int i = 0; i < 10; i++ ) {
            /*  214 */
            switch ( i ) {
                case 0:
                    /*  216 */
                    newColor = new Color( 0.1F, 0.1F, 0.1F );
                    break;
                /*  217 */
                case 1:
                    newColor = new Color( 0.6F, 0.3F, 0.0F );
                    break;
                /*  218 */
                case 2:
                    newColor = new Color( 0.0F, 0.4F, 0.0F );
                    break;
                /*  219 */
                case 3:
                    newColor = new Color( 0.2F, 0.6F, 0.4F );
                    break;
                /*  220 */
                case 4:
                    newColor = new Color( 0.3F, 0.1F, 0.8F );
                    break;
                /*  221 */
                case 5:
                    newColor = new Color( 0.5F, 0.1F, 0.5F );
                    break;
                /*  222 */
                case 6:
                    newColor = new Color( 0.3F, 0.3F, 0.3F );
                    break;
                /*  223 */
                case 7:
                    newColor = new Color( 0.0F, 0.0F, 0.4F );
                    break;
                /*  224 */
                case 8:
                    newColor = new Color( 0.4F, 0.0F, 0.0F );
                    break;
                /*  225 */
                case 9:
                    newColor = new Color( 0.1F, 0.4F, 0.4F );
                    break;
                /*  226 */
                default:
                    System.out.println( "Error: Out of colors!" );
            }

            /*  229 */
            darkColors[i] = newColor;
        }

        /*  232 */
        textAttributes = new SimpleAttributeSet();
        /*  233 */
        textAttributes.addAttribute( javax.swing.text.StyleConstants.FontConstants.Size, prefs.getPref( 15 ) );
        /*  234 */
        textAttributes.addAttribute( javax.swing.text.StyleConstants.FontConstants.Family, prefs.getPref( 16 ) );






        /*  241 */
        setColorScheme();

        /*  243 */
        inputLine = new JTextField( 35 );
        /*  244 */
        inputLine.setEnabled( false );
        /*  245 */
        inputLine.addActionListener( new java.awt.event.ActionListener() {
            public void actionPerformed( java.awt.event.ActionEvent paramAnonymousActionEvent ) {
                /*  247 */
                if ( !JPClient.muted && !JPClient.frozen ) {
                    JPClient.sendChat( JPClient.inputLine.getText() );
                }
            }
            /*  250 */
        } );
        /*  251 */
        inputLine.addKeyListener( new java.awt.event.KeyListener() {
            public void keyTyped( java.awt.event.KeyEvent paramAnonymousKeyEvent ) {
            }

            public void keyPressed( java.awt.event.KeyEvent paramAnonymousKeyEvent ) {
                /*  255 */
                jparanoia.shared.JParanoia.previousKey = jparanoia.shared.JParanoia.thisKey;
                /*  256 */
                jparanoia.shared.JParanoia.thisKey = paramAnonymousKeyEvent.getKeyCode();
                /*  257 */
                if ( jparanoia.shared.JParanoia.thisKey == 9 ) {
                    /*  259 */
                    paramAnonymousKeyEvent.consume();

                    /*  261 */
                    if ( JPClient.inputLine.getText().length() > 0 ) {
                        /*  263 */
                        String str = JPClient.inputLine.getText();
                        /*  264 */
                        if ( str.startsWith( "'" ) && str.length() > 1 ) {
                            /*  265 */
                            JPClient.inputLine.setText( "'" +
                                    JPClient.nameCompletion( str.substring( 1 ), jparanoia.shared.JParanoia.thisKey ==
                                            jparanoia.shared.JParanoia.previousKey ) );
                            /*  266 */
                        } else if ( !str.startsWith( "'" ) ) {
                            /*  267 */
                            JPClient.inputLine.setText( JPClient.nameCompletion( str, jparanoia.shared.JParanoia.thisKey ==
                                    jparanoia.shared.JParanoia.previousKey ) );
                        }
                    }
                }
            }

            public void keyReleased( java.awt.event.KeyEvent paramAnonymousKeyEvent ) {
            }
            /*  280 */
        } );
        /*  281 */
        inputPanel = new JPanel();

        /*  283 */
        inputPanel.add( inputLine, "Center" );

        /*  285 */
        menuBar = new javax.swing.JMenuBar();
        /*  286 */
        frame.setJMenuBar( menuBar );

        /*  288 */
        connectionMenu = new JMenu( "Connection" );
        /*  289 */
        connectMenuItem = new JMenuItem( "Connect..." );
        /*  290 */
        connectMenuItem.addActionListener( new java.awt.event.ActionListener() {
            public void actionPerformed( java.awt.event.ActionEvent paramAnonymousActionEvent ) {
            }

            /*  295 */
        } );
        /*  296 */
        disconnectMenuItem = new JMenuItem( "Disconnect" );
        /*  297 */
        disconnectMenuItem.addActionListener( new java.awt.event.ActionListener() {
            /*  299 */
            public void actionPerformed( java.awt.event.ActionEvent paramAnonymousActionEvent ) {
                JPClient.disconnect( false );
            }
            /*  300 */
        } );
        /*  301 */
        connectionMenu.add( connectMenuItem );
        /*  302 */
        connectionMenu.add( disconnectMenuItem );
        /*  303 */
        disconnectMenuItem.setEnabled( false );

        /*  305 */
        menuBar.add( connectionMenu );

        /*  307 */
        fontMenu = new FontMenu( "Font" );

        /*  309 */
        menuBar.add( fontMenu );

        /*  311 */
        optionsMenu = new JMenu( "Options" );





        /*  317 */
        autoScrollMenuItem = new JCheckBoxMenuItem( "Autoscroll" );
        /*  318 */
        autoScrollMenuItem.setSelected( true );
        /*  319 */
        autoScrollMenuItem.addActionListener( new java.awt.event.ActionListener() {
            public void actionPerformed( java.awt.event.ActionEvent paramAnonymousActionEvent ) {
                /*  321 */
                jparanoia.shared.JParanoia.autoScroll = !jparanoia.shared.JParanoia.autoScroll;
                /*  323 */
            }
        } );
        /*  324 */
        chatNotifyNewPMMenuItem = new JCheckBoxMenuItem( "New PM alert in chat" );
        /*  325 */
        chatNotifyNewPMMenuItem.setSelected( ( (Boolean) prefs.getPref( 19 ) ).booleanValue() );








        /*  334 */
        optionsMenu.add( soundMenu );
        /*  335 */
        optionsMenu.addSeparator();
        /*  336 */
        optionsMenu.add( autoScrollMenuItem );
        /*  337 */
        optionsMenu.add( chatNotifyNewPMMenuItem );

        /*  339 */
        optionsMenu.addSeparator();
        /*  340 */
        optionsMenu.add( aboutBoxMenuItem );

        /*  342 */
        menuBar.add( optionsMenu );

        /*  344 */
        pmWindowsMenu = new JMenu( "PM windows" );

        /*  346 */
        showAllPMWindowsMenuItem = new JMenuItem( "Show all" );
        /*  347 */
        showAllPMWindowsMenuItem.addActionListener( new java.awt.event.ActionListener() {
            public void actionPerformed( java.awt.event.ActionEvent paramAnonymousActionEvent ) {
                /*  349 */
                for ( int i = 0; i < JPClient.numberOfPCs; i++ ) {
                    /*  351 */
                    if ( i != JPClient.MY_PLAYER_NUMBER ) {
                        JPClient.PMFrame[i].setVisible( true );
                    }
                }
            }
            /*  354 */
        } );
        /*  355 */
        showAllPMWindowsMenuItem.setEnabled( false );

        /*  357 */
        pmWindowsMenu.add( showAllPMWindowsMenuItem );
        /*  358 */
        pmWindowsMenu.addSeparator();

        /*  360 */
        menuBar.add( pmWindowsMenu );

        /*  362 */
        infoPanel = new JPanel();

        /*  364 */
        infoPanel.setLayout( new javax.swing.BoxLayout( infoPanel, 1 ) );

        /*  366 */
        lipArray[0][0] = "Player List";

        /*  368 */
        lipTable = new javax.swing.JTable( lipArray, columnNameArray );
        /*  369 */
        lipTable.setMinimumSize( lipTablePreferredSize );
        /*  370 */
        lipTable.setPreferredSize( lipTablePreferredSize );

        /*  372 */
        lipTable.setShowGrid( false );
        /*  373 */
        lipTable.setEnabled( false );


        /*  376 */
        statusPanel = new JPanel();
        /*  377 */
        statusPanel.setLayout( new javax.swing.BoxLayout( statusPanel, 0 ) );

        /*  379 */
        notConnectedIcon = new javax.swing.ImageIcon( lookup().lookupClass().getClassLoader().getResource( "graphics/notConnectedIcon.jpg" ) );
        /*  380 */
        connectedIcon = new javax.swing.ImageIcon( lookup().lookupClass().getClassLoader().getResource( "graphics/connectedIcon.jpg" ) );
        /*  381 */
        mutedIcon = new javax.swing.ImageIcon( lookup().lookupClass().getClassLoader().getResource( "graphics/mutedIcon.jpg" ) );
        /*  382 */
        frozenIcon = new javax.swing.ImageIcon( lookup().lookupClass().getClassLoader().getResource( "graphics/frozenIcon.jpg" ) );
        /*  383 */
        nullMsgIcon = new javax.swing.ImageIcon( lookup().lookupClass().getClassLoader().getResource( "graphics/nullMsgIcon.jpg" ) );
        /*  384 */
        incomingMsgIcon = new javax.swing.ImageIcon( lookup().lookupClass().getClassLoader().getResource( "graphics/incomingMsgIcon.jpg" ) );
        /*  385 */
        gmSendingMsgIcon = new javax.swing.ImageIcon( lookup().lookupClass().getClassLoader().getResource( "graphics/gmSendingMsgIcon.jpg" ) );
        /*  386 */
        combatIcon = new javax.swing.ImageIcon( lookup().lookupClass().getClassLoader().getResource( "graphics/combatIcon.jpg" ) );

        /*  388 */
        connectionStatusLabel = new JLabel( notConnectedIcon );
        /*  389 */
        messageStatusLabel = new JLabel( nullMsgIcon );

        /*  391 */
        statusPanel.add( connectionStatusLabel );
        /*  392 */
        statusPanel.add( javax.swing.Box.createRigidArea( new java.awt.Dimension( 15, 0 ) ) );
        /*  393 */
        statusPanel.add( messageStatusLabel );

        /*  395 */
        statusPanel.setMinimumSize( new java.awt.Dimension( 100, 30 ) );
        /*  396 */
        statusPanel.setPreferredSize( new java.awt.Dimension( 100, 30 ) );
        /*  397 */
        statusPanel.setMaximumSize( new java.awt.Dimension( 100, 30 ) );

        /*  399 */
        infoPanel.add( lipTable );
        /*  400 */
        infoPanel.add( javax.swing.Box.createRigidArea( new java.awt.Dimension( 0, 5 ) ) );
        /*  401 */
        infoPanel.add( statusPanel );

        /*  403 */
        inputLine.setMinimumSize( new java.awt.Dimension( 100, 22 ) );
        /*  404 */
        inputLine.setMaximumSize( new java.awt.Dimension( 999, 22 ) );
        /*  405 */
        inputLine.setPreferredSize( new java.awt.Dimension( 997, 22 ) );

        /*  407 */
        inputLinePanel = new JPanel();
        /*  408 */
        inputLinePanel.setLayout( new javax.swing.BoxLayout( inputLinePanel, 0 ) );
        /*  409 */
        inputLinePanel.add( javax.swing.Box.createRigidArea( new java.awt.Dimension( 5, 0 ) ) );
        /*  410 */
        inputLinePanel.add( inputLine );
        /*  411 */
        inputLinePanel.add( javax.swing.Box.createRigidArea( new java.awt.Dimension( 5, 0 ) ) );



        /*  415 */
        mainPanel = new JPanel();
        /*  416 */
        mainPanel.setLayout( new javax.swing.BoxLayout( mainPanel, 1 ) );
        /*  417 */
        mainPanel.add( scrollPane );
        /*  418 */
        mainPanel.add( javax.swing.Box.createRigidArea( new java.awt.Dimension( 0, 5 ) ) );
        /*  419 */
        mainPanel.add( inputLinePanel );
        /*  420 */
        mainPanel.add( javax.swing.Box.createRigidArea( new java.awt.Dimension( 0, 5 ) ) );


        /*  423 */
        java.awt.Container localContainer = frame.getContentPane();

        /*  425 */
        localContainer.add( mainPanel, "Center" );
        /*  426 */
        localContainer.add( infoPanel, "East" );

        /*  428 */
        connectOPane = new javax.swing.JOptionPane();
        /*  429 */
        errorPane = new javax.swing.JOptionPane();

        /*  431 */
        textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, Color.white );

        /*  433 */
        systemTextAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, Color.gray );

        /*  435 */
        charsheetFrame = new JFrame( "Character Sheet" );

        /*  437 */
        charsheetFrame.setSize( 500, 300 );

        /*  439 */
        charsheetFrame.setIconImage( java.awt.Toolkit.getDefaultToolkit()
                .getImage( lookup().lookupClass().getClassLoader().getResource( "graphics/jparanoiaIcon.jpg" ) ) );

        /*  441 */
        charsheetArea = new JTextPane();
        /*  442 */
        charsheetArea.setEditable( false );
        /*  443 */
        charsheetArea.setEnabled( true );

        /*  445 */
        charScrollPane = new javax.swing.JScrollPane( charsheetArea, 22, 31 );



        /*  449 */
        charsheetFrame.getContentPane().add( charScrollPane );

        /*  451 */
        charsheetAttributes = new SimpleAttributeSet();
        /*  452 */
        charsheetAttributes.addAttribute( javax.swing.text.StyleConstants.Bold, new Boolean( true ) );
        /*  453 */
        charsheetAttributes.addAttribute( StyleConstants.CharacterConstants.Family, "SansSerif" );
        /*  454 */
        charsheetAttributes.addAttribute( StyleConstants.CharacterConstants.Size, new Integer( 12 ) );

        /*  456 */
        charsheetMenu = new JMenu( "Character Sheet" );

        /*  458 */
        showCharsheetMenuItem = new JMenuItem( "Show character sheet" );
        /*  459 */
        showCharsheetMenuItem.addActionListener( new java.awt.event.ActionListener() {
            public void actionPerformed( java.awt.event.ActionEvent paramAnonymousActionEvent ) {
                /*  461 */
                if ( !JPClient.charsheetFrame.isVisible() ) {
                    JPClient.charsheetFrame.setVisible( true );
                }
            }
            /*  463 */
        } );
        /*  464 */
        charsheetMenu.add( showCharsheetMenuItem );

        /*  466 */
        menuBar.add( charsheetMenu );


        /*  469 */
        displayWrite( Color.green, "JParanoia Client " + VERSION_NAME + "\n\n" );


        /*  472 */
        displayWrite( Color.cyan, "New in this release:\n\n" );



        /*  476 */
        displayWrite( Color.white, "- The Computer's text is now large in the log. GM text is bold.\n- Images are now logged.\n- Blank secret combat turns no longer appear in GM PM window.\n- Other miscellaneous bug fixes.\n\n" );





        /*  482 */
        displayWrite( Color.yellow, "Read the README.TXT" );

        /*  484 */
        displayWrite( Color.white, " for full details.\n\n" );




        /*  489 */
        displayWrite( Color.cyan, "Name Completion:\n" );


        /*  492 */
        displayWrite( Color.white, "Tab acts as a name completion key. See README for details.\n\n" );


        /*  495 */
        displayWrite( Color.cyan, "Expression Keys:\n" );


        /*  498 */
        displayWrite( Color.white, "Single quote ( ' ) = speech     " );

        /*  500 */
        displayWrite( Color.red, "Nory-R-BEE" );
        /*  501 */
        displayWrite( Color.white, " says, \"Welcome to JParanoia.\"\nBackslash ( \\ ) = thought bubble     " );


        /*  504 */
        displayWrite( Color.red, "Nory-R-BEE . o O ( need... food... )\n" );

        /*  506 */
        displayWrite( Color.white, "Slash ( / ) = action     " );

        /*  508 */
        displayWrite( Color.red, "* Nory-R-BEE points to the Donate link and sneers menacingly. *\n\n" );


        /*  511 */
        displayWrite( Color.cyan, "Passwords:\n" );


        /*  514 */
        displayWrite( Color.white, "If you find you can't login because the GM didn't inform you of a password, try " );

        /*  516 */
        displayWrite( Color.yellow, "asdf" );
        /*  517 */
        displayWrite( Color.white, ". This is the default password for all players. Good GMs assign passwords for enhanced security.\n\n" );



        /*  521 */
        displayWrite( Color.white, "For news, bug report forms, a complete version history, or to make a financial contribution, visit the " );

        /*  523 */
        displayWrite( Color.cyan, "JParanoia website: " );

        /*  525 */
        displayWrite( Color.orange, "http://www.byronbarry.com/jparanoia/\n" );


        /*  528 */
        keepLog = ( (Boolean) prefs.getPref( 20 ) ).booleanValue();
        /*  529 */
        htmlLog = ( (Boolean) prefs.getPref( 21 ) ).booleanValue();
        /*  530 */
        realName = (String) prefs.getPref( 22 );

        /*  532 */
        net.roydesign.mac.MRJAdapter.addQuitApplicationListener( new java.awt.event.ActionListener() {
            public void actionPerformed( java.awt.event.ActionEvent paramAnonymousActionEvent ) {
            }

            /*  536 */
        } );
        /*  537 */
        net.roydesign.mac.MRJAdapter.addAboutListener( new java.awt.event.ActionListener() {
            public void actionPerformed( java.awt.event.ActionEvent paramAnonymousActionEvent ) {
                /*  539 */
                jparanoia.shared.JParanoia.aboutBoxMenuItem.doClick();
            }
            /*  541 */
        } );
        /*  542 */
        mainFontSize = (Integer) textAttributes.getAttribute( javax.swing.text.StyleConstants.FontConstants.Size );
    }

    public static void setColorScheme() {
        /*  550 */
        if ( !currentColorScheme.equals( newColorScheme ) ) {
            /*  552 */
            if ( newColorScheme.equals( "White on Black" ) ) {
                /*  554 */
                textColor = Color.white;
                /*  555 */
                displayArea.setBackground( Color.black );
            }
            /*  558 */
            else if ( newColorScheme.equals( "Black on White" ) ) {
                /*  560 */
                textColor = Color.black;
                /*  561 */
                displayArea.setBackground( Color.white );
            } else {
                /*  564 */
                System.out.println( "Error: invalid color logic." );
            }
            /*  566 */
            currentColorScheme = newColorScheme;
            /*  567 */
            assignColorsToCharacters();
        }
    }

    public static void assignColorsToCharacters() {
        /*  574 */
        if ( loggedIn ) {

            /*  577 */
            if ( currentColorScheme.equals( "White on Black" ) ) {
                /*  579 */
                for ( int i = 0; i < numberOfPlayers; i++ ) {
                    /*  581 */
                    playerList[i].setChatColor( brightColors[i] );
                }
            }







            /*  591 */
            System.out.println( "Error: invalid color logic" );
        }
    }

    public static void disconnect( boolean paramBoolean ) {
        /*  722 */
        System.out.print( "disconnect() initiated by " );
        /*  723 */
        if ( paramBoolean ) {
            System.out.println( "server" );
        } else
            /*  724 */ {
            System.out.println( "client" );
        }
        /*  725 */
        disconnectCalled = true;



        /*  729 */
        if ( stayConnected ) {
            int i;
            /*  732 */
            if ( loggedIn && !observer ) {
                /*  734 */
                if ( !paramBoolean ) {
                    out.println( "086" + MY_PLAYER_ID );
                }
                /*  735 */
                for ( i = 0; i < numberOfPCs; ) {
                    /*  737 */
                    if ( i != MY_PLAYER_NUMBER ) {
                        PMFrame[i].dispose();
                    }
                    /*  735 */
                    i++;




                    /*  740 */
                    if ( !paramBoolean ) {
                        out.println( "086" );
                    }
                }
            }
            /*  742 */
            drop();

            /*  744 */
            if ( keepLog ) {
                logger.closeLog();
            }
            /*  746 */
            loggedIn = false;
            /*  747 */
            stayConnected = false;
            /*  748 */
            connectMenuItem.setEnabled( true );
            /*  749 */
            disconnectMenuItem.setEnabled( false );
            /*  750 */
            inputLine.setText( "" );
            /*  751 */
            inputLine.setEnabled( false );

            /*  753 */
            connectionStatusLabel.setIcon( notConnectedIcon );
            /*  754 */
            muted = false;
            /*  755 */
            frozen = false;
            /*  756 */
            pmWindowsMenu.removeAll();
            /*  757 */
            pmWindowsMenu.add( showAllPMWindowsMenuItem );
            /*  758 */
            pmWindowsMenu.addSeparator();
            /*  759 */
            showAllPMWindowsMenuItem.setEnabled( false );
            /*  760 */
            numberOfPCs = 0;
            /*  761 */
            firstCharsheetUpdate = true;
            /*  762 */
            if ( soundIsOn && soundMenu.connectedDisconnectedMenuItem.isSelected() ) {
                soundPlayer.play( 2 );
            }
            /*  763 */
            if ( combatMusicIsPlaying ) {
                soundPlayer.stopCombatMusic();
            }
        } else {
            /*  766 */
            System.out.println( "Already disconnected." );
        }
    }

    public static void drop() {
        try {
            /*  775 */
            in.close();
            /*  776 */
            out.close();
            /*  777 */
            mySock.close();
        } catch ( java.io.IOException localIOException ) {
            /*  782 */
            System.err.println( "Error: unable to close outgoing streams/writers/sockets" );
            /*  783 */
            localIOException.printStackTrace();
        }

        /*  786 */
        connected = false;
        /*  787 */
        stayConnected = false;

        /*  789 */
        connectMenuItem.setEnabled( true );
        /*  790 */
        disconnectMenuItem.setEnabled( false );

        /*  792 */
        inputLine.setText( "" );
        /*  793 */
        inputLine.setEnabled( false );
    }

    public static void sendChat( String paramString ) {
        /* 1098 */
        if ( stayConnected ) {
            /* 1100 */
            String str1 = paramString;

            /* 1102 */
            if ( str1.length() > 1000 ) {
                /* 1104 */
                absoluteChat( "Can't paste that many characters." );
                /* 1105 */
                inputLine.setText( "" );
                /* 1106 */
                return;
            }
            /* 1108 */
            str1 = str1.replace( '\n', ' ' );


            /* 1111 */
            if ( !observer ) {

                /* 1114 */
                if ( str1.startsWith( "`" ) ) {
                    /* 1116 */
                    sendPrivateMessage( "00" + MY_PLAYER_ID + str1.substring( 1 ) );
                    /* 1117 */
                    PMFrame[0].addMyMessage( str1.substring( 1 ) );
                    /* 1118 */
                    inputLine.setText( "" );
                    /* 1119 */
                    return;
                }

                /* 1122 */
                st = new java.util.StringTokenizer( str1, "`" );

                /* 1124 */
                if ( st.hasMoreTokens() ) {
                    str1 = st.nextToken();
                }
                /* 1126 */
                if ( st.hasMoreTokens() ) {

                    /* 1129 */
                    String str3 = st.nextToken();
                    /* 1130 */
                    sendPrivateMessage( "00" + MY_PLAYER_ID + str3 );
                    /* 1131 */
                    PMFrame[0].addMyMessage( str3 );
                }
            }

            /* 1135 */
            if ( !str1.equals( "" ) ) {
                String str2;
                /* 1137 */
                if ( loggedIn ) {

                    /* 1140 */
                    if ( observer ) {
                        /* 1142 */
                        str2 = "099";
                        /* 1143 */
                        out.println( str2 + realName + "@~!~~" + str1 );
                    }
                    /* 1146 */
                    else if ( str1.startsWith( "/" ) ) {
                        /* 1148 */
                        str2 = "110";
                        /* 1149 */
                        out.println( str2 + MY_PLAYER_ID + str1.substring( 1 ) );
                    }
                    /* 1152 */
                    else if ( str1.startsWith( "'" ) ) {
                        /* 1154 */
                        str2 = "120";
                        /* 1155 */
                        out.println( str2 + MY_PLAYER_ID + str1.substring( 1 ) );
                    }
                    /* 1158 */
                    else if ( str1.startsWith( "\\" ) ) {
                        /* 1160 */
                        str2 = "130";
                        /* 1161 */
                        out.println( str2 + MY_PLAYER_ID + str1.substring( 1 ) );
                    } else {
                        /* 1166 */
                        str2 = "100";
                        /* 1167 */
                        out.println( str2 + MY_PLAYER_ID + str1 );
                    }
                } else {
                    /* 1172 */
                    str2 = "901";
                    /* 1173 */
                    out.println( str2 + str1 );
                }
                /* 1175 */
                inputLine.setText( "" );
            }
        }
    }

    public static void sendPrivateMessage( String paramString ) {
        /* 1182 */
        String str1 = paramString;
        /* 1183 */
        String str2 = "200";

        /* 1185 */
        if ( loggedIn && stayConnected ) {
            /* 1187 */
            out.println( str2 + str1 );
        }
    }

    public static void absoluteChat( String paramString ) {
        SimpleAttributeSet localSimpleAttributeSet;
        /* 1406 */
        if ( loggedIn ) {
            localSimpleAttributeSet = systemTextAttributes;
        } else {
            /* 1407 */
            localSimpleAttributeSet = textAttributes;
        }
        try {
            /* 1410 */
            chatDocument.insertString( chatDocument.getLength(), paramString + "\n", localSimpleAttributeSet );


            /* 1413 */
            displayArea.setDocument( chatDocument );
            /* 1414 */
            if ( autoScroll ) {
                displayArea.setCaretPosition( chatDocument.getLength() );
            }
            /* 1415 */
            if ( logger != null && loggedIn && keepLog ) {
                String str;
                /* 1418 */
                if ( htmlLog ) {
                    str = "<span class=\"gray\">" + paramString + "</span><br/>";
                } else
                    /* 1419 */ {
                    str = paramString;
                }
                /* 1420 */
                logger.logEntry( str );
            }
        } catch ( javax.swing.text.BadLocationException localBadLocationException ) {
            /* 1427 */
            System.err.println( "Unhandled exception. (Bad Location)" );
        }
    }

    public static String nameCompletion( String paramString, boolean paramBoolean ) {
        /* 1676 */
        String str1 = "";
        /* 1677 */
        String str2 = "";

        /* 1679 */
        if ( paramBoolean ) {
            paramString = paramString.substring( 0, paramString.length() - lastNameCompleted.length() + 1 );
        }
        /* 1681 */
        st = new java.util.StringTokenizer( paramString );

        /* 1683 */
        str1 = st.nextToken();

        /* 1685 */
        while ( st.hasMoreTokens() ) {
            /* 1686 */
            str2 = str2 + str1 + " ";
            /* 1687 */
            str1 = st.nextToken();
        }

        /* 1690 */
        if ( paramBoolean ) {
            /* 1691 */
            if ( lastCompletionPlayer == sortedNames.size() - 1 ) {
                /* 1692 */
                lastCompletionPlayer = 0;
                /* 1693 */
                lastNameCompleted = ( (ClientPlayer) sortedNames.get( lastCompletionPlayer ) ).getName();
                /* 1694 */
                return str2 + lastNameCompleted;
            }

            /* 1697 */
            lastNameCompleted = ( (ClientPlayer) sortedNames.get( ++lastCompletionPlayer ) ).getName();
            /* 1698 */
            return str2 + lastNameCompleted;
        }




        /* 1704 */
        for ( int i = 0;
              i < sortedNames.size() &&
                      str1.compareToIgnoreCase( ( (ClientPlayer) sortedNames.get( i ) ).getName() ) > 0;
              i++ ) {

            /* 1705 */
            if ( i < sortedNames.size() ) {
                lastCompletionPlayer = i;
            } else
                /* 1706 */ {
                lastCompletionPlayer = sortedNames.size() - 1;
            }
            /* 1707 */
            lastNameCompleted = ( (ClientPlayer) sortedNames.get( lastCompletionPlayer ) ).getName();
        }
        /* 1708 */
        return str2 + lastNameCompleted;
    }

    public static void main( String[] paramArrayOfString ) {
        /*  150 */
        System.out.println( "\n\nThis is the JParanoia client console.\n" );
        /*  151 */
        System.out.println( "Running under Java Runtime Environment version " + System.getProperty( "java.version" ) );
        /*  152 */
        JPClient localJPClient = new JPClient();
        /*  153 */
        frame.setLocation( new java.awt.Point( 20, 20 ) );
        /*  154 */
        frame.setVisible( true );
    }

    public static void connect( String paramString1, boolean paramBoolean, String paramString2 ) {
        /*  601 */
        if ( paramString1 == null ) {
            return;
        }
        /*  603 */
        if ( paramString1.equals( "" ) ) {
            return;
        }
        /*  605 */
        for ( ;
            /*  605 */
              realName.trim().equals( "default" ) || realName == null || realName.trim().equals( "" );



            /*  609 */
              realName = (String) javax.swing.JOptionPane.showInputDialog( null, "You have not provided your \"real\" name in the\njpConfig.ini file. The sooner you do, the sooner\nthis message will stop appearing. Your \"real\" name\nis announced when you join and logged for posterity.\nMost likely the best choice rather than your actual\nname would be the name you are known by in your RPG\ncircles or your Internet persona. For example, your\nuser name on Paranoia-Live.net would be an excellent\nname to provide here.\n\n\"Real\" name:", "\"Real\" name...", -1, null, null, realName ) ) {
            /*  607 */
            System.out.println( "realName == \"" + realName + "\"" );

            /*  609 */
            new javax.swing.JOptionPane();
        }





















        /*  632 */
        connectMenuItem.setEnabled( false );
        /*  633 */
        disconnectMenuItem.setEnabled( true );

        /*  635 */
        disconnectCalled = false;

        /*  637 */
        String str = "hey";
        try {
            /*  641 */
            mySock = new java.net.Socket( paramString1, 11777 );
            /*  642 */
            System.out.println( "Connected to server: " + paramString2 );
            /*  643 */
            System.out.println();

            /*  645 */
            if ( soundIsOn && soundMenu.connectedDisconnectedMenuItem.isSelected() ) {
                soundPlayer.play( 1 );
            }
            /*  647 */
            connected = true;
            /*  648 */
            stayConnected = true;

            /*  650 */
            out = new PrintWriter( mySock.getOutputStream(), true );
            /*  651 */
            in = new java.io.BufferedReader( new java.io.InputStreamReader( mySock.getInputStream() ) );

            /*  653 */
            myListener = new ChatListenerThread();
            /*  654 */
            myListener.setDaemon( true );
            /*  655 */
            myListener.start();
        } catch ( java.net.UnknownHostException localUnknownHostException ) {

            /*  661 */
            javax.swing.JOptionPane.showMessageDialog( null, paramString2 + "\nUnknown host.", "Uknown host", 0 );



            /*  665 */
            connectMenuItem.setEnabled( true );
            /*  666 */
            disconnectMenuItem.setEnabled( false );
        } catch ( java.net.ConnectException localConnectException ) {
            /*  671 */
            javax.swing.JOptionPane.showMessageDialog( null, paramString2 +
                    "\nConnection at this host failed.\n" +
                    "Host may not be running a server.", "Connect failed", 0 );




            /*  676 */
            if ( paramBoolean ) {
                jparanoia.shared.GameRegistrar.deleteUnreachableGame( paramString1 );
            }
            /*  678 */
            connectMenuItem.setEnabled( true );
            /*  679 */
            disconnectMenuItem.setEnabled( false );
        } catch ( java.io.IOException localIOException ) {
            /*  684 */
            System.out.println( "ERROR: Unhandled IO Exception..." );
            /*  685 */
            localIOException.printStackTrace();
        }
    }

    public static void checkVersion( String paramString ) {
        /*  692 */
        if ( new jparanoia.shared.JPVersionNumber( paramString ).compareTo( MIN_COMPATIBLE_VERSION_NUMBER ) < 0 ) {
            /*  694 */
            out.println( "961Someone using client version " +
                    VERSION_NUMBER.toString() +
                    " has attempted (and failed) to connect." +
                    " You must upgrade to server version " +
                    MIN_COMPATIBLE_VERSION_NUMBER.toString() +
                    " to support this client." +
                    " The current version of JParanoia may be found at " +
                    "http://www.byronbarry.com/jparanoia/" );



            /*  698 */
            disconnect( false );

            /*  700 */
            absoluteChat( "Connection failure: Server version is too old to work properly with your client." );
            /*  701 */
            absoluteChat( "Alert GM to locate a current version of JParanoia." );
            /*  702 */
            absoluteChat( "The JParanoia website is:" );
            /*  703 */
            absoluteChat( "http://www.byronbarry.com/jparanoia/" );
        } else {
            /*  706 */
            out.println( "960" );
        }
    }

    public static void respond( String paramString ) {
        /*  712 */
        java.util.Random localRandom = new java.util.Random();

        /*  714 */
        int i = Integer.parseInt( paramString.substring( 2 ) );
        /*  715 */
        i *= localRandom.nextInt( 845 ) + 154;
        /*  716 */
        out.println( i );
    }

    public static void initializePlayerList( String paramString ) {
        /*  800 */
        numberOfPlayers = Integer.parseInt( paramString );
        /*  801 */
        playerList = new ClientPlayer[numberOfPlayers];
        /*  802 */
        out.println( "numberOfPlayers received" );
    }

    public static void loginError() {
        /*  809 */
        if ( soundIsOn && soundMenu.loginBadLoginMenuItem.isSelected() ) {
            soundPlayer.play( 4 );
        }
    }

    public static void receivePlayerUpdate( String paramString ) {
        /*  816 */
        boolean bool1 = false;
        /*  817 */
        boolean bool2 = true;

        /*  819 */
        Color localColor = null;

        /*  821 */
        int i = Integer.parseInt( paramString.substring( 0, 2 ) );

        /*  823 */
        if ( paramString.substring( 2, 3 ).equals( "p" ) ) {
            bool1 = true;
        }
        /*  824 */
        if ( paramString.substring( 3, 4 ).equals( "n" ) ) {
            bool2 = false;
        }
        /*  825 */
        String str = paramString.substring( 4 );


        /*  828 */
        if ( playerList[i] != null ) {
            localColor = playerList[i].getChatColor();
        }
        /*  830 */
        sortedNames.remove( playerList[i] );

        /*  832 */
        playerList[i] = new ClientPlayer( i, str, bool1, bool2 );

        /*  834 */
        if ( playerList[i].isAPlayer() ) {
            sortedNames.add( playerList[i] );
        }
        /*  836 */
        sortNames();


        /*  839 */
        if ( localColor != null ) {
            playerList[i].setChatColor( localColor );
        }

        /*  842 */
        updateLipArray();
        /*  843 */
        if ( i == MY_PLAYER_NUMBER && loggedIn && !observer ) {
            /*  845 */
            myPlayer = playerList[MY_PLAYER_NUMBER];
            /*  846 */
            myTitle.setPlayerName( myPlayer.toString() );
            /*  847 */
            frame.setTitle( myTitle.get() );
        }
        /*  850 */
        else if ( i < numberOfPCs && loggedIn && !observer ) {
            /*  852 */
            playerList[i].setPMFrame( PMFrame[i] );
            /*  853 */
            PMFrame[i].setTitle( playerList[i].getName() );
            /*  854 */
            pmWindowsMenu.removeAll();
            /*  855 */
            pmWindowsMenu.add( showAllPMWindowsMenuItem );
            /*  856 */
            pmWindowsMenu.addSeparator();
            /*  857 */
            for ( int j = 0; j < numberOfPCs; j++ ) {
                /*  858 */
                if ( j != MY_PLAYER_NUMBER ) {
                    PMFrame[j].addItemToMenu();
                }
            }
        }
    }

    public static void updateLipArray() {
        /* 1050 */
        java.util.Vector localVector = new java.util.Vector();

        /* 1052 */
        int i = 0;

        /* 1054 */
        for ( int j = 0; j < numberOfPCs; j++ ) {



            /* 1059 */
            if ( playerList[j].isLoggedIn() && playerList[j].isAPlayer() ) {
                /* 1061 */
                localVector.addElement( playerList[j] );
            } else {
                /* 1064 */
                i++;
            }
        }


        /* 1069 */
        for ( int j = 0; j < i; j++ ) {
            /* 1071 */
            localVector.addElement( "" );
        }





        /* 1078 */
        if ( localVector.size() != numberOfPCs ) {
            System.out.println( "Error: lipNames size not equal to number of player characters." );
        }
        /* 1080 */
        Object[][] arrayOfObject = new Object[numberOfPCs][1];

        /* 1082 */
        for ( int k = 0; k < localVector.size(); k++ ) {
            /* 1084 */
            arrayOfObject[k][0] = localVector.elementAt( k );
        }

        /* 1087 */
        for ( int k = 0; k < numberOfPCs; k++ ) {
            /* 1089 */
            lipArray[k][0] = arrayOfObject[k][0];
        }

        /* 1092 */
        lipTable.repaint();
    }

    private static void sortNames() {
        /* 1654 */
        if ( sortedNames.size() > 1 ) {
            /* 1655 */
            int i = sortedNames.size() - 1;
            /* 1656 */
            int j = i - 1;
            /* 1657 */
            while ( j >= 0 &&
                    ( (ClientPlayer) sortedNames.get( i ) ).getName()
                            .compareToIgnoreCase( ( (ClientPlayer) sortedNames.get( j ) ).getName() ) < 0 ) {

                /* 1660 */
                sortedNames.add( i, sortedNames.remove( j ) );

                /* 1662 */
                i = j;
                /* 1663 */
                j--;
            }
        }
    }

    public static void playerHasJoined( String paramString ) {
        /*  869 */
        int i = Integer.parseInt( paramString );

        /*  871 */
        playerList[i].setLoggedIn( true );
        /*  872 */
        if ( !observer ) {
            playerList[i].pmFrame.enableInput();
        }
        /*  874 */
        absoluteChat( "--- " + playerList[i].toString() + " has joined ---" );
        /*  875 */
        updateLipArray();

        /*  877 */
        assignColorsToCharacters();
        /*  878 */
        if ( soundIsOn && soundMenu.joinLeaveMenuItem.isSelected() ) {
            soundPlayer.play( 5 );
        }
    }

    public static void playerHasLeft( String paramString ) {
        /*  891 */
        int i = Integer.parseInt( paramString );


        /*  894 */
        playerList[i].setLoggedIn( false );
        /*  895 */
        if ( !observer ) {
            playerList[i].pmFrame.disableInput();
        }
        /*  896 */
        absoluteChat( "--- " + playerList[i].toString() + " has left ---" );
        /*  897 */
        updateLipArray();
        /*  898 */
        if ( soundIsOn && soundMenu.joinLeaveMenuItem.isSelected() ) {
            soundPlayer.play( 6 );
        }
    }

    public static void playerHasDied( String paramString ) {
        /*  906 */
        int i = Integer.parseInt( paramString );
        /*  907 */
        ClientPlayer localClientPlayer = playerList[i];
        /*  908 */
        localClientPlayer.die();
        /*  909 */
        updateLipArray();
        /*  910 */
        if ( i == MY_PLAYER_NUMBER && !observer ) {
            /*  912 */
            myTitle.setPlayerName( myPlayer.toString() );
            /*  913 */
            frame.setTitle( myTitle.get() );
        }

        /*  916 */
        if ( soundIsOn && soundMenu.deathAlertMenuItem.isSelected() ) {
            soundPlayer.play( 14 );
        }
    }

    public static void playerHasUndied( String paramString ) {
        /*  927 */
        int i = Integer.parseInt( paramString );
        /*  928 */
        ClientPlayer localClientPlayer = playerList[i];
        /*  929 */
        localClientPlayer.unDie();
        /*  930 */
        updateLipArray();
        /*  931 */
        if ( i == MY_PLAYER_NUMBER && !observer ) {
            /*  933 */
            myTitle.setPlayerName( myPlayer.toString() );
            /*  934 */
            frame.setTitle( myTitle.get() );
        }
    }

    public static void receiveMyPlayerID( String paramString ) {
        /*  943 */
        MY_PLAYER_NUMBER = Integer.parseInt( paramString );
        /*  944 */
        if ( MY_PLAYER_NUMBER < 10 ) {
            MY_PLAYER_ID = "0" + MY_PLAYER_NUMBER;
        } else {
            /*  945 */
            MY_PLAYER_ID = "" + MY_PLAYER_NUMBER;
        }
        /*  947 */
        if ( MY_PLAYER_NUMBER != 99 || !observer ) {
            /*  949 */
            playerList[MY_PLAYER_NUMBER].setLoggedIn( true );
            /*  950 */
            myPlayer = playerList[MY_PLAYER_NUMBER];
        }

        /*  953 */
        int i = 0;

        /*  955 */
        for ( int j = 0; j < numberOfPlayers; j++ ) {
            /*  957 */
            if ( playerList[j].isAPlayer() ) {
                i++;
            }
        }
        /*  960 */
        numberOfPCs = i;

        /*  962 */
        loggedIn = true;



        /*  966 */
        updateLipArray();
        /*  967 */
        assignColorsToCharacters();


        /*  970 */
        if ( !observer ) {
            /*  972 */
            PMFrame = new PrivateMessageFrame[numberOfPCs];

            /*  974 */
            for ( int j = 0; j < numberOfPCs; j++ ) {
                /*  976 */
                if ( j != MY_PLAYER_NUMBER ) {
                    /*  978 */
                    PMFrame[j] = new PrivateMessageFrame( playerList[j] );
                }
            }



            /*  984 */
            showAllPMWindowsMenuItem.setEnabled( true );



            /*  988 */
            myTitle.setPlayerName( myPlayer.toString() );


            /*  991 */
            frame.setTitle( myTitle.get() );
        }


        /*  995 */
        connectionStatusLabel.setIcon( connectedIcon );

        /*  997 */
        if ( keepLog ) {
            /*  998 */
            if ( htmlLog ) {
                logger = new jparanoia.shared.GameLogger( playerList );
            } else
                /*  999 */ {
                logger = new jparanoia.shared.GameLogger();
            }
        }
        /* 1001 */
        if ( soundIsOn && soundMenu.loginBadLoginMenuItem.isSelected() ) {
            soundPlayer.play( 3 );
        }
        /* 1003 */
        if ( observer ) {
            /* 1004 */
            out.println( "400" + realName );
        }
    }

    public static void receiveCharacterSheet() {
        /* 1012 */
        synchronized ( in ) {
            try {
                /* 1017 */
                myPlayer.characterSheet = new javax.swing.text.DefaultStyledDocument();
                /* 1018 */
                String str = in.readLine();
                /* 1019 */
                while ( !str.equals( "402" ) ) {

                    /* 1022 */
                    myPlayer.characterSheet.insertString( myPlayer.characterSheet.getLength(), str +
                            "\n", charsheetAttributes );

                    /* 1024 */
                    str = in.readLine();
                }
                /* 1026 */
                charsheetArea.setDocument( myPlayer.characterSheet );
                /* 1027 */
                charsheetArea.repaint();
                /* 1028 */
                charsheetFrame.repaint();
                /* 1029 */
                absoluteChat( "(Your character sheet has been updated by the GM.)" );
            } catch ( Exception localException ) {
                /* 1032 */
                System.out.println( "Error receiving character sheet." );
                localException.printStackTrace();
            }
        }
        /* 1035 */
        if ( !firstCharsheetUpdate && soundIsOn && soundMenu.charSheetAlertMenuItem.isSelected() ) {
            /* 1036 */
            soundPlayer.play( 20 );
        }
        /* 1038 */
        else if ( firstCharsheetUpdate ) {
            firstCharsheetUpdate = false;
        }
    }

    public static void generalChat( String paramString ) {
        /* 1225 */
        int i = Integer.parseInt( paramString.substring( 0, 2 ) );
        /* 1226 */
        paramString = paramString.substring( 2 );

        /* 1228 */
        styleBegin = styleEnd = "";

        /* 1230 */
        if ( i == numberOfPCs ) {
            useComputerFont();
            /* 1231 */
        } else if ( i == 0 ) {
            useGmFont();
        }
        try {
            /* 1235 */
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, playerList[i].getChatColor() );

            /* 1237 */
            chatDocument.insertString( chatDocument.getLength(), "   " +
                    playerList[i].toString() +
                    ": ", textAttributes );


            /* 1240 */
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, textColor );

            /* 1242 */
            chatDocument.insertString( chatDocument.getLength(), paramString + "\n", textAttributes );


            /* 1245 */
            displayArea.setDocument( chatDocument );
            /* 1246 */
            if ( autoScroll ) {
                displayArea.setCaretPosition( chatDocument.getLength() );
            }
            /* 1247 */
            if ( keepLog ) {
                String str;
                /* 1250 */
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
                } else
                    /* 1251 */ {
                    str = playerList[i].toString() + ": " + paramString;
                }
                /* 1252 */
                logger.logEntry( str );
            }
        } catch ( javax.swing.text.BadLocationException localBadLocationException ) {
            /* 1258 */
            System.err.println( "Unhandled exception. (Bad Location)" );
        }

        /* 1261 */
        if ( i == numberOfPCs ) {
            restoreOriginalFont();
            /* 1262 */
        } else if ( i == 0 ) {
            restoreOriginalFont();
        }
        /* 1264 */
        if ( soundIsOn && soundMenu.newTextMenuItem.isSelected() ) {
            soundPlayer.play( 7 );
        }
    }

    public static void useComputerFont() {
        /* 1199 */
        styleBegin = "<span class=\"computer\">";
        /* 1200 */
        styleEnd = "</span>";
        /* 1201 */
        mainFontSize = (Integer) textAttributes.getAttribute( javax.swing.text.StyleConstants.FontConstants.Size );

        /* 1203 */
        int i = mainFontSize.intValue() + computerFontIncrease;
        /* 1204 */
        textAttributes.addAttribute( javax.swing.text.StyleConstants.FontConstants.Size, new Integer( i ) );
        /* 1205 */
        if ( !fontIsBold ) {
            setFontBold( true );
        }
    }

    public static void setFontBold( boolean paramBoolean ) {
        /* 1193 */
        Boolean localBoolean = new Boolean( paramBoolean );
        /* 1194 */
        textAttributes.addAttribute( javax.swing.text.StyleConstants.FontConstants.Bold, localBoolean );
    }

    public static void useGmFont() {
        /* 1210 */
        styleBegin = "<span class=\"gmText\">";
        /* 1211 */
        styleEnd = "</span>";

        /* 1213 */
        if ( !fontIsBold ) {
            setFontBold( true );
        }
    }

    public static void restoreOriginalFont() {
        /* 1218 */
        styleBegin = styleEnd = "";
        /* 1219 */
        textAttributes.addAttribute( javax.swing.text.StyleConstants.FontConstants.Size, mainFontSize );
        /* 1220 */
        if ( !fontIsBold ) {
            setFontBold( false );
        }
    }

    public static void actionChat( String paramString ) {
        /* 1270 */
        int i = Integer.parseInt( paramString.substring( 0, 2 ) );
        /* 1271 */
        paramString = paramString.substring( 2 );

        /* 1273 */
        styleBegin = styleEnd = "";

        /* 1275 */
        if ( i == numberOfPCs ) {
            useComputerFont();
            /* 1276 */
        } else if ( i == 0 ) {
            useGmFont();
        }
        try {
            /* 1281 */
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, playerList[i].getChatColor() );

            /* 1283 */
            chatDocument.insertString( chatDocument.getLength(), "* " +
                    playerList[i].toString() +
                    " " +
                    paramString +
                    " *\n", textAttributes );

            /* 1285 */
            displayArea.setDocument( chatDocument );
            /* 1286 */
            if ( autoScroll ) {
                displayArea.setCaretPosition( chatDocument.getLength() );
            }
            /* 1287 */
            if ( keepLog ) {
                String str;
                /* 1290 */
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
                } else
                    /* 1291 */ {
                    str = "* " + playerList[i].toString() + " " + paramString + " *";
                }
                /* 1292 */
                logger.logEntry( str );
            }

            /* 1295 */
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, textColor );
        } catch ( javax.swing.text.BadLocationException localBadLocationException ) {

            /* 1301 */
            System.err.println( "Unhandled exception. (Bad Location)" );
        }

        /* 1304 */
        if ( i == numberOfPCs ) {
            restoreOriginalFont();
            /* 1305 */
        } else if ( i == 0 ) {
            restoreOriginalFont();
        }
        /* 1307 */
        if ( soundIsOn && soundMenu.newTextMenuItem.isSelected() ) {
            soundPlayer.play( 7 );
        }
    }

    public static void speechChat( String paramString ) {
        /* 1313 */
        int i = Integer.parseInt( paramString.substring( 0, 2 ) );
        /* 1314 */
        paramString = paramString.substring( 2 );

        /* 1316 */
        styleBegin = styleEnd = "";



        /* 1320 */
        if ( i == numberOfPCs ) {
            useComputerFont();
            /* 1321 */
        } else if ( i == 0 ) {
            useGmFont();
        }
        String str1;
        /* 1324 */
        if ( paramString.endsWith( "!!" ) ) {
            str1 = "screams, ";
            /* 1325 */
        } else if ( paramString.endsWith( "!" ) ) {
            str1 = "shouts, ";
        } else {
            /* 1326 */
            str1 = "says, ";
        }
        try {
            /* 1330 */
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, playerList[i].getChatColor() );

            /* 1332 */
            chatDocument.insertString( chatDocument.getLength(), playerList[i].toString() + " ", textAttributes );
            /* 1333 */
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, textColor );
            /* 1334 */
            chatDocument.insertString( chatDocument.getLength(), str1 + "\"" + paramString + "\"\n", textAttributes );

            /* 1336 */
            displayArea.setDocument( chatDocument );
            /* 1337 */
            if ( autoScroll ) {
                displayArea.setCaretPosition( chatDocument.getLength() );
            }
            /* 1338 */
            if ( keepLog ) {
                String str2;
                /* 1341 */
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
                } else
                    /* 1342 */ {
                    str2 = playerList[i].toString() + " " + str1 + "\"" + paramString + "\"";
                }
                /* 1343 */
                logger.logEntry( str2 );
            }

            /* 1346 */
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, textColor );
        } catch ( javax.swing.text.BadLocationException localBadLocationException ) {
            /* 1351 */
            System.err.println( "Unhandled exception. (Bad Location)" );
        }

        /* 1354 */
        if ( i == numberOfPCs ) {
            restoreOriginalFont();
            /* 1355 */
        } else if ( i == 0 ) {
            restoreOriginalFont();
        }
        /* 1357 */
        if ( soundIsOn && soundMenu.newTextMenuItem.isSelected() ) {
            soundPlayer.play( 7 );
        }
    }

    public static void thoughtChat( String paramString ) {
        /* 1363 */
        int i = Integer.parseInt( paramString.substring( 0, 2 ) );
        /* 1364 */
        paramString = paramString.substring( 2 );

        /* 1366 */
        styleBegin = styleEnd = "";

        /* 1368 */
        if ( i == numberOfPCs ) {
            useComputerFont();
            /* 1369 */
        } else if ( i == 0 ) {
            useGmFont();
        }
        try {
            /* 1373 */
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, playerList[i].getChatColor() );

            /* 1375 */
            chatDocument.insertString( chatDocument.getLength(), playerList[i].toString() +
                    " . o O ( " +
                    paramString +
                    " )\n", textAttributes );

            /* 1377 */
            displayArea.setDocument( chatDocument );
            /* 1378 */
            if ( autoScroll ) {
                displayArea.setCaretPosition( chatDocument.getLength() );
            }
            /* 1379 */
            if ( keepLog ) {
                String str;
                /* 1382 */
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
                } else
                    /* 1383 */ {
                    str = playerList[i].toString() + " . o O ( " + paramString + " )";
                }
                /* 1384 */
                logger.logEntry( str );
            }

            /* 1387 */
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, textColor );
        } catch ( javax.swing.text.BadLocationException localBadLocationException ) {
            /* 1392 */
            System.err.println( "Unhandled exception. (Bad Location)" );
        }

        /* 1395 */
        if ( i == numberOfPCs ) {
            restoreOriginalFont();
            /* 1396 */
        } else if ( i == 0 ) {
            restoreOriginalFont();
        }
        /* 1398 */
        if ( soundIsOn && soundMenu.newTextMenuItem.isSelected() ) {
            soundPlayer.play( 7 );
        }
    }

    public static void observerChat( String paramString ) {
        /* 1433 */
        String str1 = paramString.substring( 0, paramString.indexOf( "@~!~~" ) );
        /* 1434 */
        paramString = paramString.substring( paramString.indexOf( "@~!~~" ) + 5 );
        try {
            /* 1438 */
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, Color.gray );

            /* 1440 */
            chatDocument.insertString( chatDocument.getLength(), "   " + str1 + ": ", textAttributes );

            /* 1442 */
            textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, textColor.darker() );

            /* 1444 */
            chatDocument.insertString( chatDocument.getLength(), paramString + "\n", textAttributes );


            /* 1447 */
            displayArea.setDocument( chatDocument );
            /* 1448 */
            if ( autoScroll ) {
                displayArea.setCaretPosition( chatDocument.getLength() );
            }
            /* 1449 */
            if ( keepLog ) {
                String str2;
                /* 1452 */
                if ( htmlLog ) {
                    str2 = "<span class=\"observer\">" +
                            str1 +
                            ": </span><span class=\"gray\">" +
                            paramString +
                            "</span><br/>";
                } else
                    /* 1453 */ {
                    str2 = str1 + ": " + paramString;
                }
                /* 1454 */
                logger.logEntry( str2 );
            }
        } catch ( javax.swing.text.BadLocationException localBadLocationException ) {
            /* 1460 */
            System.err.println( "Unhandled exception. (Bad Location)" );
        }

        /* 1463 */
        if ( soundIsOn && soundMenu.newObserverTextMenuItem.isSelected() ) {
            soundPlayer.play( 7 );
        }
    }

    public static void receivePrivateMessage( String paramString ) {
        /* 1469 */
        int i = Integer.parseInt( paramString.substring( 0, 2 ) );
        /* 1470 */
        if ( i == MY_PLAYER_NUMBER ) {
            /* 1472 */
            int j = Integer.parseInt( paramString.substring( 2, 4 ) );
            /* 1473 */
            paramString = paramString.substring( 4 );
            try {
                /* 1479 */
                if ( chatNotifyNewPMMenuItem.isSelected() ) {
                    /* 1480 */
                    absoluteChat( "(New private message from " + playerList[j].getName() + ")" );
                }
                /* 1482 */
                if ( soundIsOn && soundMenu.newPMAlertMenuItem.isSelected() ) {
                    /* 1483 */
                    soundPlayer.play( 19 );
                }
                /* 1485 */
                textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, playerList[j].getChatColor() );

                /* 1487 */
                PMFrame[j].privateMessageDocument.insertString( PMFrame[j].privateMessageDocument.getLength(), " " +
                        playerList[j].getName() +
                        ": ", textAttributes );


                /* 1490 */
                textAttributes.addAttribute( StyleConstants.CharacterConstants.Foreground, textColor );

                /* 1492 */
                PMFrame[j].privateMessageDocument.insertString( PMFrame[j].privateMessageDocument.getLength(),
                        paramString +
                                "\n", textAttributes );


                /* 1495 */
                PMFrame[j].displayArea.setDocument( PMFrame[j].privateMessageDocument );
                /* 1496 */
                if ( autoScroll ) {
                    PMFrame[j].displayArea.setCaretPosition( PMFrame[j].privateMessageDocument.getLength() );
                }
                /* 1497 */
                if ( keepLog ) {
                    String str;
                    /* 1500 */
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
                    } else
                        /* 1501 */ {
                        str = "(" + playerList[j].toString() + " -> " + myPlayer.toString() + "): " + paramString;
                    }
                    /* 1502 */
                    logger.logEntry( str );
                }

                /* 1505 */
                if ( !PMFrame[j].isShowing() ) {
                    PMFrame[j].setVisible( true );
                }
            } catch ( javax.swing.text.BadLocationException localBadLocationException ) {
                /* 1511 */
                System.err.println( "Unhandled exception. (Bad Location)" );
            }
        }
    }

    public static void startCombat() {
        /* 1518 */
        inCombat = true;
        /* 1519 */
        if ( !observer ) {
            /* 1520 */
            connectionStatusLabel.setIcon( frozenIcon );
            /* 1521 */
            turnFrame = new CombatTurnFrame();
            /* 1522 */
            turnFrame.setVisible( true );
        }
        /* 1524 */
        if ( soundIsOn && soundMenu.combatAlertMenuItem.isSelected() ) {
            soundPlayer.play( 21 );
        }
        /* 1525 */
        if ( soundIsOn && soundMenu.combatMusicMenuItem.isSelected() ) {
            soundPlayer.startCombatMusic();
            combatMusicIsPlaying = true;
        }
    }

    public static void takeCombatTurn() {
        /* 1530 */
        connectionStatusLabel.setIcon( combatIcon );

        /* 1532 */
        java.awt.Toolkit.getDefaultToolkit().beep();

        /* 1534 */
        long l = System.currentTimeMillis();
        /* 1535 */
        while ( System.currentTimeMillis() - l < 300L ) {
        }

        /* 1537 */
        java.awt.Toolkit.getDefaultToolkit().beep();

        /* 1539 */
        turnFrame.sendButton.setEnabled( true );
    }

    public static void abortCombatTurn() {
        /* 1544 */
        connectionStatusLabel.setIcon( frozenIcon );
        /* 1545 */
        if ( turnFrame.isVisible() ) {
            turnFrame.dispose();
        }
    }

    public static void combatComplete() {
        /* 1551 */
        inCombat = false;
        /* 1552 */
        if ( combatMusicIsPlaying ) {
            soundPlayer.stopCombatMusic();
        }
    }

    public static void gmSendingPrivateMessage( String paramString ) {
        /* 1559 */
        int i = Integer.parseInt( paramString );
        /* 1560 */
        if ( i == MY_PLAYER_NUMBER || i == 999 ) {
            /* 1562 */
            messageStatusLabel.setIcon( incomingMsgIcon );
        } else {
            /* 1565 */
            messageStatusLabel.setIcon( gmSendingMsgIcon );
        }
    }

    public static void clearMessageStatusLabel() {
        /* 1570 */
        messageStatusLabel.setIcon( nullMsgIcon );
    }

    public static void checkMuted( String paramString ) {
        /* 1576 */
        int i = Integer.parseInt( paramString );
        /* 1577 */
        if ( i == MY_PLAYER_NUMBER ) {
            /* 1579 */
            muted = true;
            /* 1580 */
            if ( !frozen ) {
                connectionStatusLabel.setIcon( mutedIcon );
            }
            /* 1581 */
            if ( soundIsOn && soundMenu.mutedUnmutedMenuItem.isSelected() ) {
                soundPlayer.play( 8 );
            }
        }
    }

    public static void checkUnmuted( String paramString ) {
        /* 1588 */
        int i = Integer.parseInt( paramString );
        /* 1589 */
        if ( i == MY_PLAYER_NUMBER ) {
            /* 1591 */
            muted = false;
            /* 1592 */
            if ( frozen ) {
                connectionStatusLabel.setIcon( frozenIcon );
            } else
                /* 1593 */ {
                connectionStatusLabel.setIcon( connectedIcon );
            }
            /* 1594 */
            if ( soundIsOn && soundMenu.mutedUnmutedMenuItem.isSelected() ) {
                soundPlayer.play( 9 );
            }
        }
    }

    public static void listenObservers() {
        /* 1600 */
        if ( !hearObservers ) {
            /* 1601 */
            hearObservers = true;
        }
        /* 1603 */
        absoluteChat( "Observers are currently heard." );
    }

    public static void muteObservers() {
        /* 1608 */
        if ( hearObservers ) {
            /* 1609 */
            hearObservers = false;
        }
        /* 1611 */
        absoluteChat( "Observers are currently NOT heard." );
    }

    public static void freezeMe() {
        /* 1616 */
        if ( !frozen ) {
            /* 1618 */
            frozen = true;
            /* 1619 */
            connectionStatusLabel.setIcon( frozenIcon );
            /* 1620 */
            if ( soundIsOn &&
                    soundMenu.freezeUnfreezeMenuItem.isSelected() &&
                    ( !inCombat ||
                            !soundMenu.combatMusicMenuItem.isSelected() &&
                                    !soundMenu.combatAlertMenuItem.isSelected() ) ) {

                /* 1623 */
                soundPlayer.play( 10 );
            }
        }
    }

    public static void unfreezeMe() {
        /* 1629 */
        if ( frozen ) {
            /* 1631 */
            frozen = false;
            /* 1632 */
            if ( muted ) {
                connectionStatusLabel.setIcon( mutedIcon );
            } else
                /* 1633 */ {
                connectionStatusLabel.setIcon( connectedIcon );
            }
            /* 1634 */
            if ( soundIsOn && soundMenu.freezeUnfreezeMenuItem.isSelected() ) {
                soundPlayer.play( 11 );
            }
        }
    }

    public static void updateTitle( String paramString ) {
        /* 1641 */
        myTitle.setExtra( paramString );
        /* 1642 */
        frame.setTitle( myTitle.get() );
    }

    public static void updateComputerFontIncrease( String paramString ) {
        /* 1647 */
        computerFontIncrease = Integer.parseInt( paramString );
    }

    public static void updateMaxCloneNumber( String paramString ) {
        /* 1714 */
        maxNumClones = Integer.parseInt( paramString );
    }

    public static void setObserver( boolean paramBoolean ) {
        /* 1719 */
        observer = paramBoolean;

        /* 1721 */
        if ( paramBoolean ) {
            /* 1723 */
            for ( ; realName.trim().equals( "default" ) || realName == null || realName.trim().equals( "" );


                /* 1726 */
                  realName = (String) javax.swing.JOptionPane.showInputDialog( null, "Enter your observer name:", "Observer name...", -1, null, null, realName ) ) {
                new javax.swing.JOptionPane();
            }








            /* 1736 */
            myTitle.setPlayerName( realName );
            /* 1737 */
            frame.setTitle( myTitle.get() );
        } else {
            /* 1740 */
            out.println( realName );
        }
    }

    public static String getVersionName() {
        /* 1746 */
        return VERSION_NAME;
    }

    public static void playerPromoted() {
        /* 1752 */
        if ( soundIsOn && soundMenu.promotedDemotedMenuItem.isSelected() ) {
            /* 1753 */
            soundPlayer.play( 12 );
        }
    }

    public static void playerDemoted() {
        /* 1760 */
        if ( soundIsOn && soundMenu.promotedDemotedMenuItem.isSelected() ) {
            /* 1761 */
            soundPlayer.play( 13 );
        }
    }

    public static void displayImage( String paramString ) {
        /* 1767 */
        jparanoia.shared.JParanoia.displayImage( paramString );

        /* 1769 */
        String str1 = paramString.substring( 0, paramString.indexOf( "http://" ) );

        /* 1771 */
        String str2 = paramString.substring( paramString.indexOf( "http://" ) );

        /* 1773 */
        if ( keepLog ) {
            String str3;
            /* 1776 */
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
            } else
                /* 1777 */ {
                str3 = "IMAGE: \"" + str1 + "\" URL: " + str2;
            }
            /* 1778 */
            logger.logEntry( str3 );
        }
    }

    public static void exit() {
        /* 1785 */
        disconnect( false );
        /* 1786 */
        frame.dispose();
        /* 1787 */
        System.exit( 0 );
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\client\JPClient.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */
