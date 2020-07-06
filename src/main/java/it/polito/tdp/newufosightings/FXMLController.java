package it.polito.tdp.newufosightings;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.newufosightings.model.Model;
import it.polito.tdp.newufosightings.model.Peso;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

//controller turno A --> switchare al branch master_turnoB per turno B

public class FXMLController {
	
	private Model model;
	private Integer anno;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtResult;

    @FXML
    private TextField txtAnno;

    @FXML
    private Button btnSelezionaAnno;

    @FXML
    private ComboBox<String> cmbBoxForma;

    @FXML
    private Button btnCreaGrafo;

    @FXML
    private TextField txtT1;

    @FXML
    private TextField txtAlfa;

    @FXML
    private Button btnSimula;

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	String forma = this.cmbBoxForma.getValue();
    	if(forma==null) {
        	txtResult.appendText("Selezionare una forma!\n");
        	return;
    	}
    	this.model.creaGrafo(anno,forma);
    	txtResult.appendText("Hai scelto la forma: "+forma+"\n");
    	txtResult.appendText("Grafo creato con successo!\n");
    	txtResult.appendText("# vertici: " +this.model.nVertici()+"\n");
    	txtResult.appendText("# Archi: " +this.model.nArchi()+"\n");
    	List<Peso> elenco = this.model.stampaSommaPesi();
    	txtResult.appendText("\nElenco stati con somma pesi:\n");
    	for(Peso p : elenco) {
    		txtResult.appendText(p.toString()+"\n");
    	}
    	this.cmbBoxForma.getItems().clear();
    	this.btnCreaGrafo.setDisable(true);
    }

    @FXML
    void doSelezionaAnno(ActionEvent event) {
    	txtResult.clear();
    	String a = txtAnno.getText();
    	try {
    		anno = Integer.parseInt(a);
    		if(anno<1910 || anno>2014) {
    			txtResult.appendText("L'anno deve essere compreso tra 1910 e 2014.\n");
    			return;
    		}
    		this.cmbBoxForma.getItems().addAll(this.model.getForme(anno));
        	this.btnCreaGrafo.setDisable(false);
    	}catch(NumberFormatException e) {
    		txtResult.appendText("Inserire un numero intero compreso tra 1910 e 2014.\n");
    		return;
    	}
    }

    @FXML
    void doSimula(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";
        assert btnSelezionaAnno != null : "fx:id=\"btnSelezionaAnno\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";
        assert cmbBoxForma != null : "fx:id=\"cmbBoxForma\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";
        assert txtT1 != null : "fx:id=\"txtT1\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";
        assert txtAlfa != null : "fx:id=\"txtAlfa\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		this.btnCreaGrafo.setDisable(true);
	}
}
