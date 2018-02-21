package jparanoia.shared.http;
import http.HttpPoster;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;

public class GameRegistrar {
    public static final String REGISTRY_URL_STRING = "http://www.byronbarry.com/jparanoia/game_registrar.html";
    public static String formattedDesc = "";

    public static void addGame( String paramString ) {
        formattedDesc = paramString.replace( ' ', '+' );
        try {
            BufferedReader localBufferedReader = HttpPoster.postToURL( new URL( "http://www.byronbarry.com/jparanoia/game_registrar.html" ),
                    "description=" +
                            formattedDesc );
            String str;
            while ( ( str = localBufferedReader.readLine() ) != null ) {
                System.out.println( str );
            }
            localBufferedReader.close();
        } catch ( IOException localIOException ) {
            localIOException.printStackTrace();
        }
    }

    public static void removeGame() {
        try {
            BufferedReader localBufferedReader = HttpPoster.postToURL( new URL( "http://www.byronbarry" +
                    ".com/jparanoia/game_registrar.html" ), "erase=1" );
            String str;
            while ( ( str = localBufferedReader.readLine() ) != null ) {
                System.out.println( str );
            }
            localBufferedReader.close();
        } catch ( IOException localIOException ) {
            localIOException.printStackTrace();
        }
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\shared\http\GameRegistrar.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
