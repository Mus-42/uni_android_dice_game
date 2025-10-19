package knu.mus.dicegame

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {
    val viewModel by viewModels<DiceViewModel>();

    lateinit var dices: List<ImageView>;
    lateinit var diceImageResources: List<Int>;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        dices = listOf(
            findViewById<ImageView>(R.id.dice_a),
            findViewById<ImageView>(R.id.dice_b),
            findViewById<ImageView>(R.id.dice_c),
            findViewById<ImageView>(R.id.dice_d),
            findViewById<ImageView>(R.id.dice_e),
        );

        diceImageResources = listOf(
            R.drawable.dice1,
            R.drawable.dice2,
            R.drawable.dice3,
            R.drawable.dice4,
            R.drawable.dice5,
            R.drawable.dice6,
        );

        findViewById<Button>(R.id.button_roll).setOnClickListener {
            Log.d(TAG, "Rolling");
            viewModel.randomRoll()
        };

        findViewById<Button>(R.id.button_stop).setOnClickListener {
            Log.d(TAG, "Stopping");

            viewModel.signalStop()
        };


        viewModel.liveData.observe(this) { newDiceValues: List<Int> ->
            Log.d(TAG, "Rolled $newDiceValues");
            updateDiceDisplay(newDiceValues);
        }
    }

    fun updateDiceDisplay(newDiceValues: List<Int>) {
        for ((newValue, dice) in newDiceValues.zip(dices)) {
            dice.setImageResource(diceImageResources[newValue]);
        }
    }

    companion object {
        val TAG = "DICEMAIN";
    }
}