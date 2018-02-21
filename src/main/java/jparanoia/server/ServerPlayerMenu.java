
package jparanoia.server;


import javax.swing.JLabel;
import javax.swing.JMenu;




 public class ServerPlayerMenu
         extends JMenu
         {
     KillMenuItem playerKillMenuItem;
     UnkillMenuItem playerUnkillMenuItem;
     ServerPlayerClearanceMenu playerClearanceMenu;
     RenamePlayerMenuItem playerRenameMenuItem;
     KickMenuItem playerKickMenuItem;
     ChangePasswordMenuItem playerChangePasswordMenuItem;
     JLabel currentPasswordLabel;
     JLabel realNameLabel;
     ServerPlayer player;



    public ServerPlayerMenu( ServerPlayer paramServerPlayer )
     {

        super( paramServerPlayer.getName() );


        this.player = paramServerPlayer;


        this.playerKillMenuItem = new KillMenuItem( this.player );

        this.playerUnkillMenuItem = new UnkillMenuItem( this.player );

        this.playerClearanceMenu = new ServerPlayerClearanceMenu( this.player );

        this.playerRenameMenuItem = new RenamePlayerMenuItem( this.player );

        this.playerKickMenuItem = new KickMenuItem( this.player );

        this.playerChangePasswordMenuItem = new ChangePasswordMenuItem( this.player );

        this.currentPasswordLabel = new JLabel( "    Password: " + this.player.getPassword() );

        this.realNameLabel = new JLabel( "    Real Name: ???" );










        add( this.playerKillMenuItem );

        add( this.playerUnkillMenuItem );

        add( this.playerClearanceMenu );

        add( this.playerRenameMenuItem );

        add( this.playerKickMenuItem );

        add( this.playerChangePasswordMenuItem );

        add( this.currentPasswordLabel );

        add( this.realNameLabel );

    }

}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\ServerPlayerMenu.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
