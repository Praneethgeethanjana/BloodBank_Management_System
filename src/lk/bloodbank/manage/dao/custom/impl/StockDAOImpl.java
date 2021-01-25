package lk.bloodbank.manage.dao.custom.impl;

import lk.bloodbank.manage.dao.CrudUtil;
import lk.bloodbank.manage.dao.custom.StockDAO;
import lk.bloodbank.manage.entity.Stock;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StockDAOImpl implements StockDAO {
    @Override
    public boolean save(Stock stock) throws Exception {
        return CrudUtil.execute("INSERT INTO Stock VALUES(?,?,?,?,?)",
                stock.getBlood(),
                stock.getWhole(),
                stock.getRbc(),
                stock.getPlatelets(),
                stock.getPlasma());
    }

    @Override
    public boolean update(Stock stock) throws Exception {
        return CrudUtil.execute("UPDATE stock set wholeBlood=?,RBC=?,Platelets=?,Plasma=? where BloodGroup=?",
                stock.getWhole(),
                stock.getRbc(),
                stock.getPlatelets(),
                stock.getPlasma(),
                stock.getBlood()
        );
    }

    @Override
    public boolean delete(String HID) throws Exception {
        return false;
    }

    @Override
    public Stock get(String bloodGroup) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM stock WHERE BloodGroup=?");
        if(rst.next()){
            return new Stock(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
        }
        return null;
    }

    @Override
    public List<Stock> getAll() throws Exception {
        ArrayList<Stock> arraylist = new ArrayList<>();
        ResultSet resultSet= CrudUtil.execute("SELECT * FROM stock");
        while (resultSet.next()){
            arraylist.add(new Stock(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5)));
        }
        return arraylist;
    }



    /*@Override
    public Stock get(String bloodID, String type) throws Exception {
            return CrudUtil.execute("SELECT * FROM stock WHERE BloodGroup=? and ",
                    stock.getWhole(),
                    stock.getRbc(),
                    stock.getPlatelets(),
                    stock.getPlasma(),
                    stock.getBlood()
            );
        }
    }*/
}
