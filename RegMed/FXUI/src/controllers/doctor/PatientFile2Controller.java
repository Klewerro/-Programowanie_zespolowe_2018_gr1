package controllers.doctor;


import dto.*;
import helpers.DialogBox;
import htmlParser.ToHtmlDoctor;
import htmlParser.ToHtmlParser;
import htmlParser.ToHtmlPatient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.web.WebView;
import pojo.Doctor;
import pojo.File;
import pojo.Patient;
import pojo.PatientsHistory;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import static controllers.doctor.PatientFile1Controller.getSelectedPatientId;

public class PatientFile2Controller implements Initializable {

    ///////////////

    @FXML
    private TextArea
            recognition;


    ///////////////
    @FXML
    Button
            addTextButton,
            historyButton;



    @FXML
    TabPane
            tabPane;

    @FXML
    WebView
            historyOfPatient;



    private PatientsHistoryDTO patientsHistoryDTO;

    private int doctorId = 12;

    public PatientFile2Controller() {
        this.patientsHistoryDTO = new PatientsHistoryDTO();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        changeTab();
    }

    public void changeTab() {
            tabPane.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
                if (addTextButton.isVisible()) {
                    addTextButton.setVisible(false);
                    historyButton.setVisible(true);
                } else {
                    addTextButton.setVisible(true);
                    historyButton.setVisible(false);
                }
            });
    }

    public void loadHistoryButtonClick(ActionEvent event){
        try{
            historyOfPatient.getEngine().loadContent(showPatientFile(getSelectedPatientId));
        }catch (NullPointerException e){
            DialogBox.warningBox("Warning!", "Please select patient from the table!");
        }
    }

    public void addTextButtonClicked(ActionEvent event) {
        String reco = recognition.getText();
        insertPatientsFile(getSelectedPatientId, reco);
        recognition.clear();
    }


    public void insertPatientsFile(int patientId, String recognition) {
        PatientsHistory historyAdd = new PatientsHistory();
        historyAdd.setPatientId(patientId);
        historyAdd.setDoctorId(doctorId);
        historyAdd.setRecognition(recognition);
        patientsHistoryDTO.add(historyAdd);
    }

    public String showPatientFile(int patientId) {
        PatientAdministrationDTO patientDTO = new PatientAdministrationDTO();
        DoctorAdministrationDTO doctorDTO = new DoctorAdministrationDTO();
        Patient patientData = patientDTO.get(patientId);
        Doctor doctorData = doctorDTO.get(doctorId);
        ToHtmlPatient patient = null;
        ToHtmlDoctor doctor = null;
        ToHtmlParser toHtmlParser = new ToHtmlParser();
        HashMap<String, String> h = new HashMap<>();
        String out = "";
        try {
            patient = new ToHtmlPatient(patientData.getFirstName(),
                    patientData.getLastName(),
                    patientData.getPesel(),
                    patientData.getAddress().getZip(),
                    patientData.getAddress().getCity(),
                    patientData.getAddress().getStreet(),
                    Integer.toString(patientData.getAddress().getNumber()),
                    patientData.getPhoneNumber());

            doctor = new ToHtmlDoctor(doctorData.getFirstName(),
                    doctorData.getLastName());


            FileDTO file = new FileDTO();
            List<File> fileData = file.getFiles(getSelectedPatientId);

            for(int i=0;i<fileData.size();i++){
                h.put(fileData.get(i).getDate(), fileData.get(i).getHistory());
            }

            out = toHtmlParser.patietFile(patient, doctor, h);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            historyOfPatient.getEngine().loadContent("History is empty");
        }
        return out;
    }


}
