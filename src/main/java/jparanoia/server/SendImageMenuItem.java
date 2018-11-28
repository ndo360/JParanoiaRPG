package jparanoia.server;
import java.lang.invoke.MethodHandles;
import javax.swing.JMenuItem;
import jparanoia.shared.JPImage;
import jparanoia.shared.JParanoia;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

public class SendImageMenuItem extends JMenuItem {
    private final static Logger logger = getLogger( MethodHandles.lookup().lookupClass());

    JPImage imageItem;

    public SendImageMenuItem( JPImage paramJPImage ) {
        super( paramJPImage.getName() );
        this.imageItem = paramJPImage;
        addActionListener( paramAnonymousActionEvent -> {
            JPServer.spamString( "404" + SendImageMenuItem.this.getImageInfo() );
            JParanoia.displayImage( SendImageMenuItem.this.getImageInfo() );
            if ( JPServer.keepLog ) {
                String str;
                if ( JPServer.htmlLog ) {
                    str = "IMAGE: \"" +
                            SendImageMenuItem.this.imageItem.getName() +
                            "\" URL: <a href=\"" +
                            SendImageMenuItem.this.imageItem.getURL() +
                            "\">" +
                            SendImageMenuItem.this.imageItem.getURL() +
                            "</a><br/>\n<img src=\"" +
                            SendImageMenuItem.this.imageItem.getURL() +
                            "\"><br/>";
                } else {
                    str = "IMAGE: \"" +
                            SendImageMenuItem.this.imageItem.getName() +
                            "\" URL: " +
                            SendImageMenuItem.this.imageItem.getURL();
                }
                JPServer.log.logEntry( str );
            }
        } );
    }

    public String getImageInfo() {
        return this.imageItem.getName()+ "|" + this.imageItem.getURL();
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\SendImageMenuItem.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
