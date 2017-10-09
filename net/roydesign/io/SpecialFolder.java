/*     */ package net.roydesign.io;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
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
/*     */ public class SpecialFolder
/*     */ {
/*  49 */   private static final String osName = System.getProperty("os.name");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static File getHomeFolder()
/*     */   {
/*  65 */     return new File(System.getProperty("user.home"));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static File getPreferencesFolder()
/*     */     throws FileNotFoundException
/*     */   {
/*  75 */     if (MRJAdapter.mrjVersion != -1.0F)
/*     */     {
/*  77 */       return MRJAdapter.findFolder((short)32773, 1886545254, true);
/*     */     }
/*     */     
/*  80 */     if (osName.startsWith("Windows"))
/*     */     {
/*  82 */       return new File(System.getProperty("user.home"), "Application Data");
/*     */     }
/*  84 */     throw new FileNotFoundException();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static File getTemporaryItemsFolder()
/*     */     throws FileNotFoundException
/*     */   {
/*  94 */     if (MRJAdapter.mrjVersion != -1.0F)
/*     */     {
/*  96 */       return MRJAdapter.findFolder((short)32773, 1952804208, true);
/*     */     }
/*     */     
/*  99 */     if (osName.startsWith("Windows"))
/*     */     {
/* 101 */       return new File("c:\temp\"");
/*     */     }
/*     */     
/* 104 */     throw new FileNotFoundException();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static File getDesktopFolder()
/*     */     throws FileNotFoundException
/*     */   {
/* 114 */     if (MRJAdapter.mrjVersion != -1.0F)
/*     */     {
/* 116 */       return MRJAdapter.findFolder((short)32773, 1684370283, true);
/*     */     }
/*     */     
/* 119 */     if (osName.startsWith("Windows"))
/*     */     {
/* 121 */       return new File(System.getProperty("user.home"), "Desktop");
/*     */     }
/* 123 */     throw new FileNotFoundException();
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
/*     */   public static File findMacFolder(short paramShort, String paramString, boolean paramBoolean)
/*     */     throws FileNotFoundException
/*     */   {
/* 139 */     return MRJAdapter.findFolder(paramShort, paramString, paramBoolean);
/*     */   }
/*     */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\net\roydesign\io\SpecialFolder.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */