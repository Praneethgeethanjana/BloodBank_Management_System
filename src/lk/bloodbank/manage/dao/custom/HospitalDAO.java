package lk.bloodbank.manage.dao.custom;

import lk.bloodbank.manage.dao.CrudDAO;
import lk.bloodbank.manage.entity.Hospital;

public interface HospitalDAO extends CrudDAO<Hospital,String > {
    public String getHosID() throws Exception;
}
