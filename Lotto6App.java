import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This app reads up to 49 numbers from a text file and generates
 * all possible combinations in rows of 6 for a lotto game. All
 * combinations are created according to certain criteria and
 * are printed to a text file.
 */
public class Lotto6App {

    public static void main(String[] args) {
        try (Scanner in = new Scanner(new File("C:\\Users\\USER\\Desktop\\lotto6in.txt"));
             PrintStream ps = new PrintStream("C:\\Users\\USER\\Desktop\\lotto6out.txt", StandardCharsets.UTF_8)) {

            final int LOTTO_SIZE = 6;
            int[] inputNumbers = new int[49];
            int pivot = 0;
            int[] result = new int[6];
            int num;
            int window;

            while ((num = in.nextInt()) != -1 && pivot <= 48) {
                inputNumbers[pivot++] = num;
            }

            int[] numbers = Arrays.copyOfRange(inputNumbers, 0, pivot);
            Arrays.sort(numbers);

            window = pivot - LOTTO_SIZE;
            for (int i = 0; i <= window; i++) {
                for (int j = i + 1; j <= window + 1; j++) {
                    for (int k = j + 1; k <= window + 2; k++) {
                        for (int l = k + 1; l <= window + 3; l++) {
                            for (int m = l + 1; m <= window + 4; m++) {
                                for (int n = m + 1; n <= window + 5; n++) {
                                    result[0] = numbers[i];
                                    result[1] = numbers[j];
                                    result[2] = numbers[k];
                                    result[3] = numbers[l];
                                    result[4] = numbers[m];
                                    result[5] = numbers[n];

                                    if (!isEvenGreater(result, 4) && !isOddGreater(result, 4) && !areConsecutive(result, 2)
                                            && !haveTheSameLastDigit(result, 3) && !areInSameTen(result, 3)) {

                                        ps.printf("%d %d %d %d %d %d\n",
                                                result[0], result[1], result[2], result[3], result[4], result[5]);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            System.out.println("Successfully printed to file");

        } catch (IOException e) {
            System.out.println("Error in I/O");
        }
    }

    /**
     * Checks whether the number of even integers is greater than
     * the given threshold.
     *
     * @param arr       the array of ints
     * @param threshold the given threshold
     * @return true if greater than/ equal to threshold,
     * false otherwise
     */
    public static boolean isEvenGreater(int[] arr, int threshold) {
        int even = 0;

        for (int num : arr) {
            if (num % 2 == 0) even++;
        }
        return even > threshold;
    }

    /**
     * Checks whether the number of odd integers is greater than
     * the given threshold.
     *
     * @param arr       the array of ints
     * @param threshold the given threshold
     * @return true if greater than/ equal to threshold,
     * false otherwise
     */
    public static boolean isOddGreater(int[] arr, int threshold) {
        int odd = 0;

        for (int num : arr) {
            if (num % 2 != 0) odd++;
        }
        return odd > threshold;
    }

    /**
     * Checks whether the number of consecutive integers is greater than
     * the given threshold.
     *
     * @param arr       the array of ints
     * @param threshold the given threshold
     * @return true if number of consecutive ints is greater than
     * the given threshold, false otherwise
     */
    public static boolean areConsecutive(int[] arr, int threshold) {
        for (int i = 0; i < arr.length - (threshold - 1); i++) {
            if (arr[i] + (threshold - 1) == arr[i + (threshold - 1)]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether the number of integers with the same last digit
     * is greater than the given threshold.
     *
     * @param arr       the array of ints
     * @param threshold the given threshold
     * @return true if number of ints with the same last digit is greater than
     * the given threshold, false otherwise
     */
    public static boolean haveTheSameLastDigit(int[] arr, int threshold) {
        int[] digitCounts = new int[10];

        for (int num : arr) {
            int lastDigit = num % 10;
            digitCounts[lastDigit]++;
            if (digitCounts[lastDigit] > threshold) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether the number of integers that are in the same ten
     * is greater than the given threshold.
     *
     * @param arr       the array of ints
     * @param threshold the given threshold
     * @return true if number of ints that are in the same ten is greater than
     * the given threshold, false otherwise
     */
    public static boolean areInSameTen(int[] arr, int threshold) {
        int[] tenCounts = new int[10];

        for (int num : arr) {
            int ten = num / 10;
            tenCounts[ten]++;
            if (tenCounts[ten] > threshold) {
                return true;
            }
        }
        return false;
    }
}

