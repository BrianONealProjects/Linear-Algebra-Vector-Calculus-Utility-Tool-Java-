package vectorCalculus;

/**
 * 3×3 matrix representing a linear transformation in R^3. Includes matrix
 * multiplication, determinant, transpose, inverse, and rotation matrix
 * generators.
 * 
 * @author Brian O'Neal
 */

public final class Matrix3 {

	private final Vector3[] cols;
	public static final Matrix3 IDENTITY = new Matrix3(Vector3.UNIT_X, Vector3.UNIT_Y, Vector3.UNIT_Z);

	public Matrix3(Vector3 c1, Vector3 c2, Vector3 c3) {
		this.cols = new Vector3[] { c1, c2, c3 };
	}

	// Generic column vector getter.
	public Vector3 getCol(int i) {
		return cols[i];
	}

	public Vector3 col1() {
		return cols[0];
	}

	public Vector3 col2() {
		return cols[1];
	}

	public Vector3 col3() {
		return cols[2];
	}

	// Matrix acting on a Vector : Av
	public Vector3 multiply(Vector3 v) {
		return cols[0].scale(v.getX()).add(cols[1].scale(v.getY())).add(cols[2].scale(v.getZ()));
	}

	// Matrix x Matrix : AB = [Ab1 Ab2 Ab3].
	public Matrix3 multiply(Matrix3 B) {
		Vector3 newC1 = this.multiply(B.col1());
		Vector3 newC2 = this.multiply(B.col2());
		Vector3 newC3 = this.multiply(B.col3());

		return new Matrix3(newC1, newC2, newC3);
	}

	// Helper method to handle float point error noise.
	private static double clean(double x) {
		if (Math.abs(x) < 1e-10)
			return 0.0;
		if (Math.abs(x - 1.0) < 1e-10)
			return 1.0;
		if (Math.abs(x + 1.0) < 1e-10)
			return -1.0;
		return x;
	}

	// Determinant of 3x3 Matrix is the triple product c1*(c2xc3).
	public double determinant() {
		return cols[0].dot(cols[1].cross(cols[2]));
	}

	public Matrix3 transpose() {
		Vector3 r1 = new Vector3(cols[0].getX(), cols[1].getX(), cols[2].getX());
		Vector3 r2 = new Vector3(cols[0].getY(), cols[1].getY(), cols[2].getY());
		Vector3 r3 = new Vector3(cols[0].getZ(), cols[1].getZ(), cols[2].getZ());

		return new Matrix3(r1, r2, r3);
	}

	// Scale entire matrix by a scalar double 'a'.
	public Matrix3 scale(double a) {
		return new Matrix3(this.col1().scale(a), this.col2().scale(a), this.col3().scale(a));
	}

	// Rotate about X-axis.
	public static Matrix3 rotationX(double theta) {
		double cos = clean(Math.cos(theta));
		double sin = clean(Math.sin(theta));

		Vector3 c1 = Vector3.UNIT_X;
		Vector3 c2 = new Vector3(0, cos, sin);
		Vector3 c3 = new Vector3(0, -sin, cos);

		return new Matrix3(c1, c2, c3);
	}

	// Rotate about the Y-axis.
	public static Matrix3 rotationY(double theta) {
		double cos = clean(Math.cos(theta));
		double sin = clean(Math.sin(theta));

		Vector3 c1 = new Vector3(cos, 0, -sin);
		Vector3 c2 = Vector3.UNIT_Y;
		Vector3 c3 = new Vector3(sin, 0, cos);

		return new Matrix3(c1, c2, c3);
	}

	// Rotation matrix about the Z-axis.
	public static Matrix3 rotationZ(double theta) {

		double cos = clean(Math.cos(theta));
		double sin = clean(Math.sin(theta));

		Vector3 c1 = new Vector3(cos, sin, 0);
		Vector3 c2 = new Vector3(-sin, cos, 0);
		Vector3 c3 = Vector3.UNIT_Z;

		return new Matrix3(c1, c2, c3);
	}

	// Invert 3x3 matrix, returns A^(-1).
	public Matrix3 inverse() {

		double det = this.determinant();

		// Handling float point error for 0 determinant.
		if (Math.abs(det) < 1e-10)
			throw new IllegalStateException("Determinant is 0, Matrix not invertible.");

		Vector3 c1 = cols[0];
		Vector3 c2 = cols[1];
		Vector3 c3 = cols[2];

		Vector3 r1 = c2.cross(c3).scale(1.0 / det);
		Vector3 r2 = c3.cross(c1).scale(1.0 / det);
		Vector3 r3 = c1.cross(c2).scale(1.0 / det);

		return new Matrix3(r1, r2, r3).transpose();
	}

	// Sum of Eigenvalues.
	public double trace() {
		return cols[0].getX() + cols[1].getY() + cols[2].getZ();
	}

	// Checks if matrix is almostEquals by calling the Vector3.almostEquls() method
	// on the column vectors.
	public boolean almostEquals(Matrix3 m, double eps) {
		return this.col1().almostEquals(m.col1(), eps) && this.col2().almostEquals(m.col2(), eps)
				&& this.col3().almostEquals(m.col3(), eps);
	}

	// Compute 2D plane orientation in 3D field.
	public static Vector3 planeNormal(Vector3 a, Vector3 b, Vector3 c) {
		Vector3 ab = b.subtract(a);
		Vector3 ac = c.subtract(a);
		return ab.cross(ac).unitVec();
	}

	@Override
	public String toString() {

		double a11 = clean(cols[0].getX());
		double a12 = clean(cols[1].getX());
		double a13 = clean(cols[2].getX());

		double a21 = clean(cols[0].getY());
		double a22 = clean(cols[1].getY());
		double a23 = clean(cols[2].getY());

		double a31 = clean(cols[0].getZ());
		double a32 = clean(cols[1].getZ());
		double a33 = clean(cols[2].getZ());

		return "Matrix : \n" + "[" + a11 + "  " + a12 + "  " + a13 + "]\n" + "[" + a21 + "  " + a22 + "  " + a23 + "]\n"
				+ "[" + a31 + "  " + a32 + "  " + a33 + "]\n";
	}

}