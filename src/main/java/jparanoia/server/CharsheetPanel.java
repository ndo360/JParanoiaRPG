/*     */ package jparanoia.server;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextPane;
/*     */ 
/*     */ public class CharsheetPanel extends JPanel
/*     */ {
/*     */   static final int IDEAL_WIDTH = 450;
/*     */   static final int IDEAL_HEIGHT = 200;
/*     */   ServerPlayer selectedPlayer;
/*     */   java.io.InputStreamReader reader;
/*     */   java.io.FileWriter writer;
/*     */   java.io.BufferedReader in;
/*     */   JTextPane displayArea;
/*     */   JPanel savePanel;
/*     */   JPanel playerAndDicePanel;
/*     */   JPanel dicePanel;
/*     */   JPanel playerPanel;
/*     */   JComboBox playerComboBox;
/*     */   JButton saveButton;
/*     */   javax.swing.JScrollPane scrollPane;
/*     */   java.awt.Container contentPane;
/*     */   javax.swing.text.DefaultStyledDocument theFile;
/*     */   
/*     */   public CharsheetPanel()
/*     */   {
/*  34 */     setLayout(new BoxLayout(this, 1));
/*     */     
/*  36 */     this.displayArea = new JTextPane();
/*     */     
/*  38 */     this.displayArea.setCharacterAttributes(JPServer.charsheetAttributes, true);
/*     */     
/*  40 */     this.displayArea.setEditable(true);
/*  41 */     this.displayArea.setEnabled(true);
/*     */     
/*     */ 
/*     */ 
/*  45 */     this.scrollPane = new javax.swing.JScrollPane(this.displayArea, 22, 31);
/*     */     
/*     */ 
/*  48 */     this.saveButton = new JButton("Save / Send");
/*  49 */     this.saveButton.setMaximumSize(new Dimension(65, 22));
/*  50 */     this.saveButton.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
/*  53 */         CharsheetPanel.this.selectedPlayer.saveCharsheet(CharsheetPanel.this.selectedPlayer.isLoggedIn());
/*     */ 
/*     */ 
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*  61 */     });
/*  62 */     this.playerComboBox = new JComboBox(JPServer.troubleshooters);
/*  63 */     this.playerComboBox.addActionListener(new ActionListener()
/*     */     {
/*     */ 
/*     */       public void actionPerformed(ActionEvent paramAnonymousActionEvent)
/*     */       {
/*  68 */         CharsheetPanel.this.selectedPlayer = ((ServerPlayer)CharsheetPanel.this.playerComboBox.getSelectedItem());
/*     */         
/*  70 */         CharsheetPanel.this.theFile = CharsheetPanel.this.selectedPlayer.getCharsheet();
/*  71 */         CharsheetPanel.this.displayArea.setDocument(CharsheetPanel.this.theFile);
/*     */       }
/*     */       
/*  74 */     });
/*  75 */     this.selectedPlayer = ((ServerPlayer)this.playerComboBox.getSelectedItem());
/*     */     
/*  77 */     this.theFile = this.selectedPlayer.getCharsheet();
/*     */     
/*  79 */     this.displayArea.setDocument(this.theFile);
/*     */     
/*  81 */     this.dicePanel = new DicePanel();
/*     */     
/*  83 */     this.playerPanel = new JPanel();
/*     */     
/*  85 */     this.playerPanel.setLayout(new BoxLayout(this.playerPanel, 0));
/*     */     
/*  87 */     this.playerPanel.setMaximumSize(new Dimension(100, 15));
/*     */     
/*  89 */     this.playerPanel.add(Box.createRigidArea(new Dimension(5, 0)));
/*  90 */     this.playerPanel.add(this.playerComboBox);
/*  91 */     this.playerPanel.add(Box.createRigidArea(new Dimension(5, 0)));
/*  92 */     this.playerPanel.add(this.saveButton);
/*     */     
/*  94 */     this.playerAndDicePanel = new JPanel();
/*  95 */     this.playerAndDicePanel.setLayout(new java.awt.BorderLayout());
/*     */     
/*  97 */     this.playerAndDicePanel.add(this.playerPanel, "West");
/*  98 */     this.playerAndDicePanel.add(this.dicePanel, "East");
/*     */     
/* 100 */     this.playerAndDicePanel.setMaximumSize(new Dimension(1500, 20));
/* 101 */     this.playerAndDicePanel.setMinimumSize(new Dimension(200, 20));
/* 102 */     this.playerAndDicePanel.setPreferredSize(new Dimension(1500, 20));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 107 */     add(Box.createRigidArea(new Dimension(0, 5)));
/* 108 */     add(this.playerAndDicePanel);
/* 109 */     add(Box.createRigidArea(new Dimension(0, 5)));
/* 110 */     add(this.scrollPane);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 115 */     setPreferredSize(new Dimension(9200, 9200));
/*     */   }
/*     */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\CharsheetPanel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */