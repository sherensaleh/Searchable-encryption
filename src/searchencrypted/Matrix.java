
/*To change this template, choose Tools | Templates
 and open the template in the editor.*/
package searchencrypted;

  import java.io.*;
    import java.util.*;
//import org.openjdk.jmh.annotations.Scope;
//import org.openjdk.jmh.annotations.State;

//import javax.swing.JLabel;
//import javax.swing.JTextArea;

//@State(Scope.Benchmark)
public class Matrix {

	double[][] mat;
	int nRows;
	int nCols;
	Matrix P;

	public Matrix(int nR, int nC) {
		nRows = nR;
		nCols = nC;
		mat = new double[nRows][nCols];
	}

	public Matrix(int nR, int nC, int pos) {
		nRows = nR;
		nCols = nC;
		mat = new double[nRows][nCols];
		for (int i = 0; i < nR; i++) {
			if (i == pos)
				mat[i][0] = 1;
			else
				mat[i][0] = 0;
		}

	}

	public Matrix(int N) {
		mat = new double[N][N];
		int i, j;
		for (i = 0; i < N; i++) {
			for (j = 0; j < N; j++) {
				if (i == j)
					mat[i][j] = 1;
				else
					mat[i][j] = 0;
			}
		}
		nCols = N;
		nRows = N;
	}

//	public Matrix(String text) {
//		nRows = 0;
//		nCols = 0;
//		this.readFromTextArea(text);
//	}

	public Matrix(Matrix A) {
		nRows = A.getNrows();
		nCols = A.getNcols();
		mat = new double[nRows][nCols];
		double[][] m = A.getMat();
		for (int i = 0; i < A.getNrows(); i++) {
			for (int j = 0; j < A.getNcols(); j++) {
				this.mat[i][j] = m[i][j];
			}
		}
	}

	// --------------------File handling

	// Reading matrix from textArea
//	public void readFromTextArea(String text) {
//		ArrayList<ArrayList<Double>> temp = new ArrayList<ArrayList<Double>>();
//		int endChar1, endChar2;
//
//		nRows = text.length() - text.replace("\n", "").length() + 1;
//		nCols = ((text.length() - text.replace(" ", "").length()) / nRows) + 1;
//		
//
//		String line = "";
//		while (text.length() > 0) {
//			ArrayList<Double> pom = new ArrayList<Double>();
//			if ((endChar1 = text.indexOf('\n')) != -1) {
//				line = text.substring(0, endChar1);
//				while (line.length() > 0) {
//					if ((endChar2 = line.indexOf(" ")) != -1) {
//						pom.add(Double.valueOf(line.substring(0, endChar2)));
//						line = line.substring(endChar2 + 1);
//					} else {
//						pom.add(Double.valueOf(line.substring(0)));
//						line = "";
//					}
//				}
//				text = text.substring(endChar1 + 1);
//
//			} else {
//				line = text;
//				while (line.length() > 0) {
//					if ((endChar2 = line.indexOf(" ")) != -1) {
//						pom.add(Double.valueOf(line.substring(0, endChar2)));
//						line = line.substring(endChar2 + 1);
//					} else {
//						pom.add(Double.valueOf(line.substring(0)));
//						line = "";
//					}
//				}
//				text = "";
//			}
//			temp.add(pom);
//		}
//		
//		mat = new double[nRows][nCols];
//		for (int i = 0; i < temp.size(); i++) {
//			for (int j = 0; j < temp.get(i).size(); j++) {
//				mat[i][j] = temp.get(i).get(j);
//			}
//		}
//
//	}
//
//	// Writing to a textArea
//	public void writeToTextArea(JTextArea jt) {
//		
//			//Clear the area
//			jt.setText(null);
//		
//			int i, j;
//			for (i = 0; i < nRows; i++) {
//				for (j = 0; j < nCols; j++) {
//					jt.append(String.valueOf(mat[i][j]));
//					jt.append("\t");
//				}
//				jt.append("\n");
//			}
//	}


	// -----------------------------Operations
	// +=
	public void add(Matrix m) {
		double[][] temp = m.getMat();
		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nCols; j++) {
				mat[i][j] += temp[i][j];
			}
		}
	}

	// +
	public static Matrix add(Matrix m, Matrix n) {
		double[][] temp1 = m.getMat();
		double[][] temp2 = n.getMat();
		Matrix newMat = new Matrix(m.getNrows(), m.getNcols());
		double[][] temp3 = newMat.getMat();
		for (int i = 0; i < m.getNrows(); i++) {
			for (int j = 0; j < m.getNcols(); j++) {
				temp3[i][j] = temp1[i][j] + temp2[i][j];
			}
		}
		return newMat;
	}

	// -=
	public void sub(Matrix m) {
		double[][] temp = m.getMat();
		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nCols; j++) {
				mat[i][j] -= temp[i][j];
			}
		}
	}

	// -
	public static Matrix sub(Matrix m, Matrix n) {
		double[][] temp1 = m.getMat();
		double[][] temp2 = n.getMat();
		Matrix newMat = new Matrix(m.getNrows(), m.getNcols());
		double[][] temp3 = newMat.getMat();
		for (int i = 0; i < m.getNrows(); i++) {
			for (int j = 0; j < m.getNcols(); j++) {
				temp3[i][j] = temp1[i][j] - temp2[i][j];
			}
		}
		return newMat;
	}

	// A*B
	public static Matrix multMatrix(Matrix m, Matrix n) {
		double[][] temp1 = m.getMat();
		double[][] temp2 = n.getMat();
		Matrix newMat = new Matrix(m.getNrows(), n.getNcols());
		double[][] temp3 = newMat.getMat();
		for (int i = 0; i < m.getNrows(); i++) {
			for (int j = 0; j < n.getNcols(); j++) {
				for (int k = 0; k < m.getNcols(); k++) {
					temp3[i][j] = temp3[i][j] + temp1[i][k] * temp2[k][j];
				}
			}
		}
		return newMat;
	}

	// A*0.4
	public void multScal(double scalar) {
		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nCols; j++) {
				mat[i][j] *= scalar;
			}
		}
	}

	public static Matrix multScal(Matrix A, double scalar) {
		double[][] m = A.getMat();
		Matrix newMat = new Matrix(A.getNrows(), A.getNcols());
		double[][] temp = newMat.getMat();
		for (int i = 0; i < newMat.getNrows(); i++) {
			for (int j = 0; j < newMat.getNcols(); j++) {
				temp[i][j] = m[i][j] * scalar;
			}
		}
		return newMat;
	}

	// A==B
	public boolean equals(Matrix m) {
		double[][] temp = m.getMat();
		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nCols; j++) {
				if ((temp[i][j] - mat[i][j]) > 0.000001
						|| ((mat[i][j] - temp[i][j]) > 0.000001))
					return false;
			}
		}
		return true;
	}

	// a==b
	public boolean equals(double num1, double num2) {
		if (Math.abs(num1 - num2) > 0.000001)
			return false;
		else
			return true;
	}

	// ~A
	public double[][] transpose() {
		double[][] temp = new double[nRows][nCols];
		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nCols; j++) {
				temp[j][i] = mat[i][j];
			}
		}
//                    System.out.println("transpose of matrix 1 temp is :");
//                      for(int i=0;i<nRows;i++){
//                          for(int j=0;j<nCols;j++){
//                              System.out.print("\t"+temp[i][j]);
//            }
//            System.out.println();
//        }
		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nCols; j++) {
				mat[i][j] = temp[i][j];
			}
		}
//                  System.out.println("transpose of matrix 1 mat is :");
//                      for(int i=0;i<nRows;i++){
//                          for(int j=0;j<nCols;j++){
//                              System.out.print("\t"+mat[i][j]);
//            }
//                System.out.println();
//        }
                
       return temp; 
	}

	// ------------------------------Get/Set
	
	public double[][] getMat() {
		return mat;
	}

	// setting mat property
	public void setMat(double[][] temp) {
		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nCols; j++) {
				mat[i][j] = temp[i][j];
			}
		}
	}

	//setting the whole object
	public void setMatrix(Matrix m) {
		mat = m.getMat();
		nRows = m.getNrows();
		nCols = m.getNcols();
	}

	public double getEl(int rowIndex, int colIndex) {
		return mat[rowIndex][colIndex];
	}

	public void setEl(double value, int rowIndex, int colIndex) {
		mat[rowIndex][colIndex] = value;
	}

	public int getNrows() {
		return nRows;
	}

	public int getNcols() {
		return nCols;
	}

	public void changeRows(int row1, int row2) {
		double temp;
		for (int i = 0; i < nCols; i++) {
			temp = mat[row1][i];
			mat[row1][i] = mat[row2][i];
			mat[row2][i] = temp;
		}

	}

	public void printMat() {
		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nCols; j++) {
				System.out.print(String.valueOf(mat[i][j]) + " ");
			}
			System.out.println("\n");
		}
	}

	public void printVec() {
		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nCols; j++) {
				System.out.print(String.valueOf(mat[i][j]) + " ");
			}
		}
		System.out.println("\n");
	}

	// LUP decomposition for inversing the matrix
	public Matrix inverseMatrix() {
		// Jedinicna E
		ArrayList<Matrix> E = new ArrayList<Matrix>();
		ArrayList<Matrix> x = new ArrayList<Matrix>();
		Matrix inverse = new Matrix(this.getNrows(), this.getNcols());

		for (int i = 0; i < this.getNrows(); i++) {
			Matrix temp = new Matrix(this.getNrows(), 1, i);
			E.add(temp);
		}
              //this.printMat();

		this.LUPalg();
		for (int i = 0; i < this.getNrows(); i++) {
			x.add(this.BackwardS(this.ForwardS(E.get(i))));
		}
		//
		for (int i = 0; i < x.size(); i++) {
			for (int j = 0; j < inverse.getNrows(); j++) {
				this.setEl(x.get(i).getEl(j, 0), j, i);
			}
		}
               //this.printMat();
                return this;
	}

	// ---------------------------------------LU/LUP-------------------------

	public Matrix ForwardS(Matrix vecB) {
		int i, j;
		double[][] matVecB = vecB.getMat();
		for (i = 0; i < vecB.getNrows() - 1; i++) {
			for (j = i + 1; j < vecB.getNrows(); j++)
				matVecB[j][0] -= mat[j][i] * matVecB[i][0];
		}
		return vecB;
	}

	public Matrix ForwardS2(Matrix vecB) {
		double[][] matP = P.getMat();
		int i, j;
		double[][] temp = new double[getNrows()][getNrows()];
		double[][] matVecB = vecB.getMat();
		for (i = 0; i < getNrows(); i++) {
			for (j = 0; j < getNrows(); j++) {
				temp[i][0] += matVecB[j][0] * matP[i][j];
			}
		}
		vecB.setMat(temp);
		for (i = 0; i < getNrows() - 1; i++) {
			for (j = i + 1; j < getNrows(); j++)
				matVecB[j][0] -= mat[j][i] * matVecB[i][0];
		}
		return vecB;
	}

	public Matrix BackwardS(Matrix vecB) {
		int i, j;
		double[][] matVecB = vecB.getMat();
		
		for (i = vecB.getNrows() - 1; i >= 0; i--) {
			matVecB[i][0] /= mat[i][i];
			for (j = 0; j < i; j++) {
				matVecB[j][0] -= mat[j][i] * matVecB[i][0];
			}
		}
		return vecB;
	}

	public void LUalg() {
		int i, j, k;
		for (i = 0; i < getNrows() - 1; i++) {
			for (j = i + 1; j < getNrows(); j++) {
				mat[j][i] = mat[j][i] / mat[i][i];
				for (k = i + 1; k < getNrows(); k++) {
					mat[j][k] -= mat[j][i] * mat[i][k];
				}
			}
		}
	}

	public void LUPalg() {

		int i, j, k;
		P = new Matrix(getNrows());

		for (i = 0; i < getNrows(); i++) {

			// Potraga za max elementom stupca
			double max = Math.abs(mat[i][i]);
			int R = i;
			for (j = i + 1; j < getNrows(); j++) {
				if (Math.abs(mat[j][i]) > max) {
					max = Math.abs(mat[j][i]);
					R = j;
				}
			}
			if (equals(max, 0)) {
				
				System.exit(0);
			}

			this.changeRows(i, R);
			P.changeRows(i, R);

			for (j = i + 1; j < getNrows(); j++) {
				mat[j][i] = mat[j][i] / mat[i][i];
				for (k = i + 1; k < getNrows(); k++) {
					mat[j][k] -= mat[j][i] * mat[i][k];
				}
			}
		}

	}
}
