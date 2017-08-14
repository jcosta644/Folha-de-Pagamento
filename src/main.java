import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.ParseException;
import java.text.DateFormat;


/**
 * Created by alunoic on 28/07/17.
 */

public class main {
    public static void main(String[] args) throws ParseException {

        //Variaveis de scanner
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerString = new Scanner(System.in);
        Scanner scannerFloat = new Scanner(System.in);

        //Variaveis de lista
        ArrayList<funcionario> funcionarios = new ArrayList<funcionario>();
        ArrayList<cartaoPonto> cartoesPonto = new ArrayList<cartaoPonto>();
        ArrayList<agendaPagamento> agendaPagamentos = new ArrayList<agendaPagamento>();

        //Variaveis de backup
        Stack<backup> undo = new Stack<>();
        Stack<backup> redo = new Stack<>();

        //Variaveis de cartao de ponto
        String dataInicio, dataFim;
        cartaoPonto cp;

        //Variaveis de agenda de pagamento
        String tipoAgenda;
        int diaMes, diaSemana;

        //Variaveis de funcionario
        int tipo, code, metodoDePagamento;
        float salario, comissao, taxaSindical;
        String nome, endereco;
        code = 0;

        //Variaveis de busca
        int codeBusca;
        String agendaBusca;

        //Variaveis auxiliares;
        int opc, opc2;
        float valorVenda, taxaServico;

        //Agendas iniciais
        agendaPagamentos.add(new agendaPagamento("semana", 1, 6));
        agendaPagamentos.add(new agendaPagamento("mensal", 30, 0));
        agendaPagamentos.add(new agendaPagamento("bi-semanal", 2, 6));

        do {
            try {
                System.out.println("Menu:\n" +
                        "1 - Adicao de um empregado\n" +
                        "2 - Remocao de um empregado\n" +
                        "3 - Lancar um cartao de ponto\n" +
                        "4 - Lancar um resultado venda\n" +
                        "5 - Lancar uma taxa de servico\n" +
                        "6 - Alterar detalhes de um empregado\n" +
                        "7 - Rodar a folha de pagamento para hoje\n" +
                        "8 - Undo / Redo\n" +
                        "9 - Agenda de pagamento\n" +
                        "10 - Criacao de novas agendas de pagamento\n" +
                        "0 - Sair\n");
                opc = Integer.parseInt(scannerInt.next());

                switch (opc) {
                    case 1:
                        undo.push(new backup(funcionarios, cartoesPonto, agendaPagamentos));
                        System.out.println("Digite o nome do funcionario:\n");
                        nome = scannerString.nextLine();
                        System.out.println("Digite o endereco do funcionario:\n");
                        endereco = scannerString.nextLine();
                        System.out.println("Digite o tipo (1 - hourly; 2 - salaried; 3 - commissioned) do funcionario:\n");
                        tipo = Integer.parseInt(scannerInt.next());
                        System.out.println("Digite o salario do funcionario:\n");
                        salario = Float.parseFloat(scannerFloat.next());
                        switch (tipo) {
                            case 1:
                                for (agendaPagamento ap : agendaPagamentos) {
                                    if (ap.getTipoAgenda().equals("semanal")) {
                                        funcionarios.add(new funcionario(nome, endereco, tipo, code, salario, ap));
                                    }
                                }
                            case 2:
                                for (agendaPagamento ap : agendaPagamentos) {
                                    if (ap.getTipoAgenda().equals("mensal")) {
                                        funcionarios.add(new funcionario(nome, endereco, tipo, code, salario, ap));
                                    }
                                }
                                break;
                            case 3:
                                System.out.println("Digite o percentual de comissao do funcionario:\n");
                                comissao = Float.parseFloat(scannerFloat.next());
                                for (agendaPagamento ap : agendaPagamentos) {
                                    if (ap.getTipoAgenda().equals("bi-semanal")) {
                                        funcionarios.add(new funcionario(nome, endereco, tipo, code, salario, comissao, ap));
                                    }
                                }
                                break;
                        }
                        code++;
                        break;
                    case 2:
                        undo.push(new backup(funcionarios, cartoesPonto, agendaPagamentos));
                        System.out.println("Digite o codigo do funcionario que deseja remover:\n");
                        codeBusca = Integer.parseInt(scannerInt.next());
                        for (funcionario f : funcionarios) {
                            if (f.getCode() == codeBusca) {
                                funcionarios.remove(f);
                                break;
                            }
                        }
                        break;
                    case 3:
                        undo.push(new backup(funcionarios, cartoesPonto, agendaPagamentos));
                        System.out.println("Digite o codigo do funcionario que deseja adicionar o cartao de ponto:\n");
                        codeBusca = Integer.parseInt(scannerInt.next());
                        for (funcionario f : funcionarios) {
                            if (f.getCode() == codeBusca) {
                                DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                                System.out.println("Digite a data e hora de inicio do ponto (Formato: dd/MM/yyyy HH:mm:ss):\n");
                                dataInicio = scannerString.nextLine();
                                System.out.println("Digite a data e hora de termino do ponto (Formato: dd/MM/yyyy HH:mm:ss):\n");
                                dataFim = scannerString.nextLine();
                                cp = new cartaoPonto(df.parse(dataInicio), df.parse(dataFim));
                                cartoesPonto.add(cp);
                                f.addCartaoPonto(cp);
                                break;
                            }
                        }
                        break;
                    case 4:
                        undo.push(new backup(funcionarios, cartoesPonto, agendaPagamentos));
                        System.out.println("Digite o codigo do funcionario que deseja adicionar a comissao da venda:\n");
                        codeBusca = Integer.parseInt(scannerInt.next());
                        for (funcionario f : funcionarios) {
                            if (f.getCode() == codeBusca) {
                                System.out.println("Digite o valor da venda realizada:\n");
                                valorVenda = Float.parseFloat(scannerFloat.next());
                                f.setComissaoBonus(f.getComissaoBonus() + (valorVenda * f.getComissao()) / 100);
                            }
                        }
                        break;
                    case 5:
                        undo.push(new backup(funcionarios, cartoesPonto, agendaPagamentos));
                        System.out.println("Digite o codigo do funcionario que deseja cobrar a taxa de servico:\n");
                        codeBusca = Integer.parseInt(scannerInt.next());
                        for (funcionario f : funcionarios) {
                            if (f.getCode() == codeBusca) {
                                System.out.println("Digite a taxa de servico:\n");
                                taxaServico = Float.parseFloat(scannerFloat.next());
                                f.setComissaoBonus(f.getComissaoBonus() - taxaServico);
                            }
                        }
                        break;
                    case 6:
                        undo.push(new backup(funcionarios, cartoesPonto, agendaPagamentos));
                        System.out.println("Digite o codigo do funcionario que deseja alterar os dados:\n");
                        codeBusca = Integer.parseInt(scannerInt.next());
                        for (funcionario f : funcionarios) {
                            if (f.getCode() == codeBusca) {
                                System.out.println("O que deseja alterar:\n" +
                                        "1 - Nome\n" +
                                        "2 - Endereco\n" +
                                        "3 - Tipo\n" +
                                        "4 - Metodo de pagamento\n" +
                                        "5 - Entrar/Sair do sindicato\n" +
                                        "6 - Taxa sindical\n");
                                opc2 = Integer.parseInt(scannerInt.next());

                                switch (opc2) {
                                    case 1:
                                        System.out.println("Digite o novo nome do funcionario:\n");
                                        nome = scannerString.nextLine();
                                        f.setNome(nome);
                                        break;
                                    case 2:
                                        System.out.println("Digite o novo endereco do funcionario:\n");
                                        endereco = scannerString.nextLine();
                                        f.setEndereco(endereco);
                                        break;
                                    case 3:
                                        System.out.println("Digite o novo tipo (1 - hourly; 2 - salaried; 3 - commissioned) do funcionario:\n");
                                        tipo = Integer.parseInt(scannerInt.next());
                                        f.setTipo(tipo);
                                        break;
                                    case 4:
                                        System.out.println("Digite o novo metodo de pagamento (1 - cheque por correios; 2 - cheque em mãos; 3 - deposito em conta bancaria) do funcionario:\n");
                                        metodoDePagamento = Integer.parseInt(scannerInt.next());
                                        f.setMetodoDePagamento(metodoDePagamento);
                                        break;
                                    case 5:
                                        f.setPertenceSindicato(!f.isPertenceSindicato());
                                        break;
                                    case 6:
                                        System.out.println("Digite a nova taxa sindical:\n");
                                        taxaSindical = Float.parseFloat(scannerFloat.next());
                                        f.setTaxaSindical(taxaSindical);
                                        break;
                                }
                            }
                        }
                        break;
                    case 7:
                        undo.push(new backup(funcionarios, cartoesPonto, agendaPagamentos));
                        System.out.println("Todos os funcionarios receberam seus salarios!");
                        Calendar dateNow = Calendar.getInstance();
                        for (funcionario f : funcionarios) {
                            if (f.getAgendaPagamento().getDiaSemana() == 0) {
                                if (dateNow.DAY_OF_MONTH == f.getAgendaPagamento().getDiaMes()) {
                                    System.out.println("O funcionario: " + f.getNome() + ". Recebeu: " + (f.getSalario() + f.getComissaoBonus()) + " no dia de hoje!\n");
                                    f.setComissaoBonus(0);
                                }
                            } else {
                                if (dateNow.DAY_OF_WEEK == f.getAgendaPagamento().getDiaSemana()) {
                                    System.out.println("O funcionario: " + f.getNome() + ". Recebeu: " + (f.getComissaoBonus() + f.getSalario() / f.getAgendaPagamento().getDiaMes()) + " no dia de hoje!\n");
                                    f.setComissaoBonus(0);
                                }
                            }
                        }
                        break;
                    case 8:
                        System.out.println("Digite a opcao desejada:\n" +
                                "1 - Desfazer\n" +
                                "2 - Refazer\n");
                        opc2 = Integer.parseInt(scannerInt.next());
                        switch (opc2) {
                            case 1:
                                if (!undo.isEmpty()) {
                                    redo.push(new backup(funcionarios, cartoesPonto, agendaPagamentos));
                                    backup u = undo.pop();
                                    funcionarios = u.getFuncionarios();
                                    agendaPagamentos = u.getAgendaPagamentos();
                                    cartoesPonto = u.getCartoesPonto();
                                }
                                break;
                            case 2:
                                if (!redo.isEmpty()) {
                                    undo.push(new backup(funcionarios, cartoesPonto, agendaPagamentos));
                                    backup r = redo.pop();
                                    funcionarios = r.getFuncionarios();
                                    agendaPagamentos = r.getAgendaPagamentos();
                                    cartoesPonto = r.getCartoesPonto();
                                }
                                break;
                        }
                        break;
                    case 9:
                        System.out.println("Digite o codigo do funcionario que deseja alterar a agenda de pagamento:\n");
                        codeBusca = Integer.parseInt(scannerInt.next());
                        for (funcionario f : funcionarios) {
                            if (f.getCode() == codeBusca) {
                                System.out.println("Digite o nome da nova agenda do funcionario:\n");
                                agendaBusca = scannerString.nextLine();
                                for (agendaPagamento ap : agendaPagamentos) {
                                    if (ap.getTipoAgenda().equals(agendaBusca)) {
                                        f.setAgendaPagamento(ap);
                                    }
                                }
                            }
                        }
                        break;
                    case 10:
                        System.out.println("Digite o nome da agenda que deseja adicionar:\n");
                        tipoAgenda = scannerString.nextLine();
                        System.out.println("Digite o dia do mês(Em caso de pagamento não mensal, de quantas em quantas semanas irá receber) que receberá o pagamento:\n");
                        diaMes = Integer.parseInt(scannerInt.next());
                        System.out.println("Digite o dia da semana(0 - Pagamento Mensal; 1 - Domingo; 2 - Segunda; 3 - Terça...) que receberá o pagamento:\n");
                        diaSemana = Integer.parseInt(scannerInt.next());
                        agendaPagamentos.add(new agendaPagamento(tipoAgenda, diaMes, diaSemana));
                        break;
                }
            } catch(NumberFormatException e) {
                System.out.println("\nVoce deve digitar um numero!\n\n");
                opc = 1;
            }
        } while(opc != 0);
    }
}
