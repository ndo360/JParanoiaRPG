/*     */ package net.roydesign.ui;
/*     */ 
/*     */ import java.awt.Dialog;
/*     */ import java.awt.FileDialog;
/*     */ import java.awt.Frame;
/*     */ import java.io.File;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Properties;
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
/*     */ public class FolderDialog
/*     */   extends FileDialog
/*     */ {
/*  56 */   private boolean modeCheckingEnabled = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FolderDialog(Frame paramFrame)
/*     */   {
/*  64 */     this(paramFrame, "");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public FolderDialog(Frame paramFrame, String paramString)
/*     */   {
/*  75 */     super(paramFrame, paramString, getInitialMode());
/*  76 */     if (MRJAdapter.mrjVersion == -1.0F)
/*  77 */       setFile("-");
/*  78 */     this.modeCheckingEnabled = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getFile()
/*     */   {
/*  90 */     return super.getFile() != null ? "" : null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDirectory()
/*     */   {
/* 101 */     String str = super.getDirectory();
/* 102 */     if (str == null)
/* 103 */       return null;
/* 104 */     if (MRJAdapter.mrjVersion >= 3.0F)
/* 105 */       return new File(str, super.getFile()).getPath();
/* 106 */     return str;
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
/* 117 */     if (this.modeCheckingEnabled)
/* 118 */       throw new Error("can't set mode");
/* 119 */     super.setMode(paramInt);
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
/* 131 */     String str = null;
/* 132 */     if (MRJAdapter.mrjVersion >= 4.0F)
/* 133 */       str = "apple.awt.fileDialogForDirectories";
/* 134 */     Properties localProperties = System.getProperties();
/* 135 */     Object localObject = null;
/* 136 */     if (str != null)
/*     */     {
/* 138 */       localObject = localProperties.get(str);
/* 139 */       localProperties.put(str, "true");
/*     */     }
/*     */     
/*     */ 
/* 143 */     super.show();
/*     */     
/*     */ 
/* 146 */     if (str != null)
/*     */     {
/* 148 */       if (localObject == null) {
/* 149 */         localProperties.remove(str);
/*     */       } else {
/* 151 */         localProperties.put(str, localObject);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static int getInitialMode()
/*     */   {
/* 163 */     if (MRJAdapter.mrjVersion >= 4.0F)
/* 164 */       return 0;
/* 165 */     if (MRJAdapter.mrjVersion != -1.0F)
/* 166 */       return 3;
/* 167 */     return 1;
/*     */   }
/*     */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\net\roydesign\ui\FolderDialog.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */