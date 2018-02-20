/*    */
package http;
/*    */
/*    */
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/*    */
/*    */
/*    */
/*    */

/*    */
/*    */ public class Get
        /*    */ {
    /*    */
    public static void main( String[] paramArrayOfString )
    /*    */ {
        /*    */
        try
            /*    */ {
            /* 15 */
            HttpURLConnection localHttpURLConnection = null;
            /*    */
            /* 17 */
            URL localURL = null;
            /*    */
            try
                /*    */ {
                /* 20 */
                localURL = new URL( "http://www.utexas.edu/" );
                /*    */
                /*    */
            }
            /*    */ catch ( Exception localException2 )
                /*    */ {
                /* 25 */
                localException2.printStackTrace();
                System.exit( 0 );
                /*    */
            }
            /* 27 */
            try {
                localHttpURLConnection = (HttpURLConnection) localURL.openConnection();
                /* 28 */
            } catch ( Exception localException3 ) {
                localException3.printStackTrace();
                System.exit( 0 );
                /*    */
            }
            /* 30 */
            localHttpURLConnection.setRequestProperty( "User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)" );
            /*    */
            /*    */
            /*    */
            /* 34 */
            localHttpURLConnection.setInstanceFollowRedirects( true );
            /*    */
            /*    */
            /*    */
            /*    */
            /* 39 */
            localHttpURLConnection.setRequestMethod( "GET" );
            /*    */
            /*    */
            /*    */
            /*    */
            /*    */
            /*    */
            /*    */
            /*    */
            /*    */
            /*    */
            /*    */
            /*    */
            /*    */
            /*    */
            /*    */
            /* 55 */
            Map localMap = localHttpURLConnection.getHeaderFields();
            /*    */
            /* 57 */
            java.util.Set localSet = localMap.keySet();
            /*    */
            /* 59 */
            Object[] arrayOfObject = localSet.toArray();
            /*    */
            /* 61 */
            for ( int i = 0; i < arrayOfObject.length; i++ ) {
                /* 62 */
                System.out.println( "<< " +
                        arrayOfObject[i] +
                        ": " +
                        localMap.get( arrayOfObject[i] ).toString() +
                        " >>" );
                /*    */
            }
            /*    */
            /*    */
            /*    */
            /*    */
            /* 68 */
            BufferedReader localBufferedReader = util.TagTosser.tossTags( new BufferedReader( new java.io.InputStreamReader( localHttpURLConnection
                    .getInputStream() ) ) );
            /*    */
            /*    */
            /*    */
            String str;
            /*    */
            /* 73 */
            while ( ( str = localBufferedReader.readLine() ) != null ) {
                /* 74 */
                System.out.println( str );
                /*    */
            }
            /*    */
        }
        /*    */ catch ( Exception localException1 ) {
            /* 78 */
            localException1.printStackTrace();
            System.exit( 0 );
            /*    */
        }
        /*    */
    }
    /*    */
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\http\Get.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
