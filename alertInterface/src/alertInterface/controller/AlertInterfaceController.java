package alertInterface.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.json.JSONObject;

import alertInterface.view.AlertInterfacePanel;

public class AlertInterfaceController {
	AlertInterfacePanel panel;

	public AlertInterfaceController(final AlertInterfacePanel panel, final JFrame frame) {
		this.panel = panel;

		panel.getRdbtnIncendio().addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (panel.getRdbtnIncendio().isSelected()) {
					panel.getBtnConferma().setEnabled(false);
					panel.getCmbLuoghi().setSelectedItem(null);
					panel.getCmbLuoghi().setEnabled(true);
				}

			}
		});

		panel.getRdbtnIlluminazione().addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (panel.getRdbtnIlluminazione().isSelected()) {
					panel.getBtnConferma().setEnabled(false);
					panel.getCmbLuoghi().setSelectedItem(null);
					panel.getCmbLuoghi().setEnabled(true);
				}

			}
		});

		panel.getRdbtnTerremoto().addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (panel.getRdbtnTerremoto().isSelected()) {
					panel.getCmbLuoghi().setSelectedItem(null);
					panel.getCmbLuoghi().setEnabled(false);
					panel.getBtnConferma().setEnabled(true);
				}

			}
		});
		
		panel.getCmbLuoghi().addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (panel.getCmbLuoghi().getSelectedItem() != null) {
					panel.getBtnConferma().setEnabled(true);
				}

			}
		});

		panel.getBtnAnnulla().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panel.getGruppo().clearSelection();
				panel.getCmbLuoghi().setEnabled(false);
				panel.getCmbLuoghi().setSelectedItem(null);
				panel.getBtnConferma().setEnabled(false);
			}
		});
		
		panel.getBtnConferma().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String titolo;
				String luogo;
				if(panel.getRdbtnIlluminazione().isSelected()){
					titolo="Illuminazione1";	
				} else if(panel.getRdbtnIncendio().isSelected()){
					titolo="Incendio1";
				} else{
					titolo="Terremoto1";
				}
				if(panel.getCmbLuoghi().getSelectedItem() != null){
					luogo = panel.getCmbLuoghi().getSelectedItem().toString();
				} else{
					luogo="null";
				}
				
				
				try {
					
					URL url = new URL("http://hostiot.ddns.net:8080/IotServer/alert/titolo/" + titolo +"/luogo/" +luogo);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setRequestProperty("Accept", "application/json");

					if (conn.getResponseCode() != 200) {
						
						JOptionPane.showMessageDialog(frame, "Failed : HTTP error code : " + conn.getResponseCode());
				    	panel.getBtnAnnulla().doClick();
					} else{
						 StringBuilder sb = new StringBuilder();
						    String line;
						    
						    BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
						    
						    while ((line = br.readLine()) != null) {
						        sb.append(line);
						    }
						    
						    JSONObject json = new JSONObject(sb.toString());
							
						    if(json.getString("response").equals("true")){
						    	JOptionPane.showMessageDialog(frame, "Alert inviato correttamente.");
						    	panel.getBtnAnnulla().doClick();
							} else{
								JOptionPane.showMessageDialog(frame, "Errore");
						    	panel.getBtnAnnulla().doClick();
							}
					}
				
					conn.disconnect();

				  } catch (Exception ex) {
					  JOptionPane.showMessageDialog(frame, "Failed : HTTP error  : " + ex.getMessage());
				      panel.getBtnAnnulla().doClick();
					  ex.printStackTrace();

				  }
			}
		});
	
	}
}
