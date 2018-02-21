package jparanoia.server;
public class KickMenuItem extends PlayerMenuItem {
    public KickMenuItem( ServerPlayer paramServerPlayer ) {
        super( paramServerPlayer, "Kick" );
    }

    protected void action() {
        this.somePlayer.kickPlayer();
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\KickMenuItem.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
