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
		// 從二戰存錢到現在
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
		// 從1949存錢到昭和64, 存198178
		// 利率都是3%
		double expected = (198178 * Math.pow(1 + 0.03, 40));

		when(userA.depositAmount()).thenReturn(198178);
		when(userA.depositInterest(anyInt())).thenReturn(0.03);
		when(userA.expireEra()).thenReturn("昭和");
		when(userA.expireYear()).thenReturn(64);

		Deposit deposit = new Deposit(1949);
		String result = deposit.deposit(userA);
		assertEquals("平成1年: " + (int) expected, result);
	}

	@Test
	public void test1974To1994() {
		// 從老師的年代到助教的年代,存一年薪水18000
		// 利率都是5%

		when(userA.depositAmount()).thenReturn(18000);
		when(userA.depositInterest(anyInt())).thenReturn(0.05);
		when(userA.expireEra()).thenReturn("平成");
		when(userA.expireYear()).thenReturn(6);

		Deposit deposit = new Deposit(1974);
		String result = deposit.deposit(userA);
		assertEquals("平成6年: 47759", result);
	}

	@Test
	public void test1937To1945() {
		// 從二戰期間存40000元
		// 利率都是10%

		when(userA.depositAmount()).thenReturn(40000);
		when(userA.depositInterest(anyInt())).thenReturn(0.1);
		when(userA.expireEra()).thenReturn("昭和");
		when(userA.expireYear()).thenReturn(20);

		Deposit deposit = new Deposit(1937);
		String result = deposit.deposit(userA);
		assertEquals("昭和20年: 85743", result);
	}
}
