package lk.bloodbank.manage.bo;

import lk.bloodbank.manage.bo.custom.impl.*;

public class BoFactory {
    private static BoFactory boFactory;
    private BoFactory() {
    }

    public static BoFactory getInstance() {
        return (boFactory == null) ?
                (boFactory = new BoFactory()) : (boFactory);
    }


    public enum BOType{
        DONOR,HOSPITAL,DONATE,DONATESTOCK,STOCK,BLOODORDERBOIMPL
    }

    public <T> T getBo(BOType type){
        switch (type){
            case DONOR:
                return (T) new DonorBOImpl();
            case HOSPITAL:
                return (T) new HospitalBOImpl();
            case DONATE:
                return (T) new DonateBOImpl();
            case DONATESTOCK:
                return (T) new DonateStockBOImpl();
            case STOCK:
                return (T) new StockBOImpl();
            case BLOODORDERBOIMPL:
                return (T) new BloodOrderBOImpl();

            default:
                return null;

        }
    }
}
