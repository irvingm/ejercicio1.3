package ittepic.edu.mx.ejercicio13;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button b1, b2;
    Thread thread;
    boolean ejecutar,pausado;
    double con2;
    double con=0;
    double conn=0;
    TextView text;
    double numeroaleatorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=findViewById(R.id.button);
        b2=findViewById(R.id.button2);
        text=findViewById(R.id.textView);
        ejecutar=true;
        pausado=false;
        float leftLimit = 0.1f;
        float rightLimit = 3f;
        float generatedFloat = leftLimit + new Random().nextFloat() * (rightLimit - leftLimit);
        numeroaleatorio = generatedFloat;
        DecimalFormat df = new DecimalFormat("###.#");
        final String numeroale=df.format(numeroaleatorio);
        text.setText("Detener en: "+df.format(numeroaleatorio));
        conn=numeroaleatorio;


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ejecutar=true;
                if(b1.getText().toString().equals(numeroale)){
                    Toast.makeText(MainActivity.this,"Has Ganado!!!",Toast.LENGTH_SHORT).show();
                    ejecutar=false;
                }else if (b1.getText().toString().equals("iniciar")){
                    if(pausado==false){
                        try {
                            thread = new Thread() {
                                public void run() {

                                    while (ejecutar) {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                DecimalFormat df = new DecimalFormat("#.##"); String twoDigitNum = df.format(con);
                                                b1.setText(twoDigitNum);
                                            }
                                        });
                                        try {
                                            sleep(1000);
                                        } catch (InterruptedException e) {
                                        }
                                        con += 0.10;
                                        if(con>=3){con=0.00;}
                                    }
                                }
                            };
                            thread.start();
                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                        pausado=true;
                    }

                }else {
                    Toast.makeText(MainActivity.this,"Has Fallado :/",Toast.LENGTH_SHORT).show();
                    ejecutar=false;
                    finish();
                    startActivity(getIntent());
                }

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(getIntent());

            }

        });
    }
}
