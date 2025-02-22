import org.junit.Before;
import org.junit.Test;
//from Math import *;

import static org.junit.Assert.assertEquals;

import org.mockito.Mockito.*; // java mock: Mockito
import static org.mockito.Mockito.*;
import static org.mockito.AdditionalMatchers.*;

public class TestDeposit {
	Acount userA;

	@Before
	public void setUpMock() {
		userA = mock(Acount.class);
	}

	@Test
	public void test1945To2019() {
		// 從二戰存錢10000到現在"令和時代"
		when(userA.depositAmount()).thenReturn(10000);
		when(userA.depositInterest(gt(2000))).thenReturn(0.08);
		when(userA.depositInterest(leq(2000))).thenReturn(0.02);
		when(userA.expireEra()).thenReturn("令和");
		when(userA.expireYear()).thenReturn(1);

		Deposit deposit = new Deposit(1945);
		String result = deposit.deposit(userA);
		assertEquals("令和1年: 121125", result);
	}

	@Test
	public void test1949To1989() {
		// 從1949存錢到昭和24, 存198,178
		// 固定利率:3%
		int salary = 198178;
		double interestRate=0.03;
		double expected = (198178 * Math.pow(1 + interestRate, 40));
		
		when(userA.depositAmount()).thenReturn(198178);
		when(userA.depositInterest(anyInt())).thenReturn(0.03);
		when(userA.expireEra()).thenReturn("昭和");
		when(userA.expireYear()).thenReturn(24);

		Deposit deposit = new Deposit(1949);
		String result = deposit.deposit(userA);
		assertEquals("昭和24: " + (int) expected, result);
	}

	@Test
	public void test1974To1994() {
		// 從老師的年代到助教的年代,存一年薪水18000
		// 利率都是5%
		int salary = 18000;
		double interestRate=0.05;
		double expected = (salary * Math.pow(1 + interestRate, 20));
				   
		when(userA.depositAmount()).thenReturn(salary);
		when(userA.depositInterest(anyInt())).thenReturn(interestRate);
		when(userA.expireEra()).thenReturn("昭和");
		when(userA.expireYear()).thenReturn(49);

		Deposit deposit = new Deposit(1974);
		String result = deposit.deposit(userA);
		assertEquals("平成6年: 47759", result);
		//assertEquals("昭和49年:" + (int) expected, result);
	}

	@Test
	public void test1937To1945() {
		// 從二戰期間存40000元
		// 利率都是10%
		int salary = 40000;
		double interestRate=0.1;
		double expected = (salary * Math.pow(1 + interestRate, 8));
		
		when(userA.depositAmount()).thenReturn(salary);
		when(userA.depositInterest(anyInt())).thenReturn(interestRate);
		when(userA.expireEra()).thenReturn("昭和");
		when(userA.expireYear()).thenReturn(20);

		Deposit deposit = new Deposit(1937);
		String result = deposit.deposit(userA);
		assertEquals("昭和20年: 85743", result);
		//assertEquals("昭和20年:" + (int) expected, result);
	}
	
	@Test
	public void test2010To2020() {
		
		int salary = 10000;
		double interestRate=0.1;
		double expected = (salary * Math.pow(1 + interestRate, 10));
		
		when(userA.depositAmount()).thenReturn(salary);
		when(userA.depositInterest(anyInt())).thenReturn(interestRate);
		when(userA.expireEra()).thenReturn("平成");
		when(userA.expireYear()).thenReturn(22);

		Deposit deposit = new Deposit(2010);
		String result = deposit.deposit(userA);
		assertEquals("平成22年: 11000", result);
		//assertEquals("昭和20年:" + (int) expected, result);
	}
	
	@Test
	public void test2019To2023() {
		
		int salary = 100000;
		double interestRate=0.01;
		double expected = (salary * Math.pow(1 + interestRate, 4));
		
		when(userA.depositAmount()).thenReturn(salary);
		when(userA.depositInterest(anyInt())).thenReturn(interestRate);
		when(userA.expireEra()).thenReturn("令和");
		when(userA.expireYear()).thenReturn(4);

		Deposit deposit = new Deposit(2019);
		String result = deposit.deposit(userA);
		assertEquals("令和4年: 104000", result);
		//assertEquals("昭和20年:" + (int) expected, result);
	}
}
