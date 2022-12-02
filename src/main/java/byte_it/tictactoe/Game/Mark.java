package byte_it.tictactoe.Game;

public enum Mark {
    X('X'),
    O('O'),
    EMPTY(' ');

    Mark(char initMark) {
        this.mark = initMark;
    }

    private final char mark;

    public char getMark()
    {
        return this.mark;
    }

    @Override
    public String toString() {
        return String.valueOf(mark);
    }
};


