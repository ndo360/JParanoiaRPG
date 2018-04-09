package jparanoia.shared;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.invoke.MethodHandles;
import java.text.SimpleDateFormat;
import jparanoia.server.JPServer;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

public abstract class JPLogger {
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyyMMddHHmmss" );
    public static final SimpleDateFormat humanDateFormat = new SimpleDateFormat( "MMM dd yyyy - HH:mm" );
    public static final String LOGS = "./logs/";
    private final static Logger logger = getLogger( MethodHandles.lookup().lookupClass());

    public PrintWriter out;
    File logFile;
    String input;
    String logName;
    int flusharoo = 1;
    int flushLimit;

    protected void createLogFile() {
        File directory = new File( LOGS );
        if (! directory.exists()){
            directory.mkdir();
        }
        try {
            this.logFile = new File( LOGS + this.logName );
            this.out = new PrintWriter( new FileWriter( this.logFile ), true );
            logger.info( "Writing logs to {}", logFile.toString() );
        } catch ( IOException localIOException ) {
            localIOException.printStackTrace();
            JPServer.logBroken = true;
            JPServer.errorMessage( "Error creating log file", "JParanoia could not create the log file.\nCheck the console for details." );
        }
    }

    public void logEntry( String paramString ) {
        this.out.println( paramString );
    }

    public void flushomatic() {
        this.out.flush();
    }

    public void closeLog() {
        this.out.flush();
        this.out.close();
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\shared\JPLogger.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
