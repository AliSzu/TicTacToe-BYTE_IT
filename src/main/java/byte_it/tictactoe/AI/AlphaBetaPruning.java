package byte_it.tictactoe.AI;

import byte_it.tictactoe.Game.TileBoard;
import byte_it.tictactoe.UI.UIConstants;

import static byte_it.tictactoe.Game.Mark.*;

/**
 * MiniMax algorithm optimised by with Alpha-Beta Pruning
 * It is a search algorithm that seeks to decrease the number of nodes that are evaluated by the minimax algorithm in its search tree
 *
 */

public class AlphaBetaPruning {

    private static final int MAX_DEPTH = 12;
    /**
     *
     *
     * @param depth - maximum depth of the game tree
     * @param isMaximizingPlayer - tells if it's MaximizingPlayer's turn
     * @param alpha - best value that the maximizer can guarantee at that level or above
     * @param beta - best value that the minimazer can guarantee at that level or below
     * @return tile value
     */
    private static int miniMax(TileBoard tileBoard, int depth, boolean isMaximizingPlayer, int alpha, int beta) {
        int tileValue = evaluateTileBoard(tileBoard);

        if (tileValue != 0 || depth == MAX_DEPTH || !tileBoard.anyMovesAvailable()) {
            return tileValue;
        }
        if (isMaximizingPlayer) {
            int highestValue = Integer.MIN_VALUE;

            for (int row = 0; row < UIConstants.BOARD_WIDTH; row++) {
                for (int column = 0; column < UIConstants.BOARD_WIDTH; column++) {
                    if (!tileBoard.isTileMarked(row, column)) {
                        tileBoard.setMarkAt(row, column, X);
                        int value = miniMax(tileBoard, depth + 1, false, alpha, beta);
                        tileBoard.setMarkAt(row, column, EMPTY);
                        highestValue = Math.max(highestValue, value);
                        alpha = Math.max(alpha, highestValue);
                        if (beta <= alpha) {
                            break;
                        }
                    }

                }
            }
            return highestValue;
        } else {
            int lowestValue = Integer.MAX_VALUE;

            for (int row = 0; row < UIConstants.BOARD_WIDTH; row++) {
                for (int column = 0; column < UIConstants.BOARD_WIDTH; column++) {
                    if (!tileBoard.isTileMarked(row, column)) {
                        tileBoard.setMarkAt(row, column, O);
                        int value = miniMax(tileBoard, depth + 1, true, alpha, beta);
                        tileBoard.setMarkAt(row, column, EMPTY);
                        lowestValue = Math.min(lowestValue, value);
                        beta = Math.min(beta, lowestValue);
                        if (beta <= alpha) {
                            break;
                        }
                    }
                }

            }
            return lowestValue;
        }
    }

    public static int[] getBestMove(TileBoard tileBoard){
        int[] bestMove = {-1, -1};
        int bestValue = Integer.MIN_VALUE;
        for (int row = 0; row < UIConstants.BOARD_WIDTH; row++) {
            for (int column = 0; column < UIConstants.BOARD_WIDTH; column++) {
                if (!tileBoard.isTileMarked(row, column)) {
                    tileBoard.setMarkAt(row, column, X);
                    int value = miniMax(tileBoard, 0, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    tileBoard.setMarkAt(row, column, EMPTY);
                    if (value > bestValue) {
                        bestMove[0] = row;
                        bestMove[1] = column;
                        bestValue = value;
                    }
                }
            }
        }
        return bestMove;
    }
    /**
     * Evaluate the Tile Board from the perspective of X player
     * Checks rows, columns and diagonals for potential wins or loses
     * For win it returns 1, for loss -1 and for draw 0
     * @return
     */
    private static int evaluateTileBoard(TileBoard tileBoard) {
        int XWin = X.getMark() * UIConstants.BOARD_WIDTH;
        int OWin = O.getMark() * UIConstants.BOARD_WIDTH;
        int sum = 0;

        //Rows
        for (int row = 0; row < UIConstants.BOARD_WIDTH; row++) {
            for (int column = 0; column < UIConstants.BOARD_WIDTH; column++) {
                sum = sum + tileBoard.getTileAt(row, column).getMarkChar();
            }
            if (sum == XWin) {
                return 1;
            } else if (sum == OWin) {
                return -1;
            }
            sum = 0;
        }
        sum = 0;

        //Columns
        for (int column = 0; column < UIConstants.BOARD_WIDTH; column++) {
            for (int row = 0; row < UIConstants.BOARD_WIDTH; row++) {
                sum = sum + tileBoard.getTileAt(row, column).getMarkChar();
            }
            if (sum == XWin) {
                return 1;
            } else if (sum == OWin) {
                return -1;
            }
            sum = 0;
        }
        sum = 0;

        //Left to right diagonal
        for (int i = 0; i < UIConstants.BOARD_WIDTH; i++) {
            sum = sum + tileBoard.getTileAt(i, i).getMarkChar();
        }
        if (sum == XWin) {
            return 1;
        } else if (sum == OWin) {
            return -1;
        }
        sum = 0;

        //Right to Left diagonal
        for (int row = 0; row < UIConstants.BOARD_WIDTH; row++) {
            for (int column = 0; column < UIConstants.BOARD_WIDTH; column++) {
                if (row + column == 2) {
                    sum = sum + tileBoard.getTileAt(row, column).getMarkChar();
                }
            }
        }
        if (sum == XWin) {
            return 1;
        } else if (sum == OWin) {
            return -1;
        }

        return 0;
    }
}
