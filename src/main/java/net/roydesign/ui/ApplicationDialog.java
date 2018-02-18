/*     */ package net.roydesign.ui;
/*     */ 
/*     */ import java.awt.Dialog;
/*     */ import java.awt.FileDialog;
/*     */ import java.awt.Frame;
/*     */ import java.io.File;
/*     */ import java.io.FilenameFilter;
/*     */ import java.io.IOException;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Properties;
/*     */ import net.roydesign.io.ApplicationFile;
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
/*     */ public class ApplicationDialog
/*     */   extends FileDialog
/*     */ {
/*  59 */   private boolean modeCheckingEnabled = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ApplicationDialog(Frame paramFrame)
/*     */   {
/*  67 */     this(paramFrame, "");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ApplicationDialog(Frame paramFrame, String paramString)
/*     */   {
/*  78 */     super(paramFrame, paramString, 0);
/*  79 */     setFilenameFilter(new ApplicationFilter(null));
/*  80 */     this.modeCheckingEnabled = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ApplicationFile getApplicationFile()
/*     */   {
/*  92 */     String str = getFile();
/*  93 */     return str != null ? new ApplicationFile(getDirectory(), str) : null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMode(int paramInt)
/*     */   {
/* 104 */     if (this.modeCheckingEnabled)
/* 105 */       throw new Error("can't set mode");
/* 106 */     super.setMode(paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void show()
/*     */   {
/* 118 */     String str = null;
/* 119 */     if (MRJAdapter.mrjVersion >= 4.0F) {
/* 120 */       str = "apple.awt.use-file-dialog-packages";
/* 121 */     } else if (MRJAdapter.mrjVersion >= 3.0F)
/* 122 */       str = "com.apple.macos.use-file-dialog-packages";
/* 123 */     Properties localProperties = System.getProperties();
/* 124 */     Object localObject = null;
/* 125 */     if (str != null)
/*     */     {
/* 127 */       localObject = localProperties.get(str);
/* 128 */       localProperties.put(str, "true");
/*     */     }
/*     */     
/*     */ 
/* 132 */     super.show();
/*     */     
/*     */ 
/* 135 */     if (str != null)
/*     */     {
/* 137 */       if (localObject == null) {
/* 138 */         localProperties.remove(str);
/*     */       } else {
/* 140 */         localProperties.put(str, localObject);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private class ApplicationFilter implements FilenameFilter {
/*     */     ApplicationFilter(ApplicationDialog.1 param1) {
/* 147 */       this();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public boolean accept(File paramFile, String paramString)
/*     */     {
/*     */       try
/*     */       {
/* 159 */         if (MRJAdapter.mrjVersion != -1.0F) {
/* 160 */           return MRJAdapter.getFileType(new File(paramFile, paramString)).equals("APPL");
/*     */         }
/*     */       }
/*     */       catch (IOException localIOException) {}
/*     */       
/*     */ 
/*     */ 
/* 167 */       return true;
/*     */     }
/*     */     
/*     */     private ApplicationFilter() {}
/*     */   }
/*     */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\net\roydesign\ui\ApplicationDialog.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */