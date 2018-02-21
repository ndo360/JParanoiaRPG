
package jparanoia.server;

 public class RenamePlayerMenuItem extends PlayerMenuItem
         {

    public RenamePlayerMenuItem( ServerPlayer paramServerPlayer )
     {

        super( paramServerPlayer, "New family..." );

    }



    protected void action()
     {

        this.somePlayer.rename();

    }

}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\RenamePlayerMenuItem.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
