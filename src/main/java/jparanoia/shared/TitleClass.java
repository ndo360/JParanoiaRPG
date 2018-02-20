/*    */
package jparanoia.shared;
/*    */
/*    */ public class TitleClass
        /*    */ {
    /*  5 */ String name = "";
    /*  6 */ String versionString = "";
    /*  7 */ String playerName = "";
    /*  8 */ String extraTitle = "";
    /*    */
    /* 10 */ boolean isClientTitle = true;

    /*    */
    /*    */
    public TitleClass( String paramString1, String paramString2, boolean paramBoolean )
    /*    */ {
        /* 14 */
        this.name = paramString1;
        /* 15 */
        this.versionString = paramString2;
        /* 16 */
        this.isClientTitle = paramBoolean;
        /*    */
    }

    /*    */
    /*    */
    public void setExtra( String paramString )
    /*    */ {
        /* 21 */
        this.extraTitle = paramString;
        /*    */
    }

    /*    */
    /*    */
    public void setPlayerName( String paramString )
    /*    */ {
        /* 26 */
        this.playerName = paramString;
        /*    */
    }

    /*    */
    /*    */
    public void clearExtra()
    /*    */ {
        /* 31 */
        this.extraTitle = "";
        /*    */
    }

    /*    */
    /*    */
    public String get()
    /*    */ {
        /* 36 */
        String str1 = this.playerName.length() > 0 ? " -- " : "";
        /* 37 */
        String str2 = this.extraTitle.length() > 0 ? "   " : "";
        /*    */
        /* 39 */
        if ( this.isClientTitle ) {
            return this.name + " " + this.versionString + str1 + this.playerName + str2 + this.extraTitle;
        }
        /* 40 */
        return this.name + " " + this.versionString + str2 + this.extraTitle;
        /*    */
    }
    /*    */
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\shared\TitleClass.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
