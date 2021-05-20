package com.sachin.tictactoe.board

interface BoardFunctions {
    fun playMove(i: Int)

    fun getWinner(): TicTacToeMarks
}