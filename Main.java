import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bem-vindo ao sistema de gerenciamento de ferramentas!");
        // Objeto scanner para leitura de dados do usuário
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

                        // Validação da entrada numero inteiro
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
                
                case 3: {
                    // Submenu Alterar
                    int opcaoAlterar;
                    do {
                        System.out.println("\nMenu de Alterar");
                        System.out.println("1. Alterar Ferramenta");
                        System.out.println("2. Alterar Colaborador");
                        System.out.println("3. Alterar Manutenção");
                        System.out.println("4. Alterar Categoria de Ferramenta");
                        System.out.println("5. Voltar ao Menu Principal");

                        while (!scanner.hasNextInt()) {
                            System.out.println("Digite um número válido.");
                            scanner.nextLine();
                        }
                        opcaoAlterar = scanner.nextInt();
                        scanner.nextLine();

                        switch (opcaoAlterar) {
                            case 1:
                                Ferramenta.alterarFerramenta();
                                break;
                            case 2:
                                Colaborador.alterarColaborador();
                                break;
                            case 3:
                                Manutencao.alterarManutencao();
                                break;
                            case 4:
                                CategoriaFerramenta.alterarCategoriaFerramenta();
                                break;
                            case 5:
                                System.out.println("Voltando ao menu principal...");
                                break;
                            default:
                                System.out.println("Opção inválida!");
                                break;
                        }
                    } while (opcaoAlterar != 5);
                    break;
                }

            case 4: {
                    // Submenu Excluir
                    int opcaoExcluir;
                    do {
                        System.out.println("\nMenu de Excluir");
                        System.out.println("1. Excluir Ferramenta");
                        System.out.println("2. Excluir Colaborador");
                        System.out.println("3. Excluir Categoria de Ferramenta");
                        System.out.println("4. Excluir Empréstimo");
                        System.out.println("5. Voltar ao Menu Principal");

                        while (!scanner.hasNextInt()) {
                            System.out.println("Digite um número válido.");
                            scanner.nextLine();
                        }
                        opcaoExcluir = scanner.nextInt();
                        scanner.nextLine();

                        switch (opcaoExcluir) {
                            case 1:
                                Ferramenta.excluirFerramenta();
                                break;
                            case 2:
                                Colaborador.excluirColaborador();
                                break;
                            case 3:
                                CategoriaFerramenta.excluirCategoriaFerramenta();
                                break;
                            case 4:
                                // ControleFerramenta.excluirEmprestimo();
                                break;
                            case 5:
                                System.out.println("Voltando ao menu principal...");
                                break;
                            default:
                                System.out.println("Opção inválida!");
                                break;
                        }
                    } while (opcaoExcluir != 5);
                    break;
                }

                case 5: {
                    // Submenu Empréstimo
                    int opcaoEmprestimo;
                    do {
                        System.out.println("\nMenu de Empréstimo");
                        System.out.println("1. Realizar Empréstimo de Ferramenta");
                        System.out.println("2. Devolver Ferramenta");
                        System.out.println("3. Voltar ao Menu Principal");

                        while (!scanner.hasNextInt()) {
                            System.out.println("Digite um número válido.");
                            scanner.nextLine();
                        }
                        opcaoEmprestimo = scanner.nextInt();
                        scanner.nextLine();

                        switch (opcaoEmprestimo) {
                            case 1:
                                ControleFerramenta.realizarEmprestimo();
                                break;
                            case 2:
                                ControleFerramenta.devolverFerramenta();
                                break;
                            case 3:
                                System.out.println("Voltando ao menu principal...");
                                break;
                            default:
                                System.out.println("Opção inválida!");
                                break;
                        }
                    } while (opcaoEmprestimo != 3);
                    break;
                }   
            }
        } while (opcao != 7);

        scanner.close();
        System.out.println("Sistema encerrado.");
    }
}
}
