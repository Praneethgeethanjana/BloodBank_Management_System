package lk.bloodbank.manage.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.bloodbank.manage.bo.BoFactory;
import lk.bloodbank.manage.bo.custom.BloodOrderBO;
import lk.bloodbank.manage.bo.custom.HospitalBO;
import lk.bloodbank.manage.dto.BloodOrderDTO;
import lk.bloodbank.manage.dto.HospitalDTO;
import lk.bloodbank.manage.dto.OrderDTO;
import lk.bloodbank.manage.dto.OrderDetailDTO;
import lk.bloodbank.manage.view.tm.BloodOrderTM;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {


    public TextField txtOID;
    public TextField txthosID;
    public DatePicker Date;
    public TextField txthosName;
    public TableView<BloodOrderTM> tblOrder;
    public JFXComboBox cmbselectBlood;
    public TextField txtRed;
    public TextField txtWhole;
    public TextField txtPlatelets;
    public TextField txtPlasma;
    public TableColumn colBlood;
    public TableColumn colwhoole;
    public TableColumn colRBC;
    public TableColumn colPlatelet;
    public TableColumn colPlasma;
    public TableColumn colOperator;

    HospitalBO hBO= BoFactory.getInstance().getBo(BoFactory.BOType.HOSPITAL);
    BloodOrderBO bloodOrderBO=BoFactory.getInstance().getBo(BoFactory.BOType.BLOODORDERBOIMPL);




    public void orderOnAction(ActionEvent actionEvent) throws Exception {
        ArrayList<BloodOrderDTO> arrayList = new ArrayList<>();
        for (int i = 0; i < tblOrder.getItems().size(); i++) {
            arrayList.add(new BloodOrderDTO(Integer.parseInt(txtOID.getText()),txthosID.getText(),java.time.LocalDate.now().toString(),colBlood.getCellData(i).toString(),colwhoole.getCellData(i).toString(),colRBC.getCellData(i).toString(),colPlatelet.getCellData(i).toString(),colPlasma.getCellData(i).toString()));
        }

        boolean b = bloodOrderBO.saveOrder(arrayList);
        if (b){
            System.out.println("ok");
        }else {
            System.out.println("Fail");
        }





    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colBlood.setCellValueFactory(new PropertyValueFactory("bloodGroup"));
        colwhoole.setCellValueFactory(new PropertyValueFactory("wholeBlood"));
        colRBC.setCellValueFactory(new PropertyValueFactory("redBlood"));
        colPlatelet.setCellValueFactory(new PropertyValueFactory("platelets"));
        colPlasma.setCellValueFactory(new PropertyValueFactory("plasma"));
        colOperator.setCellValueFactory(new PropertyValueFactory("btn"));


        cmbselectBlood.getItems().addAll("A+","A-","B+","B-","o+","o-","AB+","AB-");


    }



    ObservableList<BloodOrderTM> obList = FXCollections.observableArrayList();
    public void btnAddOnAction(ActionEvent actionEvent) {

        Button btn = new Button("Delete");
        String bloodGroup = String.valueOf(cmbselectBlood.getValue());
        String red = txtRed.getText();
        String plasma = txtPlasma.getText();
        String platelets = txtPlatelets.getText();
        String whole = txtWhole.getText();
        obList.addAll(new BloodOrderTM(
                bloodGroup,
                red,
                plasma,
                platelets,
                whole,
                btn
        ));
        tblOrder.setItems(obList);

    }

    public void getHidOnAction(ActionEvent actionEvent) throws Exception {
        List<HospitalDTO> allHospitals=hBO.getAllHospitals();
        for (HospitalDTO allHospital : allHospitals){
            if(txthosID.getText().equals(allHospital.getHid())){
                txthosName.setText(allHospital.getName());
                return;

            }

        }
    }

    public void newOIDAction(ActionEvent actionEvent) {
        try {
            int oid=bloodOrderBO.getOrderID();
        txtOID.setText(String.valueOf(oid));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
