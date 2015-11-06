package myapplication.admin.example.com.financialmanagement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

public class Operation implements Serializable{


	private String debited;
	private String bankName;
	private String date;
	private String through;


	/**
	 * @return the debited
	 */
	public String getDebited() {
		return debited;
	}
	
	public void setDebited(String string) {
		this.debited = string;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankName() {

		return bankName;
	}
	/**
	 * @param string the debited to set
	 */
	
	public String getThrough() {
		return through;
	}
	public void setThrough(String through) {
		// TODO Auto-generated method stub
		this.through=through;
	}
	/**
	 * @param through the through to set
	 */

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	





}