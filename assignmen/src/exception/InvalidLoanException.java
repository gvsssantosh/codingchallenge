package exception;

import java.sql.SQLException;

public class InvalidLoanException extends Exception {
	public InvalidLoanException(String message) {
        super(message);
    }

	public InvalidLoanException(String string, SQLException e) {
		// TODO Auto-generated constructor stub
		super(string);
	}

}
