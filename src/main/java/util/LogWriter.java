package util;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LogWriter {
    PrintWriter out;

    public LogWriter( String paramString ) {
        try {
            this.out = new PrintWriter( new FileWriter( paramString, true ), true );
        } catch ( IOException localIOException ) {
            localIOException.printStackTrace();
        }
    }

    public void logEntry( String paramString ) {
        this.out.println( paramString );
    }

    public void close() {
        this.out.close();
    }
}
