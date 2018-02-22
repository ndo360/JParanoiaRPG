package http;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandles;
import java.net.Socket;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

public class SocketSpew extends Thread {
    private final static Logger logger = getLogger( MethodHandles.lookup().lookupClass());

    Socket socket;
    BufferedReader in;

    public SocketSpew( Socket paramSocket ) {
        this.socket = paramSocket;
    }

    public void run() {
        String str = "";
        int i = 0;
        try {
            this.in = new BufferedReader( new InputStreamReader( this.socket.getInputStream() ) );
            do {
                str = this.in.readLine();
                logger.info( str + "*" );
            } while ( str != null && !str.toUpperCase().contains( "</HTML>" ) );
        } catch ( Exception localException ) {
            localException.printStackTrace();
            System.exit( -1 );
        }
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\http\SocketSpew.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
