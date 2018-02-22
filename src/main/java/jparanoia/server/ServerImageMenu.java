package jparanoia.server;
import java.util.ArrayList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class ServerImageMenu extends JMenu {
    SendImageMenuItem[] sendImageMenuItems;
    JMenuItem unplannedMenuItem;
    ArrayList imageInfo;
    int i = 0;

    public ServerImageMenu() {
        super( "Send Image" );
        this.imageInfo = JPServer.idp.getImageInfo();
        this.sendImageMenuItems = new SendImageMenuItem[this.imageInfo.size()];
        for ( this.i = 0; this.i < this.imageInfo.size(); this.i += 1 ) {
            this.sendImageMenuItems[this.i] = new SendImageMenuItem( (jparanoia.shared.JPImage) this.imageInfo.get( this.i ) );
            add( this.sendImageMenuItems[this.i] );
        }
        addSeparator();
        this.unplannedMenuItem = new JMenuItem( "Unplanned Image..." );
        this.unplannedMenuItem.addActionListener( paramAnonymousActionEvent -> ServerImageMenu.this.sendUnplannedImage() );
        add( this.unplannedMenuItem );
    }

    private void sendUnplannedImage() {
        JOptionPane localJOptionPane = new JOptionPane();
        String str1;
        do {
            str1 = (String) JOptionPane.showInputDialog( null, "Enter a description of the image:", "Unplanned Image...", JOptionPane.PLAIN_MESSAGE, null, null, "" );
            if ( str1 == null ) {
                return;
            }
        } while ( str1.startsWith( "http://" ) );
        String str2 = (String) JOptionPane.showInputDialog( null, "Enter the URL of the image (including http://):", "Unplanned Image URL...", JOptionPane.PLAIN_MESSAGE, null, null, "" );
        if ( str2 == null || str2.equals( "" ) ) {
            return;
        }
        if ( !str2.startsWith( "http://" ) ) {
            str2 = "http://" + str2;
        }
        JPServer.spamString( "404" + str1 + str2 );
        jparanoia.shared.JParanoia.displayImage( str1 + str2 );
        if ( JPServer.keepLog ) {
            String str3;
            if ( JPServer.htmlLog ) {
                str3 = "IMAGE: \"" +
                        str1 +
                        "\" URL: <a href=\"" +
                        str2 +
                        "\">" +
                        str2 +
                        "</a><br/>\n<img src=\"" +
                        str2 +
                        "\"><br/>";
            } else {
                str3 = "IMAGE: \"" + str1 + "\" URL: " + str2;
            }
            JPServer.logger.logEntry( str3 );
        }
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\ServerImageMenu.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
