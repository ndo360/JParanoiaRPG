package util;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

public class StreamListenerThread extends Thread {
    private final static Logger logger = getLogger( MethodHandles.lookup().lookupClass());

    BufferedReader in;

    public StreamListenerThread( InputStream paramInputStream ) {
        this.in = new BufferedReader( new InputStreamReader( paramInputStream ) );
    }

    public void run() {
        String str = "";
        try {
            for ( ; ; ) {
                str = this.in.readLine();
                if ( str == null ) {
                    logger.info( "-- end of line ---" );
                    return;
                }
                logger.info( str );
            }
//       return;
        } catch ( Exception localException ) {
            localException.printStackTrace();
            System.exit( -1 );
        }
    }
}



