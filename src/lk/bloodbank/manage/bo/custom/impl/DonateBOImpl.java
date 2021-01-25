package lk.bloodbank.manage.bo.custom.impl;

import lk.bloodbank.manage.bo.custom.DonateBO;
import lk.bloodbank.manage.dao.DaoFactory;
import lk.bloodbank.manage.dao.custom.DonateDAO;
import lk.bloodbank.manage.dao.custom.DonorDAO;
import lk.bloodbank.manage.dto.DonateDTO;
import lk.bloodbank.manage.dto.DonorDTO;
import lk.bloodbank.manage.entity.Donate;

import java.util.ArrayList;
import java.util.List;

public class DonateBOImpl implements DonateBO {
    private DonateDAO dao= DaoFactory.getInstance().getDao(DaoFactory.DAOType.DONATE);
    @Override
    public boolean saveDonate(DonateDTO dto) throws Exception {
        return dao.save(new Donate(dto.getDonateID(),dto.getDid(),dto.getName(),dto.getBlood(),dto.getDonation_Type(),dto.getQty(),dto.getDate(),dto.getTime()));
    }

    @Override
    public boolean deleteDonate(String id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public DonateDTO getDonate(String id) throws Exception {
        Donate donate=dao.get(id);
        return new DonateDTO(donate.getDonateID(),donate.getDid(),donate.getName(),donate.getBlood(),donate.getDonation_Type(),donate.getQty(),donate.getDate(),donate.getTime());
    }

    @Override
    public List<DonateDTO> getAllDonates() throws Exception {
        List<Donate>donateList=dao.getAll();
        ArrayList<DonateDTO> dtoList=new ArrayList();
        for( Donate donate: donateList){
            dtoList.add(new DonateDTO(donate.getDonateID(),donate.getDid(),donate.getName(),donate.getBlood(),donate.getDonation_Type(),donate.getQty(),donate.getDate(),donate.getTime()));

        }return dtoList;
    }

    @Override
    public int getDonationID() throws Exception {
        return dao.getDonationID();
    }
}
