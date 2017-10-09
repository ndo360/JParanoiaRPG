/*    */ package jparanoia.shared;
/*    */ 
/*    */ import java.net.URL;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JPImage
/*    */ {
/*    */   String imageName;
/*    */   URL imageURL;
/*    */   
/*    */   public JPImage(String paramString, URL paramURL)
/*    */   {
/* 14 */     this.imageName = paramString;
/* 15 */     this.imageURL = paramURL;
/*    */   }
/*    */   
/*    */ 
/*    */   public String getName()
/*    */   {
/* 21 */     return this.imageName;
/*    */   }
/*    */   
/*    */   public URL getURL()
/*    */   {
/* 26 */     return this.imageURL;
/*    */   }
/*    */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\shared\JPImage.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */