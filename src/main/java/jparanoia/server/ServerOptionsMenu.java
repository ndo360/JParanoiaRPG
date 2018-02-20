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
        /*  23 */
        super( paramString );

        /*  25 */
        this.autoScrollMenuItem = new javax.swing.JCheckBoxMenuItem( "Autoscroll" );
        /*  26 */
        this.autoScrollMenuItem.setSelected( JPServer.autoScroll );
        /*  27 */
        /*  32 */
        this.autoScrollMenuItem.addActionListener(paramAnonymousActionEvent -> {
            /*  29 */
            JPServer.autoScroll = !JPServer.autoScroll;
            /*  30 */
            System.out.println( "AUTO SCROLL = " + JPServer.autoScroll );
        });
        /*  33 */
        this.makeLogMenuItem = new javax.swing.JCheckBoxMenuItem( "Log Game" );
        /*  34 */
        this.makeLogMenuItem.setToolTipText( "Logs are saved in the 'logs' directory." );


        /*  37 */
        this.makeLogMenuItem.setSelected( ( (Boolean) JPServer.prefs.getPref( 20 ) ).booleanValue() );
        /*  38 */
        this.makeLogMenuItem.addActionListener(paramAnonymousActionEvent -> {
            /*  40 */
            JPServer.keepLog = !JPServer.keepLog;
            /*  41 */
            System.out.println( "KEEP LOG = " + JPServer.keepLog );
            /*  42 */
            if ( JPServer.keepLog ) {
                JPServer.logger = new jparanoia.shared.GameLogger();
            } else
                /*  43 */ {
                JPServer.logger.closeLog();
            }
            /*  44 */
            if ( JPServer.logBroken ) {
                ServerOptionsMenu.this.makeLogMenuItem.setEnabled( false );
                ServerOptionsMenu.this.makeLogMenuItem.setSelected( false );
            }
            /*  46 */
        });
        /*  47 */
        this.quickCharsheetMenuItem = new javax.swing.JCheckBoxMenuItem( "Quick Charsheet" );
        /*  48 */
        this.quickCharsheetMenuItem.setSelected( ( (Boolean) JPServer.prefs.getPref( 33 ) ).booleanValue() );






        /*  55 */
        this.showTimeStampsMenuItem = new javax.swing.JCheckBoxMenuItem( "Show Timestamps" );
        /*  56 */
        this.showTimeStampsMenuItem.setSelected( ( (Boolean) JPServer.prefs.getPref( 24 ) ).booleanValue() );

        /*  58 */
        this.showTimeStampsMenuItem.addActionListener(paramAnonymousActionEvent -> {
            /*  60 */
            JPServer.showTimeStamps = !JPServer.showTimeStamps;
            /*  62 */
        });
        /*  63 */
        this.bigComputerFontMenuItem = new javax.swing.JCheckBoxMenuItem( "Show Big Computer Font" );
        /*  64 */
        this.bigComputerFontMenuItem.setToolTipText( "<HTML>When disabled, players see The Computer's font<BR>in the increased size but you do not.</HTML>" );






        /*  71 */
        this.bigComputerFontMenuItem.setSelected( ( (Boolean) JPServer.prefs.getPref( 25 ) ).booleanValue() );

        /*  73 */
        this.bigComputerFontMenuItem.addActionListener(paramAnonymousActionEvent -> {
            /*  75 */
            JPServer.bigComputerFont = !JPServer.bigComputerFont;
            /*  77 */
        });
        /*  78 */
        this.computerAllCapsMenuItem = new javax.swing.JCheckBoxMenuItem( "Computer speech in CAPS" );
        /*  79 */
        this.computerAllCapsMenuItem.setToolTipText( "Show The Computer's speech in ALL CAPS." );
        /*  80 */
        this.computerAllCapsMenuItem.setSelected( ( (Boolean) JPServer.prefs.getPref( 27 ) ).booleanValue() );

        /*  82 */
        /*  84 *//*  85 */
        this.computerAllCapsMenuItem.addActionListener(paramAnonymousActionEvent -> JPServer.computerAllCaps = !JPServer.computerAllCaps);
        /*  86 */
        this.computerFontIncreaseMenu = new javax.swing.JMenu( "Computer font increase" );
        /*  87 */
        this.computerFontIncreaseMenu.setToolTipText( "<HTML>Amount to increase<BR>The Computer's font size.</HTML>" );

        /*  89 */
        this.cpuIncrease0 = new javax.swing.JRadioButtonMenuItem( "None" );
        /*  90 */
        this.cpuIncrease0.addActionListener(paramAnonymousActionEvent -> {
            /*  92 */
            JPServer.spamString( "0700" );
            /*  93 */
            JPServer.computerFontIncrease = 0;
            /*  94 */
        });
        /*  95 */
        this.cpuIncrease2 = new javax.swing.JRadioButtonMenuItem( "2 points" );
        /*  96 */
        this.cpuIncrease2.addActionListener(paramAnonymousActionEvent -> {
            /*  98 */
            JPServer.spamString( "0702" );
            /*  99 */
            JPServer.computerFontIncrease = 2;
            /* 100 */
        });
        /* 101 */
        this.cpuIncrease4 = new javax.swing.JRadioButtonMenuItem( "4 points" );
        /* 102 */
        this.cpuIncrease4.addActionListener(paramAnonymousActionEvent -> {
            /* 104 */
            JPServer.spamString( "0704" );
            /* 105 */
            JPServer.computerFontIncrease = 4;
            /* 106 */
        });
        /* 107 */
        this.cpuIncrease6 = new javax.swing.JRadioButtonMenuItem( "6 points" );
        /* 108 */
        this.cpuIncrease6.addActionListener(paramAnonymousActionEvent -> {
            /* 110 */
            JPServer.spamString( "0706" );
            /* 111 */
            JPServer.computerFontIncrease = 6;
            /* 112 */
        });
        /* 113 */
        this.cpuIncrease8 = new javax.swing.JRadioButtonMenuItem( "8 points" );
        /* 114 */
        this.cpuIncrease8.addActionListener(paramAnonymousActionEvent -> {
            /* 116 */
            JPServer.spamString( "0708" );
            /* 117 */
            JPServer.computerFontIncrease = 8;
            /* 118 */
        });
        /* 119 */
        this.cpuIncrease10 = new javax.swing.JRadioButtonMenuItem( "10 points" );
        /* 120 */
        this.cpuIncrease10.addActionListener(paramAnonymousActionEvent -> {
            /* 122 */
            JPServer.spamString( "07010" );
            /* 123 */
            JPServer.computerFontIncrease = 10;
            /* 124 */
        });
        /* 125 */
        this.cpuIncrease12 = new javax.swing.JRadioButtonMenuItem( "12 points" );
        /* 126 */
        this.cpuIncrease12.addActionListener(paramAnonymousActionEvent -> {
            /* 128 */
            JPServer.spamString( "07012" );
            /* 129 */
            JPServer.computerFontIncrease = 12;
            /* 130 */
        });
        /* 131 */
        this.cpuIncrease14 = new javax.swing.JRadioButtonMenuItem( "14 points" );
        /* 132 */
        this.cpuIncrease14.addActionListener(paramAnonymousActionEvent -> {
            /* 134 */
            JPServer.spamString( "07014" );
            /* 135 */
            JPServer.computerFontIncrease = 14;
            /* 136 */
        });
        /* 137 */
        this.cpuIncrease16 = new javax.swing.JRadioButtonMenuItem( "16 points" );
        /* 138 */
        this.cpuIncrease16.addActionListener(paramAnonymousActionEvent -> {
            /* 140 */
            JPServer.spamString( "07016" );
            /* 141 */
            JPServer.computerFontIncrease = 16;
            /* 142 */
        });
        /* 143 */
        this.cpuIncrease18 = new javax.swing.JRadioButtonMenuItem( "18 points" );
        /* 144 */
        this.cpuIncrease18.addActionListener(paramAnonymousActionEvent -> {
            /* 146 */
            JPServer.spamString( "07018" );
            /* 147 */
            JPServer.computerFontIncrease = 18;
            /* 148 */
        });
        /* 149 */
        javax.swing.ButtonGroup localButtonGroup = new javax.swing.ButtonGroup();

        /* 151 */
        localButtonGroup.add( this.cpuIncrease0 );
        /* 152 */
        localButtonGroup.add( this.cpuIncrease2 );
        /* 153 */
        localButtonGroup.add( this.cpuIncrease4 );
        /* 154 */
        localButtonGroup.add( this.cpuIncrease6 );
        /* 155 */
        localButtonGroup.add( this.cpuIncrease8 );
        /* 156 */
        localButtonGroup.add( this.cpuIncrease10 );
        /* 157 */
        localButtonGroup.add( this.cpuIncrease12 );
        /* 158 */
        localButtonGroup.add( this.cpuIncrease14 );
        /* 159 */
        localButtonGroup.add( this.cpuIncrease16 );
        /* 160 */
        localButtonGroup.add( this.cpuIncrease18 );

        /* 162 */
        this.computerFontIncreaseMenu.add( this.cpuIncrease0 );
        /* 163 */
        this.computerFontIncreaseMenu.add( this.cpuIncrease2 );
        /* 164 */
        this.computerFontIncreaseMenu.add( this.cpuIncrease4 );
        /* 165 */
        this.computerFontIncreaseMenu.add( this.cpuIncrease6 );
        /* 166 */
        this.computerFontIncreaseMenu.add( this.cpuIncrease8 );
        /* 167 */
        this.computerFontIncreaseMenu.add( this.cpuIncrease10 );
        /* 168 */
        this.computerFontIncreaseMenu.add( this.cpuIncrease12 );
        /* 169 */
        this.computerFontIncreaseMenu.add( this.cpuIncrease14 );
        /* 170 */
        this.computerFontIncreaseMenu.add( this.cpuIncrease16 );
        /* 171 */
        this.computerFontIncreaseMenu.add( this.cpuIncrease18 );

        /* 173 */
        switch ( JPServer.computerFontIncrease ) {
            /* 174 */
            case 0:
                this.cpuIncrease0.setSelected( true );
                break;
            /* 175 */
            case 2:
                this.cpuIncrease2.setSelected( true );
                break;
            /* 176 */
            case 4:
                this.cpuIncrease4.setSelected( true );
                break;
            /* 177 */
            case 6:
                this.cpuIncrease6.setSelected( true );
                break;
            /* 178 */
            case 8:
                this.cpuIncrease8.setSelected( true );
                break;
            /* 179 */
            case 10:
                this.cpuIncrease10.setSelected( true );
                break;
            /* 180 */
            case 12:
                this.cpuIncrease12.setSelected( true );
                break;
            /* 181 */
            case 14:
                this.cpuIncrease14.setSelected( true );
                break;
            /* 182 */
            case 16:
                this.cpuIncrease16.setSelected( true );
                break;
            /* 183 */
            case 18:
                this.cpuIncrease18.setSelected( true );
                break;
        }




        /* 189 */
        this.titleMessageMenuItem = new javax.swing.JMenuItem( "Set Title Message..." );
        /* 190 */
        this.titleMessageMenuItem.setToolTipText( "<HTML>Displays a message in the title bar<BR>of everyone's main window.</HTML>" );




        /* 195 */
        /* 201 */
        this.titleMessageMenuItem.addActionListener(paramAnonymousActionEvent -> {
            /* 197 */
            new javax.swing.JOptionPane();
            JPServer.titleMessage = (String) javax.swing.JOptionPane.showInputDialog( null, "Enter a new title bar message:", "Set Title Message", -1, null, null, "" );

            /* 199 */
            if ( JPServer.titleMessage != null ) {
                JPServer.setTitleMessage( JPServer.titleMessage );
            }
        });
        /* 202 */
        this.clearTitleMessageMenuItem = new javax.swing.JMenuItem( "Clear Title Message" );
        /* 203 */
        /* 207 */
        this.clearTitleMessageMenuItem.addActionListener(paramAnonymousActionEvent -> {
        });
        /* 208 */
        this.useAnnouncementMenuItem = new javax.swing.JCheckBoxMenuItem( "Use Announcement" );
        /* 209 */
        this.useAnnouncementMenuItem.setSelected( ( (Boolean) JPServer.prefs.getPref( 37 ) ).booleanValue() );

        /* 211 */
        this.setAnnouncementMenuItem = new javax.swing.JMenuItem( "Set Announcement..." );
        /* 212 */
        /* 216 */
        this.setAnnouncementMenuItem.addActionListener(paramAnonymousActionEvent -> {
        });
        /* 217 */
        this.clearAnnouncementMenuItem = new javax.swing.JMenuItem( "Clear Announcement" );
        /* 218 */
        /* 222 */
        this.clearAnnouncementMenuItem.addActionListener(paramAnonymousActionEvent -> {
        });
        /* 223 */
        this.allowGMEmotesMenuItem = new javax.swing.JCheckBoxMenuItem( "Allow GM Emotes" );

        /* 225 */
        this.allowGMEmotesMenuItem.setToolTipText( "<HTML> When disabled, if the you attempt to use speech <BR> or perform an action when you are not spoofing <BR> a character, you will receive a warning and the <BR> text will not be sent.</HTML>" );








        /* 234 */
        this.allowGMEmotesMenuItem.setSelected( JPServer.allowGMEmotes );
        /* 235 */
        /* 240 */
        this.allowGMEmotesMenuItem.addActionListener(paramAnonymousActionEvent -> {
            /* 237 */
            JPServer.allowGMEmotes = !JPServer.allowGMEmotes;
        });
        /* 241 */
        this.singleUseSpoofMenuItem = new javax.swing.JCheckBoxMenuItem( "Single Use Spoofing" );

        /* 243 */
        this.singleUseSpoofMenuItem.setToolTipText( "<HTML> When checked, the Spoof checkbox will <BR>automatically deselect itself when you send a <BR>spoofed line.</HTML>" );





        /* 249 */
        this.singleUseSpoofMenuItem.setSelected( ( (Boolean) JPServer.prefs.getPref( 36 ) ).booleanValue() );
        /* 250 */
        /* 255 */
        this.singleUseSpoofMenuItem.addActionListener(paramAnonymousActionEvent -> {
        });
        /* 256 */
        this.computerFontMenu = new javax.swing.JMenu( "Computer font" );
        /* 257 */
        this.computerFontMenu.add( this.bigComputerFontMenuItem );
        /* 258 */
        this.computerFontMenu.add( this.computerAllCapsMenuItem );
        /* 259 */
        this.computerFontMenu.add( this.computerFontIncreaseMenu );

        /* 261 */
        this.titleMessageMenu = new javax.swing.JMenu( "Title message" );
        /* 262 */
        this.titleMessageMenu.add( this.titleMessageMenuItem );
        /* 263 */
        this.titleMessageMenu.add( this.clearTitleMessageMenuItem );







        /* 271 */
        add( JPServer.soundMenu );
        /* 272 */
        addSeparator();
        /* 273 */
        add( this.autoScrollMenuItem );
        /* 274 */
        add( this.makeLogMenuItem );
        /* 275 */
        add( this.showTimeStampsMenuItem );
        /* 276 */
        add( this.quickCharsheetMenuItem );
        /* 277 */
        addSeparator();
        /* 278 */
        add( this.computerFontMenu );



        /* 282 */
        addSeparator();
        /* 283 */
        add( this.titleMessageMenu );

        /* 285 */
        add( this.useAnnouncementMenuItem );


        /* 288 */
        addSeparator();
        /* 289 */
        add( this.allowGMEmotesMenuItem );
        /* 290 */
        add( this.singleUseSpoofMenuItem );
        /* 291 */
        addSeparator();
        /* 292 */
        add( JPServer.aboutBoxMenuItem );

        /* 294 */
        JPServer.computerAllCaps = this.computerAllCapsMenuItem.isSelected();
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\ServerOptionsMenu.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
