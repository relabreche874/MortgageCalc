import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        int principal = (int) readNumber("Principal: ", 1000, 1_000_000);
        float annualInterest = (float) readNumber("Annual Interest Rate: ", 1, 3.92);
        byte years = (byte) readNumber("Period of Mortgage: ", 1, 30);

        double mortgage = calculateMortgage(principal, annualInterest, years);

        String monthlyFormatted = NumberFormat.getCurrencyInstance().format(mortgage);

        System.out.print("Mortage: " + monthlyFormatted);
    }

    public static double readNumber(String prompt, double min, double max){
        Scanner scan = new Scanner(System.in);
        double value;
        while(true) {
            System.out.print(prompt);
            value = scan.nextFloat();
            if(value >= min && value <= max)

                break;
            System.out.println("Please enter a value between " + min + " and " + max);
        }
        scan.close();
        return value;
    }

    public static double calculateMortgage(int principal,
                                           float annualInterest,
                                           byte years){
        final byte MONTHS_IN_YEAR = 12;
        final int PERCENT = 100;
        // made constants because these values should never change

        float monthlyInterestRate = annualInterest / MONTHS_IN_YEAR / PERCENT;
        short numOfPayments = (short)(years * MONTHS_IN_YEAR);
        double mathPow = Math.pow(1 + monthlyInterestRate, numOfPayments);
        // did this to simplify the mortgage calculation
        double mortgage = principal * ((monthlyInterestRate * mathPow) / (mathPow - 1));

        return mortgage;
    }
}
