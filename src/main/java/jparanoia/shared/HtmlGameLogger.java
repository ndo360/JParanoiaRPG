/*    */
package jparanoia.shared;
/*    */
/*    */
import java.util.Date;
import jparanoia.server.ServerPlayer;
import util.HexConverter;

/*    */
/*    */
/*    */

/*    */
/*    */ public class HtmlGameLogger
        /*    */ extends JPLogger
        /*    */ {
    /*    */
    public HtmlGameLogger()
    /*    */ {
        /* 13 */
        this.logName = "logs/uv_" + dateFormat.format( new Date() ) + ".html";
        /* 14 */
        createLogFile();
        /*    */
        /* 16 */
        logEntry( "<html>\n<head>\n<title>JParanoia Game Log - " +
                humanDateFormat.format( new Date() ) +
                "</title>\n" +
                "<style type=\"text/css\" media=\"screen\">\n" +
                "<!--\n" +
                "body { background-color: black; background: black; color: #FFFFFF; font-family: sans-serif }\n" +
                "\n" +
                ".pm { margin-left: 5%; margin-right: 5% }\n" +
                "\n" +
                ".gmText { font-weight: bold }\n" );
        /*    */
    }

    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    public void setPlayerColors( ServerPlayer[] paramArrayOfServerPlayer )
    /*    */ {
        /* 29 */
        logEntry( ".player0 { color: #" +
                HexConverter.hexValue( paramArrayOfServerPlayer[0].getChatColor() ) +
                "; font-weight: bold }" );
        /*    */
        /* 31 */
        for ( int i = 1; i < paramArrayOfServerPlayer.length; i++ )
            /*    */ {
            /* 33 */
            logEntry( ".player" +
                    i +
                    " { color: #" +
                    HexConverter.hexValue( paramArrayOfServerPlayer[i].getChatColor() ) +
                    " }" );
            /*    */
        }
        /*    */
        /* 36 */
        logEntry( "\n.gray { color: #999999 }\n.note { color: #FFFF00 }\n\nA { background: black }\nA:link { color: #FFFFFF }\nA:visited { color: #CCCCCC }\n-->\n</style>\n</head>\n<body>" );
        /*    */
    }
    /*    */
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\shared\HtmlGameLogger.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
