package lk.bloodbank.manage.dao.custom.impl;

import lk.bloodbank.manage.dao.CrudUtil;
import lk.bloodbank.manage.dao.custom.QueryDAO;
import lk.bloodbank.manage.entity.Stock;

public class QueryDaoImpl implements QueryDAO {


    @Override
    public boolean updateStock(Stock stock) throws Exception {
        return CrudUtil.execute("UPDATE stock set wholeBlood=?,RBC=?,Platelets=?,Plasma=? WHERE BloodGroup=?",stock.getWhole(),stock.getRbc(),stock.getPlatelets(),stock.getPlasma(),stock.getBlood());
    }
}
