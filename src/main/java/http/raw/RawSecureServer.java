/*    */ package http.raw;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import javax.net.ssl.SSLServerSocket;
/*    */ import javax.net.ssl.SSLServerSocketFactory;
/*    */ import javax.net.ssl.SSLSocket;
/*    */ import javax.net.ssl.SSLSocketFactory;
/*    */ 
/*    */ public class RawSecureServer
/*    */ {
/*    */   public static void main(String[] paramArrayOfString)
/*    */   {
/*    */     try
/*    */     {
/* 15 */       SSLServerSocketFactory localSSLServerSocketFactory = (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
/*    */       
/* 17 */       SSLSocketFactory localSSLSocketFactory = (SSLSocketFactory)SSLSocketFactory.getDefault();
/*    */       
/* 19 */       SSLServerSocket localSSLServerSocket = (SSLServerSocket)localSSLServerSocketFactory.createServerSocket(443);
/*    */       
/* 21 */       System.out.println("Listening on port 443");
/*    */       
/* 23 */       SSLSocket localSSLSocket = (SSLSocket)localSSLServerSocket.accept();
/*    */       
/*    */ 
/* 26 */       java.io.PrintWriter localPrintWriter = new java.io.PrintWriter(localSSLSocket.getOutputStream(), true);
/*    */       
/* 28 */       BufferedReader localBufferedReader = new BufferedReader(new java.io.InputStreamReader(localSSLSocket.getInputStream()));
/*    */       
/*    */ 
/*    */       String str;
/*    */       
/*    */ 
/* 34 */       while ((str = localBufferedReader.readLine()) != null) {
/* 35 */         System.out.println(str);
/*    */       }
/*    */     }
/*    */     catch (Exception localException) {
/* 39 */       localException.printStackTrace();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\http\raw\RawSecureServer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */