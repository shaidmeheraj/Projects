/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author David O' Connor
 */
public class PersonTest {
    public static void main(String[] args) {
        Person p1 = new Person();
        Person p2 = new Person("Mary O' Connor", "28 Oakpark Tralee", "0871234567", "1234567R");

        //test null constructor
        System.out.println(p1);
	//test the full argument constructor
	System.out.println(p2);
        
        //Test the set method(Name)
        p1.setName("John Murphy");
        System.out.println(p1.toString());
        
        //Test the set method(Name)
        p1.setAddress("Tralee");
        System.out.println(p1.toString());
        
        //Test the set method(Phone Number)
        p1.setPhoneNumber("0864698367");
        System.out.println(p1.toString());
        
        //Test the set method(PPS Number)
        p1.setPpsNumber("3748565P");
        System.out.println(p1.toString());
        
        //test the get method(Name)
	System.out.println(p1.getName());
        
        //test the get method(Address)
	System.out.println(p1.getAddress());
        
        //test the get method(Phone Number)
	System.out.println(p1.getPhoneNumber());
        
        //test the get method(PPS Number)
	System.out.println(p1.getPpsNumber());
}
}
