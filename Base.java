import java.util.Random;

public class Base {
// ----------------NEWTON-RAPHSON---------------------------
	public static double FunctionF(int order2, int order1, int constant, double x) {
		return (order2 * (Math.pow(x, 2))) + (order1 * x) + constant;
	}

	public static double DerivativeOfF(int order2, int order1, double x) {
		return (2 * order2 * x) + order1;
	}

	public static void NewtonRaphson(int order2, int order1, int constant, int lowerBound, int upperBound,
			double toleranceValue) {
		double givenError, rold, rnew, error;
		givenError = toleranceValue;
		Random rnd = new Random();
		rold = rnd.nextInt(upperBound - lowerBound + 1) + lowerBound;

		while (rold == 0) {
			rold = rnd.nextInt(upperBound - lowerBound + 1) + lowerBound;
		}

		boolean flag = true;
		int IterationNumber = 1;
		while (flag) {

			if (DerivativeOfF(order2, order1, rold) == 0) {
				System.out.println("Derivative of F is 0.");
				break;
			}
			rnew = rold - (FunctionF(order2, order1, constant, rold) / DerivativeOfF(order2, order1, rold));
			error = Math.abs((rnew - rold));

			System.out.println(
					"Rold: " + rold + " Rnew: " + rnew + " Error: " + error + " Iteration Number: " + IterationNumber);
			System.out.println();
			IterationNumber++;
			rold = rnew;

			if (givenError > error) {
				flag = false;
			} else if (IterationNumber > 100) {
				System.out.println("There is no convergence.");
				flag = false;
			}

		}
	}

//--------------------VON-MISES--------------------------------
	public static void VonMises(int order2, int order1, int constant, int lowerBound, int upperBound,
			double toleranceValue) {
		double givenError, rold, rnew, error;
		givenError = toleranceValue;
		Random rnd = new Random();
		rold = rnd.nextInt(upperBound - lowerBound + 1) + lowerBound;

		while (rold == 0) {
			rold = rnd.nextInt(upperBound - lowerBound + 1) + lowerBound;
		}

		boolean flag = true;
		int IterationNumber = 1;
		double FirstDerivative = DerivativeOfF(order2, order1, rold);
		while (flag) {

			if (FirstDerivative == 0) {
				System.out.println("Derivative of F is 0.");
				break;
			}

			rnew = rold - (FunctionF(order2, order1, constant, rold) / FirstDerivative);
			error = Math.abs((rnew - rold));

			System.out.println(
					"Rold: " + rold + " Rnew: " + rnew + " Error: " + error + " Iteration Number: " + IterationNumber);
			System.out.println();
			IterationNumber++;
			rold = rnew;

			if (givenError > error) {
				flag = false;
			} else if (IterationNumber > 100) {
				System.out.println("There is no convergence.");
				flag = false;
			}

		}
	}

//--------------------BISECTION--------------------------------
	public static void Bisection(int order2, int order1, int constant, double a, double b, double toleranceValue) {
		int IterationNumber = 1;
		double error = 1000000;
		boolean flag = true;

		if (FunctionF(order2, order1, constant, a) * FunctionF(order2, order1, constant, b) >= 0) {
			System.out.println("Wrong start values.");
			flag = false;
		}

		double Root = (a + b) / 2;
		double prevRoot = 0;
		while (error >= toleranceValue && flag) {

			Root = (a + b) / 2;
			error = Math.abs(Root - prevRoot);
			prevRoot = Root;
			System.out.println("a: " + a + " b: " + b + " Root: " + Root + " Error: " + error + " Iteration Number: "
					+ IterationNumber);
			System.out.println();
			if (FunctionF(order2, order1, constant, Root) == 0.0) {
				System.out.println(IterationNumber + " " + Root + "    0");
				break;
			}

			else if (FunctionF(order2, order1, constant, Root) * FunctionF(order2, order1, constant, a) < 0)
				b = Root;
			else
				a = Root;

			if (IterationNumber > 100) {
				System.out.println("There is no convergence.");
				flag = false;
			}
			IterationNumber++;
		}
	}

//--------------------REGULAFALSI------------------------------a=lower
	public static void RegulaFalsi(int order2, int order1, int constant, double a, double b, double toleranceValue) {
		int IterationNumber = 1;
		double error = 1000000;
		boolean flag = true;

		if (FunctionF(order2, order1, constant, a) * FunctionF(order2, order1, constant, b) >= 0) {
			System.out.println("Wrong start values.");
			flag = false;
		}
		if ((FunctionF(order2, order1, constant, b) - FunctionF(order2, order1, constant, a) == 0)) {
			System.out.println("There is no convergence.");
			flag = false;
		}
		double Root = ((a * FunctionF(order2, order1, constant, b)) - (b * FunctionF(order2, order1, constant, a)))
				/ (FunctionF(order2, order1, constant, b) - FunctionF(order2, order1, constant, a));
		double prevRoot = 0;
		while (error >= toleranceValue && flag) {

			if ((FunctionF(order2, order1, constant, b) - FunctionF(order2, order1, constant, a) == 0)) {
				System.out.println("There is no convergence.");
				flag = false;
				break;
			}
			Root = ((a * FunctionF(order2, order1, constant, b)) - (b * FunctionF(order2, order1, constant, a)))
					/ (FunctionF(order2, order1, constant, b) - FunctionF(order2, order1, constant, a));
			error = Math.abs(Root - prevRoot);
			prevRoot = Root;
			System.out.println("a: " + a + " b: " + b + " Root: " + Root + " Error: " + error + " Iteration Number: "
					+ IterationNumber);
			System.out.println();
			if (FunctionF(order2, order1, constant, Root) == 0.0) {
				System.out.println(IterationNumber + " " + Root + "    0");
				break;
			}

			else if (FunctionF(order2, order1, constant, Root) * FunctionF(order2, order1, constant, a) < 0)
				b = Root;
			else
				a = Root;

			if (IterationNumber > 100) {
				System.out.println("There is no convergence.");
				flag = false;
			}
			IterationNumber++;
		}

	}

//--------------------FIND-ROOT--------------------------------
	public static void findRoot(int methodIndex, int order2, int order1, int constant, int lowerBound, int upperBound,
			double toleranceValue) {
		if (methodIndex == 1) {// Newton-Raphson Method
			NewtonRaphson(order2, order1, constant, lowerBound, upperBound, toleranceValue);
		} else if (methodIndex == 2) {// Von-Mises Method
			VonMises(order2, order1, constant, lowerBound, upperBound, toleranceValue);
		} else if (methodIndex == 3) {// Bisection Method
			Bisection(order2, order1, constant, lowerBound, upperBound, toleranceValue);
		} else if (methodIndex == 4) {// Regula Falsi Method
			RegulaFalsi(order2, order1, constant, lowerBound, upperBound, toleranceValue);
		} else
			System.out.println("Unexpected method index.");
	}

	public static void main(String[] args) {
		findRoot(4, 1, -9, 8, 2, 9, 0.001);

	}
}
