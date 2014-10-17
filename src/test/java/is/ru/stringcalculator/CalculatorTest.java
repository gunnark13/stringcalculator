package is.ru.stringcalculator;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CalculatorTest {

	public static void main(String args[]) {
      org.junit.runner.JUnitCore.main("is.ru.stringcalculator.CalculatorTest");
    }

	@Test
	public void testEmptyString() {
		assertEquals(0, Calculator.add(""));
	}

	@Test
	public void testOneNumber() {
		assertEquals(1, Calculator.add("1"));
	}

	@Test
	public void testTwoNumbers() {
		assertEquals(3, Calculator.add("1,2"));
	}	

	@Test
    public void testMultipleNumbers(){
    	assertEquals(6, Calculator.add("1,2,3"));
    }

    	@Test
    public void testMultipleNumbersWithNewlineAndCommaAsSeperators(){
    	assertEquals(6, Calculator.add("1\n2,3"));
    }

       	@Test
    public void testChangeTheDelimiterInBeginningOfString(){
    	assertEquals(3, Calculator.add("//;\n1;2"));
    }

    @Rule
	public ExpectedException thrown = ExpectedException.none();
 
	@Test
	public void throwsExceptionWhenNegativeNumbersAreGiven() {
	    thrown.expect(IllegalArgumentException.class);
	    thrown.expectMessage(equalTo("negatives not allowed: -1"));
	    calculator.add("-1,2");
	}
}