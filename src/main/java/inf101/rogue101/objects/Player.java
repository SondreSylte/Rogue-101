package inf101.rogue101.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import inf101.grid.GridDirection;
import inf101.rogue101.game.IGameView;
import javafx.scene.input.KeyCode;
/**
 * En spiller i Rogue 101 
 * 
 * Spilleren navigerer labyrinten og slåss med monster. For å komme seg ut 
 * og vinne spille må spilleren gå gjennom portalen; for å åpne portalen 
 * må den finne amuletten og bære den med seg. 
 * 
 * På veien kan den plukke opp gull og slåss med monster
 * 
 * @author Anna Eilertsen - anna.eilertsen@uib.no
 *
 */
public class Player implements IPlayer {
	/**
	 * char representation of this type 
	 */
	public static final char SYMBOL = '@';
	private static final int MAXHEALTH = 100;
	private int attack;
	private int defence;
	private int damage;
	private int hp;
	private String name;

	List<IItem> inventory;

	public Player() {
		attack = 10;
		defence = 2;
		damage = 3;
		hp = Player.MAXHEALTH;
		name = System.getProperty("user.name");
		this.inventory =  new ArrayList<>();
	}

	@Override
	public int getAttack() {
		return attack;
	}

	@Override
	public int getDamage() {
		return damage;
	}

	@Override
	public int getCurrentHealth() {
		return hp;
	}

	@Override
	public int getDefence() {
		return defence;
	}

	@Override
	public int getMaxHealth() {
		return Player.MAXHEALTH;
	}

	@Override
	public String getShortName() {
		return getLongName();
	}
	
	@Override
	public String getLongName() {
		return name;
	}

	@Override
	public int getSize() {
		return 5;
	}

	@Override
	public String getGraphicTextSymbol() {
			return "" + SYMBOL;
	}
	
	@Override
	public String getEmoji() {
		return hp > 0 ? "👸" : "⚱️"; // 🤴  ⚰️
	}

	@Override
	public int handleDamage(int amount) {
		amount = Math.max(0, amount - defence);
		amount = Math.min(hp + 1, amount);
		hp -= amount;
		return amount;
	}

	@Override
	public void keyPressed(IGameView game, KeyCode key) {
		System.err.println("Player moves");
		switch (key) {
		case LEFT:
			tryToMove(game, GridDirection.WEST);
			break;
		case RIGHT:
			tryToMove(game, GridDirection.EAST);
			break;
		case UP:
			tryToMove(game, GridDirection.NORTH);
			break;
		case DOWN:
			tryToMove(game, GridDirection.SOUTH);
			break;
		case P:
			pickUp(game);
			break;
		case D:
			drop(game);
			break;
		default:
			System.err.printf("Illegal key pressed. Key: %s", key);
		}
		showStatus(game);
	}

	private void tryToMove(IGameView game, GridDirection dir) {
		if (game.canGo(dir)) {
			game.displayDebug("Moving.");
			game.move(dir);
		} 
		else {
			if(game.attack(dir))
				game.displayDebug("Victory!");
			else
				game.displayDebug("Ouch! Can't go there.");
		}
	}

	private void showStatus(IGameView game) {
		if (inventory.isEmpty()) { //
			game.displayMessage("The hand is empty!"); //viser at hand er tom ved å vise det visuelt i spillet
		}else {
			String s = " ";
			for(IItem i : inventory){ //for-each loop, for hvert item i inventory
				s = s + i.getLongName() + ", "; //bruker metoden getLongName for å legge til namn for item i inventory.
			}
			game.displayMessage("Player has " + this.hp + "hp left and " + s + "in inventory");
		}
	}

	private void pickUp(IGameView game) {
		List<IItem> items = game.getLocalNonActorItems(); //liste items, som henter non-actor items på lokasjonen den spilleren står i.

		if(!items.isEmpty()) {
			Collections.sort(items, new IItemComparator()); //Sorterer items.
			Optional<IItem> found = game.pickUp(items.get(items.size() - 1)); //Liste for items som blir plukket opp.
			found.ifPresent(iItem -> inventory.add(iItem)); //IntelliJ foreslo dette.
			game.displayMessage("Picked up: " + found.get().getLongName());
		}
		else{
			game.displayMessage("Nothing to pick up.");
		}

	}

	private void drop(IGameView game) {
		if (inventory.isEmpty()){ //sjekker om inventory er tomt.
			game.displayMessage("Holds nothing");
		}
		else { //om inventory ikke er tomt, dropper den det første itemet i listen.
			game.drop(inventory.remove(0));
		}
		showStatus(game);
	}

	@Override
	public void doTurn(IGameView game) {
	}
	
	@Override 
	public boolean isDestroyed() {
		return false; //Even when dead, the player should remain on the map
	}

	@Override
	public boolean hasItem(IItem item) {
		return inventory.contains(item) ;
	}
	
	@Override
	public char getSymbol() {
		return SYMBOL;
	}
}
