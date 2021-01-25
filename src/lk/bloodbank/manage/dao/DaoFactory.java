package lk.bloodbank.manage.dao;

import lk.bloodbank.manage.bo.custom.impl.DonateBOImpl;
import lk.bloodbank.manage.dao.custom.impl.*;

import java.util.Queue;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory() {
    }

    public static DaoFactory getInstance(){

        return (daoFactory==null)?(daoFactory=new DaoFactory()):(daoFactory);
    }


    public enum DAOType {
        DONOR, HOSPITAL,DONATE,STOCK,QUERY,ORDER,ORDERDETAIL
    }

    public <T> T getDao(DAOType type) {
        switch (type) {
            case DONOR:
                return (T) new DonorDAOImpl();
            case HOSPITAL:
                return (T) new HospitalDAOImpl();
            case DONATE:
                return (T) new DonateDAOImpl();
            case STOCK:
                return (T) new StockDAOImpl();
            case QUERY:
                return (T) new QueryDaoImpl();
            case ORDER:
                return (T) new OrderDAOImpl();
            case ORDERDETAIL:
                return (T) new OrderDetailDAOImpl();
            default:
                return null;


        }
    }


}
