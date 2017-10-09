/*    */ package jparanoia.shared;
/*    */ 
/*    */ import java.awt.event.WindowEvent;
/*    */ import java.util.Vector;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JTable;
/*    */ 
/*    */ public class ObserversFrame extends JFrame
/*    */ {
/*    */   public JTable jt;
/*    */   public javax.swing.JScrollPane sp;
/*    */   
/*    */   public ObserversFrame()
/*    */   {
/* 15 */     super("Observers");
/*    */     
/* 17 */     setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(getClass().getResource("graphics/jparanoiaIcon.jpg")));
/*    */     
/* 19 */     Vector localVector = new Vector(0);
/*    */     
/* 21 */     localVector.add("whocares");
/*    */     
/* 23 */     this.jt = new JTable(JParanoia.obsNames, localVector);
/*    */     
/* 25 */     this.jt.setShowGrid(false);
/* 26 */     this.jt.setEnabled(false);
/*    */     
/*    */ 
/*    */ 
/*    */ 
/* 31 */     getContentPane().add(this.jt);
/*    */     
/* 33 */     setSize(200, 200);
/* 34 */     setLocation(600, 100);
/*    */     
/*    */ 
/* 37 */     addWindowListener(new java.awt.event.WindowAdapter()
/*    */     {
/*    */ 
/*    */       public void windowClosing(WindowEvent paramAnonymousWindowEvent)
/*    */       {
/* 42 */         ObserversFrame.this.setVisible(false);
/*    */       }
/*    */     });
/*    */   }
/*    */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\shared\ObserversFrame.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */