
package jparanoia.server;

 public class KillMenuItem extends PlayerMenuItem
         {

    public KillMenuItem( ServerPlayer paramServerPlayer )
     {

        super( paramServerPlayer, "Kill" );

    }



    protected void action()
     {

        this.somePlayer.kill();

    }

}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\KillMenuItem.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
