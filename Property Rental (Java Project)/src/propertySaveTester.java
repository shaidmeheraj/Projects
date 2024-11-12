
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author David O' Connor
 */
public class propertySaveTester {
    public static void main(String[] args) throws Exception {
        // construct a fitness class
        Landlord one = new Landlord("Mary Murphy", "Tralee", "0871234567", "2468965R", "Up to date");
        Landlord two = new Landlord("John O' Connor", "Dingle", "0858372932", "3928652R", "Up to date");
        ArrayList<Landlord> landlordList = new ArrayList<Landlord>();
        landlordList.add(one);
        landlordList.add(two);
        
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("files/landlordTest.data"));
   		os.writeObject(landlordList);
   		os.close();
    }
}
