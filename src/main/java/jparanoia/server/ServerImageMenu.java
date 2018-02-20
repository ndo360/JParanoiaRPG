/*    */
package jparanoia.server;
/*    */
/*    */
import java.util.ArrayList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/*    */
/*    */
/*    */

/*    */
/*    */ public class ServerImageMenu extends JMenu
        /*    */ {
    /*    */ SendImageMenuItem[] sendImageMenuItems;
    /*    */ JMenuItem unplannedMenuItem;
    /*    */ ArrayList imageInfo;
    /* 13 */ int i = 0;

    /*    */
    /*    */
    public ServerImageMenu()
    /*    */ {
        /* 17 */
        super( "Send Image" );
        /*    */
        /* 19 */
        this.imageInfo = JPServer.idp.getImageInfo();
        /*    */
        /* 21 */
        this.sendImageMenuItems = new SendImageMenuItem[this.imageInfo.size()];
        /*    */
        /*    */
        /*    */
        /* 25 */
        for ( this.i = 0; this.i < this.imageInfo.size(); this.i += 1 )
            /*    */ {
            /* 27 */
            this.sendImageMenuItems[this.i] = new SendImageMenuItem( (jparanoia.shared.JPImage) this.imageInfo.get( this.i ) );
            /* 28 */
            add( this.sendImageMenuItems[this.i] );
            /*    */
        }
        /*    */
        /* 31 */
        addSeparator();
        /*    */
        /* 33 */
        this.unplannedMenuItem = new JMenuItem( "Unplanned Image..." );
        /* 34 */
        /*    *//* 36 *//* 37 */
        this.unplannedMenuItem.addActionListener(paramAnonymousActionEvent -> ServerImageMenu.this.sendUnplannedImage());
        /* 38 */
        add( this.unplannedMenuItem );
        /*    */
    }

    /*    */
    /*    */
    private void sendUnplannedImage()
    /*    */ {
        /* 43 */
        JOptionPane localJOptionPane = new JOptionPane();
        /*    */
        /*    */
        String str1;
        /*    */
        do
            /*    */ {
            /* 48 */
            str1 = (String) JOptionPane.showInputDialog( null, "Enter a description of the image:", "Unplanned Image...", -1, null, null, "" );
            /*    */
            /*    */
            /* 51 */
            if ( str1 == null ) {
                return;
                /*    */
            }
            /* 53 */
        } while ( str1.startsWith( "http://" ) );
        /*    */
        /* 55 */
        String str2 = (String) JOptionPane.showInputDialog( null, "Enter the URL of the image (including http://):", "Unplanned Image URL...", -1, null, null, "" );
        /*    */
        /*    */
        /*    */
        /*    */
        /* 60 */
        if ( str2 == null || str2.equals( "" ) ) {
            return;
            /*    */
        }
        /* 62 */
        if ( !str2.startsWith( "http://" ) ) {
            str2 = "http://" + str2;
            /*    */
        }
        /* 64 */
        JPServer.spamString( "404" + str1 + str2 );
        /* 65 */
        jparanoia.shared.JParanoia.displayImage( str1 + str2 );
        /* 66 */
        if ( JPServer.keepLog )
            /*    */ {
            /*    */
            String str3;
            /* 69 */
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
            } else
                /* 70 */ {
                str3 = "IMAGE: \"" + str1 + "\" URL: " + str2;
            }
            /* 71 */
            JPServer.logger.logEntry( str3 );
            /*    */
        }
        /*    */
    }
    /*    */
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\ServerImageMenu.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
