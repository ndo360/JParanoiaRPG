/*     */ package jparanoia.shared;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BrightColorArray
/*     */ {
/*     */   public Color[] getColors()
/*     */   {
/*  13 */     Color[] arrayOfColor = new Color[55];
/*     */     
/*  15 */     Color localColor = Color.black;
/*     */     
/*  17 */     for (int i = 0; i < 55; i++)
/*     */     {
/*  19 */       switch (i) {
/*     */       case 0: 
/*  21 */         localColor = new Color(113, 249, 145); break;
/*  22 */       case 1:  localColor = new Color(234, 118, 25); break;
/*  23 */       case 2:  localColor = new Color(234, 232, 25); break;
/*  24 */       case 3:  localColor = new Color(159, 144, 208); break;
/*  25 */       case 4:  localColor = new Color(91, 182, 216); break;
/*  26 */       case 5:  localColor = new Color(219, 174, 124); break;
/*  27 */       case 6:  localColor = new Color(130, 200, 96); break;
/*  28 */       case 7:  localColor = new Color(233, 238, 151); break;
/*  29 */       case 8:  localColor = new Color(242, 142, 168); break;
/*  30 */       case 9:  localColor = new Color(234, 118, 25); break;
/*  31 */       case 10:  localColor = new Color(234, 25, 25); break;
/*  32 */       case 11:  localColor = new Color(113, 249, 145); break;
/*  33 */       case 12:  localColor = new Color(234, 118, 25); break;
/*  34 */       case 13:  localColor = new Color(234, 232, 25); break;
/*  35 */       case 14:  localColor = new Color(159, 144, 208); break;
/*  36 */       case 15:  localColor = new Color(91, 182, 216); break;
/*  37 */       case 16:  localColor = new Color(219, 174, 124); break;
/*  38 */       case 17:  localColor = new Color(130, 200, 96); break;
/*  39 */       case 18:  localColor = new Color(233, 238, 151); break;
/*  40 */       case 19:  localColor = new Color(242, 142, 168); break;
/*  41 */       case 20:  localColor = new Color(234, 118, 25); break;
/*  42 */       case 21:  localColor = new Color(234, 25, 25); break;
/*  43 */       case 22:  localColor = new Color(113, 249, 145); break;
/*  44 */       case 23:  localColor = new Color(234, 118, 25); break;
/*  45 */       case 24:  localColor = new Color(234, 232, 25); break;
/*  46 */       case 25:  localColor = new Color(159, 144, 208); break;
/*  47 */       case 26:  localColor = new Color(91, 182, 216); break;
/*  48 */       case 27:  localColor = new Color(219, 174, 124); break;
/*  49 */       case 28:  localColor = new Color(130, 200, 96); break;
/*  50 */       case 29:  localColor = new Color(233, 238, 151); break;
/*  51 */       case 30:  localColor = new Color(242, 142, 168); break;
/*  52 */       case 31:  localColor = new Color(234, 118, 25); break;
/*  53 */       case 32:  localColor = new Color(234, 25, 25); break;
/*  54 */       case 33:  localColor = new Color(113, 249, 145); break;
/*  55 */       case 34:  localColor = new Color(234, 118, 25); break;
/*  56 */       case 35:  localColor = new Color(234, 232, 25); break;
/*  57 */       case 36:  localColor = new Color(159, 144, 208); break;
/*  58 */       case 37:  localColor = new Color(91, 182, 216); break;
/*  59 */       case 38:  localColor = new Color(219, 174, 124); break;
/*  60 */       case 39:  localColor = new Color(130, 200, 96); break;
/*  61 */       case 40:  localColor = new Color(233, 238, 151); break;
/*  62 */       case 41:  localColor = new Color(242, 142, 168); break;
/*  63 */       case 42:  localColor = new Color(234, 118, 25); break;
/*  64 */       case 43:  localColor = new Color(234, 25, 25); break;
/*  65 */       case 44:  localColor = new Color(113, 249, 145); break;
/*  66 */       case 45:  localColor = new Color(234, 118, 25); break;
/*  67 */       case 46:  localColor = new Color(234, 232, 25); break;
/*  68 */       case 47:  localColor = new Color(159, 144, 208); break;
/*  69 */       case 48:  localColor = new Color(91, 182, 216); break;
/*  70 */       case 49:  localColor = new Color(219, 174, 124); break;
/*  71 */       case 50:  localColor = new Color(130, 200, 96); break;
/*  72 */       case 51:  localColor = new Color(233, 238, 151); break;
/*  73 */       case 52:  localColor = new Color(242, 142, 168); break;
/*  74 */       case 53:  localColor = new Color(234, 118, 25); break;
/*  75 */       case 54:  localColor = new Color(234, 25, 25); break;
/*  76 */       case 55:  localColor = new Color(113, 249, 145); break;
/*  77 */       case 56:  localColor = new Color(234, 118, 25); break;
/*  78 */       case 57:  localColor = new Color(234, 232, 25); break;
/*  79 */       case 58:  localColor = new Color(159, 144, 208); break;
/*  80 */       case 59:  localColor = new Color(91, 182, 216); break;
/*  81 */       case 60:  localColor = new Color(219, 174, 124); break;
/*  82 */       case 61:  localColor = new Color(130, 200, 96); break;
/*  83 */       case 62:  localColor = new Color(233, 238, 151); break;
/*  84 */       case 63:  localColor = new Color(242, 142, 168); break;
/*  85 */       case 64:  localColor = new Color(234, 118, 25); break;
/*  86 */       case 65:  localColor = new Color(234, 25, 25); break;
/*  87 */       case 66:  localColor = new Color(113, 249, 145); break;
/*  88 */       case 67:  localColor = new Color(234, 118, 25); break;
/*  89 */       case 68:  localColor = new Color(234, 232, 25); break;
/*  90 */       case 69:  localColor = new Color(159, 144, 208); break;
/*  91 */       case 70:  localColor = new Color(91, 182, 216); break;
/*  92 */       case 71:  localColor = new Color(219, 174, 124); break;
/*  93 */       case 72:  localColor = new Color(130, 200, 96); break;
/*  94 */       case 73:  localColor = new Color(233, 238, 151); break;
/*  95 */       case 74:  localColor = new Color(242, 142, 168); break;
/*  96 */       case 75:  localColor = new Color(234, 118, 25); break;
/*  97 */       case 76:  localColor = new Color(234, 25, 25); break;
/*  98 */       case 77:  localColor = new Color(113, 249, 145); break;
/*  99 */       case 78:  localColor = new Color(234, 118, 25); break;
/* 100 */       case 79:  localColor = new Color(234, 232, 25); break;
/* 101 */       case 80:  localColor = new Color(159, 144, 208); break;
/* 102 */       case 81:  localColor = new Color(91, 182, 216); break;
/* 103 */       case 82:  localColor = new Color(219, 174, 124); break;
/* 104 */       case 83:  localColor = new Color(130, 200, 96); break;
/* 105 */       case 84:  localColor = new Color(233, 238, 151); break;
/* 106 */       case 85:  localColor = new Color(242, 142, 168); break;
/* 107 */       case 86:  localColor = new Color(234, 118, 25); break;
/* 108 */       case 87:  localColor = new Color(234, 25, 25); break;
/* 109 */       default:  System.out.println("BrightColorArray() constructor: Error: Out of colors!");
/*     */       }
/*     */       
/* 112 */       arrayOfColor[i] = localColor;
/*     */     }
/*     */     
/* 115 */     return arrayOfColor;
/*     */   }
/*     */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\shared\BrightColorArray.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */