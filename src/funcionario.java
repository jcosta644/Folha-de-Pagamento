import java.util.ArrayList;

/**
 * Created by alunoic on 28/07/17.
 */

public class funcionario {
    private String nome, endereco;
    private int tipo, code, metodoDePagamento;
    private float salario, comissao, comissaoBonus, taxaSindical;
    private ArrayList<cartaoPonto> cartoesPonto = new ArrayList<cartaoPonto>();
    private agendaPagamento agendaPagamento;
    private boolean pertenceSindicato;
    //Tipo: 1 - hourly; 2 - salaried; 3 - commissioned
    //Atributo: 1 - salario horario; 2 - salario mensal; 3 - comissao
    //Metodo de pagamento: 1 - cheque por correios; 2 - cheque em mãos; 3 - deposito em conta bancaria


    public funcionario() {
    }

    public funcionario(String nome, String endereco, int tipo, int code, float salario, agendaPagamento agendaPagamento) {
        this.nome = nome;
        this.endereco = endereco;
        this.tipo = tipo;
        this.salario = salario;
        this.code = code;
        this.comissao = 0;
        this.comissaoBonus = 0;
        this.metodoDePagamento = 2;
        this.pertenceSindicato = false;
        this.agendaPagamento = agendaPagamento;
    }

    public funcionario(String nome, String endereco, int tipo, int code, float salario, float comissao, agendaPagamento agendaPagamento) {
        this.nome = nome;
        this.endereco = endereco;
        this.tipo = tipo;
        this.code = code;
        this.salario = salario;
        this.comissao = comissao;
        this.comissaoBonus = 0;
        this.metodoDePagamento = 2;
        this.pertenceSindicato = false;
        this.agendaPagamento = agendaPagamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public float getSalario() {
        return salario;
    }

    public void setAtributo(float salario) {
        this.salario = salario;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public float getComissao() {
        return comissao;
    }

    public void setComissao(float comissao) {
        this.comissao = comissao;
    }

    public float getComissaoBonus() {
        return comissaoBonus;
    }

    public void setComissaoBonus(float comissaoBonus) {
        this.comissaoBonus = comissaoBonus;
    }

    public ArrayList<cartaoPonto> getCartoesPonto() {
        return cartoesPonto;
    }

    public void addCartaoPonto(cartaoPonto cp) {
        this.cartoesPonto.add(cp);
    }

    public int getMetodoDePagamento() {
        return metodoDePagamento;
    }

    public void setMetodoDePagamento(int metodoDePagamento) {
        this.metodoDePagamento = metodoDePagamento;
    }

    public float getTaxaSindical() {
        return taxaSindical;
    }

    public void setTaxaSindical(float taxaSindical) {
        this.taxaSindical = taxaSindical;
    }

    public boolean isPertenceSindicato() {
        return pertenceSindicato;
    }

    public void setPertenceSindicato(boolean pertenceSindicato) {
        this.pertenceSindicato = pertenceSindicato;
    }

    public agendaPagamento getAgendaPagamento() {
        return agendaPagamento;
    }

    public void setAgendaPagamento(agendaPagamento agendaPagamento) {
        this.agendaPagamento = agendaPagamento;
    }
}
