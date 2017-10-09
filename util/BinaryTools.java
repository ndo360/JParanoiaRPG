/*    */ package util;
/*    */ 
/*    */ 
/*    */ public abstract class BinaryTools
/*    */ {
/*    */   public static String format(int paramInt)
/*    */   {
/*  8 */     String str = Integer.toBinaryString(paramInt);
/*  9 */     while (str.length() < 32) str = "0" + str;
/* 10 */     str = str.substring(0, 8) + " " + str.substring(8, 16) + " " + str.substring(16, 24) + " " + str.substring(24);
/*    */     
/*    */ 
/* 13 */     return str;
/*    */   }
/*    */   
/*    */ 
/*    */   public static String format(byte paramByte)
/*    */   {
/* 19 */     String str = Integer.toBinaryString(paramByte & 0xFF);
/*    */     
/* 21 */     while (str.length() < 8) { str = "0" + str;
/*    */     }
/* 23 */     return str;
/*    */   }
/*    */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\util\BinaryTools.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */