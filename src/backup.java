import java.util.ArrayList;

/**
 * Created by HP on 01/08/2017.
 */
public class backup {
    private ArrayList<funcionario> funcionarios = new ArrayList<funcionario>();
    private ArrayList<cartaoPonto> cartoesPonto = new ArrayList<cartaoPonto>();
    private ArrayList<agendaPagamento> agendaPagamentos = new ArrayList<agendaPagamento>();

    public backup(ArrayList<funcionario> funcionarios, ArrayList<cartaoPonto> cartoesPonto, ArrayList<agendaPagamento> agendaPagamentos) {
        for(funcionario f : funcionarios) {
            this.funcionarios.add(new funcionario(f.getNome(), f.getEndereco(), f.getTipo(), f.getCode(), f.getSalario(), f.getComissao(), new agendaPagamento(f.getAgendaPagamento().getTipoAgenda(), f.getAgendaPagamento().getDiaMes(), f.getAgendaPagamento().getDiaSemana())));
        }
        for(cartaoPonto cp : cartoesPonto) {
            this.cartoesPonto.add(new cartaoPonto(cp.getInicio(), cp.getFim()));
        }
        for(agendaPagamento ap : agendaPagamentos) {
            this.agendaPagamentos.add(new agendaPagamento(ap.getTipoAgenda(), ap.getDiaMes(), ap.getDiaSemana()));
        }
    }

    public ArrayList<funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(ArrayList<funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public ArrayList<cartaoPonto> getCartoesPonto() {
        return cartoesPonto;
    }

    public void setCartoesPonto(ArrayList<cartaoPonto> cartoesPonto) {
        this.cartoesPonto = cartoesPonto;
    }

    public ArrayList<agendaPagamento> getAgendaPagamentos() {
        return agendaPagamentos;
    }

    public void setAgendaPagamentos(ArrayList<agendaPagamento> agendaPagamentos) {
        this.agendaPagamentos = agendaPagamentos;
    }
}
