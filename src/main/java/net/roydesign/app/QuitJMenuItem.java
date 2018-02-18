/*     */ package net.roydesign.app;
/*     */ 
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.AbstractButton;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.KeyStroke;
/*     */ import net.roydesign.mac.MRJAdapter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class QuitJMenuItem
/*     */   extends JMenuItem
/*     */ {
/*     */   QuitJMenuItem(Application paramApplication)
/*     */   {
/*  74 */     super("Quit");
/*  75 */     setAccelerator(KeyStroke.getKeyStroke(81, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
/*     */     
/*  77 */     String str = paramApplication.getName();
/*  78 */     if ((MRJAdapter.mrjVersion >= 3.0F) && (str != null)) {
/*  79 */       setText("Quit " + str);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addActionListener(ActionListener paramActionListener)
/*     */   {
/*  89 */     MRJAdapter.addQuitApplicationListener(paramActionListener, this);
/*  90 */     super.addActionListener(paramActionListener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeActionListener(ActionListener paramActionListener)
/*     */   {
/*  99 */     MRJAdapter.removeQuitApplicationListener(paramActionListener);
/* 100 */     super.removeActionListener(paramActionListener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean isAutomaticallyPresent()
/*     */   {
/* 110 */     return MRJAdapter.isQuitAutomaticallyPresent();
/*     */   }
/*     */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\net\roydesign\app\QuitJMenuItem.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */