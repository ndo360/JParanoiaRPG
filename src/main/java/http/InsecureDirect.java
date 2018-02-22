package http;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.lang.invoke.MethodHandles;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

public class InsecureDirect {
    private final static Logger logger = getLogger( MethodHandles.lookup().lookupClass());

    public static void main( String[] paramArrayOfString ) {
        try {
            HttpURLConnection localHttpURLConnection = null;
            URL localURL = null;
            try {
                localURL = new URL( "http://127.0.0.1/hi.html" );
            } catch ( Exception localException2 ) {
                localException2.printStackTrace();
                System.exit( 0 );
            }
            try {
                localHttpURLConnection = (HttpURLConnection) localURL.openConnection();
            } catch ( Exception localException3 ) {
                localException3.printStackTrace();
                System.exit( 0 );
            }
            localHttpURLConnection.setRequestProperty( "User-Agent", "dungo" );
            localHttpURLConnection.setDoOutput( true );
            localHttpURLConnection.setRequestMethod( "POST" );
            PrintWriter localPrintWriter = new PrintWriter( localHttpURLConnection.getOutputStream(), true );
            String str1 = "LOGON=" + URLEncoder.encode( "useruser", "UTF-8" );
            String str2 = "PASSWORDS=" + URLEncoder.encode( "foobar", "UTF-8" );
            String str3 = str1 + "&" + str2 + "&NEW_PASSWORD=&CONFIRM_NEW_PASSWORD=&";
            localPrintWriter.println( str3 );
            localPrintWriter.flush();
            localPrintWriter.close();
            BufferedReader localBufferedReader = new BufferedReader( new java.io.InputStreamReader( localHttpURLConnection
                    .getInputStream() ) );
            String str4;
            while ( ( str4 = localBufferedReader.readLine() ) != null ) {
                logger.info( str4 );
            }
        } catch ( Exception localException1 ) {
            localException1.printStackTrace();
            System.exit( 0 );
        }
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\http\InsecureDirect.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
