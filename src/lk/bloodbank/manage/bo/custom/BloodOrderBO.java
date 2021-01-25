package lk.bloodbank.manage.bo.custom;

import lk.bloodbank.manage.bo.SuperBo;
import lk.bloodbank.manage.dto.BloodOrderDTO;
import lk.bloodbank.manage.dto.OrderDTO;

import java.util.ArrayList;

public interface BloodOrderBO extends SuperBo {
    public boolean saveOrder(ArrayList<BloodOrderDTO> arrayList) throws Exception;
    public int getOrderID() throws Exception;


}
