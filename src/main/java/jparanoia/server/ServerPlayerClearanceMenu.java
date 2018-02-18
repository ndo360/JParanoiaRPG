/*     */ package jparanoia.server;
/*     */ 
/*     */ import javax.swing.JRadioButtonMenuItem;
/*     */ 
/*     */ public class ServerPlayerClearanceMenu extends javax.swing.JMenu { JRadioButtonMenuItem securityInfraMenuItem;
/*     */   JRadioButtonMenuItem securityRedMenuItem;
/*     */   JRadioButtonMenuItem securityOrangeMenuItem;
/*     */   JRadioButtonMenuItem securityYellowMenuItem;
/*     */   JRadioButtonMenuItem securityGreenMenuItem;
/*     */   JRadioButtonMenuItem securityBlueMenuItem;
/*     */   JRadioButtonMenuItem securityIndigoMenuItem;
/*     */   JRadioButtonMenuItem securityVioletMenuItem;
/*  13 */   JRadioButtonMenuItem securityUltraMenuItem; javax.swing.ButtonGroup bgroup = new javax.swing.ButtonGroup();
/*     */   
/*     */   final ServerPlayer player;
/*     */   
/*     */   public ServerPlayerClearanceMenu(ServerPlayer paramServerPlayer)
/*     */   {
/*  19 */     super("Set clearance");
/*  20 */     this.player = paramServerPlayer;
/*     */     
/*  22 */     this.securityInfraMenuItem = new JRadioButtonMenuItem("IR");
/*  23 */     this.securityInfraMenuItem.addActionListener(new java.awt.event.ActionListener() {
/*     */       public void actionPerformed(java.awt.event.ActionEvent paramAnonymousActionEvent) {
/*  25 */         ServerPlayerClearanceMenu.this.player.setClearance("IR", "Infrared");
/*     */       }
/*  27 */     });
/*  28 */     add(this.securityInfraMenuItem);
/*  29 */     this.bgroup.add(this.securityInfraMenuItem);
/*     */     
/*  31 */     this.securityRedMenuItem = new JRadioButtonMenuItem("R");
/*  32 */     this.securityRedMenuItem.addActionListener(new java.awt.event.ActionListener() {
/*     */       public void actionPerformed(java.awt.event.ActionEvent paramAnonymousActionEvent) {
/*  34 */         ServerPlayerClearanceMenu.this.player.setClearance("R", "Red");
/*     */       }
/*  36 */     });
/*  37 */     add(this.securityRedMenuItem);
/*  38 */     this.bgroup.add(this.securityRedMenuItem);
/*     */     
/*  40 */     this.securityOrangeMenuItem = new JRadioButtonMenuItem("O");
/*  41 */     this.securityOrangeMenuItem.addActionListener(new java.awt.event.ActionListener() {
/*     */       public void actionPerformed(java.awt.event.ActionEvent paramAnonymousActionEvent) {
/*  43 */         ServerPlayerClearanceMenu.this.player.setClearance("O", "Orange");
/*     */       }
/*  45 */     });
/*  46 */     add(this.securityOrangeMenuItem);
/*  47 */     this.bgroup.add(this.securityOrangeMenuItem);
/*     */     
/*  49 */     this.securityYellowMenuItem = new JRadioButtonMenuItem("Y");
/*  50 */     this.securityYellowMenuItem.addActionListener(new java.awt.event.ActionListener() {
/*     */       public void actionPerformed(java.awt.event.ActionEvent paramAnonymousActionEvent) {
/*  52 */         ServerPlayerClearanceMenu.this.player.setClearance("Y", "Yellow");
/*     */       }
/*  54 */     });
/*  55 */     add(this.securityYellowMenuItem);
/*  56 */     this.bgroup.add(this.securityYellowMenuItem);
/*     */     
/*     */ 
/*  59 */     this.securityGreenMenuItem = new JRadioButtonMenuItem("G");
/*  60 */     this.securityGreenMenuItem.addActionListener(new java.awt.event.ActionListener() {
/*     */       public void actionPerformed(java.awt.event.ActionEvent paramAnonymousActionEvent) {
/*  62 */         ServerPlayerClearanceMenu.this.player.setClearance("G", "Green");
/*     */       }
/*  64 */     });
/*  65 */     add(this.securityGreenMenuItem);
/*  66 */     this.bgroup.add(this.securityGreenMenuItem);
/*     */     
/*     */ 
/*  69 */     this.securityBlueMenuItem = new JRadioButtonMenuItem("B");
/*  70 */     this.securityBlueMenuItem.addActionListener(new java.awt.event.ActionListener() {
/*     */       public void actionPerformed(java.awt.event.ActionEvent paramAnonymousActionEvent) {
/*  72 */         ServerPlayerClearanceMenu.this.player.setClearance("B", "Blue");
/*     */       }
/*  74 */     });
/*  75 */     add(this.securityBlueMenuItem);
/*  76 */     this.bgroup.add(this.securityBlueMenuItem);
/*     */     
/*     */ 
/*  79 */     this.securityIndigoMenuItem = new JRadioButtonMenuItem("I");
/*  80 */     this.securityIndigoMenuItem.addActionListener(new java.awt.event.ActionListener() {
/*     */       public void actionPerformed(java.awt.event.ActionEvent paramAnonymousActionEvent) {
/*  82 */         ServerPlayerClearanceMenu.this.player.setClearance("I", "Indigo");
/*     */       }
/*  84 */     });
/*  85 */     add(this.securityIndigoMenuItem);
/*  86 */     this.bgroup.add(this.securityIndigoMenuItem);
/*     */     
/*     */ 
/*  89 */     this.securityVioletMenuItem = new JRadioButtonMenuItem("V");
/*  90 */     this.securityVioletMenuItem.addActionListener(new java.awt.event.ActionListener() {
/*     */       public void actionPerformed(java.awt.event.ActionEvent paramAnonymousActionEvent) {
/*  92 */         ServerPlayerClearanceMenu.this.player.setClearance("V", "Violet");
/*     */       }
/*  94 */     });
/*  95 */     add(this.securityVioletMenuItem);
/*  96 */     this.bgroup.add(this.securityVioletMenuItem);
/*     */     
/*     */ 
/*  99 */     this.securityUltraMenuItem = new JRadioButtonMenuItem("U");
/* 100 */     this.securityUltraMenuItem.addActionListener(new java.awt.event.ActionListener() {
/*     */       public void actionPerformed(java.awt.event.ActionEvent paramAnonymousActionEvent) {
/* 102 */         ServerPlayerClearanceMenu.this.player.setClearance("U", "Ultraviolet");
/*     */       }
/* 104 */     });
/* 105 */     add(this.securityUltraMenuItem);
/* 106 */     this.bgroup.add(this.securityUltraMenuItem);
/*     */     
/*     */ 
/* 109 */     switch (this.player.getClearanceInt()) {
/*     */     case 0: 
/* 111 */       this.securityInfraMenuItem.setSelected(true); break;
/* 112 */     case 1:  this.securityRedMenuItem.setSelected(true); break;
/* 113 */     case 2:  this.securityOrangeMenuItem.setSelected(true); break;
/* 114 */     case 3:  this.securityYellowMenuItem.setSelected(true); break;
/* 115 */     case 4:  this.securityGreenMenuItem.setSelected(true); break;
/* 116 */     case 5:  this.securityBlueMenuItem.setSelected(true); break;
/* 117 */     case 6:  this.securityIndigoMenuItem.setSelected(true); break;
/* 118 */     case 7:  this.securityVioletMenuItem.setSelected(true); break;
/* 119 */     case 8:  this.securityUltraMenuItem.setSelected(true);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\ServerPlayerClearanceMenu.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */