package fr.pizzeria;

public class MenuServiceFactory {

	public MenuServiceFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public MenuService run(Integer answer) {
		switch(answer) {
		case 1:
			return new ListerPizzasService();
		case 2:
			return new AjouterPizzaService();
		case 3:
			return new ModifierPizzaService();
		case 4:
			return new SupprimerPizzaService();
		case 99:
			return new ExitService();
		default:
			return new DefaultService();
		}
	}

}
