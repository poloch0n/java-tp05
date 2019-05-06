package fr.pizzeria;

import fr.pizzeria.model.Pizza;

public class ListerPizzasService extends MenuService{
	void executeUc(PizzaMemDao dao) {
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
}
