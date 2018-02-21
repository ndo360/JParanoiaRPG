package http.connector;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import util.StreamListenerThread;

public class HttpConnect {
    static final int GET = 0;
    static final int POST = 1;
    static final int REG_POST = 2;
    static final int IE_POST = 3;
    static final int REAL_POST = 4;
    static int method;
    static HttpURLConnection connecto;
    static StreamListenerThread slt;

    public static void main( String[] paramArrayOfString ) {
        try {

            connecto = (HttpURLConnection) new URL( "http://127.0.0.1/formyform.html" ).openConnection();

            connecto.setDoOutput( true );
        } catch ( Exception localException ) {


            System.out.println( "Aww, exception." );

            localException.printStackTrace();
        }



        doStuff();
    }

    public static void doStuff() {
        try {

            String str = "description=foop";



            System.out.println( "Connected. Sending request..." );





            method = 4;


            switch ( method ) {
                case 4:

                    connecto.setRequestMethod( "POST" );


                    PrintWriter localObject = new PrintWriter( connecto.getOutputStream(), true );






                    localObject.println( str );

                    localObject.close();


                    slt = new StreamListenerThread( connecto.getInputStream() );

                    slt.start();
            }







































            System.out.println( "Request sent. Waiting for reply..." );










            Object localObject = "";
        } catch ( Exception localException ) {






            localException.printStackTrace();
        }
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\http\connector\HttpConnect.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
