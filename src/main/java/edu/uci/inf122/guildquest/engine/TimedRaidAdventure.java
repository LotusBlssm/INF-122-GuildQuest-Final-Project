package edu.uci.inf122.guildquest.engine;

import edu.uci.inf122.guildquest.api.AdventureSnapshot;
import edu.uci.inf122.guildquest.api.TurnTimer;
import edu.uci.inf122.guildquest.api.state.GridState;
import edu.uci.inf122.guildquest.api.state.State;
import edu.uci.inf122.guildquest.api.win_conditions.WinCondition;
import edu.uci.inf122.guildquest.content.Realm;
import edu.uci.inf122.guildquest.content.User;
import edu.uci.inf122.guildquest.entities.Entity;
import edu.uci.inf122.guildquest.entities.playablecharacters.PlayableCharacter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TimedRaidAdventure extends MiniAdventure {
    private TurnTimer turnTimer;
    private int currentPlayerIndex;
    private int turnNumber;
    private boolean gameOver;
    private Scanner scanner;
    private GridState grid;
    private List<PlayableCharacter> playerCharacters;
    private int[][] playerPositions; // [playerIndex][0=row, 1=col]
    private String pendingCommand;

    public TimedRaidAdventure(List<Realm> realms, List<Entity> entities,
            List<WinCondition> winConditions, List<User> players,
            State state, int secondsPerPlayer,
            List<PlayableCharacter> playerCharacters) {
        super(realms, entities, winConditions, players, state);
        this.turnTimer = new TurnTimer(players.size(), secondsPerPlayer);
        this.currentPlayerIndex = 0;
        this.turnNumber = 0;
        this.gameOver = false;
        this.scanner = new Scanner(System.in);
        this.grid = (GridState) state;
        this.playerCharacters = playerCharacters;
        this.playerPositions = new int[players.size()][2];
        this.pendingCommand = null;
    }

    @Override
    public String getName() {
        return "Timed Raid";
    }

    @Override
    public String getDescription() {
        return "A timed raid where two players compete under a turn timer.";
    }

    @Override
    public void play() {
        // TODO: Main game loop
    }

    @Override
    public void acceptInput() {
        // TODO: Read the current player's action from stdin
    }

    @Override
    public void advanceCycle() {
        // TODO: End clock, process action, check conditions, swap turns
    }

    @Override
    public AdventureSnapshot saveSnapshot() {
        return new AdventureSnapshot(getName(), turnNumber, 0, 0, new ArrayList<>());
    }

    private User getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    private void swapTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    private boolean checkGameOver() {
        for (int i = 0; i < players.size(); i++) {
            if (turnTimer.isExpired(i)) {
                gameOver = true;
                return true;
            }
        }
        for (WinCondition wc : winConditions) {
            if (wc.isWon(this) || wc.isLost(this)) {
                gameOver = true;
                return true;
            }
        }
        return false;
    }
}
