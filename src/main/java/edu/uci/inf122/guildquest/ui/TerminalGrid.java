package edu.uci.inf122.guildquest.ui;

import edu.uci.inf122.guildquest.api.state.GridCell;
import edu.uci.inf122.guildquest.api.state.GridState;

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
}
