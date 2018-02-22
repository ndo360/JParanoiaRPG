package util;
public abstract class BinaryTools {
    public static String format( int paramInt ) {
        StringBuilder str = new StringBuilder( Integer.toBinaryString( paramInt ) );
        while ( str.length() < 32 ) {
            str.insert( 0, "0" );
        }
        str = new StringBuilder( str.substring( 0, 8 ) +
                " " +
                str.substring( 8, 16 ) +
                " " +
                str.substring( 16, 24 ) +
                " " +
                str.substring( 24 ) );
        return str.toString();
    }

    public static String format( byte paramByte ) {
        StringBuilder str = new StringBuilder( Integer.toBinaryString( paramByte & 0xFF ) );
        while ( str.length() < 8 ) {
            str.insert( 0, "0" );
        }
        return str.toString();
    }
}
