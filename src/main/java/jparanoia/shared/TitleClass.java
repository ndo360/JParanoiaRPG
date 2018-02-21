
package jparanoia.shared;

 public class TitleClass
         {
     String name = "";
     String versionString = "";
     String playerName = "";
     String extraTitle = "";

     boolean isClientTitle = true;



    public TitleClass( String paramString1, String paramString2, boolean paramBoolean )
     {

        this.name = paramString1;

        this.versionString = paramString2;

        this.isClientTitle = paramBoolean;

    }



    public void setExtra( String paramString )
     {

        this.extraTitle = paramString;

    }



    public void setPlayerName( String paramString )
     {

        this.playerName = paramString;

    }



    public void clearExtra()
     {

        this.extraTitle = "";

    }



    public String get()
     {

        String str1 = this.playerName.length() > 0 ? " -- " : "";

        String str2 = this.extraTitle.length() > 0 ? "   " : "";


        if ( this.isClientTitle ) {
            return this.name + " " + this.versionString + str1 + this.playerName + str2 + this.extraTitle;
        }

        return this.name + " " + this.versionString + str2 + this.extraTitle;

    }

}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\shared\TitleClass.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
