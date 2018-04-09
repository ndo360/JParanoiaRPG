package jparanoia.server;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

public class ReturnListener implements KeyListener {
    private final static Logger logger = getLogger( MethodHandles.lookup().lookupClass());

    public void keyTyped( KeyEvent paramKeyEvent ) {}

    public void keyPressed( KeyEvent paramKeyEvent ) {
        logger.info( "Key Code = " + paramKeyEvent.getKeyCode() );
    }

    public void keyReleased( KeyEvent paramKeyEvent ) {}
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\ReturnListener.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
