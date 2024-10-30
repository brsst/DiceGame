package com.example.dicegame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // tablica przechowujaca referencje do TextView wyswietlajacych wyniki kosci
    private TextView[] resultTextViews;
    private TextView wynikTegoLosowaniaTextView;
    private TextView wynikGryTextView;
    private TextView liczbaRzutowTextView;
    private int wynikGry = 0;
    private int liczbaRzutow = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // inicjalizacja TextView
        resultTextViews = new TextView[5];
        resultTextViews[0] = findViewById(R.id.result1);
        resultTextViews[1] = findViewById(R.id.result2);
        resultTextViews[2] = findViewById(R.id.result3);
        resultTextViews[3] = findViewById(R.id.result4);
        resultTextViews[4] = findViewById(R.id.result5);

        wynikTegoLosowaniaTextView = findViewById(R.id.wynikTegoLosowania);
        wynikGryTextView = findViewById(R.id.wynikGry);
        liczbaRzutowTextView = findViewById(R.id.liczbaRzutow);

        // listener dla przycisku "RZUĆ KOŚĆMI"
        Button rollButton = findViewById(R.id.button);
        rollButton.setOnClickListener(v -> rollDice());

        // listener dla przycisku "RESETUJ WYNIK"
        Button resetButton = findViewById(R.id.button2);
        resetButton.setOnClickListener(v -> resetGame());
    }

    // metoda wywolywana po nacisnieciu przycisku "RZUĆ KOŚĆMI"
    private void rollDice() {
        int[] diceResults = voidRollDice();
        displayDiceResults(diceResults);
        int newScore = calculateScore(diceResults);
        updateScore(newScore);
        updateRollCount();
    }

    // losuje 5 liczb z zakresu 1-6 i zwraca je w tablicy
    private int[] voidRollDice() {
        Random random = new Random();
        int[] diceResults = new int[5];
        for (int i = 0; i < 5; i++) {
            diceResults[i] = random.nextInt(6) + 1;
        }
        return diceResults;
    }


    // resetuje gre do stanu poczaatkowego
    private void resetGame() {
        voidResetGame();
    }

    // resetuje wszystkie wartosci do stanu poczatkowego
    private void voidResetGame(){
        wynikGry = 0;
        liczbaRzutow = 0;

        for (TextView textView : resultTextViews) {
            textView.setText("?");
        }

        wynikTegoLosowaniaTextView.setText("Wynik tego losowania: ");
        wynikGryTextView.setText("Wynik gry: 0");
        liczbaRzutowTextView.setText("Liczba rzutów: 0");
    }

    // aktualizuje wynik gry
    private void updateScore(int newScore) {
        voidUpdateScore(newScore);
    }
    // dodaje nowy wynik do aktualnego wyniku gry
    private void voidUpdateScore(int newScore){
        wynikGry += newScore;
        wynikGryTextView.setText("Wynik gry: " + wynikGry);
        wynikTegoLosowaniaTextView.setText("Wynik tego losowania: " + newScore);
    }

    // aktualizuje licznik rzutow
    private void updateRollCount() {
        voidUpdateRollCount();
    }
    // zwieksza licznik rzutow o 1 i aktualizuje TextView
    private void voidUpdateRollCount(){
        liczbaRzutow++;
        liczbaRzutowTextView.setText("Liczba rzutów: " + liczbaRzutow);
    }

    // wyswietla wyniki rzutow w TextView
    private void displayDiceResults(int[] diceResults) {
        voidDisplayDiceResults(diceResults);
    }
    // ustawia tekst w TextView na podstawie wyników rzutow
    private void voidDisplayDiceResults(int[] diceResults){
        for (int i = 0; i < 5; i++) {
            resultTextViews[i].setText(String.valueOf(diceResults[i]));
        }
    }


    // oblicza sume wynikow rzutow
    private int calculateScore(int[] diceResults) {
        int score = 0;
        for (int result : diceResults) {
            score += result;
        }
        return score;
    }
}