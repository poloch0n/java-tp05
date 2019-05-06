package fr.pizzeria;

import java.util.Scanner;
import fr.pizzeria.model.*;
import java.lang.Integer;
import fr.pizzeria.FromMenu;
import fr.pizzeria.ShowMenu;

public class PizzeriaAdminConsoleApp {

	private static PizzaMemDao dao;
	private static FromMenu fm;
	public static void main(String[] args) {
		
		dao = new PizzaMemDao();
		fm = new FromMenu();
		
		ShowMenu.showIntroduction();
		run(dao,fm);
	}

	private static void run(PizzaMemDao dao, FromMenu fm) {

		ShowMenu.showMenuOptions();
		int answer = FromMenu.getIntFromMenu();
		switch(answer) {

		case 1:
			ListerPizzasService listage = new ListerPizzasService();
			listage.executeUc(dao);
			break;
		case 2:
			ListerPizzasService ajout = new ListerPizzasService();
			ajout.executeUc(dao);
			break;
		case 3:
			ListerPizzasService modification = new ListerPizzasService();
			modification.executeUc(dao);
			break;
		case 4:
			ListerPizzasService suppression = new ListerPizzasService();
			suppression.executeUc(dao);
			break;
		case 99:
			ShowMenu.showText("Aurevoir☹ ");
			System.exit(0);
			break;
		default:
			ShowMenu.showText("Veuillez choisir une option valide");
		    break;
		}
		run(dao,fm);
	}
}