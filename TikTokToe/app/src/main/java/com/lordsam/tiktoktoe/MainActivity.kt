package com.lordsam.tiktoktoe

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private var activePlayer = 1
    private var player1 = ArrayList<Int>()
    private var player2 = ArrayList<Int>()
    private var player1Wins = 0
    private var player2Wins = 0

    fun buClick(view : View){

        val buttonId = view as Button
        var cell = 0

        when (buttonId.id){
            R.id.button1 -> cell = 1
            R.id.button2 -> cell = 2
            R.id.button3 -> cell = 3
            R.id.button4 -> cell = 4
            R.id.button5 -> cell = 5
            R.id.button6 -> cell = 6
            R.id.button7 -> cell = 7
            R.id.button8 -> cell = 8
            R.id.button9 -> cell = 9
        }

        playGame(cell, buttonId)

    }

    private fun playGame(cell :Int, buttonId :Button){

        if (activePlayer == 1){
            buttonId.text = "X"
            buttonId.setBackgroundResource(R.color.player1)
            player1.add(cell)
            activePlayer = 2
            bot()
        }
       else{
            buttonId.text = "O"
            buttonId.setBackgroundResource(R.color.player2)
            player2.add(cell)
            activePlayer = 1
        }

        buttonId.isEnabled = false
        checkWinner()

    }

    @SuppressLint("SetTextI18n")
    private fun checkWinner(){

        var winner = -1
//        Log.i("player1","$player1")
//        Log.i("player2","$player2")

        //rows
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)){
            winner = 1
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)){
            winner = 2
        }
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)){
            winner = 1
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)){
            winner = 2
        }
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)){
            winner = 1
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
        }

        //columns
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)){
            winner = 1
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)){
            winner = 2
        }
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)){
            winner = 1
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)){
            winner = 2
        }
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)){
            winner = 1
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
        }

        //Diagonals
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)){
            winner = 1
        }
        if (player2.contains(1) && player2.contains(5) && player2.contains(9)){
            winner = 2
        }
        if (player1.contains(3) && player1.contains(5) && player1.contains(7)){
            winner = 1
        }
        if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winner = 2
        }

        //winner
        if (winner == 1){
            Toast.makeText(this, "Player 1 Wins!", Toast.LENGTH_SHORT).show()
            player1Wins++
            reset()
        }
        if (winner == 2){
            Toast.makeText(this, "Player 2 Wins!", Toast.LENGTH_SHORT).show()
            player2Wins++
            reset()
        }

        textViewP.text = "Player 1: $player1Wins  Player 2: $player2Wins"
    }

    private fun bot(){
        val emptyCells = ArrayList<Int>()

        for (cell in 1..9){
            if (!(player1.contains(cell)) || !(player2.contains(cell))){
                Log.d("bot", "cell added $cell")
                emptyCells.add(cell)
            }
        }

        if (emptyCells.size == 0){
            reset()
        }

        val randomIndex = Random.nextInt(emptyCells.size)
        val botCell = emptyCells[randomIndex]

        val buttonId = when (botCell){
            1 -> button1
            2 -> button2
            3 -> button3
            4 -> button4
            5 -> button5
            6 -> button6
            7 -> button7
            8 -> button8
            9 -> button9
            else -> button1
        }

        playGame(botCell, buttonId)
    }

    private fun reset(){
        activePlayer = 1
        player1.clear()
        player2.clear()

        for (cell in 1..9){
            val buttonId = when (cell){
                1 -> button1
                2 -> button2
                3 -> button3
                4 -> button4
                5 -> button5
                6 -> button6
                7 -> button7
                8 -> button8
                9 -> button9
                else -> button1
            }

            buttonId.text = ""
            buttonId.setBackgroundResource(R.color.buttonColor)
            buttonId.isEnabled = true
        }
        Toast.makeText(this, "Player 1 Wins :$player1Wins - Player 2 Wins :$player2Wins", Toast.LENGTH_LONG).show()
    }

}
