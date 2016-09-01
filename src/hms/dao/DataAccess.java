package hms.dao;

import com.mysql.jdbc.Connection;
import java.sql.*;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A singleton database access class for MySQL database. It contains  INSERT, 
 * UPDATE or DELETE and  Query 
 *
 * @author Deshan
 */
public final class DataAccess {
    
    private final String URL = "jdbc:mysql://localhost:3306/";
    private final String DB_NAME = "hms";
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String USERNAME = "root";
    private final String PASSWORD = "";
    
    public Connection conn;
    private Statement statement;
    public static DataAccess db;

    private DataAccess() {

        try {
            Class.forName(DRIVER).newInstance();
            this.conn = (Connection) DriverManager.getConnection(URL + DB_NAME, USERNAME, PASSWORD);
        } catch (Exception sqle) {
            sqle.printStackTrace();
        }
    }

    /**
     *
     * @return MysqlConnect Database connection object
     */
    public static synchronized DataAccess getDbCon() {
        if (db == null) {
            db = new DataAccess();
        }
        return db;

    }

    /**
     *
     * @param query String The query to be executed
     * @return a ResultSet object containing the results or null if not
     * available
     * @throws SQLException
     */
    public ResultSet executeQ(String query) throws SQLException {
        statement = db.conn.createStatement();
        ResultSet res = statement.executeQuery(query);
        return res;
    }

    /**
     * @desc Method to  INSERT, UPDATE or DELETE
     * @param insertQuery String The Insert query
     * @return boolean
     * @throws SQLException
     */
    public int exceuteU(String insertQuery) {
        try {
            statement = db.conn.createStatement();
            int result = statement.executeUpdate(insertQuery);
            return result;
            
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    /**
     * delete the row base on table name  and id
     * @param tableName
     * @param id
     * @return int
     */
    public int deleteById(String tableName, int id)  {
        String query = "DELETE FROM "+ tableName + " WHERE id="+ id;
        System.out.println("deleteById: " + query);
        return DataAccess.getDbCon().exceuteU(query);
    }
    
    /**
     * Check if the id is available or not
     * @param tableName
     * @param id
     * @return int
     */
    public int isAvailable(String tableName, int id)  {
        String query = "SELECT EXISTS(SELECT 1 FROM "+tableName+" WHERE id ="+id+" LIMIT 1)";
        System.out.println("isAvailable(String tableName, int id) : " + query);
        int res = 0;
        ResultSet rs;
        try {
            rs = DataAccess.getDbCon().executeQ(query);
            if(rs.first()){
                res = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return  res;
    }
    
    /**
     * Get row data by id
     * 
     * @param tableName
     * @param id
     * @return ResultSet
     */
    public ResultSet getById(String tableName, int id)  {
        String query = "SELECT * FROM "+tableName+" WHERE id ="+id+" LIMIT 1";
        System.out.println("getById(String tableName, int id) : " + query);
        ResultSet rs = null;
        try {
            rs = DataAccess.getDbCon().executeQ(query);
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return  rs;
    }
    
    /**
     * Get All rows in table
     * 
     * @param tableName
     * @return 
     */
    public ResultSet getAll(String tableName)  {
        String query = "SELECT * FROM "+tableName;
        System.out.println("getAll(String tableName) : " + query);
        ResultSet rs = null;
        try {
            rs = DataAccess.getDbCon().executeQ(query);
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return  rs;
    }
}
