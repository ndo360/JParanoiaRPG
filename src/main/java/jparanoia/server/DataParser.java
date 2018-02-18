/*    */ package jparanoia.server;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.FileReader;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ import java.util.StringTokenizer;
/*    */ import java.util.Vector;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataParser
/*    */ {
/*    */   BufferedReader reader;
/*    */   String input;
/*    */   String currentText;
/*    */   Vector newPlayers;
/*    */   StringTokenizer st;
/*    */   ServerPlayer somePlayer;
/*    */   ServerPlayer[] somePlayerArray;
/*    */   int playerNumber;
/*    */   String playerName;
/*    */   boolean playerIsPlayer;
/*    */   String playerPassword;
/*    */   String playerDataFileName;
/*    */   
/*    */   public ServerPlayer[] parsePlayerList(String paramString)
/*    */   {
/* 32 */     this.newPlayers = new Vector();
/*    */     
/*    */     try
/*    */     {
/* 36 */       System.out.println("\nProcessing playerList.txt:");
/*    */       
/* 38 */       this.reader = new BufferedReader(new FileReader(paramString));
/* 39 */       this.input = this.reader.readLine();
/* 40 */       int i = 0;
/*    */       
/*    */ 
/*    */ 
/* 44 */       while (this.input != null)
/*    */       {
/*    */ 
/* 47 */         if (!this.input.startsWith("#"))
/*    */         {
/*    */ 
/* 50 */           this.st = new StringTokenizer(this.input, "|");
/*    */           
/* 52 */           while (this.st.hasMoreTokens())
/*    */           {
/* 54 */             this.playerNumber = i;
/* 55 */             this.playerName = this.st.nextToken();
/* 56 */             System.out.println("     Player " + i + ": " + this.playerName);
/* 57 */             this.playerIsPlayer = this.st.nextToken().equals("p");
/*    */             
/* 59 */             if (this.playerIsPlayer)
/*    */             {
/* 61 */               this.playerPassword = this.st.nextToken();
/* 62 */               this.playerDataFileName = this.st.nextToken();
/*    */ 
/*    */             }
/*    */             else
/*    */             {
/*    */ 
/* 68 */               this.playerPassword = "npc";
/* 69 */               this.playerDataFileName = "npc";
/*    */             }
/*    */             
/* 72 */             this.somePlayer = new ServerPlayer(this.playerNumber, this.playerName, this.playerIsPlayer, this.playerPassword, "playerData/" + this.playerDataFileName);
/*    */             
/*    */ 
/*    */ 
/* 76 */             if (this.somePlayer.isAnActualPlayer()) { this.somePlayer.readCharacterSheetFile();
/*    */             }
/* 78 */             this.newPlayers.addElement(this.somePlayer);
/* 79 */             i++;
/*    */           }
/*    */         }
/* 82 */         this.input = this.reader.readLine();
/*    */       }
/*    */       
/*    */     }
/*    */     catch (IOException localIOException)
/*    */     {
/* 88 */       System.out.println("* Error reading playerList.txt");
/* 89 */       localIOException.printStackTrace();
/*    */     }
/*    */     
/* 92 */     this.somePlayerArray = new ServerPlayer[this.newPlayers.size()];
/* 93 */     this.newPlayers.copyInto(this.somePlayerArray);
/*    */     
/* 95 */     return this.somePlayerArray;
/*    */   }
/*    */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\DataParser.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */