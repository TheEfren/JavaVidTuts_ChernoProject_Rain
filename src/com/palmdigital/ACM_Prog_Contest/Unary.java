package com.palmdigital.ACM_Prog_Contest;

import java.io.*;

public class Unary 
{
	public static void main(String[] args) throws IOException
	{		
		int 			n;
		String 			s;
		BufferedReader 	stdin;	// use BufferedReader when dealing with file input/output
		
		stdin = new BufferedReader(new InputStreamReader(System.in));
		
		while( (s = stdin.readLine()) != null)
		{
			n = Integer.parseInt(s);
			System.out.print(n + " ");
			
			for(int i = 0; i < n - 1; i++)
			{
				System.out.print("1");
			}
			System.out.print("0");
		}		
		System.exit(0);
	}
}
