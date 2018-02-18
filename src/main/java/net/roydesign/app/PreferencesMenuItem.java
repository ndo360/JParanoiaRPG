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
/*     */ 
/*     */ public class PreferencesMenuItem
/*     */   extends MenuItem
/*     */ {
/*     */   PreferencesMenuItem()
/*     */   {
/*  69 */     super("Preferences");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addActionListener(ActionListener paramActionListener)
/*     */   {
/*  78 */     MRJAdapter.addPreferencesListener(paramActionListener, this);
/*  79 */     super.addActionListener(paramActionListener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeActionListener(ActionListener paramActionListener)
/*     */   {
/*  88 */     MRJAdapter.removePreferencesListener(paramActionListener);
/*  89 */     super.removeActionListener(paramActionListener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEnabled(boolean paramBoolean)
/*     */   {
/*  99 */     MRJAdapter.setPreferencesEnabled(paramBoolean);
/* 100 */     super.setEnabled(paramBoolean);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean isAutomaticallyPresent()
/*     */   {
/* 110 */     return MRJAdapter.isPreferencesAutomaticallyPresent();
/*     */   }
/*     */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\net\roydesign\app\PreferencesMenuItem.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */