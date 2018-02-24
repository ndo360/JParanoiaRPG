package jparanoia.server;
import javax.swing.JMenu;

public class NPCMenu extends JMenu {
    NPCRenameMenuItem npcRenameMenuItem;

    public NPCMenu( ServerPlayer paramServerPlayer ) {
        super( paramServerPlayer.getName() );
        add( this.npcRenameMenuItem = new NPCRenameMenuItem( paramServerPlayer ) );
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\NPCMenu.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
