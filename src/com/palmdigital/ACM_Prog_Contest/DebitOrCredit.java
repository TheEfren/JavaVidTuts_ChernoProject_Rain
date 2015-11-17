package com.palmdigital.ACM_Prog_Contest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class DebitOrCredit 
{
	public static void main(String[] args) 
	{
		float 			D, // Debit price in $/gal
						F, // Transaction fee in $
						C, // Credit price in $/gal
						g; // number of gallons at which consumers break even
		
//		BufferedReader 	stdin;		
//		stdin = new BufferedReader(new InputStreamReader(System.in));
		
		D = 2.959f;
		F = 0.45f;
		C = 3.039f;
//		D = 3.119f;
//		F = 0.35f;
//		C = 3.139f;
		g = Math.abs(round(F / (D - C), 3));
		System.out.println(String.format("%.3f", g));
	}
	
	public static float round(float value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.floatValue();
	}
}
