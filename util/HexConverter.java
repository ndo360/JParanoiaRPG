/*    */ package util;
/*    */ 
/*    */ import java.awt.Color;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class HexConverter
/*    */ {
/*    */   public static String hexValue(int paramInt)
/*    */   {
/* 17 */     String str = "";
/*    */     
/* 19 */     int i = paramInt / 16;
/* 20 */     int j = paramInt % 16;
/*    */     
/*    */ 
/*    */ 
/*    */ 
/* 25 */     if (j < 10) str = Integer.toString(j); else {
/* 26 */       switch (j) {
/* 27 */       case 10:  str = "A"; break;
/* 28 */       case 11:  str = "B"; break;
/* 29 */       case 12:  str = "C"; break;
/* 30 */       case 13:  str = "D"; break;
/* 31 */       case 14:  str = "E"; break;
/* 32 */       case 15:  str = "F";
/*    */       }
/*    */     }
/* 35 */     if (i > 9) return hexValue(i) + str;
/* 36 */     if (i > 0) { return Integer.toString(i) + str;
/*    */     }
/* 38 */     return str;
/*    */   }
/*    */   
/*    */   public static String hexValue(Color paramColor)
/*    */   {
/* 43 */     String str1 = "";
/*    */     
/* 45 */     String str2 = hexValue(paramColor.getRed());
/* 46 */     if (str2.length() < 2) str2 = "0" + str2;
/* 47 */     str1 = str1 + str2;
/*    */     
/* 49 */     str2 = hexValue(paramColor.getGreen());
/* 50 */     if (str2.length() < 2) str2 = "0" + str2;
/* 51 */     str1 = str1 + str2;
/*    */     
/* 53 */     str2 = hexValue(paramColor.getBlue());
/* 54 */     if (str2.length() < 2) str2 = "0" + str2;
/* 55 */     str1 = str1 + str2;
/*    */     
/* 57 */     return str1;
/*    */   }
/*    */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\util\HexConverter.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */