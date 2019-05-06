package fr.pizzeria;

import java.util.Scanner;

import fr.pizzeria.model.Pizza;

public class FromMenu {
	
	static Scanner questionUser;
	public FromMenu() {
		questionUser = new Scanner(System.in);
	}
	
	public static Pizza getInformationPizza() {

		showText("Veuillez saisir le code :");
		String code = getCode();

		showText("Veuillez saisir le libelle (sans espace au possible) :");
		String libelle = getLibelle();
		
		showText("Veuillez saisir le prix :");
		double prix = getPrice();
		
		return new Pizza(code,libelle,prix);
	}
	
	public static String getCode() {
		String code = getStringFromMenu();
		return code;
	}

	public static String getLibelle() {
		String libelle = getStringFromMenu();
		return libelle;
	}

	public static Double getPrice() {
		Double prix = getDoubleFromMenu();
		return prix;
	}
	
	public static String getStringFromMenu() {
		try {
			//todo : attention injection ?
			return (String) questionUser.next();
		} catch(Exception e)  {
			// Gestion des cas où l'utilisateur ne rentre pas un text au bon format
			return "";
		}
	}

	public static Double getDoubleFromMenu() {
		String input = questionUser.next();
	    double number = 0;
	    try {
	        number = Double.parseDouble(input);
	        return number;
	    } catch (Exception e) {
	        return -1.00;
	    }
	}
	
	public static int getIntFromMenu() {
		String input = questionUser.next();
        int number = 0;
        try {
            number = Integer.parseInt(input);
            return number;
        } catch (Exception e) {
            return 0;
        }   
	}

	private static void showText(String texte) {
		System.out.println(texte);
	}
}
