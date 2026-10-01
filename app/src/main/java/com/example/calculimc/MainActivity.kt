package com.example.calculimc

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var editTextPoids: EditText
    private lateinit var editTextTaille: EditText
    private lateinit var buttonCalculer: Button
    private lateinit var buttonEffacer: Button
    private lateinit var textViewImc: TextView
    private lateinit var textViewCategorie: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextPoids = findViewById(R.id.editTextPoids)
        editTextTaille = findViewById(R.id.editTextTaille)
        buttonCalculer = findViewById(R.id.buttonCalculer)
        buttonEffacer = findViewById(R.id.buttonEffacer)
        textViewImc = findViewById(R.id.textViewImc)
        textViewCategorie = findViewById(R.id.textViewCategorie)

        buttonCalculer.setOnClickListener {
            calculerIMC()
        }

        buttonEffacer.setOnClickListener {
            effacer()
        }
    }

    private fun calculerIMC() {

        val poidsTexte = editTextPoids.text.toString()
        val tailleTexte = editTextTaille.text.toString()

        // Vérifier si les champs sont vides
        if (poidsTexte.isEmpty() || tailleTexte.isEmpty()) {
            Toast.makeText(
                this,
                "Veuillez remplir tous les champs",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        // Accepter . ou ,
        val poids = poidsTexte.replace(',', '.').toDoubleOrNull()
        val taille = tailleTexte.replace(',', '.').toDoubleOrNull()

        // Vérifier si les valeurs sont valides
        if (poids == null || taille == null) {
            Toast.makeText(
                this,
                "Veuillez entrer des valeurs valides",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        // Vérifier que les valeurs sont positives
        if (poids <= 0 || taille <= 0) {
            Toast.makeText(
                this,
                "Le poids et la taille doivent être positifs",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        // Calcul de l'IMC
        val imc = poids / (taille * taille)

        val imcFormate = String.format(
            Locale.getDefault(),
            "%.2f",
            imc
        )

        textViewImc.text = "IMC : $imcFormate"

        // Déterminer la catégorie
        if (imc < 18.5) {
            textViewCategorie.text = "Catégorie : Insuffisance pondérale"
        } else if (imc < 25) {
            textViewCategorie.text = "Catégorie : Corpulence normale"
        } else if (imc < 30) {
            textViewCategorie.text = "Catégorie : Surpoids"
        } else if (imc < 35) {
            textViewCategorie.text = "Catégorie : Obésité modérée"
        } else if (imc < 40) {
            textViewCategorie.text = "Catégorie : Obésité sévère"
        } else {
            textViewCategorie.text = "Catégorie : Obésité morbide"
        }
    }

    private fun effacer() {

        editTextPoids.text.clear()
        editTextTaille.text.clear()

        textViewImc.text = "IMC :"
        textViewCategorie.text = "Catégorie :"

        editTextPoids.requestFocus()
    }
}