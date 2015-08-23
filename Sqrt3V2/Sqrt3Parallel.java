package Sqrt3V2;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Sqrt3Parallel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sqrt3Parallel sqrt = new Sqrt3Parallel();
		/*for(int i = 9; i < 11; i++){
			for(int j = 9; j < 10; j++) {
				int rounding = i*100;
				int iterations = j*10;
				long start = System.currentTimeMillis();
				int digits = sqrt.start(rounding,iterations );
				long end = System.currentTimeMillis();
				System.out.println(rounding + ";" + iterations + ";" + (end-start) + ";" + digits);
			}
		}*/
		int rounding = 1500;
		int iterations = 5000;
		long start = System.currentTimeMillis();
		int digits = sqrt.start(rounding,iterations );
		long end = System.currentTimeMillis();
		System.out.println(rounding + ";" + iterations + ";" + (end-start) + ";" + digits);
	}

	private int start(int rounding, int iterations) {
		BigDecimal three = new BigDecimal("3");
		BigDecimal two = new BigDecimal("2");
		MathContext division = new MathContext(
				(int) Math.round(rounding), RoundingMode.HALF_UP);
		
		BigDecimal x0 = new BigDecimal("1.73");
		BigDecimal x1 = new BigDecimal("1.73");

		for(int i = 0; i < iterations; i++) {
			/*x1 = x0.pow(2);
			System.out.println("1: " + x1.toPlainString());
			x1 = x1.subtract(three);
			System.out.println("2: " + x1.toPlainString());
			x1 = x1.divide(x0.multiply(two), division);
			System.out.println("3: " + x1.toPlainString());
			x0 = x0.subtract(x1);
			System.out.println("x0: " + x1.toPlainString());*/
			x1 = x0.subtract((x0.pow(2).subtract(three)).divide(x0.multiply(two), division));
			x0 = x1;
			if(i % 10 == 0 && i > 10) { System.out.println(i); 
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date(System.currentTimeMillis());

			PrintWriter writer;
			try {
				writer = new PrintWriter("Sqrt3 - i " + i + " date " + dateFormat.format(date) + ".txt", "UTF-8");
				writer.println(x0.toString());
				writer.println(x1.toString());
				writer.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
}
		}
		String digits1 = x1.toString();
		x1 = x0.subtract((x0.pow(2).subtract(three)).divide(x0.multiply(two), division));
		x0 = x1;
		String digits2 = x1.toString();

		//System.out.println("Sqrt(3) = " + x1.toPlainString());
	
		
		return checkDigits(digits1, digits2);
	}
	
	public int checkDigits(String first, String second) {
		int i = 0;
		for(i = 0; i < first.length(); i++) {
			if(i >= second.length()) {
				//System.out.println("correct digits at least:" + i + " (Ran out of digits to compare to)");
				break;
			}
			if(first.charAt(i) != second.charAt(i)) {
				//System.out.println("correct digits:" + i);
				break;
			}
		}
		//System.out.println("correct digits at least:" + i + " (Ran out of digits to compare to)");
		return i;
	}
}
