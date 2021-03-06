package http;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;
import javax.net.ssl.HttpsURLConnection;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;
import util.TagTosser;

public class Direct {
    private final static Logger logger = getLogger( MethodHandles.lookup().lookupClass());

    public static void main( String[] paramArrayOfString ) {
        try {
            HttpsURLConnection localHttpsURLConnection = null;
            URL localURL = null;
            try {
                localURL = new URL( "https://utdirect.utexas.edu/" );
            } catch ( Exception localException2 ) {
                localException2.printStackTrace();
                System.exit( 0 );
            }
            try {
                localHttpsURLConnection = (HttpsURLConnection) localURL.openConnection();
            } catch ( Exception localException3 ) {
                localException3.printStackTrace();
                System.exit( 0 );
            }
            localHttpsURLConnection.setRequestProperty( "User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)" );
            localHttpsURLConnection.addRequestProperty( "Cookie", "DOC=/acct/rec/wio/wio_home.WBX;domain=.utexas.edu;path=/" );
            localHttpsURLConnection.setInstanceFollowRedirects( true );
            localHttpsURLConnection.setDoOutput( true );
            localHttpsURLConnection.setRequestMethod( "POST" );
            PrintWriter localPrintWriter = new PrintWriter( localHttpsURLConnection.getOutputStream(), true );
            String str1 = "LOGON=" + URLEncoder.encode( "buried", "UTF-8" );
            String str2 = "PASSWORDS=" + URLEncoder.encode( "wdml1299", "UTF-8" );
            String str3 = str1 + "&" + str2 + "&NEW_PASSWORD=&CONFIRM_NEW_PASSWORD=&";
            localPrintWriter.println( str3 );
            localPrintWriter.flush();
            localPrintWriter.close();
            Map localMap = localHttpsURLConnection.getHeaderFields();
            Set localSet = localMap.keySet();
            Object[] arrayOfObject = localSet.toArray();
            for ( final Object anArrayOfObject : arrayOfObject ) {
                logger.info( "*** " + anArrayOfObject + ": " + localMap.get( anArrayOfObject ).toString() + " ***" );
            }
            BufferedReader localBufferedReader = TagTosser.tossTags( new BufferedReader( new InputStreamReader( localHttpsURLConnection
                    .getInputStream() ) ) );
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


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\http\Direct.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
