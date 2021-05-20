package com.sachin.tictactoe.board

import android.util.Log

class TicTacToeBoard(private val boardSize: Int, _firstMove: TicTacToeMarks = TicTacToeMarks.X) :
    BoardFunctions {

    private var board: Array<Array<TicTacToeMarks>> = Array(boardSize) {
        Array(boardSize) { TicTacToeMarks.NULL }
    }

    private var currentMove: TicTacToeMarks = _firstMove

    /**
     *  This method can be used for changing the current move / player also for going
     *  to the last move / player
     */
    private fun toggleMove() {
        currentMove = if (currentMove == TicTacToeMarks.X) {
            TicTacToeMarks.O
        } else {
            TicTacToeMarks.X
        }
    }

    /**
     *  This method playMove() with { @param i } as the place of move
     */
    override fun playMove(i: Int) {
        val x = (i - 1) / boardSize
        val y = (i - 1) % boardSize
        board[x][y] = currentMove
        toggleMove()
        Log.d("Board", x.toString() + y.toString() + currentMove.toString())
    }

    /**
     *  This method getLastPlayedMove() { @return BoardValues } which was played last time
     */
    fun getLastPlayedMove() : TicTacToeMarks{
        return if (currentMove == TicTacToeMarks.X) {
            TicTacToeMarks.O
        } else {
            TicTacToeMarks.X
        }
    }

    override fun getWinner(): TicTacToeMarks {
        var winner = checkInRows()
        if (winner != TicTacToeMarks.NULL) {
            return winner
        }
        winner = checkInColumns()
        if(winner != TicTacToeMarks.NULL) {
            return winner
        }
        return checkInDiagonal()
    }

    fun clear() {
        for (i in 0 until boardSize) {
            for (j in 0 until boardSize) {
                board[i][j] = TicTacToeMarks.NULL
            }
        }
    }

    private fun checkInRows(): TicTacToeMarks {

        for (x in 0 until boardSize) {
            var xv = true
            var ov = true
            for (y in 0 until boardSize) {
                when (board[x][y]) {
                    TicTacToeMarks.NULL -> {
                        xv = false
                        ov = false
                        break
                    }
                    TicTacToeMarks.X -> ov = false
                    TicTacToeMarks.O -> xv = false
                }
            }
            if (xv) {
                Log.d("TicTAc", "ROw winner X")
                return TicTacToeMarks.X
            }
            if (ov) {
                Log.d("TicTAc", "ROw winner O")

                return TicTacToeMarks.O
            }
        }
        Log.d("TicTAc", "ROw winner No")

        return TicTacToeMarks.NULL
    }

    private fun checkInColumns(): TicTacToeMarks {

        for (y in 0 until boardSize) {
            var xv = true
            var ov = true
            for (x in 0 until boardSize) {
                when (board[x][y]) {
                    TicTacToeMarks.NULL -> {
                        xv = false
                        ov = false
                        break
                    }
                    TicTacToeMarks.X -> ov = false
                    TicTacToeMarks.O -> xv = false
                }
            }
            if (xv) {
                Log.d("TicTAc", "Col winner X")

                return TicTacToeMarks.X
            }
            if (ov) {
                Log.d("TicTAc", "Col winner O")

                return TicTacToeMarks.O
            }
        }
        Log.d("TicTAc", "Col winner NO")

        return TicTacToeMarks.NULL
    }

    private fun checkInDiagonal(): TicTacToeMarks {
        var xv1 = true
        var xv2 = true
        var ov2 = true
        var ov1 = true
        for (x in 0 until boardSize) {
            when (board[x][x]) {
                TicTacToeMarks.X -> ov1 = false
                TicTacToeMarks.O -> xv1 = false
                TicTacToeMarks.NULL -> {
                    ov1 = false
                    xv1 = false
                }
            }
            when (board[boardSize - x - 1][x]) {
                TicTacToeMarks.X -> ov2 = false
                TicTacToeMarks.O -> xv2 = false
                TicTacToeMarks.NULL -> {
                    ov2 = false
                    xv2 = false
                }
            }
        }

        return if (xv1 || xv2) {
            TicTacToeMarks.X
        } else if (ov1 || ov2) {
            TicTacToeMarks.O
        } else {
            TicTacToeMarks.NULL
        }
    }


}