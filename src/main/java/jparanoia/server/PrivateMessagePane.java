package jparanoia.server;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;

public class PrivateMessagePane extends javax.swing.JPanel {
    /*  20 */   static SimpleAttributeSet pmAttributes = new SimpleAttributeSet();
    final ServerPlayer player;
    /*  14 */   final Dimension IDEAL_DIMENSION = new Dimension( 250, 175 );
    JTextPane displayArea;
    javax.swing.JScrollPane scrollPane;
    DefaultStyledDocument privateMessageDocument;
    JTextField inputLine;
    StatusPanel statusPanel;

    public PrivateMessagePane( ServerPlayer paramServerPlayer ) {
        /*  26 */
        this.player = paramServerPlayer;

        /*  28 */
        pmAttributes.addAttribute( javax.swing.text.StyleConstants.FontConstants.Family, "SansSerif" );
        /*  29 */
        pmAttributes.addAttribute( javax.swing.text.StyleConstants.CharacterConstants.Foreground, java.awt.Color.white );
        /*  30 */
        pmAttributes.addAttribute( javax.swing.text.StyleConstants.CharacterConstants.Size, new Integer( 10 ) );

        /*  32 */
        this.displayArea = new JTextPane();
        /*  33 */
        this.displayArea.setEditable( false );
        /*  34 */
        this.displayArea.setEnabled( true );
        /*  35 */
        this.displayArea.addFocusListener( new java.awt.event.FocusAdapter() {
            public void focusGained( java.awt.event.FocusEvent paramAnonymousFocusEvent ) {
                /*  37 */
                if ( !JPServer.optionsMenu.quickCharsheetMenuItem.isSelected() ||
                        JPServer.charsheetPanel.playerComboBox.getSelectedItem() == PrivateMessagePane.this.player ) {

                    /*  40 */
                    if ( !JPServer.jvm140 || JPServer.quickNamesToggle ) {



                        /*  45 */
                        String str = JPServer.inputLine.getText();
                        /*  46 */
                        int i = JPServer.inputLine.getSelectionStart();
                        /*  47 */
                        if ( str.equals( "" ) || i == 0 || Character.isWhitespace( str.charAt( i - 1 ) ) )
                            /*  48 */ {
                            JPServer.inputLine.replaceSelection( PrivateMessagePane.this.player.getName() );
                        } else {
                            /*  49 */
                            JPServer.inputLine.replaceSelection( " " + PrivateMessagePane.this.player.getName() );
                        }
                        /*  51 */
                        str = JPServer.inputLine.getText();
                        /*  52 */
                        i = JPServer.inputLine.getCaretPosition();
                        /*  53 */
                        if ( i < str.length() &&
                                /*  54 */               !Character.isWhitespace( str.charAt( i ) ) ) {
                            JPServer.inputLine.replaceSelection( " " );
                        }
                        /*  56 */
                        JPServer.quickNamesToggle = false;
                    } else {

                        /*  62 */
                        JPServer.quickNamesToggle = true;
                    }
                } else {
                    /*  69 */
                    JPServer.charsheetPanel.playerComboBox.setSelectedItem( PrivateMessagePane.this.player );
                    /*  70 */
                    JPServer.quickNamesToggle = false;
                }


                /*  74 */
                JPServer.inputLine.requestFocus();
            }
        } );
        /*  77 */
        if ( JPServer.currentColorScheme.equals( "White on Black" ) ) {
            /*  79 */
            this.displayArea.setDisabledTextColor( java.awt.Color.white );
            /*  80 */
            this.displayArea.setBackground( java.awt.Color.black );
        }
        /*  83 */
        else if ( JPServer.currentColorScheme.equals( "Black on White" ) ) {
            /*  85 */
            this.displayArea.setDisabledTextColor( java.awt.Color.black );
            /*  86 */
            this.displayArea.setBackground( java.awt.Color.white );
        } else {
            /*  89 */
            System.out.println( "PivateMessagePane error: no recognized color scheme selected..." );
        }
        /*  91 */
        this.scrollPane = new javax.swing.JScrollPane( this.displayArea, 22, 31 );


        /*  94 */
        this.privateMessageDocument = new DefaultStyledDocument();

        /*  96 */
        this.inputLine = new JTextField( 20 );
        /*  97 */
        this.inputLine.setEnabled( false );
        /*  98 */
        /* 112 */
        this.inputLine.addActionListener(paramAnonymousActionEvent -> {
            /* 100 */
            String str = PrivateMessagePane.this.inputLine.getText();
            /* 101 */
            if ( !str.equals( "" ) ) {
                /* 103 */
                PrivateMessagePane.this.player.specificSend( "200" +
                        PrivateMessagePane.this.player.getID() +
                        JPServer.myPlayer.getID() +
                        str );
                /* 104 */
                PrivateMessagePane.this.addMyMessage( str );
                /* 105 */
                PrivateMessagePane.this.inputLine.setText( "" );
            }
            /* 107 */
            JPServer.inputLine.requestFocus();
            /* 108 */
            JPServer.spamString( "211" );
        });
        /* 113 */
        this.inputLine.addFocusListener( new java.awt.event.FocusAdapter() {
            public void focusGained( java.awt.event.FocusEvent paramAnonymousFocusEvent ) {
                /* 115 */
                JPServer.sendingPrivateMessage( PrivateMessagePane.this.player );
                /* 116 */
                PrivateMessagePane.this.player.statusPanel.statusNewMessage( false );
                /* 117 */
                JPServer.setSendMainText( false );
            }

            public void focusLost( java.awt.event.FocusEvent paramAnonymousFocusEvent ) {
                /* 122 */
                JPServer.spamString( "211" );
            }

            /* 125 */
        } );
        /* 126 */
        this.statusPanel = new StatusPanel( this.player );

        /* 128 */
        setLayout( new javax.swing.BoxLayout( this, 1 ) );

        /* 130 */
        this.displayArea.setMinimumSize( new Dimension( 200, 60 ) );
        /* 131 */
        this.displayArea.setPreferredSize( new Dimension( 200, 60 ) );
        /* 132 */
        this.displayArea.setMaximumSize( new Dimension( 200, 60 ) );

        /* 134 */
        this.scrollPane.setMinimumSize( new Dimension( 240, 60 ) );
        /* 135 */
        this.scrollPane.setPreferredSize( new Dimension( 240, 60 ) );

        /* 137 */
        this.inputLine.setMaximumSize( new Dimension( 240, 10 ) );
        /* 138 */
        this.inputLine.setPreferredSize( new Dimension( 240, 19 ) );







        /* 146 */
        add( this.scrollPane );
        /* 147 */
        add( this.inputLine );
        try {
            /* 151 */
            this.privateMessageDocument.insertString( this.privateMessageDocument.getLength(), this.player.getName() +
                    "\n", pmAttributes );
        } catch ( BadLocationException localBadLocationException ) {
            /* 155 */
            System.out.println( "bad location exception" );
        }
        /* 157 */
        this.displayArea.setDocument( this.privateMessageDocument );

        /* 159 */
        this.player.setPMPane( this );

        /* 161 */
        PMAndStatusPanel localPMAndStatusPanel = new PMAndStatusPanel( this, this.statusPanel );
    }

    public void addMyMessage( String paramString ) {
        try {
            /* 169 */
            pmAttributes.addAttribute( javax.swing.text.StyleConstants.CharacterConstants.Foreground, JPServer.myPlayer.getChatColor() );

            /* 171 */
            this.privateMessageDocument.insertString( this.privateMessageDocument.getLength(), "  " +
                    JPServer.myPlayer.getName().substring( 0, 1 ) +
                    ": ", pmAttributes );


            /* 174 */
            pmAttributes.addAttribute( javax.swing.text.StyleConstants.CharacterConstants.Foreground, JPServer.textColor );

            /* 176 */
            this.privateMessageDocument.insertString( this.privateMessageDocument.getLength(), paramString +
                    "\n", pmAttributes );


            /* 179 */
            this.displayArea.setDocument( this.privateMessageDocument );
            /* 180 */
            if ( JPServer.autoScroll ) {
                this.displayArea.setCaretPosition( this.privateMessageDocument.getLength() );
            }
            /* 181 */
            if ( JPServer.keepLog ) {
                String str;
                /* 184 */
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
                } else
                    /* 185 */ {
                    str = "(" + JPServer.myPlayer.getName() + " -> " + this.player.toString() + "): " + paramString;
                }
                /* 186 */
                JPServer.logger.logEntry( str );
            }
        } catch ( BadLocationException localBadLocationException ) {
            /* 192 */
            System.err.println( "Unhandled exception. (Bad Location)" );
        }
    }

    public void reflectNameChange() {
        try {
            /* 200 */
            pmAttributes.addAttribute( javax.swing.text.StyleConstants.CharacterConstants.Foreground, JPServer.myPlayer.getChatColor() );

            /* 202 */
            this.privateMessageDocument.insertString( this.privateMessageDocument.getLength(), "\n\nNew Clone Family:\n", pmAttributes );


            /* 205 */
            pmAttributes.addAttribute( javax.swing.text.StyleConstants.CharacterConstants.Foreground, JPServer.textColor );

            /* 207 */
            this.privateMessageDocument.insertString( this.privateMessageDocument.getLength(), this.player.getName() +
                    "\n", pmAttributes );


            /* 210 */
            this.displayArea.setDocument( this.privateMessageDocument );
            /* 211 */
            if ( JPServer.autoScroll ) {
                this.displayArea.setCaretPosition( this.privateMessageDocument.getLength() );
            }
        } catch ( BadLocationException localBadLocationException ) {
            /* 216 */
            System.err.println( "Unhandled exception. (Bad Location)" );
        }
    }

    public void enableInput() {
        /* 222 */
        this.inputLine.setEnabled( true );
    }

    public void disableInput() {
        /* 227 */
        this.inputLine.setEnabled( false );
    }

    public String toString() {
        /* 233 */
        String str = this.player.getName() + "'s private message pane";
        /* 234 */
        return str;
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\PrivateMessagePane.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
