
package jparanoia.shared;


import java.awt.event.ActionEvent;
import javax.swing.JButton;




 public class JPGameInfo
         {
       public String ipAddress;
       public String description;
       public JButton connectButton;



    public JPGameInfo( String paramString1, String paramString2 )
     {

        this.ipAddress = paramString1;

        this.description = paramString2;

        this.connectButton = new JButton( "Join" );


        this.connectButton.addActionListener(paramAnonymousActionEvent -> {

            jparanoia.client.JPClient.connect( JPGameInfo.this.ipAddress, true, JPGameInfo.this.description );

            jparanoia.client.ConnectManager.frame.setVisible( false );

        });

    }

}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\shared\JPGameInfo.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
