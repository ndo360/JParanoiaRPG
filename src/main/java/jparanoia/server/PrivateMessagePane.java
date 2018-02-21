package jparanoia.server;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;

public class PrivateMessagePane extends javax.swing.JPanel {
    static SimpleAttributeSet pmAttributes = new SimpleAttributeSet();
    final ServerPlayer player;
    final Dimension IDEAL_DIMENSION = new Dimension( 250, 175 );
    JTextPane displayArea;
    javax.swing.JScrollPane scrollPane;
    DefaultStyledDocument privateMessageDocument;
    JTextField inputLine;
    StatusPanel statusPanel;

    public PrivateMessagePane( ServerPlayer paramServerPlayer ) {
        this.player = paramServerPlayer;
        pmAttributes.addAttribute( javax.swing.text.StyleConstants.FontConstants.Family, "SansSerif" );
        pmAttributes.addAttribute( javax.swing.text.StyleConstants.CharacterConstants.Foreground, java.awt.Color.white );
        pmAttributes.addAttribute( javax.swing.text.StyleConstants.CharacterConstants.Size, new Integer( 10 ) );
        this.displayArea = new JTextPane();
        this.displayArea.setEditable( false );
        this.displayArea.setEnabled( true );
        this.displayArea.addFocusListener( new java.awt.event.FocusAdapter() {
            public void focusGained( java.awt.event.FocusEvent paramAnonymousFocusEvent ) {
                if ( !JPServer.optionsMenu.quickCharsheetMenuItem.isSelected() ||
                        JPServer.charsheetPanel.playerComboBox.getSelectedItem() == PrivateMessagePane.this.player ) {
                    if ( !JPServer.jvm140 || JPServer.quickNamesToggle ) {
                        String str = JPServer.inputLine.getText();
                        int i = JPServer.inputLine.getSelectionStart();
                        if ( str.equals( "" ) || i == 0 || Character.isWhitespace( str.charAt( i - 1 ) ) ) {
                            JPServer.inputLine.replaceSelection( PrivateMessagePane.this.player.getName() );
                        } else {
                            JPServer.inputLine.replaceSelection( " " + PrivateMessagePane.this.player.getName() );
                        }
                        str = JPServer.inputLine.getText();
                        i = JPServer.inputLine.getCaretPosition();
                        if ( i < str.length() && !Character.isWhitespace( str.charAt( i ) ) ) {
                            JPServer.inputLine.replaceSelection( " " );
                        }
                        JPServer.quickNamesToggle = false;
                    } else {
                        JPServer.quickNamesToggle = true;
                    }
                } else {
                    JPServer.charsheetPanel.playerComboBox.setSelectedItem( PrivateMessagePane.this.player );
                    JPServer.quickNamesToggle = false;
                }
                JPServer.inputLine.requestFocus();
            }
        } );
        if ( JPServer.currentColorScheme.equals( "White on Black" ) ) {
            this.displayArea.setDisabledTextColor( java.awt.Color.white );
            this.displayArea.setBackground( java.awt.Color.black );
        } else if ( JPServer.currentColorScheme.equals( "Black on White" ) ) {
            this.displayArea.setDisabledTextColor( java.awt.Color.black );
            this.displayArea.setBackground( java.awt.Color.white );
        } else {
            System.out.println( "PivateMessagePane error: no recognized color scheme selected..." );
        }
        this.scrollPane = new javax.swing.JScrollPane( this.displayArea, 22, 31 );
        this.privateMessageDocument = new DefaultStyledDocument();
        this.inputLine = new JTextField( 20 );
        this.inputLine.setEnabled( false );
        this.inputLine.addActionListener( paramAnonymousActionEvent -> {
            String str = PrivateMessagePane.this.inputLine.getText();
            if ( !str.equals( "" ) ) {
                PrivateMessagePane.this.player.specificSend( "200" +
                        PrivateMessagePane.this.player.getID() +
                        JPServer.myPlayer.getID() +
                        str );
                PrivateMessagePane.this.addMyMessage( str );
                PrivateMessagePane.this.inputLine.setText( "" );
            }
            JPServer.inputLine.requestFocus();
            JPServer.spamString( "211" );
        } );
        this.inputLine.addFocusListener( new java.awt.event.FocusAdapter() {
            public void focusGained( java.awt.event.FocusEvent paramAnonymousFocusEvent ) {
                JPServer.sendingPrivateMessage( PrivateMessagePane.this.player );
                PrivateMessagePane.this.player.statusPanel.statusNewMessage( false );
                JPServer.setSendMainText( false );
            }

            public void focusLost( java.awt.event.FocusEvent paramAnonymousFocusEvent ) {
                JPServer.spamString( "211" );
            }
        } );
        this.statusPanel = new StatusPanel( this.player );
        setLayout( new javax.swing.BoxLayout( this, 1 ) );
        this.displayArea.setMinimumSize( new Dimension( 200, 60 ) );
        this.displayArea.setPreferredSize( new Dimension( 200, 60 ) );
        this.displayArea.setMaximumSize( new Dimension( 200, 60 ) );
        this.scrollPane.setMinimumSize( new Dimension( 240, 60 ) );
        this.scrollPane.setPreferredSize( new Dimension( 240, 60 ) );
        this.inputLine.setMaximumSize( new Dimension( 240, 10 ) );
        this.inputLine.setPreferredSize( new Dimension( 240, 19 ) );
        add( this.scrollPane );
        add( this.inputLine );
        try {
            this.privateMessageDocument.insertString( this.privateMessageDocument.getLength(), this.player.getName() +
                    "\n", pmAttributes );
        } catch ( BadLocationException localBadLocationException ) {
            System.out.println( "bad location exception" );
        }
        this.displayArea.setDocument( this.privateMessageDocument );
        this.player.setPMPane( this );
        PMAndStatusPanel localPMAndStatusPanel = new PMAndStatusPanel( this, this.statusPanel );
    }

    public void addMyMessage( String paramString ) {
        try {
            pmAttributes.addAttribute( javax.swing.text.StyleConstants.CharacterConstants.Foreground, JPServer.myPlayer.getChatColor() );
            this.privateMessageDocument.insertString( this.privateMessageDocument.getLength(), "  " +
                    JPServer.myPlayer.getName().substring( 0, 1 ) +
                    ": ", pmAttributes );
            pmAttributes.addAttribute( javax.swing.text.StyleConstants.CharacterConstants.Foreground, JPServer.textColor );
            this.privateMessageDocument.insertString( this.privateMessageDocument.getLength(), paramString +
                    "\n", pmAttributes );
            this.displayArea.setDocument( this.privateMessageDocument );
            if ( JPServer.autoScroll ) {
                this.displayArea.setCaretPosition( this.privateMessageDocument.getLength() );
            }
            if ( JPServer.keepLog ) {
                String str;
                if ( JPServer.htmlLog ) {
                    str = "<span class=\"pm\"><span class=\"player0\">(" +
                            JPServer.myPlayer.getName() +
                            "</span> -> <span class=\"player" +
                            this.player.getPlayerNumber() +
                            "\">" +
                            this.player.toString() +
                            ")</span>: " +
                            paramString +
                            "</span><br/>";
                } else {
                    str = "(" + JPServer.myPlayer.getName() + " -> " + this.player.toString() + "): " + paramString;
                }
                JPServer.logger.logEntry( str );
            }
        } catch ( BadLocationException localBadLocationException ) {
            System.err.println( "Unhandled exception. (Bad Location)" );
        }
    }

    public void reflectNameChange() {
        try {
            pmAttributes.addAttribute( javax.swing.text.StyleConstants.CharacterConstants.Foreground, JPServer.myPlayer.getChatColor() );
            this.privateMessageDocument.insertString( this.privateMessageDocument.getLength(), "\n\nNew Clone Family:\n", pmAttributes );
            pmAttributes.addAttribute( javax.swing.text.StyleConstants.CharacterConstants.Foreground, JPServer.textColor );
            this.privateMessageDocument.insertString( this.privateMessageDocument.getLength(), this.player.getName() +
                    "\n", pmAttributes );
            this.displayArea.setDocument( this.privateMessageDocument );
            if ( JPServer.autoScroll ) {
                this.displayArea.setCaretPosition( this.privateMessageDocument.getLength() );
            }
        } catch ( BadLocationException localBadLocationException ) {
            System.err.println( "Unhandled exception. (Bad Location)" );
        }
    }

    public void enableInput() {
        this.inputLine.setEnabled( true );
    }

    public void disableInput() {
        this.inputLine.setEnabled( false );
    }

    public String toString() {
        String str = this.player.getName() + "'s private message pane";
        return str;
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\PrivateMessagePane.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
