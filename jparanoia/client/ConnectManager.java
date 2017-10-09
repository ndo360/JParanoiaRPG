/*     */ package jparanoia.client;
/*     */ 
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class ConnectManager
/*     */ {
/*  11 */   public static JFrame frame = new JFrame("Connect...");
/*  12 */   static java.awt.Dimension dimen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
/*     */   
/*  14 */   static int screenWidth = (int)dimen.getWidth();
/*  15 */   static int screenHeight = (int)dimen.getHeight();
/*     */   
/*  17 */   static boolean noGames = false;
/*     */   
/*     */   static JLabel manualLabel;
/*  20 */   static javax.swing.JTextField manualEntryField = new javax.swing.JTextField(15);
/*     */   
/*     */ 
/*     */ 
/*     */   static jparanoia.shared.JPGameInfo[] games;
/*     */   
/*     */ 
/*     */   static JPanel contentPane;
/*     */   
/*     */ 
/*     */ 
/*     */   static
/*     */   {
/*  33 */     manualEntryField.setText(JPClient.addressToTry);
/*     */     
/*  35 */     manualEntryField.addActionListener(new java.awt.event.ActionListener() {
/*     */       public void actionPerformed(java.awt.event.ActionEvent paramAnonymousActionEvent) {
/*  37 */         ConnectManager.frame.setVisible(false);
/*  38 */         JPClient.connect(ConnectManager.manualEntryField.getText(), false, ConnectManager.manualEntryField.getText());
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */   public static void activate() {
/*  44 */     games = jparanoia.shared.GameRegistrar.getGames();
/*  45 */     noGames = (games == null) || (games.length == 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  52 */     contentPane = new JPanel();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  58 */     GridBagLayout localGridBagLayout = new GridBagLayout();
/*  59 */     GridBagConstraints localGridBagConstraints = new GridBagConstraints();
/*     */     
/*  61 */     contentPane.setLayout(localGridBagLayout);
/*     */     
/*  63 */     localGridBagConstraints.gridx = 0;
/*  64 */     localGridBagConstraints.gridy = 0;
/*     */     
/*  66 */     localGridBagConstraints.weightx = 0.0D;
/*  67 */     localGridBagConstraints.gridwidth = 1;
/*  68 */     localGridBagConstraints.fill = 1;
/*  69 */     localGridBagConstraints.anchor = 17;
/*  70 */     localGridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
/*     */     
/*  72 */     if (games != null)
/*     */     {
/*     */ 
/*  75 */       for (int i = 0; i < games.length; i++)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  86 */         JLabel localJLabel2 = new JLabel(games[i].description);
/*     */         
/*  88 */         localGridBagLayout.setConstraints(games[i].connectButton, localGridBagConstraints);
/*  89 */         contentPane.add(games[i].connectButton);
/*     */         
/*  91 */         localGridBagConstraints.gridx += 1;
/*     */         
/*  93 */         localGridBagConstraints.gridwidth = 50;
/*  94 */         localGridBagConstraints.weightx = 0.75D;
/*     */         
/*  96 */         localGridBagLayout.setConstraints(localJLabel2, localGridBagConstraints);
/*  97 */         contentPane.add(localJLabel2);
/*     */         
/*  99 */         localGridBagConstraints.gridy += 1;
/* 100 */         localGridBagConstraints.gridx = 0;
/* 101 */         localGridBagConstraints.weightx = 0.0D;
/*     */         
/* 103 */         localGridBagConstraints.gridwidth = 1;
/*     */       }
/*     */     }
/*     */     
/* 107 */     if (noGames)
/*     */     {
/* 109 */       localGridBagConstraints.gridx += 1;
/* 110 */       manualLabel = new JLabel(" IP Address: ");
/* 111 */       JLabel localJLabel1 = new JLabel("No Games Found - Contact Your GM for IP Address");
/*     */       
/* 113 */       localGridBagLayout.setConstraints(localJLabel1, localGridBagConstraints);
/* 114 */       contentPane.add(localJLabel1);
/* 115 */       localGridBagConstraints.weightx = 0.0D;
/* 116 */       localGridBagConstraints.gridx = 0;
/* 117 */       localGridBagConstraints.gridy += 1;
/*     */     } else {
/* 119 */       manualLabel = new JLabel("or enter IP: ");
/*     */     }
/* 121 */     localGridBagLayout.setConstraints(manualLabel, localGridBagConstraints);
/* 122 */     contentPane.add(manualLabel);
/*     */     
/* 124 */     localGridBagConstraints.gridx += 1;
/*     */     
/* 126 */     localGridBagConstraints.gridwidth = 50;
/* 127 */     localGridBagConstraints.weightx = 0.75D;
/*     */     
/* 129 */     localGridBagLayout.setConstraints(manualEntryField, localGridBagConstraints);
/*     */     
/* 131 */     contentPane.add(manualEntryField);
/*     */     
/* 133 */     frame.setContentPane(contentPane);
/*     */     try {
/* 135 */       frame.setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(Class.forName("jparanoia.client.ConnectManager").getResource("graphics/jparanoiaIcon.jpg")));
/* 136 */     } catch (Exception localException) { jparanoia.shared.JParanoia.errorMessage("Error getting icon...", "Unable to load icon for ConnectManager frame.\nCheck console.");
/*     */       
/* 138 */       localException.printStackTrace();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 143 */     int j = noGames ? 1 : games.length;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 149 */     frame.setSize(400, 24 + 28 * (1 + j));
/*     */     
/* 151 */     frame.setLocation(screenWidth / 2 - frame.getWidth() / 2, screenHeight / 2 - frame.getHeight() / 2);
/*     */     
/* 153 */     frame.setVisible(true);
/*     */   }
/*     */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\client\ConnectManager.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */