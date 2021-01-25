package lk.bloodbank.manage.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.bloodbank.manage.bo.BoFactory;
import lk.bloodbank.manage.bo.custom.StockBO;
import lk.bloodbank.manage.dao.custom.StockDAO;
import lk.bloodbank.manage.dto.HospitalDTO;
import lk.bloodbank.manage.dto.StockDTO;
import lk.bloodbank.manage.view.tm.HospitalTM;
import lk.bloodbank.manage.view.tm.Stock;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StockFormController implements Initializable {
    public TableView tblStock;
    public TableColumn colBloodGroup;
    public TableColumn colWholeBlood;
    public TableColumn colRBC;
    public TableColumn colPlatelets;
    public TableColumn colPlasma;

    StockBO bo= BoFactory.getInstance().getBo(BoFactory.BOType.STOCK);


    public void loadStock() throws Exception {
        tblStock.getItems().clear();
        List<StockDTO> allStocks = bo.loadAllStock();
        ObservableList<Stock> tmList = FXCollections.observableArrayList();

        System.out.println("allstock = " + allStocks);

        for (StockDTO stock: allStocks) {



            Stock tm = new Stock(
                    stock.getBlood(),
                    stock.getWhole(),
                    stock.getRbc(),
                    stock.getPlatelets(),
                    stock.getPlasma());


            tmList.add(tm);


        }
        tblStock.setItems(tmList);


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colBloodGroup.setCellValueFactory(new PropertyValueFactory("blood"));
        colWholeBlood.setCellValueFactory(new PropertyValueFactory("whole"));
        colRBC.setCellValueFactory(new PropertyValueFactory("rbc"));
        colPlatelets.setCellValueFactory(new PropertyValueFactory("platelets"));
        colPlasma.setCellValueFactory(new PropertyValueFactory("plasma"));

        try {
            loadStock();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

