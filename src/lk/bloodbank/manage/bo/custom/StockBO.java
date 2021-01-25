package lk.bloodbank.manage.bo.custom;

import lk.bloodbank.manage.bo.SuperBo;
import lk.bloodbank.manage.dto.StockDTO;
import lk.bloodbank.manage.view.tm.Stock;

import java.util.ArrayList;
import java.util.List;

public interface StockBO extends SuperBo {
    public boolean updateStock(StockDTO dto)throws Exception;
    public ArrayList<StockDTO>getStock()throws Exception;
    public List<StockDTO> loadAllStock()throws Exception;
    public StockDTO getStock(String bloodGroup) throws Exception;

}
