package lk.bloodbank.manage.bo.custom;

import lk.bloodbank.manage.bo.SuperBo;
import lk.bloodbank.manage.dto.DonateDTO;
import lk.bloodbank.manage.dto.StockDTO;

import java.util.ArrayList;

public interface DonateStockBO extends SuperBo {
    public boolean update(DonateDTO dto)throws Exception;

}
