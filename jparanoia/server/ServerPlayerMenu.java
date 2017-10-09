/*    */ package jparanoia.server;
/*    */ 
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JMenu;
/*    */ 
/*    */ public class ServerPlayerMenu
/*    */   extends JMenu
/*    */ {
/*    */   KillMenuItem playerKillMenuItem;
/*    */   UnkillMenuItem playerUnkillMenuItem;
/*    */   ServerPlayerClearanceMenu playerClearanceMenu;
/*    */   RenamePlayerMenuItem playerRenameMenuItem;
/*    */   KickMenuItem playerKickMenuItem;
/*    */   ChangePasswordMenuItem playerChangePasswordMenuItem;
/*    */   JLabel currentPasswordLabel;
/*    */   JLabel realNameLabel;
/*    */   ServerPlayer player;
/*    */   
/*    */   public ServerPlayerMenu(ServerPlayer paramServerPlayer)
/*    */   {
/* 21 */     super(paramServerPlayer.getName());
/*    */     
/* 23 */     this.player = paramServerPlayer;
/*    */     
/* 25 */     this.playerKillMenuItem = new KillMenuItem(this.player);
/* 26 */     this.playerUnkillMenuItem = new UnkillMenuItem(this.player);
/* 27 */     this.playerClearanceMenu = new ServerPlayerClearanceMenu(this.player);
/* 28 */     this.playerRenameMenuItem = new RenamePlayerMenuItem(this.player);
/* 29 */     this.playerKickMenuItem = new KickMenuItem(this.player);
/* 30 */     this.playerChangePasswordMenuItem = new ChangePasswordMenuItem(this.player);
/* 31 */     this.currentPasswordLabel = new JLabel("    Password: " + this.player.getPassword());
/* 32 */     this.realNameLabel = new JLabel("    Real Name: ???");
/*    */     
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 42 */     add(this.playerKillMenuItem);
/* 43 */     add(this.playerUnkillMenuItem);
/* 44 */     add(this.playerClearanceMenu);
/* 45 */     add(this.playerRenameMenuItem);
/* 46 */     add(this.playerKickMenuItem);
/* 47 */     add(this.playerChangePasswordMenuItem);
/* 48 */     add(this.currentPasswordLabel);
/* 49 */     add(this.realNameLabel);
/*    */   }
/*    */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\ServerPlayerMenu.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */