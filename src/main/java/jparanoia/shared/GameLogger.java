package jparanoia.shared;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;

public class GameLogger extends JPLogger {
    private boolean html = false;

    public GameLogger() {
        this.logName = "uv_" + dateFormat.format( new Date() ) + ".txt";
        createLogFile();
    }

    public GameLogger( JPPlayer[] paramArrayOfJPPlayer ) {
        this.html = true;
        this.logName = "uv_" + dateFormat.format( new Date() ) + ".html";
        createLogFile();
        logEntry( "<html>\n<head>\n<title>JParanoia Game Log - " +
                humanDateFormat.format( new Date() ) +
                "</title>\n" +
                "<style type=\"text/css\" media=\"screen\">\n" +
                "<!--\n" +
                "body { background-color: black; background: black; color: #FFFFFF; font-family: sans-serif }\n" +
                "\n" +
                ".pm { margin-left: 5%; margin-right: 5% }\n" +
                "\n" +
                ".gmText { font-weight: bold }\n" +
                "\n" +
                ".player0 { color: #" +
                util.HexConverter.hexValue( paramArrayOfJPPlayer[0].getChatColor() ) +
                "; font-weight: bold }" );
        for ( int i = 1; i < paramArrayOfJPPlayer.length; i++ ) {
            logEntry( ".player" +
                    i +
                    " { color: #" +
                    util.HexConverter.hexValue( paramArrayOfJPPlayer[i].getChatColor() ) +
                    " }" );
        }
        logEntry( "\n.observer { color: #666666 }\n\n.computer { font-size: 180% }\n\n.gray { color: #999999 }\n.note { color: #FFFF00 }\n\nA { background: black }\nA:link { color: #FFFFFF }\nA:visited { color: #CCCCCC }\n-->\n</style>\n</head>\n<body>" );
    }

    public void closeLog() {
        if ( this.html ) {
            logEntry( "</body>\n</html>\n" );
        }
        super.closeLog();
    }

    public void sanitize() {
        try {
            BufferedReader localBufferedReader = new BufferedReader( new InputStreamReader( new FileInputStream( logFile ) ) );
            String str1 = this.logFile.getName();
            if ( str1.startsWith( "uv_" ) ) {
                str1 = str1.substring( 3 );
            }
            String str2 = this.logFile.getParent() + java.io.File.separator + "red_" + str1;
            PrintWriter localPrintWriter = new PrintWriter( new java.io.FileWriter( str2 ) );
            String str3;
            while ( ( str3 = localBufferedReader.readLine() ) != null ) {
                if ( !str3.startsWith( "<span class=\"pm\">" ) ) {
                    localPrintWriter.println( str3 );
                }
            }
            localPrintWriter.flush();
            localPrintWriter.close();
        } catch ( IOException localIOException ) {
            localIOException.printStackTrace();
        }
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\shared\GameLogger.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
