package http;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandles;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;
import util.TagTosser;

public class Get {
    private final static Logger logger = getLogger( MethodHandles.lookup().lookupClass());

    public static void main( String[] paramArrayOfString ) {
        try {
            HttpURLConnection localHttpURLConnection = null;
            URL localURL = null;
            try {
                localURL = new URL( "http://www.utexas.edu/" );
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
            localHttpURLConnection.setRequestProperty( "User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)" );
            localHttpURLConnection.setInstanceFollowRedirects( true );
            localHttpURLConnection.setRequestMethod( "GET" );
            Map localMap = localHttpURLConnection.getHeaderFields();
            Set localSet = localMap.keySet();
            Object[] arrayOfObject = localSet.toArray();
            for ( final Object anArrayOfObject : arrayOfObject ) {
                logger.info( "<< " + anArrayOfObject + ": " + localMap.get( anArrayOfObject ).toString() + " >>" );
            }
            BufferedReader localBufferedReader = TagTosser.tossTags( new BufferedReader( new InputStreamReader( localHttpURLConnection
                    .getInputStream() ) ) );
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


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\http\Get.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
