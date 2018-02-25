package jparanoia.server;
import java.lang.invoke.MethodHandles;
import javax.swing.JMenuItem;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

public abstract class PlayerMenuItem extends JMenuItem {
    private final static Logger logger = getLogger( MethodHandles.lookup().lookupClass());

    ServerPlayer somePlayer;

    public PlayerMenuItem() {
        logger.info( "Error: invalid use of PlayerMenuItem constructor; must provide ServerPlayer object" );
    }

    public PlayerMenuItem( ServerPlayer paramServerPlayer, String paramString ) {
        super( paramString );
        this.somePlayer = paramServerPlayer;
        addActionListener( paramAnonymousActionEvent -> {
            PlayerMenuItem.this.action();
        } );
    }

    protected void action() {}
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\PlayerMenuItem.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
