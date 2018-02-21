package http.connector;
import http.SocketSpew;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HttpConnector {
    static final int GET = 0;
    static final int POST = 1;
    static final int REG_POST = 2;
    static final int IE_POST = 3;
    static SocketSpew sockSpew;
    static int method;

    public static void main( String[] paramArrayOfString ) {
        try {

            String str = "wnt.cc.utexas.edu";



            System.out.println( "Connecting to " + str + ":" + 80 + "..." );


            Socket localSocket = new Socket( str, 80 );


            sockSpew = new SocketSpew( localSocket );

            sockSpew.start();


            doStuff( localSocket );
        } catch ( Exception localException ) {

            System.out.println( "Aww, exception." );

            localException.printStackTrace();
        }
    }

    public static void doStuff( Socket paramSocket ) {
        try {

            PrintWriter localPrintWriter = new PrintWriter( paramSocket.getOutputStream(), true );

            BufferedReader localBufferedReader = new BufferedReader( new InputStreamReader( paramSocket.getInputStream() ) );



            String str1 = "description=foop&erase=0";



            System.out.println( "Connected. Sending request..." );


            method = 0;




            switch ( method ) {
                case 0:

                    localPrintWriter.println( "GET /~byronb/iptest.php HTTP/1.1" );

                    localPrintWriter.println( "Host: 127.0.0.1" );

                    localPrintWriter.println( "User-Agent: Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.1) Gecko/20020826" );

                    localPrintWriter.println( "Accept: text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,video/x-mng,image/png,image/jpeg,image/gif;q=0.2,text/css,*/*;q=0.1" );

                    localPrintWriter.println( "Accept-Language: en-us, en;q=0.50" );

                    localPrintWriter.println( "Accept-Encoding: gzip, deflate, compress;q=0.9" );

                    localPrintWriter.println( "Accept-Charset: ISO-8859-1, utf-8;q=0.66, *;q=0.66" );

                    localPrintWriter.println( "Keep-Alive: 300" );

                    localPrintWriter.println( "Connection: keep-alive" );

                    localPrintWriter.println();

                    break;
                case 1:

                    localPrintWriter.println( "POST /~byronb/jparanoia/forms/bugReport_submit.cfm HTTP/1.1" );

                    localPrintWriter.println( "Host: 127.0.0.1" );

                    localPrintWriter.println( "User-Agent: Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.1) Gecko/20020826" );

                    localPrintWriter.println( "Accept: text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,video/x-mng,image/png,image/jpeg,image/gif;q=0.2,text/css,*/*;q=0.1" );

                    localPrintWriter.println( "Accept-Language: en-us, en;q=0.50" );

                    localPrintWriter.println( "Accept-Encoding: gzip, deflate, compress;q=0.9" );

                    localPrintWriter.println( "Accept-Charset: ISO-8859-1, utf-8;q=0.66, *;q=0.66" );

                    localPrintWriter.println( "Keep-Alive: 300" );

                    localPrintWriter.println( "Connection: keep-alive" );

                    localPrintWriter.println( "Referer: http://127.0.0.1/formyform.html" );

                    localPrintWriter.println( "Content-Type: application/x-www-form-urlencoded" );

                    localPrintWriter.println( "Content-Length: " + str1.length() );

                    localPrintWriter.println();









                    localPrintWriter.println( str1 );

                    break;
                case 2:

                    localPrintWriter.println( "POST /~byronb/gameRegistry.php HTTP/1.1" );

                    localPrintWriter.println( "Accept: text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,video/x-mng,image/png,image/jpeg,image/gif;q=0.2,text/css,*/*;q=0.1" );

                    localPrintWriter.println( "Referer: http://127.0.0.1/formyform.html" );

                    localPrintWriter.println( "Content-Type: application/x-www-form-urlencoded" );

                    localPrintWriter.println( "Accept-Encoding: gzip, deflate, compress;q=0.9" );



                    localPrintWriter.println( "Host: 127.0.0.1" );




                    localPrintWriter.println( "Content-Length: " + str1.length() );

                    localPrintWriter.println( "Connection: Keep-Alive" );

                    localPrintWriter.println( "Cache-Control: no-cache" );




                    System.out.println( "-- Content-Length: " + str1.length() + " --" );


                    break;
                case 3:

                    localPrintWriter.println( "POST /~byronb/gameRegistry.php HTTP/1.1" );

                    localPrintWriter.println( "Accept: image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-gsarcade-launch, */*" );

                    localPrintWriter.println( "Referer: http://127.0.0.1/poop.html" );

                    localPrintWriter.println( "Content-Type: application/x-www-form-urlencoded" );

                    localPrintWriter.println( "Accept-Encoding: gzip, deflate" );


                    localPrintWriter.println( "User-Agent: Byron/0.0 (WindowsXP)" );

                    localPrintWriter.println( "Host: 127.0.0.1" );

                    localPrintWriter.println( "Content-Length: " + str1.length() );

                    localPrintWriter.println( "Connection: Keep-Alive" );

                    localPrintWriter.println( "Cache-Control: no-cache" );

                    localPrintWriter.println( "" );


                    localPrintWriter.println( str1 );

                    System.out.println( "-- Content-Length: " + str1.length() + " --" );
            }














            System.out.println( "Request sent. Waiting for reply..." );










            String str2 = "";
        } catch ( Exception localException ) {






            localException.printStackTrace();
        }
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\http\connector\HttpConnector.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
