package lk.bloodbank.manage.controller;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import lk.bloodbank.manage.bo.BoFactory;
import lk.bloodbank.manage.bo.custom.*;
import lk.bloodbank.manage.dao.CrudUtil;
import lk.bloodbank.manage.db.DBConnection;
import lk.bloodbank.manage.dto.DonateDTO;
import lk.bloodbank.manage.dto.DonorDTO;
import lk.bloodbank.manage.dto.StockDTO;
import lk.bloodbank.manage.view.tm.DonateTM;
import lk.bloodbank.manage.view.tm.DonorTM;

import javax.swing.table.TableModel;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class DonateFormController implements Initializable {
    public TextField txtDonateID;

    public TextField txtBlood;
    public DatePicker Date;
    public TextField txtQty;
    public TextField txtName;
    public JFXTimePicker Time;

    public TableView<DonateTM> tblDonate;
    public ComboBox cmbID;
    public ComboBox cmdDonateType;
    public TableColumn colDonateID;
    public TableColumn colDonorID;
    public TableColumn colName;
    public TableColumn colBlood;
    public TableColumn colType;
    public TableColumn colQty;
    public TableColumn colDate;
    public TableColumn colTime;
    public TableColumn colOperator;
    public Label lblDonateID;
    public Label lblDonate;
    public JFXTextField txtSearchDonateID;
    public Button btnAdd;

    DonateStockBO stockBO = BoFactory.getInstance().getBo(BoFactory.BOType.DONATESTOCK);
    DonorBO dBO = BoFactory.getInstance().getBo(BoFactory.BOType.DONOR);
    DonateBO bo = BoFactory.getInstance().getBo(BoFactory.BOType.DONATE);
    StockBO stock = BoFactory.getInstance().getBo(BoFactory.BOType.STOCK);

    ObservableList<String> observableList = FXCollections.observableArrayList();
    ObservableList<TableModel> obtable = FXCollections.observableArrayList();

    private void setData(DonateTM donateTM){
        txtDonateID.setText(String.valueOf(donateTM.getDonateID()));
        cmbID.setValue(donateTM.getDid());
        txtName.setText(donateTM.getName());
        cmdDonateType.setValue(donateTM.getDonation_Type());
        txtBlood.setText(donateTM.getBlood());
        txtQty.setText(donateTM.getQty());
        Date.setValue(LocalDate.parse(donateTM.getDate()));
        Time.setValue(LocalTime.parse(donateTM.getTime()));


    }


    public void getDonorID() throws Exception {
        try {

            List<DonorDTO> list = dBO.getAllDonors();

            for (DonorDTO s : list) {
                cmbID.getItems().addAll(s.getDid());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cmdDonateType.getItems().addAll("WholeBlood", "RBC", "Platelets", "Plasma");

        colDonateID.setCellValueFactory(new PropertyValueFactory<>("donateID"));
        colDonorID.setCellValueFactory(new PropertyValueFactory<>("did"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colBlood.setCellValueFactory(new PropertyValueFactory<>("blood"));
        colType.setCellValueFactory(new PropertyValueFactory<>("donation_Type"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colOperator.setCellValueFactory(new PropertyValueFactory<>("btn"));


        tblDonate.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData( newValue);
                });
        try {
            getDonorID();
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            loadAllDonates();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void addOnAction(ActionEvent actionEvent) throws Exception {

        if (Pattern.compile("^[A-z]{1,}$").matcher(txtName.getText()).matches()) {
            if (Pattern.compile("^[0-9]{1,}$").matcher(txtQty.getText()).matches()) {

                boolean isAdded = stockBO.update(new DonateDTO(Integer.parseInt(txtDonateID.getText()), cmbID.getValue().toString(), txtName.getText(), txtBlood.getText(), cmdDonateType.getValue().toString(), txtQty.getText(), Date.getValue().toString(), Time.getValue().toString()));

                if (isAdded) {

                        new Alert(Alert.AlertType.CONFIRMATION, "Added Success..!. Blood Stock Updated", ButtonType.OK, ButtonType.CANCEL).show();
                    } else {
                        new Alert(Alert.AlertType.CONFIRMATION, "Added Failed..!", ButtonType.OK, ButtonType.CANCEL).show();
                    }
                    loadAllDonates();



            } else {
                txtQty.setStyle("-fx-border-color:red;-fx-border-width:2px;");
                txtQty.requestFocus();
            }

        } else {
            txtName.setStyle("-fx-border-color:red;-fx-border-width:2px;");
            txtName.requestFocus();
        }
    }



    public void updateOnAction(ActionEvent actionEvent) {


    }

    public void getDonorOnAction(ActionEvent actionEvent) throws Exception {
        List<DonorDTO> allDonors = dBO.getAllDonors();
        for (DonorDTO allDonor : allDonors) {
            if (cmbID.getValue().equals(allDonor.getDid())) {
                txtName.setText(allDonor.getName());
                txtBlood.setText(allDonor.getBlood());
                return;
            }
        }

    }

    public void getDonations(ActionEvent actionEvent) throws Exception {
        DonateDTO donate = bo.getDonate(txtSearchDonateID.getText());
        if (donate != null) {
            txtDonateID.setText(String.valueOf(donate.getDonateID()));
            cmbID.setValue(donate.getDid());
            txtName.setText(donate.getName());
            txtBlood.setText(donate.getBlood());
            cmdDonateType.setValue(donate.getDonation_Type());
            txtQty.setText(donate.getQty());
            Date.setValue(LocalDate.parse(donate.getDate()));
            Time.setValue(LocalTime.parse(donate.getTime()));

        }

    }

    public void loadAllDonates() throws Exception {
        tblDonate.getItems().clear();

        List<DonateDTO> allDonates = bo.getAllDonates();
        ObservableList<DonateTM> tmList = FXCollections.observableArrayList();

        for (DonateDTO donate : allDonates) {

            Button btn = new Button("Delete");
            System.out.println(donate.getBlood() + "dto");
            DonateTM tm = new DonateTM(donate.getDonateID(), donate.getDid(), donate.getName(), donate.getBlood(), donate.getDonation_Type(), donate.getQty(), donate.getDate(), donate.getTime(), btn);
            tmList.add(tm);
            System.out.println(tm.getBlood() + "bfbe");
            btn.setOnAction((e) -> {
                        try {
                            ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                            ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);

                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure", ok, no);
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.orElse(no) == ok) {
                                //if (bo.deleteDonate(tm.getDid())) {
                                if(bo.deleteDonate(String.valueOf(tm.getDonateID()))){

                                    new Alert(Alert.AlertType.CONFIRMATION, "Deleted", ButtonType.OK).show();
                                    loadAllDonates();
                                    return;
                                }
                            }
                            new Alert(Alert.AlertType.WARNING, "Try Again", ButtonType.OK).show();

                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }

                    }
            );
        }
        tblDonate.setItems(tmList);
    }

    public void getNewDonation(ActionEvent actionEvent) throws Exception {
        cmbID.getSelectionModel().clearSelection();
        txtName.clear();
        txtBlood.clear();
        txtQty.clear();
        cmdDonateType.getSelectionModel().clearSelection();
        Date.setValue(null);
        Time.setValue(null);
        btnAdd.setDisable(false);


        int donateID=bo.getDonationID();
        txtDonateID.setText(String.valueOf(donateID));

    }
}

