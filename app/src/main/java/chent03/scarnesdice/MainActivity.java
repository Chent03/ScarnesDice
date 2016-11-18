package chent03.scarnesdice;

import android.app.ActivityManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    private int userOverall = 0;
    private int userTurn = 0;
    private int compOverall = 0;
    private int compTurn = 0;
    TextView test;
    Button hold;
    Button roll;

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            rolldice("comp");
            System.out.println(compTurn);
            if(compTurn == 0) {
                test.setText("Your Score: " + userOverall +" Computer score: " + compOverall + " Computer rolled a one");
                hold.setEnabled(true);
                roll.setEnabled(true);
                timerHandler.removeCallbacks(timerRunnable);
            }else if(compTurn >= 20){
                compOverall += compTurn;
                compTurn = 0;
                test.setText("Your Score: " + userOverall +" Computer score: " + compOverall + " Computer holds");
                hold.setEnabled(true);
                roll.setEnabled(true);
            }else if(compTurn < 20){
                timerHandler.postDelayed(this, 600);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView test = (TextView)findViewById(R.id.score);
        final Button reset = (Button)findViewById(R.id.reset);
        final Button roll = (Button)findViewById(R.id.roll);
        final Button hold = (Button)findViewById(R.id.hold);
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rolldice("user");
                Log.d("dice", "roll" + userTurn);
                System.out.println("user: " + userTurn);
                test.setText("Your Score: " + userOverall +" Computer score: " + compOverall + " Your turn score: " + userTurn);
                if(userTurn == 0){
                    computerTurn();
                }
        }
    });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userTurn = 0;
                userOverall = 0;
                compOverall = 0;
                compTurn = 0;
                test.setText("Your Score: 0 Computer score: 0");
            }
        });
        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userOverall += userTurn;
                userTurn = 0;
                test.setText("Your Score: " + userOverall +" Computer score: " + compOverall);
                computerTurn();
            }
        });
    }
    public void rolldice(String who){
        ImageView dice = (ImageView)findViewById(R.id.dice);
        int random = (int)(Math.random()*6)+1;
        switch (random){
            case 1:
                dice.setImageResource(R.drawable.dice1);
                if(who.equals("user")){
                    userTurn = 0;
                }else if(who.equals("comp")){
                    compTurn = 0;
                }
                break;
            case 2:
                dice.setImageResource(R.drawable.dice2);
                if(who.equals("user")){
                    userTurn += random;
                }else if(who.equals("comp")){
                    compTurn += random;
                }
                break;
            case 3:
                dice.setImageResource(R.drawable.dice3);
                if(who.equals("user")){
                    userTurn += random;
                }else if(who.equals("comp")){
                    compTurn += random;
                }
                break;
            case 4:
                dice.setImageResource(R.drawable.dice4);
                if(who.equals("user")){
                    userTurn += random;
                }else if(who.equals("comp")){
                    compTurn += random;
                }
                break;
            case 5:
                dice.setImageResource(R.drawable.dice5);
                if(who.equals("user")){
                    userTurn += random;
                }else if(who.equals("comp")){
                    compTurn += random;
                }
                break;
            case 6:
                dice.setImageResource(R.drawable.dice6);
                if(who.equals("user")){
                    userTurn += random;
                }else if(who.equals("comp")){
                    compTurn += random;
                }
                break;
            default:
                dice.setImageResource(R.drawable.dice1);
                break;
        }
    }
    public void computerTurn(){
        test = (TextView)findViewById(R.id.score) ;
        hold = (Button)findViewById(R.id.hold);
        roll = (Button)findViewById(R.id.roll);
        hold.setEnabled(false);
        roll.setEnabled(false);
        timerHandler.postDelayed(timerRunnable, 600);
            //rolldice("comp");
            //System.out.println("overall: " + compOverall);

    }

}
