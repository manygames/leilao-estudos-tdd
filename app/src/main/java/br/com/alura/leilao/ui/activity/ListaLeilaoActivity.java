package br.com.alura.leilao.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.alura.leilao.R;
import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.ui.recyclerview.adapter.ListaLeilaoAdapter;

public class ListaLeilaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_leilao);
        ListaLeilaoAdapter adapter = new ListaLeilaoAdapter(this, leiloesDeExemplo());
        RecyclerView recyclerView = findViewById(R.id.lista_leilao_recyclerview);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new ListaLeilaoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Leilao leilao) {
                Intent vaiParaLancesLeilao = new Intent(ListaLeilaoActivity.this, LancesLeilaoActivity.class);
                vaiParaLancesLeilao.putExtra("leilao", leilao);
                startActivity(vaiParaLancesLeilao);
            }
        });
    }

    private List<Leilao> leiloesDeExemplo() {
        Leilao console = new Leilao("Console");
        console.propoe(new Lance(new Usuario("Weber"), 200));
        console.propoe(new Lance(new Usuario("Joice"), 100));
        console.propoe(new Lance(new Usuario("Joice"), 300));

        Leilao computador = new Leilao("Computador");
        computador.propoe(new Lance(new Usuario("Weber"), 2000));
        computador.propoe(new Lance(new Usuario("Joice"), 1000));
        computador.propoe(new Lance(new Usuario("Joice"), 3000));

        Leilao carro = new Leilao("Carro");
        carro.propoe(new Lance(new Usuario("Weber"), 20000));
        carro.propoe(new Lance(new Usuario("Joice"), 10000));
        carro.propoe(new Lance(new Usuario("Joice"), 30000));

        ArrayList<Leilao> leiloes = new ArrayList<>();
        leiloes.add(console);
        leiloes.add(computador);
        leiloes.add(carro);

        return leiloes;
    }

}
