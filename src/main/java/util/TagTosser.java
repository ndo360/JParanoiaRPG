package util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public class TagTosser {
    static BufferedReader reader;
    static String spareString = "";
    static String source = "";

    public static BufferedReader tossTags( BufferedReader paramBufferedReader ) {
        reader = paramBufferedReader;
        String str1 = "";
        String str2 = "";
        int i = 0;
        try {
            while ( ( str2 = reader.readLine() ) != null ) {
                source = source.concat( str2 + "\n" );
                i++;
            }
        } catch ( IOException localIOException ) {
            localIOException.printStackTrace();
        }
        str1 = stripTags( source );
        return new BufferedReader( new StringReader( str1 ) );
    }

    private static String stripTags( String paramString ) {
        String str1 = "<head>(?:[\\s\\S]*?)[[(</head>)][(</HEAD>)]]";
        String str2 = "<script(?:[\\s\\S]*?)</script>";
        String str3 = "<!--(?:[\\s\\S]*?)-->";
        String str4 = "<(?:[[\\s\\S]&&[^>]]*)>";
        String str5 = "(?:\\s*\\n)+";
        String str6 = "\\t";
        String str7 = "(?:[[\\s]&&[^\\n]][[\\s]&&[^\\n]]+)";
        String str8 = "&nbsp;";
        String str9 = "&amp;";
        String str10 = paramString;
        str10 = str10.replaceAll( str1, "[head tag removed]" );
        str10 = str10.replaceAll( str2, "[script tag removed]" );
        str10 = str10.replaceAll( str3, "" );
        str10 = str10.replaceAll( str4, "" );
        str10 = str10.replaceAll( str8, " " );
        str10 = str10.replaceAll( str9, "&" );
        str10 = str10.replaceAll( str5, "\n" );
        str10 = str10.replaceAll( str6, "" );
        str10 = str10.replaceAll( str7, "" );
        return str10;
    }
}

