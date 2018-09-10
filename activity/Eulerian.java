package activity;

import Array_Chain_Stack.ArrayLinearList;

/**
 * 
 * @author Anderson Mahecha 1070982043
 * @author Sergio Delgado 1077976177 
 *@since 07/09/2018
 */

public class Eulerian {
	/**
	 * Function that return a matrix with the possible permutation
	 * @param n The number n for permutation
	 * @param m The number m for permutation
	 * @return matrix that represents all the possible permutation
	 */
	public static ArrayLinearList Euleriannumber(int n, int m) {
		ArrayLinearList res = new ArrayLinearList(n+1);
		for(int i=0;i<n+1;i++) {
			ArrayLinearList a = new ArrayLinearList(m+1);
			res.add(i, a);
			for(int j=0;j<m+1;j++) {
				((ArrayLinearList) res.get(i)).add(j,new Integer(-1));
				if(j>=i || i==0) {
					((ArrayLinearList) res.get(i)).set(j,new Integer(0));
				}
				else if(j==0) {
					((ArrayLinearList) res.get(i)).set(j,new Integer(1));
				}
				else {
					((ArrayLinearList) res.get(i)).set(j,((i-j)*
							(int)((ArrayLinearList) res.get(i-1)).get(j-1)+(j+1)*
							(int)((ArrayLinearList) res.get(i-1)).get(j)));
					
				}
			}
		}
		imprimir(res);
		return res;
		
	}
	
	/**
	 * print a matrix nxm
	 * @param a matrix to print
	 */
	public static void imprimir(ArrayLinearList a) {
		for(int i=0;i<a.size();i++) {
			ArrayLinearList aux = (ArrayLinearList) a.get(i);
			System.out.print("|");
			for(int j=0;j<aux.size();j++) {
				System.out.printf("%-4d|",aux.get(j));
			}
			System.out.println();
		}
	}
}
