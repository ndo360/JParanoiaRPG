/*    */
package http;
/*    */
/*    */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import util.StringReplace;

/*    */
/*    */
/*    */
/*    */
/*    */
/*    */

/*    */
/*    */ public class HttpPoster
        /*    */ {
    /*    */   static HttpURLConnection connecto;

    /*    */
    /*    */
    public static BufferedReader postToURL( URL paramURL, String paramString ) throws IOException
    /*    */ {
        /* 17 */
        String str = StringReplace.replaceStr( paramString, "%", "PERCENTGOESHEREOMG" );
        /* 18 */
        str = StringReplace.replaceStr( paramString, "&", "%26" );

        str = StringReplace.replaceStr( str, "+", "%2B" ).replace( ' ', '+' );

        str = StringReplace.replaceStr( str, "\\", "%5C" );
        /* 45 */
        str = StringReplace.replaceStr( str, "'", "%27" );
        /* 46 */
        str = StringReplace.replaceStr( str, "\"", "%22" );
        /* 47 */
        str = StringReplace.replaceStr( str, "PERCENTGOESHEREOMG", "%25" );
        /*    */
        /* 49 */
        connecto = (HttpURLConnection) paramURL.openConnection();
        /*    */
        /*    */
        /*    */
        /* 53 */
        connecto.setDoOutput( true );
        /* 54 */
        connecto.setRequestMethod( "POST" );
        /*    */
        /*    */
        /*    */
        /*    */
        /* 59 */
        PrintWriter localPrintWriter = new PrintWriter( connecto.getOutputStream(), true );
        /*    */
        /*    */
        /*    */
        /*    */
        /* 64 */
        localPrintWriter.print( str );
        /*    */
        /* 66 */
        localPrintWriter.close();
        /* 67 */
        return new BufferedReader( new InputStreamReader( connecto.getInputStream() ) );
        /*    */
    }
    /*    */
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\http\HttpPoster.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
