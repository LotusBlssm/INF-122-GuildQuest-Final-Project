package edu.uci.inf122.guildquest.ui;

import edu.uci.inf122.guildquest.api.state.GridCell;
import edu.uci.inf122.guildquest.api.state.GridState;

import java.util.List;

public class TerminalGrid extends GridState {
    private GridState grid;
    private Page out;

    @Override
    public void render() {
        int longestNameLen=0;
        for (List<GridCell> row : grid.getGrid()){
            for (GridCell cell : row){
                String name = cell.getContent().getName().toString();
                longestNameLen = Math.max(name.length(), longestNameLen);
            }
        }

        int leftPad;
        int rightPad;
        int padding;
        for (List<GridCell> row : grid.getGrid()){
            for (GridCell cell : row){
                String name = cell.getContent().getName().toString();
                padding = longestNameLen - name.length();
                leftPad = padding / 2;
                rightPad = padding - leftPad;
                out.print("| "+" ".repeat(leftPad)+name+" ".repeat(rightPad)+" |");
            }
            out.print('\n');
        }
    }

    @Override
    public void changeState() {

    }
}
