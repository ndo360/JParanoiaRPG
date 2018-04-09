package jparanoia.server;
import java.io.IOException;
import static java.lang.System.err;
import static java.lang.System.out;
import java.lang.invoke.MethodHandles;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import static jparanoia.server.JPServer.absoluteChat;
import static jparanoia.server.JPServer.displayTimeStamp;
import static jparanoia.server.JPServer.serverRunning;
import static jparanoia.server.JPServer.showTimeStamps;
import static jparanoia.server.JPServer.startServerMenuItem;
import static jparanoia.server.JPServer.stopServerMenuItem;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

public class ServerSocketThread extends Thread {
    private final static Logger logger = getLogger( MethodHandles.lookup().lookupClass());

    final int PORT_NUMBER = 11777;
    public boolean listening = true;
    ServerSocket serverSocket = null;
    Socket someSock;

    public void run() {
        stopServerMenuItem.setEnabled( true );
        startServerMenuItem.setEnabled( false );
        try {
            this.serverSocket = new ServerSocket( 11777 );
        } catch ( IOException localIOException1 ) {
            logger.info( "Error starting server: could not listen on port: 11777" );
            absoluteChat( "Error starting server: could not listen on port: 11777" );
            absoluteChat( "Likely cause: another instance of server is currently running." );
            return;
        }
        logger.info( "ServerSocket established and listening on port 11777" );
        absoluteChat( "\nServer started and listening." );
        if ( showTimeStamps ) {
            displayTimeStamp();
        }
        serverRunning = true;
        try {
            while ( this.listening ) {
                this.someSock = this.serverSocket.accept();
                out.print( "New connection accepted... " );
                new ServerChatThread( this.someSock ).start();
            }
        } catch ( SocketException localSocketException ) {
            if ( this.listening ) {
                absoluteChat( "ServerSocket closed by outside force..." );
            } else {
                absoluteChat( "ServerSocket closed by user (that's you)." );
            }
        } catch ( IOException localIOException2 ) {
            err.println( "Error: unhandled I/O exception" );
            localIOException2.printStackTrace();
        }
        stopServerMenuItem.setEnabled( false );
        startServerMenuItem.setEnabled( true );
        logger.info( "ServerSocket closed, no longer listening on 11777" );
        absoluteChat( "Server no longer listening for new connections." );
        if ( showTimeStamps ) {
            displayTimeStamp();
        }
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\ServerSocketThread.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
