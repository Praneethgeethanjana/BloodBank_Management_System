package lk.bloodbank.manage.dao.custom;

import lk.bloodbank.manage.dao.CrudDAO;
import lk.bloodbank.manage.entity.Donate;

public interface DonateDAO extends CrudDAO<Donate,String> {
    public int getDonationID() throws Exception;
}
