/*    */
package util;
/*    */
/*    */
import java.io.PrintWriter;

/*    */
/*    */ public class LogWriter
        /*    */ {
    /*    */ PrintWriter out;

    /*    */
    /*    */
    public LogWriter( String paramString )
    /*    */ {
        /*    */
        try
            /*    */ {
            /* 13 */
            this.out = new PrintWriter( new java.io.FileWriter( paramString, true ), true );
            /*    */
            /*    */
        }
        /*    */ catch ( java.io.IOException localIOException )
            /*    */ {
            /* 18 */
            localIOException.printStackTrace();
            /*    */
        }
        /*    */
    }

    /*    */
    /*    */
    public void logEntry( String paramString )
    /*    */ {
        /* 24 */
        this.out.println( paramString );
        /*    */
    }

    /*    */
    /*    */
    public void close()
    /*    */ {
        /* 29 */
        this.out.close();
        /*    */
    }
    /*    */
}
