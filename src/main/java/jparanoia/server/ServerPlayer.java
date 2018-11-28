package jparanoia.server;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import static java.lang.System.exit;
import static java.lang.System.out;
import java.lang.invoke.MethodHandles;
import static java.lang.invoke.MethodHandles.lookup;
import static java.util.Objects.requireNonNull;
import java.util.StringTokenizer;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleContext;
import static jparanoia.server.JPServer.absoluteChat;
import static jparanoia.server.JPServer.absoluteSpam;
import static jparanoia.server.JPServer.repaintMenus;
import static jparanoia.server.JPServer.spamString;
import static jparanoia.server.JPServer.spareNpcs;
import static jparanoia.server.JPServer.stripComments;
import jparanoia.shared.JPPlayer;
import jparanoia.shared.JParanoia;
import static jparanoia.shared.JParanoia.errorMessage;
import static jparanoia.shared.JParanoia.soundIsOn;
import static jparanoia.shared.JParanoia.soundMenu;
import static jparanoia.shared.JParanoia.soundPlayer;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

public class ServerPlayer extends JPPlayer {
    private final static Logger logger = getLogger( MethodHandles.lookup().lookupClass());

    static int numUnsavedCharsheets = 0;
    static FileWriter writer;
    static SimpleAttributeSet sas;
    static StringTokenizer st;
    final int PLAYER_NUMBER;
    final boolean IS_PLAYER;
    int cloneNumber;
    boolean loggedIn = false;
    boolean isDead = false;
    boolean muted = false;
    boolean frozen = false;
    boolean unsavedCharsheet = false;
    PrivateMessagePane pmPane;
    StatusPanel statusPanel;
    DefaultStyledDocument characterSheet;
    Color chatColor = Color.gray;
    String dataFile;
    String data;
    ServerChatThread chatThread = null;
    BufferedReader reader;
    private boolean debugSpecific = false;
    private String name;
    private String clearance;
    private int clearanceInt;
    private String sector;
    private String password;
    private String realName;
    private ServerPlayerMenu playerMenu;
    private NPCMenu npcMenu;
    private JCheckBox globalExcludeCheckBox;

    public ServerPlayer( int playerNumber, String name, boolean isPlayer, String password, String dataFile ) {
        this.PLAYER_NUMBER = playerNumber;
        this.password = password;
        this.IS_PLAYER = isPlayer;
        this.name = name;
        if ( !this.IS_PLAYER && this.name.startsWith( "spareNPC" ) ) {
            this.loggedIn = true;
            this.npcMenu = new NPCMenu( this );
            logger.info( "Generated NPCMenu for " + this.name );
            spareNpcs.add( this );
        }
        this.dataFile = dataFile;
        this.characterSheet = new DefaultStyledDocument( new StyleContext() );
        sas = JPServer.charsheetAttributes;
    }

    public ServerPlayerMenu getPlayerMenu() {
        return this.playerMenu;
    }

    public String getPassword() {
        return this.password;
    }

    public void readCharacterSheetFile() {
        try {
            this.reader = new BufferedReader( new FileReader( dataFile ) );
        } catch ( Exception localException1 ) {
            logger.info( "An exception occured while attemping to access " + this.dataFile );
            localException1.printStackTrace();
        }
        try {
            this.data = this.reader.readLine();
            String str = null;
            try {
                str = this.data.substring( 0, this.data.lastIndexOf( "-" ) );
            } catch ( Exception localException3 ) {
                localException3.printStackTrace();
                JParanoia.errorMessage( "Invalid Charsheet", "The charsheet file \"" +
                        this.dataFile +
                        "\" does not have\n" +
                        "the character's name on the first line. This is mandatory." );
                System.exit( 0 );
            }
            String newName;
            if ( this.PLAYER_NUMBER == 0 ) {
                if ( JPServer.gmNameNag && this.data.substring( 0, this.data.length() - 2 ).equals( "GM" ) ) {
                    newName = (String) JOptionPane.showInputDialog( null, "Your name, as defined in your " +
                            "own charsheet file, " +
                            this.dataFile +
                            "\n" +
                            "is \"GM\". This does not make you very unique. You can choose a name here\n" +
                            "but it will be forgotten when you exit JParanoia. To choose a lasting name,\n" +
                            "you must change the first line of your charsheet file.\n" +
                            "Just be sure to leave the -0 on the end.\n" +
                            "\n" +
                            "(You can click Cancel to keep \"GM\" if you so choose.\n" +
                            "To permanently surpress this notice, set bGmNameNag=false\n" +
                            "in your jpConfig.ini file.)", "Boring GM Name...", JOptionPane.PLAIN_MESSAGE, null, null, "GM" );
                    if ( newName != null && !newName.equals( "" ) ) {
                        this.name = newName;
                    } else {
                        logger.info( "NUNAME == " + newName );
                        this.name = this.data.substring( 0, this.data.length() - 2 );
                    }
                } else {
                    this.name = this.data.substring( 0, this.data.length() - 2 );
                }
            } else if ( this.IS_PLAYER ) {
                st = new StringTokenizer( str, "-" );
                logger.info( "Parsing name for: " + str );
                this.name = str.substring( 0, str.indexOf( "-" ) );
                if ( st.countTokens() > 3 || st.countTokens() < 2 ) {
                    errorMessage( "Invalid Player Name", "The character sheet " +
                            this.dataFile +
                            "\n" +
                            "attempts to define a player with\n" +
                            "an invalid name \"" +
                            str +
                            "\".\n" +
                            "\n" +
                            "Player names must consist of a first name,\n" +
                            "a clearance initial, and a sector. (Clearance\n" +
                            "initial may be omitted for infrared players.)\n" +
                            "\n" +
                            "Correct the error and relaunch the server." );
                    exit( 0 );
                }
                if ( st.countTokens() == 2 ) {
                    logger.info( "2 tokens in \"" + str + "\", assigning Infrared clearance." );
                    this.clearance = "IR";
                } else {
                    this.clearance = str.substring( str.indexOf( "-" ) + 1, str.lastIndexOf( "-" ) );
                }
                if ( ( this.clearanceInt = evaluateClearance( this.clearance ) ) == -99 ) {
                    errorMessage( "Invalid clearance", "The character sheet " +
                            this.dataFile +
                            "\n" +
                            "attempts to grant a player an\n" +
                            "invalid security clearance \"" +
                            this.clearance +
                            "\".\n" +
                            "\n" +
                            "Allowed clearance codes are:\n" +
                            "(blank) = infrared\n" +
                            "R = red\n" +
                            "O = orange\n" +
                            "Y = yellow\n" +
                            "G = green\n" +
                            "B = blue\n" +
                            "I = indigo\n" +
                            "V = violet\n" +
                            "U = ultraviolet\n" +
                            "\n" +
                            "Correct the error and relaunch the server." );
                    exit( 0 );
                }
                this.sector = str.substring( str.lastIndexOf( "-" ) + 1 );
                if ( this.sector.length() != 3 ) {
                    errorMessage( "Invalid sector", "The character sheet " +
                            this.dataFile +
                            "\n" +
                            "attempts to define a player with\n" +
                            "invalid sector name \"" +
                            this.sector +
                            "\".\n" +
                            "\n" +
                            "Sector names MUST be exactly three characters in length.\n" +
                            "\n" +
                            "Correct the error and relaunch the server." );
                    exit( 0 );
                }
                char[] sectorChars = this.sector.toCharArray();
                for (int i = 0; i < sectorChars.length; ++i) {
                    if ( sectorChars[i] < 'A' || sectorChars[i] > 'Z' ) {
                        errorMessage( "Invalid sector", "The character sheet " +
                                this.dataFile +
                                "\n" +
                                "attempts to define a player with\n" +
                                "invalid sector name \"" +
                                this.sector +
                                "\".\n" +
                                "\n" +
                                "Sector names MUST only contain capital letters A-Z.\n" +
                                "\n" +
                                "Correct the error and relaunch the server." );
                        exit( 0 );
                    }
                }
            } else {
                this.name = this.data.substring( 0, this.data.length() - 2 );
            }
            if ( this.name.startsWith( "(dead)" ) ) {
                this.isDead = true;
            }
            this.cloneNumber = Integer.parseInt( this.data.substring( this.data.lastIndexOf( "-" ) + 1 ) );

            for(newName = this.reader.readLine(); newName != null; newName = this.reader.readLine()) {
                if (!newName.startsWith("#")) {
                    this.characterSheet.insertString(this.characterSheet.getLength(), newName + "\n", sas);
                }
            }
            this.reader.close();
            this.characterSheet.addDocumentListener( new DocumentListener() {
                public void insertUpdate( DocumentEvent paramAnonymousDocumentEvent ) {
                    if ( !ServerPlayer.this.unsavedCharsheet ) {
                        ServerPlayer.this.charsheetUpdated();
                    }
                }

                public void removeUpdate( DocumentEvent paramAnonymousDocumentEvent ) {
                    if ( !ServerPlayer.this.unsavedCharsheet ) {
                        ServerPlayer.this.charsheetUpdated();
                    }
                }

                public void changedUpdate( DocumentEvent paramAnonymousDocumentEvent ) {}
            } );
            this.playerMenu = new ServerPlayerMenu( this );
            this.globalExcludeCheckBox = new JCheckBox( getName() );
        } catch ( Exception localException2 ) {
            logger.info( "An exception occured while reading " + this.name + "'s data file." );
            localException2.printStackTrace();
            errorMessage( "Exception", "An exception occured while reading " +
                    this.name +
                    "'s data file.\n" +
                    "Run JParanoia with the console window to view errors.\n" +
                    "JParanoia will now terminate." );
            exit( -1 );
        }
    }

    public DefaultStyledDocument getCharsheet() {
        return this.characterSheet;
    }

    public String getCharacterSheetFile() {
        return this.dataFile;
    }

    public boolean checkPassword( String paramString ) {
        JPServer.absoluteChat( getName() + " has attempted to login!" );
        return paramString.equalsIgnoreCase( this.password );
    }

    public String getName() {
        if ( isAnActualPlayer() && this.PLAYER_NUMBER != 0 ) {
            if ( this.clearanceInt == 0 ) {
                return this.name + "-" + this.sector;
            }
            return this.name + "-" + this.clearance + "-" + this.sector;
        }
        return this.name;
    }

    public boolean isAnActualPlayer() {
        return this.IS_PLAYER;
    }

    public void setPMPane( PrivateMessagePane paramPrivateMessagePane ) {
        this.pmPane = paramPrivateMessagePane;
    }

    public boolean isLoggedIn() {
        return this.loggedIn;
    }

    public void setLoggedIn( boolean paramBoolean ) {
        this.loggedIn = paramBoolean;
    }

    public Color getChatColor() {
        return this.chatColor;
    }

    public void setChatColor( Color paramColor ) {
        this.chatColor = paramColor;
    }

    public int getPlayerNumber() {
        return this.PLAYER_NUMBER;
    }

    public String getRealName() {
        return this.realName;
    }

    public void setRealName( String paramString ) {
        this.realName = paramString;
        this.playerMenu.realNameLabel.setText( "    Real Name: " + this.realName );
    }

    public void setStatusPanel( StatusPanel paramStatusPanel ) {
        this.statusPanel = paramStatusPanel;
    }

    public boolean isMuted() {
        return this.muted;
    }

    public void setMuted( boolean paramBoolean ) {
        this.muted = paramBoolean;
    }

    public void kill() {
        if ( isAnActualPlayer() && !this.isDead ) {
            if ( this.cloneNumber < JPServer.maxNumClones || JPServer.isPXPGame ) {
                String str1 = this.name;
                String str2 = deathEuphamism( toString() );
                this.cloneNumber += 1;
                JPServer.absoluteChat( str2 );
                JPServer.spamString( "199" + str2 );
            } else {
                this.isDead = true;
                JPServer.absoluteChat( toString() + " has died and has no clones left! Oh, the humanity!!" );
                JPServer.spamString( "199" + toString() + " has died and has no clones left! Oh, the humanity!!" );
                this.name = "(dead)" + this.name;
            }
            JPServer.notifyPlayersOfDeath( this );
            JPServer.spoofComboBox.repaint();
            saveCharsheet( false );
        } else if ( isAnActualPlayer() && this.isDead ) {
            JPServer.absoluteChat( "The GM has attempted to kill " +
                    this.name +
                    " one more time. This is, of course, impossible." );
            JPServer.spamString( "199The GM has attempted to kill " +
                    this.name +
                    " one more time. This is, of course, impossible." );
        }
    }

    public void unkill() {
        if ( isAnActualPlayer() ) {
            if ( this.isDead ) {
                this.isDead = false;
                JPServer.absoluteChat( toString() + " has been refunded a clone due to a clerical error." );
                JPServer.spamString( "199" + toString() + " has been refunded a clone due to a clerical error." );
                this.name = this.name.substring( 6 );
                JPServer.notifyPlayersOfUndeath( this );
                JPServer.spoofComboBox.repaint();
                saveCharsheet( false );
            } else if ( this.cloneNumber > 1 ) {
                JPServer.absoluteChat( toString() + " has been refunded a clone due to a clerical error." );
                JPServer.spamString( "199" + toString() + " has been refunded a clone due to a clerical error." );
                this.cloneNumber -= 1;
                JPServer.notifyPlayersOfUndeath( this );
                JPServer.spoofComboBox.repaint();
                saveCharsheet( false );
            } else {
                JPServer.absoluteChat( "The GM has attempted to give " +
                        toString() +
                        " another clone. This is, of course, impossible." );
                JPServer.spamString( "199The GM has attempted to give " +
                        toString() +
                        " another clone. This is, of course, impossible." );
            }
        }
    }

    private String deathEuphamism( String paramString ) {
        int i = JPServer.rand.nextInt( 14 );
        String str = paramString + " ";
        switch ( i ) {
            case 0:
                str = str + "has gone to Great Alpha Complex in the Sky.";
                break;
            case 1:
                str = str + "has kicked the synthe-bucket.";
                break;
            case 2:
                str = str + "has been visited by the reaper-bot.";
                break;
            case 3:
                str = str + "has shuffled off this mortal sector.";
                break;
            case 4:
                str = str + "has got bored of his present clone.";
                break;
            case 5:
                str = str + "has bought the highly-treasonous farm.";
                break;
            case 6:
                str = str + "has drunk his last bottle of Bouncy Bubble Beverage.";
                break;
            case 7:
                str = str + "has eaten his last Hot Fun.";
                break;
            case 8:
                str = str + "is pushing up traitorous daises.";
                break;
            case 9:
                str = str + "has passed onto a better sector.";
                break;
            case 10:
                str = str + "has exited, vid-stage left.";
                break;
            case 11:
                str = str + "is, alas, no more.";
                break;
            case 12:
                str = str + "has passed away.";
                break;
            case 13:
                str = str + "has ceased to show any signs of life.";
                break;
            case 14:
                str = str + "has cashed in his credits.";
        }
        return str;
    }

    void rename() {
        String str1 = getName();
        String str4 = "";
        new JOptionPane();
        String str5 = (String) JOptionPane.showInputDialog( null, "Enter new name for " +
                getName() +
                "\n" +
                "(omit the clone number):", "New Clone Family...", JOptionPane.PLAIN_MESSAGE, null, null, getName() );
        if ( str5 != null && !str5.equals( "" ) ) {
            logger.info( "About to attempt name parsing on: " + str5 );
            try {
                st = new StringTokenizer( str5, "-" );
                if ( st.countTokens() > 3 ) {
                    errorMessage( "Invalid name", "I told you to leave off the clone number.\nTry again." );
                    return;
                }
                if ( st.countTokens() < 2 ) {
                    errorMessage( "Invalid name", "You didn't give this clone a sector and/or\nsecurity clearance.\nTry again." );
                    return;
                }
                String str3;
                if ( st.countTokens() == 2 ) {
                    logger.info( "2 tokens in \"" + str5 + "\", assigning Infrared clearance." );
                    str3 = "IR";
                } else {
                    str3 = str5.substring( str5.indexOf( "-" ) + 1, str5.lastIndexOf( "-" ) );
                }
                String str2 = str5.substring( 0, str5.indexOf( "-" ) );
                str4 = str5.substring( str5.lastIndexOf( "-" ) + 1 );
                if ( str4.length() != 3 ) {
                    errorMessage( "Invalid sector", "Sector names MUST consist of three capital letters A-Z.\nTry again." );
                    return;
                }
                char[] arrayOfChar = str4.toCharArray();
                for ( final char anArrayOfChar : arrayOfChar ) {
                    if ( anArrayOfChar < 'A' || anArrayOfChar > 'Z' ) {
                        errorMessage( "Invalid sector", "The sector \"" +
                                str4 +
                                "\" is invalid.\n" +
                                "Sector names MUST only contain capital letters A-Z.\n" +
                                "\n" +
                                "Try again." );
                        return;
                    }
                }
                int i;
                if ( ( i = evaluateClearance( str3 ) ) == -99 ) {
                    errorMessage( "Invalid clearance", "Security clearance \"" +
                            str3 +
                            "\" is invalid.\n" +
                            "\n" +
                            "Allowed clearance codes are:\n" +
                            "(blank) = infrared\n" +
                            "R = red\n" +
                            "O = orange\n" +
                            "Y = yellow\n" +
                            "G = green\n" +
                            "B = blue\n" +
                            "I = indigo\n" +
                            "V = violet\n" +
                            "U = ultraviolet\n" +
                            "\n" +
                            "Try again." );
                    return;
                }
                this.clearanceInt = i;
                this.name = str2;
                this.clearance = str3;
                this.sector = str4;
            } catch ( Exception localException ) {
                errorMessage( "Invalid name", "You have entered a name incompatible\nwith the standards set forth by Friend\nComputer. Report for termination." );
            }
            spamString( "199" + str1 + " has been replaced by " + str5 );
            absoluteChat( str1 + " has been replaced by " + str5 );
            String str6;
            if ( this.loggedIn ) {
                str6 = "y";
            } else {
                str6 = "n";
            }
            this.cloneNumber = 1;
            this.isDead = false;
            this.globalExcludeCheckBox.setText( getName() );
            repaintMenus();
            this.playerMenu.setText( getName() );
            spamString( "010" + getID() + "p" + str6 + str5 + "-" + this.cloneNumber );
            saveCharsheet( false );
            this.pmPane.reflectNameChange();
        }
    }

    void setClearance( String paramString1, String paramString2 ) {
        String str1 = "";
        int i = evaluateClearance( paramString1 );
        if ( i < this.clearanceInt ) {
            str1 = "demoted";
            if ( JPServer.soundIsOn && JPServer.soundMenu.promotedDemotedMenuItem.isSelected() ) {
                JPServer.soundPlayer.play( 13 );
            }
            JPServer.spamString( "020" );
        } else if ( i > this.clearanceInt ) {
            str1 = "promoted";
            if ( JPServer.soundIsOn && JPServer.soundMenu.promotedDemotedMenuItem.isSelected() ) {
                JPServer.soundPlayer.play( 12 );
            }
            JPServer.spamString( "021" );
        } else {
            return;
        }
        JPServer.absoluteSpam( getName() + " has been " + str1 + " to " + paramString2 + " clearance!" );
        this.clearance = paramString1;
        this.clearanceInt = i;
        String str2;
        if ( this.loggedIn ) {
            str2 = "y";
        } else {
            str2 = "n";
        }
        JPServer.repaintMenus();
        this.playerMenu.setText( getName() );
        JPServer.spamString( "010" + getID() + "p" + str2 + getName() + "-" + this.cloneNumber );
        saveCharsheet( false );
    }

    public int evaluateClearance( String paramString ) {
        if ( this.playerMenu != null ) {
            if ( paramString.equals( "IR" ) ) {
                this.playerMenu.playerClearanceMenu.securityInfraMenuItem.setSelected( true );
                return 0;
            }
            if ( paramString.equals( "R" ) ) {
                this.playerMenu.playerClearanceMenu.securityRedMenuItem.setSelected( true );
                return 1;
            }
            if ( paramString.equals( "O" ) ) {
                this.playerMenu.playerClearanceMenu.securityOrangeMenuItem.setSelected( true );
                return 2;
            }
            if ( paramString.equals( "Y" ) ) {
                this.playerMenu.playerClearanceMenu.securityYellowMenuItem.setSelected( true );
                return 3;
            }
            if ( paramString.equals( "G" ) ) {
                this.playerMenu.playerClearanceMenu.securityGreenMenuItem.setSelected( true );
                return 4;
            }
            if ( paramString.equals( "B" ) ) {
                this.playerMenu.playerClearanceMenu.securityBlueMenuItem.setSelected( true );
                return 5;
            }
            if ( paramString.equals( "I" ) ) {
                this.playerMenu.playerClearanceMenu.securityIndigoMenuItem.setSelected( true );
                return 6;
            }
            if ( paramString.equals( "V" ) ) {
                this.playerMenu.playerClearanceMenu.securityVioletMenuItem.setSelected( true );
                return 7;
            }
            if ( paramString.equals( "U" ) ) {
                this.playerMenu.playerClearanceMenu.securityUltraMenuItem.setSelected( true );
                return 8;
            }
        } else {
            if ( paramString.equals( "IR" ) ) {
                return 0;
            }
            if ( paramString.equals( "R" ) ) {
                return 1;
            }
            if ( paramString.equals( "O" ) ) {
                return 2;
            }
            if ( paramString.equals( "Y" ) ) {
                return 3;
            }
            if ( paramString.equals( "G" ) ) {
                return 4;
            }
            if ( paramString.equals( "B" ) ) {
                return 5;
            }
            if ( paramString.equals( "I" ) ) {
                return 6;
            }
            if ( paramString.equals( "V" ) ) {
                return 7;
            }
            if ( paramString.equals( "U" ) ) {
                return 8;
            }
        }
        return -99;
    }

    public int getClearanceInt() {
        return this.clearanceInt;
    }

    public ServerChatThread getThread() {
        return this.chatThread;
    }

    public void setThread( ServerChatThread paramServerChatThread ) {
        this.chatThread = paramServerChatThread;
    }

    public JCheckBox getExcludeCheckBox() {
        return this.globalExcludeCheckBox;
    }

    public void sendGlobalPM( String paramString ) {
        if ( this.chatThread != null && !this.globalExcludeCheckBox.isSelected() ) {
            specificSend( "200" + getID() + JPServer.myPlayer.getID() + paramString );
            this.pmPane.addMyMessage( paramString );
        }
    }

    public String getID() {
        String str;
        if ( this.PLAYER_NUMBER < 10 ) {
            str = "0" + this.PLAYER_NUMBER;
        } else {
            str = "" + this.PLAYER_NUMBER;
        }
        return str;
    }

    public synchronized void specificSend( String paramString ) {
        if ( this.chatThread == null ) {
            if ( this.debugSpecific ) {
                JParanoia.errorMessage( "No chat thread", getName() +
                        " does not have a\n" +
                        "chat thread. (Probably due to not\n" +
                        "being logged in.)" );
            }
        } else {
            this.chatThread.out.println( paramString );
        }
    }

    public void sendingGlobalPM() {
        if ( this.chatThread != null ) {
            if ( !this.globalExcludeCheckBox.isSelected() ) {
                specificSend( "210" + getID() );
            } else {
                specificSend( "21099" );
            }
        }
    }

    public void sendCharsheet() {
        logger.info( "Sending " + getName() + " their char sheet..." );
        specificSend( "400" );
        try {
            specificSend( stripComments( this.characterSheet.getText( 0, this.characterSheet.getLength() ) ) );
        } catch ( Exception localException ) {
            logger.info( "Bad location exception while sending charsheet." );
        }
        specificSend( "402" );
    }

    public void sendLastSavedCharsheet() {
/*        logger.info( "Sending " + getName() + " their last saved char sheet..." );
        specificSend( "400" );
        try {
            final ClassLoader classLoader = lookup().lookupClass().getClassLoader();
            final File file = new File( requireNonNull( classLoader.getResource( dataFile ) ).getFile() );
            this.reader = new BufferedReader( new InputStreamReader( new FileInputStream( file ) ) );
            StringBuilder localStringBuffer = new StringBuilder();
            String str = this.reader.readLine();
            str = this.reader.readLine();
            while ( str != null ) {
                if ( !str.startsWith( "#" ) ) {
                    localStringBuffer.append( str ).append( "\n" );
                }
                str = this.reader.readLine();
            }
            this.reader.close();
            specificSend( stripComments( localStringBuffer.toString() ) );
        } catch ( Exception localException ) {
            logger.info( "Bad location exception while sending charsheet." );
        }
        specificSend( "402" );
    }
*/
        logger.info( "Sending " + getName() + " their char sheet..." );
        specificSend( "400" );
        try {
            specificSend( stripComments( this.characterSheet.getText( 0, this.characterSheet.getLength() ) ) );
        } catch ( Exception localException ) {
            logger.info( "Bad location exception while sending charsheet." );
        }
        specificSend( "402" );
    }
    //The above code within the fuction, that isn't commented, is copied from 'public void sendCharsheet()' as that code still works.
    public void saveCharsheet( boolean paramBoolean ) {
        String str = null;
        try {
            str = this.dataFile;
            int i = 0;
            while ( ( i = str.indexOf( "%20" ) ) != -1 ) {
                str = str.substring( 0, i ) + " " + str.substring( i + 3 );
            }
            writer = new FileWriter( str );
            writer.write( toString() + "\n" );
            writer.write( this.characterSheet.getText( 0, this.characterSheet.getLength() ) );
            writer.flush();
            writer.close();
            charsheetSaved();
            if ( paramBoolean ) {
                logger.info( "saveCharsheet(...): calling serverPlayer.sendCharsheet(...)" );
                sendCharsheet();
                if ( soundIsOn && soundMenu.charSheetAlertMenuItem.isSelected() ) {
                    soundPlayer.play( 20 );
                }
            }
        } catch ( Exception localException ) {
            logger.info( "An exception ocurred while attempting to write/send the file." );
            logger.info( "RAW outputFilepath == \"" + str + "\"" );
            localException.printStackTrace();
        }
    }

    public boolean hasUnsavedCharsheet() {
        return this.unsavedCharsheet;
    }

    public void charsheetUpdated() {
        numUnsavedCharsheets += 1;
        this.unsavedCharsheet = true;
        logger.info( getName() + "charsheetUpdated(): numUnsavedCharsheets == " + numUnsavedCharsheets );
    }

    public void charsheetSaved() {
        if ( this.unsavedCharsheet ) {
            this.unsavedCharsheet = false;
            numUnsavedCharsheets -= 1;
            logger.info( getName() + "charsheetSaved(): numUnsavedCharsheets == " + numUnsavedCharsheets );
        }
    }

    public void promptPlayerForCombatTurn() {
        specificSend( "600" );
    }

    public void playerAbortedTurn() {
        JPServer.absoluteSpam( getName() + " waived their right to a combat turn. (Not too bright)" );
        JPServer.combatFrame.removePlayer( this );
    }

    public void kickPlayer() {
        out.print( "Attempting to kick " + getName() + "... " );
        if ( this.chatThread == null ) {
            errorMessage( "No chat thread", getName() +
                    " does not have a\n" +
                    "chat thread. (Probably due to not\n" +
                    "being logged in.)" );
            return;
        }
        this.chatThread.out.println( "999*** You have been kicked ***" );
        this.chatThread.disconnect( true );
        absoluteSpam( "( * Kicked by server * )" );
        logger.info( "Player kicked." );
    }

    public void changePassword() {
        String str = null;
        if ( ( str = JOptionPane.showInputDialog( JPServer.frame, "Enter a new password for " +
                toString(), "New Password", JOptionPane.PLAIN_MESSAGE ) ) != null ) {
            this.password = str;
            this.playerMenu.currentPasswordLabel.setText( "    Password: " + this.password );
        }
    }

    public String toString() {
        if ( isAnActualPlayer() && this.PLAYER_NUMBER != 0 ) {
            return getName() + "-" + this.cloneNumber;
        }
        return getName();
    }

    public NPCMenu getNpcMenu() {
        return this.npcMenu;
    }

    public void npcRename() {
        String str = null;
        if ( ( str = (String) JOptionPane.showInputDialog( JPServer.frame, "Enter a new name for " +
                toString(), "New Name", JOptionPane.PLAIN_MESSAGE, null, null, this.name ) ) != null && !str.equals( "" ) ) {
            this.name = str;
            JPServer.repaintMenus();
            this.npcMenu.setText( getName() );
            JPServer.spamString( "010" + getID() + "n" + "n" + str );
        }
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\ServerPlayer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
