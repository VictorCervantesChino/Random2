package com.example.imgrand2sound;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
/*
* ------------NOTA------------------------------------
* para que se puedan reproducir archivos GIF.
* es necesario agregar dependencias en los archivos:
* build.grandle(Project)
* build.grandle(Module)
* cambiar en activity_main.xml ImageView por GifImageView
* */
public class MainActivity extends AppCompatActivity {

    ImageView imagen;
    TextView letrero;
    Button btn;

    MediaPlayer mp;
    Vibrator vibrator;

    int actual=0;
    int actual2=0; //inicialice una nueva variable que tendra el valor a comparar
    Random ww = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ASOCIAMOS ATRIBUTOS, ID, LAYOUT
        imagen = (ImageView) findViewById(R.id.imageView);
        letrero = (TextView) findViewById(R.id.textView);
        btn = (Button) findViewById(R.id.button);

        //mostrarRand();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarRand();
            }
        });
    }

    //Mostrar valores de aleatorio
    public void mostrarRand() {


        actual = ww.nextInt(6);
        if (actual== actual2 ) { // aqui comparamos el valor generado aleatoriamnete con el actual2 = 0
        mostrarRand(); //si es igual mandamos a llamar otra ves a la clase
        }else { // si es distinto ejecutamos el codigo para mostrar la imagen
        imagen.setImageResource(randArray[actual].getImagen());
        letrero.setText(randArray[actual].getAleatorio());

        //generar patron de vibracion
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        Random rand = new Random();
        int n = rand.nextInt(1000);
        long[] pattern = {n, n, n, n};
        vibrator.vibrate(pattern, -1);

        stopPlying();
        mp = MediaPlayer.create(this, randArray[actual].getSonido());
        mp.start();
        actual2 = actual; //ahora asignamos el valor creado para actual a la variable actual2 para que se mantenga actualizada
    }
    }
    Aleatorio i1 = new Aleatorio(R.drawable.uno,   "img1", R.raw.sound1);
    Aleatorio i2 = new Aleatorio(R.drawable.dos,    "img2", R.raw.sound2);
    Aleatorio i3 = new Aleatorio(R.drawable.tres,   "img3", R.raw.sound3);
    Aleatorio i4 = new Aleatorio(R.drawable.cuatro, "img4", R.raw.sound4);
    Aleatorio i5 = new Aleatorio(R.drawable.cinco,  "img5", R.raw.sound5);
    Aleatorio i6 = new Aleatorio(R.drawable.seis,   "img6", R.raw.sound6);
    Aleatorio i7 = new Aleatorio(R.drawable.siete,  "img7", R.raw.sound7);

    //LLenando array
    Aleatorio[] randArray = new Aleatorio[]{ i1, i2, i3, i4, i5, i6, i7 };

    // METODO PARA DETENER E INICIAR UNA REPRODUCCION
    private void stopPlying(){
        if(mp != null){
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}
