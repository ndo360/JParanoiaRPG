package jparanoia.shared;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ObserversFrame extends JFrame {
    public JTable jt;
    public JScrollPane sp;

    public ObserversFrame() {
        super( "Observers" );
        setIconImage( Toolkit.getDefaultToolkit()
                .getImage( getClass().getClassLoader().getResource( "graphics/jparanoiaIcon.jpg" ) ) );
        Vector localVector = new Vector( 0 );
        localVector.add( "whocares" );
        this.jt = new JTable( JParanoia.obsNames, localVector );
        this.jt.setShowGrid( false );
        this.jt.setEnabled( false );
        getContentPane().add( this.jt );
        setSize( 200, 200 );
        setLocation( 600, 100 );
        addWindowListener( new WindowAdapter() {
            public void windowClosing( WindowEvent paramAnonymousWindowEvent ) {
                ObserversFrame.this.setVisible( false );
            }
        } );
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\shared\ObserversFrame.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
