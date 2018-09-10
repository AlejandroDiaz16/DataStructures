package activity;

import java.util.Scanner;

import HashTable.HashLCFS;

/**
 * 
 * @author Anderson Mahecha 1070982043
 * @author Sergio Delgado 1077976177 
 * @since 07/09/2018
 */

public class PassCheck {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String arg = in.nextLine();
		HashLCFS dic = new HashLCFS(97);
		while (!arg.equals(".")) {
			dic.put(arg, 0);
			arg = in.nextLine();
		}
		int cases = Integer.parseInt(in.nextLine());
		System.out.println(cases);
		for(int i = 0;i<cases;i++) {
			String a = in.nextLine();
			if(a.length()>=8) {
				Object o = dic.put(a, 0);
				if(o==null) {
					dic.remove(a);
					String b = a.substring(0, a.length()-2);
					o = dic.put(b, 0);
					if(o==null) {
						dic.remove(b);
						int count =0;
						for(int k=0;k<a.length();k++) {
							if(a.charAt(k)>='0' && a.charAt(k)<='9') {
								count++;
							}
						}
						if(count ==1) {
							System.out.println("bad");
						}
						else {
							System.out.println("good");
						}
					}
					else {
						System.out.println("bad");
					}
				}
				else
					System.out.println("bad");
			}
			else
				System.out.println("bad");
		}
	}
}
