/*    */ package jparanoia.server;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class ServerSocketThread extends Thread
/*    */ {
/*  8 */   java.net.ServerSocket serverSocket = null;
/*    */   java.net.Socket someSock;
/* 10 */   public boolean listening = true;
/* 11 */   final int PORT_NUMBER = 11777;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void run()
/*    */   {
/* 19 */     JPServer.stopServerMenuItem.setEnabled(true);
/* 20 */     JPServer.startServerMenuItem.setEnabled(false);
/*    */     
/*    */     try
/*    */     {
/* 24 */       this.serverSocket = new java.net.ServerSocket(11777);
/*    */ 
/*    */     }
/*    */     catch (IOException localIOException1)
/*    */     {
/* 29 */       System.out.println("Error starting server: could not listen on port: 11777");
/* 30 */       JPServer.absoluteChat("Error starting server: could not listen on port: 11777");
/* 31 */       JPServer.absoluteChat("Likely cause: another instance of server is currently running.");
/* 32 */       return;
/*    */     }
/*    */     
/* 35 */     System.out.println("ServerSocket established and listening on port 11777");
/* 36 */     JPServer.absoluteChat("\nServer started and listening.");
/*    */     
/* 38 */     if (JPServer.showTimeStamps) { JPServer.displayTimeStamp();
/*    */     }
/* 40 */     JPServer.serverRunning = true;
/*    */     
/*    */     try
/*    */     {
/* 44 */       while (this.listening)
/*    */       {
/* 46 */         this.someSock = this.serverSocket.accept();
/* 47 */         System.out.print("New connection accepted... ");
/*    */         
/*    */ 
/*    */ 
/* 51 */         new ServerChatThread(this.someSock).start();
/*    */       }
/*    */       
/*    */ 
/*    */     }
/*    */     catch (java.net.SocketException localSocketException)
/*    */     {
/*    */ 
/* 59 */       if (this.listening) JPServer.absoluteChat("ServerSocket closed by outside force..."); else {
/* 60 */         JPServer.absoluteChat("ServerSocket closed by user (that's you).");
/*    */       }
/*    */     }
/*    */     catch (IOException localIOException2)
/*    */     {
/* 65 */       System.err.println("Error: unhandled I/O exception");
/* 66 */       localIOException2.printStackTrace();
/*    */     }
/*    */     
/* 69 */     JPServer.stopServerMenuItem.setEnabled(false);
/* 70 */     JPServer.startServerMenuItem.setEnabled(true);
/*    */     
/* 72 */     System.out.println("ServerSocket closed, no longer listening on 11777");
/* 73 */     JPServer.absoluteChat("Server no longer listening for new connections.");
/* 74 */     if (JPServer.showTimeStamps) JPServer.displayTimeStamp();
/*    */   }
/*    */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\ServerSocketThread.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */