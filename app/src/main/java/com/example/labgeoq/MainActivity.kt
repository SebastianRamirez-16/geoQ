package com.example.labgeoq

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var questions: ArrayList<Question>
    private var position = 0
    private lateinit var btYes: Button
    private lateinit var btNo: Button
    private lateinit var btNext: Button

    private var score = 0
    private var allQuestionsAnswered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btYes = findViewById<Button>(R.id.btYes)
        btNo = findViewById<Button>(R.id.btNo)
        btNext = findViewById<Button>(R.id.btNext)
        loadQuestion()
        setupViews()
    }

    private fun loadQuestion(){
        questions = ArrayList()
        var question1 = Question("¿Es Lima la capital de Perú?", true)
        questions.add(question1)

        var question2 = Question("¿Es París la capital de Francia?", true)
        questions.add(question2)

        var question3 = Question("¿Es Madrid la capital de España?", true)
        questions.add(question3)

        var question4 = Question("¿Es Roma la capital de Italia?", true)
        questions.add(question4)

        var question5 = Question("¿Es Lima la capital de Argentina?", false)
        questions.add(question5)

        var question6 = Question("¿Es Roma la capital de Brasil?", false)
        questions.add(question6)

        var question7 = Question("¿Es Washington DC la capital de México?", false)
        questions.add(question7)

        var question8 = Question("¿Es Tokio la capital de Japón?", true)
        questions.add(question8)

        var question9 = Question("¿Es Canberra la capital de Canadá?", false)
        questions.add(question9)

        var question10 = Question("¿Es Ottawa la capital de Australia?", false)
        questions.add(question10)
    }
    // Declarar una variable para rastrear la puntuación
    private var currentToast: Toast? = null // Variable para rastrear el Toast actual

    private fun setupViews() {
        showSentence()
        updateScoreView()

        val originalButtonColor = btYes.background

        btYes.setOnClickListener {
            if (!allQuestionsAnswered) {
                if (questions[position].answer) {
                    score++
                    updateScoreView()
                    cancelCurrentToast() // Cancelar el Toast actual, si existe
                    showToast("Correcto") // Mostrar Toast "Correcto"
                } else {
                    cancelCurrentToast() // Cancelar el Toast actual, si existe
                    showToast("Incorrecto") // Mostrar Toast "Incorrecto"
                }
                moveToNextQuestion()
            }
        }

        btNo.setOnClickListener {
            if (!allQuestionsAnswered) {
                if (!questions[position].answer) {
                    score++
                    updateScoreView()
                    cancelCurrentToast() // Cancelar el Toast actual, si existe
                    showToast("Correcto") // Mostrar Toast "Correcto"
                } else {
                    cancelCurrentToast() // Cancelar el Toast actual, si existe
                    showToast("Incorrecto") // Mostrar Toast "Incorrecto"
                }
                moveToNextQuestion()
            }
        }

        btNext.setOnClickListener {
            moveToNextQuestion()
        }
    }

    // Función para mostrar un Toast con un mensaje
    private fun showToast(message: String) {
        currentToast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        currentToast?.show()
    }

    // Función para cancelar el Toast actual
    private fun cancelCurrentToast() {
        currentToast?.cancel()
    }

    private fun updateScoreView() {
        val scoreTextView = findViewById<TextView>(R.id.tvScore)
        scoreTextView.text = "Puntuación: $score"
    }

    private fun moveToNextQuestion() {
        if (position < questions.size - 1) {
            position++
            showSentence()
        } else {
            showFinalScore()
        }
    }

    private fun showFinalScore() {
        Toast.makeText(this, "¡Has respondido todas las preguntas!", Toast.LENGTH_SHORT).show()
        allQuestionsAnswered = true
    }

    private fun showSentence() {
        val tvSentence = findViewById<TextView>(R.id.tvSentence)
        tvSentence.text = questions[position].sentence
    }
}
