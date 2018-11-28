package jparanoia.client;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static java.lang.invoke.MethodHandles.lookup;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CombatTurnFrame extends JFrame {
    JTextField publicInputLine;
    JTextField secretInputLine;
    JLabel publicLabel;
    JLabel secretLabel;
    JButton sendButton;
    JPanel inputPanel;
    JPanel sendPanel;
    Container contentPane;

    public CombatTurnFrame() {
        addWindowListener( new WindowAdapter() {
            public void windowClosing( WindowEvent paramAnonymousWindowEvent ) {
                JPClient.out.println( "602" );
                JPClient.connectionStatusLabel.setIcon( JPClient.frozenIcon );
                CombatTurnFrame.this.dispose();
            }
        } );
        setTitle( "Combat!" );
        setSize( 300, 135 );
        setResizable( false );
        setIconImage( Toolkit.getDefaultToolkit()
                .getImage( lookup().lookupClass().getClassLoader().getResource( "graphics/jparanoiaIcon.jpg" ) ) );
        this.publicLabel = new JLabel( "Public text:" );
        this.publicInputLine = new JTextField( 40 );
        this.publicInputLine.setPreferredSize( new Dimension( 300, 22 ) );
        this.publicInputLine.addKeyListener( new KeyListener() {
            public void keyTyped( KeyEvent paramAnonymousKeyEvent ) {
            }

            public void keyPressed( KeyEvent paramAnonymousKeyEvent ) {
                JPClient.previousKey = JPClient.thisKey;
                JPClient.thisKey = paramAnonymousKeyEvent.getKeyCode();
                if ( JPClient.thisKey == 17 ) {
                    if ( CombatTurnFrame.this.publicInputLine.getText().length() > 0 ) {
                        CombatTurnFrame.this.publicInputLine.setText( JPClient.nameCompletion( CombatTurnFrame.this.publicInputLine
                                .getText(), JPClient.thisKey == JPClient.previousKey ) );
                    }
                }
            }

            public void keyReleased( KeyEvent paramAnonymousKeyEvent ) {
            }
        } );
        this.secretLabel = new JLabel( "Secret text:" );
        this.secretInputLine = new JTextField( 40 );
        this.secretInputLine.setPreferredSize( new Dimension( 300, 22 ) );
        this.secretInputLine.addKeyListener( new KeyListener() {
            public void keyTyped( KeyEvent paramAnonymousKeyEvent ) {
            }

            public void keyPressed( KeyEvent paramAnonymousKeyEvent ) {
                JPClient.previousKey = JPClient.thisKey;
                JPClient.thisKey = paramAnonymousKeyEvent.getKeyCode();
                if ( JPClient.thisKey == 17 ) {
                    if ( CombatTurnFrame.this.secretInputLine.getText().length() > 0 ) {
                        CombatTurnFrame.this.secretInputLine.setText( JPClient.nameCompletion( CombatTurnFrame.this.secretInputLine
                                .getText(), JPClient.thisKey == JPClient.previousKey ) );
                    }
                }
            }

            public void keyReleased( KeyEvent paramAnonymousKeyEvent ) {
            }
        } );
        this.sendButton = new JButton( "Send Turn" );
        this.sendButton.setPreferredSize( new Dimension( 100, 30 ) );
        this.sendButton.setEnabled( false );
        this.sendButton.addActionListener( paramAnonymousActionEvent -> {
            String str = CombatTurnFrame.this.publicInputLine.getText();
            if ( str.equals( "" ) ) {
                str = "/appears to do nothing.";
                JPClient.out.println( "601" + str + "~" + CombatTurnFrame.this.secretInputLine.getText() );
                JPClient.PMFrame[0].addMyMessage( CombatTurnFrame.this.secretInputLine.getText() );
                JPClient.connectionStatusLabel.setIcon( JPClient.frozenIcon );
                CombatTurnFrame.this.dispose();
            } else if ( str.startsWith( "/" ) || str.startsWith( "'" ) ) {
                JPClient.out.println( "601" + str + "~" + CombatTurnFrame.this.secretInputLine.getText() );
                JPClient.PMFrame[0].addMyMessage( CombatTurnFrame.this.secretInputLine.getText() );
                JPClient.connectionStatusLabel.setIcon( JPClient.frozenIcon );
                CombatTurnFrame.this.dispose();
            } else {
                new JOptionPane();
                JOptionPane.showMessageDialog( null, "Your public text must begin with\n/  or  '\nor you can leave it blank.", "Combat Error", JOptionPane.PLAIN_MESSAGE );
            }
        } );
        this.inputPanel = new JPanel();
        this.inputPanel.setLayout( new BoxLayout( this.inputPanel, BoxLayout.Y_AXIS ) );
        this.inputPanel.add( this.publicLabel );
        this.inputPanel.add( this.publicInputLine );
        this.inputPanel.add( this.secretLabel );
        this.inputPanel.add( this.secretInputLine );
        this.sendPanel = new JPanel();
        this.sendPanel.setLayout( new BoxLayout( this.sendPanel, BoxLayout.X_AXIS ) );
        this.sendPanel.add( Box.createRigidArea( new Dimension( 200, 0 ) ) );
        this.sendPanel.add( this.sendButton );
        this.contentPane = getContentPane();
        this.contentPane.setLayout( new BoxLayout( this.contentPane, BoxLayout.Y_AXIS ) );
        this.contentPane.add( this.inputPanel );
        this.contentPane.add( this.sendPanel );
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\client\CombatTurnFrame.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
