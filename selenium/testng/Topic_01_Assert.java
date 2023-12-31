package testng;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;

public class Topic_01_Assert {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String fullName = "Automation testing";
		
		// asser true false trả dữ liêu boolean sẽ đi cùng với các webelement có kiểu dữ liệu trả về là boolean 
		
		// Mong đợi 1 điều trả về là đúng
		Assert.assertTrue(fullName.contains("Auto"));
		
		// Mong đợi 1 điều trả về là sai
		
		Assert.assertFalse(fullName.contains("Loan"));
		
		
		// Assert equal sẽ đi cùng với gettext, get dữ liệu
		
		// mong đợi 2 điều kiên bằng nhau
		// expect bằng nhau với actual result
		
		Assert.assertEquals(fullName, "Automation testing");
	}

}
