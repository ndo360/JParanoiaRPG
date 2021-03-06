package http;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.invoke.MethodHandles;
import java.util.Objects;

public class PageSpewer {
    static BufferedReader reader;
    static String line = "";

    public static void spewPage( String paramString, PrintWriter paramPrintWriter ) {
        try {
            final ClassLoader classLoader = MethodHandles.lookup().lookupClass().getClassLoader();
            final File file = new File( Objects.requireNonNull( classLoader.getResource( paramString ) ).getFile() );
            reader = new BufferedReader( new InputStreamReader( new FileInputStream( file ) ) );
            while ( line != null ) {
                line = reader.readLine();
                if ( line != null ) {
                    paramPrintWriter.println( line );
                }
            }
        } catch ( Exception localException ) {
            localException.printStackTrace();
            System.exit( -1 );
        }
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\http\PageSpewer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
