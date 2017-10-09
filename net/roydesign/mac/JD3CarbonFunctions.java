/*    */ package net.roydesign.mac;
/*    */ 
/*    */ import com.apple.mrj.jdirect.Linker;
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
/*    */ class JD3CarbonFunctions
/*    */ {
/*    */   public static final String JDirect_MacOSX = "/System/Library/Frameworks/Carbon.framework/Versions/A/Carbon";
/* 45 */   private static final Object linkage = new Linker(JD3CarbonFunctions.class);
/*    */   
/*    */   public static native void EnableMenuCommand(int paramInt1, int paramInt2);
/*    */   
/*    */   public static native void DisableMenuCommand(int paramInt1, int paramInt2);
/*    */   
/*    */   public static native int AEInstallEventHandler(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean);
/*    */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\net\roydesign\mac\JD3CarbonFunctions.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */