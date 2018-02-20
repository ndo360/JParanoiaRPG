package jparanoia.server;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.invoke.MethodHandles.lookup;
import javax.swing.Box;
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
    /*  21 */ int oldRoll = 0;
    int rollValue;
    String rollString;
    String rollZero;

    public DicePanel() {
        /*  29 */
        this.r = new java.util.Random();

        /*  31 */
        setLayout( new javax.swing.BoxLayout( this, 0 ) );



        /*  35 */
        this.d10Icon = new ImageIcon( lookup().lookupClass().getClassLoader().getResource( "graphics/d10.jpg" ) );
        /*  36 */
        this.d20Icon = new ImageIcon( lookup().lookupClass().getClassLoader().getResource( "graphics/d20.jpg" ) );
        /*  37 */
        this.d100Icon = new ImageIcon( lookup().lookupClass().getClassLoader().getResource( "graphics/d100.jpg" ) );


        /*  40 */
        this.d10Button = new JButton( this.d10Icon );
        /*  41 */
        this.d10Button.setPreferredSize( new Dimension( 32, 18 ) );
        /*  42 */
        this.d10Button.setMaximumSize( new Dimension( 32, 18 ) );
        /*  43 */
        /*  49 */
        this.d10Button.addActionListener(paramAnonymousActionEvent -> {
            /*  45 */
            DicePanel.this.rollDie( 10 );
            /*  46 */
            JPServer.inputLine.requestFocus();
        });
        /*  50 */
        this.d20Button = new JButton( this.d20Icon );
        /*  51 */
        this.d20Button.setPreferredSize( new Dimension( 32, 18 ) );
        /*  52 */
        this.d20Button.setMaximumSize( new Dimension( 32, 18 ) );
        /*  53 */
        /*  58 */
        this.d20Button.addActionListener(paramAnonymousActionEvent -> {
            /*  55 */
            DicePanel.this.rollDie( 20 );
            /*  56 */
            JPServer.inputLine.requestFocus();
        });
        /*  59 */
        this.d100Button = new JButton( this.d100Icon );
        /*  60 */
        this.d100Button.setMinimumSize( new Dimension( 38, 18 ) );
        /*  61 */
        this.d100Button.setPreferredSize( new Dimension( 38, 18 ) );
        /*  62 */
        this.d100Button.setMaximumSize( new Dimension( 38, 18 ) );
        /*  63 */
        /*  68 */
        this.d100Button.addActionListener(paramAnonymousActionEvent -> {
            /*  65 */
            DicePanel.this.rollDie( 100 );
            /*  66 */
            JPServer.inputLine.requestFocus();
        });
        /*  69 */
        this.diceOutputField = new JTextField( 4 );

        /*  71 */
        this.diceOutputField.setEnabled( false );
        /*  72 */
        this.diceOutputField.setEditable( false );
        /*  73 */
        this.diceOutputField.setDisabledTextColor( java.awt.Color.black );

        /*  75 */
        java.awt.Font localFont = new java.awt.Font( "Monospaced", 1, 16 );

        /*  77 */
        this.diceOutputField.setFont( localFont );
        /*  78 */
        this.diceOutputField.setHorizontalAlignment( 0 );

        /*  80 */
        add( this.d10Button );
        /*  81 */
        add( Box.createRigidArea( new Dimension( 5, 0 ) ) );
        /*  82 */
        add( this.d20Button );
        /*  83 */
        add( Box.createRigidArea( new Dimension( 5, 0 ) ) );
        /*  84 */
        add( this.d100Button );
        /*  85 */
        add( Box.createRigidArea( new Dimension( 5, 0 ) ) );
        /*  86 */
        add( this.diceOutputField );
        /*  87 */
        add( Box.createRigidArea( new Dimension( 5, 0 ) ) );
    }

    public void rollDie( int paramInt ) {
        /*  93 */
        this.diceOutputField.setDisabledTextColor( java.awt.Color.black );

        /*  95 */
        this.rollValue = this.r.nextInt( paramInt ) + 1;

        /*  97 */
        if ( this.oldRoll == this.rollValue ) {
            /*  99 */
            this.diceOutputField.setDisabledTextColor( java.awt.Color.red );
            /* 100 */
            this.oldRoll = 0;
        } else {
            /* 103 */
            this.oldRoll = this.rollValue;
        }
        /* 105 */
        if ( this.rollValue < 10 ) {
            this.rollZero = "0";
        } else {
            /* 106 */
            this.rollZero = "";
        }
        /* 108 */
        this.rollString = this.rollZero + this.rollValue;

        /* 110 */
        this.diceOutputField.setText( this.rollString );
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\DicePanel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
