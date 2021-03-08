package inf101.rogue101.objects;

public class Gold implements IItem{
    public static final char SYMBOL = 'G';
    private int hp = getMaxHealth();

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
        return 15;
    }

    @Override
    public String getLongName() {
        return "gold";
    }

    @Override
    public String getShortName() {
        return "expensive gold";
    }

    @Override
    public int getSize() {
        return 2;
    }

    @Override
    public int handleDamage(int amount) {
        return 0;
    }

    @Override
    public char getSymbol() {
        return SYMBOL;
    }
}
