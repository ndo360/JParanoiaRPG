/*     */ package net.roydesign.mac;
/*     */ 
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.File;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import net.roydesign.event.ApplicationEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ abstract class MRJEventProxy
/*     */ {
/*     */   private final String ABOUT_KEY = "about";
/*     */   private final String PREFERENCES_KEY = "preferences";
/*     */   private final String OPEN_APPLICATION_KEY = "open application";
/*     */   private final String QUIT_APPLICATION_KEY = "quit application";
/*     */   private final String OPEN_DOCUMENT_KEY = "open document";
/*     */   private final String PRINT_DOCUMENT_KEY = "print document";
/*     */   private final String REOPEN_APPLICATION_KEY = "reopen application";
/*     */   private Hashtable listenerLists;
/*     */   
/*     */   MRJEventProxy()
/*     */   {
/*  56 */     this.ABOUT_KEY = "about";
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  61 */     this.PREFERENCES_KEY = "preferences";
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  66 */     this.OPEN_APPLICATION_KEY = "open application";
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  71 */     this.QUIT_APPLICATION_KEY = "quit application";
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  76 */     this.OPEN_DOCUMENT_KEY = "open document";
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  81 */     this.PRINT_DOCUMENT_KEY = "print document";
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  86 */     this.REOPEN_APPLICATION_KEY = "reopen application";
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  93 */     this.listenerLists = new Hashtable(); }
/*     */   
/*     */   private class ListenerInfo { ActionListener actionListener;
/*     */     
/*     */     private ListenerInfo() {}
/*     */     
/*  99 */     ListenerInfo(MRJEventProxy.1 param1) { this(); }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     Object source;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addAboutListener(ActionListener paramActionListener, Object paramObject)
/*     */   {
/* 113 */     addListener(paramActionListener, paramObject, "about");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeAboutListener(ActionListener paramActionListener)
/*     */   {
/* 122 */     removeListener(paramActionListener, "about");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addPreferencesListener(ActionListener paramActionListener, Object paramObject)
/*     */   {
/* 134 */     if (this.listenerLists.get("preferences") == null) {
/* 135 */       setPreferencesEnabled(true);
/*     */     }
/* 137 */     addListener(paramActionListener, paramObject, "preferences");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removePreferencesListener(ActionListener paramActionListener)
/*     */   {
/* 146 */     removeListener(paramActionListener, "preferences");
/*     */     
/*     */ 
/* 149 */     if (this.listenerLists.get("preferences") == null) {
/* 150 */       setPreferencesEnabled(false);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addOpenApplicationListener(ActionListener paramActionListener, Object paramObject)
/*     */   {
/* 161 */     addListener(paramActionListener, paramObject, "open application");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeOpenApplicationListener(ActionListener paramActionListener)
/*     */   {
/* 170 */     removeListener(paramActionListener, "open application");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addReopenApplicationListener(ActionListener paramActionListener, Object paramObject)
/*     */   {
/* 181 */     addListener(paramActionListener, paramObject, "reopen application");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeReopenApplicationListener(ActionListener paramActionListener)
/*     */   {
/* 190 */     removeListener(paramActionListener, "reopen application");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addQuitApplicationListener(ActionListener paramActionListener, Object paramObject)
/*     */   {
/* 201 */     addListener(paramActionListener, paramObject, "quit application");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeQuitApplicationListener(ActionListener paramActionListener)
/*     */   {
/* 210 */     removeListener(paramActionListener, "quit application");
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
/*     */   public void addOpenDocumentListener(ActionListener paramActionListener, Object paramObject)
/*     */   {
/* 223 */     addListener(paramActionListener, paramObject, "open document");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeOpenDocumentListener(ActionListener paramActionListener)
/*     */   {
/* 232 */     removeListener(paramActionListener, "open document");
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
/*     */   public void addPrintDocumentListener(ActionListener paramActionListener, Object paramObject)
/*     */   {
/* 245 */     addListener(paramActionListener, paramObject, "print document");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removePrintDocumentListener(ActionListener paramActionListener)
/*     */   {
/* 254 */     removeListener(paramActionListener, "print document");
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
/*     */   private void addListener(ActionListener paramActionListener, Object paramObject, String paramString)
/*     */   {
/* 268 */     Hashtable localHashtable = (Hashtable)this.listenerLists.get(paramString);
/* 269 */     if (localHashtable == null)
/*     */     {
/*     */ 
/* 272 */       localHashtable = new Hashtable(1);
/* 273 */       this.listenerLists.put(paramString, localHashtable);
/*     */     }
/*     */     
/*     */ 
/* 277 */     String str = paramActionListener.getClass().getName();
/* 278 */     if (localHashtable.containsKey(str)) {
/* 279 */       return;
/*     */     }
/*     */     
/* 282 */     ListenerInfo localListenerInfo = new ListenerInfo(null);
/* 283 */     localListenerInfo.actionListener = paramActionListener;
/* 284 */     localListenerInfo.source = (paramObject != null ? paramObject : this);
/* 285 */     localHashtable.put(str, localListenerInfo);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void removeListener(ActionListener paramActionListener, String paramString)
/*     */   {
/* 296 */     Hashtable localHashtable = (Hashtable)this.listenerLists.get(paramString);
/* 297 */     String str = paramActionListener.getClass().getName();
/* 298 */     if ((localHashtable != null) && (localHashtable.remove(str) != null) && (localHashtable.isEmpty())) {
/* 299 */       this.listenerLists.remove(paramString);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract boolean isPreferencesEnabled();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract void setPreferencesEnabled(boolean paramBoolean);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void fireMenuEvent(int paramInt)
/*     */   {
/* 326 */     Hashtable localHashtable = null;
/* 327 */     switch (paramInt)
/*     */     {
/*     */     case 1: 
/* 330 */       localHashtable = (Hashtable)this.listenerLists.get("about");
/* 331 */       break;
/*     */     case 2: 
/* 333 */       localHashtable = (Hashtable)this.listenerLists.get("preferences");
/* 334 */       break;
/*     */     default: 
/* 336 */       throw new Error("unknown event type");
/*     */     }
/* 338 */     if (localHashtable == null)
/* 339 */       return;
/* 340 */     Enumeration localEnumeration = localHashtable.elements();
/* 341 */     while (localEnumeration.hasMoreElements())
/*     */     {
/* 343 */       ListenerInfo localListenerInfo = (ListenerInfo)localEnumeration.nextElement();
/* 344 */       ApplicationEvent localApplicationEvent = new ApplicationEvent(localListenerInfo.source, paramInt);
/* 345 */       localListenerInfo.actionListener.actionPerformed(localApplicationEvent);
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
/*     */   protected void fireDocumentEvent(int paramInt, File paramFile)
/*     */   {
/* 358 */     Hashtable localHashtable = null;
/* 359 */     switch (paramInt)
/*     */     {
/*     */     case 5: 
/* 362 */       localHashtable = (Hashtable)this.listenerLists.get("open document");
/* 363 */       break;
/*     */     case 6: 
/* 365 */       localHashtable = (Hashtable)this.listenerLists.get("print document");
/* 366 */       break;
/*     */     default: 
/* 368 */       throw new Error("unknown event type");
/*     */     }
/* 370 */     if (localHashtable == null)
/* 371 */       return;
/* 372 */     Enumeration localEnumeration = localHashtable.elements();
/* 373 */     while (localEnumeration.hasMoreElements())
/*     */     {
/* 375 */       ListenerInfo localListenerInfo = (ListenerInfo)localEnumeration.nextElement();
/* 376 */       ApplicationEvent localApplicationEvent = new ApplicationEvent(localListenerInfo.source, paramInt, paramFile);
/* 377 */       localListenerInfo.actionListener.actionPerformed(localApplicationEvent);
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
/*     */   protected void fireApplicationEvent(int paramInt)
/*     */   {
/* 390 */     Hashtable localHashtable = null;
/* 391 */     switch (paramInt)
/*     */     {
/*     */     case 3: 
/* 394 */       localHashtable = (Hashtable)this.listenerLists.get("open application");
/* 395 */       break;
/*     */     case 7: 
/* 397 */       localHashtable = (Hashtable)this.listenerLists.get("reopen application");
/* 398 */       break;
/*     */     case 4: 
/* 400 */       localHashtable = (Hashtable)this.listenerLists.get("quit application");
/* 401 */       if (localHashtable == null)
/*     */       {
/* 403 */         System.exit(0); return;
/*     */       }
/*     */       break;
/*     */     case 5: case 6: 
/*     */     default: 
/* 408 */       throw new Error("unknown event type");
/*     */     }
/* 410 */     if (localHashtable == null)
/* 411 */       return;
/* 412 */     Enumeration localEnumeration = localHashtable.elements();
/* 413 */     while (localEnumeration.hasMoreElements())
/*     */     {
/* 415 */       ListenerInfo localListenerInfo = (ListenerInfo)localEnumeration.nextElement();
/* 416 */       ApplicationEvent localApplicationEvent = new ApplicationEvent(localListenerInfo.source, paramInt);
/* 417 */       localListenerInfo.actionListener.actionPerformed(localApplicationEvent);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\net\roydesign\mac\MRJEventProxy.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */