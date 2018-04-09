package jparanoia.server;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultStyledDocument;

public class CharsheetPanel extends JPanel {
    static final int IDEAL_WIDTH = 450;
    static final int IDEAL_HEIGHT = 200;
    ServerPlayer selectedPlayer;
    InputStreamReader reader;
    FileWriter writer;
    BufferedReader in;
    JTextPane displayArea;
    JPanel savePanel;
    JPanel playerAndDicePanel;
    JPanel dicePanel;
    JPanel playerPanel;
    JComboBox playerComboBox;
    JButton saveButton;
    JScrollPane scrollPane;
    Container contentPane;
    DefaultStyledDocument theFile;

    public CharsheetPanel() {
        setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );
        this.displayArea = new JTextPane();
        this.displayArea.setCharacterAttributes( JPServer.charsheetAttributes, true );
        this.displayArea.setEditable( true );
        this.displayArea.setEnabled( true );
        this.scrollPane = new JScrollPane( this.displayArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
        this.saveButton = new JButton( "Save / Send" );
        this.saveButton.setMaximumSize( new Dimension( 65, 22 ) );
        this.saveButton.addActionListener( paramAnonymousActionEvent -> {
            CharsheetPanel.this.selectedPlayer.saveCharsheet( CharsheetPanel.this.selectedPlayer.isLoggedIn() );
        } );
        this.playerComboBox = new JComboBox( JPServer.troubleshooters );
        this.playerComboBox.addActionListener( paramAnonymousActionEvent -> {
            CharsheetPanel.this.selectedPlayer = (ServerPlayer) CharsheetPanel.this.playerComboBox.getSelectedItem();
            CharsheetPanel.this.theFile = CharsheetPanel.this.selectedPlayer.getCharsheet();
            CharsheetPanel.this.displayArea.setDocument( CharsheetPanel.this.theFile );
        } );
        this.selectedPlayer = (ServerPlayer) this.playerComboBox.getSelectedItem();
        this.theFile = this.selectedPlayer.getCharsheet();
        this.displayArea.setDocument( this.theFile );
        this.dicePanel = new DicePanel();
        this.playerPanel = new JPanel();
        this.playerPanel.setLayout( new BoxLayout( this.playerPanel, BoxLayout.X_AXIS ) );
        this.playerPanel.setMaximumSize( new Dimension( 100, 15 ) );
        this.playerPanel.add( Box.createRigidArea( new Dimension( 5, 0 ) ) );
        this.playerPanel.add( this.playerComboBox );
        this.playerPanel.add( Box.createRigidArea( new Dimension( 5, 0 ) ) );
        this.playerPanel.add( this.saveButton );
        this.playerAndDicePanel = new JPanel();
        this.playerAndDicePanel.setLayout( new BorderLayout() );
        this.playerAndDicePanel.add( this.playerPanel, "West" );
        this.playerAndDicePanel.add( this.dicePanel, "East" );
        this.playerAndDicePanel.setMaximumSize( new Dimension( 1500, 20 ) );
        this.playerAndDicePanel.setMinimumSize( new Dimension( 200, 20 ) );
        this.playerAndDicePanel.setPreferredSize( new Dimension( 1500, 20 ) );
        add( Box.createRigidArea( new Dimension( 0, 5 ) ) );
        add( this.playerAndDicePanel );
        add( Box.createRigidArea( new Dimension( 0, 5 ) ) );
        add( this.scrollPane );
        setPreferredSize( new Dimension( 9200, 9200 ) );
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\server\CharsheetPanel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
