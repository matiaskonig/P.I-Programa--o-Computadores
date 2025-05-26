import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bem-vindo ao sistema de gerenciamento de ferramentas!");
        Scanner scanner = new Scanner(System.in);
        int opcao;
        
        do {
            // Menu principal
            System.out.println("\nMenu Principal");
            System.out.println("1. Cadastrar");
            System.out.println("2. Consultar"); 
            System.out.println("3. Alterar");
            System.out.println("4. Excluir");
            System.out.println("5. Realizar Empréstimo");
            System.out.println("6. Relatórios");
            System.out.println("7. Sair");

            // Validação da entrada
            do {
                while (!scanner.hasNextInt()) {
                    System.out.println("Digite um número válido.");
                    scanner.nextLine();
                }
                opcao = scanner.nextInt();
                scanner.nextLine();
                
                if (opcao < 1 || opcao > 7) {
                    System.out.println("Digite um número entre 1 e 7!");
                }
            } while (opcao < 1 || opcao > 7);

            switch (opcao) {
                case 1: {
                    // Submenu Cadastro
                    int opcaoCadastro;
                    do {
                        System.out.println("\nMenu de Cadastro");
                        System.out.println("1. Cadastrar Ferramenta");
                        System.out.println("2. Cadastrar Colaborador");
                        System.out.println("3. Cadastrar Categoria de Ferramenta");
                        System.out.println("4. Cadastrar Manutenção");
                        System.out.println("5. Cadastrar Controle de Ferramenta"); //a fazer
                        System.out.println("6. Voltar ao Menu Principal");

                        while (!scanner.hasNextInt()) {
                            System.out.println("Digite um número válido.");
                            scanner.nextLine();
                        }
                        opcaoCadastro = scanner.nextInt();
                        scanner.nextLine();

                        switch (opcaoCadastro) {
                            case 1:
                                Ferramenta.cadastrarFerramenta();
                                break;
                            case 2:
                                Colaborador.cadastrarColaborador();
                                break;
                            case 3:
                                CategoriaFerramenta.cadastrarCategoriaFerramenta();
                                break;
                            case 4:
                                Manutencao.cadastrarManutencao();
                                break;
                            case 5:
                                // ControleFerramenta.cadastrarControleFerramenta(); 
                            case 6:
                                System.out.println("Voltando ao menu principal...");
                                break;
                            default:
                                System.out.println("Opção inválida!");
                                break;
                        }
                    } while (opcaoCadastro != 6);
                    break;
                }
                case 2: {
                    // Submenu Consultar
                    int opcaoConsultar;
                    do {
                        System.out.println("\nMenu de Consultar");
                        System.out.println("1. Consultar Ferramenta");
                        System.out.println("2. Consultar Colaborador");
                        System.out.println("3. Consultar Manutenção");
                        System.out.println("4. Consultar Categoria de Ferramenta");
                        System.out.println("5. Consultar Controle de Ferramenta"); //A fazer
                        System.out.println("6. Voltar ao Menu Principal");

                        while (!scanner.hasNextInt()) {
                            System.out.println("Digite um número válido.");
                            scanner.nextLine();
                        }
                        opcaoConsultar = scanner.nextInt();
                        scanner.nextLine();

                        switch (opcaoConsultar) {
                            case 1:
                                Ferramenta.listarFerramentas();
                                break;
                            case 2:
                                Colaborador.listarColaboradores();
                                break;
                            case 3:
                                Manutencao.listarManutencoes();
                                break;
                            case 4:
                                CategoriaFerramenta.listarCategoriaFerramentas();
                                break;
                            case 5:
                                // ControleFerramenta.listarControleFerramentas();
                            case 6:
                                System.out.println("Voltando ao menu principal...");
                                break;
                            default:
                                System.out.println("Opção inválida!");
                                break;
                        }
                    } while (opcaoConsultar != 6);
                    break;
                }
            }
        } while (opcao != 7);

        scanner.close();
        System.out.println("Sistema encerrado.");
    }
}
