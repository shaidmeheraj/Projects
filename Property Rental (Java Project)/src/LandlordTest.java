/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author David O' Connor
 */
public class LandlordTest {
    public static void main(String[] args) {
        Landlord l1 = new Landlord();
	Landlord l2 = new Landlord("John O' Connor", "Oakpark Tralee Co. Kerry", "0871234567", "12344567R", "Available");
        
        //Test the Null Constructor
        System.out.println(l1);
        
        //Test the Full argument Constructor
	System.out.println(l2);
        
        //Test the set method(Name)
        l1.setName("Simon");
        System.out.println(l1.toString());
        
        //Test the set method(Address)
        l1.setAddress("Clash Tralee Co. Kerry");
        System.out.println(l1.toString());
        
        //Test the set medhod(PhoneNumber)
        l1.setPhoneNumber("0864567823");
        System.out.println(l1.toString());
        
        //Test the set medhod(PpsNumber)
        l1.setPpsNumber("4567852R");
        System.out.println(l1.toString());
        
        //Test the set medhod(LandlordStatus)
        l1.setLandlordStatus("Available");
        System.out.println(l1.toString());
        
        //test the get method(Name)
	System.out.println(l1.getName());
        
        //test the get method(Address)
	System.out.println(l1.getAddress());
        
        //test the get method(PhoneNumber)
	System.out.println(l1.getPhoneNumber());
        
        //test the get method(PpsNumber)
	System.out.println(l1.getPpsNumber());
        
        //test the get method(LandlordStatus)
	System.out.println(l1.getLandlordStatus());
    }
}
