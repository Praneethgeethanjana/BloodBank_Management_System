package lk.bloodbank.manage.bo.custom.impl;

import lk.bloodbank.manage.bo.custom.DonorBO;
import lk.bloodbank.manage.dao.DaoFactory;
import lk.bloodbank.manage.dao.custom.DonorDAO;
import lk.bloodbank.manage.dto.DonorDTO;
import lk.bloodbank.manage.entity.Donor;

import java.util.ArrayList;
import java.util.List;

public class DonorBOImpl implements DonorBO {
    private DonorDAO dao= DaoFactory.getInstance().getDao(DaoFactory.DAOType.DONOR);


    @Override
    public boolean saveDonor(DonorDTO dto) throws Exception {
        return dao.save(new Donor(dto.getDid(),dto.getName(),dto.getNic(),dto.getGender(),dto.getDob(),dto.getAddress(),dto.getBlood(),dto.getContact(),dto.getEmail()));
    }

    @Override
    public boolean updateDonor(DonorDTO dto) throws Exception {
        return dao.update(new Donor(
                dto.getDid(),
                dto.getName(),
                dto.getNic(),
                dto.getGender(),
                dto.getDob(),
                dto.getAddress(),
                dto.getBlood(),
                dto.getContact(),
                dto.getEmail()
        ));
    }

    @Override
    public boolean deleteDonor(String id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public DonorDTO getDonor(String id) throws Exception {
        Donor donor=dao.get(id);
        return new DonorDTO(donor.getDid(),donor.getName(),donor.getNic(),donor.getGender(),donor.getDob(),donor.getAddress(),donor.getBlood(),donor.getContact(),donor.getEmail());
    }

    @Override
    public List<DonorDTO> getAllDonors() throws Exception {
        List<Donor>dList=dao.getAll();
        ArrayList<DonorDTO>dtoList=new ArrayList();
        for(Donor donor:dList){
            dtoList.add(new DonorDTO(donor.getDid(),donor.getName(),donor.getNic(),donor.getGender(),donor.getDob(),donor.getAddress(),donor.getBlood(),donor.getContact(),donor.getEmail()
            ));
        }return dtoList;
    }

    @Override
    public String getDonorId() throws Exception {
        return dao.getDonorId();
    }


}
