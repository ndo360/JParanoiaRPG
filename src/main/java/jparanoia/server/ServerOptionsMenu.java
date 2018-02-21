package jparanoia.server;
import java.awt.event.ActionEvent;

public class ServerOptionsMenu extends javax.swing.JMenu {
    javax.swing.JCheckBoxMenuItem autoScrollMenuItem;
    javax.swing.JCheckBoxMenuItem showTimeStampsMenuItem;
    javax.swing.JCheckBoxMenuItem bigComputerFontMenuItem;
    javax.swing.JCheckBoxMenuItem computerAllCapsMenuItem;
    javax.swing.JCheckBoxMenuItem makeLogMenuItem;
    javax.swing.JCheckBoxMenuItem allowGMEmotesMenuItem;
    javax.swing.JCheckBoxMenuItem quickCharsheetMenuItem;
    javax.swing.JCheckBoxMenuItem useAnnouncementMenuItem;
    javax.swing.JCheckBoxMenuItem singleUseSpoofMenuItem;
    javax.swing.JRadioButtonMenuItem cpuIncrease0;
    javax.swing.JRadioButtonMenuItem cpuIncrease2;
    javax.swing.JRadioButtonMenuItem cpuIncrease4;
    javax.swing.JRadioButtonMenuItem cpuIncrease6;
    javax.swing.JRadioButtonMenuItem cpuIncrease8;
    javax.swing.JRadioButtonMenuItem cpuIncrease10;
    javax.swing.JRadioButtonMenuItem cpuIncrease12;
    javax.swing.JRadioButtonMenuItem cpuIncrease14;
    javax.swing.JRadioButtonMenuItem cpuIncrease16;
    javax.swing.JRadioButtonMenuItem cpuIncrease18;
    javax.swing.JMenu computerFontMenu;
    javax.swing.JMenu computerFontIncreaseMenu;
    javax.swing.JMenu titleMessageMenu;
    javax.swing.JMenu announcementMenu;
    javax.swing.JMenuItem titleMessageMenuItem;
    javax.swing.JMenuItem clearTitleMessageMenuItem;
    javax.swing.JMenuItem aboutBoxMenuItem;
    javax.swing.JMenuItem setGameDescriptionMenuItem;
    javax.swing.JMenuItem setAnnouncementMenuItem;
    javax.swing.JMenuItem clearAnnouncementMenuItem;
    public ServerOptionsMenu( String paramString ) {

        super( paramString );


        this.autoScrollMenuItem = new javax.swing.JCheckBoxMenuItem( "Autoscroll" );

        this.autoScrollMenuItem.setSelected( JPServer.autoScroll );


        this.autoScrollMenuItem.addActionListener(paramAnonymousActionEvent -> {

            JPServer.autoScroll = !JPServer.autoScroll;

            System.out.println( "AUTO SCROLL = " + JPServer.autoScroll );
        });

        this.makeLogMenuItem = new javax.swing.JCheckBoxMenuItem( "Log Game" );

        this.makeLogMenuItem.setToolTipText( "Logs are saved in the 'logs' directory." );



        this.makeLogMenuItem.setSelected( ( (Boolean) JPServer.prefs.getPref( 20 ) ).booleanValue() );

        this.makeLogMenuItem.addActionListener(paramAnonymousActionEvent -> {

            JPServer.keepLog = !JPServer.keepLog;

            System.out.println( "KEEP LOG = " + JPServer.keepLog );

            if ( JPServer.keepLog ) {
                JPServer.logger = new jparanoia.shared.GameLogger();
            } else
                 {
                JPServer.logger.closeLog();
            }

            if ( JPServer.logBroken ) {
                ServerOptionsMenu.this.makeLogMenuItem.setEnabled( false );
                ServerOptionsMenu.this.makeLogMenuItem.setSelected( false );
            }

        });

        this.quickCharsheetMenuItem = new javax.swing.JCheckBoxMenuItem( "Quick Charsheet" );

        this.quickCharsheetMenuItem.setSelected( ( (Boolean) JPServer.prefs.getPref( 33 ) ).booleanValue() );







        this.showTimeStampsMenuItem = new javax.swing.JCheckBoxMenuItem( "Show Timestamps" );

        this.showTimeStampsMenuItem.setSelected( ( (Boolean) JPServer.prefs.getPref( 24 ) ).booleanValue() );


        this.showTimeStampsMenuItem.addActionListener(paramAnonymousActionEvent -> {

            JPServer.showTimeStamps = !JPServer.showTimeStamps;

        });

        this.bigComputerFontMenuItem = new javax.swing.JCheckBoxMenuItem( "Show Big Computer Font" );

        this.bigComputerFontMenuItem.setToolTipText( "<HTML>When disabled, players see The Computer's font<BR>in the increased size but you do not.</HTML>" );







        this.bigComputerFontMenuItem.setSelected( ( (Boolean) JPServer.prefs.getPref( 25 ) ).booleanValue() );


        this.bigComputerFontMenuItem.addActionListener(paramAnonymousActionEvent -> {

            JPServer.bigComputerFont = !JPServer.bigComputerFont;

        });

        this.computerAllCapsMenuItem = new javax.swing.JCheckBoxMenuItem( "Computer speech in CAPS" );

        this.computerAllCapsMenuItem.setToolTipText( "Show The Computer's speech in ALL CAPS." );

        this.computerAllCapsMenuItem.setSelected( ( (Boolean) JPServer.prefs.getPref( 27 ) ).booleanValue() );



        this.computerAllCapsMenuItem.addActionListener(paramAnonymousActionEvent -> JPServer.computerAllCaps = !JPServer.computerAllCaps);

        this.computerFontIncreaseMenu = new javax.swing.JMenu( "Computer font increase" );

        this.computerFontIncreaseMenu.setToolTipText( "<HTML>Amount to increase<BR>The Computer's font size.</HTML>" );


        this.cpuIncrease0 = new javax.swing.JRadioButtonMenuItem( "None" );

        this.cpuIncrease0.addActionListener(paramAnonymousActionEvent -> {

            JPServer.spamString( "0700" );

            JPServer.computerFontIncrease = 0;

        });

        this.cpuIncrease2 = new javax.swing.JRadioButtonMenuItem( "2 points" );

        this.cpuIncrease2.addActionListener(paramAnonymousActionEvent -> {

            JPServer.spamString( "0702" );

            JPServer.computerFontIncrease = 2;

        });

        this.cpuIncrease4 = new javax.swing.JRadioButtonMenuItem( "4 points" );

        this.cpuIncrease4.addActionListener(paramAnonymousActionEvent -> {

            JPServer.spamString( "0704" );

            JPServer.computerFontIncrease = 4;

        });

        this.cpuIncrease6 = new javax.swing.JRadioButtonMenuItem( "6 points" );

        this.cpuIncrease6.addActionListener(paramAnonymousActionEvent -> {

            JPServer.spamString( "0706" );

            JPServer.computerFontIncrease = 6;

        });

        this.cpuIncrease8 = new javax.swing.JRadioButtonMenuItem( "8 points" );

        this.cpuIncrease8.addActionListener(paramAnonymousActionEvent -> {

            JPServer.spamString( "0708" );

            JPServer.computerFontIncrease = 8;

        });

        this.cpuIncrease10 = new javax.swing.JRadioButtonMenuItem( "10 points" );

        this.cpuIncrease10.addActionListener(paramAnonymousActionEvent -> {

            JPServer.spamString( "07010" );

            JPServer.computerFontIncrease = 10;

        });

        this.cpuIncrease12 = new javax.swing.JRadioButtonMenuItem( "12 points" );

        this.cpuIncrease12.addActionListener(paramAnonymousActionEvent -> {

            JPServer.spamString( "07012" );

            JPServer.computerFontIncrease = 12;

        });

        this.cpuIncrease14 = new javax.swing.JRadioButtonMenuItem( "14 points" );

        this.cpuIncrease14.addActionListener(paramAnonymousActionEvent -> {

            JPServer.spamString( "07014" );

            JPServer.computerFontIncrease = 14;

        });

        this.cpuIncrease16 = new javax.swing.JRadioButtonMenuItem( "16 points" );

        this.cpuIncrease16.addActionListener(paramAnonymousActionEvent -> {

            JPServer.spamString( "07016" );

            JPServer.computerFontIncrease = 16;

        });

        this.cpuIncrease18 = new javax.swing.JRadioButtonMenuItem( "18 points" );

        this.cpuIncrease18.addActionListener(paramAnonymousActionEvent -> {

            JPServer.spamString( "07018" );

            JPServer.computerFontIncrease = 18;

        });

        javax.swing.ButtonGroup localButtonGroup = new javax.swing.ButtonGroup();


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





        this.titleMessageMenuItem = new javax.swing.JMenuItem( "Set Title Message..." );

        this.titleMessageMenuItem.setToolTipText( "<HTML>Displays a message in the title bar<BR>of everyone's main window.</HTML>" );






        this.titleMessageMenuItem.addActionListener(paramAnonymousActionEvent -> {

            new javax.swing.JOptionPane();
            JPServer.titleMessage = (String) javax.swing.JOptionPane.showInputDialog( null, "Enter a new title bar message:", "Set Title Message", -1, null, null, "" );


            if ( JPServer.titleMessage != null ) {
                JPServer.setTitleMessage( JPServer.titleMessage );
            }
        });

        this.clearTitleMessageMenuItem = new javax.swing.JMenuItem( "Clear Title Message" );


        this.clearTitleMessageMenuItem.addActionListener(paramAnonymousActionEvent -> {
        });

        this.useAnnouncementMenuItem = new javax.swing.JCheckBoxMenuItem( "Use Announcement" );

        this.useAnnouncementMenuItem.setSelected( ( (Boolean) JPServer.prefs.getPref( 37 ) ).booleanValue() );


        this.setAnnouncementMenuItem = new javax.swing.JMenuItem( "Set Announcement..." );


        this.setAnnouncementMenuItem.addActionListener(paramAnonymousActionEvent -> {
        });

        this.clearAnnouncementMenuItem = new javax.swing.JMenuItem( "Clear Announcement" );


        this.clearAnnouncementMenuItem.addActionListener(paramAnonymousActionEvent -> {
        });

        this.allowGMEmotesMenuItem = new javax.swing.JCheckBoxMenuItem( "Allow GM Emotes" );


        this.allowGMEmotesMenuItem.setToolTipText( "<HTML> When disabled, if the you attempt to use speech <BR> or perform an action when you are not spoofing <BR> a character, you will receive a warning and the <BR> text will not be sent.</HTML>" );









        this.allowGMEmotesMenuItem.setSelected( JPServer.allowGMEmotes );


        this.allowGMEmotesMenuItem.addActionListener(paramAnonymousActionEvent -> {

            JPServer.allowGMEmotes = !JPServer.allowGMEmotes;
        });

        this.singleUseSpoofMenuItem = new javax.swing.JCheckBoxMenuItem( "Single Use Spoofing" );


        this.singleUseSpoofMenuItem.setToolTipText( "<HTML> When checked, the Spoof checkbox will <BR>automatically deselect itself when you send a <BR>spoofed line.</HTML>" );






        this.singleUseSpoofMenuItem.setSelected( ( (Boolean) JPServer.prefs.getPref( 36 ) ).booleanValue() );


        this.singleUseSpoofMenuItem.addActionListener(paramAnonymousActionEvent -> {
        });

        this.computerFontMenu = new javax.swing.JMenu( "Computer font" );

        this.computerFontMenu.add( this.bigComputerFontMenuItem );

        this.computerFontMenu.add( this.computerAllCapsMenuItem );

        this.computerFontMenu.add( this.computerFontIncreaseMenu );


        this.titleMessageMenu = new javax.swing.JMenu( "Title message" );

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
