import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner scan= new Scanner(System.in);

        int principal = 0;
        byte years = 0;
        float annualInterest = 0;


        while(true){
            System.out.print("Please enter the principal: ");
            principal = scan.nextInt();
            if(principal >= 1000 && principal <= 1_000_000)
                break;
            System.out.println("Enter a value between 1000 and 1000000");
        }

        while(true) {
            System.out.print("Please enter the annual interest rate: ");
            annualInterest = scan.nextFloat();
            if(annualInterest >= 1 && annualInterest <= 4.0)
                break;
            System.out.println("Please enter a value between 1.0 and 4.0 ");
        }

        while(true) {
            System.out.print("Please enter the period of the mortgage: ");
            years = scan.nextByte();
            if(years >= 1 && years <=30)
                // only need a byte for the number of years
                break;
            System.out.println("Please enter a value between 1 and 30");
        }

        scan.close();

        double mortgage = calculateMortgage(principal, annualInterest, years);

        String monthlyFormatted = NumberFormat.getCurrencyInstance().format(mortgage);

        System.out.print("Mortage: " + monthlyFormatted);
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
