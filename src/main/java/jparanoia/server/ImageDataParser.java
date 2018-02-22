package jparanoia.server;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;
import jparanoia.shared.JPImage;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

public class ImageDataParser {
    private final static Logger logger = getLogger( MethodHandles.lookup().lookupClass());

    BufferedReader reader;
    String input;
    String name;
    StringTokenizer st;
    ArrayList imageInfo = new ArrayList( 20 );

    public void parseImageURLs( String paramString ) {
        try {
            final Class cl = MethodHandles.lookup().lookupClass();

            this.reader = new BufferedReader( new InputStreamReader( cl.getResourceAsStream( "/" + paramString ) ) );
            this.input = this.reader.readLine();
            while ( this.input != null ) {
                if ( !this.input.startsWith( "#" ) ) {
                    this.st = new StringTokenizer( this.input, "|" );
                    while ( this.st.hasMoreTokens() ) {
                        String str = this.st.nextToken();
                        logger.info( "     Image: " + str );
                        this.imageInfo.add( new JPImage( str, new URL( this.st.nextToken() ) ) );
                    }
                }
                this.input = this.reader.readLine();
            }
        } catch ( Exception localException ) {
            localException.printStackTrace();
        }
    }

    public ArrayList getImageInfo() {
        return this.imageInfo;
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\ImageDataParser.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
