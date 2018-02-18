/*     */ package net.roydesign.event;
/*     */ 
/*     */ import java.awt.event.ActionEvent;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ApplicationEvent
/*     */   extends ActionEvent
/*     */ {
/*     */   public static final int ABOUT = 1;
/*     */   public static final int PREFERENCES = 2;
/*     */   public static final int OPEN_APPLICATION = 3;
/*     */   public static final int QUIT_APPLICATION = 4;
/*     */   public static final int OPEN_DOCUMENT = 5;
/*     */   public static final int PRINT_DOCUMENT = 6;
/*     */   public static final int REOPEN_APPLICATION = 7;
/*     */   private int type;
/*     */   private File file;
/*     */   
/*     */   public ApplicationEvent(Object paramObject, int paramInt)
/*     */   {
/* 126 */     this(paramObject, paramInt, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ApplicationEvent(Object paramObject, int paramInt, File paramFile)
/*     */   {
/* 136 */     super(paramObject, 1001, "", 0);
/* 137 */     switch (paramInt)
/*     */     {
/*     */     case 1: 
/*     */     case 2: 
/*     */     case 3: 
/*     */     case 4: 
/*     */     case 7: 
/* 144 */       if (paramFile != null) {
/* 145 */         throw new IllegalArgumentException("adapter event ID can't include a file");
/*     */       }
/*     */       break;
/*     */     case 5: case 6: 
/* 149 */       if (paramFile == null)
/* 150 */         throw new IllegalArgumentException("adapter event ID requires a file");
/*     */       break;
/*     */     }
/* 153 */     this.type = paramInt;
/* 154 */     this.file = paramFile;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getType()
/*     */   {
/* 163 */     return this.type;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public File getFile()
/*     */   {
/* 172 */     return this.file;
/*     */   }
/*     */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\net\roydesign\event\ApplicationEvent.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */