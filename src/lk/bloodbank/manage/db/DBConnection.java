package lk.bloodbank.manage.db;

import com.mongodb.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dbConnection;
    public Connection connection;


    public DBConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        connection=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","ijse");

    }

    public static  DBConnection getInstance() throws SQLException, ClassNotFoundException {
       if (null==dbConnection) {
           dbConnection=new DBConnection();
       }
        return dbConnection;
        }
        public Connection getConnection(){ return connection; }

}
