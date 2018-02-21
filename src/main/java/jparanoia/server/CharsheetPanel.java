package jparanoia.server;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class CharsheetPanel extends JPanel {
    static final int IDEAL_WIDTH = 450;
    static final int IDEAL_HEIGHT = 200;
    ServerPlayer selectedPlayer;
    java.io.InputStreamReader reader;
    java.io.FileWriter writer;
    java.io.BufferedReader in;
    JTextPane displayArea;
    JPanel savePanel;
    JPanel playerAndDicePanel;
    JPanel dicePanel;
    JPanel playerPanel;
    JComboBox playerComboBox;
    JButton saveButton;
    javax.swing.JScrollPane scrollPane;
    java.awt.Container contentPane;
    javax.swing.text.DefaultStyledDocument theFile;

    public CharsheetPanel() {
        setLayout( new BoxLayout( this, 1 ) );
        this.displayArea = new JTextPane();
        this.displayArea.setCharacterAttributes( JPServer.charsheetAttributes, true );
        this.displayArea.setEditable( true );
        this.displayArea.setEnabled( true );
        this.scrollPane = new javax.swing.JScrollPane( this.displayArea, 22, 31 );
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
        this.playerPanel.setLayout( new BoxLayout( this.playerPanel, 0 ) );
        this.playerPanel.setMaximumSize( new Dimension( 100, 15 ) );
        this.playerPanel.add( Box.createRigidArea( new Dimension( 5, 0 ) ) );
        this.playerPanel.add( this.playerComboBox );
        this.playerPanel.add( Box.createRigidArea( new Dimension( 5, 0 ) ) );
        this.playerPanel.add( this.saveButton );
        this.playerAndDicePanel = new JPanel();
        this.playerAndDicePanel.setLayout( new java.awt.BorderLayout() );
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
