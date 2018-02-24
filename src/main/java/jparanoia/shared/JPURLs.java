package jparanoia.shared;
import java.io.BufferedReader;
import java.net.MalformedURLException;
import java.net.URL;

public class JPURLs {
    //       public static final String URLS_URL_STRING = "http://www.paranoia-live.net/jpreg/jparanoia/urls.html";
    public static URL URLS_URL = null;
    public static URL gameRegistrarURL;

    static {
        try {
            URLS_URL = new URL( "http://127.0.0.1/jpreg/jparanoia/urls.html" );
        } catch ( MalformedURLException localMalformedURLException ) {
            JParanoia.errorMessage( "Malformed master URL", "There is a problem with the master URL.\nPlease submit a bug report on\nhttp://byronbarry.com/jparanoia" );
            localMalformedURLException.printStackTrace();
        }
    }

    public static void getURLs() throws java.io.IOException {
        BufferedReader localBufferedReader = http.HttpGetter.getFromURL( URLS_URL );
        String str1 = "";
        int i = -1;
        String str2 = null;
        try {
            while ( ( str1 = localBufferedReader.readLine() ) != null ) {
                if ( str1.contains( "GAME-REGISTRAR:" ) ) {
                    str2 = "GAME-REGISTRAR";
                    gameRegistrarURL = new URL( str1.substring( str1.indexOf( ":" ) + 1 ) );
                }
            }
        } catch ( MalformedURLException localMalformedURLException ) {
            JParanoia.errorMessage( "Malformed URL", "There is a problem with the " +
                    str2 +
                    " URL.\n" +
                    "Please submit a bug report on\n" +
                    "http://www.byronbarry.com/jparanoia" );
        }
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\shared\JPURLs.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */
