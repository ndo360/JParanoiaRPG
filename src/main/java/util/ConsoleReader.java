package util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConsoleReader {
    private BufferedReader reader;

    public ConsoleReader() {
        this( System.in );
    }

    public ConsoleReader( InputStream paramInputStream ) {
        this.reader = new BufferedReader( new InputStreamReader( paramInputStream ) );
    }

    public int readInt() {
        String str = readLine();
        return Integer.parseInt( str );
    }

    public String readLine() {
        String str = "";
        try {
            str = this.reader.readLine();
        } catch ( IOException localIOException ) {
            System.out.println( localIOException );
            System.exit( 1 );
        }
        return str;
    }

    public long readLong() {
        String str = readLine();
        return Long.parseLong( str );
    }

    public double readDouble() {
        String str = readLine();
        return Double.parseDouble( str );
    }
}
