import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Calendar;
import java.util.Date;
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
        ArrayList<funcionario> funcionariosRemovidos = new ArrayList<funcionario>();
        ArrayList<cartaoPonto> cartoesPonto = new ArrayList<cartaoPonto>();
        ArrayList<agendaPagamento> agendaPagamentos = new ArrayList<agendaPagamento>();

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
        agendaPagamentos.add(new agendaPagamento("semana", 1, 5));
        agendaPagamentos.add(new agendaPagamento("mensal", 30, 0));
        agendaPagamentos.add(new agendaPagamento("bi-semanal", 2, 5));

        do {
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
            opc = scannerInt.nextInt();

            switch(opc) {
                case 1:
                    System.out.println("Digite o nome do funcionario:\n");
                    nome = scannerString.nextLine();
                    System.out.println("Digite o endereco do funcionario:\n");
                    endereco = scannerString.nextLine();
                    System.out.println("Digite o tipo (1 - hourly; 2 - salaried; 3 - commissioned) do funcionario:\n");
                    tipo = scannerInt.nextInt();
                    System.out.println("Digite o salario do funcionario:\n");
                    salario = scannerFloat.nextFloat();
                    switch(tipo) {
                        case 1:
                            for(agendaPagamento ap : agendaPagamentos) {
                                if(ap.getTipoAgenda().equals("semanal")) {
                                    funcionarios.add(new funcionario(nome, endereco, tipo, code, salario, ap));
                                }
                            }
                        case 2:
                            for(agendaPagamento ap : agendaPagamentos) {
                                if (ap.getTipoAgenda().equals("mensal")) {
                                    funcionarios.add(new funcionario(nome, endereco, tipo, code, salario, ap));
                                }
                            }
                            break;
                        case 3:
                            System.out.println("Digite o percentual de comissao do funcionario:\n");
                            comissao = scannerFloat.nextFloat();
                            for(agendaPagamento ap : agendaPagamentos) {
                                if (ap.getTipoAgenda().equals("bi-semanal")) {
                                    funcionarios.add(new funcionario(nome, endereco, tipo, code, salario, comissao, ap));
                                }
                            }
                            break;
                    }
                    code++;
                    break;
                case 2:
                    System.out.println("Digite o codigo do funcionario que deseja remover:\n");
                    codeBusca = scannerInt.nextInt();
                    for(funcionario f : funcionarios) {
                        if(f.getCode() == codeBusca) {
                            funcionariosRemovidos.add(f);
                            funcionarios.remove(f);
                            break;
                        }
                    }
                    break;
                case 3:
                    System.out.println("Digite o codigo do funcionario que deseja adicionar o cartao de ponto:\n");
                    codeBusca = scannerInt.nextInt();
                    for(funcionario f : funcionarios) {
                        if(f.getCode() == codeBusca) {
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
                    System.out.println("Digite o codigo do funcionario que deseja adicionar a comissao da venda:\n");
                    codeBusca = scannerInt.nextInt();
                    for(funcionario f : funcionarios) {
                        if(f.getCode() == codeBusca) {
                            System.out.println("Digite o valor da venda realizada:\n");
                            valorVenda = scannerFloat.nextFloat();
                            f.setComissaoBonus(f.getComissaoBonus() + (valorVenda * f.getComissao()) / 100);
                        }
                    }
                    break;
                case 5:
                    System.out.println("Digite o codigo do funcionario que deseja cobrar a taxa de servico:\n");
                    codeBusca = scannerInt.nextInt();
                    for(funcionario f : funcionarios) {
                        if(f.getCode() == codeBusca) {
                            System.out.println("Digite a taxa de servico:\n");
                            taxaServico = scannerFloat.nextFloat();
                            f.setComissaoBonus(f.getComissaoBonus() - taxaServico);
                        }
                    }
                    break;
                case 6:
                    System.out.println("Digite o codigo do funcionario que deseja alterar os dados:\n");
                    codeBusca = scannerInt.nextInt();
                    for(funcionario f : funcionarios) {
                        if(f.getCode() == codeBusca) {
                            System.out.println("O que deseja alterar:\n" +
                                    "1 - Nome\n" +
                                    "2 - Endereco\n" +
                                    "3 - Tipo\n" +
                                    "4 - Metodo de pagamento\n" +
                                    "5 - Entrar/Sair do sindicato\n" +
                                    "6 - Taxa sindical\n");
                            opc2 = scannerInt.nextInt();

                            switch(opc2) {
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
                                    tipo = scannerInt.nextInt();
                                    f.setTipo(tipo);
                                    break;
                                case 4:
                                    System.out.println("Digite o novo metodo de pagamento (1 - cheque por correios; 2 - cheque em mãos; 3 - deposito em conta bancaria) do funcionario:\n");
                                    metodoDePagamento = scannerInt.nextInt();
                                    f.setMetodoDePagamento(metodoDePagamento);
                                    break;
                                case 5:
                                    f.setPertenceSindicato(!f.isPertenceSindicato());
                                    break;
                                case 6:
                                    System.out.println("Digite a nova taxa sindical:\n");
                                    taxaSindical = scannerFloat.nextFloat();
                                    f.setTaxaSindical(taxaSindical);
                                    break;
                            }
                        }
                    }
                    break;
                case 7:
                    System.out.println("Todos os funcionarios receberam seus salarios!");
                    break;
                case 8:
                    System.out.println("Ultima ação foi desfeita!");
                    break;
                case 9:
                    System.out.println("Digite o codigo do funcionario que deseja alterar a agenda de pagamento:\n");
                    codeBusca = scannerInt.nextInt();
                    for(funcionario f : funcionarios) {
                        if(f.getCode() == codeBusca) {
                            System.out.println("Digite o nome da nova agenda do funcionario:\n");
                            agendaBusca = scannerString.nextLine();
                            for(agendaPagamento ap : agendaPagamentos) {
                                if(ap.getTipoAgenda().equals(agendaBusca)) {
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
                    diaMes = scannerInt.nextInt();
                    System.out.println("Digite o dia da semana(0 - Pagamento Mensal; 1 - Sábado; 2 - Segunda; 3 - Terça...) que receberá o pagamento:\n");
                    diaSemana = scannerInt.nextInt();
                    agendaPagamentos.add(new agendaPagamento(tipoAgenda, diaMes, diaSemana));
                    break;
            }

        } while(opc != 0);
    }
}
