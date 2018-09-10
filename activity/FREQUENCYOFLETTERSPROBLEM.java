package activity;

import java.util.Scanner;

import Dictionary.SortedChain;
/**
 * 
 * @author Anderson Mahecha 1070982043
 * @since 29-08-2018 
 *
 */
public class FREQUENCYOFLETTERSPROBLEM {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		in.nextLine();
		
		for(int i=0;i<n;i++) {
			SortedChain c = new SortedChain();
			String a = in.nextLine();
			for(int j=0;j<a.length();j++) {
				if(c.get(a.charAt(j))== null) {
					c.put(a.charAt(j), 1);
				}
				else {
					c.put(a.charAt(j), (int)c.get(a.charAt(j))+1);
				}
			}			
			boolean si = true;
			for(int j=0;j<a.length()-1;j++) {
				if(c.get(a.charAt(j))!=c.get(a.charAt(j+1))) {
					si = false;
					break;
				}
			}
			if(si) {
				System.out.println("YES");
			}
			else {
				System.out.println("NO");
			}
			
		}
		

	}

}
