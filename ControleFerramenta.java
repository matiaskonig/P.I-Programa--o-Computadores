import java.util.Scanner;
import java.util.stream.Collectors;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ControleFerramenta extends IdAutomatico {

    private LocalDate dataInicioEmprestimo;
    private LocalDate dataFimEmprestimo;
    private List<Ferramenta> ferramentas; // Alterado para Lista
    private Colaborador colaborador;
    private static List<Ferramenta> listaFerramentas = new ArrayList<>();
    private static List<Colaborador> colaboradores = new ArrayList<>();
    private static List<ControleFerramenta> controleFerramentas = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    // Construtor
    public ControleFerramenta(LocalDate dataInicioEmprestimo, LocalDate dataFimEmprestimo) {
        super();
        this.dataInicioEmprestimo = dataInicioEmprestimo;
        this.dataFimEmprestimo = dataFimEmprestimo;
        this.ferramentas = new ArrayList<>(); // Inicializa a lista de ferramentas
    }

    // Getters e Setters
    public void setDataInicioEmprestimo() {
        while (true) {
            try {
                System.out.println("Digite a data de início do empréstimo (dd/MM/yyyy):");
                String data = scanner.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate localDate = LocalDate.parse(data, formatter);
                this.dataInicioEmprestimo = localDate;
                System.out.println("Data registrada: " + localDate.format(formatter));
                break;
            } catch (DateTimeException e) {
                System.out.println("Data inválida! Digite novamente.");
            }
        }
    }

    public LocalDate getDataInicioEmprestimo() {
        return dataInicioEmprestimo;
    }

    public void setDataFimEmprestimo() {
        System.out.println("Digite a data do fim da manutenção (dd/MM/yyyy) ou pressione ENTER para pular:");
        String dataHora = scanner.nextLine();
        if (!dataHora.isEmpty()) {
            while (true) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate localDate = LocalDate.parse(dataHora, formatter);
                    this.dataFimEmprestimo = localDate;
                    System.out.println("Data registrada: " + localDate.format(formatter));
                    break;
                } catch (DateTimeException e) {
                    System.out.println("Data inválida! Digite novamente:");
                    dataHora = scanner.nextLine();
                }
            }
        }
    }

    public LocalDate getDataFimEmprestimo() {
        return dataFimEmprestimo;
    }

    public List<Ferramenta> getFerramentas() {
        return ferramentas;
    }

    public void setFerramentas(List<Ferramenta> ferramentas) {
        this.ferramentas = ferramentas;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    // Método para selecionar múltiplas ferramentas
    public boolean setFerramenta() {
        List<Ferramenta> ferramentasDisponiveis = Ferramenta.getFerramentas();
        this.ferramentas = new ArrayList<>();

        // Exibe as ferramentas disponíveis
        System.out.println("\nFerramentas disponíveis:");
        boolean temFerramentaDisponivel = false;
        for (Ferramenta f : ferramentasDisponiveis) {
            if (f.getStatus() == Ferramenta.Status.DISPONIVEL) {
                System.out.println("ID: " + f.getId() + " - " + f.getNomeFerramenta());
                temFerramentaDisponivel = true;
            }
        }

        if (!temFerramentaDisponivel) {
            System.out.println("Nenhuma ferramenta disponível.");
            return false;
        }

        System.out.println("\nDigite o ID das ferramentas que deseja cadastrar na locação (0 para finalizar):");
        while (true) {
            if (!scanner.hasNextInt()) {
                System.out.println("Digite um número válido.");
                scanner.next();
                continue;
            }

            int idFerramenta = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            if (idFerramenta == 0) {
                if (this.ferramentas.isEmpty()) {
                    System.out.println("Nenhuma ferramenta selecionada. Pelo menos uma ferramenta deve ser escolhida.");
                    continue;
                }
                break; // Finaliza a seleção
            }

            boolean ferramentaEncontrada = false;
            for (Ferramenta f : ferramentasDisponiveis) {
                if (f.getId() == idFerramenta && f.getStatus() == Ferramenta.Status.DISPONIVEL) {
                    this.ferramentas.add(f);
                    ferramentaEncontrada = true;
                    System.out.println("Ferramenta " + f.getNomeFerramenta() + " adicionada.");
                    break;
                }
            }

            if (!ferramentaEncontrada) {
                System.out.println("ID inválido ou ferramenta não disponível. Digite outro ID ou 0 para finalizar:");
            }
        }

        return !this.ferramentas.isEmpty();
    }

    // Método para definir o colaborador (sem alterações)
    public boolean setColaborador() {
        List<Colaborador> colaboradores = Colaborador.getColaboradores();

        System.out.println("\nColaboradores ativos:");
        boolean temColaboradorDisponivel = false;
        for (Colaborador c : colaboradores) {
            if (c.getStatus() == Colaborador.Status.ATIVO) {
                System.out.println("ID: " + c.getId() + " - " + c.getNomeColaborador());
                temColaboradorDisponivel = true;
            }
        }

        if (!temColaboradorDisponivel) {
            System.out.println("Nenhum colaborador ativo cadastrado.");
            return false;
        }

        System.out.println("Digite o ID do colaborador:");
        while (true) {
            if (!scanner.hasNextInt()) {
                System.out.println("Digite um número válido.");
                scanner.next();
                continue;
            }

            int idColaborador = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            for (Colaborador c : colaboradores) {
                if (c.getId() == idColaborador && c.getStatus() == Colaborador.Status.ATIVO) {
                    this.colaborador = c;
                    return true;
                }
            }

            System.out.println("ID não encontrado ou colaborador não está ativo.");
        }
    }

    public static void cadastrarControleFerramenta() {

        if (Ferramenta.getFerramentas().isEmpty()) {
            System.out.println("Não há ferramentas cadastradas.");
            return;
        }

        if (Colaborador.getColaboradores().isEmpty()) {
            System.out.println("Não há colaboradores cadastrados.");
            return;
        }

        // Verifica ferramentas disponíveis
        List<Ferramenta> ferramentasDisponiveis = Ferramenta.getFerramentas().stream()
                .filter(f -> f.getStatus() == Ferramenta.Status.DISPONIVEL)
                .collect(Collectors.toList());

        if (ferramentasDisponiveis.isEmpty()) {
            System.out.println("Não há ferramentas disponíveis no momento.");
            return;
        }

        // Cria novo controle
        ControleFerramenta controle = new ControleFerramenta(null, null);

        // Configura datas
        controle.setDataInicioEmprestimo();
        controle.setDataFimEmprestimo();

        // Seleção de ferramentas
        System.out.println("\nSelecione as ferramentas para o empréstimo:");
        while (true) {
            System.out.println("\nFerramentas disponíveis:");
            ferramentasDisponiveis.forEach(f -> System.out.println("ID: " + f.getId() + " - " + f.getNomeFerramenta()));

            System.out.println("\nDigite o ID da ferramenta:");
            int idFerramenta;
            while (true) {
                if (!scanner.hasNextInt()) {
                    System.out.println("Digite um número válido.");
                    scanner.next();
                    continue;
                }
                idFerramenta = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer
                break;
            }

            Optional<Ferramenta> ferramentaSelecionada = ferramentasDisponiveis.stream()
                    .filter(f -> f.getId() == idFerramenta)
                    .findFirst();

            if (ferramentaSelecionada.isPresent()) {
                controle.getFerramentas().add(ferramentaSelecionada.get());
                System.out.println("Ferramenta adicionada: " + ferramentaSelecionada.get().getNomeFerramenta());
                ferramentasDisponiveis.remove(ferramentaSelecionada.get());

                // Perguntar se o usuário deseja adicionar mais
                System.out.println("Deseja adicionar outra ferramenta? (S/N)");
                String resposta = scanner.nextLine().trim().toUpperCase();

                while (!resposta.equals("S") && !resposta.equals("N")) {
                    System.out.println("Resposta inválida. Digite S para Sim ou N para Não:");
                    resposta = scanner.nextLine().trim().toUpperCase();
                }

                if (resposta.equals("N")) {
                    break;
                }

            } else {
                System.out.println("ID inválido ou ferramenta não disponível!");
            }
        }

        if (controle.getFerramentas().isEmpty()) {
            System.out.println("Nenhuma ferramenta selecionada. Operação cancelada.");
            return;
        }

        // Seleção de colaborador
        if (!controle.setColaborador()) {
            System.out.println("Operação cancelada.");
            return;
        }

        // Atualiza status das ferramentas
        controle.getFerramentas().forEach(f -> f.setStatusAutomatico(Ferramenta.Status.EM_USO));

        // Adiciona ao controle
        controleFerramentas.add(controle);

        // Exibe resumo
        System.out.println("\n=== EMPRÉSTIMO REGISTRADO COM SUCESSO ===");
        System.out.println("ID: " + controle.getId());
        System.out.println("Data Início: " + controle.getDataInicioEmprestimo());
        System.out.println("Data Fim: " + controle.getDataFimEmprestimo());
        System.out.println("Ferramentas: " + controle.getFerramentas().stream()
                .map(Ferramenta::getNomeFerramenta)
                .collect(Collectors.joining(", ")));
        System.out.println("Colaborador: " + controle.getColaborador().getNomeColaborador());

    }

    // Método para listar todos os controles de ferramentas
    public static void listarControlesFerramentas() {
        if (controleFerramentas.isEmpty()) {
            System.out.println("Não existem controles de ferramentas cadastrados!");
            return;
        }

        LocalDate dataFiltroLocalDate = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println(
                "Digite a data do empréstimo da ferramenta (dd/MM/yyyy): ");
        while (true) {
            String dataFiltro = scanner.nextLine().trim();

            try {
                dataFiltroLocalDate = LocalDate.parse(dataFiltro, formatter);
                break;
            } catch (DateTimeException e) {
                System.out.println(
                        "Data inválida! Digite novamente no formato dd/MM/yyyy: ");
            }
        }

        boolean encontrouControle = false;
        for (ControleFerramenta controle : controleFerramentas) {
            if (dataFiltroLocalDate == null || controle.getDataInicioEmprestimo().equals(dataFiltroLocalDate)) {
                encontrouControle = true;
                StringBuilder ferramentasNomes = new StringBuilder();
                for (Ferramenta f : controle.getFerramentas()) {
                    ferramentasNomes.append(f.getNomeFerramenta()).append(", ");
                }
                if (ferramentasNomes.length() > 0) {
                    ferramentasNomes.setLength(ferramentasNomes.length() - 2); // Remove a última vírgula
                }
                System.out.println("ID: " + controle.getId() +
                        "\nData Início: " + controle.getDataInicioEmprestimo() +
                        "\nData Fim: " + controle.getDataFimEmprestimo() +
                        "\nFerramentas: " + ferramentasNomes +
                        "\nColaborador: " + controle.getColaborador().getNomeColaborador());
            }
        }

        if (!encontrouControle) {
            System.out.println("Nenhum controle encontrado para a data informada.");
        }
    }

    // Método para alterar um controle de ferramenta
    public static void alterarControleFerramenta() {
        if (controleFerramentas.isEmpty()) {
            System.out.println("Não existem controles de ferramentas cadastrados!");
            return;
        }

        listarControlesFerramentas();

        System.out.println("Digite o ID do controle de ferramenta a ser alterado: ");
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Digite um número válido.");
                scanner.next();
            }
            int idControle = scanner.nextInt();
            scanner.nextLine();

            ControleFerramenta controleParaAlterar = null;
            for (ControleFerramenta controle : controleFerramentas) {
                if (controle.getId() == idControle) {
                    controleParaAlterar = controle;
                    break;
                }
            }

            if (controleParaAlterar != null) {
                controleParaAlterar.setDataFimEmprestimo();
                System.out.println("Controle de ferramenta alterada com sucesso!");
                return;
            } else {
                System.out.println("ID não encontrado.");
            }
        } while (true);
    }

    // Método para excluir um controle de ferramenta
    public static void excluirControleFerramenta() {
        if (controleFerramentas.isEmpty()) {
            System.out.println("Não existem controles de ferramentas cadastrados!");
            return;
        }

        listarControlesFerramentas();

        System.out.println("Digite o ID do controle de ferramenta a ser excluído: ");
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Digite um número válido.");
                scanner.next();
            }
            int idControle = scanner.nextInt();
            scanner.nextLine();

            for (ControleFerramenta controle : controleFerramentas) {
                if (controle.getId() == idControle) {
                    for (Ferramenta f : controle.getFerramentas()) {
                        f.setStatusAutomatico(Ferramenta.Status.DISPONIVEL);
                    }
                    controleFerramentas.remove(controle);
                    System.out.println("Controle de ferramenta excluído com sucesso!");
                    return;
                }
            }
            System.out.println("ID não encontrado.");
        } while (true);
    }
}