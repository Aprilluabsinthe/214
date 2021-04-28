package edu.cmu.cs.cs214.rec13;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;

import static java.math.BigInteger.ONE;

/**
 *  Prints the first 20 Mersenne primes.  A Mersenne prime is a prime
 *  number of the form 2^p - 1, for some p that is itself prime.
 */
public class ParallelMersennePrimes {
    private static final int LIMIT = 20; // Number of Mersenne primes to find
    private static final BigInteger TWO = new BigInteger("2");

    public static void main(String[] args) {
        Instant startTime = Instant.now(); // Record the start time for simple benchmarking

        // TODO: Use Java concurrency tools to improve the performance of Mersenne prime-finding here.

        Duration taskTime = Duration.between(startTime, Instant.now());
        System.out.printf("It took %d.%d seconds to find %d Mersenne primes.\n",
                taskTime.getSeconds(), taskTime.toMillisPart(), LIMIT);
    }
}
