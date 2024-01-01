package entity;

public class CarLoan extends Loan {
	private String carModel;
	private int carValue;

	public CarLoan() {
		// TODO Auto-generated constructor stub
	}

	public CarLoan(int loanId, int customerid, double principalAmount, double interestRate, int loanTerm,
			String loanType, String loanStatus,String carModel,int carValue) 
	{
		super(loanId, customerid, principalAmount, interestRate, loanTerm, loanType, loanStatus);
		// TODO Auto-generated constructor stub
		this.setCarModel(carModel);
        this.setCarValue(carValue);
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public int getCarValue() {
		return carValue;
	}

	public void setCarValue(int carValue) {
		this.carValue = carValue;
	}

}
