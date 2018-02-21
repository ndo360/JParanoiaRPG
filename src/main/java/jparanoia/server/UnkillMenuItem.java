
package jparanoia.server;

 public class UnkillMenuItem extends PlayerMenuItem
         {

    public UnkillMenuItem( ServerPlayer paramServerPlayer )
     {

        super( paramServerPlayer, "Unkill" );

    }



    protected void action()
     {

        this.somePlayer.unkill();

    }

}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\UnkillMenuItem.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
