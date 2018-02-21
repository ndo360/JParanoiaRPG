
package jparanoia.server;

 public class NPCRenameMenuItem
         extends PlayerMenuItem
         {

    public NPCRenameMenuItem( ServerPlayer paramServerPlayer )
     {

        super( paramServerPlayer, "Rename" );

    }



    protected void action()
     {

        this.somePlayer.npcRename();

    }

}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\NPCRenameMenuItem.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
