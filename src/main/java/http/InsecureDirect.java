/*    */ package http;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.PrintWriter;
/*    */ import java.net.HttpURLConnection;
/*    */ import java.net.URL;
/*    */ import java.net.URLEncoder;
/*    */ 
/*    */ public class InsecureDirect
/*    */ {
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/*    */     try
/*    */     {
/* 15 */       HttpURLConnection localHttpURLConnection = null;
/*    */       
/* 17 */       URL localURL = null;
/*    */       
/*    */ 
/*    */       try
/*    */       {
/* 22 */         localURL = new URL("http://127.0.0.1/hi.html");
/* 23 */       } catch (Exception localException2) { localException2.printStackTrace();System.exit(0);
/*    */       }
/*    */       try {
/* 26 */         localHttpURLConnection = (HttpURLConnection)localURL.openConnection();
/* 27 */       } catch (Exception localException3) { localException3.printStackTrace();System.exit(0);
/*    */       }
/* 29 */       localHttpURLConnection.setRequestProperty("User-Agent", "dungo");
/*    */       
/*    */ 
/* 32 */       localHttpURLConnection.setDoOutput(true);
/* 33 */       localHttpURLConnection.setRequestMethod("POST");
/*    */       
/* 35 */       PrintWriter localPrintWriter = new PrintWriter(localHttpURLConnection.getOutputStream(), true);
/*    */       
/* 37 */       String str1 = "LOGON=" + URLEncoder.encode("useruser", "UTF-8");
/* 38 */       String str2 = "PASSWORDS=" + URLEncoder.encode("foobar", "UTF-8");
/*    */       
/* 40 */       String str3 = str1 + "&" + str2 + "&NEW_PASSWORD=&CONFIRM_NEW_PASSWORD=&";
/*    */       
/* 42 */       localPrintWriter.println(str3);
/*    */       
/* 44 */       localPrintWriter.flush();
/* 45 */       localPrintWriter.close();
/*    */       
/*    */ 
/* 48 */       BufferedReader localBufferedReader = new BufferedReader(new java.io.InputStreamReader(localHttpURLConnection.getInputStream()));
/*    */       
/*    */       String str4;
/*    */       
/* 52 */       while ((str4 = localBufferedReader.readLine()) != null) {
/* 53 */         System.out.println(str4);
/*    */       }
/*    */     }
/*    */     catch (Exception localException1) {
/* 57 */       localException1.printStackTrace();System.exit(0);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\http\InsecureDirect.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */