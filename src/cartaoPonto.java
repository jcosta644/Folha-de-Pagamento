import java.util.Calendar;
import java.util.Date;

/**
 * Created by alunoic on 28/07/17.
 */

public class cartaoPonto {
    private Date inicio;
    private Date fim;

    public cartaoPonto() {
    }

    public cartaoPonto(Date inicio, Date fim) {
        this.inicio = inicio;
        this.fim = fim;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }
}
