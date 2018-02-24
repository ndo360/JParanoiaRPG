package jparanoia.server;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class SpinnerPanel extends JPanel {
    Spinner gppSpinner = new Spinner( "PPs" );
    Spinner powerSpinner = new Spinner( "Pow" );
    Spinner accessSpinner = new Spinner( "Acc" );

    public SpinnerPanel() {
        setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );
        setPreferredSize( new Dimension( 85, 70 ) );
        setMinimumSize( new Dimension( 85, 70 ) );
        add( this.gppSpinner );
        add( this.powerSpinner );
        add( this.accessSpinner );
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\SpinnerPanel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
