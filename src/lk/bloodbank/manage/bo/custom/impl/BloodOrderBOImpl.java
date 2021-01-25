package lk.bloodbank.manage.bo.custom.impl;

import lk.bloodbank.manage.bo.BoFactory;
import lk.bloodbank.manage.bo.custom.BloodOrderBO;
import lk.bloodbank.manage.dao.DaoFactory;
import lk.bloodbank.manage.dao.custom.OrderDAO;
import lk.bloodbank.manage.dao.custom.OrderDetailDAO;
import lk.bloodbank.manage.dao.custom.StockDAO;
import lk.bloodbank.manage.db.DBConnection;
import lk.bloodbank.manage.dto.BloodOrderDTO;
import lk.bloodbank.manage.dto.OrderDTO;
import lk.bloodbank.manage.entity.Order;
import lk.bloodbank.manage.entity.OrderDetail;
import lk.bloodbank.manage.entity.Stock;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class BloodOrderBOImpl implements BloodOrderBO {
   OrderDAO dao= DaoFactory.getInstance().getDao(DaoFactory.DAOType.ORDER);
    OrderDetailDAO orderDetailDAO = DaoFactory.getInstance().getDao(DaoFactory.DAOType.ORDERDETAIL);
    StockDAO stockDAO = DaoFactory.getInstance().getDao(DaoFactory.DAOType.STOCK);


    @Override

        public boolean saveOrder(ArrayList<BloodOrderDTO> arrayList) throws Exception {
		        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            for (BloodOrderDTO bloodOrderDTO : arrayList) {
                if (dao.save(new Order(bloodOrderDTO.getOid(),bloodOrderDTO.getHid(),bloodOrderDTO.getDate()))){
                    for (int i = 0; i < arrayList.size(); i++) {
                        System.out.println(arrayList.size());
                        if (orderDetailDAO.save(new OrderDetail(bloodOrderDTO.getOid(),arrayList.get(i).getBlood(),arrayList.get(i).getWhole_qty(),arrayList.get(i).getRbc_qty(),arrayList.get(i).getPlatelets_qty(),arrayList.get(i).getPlasma_qty()))) {

                            List<Stock> all = stockDAO.getAll();
                            for (Stock stock : all) {
                                boolean update = stockDAO.update(new Stock(arrayList.get(i).getBlood(), String.valueOf(Integer.parseInt(stock.getWhole()) - Integer.parseInt(arrayList.get(i).getWhole_qty())), String.valueOf(Integer.parseInt(stock.getRbc()) - Integer.parseInt(arrayList.get(i).getRbc_qty())), String.valueOf(Integer.parseInt(stock.getPlatelets()) - Integer.parseInt(arrayList.get(i).getPlatelets_qty())), String.valueOf(Integer.parseInt(stock.getPlasma()) - Integer.parseInt(arrayList.get(i).getPlasma_qty()))));
                                if (update){
                                    System.out.println("yes");
                                }else {
                                    connection.rollback();
                                }
                            }

                        }else {
                            connection.rollback();
                        }
                    }
                    connection.commit();
                    return true;
                }
            }
        }finally {
            connection.setAutoCommit(true);
        }
        return false;


    }

    @Override
    public int getOrderID() throws Exception {
        return dao.getOrderID();
    }


}
