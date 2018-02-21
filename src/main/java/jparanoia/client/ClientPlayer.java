package jparanoia.client;
import java.awt.Color;
import javax.swing.text.SimpleAttributeSet;
import jparanoia.shared.JPPlayer;

public class ClientPlayer extends JPPlayer {
    final int PLAYER_NUMBER;
    final boolean IS_PLAYER;
    String name;
    int cloneNumber;
     boolean loggedIn = true;
     boolean isDead = false;
    javax.swing.text.DefaultStyledDocument characterSheet;
    PrivateMessageFrame pmFrame;
     Color chatColor = Color.gray;
     SimpleAttributeSet sas = new SimpleAttributeSet();

    public ClientPlayer( int paramInt, String paramString, boolean paramBoolean1, boolean paramBoolean2 ) {

        this.PLAYER_NUMBER = paramInt;

        this.name = paramString;

        this.IS_PLAYER = paramBoolean1;

        this.loggedIn = paramBoolean2;
    }

    public String getName() {

        if ( this.PLAYER_NUMBER == 0 ) {
            return this.name;
        }

        return this.name.substring( 0, this.name.lastIndexOf( "-" ) );
    }

    public void setName( String paramString ) {

        this.name = paramString;
    }

    public boolean isLoggedIn() {

        return this.loggedIn;
    }

    public void setLoggedIn( boolean paramBoolean ) {

        this.loggedIn = paramBoolean;
    }

    public boolean isAPlayer() {

        return this.IS_PLAYER;
    }

    public Color getChatColor() {

        return this.chatColor;
    }

    public void setChatColor( Color paramColor ) {

        this.chatColor = paramColor;
    }

    public String getID() {
        String str;

        if ( this.PLAYER_NUMBER < 10 ) {
            str = "0" + this.PLAYER_NUMBER;
        } else
             {
            str = "" + this.PLAYER_NUMBER;
        }

        return str;
    }

    public int getPlayerNumber() {

        return this.PLAYER_NUMBER;
    }

    public void setPMFrame( PrivateMessageFrame paramPrivateMessageFrame ) {

        this.pmFrame = paramPrivateMessageFrame;
    }

    public void die() {

        if ( !this.isDead ) {

            this.cloneNumber = Integer.parseInt( this.name.substring( this.name.lastIndexOf( "-" ) + 1 ) );

            if ( this.cloneNumber < JPClient.maxNumClones ) {

                String str = this.name;

                this.cloneNumber += 1;

                this.name = this.name.substring( 0, this.name.lastIndexOf( "-" ) + 1 ) + this.cloneNumber;
            } else {

                this.isDead = true;

                this.name = "(dead)" + this.name;
            }
        }
    }

    public void unDie() {

        this.cloneNumber = Integer.parseInt( this.name.substring( this.name.lastIndexOf( "-" ) + 1 ) );

        if ( !this.isDead && this.cloneNumber > 1 ) {


            this.cloneNumber -= 1;

            this.name = this.name.substring( 0, this.name.lastIndexOf( "-" ) + 1 ) + this.cloneNumber;
        }


        if ( this.isDead ) {

            this.isDead = false;

            this.name = this.name.substring( 6 );
        }
    }

    public String toString() {

        return this.name;
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\client\ClientPlayer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
