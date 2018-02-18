/*     */ package jparanoia;
/*     */ 
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.io.PrintStream;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import jparanoia.client.JPClient;
/*     */ import jparanoia.server.JPServer;
/*     */ 
/*     */ public class ParanoiaSelector
/*     */ {
/*     */   static
/*     */   {
/*  24 */     System.setProperty("swing.metalTheme", "steel");
/*     */   }
/*     */   
/*  27 */   static JFrame frame = new JFrame("Launch JParanoia");
/*  28 */   static JButton serverButton = new JButton("Server " + JPServer.getVersionName());
/*  29 */   static JButton clientButton = new JButton("Client " + JPClient.getVersionName());
/*  30 */   static Container contentPane = frame.getContentPane();
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
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/*  45 */     System.out.println("JPSERVER VERSION: " + JPServer.getVersionName());
/*  46 */     System.out.println("JPCLIENT VERSION: " + JPClient.getVersionName());
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
/*  58 */     contentPane.setLayout(null);
/*     */     
/*  60 */     Dimension localDimension = Toolkit.getDefaultToolkit().getScreenSize();
/*     */     
/*  62 */     int i = (int)localDimension.getWidth();
/*  63 */     int j = (int)localDimension.getHeight();
/*     */     
/*  65 */     frame.setSize(610, 360);
/*     */     
/*  67 */     frame.setLocation(i / 2 - frame.getWidth() / 2, j / 2 - frame.getHeight() / 2);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  72 */     ImageIcon localImageIcon = null;
/*     */     try
/*     */     {
/*  75 */       localImageIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(Class.forName("jparanoia.ParanoiaSelector").getResource("shared/graphics/jpsplash.jpg")));
/*  76 */     } catch (Exception localException1) { localException1.printStackTrace();System.exit(-1);
/*     */     }
/*     */     
/*     */ 
/*  80 */     JLabel localJLabel = new JLabel(localImageIcon);
/*     */     
/*  82 */     JPanel localJPanel = new JPanel();
/*     */     
/*  84 */     localJPanel.setLayout(new java.awt.GridLayout(1, 2, 4, 4));
/*     */     
/*  86 */     localJPanel.add(serverButton);
/*  87 */     localJPanel.add(clientButton);
/*     */     
/*     */ 
/*  90 */     serverButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
/*  92 */         ParanoiaSelector.frame.dispose();
/*  93 */         JPServer.main(null);
/*     */       }
/*  95 */     });
/*  96 */     clientButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
/*  98 */         ParanoiaSelector.frame.dispose();
/*  99 */         JPClient.main(null);
/*     */       }
/*     */       
/* 102 */     });
/* 103 */     contentPane.add(localJLabel);
/* 104 */     contentPane.add(localJPanel);
/*     */     
/* 106 */     Insets localInsets = contentPane.getInsets();
/* 107 */     localJLabel.setBounds(0 + localInsets.left, 0 + localInsets.top, 600, 295);
/* 108 */     localJPanel.setBounds(0 + localInsets.left, 298 + localInsets.top, 600, 35);
/*     */     try
/*     */     {
/* 111 */       frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Class.forName("jparanoia.ParanoiaSelector").getResource("server/graphics/jparanoiaIcon.jpg")));
/* 112 */     } catch (Exception localException2) { localException2.printStackTrace();System.exit(-1);
/*     */     }
/* 114 */     frame.addWindowListener(new WindowAdapter() {
/*     */       public void windowClosing(WindowEvent paramAnonymousWindowEvent) {
/* 116 */         System.exit(0);
/*     */       }
/* 118 */     });
/* 119 */     frame.setResizable(false);
/* 120 */     frame.setVisible(true);
/*     */   }
/*     */ }


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\ParanoiaSelector.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */