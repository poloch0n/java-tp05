package fr.pizzeria;

import fr.pizzeria.model.Pizza;

public class PizzaMemDao implements IPizzaDao {


	static Pizza[] menu;
	PizzaMemDao(){
		initialisationMenu();
	}
	private void addPizzaMenuWithId(Integer id,String code, String libelle, double prix) {
		
		Pizza pizza = new Pizza(id,code,libelle,prix);
		saveNewPizza(pizza);
	}
	
	@Override
	public Pizza[] findAllPizzas() {
		
		return menu;
	}

	@Override
	public void saveNewPizza(Pizza newPizza) {

		Pizza[] menuTemporary;
		if(menu != null) {
			menuTemporary = new Pizza[menu.length + 1];
			if(menu.length != 0) {
				for (int i = 0; i < menu.length; i++) {
					menuTemporary[i] = menu[i];
				}
			}
			menuTemporary[menu.length] = newPizza;
		} else {
			menuTemporary = new Pizza[1];
			menuTemporary[0] = newPizza;
		}
		menu = menuTemporary;
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {

		menu[getIndexPizzaOnMenu(codePizza)] = pizza;
	}

	@Override
	public void deletePizza(String codePizza) {
		
		Pizza[] menuTemporary = null;
		if(menu.length == 1) {
			//todo delete menu;
			menuTemporary = null;
		} else if(menu != null ) {
			menuTemporary = new Pizza[menu.length - 1];
			//V1
				//todo think to do the same with only 1 loop
			int index = getIndexPizzaOnMenu(codePizza);
			for (int i = 0; i < index; i++) {
					menuTemporary[i] = new Pizza(menu[i].id,menu[i].code,menu[i].libelle,menu[i].prix);
				}
			for (int i = index; i < menuTemporary.length; i++) {
			menuTemporary[i] = new Pizza(menu[i+1].id,menu[i+1].code,menu[i+1].libelle,menu[i+1].prix);
			}
		}
		menu = menuTemporary;
	}

	@Override
	public Pizza findPizzaByCode(String codePizza) {
		
		if(checkMenuEmpty()){
			return null;
		}
		int index;
		for (index = 0; index < menu.length; index++ ) {
			if(menu[index].code.equals(codePizza)) {
				return new Pizza(menu[index].id,menu[index].code,menu[index].libelle,menu[index].prix);
			}
		}
		return null;
	}

	@Override
	public boolean pizzaExists(String codePizza) {
		for (int i = 0; i < menu.length; i++ ) {
			if(menu[i].code.equals(codePizza)) {
				return true;
			}
		}
		return false;
	}

	private Integer getIndexPizzaOnMenu(String codePizza) {
		if(pizzaExists(codePizza)) {
			int index;
			for (index = 0; index < menu.length; index++ ) {
				if(menu[index].code.equals(codePizza)) {

					return index;
				}
			}
		}
		return null;
	}
	
	void initialisationMenu() {
		
		addPizzaMenuWithId(0, "PEP","Pépéroni", 12.50);
		addPizzaMenuWithId(1, "MAR","Margherita", 14.00);
		addPizzaMenuWithId(2, "REI","La Reine", 11.50);
		addPizzaMenuWithId(3, "FRO","La 4 fromages", 12.00);
		addPizzaMenuWithId(4, "CAN","La cannibale", 12.50);
		addPizzaMenuWithId(5, "SAV","La savoyarde", 13.00);
		addPizzaMenuWithId(6, "ORI","L'orientale", 13.50);
		addPizzaMenuWithId(7, "IND","L'indienne", 14.00);
		
	}
	
	boolean checkMenuEmpty() {
		
		if(menu == null) {
			return true;
		} else {
			return false;
		}
	}

	public String checkInformationPizza(Pizza pizza, boolean unicity, String methode) {
		
		String error = "";
		error += checkFormatInformationPizza(pizza);

		if(unicity) {
			error += checkUnicityInformationPizza(pizza,methode);
		}
		
		return error;
	}

	public String checkFormatInformationPizza(Pizza pizza) {
		String error = "";
		if(pizza.code.equals("")) {
			error += "\r\nLe format du code "+ pizza.code +" est invalide";
		}
		if(pizza.libelle.equals("")) {
			error += "\r\nLe format du libelle " + pizza.libelle + " est invalide";
		}
		if(isNegative(pizza.prix)) {
			error += "\r\nLe format du prix est invalide";
		}
		return error;
	}
	
	private String checkUnicityInformationPizza(Pizza pizza,String methode) {
		String error = "";
		for (Pizza pizzaSaved : menu) {
			if(pizzaSaved.code.equals(pizza.code)) {
				error += "\r\nCe code a déjà été utilisé";
			}
			if(pizzaSaved.libelle.equals(pizza.libelle)) {
				error += "\r\nCe libelle a déjà été utilisé";
			}
			if(methode.equals("add") && pizzaSaved.id == pizza.id) {
					pizza.id ++;
			}
		}
		return error;
	}

	boolean isNegative(double d) {
	     return Double.compare(d, 0.0) < 0;
	}
}
