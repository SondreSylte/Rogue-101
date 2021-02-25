package inf101.rogue101.objects;

import inf101.gfx.gfxmode.DrawHelper;
import inf101.gfx.gfxmode.IBrush;
import inf101.gfx.textmode.Printer;
import inf101.rogue101.game.EmojiFactory;
import javafx.scene.paint.Color;

/**
 * En gulrot i Rogue 101-spillet.  
 * 
 * Gulrøtter dør hvis de blir spist. 
 * 
 * @author Anna Eilertsen - anna.eilertsen@uib.no
 *
 */
public class Carrot implements IItem {
	/**
	 * char representation of this type 
	 */
	public static final char SYMBOL = 'C';
	private int hp = getMaxHealth();

	@Override
	public boolean draw(IBrush painter, double w, double h) {
		if (!EmojiFactory.USE_EMOJI) {
			DrawHelper.drawCarrot(painter, h, w, getHealthStatus());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int getCurrentHealth() {
		return hp;
	}

	@Override
	public int getDefence() {
		return 0;
	}

	@Override
	public int getMaxHealth() {
		return 5;
	}

	@Override
	public String getShortName() {
		return "carrot";
	}

	@Override
	public String getLongName() {
		return "juicy carrot";
	}

	@Override
	public int getSize() {
		return 2;
	}
	
	@Override
	public String getEmoji() {
		return Printer.coloured("🥕", Color.ORANGE);
	}

	@Override
	public int handleDamage(int amount) {
		int damageDone = amount;
		//setter damageDone til å være lik amount, den faktiske damagen som blir gitt.
		//sjekker om damagen er mer enn hp'en til tingen.
		//om damagen er mer enn det tingen tåler, setter vi hp til 0, fordi den er ødelagt.
		//Vi setter først damageDone til hp, fordi man kan ikke påføre mer damage enn hp.
		// else, setter vi at damageDone er hp - amount, og returnerer damageDone.
		if (amount > hp){
			damageDone = hp;
			hp = 0;
		} else {
			damageDone = amount;
			hp -= amount;
		}
		return damageDone;
	}
	
	@Override
	public char getSymbol() {
		return SYMBOL;
	}
}
