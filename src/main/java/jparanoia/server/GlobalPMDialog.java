package jparanoia.server;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GlobalPMDialog extends JFrame {
    static ServerPlayer[] ts = JPServer.troubleshooters;
    Container contentPane;
    JTextField textField = new JTextField( 40 );
    JPanel excludePanel = new JPanel();
    JPanel textPanel = new JPanel();
    JButton resetButton = new JButton( "Reset" );

    public GlobalPMDialog() {
        super( "Global PM..." );
        addWindowListener( new WindowAdapter() {
            public void windowClosing( WindowEvent paramAnonymousWindowEvent ) {
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
        this.excludePanel.setLayout( new BoxLayout( this.excludePanel, BoxLayout.Y_AXIS ) );
        this.excludePanel.add( Box.createRigidArea( new Dimension( 2, 0 ) ) );
        for ( final ServerPlayer t : ts ) {
            this.excludePanel.add( t.getExcludeCheckBox() );
            this.excludePanel.add( Box.createRigidArea( new Dimension( 2, 0 ) ) );
        }
        this.excludePanel.setBorder( BorderFactory.createTitledBorder( "Exclude" ) );
        this.contentPane.setLayout( new BoxLayout( this.contentPane, BoxLayout.X_AXIS ) );
        this.textField.setMaximumSize( new Dimension( 500, 25 ) );
        this.textPanel.setLayout( new BoxLayout( this.textPanel, BoxLayout.Y_AXIS ) );
        this.textPanel.add( this.textField );
        this.textPanel.add( Box.createRigidArea( new Dimension( 4, 0 ) ) );
        this.textPanel.add( this.resetButton );
        setSize( 400, ts.length * 22 + 72 );
        this.textField.addFocusListener( new FocusAdapter() {
            public void focusGained( FocusEvent paramAnonymousFocusEvent ) {
                for ( int i = 0; i < GlobalPMDialog.ts.length; i++ ) {
                    GlobalPMDialog.ts[i].getExcludeCheckBox().setEnabled( false );
                    GlobalPMDialog.ts[i].sendingGlobalPM();
                }
            }

            public void focusLost( FocusEvent paramAnonymousFocusEvent ) {
            }
        } );
        this.textField.addActionListener( paramAnonymousActionEvent -> {
            GlobalPMDialog.this.sendGlobalPM( GlobalPMDialog.this.textField.getText() );
        } );
        this.contentPane.add( this.excludePanel );
        this.contentPane.add( this.textPanel );
    }

    public void sendGlobalPM( String paramString ) {
        for ( final ServerPlayer t : ts ) {
            t.sendGlobalPM( paramString );
        }
        JPServer.spamString( "211" );
        dispose();
    }

    public static void resetExcludeCheckBoxes() {
        for ( final ServerPlayer t : ts ) {
            t.getExcludeCheckBox().setEnabled( true );
            t.getExcludeCheckBox().setSelected( false );
        }
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\GlobalPMDialog.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
