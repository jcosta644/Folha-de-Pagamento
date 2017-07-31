/**
 * Created by alunoic on 31/07/17.
 */
public class agendaPagamento {
    private String tipoAgenda;
    private int diaMes, diaSemana;

    public agendaPagamento(String tipoAgenda, int diaMes, int diaSemana) {
        this.tipoAgenda = tipoAgenda;
        this.diaMes = diaMes;
        this.diaSemana = diaSemana;
    }

    public String getTipoAgenda() {
        return tipoAgenda;
    }

    public void setTipoAgenda(String tipoAgenda) {
        this.tipoAgenda = tipoAgenda;
    }

    public int getDiaMes() {
        return diaMes;
    }

    public void setDiaMes(int diaMes) {
        this.diaMes = diaMes;
    }

    public int getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(int diaSemana) {
        this.diaSemana = diaSemana;
    }
}
