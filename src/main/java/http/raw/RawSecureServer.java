package http.raw;
import java.io.BufferedReader;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class RawSecureServer {
    public static void main( String[] paramArrayOfString ) {
        try {
            SSLServerSocketFactory localSSLServerSocketFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            SSLSocketFactory localSSLSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLServerSocket localSSLServerSocket = (SSLServerSocket) localSSLServerSocketFactory.createServerSocket( 443 );
            System.out.println( "Listening on port 443" );
            SSLSocket localSSLSocket = (SSLSocket) localSSLServerSocket.accept();
            java.io.PrintWriter localPrintWriter = new java.io.PrintWriter( localSSLSocket.getOutputStream(), true );
            BufferedReader localBufferedReader = new BufferedReader( new java.io.InputStreamReader( localSSLSocket.getInputStream() ) );
            String str;
            while ( ( str = localBufferedReader.readLine() ) != null ) {
                System.out.println( str );
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
