package br.com.alura.leilao.model;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import br.com.alura.leilao.builder.LeilaoBuilder;
import br.com.alura.leilao.exception.LanceMenorQueUltimoLanceException;
import br.com.alura.leilao.exception.LanceSeguidoDeMesmoUsuarioException;
import br.com.alura.leilao.exception.UsuarioJaDeuCincoLancesException;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.*;

public class LeilaoTest {

    public static final double DELTA = 0.0001;
    private final Leilao CONSOLE = new Leilao("Console");
    private final Usuario WEBER = new Usuario("Weber");

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void deve_DevolverDescricao_QuandoRecebeDescricao() {
        String descricaoDevolvida = CONSOLE.getDescricao();
        assertThat(descricaoDevolvida, is("Console"));
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeApenasUmLance() {
        CONSOLE.propoe(new Lance(WEBER, 200.0));
        double maiorLanceDevolvido = CONSOLE.getMaiorLance();
        assertThat(maiorLanceDevolvido, closeTo(200.0, DELTA));
    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {
        CONSOLE.propoe(new Lance(WEBER, 100.0));
        Usuario joice = new Usuario("Joice");
        CONSOLE.propoe(new Lance(joice, 200.0));
        double maiorLanceDevolvido = CONSOLE.getMaiorLance();
        assertEquals(200, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeApenasUmLance() {
        CONSOLE.propoe(new Lance(WEBER, 200));
        double menorLanceDevolvido = CONSOLE.getMenorLance();
        assertEquals(200, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeMaisDeUmLanceEmOrdemCrescente() {
        CONSOLE.propoe(new Lance(WEBER, 100.0));
        Usuario joice = new Usuario("Joice");
        CONSOLE.propoe(new Lance(joice, 200.0));
        double menorLanceDevolvido = CONSOLE.getMenorLance();
        assertEquals(100, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeExatosTresLances() {
        CONSOLE.propoe(new Lance(WEBER, 200.0));
        CONSOLE.propoe(new Lance(new Usuario("Joice"), 300.0));
        CONSOLE.propoe(new Lance(WEBER, 400.0));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertThat(tresMaioresLancesDevolvidos,
                both(Matchers.<Lance>hasSize(3)).and(contains(
                        new Lance(WEBER, 400.0),
                        new Lance(new Usuario("Joice"), 300.0),
                        new Lance(WEBER, 200.0))));
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoNaoRecebeLances() {
        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();
        assertEquals(0, tresMaioresLancesDevolvidos.size());
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeUmLance() {
        CONSOLE.propoe(new Lance(WEBER, 400));
        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();
        assertEquals(1, tresMaioresLancesDevolvidos.size());
        assertEquals(400.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeDoisLances() {
        CONSOLE.propoe(new Lance(new Usuario("Joice"), 300));
        CONSOLE.propoe(new Lance(WEBER, 400));
        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();
        assertEquals(2, tresMaioresLancesDevolvidos.size());
        assertEquals(400.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(300.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLancesParaQuatroLances_QuandoRecebeMaisDeTresLances() {
        CONSOLE.propoe(new Lance(WEBER, 300));
        CONSOLE.propoe(new Lance(new Usuario("Joice"), 400));
        CONSOLE.propoe(new Lance(WEBER, 500));
        CONSOLE.propoe(new Lance(new Usuario("Joice"), 700));
        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();
        assertEquals(3, tresMaioresLancesDevolvidos.size());
        assertEquals(700.0, tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(500.0, tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
        assertEquals(400.0, tresMaioresLancesDevolvidos.get(2).getValor(), DELTA);

        CONSOLE.propoe(new Lance(WEBER, 999));

        List<Lance> tresMaioresLancesParaCincoLances = CONSOLE.tresMaioresLances();
        assertEquals(3, tresMaioresLancesParaCincoLances.size());
        assertEquals(999.0, tresMaioresLancesParaCincoLances.get(0).getValor(), DELTA);
        assertEquals(700.0, tresMaioresLancesParaCincoLances.get(1).getValor(), DELTA);
        assertEquals(500.0, tresMaioresLancesParaCincoLances.get(2).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverZeroParaMaiorLance_QuandoNaoTiverLances() {
        double maiorLanceDevolvido = CONSOLE.getMaiorLance();
        assertEquals(0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverZeroParaMenorLance_QuandoNaoTiverLances() {
        double menorLanceDevolvido = CONSOLE.getMenorLance();
        assertEquals(0, menorLanceDevolvido, DELTA);
    }

    @Test(expected = LanceMenorQueUltimoLanceException.class)
    public void naoDeve_AdicionarLance_QuandoForMenorQueMaiorLance() {
        CONSOLE.propoe(new Lance(WEBER, 500.0));
        CONSOLE.propoe(new Lance(new Usuario("Joice"), 400.0));
    }

    @Test(expected = LanceSeguidoDeMesmoUsuarioException.class)
    public void naoDeve_AdicionarLance_QuandoForOMesmoUsuarioDoUltimoLance() {
        CONSOLE.propoe(new Lance(WEBER, 500));
        CONSOLE.propoe(new Lance(WEBER, 600));
    }

    @Test(expected = UsuarioJaDeuCincoLancesException.class)
    public void naoDeve_AdicionarLance_QuandoUsuarioJaDeuCincoLances() {
        final Usuario JOICE = new Usuario("Joice");
        new LeilaoBuilder("Console")
                .lance(WEBER, 100)
                .lance(JOICE, 200)
                .lance(WEBER, 300)
                .lance(JOICE, 400)
                .lance(WEBER, 500)
                .lance(JOICE, 600)
                .lance(WEBER, 700)
                .lance(JOICE, 800)
                .lance(WEBER, 900)
                .lance(JOICE, 1000)
                .lance(WEBER, 1100)
                .build();
    }
}