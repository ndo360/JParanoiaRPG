/*    */
package util;
/*    */
/*    */
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/*    */
/*    */
/*    */
/*    */
/*    */ public class StreamListenerThread
        /*    */ extends Thread
        /*    */ {
    /*    */ BufferedReader in;

    /*    */
    /*    */
    public StreamListenerThread( InputStream paramInputStream )
    /*    */ {
        /* 15 */
        this.in = new BufferedReader( new InputStreamReader( paramInputStream ) );
        /*    */
    }

    /*    */
    /*    */
    public void run()
    /*    */ {
        /* 20 */
        String str = "";
        /*    */
        /*    */
        /*    */
        /*    */
        try
            /*    */ {
            /*    */
            for ( ; ; )
                /*    */ {
                /* 28 */
                str = this.in.readLine();
                /*    */
                /*    */
                /*    */
                /*    */
                /*    */
                /*    */
                /* 35 */
                if ( str == null ) {
                    /* 36 */
                    System.out.println( "-- end of line ---" );
                    /* 37 */
                    return;
                    /*    */
                }
                /*    */
                /*    */
                /* 41 */
                System.out.println( str );
                /*    */
            }
            /*    */
            /*    */
///*    */       return;
            /*    */
        }
        /*    */ catch ( Exception localException )
            /*    */ {
            /* 49 */
            localException.printStackTrace();
            System.exit( -1 );
            /*    */
        }
        /*    */
    }
    /*    */
}



