package edu.uci.inf122.guildquest.api;

public class TurnTimer {
    private long[] playerBankMillis;
    private long turnStartTime;
    private int activePlayer;

    public TurnTimer(int numPlayers, int secondsPerPlayer) {
        if (numPlayers <= 0) {
            throw new IllegalArgumentException("Must have at least 1 player");
        }
        if (secondsPerPlayer <= 0) {
            throw new IllegalArgumentException("Seconds per player must be positive");
        }
        playerBankMillis = new long[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            playerBankMillis[i] = secondsPerPlayer * 1000L;
        }
        activePlayer = -1;
    }

    public void startTurn(int playerIndex) {
        activePlayer = playerIndex;
        turnStartTime = System.currentTimeMillis();
    }

    public void endTurn() {
        if (activePlayer == -1)
            return;
        long elapsed = System.currentTimeMillis() - turnStartTime;
        playerBankMillis[activePlayer] -= elapsed;
        if (playerBankMillis[activePlayer] < 0) {
            playerBankMillis[activePlayer] = 0;
        }
        activePlayer = -1;
    }

    public boolean isExpired(int playerIndex) {
        if (playerIndex == activePlayer) {
            long elapsed = System.currentTimeMillis() - turnStartTime;
            return (playerBankMillis[playerIndex] - elapsed) <= 0;
        }
        return playerBankMillis[playerIndex] <= 0;
    }

    public int getSecondsRemaining(int playerIndex) {
        long remaining = playerBankMillis[playerIndex];
        if (playerIndex == activePlayer) {
            long elapsed = System.currentTimeMillis() - turnStartTime;
            remaining -= elapsed;
        }
        if (remaining < 0)
            remaining = 0;
        return (int) (remaining / 1000);
    }
}
