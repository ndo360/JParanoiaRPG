package jparanoia.client;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import static java.lang.invoke.MethodHandles.lookup;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;

public class PrivateMessageFrame extends javax.swing.JFrame {
    final ClientPlayer player;
    /*  16 */   final java.awt.Dimension IDEAL_DIMENSION = new java.awt.Dimension( 250, 175 );
    JTextPane displayArea;
    javax.swing.JScrollPane scrollPane;
    JMenuItem pmWindowMenuItem;
    Container contentPane;
    DefaultStyledDocument privateMessageDocument;
    JTextField inputLine;
    /*  21 */
    public PrivateMessageFrame( ClientPlayer paramClientPlayer ) {
        this.player = paramClientPlayer;




        /*  26 */
        setTitle( this.player.getName() );

        /*  28 */
        setIconImage( java.awt.Toolkit.getDefaultToolkit()
                .getImage( lookup().lookupClass().getClassLoader().getResource( "graphics/jparanoiaIcon.jpg" ) ) );

        /*  30 */
        addItemToMenu();

        /*  32 */
        setDefaultCloseOperation( 1 );



        /*  36 */
        this.displayArea = new JTextPane();
        /*  37 */
        this.displayArea.setEditable( false );
        /*  38 */
        this.displayArea.setEnabled( false );


        /*  41 */
        if ( JPClient.currentColorScheme.equals( "White on Black" ) ) {
            /*  43 */
            this.displayArea.setDisabledTextColor( Color.white );
            /*  44 */
            this.displayArea.setBackground( Color.black );
        }
        /*  47 */
        else if ( JPClient.currentColorScheme.equals( "Black on White" ) ) {
            /*  49 */
            this.displayArea.setDisabledTextColor( Color.black );
            /*  50 */
            this.displayArea.setBackground( Color.white );
        } else {
            /*  53 */
            System.out.println( "PivateMessageFrame error: no recognized color scheme selected..." );
        }
        /*  55 */
        this.scrollPane = new javax.swing.JScrollPane( this.displayArea, 22, 31 );


        /*  58 */
        this.privateMessageDocument = new DefaultStyledDocument();

        /*  60 */
        this.inputLine = new JTextField( 20 );
        /*  61 */
        /*  75 */
        this.inputLine.addActionListener(paramAnonymousActionEvent -> {
            /*  63 */
            String str = PrivateMessageFrame.this.inputLine.getText();
            /*  64 */
            if ( !str.equals( "" ) ) {
                /*  65 */
                JPClient.sendPrivateMessage( PrivateMessageFrame.this.player.getID() +
                        JPClient.myPlayer.getID() +
                        str );
                /*  66 */
                PrivateMessageFrame.this.addMyMessage( str );
            }
            /*  68 */
            PrivateMessageFrame.this.inputLine.setText( "" );
        });
        /*  76 */
        this.inputLine.setEnabled( this.player.isLoggedIn() );

        /*  78 */
        Container localContainer = getContentPane();

        /*  80 */
        localContainer.add( this.scrollPane, "Center" );
        /*  81 */
        localContainer.add( this.inputLine, "South" );

        /*  83 */
        pack();

        /*  85 */
        setSize( this.IDEAL_DIMENSION );

        /*  87 */
        this.player.setPMFrame( this );

        /*  89 */
        setLocation( new java.awt.Point( JPClient.rand.nextInt( 500 ), JPClient.rand.nextInt( 400 ) ) );
    }

    public void addMyMessage( String paramString ) {
        /* 100 */
        if ( paramString.trim().equals( "" ) ) {
            return;
        }
        try {
            /* 104 */
            JPClient.textAttributes.addAttribute( javax.swing.text.StyleConstants.CharacterConstants.Foreground, JPClient.myPlayer
                    .getChatColor() );

            /* 106 */
            this.privateMessageDocument.insertString( this.privateMessageDocument.getLength(), "  " +
                    JPClient.myPlayer.getName() +
                    ": ", JPClient.textAttributes );


            /* 109 */
            JPClient.textAttributes.addAttribute( javax.swing.text.StyleConstants.CharacterConstants.Foreground, JPClient.textColor );

            /* 111 */
            this.privateMessageDocument.insertString( this.privateMessageDocument.getLength(), paramString +
                    "\n", JPClient.textAttributes );


            /* 114 */
            this.displayArea.setDocument( this.privateMessageDocument );
            /* 115 */
            if ( JPClient.autoScroll ) {
                this.displayArea.setCaretPosition( this.privateMessageDocument.getLength() );
            }
            /* 116 */
            if ( JPClient.keepLog ) {
                String str;
                /* 119 */
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
                } else
                    /* 120 */ {
                    str = "(" + JPClient.myPlayer.getName() + " -> " + this.player.toString() + "): " + paramString;
                }
                /* 121 */
                JPClient.logger.logEntry( str );
            }
        } catch ( BadLocationException localBadLocationException ) {
            /* 127 */
            System.err.println( "Unhandled exception. (Bad Location)" );
        }
    }

    void addItemToMenu() {
        /* 133 */
        this.pmWindowMenuItem = new JMenuItem( getTitle() );
        /* 134 */
        /* 140 */
        this.pmWindowMenuItem.addActionListener(paramAnonymousActionEvent -> {
            /* 137 */
            PrivateMessageFrame.this.setVisible( true );
            /* 138 */
            PrivateMessageFrame.this.toFront();
        });
        /* 141 */
        JPClient.pmWindowsMenu.add( this.pmWindowMenuItem );
    }

    public void enableInput() {
        /* 147 */
        this.inputLine.setEnabled( true );
    }

    public void disableInput() {
        /* 152 */
        this.inputLine.setEnabled( false );
    }

    public String toString() {
        /* 157 */
        String str = this.player.getName() + "'s private message frame";
        /* 158 */
        return str;
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\client\PrivateMessageFrame.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
