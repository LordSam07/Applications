package com.lordsam.passvalidator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

        var upperCaseAlphabets = arrayOf(
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'X', 'Y', 'Z'
        )

        var lowerCaseAlphabets = arrayOf(
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
        )

        var digits = arrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')

        var symbols = arrayOf(
            '@', '%', '#', '&', '!', '$', '^', '*', '(', ')', '-', '_', '+', '=', '{', '}', '[', ']',
            ':', ':', '"', '<', '>', '?', '.', '/', ',', '|'
        )

                fun check(view :View) {
                    var pass = editText.text.toString()
                    var size = pass.length
                    var temp = ""
                    var count = 0

                    for (i in pass) {

                        for (j in upperCaseAlphabets)
                            if (i == j) {
                                count++
                                temp += j
                            }


                        for (k in lowerCaseAlphabets)
                            if (i == k) {
                                count++
                                temp += k
                            }


                        for (l in digits)
                            if (i == l) {
                                count++
                                temp += l
                            }

                        for (m in symbols)
                            if (i == m) {
                                count++
                                temp += m
                            }
                    }
                    if (count == size) {
                        Toast.makeText(this, "Matched", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Unmatched",Toast.LENGTH_SHORT).show()
                    }
                }
}
