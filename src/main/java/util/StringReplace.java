/*    */ package util;
/*    */ 
/*    */ public class StringReplace
/*    */ {
/*    */   public static String replaceStr(String paramString1, String paramString2, String paramString3)
/*    */   {
/*  7 */     int i = paramString2.length();
/*  8 */     int j = paramString3.length();
/*    */     
/* 10 */     int k = 0;
/*    */     
/*    */ 
/*    */ 
/* 14 */     while ((k = paramString1.indexOf(paramString2)) != -1)
/*    */     {
/* 16 */       paramString1 = paramString1.substring(0, k) + paramString3 + paramString1.substring(k + i);
/*    */     }
/*    */     
/* 19 */     return paramString1;
/*    */   }
/*    */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\util\StringReplace.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */