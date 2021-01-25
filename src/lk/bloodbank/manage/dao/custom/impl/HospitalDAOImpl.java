package lk.bloodbank.manage.dao.custom.impl;

import lk.bloodbank.manage.dao.CrudUtil;
import lk.bloodbank.manage.dao.custom.HospitalDAO;
import lk.bloodbank.manage.entity.Hospital;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HospitalDAOImpl implements HospitalDAO {
    @Override
    public boolean save(Hospital hospital) throws Exception {
        return CrudUtil.execute("INSERT INTO  hospital VALUES(?,?,?,?,?)",
                hospital.getHid(),
                hospital.getName(),
                hospital.getAddress(),
                hospital.getType(),
                hospital.getContact()
        );
    }

    @Override
    public boolean update(Hospital hospital) throws Exception {
        return CrudUtil.execute("UPDATE hospital set hospital_name=?,address=?,type=?,telephone=? where HID=?",
                hospital.getName(),
                hospital.getAddress(),
                hospital.getType(),
                hospital.getContact(),
                hospital.getHid()
        );


    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.execute("DELETE FROM Hospital WHERE HID=?",id);
    }

    @Override
    public Hospital get(String id) throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM hospital WHERE HID=?", id);
        if(resultSet.next()){
            return new Hospital(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)

            );
        }else {
            return null;

        }

    }

    @Override
    public List<Hospital> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM hospital");
        ArrayList<Hospital> hospitals = new ArrayList<>();
        while (resultSet.next()) {
            hospitals.add(
                    new Hospital(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5)
                    )
            );
        }
        System.out.println("hospitals = " + hospitals);
        return hospitals;
    }


    @Override
    public String getHosID() throws Exception {
        ResultSet set = CrudUtil.
                execute("SELECT HID FROM Hospital ORDER BY HID DESC LIMIT 1");
        String id="h001";
        if (set.next()){
            String temp=set.getString(1);
            String[] cs = temp.split("h");
            int selectedId=Integer.parseInt(cs[1]);
            if (selectedId<9){
                id="h00"+(selectedId+1);
            }else if(selectedId >= 9 && selectedId <99){
                id="h0"+(selectedId+1);
            }else if(selectedId >= 99){
                id="h"+(selectedId+1);
            }
        }
        return id;
    }
}
