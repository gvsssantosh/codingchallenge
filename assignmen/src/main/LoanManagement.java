package main;
import java.util.*;
import entity.*;
import java.sql.*;
import exception.*;

import dao.ILoanRepository;
import dao.ILoanRepositoryImpl;

public class LoanManagement
{

	public static void main(String[] args) throws InvalidLoanException, ClassNotFoundException, SQLException
	{
	
        
            
            Scanner scanner = new Scanner(System.in);

            while (true) 
            {
                System.out.println("\n===== Loan Management System =====");
                System.out.println("1. Apply for a Loan");
                System.out.println("2. Get All Loans");
                System.out.println("3. Get Loan by ID");
                System.out.println("4. Repay Loan");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        applyLoan();
                        break;
                    case 2:
                        getAllLoan();
                        break;
                    case 3:
                        getLoanById();
                        break;
                    case 4:
                        repayLoan();
                        break;
                    case 5:
                        System.out.println("Exiting Loan Management System. Thank you!");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        }
    


    public static void applyLoan() throws InvalidLoanException, ClassNotFoundException, SQLException {
    	ILoanRepository loanRepository = new ILoanRepositoryImpl();
    	//loanRepository.applyLoan();
    	Scanner sc=new Scanner(System.in);
    	System.out.print("Enter LoanID : ");
        int loanid=sc.nextInt();
        
        System.out.print("Enter  customerid: ");
        int customerid=sc.nextInt();
        
        System.out.print("Enter principleamount : ");
        double principleamount=sc.nextDouble();
        
        System.out.print("Enter interestrate : ");
        double interestrate=sc.nextDouble();
        System.out.print("Enter loanterm : ");
        int loanterm=sc.nextInt();
        System.out.print("Enter loantype- 1:carloan 2:houseloan");
        int iloantype=sc.nextInt();
        if(iloantype==1) {
        	
        	String loantype="carloan";
        	System.out.print("Enter loanstatus Pending/ Approved : ");
        	
        	String loanstatus=sc.next();
        	System.out.print("Enter carmodel : ");
        	String carmodel=sc.next();
        	System.out.print("Enter carvalue : ");
        	int carvalue=sc.nextInt();
        	Loan obj=new CarLoan(loanid,customerid,principleamount,interestrate,loanterm,loantype,loanstatus,carmodel,carvalue);
        	loanRepository.applyLoan(obj);
        }
//    	public CarLoan(int loanId, int customerid, double principalAmount, double interestRate, int loanTerm,
//    			String loanType, String loanStatus,String carModel,int carValue)
        if(iloantype==2) {
        	
        	String loantype="Homeloan";
        	System.out.print("Enter loanstatus Pending/ Approved : ");
        	
        	String loanstatus=sc.next();
        	System.out.print("Enter propety address : ");
        	
        	String paddress=sc.next();
        	System.out.print("Enter property value : ");
        	int pvalue=sc.nextInt();
        	Loan obj=new HomeLoan(loanid,customerid,principleamount,interestrate,loanterm,loantype,loanstatus,paddress,pvalue);
        	loanRepository.applyLoan(obj);
//        	public HomeLoan(int loanId, int customerid, double principalAmount, double interestRate, int loanTerm,
//        			String loanType, String loanStatus,String propertyAddress,int propertyValue)
        }
        
        

        
        
    	
    	
    
    }
    private static void getAllLoan() throws ClassNotFoundException, SQLException, InvalidLoanException {
    	ILoanRepository loanRepository = new ILoanRepositoryImpl();
    	loanRepository.getAllLoan();
    }
    

    private static void getLoanById() throws ClassNotFoundException, SQLException {
        System.out.print("Enter loan ID: ");
        ILoanRepository loanRepository = new ILoanRepositoryImpl();
        Scanner scanner = new Scanner(System.in);
        int loanId = scanner.nextInt();
        try {
            loanRepository.getLoanById(loanId);
        } catch (InvalidLoanException e) {
            System.out.println("Error getting loan by ID: " + e.getMessage());
        }
    }

    private static void repayLoan() throws InvalidLoanException, ClassNotFoundException, SQLException {
    	ILoanRepository loanRepository = new ILoanRepositoryImpl();
    	Scanner scanner = new Scanner(System.in);
    	int a=scanner.nextInt();
    	double b=scanner.nextDouble();
    	loanRepository.loanRepayment(a,b);
       
    }
    
    
    
}