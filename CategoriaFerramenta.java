import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class CategoriaFerramenta extends IdAutomatico {
    private String categoriaFerramenta;
    private static List<CategoriaFerramenta> categoriaFerramentas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    // Construtor da classe
    public CategoriaFerramenta(String categoriaFerramenta) {
        super();
        this.categoriaFerramenta = categoriaFerramenta;
    }

    public void setCategoriaFerramenta(String categoriaFerramenta) {
        do {
            System.out.println("Digite o nome da categoria: ");
            categoriaFerramenta = scanner.nextLine().trim().toUpperCase();

            if (categoriaFerramenta.isEmpty() || categoriaFerramenta.length() < 3) {
                System.out.println("O nome da categoria deve ter pelo menos 3 caracteres.");
            } else {
                this.categoriaFerramenta = categoriaFerramenta;
                break;
            }
        } while (true);
    }

    public String getCategoriaFerramenta() {
        return categoriaFerramenta;
    }

    // Método para cadastrar um novo tipo de ferramenta
    public static void cadastrarCategoriaFerramenta() {
        System.out.println("Digite a categoria de ferramenta: ");
        String novoTipo = scanner.nextLine().trim().toUpperCase();

        if (novoTipo.isEmpty() || novoTipo.length() < 3) {
            System.out.println("A categoria deve ter pelo menos 3 caracteres.");
            return;
        }

        CategoriaFerramenta categoriaFerramenta = new CategoriaFerramenta(novoTipo);
        categoriaFerramentas.add(categoriaFerramenta);
        System.out.println("Categoria de ferramenta cadastrada com sucesso!" +
                "\nID: " + categoriaFerramenta.getId() +
                "\nCategoria de Ferramenta: " + categoriaFerramenta.getCategoriaFerramenta());
    }

    // Método para listar os tipos de ferramentas
    public static void listarCategoriaFerramentas() {
        if (categoriaFerramentas.isEmpty()) {
            System.out.println("Não existem categorias de ferramentas cadastradas!");
            return;
        }
        System.out.println("Lista de Categorias de Ferramentas:");
        for (CategoriaFerramenta categoria : categoriaFerramentas) {
            System.out
                    .println("ID: " + categoria.getId() + " - Categoria: " + categoria.getCategoriaFerramenta() + "\n");
        }
    }

    // Método para excluir uma categoria de ferramenta
    public static void excluirCategoriaFerramenta() {
        if (categoriaFerramentas.isEmpty()) {
            System.out.println("Não existem categorias de ferramentas cadastradas!");
            return;
        }

        listarCategoriaFerramentas();
        int idCategoria;
        System.out.println("Digite o ID da categoria de ferramenta a ser excluída:");
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Digite um número válido.");
                scanner.next();
            }

            idCategoria = scanner.nextInt();
            scanner.nextLine();

            CategoriaFerramenta categoriaParaExcluir = null;
            for (CategoriaFerramenta c : categoriaFerramentas) {
                if (c.getId() == idCategoria) {
                    categoriaParaExcluir = c;
                    System.out.println(
                            "Categoria de ferramenta: " + c.getCategoriaFerramenta() + " excluída com sucesso!");
                    categoriaFerramentas.remove(c);

                    return;
                } else {
                    System.out.println("ID não encontrado.");
                }
            }
        } while (true);

    }

    // Método para alterar os dados de uma categoria de ferramenta
    public static void alterarCategoriaFerramenta() {
        if (categoriaFerramentas.isEmpty()) {
            System.out.println("Não existem categorias de ferramentas cadastradas!");
            return;
        }

        listarCategoriaFerramentas();
        int idCategoria;
        System.out.println("Digite o ID da categoria de ferramenta a ser alterada:");
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Digite um número válido.");
                scanner.next();
            }

            idCategoria = scanner.nextInt();
            scanner.nextLine();

            CategoriaFerramenta categoriaParaAlterar = null;
            for (CategoriaFerramenta categoria : categoriaFerramentas) {
                if (categoria.getId() == idCategoria) {
                    categoriaParaAlterar = categoria;
                    break;
                }
            }

            if (categoriaParaAlterar != null) {
                categoriaParaAlterar.setCategoriaFerramenta(null);
                System.out.println("Categoria de ferramenta alterada com sucesso!");
                return;
            } else {
                System.out.println("ID não encontrado.");
            }
        } while (true);
    }

    // Método para obter a lista de tipos de ferramentas
    public static List<CategoriaFerramenta> getCategoriaFerramentas() {
        return categoriaFerramentas;
    }

}
