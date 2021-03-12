package inf101.rogue101.objects;

import java.util.List;
import java.util.Random;

import inf101.gfx.gfxmode.DrawHelper;
import inf101.gfx.gfxmode.IBrush;
import inf101.gfx.textmode.Printer;
import inf101.grid.GridDirection;
import inf101.rogue101.game.EmojiFactory;
import inf101.rogue101.game.IGameView;
import javafx.scene.paint.Color;

/**
 * En snegle i spillet Rogue 101.
 *
 * Snegler er bare en aktør som er på kartet og er en hindring.
 *
 *
 * @author Martin Vatshelle
 *
 */
public class Snail implements IActor {
    /**
     * char representation of this type
     */
    public static final char SYMBOL = '§';
    public static Snail first = null;
    final static int MAXHEALTH = 10;
    private int hp;
    private int defence;
    private int attack;
    private int damage;
    private GridDirection lastDir;

    public Snail() {
        hp = Snail.MAXHEALTH;
        defence = 1; //they are easy to kill
        damage = 1;
        attack = 50; //they sneak in so almost impossible to resist attack
        lastDir = GridDirection.NORTHEAST;
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
        return Snail.MAXHEALTH;
    }

    @Override
    public String getShortName() {
        return getLongName();
    }

    @Override
    public String getLongName() {
        return "Snail";
    }

    @Override
    public int getSize() {
        return 2;
    }

    @Override
    public String getEmoji() {
        return Printer.coloured("\uD83D\uDC0C", Color.GREEN);
    }

    @Override
    public int handleDamage(int amount) {
        amount -= Math.min(amount, getDefence());
        int damage = Math.min(amount, hp);
        hp -= damage;
        return damage;
    }

    @Override
    public boolean draw(IBrush painter, double w, double h) {
        if (!EmojiFactory.USE_EMOJI) {
            DrawHelper.drawSnail(painter, h, w, hp / getMaxHealth());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void doTurn(IGameView game) {

        for(GridDirection dir : GridDirection.EIGHT_DIRECTIONS) {
            if(dir.equals(GridDirection.CENTER))
                continue;

        }

        List<GridDirection> moves = game.getPossibleMoves();
        if (game.canGo(lastDir)) {
            game.move(lastDir);
        } else {
            moves.remove(GridDirection.CENTER);
            if (!moves.isEmpty()) {
                Random r = new Random();
                lastDir = moves.get(r.nextInt(moves.size()));
                game.move(lastDir);
            }
        }
    }

    @Override
    public int getAttack() {
        return this.attack;
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public char getSymbol() {
        return SYMBOL;
    }
}
