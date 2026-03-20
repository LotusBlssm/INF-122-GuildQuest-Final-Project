package edu.uci.inf122.guildquest.engine;

import edu.uci.inf122.guildquest.api.AdventureSnapshot;
import edu.uci.inf122.guildquest.api.Status;
import edu.uci.inf122.guildquest.api.TurnTimer;
import edu.uci.inf122.guildquest.api.state.GridCell;
import edu.uci.inf122.guildquest.api.state.GridState;
import edu.uci.inf122.guildquest.api.state.State;
import edu.uci.inf122.guildquest.api.win_conditions.WinCondition;
import edu.uci.inf122.guildquest.content.Realm;
import edu.uci.inf122.guildquest.content.User;
import edu.uci.inf122.guildquest.entities.Entity;
import edu.uci.inf122.guildquest.entities.npcs.Hostile;
import edu.uci.inf122.guildquest.entities.npcs.NPC;
import edu.uci.inf122.guildquest.entities.playablecharacters.PlayableCharacter;

import edu.uci.inf122.guildquest.ui.Page;

import java.util.ArrayList;
import java.util.List;

public class TimedRaidAdventure extends MiniAdventure {
    private TurnTimer turnTimer;
    private int currentPlayerIndex;
    private int turnNumber;
    private boolean gameOver;
    private Page page;
    private GridState grid;
    private List<PlayableCharacter> playerCharacters;
    private int[][] playerPositions; // [playerIndex][0=row, 1=col]
    private String pendingCommand;
    private List<User> players;
    private List<Entity> entities;
    private List<WinCondition> winConditions;

    public TimedRaidAdventure(List<Realm> realms, List<Entity> entities,
            List<WinCondition> winConditions, List<User> players,
            State state, int secondsPerPlayer,
            List<PlayableCharacter> playerCharacters) {
        super(realms, entities, winConditions, players);
        this.players = players;
        this.entities = entities;
        this.winConditions = winConditions;
        this.turnTimer = new TurnTimer(players.size(), secondsPerPlayer);
        this.currentPlayerIndex = 0;
        this.turnNumber = 0;
        this.gameOver = false;
        this.page = Page.getPage();
        this.grid = (GridState) state;
        this.playerCharacters = playerCharacters;
        this.playerPositions = new int[players.size()][2];
        this.pendingCommand = null;
    }

    public String getName() {
        return "Timed Raid";
    }

    public String getDescription() {
        return "A timed raid where players compete under a turn timer.";
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
            boolean validCommand = false;
            while (!validCommand && !gameOver) {
                acceptInput();
                if (turnTimer.isExpired(currentPlayerIndex)) {
                    turnTimer.endTurn();
                    System.out.println(getCurrentPlayer().getUsername() + " ran out of time!");
                    gameOver = true;
                    break;
                }
                validCommand = processCommand(pendingCommand);
            }
            if (!gameOver) {
                removeDeadEntities();
                turnTimer.endTurn();
                if (!checkGameOver()) {
                    turnNumber++;
                    if (currentPlayerIndex == players.size() - 1) {
                        hostileTurn();
                        removeDeadEntities();
                        if (checkGameOver())
                            continue;
                    }
                    swapTurn();
                }
            }
        }

        announceResults();
    }

    @Override
    public Status acceptInput() {
        User player = getCurrentPlayer();
        System.out.println();
        System.out.println(player.getUsername() + "'s turn (Time: "
                + turnTimer.getSecondsRemaining(currentPlayerIndex) + "s)");
        System.out.println("Commands: move <n/s/e/w>, attack <n/s/e/w>, open <n/s/e/w>, end");
        System.out.print("> ");

        pendingCommand = page.acceptStr("").trim().toLowerCase();
        return new Status(Status.Option.CONTINUE);
    }

    @Override
    public Status advanceCycle() {
        turnTimer.endTurn();
        if (!checkGameOver()) {
            turnNumber++;
            swapTurn();
        }
        return new Status(Status.Option.CONTINUE);
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

        return switch (action) {
            case "n", "s", "e", "w" -> handleMove(action);
            case "move" -> {
                if (parts.length < 2) {
                    System.out.println("Usage: move <n/s/e/w>");
                    yield false;
                }
                yield handleMove(parts[1]);
            }
            case "attack" -> {
                if (parts.length < 2) {
                    System.out.println("Usage: attack <n/s/e/w>");
                    yield false;
                }
                yield handleAttack(parts[1]);
            }
            case "open" -> {
                if (parts.length < 2) {
                    System.out.println("Usage: open <n/s/e/w>");
                    yield false;
                }
                yield handleOpen(parts[1]);
            }
            case "end" -> {
                System.out.println("Turn ended.");
                yield true;
            }
            default -> {
                System.out.println("Unknown command: " + action);
                yield false;
            }
        };
    }

    private boolean handleMove(String direction) {
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
                return false;
            }
        }

        if (!grid.isValidPosition(newRow, newCol)) {
            System.out.println("Can't move there — out of bounds.");
            return false;
        }

        GridCell targetCell = grid.getCell(newRow, newCol);
        if (!targetCell.isEmpty()) {
            System.out.println("That cell is occupied.");
            return false;
        }

        PlayableCharacter pc = playerCharacters.get(currentPlayerIndex);
        grid.getCell(current[0], current[1]).removeContent();
        grid.setCell(newRow, newCol, pc);
        current[0] = newRow;
        current[1] = newCol;

        System.out.println(pc.getName() + " moved " + directionName(direction) + ".");
        return true;
    }

    private boolean handleAttack(String direction) {
        int[] current = playerPositions[currentPlayerIndex];
        int targetRow = current[0];
        int targetCol = current[1];

        switch (direction) {
            case "n" -> targetRow--;
            case "s" -> targetRow++;
            case "e" -> targetCol++;
            case "w" -> targetCol--;
            default -> {
                System.out.println("Invalid direction. Use n/s/e/w.");
                return false;
            }
        }

        if (!grid.isValidPosition(targetRow, targetCol)) {
            System.out.println("Nothing to attack there.");
            return false;
        }

        GridCell targetCell = grid.getCell(targetRow, targetCol);
        if (targetCell.isEmpty()) {
            System.out.println("No entity in that direction.");
            return false;
        }

        PlayableCharacter pc = playerCharacters.get(currentPlayerIndex);
        Entity target = targetCell.getContent().get(0);

        pc.attack(target);
        return true;
    }

    private boolean handleOpen(String direction) {
        int[] current = playerPositions[currentPlayerIndex];
        int targetRow = current[0];
        int targetCol = current[1];

        switch (direction) {
            case "n" -> targetRow--;
            case "s" -> targetRow++;
            case "e" -> targetCol++;
            case "w" -> targetCol--;
            default -> {
                System.out.println("Invalid direction. Use n/s/e/w.");
                return false;
            }
        }

        if (!grid.isValidPosition(targetRow, targetCol)) {
            System.out.println("Nothing to open there.");
            return false;
        }

        GridCell targetCell = grid.getCell(targetRow, targetCol);
        if (targetCell.isEmpty()) {
            System.out.println("Nothing in that direction.");
            return false;
        }

        Entity target = targetCell.getContent().get(0);
        // TODO: chest interaction, loot items, etc.
        System.out.println("Interacting with " + target.getName() + "...");
        target.act();
        return true;
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
        int rows = grid.getLength();
        int cols = grid.getWidth();
        int[][] corners = {{0, 0}, {rows - 1, 0}, {0, cols - 1}, {rows - 1, cols - 1}};
        for (int i = 0; i < playerCharacters.size(); i++) {
            PlayableCharacter pc = playerCharacters.get(i);
            int row = corners[i][0];
            int col = corners[i][1];
            playerPositions[i][0] = row;
            playerPositions[i][1] = col;
            grid.setCell(row, col, pc);
        }
    }

    private void printStatus() {
        System.out.println("--- Turn " + turnNumber + " ---");
        System.out.println("Players:");
        for (int i = 0; i < players.size(); i++) {
            PlayableCharacter pc = playerCharacters.get(i);
            String marker = (i == currentPlayerIndex) ? " <<" : "";
            System.out.println("  [P" + (i + 1) + "] " + players.get(i).getUsername()
                    + " (" + pc.getName() + ") HP:" + pc.getHealth()
                    + " | Time: " + turnTimer.getSecondsRemaining(i) + "s" + marker);
        }
        boolean hasEnemies = false;
        for (Entity e : entities) {
            if (e instanceof NPC npc && e instanceof Hostile && !npc.isDead()) {
                if (!hasEnemies) {
                    System.out.println("Enemies:");
                    hasEnemies = true;
                }
                System.out.println("  " + npc.getName() + " HP:" + npc.getHealth());
            }
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
            if (wc.isWon()) {
                System.out.println("Victory! The raid was successful!");
            }
            if (wc.isLost()) {
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

    private void hostileTurn() {
        System.out.println();
        System.out.println("--- Enemy turn ---");
        java.util.Set<Entity> acted = new java.util.HashSet<>();
        for (int r = 0; r < grid.getLength(); r++) {
            for (int c = 0; c < grid.getWidth(); c++) {
                GridCell cell = grid.getCell(r, c);
                if (cell.isEmpty())
                    continue;
                Entity e = cell.getContent().get(0);
                if (!(e instanceof Hostile hostile) || !(e instanceof NPC npc))
                    continue;
                if (npc.isDead() || acted.contains(e))
                    continue;
                acted.add(e);

                boolean attacked = false;
                int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
                for (int[] d : dirs) {
                    int adjR = r + d[0];
                    int adjC = c + d[1];
                    if (!grid.isValidPosition(adjR, adjC))
                        continue;
                    GridCell adjCell = grid.getCell(adjR, adjC);
                    if (!adjCell.isEmpty() && adjCell.getContent().get(0) instanceof PlayableCharacter) {
                        hostile.attack(adjCell.getContent().get(0));
                        attacked = true;
                        break;
                    }
                }

                if (!attacked) {
                    int bestDist = Integer.MAX_VALUE;
                    int bestPIdx = 0;
                    for (int p = 0; p < playerPositions.length; p++) {
                        int dist = grid.getDistance(r, c, playerPositions[p][0], playerPositions[p][1]);
                        if (dist < bestDist) {
                            bestDist = dist;
                            bestPIdx = p;
                        }
                    }

                    int targetR = playerPositions[bestPIdx][0];
                    int targetC = playerPositions[bestPIdx][1];
                    int moveR = r;
                    int moveC = c;

                    if (Math.abs(targetR - r) >= Math.abs(targetC - c)) {
                        moveR += (targetR > r) ? 1 : -1;
                    } else {
                        moveC += (targetC > c) ? 1 : -1;
                    }

                    if (grid.isValidPosition(moveR, moveC) && grid.getCell(moveR, moveC).isEmpty()) {
                        cell.removeContent();
                        grid.setCell(moveR, moveC, e);
                        System.out.println(npc.getName() + " moves closer...");
                    }
                }
            }
        }
    }

    private void removeDeadEntities() {
        for (int r = 0; r < grid.getLength(); r++) {
            for (int c = 0; c < grid.getWidth(); c++) {
                GridCell cell = grid.getCell(r, c);
                if (!cell.isEmpty()) {
                    Entity e = cell.getContent().get(0);
                    if (e instanceof NPC npc && npc.isDead()) {
                        cell.removeContent();
                        System.out.println(npc.getName() + " has been slain!");
                    }
                }
            }
        }
    }

    private boolean checkGameOver() {
        for (int i = 0; i < players.size(); i++) {
            if (turnTimer.isExpired(i)) {
                gameOver = true;
                return true;
            }
        }
        for (WinCondition wc : winConditions) {
            if (wc.isWon() || wc.isLost()) {
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
