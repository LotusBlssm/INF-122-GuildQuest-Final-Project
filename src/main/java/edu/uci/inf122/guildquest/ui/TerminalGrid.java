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
        for (List<GridCell> row : grid){
            if (row==null) continue;
            for (GridCell cell : row){
                if (cell.isEmpty()) {
                    out.print("|"+" ".repeat(longestNameLen+2)+"|");
                    continue;
                }
                padding = longestNameLen - cell.getContent().get(cell.getContent().size()-1).getName().length();
                leftPad = padding / 2;
                rightPad = padding - leftPad;
                out.print("| "+" ".repeat(leftPad)+
                        cell.getContent().get(cell.getContent().size()-1).getName()
                        +" ".repeat(rightPad)+" |");
            }
            out.print('\n');
        }
    }

    @Override
    public void changeState() {

    }
}
