/*     */ package net.roydesign.app;
/*     */ 
/*     */ import java.awt.MenuBar;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JMenuBar;
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
/*     */ public class Application
/*     */ {
/*     */   private static Application instance;
/*     */   private String name;
/*     */   private AboutJMenuItem macAboutJMenuItem;
/*     */   private AboutMenuItem macAboutMenuItem;
/*     */   private PreferencesJMenuItem macPreferencesJMenuItem;
/*     */   private PreferencesMenuItem macPreferencesMenuItem;
/*     */   private QuitJMenuItem macQuitJMenuItem;
/*     */   private QuitMenuItem macQuitMenuItem;
/*     */   
/*     */   protected Application()
/*     */   {
/* 138 */     if (instance != null)
/* 139 */       throw new IllegalStateException();
/* 140 */     instance = this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static synchronized Application getInstance()
/*     */   {
/* 150 */     if (instance == null)
/* 151 */       new Application();
/* 152 */     return instance;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setName(String paramString)
/*     */   {
/* 161 */     this.name = paramString;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/* 170 */     if (this.name == null)
/* 171 */       this.name = System.getProperty("com.apple.mrj.application.apple.menu.about.name");
/* 172 */     return this.name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setFramelessMenuBar(MenuBar paramMenuBar)
/*     */   {
/* 184 */     MRJAdapter.setFramelessMenuBar(paramMenuBar);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MenuBar getFramelessMenuBar()
/*     */   {
/* 194 */     return MRJAdapter.getFramelessMenuBar();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setFramelessJMenuBar(JMenuBar paramJMenuBar)
/*     */   {
/* 206 */     MRJAdapter.setFramelessJMenuBar(paramJMenuBar);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public JMenuBar getFramelessJMenuBar()
/*     */   {
/* 216 */     return MRJAdapter.getFramelessJMenuBar();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AboutJMenuItem getAboutJMenuItem()
/*     */   {
/* 226 */     if (MRJAdapter.mrjVersion != -1.0F)
/*     */     {
/* 228 */       if (this.macAboutJMenuItem == null)
/* 229 */         this.macAboutJMenuItem = new AboutJMenuItem(this);
/* 230 */       return this.macAboutJMenuItem;
/*     */     }
/*     */     
/*     */ 
/* 234 */     return new AboutJMenuItem(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AboutMenuItem getAboutMenuItem()
/*     */   {
/* 244 */     if (MRJAdapter.mrjVersion != -1.0F)
/*     */     {
/* 246 */       if (this.macAboutMenuItem == null)
/* 247 */         this.macAboutMenuItem = new AboutMenuItem(this);
/* 248 */       return this.macAboutMenuItem;
/*     */     }
/*     */     
/*     */ 
/* 252 */     return new AboutMenuItem(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PreferencesJMenuItem getPreferencesJMenuItem()
/*     */   {
/* 263 */     if (MRJAdapter.mrjVersion >= 3.0D)
/*     */     {
/* 265 */       if (this.macPreferencesJMenuItem == null)
/* 266 */         this.macPreferencesJMenuItem = new PreferencesJMenuItem();
/* 267 */       return this.macPreferencesJMenuItem;
/*     */     }
/*     */     
/*     */ 
/* 271 */     return new PreferencesJMenuItem();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PreferencesMenuItem getPreferencesMenuItem()
/*     */   {
/* 281 */     if (MRJAdapter.mrjVersion >= 3.0D)
/*     */     {
/* 283 */       if (this.macPreferencesMenuItem == null)
/* 284 */         this.macPreferencesMenuItem = new PreferencesMenuItem();
/* 285 */       return this.macPreferencesMenuItem;
/*     */     }
/*     */     
/*     */ 
/* 289 */     return new PreferencesMenuItem();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public QuitJMenuItem getQuitJMenuItem()
/*     */   {
/* 300 */     if (MRJAdapter.mrjVersion >= 3.0D)
/*     */     {
/* 302 */       if (this.macQuitJMenuItem == null)
/* 303 */         this.macQuitJMenuItem = new QuitJMenuItem(this);
/* 304 */       return this.macQuitJMenuItem;
/*     */     }
/*     */     
/*     */ 
/* 308 */     return new QuitJMenuItem(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public QuitMenuItem getQuitMenuItem()
/*     */   {
/* 318 */     if (MRJAdapter.mrjVersion >= 3.0D)
/*     */     {
/* 320 */       if (this.macQuitMenuItem == null)
/* 321 */         this.macQuitMenuItem = new QuitMenuItem(this);
/* 322 */       return this.macQuitMenuItem;
/*     */     }
/*     */     
/*     */ 
/* 326 */     return new QuitMenuItem(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addOpenApplicationListener(ActionListener paramActionListener)
/*     */   {
/* 337 */     MRJAdapter.addOpenApplicationListener(paramActionListener, this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeOpenApplicationListener(ActionListener paramActionListener)
/*     */   {
/* 347 */     MRJAdapter.removeOpenApplicationListener(paramActionListener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addReopenApplicationListener(ActionListener paramActionListener)
/*     */   {
/* 357 */     MRJAdapter.addReopenApplicationListener(paramActionListener, this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeReopenApplicationListener(ActionListener paramActionListener)
/*     */   {
/* 367 */     MRJAdapter.removeReopenApplicationListener(paramActionListener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addOpenDocumentListener(ActionListener paramActionListener)
/*     */   {
/* 377 */     MRJAdapter.addOpenDocumentListener(paramActionListener, this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeOpenDocumentListener(ActionListener paramActionListener)
/*     */   {
/* 387 */     MRJAdapter.removeOpenDocumentListener(paramActionListener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addPrintDocumentListener(ActionListener paramActionListener)
/*     */   {
/* 397 */     MRJAdapter.addPrintDocumentListener(paramActionListener, this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removePrintDocumentListener(ActionListener paramActionListener)
/*     */   {
/* 407 */     MRJAdapter.removePrintDocumentListener(paramActionListener);
/*     */   }
/*     */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\net\roydesign\app\Application.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */