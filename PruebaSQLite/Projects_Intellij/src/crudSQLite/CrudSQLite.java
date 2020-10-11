package crudSQLite;

import entities.Person;
import maintenance.MPerson;
import utils.BufferedZ;

import java.sql.SQLException;

public class CrudSQLite {
    public static void main(String[] args)throws SQLException {
        BufferedZ keyboard = new BufferedZ();

        int opt;
        do {
            opt = keyboard.read(0,"|>------- CRUD SQLite ------<|\n"+
                    "1 Register new person\n"+
                    "2 Check Registration\n"+
                    "3 Update Record\n"+
                    "4 Delete Record\n"+
                    "0 Quit\n");
            System.out.println("You selected the option: "+ opt);

            switch (opt){
                case 1:
                    MPerson newp = new MPerson();
                    Person p = new Person();
                    System.out.println("||> Register new person");
                    p.setS_id(keyboard.read(0,"Enter the id:"));
                    p.setS_name(keyboard.read("","Enter the person's name:"));
                    p.setS_FatherLN(keyboard.read("","Enter the father's last name:"));
                    p.setS_MotherLN(keyboard.read("","Enter the mother's last name"));
                    p.setS_sex(keyboard.read("","Enter you gender:"));
                    p.setS_mail(keyboard.read("","Enter mail:"));
                    p.setS_direction(keyboard.read("","Enter the address"));
                    p.setS_dni(keyboard.read(0,"Enter the DNI"));
                    newp.registryPerson(p);
                    break;
                case 2:
                    MPerson check = new MPerson();
                    System.out.println("||> Check Registration");
                    String nameTable = keyboard.read("","Enter the table name");
                    check.toList(nameTable);
                    break;
                case 3:
                    MPerson up = new MPerson();
                    System.out.println("||> Update Record");
                    String nameTableUp = keyboard.read("","Enter the table name");
                    int id = keyboard.read(0,"Enter the ID of the record you want to update");
                    up.updatePerson(nameTableUp,id);
                    break;
                case 4:
                    MPerson remove = new MPerson();
                    System.out.println("||> Delete Record");
                    String nameTableR = keyboard.read("","Enter the table name");
                    int idr = keyboard.read(0,"Enter the ID of the record you want to delete");
                    remove.deleteP(nameTableR, idr);
                    break;
                case 0:
                    System.out.println("|> coming out...");
                    break;
                default:
                    System.out.println("|> Wrong selection!");
                    break;
            }
        }while (opt != 0);
    }
}
