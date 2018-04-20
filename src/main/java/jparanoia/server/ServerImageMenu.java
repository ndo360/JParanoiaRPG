package jparanoia.server;
import java.lang.invoke.MethodHandles;
import java.text.MessageFormat;
import java.util.ArrayList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import jparanoia.shared.JPImage;
import jparanoia.shared.JParanoia;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

public class ServerImageMenu extends JMenu {
    private final static Logger logger = getLogger( MethodHandles.lookup().lookupClass());

    SendImageMenuItem[] sendImageMenuItems;
    JMenuItem unplannedMenuItem;
    ArrayList imageInfo;
    int i = 0;

    public ServerImageMenu() {
        super( "Send Image" );
        this.imageInfo = JPServer.idp.getImageInfo();
        this.sendImageMenuItems = new SendImageMenuItem[this.imageInfo.size()];
        for ( this.i = 0; this.i < this.imageInfo.size(); this.i += 1 ) {
            this.sendImageMenuItems[this.i] = new SendImageMenuItem( (JPImage) this.imageInfo.get( this.i ) );
            add( this.sendImageMenuItems[this.i] );
        }
        addSeparator();
        this.unplannedMenuItem = new JMenuItem( "Unplanned Image..." );
        this.unplannedMenuItem.addActionListener( paramAnonymousActionEvent -> ServerImageMenu.this.sendUnplannedImage() );
        add( this.unplannedMenuItem );
    }

    private void sendUnplannedImage() {
//        JOptionPane localJOptionPane = new JOptionPane();
        String imgDescription;
        do {
            imgDescription = (String) JOptionPane.showInputDialog( null, "Enter a description of the image:",
                    "Unplanned Image...", JOptionPane.PLAIN_MESSAGE, null, null, "" );
            if ( imgDescription == null ) {
                return;
            }
        } while ( isLink(imgDescription) ); //For folks that insert links into description

        String imgLink = (String) JOptionPane.showInputDialog( null, "Enter the URL of the image (including http://):",
                "Unplanned Image URL...", JOptionPane.PLAIN_MESSAGE, null, null, "" );
        if ( imgLink == null || imgLink.equals( "" ) ) {
            return;
        }
        if ( !isLink( imgLink ) ) {
            imgLink = "http://" + imgLink;
        }
        JPServer.spamString( "404" + imgDescription + JParanoia.IMG_DELIMITER + imgLink );
        JParanoia.displayImage( imgDescription + JParanoia.IMG_DELIMITER + imgLink );
        if ( JPServer.keepLog ) {
            String logEntry;
            if ( JPServer.htmlLog ) {
                logEntry = MessageFormat.format( "IMAGE: \"{0}\" " +
                                "URL: <a href=\"{1}\">{2}</a><br/>\n<img src=\"{3}\"><br/>",
                                imgDescription, imgLink, imgLink, imgLink );
            } else {
                logEntry = "IMAGE: \"" + imgDescription + "\" URL: " + imgLink;
            }
            JPServer.log.logEntry( logEntry );
        }
    }

    private static boolean isLink( String supposedLink ) {
     return supposedLink.startsWith( "http://" ) || supposedLink.startsWith( "https://" );
    }
}

/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\ServerImageMenu.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
