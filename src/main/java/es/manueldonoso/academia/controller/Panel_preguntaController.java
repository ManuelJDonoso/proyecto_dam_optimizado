/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.manueldonoso.academia.controller;

import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class Panel_preguntaController implements Initializable {

    @FXML
    private VBox root;
    @FXML
    private VBox pregunta;
    @FXML
    private JFXTextArea ta_pregunta;
    @FXML
    private VBox verdadera;
    @FXML
    private JFXTextArea ta_res_v;
    @FXML
    private VBox falsa1;
    @FXML
    private JFXTextArea ta_resF1;
    @FXML
    private VBox falsa2;
    @FXML
    private JFXTextArea ta_resF2;
    @FXML
    private VBox falsa3;
    @FXML
    private JFXTextArea ta_resF3;
    @FXML
    private HBox lb1;
    @FXML
    private Text lb;
    @FXML
    private Text lb10;
    @FXML
    private Text lb2;
    @FXML
    private Text lb3;
    @FXML
    private Text lb4;
    @FXML
    private Text lb5;
    @FXML
    private Text lb6;
    @FXML
    private Text lb7;
    @FXML
    private HBox lb9;
    @FXML
    private Text lb8;
    @FXML
    private VBox panel_principal;
    @FXML
    private HBox panel3;
    @FXML
    private HBox panel454;
    @FXML
    private HBox panel366;
    @FXML
    private Text lb11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   public void cargarpregunta(int numero){
   lb10.setText(numero+"");
   }
}
