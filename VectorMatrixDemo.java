package vectorCalculus;

public class VectorMatrixDemo {

	public static void main(String[] args) {

		// ----------------------------
		// Vector setup
		// ----------------------------
		Vector3 u = new Vector3(1, 2, 3);
		Vector3 v = new Vector3(3, 2, 1);
		Vector3 w = new Vector3(2, 2, 2);

		printHeader("VECTOR OPERATIONS");
		System.out.println("u = " + u);
		System.out.println("v = " + v);
		System.out.println("w = " + w);
		System.out.println();

		System.out.println("u + v = " + u.add(v));
		System.out.println("u - v = " + u.subtract(v));
		System.out.println("distance(u, v) = " + u.distanceTo(v));
		System.out.println("u dot v = " + u.dot(v));
		System.out.println("u cross v = " + u.cross(v));
		System.out.println("|u cross v| = " + u.cross(v).norm());
		System.out.println("||u||^2 = " + u.normSq());
		System.out.println("unit(u) = " + u.unitVec());
		System.out.println("proj_u onto v = " + u.projOnto(v));
		System.out.println("ZERO = " + Vector3.ZERO);
		System.out.println();

		// ----------------------------
		// Matrix setup
		// ----------------------------
		Matrix3 A = new Matrix3(u, v, w);
		Vector3 x = new Vector3(5, 4, 6);

		printHeader("MATRIX OPERATIONS");
		System.out.println("A = ");
		System.out.println(A);

		System.out.println("x = " + x);
		System.out.println("Ax = " + A.multiply(x));
		System.out.println("Av = " + A.multiply(v));
		System.out.println("det(A) = " + A.determinant());
		System.out.println("A^T = ");
		System.out.println(A.transpose());

		try {
			System.out.println("A^(-1) = ");
			System.out.println(A.inverse());
			System.out.println("A * A^(-1) = ");
			System.out.println(A.multiply(A.inverse()));
		} catch (IllegalStateException e) {
			System.out.println("A is not invertible: " + e.getMessage());
		}

		System.out.println("A^T A x = " + A.transpose().multiply(A).multiply(x));
		System.out.println();
		Vector3 residual = A.multiply(x).subtract(x);
		System.out.println("Residual Ax - x = " + residual);

		// ----------------------------
		// Simple diagonal scaling matrix
		// ----------------------------
		Matrix3 B = new Matrix3(new Vector3(1, 0, 0), new Vector3(0, 2, 0), new Vector3(0, 0, 3));

		printHeader("DIAGONAL MATRIX EXAMPLE");
		System.out.println("B = ");
		System.out.println(B);
		System.out.println("Bx = " + B.multiply(x));
		System.out.println();

		// ----------------------------
		// Rotation example
		// ----------------------------
		Matrix3 R = Matrix3.rotationZ(Math.PI / 2);

		printHeader("ROTATION ABOUT Z-AXIS");
		System.out.println("R = ");
		System.out.println(R);
		System.out.println("R * UNIT_X = " + R.multiply(Vector3.UNIT_X));
		System.out.println("R * u = " + R.multiply(u));
		System.out.println("R^(-1) = ");
		System.out.println(R.inverse());
		System.out.println("R * R^(-1) = ");
		System.out.println(R.multiply(R.inverse()));
	}

	private static void printHeader(String title) {
		System.out.println();
		System.out.println("=== " + title + " ===");
	}

}