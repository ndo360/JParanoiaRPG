/*     */ package net.roydesign.mac;
/*     */ 
/*     */ import com.apple.mrj.MRJAboutHandler;
/*     */ import com.apple.mrj.MRJApplicationUtils;
/*     */ import com.apple.mrj.MRJOpenApplicationHandler;
/*     */ import com.apple.mrj.MRJOpenDocumentHandler;
/*     */ import com.apple.mrj.MRJPrefsHandler;
/*     */ import com.apple.mrj.MRJPrintDocumentHandler;
/*     */ import com.apple.mrj.MRJQuitHandler;
/*     */ import com.apple.mrj.jdirect.MethodClosure;
/*     */ import com.apple.mrj.jdirect.MethodClosureUPP;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class MRJ23EventProxy
/*     */   extends MRJEventProxy
/*     */ {
/*     */   private static final int kCoreEventClass = 1634039412;
/*     */   private static final int kPreferencesItem = 1886545254;
/*     */   private static final int kReopenApplicationEvent = 1918988400;
/*     */   private static MRJ23EventProxy instance;
/*     */   private Object preferencesHandler;
/*     */   private Object reopenApplicationHandler;
/*  95 */   private boolean preferencesEnabled = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static MRJ23EventProxy getInstance()
/*     */   {
/* 103 */     if (instance == null)
/* 104 */       instance = new MRJ23EventProxy();
/* 105 */     return instance;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private MRJ23EventProxy()
/*     */   {
/* 113 */     Handler localHandler = new Handler(null);
/* 114 */     MRJApplicationUtils.registerAboutHandler(localHandler);
/* 115 */     MRJApplicationUtils.registerOpenApplicationHandler(localHandler);
/* 116 */     MRJApplicationUtils.registerOpenDocumentHandler(localHandler);
/* 117 */     MRJApplicationUtils.registerPrintDocumentHandler(localHandler);
/* 118 */     MRJApplicationUtils.registerQuitHandler(localHandler);
/* 119 */     if (MRJAdapter.mrjVersion >= 3.2F)
/*     */     {
/* 121 */       this.preferencesHandler = new MRJPrefsHandler()
/*     */       {
/*     */         public void handlePrefs()
/*     */         {
/* 125 */           MRJ23EventProxy.this.fireMenuEvent(2);
/*     */         }
/*     */       };
/*     */     }
/* 129 */     else if (MRJAdapter.mrjVersion >= 3.0F)
/*     */     {
/* 131 */       this.preferencesHandler = new JD3AppleEventHandlerThunk(new AppleEventHandler()
/*     */       {
/*     */         public short handleEvent(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
/*     */         {
/* 135 */           new MRJ23EventProxy.3(this).start();
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 142 */           return 0;
/*     */         }
/* 144 */       });
/* 145 */       JD3CarbonFunctions.AEInstallEventHandler(1634039412, 1886545254, ((JD3AppleEventHandlerThunk)this.preferencesHandler).getProc(), 0, false);
/*     */     }
/*     */     
/* 148 */     if (MRJAdapter.mrjVersion >= 2.1F)
/*     */     {
/* 150 */       AppleEventHandler local4 = new AppleEventHandler()
/*     */       {
/*     */         public short handleEvent(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
/*     */         {
/* 154 */           new MRJ23EventProxy.5(this).start();
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 161 */           return 0;
/*     */         }
/*     */       };
/* 164 */       if (MRJAdapter.mrjVersion >= 3.0F)
/*     */       {
/* 166 */         this.reopenApplicationHandler = new JD3AppleEventHandlerThunk(local4);
/* 167 */         JD3CarbonFunctions.AEInstallEventHandler(1634039412, 1918988400, ((JD3AppleEventHandlerThunk)this.reopenApplicationHandler).getProc(), 0, false);
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 172 */         this.reopenApplicationHandler = new JD2AppleEventHandlerThunk(local4);
/* 173 */         JD2AppleEventFunctions.AEInstallEventHandler(1634039412, 1918988400, ((JD2AppleEventHandlerThunk)this.reopenApplicationHandler).getProc(), 0, false);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isPreferencesEnabled()
/*     */   {
/* 191 */     return this.preferencesEnabled;
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
/* 202 */     if (paramBoolean != this.preferencesEnabled)
/*     */     {
/* 204 */       if (MRJAdapter.mrjVersion >= 3.2F)
/*     */       {
/*     */ 
/*     */ 
/* 208 */         if (paramBoolean) {
/* 209 */           MRJApplicationUtils.registerPrefsHandler((MRJPrefsHandler)this.preferencesHandler);
/*     */         } else {
/* 211 */           MRJApplicationUtils.registerPrefsHandler(null);
/*     */         }
/* 213 */       } else if (MRJAdapter.mrjVersion >= 3.0F)
/*     */       {
/* 215 */         if (paramBoolean) {
/* 216 */           JD3CarbonFunctions.EnableMenuCommand(0, 1886545254);
/*     */         } else
/* 218 */           JD3CarbonFunctions.DisableMenuCommand(0, 1886545254);
/*     */       }
/* 220 */       this.preferencesEnabled = paramBoolean;
/*     */     }
/*     */   }
/*     */   
/*     */   private class Handler
/*     */     implements MRJAboutHandler, MRJOpenApplicationHandler, MRJOpenDocumentHandler, MRJPrintDocumentHandler, MRJQuitHandler
/*     */   {
/*     */     Handler(MRJ23EventProxy.1 param1)
/*     */     {
/* 229 */       this();
/*     */     }
/*     */     
/*     */     public void handleAbout()
/*     */     {
/* 234 */       MRJ23EventProxy.this.fireMenuEvent(1);
/*     */     }
/*     */     
/*     */     public void handleOpenApplication()
/*     */     {
/* 239 */       MRJ23EventProxy.this.fireApplicationEvent(3);
/*     */     }
/*     */     
/*     */     public void handleQuit()
/*     */     {
/* 244 */       MRJ23EventProxy.this.fireApplicationEvent(4);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 249 */       if (MRJAdapter.mrjVersion >= 3.0F) {
/* 250 */         throw new IllegalStateException();
/*     */       }
/*     */     }
/*     */     
/*     */     public void handleOpenFile(File paramFile) {
/* 255 */       MRJ23EventProxy.this.fireDocumentEvent(5, paramFile);
/*     */     }
/*     */     
/*     */     public void handlePrintFile(File paramFile)
/*     */     {
/* 260 */       MRJ23EventProxy.this.fireDocumentEvent(6, paramFile);
/*     */     }
/*     */     
/*     */     private Handler() {}
/*     */   }
/*     */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\net\roydesign\mac\MRJ23EventProxy.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */