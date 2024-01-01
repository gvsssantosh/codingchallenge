package dao;
import entity.Loan;
import exception.InvalidLoanException;

public interface ILoanRepository {
    void applyLoan(Loan obj) throws InvalidLoanException;

    double calculateInterest(int loanId) throws InvalidLoanException;


    void loanStatus(int loanId) throws InvalidLoanException;

    void calculateEMI(int loanId) throws InvalidLoanException;

//    void calculateEMI(int loanId, double principalAmount, double interestRate, int loanTerm);

    void loanRepayment(int loanId, double amount) throws InvalidLoanException;

    void getAllLoan() throws InvalidLoanException;

    void getLoanById(int loanId) throws InvalidLoanException;	
	

}
