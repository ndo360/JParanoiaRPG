/*     */ package net.roydesign.app;
/*     */ 
/*     */ import java.awt.MenuItem;
/*     */ import java.awt.event.ActionListener;
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
/*     */ public class AboutMenuItem
/*     */   extends MenuItem
/*     */ {
/*     */   AboutMenuItem(Application paramApplication)
/*     */   {
/*  68 */     super("About");
/*  69 */     String str = paramApplication.getName();
/*  70 */     if (str != null) {
/*  71 */       setLabel("About " + str);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addActionListener(ActionListener paramActionListener)
/*     */   {
/*  81 */     MRJAdapter.addAboutListener(paramActionListener, this);
/*  82 */     super.addActionListener(paramActionListener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeActionListener(ActionListener paramActionListener)
/*     */   {
/*  91 */     MRJAdapter.removeAboutListener(paramActionListener);
/*  92 */     super.removeActionListener(paramActionListener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean isAutomaticallyPresent()
/*     */   {
/* 102 */     return MRJAdapter.isAboutAutomaticallyPresent();
/*     */   }
/*     */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\net\roydesign\app\AboutMenuItem.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */