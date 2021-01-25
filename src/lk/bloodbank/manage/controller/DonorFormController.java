package lk.bloodbank.manage.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import lk.bloodbank.manage.bo.BoFactory;
import lk.bloodbank.manage.bo.custom.DonorBO;
import lk.bloodbank.manage.dto.DonorDTO;
import lk.bloodbank.manage.view.tm.DonorTM;
import lk.bloodbank.manage.view.tm.HospitalTM;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class DonorFormController implements Initializable {


    public TextField txtDid;
    public TextField txtName;
    public TextField txtNic;
    public TextField txtAddress;
    public ComboBox cmbBlood;

    public DatePicker dateDOB;
    public TextField txtContact;
    public TextField txtEmail;
    public TableView<DonorTM> tblDonor;
    public JFXButton btnSave;
    public ComboBox cmbGender;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colNIC;
    public TableColumn colAddress;
    public TableColumn colGender;
    public TableColumn colBlood;
    public TableColumn colDOB;
    public TableColumn colPhone;
    public TableColumn colEmail;
    public TableColumn colOperator;
    public JFXTextField txtSearch;

      DonorBO bo = BoFactory.getInstance().getBo(BoFactory.BOType.DONOR);


    private void setData(DonorTM tm) {
        txtDid.setText(tm.getDid());
        txtName.setText(tm.getName());
        txtNic.setText(tm.getNic());
        cmbBlood.setValue(tm.getBlood());
        cmbGender.setValue(tm.getGender());
        txtAddress.setText(tm.getAddress());
        txtContact.setText(tm.getContact());
        txtEmail.setText(tm.getEmail());
        dateDOB.setValue(LocalDate.parse(tm.getDob()));


    }


    public void saveDonorOnAction(ActionEvent actionEvent) throws Exception {

        if (Pattern.compile("^(D)[0-9]{1,}$").matcher(txtDid.getText()).matches()) {
            if (Pattern.compile("^[A-z]{1,}$").matcher(txtName.getText()).matches()) {
                if (Pattern.compile("^[0-9]{1,}(v)$").matcher(txtNic.getText()).matches()) {
                  //  if (Pattern.compile("^[A-z]{1,}$").matcher(dateDOB.getValue().toString() + "").matches()) {
                      //  if (Pattern.compile("^[A-z]{1,}$").matcher(txtAddress.getText()).matches()) {
                            if (Pattern.compile("^[0-9]{10}$").matcher(txtContact.getText()).matches()) {



                                boolean isSaved=bo.saveDonor(new DonorDTO(txtDid.getText(),txtName.getText(),txtNic.getText(),cmbGender.getValue().toString(),dateDOB.getValue().toString(),txtAddress.getText(),cmbBlood.getValue().toString(),txtContact.getText(),txtEmail.getText()));
                                if (isSaved) {
                                    new Alert(Alert.AlertType.CONFIRMATION, "Added Success..!", ButtonType.OK, ButtonType.CANCEL).show();
                                } else {
                                    new Alert(Alert.AlertType.CONFIRMATION, "Added Failed..!", ButtonType.OK, ButtonType.CANCEL).show();
                                }
                                loadAllDonors();







                        } else {
                            txtContact.setStyle("-fx-border-color:red;-fx-border-width:2px;");
                            txtContact.requestFocus();
                        }

                   /* } else {
                        txtAddress.setStyle("-fx-border-color:red;-fx-border-width:2px;");
                        txtAddress.requestFocus();
                    }*/

                /*} else {
                    dateDOB.setStyle("-fx-border-color:red;-fx-border-width:2px;");
                    dateDOB.requestFocus();

                }*/
            } else {
                txtNic.setStyle("-fx-border-color:red;-fx-border-width:2px;");
                txtNic.requestFocus();
            }
        } else {
            txtName.setStyle("-fx-border-color:red;-fx-border-width:2px;");
            txtName.requestFocus();
        }
    }else{

        txtDid.setStyle("-fx-border-color:red;-fx-border-width:2px;");
        txtDid.requestFocus();
    }


}


    public void updateDonorOnAction(ActionEvent actionEvent) throws Exception {
        boolean isUpdate=bo.updateDonor(new DonorDTO(txtDid.getText(),txtName.getText(),txtNic.getText(),cmbGender.getValue().toString(),dateDOB.getValue().toString(),txtAddress.getText(),cmbBlood.getValue().toString(),txtContact.getText(),txtEmail.getText()));


        if(isUpdate) {
            new Alert(Alert.AlertType.CONFIRMATION, "Donor is Updated").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Update Fail");
        }
        loadAllDonors();

    }


    public void getDataOnAction(ActionEvent actionEvent) throws Exception {


    }
    ObservableList<DonorTM>tmList= FXCollections.observableArrayList();

    private void loadAllDonors() throws Exception {
        tblDonor.getItems().clear();

        List<DonorDTO>allDonors=bo.getAllDonors();


        for(DonorDTO donor:allDonors) {
            Button btn = new Button("Delete");

            DonorTM tm = new DonorTM(
                    donor.getDid(),
                    donor.getName(),
                    donor.getNic(),
                    donor.getGender(),
                    donor.getDob(),
                    donor.getAddress(),
                    donor.getBlood(),
                    donor.getContact(),
                    donor.getEmail(), btn);

            tmList.add(tm);

            btn.setOnAction((e) -> {
                try {

                    ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are Yoe Sure", ok, no);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.orElse(no) == ok) {


                        if (bo.deleteDonor(tm.getDid())) {

                            new Alert(Alert.AlertType.CONFIRMATION, "Deleted", ButtonType.OK).show();
                            loadAllDonors();
                            return;
                        }
                    }
                    new Alert(Alert.AlertType.WARNING, "Try Again", ButtonType.OK).show();

                } catch (Exception e1) {
                    e1.printStackTrace();
                }

            }
            );
            }tblDonor.setItems(tmList);




    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbBlood.getItems().addAll("A+","A-","B+","B-","o+","o-","AB+","AB-");
        cmbGender.getItems().addAll("Male","Female");


        colID.setCellValueFactory(new PropertyValueFactory("did"));
        colName.setCellValueFactory(new PropertyValueFactory("name"));
        colNIC.setCellValueFactory(new PropertyValueFactory("nic"));
        colGender.setCellValueFactory(new PropertyValueFactory("gender"));
        colDOB.setCellValueFactory(new PropertyValueFactory("dob"));
        colAddress.setCellValueFactory(new PropertyValueFactory("address"));
        colBlood.setCellValueFactory(new PropertyValueFactory("blood"));
        colPhone.setCellValueFactory(new PropertyValueFactory("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory("email"));
        colOperator.setCellValueFactory(new PropertyValueFactory("btn"));

        try {
            loadAllDonors();
        } catch (Exception e) {
            e.printStackTrace();
        }


        tblDonor.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData( newValue);
                });

    }

    public void onSearchClick(MouseEvent mouseEvent) throws Exception {

    }

    public void searchOnAction(ActionEvent actionEvent) throws Exception {
        DonorDTO donor=bo.getDonor(txtSearch.getText());
        if(donor !=null){
            txtDid.setText(donor.getDid());
            txtName.setText(donor.getName());
            txtNic.setText(donor.getNic());
            cmbGender.setValue(donor.getGender());
            dateDOB.setValue(LocalDate.parse(donor.getDob()));
            txtAddress.setText(donor.getAddress());
            cmbBlood.setValue(donor.getBlood());
            txtContact.setText(donor.getContact());
            txtEmail.setText(donor.getEmail());


        }
    }

    public void btnNewOnAction(ActionEvent actionEvent) {
        try {
            String donorId = bo.getDonorId();
            txtDid.setText(donorId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}

