/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author David O' Connor
 */
public class PropertyTest {
    public static void main(String[] args) {
        Property p1 = new Property();
        Property p2 = new Property("26 Oakpark Tralee", "4", "Two Storey", "750", "Up to date");
        
        //test null constructor
        System.out.println(p1);
	//test the full argument constructor
	System.out.println(p2);
        
        //Test the set method(Address)
        p1.setAddress_("32 Sunday Wells Tralee");
        System.out.println(p1.toString());
        
        //Test the set method(Beds)
        p1.setBeds_("3");
        System.out.println(p1.toString());
        
        //Test the set method(Property Type)
        p1.setPropertyType_("Two Storey");
        System.out.println(p1.toString());
        
        //Test the set method(Rent)
        p1.setRentAmount_("650");
        System.out.println(p1.toString());
        
        //Test the set method(Property Status)
        p1.setPropertyStatus_("Up to date");
        System.out.println(p1.toString());
        
        //test the get method(Address)
	System.out.println(p1.getAddress_());
        
        //test the get method(Beds)
	System.out.println(p1.getBeds_());
        
        //test the get method(Property Type)
	System.out.println(p1.getPropertyType_());
        
        //test the get method(Rent)
	System.out.println(p1.getRentAmount_());
        
        //test the get method(Property Status)
	System.out.println(p1.getPropertyStatus_());
}
}