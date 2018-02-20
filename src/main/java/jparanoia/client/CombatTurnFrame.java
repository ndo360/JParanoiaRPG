package jparanoia.client;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

public class CombatTurnFrame extends javax.swing.JFrame {
    JTextField publicInputLine;
    JTextField secretInputLine;
    javax.swing.JLabel publicLabel;
    javax.swing.JLabel secretLabel;
    javax.swing.JButton sendButton;
    javax.swing.JPanel inputPanel;
    javax.swing.JPanel sendPanel;
    java.awt.Container contentPane;

    public CombatTurnFrame() {
        /*  19 */
        addWindowListener( new java.awt.event.WindowAdapter() {
            public void windowClosing( java.awt.event.WindowEvent paramAnonymousWindowEvent ) {
                /*  21 */
                JPClient.out.println( "602" );
                /*  22 */
                JPClient.connectionStatusLabel.setIcon( JPClient.frozenIcon );
                /*  23 */
                CombatTurnFrame.this.dispose();
            }
            /*  25 */
        } );
        /*  26 */
        setTitle( "Combat!" );
        /*  27 */
        setSize( 300, 135 );
        /*  28 */
        setResizable( false );
        /*  29 */
        setIconImage( java.awt.Toolkit.getDefaultToolkit()
                .getImage( getClass().getResource( "graphics/jparanoiaIcon.jpg" ) ) );

        /*  31 */
        this.publicLabel = new javax.swing.JLabel( "Public text:" );

        /*  33 */
        this.publicInputLine = new JTextField( 40 );
        /*  34 */
        this.publicInputLine.setPreferredSize( new java.awt.Dimension( 300, 22 ) );


        /*  37 */
        this.publicInputLine.addKeyListener( new java.awt.event.KeyListener() {
            public void keyTyped( KeyEvent paramAnonymousKeyEvent ) {
            }

            /*  40 */
            public void keyPressed( KeyEvent paramAnonymousKeyEvent ) {
                JPClient.previousKey = JPClient.thisKey;
                /*  41 */
                JPClient.thisKey = paramAnonymousKeyEvent.getKeyCode();
                /*  42 */
                if ( JPClient.thisKey == 17 ) {
                    /*  44 */
                    if ( CombatTurnFrame.this.publicInputLine.getText().length() > 0 ) {
                        CombatTurnFrame.this.publicInputLine.setText( JPClient.nameCompletion( CombatTurnFrame.this.publicInputLine
                                .getText(), JPClient.thisKey == JPClient.previousKey ) );
                    }
                }
            }

            public void keyReleased( KeyEvent paramAnonymousKeyEvent ) {
            }
            /*  49 */
        } );
        /*  50 */
        this.secretLabel = new javax.swing.JLabel( "Secret text:" );

        /*  52 */
        this.secretInputLine = new JTextField( 40 );
        /*  53 */
        this.secretInputLine.setPreferredSize( new java.awt.Dimension( 300, 22 ) );


        /*  56 */
        this.secretInputLine.addKeyListener( new java.awt.event.KeyListener() {
            public void keyTyped( KeyEvent paramAnonymousKeyEvent ) {
            }

            /*  59 */
            public void keyPressed( KeyEvent paramAnonymousKeyEvent ) {
                JPClient.previousKey = JPClient.thisKey;
                /*  60 */
                JPClient.thisKey = paramAnonymousKeyEvent.getKeyCode();
                /*  61 */
                if ( JPClient.thisKey == 17 ) {
                    /*  63 */
                    if ( CombatTurnFrame.this.secretInputLine.getText().length() > 0 ) {
                        CombatTurnFrame.this.secretInputLine.setText( JPClient.nameCompletion( CombatTurnFrame.this.secretInputLine
                                .getText(), JPClient.thisKey == JPClient.previousKey ) );
                    }
                }
            }

            public void keyReleased( KeyEvent paramAnonymousKeyEvent ) {
            }
            /*  69 */
        } );
        /*  70 */
        this.sendButton = new javax.swing.JButton( "Send Turn" );

        /*  72 */
        this.sendButton.setPreferredSize( new java.awt.Dimension( 100, 30 ) );
        /*  73 */
        this.sendButton.setEnabled( false );

        /*  75 */
        this.sendButton.addActionListener( new java.awt.event.ActionListener() {
            public void actionPerformed( java.awt.event.ActionEvent paramAnonymousActionEvent ) {
                /*  77 */
                String str = CombatTurnFrame.this.publicInputLine.getText();
                /*  78 */
                if ( str.equals( "" ) ) {
                    /*  80 */
                    str = "/appears to do nothing.";
                    /*  81 */
                    JPClient.out.println( "601" + str + "~" + CombatTurnFrame.this.secretInputLine.getText() );
                    /*  82 */
                    JPClient.PMFrame[0].addMyMessage( CombatTurnFrame.this.secretInputLine.getText() );
                    /*  83 */
                    JPClient.connectionStatusLabel.setIcon( JPClient.frozenIcon );
                    /*  84 */
                    CombatTurnFrame.this.dispose();
                }
                /*  87 */
                else if ( str.startsWith( "/" ) || str.startsWith( "'" ) ) {
                    /*  89 */
                    JPClient.out.println( "601" + str + "~" + CombatTurnFrame.this.secretInputLine.getText() );
                    /*  90 */
                    JPClient.PMFrame[0].addMyMessage( CombatTurnFrame.this.secretInputLine.getText() );
                    /*  91 */
                    JPClient.connectionStatusLabel.setIcon( JPClient.frozenIcon );
                    /*  92 */
                    CombatTurnFrame.this.dispose();
                } else {
                    /*  94 */
                    new javax.swing.JOptionPane();
                    javax.swing.JOptionPane.showMessageDialog( null, "Your public text must begin with\n/  or  '\nor you can leave it blank.", "Combat Error", -1 );
                }
            }
            /*  98 */
        } );
        /*  99 */
        this.inputPanel = new javax.swing.JPanel();

        /* 101 */
        this.inputPanel.setLayout( new javax.swing.BoxLayout( this.inputPanel, 1 ) );



        /* 105 */
        this.inputPanel.add( this.publicLabel );
        /* 106 */
        this.inputPanel.add( this.publicInputLine );
        /* 107 */
        this.inputPanel.add( this.secretLabel );
        /* 108 */
        this.inputPanel.add( this.secretInputLine );

        /* 110 */
        this.sendPanel = new javax.swing.JPanel();
        /* 111 */
        this.sendPanel.setLayout( new javax.swing.BoxLayout( this.sendPanel, 0 ) );

        /* 113 */
        this.sendPanel.add( javax.swing.Box.createRigidArea( new java.awt.Dimension( 200, 0 ) ) );
        /* 114 */
        this.sendPanel.add( this.sendButton );


        /* 117 */
        this.contentPane = getContentPane();
        /* 118 */
        this.contentPane.setLayout( new javax.swing.BoxLayout( this.contentPane, 1 ) );

        /* 120 */
        this.contentPane.add( this.inputPanel );
        /* 121 */
        this.contentPane.add( this.sendPanel );
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\client\CombatTurnFrame.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
