import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class CategoriaFerramenta extends IdAutomatico {
    private String categoriaFerramenta;
    // cria uma lista para armazenar os tipos de ferramentas
    private static List<CategoriaFerramenta> categoriaFerramentas = new ArrayList<>();
    // cria um objeto scanner para leitura de dados do usuário
    private static Scanner scanner = new Scanner(System.in);

    // construtor da classe
    public CategoriaFerramenta(String categoriaFerramenta) {
        super(); // chama o construtor da classe IdAutomatico
        this.categoriaFerramenta = categoriaFerramenta;
    }

    // setter do tipo
    public void setCategoriaFerramenta(String categoriaFerramenta) {
        this.categoriaFerramenta = categoriaFerramenta;
    }

    public String getCategoriaFerramenta() {
        return categoriaFerramenta;
    }

    @Override
    public String toString() {
        return this.getCategoriaFerramenta();
    }

    // Método para cadastrar um novo tipo de ferramenta
    public static void cadastrarCategoriaFerramenta() {
        do {
            System.out.println("Digite a categoria de ferramenta:");
            String novoTipo = scanner.nextLine().trim().toUpperCase();

            if (novoTipo.isEmpty() || novoTipo.length() < 3) {
                System.out.println("A categoria deve ter pelo menos 3 caracteres.");
                continue;
            }

            CategoriaFerramenta categoriaFerramenta = new CategoriaFerramenta(novoTipo);
            categoriaFerramentas.add(categoriaFerramenta);
            System.out.println("Categoria de ferramenta cadastrado com sucesso!" +
                    "\nID: " + categoriaFerramenta.getId() +
                    "\nCategoria de Ferramenta: " + categoriaFerramenta.getCategoriaFerramenta());
            break;

        } while (true);
    }

    // Método para listar os tipos de ferramentas
    public static void listarCategoriaFerramentas() {
        if (categoriaFerramentas.isEmpty()) {
            System.out.println("Não existem categorias de ferramentas cadastradas!");
            return;
        }
        System.out.println("Lista de Categorias de Ferramentas:");
        for (CategoriaFerramenta categoria : categoriaFerramentas) {
            System.out.println("ID: " + categoria.getId() + " - Categoria: " + categoria.getCategoriaFerramenta() + "\n");
        }
    }

    // Método para obter a lista de tipos de ferramentas
    public static List<CategoriaFerramenta> getCategoriaFerramentas() {
        return categoriaFerramentas;
    }

}
