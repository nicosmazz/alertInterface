package alertInterface.view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import alertInterface.AlertInterface;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.JComboBox;


public class AlertInterfacePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7730647679806799533L;
	private JButton btnConferma;
	private JButton btnAnnulla;
	private JLabel lblNewLabel;
	private JRadioButton rdbtnIncendio;
	private JRadioButton rdbtnTerremoto;
	private JRadioButton rdbtnIlluminazione;
	private JPanel panel;
	private JComboBox<String> cmbLuoghi;
	private JLabel lblselezionareInLuogo;
	private ButtonGroup gruppo;

	public AlertInterfacePanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelTitolo = new JPanel();
		add(panelTitolo, BorderLayout.NORTH);
		
		JLabel lblGeneratoreDiAlert = new JLabel("Generatore di alert");
		panelTitolo.add(lblGeneratoreDiAlert);
		
		JPanel panelPulsanti = new JPanel();
		add(panelPulsanti, BorderLayout.SOUTH);
		
		btnConferma = new JButton("Conferma");
		btnConferma.setEnabled(false);
		panelPulsanti.add(btnConferma);
		
		btnAnnulla = new JButton("Annulla");
		panelPulsanti.add(btnAnnulla);
		
		JPanel panelOpzioni = new JPanel();
		add(panelOpzioni, BorderLayout.CENTER);
		panelOpzioni.setLayout(new BoxLayout(panelOpzioni, BoxLayout.Y_AXIS));
		
		panel = new JPanel();
		panelOpzioni.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panelOptButton = new JPanel();
		panel.add(panelOptButton);
		panelOptButton.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		rdbtnIncendio = new JRadioButton("Incendio");
		panelOptButton.add(rdbtnIncendio);
		
		rdbtnTerremoto = new JRadioButton("Terremoto");
		panelOptButton.add(rdbtnTerremoto);
		
		rdbtnIlluminazione = new JRadioButton("Illuminazione");
		panelOptButton.add(rdbtnIlluminazione);
		
		gruppo = new ButtonGroup ();
		gruppo.add(rdbtnIncendio);
		gruppo.add(rdbtnTerremoto);
		gruppo.add(rdbtnIlluminazione);
		
		 
		JPanel panelDescrizione = new JPanel();
		panel.add(panelDescrizione);
		panelDescrizione.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		lblNewLabel = new JLabel("Luogo del pericolo");
		panelDescrizione.add(lblNewLabel);
		
		cmbLuoghi = new JComboBox<String>();
		cmbLuoghi.setEnabled(false);
		cmbLuoghi.addItem(null);
		
		//String csvFile = "fileConfig/nodi.csv";
        String line = "";
        String cvsSplitBy = ",";

        try {
        	URL url = AlertInterface.class.getResource("/nodi.csv");
        	BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            br.readLine();
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] nodi = line.split(cvsSplitBy);
                cmbLuoghi.addItem(nodi[0]);   
            }
            
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
		
        panelDescrizione.add(cmbLuoghi);
        
        lblselezionareInLuogo = new JLabel("(Selezionare il luogo in caso di incendio o problema di illuminazione)");
        panelDescrizione.add(lblselezionareInLuogo);
	}

	public JComboBox<String> getCmbLuoghi() {
		return cmbLuoghi;
	}

	public JButton getBtnConferma() {
		return btnConferma;
	}

	public JButton getBtnAnnulla() {
		return btnAnnulla;
	}

	public JLabel getLblNewLabel() {
		return lblNewLabel;
	}

	public JRadioButton getRdbtnIncendio() {
		return rdbtnIncendio;
	}

	public JRadioButton getRdbtnTerremoto() {
		return rdbtnTerremoto;
	}

	public JRadioButton getRdbtnIlluminazione() {
		return rdbtnIlluminazione;
	}

	public JPanel getPanel() {
		return panel;
	}

	public ButtonGroup getGruppo() {
		return gruppo;
	}
	
}
