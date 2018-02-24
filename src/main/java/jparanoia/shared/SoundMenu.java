package jparanoia.shared;
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
        super( paramString );
        this.caller = paramJParanoia;
        this.prefs = paramPrefs;
        this.playSoundsMenuItem = new JCheckBoxMenuItem( "Play Sounds" );
        this.playSoundsMenuItem.setSelected( (Boolean) this.prefs.getPref( 0 ) );
        this.playSoundsMenuItem.addActionListener( paramAnonymousActionEvent -> {
            JParanoia.toggleSoundEngine();
            SoundMenu.this.prefs.setPref( 0, SoundMenu.this.playSoundsMenuItem.isSelected() );
        } );
        add( this.playSoundsMenuItem );
        addSeparator();
        add( new JLabel( "  Play sounds for:" ) );
        this.startupMenuItem = new JCheckBoxMenuItem( "Startup" );
        this.startupMenuItem.setSelected( (Boolean) this.prefs.getPref( 1 ) );
        this.startupMenuItem.addActionListener( paramAnonymousActionEvent -> {
            SoundMenu.this.prefs.setPref( 1, SoundMenu.this.startupMenuItem.isSelected() );
        } );
        add( this.startupMenuItem );
        this.joinLeaveMenuItem = new JCheckBoxMenuItem( "Join / Leave" );
        this.joinLeaveMenuItem.setSelected( (Boolean) this.prefs.getPref( 2 ) );
        this.joinLeaveMenuItem.addActionListener( paramAnonymousActionEvent -> {
            SoundMenu.this.prefs.setPref( 2, SoundMenu.this.joinLeaveMenuItem.isSelected() );
        } );
        add( this.joinLeaveMenuItem );
        this.newTextMenuItem = new JCheckBoxMenuItem( "New Text" );
        this.newTextMenuItem.setSelected( (Boolean) this.prefs.getPref( 3 ) );
        this.newTextMenuItem.addActionListener( paramAnonymousActionEvent -> {
            SoundMenu.this.prefs.setPref( 3, SoundMenu.this.newTextMenuItem.isSelected() );
        } );
        add( this.newTextMenuItem );
        this.newObserverTextMenuItem = new JCheckBoxMenuItem( "New Observer Text" );
        this.newObserverTextMenuItem.setSelected( (Boolean) this.prefs.getPref( 4 ) );
        this.newObserverTextMenuItem.addActionListener( paramAnonymousActionEvent -> {
            SoundMenu.this.prefs.setPref( 4, SoundMenu.this.newObserverTextMenuItem.isSelected() );
        } );
        add( this.newObserverTextMenuItem );
        this.mutedUnmutedMenuItem = new JCheckBoxMenuItem( "Muted / Unmuted" );
        this.mutedUnmutedMenuItem.setSelected( (Boolean) this.prefs.getPref( 5 ) );
        this.mutedUnmutedMenuItem.addActionListener( paramAnonymousActionEvent -> {
            SoundMenu.this.prefs.setPref( 5, SoundMenu.this.mutedUnmutedMenuItem.isSelected() );
        } );
        add( this.mutedUnmutedMenuItem );
        this.freezeUnfreezeMenuItem = new JCheckBoxMenuItem( "Frozen / Unfrozen" );
        this.freezeUnfreezeMenuItem.setSelected( (Boolean) this.prefs.getPref( 6 ) );
        this.freezeUnfreezeMenuItem.addActionListener( paramAnonymousActionEvent -> {
            SoundMenu.this.prefs.setPref( 6, SoundMenu.this.freezeUnfreezeMenuItem.isSelected() );
        } );
        add( this.freezeUnfreezeMenuItem );
        this.promotedDemotedMenuItem = new JCheckBoxMenuItem( "Promoted / Demoted" );
        this.promotedDemotedMenuItem.setSelected( (Boolean) this.prefs.getPref( 7 ) );
        this.promotedDemotedMenuItem.addActionListener( paramAnonymousActionEvent -> {
            SoundMenu.this.prefs.setPref( 7, SoundMenu.this.promotedDemotedMenuItem.isSelected() );
        } );
        add( this.promotedDemotedMenuItem );
        this.loginBadLoginMenuItem = new JCheckBoxMenuItem( "Login Valid / Invalid" );
        this.loginBadLoginMenuItem.setSelected( (Boolean) this.prefs.getPref( 8 ) );
        this.loginBadLoginMenuItem.addActionListener( paramAnonymousActionEvent -> {
            SoundMenu.this.prefs.setPref( 8, SoundMenu.this.loginBadLoginMenuItem.isSelected() );
        } );
        add( this.loginBadLoginMenuItem );
        this.connectedDisconnectedMenuItem = new JCheckBoxMenuItem( "Connect / Disconnect" );
        this.connectedDisconnectedMenuItem.setSelected( (Boolean) this.prefs.getPref( 9 ) );
        this.connectedDisconnectedMenuItem.addActionListener( paramAnonymousActionEvent -> {
            SoundMenu.this.prefs.setPref( 9, SoundMenu.this.connectedDisconnectedMenuItem.isSelected() );
        } );
        add( this.connectedDisconnectedMenuItem );
        this.combatAlertMenuItem = new JCheckBoxMenuItem( "Combat Alert" );
        this.combatAlertMenuItem.setSelected( (Boolean) this.prefs.getPref( 10 ) );
        this.combatAlertMenuItem.addActionListener( paramAnonymousActionEvent -> {
            SoundMenu.this.prefs.setPref( 10, SoundMenu.this.combatAlertMenuItem.isSelected() );
        } );
        add( this.combatAlertMenuItem );
        this.combatMusicMenuItem = new JCheckBoxMenuItem( "Combat Music" );
        this.combatMusicMenuItem.setSelected( (Boolean) this.prefs.getPref( 11 ) );
        this.combatMusicMenuItem.addActionListener( paramAnonymousActionEvent -> {
            SoundMenu.this.prefs.setPref( 11, SoundMenu.this.combatMusicMenuItem.isSelected() );
        } );
        add( this.combatMusicMenuItem );
        this.charSheetAlertMenuItem = new JCheckBoxMenuItem( "Char Sheet Updates" );
        this.charSheetAlertMenuItem.setSelected( (Boolean) this.prefs.getPref( 12 ) );
        this.charSheetAlertMenuItem.addActionListener( paramAnonymousActionEvent -> {
            SoundMenu.this.prefs.setPref( 12, SoundMenu.this.charSheetAlertMenuItem.isSelected() );
        } );
        add( this.charSheetAlertMenuItem );
        this.newPMAlertMenuItem = new JCheckBoxMenuItem( "Incoming Private Msg" );
        this.newPMAlertMenuItem.setSelected( (Boolean) this.prefs.getPref( 13 ) );
        this.newPMAlertMenuItem.addActionListener( paramAnonymousActionEvent -> {
            SoundMenu.this.prefs.setPref( 13, SoundMenu.this.newPMAlertMenuItem.isSelected() );
        } );
        add( this.newPMAlertMenuItem );
        this.deathAlertMenuItem = new JCheckBoxMenuItem( "Deaths" );
        this.deathAlertMenuItem.setSelected( (Boolean) this.prefs.getPref( 14 ) );
        this.deathAlertMenuItem.addActionListener( paramAnonymousActionEvent -> {
            SoundMenu.this.prefs.setPref( 14, SoundMenu.this.deathAlertMenuItem.isSelected() );
        } );
        add( this.deathAlertMenuItem );
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\shared\SoundMenu.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
