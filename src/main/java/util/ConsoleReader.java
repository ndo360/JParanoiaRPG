/*    */
package util;
/*    */
/*    */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */ public class ConsoleReader
        /*    */ {
    /*    */   private BufferedReader reader;

    /*    */
    /*    */
    public ConsoleReader()
    /*    */ {
        /* 19 */
        this( System.in );
        /*    */
    }

    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    public ConsoleReader( InputStream paramInputStream )
    /*    */ {
        /* 27 */
        this.reader = new BufferedReader( new InputStreamReader( paramInputStream ) );
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
    public int readInt()
    /*    */ {
        /* 38 */
        String str = readLine();
        /* 39 */
        int i = Integer.parseInt( str );
        /* 40 */
        return i;
        /*    */
    }

    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    public String readLine()
    /*    */ {
        /* 68 */
        String str = "";
        /*    */
        try
            /*    */ {
            /* 71 */
            str = this.reader.readLine();
            /*    */
        }
        /*    */ catch ( IOException localIOException ) {
            /* 74 */
            System.out.println( localIOException );
            /* 75 */
            System.exit( 1 );
            /*    */
        }
        /*    */
        /* 78 */
        return str;
        /*    */
    }

    /*    */
    /*    */
    public long readLong() {
        /* 44 */
        String str = readLine();
        /* 45 */
        long l = Long.parseLong( str );
        /* 46 */
        return l;
        /*    */
    }

    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    public double readDouble()
    /*    */ {
        /* 56 */
        String str = readLine();
        /* 57 */
        double d = Double.parseDouble( str );
        /* 58 */
        return d;
        /*    */
    }
    /*    */
}
