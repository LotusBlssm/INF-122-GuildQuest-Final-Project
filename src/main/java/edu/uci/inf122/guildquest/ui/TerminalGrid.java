package edu.uci.inf122.guildquest.ui;

import edu.uci.inf122.guildquest.api.state.GridCell;
import edu.uci.inf122.guildquest.api.state.GridState;
import edu.uci.inf122.guildquest.entities.playablecharacters.Move;

import java.util.List;

public class TerminalGrid extends GridState {
//    private GridState grid;
    private Page out;
    public TerminalGrid(int l, int w){
        super(l, w);
        out = Page.getPage();
    }

    @Override
    public void render() {
        out.print(getGridStr());
    }
    public String getGridStr(){
        int longestNameLen=0;
        for (List<GridCell> row : grid){
            if (row==null) continue;
            for (GridCell cell : row){
                if (cell.isEmpty()) continue;
                longestNameLen = Math.max(cell.getContent().get(cell.getContent().size()-1).getName().length(), longestNameLen);
            }
        }

        int leftPad;
        int rightPad;
        int padding;
        StringBuilder res = new StringBuilder();
        res.append("-".repeat((longestNameLen+4)*grid.size())).append('\n');
        for (List<GridCell> row : grid){
            if (row==null) continue;
            for (GridCell cell : row){
                if (cell.isEmpty()) {
                    res.append("|").append(" ".repeat(longestNameLen + 2)).append("|");
                    continue;
                }
                padding = longestNameLen - cell.getContent().get(cell.getContent().size()-1).getName().length();
                leftPad = padding / 2;
                rightPad = padding - leftPad;
                res.append("| ")
                        .append(" ".repeat(leftPad))
                        .append(cell.getContent().get(cell.getContent().size() - 1).getName())
                        .append(" ".repeat(rightPad))
                        .append(" |");
            }
            res.append('\n');
        }
        res.append("-".repeat((longestNameLen+4)*grid.size())).append('\n');
        return res.toString();
    }

    @Override
    public void changeState() {

    }

    public static String toOptions(List<Move.ValidMoves> moves) {
        StringBuilder res = new StringBuilder();
        for (Move.ValidMoves m : moves){
            switch (m){
                case ATTACK -> res.append("Attack: North (n), South (s), East (e), West (w)\n");
                case TRAVEL -> res.append("Move: North (n), South (s), East (e), West (w)\n");
                case HEAL_OTHER -> res.append("Heal other: North (n), South (s), East (e), West (w)\n");
                case HEAL_SELF -> res.append("Heal self: (heal self)\n");
                case TAKE_ITEM -> res.append("Take Item: North (n), South (s), East (e), West (w)\n");
                case USE_ITEM -> res.append("Use Item: (use item)\n");
                case REQUEST_HINT -> res.append("Request Hint: (r)\n");
            }
        }
        return res.toString();
    }
    public static String toOptionsRegex(List<Move.ValidMoves> moves) {
        StringBuilder res = new StringBuilder();
        for (Move.ValidMoves m : moves){
            switch (m){
                case ATTACK -> res.append("(attack [nsew])");
                case TRAVEL -> res.append("(move [nsew])");
                case HEAL_OTHER -> res.append("(heal [nsew])");
                case HEAL_SELF -> res.append("(heal self)");
                case TAKE_ITEM -> res.append("(take item [nsew])");
                case USE_ITEM -> res.append("(use item)");
                case REQUEST_HINT -> res.append("(r)");
            }
            res.append('|');
        }
        res.deleteCharAt(res.length()-1);
        return res.toString();
    }
}
