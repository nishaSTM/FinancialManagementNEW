package myapplication.admin.example.com.financialmanagement;

import java.io.Serializable;
//import java.math.BigDecimal;

public class DataForMain implements Serializable{

	 String name;
	 String amount;
     String imageName;
	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getAmount() {
	        return amount;
	    }

	    public void setAmount(String amount) {
	        this.amount = amount;
	    }
	    
	    public String getImageName() {
	        return imageName;
	    }

	    public void setImageName(String imageName) {
	        this.imageName = imageName;
	    }

}
