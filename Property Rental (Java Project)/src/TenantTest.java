/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author David O' Connor
 */
public class TenantTest {
    public static void main(String[] args) {
        Tenant t1 = new Tenant();
	Tenant t2 = new Tenant("John O' Sullivan", "Manor Village Tralee Co. Kerry", "0873456789", "2638649R", "Available");
        
        //Test the Null Constructor
        System.out.println(t1);
        
        //Test the Full argument Constructor
	System.out.println(t2);
        
        //Test the set method(Name)
        t1.setName("Mary O' Connor");
        System.out.println(t1.toString());
        
        //Test the set method(Address)
        t1.setAddress("Spa Road Tralee Co. Kerry");
        System.out.println(t1.toString());
        
        //Test the set medhod(PhoneNumber)
        t1.setPhoneNumber("0854679256");
        System.out.println(t1.toString());
        
        //Test the set medhod(PpsNumber)
        t1.setPpsNumber("2539475L");
        System.out.println(t1.toString());
        
        //Test the set medhod(LandlordStatus)
        t1.setTenantStatus("Available");
        System.out.println(t1.toString());
        
        //test the get method(Name)
	System.out.println(t1.getName());
        
        //test the get method(Address)
	System.out.println(t1.getAddress());
        
        //test the get method(PhoneNumber)
	System.out.println(t1.getPhoneNumber());
        
        //test the get method(PpsNumber)
	System.out.println(t1.getPpsNumber());
        
        //test the get method(TenantStatus)
	System.out.println(t1.getTenantStatus());
    }
}
