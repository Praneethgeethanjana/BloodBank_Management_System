package lk.bloodbank.manage.bo.custom;

import lk.bloodbank.manage.bo.SuperBo;
import lk.bloodbank.manage.dto.HospitalDTO;

import java.util.ArrayList;
import java.util.List;



public interface HospitalBO extends SuperBo {
    public boolean saveHospital(HospitalDTO dto) throws Exception;
    public boolean updateHospital(HospitalDTO dto) throws Exception;
    public boolean deleteHospital(String id) throws Exception;
    public HospitalDTO getHospital(String id)throws Exception;
    public List<HospitalDTO> getAllHospitals() throws Exception;
    public String getHospitalID() throws Exception;

}
