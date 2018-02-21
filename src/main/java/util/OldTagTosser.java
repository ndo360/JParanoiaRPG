
package util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;





 public class OldTagTosser
         {
       static BufferedReader reader;
       static String spareString = "";



    public static BufferedReader tossTags( BufferedReader paramBufferedReader )
     {

        reader = paramBufferedReader;


        String str1 = "";


        String str2 = "";

        try
             {

            while ( ( str2 = reader.readLine() ) != null )
                 {

                str1 = str1.concat( stripTags( str2 ) + "\n" );

            }

        } catch ( IOException localIOException ) {

            localIOException.printStackTrace();

        }





        return new BufferedReader( new StringReader( str1 ) );

    }



    private static String stripTags( String paramString ) throws IOException
     {

        String str = "";


        int i = 0;

        int j = 0;

        int k = 0;

        int m = 0;




        if (paramString.contains("<head>") || paramString.contains("<HEAD>"))
             {

            System.out.println( "In <head>..." );


            while (!reader.readLine().contains("</head>")) {
            }



            System.out.println( "Out of <head>." );


            return "";

        }


        if (paramString.contains("<script") || paramString.contains("<SCRIPT"))
             {

            System.out.println( "In <script..." );


            while (!reader.readLine().contains("</script>")) {
            }



            System.out.println( "Out of <script>." );


            return "";

        }


        if (paramString.contains("<!--"))
             {

            System.out.println( "In <!--..." );


            while (!paramString.contains("-->") &&
                             !reader.readLine().contains("-->")) {
            }



            System.out.println( "Out of <!--." );


            return "";

        }


        for ( int n = 0; n < paramString.length(); n++ )
             {

            char c = paramString.charAt( n );


            if ( i != 0 )
                 {

                if ( c == '>' ) {
                    i = 0;

                }

            }

            else if ( c == '<' ) {
                i = 1;

            } else {

                str.concat( Character.toString( c ) );

            }

        }

        return str;

    }

}

