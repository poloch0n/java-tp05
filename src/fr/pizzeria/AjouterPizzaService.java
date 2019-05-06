package fr.pizzeria;

import fr.pizzeria.model.Pizza;
import fr.pizzeria.FromMenu;

public class AjouterPizzaService extends MenuService{
	void executeUc(PizzaMemDao dao) {
		showText("Ajout d'une nouvelle pizza");
		Pizza newPizza = FromMenu.getInformationPizza();
		String message = dao.checkInformationPizza(newPizza,true,"add");
		if(!message.equals("")) {
			showText(message);
			executeUc(dao);
			return;
		}
		dao.saveNewPizza(newPizza);
	}
}
