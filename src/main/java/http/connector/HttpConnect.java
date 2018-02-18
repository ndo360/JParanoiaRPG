/*     */ package http.connector;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URL;
/*     */ import util.StreamListenerThread;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HttpConnect
/*     */ {
/*     */   static int method;
/*     */   static final int GET = 0;
/*     */   static final int POST = 1;
/*     */   static final int REG_POST = 2;
/*     */   static final int IE_POST = 3;
/*     */   static final int REAL_POST = 4;
/*     */   static HttpURLConnection connecto;
/*     */   static StreamListenerThread slt;
/*     */   
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/*     */     try
/*     */     {
/*  34 */       connecto = (HttpURLConnection)new URL("http://127.0.0.1/formyform.html").openConnection();
/*  35 */       connecto.setDoOutput(true);
/*     */ 
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*     */ 
/*  41 */       System.out.println("Aww, exception.");
/*  42 */       localException.printStackTrace();
/*     */     }
/*     */     
/*     */ 
/*  46 */     doStuff();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void doStuff()
/*     */   {
/*     */     try
/*     */     {
/*  77 */       String str = "description=foop";
/*     */       
/*     */ 
/*  80 */       System.out.println("Connected. Sending request...");
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*  85 */       method = 4;
/*     */       
/*  87 */       switch (method)
/*     */       {
/*     */ 
/*     */ 
/*     */       case 4: 
/*  92 */         connecto.setRequestMethod("POST");
/*     */         
/*  94 */         localObject = new PrintWriter(connecto.getOutputStream(), true);
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 100 */         ((PrintWriter)localObject).println(str);
/* 101 */         ((PrintWriter)localObject).close();
/*     */         
/* 103 */         slt = new StreamListenerThread(connecto.getInputStream());
/* 104 */         slt.start();
/*     */       }
/*     */       
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 144 */       System.out.println("Request sent. Waiting for reply...");
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 154 */       Object localObject = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 168 */       localException.printStackTrace();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\http\connector\HttpConnect.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */