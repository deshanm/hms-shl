/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms.dao;

import hms.modal.ZumbaMember;
import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Deshan
 */
public class PatientAccess {
    public boolean addPatient(int id, String firstName, String lastName, int age, String contact){
        String query = "INSERT INTO opd_patient VALUES(null,'"+firstName+"','"+lastName+"','"+contact+"',"+age+")";
        return DataAccess.getDbCon().exceuteU(query) == 1;
    }
    
    public boolean addZumbaProgress(int memberId, String name, String mesurementType, String date, String amount){
        String query = "INSERT INTO zumba_progress VALUES("+memberId+",'"+date+"','"+mesurementType+"',"+amount+")";
        return DataAccess.getDbCon().exceuteU(query) == 1;
    }
    
    public ArrayList<ZumbaMember> getZumbaProgresses(){
         ResultSet res = DataAccess.getDbCon().getAll("zumba_progress");
        ArrayList<ZumbaMember> zumbaMembers = new ArrayList<>();
       
        try {
            while (res.next()) {
                ZumbaMember z = new ZumbaMember();
                z.setMemberId(res.getString("mem_id"));
                z.setDate(res.getString("date"));
                z.setAmmount(res.getString("amount"));
               z.setMeasureType(res.getString("measurement_type"));
              
                zumbaMembers.add(z);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return zumbaMembers;
    }
}
