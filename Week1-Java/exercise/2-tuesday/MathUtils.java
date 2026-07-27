public class MathUtils {
    public static long factorial(int n) {
        long answer = 1;
        if(n<=1) return answer;
        for (int i = n; i >= 1; i--) {
            answer*=i;
        }
        return answer;
    }

    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n % 2 != 0 && n % 3 != 0 && n % 5 != 0 && n % 7 != 0) {
            return true;
        }
        return false;
    }

    public static double celsiusToFahrenheit(double celsius){
        return (celsius * 9/5) + 32;
    }

    public static int findMax(int[] arr){
        int max = Integer.MIN_VALUE;
        if(arr == null) return max;
        for(int i : arr){
            if(i>max){
                max = i;
            }
        }
        return max;
    }

    public static double findMax(double[] arr){
        double max = Integer.MIN_VALUE;
        if(arr == null) return max;
        for(double i : arr){
            if(i>max){
                max = i;
            }
        }
        return max;
    }

    public static void main(String[] args){
        System.out.println("Factorial of 5: " + MathUtils.factorial(5));
        System.out.println("Is 2 prime? " + MathUtils.isPrime(2));
        System.out.println("Farenheit of 100 Celsius: " + MathUtils.celsiusToFahrenheit(100));
        System.out.println("Max of 2 and 3: " + MathUtils.findMax(new int[]{2, 3}));
        System.out.println("Max of 4.0 and 3.0: " + MathUtils.findMax(new double[]{4.0, 3.0}));
    }

}
