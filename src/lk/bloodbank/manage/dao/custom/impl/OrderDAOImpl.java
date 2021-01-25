package lk.bloodbank.manage.dao.custom.impl;

import lk.bloodbank.manage.dao.CrudUtil;
import lk.bloodbank.manage.dao.custom.OrderDAO;
import lk.bloodbank.manage.entity.Hospital;
import lk.bloodbank.manage.entity.Order;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean save(Order order) throws Exception {
        return CrudUtil.execute("INSERT INTO  Orders VALUES(?,?,?)",
                order.getOid(),
                order.getHid(),
                order.getDate()

        );
    }

    @Override
    public boolean update(Order order) throws Exception {
        return CrudUtil.execute("UPDATE Orders set date=? where OID=?",
                order.getDate(),
                order.getOid());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE from Orders where OID=?",s);
    }

    @Override
    public Order get(String s) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Orders WHERE OID=?", s);
        if(resultSet.next()) {
            return new Order(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            );
        }else {
            return null;
        }
    }

    @Override
    public List<Order> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Orders");
        ArrayList<Order> orders = new ArrayList<>();
        while (resultSet.next()) {
            orders.add(
                    new Order(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3)
                    ));

    }return orders;
}

    @Override
    public int getOrderID() throws Exception {
        ResultSet set=CrudUtil.execute("SELECT OID FROM Orders ORDER BY OID DESC LIMIT 1");
        int id=1;
        if(set.next()){
            int temp=set.getInt(1);
            id=temp+1;
        }
        return id;
    }
}
