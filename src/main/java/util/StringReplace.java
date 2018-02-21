package util;
public class StringReplace {
    public static String replaceStr( String paramString1, String paramString2, String paramString3 ) {
        int i = paramString2.length();
        int j = paramString3.length();
        int k = 0;
        while ( ( k = paramString1.indexOf( paramString2 ) ) != -1 ) {
            paramString1 = paramString1.substring( 0, k ) + paramString3 + paramString1.substring( k + i );
        }
        return paramString1;
    }
}
