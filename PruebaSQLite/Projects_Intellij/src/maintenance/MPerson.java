package maintenance;

import entities.Person;
import connection.ConnectionSQLite;
import utils.BufferedZ;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;

public class MPerson {
    BufferedZ keyboard = new BufferedZ();
    ConnectionSQLite cx = new ConnectionSQLite();
    ResultSet rs = null;
    Statement stmt = null;
    Connection conn = null;

    public void registryPerson (Person p) {
        String query = "INSERT INTO people(id, name, fatherln, motherln, sex, mail, direction, dni)" +
                " VALUES ('"+p.getS_id()+"', '"+p.getS_name()+"', '"+p.getS_FatherLN()+"', '"+p.getS_MotherLN()+""+
                "', '"+p.getS_sex()+"','"+p.getS_mail()+"','"+p.getS_direction()+"','"+p.getS_dni()+"')";

        try {
            stmt = cx.connect().createStatement();
            stmt.executeUpdate(query);
            System.out.println("This is the query: "+query);
            System.out.println("|> Successfully registered 100%");
            System.out.println();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void toList (String nameTable){
        String consult = "SELECT * FROM "+nameTable+"";
         try{
             stmt = cx.connect().createStatement();
             rs = stmt.executeQuery(consult);
            while (rs.next()){
                int id = rs.getInt("ID");
                String name = rs.getString("NAME");
                String fatherln = rs.getString("FATHERLN");
                String motherln = rs.getString("MOTHERLN");
                String sex = rs.getString("SEX");
                String mail = rs.getString("MAIL");
                String direction = rs.getString("DIRECTION");
                int dni = rs.getInt("DNI");
                System.out.println("||> Table content :"+id+" | "+name+" | "+fatherln+" | "+motherln+"" +
                        " | "+sex+" | "+mail+" | "+direction+" | "+dni);
            }
            rs.close();
            stmt.close();

         }catch (Exception e){
             System.out.println("Error"+e.getMessage());
         }
    }

    public void updatePerson (String nameTableUp, int id) throws SQLException{
        stmt = cx.connect().createStatement();
        String consult = "SELECT * FROM "+nameTableUp+"";
        rs = stmt.executeQuery(consult);
        while (rs.next()){
            String nameColumn = keyboard.read("","Ingrese el nombre de la columna");
            String newValue = keyboard.read("","Ingrese el nuevo valor");
            String updateP = "UPDATE " + nameTableUp + " SET " + nameColumn +" = '"+newValue+"' WHERE =" + id + "";
            try {
                stmt.executeUpdate(updateP);
                conn.commit();
            } catch (Exception e) {
                System.out.println("Error <> :"+e.getMessage());
            }
        }
        stmt.close();
    }

    public void deleteP (String nameTableR, int idr)throws SQLException{
        String consult = "DELETE FROM "+nameTableR+" WHERE ID = "+idr+"";
        try {
            stmt = cx.connect().createStatement();
            stmt.executeQuery(consult);
        }catch (SQLException e){
            System.out.println("Error :"+e.getMessage());
        }
        stmt.close();
    }
}
