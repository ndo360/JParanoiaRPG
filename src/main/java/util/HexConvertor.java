/*    */ package util;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class HexConvertor
/*    */ {
/*    */   public static String hexValue(int paramInt)
/*    */   {
/* 15 */     String str = "";
/*    */     
/* 17 */     int i = paramInt / 16;
/* 18 */     int j = paramInt % 16;
/*    */     
/* 20 */     if (j < 10) str = Integer.toString(j); else {
/* 21 */       switch (j) {
/* 22 */       case 10:  str = "A"; break;
/* 23 */       case 11:  str = "B"; break;
/* 24 */       case 12:  str = "C"; break;
/* 25 */       case 13:  str = "D"; break;
/* 26 */       case 14:  str = "E"; break;
/* 27 */       case 15:  str = "F";
/*    */       }
/*    */     }
/* 30 */     if (i > 10) return hexValue(i) + str;
/* 31 */     if (i > 0) { return Integer.toString(i) + str;
/*    */     }
/* 33 */     return str;
/*    */   }
/*    */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\util\HexConvertor.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */