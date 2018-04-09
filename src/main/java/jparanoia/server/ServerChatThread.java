package jparanoia.server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.lang.invoke.MethodHandles;
import java.net.Socket;
import java.net.SocketException;
import java.util.Random;
import java.util.Vector;
import static jparanoia.server.JPServer.MIN_COMPATIBLE_VERSION_NUMBER;
import static jparanoia.server.JPServer.absoluteChat;
import static jparanoia.server.JPServer.actionChat;
import static jparanoia.server.JPServer.chatThreads;
import static jparanoia.server.JPServer.combatButton;
import static jparanoia.server.JPServer.combatFrame;
import static jparanoia.server.JPServer.generalChat;
import static jparanoia.server.JPServer.hearObserversMenuItem;
import static jparanoia.server.JPServer.numberOfConnectedClients;
import static jparanoia.server.JPServer.numberOfConnectedObservers;
import static jparanoia.server.JPServer.observerChat;
import static jparanoia.server.JPServer.observerHasJoined;
import static jparanoia.server.JPServer.observerHasLeft;
import static jparanoia.server.JPServer.playerHasLeft;
import static jparanoia.server.JPServer.players;
import static jparanoia.server.JPServer.privateMessageHandler;
import static jparanoia.server.JPServer.reassignThreadNumbers;
import static jparanoia.server.JPServer.spamString;
import static jparanoia.server.JPServer.speechChat;
import static jparanoia.server.JPServer.thoughtChat;
import jparanoia.shared.JPVersionNumber;
import static jparanoia.shared.JParanoia.addObsName;
import static jparanoia.shared.JParanoia.announceObservers;
import static jparanoia.shared.JParanoia.removeObsName;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

class ServerChatThread extends Thread {
    private final static Logger logger = getLogger( MethodHandles.lookup().lookupClass());

    public Socket socket = null;
    public PrintWriter out = null;
    public BufferedReader in = null;
    public int playerID;
    public int threadNumber;
    ServerPlayer thisPlayer = new ServerPlayer( 999, "Unknown Player", false, "llama", "jazzer.txt" );
    String inputLine = "hello";
    boolean disconnectCalled = false;
    boolean hasSignedOn = false;
    boolean acceptedLogin = false;
    boolean observer = false;
    boolean listening = false;
    String obsName = "";
    Vector obsNameVector = null;

    public ServerChatThread( Socket paramSocket ) {
        super( JPServer.chatThreadGroup, "new JParanoia Thread" );
        this.socket = paramSocket;
        this.thisPlayer.setLoggedIn( false );
    }

    public void run() {
        try {
            this.out = new PrintWriter( this.socket.getOutputStream(), true );
            this.in = new BufferedReader( new InputStreamReader( this.socket.getInputStream() ) );
            this.listening = true;
            playerLogin();
            if ( !this.acceptedLogin ) {
                disconnect( true );
                this.listening = false;
                return;
            }
            while ( this.listening ) {
                String str = this.in.readLine();
                try {
                    switch ( parseInt( str.substring( 0, 3 ) ) ) {
                        case 86:
                            if ( this.listening ) {
                                disconnect( false );
                            }
                            this.listening = false;
                            break;
                        case 99:
                            spamString( str );
                            if ( hearObserversMenuItem.isSelected() ) {
                                observerChat( str.substring( 3 ) );
                            }
                            break;
                        case 100:
                            spamString( str );
                            generalChat( str.substring( 3 ) );
                            break;
                        case 110:
                            spamString( str );
                            actionChat( str.substring( 3 ) );
                            break;
                        case 120:
                            spamString( str );
                            speechChat( str.substring( 3 ) );
                            break;
                        case 130:
                            spamString( str );
                            thoughtChat( str.substring( 3 ) );
                            break;
                        case 199:
                            absoluteChat( str.substring( 3 ) );
                            break;
                        case 200:
                            privateMessageHandler( str.substring( 3 ), true );
                            break;
                        case 400:
                            this.obsName = str.substring( 3 );
                            addObsName( this.obsName );
                            if ( announceObservers ) {
                                observerHasJoined( this.obsName );
                            }
                            break;
                        case 601:
                            combatFrame.receiveCombatTurn( this.thisPlayer, str.substring( 3 ) );
                            break;
                        case 602:
                            this.thisPlayer.playerAbortedTurn();
                            break;
                        default:
                            logger.info( "\nError: unknown command type\n" + str + "\n" );
                            absoluteChat( "\nError: unknown command type\n" + str + "\n" );
                    }
                } catch ( NumberFormatException localNumberFormatException ) {
                    this.out.println( "199* * * WARNING * * *\n199Your client has sent an invalid command.\n199You will now be disconnected. Exit and relaunch the client.\n199If this problem persists, submit a bug report on the JParanoia website.\n199(http://www.byronbarry.com/jparanoia/)" );
                    this.out.println( "086Dropping due to invalid command." );
                    if ( !this.disconnectCalled ) {
                        disconnect( true );
                    }
                } catch ( NullPointerException localNullPointerException ) {
                    this.out.println( "086If you are reading this sentence, please alert the author via a bug report." );
                    logger.info( "NPE in ServerChatThread.run()" );
                    if ( !this.disconnectCalled ) {
                        disconnect( true );
                    }
                }
            }
            logger.info( "ServerChatThread.run() exiting naturally." );
        } catch ( SocketException localSocketException ) {
            if ( !this.observer && !this.disconnectCalled ) {
                logger.info( this.thisPlayer.getName() + " unexpectedly lost their connection. (SocketException)" );
                spamString( "199" +
                        this.thisPlayer.getName() +
                        " unexpectedly lost their connection. (SocketException)" );
                absoluteChat( this.thisPlayer.getName() +
                        " unexpectedly lost their connection. (SocketException)" );
            }
            disconnect( true );
        } catch ( IOException localIOException ) {
            if ( !this.observer && !this.disconnectCalled ) {
                logger.info( this.thisPlayer.getName() + " unexpectedly lost their connection. (IOException)" );
                spamString( "199" +
                        this.thisPlayer.getName() +
                        " unexpectedly lost their connection. (IOException)" );
                absoluteChat( this.thisPlayer.getName() +
                        " unexpectedly lost their connection. (IOException)" );
            }
            disconnect( true );
            localIOException.printStackTrace();
        } catch ( Exception localException ) {
            if ( !this.observer && !this.disconnectCalled ) {
                logger.info( this.thisPlayer.getName() + " unexpectedly lost their connection. (Exception)" );
                spamString( "199" +
                        this.thisPlayer.getName() +
                        " unexpectedly lost their connection. (Exception)" );
                absoluteChat( this.thisPlayer.getName() + " unexpectedly lost their connection. (Exception)" );
            }
            disconnect( true );
            localException.printStackTrace();
        }
    }

    public void playerLogin() {
        int i = 999;
        String str2 = "hello";
        try {
            this.out.println( "902" );
            while ( !str2.equals( "901" ) ) {
                str2 = this.in.readLine();
            }
            if ( !checkVersion() ) {
                return;
            }
            if ( !challenge() ) {
                return;
            }
            this.out.println( "900 Welcome to JParanoia" );
            this.out.println( "900----------------------" );
            for ( int j = 0; j < JPServer.numberOfPlayers; j++ ) {
                if ( JPServer.players[j].isAnActualPlayer() && !JPServer.players[j].isLoggedIn() ) {
                    this.out.println( "900" + j + "  " + JPServer.players[j].getName() );
                }
            }
            if ( JPServer.allowObservers ) {
                this.out.println( "90099 observe" );
            }
            while ( !this.acceptedLogin ) {
                this.out.println( "950" );
                this.out.println( "900----------------------" );
                this.out.println( "900 Enter your player number." );
                try {
                    str2 = this.in.readLine();
                    if ( str2 == null || str2.equals( "086" ) ) {
                        return;
                    }
                    i = Integer.parseInt( str2.substring( 3 ) );
                    if ( i < JPServer.numberOfPCs ) {
                        logger.info( "New user attempting to connect as " + players[i].getName() );
                        if ( players[i].isLoggedIn() ) {
                            this.out.println( "910 Invalid entry. That player is already logged in. (Nice try.)" );
                        } else {
                            this.out.println( "900 Enter your password." );
                            String str1 = this.in.readLine().substring( 3 );
                            this.acceptedLogin = players[i].checkPassword( str1 );
                            if ( !this.acceptedLogin ) {
                                this.out.println( "910 Incorrect password." );
                            }
                        }
                    } else if ( i == 99 ) {
                        if ( JPServer.allowObservers ) {
                            this.out.println( "040" );
                            this.out.println( "900 Entering as an observer..." );
                            this.observer = true;
                            this.acceptedLogin = true;
                        } else {
                            this.out.println( "910 Observers not currently allowed on this server." );
                        }
                    } else {
                        this.out.println( "910 Invalid entry. That is not a valid player ID number." );
                    }
                } catch ( NumberFormatException localNumberFormatException ) {
                    this.out.println( "910 Invalid entry. Not a number." );
                }
            }
            if ( !this.observer ) {
                this.playerID = i;
                this.thisPlayer = JPServer.players[this.playerID];
                this.thisPlayer.setThread( this );
                this.out.println( "041" );
                this.thisPlayer.setRealName( this.in.readLine() );
                this.out.println( "900 Password accepted. Entering server..." );
            }
            this.out.println( "199-----------------------------------------" );
            this.out.println( "904" + JPServer.numberOfPlayers );
            if ( this.in.readLine().equals( "numberOfPlayers received" ) ) {
                for ( int k = 0; k < JPServer.numberOfPlayers; k++ ) {
                    String str5 = "" + k;
                    if ( str5.length() < 2 ) {
                        str5 = "0" + str5;
                    }
                    String str3 = "n";
                    String str4 = "n";
                    if ( JPServer.players[k].isAnActualPlayer() ) {
                        str3 = "p";
                    }
                    if ( JPServer.players[k].isLoggedIn() ) {
                        str4 = "y";
                    }
                    this.out.println( "010" + str5 + str3 + str4 + JPServer.players[k].toString() );
                }
            }
            if ( this.observer ) {
                this.out.println( "90699" );
            } else {
                this.out.println( "906" + i );
                setName( this.thisPlayer.getID() );
                if ( JPServer.frozen ) {
                    this.out.println( "052" );
                }
                if ( this.thisPlayer.isMuted() ) {
                    this.out.println( "051" + this.thisPlayer.getID() );
                }
            }
            if ( JPServer.hearObserversMenuItem.isSelected() ) {
                this.out.println( "097" );
            } else {
                this.out.println( "098" );
            }
            this.out.println( "070" + JPServer.computerFontIncrease );
            this.out.println( "074" + JPServer.maxNumClones );
            this.out.println( "013" + JPServer.titleMessage );
            if ( JPServer.optionsMenu.useAnnouncementMenuItem.isSelected() ) {
                this.out.println( JPServer.getAnnouncement() );
            }
        } catch ( IOException localIOException ) {
            logger.info( "IO exception during connect()" );
            localIOException.printStackTrace();
        }
        if ( !this.observer ) {
            JPServer.spamString( "011" + this.playerID );
            JPServer.playerHasJoined( this.playerID );
        } else {
            numberOfConnectedObservers += 1;
            logger.info( "JPServer.numberOfConnectedObservers == " + numberOfConnectedObservers );
        }
        synchronized ( JPServer.chatThreads ) {
            this.threadNumber = numberOfConnectedClients;
            chatThreads.add( this );
            numberOfConnectedClients = chatThreads.size();
            logger.info( "JPServer.numberOfConnectedClients == " + numberOfConnectedClients );
            if ( !combatButton.isEnabled() &&
                    numberOfConnectedClients - numberOfConnectedObservers > 1 ) {
                combatButton.setEnabled( true );
            }
        }
        if ( !this.observer ) {
            this.thisPlayer.sendLastSavedCharsheet();
        }
    }

    public boolean checkVersion() {
        try {
            this.out.println( "955 Identify your version" );
            if ( new JPVersionNumber( this.in.readLine() ).compareTo( JPServer.MIN_COMPATIBLE_VERSION_NUMBER ) < 0 ) {
                this.out.println( "961Incompatible version. You must have JParanoia Client version " +
                        MIN_COMPATIBLE_VERSION_NUMBER.toString() +
                        " or greater to connect to this server. The JParanoia website is: " +
                        "http://www.byronbarry.com/jparanoia/" );
                logger.info( "Notice: Someone has attempted to connect using a client version that is too old." );
                return false;
            }
            this.out.println( "960" + JPServer.VERSION_NUMBER.toString() );
            String str = this.in.readLine();
            if ( str.substring( 0, 3 ).equals( "961" ) ) {
                JPServer.absoluteChat( str.substring( 3 ) );
                return false;
            }
            if ( str.equals( "960" ) ) {
                return true;
            }
            JPServer.absoluteChat( "An error occured: unrecognized version code." );
            return false;
        } catch ( IOException localIOException ) {
            logger.info( "Error: An I/O Exception occured during version check." );
        }
        return false;
    }

    public boolean challenge() {
        try {
            Random localRandom = new Random();
            int i = localRandom.nextInt( 40000 ) + 10000;
            if ( i % 10 == 0 ) {
                i++;
            }
            String str1 = "" + i;
            this.out.println( "970" + str1 );
            String str2 = this.in.readLine();
            int j = Integer.parseInt( str2 );
            int k = Integer.parseInt( str1.substring( 2 ) );
            if ( j % k == 0 ) {
                logger.info( "Client passed challenge." );
                return true;
            }
            JPServer.absoluteChat( "WARNING: Someone has attempted (and failed) to connect using an invalid client application. It is extremely likely that this is not an accident." );
            return false;
        } catch ( IOException localIOException ) {
            logger.info( "Error: An I/O Exception occured during check." );
        }
        return false;
    }

    public void disconnect( boolean paramBoolean ) {
        this.disconnectCalled = true;
        this.listening = false;
        if ( paramBoolean ) {
            this.out.println( "086" );
        }
        try {
            this.out.close();
            this.in.close();
            this.socket.close();
        } catch ( IOException localIOException ) {
            logger.info( "Unable to close Writer, Reader or Socket." );
            localIOException.printStackTrace();
        }
        if ( this.observer ) {
            numberOfConnectedObservers -= 1;
            logger.info( "JPServer.numberOfConnectedObservers == " + numberOfConnectedObservers );
            removeObsName( this.obsName );
            if ( announceObservers ) {
                observerHasLeft( this.obsName );
            }
        }
        if ( !this.thisPlayer.isLoggedIn() ) {
            logger.info( "Unknown user disconnected. (Not signed in.)" );
        } else {
            JPServer.spamString( "012" + this.playerID );
            System.out.print( this.thisPlayer.getName() + " disconnected... " );
            this.thisPlayer.setThread( null );
        }
        synchronized ( JPServer.chatThreads ) {
            if ( this.observer || this.thisPlayer.isLoggedIn() ) {
                chatThreads.remove( this.threadNumber );
            }
            if ( !this.observer && this.thisPlayer.isLoggedIn() ) {
                playerHasLeft( this.playerID );
            }
            numberOfConnectedClients = chatThreads.size();
            logger.info( "JPServer.numberOfConnectedClients == " + numberOfConnectedClients );
            reassignThreadNumbers();
        }
        if ( JPServer.combatButton.isEnabled() &&
                JPServer.numberOfConnectedClients - JPServer.numberOfConnectedObservers < 2 ) {
            JPServer.combatButton.setEnabled( false );
        }
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\ServerChatThread.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
