package com.example.ejercicio1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ConstraintLayout layoutPrincipal = findViewById(R.id.main);
        Lienzo lienzo = new Lienzo(this);
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        layoutPrincipal.addView(lienzo, params);
    }

    class Lienzo extends View {
        Paint pincel1;
        Paint pincel2;
        Paint pincel3;
        Paint pincel4;
        Paint pincelTexto;
        Paint pincelTexto2;
        int anchoPantalla;
        int altoPantalla;
        Bitmap bmp;
        Paint pincelBitmap;
        Bitmap bitmap;
        Matrix matrix = new Matrix();

        public Lienzo(MainActivity contexto) {
            super(contexto);
            Display pantalla = contexto.getWindowManager().getDefaultDisplay();
            Point tam = new Point();
            pantalla.getSize(tam);
            anchoPantalla = tam.x;
            altoPantalla = tam.y;

            pincel1 = new Paint();
            pincel1.setColor(Color.RED);
            pincel1.setStyle(Paint.Style.FILL);

            pincel2 = new Paint();
            pincel2.setColor(Color.GREEN);
            pincel2.setStyle(Paint.Style.STROKE);
            pincel2.setStrokeWidth(10);

            pincel3 = new Paint();
            pincel3.setColor(Color.BLUE);
            pincel3.setStyle(Paint.Style.FILL_AND_STROKE);
            pincel3.setStrokeWidth(5);

            pincel4 = new Paint();
            pincel4.setColor(Color.MAGENTA);
            pincel4.setStyle(Paint.Style.STROKE);
            pincel4.setStrokeWidth(5);

            pincelTexto = new Paint();
            pincelTexto.setColor(Color.YELLOW);
            pincelTexto.setTextSize(70);
            pincelTexto.setTypeface(Typeface.SANS_SERIF);

            pincelTexto2 = new Paint();
            pincelTexto2.setColor(Color.WHITE);
            pincelTexto2.setTextSize(100);
            pincelTexto2.setTypeface(Typeface.DEFAULT_BOLD);

            pincelBitmap = new Paint();
            pincelBitmap.setColorFilter(new BlendModeColorFilter(Color.BLUE, BlendMode.COLOR));
            matrix.setRotate(25);

        }

        @Override
        protected void onDraw(Canvas lienzo) {
            super.onDraw(lienzo);
            lienzo.drawColor(Color.BLACK);
            lienzo.drawCircle(anchoPantalla / 2f, altoPantalla / 4f, 100, pincel1);
            lienzo.drawRect(0, 0, anchoPantalla / 3f, altoPantalla / 4f, pincel2);
            lienzo.drawOval(new RectF(anchoPantalla * 0.6f, altoPantalla * 0.1f, anchoPantalla * 0.9f, altoPantalla * 0.3f), pincel3);
            lienzo.drawPoint(anchoPantalla * 0.5f, altoPantalla * 0.5f, pincel4);
            lienzo.drawLine(anchoPantalla * 0.1f, altoPantalla * 0.6f, anchoPantalla * 0.9f, altoPantalla * 0.6f, pincel4);
            lienzo.drawArc(new RectF(anchoPantalla * 0.1f, altoPantalla * 0.7f, anchoPantalla * 0.4f, altoPantalla * 0.9f),
                    0, 135, true, pincel1);
            lienzo.save();
            pincelTexto.setTextSkewX(-0.4f);
            lienzo.drawText("DIO MIO", anchoPantalla * 0.5f, altoPantalla * 0.55f, pincelTexto);
            lienzo.restore();

            pincelTexto2.setTextSize(50);
            pincelTexto2.setTextAlign(Paint.Align.LEFT);
            lienzo.drawText("Texto normal", anchoPantalla * 0.05f, altoPantalla * 0.95f, pincelTexto2);
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.images);
            lienzo.drawBitmap(bmp, 50, 1300, null);

            lienzo.drawBitmap(bmp, 50, 600, pincelBitmap);
            lienzo.drawBitmap(bmp, matrix, null);

        }
    }
}