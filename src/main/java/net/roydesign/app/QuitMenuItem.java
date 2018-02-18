/*     */ package net.roydesign.app;
/*     */ 
/*     */ import java.awt.MenuItem;
/*     */ import java.awt.MenuShortcut;
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
/*     */ 
/*     */ 
/*     */ public class QuitMenuItem
/*     */   extends MenuItem
/*     */ {
/*     */   QuitMenuItem(Application paramApplication)
/*     */   {
/*  71 */     super("Quit", new MenuShortcut(81));
/*  72 */     String str = paramApplication.getName();
/*  73 */     if ((MRJAdapter.mrjVersion >= 3.0F) && (str != null)) {
/*  74 */       setLabel("Quit " + str);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addActionListener(ActionListener paramActionListener)
/*     */   {
/*  83 */     MRJAdapter.addQuitApplicationListener(paramActionListener, this);
/*  84 */     super.addActionListener(paramActionListener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeActionListener(ActionListener paramActionListener)
/*     */   {
/*  93 */     MRJAdapter.removeQuitApplicationListener(paramActionListener);
/*  94 */     super.removeActionListener(paramActionListener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean isAutomaticallyPresent()
/*     */   {
/* 104 */     return MRJAdapter.isQuitAutomaticallyPresent();
/*     */   }
/*     */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\net\roydesign\app\QuitMenuItem.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */