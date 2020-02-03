package com.lordsam.kalkulator

import android.util.Log
import java.util.Stack

class InfixEvaluation {
    fun evaluate(expression: String): Int { //Stack for Numbers
        val numbers = Stack<Int>()
        //Stack for operators
        val operations = Stack<Char>()
        var i = 0
        while (i < expression.length) {
            var c = expression[i]

            //check if it is number
            if (Character.isDigit(c)) { //Entry is Digit, it could be greater than one digit number
                var num = 0
                while (Character.isDigit(c)) {
                    num = num * 10 + (c - '0')
                    i++
                    c = if (i < expression.length) expression[i] else break
                }
                i--
                //push it into stack
                numbers.push(num)
            } else if (c == '(') { //push it to operators stack
                operations.push(c)
            } else if (c == ')') {
                while (operations.peek() != '(') {
                    val output = performOperation(numbers, operations)
                    //push it back to stack
                    numbers.push(output)
                }
                operations.pop()
                //3.(i != expression.length - 1) to check if the last index of the expression is not operator,otherwise don't push
//4.(((isOperator(c) && i !=  1) && expression.length != 1)) so if it is not an operator at place 1
            } else if (isOperator(c) && expression.length == 1){
                return 0
            }else if ((isOperator(c) && i != expression.length - 1)) { //1. If current operator has higher precedence than operator on top of the stack,
//the current operator can be placed in stack
// 2. else keep popping operator from stack and perform the operation in  numbers stack till
//either stack is not empty or current operator has higher precedence than operator on top of the stack

                while (!operations.isEmpty() && precedence(c) < precedence(
                        operations.peek()
                    )
                ) {
                    val output = performOperation(numbers, operations)
                    //push it back to stack
                    numbers.push(output)
                }
                //now push the current operator to stack
                operations.push(c)
            }
            i++
        }
        //If here means entire expression has been processed,
//Perform the remaining operations in stack to the numbers stack
        while (!operations.isEmpty()) {
            val output = performOperation(numbers, operations)
            //push it back to stack
            numbers.push(output)
        }
        return numbers.pop()
    }

    fun performOperation(numbers: Stack<Int>, operations: Stack<Char>): Int {
        val a = numbers.pop()
        val b = numbers.pop()
        val operation = operations.pop()
        when (operation) {
            '+' -> return a + b
            '-' -> return b - a
            '*' -> return a * b
            '/' -> {
                if (a == 0) throw UnsupportedOperationException("Cannot divide by zero")
                return b / a
            }
        }
        return 0
    }

    fun isOperator(c: Char): Boolean {
        return c == '+' || c == '-' || c == '/' || c == '*' || c == '^'
    }

    fun isOperator(c: String): Boolean {
        return c == "+" || c == "-" || c == "/" || c == "*" || c == "^"
    }

    companion object {
        fun precedence(c: Char): Int {
            when (c) {
                '+', '-' -> return 1
                '*', '/' -> return 2
                '^' -> return 3
            }
            return -1
        }

        @JvmStatic
        fun main(args: Array<String>) {
            val infixExpression = "20*(5 *(3+6))/(15-20)"
            val i = InfixEvaluation()
            println(i.evaluate(infixExpression))
        }
    }
}
