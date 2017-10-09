/*    */ package util;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.IOException;
/*    */ import java.io.StringReader;
/*    */ 
/*    */ 
/*    */ public class TagTosser
/*    */ {
/*    */   static BufferedReader reader;
/* 11 */   static String spareString = "";
/* 12 */   static String source = "";
/*    */   
/*    */   public static BufferedReader tossTags(BufferedReader paramBufferedReader)
/*    */   {
/* 16 */     reader = paramBufferedReader;
/*    */     
/* 18 */     String str1 = "";
/*    */     
/* 20 */     String str2 = "";
/*    */     
/* 22 */     int i = 0;
/*    */     try
/*    */     {
/* 25 */       while ((str2 = reader.readLine()) != null)
/*    */       {
/* 27 */         source = source.concat(str2 + "\n");
/* 28 */         i++;
/*    */       }
/*    */     }
/*    */     catch (IOException localIOException) {
/* 32 */       localIOException.printStackTrace();
/*    */     }
/* 34 */     str1 = stripTags(source);
/*    */     
/*    */ 
/*    */ 
/*    */ 
/* 39 */     return new BufferedReader(new StringReader(str1));
/*    */   }
/*    */   
/*    */   private static String stripTags(String paramString)
/*    */   {
/* 44 */     String str1 = "<head>(?:[\\s\\S]*?)[[(</head>)][(</HEAD>)]]";
/* 45 */     String str2 = "<script(?:[\\s\\S]*?)</script>";
/* 46 */     String str3 = "<!--(?:[\\s\\S]*?)-->";
/*    */     
/* 48 */     String str4 = "<(?:[[\\s\\S]&&[^>]]*)>";
/*    */     
/* 50 */     String str5 = "(?:\\s*\\n)+";
/* 51 */     String str6 = "\\t";
/* 52 */     String str7 = "(?:[[\\s]&&[^\\n]][[\\s]&&[^\\n]]+)";
/* 53 */     String str8 = "&nbsp;";
/* 54 */     String str9 = "&amp;";
/*    */     
/* 56 */     String str10 = paramString;
/*    */     
/* 58 */     str10 = str10.replaceAll(str1, "[head tag removed]");
/* 59 */     str10 = str10.replaceAll(str2, "[script tag removed]");
/* 60 */     str10 = str10.replaceAll(str3, "");
/* 61 */     str10 = str10.replaceAll(str4, "");
/*    */     
/* 63 */     str10 = str10.replaceAll(str8, " ");
/* 64 */     str10 = str10.replaceAll(str9, "&");
/*    */     
/* 66 */     str10 = str10.replaceAll(str5, "\n");
/* 67 */     str10 = str10.replaceAll(str6, "");
/* 68 */     str10 = str10.replaceAll(str7, "");
/*    */     
/* 70 */     return str10;
/*    */   }
/*    */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\util\TagTosser.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */