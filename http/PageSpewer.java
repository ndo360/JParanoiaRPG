/*    */ package http;
/*    */ 
/*    */ import java.io.PrintWriter;
/*    */ 
/*    */ public class PageSpewer
/*    */ {
/*    */   static java.io.BufferedReader reader;
/*  8 */   static String line = "";
/*    */   
/*    */ 
/*    */   public static void spewPage(String paramString, PrintWriter paramPrintWriter)
/*    */   {
/*    */     try
/*    */     {
/* 15 */       reader = new java.io.BufferedReader(new java.io.FileReader(paramString));
/*    */       
/* 17 */       while (line != null)
/*    */       {
/* 19 */         line = reader.readLine();
/*    */         
/* 21 */         if (line != null) paramPrintWriter.println(line);
/*    */       }
/*    */     } catch (Exception localException) {
/* 24 */       localException.printStackTrace();System.exit(-1);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\http\PageSpewer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */