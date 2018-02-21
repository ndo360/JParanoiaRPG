package http;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class Get {
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
            java.util.Set localSet = localMap.keySet();
            Object[] arrayOfObject = localSet.toArray();
            for ( int i = 0; i < arrayOfObject.length; i++ ) {
                System.out.println( "<< " +
                        arrayOfObject[i] +
                        ": " +
                        localMap.get( arrayOfObject[i] ).toString() +
                        " >>" );
            }
            BufferedReader localBufferedReader = util.TagTosser.tossTags( new BufferedReader( new java.io.InputStreamReader( localHttpURLConnection
                    .getInputStream() ) ) );
            String str;
            while ( ( str = localBufferedReader.readLine() ) != null ) {
                System.out.println( str );
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
