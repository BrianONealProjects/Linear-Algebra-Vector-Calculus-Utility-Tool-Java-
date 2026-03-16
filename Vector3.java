package vectorCalculus;

/**
 * This is a Vector object class to construct immutable 3D vector objects and
 * perform various useful computations and operations on them.
 * 
 * @author Brian O'Neal
 *
 */
public final class Vector3 {
	private final double x;
	private final double y;
	private final double z;

	// Constant vectors - Zero, x/y/z unit vectors.
	public static final Vector3 ZERO = new Vector3(0, 0, 0);
	public static final Vector3 UNIT_X = new Vector3(1, 0, 0);
	public static final Vector3 UNIT_Y = new Vector3(0, 1, 0);
	public static final Vector3 UNIT_Z = new Vector3(0, 0, 1);

	// Input constructor.
	public Vector3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;

	}

	// Copy vector constructor.
	public Vector3(Vector3 v) {
		this.x = v.getX();
		this.y = v.getY();
		this.z = v.getZ();

	}

	// Getter - X
	public double getX() {
		return this.x;
	}

	// Getter - Y
	public double getY() {
		return this.y;
	}

	// Getter - Z
	public double getZ() {
		return this.z;
	}

	// Add two vectors
	public Vector3 add(Vector3 v) {
		return new Vector3(this.x + v.x, this.y + v.y, this.z + v.z);
	}

	// Distance between vectors
	public Vector3 subtract(Vector3 v) {
		return new Vector3(this.x - v.x, this.y - v.y, this.z - v.z);
	}

	// Scales a vector by scalar a
	public Vector3 scale(double a) {
		return new Vector3(this.x * a, this.y * a, this.z * a);
	}

	// Dot product of this dot v.
	public double dot(Vector3 v) {
		return this.x * v.x + this.y * v.y + this.z * v.z;
	}

	// Cross Product of this cross v.
	public Vector3 cross(Vector3 v) {
		return new Vector3(this.y * v.z - this.z * v.y, this.z * v.x - this.x * v.z, this.x * v.y - this.y * v.x);
	}

	// Square norm method for use in other calculations.
	public double normSq() {
		return this.dot(this);
	}

	// Calculates norm/magnitude of a given vector.
	public double norm() {
		return Math.sqrt(this.normSq());
	}

	// Calculates unit vector.
	public Vector3 unitVec() {
		double norm = this.norm();
		if (norm == 0)
			throw new IllegalStateException("Zero vector has no direction.");
		return scale(1.0 / norm);
	}

	public double distanceTo(Vector3 v) {
		return this.subtract(v).norm();
	}

	/**
	 * Projection of this vector onto parameter vector.
	 * 
	 * @param v - vector being projected onto.
	 * @return - The projection of this vector onto vector v.
	 */
	public Vector3 projOnto(Vector3 v) throws IllegalArgumentException {
		double mag = v.normSq();
		if (mag == 0)
			throw new IllegalArgumentException("Cannot project onto zero vector");
		return (v.scale(this.dot(v) / mag));
	}

	// Angle method. Calculates identity cos(theta) = (u dot v)/(|u|*|v|), then
	// inverts it to recover theta.
	public double angleTo(Vector3 v) {
		double denom = this.norm() * v.norm();
		if (denom == 0.0)
			throw new IllegalArgumentException("Angle with zero vector undefined.");

		double cos = this.dot(v) / denom;
		cos = Math.max(-1.0, Math.min(1.0, cos));
		return Math.acos(cos);
	}

	// Almost equals method to deal with float-point errors.
	public boolean almostEquals(Vector3 v, double eps) {
		return Math.abs(this.x - v.x) < eps && Math.abs(this.y - v.y) < eps && Math.abs(this.z - v.z) < eps;
	}

	@Override
	public String toString() {
		if (this.norm() == 0)
			return "Zero Vector : <" + this.x + ", " + this.y + ", " + this.z + ">";
		return "Vector <" + this.x + ", " + this.y + ", " + this.z + ">";

	}
}
