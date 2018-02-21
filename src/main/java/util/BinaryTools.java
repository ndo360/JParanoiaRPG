package util;
public abstract class BinaryTools {
    public static String format( int paramInt ) {
        String str = Integer.toBinaryString( paramInt );
        while ( str.length() < 32 ) {
            str = "0" + str;
        }
        str = str.substring( 0, 8 ) +
                " " +
                str.substring( 8, 16 ) +
                " " +
                str.substring( 16, 24 ) +
                " " +
                str.substring( 24 );
        return str;
    }

    public static String format( byte paramByte ) {
        String str = Integer.toBinaryString( paramByte & 0xFF );
        while ( str.length() < 8 ) {
            str = "0" + str;
        }
        return str;
    }
}
