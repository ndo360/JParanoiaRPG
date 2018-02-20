/*    */
package jparanoia.server;
/*    */
/*    */
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/*    */
/*    */

/*    */
/*    */ public class SpinnerPanel extends JPanel
        /*    */ {
    /*  9 */ Spinner gppSpinner = new Spinner( "PPs" );
    /* 10 */ Spinner powerSpinner = new Spinner( "Pow" );
    /* 11 */ Spinner accessSpinner = new Spinner( "Acc" );

    /*    */
    /*    */
    /*    */
    /*    */
    public SpinnerPanel()
    /*    */ {
        /* 17 */
        setLayout( new BoxLayout( this, 1 ) );
        /*    */
        /* 19 */
        setPreferredSize( new Dimension( 85, 70 ) );
        /* 20 */
        setMinimumSize( new Dimension( 85, 70 ) );
        /*    */
        /* 22 */
        add( this.gppSpinner );
        /* 23 */
        add( this.powerSpinner );
        /* 24 */
        add( this.accessSpinner );
        /*    */
    }
    /*    */
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\SpinnerPanel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
