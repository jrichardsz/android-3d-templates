package android.jrichardsz.org.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSuavizado, btnSinSuavizado, btn3ds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSinSuavizado = (Button) findViewById(R.id.sinSuavizado);
        btnSuavizado = (Button) findViewById(R.id.conSuavizado);
        btn3ds = (Button) findViewById(R.id.con3ds);

        btnSinSuavizado.setOnClickListener(this);
        btnSuavizado.setOnClickListener(this);
        btn3ds.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sinSuavizado:
                startActivity(new Intent(this, _items[1].cls));
                break;
            case R.id.conSuavizado:
                startActivity(new Intent(this, _items[0].cls));
                break;
            case R.id.con3ds:
                startActivity(new Intent(this, _items[2].cls));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }


    class ItemVo {
        public String filename;
        public Class<?> cls;
        public String label;

        public ItemVo(String $label, Class<?> $class, String $filename) {
            label = $label;
            cls = $class;
            filename = $filename;
        }
    }

    private ItemVo[] _items = {
            new ItemVo("Short 3D Suavizado", Short3DView.class, "Short3DView.java"),
            new ItemVo("Short 3D sin Suavizado", Short3DView2.class, "Short3DView2.java"),
            new ItemVo("Short 3D con 3ds", Short3DView3.class, "Short3DView3.java")

    };

}