/*    */ package util;
/*    */ 
/*    */ public class VersionNumber implements Comparable
/*    */ {
/*    */   private int major;
/*    */   private int minor;
/*    */   private int patch;
/*    */   
/*    */   public VersionNumber(int paramInt1, int paramInt2, int paramInt3) {
/* 10 */     this.major = paramInt1;
/* 11 */     this.minor = paramInt2;
/* 12 */     this.patch = paramInt3;
/*    */   }
/*    */   
/*    */   public int getMajor()
/*    */   {
/* 17 */     return this.major;
/*    */   }
/*    */   
/*    */   public int getMinor()
/*    */   {
/* 22 */     return this.minor;
/*    */   }
/*    */   
/*    */   public int getPatch()
/*    */   {
/* 27 */     return this.patch;
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 32 */     return Integer.toString(this.major) + "." + Integer.toString(this.minor) + "." + Integer.toString(this.patch);
/*    */   }
/*    */   
/*    */   public boolean equals(Object paramObject)
/*    */   {
/* 37 */     VersionNumber localVersionNumber = (VersionNumber)paramObject;
/*    */     
/* 39 */     return toString().equals(localVersionNumber.toString());
/*    */   }
/*    */   
/*    */   public int compareTo(Object paramObject)
/*    */   {
/* 44 */     VersionNumber localVersionNumber = (VersionNumber)paramObject;
/*    */     
/* 46 */     if (this.major < localVersionNumber.major) return -1;
/* 47 */     if (this.major > localVersionNumber.major) { return 1;
/*    */     }
/*    */     
/* 50 */     if (this.minor < localVersionNumber.minor) return -1;
/* 51 */     if (this.minor > localVersionNumber.minor) { return 1;
/*    */     }
/*    */     
/* 54 */     if (this.patch < localVersionNumber.patch) return -1;
/* 55 */     if (this.patch > localVersionNumber.patch) { return 1;
/*    */     }
/* 57 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\util\VersionNumber.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */