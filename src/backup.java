import java.util.ArrayList;

/**
 * Created by HP on 01/08/2017.
 */
public class backup {
    private ArrayList<funcionario> funcionarios = new ArrayList<funcionario>();
    private ArrayList<cartaoPonto> cartoesPonto = new ArrayList<cartaoPonto>();
    private ArrayList<agendaPagamento> agendaPagamentos = new ArrayList<agendaPagamento>();

    public backup(ArrayList<funcionario> funcionarios, ArrayList<cartaoPonto> cartoesPonto, ArrayList<agendaPagamento> agendaPagamentos) {
        this.funcionarios = funcionarios;
        this.cartoesPonto = cartoesPonto;
        this.agendaPagamentos = agendaPagamentos;
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
