package fr.pizzeria;

import fr.pizzeria.FromMenu;
public class SupprimerPizzaService extends MenuService{
	void executeUc(PizzaMemDao dao) {
		showText("Suppression d’une pizza");

		if(dao.checkMenuEmpty()) {
			showText("Vous n'avez pas encore ajouter de pizza, prenez l'option 2");
			return;
		}
		showText("Veuillez choisir le code de la pizza à supprimer");

		String code = FromMenu.getCode();
		//Vérification de l'existance de la pizza
		if(!dao.pizzaExists(code)) {
			showText("Le code saisi semble ne correspondre a aucune pizza, pouvez vous réessayer ?\r\n");
			executeUc(dao);
			return;
		}

		dao.deletePizza(code);
		showText("what is done can't be undone. you won't see him again");
	
	}
}
