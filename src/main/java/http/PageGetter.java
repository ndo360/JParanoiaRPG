package http;
import java.io.BufferedReader;
import java.net.URL;

public class PageGetter {
    static BufferedReader reader;
    static String line = "";

    public static void main( String[] paramArrayOfString ) {
        getPage( "http://127.0.0.1/hi.html" );
    }

    public static void getPage( String paramString ) {
        try {
            reader = new BufferedReader( HttpGetter.getFromURL( new URL( paramString ) ) );
            while ( line != null ) {
                line = reader.readLine();
                if ( line != null ) {
                    System.out.println( line );
                }
            }
        } catch ( Exception localException ) {
            localException.printStackTrace();
            System.exit( -1 );
        }
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\http\PageGetter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
