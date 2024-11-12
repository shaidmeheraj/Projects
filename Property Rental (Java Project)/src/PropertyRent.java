
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author David O' Connor
 */
public class PropertyRent implements Serializable{
  
  private String property;
  private int propertyAvailable;
  private int propertyTaken;
    
  public void cancelProperty(){
   	  if (propertyTaken>0)
   	  	propertyTaken--;
   }
  
  public boolean takeProperty(){
   	 if (propertyTaken<propertyAvailable){
   	 	propertyTaken++;
   	 	return true;
   	 }
   	 else
   	 	return false;
   }
  
  public int placesLeft(){
   	  return propertyAvailable - propertyTaken;
   }
  
   public int available(){
   	  return propertyAvailable - propertyTaken;
   }
   
   public PropertyRent(String title, int totCapacity){
    	this.property = title;
    	this.propertyAvailable = propertyTaken;
    	propertyTaken = 0;
    }
   
   public PropertyRent(String className){
    	this(className, 2); // temp, for purposes of testing the class system
    }
   
   public PropertyRent() {
    	this("Title unknown", 0);
    }
   
   public void setProperty(String property){
    	this.property = property;
    }
   
   public void setPropertyAvailable(int totCapacity){
    	this.propertyAvailable = totCapacity;
    }
   
    public String getProperty() { return property;}
    public int getPropertyAvailable() { return propertyAvailable;}
    public int getPropertyTaken() { return propertyTaken;};
    
    public String toString() {
    	return "\n Property: " + getProperty() + "\n Property Available: " + getPropertyAvailable() + 
                "\n Property Taken: " + getPropertyTaken();
    }
}
