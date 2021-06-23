package com.calcandroid.calculadoraconstantlayout

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {
    private var n1: Double = 0.0
    private var n2: Double = 0.0
    private var algoritmos: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val zero = findViewById<Button>(R.id.numero_zero)
        val um = findViewById<Button>(R.id.numero_um)
        val dois = findViewById<Button>(R.id.numero_dois)
        val tres = findViewById<Button>(R.id.numero_tres)
        val quatro = findViewById<Button>(R.id.numero_quatro)
        val cinco = findViewById<Button>(R.id.numero_cinco)
        val seis = findViewById<Button>(R.id.numero_seis)
        val sete = findViewById<Button>(R.id.numero_sete)
        val oito = findViewById<Button>(R.id.numero_oito)
        val nove = findViewById<Button>(R.id.numero_nove)
        val ponto = findViewById<Button>(R.id.ponto)
        val add = findViewById<Button>(R.id.adicao)
        val sub = findViewById<Button>(R.id.subtracao)
        val divi = findViewById<Button>(R.id.Divisao)
        val multi = findViewById<Button>(R.id.multiplicacao)


        zero.setOnClickListener { numeroDigitado("0") }
        um.setOnClickListener { numeroDigitado("1") }
        dois.setOnClickListener { numeroDigitado("2") }
        tres.setOnClickListener { numeroDigitado("3") }
        quatro.setOnClickListener { numeroDigitado("4") }
        cinco.setOnClickListener { numeroDigitado("5") }
        seis.setOnClickListener { numeroDigitado("6") }
        sete.setOnClickListener { numeroDigitado("7") }
        oito.setOnClickListener { numeroDigitado("8") }
        nove.setOnClickListener { numeroDigitado("9") }
        ponto.setOnClickListener { expressao.text= "."}

        add.setOnClickListener { algoritmosDigitados(soma) }
        sub.setOnClickListener { algoritmosDigitados(subtracaao) }
        multi.setOnClickListener { algoritmosDigitados(multiplicacaao) }
        divi.setOnClickListener { algoritmosDigitados(divisaao) }

        Limpar.setOnClickListener { limpartudo() }

        backspace.setOnClickListener { apagar() }

        igual.setOnClickListener { resolver() }
    }



    private fun numeroDigitado(digito: String) {


        if (expressao.text == "0" && digito != ".") {
            expressao.text = digito
        } else {
            "${expressao.text}$digito".also { expressao.text = it }
        }

        if (algoritmos == Nao_operacao) {
            n1 = expressao.text.toString().toDouble()
        } else {
            n2 = expressao.text.toString().toDouble()
        }
    }


    private fun algoritmosDigitados(operacao: Int) {
        this.algoritmos = operacao

        n1 = expressao.text.toString().toDouble()

        expressao.text = ""


    }



    private fun resolver() {
        val result = when (algoritmos) {
            soma -> n1 + n2

            subtracaao -> n1 - n2


            multiplicacaao -> n1 * n2


            divisaao -> n1 / n2

            else -> 0
        }

        n1 = result as Double

        expressao.text = if ("$result".endsWith(".0")) {
            "$result".replace(".0", "")
        } else {
            "%.2f".format(result)
        }
    }


    private fun limpartudo() {
        expressao.text = ""
        n1 = 0.0
        n2 = 0.0

    }

    private fun apagar() {

        val string = expressao.text.toString()

        if (string.isNotBlank()) {
            expressao.text = string.substring(0, string.length - 1)

        }

    }

    companion object {
        const val soma = 1
        const val subtracaao = 2
        const val multiplicacaao = 3
        const val divisaao = 4
        const val Nao_operacao = 0
    }


}