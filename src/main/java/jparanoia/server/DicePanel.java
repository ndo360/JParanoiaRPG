package jparanoia.server;
import java.awt.Dimension;
import java.awt.Font;
import static java.lang.invoke.MethodHandles.lookup;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

public class DicePanel extends javax.swing.JPanel {
    JButton d10Button;
    JButton d20Button;
    JButton d100Button;
    JTextField diceOutputField;
    ImageIcon d10Icon;
    ImageIcon d20Icon;
    ImageIcon d100Icon;
    java.util.Random r;
    int oldRoll = 0;
    int rollValue;
    String rollString;
    String rollZero;

    public DicePanel() {
        this.r = new java.util.Random();
        setLayout( new javax.swing.BoxLayout( this, BoxLayout.X_AXIS ) );
        this.d10Icon = new ImageIcon( lookup().lookupClass().getClassLoader().getResource( "graphics/d10.jpg" ) );
        this.d20Icon = new ImageIcon( lookup().lookupClass().getClassLoader().getResource( "graphics/d20.jpg" ) );
        this.d100Icon = new ImageIcon( lookup().lookupClass().getClassLoader().getResource( "graphics/d100.jpg" ) );
        this.d10Button = new JButton( this.d10Icon );
        this.d10Button.setPreferredSize( new Dimension( 32, 18 ) );
        this.d10Button.setMaximumSize( new Dimension( 32, 18 ) );
        this.d10Button.addActionListener( paramAnonymousActionEvent -> {
            DicePanel.this.rollDie( 10 );
            JPServer.inputLine.requestFocus();
        } );
        this.d20Button = new JButton( this.d20Icon );
        this.d20Button.setPreferredSize( new Dimension( 32, 18 ) );
        this.d20Button.setMaximumSize( new Dimension( 32, 18 ) );
        this.d20Button.addActionListener( paramAnonymousActionEvent -> {
            DicePanel.this.rollDie( 20 );
            JPServer.inputLine.requestFocus();
        } );
        this.d100Button = new JButton( this.d100Icon );
        this.d100Button.setMinimumSize( new Dimension( 38, 18 ) );
        this.d100Button.setPreferredSize( new Dimension( 38, 18 ) );
        this.d100Button.setMaximumSize( new Dimension( 38, 18 ) );
        this.d100Button.addActionListener( paramAnonymousActionEvent -> {
            DicePanel.this.rollDie( 100 );
            JPServer.inputLine.requestFocus();
        } );
        this.diceOutputField = new JTextField( 4 );
        this.diceOutputField.setEnabled( false );
        this.diceOutputField.setEditable( false );
        this.diceOutputField.setDisabledTextColor( java.awt.Color.black );
        java.awt.Font localFont = new java.awt.Font( "Monospaced", Font.BOLD, 16 );
        this.diceOutputField.setFont( localFont );
        this.diceOutputField.setHorizontalAlignment( 0 );
        add( this.d10Button );
        add( Box.createRigidArea( new Dimension( 5, 0 ) ) );
        add( this.d20Button );
        add( Box.createRigidArea( new Dimension( 5, 0 ) ) );
        add( this.d100Button );
        add( Box.createRigidArea( new Dimension( 5, 0 ) ) );
        add( this.diceOutputField );
        add( Box.createRigidArea( new Dimension( 5, 0 ) ) );
    }

    public void rollDie( int paramInt ) {
        this.diceOutputField.setDisabledTextColor( java.awt.Color.black );
        this.rollValue = this.r.nextInt( paramInt ) + 1;
        if ( this.oldRoll == this.rollValue ) {
            this.diceOutputField.setDisabledTextColor( java.awt.Color.red );
            this.oldRoll = 0;
        } else {
            this.oldRoll = this.rollValue;
        }
        if ( this.rollValue < 10 ) {
            this.rollZero = "0";
        } else {
            this.rollZero = "";
        }
        this.rollString = this.rollZero + this.rollValue;
        this.diceOutputField.setText( this.rollString );
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\DicePanel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
