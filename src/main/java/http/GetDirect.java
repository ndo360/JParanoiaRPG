/*    */ package http;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.net.URL;
/*    */ import java.util.Map;
/*    */ import javax.net.ssl.HttpsURLConnection;
/*    */ 
/*    */ public class GetDirect
/*    */ {
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/*    */     try
/*    */     {
/* 14 */       HttpsURLConnection localHttpsURLConnection = null;
/*    */       
/* 16 */       URL localURL = null;
/*    */       
/*    */       try
/*    */       {
/* 20 */         localURL = new URL("https://utdirect.utexas.edu/security-443/UTEIDLogon.wb");
/*    */       } catch (Exception localException2) {
/* 22 */         localException2.printStackTrace();System.exit(0);
/*    */       }
/* 24 */       try { localHttpsURLConnection = (HttpsURLConnection)localURL.openConnection();
/* 25 */       } catch (Exception localException3) { localException3.printStackTrace();System.exit(0);
/*    */       }
/* 27 */       localHttpsURLConnection.setInstanceFollowRedirects(false);
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
/*    */ 
/*    */ 
/* 45 */       Map localMap = localHttpsURLConnection.getHeaderFields();
/*    */       
/* 47 */       java.util.Set localSet = localMap.keySet();
/*    */       
/* 49 */       Object[] arrayOfObject = localSet.toArray();
/*    */       
/* 51 */       for (int i = 0; i < arrayOfObject.length; i++) {
/* 52 */         System.out.println("*** " + arrayOfObject[i] + ": " + localMap.get(arrayOfObject[i]).toString() + " ***");
/*    */       }
/*    */       
/*    */ 
/*    */ 
/* 57 */       BufferedReader localBufferedReader = new BufferedReader(new java.io.InputStreamReader(localHttpsURLConnection.getInputStream()));
/*    */       
/*    */       String str;
/*    */       
/* 61 */       while ((str = localBufferedReader.readLine()) != null) {
/* 62 */         System.out.println(str);
/*    */       }
/*    */       
/*    */     }
/*    */     catch (Exception localException1)
/*    */     {
/* 68 */       localException1.printStackTrace();System.exit(0);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\http\GetDirect.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */