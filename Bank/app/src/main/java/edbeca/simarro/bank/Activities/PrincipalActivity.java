package edbeca.simarro.bank.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import edbeca.simarro.bank.R;
import edbeca.simarro.bank.pojo.Cliente;

public class PrincipalActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textoBievenida;
    private Cliente cliente;
    private ImageButton btCambiarClave;
    private ImageButton btVolver;
    private ImageButton btPosicionGlobal, btTranferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        textoBievenida = (TextView)findViewById(R.id.textBienvenida);

        cliente = (Cliente) getIntent().getSerializableExtra("Cliente");

        textoBievenida.setText("Hola " + cliente.getNombre() + " " + cliente.getApellidos());


        btCambiarClave = (ImageButton)findViewById(R.id.btCambiarClave);
        btVolver = (ImageButton)findViewById(R.id.btVolver);
        btPosicionGlobal = (ImageButton)findViewById(R.id.btPosGlobal);
        btTranferencias = (ImageButton)findViewById(R.id.btTransfer);

        btCambiarClave.setOnClickListener(this);
        btVolver.setOnClickListener(this);
        btPosicionGlobal.setOnClickListener(this);
        btTranferencias.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        String opciones = ((ImageButton) view).getTag().toString();
        Intent i;
        switch (opciones) {
            case "Volver":
                i = new Intent(view.getContext(), LoginActivity.class);
                startActivity(i);
                break;

            case "Cambiar clave":
                i = new Intent(view.getContext(), ClaveActivity.class);
                i.putExtra("Cliente", cliente);
                startActivity(i);
                break;

            case "Posicion Global":
                i = new Intent(view.getContext(), PosicionGlobalActivity.class);
                i.putExtra("Cliente", cliente);
                startActivity(i);
                break;

            case "Transferencias":

                i = new Intent(view.getContext(), TransferenciaActivity.class);
                i.putExtra("Cliente", cliente);
                startActivity(i);
                break;

        }
    }
}