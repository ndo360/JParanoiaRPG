/*    */ package jparanoia.shared.http;
/*    */ 
/*    */ import http.HttpPoster;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.IOException;
/*    */ 
/*    */ public class GameRegistrar
/*    */ {
/*    */   public static final String REGISTRY_URL_STRING = "http://www.byronbarry.com/jparanoia/game_registrar.html";
/* 10 */   public static String formattedDesc = "";
/*    */   
/*    */   public static void addGame(String paramString)
/*    */   {
/* 14 */     formattedDesc = paramString.replace(' ', '+');
/*    */     
/*    */ 
/*    */     try
/*    */     {
/* 19 */       BufferedReader localBufferedReader = HttpPoster.postToURL("http://www.byronbarry.com/jparanoia/game_registrar.html", "description=" + formattedDesc);
/*    */       
/*    */       String str;
/*    */       
/* 23 */       while ((str = localBufferedReader.readLine()) != null)
/* 24 */         System.out.println(str);
/* 25 */       localBufferedReader.close();
/*    */     } catch (IOException localIOException) {
/* 27 */       localIOException.printStackTrace();
/*    */     }
/*    */   }
/*    */   
/*    */   public static void removeGame()
/*    */   {
/*    */     try
/*    */     {
/* 35 */       BufferedReader localBufferedReader = HttpPoster.postToURL("http://www.byronbarry.com/jparanoia/game_registrar.html", "erase=1");
/*    */       
/*    */       String str;
/*    */       
/* 39 */       while ((str = localBufferedReader.readLine()) != null)
/* 40 */         System.out.println(str);
/* 41 */       localBufferedReader.close();
/*    */     } catch (IOException localIOException) {
/* 43 */       localIOException.printStackTrace();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\shared\http\GameRegistrar.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */