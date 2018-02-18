/*     */ package jparanoia.shared;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.StringTokenizer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Prefs
/*     */ {
/*     */   public static final int PLAY_SOUNDS = 0;
/*     */   public static final int PLAY_STARTUP = 1;
/*     */   public static final int PLAY_JOIN_LEAVE = 2;
/*     */   public static final int PLAY_NEW_TEXT = 3;
/*     */   public static final int PLAY_NEW_OBSERVER_TEXT = 4;
/*     */   public static final int PLAY_MUTED_UNMUTED = 5;
/*     */   public static final int PLAY_FROZEN_UNFROZEN = 6;
/*     */   public static final int PLAY_PROMOTED_DEMOTED = 7;
/*     */   public static final int PLAY_LOGIN_BADLOGIN = 8;
/*     */   public static final int PLAY_CONNECTED_DISCONNECTED = 9;
/*     */   public static final int PLAY_COMBAT_ALERT = 10;
/*     */   public static final int PLAY_COMBAT_MUSIC = 11;
/*     */   public static final int PLAY_CHARSHEET = 12;
/*     */   public static final int PLAY_NEW_PM = 13;
/*     */   public static final int PLAY_DEATH = 14;
/*     */   public static final int FONT_SIZE = 15;
/*     */   public static final int FONT_FAMILY = 16;
/*     */   public static final int FONT_BOLD = 17;
/*     */   public static final int CLOBBER_AQUA = 18;
/*     */   public static final int PM_CHAT_NOTIFICATION = 19;
/*     */   public static final int KEEP_LOG = 20;
/*     */   public static final int HTML_LOG = 21;
/*     */   public static final int REAL_NAME = 22;
/*     */   public static final int MAX_NUM_CLONES = 23;
/*     */   public static final int SHOW_TIMESTAMPS = 24;
/*     */   public static final int COMPUTER_BIG_FONT = 25;
/*     */   public static final int COMPUTER_FONT_INCREASE = 26;
/*     */   public static final int COMPUTER_ALL_CAPS = 27;
/*     */   public static final int ALLOW_OBSERVERS = 28;
/*     */   public static final int HEAR_OBSERVERS = 29;
/*     */   public static final int ANNOUNCE_OBSERVERS = 30;
/*     */   public static final int REGISTER_GAME = 31;
/*     */   public static final int BEHIND_ROUTER = 32;
/*     */   public static final int QUICK_CHARSHEET = 33;
/*     */   public static final int PXP_GAME = 34;
/*     */   public static final int GM_NAME_NAG = 35;
/*     */   public static final int SINGLE_USE_SPOOF = 36;
/*     */   public static final int USE_ANNOUNCEMENT = 37;
/*  57 */   String[][] prefStrings = new String[40][2];
/*  58 */   Object[] prefsArray = new Object[40];
/*     */   
/*     */ 
/*     */ 
/*     */   public Prefs()
/*     */   {
/*  64 */     parsePrefs("jpConfig.ini");
/*     */   }
/*     */   
/*     */   public Object getPref(int paramInt)
/*     */   {
/*  69 */     return this.prefsArray[paramInt];
/*     */   }
/*     */   
/*     */   public void setPref(int paramInt, Object paramObject)
/*     */   {
/*  74 */     this.prefsArray[paramInt] = paramObject;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void parsePrefs(String paramString)
/*     */   {
/*     */     try
/*     */     {
/*  86 */       BufferedReader localBufferedReader = new BufferedReader(new FileReader(paramString));
/*  87 */       String str1 = localBufferedReader.readLine();
/*  88 */       int i = 0;
/*     */       
/*  90 */       String str2 = "uninitialized";
/*  91 */       String str3 = "uninitialized";
/*     */       
/*     */ 
/*     */ 
/*  95 */       while (str1 != null)
/*     */       {
/*     */ 
/*  98 */         if (!str1.startsWith("#"))
/*     */         {
/*     */ 
/* 101 */           StringTokenizer localStringTokenizer = new StringTokenizer(str1, "=");
/*     */           
/* 103 */           while (localStringTokenizer.hasMoreTokens())
/*     */           {
/* 105 */             String str4 = "";
/*     */             
/* 107 */             str2 = localStringTokenizer.nextToken();
/* 108 */             str3 = localStringTokenizer.nextToken();
/*     */             
/* 110 */             if (str2.startsWith("b")) { this.prefsArray[i] = new Boolean(str3.equals("true"));str4 = "BOOLEAN";
/* 111 */             } else if (str2.startsWith("i")) { this.prefsArray[i] = new Integer(Integer.parseInt(str3));str4 = "INTEGER";
/* 112 */             } else if (str2.startsWith("s")) { this.prefsArray[i] = str3;str4 = "STRING";
/*     */             }
/* 114 */             this.prefStrings[i][0] = str2;
/* 115 */             this.prefStrings[i][1] = str3;
/*     */             
/*     */ 
/*     */ 
/* 119 */             i++;
/*     */           }
/*     */         }
/* 122 */         str1 = localBufferedReader.readLine();
/*     */       }
/*     */       
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/* 128 */       System.out.println("* Error reading jpConfig.ini");
/* 129 */       localIOException.printStackTrace();
/* 130 */       System.exit(-1);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\shared\Prefs.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */