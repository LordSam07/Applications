package com.lordsam.basketballscoreboard


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_score_board.*


class ScoreBoardActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score_board)

        var teamA = 0
        var teamB = 0

        buttonA1.setOnClickListener {
            teamA += 1
            textViewA.text = teamA.toString()
        }

        buttonA2.setOnClickListener {
            teamA += 2
            textViewA.text = teamA.toString()
        }

        buttonA3.setOnClickListener {
            teamA += 3
            textViewA.text = teamA.toString()
        }

        buttonB1.setOnClickListener {
            teamB += 1
            textViewB.text = teamB.toString()
        }

        buttonB2.setOnClickListener {
            teamB += 2
            textViewB.text = teamB.toString()
        }

        buttonB3.setOnClickListener {
            teamB += 3
            textViewB.text = teamB.toString()
        }

        buttonReset.setOnClickListener {
            teamA = 0
            teamB = 0
            textViewA.text = teamA.toString()
            textViewB.text = teamB.toString()
        }
    }

}