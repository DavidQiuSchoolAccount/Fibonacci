import java.util.function.*;

/**
 * Calculates the nth Fibonacci number.
 *
 * Methods are static, because having to construct a new object just to calculate Fibonacci seems wasteful.
 */
public class Fibonacci {
	/**
	 * Calculates Fibonacci tree-recursively.
	 */
	public static long fibR(int n) {
		if(n <= 0) { return 0; }
		else if(n <= 2) { return 1; }
		else { return fibR(n - 1) + fibR(n - 2); }
	}

	/**
	 * Calculates Fibonacci iteratively, in a loop.
	 * Time complexity: O(n)
	 */
	public static long fibI(int n) {
		long current = 0;
		long next = 1;
		for(int i = 0; i < n; i++) {
			long oldNext = next;
			next += current;
			current = oldNext;
		} return current;
	}

	/**
	 * Calculates Fibonacci using Binet's formula.
	 * Time complexity: O(1)
	 */
	public static long fibBinet(int n) {
		double phi = (1 + Math.sqrt(5)) / 2.0;
		double psi = (1 - Math.sqrt(5)) / 2.0;
		return (long) Math.round((Math.pow(phi, n) - Math.pow(psi, n)) / Math.sqrt(5));
	}

	private static void testFib(String name, Function<Integer, Long> func, int n) {
		long startTime = System.currentTimeMillis();
		long result = func.apply(n);
		int duration = (int) (System.currentTimeMillis() - startTime);
		System.out.println(name + ": fib(" + n + ") = " + result + " in " + duration + "ms.");
	}

	public static void main(String[] args) {
		try {
			int n = Integer.parseInt(args[0]);
			testFib("Tree-recursion", Fibonacci::fibR, n);
			testFib("Iterative loop", Fibonacci::fibI, n);
			testFib("Binet's formula", Fibonacci::fibBinet, n);

			// Example result:
			// $ java Fibonacci 45
			// Tree-recursion: fib(45) = 1134903170 in 2722ms.
			// Iterative loop: fib(45) = 1134903170 in 0ms.
			// Binet's formula: fib(45) = 1134903170 in 0ms.
		} catch(NumberFormatException e) {
			System.err.println("Pass a command-line argument to this program, specifying the nth Fibonacci number to calculate.");
			System.exit(1);
		}
	}
}