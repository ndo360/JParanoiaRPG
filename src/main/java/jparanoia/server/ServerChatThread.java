/*     */ package jparanoia.server;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.net.Socket;
/*     */ import java.util.Vector;
/*     */ import jparanoia.shared.JPVersionNumber;
/*     */ 
/*     */ class ServerChatThread extends Thread
/*     */ {
/*  13 */   public Socket socket = null;
/*  14 */   public PrintWriter out = null;
/*  15 */   public BufferedReader in = null;
/*     */   
/*  17 */   ServerPlayer thisPlayer = new ServerPlayer(999, "Unknown Player", false, "llama", "jazzer.txt");
/*  18 */   String inputLine = "hello";
/*  19 */   boolean disconnectCalled = false;
/*  20 */   boolean hasSignedOn = false;
/*  21 */   boolean acceptedLogin = false;
/*  22 */   boolean observer = false;
/*  23 */   boolean listening = false;
/*     */   
/*     */   public int playerID;
/*     */   
/*     */   public int threadNumber;
/*  28 */   String obsName = "";
/*  29 */   Vector obsNameVector = null;
/*     */   
/*     */   public ServerChatThread(Socket paramSocket)
/*     */   {
/*  33 */     super(JPServer.chatThreadGroup, "new JParanoia Thread");
/*  34 */     this.socket = paramSocket;
/*  35 */     this.thisPlayer.setLoggedIn(false);
/*     */   }
/*     */   
/*     */ 
/*     */   public void run()
/*     */   {
/*     */     try
/*     */     {
/*  43 */       this.out = new PrintWriter(this.socket.getOutputStream(), true);
/*  44 */       this.in = new BufferedReader(new java.io.InputStreamReader(this.socket.getInputStream()));
/*  45 */       this.listening = true;
/*     */       
/*     */ 
/*     */ 
/*  49 */       playerLogin();
/*     */       
/*  51 */       if (!this.acceptedLogin) { disconnect(true);this.listening = false;return;
/*     */       }
/*  53 */       while (this.listening)
/*     */       {
/*     */ 
/*  56 */         String str = this.in.readLine();
/*     */         
/*     */ 
/*     */         try
/*     */         {
/*  61 */           switch (Integer.parseInt(str.substring(0, 3)))
/*     */           {
/*     */           case 86: 
/*  64 */             if (this.listening) disconnect(false); this.listening = false; break;
/*  65 */           case 99:  JPServer.spamString(str); if (JPServer.hearObserversMenuItem.isSelected()) JPServer.observerChat(str.substring(3));
/*     */             break; case 100:  JPServer.spamString(str);JPServer.generalChat(str.substring(3)); break;
/*  67 */           case 110:  JPServer.spamString(str);JPServer.actionChat(str.substring(3)); break;
/*  68 */           case 120:  JPServer.spamString(str);JPServer.speechChat(str.substring(3)); break;
/*  69 */           case 130:  JPServer.spamString(str);JPServer.thoughtChat(str.substring(3)); break;
/*  70 */           case 199:  JPServer.absoluteChat(str.substring(3)); break;
/*  71 */           case 200:  JPServer.privateMessageHandler(str.substring(3), true); break;
/*  72 */           case 400:  this.obsName = str.substring(3);jparanoia.shared.JParanoia.addObsName(this.obsName); if (jparanoia.shared.JParanoia.announceObservers) JPServer.observerHasJoined(this.obsName);
/*     */             break; case 601:  JPServer.combatFrame.receiveCombatTurn(this.thisPlayer, str.substring(3)); break;
/*  74 */           case 602:  this.thisPlayer.playerAbortedTurn(); break;
/*     */           
/*     */           default: 
/*  77 */             System.out.println("\nError: unknown command type\n" + str + "\n");
/*  78 */             JPServer.absoluteChat("\nError: unknown command type\n" + str + "\n");
/*     */           }
/*     */           
/*     */         }
/*     */         catch (NumberFormatException localNumberFormatException)
/*     */         {
/*  84 */           this.out.println("199* * * WARNING * * *\n199Your client has sent an invalid command.\n199You will now be disconnected. Exit and relaunch the client.\n199If this problem persists, submit a bug report on the JParanoia website.\n199(http://www.byronbarry.com/jparanoia/)");
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  90 */           this.out.println("086Dropping due to invalid command.");
/*     */           
/*  92 */           if (!this.disconnectCalled) { disconnect(true);
/*     */           }
/*     */         }
/*     */         catch (NullPointerException localNullPointerException)
/*     */         {
/*  97 */           this.out.println("086If you are reading this sentence, please alert the author via a bug report.");
/*  98 */           System.out.println("NPE in ServerChatThread.run()");
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 104 */           if (!this.disconnectCalled) { disconnect(true);
/*     */           }
/*     */         }
/*     */       }
/*     */       
/* 109 */       System.out.println("ServerChatThread.run() exiting naturally.");
/*     */ 
/*     */     }
/*     */     catch (java.net.SocketException localSocketException)
/*     */     {
/*     */ 
/* 115 */       if ((!this.observer) && (!this.disconnectCalled))
/*     */       {
/* 117 */         System.out.println(this.thisPlayer.getName() + " unexpectedly lost their connection. (SocketException)");
/* 118 */         JPServer.spamString("199" + this.thisPlayer.getName() + " unexpectedly lost their connection. (SocketException)");
/* 119 */         JPServer.absoluteChat(this.thisPlayer.getName() + " unexpectedly lost their connection. (SocketException)");
/*     */       }
/*     */       
/* 122 */       disconnect(true);
/*     */ 
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/*     */ 
/* 128 */       if ((!this.observer) && (!this.disconnectCalled))
/*     */       {
/* 130 */         System.out.println(this.thisPlayer.getName() + " unexpectedly lost their connection. (IOException)");
/* 131 */         JPServer.spamString("199" + this.thisPlayer.getName() + " unexpectedly lost their connection. (IOException)");
/* 132 */         JPServer.absoluteChat(this.thisPlayer.getName() + " unexpectedly lost their connection. (IOException)");
/*     */       }
/*     */       
/*     */ 
/* 136 */       disconnect(true);
/*     */       
/* 138 */       localIOException.printStackTrace();
/*     */ 
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/* 143 */       if ((!this.observer) && (!this.disconnectCalled))
/*     */       {
/* 145 */         System.out.println(this.thisPlayer.getName() + " unexpectedly lost their connection. (Exception)");
/* 146 */         JPServer.spamString("199" + this.thisPlayer.getName() + " unexpectedly lost their connection. (Exception)");
/* 147 */         JPServer.absoluteChat(this.thisPlayer.getName() + " unexpectedly lost their connection. (Exception)");
/*     */       }
/*     */       
/*     */ 
/* 151 */       disconnect(true);
/*     */       
/* 153 */       localException.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void playerLogin()
/*     */   {
/* 161 */     int i = 999;
/*     */     
/* 163 */     String str2 = "hello";
/*     */     
/*     */     try
/*     */     {
/* 167 */       this.out.println("902");
/* 168 */       while (!str2.equals("901")) { str2 = this.in.readLine();
/*     */       }
/* 170 */       if (!checkVersion()) { return;
/*     */       }
/* 172 */       if (!challenge()) { return;
/*     */       }
/* 174 */       this.out.println("900 Welcome to JParanoia");
/* 175 */       this.out.println("900----------------------");
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 181 */       for (int j = 0; j < JPServer.numberOfPlayers; j++)
/*     */       {
/* 183 */         if ((JPServer.players[j].isAnActualPlayer()) && (!JPServer.players[j].isLoggedIn())) {
/* 184 */           this.out.println("900" + j + "  " + JPServer.players[j].getName());
/*     */         }
/*     */       }
/* 187 */       if (JPServer.allowObservers) {
/* 188 */         this.out.println("90099 observe");
/*     */       }
/* 190 */       while (!this.acceptedLogin)
/*     */       {
/* 192 */         this.out.println("950");
/* 193 */         this.out.println("900----------------------");
/* 194 */         this.out.println("900 Enter your player number.");
/*     */         
/*     */         try
/*     */         {
/* 198 */           str2 = this.in.readLine();
/* 199 */           if ((str2 == null) || (str2.equals("086"))) return;
/* 200 */           i = Integer.parseInt(str2.substring(3));
/*     */           
/*     */ 
/*     */ 
/* 204 */           if (i < JPServer.numberOfPCs)
/*     */           {
/* 206 */             System.out.println("New user attempting to connect as " + JPServer.players[i].getName());
/*     */             
/* 208 */             if (JPServer.players[i].isLoggedIn()) { this.out.println("910 Invalid entry. That player is already logged in. (Nice try.)");
/*     */             }
/*     */             else
/*     */             {
/* 212 */               this.out.println("900 Enter your password.");
/* 213 */               String str1 = this.in.readLine().substring(3);
/* 214 */               this.acceptedLogin = JPServer.players[i].checkPassword(str1);
/* 215 */               if (!this.acceptedLogin) { this.out.println("910 Incorrect password.");
/*     */               }
/*     */             }
/*     */           }
/* 219 */           else if (i == 99) {
/* 220 */             if (JPServer.allowObservers) {
/* 221 */               this.out.println("040");
/* 222 */               this.out.println("900 Entering as an observer...");
/* 223 */               this.observer = true;
/* 224 */               this.acceptedLogin = true;
/*     */             } else {
/* 226 */               this.out.println("910 Observers not currently allowed on this server.");
/*     */             }
/* 228 */           } else { this.out.println("910 Invalid entry. That is not a valid player ID number.");
/*     */           }
/*     */         }
/*     */         catch (NumberFormatException localNumberFormatException)
/*     */         {
/* 233 */           this.out.println("910 Invalid entry. Not a number.");
/*     */         }
/*     */       }
/*     */       
/* 237 */       if (!this.observer)
/*     */       {
/*     */ 
/* 240 */         this.playerID = i;
/*     */         
/* 242 */         this.thisPlayer = JPServer.players[this.playerID];
/*     */         
/*     */ 
/* 245 */         this.thisPlayer.setThread(this);
/*     */         
/*     */ 
/*     */ 
/* 249 */         this.out.println("041");
/* 250 */         this.thisPlayer.setRealName(this.in.readLine());
/* 251 */         this.out.println("900 Password accepted. Entering server...");
/*     */       }
/* 253 */       this.out.println("199-----------------------------------------");
/* 254 */       this.out.println("904" + JPServer.numberOfPlayers);
/* 255 */       if (this.in.readLine().equals("numberOfPlayers received"))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 261 */         for (int k = 0; k < JPServer.numberOfPlayers; k++)
/*     */         {
/* 263 */           String str5 = "" + k;
/* 264 */           if (str5.length() < 2) str5 = "0" + str5;
/* 265 */           String str3 = "n";
/* 266 */           String str4 = "n";
/* 267 */           if (JPServer.players[k].isAnActualPlayer()) str3 = "p";
/* 268 */           if (JPServer.players[k].isLoggedIn()) str4 = "y";
/* 269 */           this.out.println("010" + str5 + str3 + str4 + JPServer.players[k].toString());
/*     */         }
/*     */       }
/*     */       
/* 273 */       if (this.observer) { this.out.println("90699");
/*     */       }
/*     */       else {
/* 276 */         this.out.println("906" + i);
/* 277 */         setName(this.thisPlayer.getID());
/*     */         
/*     */ 
/*     */ 
/* 281 */         if (JPServer.frozen) this.out.println("052");
/* 282 */         if (this.thisPlayer.isMuted()) { this.out.println("051" + this.thisPlayer.getID());
/*     */         }
/*     */       }
/* 285 */       if (JPServer.hearObserversMenuItem.isSelected()) this.out.println("097"); else {
/* 286 */         this.out.println("098");
/*     */       }
/* 288 */       this.out.println("070" + JPServer.computerFontIncrease);
/* 289 */       this.out.println("074" + JPServer.maxNumClones);
/* 290 */       this.out.println("013" + JPServer.titleMessage);
/*     */       
/*     */ 
/* 293 */       if (JPServer.optionsMenu.useAnnouncementMenuItem.isSelected()) {
/* 294 */         this.out.println(JPServer.getAnnouncement());
/*     */ 
/*     */       }
/*     */       
/*     */ 
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/*     */ 
/* 303 */       System.out.println("IO exception during connect()");
/* 304 */       localIOException.printStackTrace();
/*     */     }
/*     */     
/* 307 */     if (!this.observer)
/*     */     {
/*     */ 
/*     */ 
/* 311 */       JPServer.spamString("011" + this.playerID);
/* 312 */       JPServer.playerHasJoined(this.playerID);
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 317 */       JPServer.numberOfConnectedObservers += 1;
/* 318 */       System.out.println("JPServer.numberOfConnectedObservers == " + JPServer.numberOfConnectedObservers);
/*     */     }
/*     */     
/* 321 */     synchronized (JPServer.chatThreads)
/*     */     {
/* 323 */       this.threadNumber = JPServer.numberOfConnectedClients;
/*     */       
/* 325 */       JPServer.chatThreads.add(this);
/* 326 */       JPServer.numberOfConnectedClients = JPServer.chatThreads.size();
/* 327 */       System.out.println("JPServer.numberOfConnectedClients == " + JPServer.numberOfConnectedClients);
/* 328 */       if ((!JPServer.combatButton.isEnabled()) && (JPServer.numberOfConnectedClients - JPServer.numberOfConnectedObservers > 1)) {
/* 329 */         JPServer.combatButton.setEnabled(true);
/*     */       }
/*     */     }
/* 332 */     if (!this.observer) { this.thisPlayer.sendLastSavedCharsheet();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean checkVersion()
/*     */   {
/*     */     try
/*     */     {
/* 340 */       this.out.println("955 Identify your version");
/*     */       
/*     */ 
/* 343 */       if (new JPVersionNumber(this.in.readLine()).compareTo(JPServer.MIN_COMPATIBLE_VERSION_NUMBER) < 0)
/*     */       {
/* 345 */         this.out.println("961Incompatible version. You must have JParanoia Client version " + JPServer.MIN_COMPATIBLE_VERSION_NUMBER.toString() + " or greater to connect to this server. The JParanoia website is: " + "http://www.byronbarry.com/jparanoia/");
/*     */         
/*     */ 
/*     */ 
/* 349 */         System.out.println("Notice: Someone has attempted to connect using a client version that is too old.");
/*     */         
/* 351 */         return false;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 356 */       this.out.println("960" + JPServer.VERSION_NUMBER.toString());
/* 357 */       String str = this.in.readLine();
/* 358 */       if (str.substring(0, 3).equals("961"))
/*     */       {
/* 360 */         JPServer.absoluteChat(str.substring(3));
/* 361 */         return false;
/*     */       }
/*     */       
/* 364 */       if (str.equals("960")) { return true;
/*     */       }
/* 366 */       JPServer.absoluteChat("An error occured: unrecognized version code.");return false;
/*     */ 
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/*     */ 
/* 372 */       System.out.println("Error: An I/O Exception occured during version check."); }
/* 373 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean challenge()
/*     */   {
/*     */     try
/*     */     {
/* 381 */       java.util.Random localRandom = new java.util.Random();
/*     */       
/* 383 */       int i = localRandom.nextInt(40000) + 10000;
/*     */       
/* 385 */       if (i % 10 == 0) { i++;
/*     */       }
/* 387 */       String str1 = "" + i;
/*     */       
/* 389 */       this.out.println("970" + str1);
/*     */       
/* 391 */       String str2 = this.in.readLine();
/*     */       
/* 393 */       int j = Integer.parseInt(str2);
/*     */       
/* 395 */       int k = Integer.parseInt(str1.substring(2));
/*     */       
/* 397 */       if (j % k == 0)
/*     */       {
/* 399 */         System.out.println("Client passed challenge.");
/* 400 */         return true;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 405 */       JPServer.absoluteChat("WARNING: Someone has attempted (and failed) to connect using an invalid client application. It is extremely likely that this is not an accident.");
/*     */       
/*     */ 
/* 408 */       return false;
/*     */ 
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/*     */ 
/* 414 */       System.out.println("Error: An I/O Exception occured during check."); }
/* 415 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void disconnect(boolean paramBoolean)
/*     */   {
/* 421 */     this.disconnectCalled = true;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 431 */     this.listening = false;
/*     */     
/*     */ 
/*     */ 
/* 435 */     if (paramBoolean) { this.out.println("086");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     try
/*     */     {
/* 443 */       this.out.close();
/* 444 */       this.in.close();
/* 445 */       this.socket.close();
/*     */ 
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/* 450 */       System.out.println("Unable to close Writer, Reader or Socket.");
/* 451 */       localIOException.printStackTrace();
/*     */     }
/*     */     
/*     */ 
/* 455 */     if (this.observer)
/*     */     {
/* 457 */       JPServer.numberOfConnectedObservers -= 1;
/* 458 */       System.out.println("JPServer.numberOfConnectedObservers == " + JPServer.numberOfConnectedObservers);
/* 459 */       jparanoia.shared.JParanoia.removeObsName(this.obsName);
/* 460 */       if (jparanoia.shared.JParanoia.announceObservers) { JPServer.observerHasLeft(this.obsName);
/*     */       }
/*     */     }
/* 463 */     if (!this.thisPlayer.isLoggedIn()) { System.out.println("Unknown user disconnected. (Not signed in.)");
/*     */     }
/*     */     else
/*     */     {
/* 467 */       JPServer.spamString("012" + this.playerID);
/* 468 */       System.out.print(this.thisPlayer.getName() + " disconnected... ");
/* 469 */       this.thisPlayer.setThread(null);
/*     */     }
/*     */     
/*     */ 
/* 473 */     synchronized (JPServer.chatThreads)
/*     */     {
/* 475 */       if ((this.observer) || (this.thisPlayer.isLoggedIn())) JPServer.chatThreads.remove(this.threadNumber);
/* 476 */       if ((!this.observer) && (this.thisPlayer.isLoggedIn())) JPServer.playerHasLeft(this.playerID);
/* 477 */       JPServer.numberOfConnectedClients = JPServer.chatThreads.size();
/* 478 */       System.out.println("JPServer.numberOfConnectedClients == " + JPServer.numberOfConnectedClients);
/* 479 */       JPServer.reassignThreadNumbers();
/*     */     }
/*     */     
/* 482 */     if ((JPServer.combatButton.isEnabled()) && (JPServer.numberOfConnectedClients - JPServer.numberOfConnectedObservers < 2)) {
/* 483 */       JPServer.combatButton.setEnabled(false);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\ServerChatThread.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */