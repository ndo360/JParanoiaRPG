/*     */ package jparanoia.server;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.StringTokenizer;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JCheckBoxMenuItem;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JRadioButtonMenuItem;
/*     */ import javax.swing.event.DocumentEvent;
/*     */ import javax.swing.text.DefaultStyledDocument;
/*     */ import jparanoia.shared.JPSounds;
/*     */ import jparanoia.shared.JParanoia;
/*     */ import jparanoia.shared.SoundMenu;
/*     */ 
/*     */ public class ServerPlayer extends jparanoia.shared.JPPlayer
/*     */ {
/*  22 */   static int numUnsavedCharsheets = 0;
/*     */   
/*     */   static FileWriter writer;
/*     */   
/*  26 */   private boolean debugSpecific = false;
/*     */   
/*     */   private String name;
/*     */   
/*     */   private String clearance;
/*     */   private int clearanceInt;
/*     */   private String sector;
/*     */   private String password;
/*     */   private String realName;
/*     */   final int PLAYER_NUMBER;
/*     */   int cloneNumber;
/*  37 */   boolean loggedIn = false;
/*  38 */   boolean isDead = false;
/*  39 */   boolean muted = false;
/*  40 */   boolean frozen = false;
/*  41 */   boolean unsavedCharsheet = false;
/*     */   final boolean IS_PLAYER;
/*     */   PrivateMessagePane pmPane;
/*     */   StatusPanel statusPanel;
/*     */   DefaultStyledDocument characterSheet;
/*  46 */   Color chatColor = Color.gray;
/*     */   
/*     */   String dataFile;
/*     */   
/*     */   String data;
/*     */   
/*  52 */   ServerChatThread chatThread = null;
/*     */   
/*     */   BufferedReader reader;
/*     */   
/*     */   static javax.swing.text.SimpleAttributeSet sas;
/*     */   
/*     */   static StringTokenizer st;
/*     */   
/*     */   private ServerPlayerMenu playerMenu;
/*     */   private NPCMenu npcMenu;
/*     */   private JCheckBox globalExcludeCheckBox;
/*     */   
/*     */   public ServerPlayer(int paramInt, String paramString1, boolean paramBoolean, String paramString2, String paramString3)
/*     */   {
/*  66 */     this.PLAYER_NUMBER = paramInt;
/*     */     
/*  68 */     this.password = paramString2;
/*  69 */     this.IS_PLAYER = paramBoolean;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  85 */     this.name = paramString1;
/*     */     
/*  87 */     if ((!this.IS_PLAYER) && (this.name.startsWith("spareNPC")))
/*     */     {
/*  89 */       this.loggedIn = true;
/*  90 */       this.npcMenu = new NPCMenu(this);
/*  91 */       System.out.println("Generated NPCMenu for " + this.name);
/*  92 */       JPServer.spareNpcs.add(this);
/*     */     }
/*  94 */     this.dataFile = paramString3;
/*     */     
/*  96 */     this.characterSheet = new DefaultStyledDocument(new javax.swing.text.StyleContext());
/*  97 */     sas = JPServer.charsheetAttributes;
/*     */   }
/*     */   
/*     */   public ServerPlayerMenu getPlayerMenu()
/*     */   {
/* 102 */     return this.playerMenu;
/*     */   }
/*     */   
/*     */   public String getPassword()
/*     */   {
/* 107 */     return this.password;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void readCharacterSheetFile()
/*     */   {
/*     */     try
/*     */     {
/* 118 */       this.reader = new BufferedReader(new java.io.FileReader(this.dataFile));
/*     */ 
/*     */     }
/*     */     catch (Exception localException1)
/*     */     {
/* 123 */       System.out.println("An exception occured while attemping to access " + this.dataFile);
/* 124 */       localException1.printStackTrace();
/*     */     }
/*     */     
/*     */     try
/*     */     {
/* 129 */       this.data = this.reader.readLine();
/*     */       
/* 131 */       String str = null;
/*     */       try {
/* 133 */         str = this.data.substring(0, this.data.lastIndexOf("-"));
/* 134 */       } catch (Exception localException3) { localException3.printStackTrace();JParanoia.errorMessage("Invalid Charsheet", "The charsheet file \"" + this.dataFile + "\" does not have\n" + "the character's name on the first line. This is mandatory.");
/*     */         
/* 136 */         System.exit(0); }
/*     */       int i;
/* 138 */       if (this.PLAYER_NUMBER == 0) {
/* 139 */         if ((JPServer.gmNameNag) && (this.data.substring(0, this.data.length() - 2).equals("GM")))
/*     */         {
/* 141 */           new JOptionPane();localObject = (String)JOptionPane.showInputDialog(null, "Your name, as defined in your own charsheet file, " + this.dataFile + "\n" + "is \"GM\". This does not make you very unique. You can choose a name here\n" + "but it will be forgotten when you exit JParanoia. To choose a lasting name,\n" + "you must change the first line of your charsheet file.\n" + "Just be sure to leave the -0 on the end.\n" + "\n" + "(You can click Cancel to keep \"GM\" if you so choose.\n" + "To permanently surpress this notice, set bGmNameNag=false\n" + "in your jpConfig.ini file.)", "Boring GM Name...", -1, null, null, "GM");
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 152 */           if ((localObject != null) && (!((String)localObject).equals("")))
/*     */           {
/* 154 */             this.name = ((String)localObject);
/*     */           }
/*     */           else {
/* 157 */             System.out.println("NUNAME == " + (String)localObject);this.name = this.data.substring(0, this.data.length() - 2);
/*     */           }
/*     */         } else {
/* 160 */           this.name = this.data.substring(0, this.data.length() - 2);
/*     */         }
/* 162 */       } else if ((this.IS_PLAYER) && (this.PLAYER_NUMBER != 0))
/*     */       {
/* 164 */         st = new StringTokenizer(str, "-");
/*     */         
/* 166 */         System.out.println("Parsing name for: " + str);
/*     */         
/* 168 */         this.name = str.substring(0, str.indexOf("-"));
/*     */         
/*     */ 
/* 171 */         if ((st.countTokens() > 3) || (st.countTokens() < 2))
/*     */         {
/* 173 */           JParanoia.errorMessage("Invalid Player Name", "The character sheet " + this.dataFile + "\n" + "attempts to define a player with\n" + "an invalid name \"" + str + "\".\n" + "\n" + "Player names must consist of a first name,\n" + "a clearance initial, and a sector. (Clearance\n" + "initial may be omitted for infrared players.)\n" + "\n" + "Correct the error and relaunch the server.");
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 184 */           System.exit(0);
/*     */         }
/*     */         
/* 187 */         if (st.countTokens() == 2)
/*     */         {
/* 189 */           System.out.println("2 tokens in \"" + str + "\", assigning Infrared clearance.");
/* 190 */           this.clearance = "IR";
/*     */         }
/*     */         else
/*     */         {
/* 194 */           this.clearance = str.substring(str.indexOf("-") + 1, str.lastIndexOf("-"));
/*     */         }
/* 196 */         if ((this.clearanceInt = evaluateClearance(this.clearance)) == -99)
/*     */         {
/* 198 */           JParanoia.errorMessage("Invalid clearance", "The character sheet " + this.dataFile + "\n" + "attempts to grant a player an\n" + "invalid security clearance \"" + this.clearance + "\".\n" + "\n" + "Allowed clearance codes are:\n" + "(blank) = infrared\n" + "R = red\n" + "O = orange\n" + "Y = yellow\n" + "G = green\n" + "B = blue\n" + "I = indigo\n" + "V = violet\n" + "U = ultraviolet\n" + "\n" + "Correct the error and relaunch the server.");
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 216 */           System.exit(0);
/*     */         }
/*     */         
/* 219 */         this.sector = str.substring(str.lastIndexOf("-") + 1);
/*     */         
/* 221 */         if (this.sector.length() != 3)
/*     */         {
/* 223 */           JParanoia.errorMessage("Invalid sector", "The character sheet " + this.dataFile + "\n" + "attempts to define a player with\n" + "invalid sector name \"" + this.sector + "\".\n" + "\n" + "Sector names MUST be exactly three characters in length.\n" + "\n" + "Correct the error and relaunch the server.");
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 232 */           System.exit(0);
/*     */         }
/*     */         
/* 235 */         localObject = this.sector.toCharArray();
/* 236 */         for (i = 0; i < localObject.length;) {
/* 237 */           if ((localObject[i] < 'A') || (localObject[i] > 'Z'))
/*     */           {
/* 239 */             JParanoia.errorMessage("Invalid sector", "The character sheet " + this.dataFile + "\n" + "attempts to define a player with\n" + "invalid sector name \"" + this.sector + "\".\n" + "\n" + "Sector names MUST only contain capital letters A-Z.\n" + "\n" + "Correct the error and relaunch the server.");
/*     */             
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 248 */             System.exit(0);
/*     */           }
/* 236 */           i++; continue;
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 253 */           this.name = this.data.substring(0, this.data.length() - 2);
/*     */         } }
/* 255 */       if (this.name.startsWith("(dead)")) { this.isDead = true;
/*     */       }
/*     */       
/* 258 */       this.cloneNumber = Integer.parseInt(this.data.substring(this.data.lastIndexOf("-") + 1));
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 265 */       Object localObject = this.reader.readLine();
/* 266 */       while (localObject != null)
/*     */       {
/* 268 */         if (!((String)localObject).startsWith("#")) this.characterSheet.insertString(this.characterSheet.getLength(), (String)localObject + "\n", sas);
/* 269 */         localObject = this.reader.readLine();
/*     */       }
/* 271 */       this.reader.close();
/*     */       
/* 273 */       this.characterSheet.addDocumentListener(new javax.swing.event.DocumentListener() {
/* 274 */         public void insertUpdate(DocumentEvent paramAnonymousDocumentEvent) { if (!ServerPlayer.this.unsavedCharsheet) ServerPlayer.this.charsheetUpdated(); }
/* 275 */         public void removeUpdate(DocumentEvent paramAnonymousDocumentEvent) { if (!ServerPlayer.this.unsavedCharsheet) ServerPlayer.this.charsheetUpdated();
/*     */         }
/*     */         
/* 278 */         public void changedUpdate(DocumentEvent paramAnonymousDocumentEvent) {} });
/* 279 */       this.playerMenu = new ServerPlayerMenu(this);
/* 280 */       this.globalExcludeCheckBox = new JCheckBox(getName());
/*     */ 
/*     */     }
/*     */     catch (Exception localException2)
/*     */     {
/* 285 */       System.out.println("An exception occured while reading " + this.name + "'s data file.");
/* 286 */       localException2.printStackTrace();
/* 287 */       JParanoia.errorMessage("Exception", "An exception occured while reading " + this.name + "'s data file.\n" + "Run JParanoia with the console window to view errors.\n" + "JParanoia will now terminate.");
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 292 */       System.exit(-1);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public DefaultStyledDocument getCharsheet()
/*     */   {
/* 299 */     return this.characterSheet;
/*     */   }
/*     */   
/*     */ 
/*     */   public String getCharacterSheetFile()
/*     */   {
/* 305 */     return this.dataFile;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/* 322 */     if ((isAnActualPlayer()) && (this.PLAYER_NUMBER != 0))
/*     */     {
/* 324 */       if (this.clearanceInt == 0) return this.name + "-" + this.sector;
/* 325 */       return this.name + "-" + this.clearance + "-" + this.sector; }
/* 326 */     return this.name;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean checkPassword(String paramString)
/*     */   {
/* 332 */     JPServer.absoluteChat(getName() + " has attempted to login!");
/* 333 */     return paramString.equalsIgnoreCase(this.password);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setPMPane(PrivateMessagePane paramPrivateMessagePane)
/*     */   {
/* 339 */     this.pmPane = paramPrivateMessagePane;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isLoggedIn()
/*     */   {
/* 345 */     return this.loggedIn;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setLoggedIn(boolean paramBoolean)
/*     */   {
/* 352 */     this.loggedIn = paramBoolean;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isAnActualPlayer()
/*     */   {
/* 358 */     return this.IS_PLAYER;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setChatColor(Color paramColor)
/*     */   {
/* 364 */     this.chatColor = paramColor;
/*     */   }
/*     */   
/*     */ 
/*     */   public Color getChatColor()
/*     */   {
/* 370 */     return this.chatColor;
/*     */   }
/*     */   
/*     */ 
/*     */   public int getPlayerNumber()
/*     */   {
/* 376 */     return this.PLAYER_NUMBER;
/*     */   }
/*     */   
/*     */   public void setRealName(String paramString)
/*     */   {
/* 381 */     this.realName = paramString;
/* 382 */     this.playerMenu.realNameLabel.setText("    Real Name: " + this.realName);
/*     */   }
/*     */   
/*     */   public String getRealName()
/*     */   {
/* 387 */     return this.realName;
/*     */   }
/*     */   
/*     */ 
/*     */   public String getID()
/*     */   {
/*     */     String str;
/* 394 */     if (this.PLAYER_NUMBER < 10) str = "0" + this.PLAYER_NUMBER; else
/* 395 */       str = "" + this.PLAYER_NUMBER;
/* 396 */     return str;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setStatusPanel(StatusPanel paramStatusPanel)
/*     */   {
/* 402 */     this.statusPanel = paramStatusPanel;
/*     */   }
/*     */   
/*     */   public boolean isMuted()
/*     */   {
/* 407 */     return this.muted;
/*     */   }
/*     */   
/*     */   public void setMuted(boolean paramBoolean)
/*     */   {
/* 412 */     this.muted = paramBoolean;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void kill()
/*     */   {
/* 419 */     if ((isAnActualPlayer()) && (!this.isDead))
/*     */     {
/*     */ 
/* 422 */       if ((this.cloneNumber < JPServer.maxNumClones) || (JPServer.isPXPGame))
/*     */       {
/* 424 */         String str1 = this.name;
/*     */         
/* 426 */         String str2 = deathEuphamism(toString());
/* 427 */         this.cloneNumber += 1;
/* 428 */         JPServer.absoluteChat(str2);
/* 429 */         JPServer.spamString("199" + str2);
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 434 */         this.isDead = true;
/* 435 */         JPServer.absoluteChat(toString() + " has died and has no clones left! Oh, the humanity!!");
/* 436 */         JPServer.spamString("199" + toString() + " has died and has no clones left! Oh, the humanity!!");
/* 437 */         this.name = ("(dead)" + this.name);
/*     */       }
/* 439 */       JPServer.notifyPlayersOfDeath(this);
/* 440 */       JPServer.spoofComboBox.repaint();
/* 441 */       saveCharsheet(false);
/*     */ 
/*     */     }
/* 444 */     else if ((isAnActualPlayer()) && (this.isDead))
/*     */     {
/* 446 */       JPServer.absoluteChat("The GM has attempted to kill " + this.name + " one more time. This is, of course, impossible.");
/* 447 */       JPServer.spamString("199The GM has attempted to kill " + this.name + " one more time. This is, of course, impossible.");
/*     */     }
/*     */   }
/*     */   
/*     */   public void unkill()
/*     */   {
/* 453 */     if (isAnActualPlayer())
/*     */     {
/*     */ 
/* 456 */       if (this.isDead)
/*     */       {
/* 458 */         this.isDead = false;
/*     */         
/* 460 */         JPServer.absoluteChat(toString() + " has been refunded a clone due to a clerical error.");
/* 461 */         JPServer.spamString("199" + toString() + " has been refunded a clone due to a clerical error.");
/*     */         
/*     */ 
/* 464 */         this.name = this.name.substring(6);
/*     */         
/*     */ 
/* 467 */         JPServer.notifyPlayersOfUndeath(this);
/* 468 */         JPServer.spoofComboBox.repaint();
/* 469 */         saveCharsheet(false);
/*     */ 
/*     */ 
/*     */ 
/*     */       }
/* 474 */       else if (this.cloneNumber > 1)
/*     */       {
/* 476 */         JPServer.absoluteChat(toString() + " has been refunded a clone due to a clerical error.");
/* 477 */         JPServer.spamString("199" + toString() + " has been refunded a clone due to a clerical error.");
/*     */         
/* 479 */         this.cloneNumber -= 1;
/*     */         
/*     */ 
/*     */ 
/* 483 */         JPServer.notifyPlayersOfUndeath(this);
/* 484 */         JPServer.spoofComboBox.repaint();
/* 485 */         saveCharsheet(false);
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 490 */         JPServer.absoluteChat("The GM has attempted to give " + toString() + " another clone. This is, of course, impossible.");
/* 491 */         JPServer.spamString("199The GM has attempted to give " + toString() + " another clone. This is, of course, impossible.");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 500 */     if ((isAnActualPlayer()) && (this.PLAYER_NUMBER != 0)) return getName() + "-" + this.cloneNumber;
/* 501 */     return getName();
/*     */   }
/*     */   
/*     */ 
/*     */   private String deathEuphamism(String paramString)
/*     */   {
/* 507 */     int i = JPServer.rand.nextInt(14);
/* 508 */     String str = paramString + " ";
/* 509 */     switch (i) {
/*     */     case 0: 
/* 511 */       str = str + "has gone to Great Alpha Complex in the Sky."; break;
/* 512 */     case 1:  str = str + "has kicked the synthe-bucket."; break;
/* 513 */     case 2:  str = str + "has been visited by the reaper-bot."; break;
/* 514 */     case 3:  str = str + "has shuffled off this mortal sector."; break;
/* 515 */     case 4:  str = str + "has got bored of his present clone."; break;
/* 516 */     case 5:  str = str + "has bought the highly-treasonous farm."; break;
/* 517 */     case 6:  str = str + "has drunk his last bottle of Bouncy Bubble Beverage."; break;
/* 518 */     case 7:  str = str + "has eaten his last Hot Fun."; break;
/* 519 */     case 8:  str = str + "is pushing up traitorous daises."; break;
/* 520 */     case 9:  str = str + "has passed onto a better sector."; break;
/* 521 */     case 10:  str = str + "has exited, vid-stage left."; break;
/* 522 */     case 11:  str = str + "is, alas, no more."; break;
/* 523 */     case 12:  str = str + "has passed away."; break;
/* 524 */     case 13:  str = str + "has ceased to show any signs of life."; break;
/* 525 */     case 14:  str = str + "has cashed in his credits.";
/*     */     }
/* 527 */     return str;
/*     */   }
/*     */   
/*     */   void rename()
/*     */   {
/* 532 */     String str1 = getName();
/* 533 */     String str4 = "";
/*     */     
/*     */ 
/*     */ 
/* 537 */     new JOptionPane();String str5 = (String)JOptionPane.showInputDialog(null, "Enter new name for " + getName() + "\n" + "(omit the clone number):", "New Clone Family...", -1, null, null, getName());
/*     */     
/*     */ 
/*     */ 
/* 541 */     if ((str5 != null) && (!str5.equals("")))
/*     */     {
/* 543 */       System.out.println("About to attempt name parsing on: " + str5);
/*     */       
/*     */       try
/*     */       {
/* 547 */         st = new StringTokenizer(str5, "-");
/*     */         
/* 549 */         if (st.countTokens() > 3)
/*     */         {
/* 551 */           JParanoia.errorMessage("Invalid name", "I told you to leave off the clone number.\nTry again.");
/*     */           
/*     */ 
/* 554 */           return;
/*     */         }
/*     */         
/* 557 */         if (st.countTokens() < 2)
/*     */         {
/* 559 */           JParanoia.errorMessage("Invalid name", "You didn't give this clone a sector and/or\nsecurity clearance.\nTry again."); return;
/*     */         }
/*     */         
/*     */ 
/*     */         String str3;
/*     */         
/*     */ 
/* 566 */         if (st.countTokens() == 2)
/*     */         {
/* 568 */           System.out.println("2 tokens in \"" + str5 + "\", assigning Infrared clearance.");
/* 569 */           str3 = "IR";
/*     */         } else {
/* 571 */           str3 = str5.substring(str5.indexOf("-") + 1, str5.lastIndexOf("-"));
/*     */         }
/* 573 */         String str2 = str5.substring(0, str5.indexOf("-"));
/*     */         
/* 575 */         str4 = str5.substring(str5.lastIndexOf("-") + 1);
/*     */         
/* 577 */         if (str4.length() != 3)
/*     */         {
/* 579 */           JParanoia.errorMessage("Invalid sector", "Sector names MUST consist of three capital letters A-Z.\nTry again.");
/*     */           
/*     */ 
/* 582 */           return;
/*     */         }
/*     */         
/* 585 */         char[] arrayOfChar = str4.toCharArray();
/* 586 */         for (int j = 0; j < arrayOfChar.length; j++) {
/* 587 */           if ((arrayOfChar[j] < 'A') || (arrayOfChar[j] > 'Z'))
/*     */           {
/* 589 */             JParanoia.errorMessage("Invalid sector", "The sector \"" + str4 + "\" is invalid.\n" + "Sector names MUST only contain capital letters A-Z.\n" + "\n" + "Try again."); return;
/*     */           }
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */         int i;
/*     */         
/*     */ 
/*     */ 
/* 599 */         if ((i = evaluateClearance(str3)) == -99)
/*     */         {
/* 601 */           JParanoia.errorMessage("Invalid clearance", "Security clearance \"" + str3 + "\" is invalid.\n" + "\n" + "Allowed clearance codes are:\n" + "(blank) = infrared\n" + "R = red\n" + "O = orange\n" + "Y = yellow\n" + "G = green\n" + "B = blue\n" + "I = indigo\n" + "V = violet\n" + "U = ultraviolet\n" + "\n" + "Try again.");
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 616 */           return;
/*     */         }
/*     */         
/* 619 */         this.clearanceInt = i;
/* 620 */         this.name = str2;
/* 621 */         this.clearance = str3;
/* 622 */         this.sector = str4;
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/* 626 */         JParanoia.errorMessage("Invalid name", "You have entered a name incompatible\nwith the standards set forth by Friend\nComputer. Report for termination.");
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 632 */       JPServer.spamString("199" + str1 + " has been replaced by " + str5);
/* 633 */       JPServer.absoluteChat(str1 + " has been replaced by " + str5);
/*     */       
/*     */ 
/*     */       String str6;
/*     */       
/*     */ 
/* 639 */       if (this.loggedIn) str6 = "y"; else {
/* 640 */         str6 = "n";
/*     */       }
/* 642 */       this.cloneNumber = 1;
/* 643 */       this.isDead = false;
/*     */       
/*     */ 
/*     */ 
/* 647 */       this.globalExcludeCheckBox.setText(getName());
/*     */       
/* 649 */       JPServer.repaintMenus();
/* 650 */       this.playerMenu.setText(getName());
/* 651 */       JPServer.spamString("010" + getID() + "p" + str6 + str5 + "-" + this.cloneNumber);
/* 652 */       saveCharsheet(false);
/*     */       
/* 654 */       this.pmPane.reflectNameChange();
/*     */     }
/*     */   }
/*     */   
/*     */   void setClearance(String paramString1, String paramString2)
/*     */   {
/* 660 */     String str1 = "";
/*     */     
/* 662 */     int i = evaluateClearance(paramString1);
/*     */     
/* 664 */     if (i < this.clearanceInt)
/*     */     {
/* 666 */       str1 = "demoted";
/* 667 */       if ((JPServer.soundIsOn) && (JPServer.soundMenu.promotedDemotedMenuItem.isSelected()))
/* 668 */         JPServer.soundPlayer.play(13);
/* 669 */       JPServer.spamString("020");
/*     */     }
/* 671 */     else if (i > this.clearanceInt)
/*     */     {
/* 673 */       str1 = "promoted";
/* 674 */       if ((JPServer.soundIsOn) && (JPServer.soundMenu.promotedDemotedMenuItem.isSelected()))
/* 675 */         JPServer.soundPlayer.play(12);
/* 676 */       JPServer.spamString("021");
/*     */     } else {
/* 678 */       return;
/*     */     }
/* 680 */     JPServer.absoluteSpam(getName() + " has been " + str1 + " to " + paramString2 + " clearance!");
/*     */     
/* 682 */     this.clearance = paramString1;
/* 683 */     this.clearanceInt = i;
/*     */     
/*     */     String str2;
/*     */     
/* 687 */     if (this.loggedIn) str2 = "y"; else {
/* 688 */       str2 = "n";
/*     */     }
/*     */     
/* 691 */     JPServer.repaintMenus();
/* 692 */     this.playerMenu.setText(getName());
/* 693 */     JPServer.spamString("010" + getID() + "p" + str2 + getName() + "-" + this.cloneNumber);
/* 694 */     saveCharsheet(false);
/*     */   }
/*     */   
/*     */   public int evaluateClearance(String paramString)
/*     */   {
/* 699 */     if (this.playerMenu != null)
/*     */     {
/* 701 */       if (paramString.equals("IR")) { this.playerMenu.playerClearanceMenu.securityInfraMenuItem.setSelected(true);return 0; }
/* 702 */       if (paramString.equals("R")) { this.playerMenu.playerClearanceMenu.securityRedMenuItem.setSelected(true);return 1; }
/* 703 */       if (paramString.equals("O")) { this.playerMenu.playerClearanceMenu.securityOrangeMenuItem.setSelected(true);return 2; }
/* 704 */       if (paramString.equals("Y")) { this.playerMenu.playerClearanceMenu.securityYellowMenuItem.setSelected(true);return 3; }
/* 705 */       if (paramString.equals("G")) { this.playerMenu.playerClearanceMenu.securityGreenMenuItem.setSelected(true);return 4; }
/* 706 */       if (paramString.equals("B")) { this.playerMenu.playerClearanceMenu.securityBlueMenuItem.setSelected(true);return 5; }
/* 707 */       if (paramString.equals("I")) { this.playerMenu.playerClearanceMenu.securityIndigoMenuItem.setSelected(true);return 6; }
/* 708 */       if (paramString.equals("V")) { this.playerMenu.playerClearanceMenu.securityVioletMenuItem.setSelected(true);return 7; }
/* 709 */       if (paramString.equals("U")) { this.playerMenu.playerClearanceMenu.securityUltraMenuItem.setSelected(true);return 8;
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 714 */       if (paramString.equals("IR")) return 0;
/* 715 */       if (paramString.equals("R")) return 1;
/* 716 */       if (paramString.equals("O")) return 2;
/* 717 */       if (paramString.equals("Y")) return 3;
/* 718 */       if (paramString.equals("G")) return 4;
/* 719 */       if (paramString.equals("B")) return 5;
/* 720 */       if (paramString.equals("I")) return 6;
/* 721 */       if (paramString.equals("V")) return 7;
/* 722 */       if (paramString.equals("U")) { return 8;
/*     */       }
/*     */     }
/* 725 */     return -99;
/*     */   }
/*     */   
/*     */   public int getClearanceInt()
/*     */   {
/* 730 */     return this.clearanceInt;
/*     */   }
/*     */   
/*     */   public void setThread(ServerChatThread paramServerChatThread)
/*     */   {
/* 735 */     this.chatThread = paramServerChatThread;
/*     */   }
/*     */   
/*     */   public ServerChatThread getThread()
/*     */   {
/* 740 */     return this.chatThread;
/*     */   }
/*     */   
/*     */   public JCheckBox getExcludeCheckBox()
/*     */   {
/* 745 */     return this.globalExcludeCheckBox;
/*     */   }
/*     */   
/*     */   public void sendGlobalPM(String paramString)
/*     */   {
/* 750 */     if ((this.chatThread != null) && 
/* 751 */       (!this.globalExcludeCheckBox.isSelected()))
/*     */     {
/* 753 */       specificSend("200" + getID() + JPServer.myPlayer.getID() + paramString);
/* 754 */       this.pmPane.addMyMessage(paramString);
/*     */     }
/*     */   }
/*     */   
/*     */   public void sendingGlobalPM()
/*     */   {
/* 760 */     if (this.chatThread != null) {
/* 761 */       if (!this.globalExcludeCheckBox.isSelected()) specificSend("210" + getID()); else {
/* 762 */         specificSend("21099");
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public synchronized void specificSend(String paramString) {
/* 768 */     if (this.chatThread == null)
/*     */     {
/* 770 */       if (this.debugSpecific) { JParanoia.errorMessage("No chat thread", getName() + " does not have a\n" + "chat thread. (Probably due to not\n" + "being logged in.)");
/*     */       }
/*     */       
/*     */ 
/*     */     }
/*     */     else {
/* 776 */       this.chatThread.out.println(paramString);
/*     */     }
/*     */   }
/*     */   
/*     */   public void sendCharsheet() {
/* 781 */     System.out.println("Sending " + getName() + " their char sheet...");
/* 782 */     specificSend("400");
/* 783 */     try { specificSend(JPServer.stripComments(this.characterSheet.getText(0, this.characterSheet.getLength())));
/* 784 */     } catch (Exception localException) { System.out.println("Bad location exception while sending charsheet."); }
/* 785 */     specificSend("402");
/*     */   }
/*     */   
/*     */   public void sendLastSavedCharsheet()
/*     */   {
/* 790 */     System.out.println("Sending " + getName() + " their last saved char sheet...");
/* 791 */     specificSend("400");
/*     */     try {
/* 793 */       this.reader = new BufferedReader(new java.io.FileReader(this.dataFile));
/* 794 */       StringBuffer localStringBuffer = new StringBuffer();
/* 795 */       String str = this.reader.readLine();
/* 796 */       str = this.reader.readLine();
/* 797 */       while (str != null)
/*     */       {
/* 799 */         if (!str.startsWith("#")) localStringBuffer.append(str + "\n");
/* 800 */         str = this.reader.readLine();
/*     */       }
/* 802 */       this.reader.close();
/* 803 */       specificSend(JPServer.stripComments(localStringBuffer.toString()));
/*     */     } catch (Exception localException) {
/* 805 */       System.out.println("Bad location exception while sending charsheet."); }
/* 806 */     specificSend("402");
/*     */   }
/*     */   
/*     */   public void saveCharsheet(boolean paramBoolean)
/*     */   {
/* 811 */     String str = null;
/*     */     
/*     */ 
/*     */ 
/*     */     try
/*     */     {
/* 817 */       str = this.dataFile;
/*     */       
/*     */ 
/*     */ 
/* 821 */       int i = 0;
/*     */       
/* 823 */       while ((i = str.indexOf("%20")) != -1)
/*     */       {
/* 825 */         str = str.substring(0, i) + " " + str.substring(i + 3);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 833 */       writer = new FileWriter(str);
/*     */       
/* 835 */       writer.write(toString() + "\n");
/* 836 */       writer.write(this.characterSheet.getText(0, this.characterSheet.getLength()));
/* 837 */       writer.flush();
/* 838 */       writer.close();
/*     */       
/* 840 */       charsheetSaved();
/*     */       
/*     */ 
/* 843 */       if (paramBoolean)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 849 */         System.out.println("saveCharsheet(...): calling serverPlayer.sendCharsheet(...)");
/*     */         
/* 851 */         sendCharsheet();
/*     */         
/* 853 */         if ((JPServer.soundIsOn) && (JPServer.soundMenu.charSheetAlertMenuItem.isSelected())) { JPServer.soundPlayer.play(20);
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/* 859 */       System.out.println("An exception ocurred while attempting to write/send the file.");
/* 860 */       System.out.println("RAW outputFilepath == \"" + str + "\"");
/* 861 */       localException.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean hasUnsavedCharsheet() {
/* 866 */     return this.unsavedCharsheet;
/*     */   }
/*     */   
/*     */   public void charsheetUpdated() {
/* 870 */     numUnsavedCharsheets += 1;
/* 871 */     this.unsavedCharsheet = true;
/* 872 */     System.out.println(getName() + "charsheetUpdated(): numUnsavedCharsheets == " + numUnsavedCharsheets);
/*     */   }
/*     */   
/*     */   public void charsheetSaved()
/*     */   {
/* 877 */     if (this.unsavedCharsheet)
/*     */     {
/* 879 */       this.unsavedCharsheet = false;
/* 880 */       numUnsavedCharsheets -= 1;
/* 881 */       System.out.println(getName() + "charsheetSaved(): numUnsavedCharsheets == " + numUnsavedCharsheets);
/*     */     }
/*     */   }
/*     */   
/*     */   public void promptPlayerForCombatTurn()
/*     */   {
/* 887 */     specificSend("600");
/*     */   }
/*     */   
/*     */   public void playerAbortedTurn()
/*     */   {
/* 892 */     JPServer.absoluteSpam(getName() + " waived their right to a combat turn. (Not too bright)");
/*     */     
/* 894 */     JPServer.combatFrame.removePlayer(this);
/*     */   }
/*     */   
/*     */   public void kickPlayer()
/*     */   {
/* 899 */     System.out.print("Attempting to kick " + getName() + "... ");
/*     */     
/* 901 */     if (this.chatThread == null)
/*     */     {
/* 903 */       JParanoia.errorMessage("No chat thread", getName() + " does not have a\n" + "chat thread. (Probably due to not\n" + "being logged in.)");
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 908 */       return;
/*     */     }
/*     */     
/* 911 */     this.chatThread.out.println("999*** You have been kicked ***");
/* 912 */     this.chatThread.disconnect(true);
/* 913 */     JPServer.absoluteSpam("( * Kicked by server * )");
/* 914 */     System.out.println("Player kicked.");
/*     */   }
/*     */   
/*     */   public void changePassword()
/*     */   {
/* 919 */     String str = null;
/* 920 */     if ((str = JOptionPane.showInputDialog(JPServer.frame, "Enter a new password for " + toString(), "New Password", -1)) != null)
/*     */     {
/*     */ 
/*     */ 
/* 924 */       this.password = str;
/* 925 */       this.playerMenu.currentPasswordLabel.setText("    Password: " + this.password);
/*     */     }
/*     */   }
/*     */   
/*     */   public NPCMenu getNpcMenu()
/*     */   {
/* 931 */     return this.npcMenu;
/*     */   }
/*     */   
/*     */   public void npcRename()
/*     */   {
/* 936 */     String str = null;
/* 937 */     if (((str = (String)JOptionPane.showInputDialog(JPServer.frame, "Enter a new name for " + toString(), "New Name", -1, null, null, this.name)) != null) && (!str.equals("")))
/*     */     {
/*     */ 
/*     */ 
/* 941 */       this.name = str;
/*     */       
/* 943 */       JPServer.repaintMenus();
/* 944 */       this.npcMenu.setText(getName());
/* 945 */       JPServer.spamString("010" + getID() + "n" + "n" + str);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\ServerPlayer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */