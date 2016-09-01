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
public class ZumbaAccess {
        public boolean addZumbaMember(String id, String ado, String contact, String address,  String gender,String dob){
        String query = "INSERT INTO zumba_membership VALUES("
                + id + ",'"
                + ado +"','"
                + contact +"','"
                + address +"','"
                 + dob +"','"
                + gender +"')";
        
            System.out.println(query);
        return DataAccess.getDbCon().exceuteU(query) == 1;
    }
}
