package jparanoia.server;
import java.lang.invoke.MethodHandles;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.text.StyleConstants;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

public class FontMenu extends JMenu {
    private final static Logger logger = getLogger( MethodHandles.lookup().lookupClass());

    JMenu fontFamilyMenu;
    JMenu fontSizeMenu;
    JCheckBoxMenuItem fontBoldMenuItem;
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
        this.fontFamilyMenu = new JMenu( "Family" );
        this.serifButton = new JRadioButtonMenuItem( "Serif" );
        this.serifButton.addActionListener( paramAnonymousActionEvent -> {
            JPServer.textAttributes.addAttribute( StyleConstants.FontConstants.Family, "Serif" );
        } );
        this.sansSerifButton = new JRadioButtonMenuItem( "Sans-Serif" );
        this.sansSerifButton.addActionListener( paramAnonymousActionEvent -> {
            JPServer.textAttributes.addAttribute( StyleConstants.FontConstants.Family, "SansSerif" );
        } );
        this.monospacedButton = new JRadioButtonMenuItem( "Monospaced" );
        this.monospacedButton.addActionListener( paramAnonymousActionEvent -> {
            JPServer.textAttributes.addAttribute( StyleConstants.FontConstants.Family, "Monospaced" );
        } );
        ButtonGroup localButtonGroup1 = new ButtonGroup();
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
        this.fontSizeMenu = new JMenu( "Size" );
        this.size10Button = new JRadioButtonMenuItem( "10" );
        this.size10Button.addActionListener( paramAnonymousActionEvent -> {
            JPServer.mainFontSize = 10;
            JPServer.textAttributes.addAttribute( StyleConstants.FontConstants.Size, 10 );
        } );
        this.size12Button = new JRadioButtonMenuItem( "12" );
        this.size12Button.addActionListener( paramAnonymousActionEvent -> {
            JPServer.mainFontSize = 12;
            JPServer.textAttributes.addAttribute( StyleConstants.FontConstants.Size, 12 );
        } );
        this.size14Button = new JRadioButtonMenuItem( "14" );
        this.size14Button.addActionListener( paramAnonymousActionEvent -> {
            JPServer.mainFontSize = 14;
            JPServer.textAttributes.addAttribute( StyleConstants.FontConstants.Size, 14 );
        } );
        this.size16Button = new JRadioButtonMenuItem( "16" );
        this.size16Button.addActionListener( paramAnonymousActionEvent -> {
            JPServer.mainFontSize = 16;
            JPServer.textAttributes.addAttribute( StyleConstants.FontConstants.Size, 16 );
        } );
        this.size18Button = new JRadioButtonMenuItem( "18" );
        this.size18Button.addActionListener( paramAnonymousActionEvent -> {
            JPServer.mainFontSize = 18;
            JPServer.textAttributes.addAttribute( StyleConstants.FontConstants.Size, 18 );
        } );
        this.size24Button = new JRadioButtonMenuItem( "24" );
        this.size24Button.addActionListener( paramAnonymousActionEvent -> {
            JPServer.mainFontSize = 24;
            JPServer.textAttributes.addAttribute( StyleConstants.CharacterConstants.Size, 24 );
        } );
        this.size36Button = new JRadioButtonMenuItem( "36" );
        this.size36Button.addActionListener( paramAnonymousActionEvent -> {
            JPServer.mainFontSize = 36;
            JPServer.textAttributes.addAttribute( StyleConstants.CharacterConstants.Size, 36 );
        } );
        this.size48Button = new JRadioButtonMenuItem( "48" );
        this.size48Button.addActionListener( paramAnonymousActionEvent -> {
            JPServer.mainFontSize = 48;
            JPServer.textAttributes.addAttribute( StyleConstants.CharacterConstants.Size, 48 );
        } );
        this.size72Button = new JRadioButtonMenuItem( "72" );
        this.size72Button.addActionListener( paramAnonymousActionEvent -> {
            JPServer.mainFontSize = 72;
            JPServer.textAttributes.addAttribute( StyleConstants.CharacterConstants.Size, 72 );
        } );
        this.size96Button = new JRadioButtonMenuItem( "96" );
        this.size96Button.addActionListener( paramAnonymousActionEvent -> {
            JPServer.mainFontSize = 96;
            JPServer.textAttributes.addAttribute( StyleConstants.CharacterConstants.Size, 96 );
        } );
        this.size120Button = new JRadioButtonMenuItem( "120" );
        this.size120Button.addActionListener( paramAnonymousActionEvent -> {
            JPServer.mainFontSize = 120;
            JPServer.textAttributes.addAttribute( StyleConstants.CharacterConstants.Size, 120 );
        } );
        ButtonGroup localButtonGroup2 = new ButtonGroup();
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
        switch ( (Integer) JPServer.prefs.getPref( 15 ) ) {
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
                logger.info( "ALERT: THE INI FILE IS ABOVE YOUR SECURITY CLEARANCE, CITIZEN!" );
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
        this.fontBoldMenuItem = new JCheckBoxMenuItem( "Bold" );
        this.fontBoldMenuItem.setSelected( JPServer.prefs.getPref( 17 ).equals( Boolean.TRUE ) );
        this.fontBoldMenuItem.addActionListener( paramAnonymousActionEvent -> {
            if ( JPServer.fontIsBold ) {
                JPServer.textAttributes.addAttribute( StyleConstants.FontConstants.Bold, Boolean.FALSE );
                JPServer.fontIsBold = false;
            } else if ( !JPServer.fontIsBold ) {
                JPServer.textAttributes.addAttribute( StyleConstants.FontConstants.Bold, Boolean.TRUE );
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
