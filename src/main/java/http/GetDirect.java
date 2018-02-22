package http;
import java.io.BufferedReader;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

public class GetDirect {
    private final static Logger logger = getLogger( MethodHandles.lookup().lookupClass());

    public static void main( String[] paramArrayOfString ) {
        try {
            HttpsURLConnection localHttpsURLConnection = null;
            URL localURL = null;
            try {
                localURL = new URL( "https://utdirect.utexas.edu/security-443/UTEIDLogon.wb" );
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
            localHttpsURLConnection.setInstanceFollowRedirects( false );
            Map localMap = localHttpsURLConnection.getHeaderFields();
            java.util.Set localSet = localMap.keySet();
            Object[] arrayOfObject = localSet.toArray();
            for ( int i = 0; i < arrayOfObject.length; i++ ) {
                logger.info( "*** " + arrayOfObject[i] + ": " + localMap.get( arrayOfObject[i] ).toString() + " ***" );
            }
            BufferedReader localBufferedReader = new BufferedReader( new java.io.InputStreamReader( localHttpsURLConnection
                    .getInputStream() ) );
            String str;
            while ( ( str = localBufferedReader.readLine() ) != null ) {
                logger.info( str );
            }
        } catch ( Exception localException1 ) {
            localException1.printStackTrace();
            System.exit( 0 );
        }
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\http\GetDirect.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
