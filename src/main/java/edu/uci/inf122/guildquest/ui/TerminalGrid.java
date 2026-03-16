package edu.uci.inf122.guildquest.ui;

import edu.uci.inf122.guildquest.api.state.GridCell;
import edu.uci.inf122.guildquest.api.state.GridState;

import java.util.List;

public class TerminalGrid extends GridState {
    private GridState grid;
    private Page out;
    public TerminalGrid(int l, int w){
        super(l, w);
        out = Page.getPage();
    }

    @Override
    public void render() {
        int longestNameLen=0;
        for (List<GridCell> row : grid.getGrid()){
            for (GridCell cell : row){
                longestNameLen = Math.max(cell.getContent().getLast().getName().length(), longestNameLen);
            }
        }

        int leftPad;
        int rightPad;
        int padding;
        for (List<GridCell> row : grid.getGrid()){
            for (GridCell cell : row){
                padding = longestNameLen - cell.getContent().getLast().getName().length();
                leftPad = padding / 2;
                rightPad = padding - leftPad;
                out.print("| "+" ".repeat(leftPad)+cell.getContent().getLast()+" ".repeat(rightPad)+" |");
            }
            out.print('\n');
        }
    }

    @Override
    public void changeState() {

    }
}
