package lk.bloodbank.manage.bo.custom;

import lk.bloodbank.manage.bo.SuperBo;
import lk.bloodbank.manage.dto.DonateDTO;
import lk.bloodbank.manage.dto.DonorDTO;

import java.util.List;

public interface DonateBO extends SuperBo {
        public boolean saveDonate(DonateDTO dto)throws Exception;
        public boolean deleteDonate(String id) throws Exception;
        public DonateDTO getDonate(String id) throws Exception;
        public List<DonateDTO> getAllDonates()throws Exception;
        public int getDonationID() throws Exception;
}
