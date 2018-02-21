package http.listener;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpListener {
    static BufferedReader in;
    static PrintWriter out;
    static String incomingLine;
    static Socket socket;
    static boolean get;

    public static void main( String[] paramArrayOfString ) {

        ServerSocket localServerSocket = null;
        try {

            localServerSocket = new ServerSocket( 80 );


            System.out.println( "Listening on TCP port 80..." );
            for ( ; ; ) {

                socket = localServerSocket.accept();


                doStuff();
            }
        } catch ( Exception localException ) {

            System.out.println( "Aww, exception." );

            localException.printStackTrace();
        }
    }

    public static void doStuff() {

        InetAddress localInetAddress = socket.getInetAddress();


        System.out.println( "Connection from: " +
                localInetAddress.getHostAddress() +
                " aka: " +
                localInetAddress.getHostName() );
        try {

            out = new PrintWriter( socket.getOutputStream(), true );

            in = new BufferedReader( new java.io.InputStreamReader( socket.getInputStream() ) );


            incomingLine = "hi there";



            int i = 1;


            get = false;


            while ( i != 0 ) {



                incomingLine = in.readLine();



                System.out.println( incomingLine + "*" );


                if ( incomingLine.startsWith( "GET" ) ) {
                    get = true;
                }


                if ( incomingLine.equals( "" ) &&
                                   get ) {

                    System.out.println( "Sending local page..." );








































                    http.PageSpewer.spewPage( "C:/web publishing/gameTestForm.html", out );



                    System.out.println( "Form sent." );


                    i = 0;


                    socket.close();
                }
            }
        } catch ( NullPointerException localNullPointerException ) {



            System.out.println( "Null input." );


            while ( incomingLine == null ) {
                try {

                    incomingLine = in.readLine();
                } catch ( Exception localException1 ) {
                }

                if ( incomingLine != null ) {

                    System.out.println( incomingLine + "*" );

                    doStuff();
                }
            }
        } catch ( Exception localException2 ) {

            System.out.println( "Exception" );

            localException2.printStackTrace();
        }
    }

    public static void sendLinefeed() {

        System.out.println( "Sending line feed..." );


        out.println();
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\http\listener\HttpListener.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
