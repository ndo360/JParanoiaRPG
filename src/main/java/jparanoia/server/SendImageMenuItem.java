/*    */
package jparanoia.server;
/*    */
/*    */
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import jparanoia.shared.JPImage;

/*    */
/*    */

/*    */
/*    */ public class SendImageMenuItem extends JMenuItem
        /*    */ {
    /*    */ JPImage imageItem;

    /*    */
    /*    */
    public SendImageMenuItem( JPImage paramJPImage )
    /*    */ {
        /* 13 */
        super( paramJPImage.getName() );
        /* 14 */
        this.imageItem = paramJPImage;
        /* 15 */
        addActionListener( new java.awt.event.ActionListener() {
            /*    */
            public void actionPerformed( ActionEvent paramAnonymousActionEvent ) {
                /* 17 */
                JPServer.spamString( "404" + SendImageMenuItem.this.getImageInfo() );
                /* 18 */
                jparanoia.shared.JParanoia.displayImage( SendImageMenuItem.this.getImageInfo() );
                /* 19 */
                if ( JPServer.keepLog )
                    /*    */ {
                    /*    */
                    String str;
                    /* 22 */
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
                    } else
                        /* 23 */ {
                        str = "IMAGE: \"" +
                                SendImageMenuItem.this.imageItem.getName() +
                                "\" URL: " +
                                SendImageMenuItem.this.imageItem.getURL();
                    }
                    /* 24 */
                    JPServer.logger.logEntry( str );
                    /*    */
                }
                /*    */
            }
            /*    */
        } );
        /*    */
    }

    /*    */
    /*    */
    public String getImageInfo() {
        /* 31 */
        return this.imageItem.getName() + this.imageItem.getURL();
        /*    */
    }
    /*    */
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\SendImageMenuItem.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
