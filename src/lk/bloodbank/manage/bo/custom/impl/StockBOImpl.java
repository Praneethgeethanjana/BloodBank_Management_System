package lk.bloodbank.manage.bo.custom.impl;

import lk.bloodbank.manage.bo.custom.StockBO;
import lk.bloodbank.manage.dao.DaoFactory;
import lk.bloodbank.manage.dao.custom.StockDAO;
import lk.bloodbank.manage.dto.HospitalDTO;
import lk.bloodbank.manage.dto.StockDTO;
import lk.bloodbank.manage.entity.Hospital;
import lk.bloodbank.manage.entity.Stock;

import java.util.ArrayList;
import java.util.List;

public class StockBOImpl implements StockBO {
    StockDAO stockDAO= DaoFactory.getInstance().getDao(DaoFactory.DAOType.STOCK);
    @Override
    public boolean updateStock(StockDTO dto) throws Exception {
        return stockDAO.update(new Stock(dto.getBlood(), dto.getWhole(), dto.getRbc(), dto.getPlatelets(), dto.getPlasma()));

    }

    @Override
    public ArrayList<StockDTO> getStock() throws Exception {
        ArrayList<StockDTO> arrayList = new ArrayList<>();
        List<Stock> all = stockDAO.getAll();
        for (Stock stock : all) {
            arrayList.add(new StockDTO(stock.getBlood(),stock.getWhole(),stock.getRbc(),stock.getPlatelets(),stock.getPlasma()));
        }
        return arrayList;
    }

    @Override
    public List<StockDTO> loadAllStock() throws Exception {
        List<Stock> sList = stockDAO.getAll();
        ArrayList<StockDTO> dtoList = new ArrayList();
        for (Stock stock : sList) {
            dtoList.add(new StockDTO(
                    stock.getBlood(),
                    stock.getWhole(),
                    stock.getRbc(),
                    stock.getPlatelets(),
                    stock.getPlasma()

            ));

        }return dtoList;
    }
    @Override
    public StockDTO getStock(String bloodGroup) throws Exception {
        Stock stock = stockDAO.get(bloodGroup);
        return new StockDTO(stock.getBlood(),stock.getWhole(),stock.getRbc(),stock.getPlatelets(),stock.getPlasma());

    }
}
