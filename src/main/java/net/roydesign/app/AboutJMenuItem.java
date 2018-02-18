/*     */ package net.roydesign.app;
/*     */ 
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.AbstractButton;
/*     */ import javax.swing.JMenuItem;
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
/*     */ public class AboutJMenuItem
/*     */   extends JMenuItem
/*     */ {
/*     */   AboutJMenuItem(Application paramApplication)
/*     */   {
/*  71 */     super("About");
/*  72 */     String str = paramApplication.getName();
/*  73 */     if (str != null) {
/*  74 */       setText("About " + str);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addActionListener(ActionListener paramActionListener)
/*     */   {
/*  84 */     MRJAdapter.addAboutListener(paramActionListener, this);
/*  85 */     super.addActionListener(paramActionListener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeActionListener(ActionListener paramActionListener)
/*     */   {
/*  94 */     MRJAdapter.removeAboutListener(paramActionListener);
/*  95 */     super.removeActionListener(paramActionListener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean isAutomaticallyPresent()
/*     */   {
/* 105 */     return MRJAdapter.isAboutAutomaticallyPresent();
/*     */   }
/*     */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\net\roydesign\app\AboutJMenuItem.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */