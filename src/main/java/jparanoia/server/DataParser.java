package jparanoia.server;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.StringTokenizer;
import java.util.Vector;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

public class DataParser {
    private final static Logger logger = getLogger( MethodHandles.lookup().lookupClass());
    public static final String DATA_FOLDER = "playerData/";
    BufferedReader reader;
    String input;
    String currentText;
    Vector newPlayers;
    StringTokenizer st;
    ServerPlayer somePlayer;
    ServerPlayer[] somePlayerArray;
    int playerNumber;
    String playerName;
    boolean playerIsPlayer;
    String playerPassword;
    String playerDataFileName;

    public ServerPlayer[] parsePlayerList( String paramString ) {
        this.newPlayers = new Vector();
        try {
            logger.info( "\nProcessing playerList.txt:" );
            this.reader = new BufferedReader(new FileReader(DATA_FOLDER + paramString));
            this.input = this.reader.readLine();
            int i = 0;
            while ( this.input != null ) {
                if ( !this.input.startsWith( "#" ) ) {
                    this.st = new StringTokenizer( this.input, "|" );
                    while ( this.st.hasMoreTokens() ) {
                        this.playerNumber = i;
                        this.playerName = this.st.nextToken();
                        logger.info( "     Player " + i + ": " + this.playerName );
                        this.playerIsPlayer = this.st.nextToken().equals( "p" );
                        if ( this.playerIsPlayer ) {
                            this.playerPassword = this.st.nextToken();
                            this.playerDataFileName = this.st.nextToken();
                        } else {
                            this.playerPassword = "npc";
                            this.playerDataFileName = "npc";
                        }
                        this.somePlayer = new ServerPlayer( this.playerNumber, this.playerName, this.playerIsPlayer, this.playerPassword,
                                DATA_FOLDER +
                                        this.playerDataFileName );
                        if ( this.somePlayer.isAnActualPlayer() ) {
                            this.somePlayer.readCharacterSheetFile();
                        }
                        this.newPlayers.addElement( this.somePlayer );
                        i++;
                    }
                }
                this.input = this.reader.readLine();
            }
        } catch ( IOException localIOException ) {
            logger.info( "* Error reading playerList.txt" );
            localIOException.printStackTrace();
        }
        this.somePlayerArray = new ServerPlayer[this.newPlayers.size()];
        this.newPlayers.copyInto( this.somePlayerArray );
        return this.somePlayerArray;
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\DataParser.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
