/*    */
package jparanoia.shared;
/*    */
/*    */
import java.util.Date;

/*    */

/*    */
/*    */ public class ErrorLogger extends JPLogger
        /*    */ {
    /*    */
    public ErrorLogger( String paramString1, String paramString2 )
    /*    */ {
        /* 10 */
        this.logName = paramString1 + dateFormat.format( new Date() ).substring( 4 ) + ".txt";
        /* 11 */
        createLogFile();
        /* 12 */
        logEntry( paramString2 + "\n\n" );
        /*    */
    }
    /*    */
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\shared\ErrorLogger.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
