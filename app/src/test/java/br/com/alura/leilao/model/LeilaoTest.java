package br.com.alura.leilao.model;

import android.content.SyncStatusObserver;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class LeilaoTest {

    public static final double DELTA = 0.0001;
    private final Leilao console = new Leilao("Console");
    private final Usuario weber = new Usuario("Weber");

    @Test
    public void deve_DevolverDescricao_QuandoRecebeDescricao() {
        String descricaoDevolvida = console.getDescricao();
        //ressultado esperado
        assertEquals("Console", descricaoDevolvida);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeApenasUmLance(){
        console.propoe(new Lance(weber, 200));
        double maiorLanceDevolvido = console.getMaiorLance();
        assertEquals(200, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente(){
        console.propoe(new Lance(weber, 100.0));
        Usuario joice = new Usuario("Joice");
        console.propoe(new Lance(joice, 200.0));
        double maiorLanceDevolvido = console.getMaiorLance();
        assertEquals(200, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente(){
        console.propoe(new Lance(weber, 20000.0));
        Usuario joice = new Usuario("Joice");
        console.propoe(new Lance(joice, 10000.0));
        double maiorLanceDevolvido = console.getMaiorLance();
        assertEquals(20000, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeApenasUmLance(){
        console.propoe(new Lance(weber, 200));
        double menorLanceDevolvido = console.getMenorLance();
        assertEquals(200, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente(){
        console.propoe(new Lance(weber, 100.0));
        Usuario joice = new Usuario("Joice");
        console.propoe(new Lance(joice, 200.0));
        double menorLanceDevolvido = console.getMenorLance();
        assertEquals(100, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente(){
        console.propoe(new Lance(weber, 20000.0));
        Usuario joice = new Usuario("Joice");
        console.propoe(new Lance(joice, 10000.0));
        double menorLanceDevolvido = console.getMenorLance();
        assertEquals(10000, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeExatosTresLances(){
        console.propoe(new Lance(weber, 200.0));
        console.propoe(new Lance(new Usuario("Joice"), 300.0));
        console.propoe(new Lance(weber, 400.0));

        List<Lance> tresMaioresLancesDevolvidos = console.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidos.size());
        assertEquals(400.0,
                tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(300.0,
                tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
        assertEquals(200.0,
                tresMaioresLancesDevolvidos.get(2).getValor(), DELTA);
    }
}