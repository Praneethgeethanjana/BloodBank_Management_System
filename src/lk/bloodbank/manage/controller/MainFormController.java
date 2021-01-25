package lk.bloodbank.manage.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.bloodbank.manage.bo.BoFactory;
import lk.bloodbank.manage.bo.custom.DonorBO;
import lk.bloodbank.manage.bo.custom.HospitalBO;
import lk.bloodbank.manage.db.DBConnection;
import lk.bloodbank.manage.dto.DonorDTO;
import lk.bloodbank.manage.dto.HospitalDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {
    public AnchorPane root;
    public ImageView rootImage;
    public Label lblDoner;
    public Label lblHospitals;

    DonorBO donorBO= BoFactory.getInstance().getBo(BoFactory.BOType.DONOR);
    HospitalBO hospitalBO=BoFactory.getInstance().getBo(BoFactory.BOType.HOSPITAL);


    private void setUI(String location) throws IOException {
         // Stage stage = (Stage) root.getScene().getWindow();
        // stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/lk/bloodbank/manage/view/" + location))));
        this.root.getChildren().clear();
        this.root.getChildren().add(FXMLLoader.load(this.getClass().getResource("/lk/bloodbank/manage/view/" + location)));
    }

    public void donorOnClick(MouseEvent mouseEvent) throws IOException {
        setUI("DonorForm.fxml");
    }

    public void hosOnClick(MouseEvent mouseEvent) throws IOException {
        setUI("HospitalForm.fxml");
    }

    public void bloodOnClick(MouseEvent mouseEvent) throws IOException {
        setUI("StockForm.fxml");

    }

    public void orderOnClick(MouseEvent mouseEvent) throws IOException {
        setUI("OrderForm.fxml");

    }

    public void reportOnClick(MouseEvent mouseEvent) {

    }

    public void donateOnClick(MouseEvent mouseEvent) throws IOException {
        setUI("DonateForm.fxml");
    }

    public void homeOnClick(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/lk/bloodbank/manage/view/MainForm.fxml" ))));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            List<DonorDTO> allDonors = donorBO.getAllDonors();
            lblDoner.setText(String.valueOf(allDonors.size()));

            List<HospitalDTO> allHospitals = hospitalBO.getAllHospitals();
            lblHospitals.setText(String.valueOf(allHospitals.size()));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void donorListOnAction(ActionEvent actionEvent){
        try {
            InputStream is=this.getClass().getResourceAsStream("/lk/bloodbank/manage/reports/DonorReport.jrxml");
            JasperReport jr= JasperCompileManager.compileReport(is);
            JasperPrint jp= JasperFillManager.fillReport(jr,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jp,false);


        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void hospitalListOnAction(ActionEvent actionEvent) {
        try {
        InputStream is=this.getClass().getResourceAsStream("/lk/bloodbank/manage/reports/HospitalReport.jrxml");
        JasperReport jr = JasperCompileManager.compileReport(is);
            JasperPrint jp= JasperFillManager.fillReport(jr,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jp,true);


        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void donationListOnAction(ActionEvent actionEvent) {
        try {
        InputStream is=this.getClass().getResourceAsStream("/lk/bloodbank/manage/reports/DonationReport.jrxml");
        JasperReport  jr = JasperCompileManager.compileReport(is);
            JasperPrint jp= JasperFillManager.fillReport(jr,null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jp,true);

        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}

