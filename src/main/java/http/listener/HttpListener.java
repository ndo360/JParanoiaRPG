/*     */ package http.listener;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.net.InetAddress;
/*     */ import java.net.ServerSocket;
/*     */ import java.net.Socket;
/*     */ 
/*     */ public class HttpListener
/*     */ {
/*     */   static BufferedReader in;
/*     */   static PrintWriter out;
/*     */   static String incomingLine;
/*     */   static Socket socket;
/*     */   static boolean get;
/*     */   
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/*  20 */     ServerSocket localServerSocket = null;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     try
/*     */     {
/*  27 */       localServerSocket = new ServerSocket(80);
/*     */       
/*  29 */       System.out.println("Listening on TCP port 80...");
/*     */       
/*     */ 
/*     */       for (;;)
/*     */       {
/*  34 */         socket = localServerSocket.accept();
/*     */         
/*  36 */         doStuff();
/*     */       }
/*     */       
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*  42 */       System.out.println("Aww, exception.");
/*  43 */       localException.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public static void doStuff()
/*     */   {
/*  49 */     InetAddress localInetAddress = socket.getInetAddress();
/*     */     
/*  51 */     System.out.println("Connection from: " + localInetAddress.getHostAddress() + " aka: " + localInetAddress.getHostName());
/*     */     
/*     */     try
/*     */     {
/*  55 */       out = new PrintWriter(socket.getOutputStream(), true);
/*  56 */       in = new BufferedReader(new java.io.InputStreamReader(socket.getInputStream()));
/*     */       
/*  58 */       incomingLine = "hi there";
/*     */       
/*     */ 
/*  61 */       int i = 1;
/*     */       
/*  63 */       get = false;
/*     */       
/*  65 */       while (i != 0)
/*     */       {
/*     */ 
/*     */ 
/*  69 */         incomingLine = in.readLine();
/*     */         
/*     */ 
/*  72 */         System.out.println(incomingLine + "*");
/*     */         
/*  74 */         if (incomingLine.startsWith("GET")) { get = true;
/*     */         }
/*     */         
/*  77 */         if ((incomingLine.equals("")) && 
/*  78 */           (get))
/*     */         {
/*  80 */           System.out.println("Sending local page...");
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
/*     */ 
/* 120 */           http.PageSpewer.spewPage("C:/web publishing/gameTestForm.html", out);
/*     */           
/*     */ 
/* 123 */           System.out.println("Form sent.");
/*     */           
/* 125 */           i = 0;
/*     */           
/* 127 */           socket.close();
/*     */ 
/*     */         }
/*     */         
/*     */ 
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */     }
/*     */     catch (NullPointerException localNullPointerException)
/*     */     {
/*     */ 
/*     */ 
/* 141 */       System.out.println("Null input.");
/*     */       
/* 143 */       while (incomingLine == null) {
/*     */         try {
/* 145 */           incomingLine = in.readLine();
/*     */         } catch (Exception localException1) {}
/* 147 */         if (incomingLine != null)
/*     */         {
/* 149 */           System.out.println(incomingLine + "*");
/* 150 */           doStuff();
/*     */         }
/*     */         
/*     */       }
/*     */     }
/*     */     catch (Exception localException2)
/*     */     {
/* 157 */       System.out.println("Exception");
/* 158 */       localException2.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public static void sendLinefeed()
/*     */   {
/* 164 */     System.out.println("Sending line feed...");
/*     */     
/* 166 */     out.println();
/*     */   }
/*     */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\http\listener\HttpListener.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */