package lk.bloodbank.manage.bo.custom;

import lk.bloodbank.manage.bo.SuperBo;
import lk.bloodbank.manage.dto.DonorDTO;
import lk.bloodbank.manage.entity.Donor;

import java.util.List;

public interface DonorBO extends SuperBo {
    public boolean saveDonor(DonorDTO dto) throws Exception;
    public boolean updateDonor(DonorDTO dto) throws Exception;
    public boolean deleteDonor(String id) throws Exception;
    public DonorDTO getDonor(String id) throws Exception;
    public List<DonorDTO> getAllDonors() throws Exception;
    public String getDonorId() throws Exception;


}
