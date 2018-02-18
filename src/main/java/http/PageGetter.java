/*    */ package http;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.net.URL;
/*    */ 
/*    */ public class PageGetter
/*    */ {
/*    */   static BufferedReader reader;
/*  9 */   static String line = "";
/*    */   
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/* 13 */     getPage("http://127.0.0.1/hi.html");
/*    */   }
/*    */   
/*    */ 
/*    */   public static void getPage(String paramString)
/*    */   {
/*    */     try
/*    */     {
/* 21 */       reader = new BufferedReader(HttpGetter.getFromURL(new URL(paramString)));
/*    */       
/* 23 */       while (line != null)
/*    */       {
/* 25 */         line = reader.readLine();
/*    */         
/* 27 */         if (line != null) System.out.println(line);
/*    */       }
/*    */     } catch (Exception localException) {
/* 30 */       localException.printStackTrace();System.exit(-1);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\http\PageGetter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */