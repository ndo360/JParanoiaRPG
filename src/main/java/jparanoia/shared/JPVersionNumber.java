/*    */ package jparanoia.shared;
/*    */ 
/*    */ import java.util.StringTokenizer;
/*    */ 
/*    */ public class JPVersionNumber implements Comparable {
/*    */   private int major;
/*    */   private int minor;
/*    */   private int patch;
/*  9 */   private boolean isValid = true;
/*    */   
/*    */   public JPVersionNumber(int paramInt1, int paramInt2, int paramInt3)
/*    */   {
/* 13 */     this.major = paramInt1;
/* 14 */     this.minor = paramInt2;
/* 15 */     this.patch = paramInt3;
/*    */   }
/*    */   
/*    */   public JPVersionNumber(String paramString)
/*    */   {
/* 20 */     StringTokenizer localStringTokenizer = new StringTokenizer(paramString, ".");
/*    */     
/* 22 */     if (localStringTokenizer.countTokens() != 3)
/*    */     {
/* 24 */       this.isValid = false;
/*    */ 
/*    */     }
/*    */     else
/*    */     {
/* 29 */       this.major = Integer.parseInt(localStringTokenizer.nextToken());
/* 30 */       this.minor = Integer.parseInt(localStringTokenizer.nextToken());
/* 31 */       this.patch = Integer.parseInt(localStringTokenizer.nextToken());
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean isValid()
/*    */   {
/* 37 */     return this.isValid;
/*    */   }
/*    */   
/*    */   public int getMajor()
/*    */   {
/* 42 */     return this.major;
/*    */   }
/*    */   
/*    */   public int getMinor()
/*    */   {
/* 47 */     return this.minor;
/*    */   }
/*    */   
/*    */   public int getPatch()
/*    */   {
/* 52 */     return this.patch;
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 57 */     return Integer.toString(this.major) + "." + Integer.toString(this.minor) + "." + Integer.toString(this.patch);
/*    */   }
/*    */   
/*    */   public boolean equals(Object paramObject)
/*    */   {
/* 62 */     JPVersionNumber localJPVersionNumber = (JPVersionNumber)paramObject;
/*    */     
/* 64 */     return toString().equals(localJPVersionNumber.toString());
/*    */   }
/*    */   
/*    */   public int compareTo(Object paramObject)
/*    */   {
/* 69 */     JPVersionNumber localJPVersionNumber = (JPVersionNumber)paramObject;
/*    */     
/* 71 */     if (this.major < localJPVersionNumber.major) return -1;
/* 72 */     if (this.major > localJPVersionNumber.major) { return 1;
/*    */     }
/*    */     
/* 75 */     if (this.minor < localJPVersionNumber.minor) return -1;
/* 76 */     if (this.minor > localJPVersionNumber.minor) { return 1;
/*    */     }
/*    */     
/* 79 */     if (this.patch < localJPVersionNumber.patch) return -1;
/* 80 */     if (this.patch > localJPVersionNumber.patch) { return 1;
/*    */     }
/* 82 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\shared\JPVersionNumber.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */