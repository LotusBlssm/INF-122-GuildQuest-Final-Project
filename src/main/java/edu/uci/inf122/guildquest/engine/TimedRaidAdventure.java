package edu.uci.inf122.guildquest.engine;

import edu.uci.inf122.guildquest.api.AdventureSnapshot;
import edu.uci.inf122.guildquest.api.TurnTimer;
import edu.uci.inf122.guildquest.api.state.GridCell;
import edu.uci.inf122.guildquest.api.state.GridState;
import edu.uci.inf122.guildquest.api.state.State;
import edu.uci.inf122.guildquest.api.win_conditions.WinCondition;
import edu.uci.inf122.guildquest.content.Realm;
import edu.uci.inf122.guildquest.content.User;
import edu.uci.inf122.guildquest.entities.Entity;
import edu.uci.inf122.guildquest.entities.npcs.Hostile;
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
        placePlayersOnGrid();

        System.out.println("=== " + getName() + " ===");
        System.out.println(getDescription());
        System.out.println();

        while (!gameOver) {
            renderGrid();
            printStatus();

            turnTimer.startTurn(currentPlayerIndex);
            acceptInput();
            advanceCycle();
        }

        announceResults();
    }

    @Override
    public void acceptInput() {
        User player = getCurrentPlayer();
        System.out.println();
        System.out.println(player.getUsername() + "'s turn (Time: "
                + turnTimer.getSecondsRemaining(currentPlayerIndex) + "s)");
        System.out.println("Commands: move <n/s/e/w>, end");
        System.out.print("> ");

        pendingCommand = scanner.nextLine().trim().toLowerCase();
    }

    @Override
    public void advanceCycle() {
        if (turnTimer.isExpired(currentPlayerIndex)) {
            turnTimer.endTurn();
            System.out.println(getCurrentPlayer().getUsername() + " ran out of time!");
            gameOver = true;
            return;
        }

        processCommand(pendingCommand);
        turnTimer.endTurn();

        if (!checkGameOver()) {
            turnNumber++;
            swapTurn();
        }
    }

    @Override
    public AdventureSnapshot saveSnapshot() {
        return new AdventureSnapshot(getName(), turnNumber, 0, 0, new ArrayList<>());
    }

    private boolean processCommand(String command) {
        if (command == null || command.isEmpty()) {
            System.out.println("No command entered.");
            return false;
        }

        String[] parts = command.split("\\s+");
        String action = parts[0];

        switch (action) {
            case "n", "s", "e", "w" -> handleMove(action);
            case "move" -> {
                if (parts.length < 2) {
                    System.out.println("Usage: move <n/s/e/w>");
                    return false;
                }
                handleMove(parts[1]);
            }
            case "end" -> System.out.println("Turn ended.");
            default -> {
                System.out.println("Unknown command: " + action);
                return false;
            }
        }
        return true;
    }

    private void handleMove(String direction) {
        int[] current = playerPositions[currentPlayerIndex];
        int newRow = current[0];
        int newCol = current[1];

        switch (direction) {
            case "n" -> newRow--;
            case "s" -> newRow++;
            case "e" -> newCol++;
            case "w" -> newCol--;
            default -> {
                System.out.println("Invalid direction. Use n/s/e/w.");
                return;
            }
        }

        if (!grid.isValidPosition(newRow, newCol)) {
            System.out.println("Can't move there — out of bounds.");
            return;
        }

        GridCell targetCell = grid.getCell(newRow, newCol);
        if (!targetCell.isEmpty()) {
            System.out.println("That cell is occupied.");
            return;
        }

        PlayableCharacter pc = playerCharacters.get(currentPlayerIndex);
        grid.getCell(current[0], current[1]).removeContent();
        grid.setCell(newRow, newCol, pc);
        current[0] = newRow;
        current[1] = newCol;

        System.out.println(pc.getName() + " moved " + directionName(direction) + ".");
    }

    private void renderGrid() {
        System.out.println();
        int rows = grid.getLength();
        int cols = grid.getWidth();

        System.out.print("   ");
        for (int c = 0; c < cols; c++) {
            System.out.printf(" %2d ", c);
        }
        System.out.println();

        for (int r = 0; r < rows; r++) {
            System.out.printf("%2d ", r);
            for (int c = 0; c < cols; c++) {
                GridCell cell = grid.getCell(r, c);
                if (cell.isEmpty()) {
                    System.out.print(" .  ");
                } else {
                    Entity e = cell.getContent().get(0);
                    System.out.print(" " + getEntitySymbol(e) + "  ");
                }
            }
            System.out.println();
        }
    }

    private String getEntitySymbol(Entity e) {
        if (e instanceof PlayableCharacter) {
            int idx = playerCharacters.indexOf(e);
            return "P" + (idx + 1);
        }
        if (e instanceof Hostile)
            return "!!";
        return "??";
    }

    private void placePlayersOnGrid() {
        for (int i = 0; i < playerCharacters.size(); i++) {
            PlayableCharacter pc = playerCharacters.get(i);
            int row = i * (grid.getLength() - 1);
            playerPositions[i][0] = row;
            playerPositions[i][1] = 0;
            grid.setCell(row, 0, pc);
        }
    }

    private void printStatus() {
        System.out.println("--- Turn " + turnNumber + " ---");
        for (int i = 0; i < players.size(); i++) {
            PlayableCharacter pc = playerCharacters.get(i);
            String marker = (i == currentPlayerIndex) ? " <<" : "";
            System.out.println("  " + players.get(i).getUsername()
                    + " (" + pc.getName() + ") HP:" + pc.getHealth().getHealth()
                    + " | Time: " + turnTimer.getSecondsRemaining(i) + "s" + marker);
        }
    }

    private void announceResults() {
        System.out.println();
        System.out.println("=== GAME OVER ===");

        for (int i = 0; i < players.size(); i++) {
            if (turnTimer.isExpired(i)) {
                System.out.println(players.get(i).getUsername() + " ran out of time!");
            }
        }

        for (WinCondition wc : winConditions) {
            if (wc.isWon(this)) {
                System.out.println("Victory! The raid was successful!");
            }
            if (wc.isLost(this)) {
                System.out.println("Defeat! The raid failed.");
            }
        }
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

    private String directionName(String d) {
        return switch (d) {
            case "n" -> "north";
            case "s" -> "south";
            case "e" -> "east";
            case "w" -> "west";
            default -> d;
        };
    }
}
