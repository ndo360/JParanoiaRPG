package jparanoia.server;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GlobalPMDialog extends javax.swing.JFrame {
    static ServerPlayer[] ts = JPServer.troubleshooters;
    Container contentPane;
    JTextField textField = new JTextField( 40 );
    JPanel excludePanel = new JPanel();
    JPanel textPanel = new JPanel();
    javax.swing.JButton resetButton = new javax.swing.JButton( "Reset" );

    public GlobalPMDialog() {
        super( "Global PM..." );
        addWindowListener( new java.awt.event.WindowAdapter() {
            public void windowClosing( java.awt.event.WindowEvent paramAnonymousWindowEvent ) {
                JPServer.spamString( "211" );
            }
        } );
        resetExcludeCheckBoxes();
        this.resetButton.addActionListener( paramAnonymousActionEvent -> {
            GlobalPMDialog.this.textField.setText( "" );
            JPServer.spamString( "211" );
            GlobalPMDialog.resetExcludeCheckBoxes();
        } );
        this.resetButton.setAlignmentX( 0.5F );
        this.contentPane = getContentPane();
        this.excludePanel.setLayout( new javax.swing.BoxLayout( this.excludePanel, 1 ) );
        this.excludePanel.add( javax.swing.Box.createRigidArea( new Dimension( 2, 0 ) ) );
        for ( int i = 0; i < ts.length; i++ ) {
            this.excludePanel.add( ts[i].getExcludeCheckBox() );
            this.excludePanel.add( javax.swing.Box.createRigidArea( new Dimension( 2, 0 ) ) );
        }
        this.excludePanel.setBorder( javax.swing.BorderFactory.createTitledBorder( "Exclude" ) );
        this.contentPane.setLayout( new javax.swing.BoxLayout( this.contentPane, 0 ) );
        this.textField.setMaximumSize( new Dimension( 500, 25 ) );
        this.textPanel.setLayout( new javax.swing.BoxLayout( this.textPanel, 1 ) );
        this.textPanel.add( this.textField );
        this.textPanel.add( javax.swing.Box.createRigidArea( new Dimension( 4, 0 ) ) );
        this.textPanel.add( this.resetButton );
        setSize( 400, ts.length * 22 + 72 );
        this.textField.addFocusListener( new java.awt.event.FocusAdapter() {
            public void focusGained( java.awt.event.FocusEvent paramAnonymousFocusEvent ) {
                for ( int i = 0; i < GlobalPMDialog.ts.length; i++ ) {
                    GlobalPMDialog.ts[i].getExcludeCheckBox().setEnabled( false );
                    GlobalPMDialog.ts[i].sendingGlobalPM();
                }
            }

            public void focusLost( java.awt.event.FocusEvent paramAnonymousFocusEvent ) {
            }
        } );
        this.textField.addActionListener( paramAnonymousActionEvent -> {
            GlobalPMDialog.this.sendGlobalPM( GlobalPMDialog.this.textField.getText() );
        } );
        this.contentPane.add( this.excludePanel );
        this.contentPane.add( this.textPanel );
    }

    public void sendGlobalPM( String paramString ) {
        for ( int i = 0; i < ts.length; i++ ) {
            ts[i].sendGlobalPM( paramString );
        }
        JPServer.spamString( "211" );
        dispose();
    }

    public static void resetExcludeCheckBoxes() {
        for ( int i = 0; i < ts.length; i++ ) {
            ts[i].getExcludeCheckBox().setEnabled( true );
            ts[i].getExcludeCheckBox().setSelected( false );
        }
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\GlobalPMDialog.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
