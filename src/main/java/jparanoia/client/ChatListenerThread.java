package jparanoia.client;
import java.io.IOException;
import java.net.SocketException;
import javax.swing.JOptionPane;

public class ChatListenerThread extends Thread {
    public void run() {
        try {
            /*  21 */
            while ( JPClient.stayConnected ) {
                /*  23 */
                if ( JPClient.stayConnected ) {
                    /*  25 */
                    String str = JPClient.in.readLine();



                    /*  29 */
                    if ( str != null ) {
                        switch ( Integer.parseInt( str.substring( 0, 3 ) ) ) {
                            case 100:
                                /*  31 */
                                JPClient.generalChat( str.substring( 3 ) );
                                break;
                            /*  32 */
                            case 110:
                                JPClient.actionChat( str.substring( 3 ) );
                                break;
                            /*  33 */
                            case 120:
                                JPClient.speechChat( str.substring( 3 ) );
                                break;
                            /*  34 */
                            case 130:
                                JPClient.thoughtChat( str.substring( 3 ) );
                                break;
                            /*  35 */
                            case 199:
                                JPClient.absoluteChat( str.substring( 3 ) );
                                break;
                            /*  36 */
                            case 200:
                                if ( !JPClient.observer ) {
                                    JPClient.receivePrivateMessage( str.substring( 3 ) );
                                }
                                break;
                            case 210:
                                JPClient.gmSendingPrivateMessage( str.substring( 3 ) );
                                break;
                            /*  38 */
                            case 211:
                                JPClient.clearMessageStatusLabel();
                                break;
                            /*  39 */
                            case 400:
                                JPClient.receiveCharacterSheet();
                                yield();
                                break;
                            case 404:
                                /*  41 */
                                JPClient.displayImage( str.substring( 3 ) );
                                break;
                            /*  42 */
                            case 597:
                                JPClient.combatComplete();
                                break;
                            /*  43 */
                            case 599:
                                JPClient.startCombat();
                                break;
                            /*  44 */
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
                                /*  48 */
                                JPClient.absoluteChat( str.substring( 3 ) );
                                break;
                            /*  49 */
                            case 902:
                                JPClient.absoluteChat( str.substring( 3 ) );
                                JPClient.out.println( "901" );
                                break;
                            /*  50 */
                            case 904:
                                JPClient.initializePlayerList( str.substring( 3 ) );
                                break;
                            /*  51 */
                            case 906:
                                JPClient.receiveMyPlayerID( str.substring( 3 ) );
                                JPClient.loggedIn = true;
                                break;
                            /*  52 */
                            case 910:
                                JPClient.loginError();
                                JPClient.absoluteChat( str.substring( 3 ) );
                                break;
                            case 950:
                                /*  55 */
                                JPClient.inputLine.setEnabled( true );
                                JPClient.inputLine.requestFocus();
                                break;
                            case 955:
                                /*  58 */
                                JPClient.out.println( JPClient.VERSION_NUMBER.toString() );
                                break;
                            case 960:
                                /*  62 */
                                JPClient.checkVersion( str.substring( 3 ) );
                                break;
                            case 961:
                                /*  64 */
                                JPClient.absoluteChat( str.substring( 3 ) );
                                JPClient.disconnect( false );
                                break;
                            case 970:
                                /*  66 */
                                JPClient.respond( str.substring( 3 ) );
                                break;
                            case 999:
                                /*  68 */
                                JPClient.absoluteChat( str.substring( 3 ) );
                                break;
                            /*  69 */
                            case 10:
                                JPClient.receivePlayerUpdate( str.substring( 3 ) );
                                break;
                            /*  70 */
                            case 11:
                                JPClient.playerHasJoined( str.substring( 3 ) );
                                break;
                            /*  71 */
                            case 12:
                                JPClient.playerHasLeft( str.substring( 3 ) );
                                break;
                            /*  72 */
                            case 13:
                                JPClient.updateTitle( str.substring( 3 ) );
                                break;
                            /*  73 */
                            case 20:
                                JPClient.playerDemoted();
                                break;
                            /*  74 */
                            case 21:
                                JPClient.playerPromoted();
                                break;
                            /*  75 */
                            case 40:
                                JPClient.setObserver( true );
                                JPClient.loggedIn = true;
                                break;
                            /*  76 */
                            case 41:
                                JPClient.setObserver( false );
                                break;
                            /*  77 */
                            case 50:
                                JPClient.checkUnmuted( str.substring( 3 ) );
                                break;
                            /*  78 */
                            case 51:
                                JPClient.checkMuted( str.substring( 3 ) );
                                break;
                            /*  79 */
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
                            /*  82 */
                            case 61:
                                JPClient.playerHasUndied( str.substring( 3 ) );
                                break;
                            /*  83 */
                            case 70:
                                JPClient.updateComputerFontIncrease( str.substring( 3 ) );
                                break;
                            /*  84 */
                            case 74:
                                JPClient.updateMaxCloneNumber( str.substring( 3 ) );
                                break;
                            case 86:
                                /*  87 */
                                if ( !JPClient.disconnectCalled ) {
                                    JPClient.disconnect( true );
                                } else
                                    /*  88 */ {
                                    JPClient.disconnectCalled = false;
                                }
                                break;
                            case 97:
                                JPClient.listenObservers();
                                break;
                            /*  90 */
                            case 98:
                                JPClient.muteObservers();
                                break;
                            /*  91 */
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
                /*  96 */
                yield();
            }

            /*  99 */
            JPClient.in.close();
            /* 100 */
            JPClient.out.close();
            /* 101 */
            JPClient.mySock.close();
            /* 102 */
            JPClient.loggedIn = false;
            /* 103 */
            JPClient.connected = false;

            /* 105 */
            JPClient.absoluteChat( "\nDisconnected from server.\n" );
            /* 106 */
            JPClient.muted = false;
            /* 107 */
            JPClient.frozen = false;
        } catch ( SocketException localSocketException ) {
            /* 112 */
            System.out.println( localSocketException.getMessage() );
            /* 113 */
            JOptionPane.showMessageDialog( null, "Connection to host lost.", "Connection lost", 0 );
            /* 114 */
            JPClient.disconnect( false );
            /* 115 */
            JPClient.loggedIn = false;
            /* 116 */
            JPClient.connected = false;
            /* 117 */
            JPClient.muted = false;
            /* 118 */
            JPClient.frozen = false;
            /* 119 */
            System.out.println( "JPClient.socket closed\n" );
            /* 120 */
            JPClient.absoluteChat( "\nDisconnected from server.\n" );
        } catch ( IOException localIOException ) {
        } catch ( StringIndexOutOfBoundsException localStringIndexOutOfBoundsException ) {











            /* 147 */
            run();
        }
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\client\ChatListenerThread.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
