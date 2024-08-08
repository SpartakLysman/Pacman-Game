package pacmanGame.screens.gameScreen;

public class DigitsBlock implements DigitsBlockDelegate {

    private int currentValue = 0;
    private DigitsBlockDelegate delegate;

    public int getValue() {
        return currentValue;
    }

    public void reset() {
        currentValue = 0;

    }

    @Override
    public void next() {
        if (delegate != null) {
            if (currentValue < 60) {
                currentValue++;
            } else {
                reset();
                delegate.next();
            }
        } else {
            if (currentValue < 24) {
                currentValue++;
            } else {
                reset();
            }
        }
    }

    public void setDelegate(DigitsBlockDelegate delegate) {
        this.delegate = delegate;
    }

    public String getFirstDigit() {
        return String.valueOf(toString().charAt(0));
    }

    public String getSecondDigit() {
        return String.valueOf(toString().charAt(1));
    }

    @Override
    public String toString() {
        if (currentValue > 9) {
            return "" + currentValue;
        }
        return "0" + currentValue;
    }
}
