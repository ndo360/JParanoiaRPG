/*     */ package net.roydesign.io;
/*     */ 
/*     */ import java.io.File;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DocumentFile
/*     */ {
/*  77 */   private static final String osName = System.getProperty("os.name");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   File file;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  87 */   private String macCreator = "";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  92 */   private String macType = "";
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DocumentFile(String paramString)
/*     */   {
/* 101 */     this.file = new File(paramString);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DocumentFile(String paramString1, String paramString2)
/*     */   {
/* 113 */     this.file = new File(paramString1, paramString2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DocumentFile(File paramFile, String paramString)
/*     */   {
/* 125 */     this.file = new File(paramFile, paramString);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DocumentFile(File paramFile)
/*     */   {
/* 137 */     this(paramFile.getPath());
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
/*     */   public boolean open()
/*     */     throws IOException
/*     */   {
/* 156 */     if (MRJAdapter.mrjVersion >= 3.0F)
/*     */     {
/*     */       try
/*     */       {
/*     */ 
/* 161 */         Process localProcess = Runtime.getRuntime().exec(new String[] { "open", this.file.getAbsolutePath() });
/* 162 */         if (localProcess.waitFor() != 0) {
/* 163 */           return false;
/*     */         }
/*     */       }
/*     */       catch (InterruptedException localInterruptedException1) {
/* 167 */         return false;
/*     */       } }
/*     */     Object localObject;
/* 170 */     if (MRJAdapter.mrjVersion != -1.0F)
/*     */     {
/*     */ 
/*     */ 
/* 174 */       localObject = MRJAdapter.findApplication(MRJAdapter.getFileCreator(this.file));
/*     */       
/*     */ 
/*     */ 
/* 178 */       File localFile = new File(((File)localObject).getParent());
/* 179 */       String str = localFile.getName();
/* 180 */       if ((str.equals("MacOS")) || (str.equals("MacOSClassic")))
/*     */       {
/* 182 */         localFile = new File(localFile.getParent());
/* 183 */         if ((localFile.getName().equals("Contents")) && (new File(localFile, "Info.plist").exists()))
/*     */         {
/* 185 */           localFile = new File(localFile.getParent());
/* 186 */           if (localFile.getName().endsWith(".app")) {
/* 187 */             localObject = localFile;
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 193 */       Runtime.getRuntime().exec(new String[] { ((File)localObject).getAbsolutePath(), this.file.getAbsolutePath() });
/*     */     } else {
/* 195 */       if (osName.startsWith("Windows"))
/*     */       {
/*     */ 
/*     */         try
/*     */         {
/*     */ 
/* 201 */           localObject = Runtime.getRuntime().exec(new String[] { "cmd", "/c", "start", "\"\"", this.file.getAbsolutePath() });
/*     */           
/* 203 */           if (((Process)localObject).waitFor() != 0) {
/* 204 */             return false;
/*     */           }
/*     */         }
/*     */         catch (InterruptedException localInterruptedException2) {
/* 208 */           return false;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 214 */       return false;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 219 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean openWith(ApplicationFile paramApplicationFile)
/*     */     throws IOException
/*     */   {
/* 231 */     return paramApplicationFile.openDocument(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean openWith(File paramFile)
/*     */     throws IOException
/*     */   {
/* 243 */     return openWith(new ApplicationFile(paramFile));
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
/*     */   public void setMacType(String paramString)
/*     */     throws IOException
/*     */   {
/* 257 */     this.macType = paramString;
/* 258 */     MRJAdapter.setFileType(this.file, paramString);
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
/*     */   public String getMacType()
/*     */     throws IOException
/*     */   {
/* 272 */     String str = MRJAdapter.getFileType(this.file);
/* 273 */     if ((str.length() == 0) && (this.macType != null))
/* 274 */       return this.macType;
/* 275 */     return str;
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
/*     */   public void setMacCreator(String paramString)
/*     */     throws IOException
/*     */   {
/* 289 */     this.macCreator = paramString;
/* 290 */     MRJAdapter.setFileCreator(this.file, paramString);
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
/*     */   public String getMacCreator()
/*     */     throws IOException
/*     */   {
/* 304 */     String str = MRJAdapter.getFileCreator(this.file);
/* 305 */     if ((str.length() == 0) && (this.macCreator != null))
/* 306 */       return this.macCreator;
/* 307 */     return str;
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
/*     */   public void setMacCreatorAndType(String paramString1, String paramString2)
/*     */     throws IOException
/*     */   {
/* 322 */     this.macCreator = paramString1;
/* 323 */     this.macType = paramString2;
/* 324 */     MRJAdapter.setFileCreatorAndType(this.file, paramString1, paramString2);
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
/*     */   public void setExtension(String paramString)
/*     */     throws IOException
/*     */   {
/* 340 */     StringBuffer localStringBuffer = new StringBuffer();
/* 341 */     localStringBuffer.append(getTitle());
/* 342 */     if ((paramString != null) && (paramString.length() > 0))
/*     */     {
/* 344 */       localStringBuffer.append('.');
/* 345 */       localStringBuffer.append(paramString);
/*     */     }
/* 347 */     File localFile = new File(this.file.getParent(), localStringBuffer.toString());
/* 348 */     if (!this.file.renameTo(localFile))
/* 349 */       throw new IOException("failed to rename file");
/* 350 */     this.file = localFile;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getExtension()
/*     */     throws IOException
/*     */   {
/* 363 */     String str = this.file.getName();
/* 364 */     int i = str.lastIndexOf('.');
/* 365 */     if ((i != -1) && (i + 1 != str.length()))
/* 366 */       return str.substring(i + 1);
/* 367 */     return "";
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
/*     */   public void setTitle(String paramString)
/*     */     throws IOException
/*     */   {
/* 384 */     if ((paramString == null) || (paramString.length() == 0))
/* 385 */       throw new IllegalArgumentException("title can't be null or zero length");
/* 386 */     StringBuffer localStringBuffer = new StringBuffer();
/* 387 */     localStringBuffer.append(paramString);
/* 388 */     String str = getExtension();
/* 389 */     if ((str != null) && (str.length() > 0))
/*     */     {
/* 391 */       localStringBuffer.append('.');
/* 392 */       localStringBuffer.append(str);
/*     */     }
/* 394 */     File localFile = new File(this.file.getParent(), localStringBuffer.toString());
/* 395 */     if (!this.file.renameTo(localFile))
/* 396 */       throw new IOException("failed to rename file");
/* 397 */     this.file = localFile;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getTitle()
/*     */     throws IOException
/*     */   {
/* 408 */     String str = this.file.getName();
/* 409 */     int i = str.lastIndexOf('.');
/* 410 */     if ((i != -1) && (i != 0) && (i + 1 != str.length()))
/* 411 */       return str.substring(0, i);
/* 412 */     return str;
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
/*     */   public void setTitleAndExtension(String paramString1, String paramString2)
/*     */     throws IOException
/*     */   {
/* 427 */     if ((paramString1 == null) || (paramString1.length() == 0))
/* 428 */       throw new IllegalArgumentException("title can't be null or zero length");
/* 429 */     StringBuffer localStringBuffer = new StringBuffer();
/* 430 */     localStringBuffer.append(paramString1);
/* 431 */     if ((paramString2 != null) && (paramString2.length() > 0))
/*     */     {
/* 433 */       localStringBuffer.append('.');
/* 434 */       localStringBuffer.append(paramString2);
/*     */     }
/* 436 */     File localFile = new File(this.file.getParent(), localStringBuffer.toString());
/* 437 */     if (!this.file.renameTo(localFile))
/* 438 */       throw new IOException("failed to rename file");
/* 439 */     this.file = localFile;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public File getFile()
/*     */   {
/* 448 */     return this.file;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getPath()
/*     */   {
/* 458 */     return this.file.getPath();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getAbsolutePath()
/*     */   {
/* 468 */     return this.file.getAbsolutePath();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getCanonicalPath()
/*     */     throws IOException
/*     */   {
/* 478 */     return this.file.getCanonicalPath();
/*     */   }
/*     */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\net\roydesign\io\DocumentFile.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */