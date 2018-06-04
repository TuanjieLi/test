package functiontest;

/**
 * Turns out that intPower is always two times (but not exponentially) faster than MathPower.
 */
public class IntPow {
    public static void main(String[] args) {

        double base;
        int exponent;
        long time;

        //the initialisation is always slow, so we do some warm up here and do not count in their time cost.

        long intPowerTotalTimeCost = 0;
        long mathPowerTotalTimeCost = 0;

        //some warm up
        for(int i = 0 ; i < 10; i++) {
            base = Math.random();
            exponent = (int)(Math.random() * 10000000);
            time = System.nanoTime();
            Math.pow(base, exponent);
            mathPowerTotalTimeCost += (System.nanoTime() - time);

            //make sure there is no cache for this base and exponent pair.
            base = Math.random();
            exponent = (int)(Math.random() * 10000000);
            time = System.nanoTime();
            intPower(base, exponent);
            intPowerTotalTimeCost += (System.nanoTime() - time);
        }

        System.out.println("intPower warm up: " + intPowerTotalTimeCost);
        System.out.println("MathPower warm up: " + mathPowerTotalTimeCost);

        intPowerTotalTimeCost = 0;
        mathPowerTotalTimeCost = 0;

        //the real test.
        for(int i = 0 ; i < 1000000; i++) {
            base = Math.random();
            exponent = (int)(Math.random() * 10000000);
            time = System.nanoTime();
            Math.pow(base, exponent);
            mathPowerTotalTimeCost += (System.nanoTime() - time);

            //make sure there is no cache for this base and exponent pair.
            base = Math.random();
            exponent = (int)(Math.random() * 10000000);
            time = System.nanoTime();
            intPower(base, exponent);
            intPowerTotalTimeCost += (System.nanoTime() - time);
        }

        // you can see that intPower is always two times faster than MathPower.
        System.out.println("intPowerTotalTimeCost: " + intPowerTotalTimeCost);
        System.out.println("MathPowerTotalTimeCost: " + mathPowerTotalTimeCost);
    }

    private static double intPower(double base, int exponent) {
        if(exponent == 0) return 1;
        if(exponent == 1) return base;
        double half = intPower(base, exponent / 2);
        return ((exponent & 1) == 0) ? half * half : half * half * base;
    }

}
