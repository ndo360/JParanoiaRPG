package jparanoia.server;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GlobalPMDialog extends javax.swing.JFrame {
    /*  16 */   static ServerPlayer[] ts = JPServer.troubleshooters;
    Container contentPane;
    /*  11 */ JTextField textField = new JTextField( 40 );
    /*  12 */ JPanel excludePanel = new JPanel();
    /*  13 */ JPanel textPanel = new JPanel();
    /*  14 */ javax.swing.JButton resetButton = new javax.swing.JButton( "Reset" );

    public GlobalPMDialog() {
        /*  20 */
        super( "Global PM..." );

        /*  22 */
        addWindowListener( new java.awt.event.WindowAdapter() {
            public void windowClosing( java.awt.event.WindowEvent paramAnonymousWindowEvent ) {
                /*  24 */
                JPServer.spamString( "211" );
            }
            /*  26 */
        } );
        /*  27 */
        resetExcludeCheckBoxes();

        /*  29 */
        this.resetButton.addActionListener( new java.awt.event.ActionListener() {
            public void actionPerformed( java.awt.event.ActionEvent paramAnonymousActionEvent ) {
                /*  31 */
                GlobalPMDialog.this.textField.setText( "" );
                /*  32 */
                JPServer.spamString( "211" );
                /*  33 */
                GlobalPMDialog.resetExcludeCheckBoxes();
                /*  34 */
            }
        } );
        /*  35 */
        this.resetButton.setAlignmentX( 0.5F );

        /*  37 */
        this.contentPane = getContentPane();


        /*  40 */
        this.excludePanel.setLayout( new javax.swing.BoxLayout( this.excludePanel, 1 ) );
        /*  41 */
        this.excludePanel.add( javax.swing.Box.createRigidArea( new Dimension( 2, 0 ) ) );

        /*  43 */
        for ( int i = 0; i < ts.length; i++ ) {
            /*  45 */
            this.excludePanel.add( ts[i].getExcludeCheckBox() );
            /*  46 */
            this.excludePanel.add( javax.swing.Box.createRigidArea( new Dimension( 2, 0 ) ) );
        }

        /*  49 */
        this.excludePanel.setBorder( javax.swing.BorderFactory.createTitledBorder( "Exclude" ) );

        /*  51 */
        this.contentPane.setLayout( new javax.swing.BoxLayout( this.contentPane, 0 ) );

        /*  53 */
        this.textField.setMaximumSize( new Dimension( 500, 25 ) );

        /*  55 */
        this.textPanel.setLayout( new javax.swing.BoxLayout( this.textPanel, 1 ) );
        /*  56 */
        this.textPanel.add( this.textField );
        /*  57 */
        this.textPanel.add( javax.swing.Box.createRigidArea( new Dimension( 4, 0 ) ) );
        /*  58 */
        this.textPanel.add( this.resetButton );

        /*  60 */
        setSize( 400, ts.length * 22 + 72 );



        /*  64 */
        this.textField.addFocusListener( new java.awt.event.FocusAdapter() {
            public void focusGained( java.awt.event.FocusEvent paramAnonymousFocusEvent ) {
                /*  67 */
                for ( int i = 0; i < GlobalPMDialog.ts.length; i++ ) {
                    /*  69 */
                    GlobalPMDialog.ts[i].getExcludeCheckBox().setEnabled( false );
                    /*  70 */
                    GlobalPMDialog.ts[i].sendingGlobalPM();
                }
            }

            public void focusLost( java.awt.event.FocusEvent paramAnonymousFocusEvent ) {
            }
            /*  76 */
        } );
        /*  77 */
        this.textField.addActionListener( new java.awt.event.ActionListener() {
            public void actionPerformed( java.awt.event.ActionEvent paramAnonymousActionEvent ) {
                /*  79 */
                GlobalPMDialog.this.sendGlobalPM( GlobalPMDialog.this.textField.getText() );
            }
            /*  81 */
        } );
        /*  82 */
        this.contentPane.add( this.excludePanel );
        /*  83 */
        this.contentPane.add( this.textPanel );
    }

    public void sendGlobalPM( String paramString ) {
        /*  89 */
        for ( int i = 0; i < ts.length; i++ ) {
            /*  91 */
            ts[i].sendGlobalPM( paramString );
        }

        /*  94 */
        JPServer.spamString( "211" );
        /*  95 */
        dispose();
    }

    public static void resetExcludeCheckBoxes() {
        /* 101 */
        for ( int i = 0; i < ts.length; i++ ) {
            /* 103 */
            ts[i].getExcludeCheckBox().setEnabled( true );
            /* 104 */
            ts[i].getExcludeCheckBox().setSelected( false );
        }
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\GlobalPMDialog.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
