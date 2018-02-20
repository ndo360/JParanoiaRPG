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
            /*  32 */
            String str = "wnt.cc.utexas.edu";


            /*  35 */
            System.out.println( "Connecting to " + str + ":" + 80 + "..." );

            /*  37 */
            Socket localSocket = new Socket( str, 80 );

            /*  39 */
            sockSpew = new SocketSpew( localSocket );
            /*  40 */
            sockSpew.start();

            /*  42 */
            doStuff( localSocket );
        } catch ( Exception localException ) {
            /*  47 */
            System.out.println( "Aww, exception." );
            /*  48 */
            localException.printStackTrace();
        }
    }

    public static void doStuff( Socket paramSocket ) {
        try {
            /*  57 */
            PrintWriter localPrintWriter = new PrintWriter( paramSocket.getOutputStream(), true );
            /*  58 */
            BufferedReader localBufferedReader = new BufferedReader( new InputStreamReader( paramSocket.getInputStream() ) );


            /*  61 */
            String str1 = "description=foop&erase=0";


            /*  64 */
            System.out.println( "Connected. Sending request..." );

            /*  66 */
            method = 0;



            /*  70 */
            switch ( method ) {
                case 0:
                    /*  76 */
                    localPrintWriter.println( "GET /~byronb/iptest.php HTTP/1.1" );
                    /*  77 */
                    localPrintWriter.println( "Host: 127.0.0.1" );
                    /*  78 */
                    localPrintWriter.println( "User-Agent: Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.1) Gecko/20020826" );
                    /*  79 */
                    localPrintWriter.println( "Accept: text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,video/x-mng,image/png,image/jpeg,image/gif;q=0.2,text/css,*/*;q=0.1" );
                    /*  80 */
                    localPrintWriter.println( "Accept-Language: en-us, en;q=0.50" );
                    /*  81 */
                    localPrintWriter.println( "Accept-Encoding: gzip, deflate, compress;q=0.9" );
                    /*  82 */
                    localPrintWriter.println( "Accept-Charset: ISO-8859-1, utf-8;q=0.66, *;q=0.66" );
                    /*  83 */
                    localPrintWriter.println( "Keep-Alive: 300" );
                    /*  84 */
                    localPrintWriter.println( "Connection: keep-alive" );
                    /*  85 */
                    localPrintWriter.println();
                    /*  86 */
                    break;
                case 1:
                    /*  94 */
                    localPrintWriter.println( "POST /~byronb/jparanoia/forms/bugReport_submit.cfm HTTP/1.1" );
                    /*  95 */
                    localPrintWriter.println( "Host: 127.0.0.1" );
                    /*  96 */
                    localPrintWriter.println( "User-Agent: Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.1) Gecko/20020826" );
                    /*  97 */
                    localPrintWriter.println( "Accept: text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,video/x-mng,image/png,image/jpeg,image/gif;q=0.2,text/css,*/*;q=0.1" );
                    /*  98 */
                    localPrintWriter.println( "Accept-Language: en-us, en;q=0.50" );
                    /*  99 */
                    localPrintWriter.println( "Accept-Encoding: gzip, deflate, compress;q=0.9" );
                    /* 100 */
                    localPrintWriter.println( "Accept-Charset: ISO-8859-1, utf-8;q=0.66, *;q=0.66" );
                    /* 101 */
                    localPrintWriter.println( "Keep-Alive: 300" );
                    /* 102 */
                    localPrintWriter.println( "Connection: keep-alive" );
                    /* 103 */
                    localPrintWriter.println( "Referer: http://127.0.0.1/formyform.html" );
                    /* 104 */
                    localPrintWriter.println( "Content-Type: application/x-www-form-urlencoded" );
                    /* 105 */
                    localPrintWriter.println( "Content-Length: " + str1.length() );
                    /* 106 */
                    localPrintWriter.println();








                    /* 115 */
                    localPrintWriter.println( str1 );
                    /* 116 */
                    break;
                case 2:
                    /* 121 */
                    localPrintWriter.println( "POST /~byronb/gameRegistry.php HTTP/1.1" );
                    /* 122 */
                    localPrintWriter.println( "Accept: text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,video/x-mng,image/png,image/jpeg,image/gif;q=0.2,text/css,*/*;q=0.1" );
                    /* 123 */
                    localPrintWriter.println( "Referer: http://127.0.0.1/formyform.html" );
                    /* 124 */
                    localPrintWriter.println( "Content-Type: application/x-www-form-urlencoded" );
                    /* 125 */
                    localPrintWriter.println( "Accept-Encoding: gzip, deflate, compress;q=0.9" );


                    /* 128 */
                    localPrintWriter.println( "Host: 127.0.0.1" );



                    /* 132 */
                    localPrintWriter.println( "Content-Length: " + str1.length() );
                    /* 133 */
                    localPrintWriter.println( "Connection: Keep-Alive" );
                    /* 134 */
                    localPrintWriter.println( "Cache-Control: no-cache" );



                    /* 138 */
                    System.out.println( "-- Content-Length: " + str1.length() + " --" );

                    /* 140 */
                    break;
                case 3:
                    /* 145 */
                    localPrintWriter.println( "POST /~byronb/gameRegistry.php HTTP/1.1" );
                    /* 146 */
                    localPrintWriter.println( "Accept: image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-gsarcade-launch, */*" );
                    /* 147 */
                    localPrintWriter.println( "Referer: http://127.0.0.1/poop.html" );
                    /* 148 */
                    localPrintWriter.println( "Content-Type: application/x-www-form-urlencoded" );
                    /* 149 */
                    localPrintWriter.println( "Accept-Encoding: gzip, deflate" );

                    /* 151 */
                    localPrintWriter.println( "User-Agent: Byron/0.0 (WindowsXP)" );
                    /* 152 */
                    localPrintWriter.println( "Host: 127.0.0.1" );
                    /* 153 */
                    localPrintWriter.println( "Content-Length: " + str1.length() );
                    /* 154 */
                    localPrintWriter.println( "Connection: Keep-Alive" );
                    /* 155 */
                    localPrintWriter.println( "Cache-Control: no-cache" );
                    /* 156 */
                    localPrintWriter.println( "" );

                    /* 158 */
                    localPrintWriter.println( str1 );
                    /* 159 */
                    System.out.println( "-- Content-Length: " + str1.length() + " --" );
            }













            /* 174 */
            System.out.println( "Request sent. Waiting for reply..." );









            /* 184 */
            String str2 = "";
        } catch ( Exception localException ) {





            /* 198 */
            localException.printStackTrace();
        }
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\http\connector\HttpConnector.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
