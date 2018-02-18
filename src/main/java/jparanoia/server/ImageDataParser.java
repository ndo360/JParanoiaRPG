/*    */ package jparanoia.server;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.FileReader;
/*    */ import java.io.PrintStream;
/*    */ import java.net.URL;
/*    */ import java.util.ArrayList;
/*    */ import java.util.StringTokenizer;
/*    */ import jparanoia.shared.JPImage;
/*    */ 
/*    */ public class ImageDataParser
/*    */ {
/*    */   BufferedReader reader;
/*    */   String input;
/*    */   String name;
/*    */   StringTokenizer st;
/* 17 */   ArrayList imageInfo = new ArrayList(20);
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void parseImageURLs(String paramString)
/*    */   {
/*    */     try
/*    */     {
/* 28 */       this.reader = new BufferedReader(new FileReader(paramString));
/* 29 */       this.input = this.reader.readLine();
/* 30 */       while (this.input != null)
/*    */       {
/*    */ 
/* 33 */         if (!this.input.startsWith("#"))
/*    */         {
/*    */ 
/* 36 */           this.st = new StringTokenizer(this.input, "|");
/*    */           
/* 38 */           while (this.st.hasMoreTokens())
/*    */           {
/* 40 */             String str = this.st.nextToken();
/* 41 */             System.out.println("     Image: " + str);
/* 42 */             this.imageInfo.add(new JPImage(str, new URL(this.st.nextToken())));
/*    */           }
/*    */         }
/* 45 */         this.input = this.reader.readLine();
/*    */       }
/*    */     } catch (Exception localException) {
/* 48 */       localException.printStackTrace();
/*    */     }
/*    */   }
/*    */   
/*    */   public ArrayList getImageInfo() {
/* 53 */     return this.imageInfo;
/*    */   }
/*    */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\ImageDataParser.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */