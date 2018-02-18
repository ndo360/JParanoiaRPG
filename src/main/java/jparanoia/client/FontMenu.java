/*     */ package jparanoia.client;
/*     */ 
/*     */ import java.awt.event.ActionEvent;
/*     */ import javax.swing.JRadioButtonMenuItem;
/*     */ 
/*     */ public class FontMenu extends javax.swing.JMenu
/*     */ {
/*     */   javax.swing.JMenu fontFamilyMenu;
/*     */   javax.swing.JMenu fontSizeMenu;
/*     */   javax.swing.JCheckBoxMenuItem fontBoldMenuItem;
/*     */   JRadioButtonMenuItem serifButton;
/*     */   JRadioButtonMenuItem sansSerifButton;
/*     */   JRadioButtonMenuItem monospacedButton;
/*     */   JRadioButtonMenuItem size10Button;
/*     */   JRadioButtonMenuItem size12Button;
/*     */   
/*     */   public FontMenu(String paramString)
/*     */   {
/*  19 */     super(paramString);
/*     */     
/*  21 */     this.fontFamilyMenu = new javax.swing.JMenu("Family");
/*     */     
/*  23 */     this.serifButton = new JRadioButtonMenuItem("Serif");
/*  24 */     this.serifButton.addActionListener(new java.awt.event.ActionListener() {
/*     */       public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
/*  26 */         JPClient.textAttributes.addAttribute(javax.swing.text.StyleConstants.FontConstants.Family, "Serif");
/*     */       }
/*  28 */     });
/*  29 */     this.sansSerifButton = new JRadioButtonMenuItem("Sans-Serif");
/*  30 */     this.sansSerifButton.addActionListener(new java.awt.event.ActionListener() {
/*     */       public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
/*  32 */         JPClient.textAttributes.addAttribute(javax.swing.text.StyleConstants.FontConstants.Family, "SansSerif");
/*     */       }
/*  34 */     });
/*  35 */     this.monospacedButton = new JRadioButtonMenuItem("Monospaced");
/*  36 */     this.monospacedButton.addActionListener(new java.awt.event.ActionListener() {
/*     */       public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
/*  38 */         JPClient.textAttributes.addAttribute(javax.swing.text.StyleConstants.FontConstants.Family, "Monospaced");
/*     */       }
/*  40 */     });
/*  41 */     javax.swing.ButtonGroup localButtonGroup1 = new javax.swing.ButtonGroup();
/*  42 */     localButtonGroup1.add(this.serifButton);
/*  43 */     localButtonGroup1.add(this.sansSerifButton);
/*  44 */     localButtonGroup1.add(this.monospacedButton);
/*     */     
/*  46 */     this.serifButton.setSelected(JPClient.prefs.getPref(16).equals("Serif"));
/*  47 */     this.sansSerifButton.setSelected(JPClient.prefs.getPref(16).equals("SansSerif"));
/*  48 */     this.monospacedButton.setSelected(JPClient.prefs.getPref(16).equals("Monospaced"));
/*     */     
/*  50 */     this.fontFamilyMenu.add(this.serifButton);
/*  51 */     this.fontFamilyMenu.add(this.sansSerifButton);
/*  52 */     this.fontFamilyMenu.add(this.monospacedButton);
/*     */     
/*  54 */     add(this.fontFamilyMenu);
/*     */     
/*  56 */     this.fontSizeMenu = new javax.swing.JMenu("Size");
/*     */     
/*  58 */     this.size10Button = new JRadioButtonMenuItem("10");
/*  59 */     this.size10Button.addActionListener(new java.awt.event.ActionListener() {
/*     */       public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
/*  61 */         JPClient.mainFontSize = new Integer(10);
/*  62 */         JPClient.textAttributes.addAttribute(javax.swing.text.StyleConstants.FontConstants.Size, new Integer(10));
/*     */       }
/*  64 */     });
/*  65 */     this.size12Button = new JRadioButtonMenuItem("12");
/*  66 */     this.size12Button.addActionListener(new java.awt.event.ActionListener() {
/*     */       public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
/*  68 */         JPClient.mainFontSize = new Integer(12);
/*  69 */         JPClient.textAttributes.addAttribute(javax.swing.text.StyleConstants.FontConstants.Size, new Integer(12));
/*     */       }
/*  71 */     });
/*  72 */     this.size14Button = new JRadioButtonMenuItem("14");
/*  73 */     this.size14Button.addActionListener(new java.awt.event.ActionListener() {
/*     */       public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
/*  75 */         JPClient.mainFontSize = new Integer(14);
/*  76 */         JPClient.textAttributes.addAttribute(javax.swing.text.StyleConstants.FontConstants.Size, new Integer(14));
/*     */       }
/*  78 */     });
/*  79 */     this.size16Button = new JRadioButtonMenuItem("16");
/*  80 */     this.size16Button.addActionListener(new java.awt.event.ActionListener() {
/*     */       public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
/*  82 */         JPClient.mainFontSize = new Integer(16);
/*  83 */         JPClient.textAttributes.addAttribute(javax.swing.text.StyleConstants.FontConstants.Size, new Integer(16));
/*     */       }
/*  85 */     });
/*  86 */     this.size18Button = new JRadioButtonMenuItem("18");
/*  87 */     this.size18Button.addActionListener(new java.awt.event.ActionListener() {
/*     */       public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
/*  89 */         JPClient.mainFontSize = new Integer(18);
/*  90 */         JPClient.textAttributes.addAttribute(javax.swing.text.StyleConstants.FontConstants.Size, new Integer(18));
/*     */       }
/*  92 */     });
/*  93 */     this.size24Button = new JRadioButtonMenuItem("24");
/*  94 */     this.size24Button.addActionListener(new java.awt.event.ActionListener() {
/*     */       public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
/*  96 */         JPClient.mainFontSize = new Integer(24);
/*  97 */         JPClient.textAttributes.addAttribute(javax.swing.text.StyleConstants.CharacterConstants.Size, new Integer(24));
/*     */       }
/*  99 */     });
/* 100 */     this.size36Button = new JRadioButtonMenuItem("36");
/* 101 */     this.size36Button.addActionListener(new java.awt.event.ActionListener() {
/*     */       public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
/* 103 */         JPClient.mainFontSize = new Integer(36);
/* 104 */         JPClient.textAttributes.addAttribute(javax.swing.text.StyleConstants.CharacterConstants.Size, new Integer(36));
/*     */       }
/* 106 */     });
/* 107 */     this.size48Button = new JRadioButtonMenuItem("48");
/* 108 */     this.size48Button.addActionListener(new java.awt.event.ActionListener() {
/*     */       public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
/* 110 */         JPClient.mainFontSize = new Integer(48);
/* 111 */         JPClient.textAttributes.addAttribute(javax.swing.text.StyleConstants.CharacterConstants.Size, new Integer(48));
/*     */       }
/* 113 */     });
/* 114 */     this.size72Button = new JRadioButtonMenuItem("72");
/* 115 */     this.size72Button.addActionListener(new java.awt.event.ActionListener() {
/*     */       public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
/* 117 */         JPClient.mainFontSize = new Integer(72);
/* 118 */         JPClient.textAttributes.addAttribute(javax.swing.text.StyleConstants.CharacterConstants.Size, new Integer(72));
/*     */       }
/*     */       
/* 121 */     });
/* 122 */     this.size96Button = new JRadioButtonMenuItem("96");
/* 123 */     this.size96Button.addActionListener(new java.awt.event.ActionListener() {
/*     */       public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
/* 125 */         JPClient.mainFontSize = new Integer(96);
/* 126 */         JPClient.textAttributes.addAttribute(javax.swing.text.StyleConstants.CharacterConstants.Size, new Integer(96));
/*     */       }
/*     */       
/* 129 */     });
/* 130 */     this.size120Button = new JRadioButtonMenuItem("120");
/* 131 */     this.size120Button.addActionListener(new java.awt.event.ActionListener() {
/*     */       public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
/* 133 */         JPClient.mainFontSize = new Integer(120);
/* 134 */         JPClient.textAttributes.addAttribute(javax.swing.text.StyleConstants.CharacterConstants.Size, new Integer(120));
/*     */       }
/* 136 */     });
/* 137 */     javax.swing.ButtonGroup localButtonGroup2 = new javax.swing.ButtonGroup();
/* 138 */     localButtonGroup2.add(this.size10Button);
/* 139 */     localButtonGroup2.add(this.size12Button);
/* 140 */     localButtonGroup2.add(this.size14Button);
/* 141 */     localButtonGroup2.add(this.size16Button);
/* 142 */     localButtonGroup2.add(this.size18Button);
/* 143 */     localButtonGroup2.add(this.size24Button);
/* 144 */     localButtonGroup2.add(this.size36Button);
/* 145 */     localButtonGroup2.add(this.size48Button);
/* 146 */     localButtonGroup2.add(this.size72Button);
/* 147 */     localButtonGroup2.add(this.size96Button);
/* 148 */     localButtonGroup2.add(this.size120Button);
/*     */     
/* 150 */     switch (((Integer)JPClient.prefs.getPref(15)).intValue()) {
/* 151 */     case 10:  this.size10Button.setSelected(true); break;
/* 152 */     case 12:  this.size12Button.setSelected(true); break;
/* 153 */     case 14:  this.size14Button.setSelected(true); break;
/* 154 */     case 16:  this.size16Button.setSelected(true); break;
/* 155 */     case 18:  this.size18Button.setSelected(true); break;
/* 156 */     case 24:  this.size24Button.setSelected(true); break;
/* 157 */     case 36:  this.size36Button.setSelected(true); break;
/* 158 */     case 48:  this.size48Button.setSelected(true); break;
/* 159 */     case 72:  this.size72Button.setSelected(true); break;
/* 160 */     case 96:  this.size96Button.setSelected(true); break;
/* 161 */     case 120:  this.size120Button.setSelected(true); break;
/* 162 */     default:  System.out.println("ALERT: THE INI FILE IS ABOVE YOUR SECURITY CLEARANCE, CITIZEN!");
/*     */     }
/*     */     
/* 165 */     this.fontSizeMenu.add(this.size10Button);
/* 166 */     this.fontSizeMenu.add(this.size12Button);
/* 167 */     this.fontSizeMenu.add(this.size14Button);
/* 168 */     this.fontSizeMenu.add(this.size16Button);
/* 169 */     this.fontSizeMenu.add(this.size18Button);
/* 170 */     this.fontSizeMenu.add(this.size24Button);
/* 171 */     this.fontSizeMenu.add(this.size36Button);
/* 172 */     this.fontSizeMenu.add(this.size48Button);
/* 173 */     this.fontSizeMenu.add(this.size72Button);
/* 174 */     this.fontSizeMenu.add(this.size96Button);
/* 175 */     this.fontSizeMenu.add(this.size120Button);
/*     */     
/* 177 */     add(this.fontSizeMenu);
/*     */     
/* 179 */     this.fontBoldMenuItem = new javax.swing.JCheckBoxMenuItem("Bold");
/* 180 */     this.fontBoldMenuItem.setSelected(JPClient.prefs.getPref(17).equals(new Boolean(true)));
/* 181 */     this.fontBoldMenuItem.addActionListener(new java.awt.event.ActionListener() {
/*     */       public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
/* 183 */         if (JPClient.fontIsBold)
/*     */         {
/* 185 */           JPClient.textAttributes.addAttribute(javax.swing.text.StyleConstants.FontConstants.Bold, Boolean.FALSE);
/* 186 */           JPClient.fontIsBold = false;
/*     */ 
/*     */         }
/* 189 */         else if (!JPClient.fontIsBold)
/*     */         {
/* 191 */           JPClient.textAttributes.addAttribute(javax.swing.text.StyleConstants.FontConstants.Bold, Boolean.TRUE);
/* 192 */           JPClient.fontIsBold = true;
/*     */         }
/* 194 */       } });
/* 195 */     addSeparator();
/* 196 */     add(this.fontBoldMenuItem);
/*     */   }
/*     */   
/*     */   JRadioButtonMenuItem size14Button;
/*     */   JRadioButtonMenuItem size16Button;
/*     */   JRadioButtonMenuItem size18Button;
/*     */   JRadioButtonMenuItem size24Button;
/*     */   JRadioButtonMenuItem size36Button;
/*     */   JRadioButtonMenuItem size48Button;
/*     */   JRadioButtonMenuItem size72Button;
/*     */   JRadioButtonMenuItem size96Button;
/*     */   JRadioButtonMenuItem size120Button;
/*     */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\client\FontMenu.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */