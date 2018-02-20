/*    */
package util;
/*    */
/*    */ public class StringReplace
        /*    */ {
    /*    */
    public static String replaceStr( String paramString1, String paramString2, String paramString3 )
    /*    */ {
        /*  7 */
        int i = paramString2.length();
        /*  8 */
        int j = paramString3.length();
        /*    */
        /* 10 */
        int k = 0;
        /*    */
        /*    */
        /*    */
        /* 14 */
        while ( ( k = paramString1.indexOf( paramString2 ) ) != -1 )
            /*    */ {
            /* 16 */
            paramString1 = paramString1.substring( 0, k ) + paramString3 + paramString1.substring( k + i );
            /*    */
        }
        /*    */
        /* 19 */
        return paramString1;
        /*    */
    }
    /*    */
}
