import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    final static byte MONTHS_IN_YEAR = 12;
    final static int PERCENT = 100;
    // made constants because these values should never change
    public static void main(String[] args){
        int principal = (int) readNumber("Principal: ", 1000, 1_000_000);
        float annualInterest = (float) readNumber("Annual Interest Rate: ", 1, 5);
        byte years = (byte) readNumber("Period of Mortgage: ", 1, 30);

        double mortgage = calculateMortgage(principal, annualInterest, years);
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("\nMORTGAGE" + "\n--------" + "\nMonthly Payments: " + mortgageFormatted);

        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");

        for(short month = 1; month <= years * MONTHS_IN_YEAR; month++){
            double balance = calculateBalance(principal, annualInterest, years, month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));

            }
        }

    public static double readNumber(String prompt, double min, double max){
        // a new prompt will be passed each time the method is called
        // as well as the min and max values
        Scanner scan = new Scanner(System.in);
        double value;
        while(true) {
            System.out.print(prompt);
            value = scan.nextFloat();
            if(value >= min && value <= max)
                // the values passed to the method call are used here
                break;
            System.out.println("Please enter a value between " + min + " and " + max);
        }
        return value;
    }

    public static double calculateBalance(int principal,
                                          float annualInterest,
                                          byte years,
                                          short numberOfPaymentsMade){

        float monthlyInterestRate = annualInterest / MONTHS_IN_YEAR / PERCENT;
        short numOfPayments = (short)(years * MONTHS_IN_YEAR);

        double balance = principal *
                (Math.pow(1 + monthlyInterestRate, numOfPayments) - Math.pow(1 + monthlyInterestRate, numberOfPaymentsMade))
                / (Math.pow(1 + monthlyInterestRate, numOfPayments) - 1);

        return balance;

    }
        // schedule = principal ((1 + month_interest)^num_of_payments)- (1 + month_interest)^payments_made) / ((1 + month_interest)^num_of_payments - 1))

    public static double calculateMortgage(int principal,
                                           float annualInterest,
                                           byte years){


        float monthlyInterestRate = annualInterest / MONTHS_IN_YEAR / PERCENT;
        short numOfPayments = (short)(years * MONTHS_IN_YEAR);
        double mathPow = Math.pow(1 + monthlyInterestRate, numOfPayments);
        // did this ^^^  to simplify the mortgage calculation
        double mortgage = principal * ((monthlyInterestRate * mathPow) / (mathPow - 1));

        return mortgage;
    }
}
