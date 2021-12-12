package com.example.doorstest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    List<List<Boolean>> doorsListList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonRandom = findViewById(R.id.buttonRandom);
        TextView textView = findViewById(R.id.results);
        buttonRandom.setOnClickListener((View w) -> {
            doorsListList = generateDoors();
            System.out.println(doorsListList);
            int winCount = testRandomDoors();
            int winMontyHall = testDoorsMontyHall();
            textView.setText("Количество выигрышей " + winCount + "\nМонти Хол " + winMontyHall);
        });
    }

    int testRandomDoors() {
        int winCounter = 0;
        for (List<Boolean> doorsList : doorsListList) {
            Random random = new Random();
            int doorToCheck = random.nextInt(doorsList.size());
            boolean isWin = doorsList.get(doorToCheck);
            if (isWin) {
                winCounter = winCounter + 1;
            }
        }
        System.out.println(winCounter);
        return winCounter;
    }

    List<List<Boolean>> generateDoors() {
        List<List<Boolean>> a = new ArrayList<>();
        for (int i = 0; i < 1000; i = i + 1) {
            List<Boolean> doorsList = new ArrayList<>();
            Random random = new Random();
            int winnerDoorsIndex = random.nextInt(3);
            for (int t = 0; t < 3; t = t + 1) {
                if (t == winnerDoorsIndex) {
                    doorsList.add(true);
                } else {
                    doorsList.add(false);
                }
            }
            a.add(doorsList);
        }
        return a;
    }

    int testDoorsMontyHall() {
        int winCounter = 0;
        for (List<Boolean> doorsList : doorsListList) {
            Random random = new Random();
            int doorToCheck = random.nextInt(doorsList.size());
            doorsList.remove(doorToCheck);
            doorsList.remove(false);
//            doorsList.remove(random.nextInt(doorsList.size()));

            boolean isWin = doorsList.get(0);
            if (isWin) {
                winCounter = winCounter + 1;
            }
        }
        System.out.println(winCounter);
        return winCounter;
    }
}