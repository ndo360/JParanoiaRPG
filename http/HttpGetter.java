/*    */ package http;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStreamReader;
/*    */ import java.net.URL;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HttpGetter
/*    */ {
/*    */   public static BufferedReader getFromURL(URL paramURL)
/*    */     throws IOException
/*    */   {
/* 17 */     return new BufferedReader(new InputStreamReader(paramURL.openStream()));
/*    */   }
/*    */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\http\HttpGetter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */