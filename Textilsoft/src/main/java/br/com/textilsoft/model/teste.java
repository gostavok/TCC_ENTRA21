package br.com.textilsoft.model;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

public class teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Fornecedor forn = new Fornecedor();
		
		forn.setTelFornecedor(1123456789);
		
		try {
			MaskFormatter mask = new MaskFormatter("(##)####-####");
			mask.setValueContainsLiteralCharacters(false);
			System.out.println(mask.valueToString(forn.getTelFornecedor()));
			forn.setTelFornecedor(Integer.valueOf(mask.valueToString(forn.getTelFornecedor())));
		} catch (ParseException e) {			
			e.printStackTrace();
		}
		
		System.out.println(forn.getTelFornecedor());
	}

}
