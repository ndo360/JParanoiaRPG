package jparanoia.shared;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import jparanoia.server.JPServer;

public abstract class JPLogger {
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyyMMddHHmmss" );
    public static final SimpleDateFormat humanDateFormat = new SimpleDateFormat( "MMM dd yyyy - HH:mm" );
    public PrintWriter out;
    File logFile;
    String input;
    String logName;
    int flusharoo = 1;
    int flushLimit;

    protected void createLogFile() {
        try {
            this.logFile = new File( this.logName );
            this.out = new PrintWriter( new FileWriter( this.logFile ), true );
        } catch ( IOException localIOException ) {
            localIOException.printStackTrace();
            JPServer.logBroken = true;
            JPServer.errorMessage( "Error creating log file", "JParanoia could not create the log file.\nThis can be caused by deleting or renaming the \"logs\" directory.\nCheck the console for details." );
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
