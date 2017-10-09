/*    */ package jparanoia.shared;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintWriter;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class GameLogger extends JPLogger
/*    */ {
/* 11 */   private boolean html = false;
/*    */   
/*    */ 
/*    */   public GameLogger()
/*    */   {
/* 16 */     this.logName = ("logs/uv_" + dateFormat.format(new Date()) + ".txt");
/* 17 */     createLogFile();
/*    */   }
/*    */   
/*    */   public GameLogger(JPPlayer[] paramArrayOfJPPlayer)
/*    */   {
/* 22 */     this.html = true;
/*    */     
/* 24 */     this.logName = ("logs/uv_" + dateFormat.format(new Date()) + ".html");
/* 25 */     createLogFile();
/*    */     
/* 27 */     logEntry("<html>\n<head>\n<title>JParanoia Game Log - " + humanDateFormat.format(new Date()) + "</title>\n" + "<style type=\"text/css\" media=\"screen\">\n" + "<!--\n" + "body { background-color: black; background: black; color: #FFFFFF; font-family: sans-serif }\n" + "\n" + ".pm { margin-left: 5%; margin-right: 5% }\n" + "\n" + ".gmText { font-weight: bold }\n" + "\n" + ".player0 { color: #" + util.HexConverter.hexValue(paramArrayOfJPPlayer[0].getChatColor()) + "; font-weight: bold }");
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
/* 39 */     for (int i = 1; i < paramArrayOfJPPlayer.length; i++)
/*    */     {
/* 41 */       logEntry(".player" + i + " { color: #" + util.HexConverter.hexValue(paramArrayOfJPPlayer[i].getChatColor()) + " }");
/*    */     }
/*    */     
/* 44 */     logEntry("\n.observer { color: #666666 }\n\n.computer { font-size: 180% }\n\n.gray { color: #999999 }\n.note { color: #FFFF00 }\n\nA { background: black }\nA:link { color: #FFFFFF }\nA:visited { color: #CCCCCC }\n-->\n</style>\n</head>\n<body>");
/*    */   }
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
/*    */   public void closeLog()
/*    */   {
/* 61 */     if (this.html)
/*    */     {
/* 63 */       logEntry("</body>\n</html>\n");
/*    */     }
/*    */     
/*    */ 
/*    */ 
/* 68 */     super.closeLog();
/*    */   }
/*    */   
/*    */   public void sanitize()
/*    */   {
/*    */     try
/*    */     {
/* 75 */       BufferedReader localBufferedReader = new BufferedReader(new java.io.FileReader(this.logFile));
/*    */       
/*    */ 
/* 78 */       String str1 = this.logFile.getName();
/*    */       
/* 80 */       if (str1.startsWith("uv_")) { str1 = str1.substring(3);
/*    */       }
/* 82 */       String str2 = this.logFile.getParent() + java.io.File.separator + "red_" + str1;
/*    */       
/*    */ 
/* 85 */       PrintWriter localPrintWriter = new PrintWriter(new java.io.FileWriter(str2));
/*    */       
/*    */ 
/*    */       String str3;
/*    */       
/* 90 */       while ((str3 = localBufferedReader.readLine()) != null) {
/* 91 */         if (!str3.startsWith("<span class=\"pm\">")) localPrintWriter.println(str3);
/*    */       }
/* 93 */       localPrintWriter.flush();
/* 94 */       localPrintWriter.close();
/*    */     }
/*    */     catch (IOException localIOException) {
/* 97 */       localIOException.printStackTrace();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\shared\GameLogger.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */