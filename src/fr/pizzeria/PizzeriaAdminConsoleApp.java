package fr.pizzeria;

import java.util.Scanner;
import fr.pizzeria.model.*;
import java.lang.Integer; 

public class PizzeriaAdminConsoleApp {

	private static Scanner questionUser;
	private static PizzaMemDao dao;
	public static void main(String[] args) {
		questionUser = new Scanner(System.in);
		
		dao = new PizzaMemDao();
		
		showIntroduction();
		run(questionUser);
	}
	private static void run(Scanner QuestionUser) {

		showMenuOptions();
		int answer = getIntFromMenu(QuestionUser);
		switch(answer) {

		case 1:
			showMenu();
			break;
		case 2:
			addPizza();
			break;
		case 3:
			updatePizza();
			break;
		case 4:
			deletePizza();
			break;
		case 99:
			showText("Aurevoir☹ ");
			System.exit(0);
			break;
		default:
			showText("Veuillez choisir une option valide");
		    break;
		}
		run(QuestionUser);
	}
	
	private static void showMenu() {
		if(dao.checkMenuEmpty()) {
			showText("Vous n'avez pas encore ajouter de pizza, prenez l'option 2");
			return;
		}
		showText("Liste des pizzas");
		for(Pizza pizza: dao.findAllPizzas()) {
			String text = pizza.code + " -> " + pizza.libelle + " (" + pizza.prix + " €) ";
			showText(text);
		}
	}
	
	private static void addPizza() {
		
		showText("Ajout d'une nouvelle pizza");
		Pizza newPizza = getInformationPizza();
		String message = dao.checkInformationPizza(newPizza,true,"add");
		if(!message.equals("")) {
			showText(message);
			addPizza();
			return;
		}
		dao.saveNewPizza(newPizza);
	}
	
	private static void updatePizza() {
		
		showText("Mise à jour d’une pizza");
		if(dao.checkMenuEmpty()) {
			showText("Vous n'avez pas encore ajouter de pizza, prenez l'option 2");
			return;
		}
		
		showText("Veuillez choisir le code de la pizza à modifier");
		String code = getCode();
		//Vérification de l'existance de la pizza
		if(!dao.pizzaExists(code)) {
			showText("Le code saisi semble ne correspondre a aucune pizza, pouvez vous réessayer ?\r\n");
			updatePizza();
			return;
		}
		Pizza updatedPizza = getInformationPizza();
		dao.updatePizza(code,updatedPizza);
		
	}
	
	private static void deletePizza() {
		showText("Suppression d’une pizza");

		if(dao.checkMenuEmpty()) {
			showText("Vous n'avez pas encore ajouter de pizza, prenez l'option 2");
			return;
		}
		showText("Veuillez choisir le code de la pizza à supprimer");

		String code = getCode();
		//Vérification de l'existance de la pizza
		if(!dao.pizzaExists(code)) {
			showText("Le code saisi semble ne correspondre a aucune pizza, pouvez vous réessayer ?\r\n");
			deletePizza();
			return;
		}

		dao.deletePizza(code);
		showText("what is done can't be undone. you won't see him again");
	}
	private static Pizza getInformationPizza() {

		showText("Veuillez saisir le code :");
		String code = getCode();

		showText("Veuillez saisir le libelle (sans espace au possible) :");
		String libelle = getLibelle();
		
		showText("Veuillez saisir le prix :");
		double prix = getPrice();
		
		return new Pizza(code,libelle,prix);
	}
	
	private static void showIntroduction() {
		showText("***** Pizzeria Administration v2 *****");
	}
	
	private static void showMenuOptions() {
		showText("1. Lister les pizzas \r\n2. Ajouter une nouvelle pizza \r\n3. Mettre à jour une pizza \r\n4. Supprimer une pizza \r\n99. Sortir");
	}	
	
	private static void showText(String texte) {
		System.out.println(texte);
	}
	
	private static String getCode() {
		String code = getStringFromMenu(questionUser);
		return code;
	}

	private static String getLibelle() {
		String libelle = getStringFromMenu(questionUser);
		return libelle;
	}

	private static Double getPrice() {
		Double prix = getDoubleFromMenu(questionUser);
		return prix;
	}
	
	private static int getIntFromMenu(Scanner QuestionUser) {

		String input = QuestionUser.next();
        int number = 0;
        try {
            number = Integer.parseInt(input);
            return number;
        } catch (Exception e) {
            return 0;
        }
        
	}

	private static String getStringFromMenu(Scanner QuestionUser) {
		try {
			//todo : attention injection ?
			return (String) questionUser.next();
		} catch(Exception e)  {
			// Gestion des cas où l'utilisateur ne rentre pas un text au bon format
			return "";
		}
	}

	private static Double getDoubleFromMenu(Scanner QuestionUser) {
		String input = QuestionUser.next();
	    double number = 0;
	    try {
	        number = Double.parseDouble(input);
	        return number;
	    } catch (Exception e) {
	        return -1.00;
	    }
	}
}