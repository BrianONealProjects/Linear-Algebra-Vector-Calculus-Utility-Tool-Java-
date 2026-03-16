# Linear-Algebra-Vector-Calculus-Utility-Tool-Java-
A small Java library implementing core 3D vector and matrix operations from scratch. This project was built to explore the mathematical foundations of linear algebra, vector geometry, and linear transformations while practicing object-oriented design in Java.  


## Vector3
Features

 - Immutable 3-dimensional vector class.
   
     Supporting:
   
       Vector addition and subtraction
   
       Scalar multiplication, dot product
   
       Cross product, vector norms and squared norms
   
       Unit vector normalization
   
       Projection of one vector onto another
   
       Distance between vectors
   
       Special constants: (ZERO, UNIT_X, UNIT_Y, UNIT_Z).


## Matrix3
  - 3×3 matrix class representing linear transformations in ℝ³.

      Implemented operations include:

        matrix–vector multiplication

        matrix–matrix multiplication

        determinant using the scalar triple product

        matrix transpose
    
        matrix inverse

        scalar scaling of matrices

        identity matrix constant

        rotation matrices about X, Y, Z axes.


## Mathematical Concepts Demonstrated

 - This project touches on several key ideas from linear algebra and vector calculus:

        linear transformations

       column-space interpretation of matrix multiplication

        determinant as signed volume scaling

        orthogonality via dot products

        vector projections

        cross products and area of parallelograms
  
        rotation matrices in 3D

        handling floating-point numerical error in geometric computations


## Motivation
 This project was created to deepen understanding of the relationship between linear algebra theory and its   implementation in code, particularly how geometric ideas such as projection, rotation, and transformation can   be expressed programmatically.
    

## Possible Future Extensions Potential additions include: 
  -

      rotations about X and Y axes

      generalized N×N matrix class
  
      Gram–Schmidt orthogonalization

      least-squares regression using normal equations

      eigenvalue and eigenvector exploration

      QR decomposition for numerical linear algebra


## Why I Built This

I implemented these classes from scratch to explore how core linear algebra concepts
such as vector projections, matrix transformations, and determinants translate into
actual software.
Writing the algorithms directly helped reinforce ideas from courses in linear algebra,
vector calculus, and mathematical modeling while improving my experience with
object-oriented design in Java.
