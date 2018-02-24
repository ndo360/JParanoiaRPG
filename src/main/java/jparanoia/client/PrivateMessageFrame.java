package jparanoia.client;
import static java.awt.Color.black;
import static java.awt.Color.white;
import java.awt.Container;
import java.lang.invoke.MethodHandles;
import static java.lang.invoke.MethodHandles.lookup;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

public class PrivateMessageFrame extends javax.swing.JFrame {
    private final static Logger logger = getLogger( MethodHandles.lookup().lookupClass());

    final ClientPlayer player;
    final java.awt.Dimension IDEAL_DIMENSION = new java.awt.Dimension( 250, 175 );
    JTextPane displayArea;
    javax.swing.JScrollPane scrollPane;
    JMenuItem pmWindowMenuItem;
    Container contentPane;
    DefaultStyledDocument privateMessageDocument;
    JTextField inputLine;

    public PrivateMessageFrame( ClientPlayer paramClientPlayer ) {
        this.player = paramClientPlayer;
        setTitle( this.player.getName() );
        setIconImage( java.awt.Toolkit.getDefaultToolkit()
                .getImage( lookup().lookupClass().getClassLoader().getResource( "graphics/jparanoiaIcon.jpg" ) ) );
        addItemToMenu();
        setDefaultCloseOperation( WindowConstants.HIDE_ON_CLOSE );
        this.displayArea = new JTextPane();
        this.displayArea.setEditable( false );
        this.displayArea.setEnabled( false );
        switch ( JPClient.currentColorScheme ) {
            case "White on Black":
                this.displayArea.setDisabledTextColor( white );
                this.displayArea.setBackground( black );
                break;
            case "Black on White":
                this.displayArea.setDisabledTextColor( black );
                this.displayArea.setBackground( white );
                break;
            default:
                logger.info( "PivateMessageFrame error: no recognized color scheme selected..." );
                break;
        }
        this.scrollPane = new javax.swing.JScrollPane( this.displayArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
        this.privateMessageDocument = new DefaultStyledDocument();
        this.inputLine = new JTextField( 20 );
        this.inputLine.addActionListener( paramAnonymousActionEvent -> {
            String str = PrivateMessageFrame.this.inputLine.getText();
            if ( !str.equals( "" ) ) {
                JPClient.sendPrivateMessage( PrivateMessageFrame.this.player.getID() +
                        JPClient.myPlayer.getID() +
                        str );
                PrivateMessageFrame.this.addMyMessage( str );
            }
            PrivateMessageFrame.this.inputLine.setText( "" );
        } );
        this.inputLine.setEnabled( this.player.isLoggedIn() );
        Container localContainer = getContentPane();
        localContainer.add( this.scrollPane, "Center" );
        localContainer.add( this.inputLine, "South" );
        pack();
        setSize( this.IDEAL_DIMENSION );
        this.player.setPMFrame( this );
        setLocation( new java.awt.Point( JPClient.rand.nextInt( 500 ), JPClient.rand.nextInt( 400 ) ) );
    }

    public void addMyMessage( String paramString ) {
        if ( paramString.trim().equals( "" ) ) {
            return;
        }
        try {
            JPClient.textAttributes.addAttribute( javax.swing.text.StyleConstants.CharacterConstants.Foreground, JPClient.myPlayer
                    .getChatColor() );
            this.privateMessageDocument.insertString( this.privateMessageDocument.getLength(), "  " +
                    JPClient.myPlayer.getName() +
                    ": ", JPClient.textAttributes );
            JPClient.textAttributes.addAttribute( javax.swing.text.StyleConstants.CharacterConstants.Foreground, JPClient.textColor );
            this.privateMessageDocument.insertString( this.privateMessageDocument.getLength(), paramString +
                    "\n", JPClient.textAttributes );
            this.displayArea.setDocument( this.privateMessageDocument );
            if ( JPClient.autoScroll ) {
                this.displayArea.setCaretPosition( this.privateMessageDocument.getLength() );
            }
            if ( JPClient.keepLog ) {
                String str;
                if ( JPClient.htmlLog ) {
                    str = "<span class=\"pm\"><span class=\"player" +
                            JPClient.MY_PLAYER_NUMBER +
                            "\">(" +
                            JPClient.myPlayer.getName() +
                            "</span> -> <span class=\"player" +
                            this.player.getPlayerNumber() +
                            "\">" +
                            this.player.toString() +
                            ")</span>: " +
                            paramString +
                            "</span><br/>";
                } else {
                    str = "(" + JPClient.myPlayer.getName() + " -> " + this.player.toString() + "): " + paramString;
                }
                JPClient.log.logEntry( str );
            }
        } catch ( BadLocationException localBadLocationException ) {
            System.err.println( "Unhandled exception. (Bad Location)" );
        }
    }

    void addItemToMenu() {
        this.pmWindowMenuItem = new JMenuItem( getTitle() );
        this.pmWindowMenuItem.addActionListener( paramAnonymousActionEvent -> {
            PrivateMessageFrame.this.setVisible( true );
            PrivateMessageFrame.this.toFront();
        } );
        JPClient.pmWindowsMenu.add( this.pmWindowMenuItem );
    }

    public void enableInput() {
        this.inputLine.setEnabled( true );
    }

    public void disableInput() {
        this.inputLine.setEnabled( false );
    }

    public String toString() {
        return this.player.getName() + "'s private message frame";
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\client\PrivateMessageFrame.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
