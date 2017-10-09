/*     */ package net.roydesign.io;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
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
/*     */ public class ApplicationFile
/*     */ {
/*  54 */   private static final String osName = System.getProperty("os.name");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   File executable;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ApplicationFile(String paramString)
/*     */   {
/*  68 */     this.executable = new File(paramString);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ApplicationFile(String paramString1, String paramString2)
/*     */   {
/*  80 */     this.executable = new File(paramString1, paramString2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ApplicationFile(File paramFile, String paramString)
/*     */   {
/*  92 */     this.executable = new File(paramFile, paramString);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ApplicationFile(File paramFile)
/*     */   {
/* 104 */     this(paramFile.getPath());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean open()
/*     */     throws IOException
/*     */   {
/* 114 */     if (MRJAdapter.mrjVersion >= 3.0F)
/*     */     {
/*     */       try
/*     */       {
/*     */ 
/* 119 */         Process localProcess1 = Runtime.getRuntime().exec(new String[] { "open", "-a", this.executable.getAbsolutePath() });
/*     */         
/* 121 */         if (localProcess1.waitFor() != 0) {
/* 122 */           return false;
/*     */         }
/*     */       }
/*     */       catch (InterruptedException localInterruptedException1) {
/* 126 */         return false;
/*     */       }
/*     */     }
/* 129 */     if (MRJAdapter.mrjVersion != -1.0F)
/*     */     {
/* 131 */       Runtime.getRuntime().exec(new String[] { this.executable.getAbsolutePath() });
/*     */     } else {
/* 133 */       if (osName.startsWith("Windows"))
/*     */       {
/*     */ 
/*     */         try
/*     */         {
/*     */ 
/* 139 */           Process localProcess2 = Runtime.getRuntime().exec(new String[] { "cmd", "/c", "start", "\"\"", this.executable.getAbsolutePath() });
/*     */           
/* 141 */           if (localProcess2.waitFor() != 0) {
/* 142 */             return false;
/*     */           }
/*     */         }
/*     */         catch (InterruptedException localInterruptedException2) {
/* 146 */           return false;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */       try
/*     */       {
/* 154 */         Process localProcess3 = Runtime.getRuntime().exec(new String[] { this.executable.getAbsolutePath() });
/* 155 */         if (localProcess3.waitFor() != 0) {
/* 156 */           return false;
/*     */         }
/*     */       }
/*     */       catch (InterruptedException localInterruptedException3) {
/* 160 */         return false;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 166 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Process open(String[] paramArrayOfString)
/*     */     throws IOException
/*     */   {
/* 178 */     String[] arrayOfString = new String[paramArrayOfString.length + 1];
/* 179 */     arrayOfString[0] = this.executable.getAbsolutePath();
/* 180 */     System.arraycopy(paramArrayOfString, 0, arrayOfString, 1, paramArrayOfString.length);
/* 181 */     return Runtime.getRuntime().exec(arrayOfString);
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
/*     */   public boolean openDocument(DocumentFile paramDocumentFile)
/*     */     throws IOException
/*     */   {
/* 197 */     return openDocument(paramDocumentFile.file);
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
/*     */   public boolean openDocument(File paramFile)
/*     */     throws IOException
/*     */   {
/* 213 */     return openDocuments(new File[] { paramFile });
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
/*     */   public boolean openDocuments(DocumentFile[] paramArrayOfDocumentFile)
/*     */     throws IOException
/*     */   {
/* 229 */     File[] arrayOfFile = new File[paramArrayOfDocumentFile.length];
/* 230 */     for (int i = 0; i < arrayOfFile.length; i++)
/* 231 */       arrayOfFile[i] = paramArrayOfDocumentFile[i].file;
/* 232 */     return openDocuments(arrayOfFile);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean openDocuments(File[] paramArrayOfFile)
/*     */     throws IOException
/*     */   {
/*     */     int i;
/*     */     
/*     */ 
/*     */ 
/*     */     Process localProcess;
/*     */     
/*     */ 
/* 248 */     if (MRJAdapter.mrjVersion >= 3.0F)
/*     */     {
/*     */       try
/*     */       {
/*     */ 
/* 253 */         String[] arrayOfString1 = new String[3 + paramArrayOfFile.length];
/* 254 */         arrayOfString1[0] = "open";
/* 255 */         arrayOfString1[1] = "-a";
/* 256 */         arrayOfString1[2] = this.executable.getAbsolutePath();
/* 257 */         for (i = 0; i < paramArrayOfFile.length; i++)
/* 258 */           arrayOfString1[(3 + i)] = paramArrayOfFile[i].getAbsolutePath();
/* 259 */         localProcess = Runtime.getRuntime().exec(arrayOfString1);
/* 260 */         if (localProcess.waitFor() != 0) {
/* 261 */           return false;
/*     */         }
/*     */       }
/*     */       catch (InterruptedException localInterruptedException1) {
/* 265 */         return false;
/*     */       } }
/*     */     String[] arrayOfString2;
/* 268 */     if (MRJAdapter.mrjVersion != -1.0F)
/*     */     {
/*     */ 
/* 271 */       arrayOfString2 = new String[1 + paramArrayOfFile.length];
/* 272 */       arrayOfString2[0] = this.executable.getAbsolutePath();
/* 273 */       for (i = 0; i < paramArrayOfFile.length; i++)
/* 274 */         arrayOfString2[(1 + i)] = paramArrayOfFile[i].getAbsolutePath();
/* 275 */       Runtime.getRuntime().exec(arrayOfString2);
/*     */     } else {
/* 277 */       if (osName.startsWith("Windows"))
/*     */       {
/*     */ 
/*     */         try
/*     */         {
/*     */ 
/* 283 */           arrayOfString2 = new String[5 + paramArrayOfFile.length];
/* 284 */           arrayOfString2[0] = "cmd";
/* 285 */           arrayOfString2[1] = "/c";
/* 286 */           arrayOfString2[2] = "start";
/* 287 */           arrayOfString2[3] = "\"\"";
/* 288 */           arrayOfString2[4] = this.executable.getAbsolutePath();
/* 289 */           for (i = 0; i < paramArrayOfFile.length; i++)
/* 290 */             arrayOfString2[(5 + i)] = paramArrayOfFile[i].getAbsolutePath();
/* 291 */           localProcess = Runtime.getRuntime().exec(arrayOfString2);
/* 292 */           if (localProcess.waitFor() != 0) {
/* 293 */             return false;
/*     */           }
/*     */         }
/*     */         catch (InterruptedException localInterruptedException2) {
/* 297 */           return false;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */       try
/*     */       {
/* 305 */         String[] arrayOfString3 = new String[1 + paramArrayOfFile.length];
/* 306 */         arrayOfString3[0] = this.executable.getAbsolutePath();
/* 307 */         for (i = 0; i < paramArrayOfFile.length; i++)
/* 308 */           arrayOfString3[(1 + i)] = paramArrayOfFile[i].getAbsolutePath();
/* 309 */         localProcess = Runtime.getRuntime().exec(arrayOfString3);
/* 310 */         if (localProcess.waitFor() != 0) {
/* 311 */           return false;
/*     */         }
/*     */       }
/*     */       catch (InterruptedException localInterruptedException3) {
/* 315 */         return false;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 321 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getPath()
/*     */   {
/* 331 */     return this.executable.getPath();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getAbsolutePath()
/*     */   {
/* 341 */     return this.executable.getAbsolutePath();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getCanonicalPath()
/*     */     throws IOException
/*     */   {
/* 351 */     return this.executable.getCanonicalPath();
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getExecutableName()
/*     */   {
/* 370 */     return this.executable.getName();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getDisplayedName()
/*     */     throws IOException
/*     */   {
/* 380 */     if (MRJAdapter.mrjVersion != -1.0F)
/*     */     {
/* 382 */       if (this.executable.isDirectory())
/*     */       {
/*     */ 
/* 385 */         File localFile = new File(this.executable, "Contents/MRJApp.properties");
/* 386 */         String str; if (localFile.exists())
/*     */         {
/* 388 */           str = MRJAdapter.parseMRJAppProperties(localFile, "com.apple.mrj.application.apple.menu.about.name");
/*     */           
/* 390 */           if (str != null) {
/* 391 */             return str;
/*     */           }
/*     */         }
/* 394 */         localFile = new File(this.executable, "Contents/Info.plist");
/* 395 */         if (localFile.exists())
/*     */         {
/* 397 */           str = MRJAdapter.parseInfoPlist(localFile, "com.apple.mrj.application.apple.menu.about.name");
/* 398 */           if (str == null)
/*     */           {
/* 400 */             str = MRJAdapter.parseInfoPlist(localFile, "CFBundleName");
/* 401 */             if (str == null)
/* 402 */               str = MRJAdapter.parseInfoPlist(localFile, "CFBundleExecutable");
/*     */           }
/* 404 */           return str;
/*     */         }
/*     */       }
/*     */     }
/* 408 */     else if (!osName.startsWith("Windows")) {}
/*     */     
/*     */ 
/*     */ 
/* 412 */     return getExecutableName();
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
/*     */ 
/*     */ 
/*     */   public String getMacCreator()
/*     */     throws IOException
/*     */   {
/* 431 */     return MRJAdapter.getFileCreator(this.executable);
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
/*     */   public File getMacBundleResource(String paramString)
/*     */     throws FileNotFoundException
/*     */   {
/* 447 */     return MRJAdapter.getBundleResource(paramString);
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
/*     */ 
/*     */   public File getMacBundleResource(String paramString1, String paramString2)
/*     */     throws FileNotFoundException
/*     */   {
/* 465 */     return MRJAdapter.getBundleResource(paramString1, paramString2);
/*     */   }
/*     */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\net\roydesign\io\ApplicationFile.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */