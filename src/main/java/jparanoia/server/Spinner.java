package jparanoia.server;
import java.awt.Dimension;
import static java.lang.invoke.MethodHandles.lookup;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Spinner extends javax.swing.JPanel {
    JTextField textField = new JTextField( 4 );
    JButton plus = new JButton( new javax.swing.ImageIcon( java.awt.Toolkit.getDefaultToolkit()
            .getImage( lookup().lookupClass().getClassLoader().getResource( "graphics/plusIcon.jpg" ) ) ) );
    JButton minus = new JButton( new javax.swing.ImageIcon( java.awt.Toolkit.getDefaultToolkit()
            .getImage( lookup().lookupClass().getClassLoader().getResource( "graphics/minusIcon.jpg" ) ) ) );
    javax.swing.JLabel label = new javax.swing.JLabel( "NO LABEL" );
    int value = 0;
    int minValue = 0;
    int maxValue = 999;

    public Spinner( String paramString ) {
        if ( paramString != null ) {
            this.label = new javax.swing.JLabel( paramString );
        }
        this.textField.setHorizontalAlignment( 4 );
        this.plus.addActionListener( paramAnonymousActionEvent -> {
            if ( Spinner.this.value < Spinner.this.maxValue ) {
                Spinner.this.value += 1;
                Spinner.this.updateField();
            }
        } );
        this.minus.addActionListener( paramAnonymousActionEvent -> {
            if ( Spinner.this.value > Spinner.this.minValue ) {
                Spinner.this.value -= 1;
                Spinner.this.updateField();
            }
        } );
        setPreferredSize( new Dimension( 40, 30 ) );
        setMaximumSize( new Dimension( 40, 30 ) );
        setLayout( new javax.swing.BoxLayout( this, 0 ) );
        this.label.setMinimumSize( new Dimension( 30, 24 ) );
        this.label.setPreferredSize( new Dimension( 30, 24 ) );
        this.label.setMaximumSize( new Dimension( 30, 24 ) );
        this.textField.setMinimumSize( new Dimension( 23, 24 ) );
        this.textField.setPreferredSize( new Dimension( 23, 24 ) );
        this.textField.setMaximumSize( new Dimension( 23, 24 ) );
        this.minus.setMinimumSize( new Dimension( 14, 24 ) );
        this.minus.setPreferredSize( new Dimension( 15, 24 ) );
        this.minus.setMaximumSize( new Dimension( 20, 24 ) );
        this.plus.setMinimumSize( new Dimension( 14, 24 ) );
        this.plus.setPreferredSize( new Dimension( 15, 24 ) );
        this.plus.setMaximumSize( new Dimension( 20, 24 ) );
        setMinimumSize( new Dimension( 85, 22 ) );
        setPreferredSize( new Dimension( 85, 22 ) );
        setMaximumSize( new Dimension( 85, 22 ) );
        add( this.label );
        add( this.textField );
        add( this.minus );
        add( this.plus );
        add( javax.swing.Box.createRigidArea( new Dimension( 2, 0 ) ) );
        updateField();
    }

    private void updateField() {
        this.textField.setText( Integer.toString( this.value ) );
    }

    int getValue() {
        return this.value;
    }

    void setValue( int paramInt ) {
        if ( paramInt < this.minValue || paramInt > this.maxValue ) {
            return;
        }
        this.value = paramInt;
        updateField();
    }

    void setMin( int paramInt ) {
        this.minValue = paramInt;
    }

    void setMax( int paramInt ) {
        this.maxValue = paramInt;
    }

    void lockMax() {
        this.maxValue = this.value;
    }

    public void setEnabled( boolean paramBoolean ) {
        this.textField.setEnabled( paramBoolean );
        this.plus.setEnabled( paramBoolean );
        this.minus.setEnabled( paramBoolean );
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\Spinner.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
