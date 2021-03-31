package edbeca.simarro.bank.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edbeca.simarro.bank.BD.MiBancoOperacional;
import edbeca.simarro.bank.R;
import edbeca.simarro.bank.pojo.Cliente;

public class ClaveActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnCambiar;
    private MiBancoOperacional bo;
    private Cliente cliente;
    private EditText nuevaPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clave);

        bo = MiBancoOperacional.getInstance(this);

        cliente = (Cliente)getIntent().getSerializableExtra("Cliente");

        btnCambiar = (Button)findViewById(R.id.btnCambiar);
        nuevaPass = (EditText)findViewById(R.id.nuevaPass);

        btnCambiar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        cliente.setClaveSeguridad(nuevaPass.getText().toString());

        if (bo.changePassword(cliente) == 1) {
            Toast.makeText(this, "La contraseña se ha cambiado correctamente", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "La contraseña no se ha cambiado correctamente", Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(view.getContext(), PrincipalActivity.class);
        intent.putExtra("Cliente", cliente);
        startActivity(intent);
    }
}