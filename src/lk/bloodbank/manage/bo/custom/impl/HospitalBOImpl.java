package lk.bloodbank.manage.bo.custom.impl;

import lk.bloodbank.manage.bo.custom.HospitalBO;
import lk.bloodbank.manage.dao.DaoFactory;
import lk.bloodbank.manage.dao.custom.DonorDAO;
import lk.bloodbank.manage.dao.custom.HospitalDAO;
import lk.bloodbank.manage.dto.HospitalDTO;
import lk.bloodbank.manage.entity.Hospital;

import java.util.ArrayList;
import java.util.List;

public class HospitalBOImpl implements HospitalBO {
    private HospitalDAO dao = DaoFactory.getInstance().getDao(DaoFactory.DAOType.HOSPITAL);

    @Override
    public boolean saveHospital(HospitalDTO dto) throws Exception {
        return dao.save(new Hospital(
                dto.getHid(),
                dto.getName(),
                dto.getAddress(),
                dto.getType(),
                dto.getContact()
        ));
    }

    @Override
    public boolean updateHospital(HospitalDTO dto) throws Exception {
        return dao.update(new Hospital(
                dto.getHid(),
                dto.getName(),
                dto.getAddress(),
                dto.getType(),
                dto.getContact()
        ));
    }

    @Override
    public boolean deleteHospital(String HID) throws Exception {
        return dao.delete(HID);


    }

    @Override
    public HospitalDTO getHospital(String HID) throws Exception {
        Hospital hospital = dao.get(HID);
        return new HospitalDTO(hospital.getHid(), hospital.getName(), hospital.getAddress(), hospital.getType(), hospital.getContact());
    }



    @Override
    public List<HospitalDTO> getAllHospitals() throws Exception {
        List<Hospital> hList = dao.getAll();
        ArrayList<HospitalDTO> dtoList = new ArrayList();
        for (Hospital hospital : hList) {
            dtoList.add(new HospitalDTO(
                            hospital.getHid(),
                            hospital.getName(),
                            hospital.getAddress(),
                            hospital.getType(),
                            hospital.getContact()
                    )
            );
            System.out.println("hospital = " + hospital);
        }
        return dtoList;
    }

    @Override
    public String getHospitalID() throws Exception {
        return dao.getHosID();
    }


}
