
package jparanoia.server;

 public class ChangePasswordMenuItem extends PlayerMenuItem
         {

    public ChangePasswordMenuItem( ServerPlayer paramServerPlayer )
     {

        super( paramServerPlayer, "New Password..." );

    }



    protected void action()
     {

        this.somePlayer.changePassword();

    }

}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\ChangePasswordMenuItem.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
