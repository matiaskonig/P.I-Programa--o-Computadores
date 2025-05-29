import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Ferramenta extends IdAutomatico {
    // Atributos da classe
    private String nomeFerramenta;
    private String descricao;
    private Status status;
    private CategoriaFerramenta categoriaFerramenta;
    private static Scanner scanner = new Scanner(System.in);
    private static List<Ferramenta> ferramentas = new ArrayList<>();

    // Enum de Status
    public enum Status {
        DISPONIVEL(1, "DISPONIVEL"),
        EM_MANUTENCAO(2, "EM MANUTENCAO"),
        EM_USO(3, "EM USO"),
        INDISPONIVEL(4, "INDISPONIVEL");

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

    // Construtor
    public Ferramenta(String nomeFerramenta, String descricao, Status status, CategoriaFerramenta categoriaFerramenta) {
        super();
        this.nomeFerramenta = nomeFerramenta;
        this.descricao = descricao;
        this.status = status;
        this.categoriaFerramenta = categoriaFerramenta;
    }

    /// Método para definir o nome da ferramenta com validação
    public void setNomeFerramenta(String nomeFerramenta) {
        do {
            System.out.println("Digite o nome da ferramenta:");
            nomeFerramenta = scanner.nextLine().trim().toUpperCase();

            if (nomeFerramenta.isEmpty() || nomeFerramenta.length() < 3) {
                System.out.println("O nome da ferramenta deve ter pelo menos 3 caracteres.");
            } else {
                this.nomeFerramenta = nomeFerramenta;
                break;
            }
        } while (true);
    }

    public String getNomeFerramenta() {
        return nomeFerramenta;
    }

    // Método para definir a descrição com validação
    public void setDescricao(String descricao) {
        do {
            System.out.println("Digite a descrição da ferramenta:");
            descricao = scanner.nextLine();

            if (descricao.isEmpty()) {
                System.out.println("A descrição não pode ser vazia.");
            } else {
                this.descricao = descricao;
                break;
            }
        } while (true);
    }

    public String getDescricao() {
        return descricao;
    }

    // Método para definir o status com validação
    public void setStatus(Status status) {
        System.out.println("Escolha o status da ferramenta:");
        System.out.println("1 - DISPONÍVEL");
        System.out.println("2 - MANUTENÇÃO");
        System.out.println("3 - EM USO");
        System.out.println("4 - INDISPONÍVEL");

        int statusCodigo;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Digite um número válido.");
                scanner.next();
            }
            statusCodigo = scanner.nextInt();
            scanner.nextLine();

            if (statusCodigo < 1 || statusCodigo > 4) {
                System.out.println("Digite um número entre 1 e 4!");
            }
        } while (statusCodigo < 1 || statusCodigo > 4);

        this.status = Status.getByCodigo(statusCodigo);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatusAutomatico(Status status) {
        if (status == null) {
            throw new IllegalArgumentException("Status não pode ser nulo.");
        }
        this.status = status;
    }

    // Método para definir o tipo de ferramenta com validação
    public boolean setCategoriaFerramenta() {
        List<CategoriaFerramenta> categorias = CategoriaFerramenta.getCategoriaFerramentas();
        if (categorias.isEmpty()) {
            System.out.println("\nNão existem categorias de ferramentas cadastradas!" +
                    "\nCrie uma categoria de ferramenta antes de cadastrar uma ferramenta.");
            return false;
        }

        System.out.println("\nCategorias de ferramentas disponíveis:");
        for (CategoriaFerramenta categoria : categorias) {
            System.out.println("ID: " + categoria.getId() + " - Categoria: " + categoria.getCategoriaFerramenta());
        }

        System.out.println("\nDigite o ID da categoria de ferramenta:");
        while (true) {
            try {
                int idCategoria = scanner.nextInt();
                scanner.nextLine();
                for (CategoriaFerramenta categoria : categorias) {
                    if (categoria.getId() == idCategoria) {
                        this.categoriaFerramenta = categoria;
                        return true;
                    }
                }
                System.out.println("ID não encontrado. Digite um ID válido:");
            } catch (Exception e) {
                System.out.println("Digite um número válido!");
                scanner.nextLine();
            }
        }
    }

    // Método para cadastrar uma ferramenta
    public static void cadastrarFerramenta() {

        // Verificar se existem tipos de ferramentas cadastrados
        if (CategoriaFerramenta.getCategoriaFerramentas().isEmpty()) {
            System.out.println("\nNão existem categorias de ferramentas cadastrados!" +
                    "\nCrie uma categoria de ferramenta antes de cadastrar uma ferramenta.");
            return;
        }

        Ferramenta ferramenta = new Ferramenta(null, null, null, null);

        if (!ferramenta.setCategoriaFerramenta()) {
            return;
        }

        ferramenta.setNomeFerramenta(null);
        ferramenta.setDescricao(null);
        ferramenta.setStatus(null);

        // Adicionar a ferramenta à lista de ferramentas
        ferramentas.add(ferramenta);

        System.out.println("Ferramenta cadastrada com sucesso!" +
                "\nID: " + ferramenta.getId() +
                "\nFerramenta: " + ferramenta.getNomeFerramenta() +
                "\nCategoria de Ferramenta: " + ferramenta.getCategoriaFerramenta());
    }

    // Getter para TipoFerramenta
    public CategoriaFerramenta getCategoriaFerramenta() {
        return categoriaFerramenta;
    }

    // Método para listar todas as ferramentas
    public static void listarFerramentas() {
        if (ferramentas.isEmpty()) {
            System.out.println("Não existem ferramentas cadastradas!");
            return;
        }

        System.out.println("\nLista de Ferramentas:");
        for (Ferramenta ferramenta : ferramentas) {
            System.out.println(
                    "ID: " + ferramenta.getId() +
                            "\nNome: " + ferramenta.getNomeFerramenta() +
                            "\nDescrição: " + ferramenta.getDescricao() +
                            "\nCategoria de Ferramenta: " + ferramenta.getCategoriaFerramenta().getCategoriaFerramenta()
                            +
                            "\nStatus: " + ferramenta.getStatus().getDescricao() +
                            "\n");
        }
    }

    // Método para buscar uma ferramenta por ID
    public static List<Ferramenta> getFerramentas() {
        return ferramentas;
    }
}