package jparanoia.server;
import java.lang.invoke.MethodHandles;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import static jparanoia.server.JPServer.keepLog;
import jparanoia.shared.GameLogger;
import static jparanoia.shared.JParanoia.autoScroll;
import static jparanoia.shared.JParanoia.log;
import static jparanoia.shared.JParanoia.logBroken;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

public class ServerOptionsMenu extends JMenu {
    private final static Logger logger = getLogger( MethodHandles.lookup().lookupClass());

    JCheckBoxMenuItem autoScrollMenuItem;
    JCheckBoxMenuItem showTimeStampsMenuItem;
    JCheckBoxMenuItem bigComputerFontMenuItem;
    JCheckBoxMenuItem computerAllCapsMenuItem;
    JCheckBoxMenuItem makeLogMenuItem;
    JCheckBoxMenuItem allowGMEmotesMenuItem;
    JCheckBoxMenuItem quickCharsheetMenuItem;
    JCheckBoxMenuItem useAnnouncementMenuItem;
    JCheckBoxMenuItem singleUseSpoofMenuItem;
    JRadioButtonMenuItem cpuIncrease0;
    JRadioButtonMenuItem cpuIncrease2;
    JRadioButtonMenuItem cpuIncrease4;
    JRadioButtonMenuItem cpuIncrease6;
    JRadioButtonMenuItem cpuIncrease8;
    JRadioButtonMenuItem cpuIncrease10;
    JRadioButtonMenuItem cpuIncrease12;
    JRadioButtonMenuItem cpuIncrease14;
    JRadioButtonMenuItem cpuIncrease16;
    JRadioButtonMenuItem cpuIncrease18;
    JMenu computerFontMenu;
    JMenu computerFontIncreaseMenu;
    JMenu titleMessageMenu;
    JMenu announcementMenu;
    JMenuItem titleMessageMenuItem;
    JMenuItem clearTitleMessageMenuItem;
    JMenuItem aboutBoxMenuItem;
    JMenuItem setGameDescriptionMenuItem;
    JMenuItem setAnnouncementMenuItem;
    JMenuItem clearAnnouncementMenuItem;

    public ServerOptionsMenu( String paramString ) {
        super( paramString );
        this.autoScrollMenuItem = new JCheckBoxMenuItem( "Autoscroll" );
        this.autoScrollMenuItem.setSelected( JPServer.autoScroll );
        this.autoScrollMenuItem.addActionListener( paramAnonymousActionEvent -> {
            autoScroll = !autoScroll;
            logger.info( "AUTO SCROLL = " + autoScroll );
        } );
        this.makeLogMenuItem = new JCheckBoxMenuItem( "Log Game" );
        this.makeLogMenuItem.setToolTipText( "Logs are saved in the 'logs' directory." );
        this.makeLogMenuItem.setSelected( (Boolean) JPServer.prefs.getPref( 20 ) );
        this.makeLogMenuItem.addActionListener( paramAnonymousActionEvent -> {
            keepLog = !keepLog;
            logger.info( "KEEP LOG = " + keepLog );
            if ( keepLog ) {
                log = new GameLogger();
            } else {
                log.closeLog();
            }
            if ( logBroken ) {
                ServerOptionsMenu.this.makeLogMenuItem.setEnabled( false );
                ServerOptionsMenu.this.makeLogMenuItem.setSelected( false );
            }
        } );
        this.quickCharsheetMenuItem = new JCheckBoxMenuItem( "Quick Charsheet" );
        this.quickCharsheetMenuItem.setSelected( (Boolean) JPServer.prefs.getPref( 33 ) );
        this.showTimeStampsMenuItem = new JCheckBoxMenuItem( "Show Timestamps" );
        this.showTimeStampsMenuItem.setSelected( (Boolean) JPServer.prefs.getPref( 24 ) );
        this.showTimeStampsMenuItem.addActionListener( paramAnonymousActionEvent -> {
            JPServer.showTimeStamps = !JPServer.showTimeStamps;
        } );
        this.bigComputerFontMenuItem = new JCheckBoxMenuItem( "Show Big Computer Font" );
        this.bigComputerFontMenuItem.setToolTipText( "<HTML>When disabled, players see The Computer's font<BR>in the increased size but you do not.</HTML>" );
        this.bigComputerFontMenuItem.setSelected( (Boolean) JPServer.prefs.getPref( 25 ) );
        this.bigComputerFontMenuItem.addActionListener( paramAnonymousActionEvent -> {
            JPServer.bigComputerFont = !JPServer.bigComputerFont;
        } );
        this.computerAllCapsMenuItem = new JCheckBoxMenuItem( "Computer speech in CAPS" );
        this.computerAllCapsMenuItem.setToolTipText( "Show The Computer's speech in ALL CAPS." );
        this.computerAllCapsMenuItem.setSelected( (Boolean) JPServer.prefs.getPref( 27 ) );
        this.computerAllCapsMenuItem.addActionListener( paramAnonymousActionEvent -> JPServer.computerAllCaps = !JPServer.computerAllCaps );
        this.computerFontIncreaseMenu = new JMenu( "Computer font increase" );
        this.computerFontIncreaseMenu.setToolTipText( "<HTML>Amount to increase<BR>The Computer's font size.</HTML>" );
        this.cpuIncrease0 = new JRadioButtonMenuItem( "None" );
        this.cpuIncrease0.addActionListener( paramAnonymousActionEvent -> {
            JPServer.spamString( "0700" );
            JPServer.computerFontIncrease = 0;
        } );
        this.cpuIncrease2 = new JRadioButtonMenuItem( "2 points" );
        this.cpuIncrease2.addActionListener( paramAnonymousActionEvent -> {
            JPServer.spamString( "0702" );
            JPServer.computerFontIncrease = 2;
        } );
        this.cpuIncrease4 = new JRadioButtonMenuItem( "4 points" );
        this.cpuIncrease4.addActionListener( paramAnonymousActionEvent -> {
            JPServer.spamString( "0704" );
            JPServer.computerFontIncrease = 4;
        } );
        this.cpuIncrease6 = new JRadioButtonMenuItem( "6 points" );
        this.cpuIncrease6.addActionListener( paramAnonymousActionEvent -> {
            JPServer.spamString( "0706" );
            JPServer.computerFontIncrease = 6;
        } );
        this.cpuIncrease8 = new JRadioButtonMenuItem( "8 points" );
        this.cpuIncrease8.addActionListener( paramAnonymousActionEvent -> {
            JPServer.spamString( "0708" );
            JPServer.computerFontIncrease = 8;
        } );
        this.cpuIncrease10 = new JRadioButtonMenuItem( "10 points" );
        this.cpuIncrease10.addActionListener( paramAnonymousActionEvent -> {
            JPServer.spamString( "07010" );
            JPServer.computerFontIncrease = 10;
        } );
        this.cpuIncrease12 = new JRadioButtonMenuItem( "12 points" );
        this.cpuIncrease12.addActionListener( paramAnonymousActionEvent -> {
            JPServer.spamString( "07012" );
            JPServer.computerFontIncrease = 12;
        } );
        this.cpuIncrease14 = new JRadioButtonMenuItem( "14 points" );
        this.cpuIncrease14.addActionListener( paramAnonymousActionEvent -> {
            JPServer.spamString( "07014" );
            JPServer.computerFontIncrease = 14;
        } );
        this.cpuIncrease16 = new JRadioButtonMenuItem( "16 points" );
        this.cpuIncrease16.addActionListener( paramAnonymousActionEvent -> {
            JPServer.spamString( "07016" );
            JPServer.computerFontIncrease = 16;
        } );
        this.cpuIncrease18 = new JRadioButtonMenuItem( "18 points" );
        this.cpuIncrease18.addActionListener( paramAnonymousActionEvent -> {
            JPServer.spamString( "07018" );
            JPServer.computerFontIncrease = 18;
        } );
        ButtonGroup localButtonGroup = new ButtonGroup();
        localButtonGroup.add( this.cpuIncrease0 );
        localButtonGroup.add( this.cpuIncrease2 );
        localButtonGroup.add( this.cpuIncrease4 );
        localButtonGroup.add( this.cpuIncrease6 );
        localButtonGroup.add( this.cpuIncrease8 );
        localButtonGroup.add( this.cpuIncrease10 );
        localButtonGroup.add( this.cpuIncrease12 );
        localButtonGroup.add( this.cpuIncrease14 );
        localButtonGroup.add( this.cpuIncrease16 );
        localButtonGroup.add( this.cpuIncrease18 );
        this.computerFontIncreaseMenu.add( this.cpuIncrease0 );
        this.computerFontIncreaseMenu.add( this.cpuIncrease2 );
        this.computerFontIncreaseMenu.add( this.cpuIncrease4 );
        this.computerFontIncreaseMenu.add( this.cpuIncrease6 );
        this.computerFontIncreaseMenu.add( this.cpuIncrease8 );
        this.computerFontIncreaseMenu.add( this.cpuIncrease10 );
        this.computerFontIncreaseMenu.add( this.cpuIncrease12 );
        this.computerFontIncreaseMenu.add( this.cpuIncrease14 );
        this.computerFontIncreaseMenu.add( this.cpuIncrease16 );
        this.computerFontIncreaseMenu.add( this.cpuIncrease18 );
        switch ( JPServer.computerFontIncrease ) {
            case 0:
                this.cpuIncrease0.setSelected( true );
                break;
            case 2:
                this.cpuIncrease2.setSelected( true );
                break;
            case 4:
                this.cpuIncrease4.setSelected( true );
                break;
            case 6:
                this.cpuIncrease6.setSelected( true );
                break;
            case 8:
                this.cpuIncrease8.setSelected( true );
                break;
            case 10:
                this.cpuIncrease10.setSelected( true );
                break;
            case 12:
                this.cpuIncrease12.setSelected( true );
                break;
            case 14:
                this.cpuIncrease14.setSelected( true );
                break;
            case 16:
                this.cpuIncrease16.setSelected( true );
                break;
            case 18:
                this.cpuIncrease18.setSelected( true );
                break;
        }
        this.titleMessageMenuItem = new JMenuItem( "Set Title Message..." );
        this.titleMessageMenuItem.setToolTipText( "<HTML>Displays a message in the title bar<BR>of everyone's main window.</HTML>" );
        this.titleMessageMenuItem.addActionListener( paramAnonymousActionEvent -> {
            new JOptionPane();
            JPServer.titleMessage = (String) JOptionPane.showInputDialog( null, "Enter a new title bar message:", "Set Title Message", JOptionPane.PLAIN_MESSAGE, null, null, "" );
            if ( JPServer.titleMessage != null ) {
                JPServer.setTitleMessage( JPServer.titleMessage );
            }
        } );
        this.clearTitleMessageMenuItem = new JMenuItem( "Clear Title Message" );
        this.clearTitleMessageMenuItem.addActionListener( paramAnonymousActionEvent -> JPServer.clearTitleMessage() );
        this.useAnnouncementMenuItem = new JCheckBoxMenuItem( "Use Announcement" );
        this.useAnnouncementMenuItem.setSelected( (Boolean) JPServer.prefs.getPref( 37 ) );
        this.setAnnouncementMenuItem = new JMenuItem( "Set Announcement..." );
        this.setAnnouncementMenuItem.addActionListener( paramAnonymousActionEvent -> JPServer.setAnnouncement());
        this.clearAnnouncementMenuItem = new JMenuItem( "Clear Announcement" );
        this.clearAnnouncementMenuItem.addActionListener( paramAnonymousActionEvent -> JPServer.clearAnnouncement() );
        this.allowGMEmotesMenuItem = new JCheckBoxMenuItem( "Allow GM Emotes" );
        this.allowGMEmotesMenuItem.setToolTipText( "<HTML> When disabled, if the you attempt to use speech <BR> or perform an action when you are not spoofing <BR> a character, you will receive a warning and the <BR> text will not be sent.</HTML>" );
        this.allowGMEmotesMenuItem.setSelected( JPServer.allowGMEmotes );
        this.allowGMEmotesMenuItem.addActionListener( paramAnonymousActionEvent -> JPServer.allowGMEmotes = !JPServer.allowGMEmotes);
        this.singleUseSpoofMenuItem = new JCheckBoxMenuItem( "Single Use Spoofing" );
        this.singleUseSpoofMenuItem.setToolTipText( "<HTML> When checked, the Spoof checkbox will <BR>automatically deselect itself when you send a <BR>spoofed line.</HTML>" );
        this.singleUseSpoofMenuItem.setSelected( (Boolean) JPServer.prefs.getPref( 36 ) );
        this.singleUseSpoofMenuItem.addActionListener( paramAnonymousActionEvent -> { /*FIXME: empty brackets!*/} );
        this.computerFontMenu = new JMenu( "Computer font" );
        this.computerFontMenu.add( this.bigComputerFontMenuItem );
        this.computerFontMenu.add( this.computerAllCapsMenuItem );
        this.computerFontMenu.add( this.computerFontIncreaseMenu );
        this.titleMessageMenu = new JMenu( "Title message" );
        this.titleMessageMenu.add( this.titleMessageMenuItem );
        this.titleMessageMenu.add( this.clearTitleMessageMenuItem );
        add( JPServer.soundMenu );
        addSeparator();
        add( this.autoScrollMenuItem );
        add( this.makeLogMenuItem );
        add( this.showTimeStampsMenuItem );
        add( this.quickCharsheetMenuItem );
        addSeparator();
        add( this.computerFontMenu );
        addSeparator();
        add( this.titleMessageMenu );
        add( this.useAnnouncementMenuItem );
        addSeparator();
        add( this.allowGMEmotesMenuItem );
        add( this.singleUseSpoofMenuItem );
        addSeparator();
        add( JPServer.aboutBoxMenuItem );
        JPServer.computerAllCaps = this.computerAllCapsMenuItem.isSelected();
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\ServerOptionsMenu.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
