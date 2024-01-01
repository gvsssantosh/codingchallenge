package entity;

public class Loan {
    private int loanId;
    private int customerid;
    private double principalAmount;
    private double interestRate;
    private int loanTerm;
    private String loanType;
    private String loanStatus;

    // Constructors
    public Loan() {
        // Default constructor
    }

    public Loan(int loanId, int customerid, double principalAmount,
                double interestRate, int loanTerm, String loanType, String loanStatus) {
        this.loanId = loanId;
        this.customerid = customerid;
        this.principalAmount = principalAmount;
        this.interestRate = interestRate;
        this.loanTerm = loanTerm;
        this.loanType = loanType;
        this.loanStatus = loanStatus;
    }

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public double getPrincipalAmount() {
		return principalAmount;
	}

	public void setPrincipalAmount(double principalAmount) {
		this.principalAmount = principalAmount;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public int getLoanTerm() {
		return loanTerm;
	}

	public void setLoanTerm(int loanTerm) {
		this.loanTerm = loanTerm;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}
	@Override
	public String toString() {
		return "Loan { loan Id=" + loanId + ", customer Id=" + customerid + ", Principal Amount=" + principalAmount
				+ ", Interest Rate=" + interestRate + ", loan Term=" + loanTerm + ", loan Type=" + loanType
				+ ", Loan Status=" + loanStatus + "}";
	}

}

