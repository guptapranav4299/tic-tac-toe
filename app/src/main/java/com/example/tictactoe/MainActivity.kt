package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var PLAYER = true
    var TURN_COUNT = 0
    var boardStatus = Array(size = 3) {IntArray(size = 3)}

    lateinit var board: Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)
        val button7 = findViewById<Button>(R.id.button7)
        val button8 = findViewById<Button>(R.id.button8)
        val button9 = findViewById<Button>(R.id.button9)
        val resetBtn = findViewById<Button>(R.id.resetBtn)
        val displayTv = findViewById<TextView>(R.id.displayTv)


        
        board = arrayOf(
            arrayOf(button1,button2,button3),
            arrayOf(button4,button5,button6),
            arrayOf(button7,button8,button9)
        )
        for(i:Array<Button> in board){
            for(button:Button in i){
                button.setOnClickListener(this)
            }
        }
        initializeBoardStatus()
        resetBtn.setOnClickListener{
            PLAYER = true
            TURN_COUNT = 0
            initializeBoardStatus()
        }
    }

    private fun initializeBoardStatus() {
       for (i:Int in 0..2){
           for(j in 0..2){
               boardStatus[i][j] = -1
           }
       }
        for (i:Array<Button> in board){
            for(button:Button in i){
                button.isEnabled = true
                button.text = ""
            }
        }
    }

    override fun onClick(view: View){

        when(view.id){
            R.id.button1 ->{
                updateValue(row =0, col=0,player = PLAYER)
            }
            R.id.button2 ->{
                updateValue(row =0, col=1,player = PLAYER)
            }
            R.id.button3 ->{
                updateValue(row =0, col=2,player = PLAYER)
            }
            R.id.button4 ->{
                updateValue(row =1, col=0,player = PLAYER)
            }
            R.id.button5 ->{
                updateValue(row =1, col=1,player = PLAYER)
            }
            R.id.button6 ->{
                updateValue(row =1, col=2,player = PLAYER)
            }
            R.id.button7 ->{
                updateValue(row =2, col=0,player = PLAYER)
            }
            R.id.button8 ->{
                updateValue(row =2, col=1,player = PLAYER)
            }
            R.id.button9 ->{
                updateValue(row =2, col=2,player = PLAYER)
            }
        }
        TURN_COUNT++
        PLAYER = !PLAYER

        if(PLAYER){
            updateDisplay("Player X Turn")
        }else{
            updateDisplay("Player 0 Turn")
        }

        if(TURN_COUNT == 9){
            updateDisplay("Game Draw")
        }
        checkWinner()

    }

    private fun checkWinner() {
//        horizontal rows
        for( i:Int in 0..2){
            if(boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2] )
            {
                if(boardStatus[i][0] == 1){
                    updateDisplay("Player X Winner")
                    break
                }else if(boardStatus[i][0] == 0){
                    updateDisplay("Player 0 Winner")
                    break
                }
            }
        }

//        vertical column
        for( i:Int in 0..2){
            if(boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i] )
            {
                if(boardStatus[0][i] == 1){
                    updateDisplay("Player X Winner")
                    break
                }else if(boardStatus[0][i] == 0){
                    updateDisplay("Player 0 Winner")
                    break
                }
            }
        }

//        check diagnol
        if(boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2]){
            if(boardStatus[0][0] == 1){
                    updateDisplay("Player X Winner")
            }else if(boardStatus[0][0] == 0){
                    updateDisplay("Player 0 Winner")
            }
        }
        if(boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0]){
            if(boardStatus[0][2] == 1){
                updateDisplay("Player X Winner")
            }else if(boardStatus[0][2] == 0){
                updateDisplay("Player 0 Winner")
            }
        }
    }


    private fun updateDisplay(text: String) {
        val displayTv = findViewById<TextView>(R.id.displayTv)
        displayTv.text = text
        if(text.contains(other= "Winner")){
            disableButton()
        }
    }

    private fun disableButton(){
        for(i:Array<Button> in board){
            for (button:Button in i){
                button.isEnabled = false
            }
        }
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val text:String = if (player) "X" else "0"
        val value:Int = if (player) 1 else 0
        board[row][col].apply {
            isEnabled = false
            setText(text)
        }
        boardStatus[row][col] = value

    }
}