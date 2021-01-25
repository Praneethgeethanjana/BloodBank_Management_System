package lk.bloodbank.manage.bo.custom.impl;

import lk.bloodbank.manage.bo.custom.DonateStockBO;
import lk.bloodbank.manage.dao.DaoFactory;
import lk.bloodbank.manage.dao.custom.DonateDAO;
import lk.bloodbank.manage.dao.custom.QueryDAO;
import lk.bloodbank.manage.dao.custom.StockDAO;
import lk.bloodbank.manage.db.DBConnection;
import lk.bloodbank.manage.dto.DonateDTO;
import lk.bloodbank.manage.dto.StockDTO;
import lk.bloodbank.manage.entity.Donate;
import lk.bloodbank.manage.entity.Stock;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


public class DonateStockBOImpl implements DonateStockBO {

    DonateDAO donateDAO = DaoFactory.getInstance().getDao(DaoFactory.DAOType.DONATE);
    StockDAO stockDAO = DaoFactory.getInstance().getDao(DaoFactory.DAOType.STOCK);
    QueryDAO queryDAO = DaoFactory.getInstance().getDao(DaoFactory.DAOType.QUERY);


    @Override
    public boolean update(DonateDTO donateDTO) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {

            Donate donate = new Donate(donateDTO.getDonateID(), donateDTO.getDid(), donateDTO.getName(),
                    donateDTO.getBlood(), donateDTO.getDonation_Type(), donateDTO.getQty(), donateDTO.getDate(), donateDTO.getTime());

            if (donateDAO.save(donate)) {
                System.out.println("in save");

                List<Stock> all = stockDAO.getAll();
                for (Stock stock : all) {
                    if (donateDTO.getDonation_Type().trim().equalsIgnoreCase("RBC")){
                        System.out.println("in rbc1");

                        if (queryDAO.updateStock(new Stock(donateDTO.getBlood(), stock.getWhole(),
                                String.valueOf((Integer.parseInt(stock.getRbc())) + (Integer.parseInt(donateDTO.getQty()))),
                                stock.getPlatelets(), stock.getPlasma()))) {
                            System.out.println("in rbc");
                            System.out.println("qty rbc "+String.valueOf((Integer.parseInt(stock.getRbc())) + (Integer.parseInt(donateDTO.getQty()))));
                            connection.commit();
                            return true;
                        } else {
                            connection.rollback();
                            return false;
                        }
                    } else if (donateDTO.getDonation_Type().trim().equalsIgnoreCase("WholeBlood")) {
                        if (queryDAO.updateStock(new Stock(donateDTO.getBlood(),
                                String.valueOf((Integer.parseInt(stock.getWhole())) + (Integer.parseInt(donateDTO.getQty()))),
                                stock.getRbc(), stock.getPlatelets(), stock.getPlasma()))) {
                            System.out.println("in Whole_Blood");
                            System.out.println("qty blood "+String.valueOf((Integer.parseInt(stock.getWhole())) + (Integer.parseInt(donateDTO.getQty()))));

                            connection.commit();
                            return true;
                        } else {
                            connection.rollback();
                            return false;
                        }
                    } else if (donateDTO.getDonation_Type().equalsIgnoreCase("Platelets")) {
                        if (queryDAO.updateStock(new Stock(donateDTO.getBlood(), stock.getWhole(), stock.getRbc(),
                                String.valueOf((Integer.parseInt(stock.getPlatelets())) + (Integer.parseInt(donateDTO.getQty()))), stock.getPlasma()))) {
                            System.out.println("qty plate "+String.valueOf((Integer.parseInt(stock.getPlatelets())) + (Integer.parseInt(donateDTO.getQty()))));
                            connection.commit();
                            return true;
                        } else {
                            connection.rollback();
                            return false;
                        }
                    } else if (donateDTO.getDonation_Type().equalsIgnoreCase("Plasma")) {
                        if (queryDAO.updateStock(new Stock(donateDTO.getBlood(), stock.getWhole(), stock.getRbc(),
                                stock.getPlatelets(), String.valueOf((Integer.parseInt(stock.getPlasma())) + (Integer.parseInt(donateDTO.getQty())))))) {
                            System.out.println("plasma "+String.valueOf((Integer.parseInt(stock.getPlasma())) + (Integer.parseInt(donateDTO.getQty()))));
                            connection.commit();
                            return true;
                        } else {
                            connection.rollback();
                            return false;
                        }
                    }
                }

            } /*else {
                connection.rollback();
                return false;
            }*/

        } finally {
            connection.setAutoCommit(true);
        }return false;
    }
}

