package jparanoia.client;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import static java.awt.Toolkit.getDefaultToolkit;
import static java.lang.invoke.MethodHandles.lookup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import jparanoia.shared.GameRegistrar;
import jparanoia.shared.JPGameInfo;
import static jparanoia.shared.JParanoia.errorMessage;

public class ConnectManager {
    public static JFrame frame = new JFrame( "Connect..." );
    static Dimension dimen = getDefaultToolkit().getScreenSize();
    static int screenWidth = (int) dimen.getWidth();
    static int screenHeight = (int) dimen.getHeight();
    static boolean noGames = false;
    static JLabel manualLabel;
    static JTextField manualEntryField = new JTextField( 15 );
    static JPGameInfo[] games;
    static JPanel contentPane;

    static {
        manualEntryField.setText( JPClient.addressToTry );
        manualEntryField.addActionListener( paramAnonymousActionEvent -> {
            ConnectManager.frame.setVisible( false );
            JPClient.connect( ConnectManager.manualEntryField.getText(), false, ConnectManager.manualEntryField.getText() );
        } );
    }

    public static void activate() {
        games = GameRegistrar.getGames();
        noGames = games == null || games.length == 0;
        contentPane = new JPanel();
        GridBagLayout localGridBagLayout = new GridBagLayout();
        GridBagConstraints localGridBagConstraints = new GridBagConstraints();
        contentPane.setLayout( localGridBagLayout );
        localGridBagConstraints.gridx = 0;
        localGridBagConstraints.gridy = 0;
        localGridBagConstraints.weightx = 0.0D;
        localGridBagConstraints.gridwidth = 1;
        localGridBagConstraints.fill = 1;
        localGridBagConstraints.anchor = 17;
        localGridBagConstraints.insets = new Insets( 2, 2, 2, 2 );
        if ( games != null ) {
            for ( final JPGameInfo game : games ) {
                JLabel localJLabel2 = new JLabel( game.description );
                localGridBagLayout.setConstraints( game.connectButton, localGridBagConstraints );
                contentPane.add( game.connectButton );
                localGridBagConstraints.gridx += 1;
                localGridBagConstraints.gridwidth = 50;
                localGridBagConstraints.weightx = 0.75D;
                localGridBagLayout.setConstraints( localJLabel2, localGridBagConstraints );
                contentPane.add( localJLabel2 );
                localGridBagConstraints.gridy += 1;
                localGridBagConstraints.gridx = 0;
                localGridBagConstraints.weightx = 0.0D;
                localGridBagConstraints.gridwidth = 1;
            }
        }
        if ( noGames ) {
            localGridBagConstraints.gridx += 1;
            manualLabel = new JLabel( " IP Address: " );
            JLabel localJLabel1 = new JLabel( "No Games Found - Contact Your GM for IP Address" );
            localGridBagLayout.setConstraints( localJLabel1, localGridBagConstraints );
            contentPane.add( localJLabel1 );
            localGridBagConstraints.weightx = 0.0D;
            localGridBagConstraints.gridx = 0;
            localGridBagConstraints.gridy += 1;
        } else {
            manualLabel = new JLabel( "or enter IP: " );
        }
        localGridBagLayout.setConstraints( manualLabel, localGridBagConstraints );
        contentPane.add( manualLabel );
        localGridBagConstraints.gridx += 1;
        localGridBagConstraints.gridwidth = 50;
        localGridBagConstraints.weightx = 0.75D;
        localGridBagLayout.setConstraints( manualEntryField, localGridBagConstraints );
        contentPane.add( manualEntryField );
        frame.setContentPane( contentPane );
        try {
            frame.setIconImage( getDefaultToolkit()
                    .getImage( lookup().lookupClass().getClassLoader().getResource( "graphics/jparanoiaIcon.jpg" ) ) );
        } catch ( Exception localException ) {
            errorMessage( "Error getting icon...", "Unable to load icon for ConnectManager frame.\nCheck console." );
            localException.printStackTrace();
        }
        int j = noGames ? 1 : games.length;
        frame.setSize( 400, 24 + 28 * ( 1 + j ) );
        frame.setLocation( screenWidth / 2 - frame.getWidth() / 2, screenHeight / 2 - frame.getHeight() / 2 );
        frame.setVisible( true );
    }
}


/* Location:              C:\Users\noahc\Desktop\JParanoia(1.31.1)\JParanoia(1.31.1).jar!\jparanoia\client\ConnectManager.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */
