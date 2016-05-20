package edu.kobinotes.sharedpreferencesexample;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    //utilizando la dependecia vista en el blog
    //http://luiscobian.blogspot.mx/2016/03/android-butterknife-enlazar-componentes.html
    // para la versión 8.0.1 ButterKnife cambia para las vistas
    //BindView

    @Bind(R.id.editText1)
    EditText texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        //Se inicializa el objeto de preferencias
        // Existen mas metodos que el MODE_PRIVATE pero estan
        //depreciados en el caso de MODE_WORLD_READABLE y
        // MODE_WORLD_WRITEABLE por tener problemas de seguridad
        SharedPreferences sp = getSharedPreferences("mispreferencias",MODE_PRIVATE);
        String nombre = sp.getString("nombre","NO HAY GUARDADO");
        //mostramos el valor guardado o si es la primera vez
        // y no hay guardado simplemente pone el valor por default
        texto.setText(nombre);
    }

    @OnClick(R.id.button)
    public void guardaValor()
    {
        // Se obtiene el valor
        String nuevoNombre = texto.getText().toString();
        // Se crea el objeto para controlar las preferencias
        SharedPreferences sp = getSharedPreferences("mispreferencias",MODE_PRIVATE);
        //Se genera un Editor para modificar
        SharedPreferences.Editor editor = sp.edit();
        //asignamos el valor al map
        editor.putString("nombre",nuevoNombre);
        // guardamos cambios
        editor.commit();
    }


}
