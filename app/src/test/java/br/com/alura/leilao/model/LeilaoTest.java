package br.com.alura.leilao.model;

import android.content.SyncStatusObserver;

import org.junit.Test;

import static org.junit.Assert.*;

public class LeilaoTest {

    @Test
    public void deve_DevolverDescricao_QuandoRecebeDescricao() {
        //criar cenário
        Leilao leilao = new Leilao("Console");
        //executar ação
        String descricaoDevolvida = leilao.getDescricao();
        //ressultado esperado
        assertEquals("Console", descricaoDevolvida);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeApenasUmLance(){
        Leilao console = new Leilao("Console");
        console.propoe(new Lance(new Usuario("Weber"), 200));
        double maiorLanceDevolvido = console.getMaiorLance();
        assertEquals(200, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente(){
        Leilao computador = new Leilao("Computador");
        computador.propoe(new Lance(new Usuario("Weber"), 100.0));
        computador.propoe(new Lance(new Usuario("Weber"), 200.0));
        double maiorLanceDevolvido = computador.getMaiorLance();
        assertEquals(200, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente(){
        Leilao carro = new Leilao("Carro");
        carro.propoe(new Lance(new Usuario("Weber"), 20000.0));
        carro.propoe(new Lance(new Usuario("Weber"), 10000.0));
        double maiorLanceDevolvido = carro.getMaiorLance();
        assertEquals(20000, maiorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeApenasUmLance(){
        Leilao console = new Leilao("Console");
        console.propoe(new Lance(new Usuario("Weber"), 200));
        double menorLanceDevolvido = console.getMenorLance();
        assertEquals(200, menorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente(){
        Leilao computador = new Leilao("Computador");
        computador.propoe(new Lance(new Usuario("Weber"), 100.0));
        computador.propoe(new Lance(new Usuario("Weber"), 200.0));
        double menorLanceDevolvido = computador.getMenorLance();
        assertEquals(100, menorLanceDevolvido, 0.0001);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemDecrescente(){
        Leilao carro = new Leilao("Carro");
        carro.propoe(new Lance(new Usuario("Weber"), 20000.0));
        carro.propoe(new Lance(new Usuario("Weber"), 10000.0));
        double menorLanceDevolvido = carro.getMenorLance();
        assertEquals(10000, menorLanceDevolvido, 0.0001);
    }
}