package jparanoia.client;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.SocketException;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;
import static jparanoia.client.JPClient.VERSION_NUMBER;
import static jparanoia.client.JPClient.abortCombatTurn;
import static jparanoia.client.JPClient.absoluteChat;
import static jparanoia.client.JPClient.actionChat;
import static jparanoia.client.JPClient.checkMuted;
import static jparanoia.client.JPClient.checkUnmuted;
import static jparanoia.client.JPClient.checkVersion;
import static jparanoia.client.JPClient.clearMessageStatusLabel;
import static jparanoia.client.JPClient.combatComplete;
import static jparanoia.client.JPClient.connected;
import static jparanoia.client.JPClient.disconnect;
import static jparanoia.client.JPClient.disconnectCalled;
import static jparanoia.client.JPClient.displayImage;
import static jparanoia.client.JPClient.freezeMe;
import static jparanoia.client.JPClient.frozen;
import static jparanoia.client.JPClient.generalChat;
import static jparanoia.client.JPClient.gmSendingPrivateMessage;
import static jparanoia.client.JPClient.hearObservers;
import static jparanoia.client.JPClient.initializePlayerList;
import static jparanoia.client.JPClient.inputLine;
import static jparanoia.client.JPClient.listenObservers;
import static jparanoia.client.JPClient.loggedIn;
import static jparanoia.client.JPClient.loginError;
import static jparanoia.client.JPClient.muteObservers;
import static jparanoia.client.JPClient.muted;
import static jparanoia.client.JPClient.observer;
import static jparanoia.client.JPClient.observerChat;
import static jparanoia.client.JPClient.out;
import static jparanoia.client.JPClient.playerDemoted;
import static jparanoia.client.JPClient.playerHasDied;
import static jparanoia.client.JPClient.playerHasJoined;
import static jparanoia.client.JPClient.playerHasLeft;
import static jparanoia.client.JPClient.playerHasUndied;
import static jparanoia.client.JPClient.playerPromoted;
import static jparanoia.client.JPClient.receiveCharacterSheet;
import static jparanoia.client.JPClient.receiveMyPlayerID;
import static jparanoia.client.JPClient.receivePlayerUpdate;
import static jparanoia.client.JPClient.receivePrivateMessage;
import static jparanoia.client.JPClient.respond;
import static jparanoia.client.JPClient.setObserver;
import static jparanoia.client.JPClient.speechChat;
import static jparanoia.client.JPClient.startCombat;
import static jparanoia.client.JPClient.takeCombatTurn;
import static jparanoia.client.JPClient.thoughtChat;
import static jparanoia.client.JPClient.unfreezeMe;
import static jparanoia.client.JPClient.updateComputerFontIncrease;
import static jparanoia.client.JPClient.updateMaxCloneNumber;
import static jparanoia.client.JPClient.updateTitle;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

public class ChatListenerThread extends Thread {
    private final static Logger logger = getLogger( MethodHandles.lookup().lookupClass());

    public void run() {
        try {
            while ( JPClient.stayConnected ) {
                if ( JPClient.stayConnected ) {
                    String str = JPClient.in.readLine();
                    if ( str != null ) {
                        switch ( Integer.parseInt( str.substring( 0, 3 ) ) ) {
                            case 100:
                                generalChat( str.substring( 3 ) );
                                break;
                            case 110:
                                actionChat( str.substring( 3 ) );
                                break;
                            case 120:
                                speechChat( str.substring( 3 ) );
                                break;
                            case 130:
                                thoughtChat( str.substring( 3 ) );
                                break;
                            case 199:
                                absoluteChat( str.substring( 3 ) );
                                break;
                            case 200:
                                if ( !observer ) {
                                    receivePrivateMessage( str.substring( 3 ) );
                                }
                                break;
                            case 210:
                                gmSendingPrivateMessage( str.substring( 3 ) );
                                break;
                            case 211:
                                clearMessageStatusLabel();
                                break;
                            case 400:
                                receiveCharacterSheet();
                                yield();
                                break;
                            case 404:
                                displayImage( str.substring( 3 ) );
                                break;
                            case 597:
                                combatComplete();
                                break;
                            case 599:
                                startCombat();
                                break;
                            case 600:
                                if ( !observer ) {
                                    takeCombatTurn();
                                }
                                break;
                            case 609:
                                if ( !observer ) {
                                    abortCombatTurn();
                                }
                                break;
                            case 900:
                                absoluteChat( str.substring( 3 ) );
                                break;
                            case 902:
                                absoluteChat( str.substring( 3 ) );
                                out.println( "901" );
                                break;
                            case 904:
                                initializePlayerList( str.substring( 3 ) );
                                break;
                            case 906:
                                receiveMyPlayerID( str.substring( 3 ) );
                                loggedIn = true;
                                break;
                            case 910:
                                loginError();
                                absoluteChat( str.substring( 3 ) );
                                break;
                            case 950:
                                inputLine.setEnabled( true );
                                inputLine.requestFocus();
                                break;
                            case 955:
                                out.println( VERSION_NUMBER.toString() );
                                break;
                            case 960:
                                checkVersion( str.substring( 3 ) );
                                break;
                            case 961:
                                absoluteChat( str.substring( 3 ) );
                                disconnect( false );
                                break;
                            case 970:
                                respond( str.substring( 3 ) );
                                break;
                            case 999:
                                absoluteChat( str.substring( 3 ) );
                                break;
                            case 10:
                                receivePlayerUpdate( str.substring( 3 ) );
                                break;
                            case 11:
                                playerHasJoined( str.substring( 3 ) );
                                break;
                            case 12:
                                playerHasLeft( str.substring( 3 ) );
                                break;
                            case 13:
                                updateTitle( str.substring( 3 ) );
                                break;
                            case 20:
                                playerDemoted();
                                break;
                            case 21:
                                playerPromoted();
                                break;
                            case 40:
                                setObserver( true );
                                loggedIn = true;
                                break;
                            case 41:
                                setObserver( false );
                                break;
                            case 50:
                                checkUnmuted( str.substring( 3 ) );
                                break;
                            case 51:
                                checkMuted( str.substring( 3 ) );
                                break;
                            case 52:
                                if ( !observer ) {
                                    freezeMe();
                                }
                                break;
                            case 53:
                                if ( !observer ) {
                                    unfreezeMe();
                                }
                                break;
                            case 60:
                                playerHasDied( str.substring( 3 ) );
                                break;
                            case 61:
                                playerHasUndied( str.substring( 3 ) );
                                break;
                            case 70:
                                updateComputerFontIncrease( str.substring( 3 ) );
                                break;
                            case 74:
                                updateMaxCloneNumber( str.substring( 3 ) );
                                break;
                            case 86:
                                if ( !disconnectCalled ) {
                                    disconnect( true );
                                } else {
                                    disconnectCalled = false;
                                }
                                break;
                            case 97:
                                listenObservers();
                                break;
                            case 98:
                                muteObservers();
                                break;
                            case 99:
                                if ( observer || hearObservers ) {
                                    observerChat( str.substring( 3 ) );
                                }
                                break;
                            default:
                                logger.info( "\nError: unknown command type\n" + str + "\n" );
                        }
                    }
                }
                yield();
            }
            JPClient.in.close();
            JPClient.out.close();
            JPClient.mySock.close();
            JPClient.loggedIn = false;
            JPClient.connected = false;
            JPClient.absoluteChat( "\nDisconnected from server.\n" );
            JPClient.muted = false;
            JPClient.frozen = false;
        } catch ( SocketException localSocketException ) {
            logger.info( localSocketException.getMessage() );
            showMessageDialog( null, "Connection to host lost.", "Connection lost", ERROR_MESSAGE );
            disconnect( false );
            loggedIn = false;
            connected = false;
            muted = false;
            frozen = false;
            logger.info( "JPClient.socket closed\n" );
            absoluteChat( "\nDisconnected from server.\n" );
        } catch ( IOException ignored) {}
          catch ( StringIndexOutOfBoundsException localStringIndexOutOfBoundsException ) {
            run();
        }
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\client\ChatListenerThread.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
