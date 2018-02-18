/*     */ package net.roydesign.mac;
/*     */ 
/*     */ import com.apple.eawt.Application;
/*     */ import com.apple.eawt.ApplicationAdapter;
/*     */ import com.apple.eawt.ApplicationEvent;
/*     */ import java.io.File;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class MRJ4EventProxy
/*     */   extends MRJEventProxy
/*     */ {
/*     */   private static MRJ4EventProxy instance;
/*     */   private Application application;
/*     */   
/*     */   public static MRJ4EventProxy getInstance()
/*     */   {
/*  68 */     if (instance == null)
/*  69 */       instance = new MRJ4EventProxy();
/*  70 */     return instance;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private MRJ4EventProxy()
/*     */   {
/*  78 */     this.application = new Application();
/*  79 */     this.application.addApplicationListener(new Handler(null));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isPreferencesEnabled()
/*     */   {
/*  90 */     return this.application.getEnabledPreferencesMenu();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPreferencesEnabled(boolean paramBoolean)
/*     */   {
/* 101 */     if (paramBoolean != this.application.getEnabledPreferencesMenu()) {
/* 102 */       this.application.setEnabledPreferencesMenu(paramBoolean);
/*     */     }
/*     */   }
/*     */   
/*     */   private class Handler extends ApplicationAdapter
/*     */   {
/*     */     Handler(MRJ4EventProxy.1 param1) {
/* 109 */       this();
/*     */     }
/*     */     
/*     */     public void handleAbout(ApplicationEvent paramApplicationEvent) {
/* 113 */       MRJ4EventProxy.this.fireMenuEvent(1);
/* 114 */       paramApplicationEvent.setHandled(true);
/*     */     }
/*     */     
/*     */     public void handlePreferences(ApplicationEvent paramApplicationEvent)
/*     */     {
/* 119 */       MRJ4EventProxy.this.fireMenuEvent(2);
/* 120 */       paramApplicationEvent.setHandled(true);
/*     */     }
/*     */     
/*     */     public void handleOpenApplication(ApplicationEvent paramApplicationEvent)
/*     */     {
/* 125 */       MRJ4EventProxy.this.fireApplicationEvent(3);
/* 126 */       paramApplicationEvent.setHandled(true);
/*     */     }
/*     */     
/*     */     public void handleReOpenApplication(ApplicationEvent paramApplicationEvent)
/*     */     {
/* 131 */       MRJ4EventProxy.this.fireApplicationEvent(7);
/* 132 */       paramApplicationEvent.setHandled(true);
/*     */     }
/*     */     
/*     */     public void handleQuit(ApplicationEvent paramApplicationEvent)
/*     */     {
/* 137 */       MRJ4EventProxy.this.fireApplicationEvent(4);
/*     */     }
/*     */     
/*     */     public void handleOpenFile(ApplicationEvent paramApplicationEvent)
/*     */     {
/* 142 */       MRJ4EventProxy.this.fireDocumentEvent(5, new File(paramApplicationEvent.getFilename()));
/* 143 */       paramApplicationEvent.setHandled(true);
/*     */     }
/*     */     
/*     */     public void handlePrintFile(ApplicationEvent paramApplicationEvent)
/*     */     {
/* 148 */       MRJ4EventProxy.this.fireDocumentEvent(6, new File(paramApplicationEvent.getFilename()));
/* 149 */       paramApplicationEvent.setHandled(true);
/*     */     }
/*     */     
/*     */     private Handler() {}
/*     */   }
/*     */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\net\roydesign\mac\MRJ4EventProxy.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */