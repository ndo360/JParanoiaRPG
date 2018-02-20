package jparanoia.server;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CombatFrame extends JFrame {
    /*  18 */ CombatFrame thisCombatFrame = this;
    /*  20 */ String debugButtonString = "";
    /*  22 */ boolean allTurnsReceived = false;
    /*  23 */ int turnsToBeReturned = 0;
    /*  25 */ CombatListener myCombatListener = new CombatListener();
    CombatButton[] playerButtons;
    JPanel buttonPanel;
    JPanel turnPanel;
    JPanel skipAndPublishPanel;
    JPanel publicTurnLabelPanel;
    JPanel secretTurnLabelPanel;
    JTextArea proposedPublicTurn;
    JTextArea secretTurn;
    JLabel publicTurnLabel;
    JLabel secretTurnLabel;
    JButton publishButton;
    JButton skipButton;
    JScrollPane proposedPublicTurnScrollPane;
    JScrollPane secretTurnScrollPane;
    ServerPlayer currentPlayer;
    Container contentPane;
    Vector waitingPlayers;
    StringTokenizer st;

    public CombatFrame() {
        /*  46 */
        super( "Combat Manager" );

        /*  48 */
        if ( !JPServer.frame.isVisible() ) {
            dispose();
            return;
        }
        /*  50 */
        setDefaultCloseOperation( 0 );

        /*  52 */
        addWindowListener( new java.awt.event.WindowAdapter() {
            public void windowClosing( java.awt.event.WindowEvent paramAnonymousWindowEvent ) {
                /*  56 */
                switch ( javax.swing.JOptionPane.showConfirmDialog( CombatFrame.this.thisCombatFrame, "WARNING: Are you SURE you want to end combat?\n", "Abort combat...", 0, 2 ) ) {
                    case 0:
                        /*  60 */
                        CombatFrame.this.abortCombat();
                        break;
                }
            }
            /*  69 */
        } );
        /*  70 */
        setIconImage( java.awt.Toolkit.getDefaultToolkit()
                .getImage( getClass().getResource( "graphics/jparanoiaIcon.jpg" ) ) );

        /*  72 */
        setSize( 500, 260 );

        /*  74 */
        this.currentPlayer = null;

        /*  76 */
        this.playerButtons = new CombatButton[JPServer.troubleshooters.length];
        /*  77 */
        this.contentPane = getContentPane();
        /*  78 */
        this.buttonPanel = new JPanel();

        /*  80 */
        this.buttonPanel.setLayout( new javax.swing.BoxLayout( this.buttonPanel, 1 ) );
        /*  81 */
        this.buttonPanel.add( javax.swing.Box.createRigidArea( new Dimension( 0, 3 ) ) );

        /*  83 */
        this.waitingPlayers = new Vector();

        /*  85 */
        this.debugButtonString += "Constructing playerButtons...\n";
        /*  86 */
        this.debugButtonString = this.debugButtonString + "   JPServer.numberOfPCs == " + JPServer.numberOfPCs + "\n";

        /*  88 */
        for ( int i = 1; i < JPServer.numberOfPCs; i++ ) {
            /*  90 */
            if ( JPServer.players[i].isLoggedIn() ) {

                /*  93 */
                this.playerButtons[i - 1] = new CombatButton( JPServer.players[i] );
                /*  94 */
                this.buttonPanel.add( this.playerButtons[i - 1], "Center" );
                /*  95 */
                this.playerButtons[i - 1].addActionListener( this.myCombatListener );
                /*  96 */
                this.buttonPanel.add( javax.swing.Box.createRigidArea( new Dimension( 0, 3 ) ) );
                /*  97 */
                this.waitingPlayers.addElement( this.playerButtons[i - 1] );
                /*  98 */
                this.turnsToBeReturned += 1;
            }
        }

        /* 102 */
        this.debugButtonString = this.debugButtonString + "   " + turnStatus();

        /* 104 */
        this.buttonPanel.setMinimumSize( new Dimension( 120, 50 ) );
        /* 105 */
        this.buttonPanel.setPreferredSize( new Dimension( 120, 50 ) );
        /* 106 */
        this.buttonPanel.setMaximumSize( new Dimension( 120, 400 ) );

        /* 108 */
        this.turnPanel = new JPanel();


        /* 111 */
        GridBagLayout localGridBagLayout = new GridBagLayout();
        /* 112 */
        java.awt.GridBagConstraints localGridBagConstraints = new java.awt.GridBagConstraints();

        /* 114 */
        localGridBagConstraints.fill = 1;
        /* 115 */
        localGridBagConstraints.anchor = 17;
        /* 116 */
        localGridBagConstraints.insets = new java.awt.Insets( 2, 2, 2, 2 );



        /* 120 */
        this.turnPanel.setLayout( localGridBagLayout );

        /* 122 */
        this.publicTurnLabel = new JLabel( "Public Turn:       " );
        /* 123 */
        this.publicTurnLabelPanel = new JPanel();
        /* 124 */
        this.publicTurnLabelPanel.add( this.publicTurnLabel, "South" );

        /* 126 */
        localGridBagConstraints.gridwidth = 1;
        /* 127 */
        localGridBagConstraints.gridheight = 1;
        /* 128 */
        localGridBagConstraints.gridx = 0;
        /* 129 */
        localGridBagConstraints.gridy = 0;
        /* 130 */
        localGridBagConstraints.weightx = 0.0D;

        /* 132 */
        localGridBagLayout.setConstraints( this.publicTurnLabelPanel, localGridBagConstraints );

        /* 134 */
        this.proposedPublicTurn = new JTextArea();
        /* 135 */
        this.proposedPublicTurn.setLineWrap( true );
        /* 136 */
        this.proposedPublicTurnScrollPane = new JScrollPane( this.proposedPublicTurn, 22, 31 );



        /* 140 */
        localGridBagConstraints.gridwidth = 3;
        /* 141 */
        localGridBagConstraints.gridheight = 3;
        /* 142 */
        localGridBagConstraints.gridy = 1;
        /* 143 */
        localGridBagConstraints.weightx = 1.0D;
        /* 144 */
        localGridBagConstraints.weighty = 1.0D;

        /* 146 */
        localGridBagLayout.setConstraints( this.proposedPublicTurnScrollPane, localGridBagConstraints );

        /* 148 */
        this.secretTurnLabel = new JLabel( "Secret Turn:       " );
        /* 149 */
        this.secretTurnLabelPanel = new JPanel();
        /* 150 */
        this.secretTurnLabelPanel.add( this.secretTurnLabel, "South" );

        /* 152 */
        localGridBagConstraints.gridwidth = 1;
        /* 153 */
        localGridBagConstraints.gridheight = 1;
        /* 154 */
        localGridBagConstraints.gridx = 0;
        /* 155 */
        localGridBagConstraints.gridy = 4;
        /* 156 */
        localGridBagConstraints.weightx = 0.0D;
        /* 157 */
        localGridBagConstraints.weighty = 0.0D;

        /* 159 */
        localGridBagLayout.setConstraints( this.secretTurnLabelPanel, localGridBagConstraints );

        /* 161 */
        this.secretTurn = new JTextArea();
        /* 162 */
        this.secretTurn.setLineWrap( true );
        /* 163 */
        this.secretTurnScrollPane = new JScrollPane( this.secretTurn, 22, 31 );

        /* 165 */
        this.secretTurn.setEditable( false );

        /* 167 */
        localGridBagConstraints.gridwidth = 3;
        /* 168 */
        localGridBagConstraints.gridheight = 3;
        /* 169 */
        localGridBagConstraints.gridy = 5;
        /* 170 */
        localGridBagConstraints.weightx = 1.0D;
        /* 171 */
        localGridBagConstraints.weighty = 1.0D;

        /* 173 */
        localGridBagLayout.setConstraints( this.secretTurnScrollPane, localGridBagConstraints );

        /* 175 */
        this.publishButton = new JButton( "Publish" );
        /* 176 */
        this.publishButton.setEnabled( false );
        /* 177 */
        this.publishButton.addActionListener( new java.awt.event.ActionListener() {
            public void actionPerformed( ActionEvent paramAnonymousActionEvent ) {
                /* 179 */
                CombatFrame.this.publishTurn( CombatFrame.this.currentPlayer, CombatFrame.this.proposedPublicTurn.getText() );
                /* 180 */
                CombatFrame.this.proposedPublicTurn.setText( "Turn published. Select next player." );
                /* 181 */
                boolean bool = CombatFrame.this.currentPlayer.statusPanel.isNewMessageWaiting();
                /* 182 */
                String str = CombatFrame.this.secretTurn.getText();
                /* 183 */
                if ( !str.equals( "No secret turn submitted." ) )
                    /* 184 */ {
                    JPServer.privateMessageHandler( "00" + CombatFrame.this.currentPlayer.getID() + str, false );
                }
                /* 185 */
                if ( !bool ) {
                    CombatFrame.this.currentPlayer.statusPanel.statusNewMessage( false );
                }
                /* 186 */
                CombatFrame.this.secretTurn.setText( "" );
                /* 187 */
                for ( int i = 0; i < CombatFrame.this.waitingPlayers.size(); i++ ) {
                    /* 189 */
                    CombatButton localCombatButton = (CombatButton) CombatFrame.this.waitingPlayers.elementAt( i );
                    /* 190 */
                    localCombatButton.setEnabled( true );
                }

                /* 193 */
                CombatFrame.this.currentPlayer.statusPanel.freeze();
                /* 194 */
                CombatFrame.this.turnsToBeReturned -= 1;
                CombatFrame
                        /* 195 */           tmp220_217 = CombatFrame.this;
                tmp220_217.debugButtonString = tmp220_217.debugButtonString +
                        "   Turn published. " +
                        CombatFrame.this.turnStatus();
                /* 196 */
                if ( CombatFrame.this.waitingPlayers.size() == 0 && CombatFrame.this.turnsToBeReturned == 0 ) {
                    CombatFrame.this.endCombat();
                }
                /* 198 */
                CombatFrame.this.currentPlayer = null;
            }

            /* 201 */
        } );
        /* 202 */
        this.skipButton = new JButton( "Skip" );
        /* 203 */
        this.skipButton.setEnabled( false );
        /* 204 */
        this.skipButton.addActionListener( new java.awt.event.ActionListener() {
            public void actionPerformed( ActionEvent paramAnonymousActionEvent ) {
                /* 206 */
                CombatFrame.this.proposedPublicTurn.setText( "Player skipped. Select next player." );
                /* 207 */
                CombatFrame.this.secretTurn.setText( "" );
                /* 208 */
                CombatFrame.this.currentPlayer.specificSend( "609" );
                /* 209 */
                JPServer.spamString( "199" + CombatFrame.this.currentPlayer.getName() + "'s turn was skipped." );
                /* 210 */
                JPServer.absoluteChat( CombatFrame.this.currentPlayer.getName() + "'s turn was skipped." );
                /* 211 */
                for ( int i = 0; i < CombatFrame.this.waitingPlayers.size(); i++ ) {
                    /* 213 */
                    CombatButton localCombatButton = (CombatButton) CombatFrame.this.waitingPlayers.elementAt( i );
                    /* 214 */
                    localCombatButton.setEnabled( true );
                }

                /* 217 */
                CombatFrame.this.currentPlayer.statusPanel.freeze();

                /* 219 */
                CombatFrame.this.turnsToBeReturned -= 1;
                CombatFrame

                        /* 221 */           tmp182_179 = CombatFrame.this;
                tmp182_179.debugButtonString = tmp182_179.debugButtonString +
                        "   Turn skipped. " +
                        CombatFrame.this.turnStatus();

                /* 223 */
                CombatFrame.this.skipButton.setEnabled( false );
                /* 224 */
                CombatFrame.this.publishButton.setEnabled( false );

                /* 226 */
                if ( CombatFrame.this.waitingPlayers.size() == 0 && CombatFrame.this.turnsToBeReturned == 0 ) {
                    CombatFrame.this.endCombat();
                }
                /* 228 */
                CombatFrame.this.currentPlayer = null;
            }
            /* 230 */
        } );
        /* 231 */
        this.skipAndPublishPanel = new JPanel();
        /* 232 */
        this.skipAndPublishPanel.add( this.skipButton );
        /* 233 */
        this.skipAndPublishPanel.add( this.publishButton );

        /* 235 */
        localGridBagConstraints.gridwidth = 1;
        /* 236 */
        localGridBagConstraints.gridheight = 1;
        /* 237 */
        localGridBagConstraints.gridx = 2;
        /* 238 */
        localGridBagConstraints.gridy = 8;
        /* 239 */
        localGridBagConstraints.weightx = 0.0D;
        /* 240 */
        localGridBagConstraints.weighty = 0.0D;
        /* 241 */
        localGridBagConstraints.anchor = 13;
        /* 242 */
        localGridBagConstraints.fill = 0;

        /* 244 */
        localGridBagLayout.setConstraints( this.skipAndPublishPanel, localGridBagConstraints );





        /* 250 */
        this.turnPanel.add( this.publicTurnLabelPanel );
        /* 251 */
        this.turnPanel.add( this.proposedPublicTurnScrollPane );
        /* 252 */
        this.turnPanel.add( this.secretTurnLabelPanel );
        /* 253 */
        this.turnPanel.add( this.secretTurnScrollPane );
        /* 254 */
        this.turnPanel.add( this.skipAndPublishPanel );


        /* 257 */
        this.contentPane.setLayout( new javax.swing.BoxLayout( this.contentPane, 0 ) );
        /* 258 */
        this.contentPane.add( this.buttonPanel );
        /* 259 */
        this.contentPane.add( this.turnPanel );

        /* 261 */
        if ( this.waitingPlayers.size() == 0 ) {
            /* 263 */
            JPServer.absoluteChat( "No players connected. Combat not started." );
            /* 264 */
            endCombat();
        }
    }

    void publishTurn( ServerPlayer paramServerPlayer, String paramString ) {
        /* 287 */
        String str2 = paramString;
        /* 288 */
        String str3 = paramServerPlayer.getID();

        /* 290 */
        str2 = str2.replace( '\n', ' ' );
        String str1;
        /* 292 */
        if ( str2.startsWith( "/" ) ) {
            /* 294 */
            str1 = "110";
            /* 295 */
            JPServer.spamString( str1 + str3 + str2.substring( 1 ) );
            /* 296 */
            JPServer.actionChat( str3 + str2.substring( 1 ) );
        }
        /* 299 */
        else if ( str2.startsWith( "'" ) ) {
            /* 301 */
            str1 = "120";
            /* 302 */
            JPServer.spamString( str1 + str3 + str2.substring( 1 ) );
            /* 303 */
            JPServer.speechChat( str3 + str2.substring( 1 ) );
        }

        /* 306 */
        this.skipButton.setEnabled( false );
        /* 307 */
        this.publishButton.setEnabled( false );
    }

    void abortCombat() {
        /* 331 */
        JPServer.spamString( "609" );
        /* 332 */
        JPServer.spamString( "199(GM aborted combat)" );
        /* 333 */
        JPServer.absoluteChat( "(GM aborted combat)" );
        /* 334 */
        for ( int i = 0; i < JPServer.troubleshooters.length; i++ ) {
            /* 336 */
            if ( JPServer.troubleshooters[i].isLoggedIn() ) {
                JPServer.troubleshooters[i].statusPanel.freeze();
            }
        }
        /* 339 */
        System.out.println( this.debugButtonString );

        /* 341 */
        JPServer.errLog = new jparanoia.shared.ErrorLogger( "cmbt", this.debugButtonString );
        /* 342 */
        JPServer.errLog.closeLog();
        /* 343 */
        JPServer.errLog = null;
        /* 344 */
        JPServer.errorMessage( "Combat Aborted", "You have aborted a combat round. This should only be done\nwhen a combat round is somehow prevented from ending normally.\nAn error log has been created in your JParanoia directory.\nPlease notify the author by submitting the contents of\nthe error log in a bug report on the JParanoia website.\nhttp://www.byronbarry.com/jparanoia" );







        /* 352 */
        endCombat();
    }

    void endCombat() {
        /* 313 */
        JPServer.spamString( "199*** COMBAT ROUND COMPLETE ***" );
        /* 314 */
        JPServer.spamString( "597" );
        /* 315 */
        JPServer.absoluteChat( "*** COMBAT ROUND COMPLETE ***" );
        /* 316 */
        JPServer.absoluteChat( "(Remember to Unfreeze your players to resume normal play.)" );
        /* 317 */
        JPServer.freezeButton.setEnabled( true );
        /* 318 */
        JPServer.combatButton.setEnabled( true );
        /* 319 */
        if ( JPServer.combatMusicIsPlaying ) {
            JPServer.soundPlayer.stopCombatMusic();
        }
        /* 324 */
        this.currentPlayer = null;

        /* 326 */
        dispose();
    }

    String turnStatus() {
        /* 406 */
        return "turnsToBeReturned == " +
                this.turnsToBeReturned +
                " -- waitingPlayers.size() == " +
                this.waitingPlayers.size() +
                "\n";
    }

    void receiveCombatTurn( ServerPlayer paramServerPlayer, String paramString ) {
        /* 271 */
        if ( paramServerPlayer != this.currentPlayer ) {
            return;
        }
        /* 273 */
        String str1 = paramString;

        /* 275 */
        this.st = new StringTokenizer( str1, "~" );
        /* 276 */
        this.proposedPublicTurn.setText( this.st.nextToken() );
        /* 277 */
        String str2;
        if ( this.st.hasMoreTokens() ) {
            str2 = this.st.nextToken();
        } else
            /* 278 */ {
            str2 = "No secret turn submitted.";
        }
        /* 279 */
        this.secretTurn.setText( str2 );

        /* 281 */
        this.publishButton.setEnabled( true );
    }

    void removePlayer( ServerPlayer paramServerPlayer ) {
        int i;
        /* 358 */
        if ( paramServerPlayer != this.currentPlayer ) {
            /* 360 */
            for ( i = 0; i < this.playerButtons.length; i++ ) {
                /* 362 */
                if ( this.playerButtons[i] != null ) {
                    /* 364 */
                    if ( this.playerButtons[i].player == paramServerPlayer ) {
                        /* 366 */
                        this.buttonPanel.remove( this.playerButtons[i] );

                        /* 368 */
                        this.waitingPlayers.removeElement( this.playerButtons[i] );
                        /* 369 */
                        this.buttonPanel.repaint();

                        /* 371 */
                        this.turnsToBeReturned -= 1;
                        /* 372 */
                        paramServerPlayer.statusPanel.freeze();
                        /* 373 */
                        if ( this.waitingPlayers.size() == 0 && this.turnsToBeReturned == 0 ) {
                            endCombat();
                        }
                        /* 374 */
                        return;
                    }
                }
            }
        }

        /* 380 */
        if ( paramServerPlayer == this.currentPlayer ) {
            /* 382 */
            this.proposedPublicTurn.setText( "Player aborted turn. Select next player." );
            /* 383 */
            this.secretTurn.setText( "" );

            /* 385 */
            for ( i = 0; i < this.waitingPlayers.size(); i++ ) {
                /* 387 */
                CombatButton localCombatButton = (CombatButton) this.waitingPlayers.elementAt( i );
                /* 388 */
                localCombatButton.setEnabled( true );
            }

            /* 391 */
            this.currentPlayer.statusPanel.freeze();

            /* 393 */
            this.turnsToBeReturned -= 1;
            /* 394 */
            this.skipButton.setEnabled( false );
            /* 395 */
            if ( this.waitingPlayers.size() == 0 && this.turnsToBeReturned == 0 ) {
                endCombat();
            }
            /* 397 */
            this.currentPlayer = null;
        }

        /* 400 */
        this.debugButtonString = this.debugButtonString + "removePlayer() called. " + turnStatus();
    }

    public String getPlayerButtonDebugString() {
        /* 411 */
        return this.debugButtonString;
    }

    class CombatListener implements java.awt.event.ActionListener {
        Object source;

        CombatListener() {
        }

        public void actionPerformed( ActionEvent paramActionEvent ) {
            /* 421 */
            this.source = paramActionEvent.getSource();
            /* 422 */
            CombatButton localCombatButton1 = (CombatButton) this.source;
            /* 423 */
            CombatFrame.this.currentPlayer = localCombatButton1.player;
            /* 424 */
            CombatFrame.this.currentPlayer.statusPanel.combat();

            /* 426 */
            CombatFrame.this.buttonPanel.remove( localCombatButton1 );
            /* 427 */
            if ( CombatFrame.this.waitingPlayers.size() == 1 ) {
                CombatFrame.this.buttonPanel.add( new JLabel( " " ) );
            }
            /* 428 */
            CombatFrame.this.buttonPanel.repaint();
            /* 429 */
            CombatFrame.this.currentPlayer.promptPlayerForCombatTurn();





            /* 435 */
            CombatFrame.this.publicTurnLabel.setText( CombatFrame.this.currentPlayer.getName() + "'s public turn:" );
            /* 436 */
            CombatFrame.this.publicTurnLabel.repaint();
            /* 437 */
            CombatFrame.this.secretTurnLabel.setText( CombatFrame.this.currentPlayer.getName() + "'s secret turn:" );
            /* 438 */
            CombatFrame.this.secretTurnLabel.repaint();

            /* 440 */
            CombatFrame.this.proposedPublicTurn.setText( "Waiting for " +
                    CombatFrame.this.currentPlayer.getName() +
                    " to submit their turn..." );
            /* 441 */
            CombatFrame.this.secretTurn.setText( "" );

            /* 443 */
            CombatFrame.this.skipButton.setEnabled( true );

            /* 445 */
            CombatFrame.this.waitingPlayers.removeElement( localCombatButton1 );
            CombatFrame

                    /* 447 */         tmp290_287 = CombatFrame.this;
            tmp290_287.debugButtonString = tmp290_287.debugButtonString +
                    "   playerButton clicked. " +
                    CombatFrame.this.turnStatus();

            /* 449 */
            for ( int i = 0; i < CombatFrame.this.waitingPlayers.size(); i++ ) {
                /* 451 */
                CombatButton localCombatButton2 = (CombatButton) CombatFrame.this.waitingPlayers.elementAt( i );
                /* 452 */
                localCombatButton2.setEnabled( false );
            }
        }
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\CombatFrame.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
