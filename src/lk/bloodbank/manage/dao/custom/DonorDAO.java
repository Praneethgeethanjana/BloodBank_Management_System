package lk.bloodbank.manage.dao.custom;

import lk.bloodbank.manage.dao.CrudDAO;
import lk.bloodbank.manage.entity.Donor;

public interface DonorDAO extends CrudDAO <Donor,String> {
    public String getDonorId() throws Exception;
}
