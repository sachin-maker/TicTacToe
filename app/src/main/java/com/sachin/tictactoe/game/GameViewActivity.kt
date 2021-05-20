package com.sachin.tictactoe.game

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.sachin.tictactoe.R
import com.sachin.tictactoe.board.TicTacToeBoard
import com.sachin.tictactoe.board.TicTacToeMarks
import com.sachin.tictactoe.databinding.ActivityGameViewBinding

class GameViewActivity : AppCompatActivity(), GameView {

    private lateinit var binding: ActivityGameViewBinding
    private lateinit var presenter: GamePresenter

    private lateinit var gameBoard: TicTacToeBoard
    private lateinit var boxes: ArrayList<ImageView>
    private var movesCount: Int = 0
    private var playerOneWins: Int = 0
    private var playerTwoWins: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        gameBoard = TicTacToeBoard(3)

        boxes = arrayListOf(
            binding.boxOne,
            binding.boxTwo,
            binding.boxThree,
            binding.boxFour,
            binding.boxFive,
            binding.boxSix,
            binding.boxSeven,
            binding.boxEight,
            binding.boxNine
        )

        for (i in 0 until boxes.size) {
            boxes[i].setOnClickListener {
                gameBoard.playMove(i + 1)
                movesCount++
                boxes[i].setImageDrawable(getDrawableForMove(gameBoard.getLastPlayedMove()))
                declareWinner()
            }
        }
    }

    override fun declareWinner() {
        val winner = gameBoard.getWinner()
        if (movesCount <= 9) {
            if (winner == TicTacToeMarks.X) {
                Toast.makeText(applicationContext, "X winner", Toast.LENGTH_SHORT).show()
                restartGame()
                playerOneWins++
                setPlayersScore()
                return
            }
            if (winner == TicTacToeMarks.O) {
                Toast.makeText(applicationContext, "O winner", Toast.LENGTH_SHORT).show()
                restartGame()
                playerTwoWins++
                setPlayersScore()
                return
            }
        } else {
            restartGame()
            Toast.makeText(applicationContext, "Game Over", Toast.LENGTH_SHORT).show()
            return
        }
    }

    override fun restartGame() {
        gameBoard.clear()
        movesCount = 0
        clearBoard(boxes)
    }

    private fun clearBoard(imageViewArray: ArrayList<ImageView>) {
        for (i in imageViewArray) {
            i.setImageResource(android.R.color.transparent)
        }
    }

    private fun getDrawableForMove(move: TicTacToeMarks): Drawable? {
        return if (move == TicTacToeMarks.X) {
            ContextCompat.getDrawable(applicationContext, R.drawable.ic_x)
        } else {
            ContextCompat.getDrawable(applicationContext, R.drawable.ic_zero)
        }
    }

    private fun setPlayersScore() {
        binding.playerOneScore.text = playerOneWins.toString()
        binding.playerTwoScore.text = playerTwoWins.toString()
    }
}