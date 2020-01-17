package com.lordsam.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun bclick(view:View){
        val buttonSelect = view as Button
        var cellID = 0

        when(buttonSelect.id){
            R.id.b1 -> cellID = 1
            R.id.b2 -> cellID = 2
            R.id.b3 -> cellID = 3
            R.id.b4 -> cellID = 4
            R.id.b5 -> cellID = 5
            R.id.b6 -> cellID = 6
            R.id.b7 -> cellID = 7
            R.id.b8 -> cellID = 8
            R.id.b9 -> cellID = 9
        }

        playGame(cellID, buttonSelect)

//        Log.d("buttonClick",buttonSelect.id.toString())
//        Log.d("CellSelected",cellID.toString())
//        Toast.makeText(this, "Button $cellID was clicked!",Toast.LENGTH_SHORT).show()
    }

    var activePlayer = 1
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
//    var count :Int = 0
    var playerWon = -1

    fun playGame(cellID :Int, buttonSelect :Button){

        if (activePlayer == 1){

            buttonSelect.text = "X"
            buttonSelect.setBackgroundResource(R.color.blue)
            player1.add(cellID)
            activePlayer = 2
//            count++
//            autoplay()

        }else{

            buttonSelect.text = "O"
            buttonSelect.setBackgroundResource(R.color.green)
            player2.add(cellID)
            activePlayer = 1
//            count++

        }
        buttonSelect.isEnabled = false
        winner()
    }

    fun winner(){

        //row 1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)){
            playerWon = 1
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)){
            playerWon = 2
        }

        //row 2
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)){
            playerWon = 1
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)){
            playerWon = 2
        }

        //row 3
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)){
            playerWon = 1

        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)){
            playerWon = 2
        }

        //column1
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)){
            playerWon = 1
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)){
            playerWon = 2
        }

        //column 2
        else if (player1.contains(2) && player1.contains(5) && player1.contains(8)){
            playerWon = 1
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)){
            playerWon = 2
        }

        //column 3
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)){
            playerWon = 1
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)){
            playerWon = 2
        }



        if (playerWon == 1){

            whoWins1 += 1
            Toast.makeText(this, "Player 1 wins!",Toast.LENGTH_SHORT).show()
            restartGame()

        }else if (playerWon == 2){

            whoWins2 += 1
            Toast.makeText(this, "Player 2 wins!",Toast.LENGTH_SHORT).show()
            restartGame()

        }
//        else if (playerWon == -1 && count > 8){
//
//            Toast.makeText(this, "Match draw!",Toast.LENGTH_SHORT).show()
//            count = 0
//            restartGame()
//
//        }


    }

    fun autoplay(){
        var emptyCells = ArrayList<Int>()

        for (cellId in 1..9){
            if ( !(player1.contains(cellId)) || (player2.contains(cellId))){
                emptyCells.add(cellId)
            }
        }

        val r = Random()
        val randomIndex = r.nextInt(emptyCells.size)
        val cellId = emptyCells[randomIndex]

        var buttonSelect :Button
        buttonSelect = when(cellId){
            1 -> b1
            2 -> b2
            3 -> b3
            4 -> b4
            5 -> b5
            6 -> b6
            7 -> b7
            8 -> b8
            9 -> b9
            else -> {b1}

        }

        playGame(cellId,buttonSelect)

    }

    var whoWins1 = 0
    var whoWins2 = 0

    fun restartGame(){

        activePlayer = 1
        playerWon = -1
        player1.clear()
        player2.clear()

        for (cellId in 1..9){

            var buttonSelect :Button = when(cellId){
                1 -> b1
                2 -> b2
                3 -> b3
                4 -> b4
                5 -> b5
                6 -> b6
                7 -> b7
                8 -> b8
                9 -> b9
                else -> {b1}
            }

            buttonSelect!!.text = ""
            buttonSelect!!.setBackgroundResource(R.color.buttoncolor)
            buttonSelect!!.isEnabled = true
        }

        Toast.makeText(this, "Player1: $whoWins1 || Player2: $whoWins2",Toast.LENGTH_SHORT).show()
    }

}
