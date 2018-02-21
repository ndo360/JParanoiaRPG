package http;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketSpew extends Thread {
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
                System.out.println( str + "*" );
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
