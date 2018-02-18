/*    */ package jparanoia.server;
/*    */ 
/*    */ import java.awt.event.KeyEvent;
/*    */ 
/*    */ public class ReturnListener implements java.awt.event.KeyListener
/*    */ {
/*    */   public void keyTyped(KeyEvent paramKeyEvent) {}
/*    */   
/*    */   public void keyReleased(KeyEvent paramKeyEvent) {}
/*    */   
/*    */   public void keyPressed(KeyEvent paramKeyEvent)
/*    */   {
/* 13 */     System.out.println("Key Code = " + paramKeyEvent.getKeyCode());
/*    */   }
/*    */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\ReturnListener.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */