package fr.pizzeria;

public class ExitService extends MenuService{

	public ExitService() {
		// TODO Auto-generated constructor stub
	}
	
	void executeUc(PizzaMemDao dao) {
		ShowMenu.showText("Aurevoir☹ ");
		System.exit(0);
	}
}
