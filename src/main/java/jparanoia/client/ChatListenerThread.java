package jparanoia.client;
import java.io.IOException;
import java.net.SocketException;
import javax.swing.JOptionPane;

public class ChatListenerThread extends Thread {
    public void run() {
        try {
            while ( JPClient.stayConnected ) {
                if ( JPClient.stayConnected ) {
                    String str = JPClient.in.readLine();
                    if ( str != null ) {
                        switch ( Integer.parseInt( str.substring( 0, 3 ) ) ) {
                            case 100:
                                JPClient.generalChat( str.substring( 3 ) );
                                break;
                            case 110:
                                JPClient.actionChat( str.substring( 3 ) );
                                break;
                            case 120:
                                JPClient.speechChat( str.substring( 3 ) );
                                break;
                            case 130:
                                JPClient.thoughtChat( str.substring( 3 ) );
                                break;
                            case 199:
                                JPClient.absoluteChat( str.substring( 3 ) );
                                break;
                            case 200:
                                if ( !JPClient.observer ) {
                                    JPClient.receivePrivateMessage( str.substring( 3 ) );
                                }
                                break;
                            case 210:
                                JPClient.gmSendingPrivateMessage( str.substring( 3 ) );
                                break;
                            case 211:
                                JPClient.clearMessageStatusLabel();
                                break;
                            case 400:
                                JPClient.receiveCharacterSheet();
                                yield();
                                break;
                            case 404:
                                JPClient.displayImage( str.substring( 3 ) );
                                break;
                            case 597:
                                JPClient.combatComplete();
                                break;
                            case 599:
                                JPClient.startCombat();
                                break;
                            case 600:
                                if ( !JPClient.observer ) {
                                    JPClient.takeCombatTurn();
                                }
                                break;
                            case 609:
                                if ( !JPClient.observer ) {
                                    JPClient.abortCombatTurn();
                                }
                                break;
                            case 900:
                                JPClient.absoluteChat( str.substring( 3 ) );
                                break;
                            case 902:
                                JPClient.absoluteChat( str.substring( 3 ) );
                                JPClient.out.println( "901" );
                                break;
                            case 904:
                                JPClient.initializePlayerList( str.substring( 3 ) );
                                break;
                            case 906:
                                JPClient.receiveMyPlayerID( str.substring( 3 ) );
                                JPClient.loggedIn = true;
                                break;
                            case 910:
                                JPClient.loginError();
                                JPClient.absoluteChat( str.substring( 3 ) );
                                break;
                            case 950:
                                JPClient.inputLine.setEnabled( true );
                                JPClient.inputLine.requestFocus();
                                break;
                            case 955:
                                JPClient.out.println( JPClient.VERSION_NUMBER.toString() );
                                break;
                            case 960:
                                JPClient.checkVersion( str.substring( 3 ) );
                                break;
                            case 961:
                                JPClient.absoluteChat( str.substring( 3 ) );
                                JPClient.disconnect( false );
                                break;
                            case 970:
                                JPClient.respond( str.substring( 3 ) );
                                break;
                            case 999:
                                JPClient.absoluteChat( str.substring( 3 ) );
                                break;
                            case 10:
                                JPClient.receivePlayerUpdate( str.substring( 3 ) );
                                break;
                            case 11:
                                JPClient.playerHasJoined( str.substring( 3 ) );
                                break;
                            case 12:
                                JPClient.playerHasLeft( str.substring( 3 ) );
                                break;
                            case 13:
                                JPClient.updateTitle( str.substring( 3 ) );
                                break;
                            case 20:
                                JPClient.playerDemoted();
                                break;
                            case 21:
                                JPClient.playerPromoted();
                                break;
                            case 40:
                                JPClient.setObserver( true );
                                JPClient.loggedIn = true;
                                break;
                            case 41:
                                JPClient.setObserver( false );
                                break;
                            case 50:
                                JPClient.checkUnmuted( str.substring( 3 ) );
                                break;
                            case 51:
                                JPClient.checkMuted( str.substring( 3 ) );
                                break;
                            case 52:
                                if ( !JPClient.observer ) {
                                    JPClient.freezeMe();
                                }
                                break;
                            case 53:
                                if ( !JPClient.observer ) {
                                    JPClient.unfreezeMe();
                                }
                                break;
                            case 60:
                                JPClient.playerHasDied( str.substring( 3 ) );
                                break;
                            case 61:
                                JPClient.playerHasUndied( str.substring( 3 ) );
                                break;
                            case 70:
                                JPClient.updateComputerFontIncrease( str.substring( 3 ) );
                                break;
                            case 74:
                                JPClient.updateMaxCloneNumber( str.substring( 3 ) );
                                break;
                            case 86:
                                if ( !JPClient.disconnectCalled ) {
                                    JPClient.disconnect( true );
                                } else {
                                    JPClient.disconnectCalled = false;
                                }
                                break;
                            case 97:
                                JPClient.listenObservers();
                                break;
                            case 98:
                                JPClient.muteObservers();
                                break;
                            case 99:
                                if ( JPClient.observer || JPClient.hearObservers ) {
                                    JPClient.observerChat( str.substring( 3 ) );
                                }
                                break;
                            default:
                                System.out.println( "\nError: unknown command type\n" + str + "\n" );
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
            System.out.println( localSocketException.getMessage() );
            JOptionPane.showMessageDialog( null, "Connection to host lost.", "Connection lost", JOptionPane.ERROR_MESSAGE );
            JPClient.disconnect( false );
            JPClient.loggedIn = false;
            JPClient.connected = false;
            JPClient.muted = false;
            JPClient.frozen = false;
            System.out.println( "JPClient.socket closed\n" );
            JPClient.absoluteChat( "\nDisconnected from server.\n" );
        } catch ( IOException localIOException ) {
        } catch ( StringIndexOutOfBoundsException localStringIndexOutOfBoundsException ) {
            run();
        }
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\client\ChatListenerThread.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
