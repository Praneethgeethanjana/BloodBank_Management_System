package lk.bloodbank.manage.dao.custom.impl;

import com.mysql.jdbc.ResultSetRow;
import lk.bloodbank.manage.dao.CrudUtil;
import lk.bloodbank.manage.dao.custom.DonorDAO;
import lk.bloodbank.manage.entity.Donor;
import lk.bloodbank.manage.entity.SuperEntity;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DonorDAOImpl implements DonorDAO{


    @Override
    public boolean save(Donor donor) throws Exception {
        return CrudUtil.execute("INSERT INTO Donor VALUES(?,?,?,?,?,?,?,?,?)",donor.getDid(),donor.getName(),donor.getNic(),donor.getGender(),donor.getDob(),donor.getAddress(),donor.getBlood(),donor.getContact(),donor.getEmail());
    }

    @Override
    public boolean update(Donor donor) throws Exception {

        return CrudUtil.execute("UPDATE donor set Donor_Name=?,NIC=?,Gender=?,dob=?,Address=?,BloodGroup=?,Contact=?,Email=? where DID=?",
                donor.getName(),
                donor.getNic(),
                donor.getGender(),
                donor.getDob(),
                donor.getAddress(),
                donor.getBlood(),
                donor.getContact(),
                donor.getEmail(),
                donor.getDid()
        );
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM Donor WHERE DID=?",s);
    }

    @Override
    public Donor get(String s) throws Exception {
        ResultSet resultSet=CrudUtil.execute("SELECT * FROM Donor WHERE DID=?",s);
        if(resultSet.next()){
            return new Donor(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9)
            );
        }else return null;
    }

    @Override
    public List<Donor> getAll() throws Exception {
        ResultSet resultSet=CrudUtil.execute("SELECT * FROM Donor");
        ArrayList<Donor> donors=new ArrayList<>();
        while(resultSet.next()){
            donors.add(new Donor(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9)

                    )
            );
            }return donors;
        }

    @Override
    public String getDonorId() throws Exception {
        ResultSet set = CrudUtil.
                execute("SELECT DID FROM Donor ORDER BY DID DESC LIMIT 1");
        String id="D001";
        if (set.next()){
            String temp=set.getString(1);
            String[] cs = temp.split("D");
            int selectedId=Integer.parseInt(cs[1]);
            if (selectedId<9){
                id="D00"+(selectedId+1);
            }else if(selectedId >= 9 && selectedId <99){
                id="D0"+(selectedId+1);
            }else if(selectedId >= 99){
                id="D"+(selectedId+1);
            }
        }
        return id;
    }
}
