package http.raw;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.invoke.MethodHandles;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import static javax.net.ssl.SSLServerSocketFactory.getDefault;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

public class RawSecureServer {
    private final static Logger logger = getLogger( MethodHandles.lookup().lookupClass());

    public static void main( String[] paramArrayOfString ) {
        try {
            SSLServerSocketFactory localSSLServerSocketFactory = (SSLServerSocketFactory) getDefault();
            SSLSocketFactory localSSLSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLServerSocket localSSLServerSocket = (SSLServerSocket) localSSLServerSocketFactory.createServerSocket( 443 );
            logger.info( "Listening on port 443" );
            SSLSocket localSSLSocket = (SSLSocket) localSSLServerSocket.accept();
            PrintWriter localPrintWriter = new PrintWriter( localSSLSocket.getOutputStream(), true );
            BufferedReader localBufferedReader = new BufferedReader( new InputStreamReader( localSSLSocket.getInputStream() ) );
            String str;
            while ( ( str = localBufferedReader.readLine() ) != null ) {
                logger.info( str );
            }
        } catch ( Exception localException ) {
            localException.printStackTrace();
        }
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\http\raw\RawSecureServer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
