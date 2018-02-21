
package util;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;





 public class StreamListenerThread
         extends Thread
         {
     BufferedReader in;



    public StreamListenerThread( InputStream paramInputStream )
     {

        this.in = new BufferedReader( new InputStreamReader( paramInputStream ) );

    }



    public void run()
     {

        String str = "";




        try
             {

            for ( ; ; )
                 {

                str = this.in.readLine();







                if ( str == null ) {

                    System.out.println( "-- end of line ---" );

                    return;

                }



                System.out.println( str );

            }


//       return;

        }
         catch ( Exception localException )
             {

            localException.printStackTrace();
            System.exit( -1 );

        }

    }

}



