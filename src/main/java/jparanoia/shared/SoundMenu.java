package jparanoia.shared;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JMenu;
import static jparanoia.shared.Prefs.PLAY_CHARSHEET;
import static jparanoia.shared.Prefs.PLAY_COMBAT_ALERT;
import static jparanoia.shared.Prefs.PLAY_COMBAT_MUSIC;
import static jparanoia.shared.Prefs.PLAY_CONNECTED_DISCONNECTED;
import static jparanoia.shared.Prefs.PLAY_DEATH;
import static jparanoia.shared.Prefs.PLAY_FROZEN_UNFROZEN;
import static jparanoia.shared.Prefs.PLAY_JOIN_LEAVE;
import static jparanoia.shared.Prefs.PLAY_LOGIN_BADLOGIN;
import static jparanoia.shared.Prefs.PLAY_MUTED_UNMUTED;
import static jparanoia.shared.Prefs.PLAY_NEW_OBSERVER_TEXT;
import static jparanoia.shared.Prefs.PLAY_NEW_PM;
import static jparanoia.shared.Prefs.PLAY_NEW_TEXT;
import static jparanoia.shared.Prefs.PLAY_PROMOTED_DEMOTED;
import static jparanoia.shared.Prefs.PLAY_SOUNDS;
import static jparanoia.shared.Prefs.PLAY_STARTUP;

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
        super( paramString );
        this.caller = paramJParanoia;
        this.prefs = paramPrefs;
        this.playSoundsMenuItem = new JCheckBoxMenuItem( "Play Sounds" );
        this.playSoundsMenuItem.setSelected( (Boolean) this.prefs.getPref( PLAY_SOUNDS ) );
        this.playSoundsMenuItem.addActionListener( paramAnonymousActionEvent -> {
            JParanoia.toggleSoundEngine();
            SoundMenu.this.prefs.setPref( PLAY_SOUNDS, SoundMenu.this.playSoundsMenuItem.isSelected() );
        } );
        add( this.playSoundsMenuItem );
        addSeparator();
        add( new JLabel( "  Play sounds for:" ) );
        this.startupMenuItem = new JCheckBoxMenuItem( "Startup" );
        this.startupMenuItem.setSelected( (Boolean) this.prefs.getPref( PLAY_STARTUP ) );
        this.startupMenuItem.addActionListener( paramAnonymousActionEvent -> {
            SoundMenu.this.prefs.setPref( PLAY_STARTUP, SoundMenu.this.startupMenuItem.isSelected() );
        } );
        add( this.startupMenuItem );
        this.joinLeaveMenuItem = new JCheckBoxMenuItem( "Join / Leave" );
        this.joinLeaveMenuItem.setSelected( (Boolean) this.prefs.getPref( PLAY_JOIN_LEAVE ) );
        this.joinLeaveMenuItem.addActionListener( paramAnonymousActionEvent -> {
            SoundMenu.this.prefs.setPref( PLAY_JOIN_LEAVE, SoundMenu.this.joinLeaveMenuItem.isSelected() );
        } );
        add( this.joinLeaveMenuItem );
        this.newTextMenuItem = new JCheckBoxMenuItem( "New Text" );
        this.newTextMenuItem.setSelected( (Boolean) this.prefs.getPref( PLAY_NEW_TEXT ) );
        this.newTextMenuItem.addActionListener( paramAnonymousActionEvent -> {
            SoundMenu.this.prefs.setPref( PLAY_NEW_TEXT, SoundMenu.this.newTextMenuItem.isSelected() );
        } );
        add( this.newTextMenuItem );
        this.newObserverTextMenuItem = new JCheckBoxMenuItem( "New Observer Text" );
        this.newObserverTextMenuItem.setSelected( (Boolean) this.prefs.getPref( PLAY_NEW_OBSERVER_TEXT ) );
        this.newObserverTextMenuItem.addActionListener( paramAnonymousActionEvent -> {
            SoundMenu.this.prefs.setPref( PLAY_NEW_OBSERVER_TEXT, SoundMenu.this.newObserverTextMenuItem.isSelected() );
        } );
        add( this.newObserverTextMenuItem );
        this.mutedUnmutedMenuItem = new JCheckBoxMenuItem( "Muted / Unmuted" );
        this.mutedUnmutedMenuItem.setSelected( (Boolean) this.prefs.getPref( PLAY_MUTED_UNMUTED ) );
        this.mutedUnmutedMenuItem.addActionListener( paramAnonymousActionEvent -> {
            SoundMenu.this.prefs.setPref( PLAY_MUTED_UNMUTED, SoundMenu.this.mutedUnmutedMenuItem.isSelected() );
        } );
        add( this.mutedUnmutedMenuItem );
        this.freezeUnfreezeMenuItem = new JCheckBoxMenuItem( "Frozen / Unfrozen" );
        this.freezeUnfreezeMenuItem.setSelected( (Boolean) this.prefs.getPref( PLAY_FROZEN_UNFROZEN ) );
        this.freezeUnfreezeMenuItem.addActionListener( paramAnonymousActionEvent -> {
            SoundMenu.this.prefs.setPref( PLAY_FROZEN_UNFROZEN, SoundMenu.this.freezeUnfreezeMenuItem.isSelected() );
        } );
        add( this.freezeUnfreezeMenuItem );
        this.promotedDemotedMenuItem = new JCheckBoxMenuItem( "Promoted / Demoted" );
        this.promotedDemotedMenuItem.setSelected( (Boolean) this.prefs.getPref( PLAY_PROMOTED_DEMOTED ) );
        this.promotedDemotedMenuItem.addActionListener( paramAnonymousActionEvent -> {
            SoundMenu.this.prefs.setPref( PLAY_PROMOTED_DEMOTED, SoundMenu.this.promotedDemotedMenuItem.isSelected() );
        } );
        add( this.promotedDemotedMenuItem );
        this.loginBadLoginMenuItem = new JCheckBoxMenuItem( "Login Valid / Invalid" );
        this.loginBadLoginMenuItem.setSelected( (Boolean) this.prefs.getPref( PLAY_LOGIN_BADLOGIN ) );
        this.loginBadLoginMenuItem.addActionListener( paramAnonymousActionEvent -> {
            SoundMenu.this.prefs.setPref( PLAY_LOGIN_BADLOGIN, SoundMenu.this.loginBadLoginMenuItem.isSelected() );
        } );
        add( this.loginBadLoginMenuItem );
        this.connectedDisconnectedMenuItem = new JCheckBoxMenuItem( "Connect / Disconnect" );
        this.connectedDisconnectedMenuItem.setSelected( (Boolean) this.prefs.getPref( PLAY_CONNECTED_DISCONNECTED ) );
        this.connectedDisconnectedMenuItem.addActionListener( paramAnonymousActionEvent -> {
            SoundMenu.this.prefs.setPref( PLAY_CONNECTED_DISCONNECTED, SoundMenu.this.connectedDisconnectedMenuItem.isSelected() );
        } );
        add( this.connectedDisconnectedMenuItem );
        this.combatAlertMenuItem = new JCheckBoxMenuItem( "Combat Alert" );
        this.combatAlertMenuItem.setSelected( (Boolean) this.prefs.getPref( PLAY_COMBAT_ALERT ) );
        this.combatAlertMenuItem.addActionListener( paramAnonymousActionEvent -> {
            SoundMenu.this.prefs.setPref( PLAY_COMBAT_ALERT, SoundMenu.this.combatAlertMenuItem.isSelected() );
        } );
        add( this.combatAlertMenuItem );
        this.combatMusicMenuItem = new JCheckBoxMenuItem( "Combat Music" );
        this.combatMusicMenuItem.setSelected( (Boolean) this.prefs.getPref( PLAY_COMBAT_MUSIC ) );
        this.combatMusicMenuItem.addActionListener( paramAnonymousActionEvent -> {
            SoundMenu.this.prefs.setPref( PLAY_COMBAT_MUSIC, SoundMenu.this.combatMusicMenuItem.isSelected() );
        } );
        add( this.combatMusicMenuItem );
        this.charSheetAlertMenuItem = new JCheckBoxMenuItem( "Char Sheet Updates" );
        this.charSheetAlertMenuItem.setSelected( (Boolean) this.prefs.getPref( PLAY_CHARSHEET ) );
        this.charSheetAlertMenuItem.addActionListener( paramAnonymousActionEvent -> {
            SoundMenu.this.prefs.setPref( PLAY_CHARSHEET, SoundMenu.this.charSheetAlertMenuItem.isSelected() );
        } );
        add( this.charSheetAlertMenuItem );
        this.newPMAlertMenuItem = new JCheckBoxMenuItem( "Incoming Private Msg" );
        this.newPMAlertMenuItem.setSelected( (Boolean) this.prefs.getPref( PLAY_NEW_PM ) );
        this.newPMAlertMenuItem.addActionListener( paramAnonymousActionEvent -> {
            SoundMenu.this.prefs.setPref( PLAY_NEW_PM, SoundMenu.this.newPMAlertMenuItem.isSelected() );
        } );
        add( this.newPMAlertMenuItem );
        this.deathAlertMenuItem = new JCheckBoxMenuItem( "Deaths" );
        this.deathAlertMenuItem.setSelected( (Boolean) this.prefs.getPref( PLAY_DEATH ) );
        this.deathAlertMenuItem.addActionListener( paramAnonymousActionEvent -> {
            SoundMenu.this.prefs.setPref( PLAY_DEATH, SoundMenu.this.deathAlertMenuItem.isSelected() );
        } );
        add( this.deathAlertMenuItem );
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\shared\SoundMenu.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
