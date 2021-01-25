package lk.bloodbank.manage.dao.custom.impl;

import lk.bloodbank.manage.dao.CrudUtil;
import lk.bloodbank.manage.dao.custom.OrderDetailDAO;
import lk.bloodbank.manage.entity.Hospital;
import lk.bloodbank.manage.entity.OrderDetail;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public boolean save(OrderDetail orderDetail) throws Exception {
        return CrudUtil.execute("INSERT INTO  OrderDetail VALUES(?,?,?,?,?,?)",
                orderDetail.getOid(),
                orderDetail.getBlood(),
                orderDetail.getWhole_qty(),
                orderDetail.getRbc_qty(),
                orderDetail.getPlatelets_qty(),
                orderDetail.getPlasma_qty()

        );
    }

    @Override
    public boolean update(OrderDetail orderDetail) throws Exception {
        return CrudUtil.execute("UPDATE OrderDetail set BloodGroup=?,wholeBlood_qty=?,rbc_qty=?,platelets_qty=?,plasma_qty=? where OID=?",
                orderDetail.getBlood(),
                orderDetail.getWhole_qty(),
                orderDetail.getRbc_qty(),
                orderDetail.getPlatelets_qty(),
                orderDetail.getPlasma_qty(),
                orderDetail.getOid());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM OrderDetail WHERE HID=?",s);
    }

    @Override
    public OrderDetail get(String s) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM OrderDetail WHERE HID=?", s);
        if(resultSet.next()){
            return new OrderDetail(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)

            );
        }else {
            return null;

        }
    }

    @Override
    public List<OrderDetail> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM OrderDetail");
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();
        while (resultSet.next()) {
            orderDetails.add(
                    new OrderDetail(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    )
            );
        }
        System.out.println("hospitals = " + orderDetails);
        return orderDetails;
    }
}
