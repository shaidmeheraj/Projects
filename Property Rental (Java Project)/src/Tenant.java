
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
public class Tenant extends Person implements Serializable {
    private final int tenantID;
    private String tenantStatus;
    private static int nextTenantID = 1;
        
    public Tenant(){
        super();
        setTenantStatus("Unknown");
        tenantID = getNextTenantID();
    }
    
    public Tenant(String name, String address, String phone, String pps, String tenStatus){
        super(name, address, phone, pps);
        tenantID = getNextTenantID();
        setTenantStatus(tenStatus);
    }
     
    public int getTenantID(){
	return tenantID;
    }
     
    public static int getNextTenantID(){
        if(HomeScreenGUI.tenantsAvailable.isEmpty()){
            int Id = nextTenantID;
            nextTenantID++;
            return Id;
        }
        else{
            int Id = HomeScreenGUI.tenantsAvailable.get(HomeScreenGUI.tenantsAvailable.size() - 1).getTenantID();
            return (Id +1);
        }
    }
    
    public void setTenantStatus(String tenStatus){
    	tenantStatus = tenStatus;
    }
    
    public String getTenantStatus(){
	return tenantStatus;
    }
    
    public String toString(){
		return "\n" + super.toString()+ "\nTenant ID: " + this.getTenantID() + "\nTenant Status: " + this.getTenantStatus();
	}
}
