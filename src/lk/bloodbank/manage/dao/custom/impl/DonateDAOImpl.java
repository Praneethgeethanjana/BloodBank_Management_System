package lk.bloodbank.manage.dao.custom.impl;

import lk.bloodbank.manage.dao.CrudUtil;
import lk.bloodbank.manage.dao.custom.DonateDAO;
import lk.bloodbank.manage.entity.Donate;
import lk.bloodbank.manage.entity.Donor;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DonateDAOImpl implements DonateDAO {

    @Override
    public boolean save(Donate donate) throws Exception {
        return CrudUtil.execute("INSERT INTO Donation VALUES (?,?,?,?,?,?,?,?)",donate.getDonateID(),donate.getDid(),donate.getName(),donate.getBlood(),donate.getDonation_Type(),donate.getQty(),donate.getDate(),donate.getTime());

    }

    @Override
    public boolean update(Donate donate) throws Exception {

        return CrudUtil.execute("UPDATE Donation set Name=?,BloodGroup=?,donatation_Type=?,qty=?,date=?,time=? where DonateID=?",
                donate.getDonateID(),
                donate.getDid(),
                donate.getName(),
                donate.getDonation_Type(),
                donate.getQty(),
                donate.getDate(),
                donate.getTime()
        );
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.execute("DELETE FROM Donation WHERE DonateID=?",id);
    }

    @Override
    public Donate get(String id) throws Exception {
        ResultSet resultSet=CrudUtil.execute("SELECT * FROM Donation WHERE DonateID=?",id);
        if(resultSet.next()){
            return new Donate(

                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8)

            );

        }else return null;
    }

    @Override
    public List<Donate> getAll() throws Exception {
        ResultSet resultSet=CrudUtil.execute("SELECT * FROM Donation");
        ArrayList<Donate> donates=new ArrayList<>();
        while (resultSet.next()){
            donates.add(
                    new Donate(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            resultSet.getString(8)
                    )
            );
        }
        return donates;
    }

    @Override
    public int getDonationID() throws Exception {
        ResultSet set=CrudUtil.execute("SELECT donateID FROM Donation ORDER BY donateID DESC LIMIT 1");
        int id=1;
        if(set.next()){
            int temp=set.getInt(1);
            id=temp+1;
        }
        return id;
    }
}
