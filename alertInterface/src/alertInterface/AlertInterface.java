package alertInterface;


import javax.swing.JFrame;
import javax.swing.UIManager;

import alertInterface.controller.AlertInterfaceController;
import alertInterface.view.AlertInterfacePanel;


public class AlertInterface {

	private JFrame frame;

	public static void main(String[] args) {
		// Set cross-platform Java L&F
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		AlertInterface window = new AlertInterface();
		window.frame.setVisible(true);

	}

	public AlertInterface() {
		initialize();
	}

	private void initialize() {

		frame = new JFrame();
		frame.setTitle("AlertInterface");
		frame.setBounds(100, 100, 450, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		AlertInterfacePanel panelAlertInterface = new AlertInterfacePanel();
		frame.getContentPane().add(panelAlertInterface);
		new AlertInterfaceController(panelAlertInterface, frame);

	}

}
