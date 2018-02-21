package jparanoia.server;
import javax.swing.JMenuItem;

public abstract class PlayerMenuItem extends JMenuItem {
    ServerPlayer somePlayer;

    public PlayerMenuItem() {
        System.out.println( "Error: invalid use of PlayerMenuItem constructor; must provide ServerPlayer object" );
    }

    public PlayerMenuItem( ServerPlayer paramServerPlayer, String paramString ) {
        super( paramString );
        this.somePlayer = paramServerPlayer;
        addActionListener( paramAnonymousActionEvent -> {
            PlayerMenuItem.this.action();
        } );
    }

    protected void action() {
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\PlayerMenuItem.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
