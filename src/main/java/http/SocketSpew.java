/*    */
package http;
/*    */
/*    */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/*    */
/*    */
/*    */

/*    */
/*    */ public class SocketSpew extends Thread
        /*    */ {
    /*    */ Socket socket;
    /*    */ BufferedReader in;

    /*    */
    /*    */
    public SocketSpew( Socket paramSocket )
    /*    */ {
        /* 15 */
        this.socket = paramSocket;
        /*    */
    }

    /*    */
    /*    */
    public void run()
    /*    */ {
        /* 20 */
        String str = "";
        /* 21 */
        int i = 0;
        /*    */
        /*    */
        try
            /*    */ {
            /* 25 */
            this.in = new BufferedReader( new InputStreamReader( this.socket.getInputStream() ) );
            /*    */
            /*    */
            do
                /*    */ {
                /* 29 */
                str = this.in.readLine();
                /*    */
                /*    */
                /*    */
                /*    */
                /*    */
                /* 35 */
                System.out.println( str + "*" );
                /*    */
            }
            /* 37 */       while ( str != null && str.toUpperCase().indexOf( "</HTML>" ) == -1 );
            return;
            /*    */
        }
        /*    */ catch ( Exception localException )
            /*    */ {
            /* 41 */
            localException.printStackTrace();
            System.exit( -1 );
            /*    */
        }
        /*    */
    }
    /*    */
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\http\SocketSpew.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
