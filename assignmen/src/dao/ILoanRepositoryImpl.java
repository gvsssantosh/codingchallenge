package dao;
import java.util.*;
import entity.Loan;
import java.sql.*;
import exception.InvalidLoanException;
import util.DBConnection;

public class ILoanRepositoryImpl implements ILoanRepository {

    private Connection connection;  // Assume that the connection is obtained from DBConnUtil

    // Constructor to initialize the connection
    public ILoanRepositoryImpl() throws ClassNotFoundException, SQLException {
        //connection = DBConnection.getConnection();
    	//not working
    	Class.forName("com.mysql.cj.jdbc.Driver");

    	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assign","root","password");
    }


	@Override
	public void applyLoan(Loan obj) throws InvalidLoanException 
	{
		// TODO Auto-generated method stub
        try {
            // Code to insert the loan into the database
            String insertQuery = "INSERT INTO loan (loanId, customerId, principalAmount, interestRate, loanTerm, loanType, loanStatus) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) 
            {
            	
                preparedStatement.setInt(1, obj.getLoanId());

                preparedStatement.setInt(2, obj.getCustomerid());
                preparedStatement.setDouble(3, obj.getPrincipalAmount());
                
                preparedStatement.setDouble(4, obj.getInterestRate());
                
                preparedStatement.setInt(5, obj.getLoanTerm());
                
                preparedStatement.setString(6, obj.getLoanType());
                
                preparedStatement.setString(7, obj.getLoanStatus());  // Initial status is pending

                // Confirm with the user before storing
                // Implement logic to get confirmation (Yes/No) from the user here

                // Execute the query if confirmed
                int rowsAffected = preparedStatement.executeUpdate();
                if(rowsAffected>0) {
                	System. out. println("loan updated");;
                }
                //return rowsAffected > 0;
            }
        } catch (SQLException e) {
            throw new InvalidLoanException("Error applying for loan.", e);
        }

    }

	@Override
	public double calculateInterest(int loanId) throws InvalidLoanException {
		// TODO Auto-generated method stub
		try {
            String selectQuery = "SELECT principalAmount, interestRate, loanTerm FROM loan WHERE loanId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setInt(1, loanId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        double principalAmount = resultSet.getDouble("principalAmount");
                        double interestRate = resultSet.getDouble("interestRate");
                        int loanTerm = resultSet.getInt("loanTerm");

                        // Calculate interest using the formula
                        return (principalAmount * interestRate * loanTerm) / 12;
                    } else {
                        throw new InvalidLoanException("Loan not found with ID: " + loanId);
                    }
                }
            }
        } catch (SQLException e) {
            throw new InvalidLoanException("Error calculating interest.", e);
        }
    }
	
//			@Override
//	public double calculateInterest(int loanId, double principalAmount, double interestRate, int loanTerm) {
//		// TODO Auto-generated method stub
//		return super.calculateInterest(loanId, principalAmount, interestRate, loanTerm);
//		return 0;
//	}	

	@Override
	public void loanStatus(int loanId) throws InvalidLoanException {
		// TODO Auto-generated method stub
		try {
            String selectQuery = "SELECT principalAmount, interestRate, creditScore FROM loan inner join customer on loan.customerId=customer.customerId WHERE loanId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setInt(1, loanId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        double principalAmount = resultSet.getDouble("principalAmount");
                        double interestRate = resultSet.getDouble("interestRate");
                        int creditScore = resultSet.getInt("creditScore");
                        if(creditScore>650) {System. out. println("loan accepted");}
                        else {System. out. println("loan accepted");}

                        // Calculate interest using the formula
                        
                    } else {
                        throw new InvalidLoanException("Loan not found with ID: " + loanId);
                    }
                }
            }
        } catch (SQLException e) {
            throw new InvalidLoanException("Error loan status.", e);
        		
        }
	}

	@Override
	public void calculateEMI(int loanId) throws InvalidLoanException {
		try {
            String selectQuery = "SELECT principalAmount, interestRate, loanTerm FROM loan WHERE loanId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setInt(1, loanId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        double principalAmount = resultSet.getDouble("principalAmount");
                        double interestRate = resultSet.getDouble("interestRate");
                        int n = resultSet.getInt("loanTerm");
                        double r=(interestRate/12/100);                   

                        // Calculate interest using the formula
//                        EMI = [P * R * (1+R)^N] / [(1+R)^N-1]
//                        		1. EMI: The Equated Monthly Installment.
//                        		2. P: Principal Amount (Loan Amount).
//                        		3. R: Monthly Interest Rate (Annual Interest Rate / 12 / 100).
//                        		4. N: Loan Tenure in months.
                        double emi=(principalAmount*r*Math.pow(1+r,n))/(Math.pow(1+r,n-1));
                        
                        System. out. println (emi);
                    } else {
                        throw new InvalidLoanException("Loan not found with ID: " + loanId);
                    }
                }
            }
        } catch (SQLException e) {
            throw new InvalidLoanException("Error calculating interest.", e);
        }
		
		// TODO Auto-generated method stub
		
	}
//	@Override
//	public void calculateEMI(int loanId, double principalAmount, double interestRate, int loanTerm) {
//		// TODO Auto-generated method stub
//		super.calculateEMI(loanId, principalAmount, interestRate, loanTerm);
//	}

	

	@Override
	public void loanRepayment(int loanId, double amount) throws InvalidLoanException {
		// TODO Auto-generated method stub
		try {
            String selectQuery = "SELECT principalAmount, interestRate, loanTerm FROM loan WHERE loanId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setInt(1, loanId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        double principalAmount = resultSet.getDouble("principalAmount");
                        double interestRate = resultSet.getDouble("interestRate");
                        int n = resultSet.getInt("loanTerm");
                        double r=(interestRate/12/100);                   

                        // Calculate interest using the formula
//                        EMI = [P * R * (1+R)^N] / [(1+R)^N-1]
//                        		1. EMI: The Equated Monthly Installment.
//                        		2. P: Principal Amount (Loan Amount).
//                        		3. R: Monthly Interest Rate (Annual Interest Rate / 12 / 100).
//                        		4. N: Loan Tenure in months.
                        double emi=(principalAmount*r*Math.pow(1+r,n))/(Math.pow(1+r,n-1));
                        
                        int noOfEmi= (int) (principalAmount/emi);
                        if(emi>amount) {
                        	System. out. println ("payment rejected");
                        	
                        }
                        else
                        {System. out. println ("payment accepted:remaining emi");}
                        
                    } else {
                        throw new InvalidLoanException("Loan not found with ID: " );
                    }
                }
            }
        } catch (SQLException e) {
            throw new InvalidLoanException("Error calculating interest.", e);
        }
	}
	@Override
	public 	void getAllLoan() throws InvalidLoanException {
		try {
            String selectQuery = "SELECT loanId,customerId,principalAmount FROM loan";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                	if(resultSet.next()) 
                	{
                		while (resultSet.next()) 
                		{
                			int loanid = resultSet.getInt("loanId");
                			int customerid = resultSet.getInt("customerId");
                			double principalAmount = resultSet.getDouble("principalAmount");
                			
                			
                			System. out. println ("loan details:"+"customerid"+customerid+"loanid"+loanid+"principalAmount"+principalAmount);                                      
                    
                        
                		}
                    } 
                	else{
                        throw new InvalidLoanException("Loans not found ");
                    }
                }
            }
        } catch (SQLException e) {
            throw new InvalidLoanException("Error calculating interest.", e);
        }
		// TODO Auto-generated method stub
		
	}	

	@Override
	public void getLoanById(int loanId) throws InvalidLoanException {
		try {
            String selectQuery = "SELECT principalAmount, interestRate, loanTerm FROM loan WHERE loanId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setInt(1, loanId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        double principalAmount = resultSet.getDouble("principalAmount");
                        double interestRate = resultSet.getDouble("interestRate");
                        int n = resultSet.getInt("loanTerm");
                        System. out. println ("loan details"+"principalAmount"+principalAmount+"interestRate"+interestRate+"loanterm"+n);                                      


                    
                        
                    } else {
                        throw new InvalidLoanException("Loan not found with ID: " + loanId);
                    }
                }
            }
        } catch (SQLException e) {
            throw new InvalidLoanException("Error calculating interest.", e);
        }
		// TODO Auto-generated method stub
		
	}

}
