package jparanoia.server;
import javax.swing.JMenuItem;
import jparanoia.shared.JPImage;

public class SendImageMenuItem extends JMenuItem {
    JPImage imageItem;

    public SendImageMenuItem( JPImage paramJPImage ) {
        super( paramJPImage.getName() );
        this.imageItem = paramJPImage;
        addActionListener( paramAnonymousActionEvent -> {
            JPServer.spamString( "404" + SendImageMenuItem.this.getImageInfo() );
            jparanoia.shared.JParanoia.displayImage( SendImageMenuItem.this.getImageInfo() );
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
                JPServer.logger.logEntry( str );
            }
        } );
    }

    public String getImageInfo() {
        return this.imageItem.getName() + this.imageItem.getURL();
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\SendImageMenuItem.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
