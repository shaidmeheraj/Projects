
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
public class LeaseProperty implements Serializable{
  private String propertyAddress;
  private int totalProperties;
  private int propertyTaken;
    
  public void cancelLease(){
   	  if (propertyTaken>0)
   	  	propertyTaken--;
   }
  
  public boolean takeProperty(){
   	 if (propertyTaken<totalProperties){
   	 	propertyTaken++;
   	 	return true;
   	 }
   	 else
   	 	return false;
   }
  
  public int placesLeft(){
   	  return totalProperties - propertyTaken;
   }
  
  public int available(){
   	  return totalProperties - propertyTaken;
   }
  
  public LeaseProperty(String title, int totCapacity){
    	this.propertyAddress = title;
    	this.totalProperties = propertyTaken;
    	propertyTaken = 0;
    }
  
  
  public LeaseProperty() {
    	this("Address unknown", 0);
    }
  
  public void setPropertyAddress(String propAddress){
    	this.propertyAddress = propAddress;
    }
   
   public void setTotalProperties(int totProperties){
    	this.totalProperties = totProperties;
    }
   
   public String getPropertyAddress() { return propertyAddress;}
    public int getTotalProperties() { return totalProperties;}
    public int getPropertyTaken() { return propertyTaken;};
    
    
    public String toString() {
    	return "\n Property Address: " + getPropertyAddress() + "\n Total Properties: " + getTotalProperties() + 
                "\n Property Taken: " + getPropertyTaken();
    }
}
