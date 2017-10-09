/*    */ package http;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.PrintStream;
/*    */ import java.net.URL;
/*    */ import java.util.Map;
/*    */ import javax.net.ssl.HttpsURLConnection;
/*    */ 
/*    */ public class GetSecure
/*    */ {
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/*    */     try
/*    */     {
/* 15 */       HttpsURLConnection localHttpsURLConnection = null;
/*    */       
/* 17 */       URL localURL = null;
/*    */       try
/*    */       {
/* 20 */         localURL = new URL("https://utdirect.utexas.edu/");
/*    */       }
/*    */       catch (Exception localException2) {
/* 23 */         localException2.printStackTrace();System.exit(0);
/*    */       }
/* 25 */       try { localHttpsURLConnection = (HttpsURLConnection)localURL.openConnection();
/* 26 */       } catch (Exception localException3) { localException3.printStackTrace();System.exit(0);
/*    */       }
/* 28 */       localHttpsURLConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
/*    */       
/*    */ 
/*    */ 
/* 32 */       localHttpsURLConnection.setInstanceFollowRedirects(true);
/*    */       
/*    */ 
/*    */ 
/*    */ 
/* 37 */       localHttpsURLConnection.setRequestMethod("GET");
/*    */       
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/* 53 */       Map localMap = localHttpsURLConnection.getHeaderFields();
/*    */       
/* 55 */       java.util.Set localSet = localMap.keySet();
/*    */       
/* 57 */       Object[] arrayOfObject = localSet.toArray();
/*    */       
/* 59 */       for (int i = 0; i < arrayOfObject.length; i++) {
/* 60 */         System.out.println("<< " + arrayOfObject[i] + ": " + localMap.get(arrayOfObject[i]).toString() + " >>");
/*    */       }
/*    */       
/*    */ 
/*    */ 
/*    */ 
/* 66 */       BufferedReader localBufferedReader = util.TagTosser.tossTags(new BufferedReader(new java.io.InputStreamReader(localHttpsURLConnection.getInputStream())));
/*    */       
/*    */ 
/*    */       String str;
/*    */       
/* 71 */       while ((str = localBufferedReader.readLine()) != null) {
/* 72 */         System.out.println(str);
/*    */       }
/*    */     }
/*    */     catch (Exception localException1) {
/* 76 */       localException1.printStackTrace();System.exit(0);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\http\GetSecure.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */