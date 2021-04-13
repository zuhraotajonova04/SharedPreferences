package Shared.Preferencesss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    EditText editText;
    Button button1, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.edittext);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadtext();
            }
        });
    }

    public void save() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();
        ed.putString("savedtext", editText.getText().toString());
        ed.commit();
        Toast.makeText(this, "Text SAVED", Toast.LENGTH_SHORT).show();
    }

    public void loadtext() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        String savedtext = sharedPreferences.getString("savedtext", "");
        editText.setText(savedtext);
        Toast.makeText(this, "Text LOADED", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menyu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.russian:
                setlocale("ru");
                recreate();
                Toast.makeText(this, "RUSSIAN", Toast.LENGTH_SHORT).show();
                break;
            case R.id.english:
                setlocale("en");
                recreate();
                Toast.makeText(this, "ENGLISH", Toast.LENGTH_SHORT).show();
                break;
            case R.id.uzbek:
                setlocale("uz");
                recreate();
                Toast.makeText(this, "UZBEK", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void loadlocale(){
        SharedPreferences sharedPreferences=getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language=sharedPreferences.getString("My_lang","");
        setlocale(language);
    }
    public void setlocale(String lang){
        Locale locale=new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration=new Configuration();
        configuration.locale=locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor=getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_lang",lang);
        editor.apply();
    }
}