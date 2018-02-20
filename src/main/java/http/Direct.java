/*    */
package http;
/*    */
/*    */
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

/*    */
/*    */
/*    */
/*    */

/*    */
/*    */ public class Direct
        /*    */ {
    /*    */
    public static void main( String[] paramArrayOfString )
    /*    */ {
        /*    */
        try
            /*    */ {
            /* 15 */
            HttpsURLConnection localHttpsURLConnection = null;
            /*    */
            /* 17 */
            URL localURL = null;
            /*    */
            try {
                /* 19 */
                localURL = new URL( "https://utdirect.utexas.edu/" );
                /*    */
            }
            /*    */ catch ( Exception localException2 ) {
                /* 22 */
                localException2.printStackTrace();
                System.exit( 0 );
                /*    */
            }
            /* 24 */
            try {
                localHttpsURLConnection = (HttpsURLConnection) localURL.openConnection();
                /* 25 */
            } catch ( Exception localException3 ) {
                localException3.printStackTrace();
                System.exit( 0 );
                /*    */
            }
            /* 27 */
            localHttpsURLConnection.setRequestProperty( "User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)" );
            /*    */
            /* 29 */
            localHttpsURLConnection.addRequestProperty( "Cookie", "DOC=/acct/rec/wio/wio_home.WBX;domain=.utexas.edu;path=/" );
            /*    */
            /* 31 */
            localHttpsURLConnection.setInstanceFollowRedirects( true );
            /*    */
            /*    */
            /* 34 */
            localHttpsURLConnection.setDoOutput( true );
            /* 35 */
            localHttpsURLConnection.setRequestMethod( "POST" );
            /*    */
            /* 37 */
            PrintWriter localPrintWriter = new PrintWriter( localHttpsURLConnection.getOutputStream(), true );
            /*    */
            /* 39 */
            String str1 = "LOGON=" + java.net.URLEncoder.encode( "buried", "UTF-8" );
            /* 40 */
            String str2 = "PASSWORDS=" + java.net.URLEncoder.encode( "wdml1299", "UTF-8" );
            /*    */
            /* 42 */
            String str3 = str1 + "&" + str2 + "&NEW_PASSWORD=&CONFIRM_NEW_PASSWORD=&";
            /*    */
            /* 44 */
            localPrintWriter.println( str3 );
            /*    */
            /* 46 */
            localPrintWriter.flush();
            /* 47 */
            localPrintWriter.close();
            /*    */
            /*    */
            /*    */
            /* 51 */
            Map localMap = localHttpsURLConnection.getHeaderFields();
            /*    */
            /* 53 */
            java.util.Set localSet = localMap.keySet();
            /*    */
            /* 55 */
            Object[] arrayOfObject = localSet.toArray();
            /*    */
            /* 57 */
            for ( int i = 0; i < arrayOfObject.length; i++ ) {
                /* 58 */
                System.out.println( "*** " +
                        arrayOfObject[i] +
                        ": " +
                        localMap.get( arrayOfObject[i] ).toString() +
                        " ***" );
                /*    */
            }
            /*    */
            /*    */
            /*    */
            /*    */
            /* 64 */
            BufferedReader localBufferedReader = util.TagTosser.tossTags( new BufferedReader( new java.io.InputStreamReader( localHttpsURLConnection
                    .getInputStream() ) ) );
            /*    */
            /*    */
            /*    */
            String str4;
            /*    */
            /* 69 */
            while ( ( str4 = localBufferedReader.readLine() ) != null ) {
                /* 70 */
                System.out.println( str4 );
                /*    */
            }
            /*    */
        }
        /*    */ catch ( Exception localException1 ) {
            /* 74 */
            localException1.printStackTrace();
            System.exit( 0 );
            /*    */
        }
        /*    */
    }
    /*    */
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\http\Direct.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
