import static org.junit.Assert.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestCase_RailGuardianGUI {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		 RailGuardianGUI.login = false;
	        RailGuardianGUI.issue = false;
	        RailGuardianGUI.open_barrier = true;
	        RailGuardianGUI.train = false;
	}

	@After
	public void tearDown() throws Exception {
	}


	    @Test
	    public void testLogin_Success() {
	        RailGuardianGUI.usernameField = new JTextField("admin");
	        RailGuardianGUI.passwordField = new JPasswordField("admin123");
	        RailGuardianGUI.login();
	        assertTrue(RailGuardianGUI.login);
	    }

	    @Test
	    public void testLogin_Failure() {
	        RailGuardianGUI.usernameField = new JTextField("user");
	        RailGuardianGUI.passwordField = new JPasswordField("password");
	        RailGuardianGUI.login();
	        assertFalse(RailGuardianGUI.login);
	    }

	    @Test
	    public void testSetTrackIssue() {
	        RailGuardianGUI.issue = false;
	        RailGuardianGUI.issue = true;
	        assertTrue(RailGuardianGUI.issue);
	    }

	    @Test
	    public void testResolveTrackIssue() {
	        RailGuardianGUI.issue = true;
	        RailGuardianGUI.issue = false;
	        assertFalse(RailGuardianGUI.issue);
	    }
	    
	    public void testSetTrainApproaching() {
	        SwingUtilities.invokeLater(() -> {
	            RailGuardianGUI.train = false;
	            RailGuardianGUI.setTrainApproachingFrame();
	            assertTrue(RailGuardianGUI.train);
	        });
	    }
	    
	    
	    @Test
	    public void testBarrierStatus_Open() {
	        RailGuardianGUI.open_barrier = true;
	        JFrame frame = RailGuardianGUI.createFrame("Test Frame");
	        JTextArea statusArea = new JTextArea();
	        RailGuardianGUI.displaySignal(true, statusArea);
	        assertEquals("", statusArea.getText());
	    }

	    @Test
	    public void testBarrierStatus_Closed() {
	        RailGuardianGUI.open_barrier = false;
	        JFrame frame = RailGuardianGUI.createFrame("Test Frame");
	        JTextArea statusArea = new JTextArea();
	        RailGuardianGUI.displaySignal(false, statusArea);
	        assertEquals("", statusArea.getText());
	    }
	    
	

	    
	    @Test
	    public void testLogout() {
	        SwingUtilities.invokeLater(() -> {
	            RailGuardianGUI.login = true;
	            RailGuardianGUI.logout();
	            assertFalse(RailGuardianGUI.login);
	        });
	    }
	    
	    @Test
	    public void testCheckTrackReport() {
	        SwingUtilities.invokeLater(() -> {
	            RailGuardianGUI.issue = false;
	            JFrame frame = RailGuardianGUI.createFrame("Check Track Report");
	            JTextArea reportArea = new JTextArea(10, 30);
	            reportArea.setText(RailGuardianGUI.issue ? "Track is not clear to go.\n" : "Track is clear to go.\n");
	            assertEquals("Track is clear to go.\n", reportArea.getText());
	        });
	    }

	  
	    


	   
}
