package http.listener;
import static http.PageSpewer.spewPage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.invoke.MethodHandles;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

public class HttpListener {
    private final static Logger logger = getLogger( MethodHandles.lookup().lookupClass());

    static BufferedReader in;
    static PrintWriter out;
    static String incomingLine;
    static Socket socket;
    static boolean get;

    public static void main( String[] paramArrayOfString ) {
        ServerSocket localServerSocket = null;
        try {
            localServerSocket = new ServerSocket( 80 );
            logger.info( "Listening on TCP port 80..." );
            for ( ; ; ) {
                socket = localServerSocket.accept();
                doStuff();
            }
        } catch ( Exception localException ) {
            logger.info( "Aww, exception." );
            localException.printStackTrace();
        }
    }

    public static void doStuff() {
        InetAddress localInetAddress = socket.getInetAddress();
        logger.info( "Connection from: " +
                localInetAddress.getHostAddress() +
                " aka: " +
                localInetAddress.getHostName() );
        try {
            out = new PrintWriter( socket.getOutputStream(), true );
            in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
            incomingLine = "hi there";
            int i = 1;
            get = false;
            while ( i != 0 ) {
                incomingLine = in.readLine();
                logger.info( incomingLine + "*" );
                if ( incomingLine.startsWith( "GET" ) ) {
                    get = true;
                }
                if ( incomingLine.equals( "" ) && get ) {
                    logger.info( "Sending local page..." );
                    spewPage( "C:/web publishing/gameTestForm.html", out );
                    logger.info( "Form sent." );
                    i = 0;
                    socket.close();
                }
            }
        } catch ( NullPointerException localNullPointerException ) {
            logger.info( "Null input." );
            while ( incomingLine == null ) {
                try {
                    incomingLine = in.readLine();
                } catch ( Exception ignored ) {}
                if ( incomingLine != null ) {
                    logger.info( incomingLine + "*" );
                    doStuff();
                }
            }
        } catch ( Exception localException2 ) {
            logger.info( "Exception" );
            localException2.printStackTrace();
        }
    }

    public static void sendLinefeed() {
        logger.info( "Sending line feed..." );
        out.println();
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\http\listener\HttpListener.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
