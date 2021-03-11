package inf101.rogue101.objects;

import java.util.Collections;
import java.util.List;

import inf101.gfx.textmode.Printer;
import inf101.grid.Grid;
import inf101.grid.GridDirection;
import inf101.rogue101.game.IGameView;
import javafx.scene.paint.Color;

/**
 * Kaniner i spillet Rogue 101. 
 * Kaniner hopper rundt i labyrinten på jakt etter gulrøtter. 
 * De har god luktesans og kan lukte gulrøtter på lang avstand. 
 * 
 * De forbrenner energi raskt og må være effektiv 
 * 
 * @author Knut Anders Stokke
 * @author Martin Vatshelle
 *
 */
public class Rabbit implements IActor {
	/**
	 * char representation of this type 
	 */
	public static final char SYMBOL = 'R';
	private int hp = getMaxHealth();

	@Override
	public void doTurn(IGameView game)/*{
		boolean isHungry = getMaxHealth()-getCurrentHealth()>2;
		if(isHungry) {
			boolean gotFood = eatIfPossible(game);
			if(gotFood) {
				//Rabbit is full and prefers to rest
				return;
			}
		}

		List<GridDirection> possibleMoves = game.getPossibleMoves();
		List<IItem> nearbyItems = game.getNearbyItems(10);
		GridDirection carrotDirection = null;
		for(IItem item : nearbyItems) {
			if(item instanceof Carrot) {
				carrotDirection = game.getDirectionTo(item);
				if(possibleMoves.contains(carrotDirection)) {
					performMove(game, carrotDirection);
				}
				else if(!possibleMoves.contains(carrotDirection)){//dersom cDirection ikke kan gå i retning gulrot
					int index = GridDirection.EIGHT_DIRECTIONS.indexOf(carrotDirection);//Finner indexen til
					for(GridDirection dir : possibleMoves) {
						performMove(game, dir);
					}
				}
			}
		}

		if(carrotDirection == null) {
			GridDirection dir = selectMove(game);

			for(GridDirection carrotDir : GridDirection.EIGHT_DIRECTIONS) {
				if(game.containsItem(carrotDir, Carrot.class)) {
					dir = carrotDir;
				}
				performMove(game,dir);

			}
		}
	}



	public GridDirection goAround() {

		return null;
	}*/



	{

		boolean isHungry = getMaxHealth() - getCurrentHealth() > 2;
		if (isHungry) {
			boolean gotFood = eatIfPossible(game);
			if (gotFood) {
				return;
			}
		}


		List<IItem> nearbyItems = game.getNearbyItems(10);
		GridDirection nearbyCarrot = null;
		for (IItem item : nearbyItems) {
			if (item instanceof Carrot) {
				nearbyCarrot = game.getDirectionTo(item);
				System.out.println("Carrot direction" + nearbyCarrot.toString());
				//performMove(game,nearbyCarrot);
				break;
			}
		}
		if (nearbyCarrot != null) {
			if (game.canGo(nearbyCarrot)) {
				performMove(game, nearbyCarrot);
				return;
			}
		} else {
			//GridDirection direction = selectMove(game);
			for (GridDirection carrotDirection : GridDirection.EIGHT_DIRECTIONS) {
				if (game.containsItem(carrotDirection, Carrot.class)) {
					if (game.canGo(carrotDirection)) {
						performMove(game, carrotDirection);
						return;
					}
				}

			}
		}
		GridDirection dir = selectMove(game);
		performMove(game, dir);




}




	/**
	 * This method selects which move the Rabbit want to make.
	 */
	private GridDirection selectMove(IGameView game) {
		List<GridDirection> possibleMoves = game.getPossibleMoves();
		Collections.shuffle(possibleMoves);
		if (possibleMoves.size() > 0 )
			return possibleMoves.get(0);
		else return GridDirection.CENTER;
		//return possibleMoves.get(0);
	}

	/**
	 * Burns one round of energy
	 * Rabbits only burn energy if they move, not when laying still.
	 */
	private void burnEnergy() {
		hp--;
	}

	/**
	 * Performs a move
	 * Every time a Rabbit moves it burns energy
	 * GridDirection.CENTER means the Rabbit is resting and not burning energy
	 * 
	 * @param game
	 */
	private void performMove(IGameView game, GridDirection dir) {
		if (!dir.equals(GridDirection.CENTER)) {
			burnEnergy();
			boolean moved = game.move(dir);
			if(!moved) {
				game.displayMessage("Rabbit wasted energy on move");
			}
		}
	}

	/**
	 * Eats carrot if any exist at current location
	 * 
	 * @param game
	 *            The game the object exists in
	 * @return true if it spend the turn eating
	 */
	private boolean eatIfPossible(IGameView game) {
		for (IItem item : game.getLocalNonActorItems()) {
			if (item instanceof Carrot) {
				System.out.println("found carrot!");
				int eaten = item.handleDamage(Math.min(5, getMaxHealth()-getCurrentHealth()));
				if (eaten > 0) {
					System.out.println("ate carrot worth " + eaten + "!");
					hp += eaten;
					game.displayMessage("You hear a faint crunching (" + getLongName() + " eats " + item.getArticle()
							+ " " + item.getLongName() + ")");
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int getAttack() {
		return 5;
	}

	@Override
	public int getCurrentHealth() {
		return hp;
	}

	@Override
	public int getDamage() {
		return 8;
	}

	@Override
	public int getDefence() {
		return 2;
	}

	@Override
	public int getMaxHealth() {
		return 10;
	}

	@Override
	public String getShortName() {
		return getLongName();
	}

	public String getLongName() {
		return "rabbit";
	}

	@Override
	public int getSize() {
		return 4;
	}

	@Override
	public String getGraphicTextSymbol() {
		return hp > 0 ? "" + SYMBOL : "¤";
	}
	
	@Override
	public String getEmoji() {
		return hp > 0 ? Printer.coloured("🐰", Color.LIGHTPINK) : "💀"; // 🐇
	}

	@Override
	public int handleDamage(int amount) {
		amount -= Math.min(amount, getDefence());
		int damage = Math.min(amount, hp);
		hp -= damage;
		return damage;
	}
	
	@Override
	public char getSymbol() {
		return SYMBOL;
	}
}
