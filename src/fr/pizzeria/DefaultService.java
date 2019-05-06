package fr.pizzeria;

public class DefaultService extends MenuService{

	public DefaultService() {
		// TODO Auto-generated constructor stub
	}

	void executeUc(PizzaMemDao dao) {
		ShowMenu.showText("Veuillez choisir une option valide");
	}
}
