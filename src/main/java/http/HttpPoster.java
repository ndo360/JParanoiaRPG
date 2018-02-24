package http;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import util.StringReplace;

public class HttpPoster {
    static HttpURLConnection connecto;

    public static BufferedReader postToURL( URL paramURL, String paramString ) throws IOException {
        String str = StringReplace.replaceStr( paramString, "%", "PERCENTGOESHEREOMG" );
        str = StringReplace.replaceStr( paramString, "&", "%26" );
        str = StringReplace.replaceStr( str, "+", "%2B" ).replace( ' ', '+' );
        str = StringReplace.replaceStr( str, "\\", "%5C" );
        str = StringReplace.replaceStr( str, "'", "%27" );
        str = StringReplace.replaceStr( str, "\"", "%22" );
        str = StringReplace.replaceStr( str, "PERCENTGOESHEREOMG", "%25" );
        connecto = (HttpURLConnection) paramURL.openConnection();
        connecto.setDoOutput( true );
        connecto.setRequestMethod( "POST" );
        PrintWriter localPrintWriter = new PrintWriter( connecto.getOutputStream(), true );
        localPrintWriter.print( str );
        localPrintWriter.close();
        return new BufferedReader( new InputStreamReader( connecto.getInputStream() ) );
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\http\HttpPoster.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
