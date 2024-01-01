package entity;

public class HomeLoan extends Loan {
    private String propertyAddress;
    private int propertyValue;


	public HomeLoan() {
		// TODO Auto-generated constructor stub
	}

	public HomeLoan(int loanId, int customerid, double principalAmount, double interestRate, int loanTerm,
			String loanType, String loanStatus,String propertyAddress,int propertyValue) {
		super(loanId, customerid, principalAmount, interestRate, loanTerm, loanType, loanStatus);
        this.setPropertyAddress(propertyAddress);
        this.setPropertyValue(propertyValue);
		
		// TODO Auto-generated constructor stub
	}

	public String getPropertyAddress() {
		return propertyAddress;
	}

	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}

	public int getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(int propertyValue) {
		this.propertyValue = propertyValue;
	}

}
