package edbeca.simarro.bank.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

import edbeca.simarro.bank.BD.MiBancoOperacional;
import edbeca.simarro.bank.R;
import edbeca.simarro.bank.pojo.Cliente;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, Serializable {

    private Button ir;
    private EditText usuario;
    private EditText password;
    public MiBancoOperacional bo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bo = MiBancoOperacional.getInstance(this);

        ir = (Button)findViewById(R.id.ir);
        usuario = (EditText)findViewById(R.id.usuario);
        password = (EditText)findViewById(R.id.password);


        ir.setOnClickListener(this);

        usuario.setText("11111111A");
        password.setText("1234");
    }

    @Override
    public void onClick(View view) {

        Cliente cliente = new Cliente();
        cliente.setNif(usuario.getText().toString());
        cliente.setClaveSeguridad(password.getText().toString());

        cliente = bo.login(cliente);

        if (cliente != null) {
            Intent intent = new Intent(view.getContext(), PrincipalActivity.class);
            intent.putExtra("Cliente", cliente);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Usuario incorrecto", Toast.LENGTH_SHORT).show();
        }

    }



    }
