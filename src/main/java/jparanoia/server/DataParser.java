package jparanoia.server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

public class DataParser {
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
            System.out.println( "\nProcessing playerList.txt:" );
//            final ClassLoader classLoader = MethodHandles.lookup().lookupClass().getClassLoader();
//            final File file = new File( Objects.requireNonNull( classLoader.getResource( paramString ) ).getFile() );
//            this.reader = new BufferedReader( new InputStreamReader(new FileInputStream( file )) );
            this.reader = new BufferedReader( new InputStreamReader( getClass().getResourceAsStream( "/playerData/" +
                    paramString ) ) );
            this.input = this.reader.readLine();
            int i = 0;
            while ( this.input != null ) {
                if ( !this.input.startsWith( "#" ) ) {
                    this.st = new StringTokenizer( this.input, "|" );
                    while ( this.st.hasMoreTokens() ) {
                        this.playerNumber = i;
                        this.playerName = this.st.nextToken();
                        System.out.println( "     Player " + i + ": " + this.playerName );
                        this.playerIsPlayer = this.st.nextToken().equals( "p" );
                        if ( this.playerIsPlayer ) {
                            this.playerPassword = this.st.nextToken();
                            this.playerDataFileName = this.st.nextToken();
                        } else {
                            this.playerPassword = "npc";
                            this.playerDataFileName = "npc";
                        }
                        this.somePlayer = new ServerPlayer( this.playerNumber, this.playerName, this.playerIsPlayer, this.playerPassword,
                                "playerData/" +
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
            System.out.println( "* Error reading playerList.txt" );
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
