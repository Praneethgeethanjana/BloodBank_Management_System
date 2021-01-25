package lk.bloodbank.manage.controller;

import com.jfoenix.controls.JFXTextField;
import com.sun.org.omg.CORBA.Initializer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import lk.bloodbank.manage.bo.BoFactory;
import lk.bloodbank.manage.bo.custom.HospitalBO;
import lk.bloodbank.manage.dto.HospitalDTO;
import lk.bloodbank.manage.entity.Hospital;
import lk.bloodbank.manage.view.tm.HospitalTM;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationException;
import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;

public class HospitalFormController implements Initializable {

    public TextField txtHid;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtPhone;
    public TableView<HospitalTM> tblHos;

    public ComboBox cmbType;

    public TableColumn colHID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colPhone;
    public TableColumn colType;
    public TableColumn colOperater;
    public JFXTextField txtSearchHos;
    public Button btnAdd;


    HospitalBO bo = BoFactory.getInstance().getBo(BoFactory.BOType.HOSPITAL);


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        cmbType.getItems().addAll("Government", "private");


        colHID.setCellValueFactory(new PropertyValueFactory("HoID"));
        colName.setCellValueFactory(new PropertyValueFactory("HoName"));
        colAddress.setCellValueFactory(new PropertyValueFactory("HoAddress"));
        colType.setCellValueFactory(new PropertyValueFactory("HoType"));
        colPhone.setCellValueFactory(new PropertyValueFactory("HoContact"));
        colOperater.setCellValueFactory(new PropertyValueFactory("btn"));




        try {
            loadAllHospitals();
        } catch (Exception e) {
            e.printStackTrace();
        }


        tblHos.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData( newValue);
                });
    }

    private void setData(HospitalTM tm){
        txtHid.setText(tm.getHoID());
       txtName.setText(tm.getHoName());
       txtAddress.setText(tm.getHoAddress());
       cmbType.setValue(tm.getHoType());
     txtPhone.setText(tm.getHoContact());

    }

    private void loadAllHospitals() throws Exception {
        tblHos.getItems().clear();
        List<HospitalDTO> allHospitals = bo.getAllHospitals();
        ObservableList<HospitalTM> tmList = FXCollections.observableArrayList();

        System.out.println("allHospitals = " + allHospitals);

        for (HospitalDTO hospital : allHospitals) {

            Button btn = new Button("Delete");

            HospitalTM tm = new HospitalTM(
                    hospital.getHid(),
                    hospital.getName(),
                    hospital.getAddress(),
                    hospital.getType(),
                    hospital.getContact(), btn);
            tmList.add(tm);


            btn.setOnAction((e) -> {
                        try {
                            ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                            ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are Yoe Sure", ok, no);
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.orElse(no) == ok) {

                                if (bo.deleteHospital(tm.getHoID())) {

                                    new Alert(Alert.AlertType.CONFIRMATION, "Deleted", ButtonType.OK).show();
                                    loadAllHospitals();
                                    return;


                                }
                                new Alert(Alert.AlertType.WARNING, "Try Again", ButtonType.OK).show();
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();

                        }
                    }
            );

        }
        tblHos.setItems(tmList);

    }



    public void addOnAction(ActionEvent actionEvent) throws Exception {


        if(Pattern.compile("^(h00)[1-9]{1,20}$").matcher(txtHid.getText()).matches()){
            if(Pattern.compile("^[A-z]{1,}$").matcher(txtName.getText()).matches()) {
                if(Pattern.compile("^[A-z]{1,}$").matcher(txtAddress.getText()).matches()) {

                        if(Pattern.compile("[0-9]{10}$").matcher(txtPhone.getText()).matches()){

                            boolean isAdded = bo.saveHospital(new HospitalDTO(
                                    txtHid.getText(),
                                    txtName.getText(),
                                    txtAddress.getText(),
                                    cmbType.getValue().toString(),
                                    txtPhone.getText()
                            ));
                            if (isAdded) {
                                new Alert(Alert.AlertType.CONFIRMATION, "Added Success..!", ButtonType.OK, ButtonType.CANCEL).show();
                            } else {
                                new Alert(Alert.AlertType.CONFIRMATION, "Added Failed..!", ButtonType.OK, ButtonType.CANCEL).show();
                            } loadAllHospitals();


                        }else {
                        txtPhone.setStyle("-fx-border-color:red;-fx-border-width:2px;");
                        txtPhone.requestFocus();
                    }

                }else {
                    txtAddress.setStyle("-fx-border-color:red;-fx-border-width:2px;");
                    txtAddress.requestFocus();
                }

            }else {
                txtName.setStyle("-fx-border-color:red;-fx-border-width:2px;");
                txtName.requestFocus();

            }
        }else {
            txtHid.setStyle("-fx-border-color:red;-fx-border-width:2px;");
            txtHid.requestFocus();
        }



    }

    public void updateOnAction(ActionEvent actionEvent) throws Exception {
        bo.updateHospital(new HospitalDTO(
                txtHid.getText(),
                txtName.getText(),
                txtAddress.getText(),
                cmbType.getValue().toString(),
                txtPhone.getText())
        );

            loadAllHospitals();

    }

    public void getDataOnAction(ActionEvent actionEvent) throws Exception {
        HospitalDTO hospital = bo.getHospital(txtHid.getText());
        if (hospital != null) {
            txtName.setText(hospital.getName());
            txtAddress.setText(hospital.getAddress());
            cmbType.setValue(hospital.getType());
            txtPhone.setText(hospital.getContact());
        }
    }

    public void onSearchHos(ActionEvent actionEvent) throws Exception {
        HospitalDTO hospital = bo.getHospital(txtSearchHos.getText());
        if (hospital != null) {
            txtHid.setText(hospital.getHid());
            txtName.setText(hospital.getName());
            txtAddress.setText(hospital.getAddress());
            cmbType.setValue(hospital.getType());
            txtPhone.setText(hospital.getContact());

        }

    }

    public void onSearchHosClick(MouseEvent mouseEvent) {


    }

    public void onNewAction(ActionEvent actionEvent) throws Exception {
        txtSearchHos.clear();
        txtHid.clear();
        txtName.clear();
        txtAddress.clear();
        cmbType.getSelectionModel().clearSelection();
        txtPhone.clear();
        btnAdd.setDisable(false);

        String hID=bo.getHospitalID();
        txtHid.setText(hID);



    }


}
