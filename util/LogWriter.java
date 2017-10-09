/*    */ package util;
/*    */ 
/*    */ import java.io.PrintWriter;
/*    */ 
/*    */ public class LogWriter
/*    */ {
/*    */   PrintWriter out;
/*    */   
/*    */   public LogWriter(String paramString)
/*    */   {
/*    */     try
/*    */     {
/* 13 */       this.out = new PrintWriter(new java.io.FileWriter(paramString, true), true);
/*    */ 
/*    */     }
/*    */     catch (java.io.IOException localIOException)
/*    */     {
/* 18 */       localIOException.printStackTrace();
/*    */     }
/*    */   }
/*    */   
/*    */   public void logEntry(String paramString)
/*    */   {
/* 24 */     this.out.println(paramString);
/*    */   }
/*    */   
/*    */   public void close()
/*    */   {
/* 29 */     this.out.close();
/*    */   }
/*    */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\util\LogWriter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */