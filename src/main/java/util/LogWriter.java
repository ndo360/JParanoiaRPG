
package util;


import java.io.PrintWriter;


 public class LogWriter
         {
     PrintWriter out;



    public LogWriter( String paramString )
     {

        try
             {

            this.out = new PrintWriter( new java.io.FileWriter( paramString, true ), true );


        }
         catch ( java.io.IOException localIOException )
             {

            localIOException.printStackTrace();

        }

    }



    public void logEntry( String paramString )
     {

        this.out.println( paramString );

    }



    public void close()
     {

        this.out.close();

    }

}
