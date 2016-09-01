/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hms.dao;

/**
 *
 * @author Deshan
 */
public class PatientAccess {
    public boolean addPatient(int id, String firstName, String lastName, int age, String contact){
        String query = "INSERT INTO opd_patient VALUES(null,'"+firstName+"','"+lastName+"','"+contact+"',"+age+")";
        return DataAccess.getDbCon().exceuteU(query) == 1;
    }
}
