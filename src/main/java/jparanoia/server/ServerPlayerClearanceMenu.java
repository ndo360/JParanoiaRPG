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
     JRadioButtonMenuItem securityUltraMenuItem;
    javax.swing.ButtonGroup bgroup = new javax.swing.ButtonGroup();

    public ServerPlayerClearanceMenu( ServerPlayer paramServerPlayer ) {

        super( "Set clearance" );

        this.player = paramServerPlayer;


        this.securityInfraMenuItem = new JRadioButtonMenuItem( "IR" );


        this.securityInfraMenuItem.addActionListener(paramAnonymousActionEvent -> {

            ServerPlayerClearanceMenu.this.player.setClearance( "IR", "Infrared" );
        });

        add( this.securityInfraMenuItem );

        this.bgroup.add( this.securityInfraMenuItem );


        this.securityRedMenuItem = new JRadioButtonMenuItem( "R" );


        this.securityRedMenuItem.addActionListener(paramAnonymousActionEvent -> {

            ServerPlayerClearanceMenu.this.player.setClearance( "R", "Red" );
        });

        add( this.securityRedMenuItem );

        this.bgroup.add( this.securityRedMenuItem );


        this.securityOrangeMenuItem = new JRadioButtonMenuItem( "O" );


        this.securityOrangeMenuItem.addActionListener(paramAnonymousActionEvent -> {

            ServerPlayerClearanceMenu.this.player.setClearance( "O", "Orange" );
        });

        add( this.securityOrangeMenuItem );

        this.bgroup.add( this.securityOrangeMenuItem );


        this.securityYellowMenuItem = new JRadioButtonMenuItem( "Y" );


        this.securityYellowMenuItem.addActionListener(paramAnonymousActionEvent -> {

            ServerPlayerClearanceMenu.this.player.setClearance( "Y", "Yellow" );
        });

        add( this.securityYellowMenuItem );

        this.bgroup.add( this.securityYellowMenuItem );



        this.securityGreenMenuItem = new JRadioButtonMenuItem( "G" );


        this.securityGreenMenuItem.addActionListener(paramAnonymousActionEvent -> {

            ServerPlayerClearanceMenu.this.player.setClearance( "G", "Green" );
        });

        add( this.securityGreenMenuItem );

        this.bgroup.add( this.securityGreenMenuItem );



        this.securityBlueMenuItem = new JRadioButtonMenuItem( "B" );


        this.securityBlueMenuItem.addActionListener(paramAnonymousActionEvent -> {

            ServerPlayerClearanceMenu.this.player.setClearance( "B", "Blue" );
        });

        add( this.securityBlueMenuItem );

        this.bgroup.add( this.securityBlueMenuItem );



        this.securityIndigoMenuItem = new JRadioButtonMenuItem( "I" );


        this.securityIndigoMenuItem.addActionListener(paramAnonymousActionEvent -> {

            ServerPlayerClearanceMenu.this.player.setClearance( "I", "Indigo" );
        });

        add( this.securityIndigoMenuItem );

        this.bgroup.add( this.securityIndigoMenuItem );



        this.securityVioletMenuItem = new JRadioButtonMenuItem( "V" );


        this.securityVioletMenuItem.addActionListener(paramAnonymousActionEvent -> {

            ServerPlayerClearanceMenu.this.player.setClearance( "V", "Violet" );
        });

        add( this.securityVioletMenuItem );

        this.bgroup.add( this.securityVioletMenuItem );



        this.securityUltraMenuItem = new JRadioButtonMenuItem( "U" );


        this.securityUltraMenuItem.addActionListener(paramAnonymousActionEvent -> {

            ServerPlayerClearanceMenu.this.player.setClearance( "U", "Ultraviolet" );
        });

        add( this.securityUltraMenuItem );

        this.bgroup.add( this.securityUltraMenuItem );



        switch ( this.player.getClearanceInt() ) {
            case 0:

                this.securityInfraMenuItem.setSelected( true );
                break;

            case 1:
                this.securityRedMenuItem.setSelected( true );
                break;

            case 2:
                this.securityOrangeMenuItem.setSelected( true );
                break;

            case 3:
                this.securityYellowMenuItem.setSelected( true );
                break;

            case 4:
                this.securityGreenMenuItem.setSelected( true );
                break;

            case 5:
                this.securityBlueMenuItem.setSelected( true );
                break;

            case 6:
                this.securityIndigoMenuItem.setSelected( true );
                break;

            case 7:
                this.securityVioletMenuItem.setSelected( true );
                break;

            case 8:
                this.securityUltraMenuItem.setSelected( true );
        }
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\ServerPlayerClearanceMenu.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
