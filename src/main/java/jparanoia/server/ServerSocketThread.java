package jparanoia.server;
import java.io.IOException;

public class ServerSocketThread extends Thread {
    final int PORT_NUMBER = 11777;
    public boolean listening = true;
    java.net.ServerSocket serverSocket = null;
    java.net.Socket someSock;

    public void run() {
        JPServer.stopServerMenuItem.setEnabled( true );
        JPServer.startServerMenuItem.setEnabled( false );
        try {
            this.serverSocket = new java.net.ServerSocket( 11777 );
        } catch ( IOException localIOException1 ) {
            System.out.println( "Error starting server: could not listen on port: 11777" );
            JPServer.absoluteChat( "Error starting server: could not listen on port: 11777" );
            JPServer.absoluteChat( "Likely cause: another instance of server is currently running." );
            return;
        }
        System.out.println( "ServerSocket established and listening on port 11777" );
        JPServer.absoluteChat( "\nServer started and listening." );
        if ( JPServer.showTimeStamps ) {
            JPServer.displayTimeStamp();
        }
        JPServer.serverRunning = true;
        try {
            while ( this.listening ) {
                this.someSock = this.serverSocket.accept();
                System.out.print( "New connection accepted... " );
                new ServerChatThread( this.someSock ).start();
            }
        } catch ( java.net.SocketException localSocketException ) {
            if ( this.listening ) {
                JPServer.absoluteChat( "ServerSocket closed by outside force..." );
            } else {
                JPServer.absoluteChat( "ServerSocket closed by user (that's you)." );
            }
        } catch ( IOException localIOException2 ) {
            System.err.println( "Error: unhandled I/O exception" );
            localIOException2.printStackTrace();
        }
        JPServer.stopServerMenuItem.setEnabled( false );
        JPServer.startServerMenuItem.setEnabled( true );
        System.out.println( "ServerSocket closed, no longer listening on 11777" );
        JPServer.absoluteChat( "Server no longer listening for new connections." );
        if ( JPServer.showTimeStamps ) {
            JPServer.displayTimeStamp();
        }
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\ServerSocketThread.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
