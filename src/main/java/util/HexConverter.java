
package util;


import java.awt.Color;










 public abstract class HexConverter
         {

    public static String hexValue( int paramInt )
     {

        String str = "";


        int i = paramInt / 16;

        int j = paramInt % 16;





        if ( j < 10 ) {
            str = Integer.toString( j );
        } else {

            switch ( j ) {

                case 10:
                    str = "A";
                    break;

                case 11:
                    str = "B";
                    break;

                case 12:
                    str = "C";
                    break;

                case 13:
                    str = "D";
                    break;

                case 14:
                    str = "E";
                    break;

                case 15:
                    str = "F";

            }

        }

        if ( i > 9 ) {
            return hexValue( i ) + str;
        }

        if ( i > 0 ) {
            return Integer.toString( i ) + str;

        }

        return str;

    }



    public static String hexValue( Color paramColor )
     {

        String str1 = "";


        String str2 = hexValue( paramColor.getRed() );

        if ( str2.length() < 2 ) {
            str2 = "0" + str2;
        }

        str1 = str1 + str2;


        str2 = hexValue( paramColor.getGreen() );

        if ( str2.length() < 2 ) {
            str2 = "0" + str2;
        }

        str1 = str1 + str2;


        str2 = hexValue( paramColor.getBlue() );

        if ( str2.length() < 2 ) {
            str2 = "0" + str2;
        }

        str1 = str1 + str2;


        return str1;

    }

}

