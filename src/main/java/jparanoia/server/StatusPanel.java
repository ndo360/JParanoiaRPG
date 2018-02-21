package jparanoia.server;
import static java.lang.invoke.MethodHandles.lookup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.Timer;

public class StatusPanel extends javax.swing.JPanel {
     int timerInterval = 500;
     TimerListener myTimerListener = new TimerListener();
     Timer newMessageAnimationTimer = new Timer( this.timerInterval, this.myTimerListener );
    ServerPlayer player;
    JButton statusButton;
    JButton newMessageLabel;
     ImageIcon notConnectedIcon = new ImageIcon( lookup().lookupClass().getClassLoader().getResource( "graphics/notConnectedIcon.jpg" ) );
     ImageIcon connectedIcon = new ImageIcon( lookup().lookupClass().getClassLoader().getResource( "graphics/connectedIcon.jpg" ) );
     ImageIcon mutedIcon = new ImageIcon( lookup().lookupClass().getClassLoader().getResource( "graphics/mutedIcon.jpg" ) );
     ImageIcon frozenIcon = new ImageIcon( lookup().lookupClass().getClassLoader().getResource( "graphics/frozenIcon.jpg" ) );
     ImageIcon combatIcon = new ImageIcon( lookup().lookupClass().getClassLoader().getResource( "graphics/combatIcon.jpg" ) );
     ImageIcon nullMessageIcon = new ImageIcon( lookup().lookupClass().getClassLoader().getResource( "graphics/nullMessageIcon.jpg" ) );
     ImageIcon newMessageIcon = new ImageIcon( lookup().lookupClass().getClassLoader().getResource( "graphics/newMessageIcon.jpg" ) );
    boolean freshTimer;

    public StatusPanel( ServerPlayer paramServerPlayer ) {

        this.player = paramServerPlayer;


        this.player.setStatusPanel( this );


        this.newMessageAnimationTimer.setInitialDelay( this.timerInterval );


        this.statusButton = new JButton( this.notConnectedIcon );

        this.statusButton.setEnabled( false );


        this.statusButton.setDisabledIcon( this.notConnectedIcon );

        this.statusButton.setDisabledSelectedIcon( this.notConnectedIcon );


        this.statusButton.setIcon( this.connectedIcon );

        this.statusButton.setPreferredSize( new java.awt.Dimension( 31, 30 ) );

        this.statusButton.setMinimumSize( new java.awt.Dimension( 31, 30 ) );

        this.statusButton.setSelected( false );



        this.statusButton.addActionListener(paramAnonymousActionEvent -> {

            if ( StatusPanel.this.player.isMuted() ) {

                JPServer.unmute( StatusPanel.this.player.getID() );

                StatusPanel.this.player.setMuted( false );

                if ( !JPServer.frozen ) {
                    StatusPanel.this.statusButton.setIcon( StatusPanel.this.connectedIcon );
                } else {

                    StatusPanel.this.statusButton.setIcon( StatusPanel.this.frozenIcon );
                }
            } else {

                JPServer.mute( StatusPanel.this.player.getID() );

                StatusPanel.this.player.setMuted( true );

                if ( !JPServer.frozen ) {
                    StatusPanel.this.statusButton.setIcon( StatusPanel.this.mutedIcon );
                }
            }

            JPServer.inputLine.requestFocus();
        });

        this.newMessageLabel = new JButton( this.nullMessageIcon );

        this.newMessageLabel.setEnabled( true );

        this.newMessageLabel.setBorderPainted( false );

        this.newMessageLabel.setPreferredSize( new java.awt.Dimension( 31, 30 ) );

        this.newMessageLabel.setMinimumSize( new java.awt.Dimension( 31, 30 ) );


        this.newMessageLabel.addActionListener(paramAnonymousActionEvent -> {

            StatusPanel.this.newMessageAnimationTimer.stop();

            StatusPanel.this.newMessageLabel.setIcon( StatusPanel.this.nullMessageIcon );

            JPServer.inputLine.requestFocus();
        });

        setLayout( new javax.swing.BoxLayout( this, 1 ) );


        add( this.statusButton );

        add( javax.swing.Box.createRigidArea( new java.awt.Dimension( 0, 2 ) ) );

        add( this.newMessageLabel );
    }

    public void freeze() {

        this.statusButton.setIcon( this.frozenIcon );
    }

    public void unfreeze() {

        if ( this.player.isMuted() ) {
            this.statusButton.setIcon( this.mutedIcon );
        } else {

            this.statusButton.setIcon( this.connectedIcon );
        }
    }

    public void combat() {

        this.statusButton.setIcon( this.combatIcon );
    }

    public void statusLoggedIn( boolean paramBoolean ) {

        this.statusButton.setEnabled( paramBoolean );
    }

    public boolean isNewMessageWaiting() {

        return this.newMessageAnimationTimer.isRunning();
    }

    public void statusNewMessage( boolean paramBoolean ) {

        if ( paramBoolean && !this.newMessageAnimationTimer.isRunning() ) {

            this.freshTimer = true;

            this.newMessageAnimationTimer.start();
        }

        else if ( !paramBoolean && this.newMessageAnimationTimer.isRunning() ) {

            this.newMessageAnimationTimer.stop();


            this.newMessageLabel.setIcon( this.nullMessageIcon );
        }
    }

    class TimerListener implements java.awt.event.ActionListener {
         int frameNumber = 0;

        TimerListener() {
        }


        public void actionPerformed( java.awt.event.ActionEvent paramActionEvent ) {
            if ( StatusPanel.this.freshTimer ) {
                this.frameNumber = 0;
                StatusPanel.this.freshTimer = false;
            }

            switch ( this.frameNumber ) {
                case 0:

                    this.frameNumber += 1;
                    StatusPanel.this.newMessageLabel.setIcon( StatusPanel.this.newMessageIcon );
                    break;

                case 1:
                    this.frameNumber += 1;
                    break;

                case 2:
                    this.frameNumber += 1;
                    break;

                case 3:
                    this.frameNumber += 1;
                    break;

                case 4:
                    this.frameNumber = 0;
                    StatusPanel.this.newMessageLabel.setIcon( StatusPanel.this.nullMessageIcon );
            }
        }
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\StatusPanel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
