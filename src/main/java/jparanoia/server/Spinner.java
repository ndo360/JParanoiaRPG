package jparanoia.server;
import java.awt.Dimension;
import static java.lang.invoke.MethodHandles.lookup;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Spinner extends javax.swing.JPanel {
    /*   9 */ JTextField textField = new JTextField( 4 );
    /*  12 */ JButton plus = new JButton( new javax.swing.ImageIcon( java.awt.Toolkit.getDefaultToolkit()
            .getImage( lookup().lookupClass().getClassLoader().getResource( "graphics/plusIcon.jpg" ) ) ) );
    /*  15 */ JButton minus = new JButton( new javax.swing.ImageIcon( java.awt.Toolkit.getDefaultToolkit()
            .getImage( lookup().lookupClass().getClassLoader().getResource( "graphics/minusIcon.jpg" ) ) ) );
    /*  18 */ javax.swing.JLabel label = new javax.swing.JLabel( "NO LABEL" );
    /*  26 */ int value = 0;
    /*  28 */ int minValue = 0;
    /*  30 */ int maxValue = 999;

    public Spinner( String paramString ) {
        /*  36 */
        if ( paramString != null ) {
            this.label = new javax.swing.JLabel( paramString );
        }








        /*  46 */
        this.textField.setHorizontalAlignment( 4 );

        /*  48 */
        this.plus.addActionListener( new java.awt.event.ActionListener() {
            public void actionPerformed( java.awt.event.ActionEvent paramAnonymousActionEvent ) {
                /*  50 */
                if ( Spinner.this.value < Spinner.this.maxValue ) {
                    /*  51 */
                    Spinner.this.value += 1;
                    /*  52 */
                    Spinner.this.updateField();
                }
            }
            /*  55 */
        } );
        /*  56 */
        this.minus.addActionListener( new java.awt.event.ActionListener() {
            public void actionPerformed( java.awt.event.ActionEvent paramAnonymousActionEvent ) {
                /*  58 */
                if ( Spinner.this.value > Spinner.this.minValue ) {
                    /*  59 */
                    Spinner.this.value -= 1;
                    /*  60 */
                    Spinner.this.updateField();
                }
            }
            /*  63 */
        } );
        /*  64 */
        setPreferredSize( new Dimension( 40, 30 ) );
        /*  65 */
        setMaximumSize( new Dimension( 40, 30 ) );


























        /*  92 */
        setLayout( new javax.swing.BoxLayout( this, 0 ) );

        /*  94 */
        this.label.setMinimumSize( new Dimension( 30, 24 ) );
        /*  95 */
        this.label.setPreferredSize( new Dimension( 30, 24 ) );
        /*  96 */
        this.label.setMaximumSize( new Dimension( 30, 24 ) );

        /*  98 */
        this.textField.setMinimumSize( new Dimension( 23, 24 ) );
        /*  99 */
        this.textField.setPreferredSize( new Dimension( 23, 24 ) );
        /* 100 */
        this.textField.setMaximumSize( new Dimension( 23, 24 ) );

        /* 102 */
        this.minus.setMinimumSize( new Dimension( 14, 24 ) );
        /* 103 */
        this.minus.setPreferredSize( new Dimension( 15, 24 ) );
        /* 104 */
        this.minus.setMaximumSize( new Dimension( 20, 24 ) );

        /* 106 */
        this.plus.setMinimumSize( new Dimension( 14, 24 ) );
        /* 107 */
        this.plus.setPreferredSize( new Dimension( 15, 24 ) );
        /* 108 */
        this.plus.setMaximumSize( new Dimension( 20, 24 ) );

        /* 110 */
        setMinimumSize( new Dimension( 85, 22 ) );
        /* 111 */
        setPreferredSize( new Dimension( 85, 22 ) );
        /* 112 */
        setMaximumSize( new Dimension( 85, 22 ) );

        /* 114 */
        add( this.label );
        /* 115 */
        add( this.textField );

        /* 117 */
        add( this.minus );
        /* 118 */
        add( this.plus );
        /* 119 */
        add( javax.swing.Box.createRigidArea( new Dimension( 2, 0 ) ) );

        /* 121 */
        updateField();
    }

    private void updateField() {
        /* 126 */
        this.textField.setText( Integer.toString( this.value ) );
    }

    int getValue() {
        /* 140 */
        return this.value;
    }

    void setValue( int paramInt ) {
        /* 131 */
        if ( paramInt < this.minValue || paramInt > this.maxValue ) {
            return;
        }
        /* 133 */
        this.value = paramInt;

        /* 135 */
        updateField();
    }

    void setMin( int paramInt ) {
        /* 145 */
        this.minValue = paramInt;
    }

    void setMax( int paramInt ) {
        /* 150 */
        this.maxValue = paramInt;
    }

    void lockMax() {
        /* 155 */
        this.maxValue = this.value;
    }

    public void setEnabled( boolean paramBoolean ) {
        /* 160 */
        this.textField.setEnabled( paramBoolean );
        /* 161 */
        this.plus.setEnabled( paramBoolean );
        /* 162 */
        this.minus.setEnabled( paramBoolean );
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\Spinner.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
