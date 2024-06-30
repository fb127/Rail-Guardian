import static org.junit.Assert.*;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class Test {

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

	@org.junit.Test
	public void testLogin_Success() {
	RailGuardianGUI.usernameField = new JTextField("admin");
	RailGuardianGUI.passwordField = new JPasswordField("admin123");
	RailGuardianGUI.login();
	assertTrue(RailGuardianGUI.login);
	}

	@org.junit.Test
	public void testLogin_Failure() {
	RailGuardianGUI.usernameField = new JTextField("user");
	RailGuardianGUI.passwordField = new JPasswordField("password");
	RailGuardianGUI.login();
	assertFalse(RailGuardianGUI.login);
	}

	@org.junit.Test
	public void testSetTrackIssue() {
	RailGuardianGUI.issue = false;
	RailGuardianGUI.issue = true;
	assertTrue(RailGuardianGUI.issue);
	}

	@org.junit.Test
	public void testResolveTrackIssue() {
	RailGuardianGUI.issue = true;
	RailGuardianGUI.issue = false;
	assertFalse(RailGuardianGUI.issue);
	}

	@org.junit.Test
	public void testBarrierStatus_Open() {
	RailGuardianGUI.open_barrier = true;
	JTextArea statusArea = new JTextArea();
	RailGuardianGUI.displaySignal(true, statusArea);
	assertEquals("", statusArea.getText());
	}

	@org.junit.Test
	public void testBarrierStatus_Closed() {
	RailGuardianGUI.open_barrier = false;
	JTextArea statusArea = new JTextArea();
	RailGuardianGUI.displaySignal(false, statusArea);
	assertEquals("", statusArea.getText());
	}
	
	@org.junit.Test
	public void testSetTrainApproaching() {
	SwingUtilities.invokeLater(() -> {
	RailGuardianGUI.train = false;
	RailGuardianGUI.setTrainApproachingFrame();
	});
	}
	
	@org.junit.Test
	public void testCheckTrainApproaching() {
	SwingUtilities.invokeLater(() -> {
	RailGuardianGUI.train = true;
	JTextArea statusArea = new JTextArea(5, 30);
	statusArea.setText(RailGuardianGUI.train ? "Train is approaching.\n" : "Train is not approaching.\n");
	assertEquals("Train is approaching.\n", statusArea.getText());
	});
	}
	
	@org.junit.Test
	public void testControlBarrier() {
	SwingUtilities.invokeLater(() -> {
	RailGuardianGUI.open_barrier = false;
	RailGuardianGUI.createMenuFrame();
	RailGuardianGUI.controlBarrierFrame();
	});
	}
	
	@org.junit.Test
	public void testLogout() {
	SwingUtilities.invokeLater(() -> {
	RailGuardianGUI.login = true;
	RailGuardianGUI.logout();
	assertFalse(RailGuardianGUI.login);
	});
	}
	
	@org.junit.Test
	public void testCheckTrackReport() {
	RailGuardianGUI.issue = false;
	JFrame frame = RailGuardianGUI.createFrame("Check Track Report");
	JTextArea reportArea = new JTextArea();
	frame.getContentPane().add(reportArea);
	reportArea.setText(RailGuardianGUI.issue ? "Track is not clear to go.\n" : "Track is clear to go.\n");

	assertEquals("Track is clear to go.\n", reportArea.getText());
	}

}
