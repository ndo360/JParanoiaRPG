package jparanoia.client;
import java.awt.Color;
import javax.swing.text.SimpleAttributeSet;
import jparanoia.shared.JPPlayer;

public class ClientPlayer extends JPPlayer {
    final int PLAYER_NUMBER;
    final boolean IS_PLAYER;
    String name;
    int cloneNumber;
    /*  13 */ boolean loggedIn = true;
    /*  14 */ boolean isDead = false;
    javax.swing.text.DefaultStyledDocument characterSheet;
    PrivateMessageFrame pmFrame;
    /*  17 */ Color chatColor = Color.gray;
    /*  18 */ SimpleAttributeSet sas = new SimpleAttributeSet();

    public ClientPlayer( int paramInt, String paramString, boolean paramBoolean1, boolean paramBoolean2 ) {
        /*  27 */
        this.PLAYER_NUMBER = paramInt;
        /*  28 */
        this.name = paramString;
        /*  29 */
        this.IS_PLAYER = paramBoolean1;
        /*  30 */
        this.loggedIn = paramBoolean2;
    }

    public String getName() {
        /*  42 */
        if ( this.PLAYER_NUMBER == 0 ) {
            return this.name;
        }
        /*  43 */
        return this.name.substring( 0, this.name.lastIndexOf( "-" ) );
    }

    public void setName( String paramString ) {
        /*  36 */
        this.name = paramString;
    }

    public boolean isLoggedIn() {
        /*  50 */
        return this.loggedIn;
    }

    public void setLoggedIn( boolean paramBoolean ) {
        /*  56 */
        this.loggedIn = paramBoolean;
    }

    public boolean isAPlayer() {
        /*  62 */
        return this.IS_PLAYER;
    }

    public Color getChatColor() {
        /*  74 */
        return this.chatColor;
    }

    public void setChatColor( Color paramColor ) {
        /*  68 */
        this.chatColor = paramColor;
    }

    public String getID() {
        String str;
        /*  80 */
        if ( this.PLAYER_NUMBER < 10 ) {
            str = "0" + this.PLAYER_NUMBER;
        } else
            /*  81 */ {
            str = "" + this.PLAYER_NUMBER;
        }
        /*  82 */
        return str;
    }

    public int getPlayerNumber() {
        /*  87 */
        return this.PLAYER_NUMBER;
    }

    public void setPMFrame( PrivateMessageFrame paramPrivateMessageFrame ) {
        /*  92 */
        this.pmFrame = paramPrivateMessageFrame;
    }

    public void die() {
        /* 109 */
        if ( !this.isDead ) {
            /* 111 */
            this.cloneNumber = Integer.parseInt( this.name.substring( this.name.lastIndexOf( "-" ) + 1 ) );
            /* 112 */
            if ( this.cloneNumber < JPClient.maxNumClones ) {
                /* 114 */
                String str = this.name;
                /* 115 */
                this.cloneNumber += 1;
                /* 116 */
                this.name = this.name.substring( 0, this.name.lastIndexOf( "-" ) + 1 ) + this.cloneNumber;
            } else {
                /* 121 */
                this.isDead = true;
                /* 122 */
                this.name = "(dead)" + this.name;
            }
        }
    }

    public void unDie() {
        /* 130 */
        this.cloneNumber = Integer.parseInt( this.name.substring( this.name.lastIndexOf( "-" ) + 1 ) );
        /* 131 */
        if ( !this.isDead && this.cloneNumber > 1 ) {

            /* 134 */
            this.cloneNumber -= 1;
            /* 135 */
            this.name = this.name.substring( 0, this.name.lastIndexOf( "-" ) + 1 ) + this.cloneNumber;
        }

        /* 138 */
        if ( this.isDead ) {
            /* 140 */
            this.isDead = false;
            /* 141 */
            this.name = this.name.substring( 6 );
        }
    }

    public String toString() {
        /* 147 */
        return this.name;
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\client\ClientPlayer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
