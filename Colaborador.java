import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

// Classe que representa um Colaborador no sistema
public class Colaborador extends IdAutomatico {
    private String nomeColaborador;
    private Funcao funcao;
    private Status status;

    // Lista estática para armazenar todos os colaboradores
    private static List<Colaborador> colaboradores = new ArrayList<>();
    // Objeto Scanner para leitura de dados do usuário
    private static Scanner scanner = new Scanner(System.in);

    // Enum que define as funções de um colaborador
    public enum Funcao {
        MONTADOR(1, "MONTADOR"),
        MARCENEIRO(2, "MARCENEIRO"),
        PROJETISTA(3, "PROJETISTA"),
        PINTOR(4, "PINTOR");

        private final int codigo;
        private final String descricao;

        // Construtor do enum
        Funcao(int codigo, String descricao) {
            this.codigo = codigo;
            this.descricao = descricao;
        }

        public int getCodigo() {
            return codigo;
        }

        public String getDescricao() {
            return descricao;
        }

        // Método para obter uma função pelo código
        public static Funcao getByCodigo(int codigo) {
            for (Funcao funcao : values()) {
                if (funcao.getCodigo() == codigo) {
                    return funcao;
                }
            }
            throw new IllegalArgumentException("Código de função inválido: " + codigo);
        }
    }

    // Enum que define status de um colaborador
    public enum Status {
        ATIVO(1, "ATIVO"),
        INATIVO(2, "INATIVO");

        private final int codigo;
        private final String descricao;

        Status(int codigo, String descricao) {
            this.codigo = codigo;
            this.descricao = descricao;
        }

        public int getCodigo() {
            return codigo;
        }

        public String getDescricao() {
            return descricao;
        }

        public static Status getByCodigo(int codigo) {
            for (Status status : values()) {
                if (status.getCodigo() == codigo) {
                    return status;
                }
            }
            throw new IllegalArgumentException("Código de status inválido: " + codigo);
        }
    }

    // Construtor da classe Colaborador
    public Colaborador(String nomeColaborador, Funcao funcao, Status status) {
        super(); // Chama construtor da classe pai (IdAutomatico)
        this.nomeColaborador = nomeColaborador;
        this.funcao = funcao;
        this.status = status;
    }

    // Método para definir o nomeColaborador com validação
    public void setNomeColaborador(String nomeColaborador) {
        do {
            System.out.println("Digite o nome do colaborador:");
            nomeColaborador = scanner.nextLine().trim().toUpperCase();

            if (nomeColaborador.isEmpty() || nomeColaborador.length() < 3) {
                System.out.println("O nome do colaborador deve ter pelo menos 3 caracteres.");
            } else {
                this.nomeColaborador = nomeColaborador;
                break;
            }
        } while (true);
    }

    public String getNomeColaborador() {
        return nomeColaborador;
    }

    // Método para definir a função com validação
    public void setFuncao(Funcao funcao) {
        System.out.println("Digite a função do colaborador:");
        System.out.println("1 - MONTADOR");
        System.out.println("2 - MARCENEIRO");
        System.out.println("3 - PROJETISTA");
        System.out.println("4 - PINTOR");

        int funcaoCodigo;
        do {
            // Verifica se o input é um número inteiro
            while (!scanner.hasNextInt()) {
                System.out.println("Digite um número válido.");
                scanner.next();
            }
            funcaoCodigo = scanner.nextInt();
            scanner.nextLine(); // limpa o buffer

            if (funcaoCodigo < 1 || funcaoCodigo > 4) {
                System.out.println("Digite um número entre 1 e 4!");
            }
        } while (funcaoCodigo < 1 || funcaoCodigo > 4);

        this.funcao = Funcao.getByCodigo(funcaoCodigo);
    }

    public Funcao getFuncao() {
        return funcao;
    }

    // Método para definir o status com validação
    public void setStatus(Status status) {
        System.out.println("Digite o status do colaborador:");
        System.out.println("1 - ATIVO");
        System.out.println("2 - INATIVO");

        int statusCodigo;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Digite um número válido.");
                scanner.next();
            }
            statusCodigo = scanner.nextInt();
            scanner.nextLine(); // limpa o buffer
            if (statusCodigo < 1 || statusCodigo > 2) {
                System.out.println("Digite um número entre 1 e 2!");
            }
        } while (statusCodigo < 1 || statusCodigo > 2);
        // Define o status com base no código fornecido
        this.status = Status.getByCodigo(statusCodigo);
    }

    public Status getStatus() {
        return status;
    }

    // Método para cadastrar um novo colaborador
    public static void cadastrarColaborador() {
        Colaborador colaborador = new Colaborador(null, null, null);
        colaborador.setNomeColaborador(null);
        colaborador.setFuncao(null);
        colaborador.setStatus(null);

        // Adiciona o colaborador à lista de colaboradores
        colaboradores.add(colaborador);
        // Exibe mensagem de sucesso
        System.out.println("Colaborador cadastrado com sucesso!" +
                "\nID: " + colaborador.getId() +
                "\nNome: " + colaborador.nomeColaborador +
                "\nFunção: " + colaborador.getFuncao() +
                "\nStatus: " + colaborador.getStatus() +
                "\n");
    }

    // Método para listar todos os colaboradores
    public static void listarColaboradores() {

        if (colaboradores.isEmpty()) {
            System.out.println("Não existem colaboradores cadastrados!");
            return;
        }
        System.out.println("Lista de Colaboradores:");
        for (Colaborador colaborador : colaboradores) {
            System.out.println(
                    "ID: " + colaborador.getId() +
                            "\nNome: " + colaborador.getNomeColaborador() +
                            "\nFunção: " + colaborador.getFuncao() +
                            "\nStatus: " + colaborador.getStatus() +
                            "\n");
        }
    }

    // Método para buscar um colaborador pelo ID
    public static List<Colaborador> getColaboradores() {
        return colaboradores;
    }

    public static void excluirColaborador() {
        if (colaboradores.isEmpty()) {
            System.out.println("Não existem colaboradores cadastrados!");
            return;
        }

        int idColaborador;

        System.out.println("Digite o ID do colaborador a ser excluído:");
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Digite um número válido.");
                scanner.next();
            }

            idColaborador = scanner.nextInt();
            scanner.nextLine();

            Colaborador colaboradorParaExcluir = null;
            for (Colaborador c : colaboradores) {
                if (c.getId() == idColaborador) {
                    colaboradorParaExcluir = c;
                    break;
                }

                if(colaboradorParaExcluir != null) {
                    colaboradores.remove(colaboradorParaExcluir);
                    System.out.println("Colaborador excluído com sucesso!");
                }
                else{
                    System.out.println("ID não encontrado.");
                }
            }
        } while (true);
    }
    
    // Método para alterar os dados de um colaborador
    public static void alterarColaborador() {
        if (colaboradores.isEmpty()) {
            System.out.println("Não existem colaboradores cadastrados!");
            return;
        }

        int idColaborador;

        System.out.println("Digite o ID do colaborador a ser alterado:");
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Digite um número válido.");
                scanner.next();
            }

            idColaborador = scanner.nextInt();
            scanner.nextLine();

            Colaborador colaboradorParaAlterar = null;
            for (Colaborador c : colaboradores) {
                if (c.getId() == idColaborador) {
                    colaboradorParaAlterar = c;
                    break;
                }
            }

            if (colaboradorParaAlterar != null) {
                System.out.println("Digite o novo nome do colaborador:");
                String novoNome = scanner.nextLine().trim().toUpperCase();
                colaboradorParaAlterar.setNomeColaborador(novoNome);
                colaboradorParaAlterar.setFuncao(null);
                colaboradorParaAlterar.setStatus(null);
                System.out.println("Colaborador alterado com sucesso!");
                return;
            } else {
                System.out.println("ID não encontrado.");
            }
        } while (true);
    }

}
