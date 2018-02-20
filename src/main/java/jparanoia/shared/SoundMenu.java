package jparanoia.shared;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JMenu;

public class SoundMenu extends JMenu {
    public JCheckBoxMenuItem playSoundsMenuItem;
    public JCheckBoxMenuItem startupMenuItem;
    public JCheckBoxMenuItem joinLeaveMenuItem;
    public JCheckBoxMenuItem newTextMenuItem;
    public JCheckBoxMenuItem newObserverTextMenuItem;
    public JCheckBoxMenuItem mutedUnmutedMenuItem;
    public JCheckBoxMenuItem freezeUnfreezeMenuItem;
    public JCheckBoxMenuItem promotedDemotedMenuItem;
    public JCheckBoxMenuItem loginBadLoginMenuItem;
    public JCheckBoxMenuItem connectedDisconnectedMenuItem;
    public JCheckBoxMenuItem combatAlertMenuItem;
    public JCheckBoxMenuItem combatMusicMenuItem;
    public JCheckBoxMenuItem charSheetAlertMenuItem;
    public JCheckBoxMenuItem newPMAlertMenuItem;
    public JCheckBoxMenuItem deathAlertMenuItem;
    Prefs prefs;
    JParanoia caller;
    boolean selected;
    public SoundMenu( String paramString, Prefs paramPrefs, JParanoia paramJParanoia ) {
        /*  23 */
        super( paramString );

        /*  25 */
        this.caller = paramJParanoia;

        /*  27 */
        this.prefs = paramPrefs;

        /*  29 */
        this.playSoundsMenuItem = new JCheckBoxMenuItem( "Play Sounds" );
        /*  30 */
        this.playSoundsMenuItem.setSelected( ( (Boolean) this.prefs.getPref( 0 ) ).booleanValue() );
        /*  31 */
        /*  36 */
        this.playSoundsMenuItem.addActionListener(paramAnonymousActionEvent -> {
            /*  33 */
            JParanoia.toggleSoundEngine();
            /*  34 */
            SoundMenu.this.prefs.setPref( 0, new Boolean( SoundMenu.this.playSoundsMenuItem.isSelected() ) );
        });
        /*  37 */
        add( this.playSoundsMenuItem );

        /*  39 */
        addSeparator();


        /*  42 */
        add( new JLabel( "  Play sounds for:" ) );


        /*  45 */
        this.startupMenuItem = new JCheckBoxMenuItem( "Startup" );
        /*  46 */
        this.startupMenuItem.setSelected( ( (Boolean) this.prefs.getPref( 1 ) ).booleanValue() );
        /*  47 */
        /*  51 */
        this.startupMenuItem.addActionListener(paramAnonymousActionEvent -> {
            /*  49 */
            SoundMenu.this.prefs.setPref( 1, new Boolean( SoundMenu.this.startupMenuItem.isSelected() ) );
        });
        /*  52 */
        add( this.startupMenuItem );


        /*  55 */
        this.joinLeaveMenuItem = new JCheckBoxMenuItem( "Join / Leave" );
        /*  56 */
        this.joinLeaveMenuItem.setSelected( ( (Boolean) this.prefs.getPref( 2 ) ).booleanValue() );
        /*  57 */
        /*  61 */
        this.joinLeaveMenuItem.addActionListener(paramAnonymousActionEvent -> {
            /*  59 */
            SoundMenu.this.prefs.setPref( 2, new Boolean( SoundMenu.this.joinLeaveMenuItem.isSelected() ) );
        });
        /*  62 */
        add( this.joinLeaveMenuItem );

        /*  64 */
        this.newTextMenuItem = new JCheckBoxMenuItem( "New Text" );
        /*  65 */
        this.newTextMenuItem.setSelected( ( (Boolean) this.prefs.getPref( 3 ) ).booleanValue() );

        /*  67 */
        /*  71 */
        this.newTextMenuItem.addActionListener(paramAnonymousActionEvent -> {
            /*  69 */
            SoundMenu.this.prefs.setPref( 3, new Boolean( SoundMenu.this.newTextMenuItem.isSelected() ) );
        });
        /*  72 */
        add( this.newTextMenuItem );

        /*  74 */
        this.newObserverTextMenuItem = new JCheckBoxMenuItem( "New Observer Text" );
        /*  75 */
        this.newObserverTextMenuItem.setSelected( ( (Boolean) this.prefs.getPref( 4 ) ).booleanValue() );

        /*  77 */
        /*  81 */
        this.newObserverTextMenuItem.addActionListener(paramAnonymousActionEvent -> {
            /*  79 */
            SoundMenu.this.prefs.setPref( 4, new Boolean( SoundMenu.this.newObserverTextMenuItem.isSelected() ) );
        });
        /*  82 */
        add( this.newObserverTextMenuItem );

        /*  84 */
        this.mutedUnmutedMenuItem = new JCheckBoxMenuItem( "Muted / Unmuted" );
        /*  85 */
        this.mutedUnmutedMenuItem.setSelected( ( (Boolean) this.prefs.getPref( 5 ) ).booleanValue() );
        /*  86 */
        /*  90 */
        this.mutedUnmutedMenuItem.addActionListener(paramAnonymousActionEvent -> {
            /*  88 */
            SoundMenu.this.prefs.setPref( 5, new Boolean( SoundMenu.this.mutedUnmutedMenuItem.isSelected() ) );
        });
        /*  91 */
        add( this.mutedUnmutedMenuItem );


        /*  94 */
        this.freezeUnfreezeMenuItem = new JCheckBoxMenuItem( "Frozen / Unfrozen" );
        /*  95 */
        this.freezeUnfreezeMenuItem.setSelected( ( (Boolean) this.prefs.getPref( 6 ) ).booleanValue() );
        /*  96 */
        /* 100 */
        this.freezeUnfreezeMenuItem.addActionListener(paramAnonymousActionEvent -> {
            /*  98 */
            SoundMenu.this.prefs.setPref( 6, new Boolean( SoundMenu.this.freezeUnfreezeMenuItem.isSelected() ) );
        });
        /* 101 */
        add( this.freezeUnfreezeMenuItem );

        /* 103 */
        this.promotedDemotedMenuItem = new JCheckBoxMenuItem( "Promoted / Demoted" );
        /* 104 */
        this.promotedDemotedMenuItem.setSelected( ( (Boolean) this.prefs.getPref( 7 ) ).booleanValue() );
        /* 105 */
        /* 109 */
        this.promotedDemotedMenuItem.addActionListener(paramAnonymousActionEvent -> {
            /* 107 */
            SoundMenu.this.prefs.setPref( 7, new Boolean( SoundMenu.this.promotedDemotedMenuItem.isSelected() ) );
        });
        /* 110 */
        add( this.promotedDemotedMenuItem );


        /* 113 */
        this.loginBadLoginMenuItem = new JCheckBoxMenuItem( "Login Valid / Invalid" );
        /* 114 */
        this.loginBadLoginMenuItem.setSelected( ( (Boolean) this.prefs.getPref( 8 ) ).booleanValue() );
        /* 115 */
        /* 119 */
        this.loginBadLoginMenuItem.addActionListener(paramAnonymousActionEvent -> {
            /* 117 */
            SoundMenu.this.prefs.setPref( 8, new Boolean( SoundMenu.this.loginBadLoginMenuItem.isSelected() ) );
        });
        /* 120 */
        add( this.loginBadLoginMenuItem );

        /* 122 */
        this.connectedDisconnectedMenuItem = new JCheckBoxMenuItem( "Connect / Disconnect" );
        /* 123 */
        this.connectedDisconnectedMenuItem.setSelected( ( (Boolean) this.prefs.getPref( 9 ) ).booleanValue() );
        /* 124 */
        /* 128 */
        this.connectedDisconnectedMenuItem.addActionListener(paramAnonymousActionEvent -> {
            /* 126 */
            SoundMenu.this.prefs.setPref( 9, new Boolean( SoundMenu.this.connectedDisconnectedMenuItem.isSelected() ) );
        });
        /* 129 */
        add( this.connectedDisconnectedMenuItem );


        /* 132 */
        this.combatAlertMenuItem = new JCheckBoxMenuItem( "Combat Alert" );
        /* 133 */
        this.combatAlertMenuItem.setSelected( ( (Boolean) this.prefs.getPref( 10 ) ).booleanValue() );
        /* 134 */
        /* 138 */
        this.combatAlertMenuItem.addActionListener(paramAnonymousActionEvent -> {
            /* 136 */
            SoundMenu.this.prefs.setPref( 10, new Boolean( SoundMenu.this.combatAlertMenuItem.isSelected() ) );
        });
        /* 139 */
        add( this.combatAlertMenuItem );

        /* 141 */
        this.combatMusicMenuItem = new JCheckBoxMenuItem( "Combat Music" );
        /* 142 */
        this.combatMusicMenuItem.setSelected( ( (Boolean) this.prefs.getPref( 11 ) ).booleanValue() );
        /* 143 */
        /* 147 */
        this.combatMusicMenuItem.addActionListener(paramAnonymousActionEvent -> {
            /* 145 */
            SoundMenu.this.prefs.setPref( 11, new Boolean( SoundMenu.this.combatMusicMenuItem.isSelected() ) );
        });
        /* 148 */
        add( this.combatMusicMenuItem );


        /* 151 */
        this.charSheetAlertMenuItem = new JCheckBoxMenuItem( "Char Sheet Updates" );
        /* 152 */
        this.charSheetAlertMenuItem.setSelected( ( (Boolean) this.prefs.getPref( 12 ) ).booleanValue() );
        /* 153 */
        /* 157 */
        this.charSheetAlertMenuItem.addActionListener(paramAnonymousActionEvent -> {
            /* 155 */
            SoundMenu.this.prefs.setPref( 12, new Boolean( SoundMenu.this.charSheetAlertMenuItem.isSelected() ) );
        });
        /* 158 */
        add( this.charSheetAlertMenuItem );


        /* 161 */
        this.newPMAlertMenuItem = new JCheckBoxMenuItem( "Incoming Private Msg" );
        /* 162 */
        this.newPMAlertMenuItem.setSelected( ( (Boolean) this.prefs.getPref( 13 ) ).booleanValue() );
        /* 163 */
        /* 167 */
        this.newPMAlertMenuItem.addActionListener(paramAnonymousActionEvent -> {
            /* 165 */
            SoundMenu.this.prefs.setPref( 13, new Boolean( SoundMenu.this.newPMAlertMenuItem.isSelected() ) );
        });
        /* 168 */
        add( this.newPMAlertMenuItem );


        /* 171 */
        this.deathAlertMenuItem = new JCheckBoxMenuItem( "Deaths" );
        /* 172 */
        this.deathAlertMenuItem.setSelected( ( (Boolean) this.prefs.getPref( 14 ) ).booleanValue() );
        /* 173 */
        /* 177 */
        this.deathAlertMenuItem.addActionListener(paramAnonymousActionEvent -> {
            /* 175 */
            SoundMenu.this.prefs.setPref( 14, new Boolean( SoundMenu.this.deathAlertMenuItem.isSelected() ) );
        });
        /* 178 */
        add( this.deathAlertMenuItem );
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\shared\SoundMenu.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
