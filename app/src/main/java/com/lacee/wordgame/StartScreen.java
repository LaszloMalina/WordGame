package com.lacee.wordgame;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StartScreen extends AppCompatActivity
{
    String sourceWord= "";
    ArrayList<String> guessWords = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        TextView source = (TextView) findViewById(R.id.textView2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
                startActivity(getIntent());

            }
        });
        //Snackbar.make(view, "Floating Action button", Snackbar.LENGTH_LONG)
        //.setAction("Action", null).show();
        sourceWord = loadRandom();
        guessWords = loadGuess();
        source.setText(sourceWord);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //read in a txt file
    //then choose a line from it randomly
    //this random word is then the source word.
    public String loadRandom()
    {
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("sourcewords.txt")));
            String line = reader.readLine();
            ArrayList<String> lines = new ArrayList<String>();

            while (line != null)
            {
                lines.add(line);
                line = reader.readLine();
            }
            reader.close();
            Random r = new Random();
            String randomLine = lines.get(r.nextInt(lines.size()));

                return randomLine;
        }
        catch(IOException error)
        {
            //TODO ERROR HANDLING
        }
        return "Error reading sourcewords";
    }
    public ArrayList<String> loadGuess()
    {
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("guesswords.txt")));
            String line = reader.readLine();
            ArrayList<String> guess = new ArrayList<String>();

            while (line != null)
            {
                guess.add(line);
                line = reader.readLine();
            }
            reader.close();

            return(guess);
        }
        catch(IOException error)
        {
            //TODO ERROR HANDLING
        }
        return null;
    }
}