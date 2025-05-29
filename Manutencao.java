
// Imports necessários
import java.util.Scanner;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;

public class Manutencao extends IdAutomatico {
    // Atributos da classe
    private Ferramenta ferramenta;
    private String motivo;
    private TipoServico tipoServico;
    private Funcionamento funcionamento;
    private String descricaoARealizar;
    private String descricaoRealizada;
    private LocalDateTime inicioOcorrencia;
    private LocalDateTime inicioManutencao;
    private LocalDateTime fimManutencao;

    // lista de manutenções
    private static List<Manutencao> manutencoes = new ArrayList<>();

    // objeto scanner para leitura de dados do usuário
    private static Scanner scanner = new Scanner(System.in);

    // enum para os tipos de serviço
    public enum TipoServico {
        CORRETIVA(1, "CORRETIVA"),
        PREVENTIVA(2, "PREVENTIVA"),
        MELHORIA(3, "MELHORIA");

        private final int codigo;
        private final String descricao;

        TipoServico(int codigo, String descricao) {
            this.codigo = codigo;
            this.descricao = descricao;
        }

        public int getCodigo() {
            return codigo;
        }

        public String getDescricao() {
            return descricao;
        }

        // método para obter um tipo de serviço pelo código
        public static TipoServico getByCodigo(int codigo) {
            for (TipoServico tipo : values()) {
                if (tipo.getCodigo() == codigo) {
                    return tipo;
                }
            }
            throw new IllegalArgumentException("Código de tipo de serviço inválido: " + codigo);
        }
    }

    // enum para o funcionamento do equipamento
    public enum Funcionamento {
        SIM(1, "SIM"),
        NAO(2, "NAO");

        private final int codigo;
        private final String descricao;

        Funcionamento(int codigo, String descricao) {
            this.codigo = codigo;
            this.descricao = descricao;
        }

        public int getCodigo() {
            return codigo;
        }

        public String getDescricao() {
            return descricao;
        }

        // método para obter um funcionamento pelo código
        public static Funcionamento getByCodigo(int codigo) {
            for (Funcionamento funcionamento : values()) {
                if (funcionamento.getCodigo() == codigo) {
                    return funcionamento;
                }
            }
            throw new IllegalArgumentException("Código de funcionamento inválido: " + codigo);
        }
    }

    // Construtor da classe Manutencao
    public Manutencao(Ferramenta ferramenta, String motivo, TipoServico tipoServico,
            Funcionamento funcionamento, String descricaoARealizar,
            String descricaoRealizada, LocalDateTime inicioOcorrencia,
            LocalDateTime inicioManutencao, LocalDateTime fimManutencao) {
        super(); // chama o construtor da classe pai (IdAutomatico)
        this.ferramenta = ferramenta;
        this.motivo = motivo;
        this.tipoServico = tipoServico;
        this.funcionamento = funcionamento;
        this.descricaoARealizar = descricaoARealizar;
        this.descricaoRealizada = descricaoRealizada;
        this.inicioOcorrencia = inicioOcorrencia;
        this.inicioManutencao = inicioManutencao;
        this.fimManutencao = fimManutencao;
    }

    public Ferramenta getFerramenta() {
        return ferramenta;
    }

    // metodo para obter a lista de ferramentas disponíveis
    public boolean setFerramenta() {
        List<Ferramenta> ferramentas = Ferramenta.getFerramentas();

        // Exibe as ferramentas disponíveis
        System.out.println("\nFerramentas disponíveis:");
        for (Ferramenta ferramenta : ferramentas) {
            if (ferramenta.getStatus() == Ferramenta.Status.DISPONIVEL) {
                System.out.println("\nID: " + ferramenta.getId() + " - " + ferramenta.getNomeFerramenta());
            }
        }

        System.out.println("\nDigite o ID da ferramenta que deseja cadastrar a manutenção:");
        int idFerramenta;

        // Loop para validar o ID da ferramenta
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Digite um número válido.");
                scanner.next();
            }

            idFerramenta = scanner.nextInt();
            scanner.nextLine();

            // Verifica se o ID da ferramenta existe
            for (Ferramenta ferramenta : ferramentas) {
                if (ferramenta.getId() == idFerramenta) {
                    this.ferramenta = ferramenta;
                    return true;
                }
            }
            System.out.println("ID não encontrado. Digite um ID válido:");
        } while (true);
    }

    // Métodos motivo da manutenção
    public void setMotivo() {
        do {
            System.out.println("Digite o motivo da manutenção:");
            this.motivo = scanner.nextLine();

            if (this.motivo.isEmpty()) {
                System.out.println("O motivo não pode estar vazio. Digite novamente.");
            }
        } while (this.motivo.isEmpty());
    }

    public String getMotivo() {
        return motivo;
    }

    // Método tipo de serviço pelo código do enum
    public void setTipoServico() {
        System.out.println("Escolha o tipo de serviço:");
        System.out.println("1 - CORRETIVA");
        System.out.println("2 - PREVENTIVA");
        System.out.println("3 - MELHORIA");

        int opcao;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Digite um número válido.");
                scanner.next();
            }

            opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao < 1 || opcao > 3) {
                System.out.println("Digite um número entre 1 e 3.");
            }
        } while (opcao < 1 || opcao > 3);

        this.tipoServico = TipoServico.getByCodigo(opcao);
    }

    public TipoServico getTipoServico() {
        return tipoServico;
    }

    // Método funcionamento da ferramenta
    public void setFuncionamento() {
        System.out.println("A ferramenta está funcionando?");
        System.out.println("1 - Sim");
        System.out.println("2 - Não");

        int opcao;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Digite um número válido.");
                scanner.next();
            }

            opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao < 1 || opcao > 2) {
                System.out.println("Digite um número entre 1 e 2.");
            }
        } while (opcao < 1 || opcao > 2);

        this.funcionamento = Funcionamento.getByCodigo(opcao);
    }

    public Funcionamento getFuncionamento() {
        return funcionamento;
    }

    // Método descrição da manutenção a realizar
    public void setDescricaoARealizar() {
        do {
            System.out.println("Digite a descrição da manutenção a realizar:");
            this.descricaoARealizar = scanner.nextLine();

            if (this.descricaoARealizar.isEmpty()) {
                System.out.println("A descrição não pode estar vazia. Digite novamente.");
            }
        } while (this.descricaoARealizar.isEmpty());
    }

    public String getDescricaoARealizar() {
        return descricaoARealizar;
    }

    // Método descrição da manutenção realizada
    public void setDescricaoRealizada() {
        System.out.println("Digite a descrição da manutenção realizada (ou pressione ENTER para pular):");
        this.descricaoRealizada = scanner.nextLine();
    }

    public String getDescricaoRealizada() {
        return descricaoRealizada;
    }

    // Método data de início da ocorrência
    public void setDataInicio() {
        while (true) {
            try {
                System.out.println("Digite a data de início da ocorrência (dd/MM/yyyy):");
                String data = scanner.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate localDate = LocalDate.parse(data, formatter);
                this.inicioOcorrencia = localDate.atStartOfDay();
                System.out.println("Data registrada: " + localDate.format(formatter));
                break;
            } catch (DateTimeException e) {
                System.out.println("Data inválida! Digite novamente.");
            }
        }
    }

    public LocalDateTime getDataInicio() {
        return inicioOcorrencia;
    }

    // Método data de início da manutenção
    public void setDataInicioManutencao() {
        while (true) {
            try {
                System.out.println("Digite a data de início da manutenção (dd/MM/yyyy):");
                String data = scanner.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate localDate = LocalDate.parse(data, formatter);
                this.inicioManutencao = localDate.atStartOfDay();
                System.out.println("Data registrada: " + localDate.format(formatter));
                break;
            } catch (DateTimeException e) {
                System.out.println("Data inválida! Digite novamente.");
            }
        }
    }

    public LocalDateTime getDataInicioManutencao() {
        return inicioManutencao;
    }

    // Método data de fim da manutenção
    public void setDataFimManutencao() {
        System.out.println("Digite a data do fim da manutenção (dd/MM/yyyy) ou pressione ENTER para pular:");
        String dataHora = scanner.nextLine();

        if (!dataHora.isEmpty()) {
            while (true) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate localDate = LocalDate.parse(dataHora, formatter);
                    this.fimManutencao = localDate.atStartOfDay();
                    System.out.println("Data registrada: " + localDate.format(formatter));
                    break;
                } catch (DateTimeException e) {
                    System.out.println("Data inválida! Digite novamente:");
                    dataHora = scanner.nextLine();
                }
            }
        }
    }

    public LocalDateTime getDataFimManutencao() {
        return fimManutencao;
    }

    // Método para cadastrar a manutenção
    public static void cadastrarManutencao() {
        List<Ferramenta> ferramentas = Ferramenta.getFerramentas();
        // Primeiro verifica se existem ferramentas cadastradas
        if (ferramentas.isEmpty()) {
            System.out.println("Não há ferramentas cadastradas.");
            return;
        }

        // Verifica se existe alguma ferramenta disponível
        boolean temFerramentaDisponivel = false;
        for (Ferramenta ferramenta : ferramentas) {
            if (ferramenta.getStatus() == Ferramenta.Status.DISPONIVEL) {
                temFerramentaDisponivel = true;
                break;
            }
        }

        if (!temFerramentaDisponivel) {
            System.out.println("Não há ferramentas disponíveis para manutenção no momento.");
            return;
        }

        Manutencao manutencao = new Manutencao(null, null, null, null, null, null, null, null, null);

        if (!manutencao.setFerramenta()) {
            return;
        }

        manutencao.setMotivo();
        manutencao.setTipoServico();
        manutencao.setFuncionamento();
        manutencao.setDescricaoARealizar();
        manutencao.setDescricaoRealizada();
        manutencao.setDataInicio();
        manutencao.setDataInicioManutencao();
        manutencao.setDataFimManutencao();

        // Adiciona a manutenção à lista de manutenções
        manutencoes.add(manutencao);

        // Altera o status da ferramenta para EM_MANUTENCAO
        manutencao.getFerramenta().setStatusAutomatico(Ferramenta.Status.EM_MANUTENCAO);
 

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Manutenção cadastrada com sucesso!" +
                "\nID: " + manutencao.getId() +
                "\nMotivo: " + manutencao.getMotivo() +
                "\nTipo de Serviço: " + manutencao.getTipoServico().getDescricao() +
                "\nFuncionamento: " + manutencao.getFuncionamento().getDescricao() +
                "\nDescrição a Realizar: " + manutencao.getDescricaoARealizar() +
                "\nDescrição Realizada: "
                + (manutencao.getDescricaoRealizada() != null ? manutencao.getDescricaoRealizada() : "Não registrada") +
                "\nData Início: " + manutencao.getDataInicio().format(formatter) +
                "\nData Início Manutenção: " + manutencao.getDataInicioManutencao().format(formatter) +
                "\nData Fim Manutenção: "
                + (manutencao.getDataFimManutencao() != null ? manutencao.getDataFimManutencao().format(formatter) : "Em andamento"));
    }

    // Método para listar todas as manutenções cadastradas
    public static void listarManutencoes() {
        if (manutencoes.isEmpty()) {
            System.out.println("Não existem manutenções cadastradas!");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("\nLista de Manutenções:");

        for (Manutencao manutencao : manutencoes) {
            System.out.println(
                    "ID: " + manutencao.getId() +
                            "\nFerramenta: " + manutencao.getFerramenta().getNomeFerramenta() +
                            "\nMotivo: " + manutencao.getMotivo() +
                            "\nTipo de Serviço: " + manutencao.getTipoServico().getDescricao() +
                            "\nFuncionamento: " + manutencao.getFuncionamento().getDescricao() +
                            "\nDescrição a Realizar: " + manutencao.getDescricaoARealizar() +
                            "\nDescrição Realizada: "
                            + (manutencao.getDescricaoRealizada() != null ? manutencao.getDescricaoRealizada()
                                    : "Não registrada")
                            +
                            "\nData Início: " + manutencao.getDataInicio().format(formatter) +
                            "\nData Início Manutenção: "
                            + (manutencao.getDataInicioManutencao() != null
                                    ? manutencao.getDataInicioManutencao().format(formatter)
                                    : "Não registrada")
                            +
                            "\nData Fim Manutenção: "
                            + (manutencao.getDataFimManutencao() != null
                                    ? manutencao.getDataFimManutencao().format(formatter)
                                    : "Em andamento")
                            +
                            "\n");
        }
    }

    // Método para listar as manutenções
    public static List<Manutencao> getManutencoes() {
        return manutencoes;
    }
}
