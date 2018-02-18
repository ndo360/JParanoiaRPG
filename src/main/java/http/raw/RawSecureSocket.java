/*    */ package http.raw;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.InputStreamReader;
/*    */ import java.io.PrintWriter;
/*    */ import javax.net.ssl.SSLSocket;
/*    */ import javax.net.ssl.SSLSocketFactory;
/*    */ 
/*    */ public class RawSecureSocket
/*    */ {
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/*    */     try
/*    */     {
/* 15 */       SSLSocketFactory localSSLSocketFactory = (SSLSocketFactory)SSLSocketFactory.getDefault();
/*    */       
/* 17 */       SSLSocket localSSLSocket = (SSLSocket)localSSLSocketFactory.createSocket("utdirect.utexas.edu", 443);
/*    */       
/*    */ 
/* 20 */       PrintWriter localPrintWriter = new PrintWriter(localSSLSocket.getOutputStream(), true);
/*    */       
/* 22 */       BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(localSSLSocket.getInputStream()));
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
/* 39 */       localPrintWriter.println("POST /security-443/logon_check.logonform HTTP/1.1");
/* 40 */       localPrintWriter.println("Accept: image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, */*");
/* 41 */       localPrintWriter.println("User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");
/* 42 */       localPrintWriter.println("Referer: https://utdirect.utexas.edu/");
/* 43 */       localPrintWriter.println("Content-Type: application/x-www-form-urlencoded");
/* 44 */       localPrintWriter.println("Host: 127.0.0.1");
/* 45 */       localPrintWriter.println("Content-Length: 68");
/* 46 */       localPrintWriter.println("Connection: Keep-Alive");
/* 47 */       localPrintWriter.println();
/* 48 */       localPrintWriter.println("LOGON=buried&PASSWORDS=wdml1299&NEW_PASSWORD=&CONFIRM_NEW_PASSWORD=&");
/*    */       
/*    */ 
/*    */       String str;
/*    */       
/*    */ 
/* 54 */       while ((str = localBufferedReader.readLine()) != null) {
/* 55 */         System.out.println(str);
/*    */       }
/*    */     }
/*    */     catch (Exception localException) {
/* 59 */       localException.printStackTrace();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\http\raw\RawSecureSocket.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */