package http.raw;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class RawSecureSocket {
    public static void main( String[] paramArrayOfString ) {
        try {
            SSLSocketFactory localSSLSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket localSSLSocket = (SSLSocket) localSSLSocketFactory.createSocket( "utdirect.utexas.edu", 443 );
            PrintWriter localPrintWriter = new PrintWriter( localSSLSocket.getOutputStream(), true );
            BufferedReader localBufferedReader = new BufferedReader( new InputStreamReader( localSSLSocket.getInputStream() ) );
            localPrintWriter.println( "POST /security-443/logon_check.logonform HTTP/1.1" );
            localPrintWriter.println( "Accept: image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, */*" );
            localPrintWriter.println( "User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)" );
            localPrintWriter.println( "Referer: https://utdirect.utexas.edu/" );
            localPrintWriter.println( "Content-Type: application/x-www-form-urlencoded" );
            localPrintWriter.println( "Host: 127.0.0.1" );
            localPrintWriter.println( "Content-Length: 68" );
            localPrintWriter.println( "Connection: Keep-Alive" );
            localPrintWriter.println();
            localPrintWriter.println( "LOGON=buried&PASSWORDS=wdml1299&NEW_PASSWORD=&CONFIRM_NEW_PASSWORD=&" );
            String str;
            while ( ( str = localBufferedReader.readLine() ) != null ) {
                System.out.println( str );
            }
        } catch ( Exception localException ) {
            localException.printStackTrace();
        }
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\http\raw\RawSecureSocket.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
