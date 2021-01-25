package lk.bloodbank.manage.dao.custom;

import lk.bloodbank.manage.dao.SuperDAO;
import lk.bloodbank.manage.entity.Stock;

public interface QueryDAO extends SuperDAO {
    public boolean updateStock(Stock stock)throws Exception;
}
