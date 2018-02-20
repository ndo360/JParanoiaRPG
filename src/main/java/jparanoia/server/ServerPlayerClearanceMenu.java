package jparanoia.server;
import javax.swing.JRadioButtonMenuItem;

public class ServerPlayerClearanceMenu extends javax.swing.JMenu {
    final ServerPlayer player;
    JRadioButtonMenuItem securityInfraMenuItem;
    JRadioButtonMenuItem securityRedMenuItem;
    JRadioButtonMenuItem securityOrangeMenuItem;
    JRadioButtonMenuItem securityYellowMenuItem;
    JRadioButtonMenuItem securityGreenMenuItem;
    JRadioButtonMenuItem securityBlueMenuItem;
    JRadioButtonMenuItem securityIndigoMenuItem;
    JRadioButtonMenuItem securityVioletMenuItem;
    /*  13 */ JRadioButtonMenuItem securityUltraMenuItem;
    javax.swing.ButtonGroup bgroup = new javax.swing.ButtonGroup();

    public ServerPlayerClearanceMenu( ServerPlayer paramServerPlayer ) {
        /*  19 */
        super( "Set clearance" );
        /*  20 */
        this.player = paramServerPlayer;

        /*  22 */
        this.securityInfraMenuItem = new JRadioButtonMenuItem( "IR" );
        /*  23 */
        /*  27 */
        this.securityInfraMenuItem.addActionListener(paramAnonymousActionEvent -> {
            /*  25 */
            ServerPlayerClearanceMenu.this.player.setClearance( "IR", "Infrared" );
        });
        /*  28 */
        add( this.securityInfraMenuItem );
        /*  29 */
        this.bgroup.add( this.securityInfraMenuItem );

        /*  31 */
        this.securityRedMenuItem = new JRadioButtonMenuItem( "R" );
        /*  32 */
        /*  36 */
        this.securityRedMenuItem.addActionListener(paramAnonymousActionEvent -> {
            /*  34 */
            ServerPlayerClearanceMenu.this.player.setClearance( "R", "Red" );
        });
        /*  37 */
        add( this.securityRedMenuItem );
        /*  38 */
        this.bgroup.add( this.securityRedMenuItem );

        /*  40 */
        this.securityOrangeMenuItem = new JRadioButtonMenuItem( "O" );
        /*  41 */
        /*  45 */
        this.securityOrangeMenuItem.addActionListener(paramAnonymousActionEvent -> {
            /*  43 */
            ServerPlayerClearanceMenu.this.player.setClearance( "O", "Orange" );
        });
        /*  46 */
        add( this.securityOrangeMenuItem );
        /*  47 */
        this.bgroup.add( this.securityOrangeMenuItem );

        /*  49 */
        this.securityYellowMenuItem = new JRadioButtonMenuItem( "Y" );
        /*  50 */
        /*  54 */
        this.securityYellowMenuItem.addActionListener(paramAnonymousActionEvent -> {
            /*  52 */
            ServerPlayerClearanceMenu.this.player.setClearance( "Y", "Yellow" );
        });
        /*  55 */
        add( this.securityYellowMenuItem );
        /*  56 */
        this.bgroup.add( this.securityYellowMenuItem );


        /*  59 */
        this.securityGreenMenuItem = new JRadioButtonMenuItem( "G" );
        /*  60 */
        /*  64 */
        this.securityGreenMenuItem.addActionListener(paramAnonymousActionEvent -> {
            /*  62 */
            ServerPlayerClearanceMenu.this.player.setClearance( "G", "Green" );
        });
        /*  65 */
        add( this.securityGreenMenuItem );
        /*  66 */
        this.bgroup.add( this.securityGreenMenuItem );


        /*  69 */
        this.securityBlueMenuItem = new JRadioButtonMenuItem( "B" );
        /*  70 */
        /*  74 */
        this.securityBlueMenuItem.addActionListener(paramAnonymousActionEvent -> {
            /*  72 */
            ServerPlayerClearanceMenu.this.player.setClearance( "B", "Blue" );
        });
        /*  75 */
        add( this.securityBlueMenuItem );
        /*  76 */
        this.bgroup.add( this.securityBlueMenuItem );


        /*  79 */
        this.securityIndigoMenuItem = new JRadioButtonMenuItem( "I" );
        /*  80 */
        /*  84 */
        this.securityIndigoMenuItem.addActionListener(paramAnonymousActionEvent -> {
            /*  82 */
            ServerPlayerClearanceMenu.this.player.setClearance( "I", "Indigo" );
        });
        /*  85 */
        add( this.securityIndigoMenuItem );
        /*  86 */
        this.bgroup.add( this.securityIndigoMenuItem );


        /*  89 */
        this.securityVioletMenuItem = new JRadioButtonMenuItem( "V" );
        /*  90 */
        /*  94 */
        this.securityVioletMenuItem.addActionListener(paramAnonymousActionEvent -> {
            /*  92 */
            ServerPlayerClearanceMenu.this.player.setClearance( "V", "Violet" );
        });
        /*  95 */
        add( this.securityVioletMenuItem );
        /*  96 */
        this.bgroup.add( this.securityVioletMenuItem );


        /*  99 */
        this.securityUltraMenuItem = new JRadioButtonMenuItem( "U" );
        /* 100 */
        /* 104 */
        this.securityUltraMenuItem.addActionListener(paramAnonymousActionEvent -> {
            /* 102 */
            ServerPlayerClearanceMenu.this.player.setClearance( "U", "Ultraviolet" );
        });
        /* 105 */
        add( this.securityUltraMenuItem );
        /* 106 */
        this.bgroup.add( this.securityUltraMenuItem );


        /* 109 */
        switch ( this.player.getClearanceInt() ) {
            case 0:
                /* 111 */
                this.securityInfraMenuItem.setSelected( true );
                break;
            /* 112 */
            case 1:
                this.securityRedMenuItem.setSelected( true );
                break;
            /* 113 */
            case 2:
                this.securityOrangeMenuItem.setSelected( true );
                break;
            /* 114 */
            case 3:
                this.securityYellowMenuItem.setSelected( true );
                break;
            /* 115 */
            case 4:
                this.securityGreenMenuItem.setSelected( true );
                break;
            /* 116 */
            case 5:
                this.securityBlueMenuItem.setSelected( true );
                break;
            /* 117 */
            case 6:
                this.securityIndigoMenuItem.setSelected( true );
                break;
            /* 118 */
            case 7:
                this.securityVioletMenuItem.setSelected( true );
                break;
            /* 119 */
            case 8:
                this.securityUltraMenuItem.setSelected( true );
        }
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\ServerPlayerClearanceMenu.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
