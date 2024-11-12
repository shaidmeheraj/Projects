
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
public class Rental implements Serializable{
    Property property_;
    Tenant tenant_;
    String term_;
    double rate;
        
    public Rental() {
        tenant_ = new Tenant();
    	property_ = new Property();
    	term_ = "no term";
        rate = (0.0);
    }
        
    public Rental(Property property, Tenant tenant){
    	setProperty(property);
    	setTenant(tenant);
    	term_ = "no term";
    }
        
    public Rental(Tenant tenant, Property property, String term, double rate){
        setTenant(tenant);
        setProperty(property);
        setTerm(term);
        setRate(rate);
    }
    
    public void setTerm(String term){
        this.term_ = term;
    } 
    
    public void setRate(double rate){
        this.rate = rate;
     }
    
    public void setProperty(Property property){
        this.property_ = property;
    }
    
    public void setTenant(Tenant tenant){
        this.tenant_ = tenant;
    }
    
    public String getTerm(){return term_;}
    public double getRate(){ return rate;}
    public Property getProperty(){return property_;}
    public Tenant getTenant(){return tenant_;}
        
    public String toString(){
		return "\n" + "\nProperty: " + property_  + "\nTenant: " + tenant_ + "\nTerm: " + getTerm();
	}

}
