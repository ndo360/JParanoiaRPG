/*    */
package util;
/*    */
/*    */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/*    */
/*    */
/*    */
/*    */
/*    */ public class OldTagTosser
        /*    */ {
    /*    */   static BufferedReader reader;
    /* 11 */   static String spareString = "";

    /*    */
    /*    */
    public static BufferedReader tossTags( BufferedReader paramBufferedReader )
    /*    */ {
        /* 15 */
        reader = paramBufferedReader;
        /*    */
        /* 17 */
        String str1 = "";
        /*    */
        /* 19 */
        String str2 = "";
        /*    */
        try
            /*    */ {
            /* 22 */
            while ( ( str2 = reader.readLine() ) != null )
                /*    */ {
                /* 24 */
                str1 = str1.concat( stripTags( str2 ) + "\n" );
                /*    */
            }
            /*    */
        } catch ( IOException localIOException ) {
            /* 27 */
            localIOException.printStackTrace();
            /*    */
        }
        /*    */
        /*    */
        /*    */
        /*    */
        /* 33 */
        return new BufferedReader( new StringReader( str1 ) );
        /*    */
    }

    /*    */
    /*    */
    private static String stripTags( String paramString ) throws IOException
    /*    */ {
        /* 38 */
        String str = "";
        /*    */
        /* 40 */
        int i = 0;
        /* 41 */
        int j = 0;
        /* 42 */
        int k = 0;
        /* 43 */
        int m = 0;
        /*    */
        /*    */
        /*    */
        /* 47 */
        if (paramString.contains("<head>") || paramString.contains("<HEAD>"))
            /*    */ {
            /* 49 */
            System.out.println( "In <head>..." );
            /*    */
            /* 51 */
            while (!reader.readLine().contains("</head>")) {
            }
            /*    */
            /*    */
            /* 54 */
            System.out.println( "Out of <head>." );
            /*    */
            /* 56 */
            return "";
            /*    */
        }
        /*    */
        /* 59 */
        if (paramString.contains("<script") || paramString.contains("<SCRIPT"))
            /*    */ {
            /* 61 */
            System.out.println( "In <script..." );
            /*    */
            /* 63 */
            while (!reader.readLine().contains("</script>")) {
            }
            /*    */
            /*    */
            /* 66 */
            System.out.println( "Out of <script>." );
            /*    */
            /* 68 */
            return "";
            /*    */
        }
        /*    */
        /* 71 */
        if (paramString.contains("<!--"))
            /*    */ {
            /* 73 */
            System.out.println( "In <!--..." );
            /*    */
            /* 75 */
            while (!paramString.contains("-->") &&
                    /* 76 */         !reader.readLine().contains("-->")) {
            }
            /*    */
            /*    */
            /* 79 */
            System.out.println( "Out of <!--." );
            /*    */
            /* 81 */
            return "";
            /*    */
        }
        /*    */
        /* 84 */
        for ( int n = 0; n < paramString.length(); n++ )
            /*    */ {
            /* 86 */
            char c = paramString.charAt( n );
            /*    */
            /* 88 */
            if ( i != 0 )
                /*    */ {
                /* 90 */
                if ( c == '>' ) {
                    i = 0;
                    /*    */
                }
                /*    */
            }
            /* 93 */
            else if ( c == '<' ) {
                i = 1;
                /*    */
            } else {
                /* 95 */
                str.concat( Character.toString( c ) );
                /*    */
            }
            /*    */
        }
        /* 98 */
        return str;
        /*    */
    }
    /*    */
}

