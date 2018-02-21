package jparanoia.client;
import java.awt.event.KeyEvent;
import static java.lang.invoke.MethodHandles.lookup;
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
        addWindowListener( new java.awt.event.WindowAdapter() {
            public void windowClosing( java.awt.event.WindowEvent paramAnonymousWindowEvent ) {
                JPClient.out.println( "602" );
                JPClient.connectionStatusLabel.setIcon( JPClient.frozenIcon );
                CombatTurnFrame.this.dispose();
            }
        } );
        setTitle( "Combat!" );
        setSize( 300, 135 );
        setResizable( false );
        setIconImage( java.awt.Toolkit.getDefaultToolkit()
                .getImage( lookup().lookupClass().getClassLoader().getResource( "graphics/jparanoiaIcon.jpg" ) ) );
        this.publicLabel = new javax.swing.JLabel( "Public text:" );
        this.publicInputLine = new JTextField( 40 );
        this.publicInputLine.setPreferredSize( new java.awt.Dimension( 300, 22 ) );
        this.publicInputLine.addKeyListener( new java.awt.event.KeyListener() {
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
        this.secretLabel = new javax.swing.JLabel( "Secret text:" );
        this.secretInputLine = new JTextField( 40 );
        this.secretInputLine.setPreferredSize( new java.awt.Dimension( 300, 22 ) );
        this.secretInputLine.addKeyListener( new java.awt.event.KeyListener() {
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
        this.sendButton = new javax.swing.JButton( "Send Turn" );
        this.sendButton.setPreferredSize( new java.awt.Dimension( 100, 30 ) );
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
                new javax.swing.JOptionPane();
                javax.swing.JOptionPane.showMessageDialog( null, "Your public text must begin with\n/  or  '\nor you can leave it blank.", "Combat Error", -1 );
            }
        } );
        this.inputPanel = new javax.swing.JPanel();
        this.inputPanel.setLayout( new javax.swing.BoxLayout( this.inputPanel, 1 ) );
        this.inputPanel.add( this.publicLabel );
        this.inputPanel.add( this.publicInputLine );
        this.inputPanel.add( this.secretLabel );
        this.inputPanel.add( this.secretInputLine );
        this.sendPanel = new javax.swing.JPanel();
        this.sendPanel.setLayout( new javax.swing.BoxLayout( this.sendPanel, 0 ) );
        this.sendPanel.add( javax.swing.Box.createRigidArea( new java.awt.Dimension( 200, 0 ) ) );
        this.sendPanel.add( this.sendButton );
        this.contentPane = getContentPane();
        this.contentPane.setLayout( new javax.swing.BoxLayout( this.contentPane, 1 ) );
        this.contentPane.add( this.inputPanel );
        this.contentPane.add( this.sendPanel );
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\client\CombatTurnFrame.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
