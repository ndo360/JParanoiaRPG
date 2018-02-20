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
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */ public class PMAndStatusPanel
        /*    */ extends JPanel
        /*    */ {
    /*    */
    public PMAndStatusPanel( PrivateMessagePane paramPrivateMessagePane, StatusPanel paramStatusPanel )
    /*    */ {
        /* 17 */
        setLayout( new BoxLayout( this, 0 ) );
        /*    */
        /* 19 */
        paramStatusPanel.setPreferredSize( new Dimension( 35, 70 ) );
        /* 20 */
        paramStatusPanel.setMinimumSize( new Dimension( 35, 70 ) );
        /*    */
        /*    */
        /* 23 */
        if ( JPServer.isPXPGame )
            /*    */ {
            /* 25 */
            SpinnerPanel localSpinnerPanel = new SpinnerPanel();
            /*    */
            /*    */
            /* 28 */
            add( localSpinnerPanel );
            /*    */
        }
        /* 30 */
        add( paramStatusPanel );
        /* 31 */
        add( paramPrivateMessagePane );
        /*    */
    }
    /*    */
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\PMAndStatusPanel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
