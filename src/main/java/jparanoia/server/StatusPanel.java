package jparanoia.server;
import static java.lang.invoke.MethodHandles.lookup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.Timer;

public class StatusPanel extends javax.swing.JPanel {
    /*   9 */ int timerInterval = 500;
    /*  11 */ TimerListener myTimerListener = new TimerListener();
    /*  13 */ Timer newMessageAnimationTimer = new Timer( this.timerInterval, this.myTimerListener );
    ServerPlayer player;
    JButton statusButton;
    JButton newMessageLabel;
    /*  20 */ ImageIcon notConnectedIcon = new ImageIcon( lookup().lookupClass().getClassLoader().getResource( "graphics/notConnectedIcon.jpg" ) );
    /*  21 */ ImageIcon connectedIcon = new ImageIcon( lookup().lookupClass().getClassLoader().getResource( "graphics/connectedIcon.jpg" ) );
    /*  22 */ ImageIcon mutedIcon = new ImageIcon( lookup().lookupClass().getClassLoader().getResource( "graphics/mutedIcon.jpg" ) );
    /*  23 */ ImageIcon frozenIcon = new ImageIcon( lookup().lookupClass().getClassLoader().getResource( "graphics/frozenIcon.jpg" ) );
    /*  24 */ ImageIcon combatIcon = new ImageIcon( lookup().lookupClass().getClassLoader().getResource( "graphics/combatIcon.jpg" ) );
    /*  26 */ ImageIcon nullMessageIcon = new ImageIcon( lookup().lookupClass().getClassLoader().getResource( "graphics/nullMessageIcon.jpg" ) );
    /*  27 */ ImageIcon newMessageIcon = new ImageIcon( lookup().lookupClass().getClassLoader().getResource( "graphics/newMessageIcon.jpg" ) );
    boolean freshTimer;

    public StatusPanel( ServerPlayer paramServerPlayer ) {
        /*  35 */
        this.player = paramServerPlayer;

        /*  37 */
        this.player.setStatusPanel( this );

        /*  39 */
        this.newMessageAnimationTimer.setInitialDelay( this.timerInterval );

        /*  41 */
        this.statusButton = new JButton( this.notConnectedIcon );
        /*  42 */
        this.statusButton.setEnabled( false );

        /*  44 */
        this.statusButton.setDisabledIcon( this.notConnectedIcon );
        /*  45 */
        this.statusButton.setDisabledSelectedIcon( this.notConnectedIcon );

        /*  47 */
        this.statusButton.setIcon( this.connectedIcon );
        /*  48 */
        this.statusButton.setPreferredSize( new java.awt.Dimension( 31, 30 ) );
        /*  49 */
        this.statusButton.setMinimumSize( new java.awt.Dimension( 31, 30 ) );
        /*  50 */
        this.statusButton.setSelected( false );

        /*  52 */
        this.statusButton.addActionListener( new java.awt.event.ActionListener() {
            public void actionPerformed( java.awt.event.ActionEvent paramAnonymousActionEvent ) {
                /*  54 */
                if ( StatusPanel.this.player.isMuted() ) {
                    /*  55 */
                    JPServer.unmute( StatusPanel.this.player.getID() );
                    /*  56 */
                    StatusPanel.this.player.setMuted( false );
                    /*  57 */
                    if ( !JPServer.frozen ) {
                        StatusPanel.this.statusButton.setIcon( StatusPanel.this.connectedIcon );
                    } else {
                        /*  58 */
                        StatusPanel.this.statusButton.setIcon( StatusPanel.this.frozenIcon );
                    }
                } else {
                    /*  61 */
                    JPServer.mute( StatusPanel.this.player.getID() );
                    /*  62 */
                    StatusPanel.this.player.setMuted( true );
                    /*  63 */
                    if ( !JPServer.frozen ) {
                        StatusPanel.this.statusButton.setIcon( StatusPanel.this.mutedIcon );
                    }
                }
                /*  65 */
                JPServer.inputLine.requestFocus();
            }

            /*  68 */
        } );
        /*  69 */
        this.newMessageLabel = new JButton( this.nullMessageIcon );
        /*  70 */
        this.newMessageLabel.setEnabled( true );
        /*  71 */
        this.newMessageLabel.setBorderPainted( false );
        /*  72 */
        this.newMessageLabel.setPreferredSize( new java.awt.Dimension( 31, 30 ) );
        /*  73 */
        this.newMessageLabel.setMinimumSize( new java.awt.Dimension( 31, 30 ) );
        /*  74 */
        this.newMessageLabel.addActionListener( new java.awt.event.ActionListener() {
            public void actionPerformed( java.awt.event.ActionEvent paramAnonymousActionEvent ) {
                /*  76 */
                StatusPanel.this.newMessageAnimationTimer.stop();
                /*  77 */
                StatusPanel.this.newMessageLabel.setIcon( StatusPanel.this.nullMessageIcon );
                /*  78 */
                JPServer.inputLine.requestFocus();
            }

            /*  81 */
        } );
        /*  82 */
        setLayout( new javax.swing.BoxLayout( this, 1 ) );

        /*  84 */
        add( this.statusButton );
        /*  85 */
        add( javax.swing.Box.createRigidArea( new java.awt.Dimension( 0, 2 ) ) );
        /*  86 */
        add( this.newMessageLabel );
    }

    public void freeze() {
        /*  91 */
        this.statusButton.setIcon( this.frozenIcon );
    }

    public void unfreeze() {
        /*  96 */
        if ( this.player.isMuted() ) {
            this.statusButton.setIcon( this.mutedIcon );
        } else {
            /*  97 */
            this.statusButton.setIcon( this.connectedIcon );
        }
    }

    public void combat() {
        /* 102 */
        this.statusButton.setIcon( this.combatIcon );
    }

    public void statusLoggedIn( boolean paramBoolean ) {
        /* 108 */
        this.statusButton.setEnabled( paramBoolean );
    }

    public boolean isNewMessageWaiting() {
        /* 116 */
        return this.newMessageAnimationTimer.isRunning();
    }

    public void statusNewMessage( boolean paramBoolean ) {
        /* 121 */
        if ( paramBoolean && !this.newMessageAnimationTimer.isRunning() ) {
            /* 123 */
            this.freshTimer = true;
            /* 124 */
            this.newMessageAnimationTimer.start();
        }
        /* 127 */
        else if ( !paramBoolean && this.newMessageAnimationTimer.isRunning() ) {
            /* 129 */
            this.newMessageAnimationTimer.stop();

            /* 131 */
            this.newMessageLabel.setIcon( this.nullMessageIcon );
        }
    }

    class TimerListener implements java.awt.event.ActionListener {
        /* 140 */ int frameNumber = 0;

        TimerListener() {
        }

        /* 144 */
        public void actionPerformed( java.awt.event.ActionEvent paramActionEvent ) {
            if ( StatusPanel.this.freshTimer ) {
                this.frameNumber = 0;
                StatusPanel.this.freshTimer = false;
            }
            /* 146 */
            switch ( this.frameNumber ) {
                case 0:
                    /* 148 */
                    this.frameNumber += 1;
                    StatusPanel.this.newMessageLabel.setIcon( StatusPanel.this.newMessageIcon );
                    break;
                /* 149 */
                case 1:
                    this.frameNumber += 1;
                    break;
                /* 150 */
                case 2:
                    this.frameNumber += 1;
                    break;
                /* 151 */
                case 3:
                    this.frameNumber += 1;
                    break;
                /* 152 */
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
