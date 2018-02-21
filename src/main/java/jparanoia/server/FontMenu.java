package jparanoia.server;
import javax.swing.JRadioButtonMenuItem;

public class FontMenu extends javax.swing.JMenu {
    javax.swing.JMenu fontFamilyMenu;
    javax.swing.JMenu fontSizeMenu;
    javax.swing.JCheckBoxMenuItem fontBoldMenuItem;
    JRadioButtonMenuItem serifButton;
    JRadioButtonMenuItem sansSerifButton;
    JRadioButtonMenuItem monospacedButton;
    JRadioButtonMenuItem size10Button;
    JRadioButtonMenuItem size12Button;
    JRadioButtonMenuItem size14Button;
    JRadioButtonMenuItem size16Button;
    JRadioButtonMenuItem size18Button;
    JRadioButtonMenuItem size24Button;
    JRadioButtonMenuItem size36Button;
    JRadioButtonMenuItem size48Button;
    JRadioButtonMenuItem size72Button;
    JRadioButtonMenuItem size96Button;
    JRadioButtonMenuItem size120Button;

    public FontMenu( String paramString ) {
        super( paramString );
        this.fontFamilyMenu = new javax.swing.JMenu( "Family" );
        this.serifButton = new JRadioButtonMenuItem( "Serif" );
        this.serifButton.addActionListener( paramAnonymousActionEvent -> {
            JPServer.textAttributes.addAttribute( javax.swing.text.StyleConstants.FontConstants.Family, "Serif" );
        } );
        this.sansSerifButton = new JRadioButtonMenuItem( "Sans-Serif" );
        this.sansSerifButton.addActionListener( paramAnonymousActionEvent -> {
            JPServer.textAttributes.addAttribute( javax.swing.text.StyleConstants.FontConstants.Family, "SansSerif" );
        } );
        this.monospacedButton = new JRadioButtonMenuItem( "Monospaced" );
        this.monospacedButton.addActionListener( paramAnonymousActionEvent -> {
            JPServer.textAttributes.addAttribute( javax.swing.text.StyleConstants.FontConstants.Family, "Monospaced" );
        } );
        javax.swing.ButtonGroup localButtonGroup1 = new javax.swing.ButtonGroup();
        localButtonGroup1.add( this.serifButton );
        localButtonGroup1.add( this.sansSerifButton );
        localButtonGroup1.add( this.monospacedButton );
        this.serifButton.setSelected( JPServer.prefs.getPref( 16 ).equals( "Serif" ) );
        this.sansSerifButton.setSelected( JPServer.prefs.getPref( 16 ).equals( "SansSerif" ) );
        this.monospacedButton.setSelected( JPServer.prefs.getPref( 16 ).equals( "Monospaced" ) );
        this.fontFamilyMenu.add( this.serifButton );
        this.fontFamilyMenu.add( this.sansSerifButton );
        this.fontFamilyMenu.add( this.monospacedButton );
        add( this.fontFamilyMenu );
        this.fontSizeMenu = new javax.swing.JMenu( "Size" );
        this.size10Button = new JRadioButtonMenuItem( "10" );
        this.size10Button.addActionListener( paramAnonymousActionEvent -> {
            JPServer.mainFontSize = new Integer( 10 );
            JPServer.textAttributes.addAttribute( javax.swing.text.StyleConstants.FontConstants.Size, new Integer( 10 ) );
        } );
        this.size12Button = new JRadioButtonMenuItem( "12" );
        this.size12Button.addActionListener( paramAnonymousActionEvent -> {
            JPServer.mainFontSize = new Integer( 12 );
            JPServer.textAttributes.addAttribute( javax.swing.text.StyleConstants.FontConstants.Size, new Integer( 12 ) );
        } );
        this.size14Button = new JRadioButtonMenuItem( "14" );
        this.size14Button.addActionListener( paramAnonymousActionEvent -> {
            JPServer.mainFontSize = new Integer( 14 );
            JPServer.textAttributes.addAttribute( javax.swing.text.StyleConstants.FontConstants.Size, new Integer( 14 ) );
        } );
        this.size16Button = new JRadioButtonMenuItem( "16" );
        this.size16Button.addActionListener( paramAnonymousActionEvent -> {
            JPServer.mainFontSize = new Integer( 16 );
            JPServer.textAttributes.addAttribute( javax.swing.text.StyleConstants.FontConstants.Size, new Integer( 16 ) );
        } );
        this.size18Button = new JRadioButtonMenuItem( "18" );
        this.size18Button.addActionListener( paramAnonymousActionEvent -> {
            JPServer.mainFontSize = new Integer( 18 );
            JPServer.textAttributes.addAttribute( javax.swing.text.StyleConstants.FontConstants.Size, new Integer( 18 ) );
        } );
        this.size24Button = new JRadioButtonMenuItem( "24" );
        this.size24Button.addActionListener( paramAnonymousActionEvent -> {
            JPServer.mainFontSize = new Integer( 24 );
            JPServer.textAttributes.addAttribute( javax.swing.text.StyleConstants.CharacterConstants.Size, new Integer( 24 ) );
        } );
        this.size36Button = new JRadioButtonMenuItem( "36" );
        this.size36Button.addActionListener( paramAnonymousActionEvent -> {
            JPServer.mainFontSize = new Integer( 36 );
            JPServer.textAttributes.addAttribute( javax.swing.text.StyleConstants.CharacterConstants.Size, new Integer( 36 ) );
        } );
        this.size48Button = new JRadioButtonMenuItem( "48" );
        this.size48Button.addActionListener( paramAnonymousActionEvent -> {
            JPServer.mainFontSize = new Integer( 48 );
            JPServer.textAttributes.addAttribute( javax.swing.text.StyleConstants.CharacterConstants.Size, new Integer( 48 ) );
        } );
        this.size72Button = new JRadioButtonMenuItem( "72" );
        this.size72Button.addActionListener( paramAnonymousActionEvent -> {
            JPServer.mainFontSize = new Integer( 72 );
            JPServer.textAttributes.addAttribute( javax.swing.text.StyleConstants.CharacterConstants.Size, new Integer( 72 ) );
        } );
        this.size96Button = new JRadioButtonMenuItem( "96" );
        this.size96Button.addActionListener( paramAnonymousActionEvent -> {
            JPServer.mainFontSize = new Integer( 96 );
            JPServer.textAttributes.addAttribute( javax.swing.text.StyleConstants.CharacterConstants.Size, new Integer( 96 ) );
        } );
        this.size120Button = new JRadioButtonMenuItem( "120" );
        this.size120Button.addActionListener( paramAnonymousActionEvent -> {
            JPServer.mainFontSize = new Integer( 120 );
            JPServer.textAttributes.addAttribute( javax.swing.text.StyleConstants.CharacterConstants.Size, new Integer( 120 ) );
        } );
        javax.swing.ButtonGroup localButtonGroup2 = new javax.swing.ButtonGroup();
        localButtonGroup2.add( this.size10Button );
        localButtonGroup2.add( this.size12Button );
        localButtonGroup2.add( this.size14Button );
        localButtonGroup2.add( this.size16Button );
        localButtonGroup2.add( this.size18Button );
        localButtonGroup2.add( this.size24Button );
        localButtonGroup2.add( this.size36Button );
        localButtonGroup2.add( this.size48Button );
        localButtonGroup2.add( this.size72Button );
        localButtonGroup2.add( this.size96Button );
        localButtonGroup2.add( this.size120Button );
        switch ( ( (Integer) JPServer.prefs.getPref( 15 ) ).intValue() ) {
            case 10:
                this.size10Button.setSelected( true );
                break;
            case 12:
                this.size12Button.setSelected( true );
                break;
            case 14:
                this.size14Button.setSelected( true );
                break;
            case 16:
                this.size16Button.setSelected( true );
                break;
            case 18:
                this.size18Button.setSelected( true );
                break;
            case 24:
                this.size24Button.setSelected( true );
                break;
            case 36:
                this.size36Button.setSelected( true );
                break;
            case 48:
                this.size48Button.setSelected( true );
                break;
            case 72:
                this.size72Button.setSelected( true );
                break;
            case 96:
                this.size96Button.setSelected( true );
                break;
            case 120:
                this.size120Button.setSelected( true );
                break;
            default:
                System.out.println( "ALERT: THE INI FILE IS ABOVE YOUR SECURITY CLEARANCE, CITIZEN!" );
        }
        this.fontSizeMenu.add( this.size10Button );
        this.fontSizeMenu.add( this.size12Button );
        this.fontSizeMenu.add( this.size14Button );
        this.fontSizeMenu.add( this.size16Button );
        this.fontSizeMenu.add( this.size18Button );
        this.fontSizeMenu.add( this.size24Button );
        this.fontSizeMenu.add( this.size36Button );
        this.fontSizeMenu.add( this.size48Button );
        this.fontSizeMenu.add( this.size72Button );
        this.fontSizeMenu.add( this.size96Button );
        this.fontSizeMenu.add( this.size120Button );
        add( this.fontSizeMenu );
        this.fontBoldMenuItem = new javax.swing.JCheckBoxMenuItem( "Bold" );
        this.fontBoldMenuItem.setSelected( JPServer.prefs.getPref( 17 ).equals( new Boolean( true ) ) );
        this.fontBoldMenuItem.addActionListener( paramAnonymousActionEvent -> {
            if ( JPServer.fontIsBold ) {
                JPServer.textAttributes.addAttribute( javax.swing.text.StyleConstants.FontConstants.Bold, Boolean.FALSE );
                JPServer.fontIsBold = false;
            } else if ( !JPServer.fontIsBold ) {
                JPServer.textAttributes.addAttribute( javax.swing.text.StyleConstants.FontConstants.Bold, Boolean.TRUE );
                JPServer.fontIsBold = true;
            }
        } );
        addSeparator();
        add( this.fontBoldMenuItem );
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\FontMenu.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
