package inf101.rogue101.game;

import inf101.rogue101.objects.*;

public class ItemFactory {

	public static IItem createItem(char symbol) {
		switch(symbol) {
		case '#':
			return new Wall();
		case 'R':
			return new Rabbit();
		case 'C':
			return new Carrot();
		case '@':
			return new Player();
		case 'S':
			return new Spider();
		case 'A':
			return Amulet.getInstance();
		case '*':
			return new Portal();
			case '.':
				return new Dust();
			case 'G':
				return new Gold();
		case ' ':
			return null;
		default : 	
			throw new IllegalArgumentException("createItem: Don't know how to create a '" + symbol + "'");
		}
	}
}
