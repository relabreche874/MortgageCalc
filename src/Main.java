import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        final byte YEAR = 12;
        final int PERCENT = 100;
        // made constants because these values should never change

        System.out.print("Please enter the prinicipal: ");
        Scanner scan= new Scanner(System.in);
        int principal = scan.nextInt();

        System.out.print("Please enter the annual interest rate: ");
        float annualInterest = scan.nextFloat();
        float monthlyInterestRate = annualInterest / YEAR / PERCENT;

        System.out.print("Please enter the period of the mortgage: ");
        byte years = scan.nextByte();
        // only need a byte for the number of years
        int numOfPayments = years * 12;

        scan.close();

        double mathPow = Math.pow(1 + monthlyInterestRate, numOfPayments);
        // did this to simplify the mortgage calculation

        double monthlyPay = principal * ((monthlyInterestRate * mathPow) / (mathPow - 1));
        String monthlyFormatted = NumberFormat.getCurrencyInstance().format(monthlyPay);

        System.out.print("Mortage: " + monthlyFormatted);

    }
}
