/*    */
package jparanoia.shared;
/*    */
/*    */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import jparanoia.server.JPServer;

/*    */
/*    */
/*    */
/*    */
/*    */

/*    */
/*    */
/*    */
/*    */
/*    */ public abstract class JPLogger
        /*    */ {
    /* 22 */   public static final SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyyMMddHHmmss" );
    /* 23 */   public static final SimpleDateFormat humanDateFormat = new SimpleDateFormat( "MMM dd yyyy - HH:mm" );
    /*    */   public PrintWriter out;
    /*    */ File logFile;
    /*    */ String input;
    /*    */ String logName;
    /* 19 */ int flusharoo = 1;
    /*    */
    /*    */ int flushLimit;

    /*    */
    /*    */
    /*    */
    /*    */
    protected void createLogFile()
    /*    */ {
        /*    */
        try
            /*    */ {
            /* 31 */
            this.logFile = new File( this.logName );
            /* 32 */
            this.out = new PrintWriter( new FileWriter( this.logFile ), true );
            /*    */
            /*    */
        }
        /*    */ catch ( IOException localIOException )
            /*    */ {
            /* 37 */
            localIOException.printStackTrace();
            /* 38 */
            JPServer.logBroken = true;
            /* 39 */
            JPServer.errorMessage( "Error creating log file", "JParanoia could not create the log file.\nThis can be caused by deleting or renaming the \"logs\" directory.\nCheck the console for details." );
            /*    */
        }
        /*    */
    }

    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    public void logEntry( String paramString )
    /*    */ {
        /* 48 */
        this.out.println( paramString );
        /*    */
    }

    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    public void flushomatic()
    /*    */ {
        /* 61 */
        this.out.flush();
        /*    */
    }

    /*    */
    /*    */
    public void closeLog()
    /*    */ {
        /* 66 */
        this.out.flush();
        /* 67 */
        this.out.close();
        /*    */
    }
    /*    */
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\shared\JPLogger.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
