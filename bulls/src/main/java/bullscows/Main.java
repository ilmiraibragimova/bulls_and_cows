package bullscows;

import java.util.Scanner;
import java.util.Random;
public class Main {
    static void generateNum( int k, char[] numMy){
        // Scanner scan = new Scanner(System.in);
        // int k = Integer.parseInt(scan.next());
        long pseudoRandomNumber = System.nanoTime();
        int[] tab = new int[10];
        int i = 0;
        while (i < k) {
            int remain = (int)(pseudoRandomNumber % 10);
            if (tab[remain] == 0 && (!(i == 0 && remain == 0))) {
                numMy[i] = (char)(remain + '0');
                tab[remain] = 1;
                i++;
            }
            pseudoRandomNumber = pseudoRandomNumber / 10;
            if (pseudoRandomNumber == 0) {
                pseudoRandomNumber = System.nanoTime();
            }
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please, enter the secret code's length:");
        String k1 = scan.next();
        for (char ch1 : k1.toCharArray()) {
            if (!(ch1 >= '0' && ch1 <= '9')) {
                System.out.println("Error: \"" + k1 + "\" isn't a valid number.");
                return;
            }
        }
        int k = Integer.parseInt(k1);
        if (k <= 0 || k > 36) {
            System.out.println("Error: can't generate a secret number with a length of " + k + " because there aren't enough unique digits.");
            return;
        }
        System.out.println("Input the number of possible symbols in the code:");
        String number1 = scan.next();
        for (char ch : number1.toCharArray()) {
            if (!(ch >= '0' && ch <= '9')) {
                System.out.println("Error: \"" + number1 + "\" isn't a valid number.");
                return;
            }
        }
        int number = Integer.parseInt(number1);
        if (number > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return;
        }
        if (number < k) {
            System.out.println("Error: it's not possible to generate a code with a length of " + k + " with " + number + " unique symbols.");
            return;
        }
        System.out.println("Okay, let's start a game!");
        char[] numMy = new char[k];
        // generateNum(k, numMy);
        int[] tab = new int[number];
        int i;
        i = 0;
        Random generator = new Random();
        while (i < k) {
            int tmp = generator.nextInt(number);
            if (tab[tmp] == 0) {
                if (tmp < 10) {
                    numMy[i] = (char)(tmp + '0');
                }
                else {
                    numMy[i] = (char)(tmp - 10 + 'a');
                }
                tab[tmp] = 1;
                i++;
            }

        }
        System.out.print("The secret is prepared: ");
        //System.out.println(numMy);
        System.out.print(("*").repeat(k));
        System.out.print(" (0-" + (number < 9? number: 9));
        if (number > 10) {
            System.out.print(", a-" + (char)(number - 11 + 'a'));
        }
        System.out.println(").");
        int t = 0;
        int bool = 0;
        while (bool != k) {
            bool = 0;
            int cow = 0;
            System.out.println("Turn " + ++t + ":");
            String num = scan.next();
            char[] charsFromString = num.toCharArray();
            for (i = 0; i < k; i++) {
                for (int j = 0; j < k; j++) {
                    if (numMy[i] == charsFromString[j]) {
                        if (i == j) {
                            bool++;
                        } else {
                            cow++;
                        }
                    }

                }
            }
            System.out.print("Grade: ");
            if (bool != 0) {
                System.out.print(bool + " bull(s)");
                if (cow != 0) {
                    System.out.println(" and " + cow + " cow(s). ");
                } else {
                    System.out.println(".");
                }
            } else {
                if (cow != 0) {
                    System.out.println(cow + " cow(s). ");
                } else {
                    System.out.println("None. ");
                }
            }
        }
        //System.out.print(" The secret code is ");
        //System.out.println("The random secret number is ");
        //for (char ch : numMy) {
        // System.out.print(ch);
        // }
        System.out.print("Congratulations! You guessed the secret code.");

    }
}