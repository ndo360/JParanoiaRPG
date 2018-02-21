package jparanoia.server;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import static java.lang.invoke.MethodHandles.lookup;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CombatFrame extends JFrame {
    CombatFrame thisCombatFrame = this;
    String debugButtonString = "";
    boolean allTurnsReceived = false;
    int turnsToBeReturned = 0;
    CombatListener myCombatListener = new CombatListener();
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
        super( "Combat Manager" );
        if ( !JPServer.frame.isVisible() ) {
            dispose();
            return;
        }
        setDefaultCloseOperation( 0 );
        addWindowListener( new java.awt.event.WindowAdapter() {
            public void windowClosing( java.awt.event.WindowEvent paramAnonymousWindowEvent ) {
                switch ( javax.swing.JOptionPane.showConfirmDialog( CombatFrame.this.thisCombatFrame, "WARNING: Are you SURE you want to end combat?\n", "Abort combat...", 0, 2 ) ) {
                    case 0:
                        CombatFrame.this.abortCombat();
                        break;
                }
            }
        } );
        setIconImage( java.awt.Toolkit.getDefaultToolkit()
                .getImage( lookup().lookupClass().getClassLoader().getResource( "graphics/jparanoiaIcon.jpg" ) ) );
        setSize( 500, 260 );
        this.currentPlayer = null;
        this.playerButtons = new CombatButton[JPServer.troubleshooters.length];
        this.contentPane = getContentPane();
        this.buttonPanel = new JPanel();
        this.buttonPanel.setLayout( new javax.swing.BoxLayout( this.buttonPanel, 1 ) );
        this.buttonPanel.add( javax.swing.Box.createRigidArea( new Dimension( 0, 3 ) ) );
        this.waitingPlayers = new Vector();
        this.debugButtonString += "Constructing playerButtons...\n";
        this.debugButtonString = this.debugButtonString + "   JPServer.numberOfPCs == " + JPServer.numberOfPCs + "\n";
        for ( int i = 1; i < JPServer.numberOfPCs; i++ ) {
            if ( JPServer.players[i].isLoggedIn() ) {
                this.playerButtons[i - 1] = new CombatButton( JPServer.players[i] );
                this.buttonPanel.add( this.playerButtons[i - 1], "Center" );
                this.playerButtons[i - 1].addActionListener( this.myCombatListener );
                this.buttonPanel.add( javax.swing.Box.createRigidArea( new Dimension( 0, 3 ) ) );
                this.waitingPlayers.addElement( this.playerButtons[i - 1] );
                this.turnsToBeReturned += 1;
            }
        }
        this.debugButtonString = this.debugButtonString + "   " + turnStatus();
        this.buttonPanel.setMinimumSize( new Dimension( 120, 50 ) );
        this.buttonPanel.setPreferredSize( new Dimension( 120, 50 ) );
        this.buttonPanel.setMaximumSize( new Dimension( 120, 400 ) );
        this.turnPanel = new JPanel();
        GridBagLayout localGridBagLayout = new GridBagLayout();
        java.awt.GridBagConstraints localGridBagConstraints = new java.awt.GridBagConstraints();
        localGridBagConstraints.fill = 1;
        localGridBagConstraints.anchor = 17;
        localGridBagConstraints.insets = new java.awt.Insets( 2, 2, 2, 2 );
        this.turnPanel.setLayout( localGridBagLayout );
        this.publicTurnLabel = new JLabel( "Public Turn:       " );
        this.publicTurnLabelPanel = new JPanel();
        this.publicTurnLabelPanel.add( this.publicTurnLabel, "South" );
        localGridBagConstraints.gridwidth = 1;
        localGridBagConstraints.gridheight = 1;
        localGridBagConstraints.gridx = 0;
        localGridBagConstraints.gridy = 0;
        localGridBagConstraints.weightx = 0.0D;
        localGridBagLayout.setConstraints( this.publicTurnLabelPanel, localGridBagConstraints );
        this.proposedPublicTurn = new JTextArea();
        this.proposedPublicTurn.setLineWrap( true );
        this.proposedPublicTurnScrollPane = new JScrollPane( this.proposedPublicTurn, 22, 31 );
        localGridBagConstraints.gridwidth = 3;
        localGridBagConstraints.gridheight = 3;
        localGridBagConstraints.gridy = 1;
        localGridBagConstraints.weightx = 1.0D;
        localGridBagConstraints.weighty = 1.0D;
        localGridBagLayout.setConstraints( this.proposedPublicTurnScrollPane, localGridBagConstraints );
        this.secretTurnLabel = new JLabel( "Secret Turn:       " );
        this.secretTurnLabelPanel = new JPanel();
        this.secretTurnLabelPanel.add( this.secretTurnLabel, "South" );
        localGridBagConstraints.gridwidth = 1;
        localGridBagConstraints.gridheight = 1;
        localGridBagConstraints.gridx = 0;
        localGridBagConstraints.gridy = 4;
        localGridBagConstraints.weightx = 0.0D;
        localGridBagConstraints.weighty = 0.0D;
        localGridBagLayout.setConstraints( this.secretTurnLabelPanel, localGridBagConstraints );
        this.secretTurn = new JTextArea();
        this.secretTurn.setLineWrap( true );
        this.secretTurnScrollPane = new JScrollPane( this.secretTurn, 22, 31 );
        this.secretTurn.setEditable( false );
        localGridBagConstraints.gridwidth = 3;
        localGridBagConstraints.gridheight = 3;
        localGridBagConstraints.gridy = 5;
        localGridBagConstraints.weightx = 1.0D;
        localGridBagConstraints.weighty = 1.0D;
        localGridBagLayout.setConstraints( this.secretTurnScrollPane, localGridBagConstraints );
        this.publishButton = new JButton( "Publish" );
        this.publishButton.setEnabled( false );
        this.publishButton.addActionListener( paramAnonymousActionEvent -> {
            CombatFrame.this.publishTurn( CombatFrame.this.currentPlayer, CombatFrame.this.proposedPublicTurn.getText() );
            CombatFrame.this.proposedPublicTurn.setText( "Turn published. Select next player." );
            boolean bool = CombatFrame.this.currentPlayer.statusPanel.isNewMessageWaiting();
            String str = CombatFrame.this.secretTurn.getText();
            if ( !str.equals( "No secret turn submitted." ) ) {
                JPServer.privateMessageHandler( "00" + CombatFrame.this.currentPlayer.getID() + str, false );
            }
            if ( !bool ) {
                CombatFrame.this.currentPlayer.statusPanel.statusNewMessage( false );
            }
            CombatFrame.this.secretTurn.setText( "" );
            for ( int i = 0; i < CombatFrame.this.waitingPlayers.size(); i++ ) {
                CombatButton localCombatButton = (CombatButton) CombatFrame.this.waitingPlayers.elementAt( i );
                localCombatButton.setEnabled( true );
            }
            CombatFrame.this.currentPlayer.statusPanel.freeze();
            CombatFrame.this.turnsToBeReturned -= 1;
            CombatFrame tmp220_217 = CombatFrame.this;
            tmp220_217.debugButtonString = tmp220_217.debugButtonString +
                    "   Turn published. " +
                    CombatFrame.this.turnStatus();
            if ( CombatFrame.this.waitingPlayers.size() == 0 && CombatFrame.this.turnsToBeReturned == 0 ) {
                CombatFrame.this.endCombat();
            }
            CombatFrame.this.currentPlayer = null;
        } );
        this.skipButton = new JButton( "Skip" );
        this.skipButton.setEnabled( false );
        this.skipButton.addActionListener( paramAnonymousActionEvent -> {
            CombatFrame.this.proposedPublicTurn.setText( "Player skipped. Select next player." );
            CombatFrame.this.secretTurn.setText( "" );
            CombatFrame.this.currentPlayer.specificSend( "609" );
            JPServer.spamString( "199" + CombatFrame.this.currentPlayer.getName() + "'s turn was skipped." );
            JPServer.absoluteChat( CombatFrame.this.currentPlayer.getName() + "'s turn was skipped." );
            for ( int i = 0; i < CombatFrame.this.waitingPlayers.size(); i++ ) {
                CombatButton localCombatButton = (CombatButton) CombatFrame.this.waitingPlayers.elementAt( i );
                localCombatButton.setEnabled( true );
            }
            CombatFrame.this.currentPlayer.statusPanel.freeze();
            CombatFrame.this.turnsToBeReturned -= 1;
            CombatFrame tmp182_179 = CombatFrame.this;
            tmp182_179.debugButtonString = tmp182_179.debugButtonString +
                    "   Turn skipped. " +
                    CombatFrame.this.turnStatus();
            CombatFrame.this.skipButton.setEnabled( false );
            CombatFrame.this.publishButton.setEnabled( false );
            if ( CombatFrame.this.waitingPlayers.size() == 0 && CombatFrame.this.turnsToBeReturned == 0 ) {
                CombatFrame.this.endCombat();
            }
            CombatFrame.this.currentPlayer = null;
        } );
        this.skipAndPublishPanel = new JPanel();
        this.skipAndPublishPanel.add( this.skipButton );
        this.skipAndPublishPanel.add( this.publishButton );
        localGridBagConstraints.gridwidth = 1;
        localGridBagConstraints.gridheight = 1;
        localGridBagConstraints.gridx = 2;
        localGridBagConstraints.gridy = 8;
        localGridBagConstraints.weightx = 0.0D;
        localGridBagConstraints.weighty = 0.0D;
        localGridBagConstraints.anchor = 13;
        localGridBagConstraints.fill = 0;
        localGridBagLayout.setConstraints( this.skipAndPublishPanel, localGridBagConstraints );
        this.turnPanel.add( this.publicTurnLabelPanel );
        this.turnPanel.add( this.proposedPublicTurnScrollPane );
        this.turnPanel.add( this.secretTurnLabelPanel );
        this.turnPanel.add( this.secretTurnScrollPane );
        this.turnPanel.add( this.skipAndPublishPanel );
        this.contentPane.setLayout( new javax.swing.BoxLayout( this.contentPane, 0 ) );
        this.contentPane.add( this.buttonPanel );
        this.contentPane.add( this.turnPanel );
        if ( this.waitingPlayers.size() == 0 ) {
            JPServer.absoluteChat( "No players connected. Combat not started." );
            endCombat();
        }
    }

    void publishTurn( ServerPlayer paramServerPlayer, String paramString ) {
        String str2 = paramString;
        String str3 = paramServerPlayer.getID();
        str2 = str2.replace( '\n', ' ' );
        String str1;
        if ( str2.startsWith( "/" ) ) {
            str1 = "110";
            JPServer.spamString( str1 + str3 + str2.substring( 1 ) );
            JPServer.actionChat( str3 + str2.substring( 1 ) );
        } else if ( str2.startsWith( "'" ) ) {
            str1 = "120";
            JPServer.spamString( str1 + str3 + str2.substring( 1 ) );
            JPServer.speechChat( str3 + str2.substring( 1 ) );
        }
        this.skipButton.setEnabled( false );
        this.publishButton.setEnabled( false );
    }

    void abortCombat() {
        JPServer.spamString( "609" );
        JPServer.spamString( "199(GM aborted combat)" );
        JPServer.absoluteChat( "(GM aborted combat)" );
        for ( int i = 0; i < JPServer.troubleshooters.length; i++ ) {
            if ( JPServer.troubleshooters[i].isLoggedIn() ) {
                JPServer.troubleshooters[i].statusPanel.freeze();
            }
        }
        System.out.println( this.debugButtonString );
        JPServer.errLog = new jparanoia.shared.ErrorLogger( "cmbt", this.debugButtonString );
        JPServer.errLog.closeLog();
        JPServer.errLog = null;
        JPServer.errorMessage( "Combat Aborted", "You have aborted a combat round. This should only be done\nwhen a combat round is somehow prevented from ending normally.\nAn error log has been created in your JParanoia directory.\nPlease notify the author by submitting the contents of\nthe error log in a bug report on the JParanoia website.\nhttp://www.byronbarry.com/jparanoia" );
        endCombat();
    }

    void endCombat() {
        JPServer.spamString( "199*** COMBAT ROUND COMPLETE ***" );
        JPServer.spamString( "597" );
        JPServer.absoluteChat( "*** COMBAT ROUND COMPLETE ***" );
        JPServer.absoluteChat( "(Remember to Unfreeze your players to resume normal play.)" );
        JPServer.freezeButton.setEnabled( true );
        JPServer.combatButton.setEnabled( true );
        if ( JPServer.combatMusicIsPlaying ) {
            JPServer.soundPlayer.stopCombatMusic();
        }
        this.currentPlayer = null;
        dispose();
    }

    String turnStatus() {
        return "turnsToBeReturned == " +
                this.turnsToBeReturned +
                " -- waitingPlayers.size() == " +
                this.waitingPlayers.size() +
                "\n";
    }

    void receiveCombatTurn( ServerPlayer paramServerPlayer, String paramString ) {
        if ( paramServerPlayer != this.currentPlayer ) {
            return;
        }
        String str1 = paramString;
        this.st = new StringTokenizer( str1, "~" );
        this.proposedPublicTurn.setText( this.st.nextToken() );
        String str2;
        if ( this.st.hasMoreTokens() ) {
            str2 = this.st.nextToken();
        } else {
            str2 = "No secret turn submitted.";
        }
        this.secretTurn.setText( str2 );
        this.publishButton.setEnabled( true );
    }

    void removePlayer( ServerPlayer paramServerPlayer ) {
        int i;
        if ( paramServerPlayer != this.currentPlayer ) {
            for ( i = 0; i < this.playerButtons.length; i++ ) {
                if ( this.playerButtons[i] != null ) {
                    if ( this.playerButtons[i].player == paramServerPlayer ) {
                        this.buttonPanel.remove( this.playerButtons[i] );
                        this.waitingPlayers.removeElement( this.playerButtons[i] );
                        this.buttonPanel.repaint();
                        this.turnsToBeReturned -= 1;
                        paramServerPlayer.statusPanel.freeze();
                        if ( this.waitingPlayers.size() == 0 && this.turnsToBeReturned == 0 ) {
                            endCombat();
                        }
                        return;
                    }
                }
            }
        }
        if ( paramServerPlayer == this.currentPlayer ) {
            this.proposedPublicTurn.setText( "Player aborted turn. Select next player." );
            this.secretTurn.setText( "" );
            for ( i = 0; i < this.waitingPlayers.size(); i++ ) {
                CombatButton localCombatButton = (CombatButton) this.waitingPlayers.elementAt( i );
                localCombatButton.setEnabled( true );
            }
            this.currentPlayer.statusPanel.freeze();
            this.turnsToBeReturned -= 1;
            this.skipButton.setEnabled( false );
            if ( this.waitingPlayers.size() == 0 && this.turnsToBeReturned == 0 ) {
                endCombat();
            }
            this.currentPlayer = null;
        }
        this.debugButtonString = this.debugButtonString + "removePlayer() called. " + turnStatus();
    }

    public String getPlayerButtonDebugString() {
        return this.debugButtonString;
    }

    class CombatListener implements java.awt.event.ActionListener {
        Object source;

        CombatListener() {
        }

        public void actionPerformed( ActionEvent paramActionEvent ) {
            this.source = paramActionEvent.getSource();
            CombatButton localCombatButton1 = (CombatButton) this.source;
            CombatFrame.this.currentPlayer = localCombatButton1.player;
            CombatFrame.this.currentPlayer.statusPanel.combat();
            CombatFrame.this.buttonPanel.remove( localCombatButton1 );
            if ( CombatFrame.this.waitingPlayers.size() == 1 ) {
                CombatFrame.this.buttonPanel.add( new JLabel( " " ) );
            }
            CombatFrame.this.buttonPanel.repaint();
            CombatFrame.this.currentPlayer.promptPlayerForCombatTurn();
            CombatFrame.this.publicTurnLabel.setText( CombatFrame.this.currentPlayer.getName() + "'s public turn:" );
            CombatFrame.this.publicTurnLabel.repaint();
            CombatFrame.this.secretTurnLabel.setText( CombatFrame.this.currentPlayer.getName() + "'s secret turn:" );
            CombatFrame.this.secretTurnLabel.repaint();
            CombatFrame.this.proposedPublicTurn.setText( "Waiting for " +
                    CombatFrame.this.currentPlayer.getName() +
                    " to submit their turn..." );
            CombatFrame.this.secretTurn.setText( "" );
            CombatFrame.this.skipButton.setEnabled( true );
            CombatFrame.this.waitingPlayers.removeElement( localCombatButton1 );
            CombatFrame tmp290_287 = CombatFrame.this;
            tmp290_287.debugButtonString = tmp290_287.debugButtonString +
                    "   playerButton clicked. " +
                    CombatFrame.this.turnStatus();
            for ( int i = 0; i < CombatFrame.this.waitingPlayers.size(); i++ ) {
                CombatButton localCombatButton2 = (CombatButton) CombatFrame.this.waitingPlayers.elementAt( i );
                localCombatButton2.setEnabled( false );
            }
        }
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\CombatFrame.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
