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
    private fun setupViews(){

        showSentence()

        btYes.setOnClickListener{
            if (questions[position].answer){
                Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT).show()

                // Cambiar el color del botón que representa la respuesta correcta a verde
                btYes.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_green_light))

                // Programar una tarea diferida para restablecer el color del botón después de un cierto tiempo

                Handler().postDelayed({

                    // cambie el color a #322c4c
                    btYes.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_500))


                }, 1000) // Cambia 1000 a la cantidad de milisegundos que desees esperar antes de restablecer el color
                // Verificar si hay más preguntas disponibles
                if (position < questions.size - 1) {
                    position++
                    showSentence()
                } else {
                    Toast.makeText(this, "¡Has respondido todas las preguntas!", Toast.LENGTH_SHORT).show()
                    // Aquí puedes realizar alguna acción adicional si se han respondido todas las preguntas
                }
            }
            else {
                Toast.makeText(this, "Incorrecto", Toast.LENGTH_SHORT).show()
            }
        }

        btNo.setOnClickListener{
            if (!questions[position].answer){
                Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT).show()

                btYes.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_green_light))

                // Verificar si hay más preguntas disponibles
                if (position < questions.size - 1) {
                    position++
                    showSentence()
                } else {
                    Toast.makeText(this, "¡Has respondido todas las preguntas!", Toast.LENGTH_SHORT).show()
                    // Aquí puedes realizar alguna acción adicional si se han respondido todas las preguntas
                }
            }
            else {
                Toast.makeText(this, "Incorrecto", Toast.LENGTH_SHORT).show()

            }
        }

        btNext.setOnClickListener {
            position++
            showSentence()

        }
    }


    private fun showSentence(){
        val tvSentence = findViewById<TextView>(R.id.tvSentence)
        tvSentence.text = questions[position].sentence
    }
}