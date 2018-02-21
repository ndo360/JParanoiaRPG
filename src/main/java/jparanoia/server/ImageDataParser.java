package jparanoia.server;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;
import jparanoia.shared.JPImage;

public class ImageDataParser {
    BufferedReader reader;
    String input;
    String name;
    StringTokenizer st;
    ArrayList imageInfo = new ArrayList( 20 );

    public void parseImageURLs( String paramString ) {
        try {
            final ClassLoader classLoader = MethodHandles.lookup().lookupClass().getClassLoader();
            final File file = new File( Objects.requireNonNull( classLoader.getResource( "conf/" + paramString ) )
                    .getFile() );
            this.reader = new BufferedReader( new InputStreamReader( new FileInputStream( file ) )/*new FileReader( paramString )*/ );
            this.input = this.reader.readLine();
            while ( this.input != null ) {
                if ( !this.input.startsWith( "#" ) ) {
                    this.st = new StringTokenizer( this.input, "|" );
                    while ( this.st.hasMoreTokens() ) {
                        String str = this.st.nextToken();
                        System.out.println( "     Image: " + str );
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
