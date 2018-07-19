package com.mygdx.game.model;

/**
 * Created by carolinacandido on 18/07/18.
 */

public class Move {

    private int moveIdx;
    private boolean currentMove;
    private boolean moveHasPassed;

    public Move(int moveIdx)
    {
        this.moveIdx = moveIdx;
        this.currentMove = false;
        this.moveHasPassed = false;
    }

    public int getMoveIdx() {
        return moveIdx;
    }

    public void setMoveIdx(int moveIdx) {
        this.moveIdx = moveIdx;
    }

    public boolean isCurrentMove() {
        return currentMove;
    }

    public void setCurrentMove(boolean currentMove) {
        this.currentMove = currentMove;
    }

    public boolean isMoveHasPassed() {
        return moveHasPassed;
    }

    public void setMoveHasPassed(boolean moveHasPassed) {
        this.moveHasPassed = moveHasPassed;
    }
}
