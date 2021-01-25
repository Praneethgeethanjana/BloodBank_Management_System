package lk.bloodbank.manage.dao.custom;

import lk.bloodbank.manage.dao.CrudDAO;
import lk.bloodbank.manage.entity.Order;
import rex.utils.S;

public interface OrderDAO extends CrudDAO<Order,String> {
    public int getOrderID() throws  Exception;

}
