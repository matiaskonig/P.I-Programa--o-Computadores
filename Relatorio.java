import java.util.List;

public class Relatorio {

    public static void ferramentasEmprestadas() {
        List<ControleFerramenta> controles = ControleFerramenta.getControleFerramentas(); // Crie esse getter se não
                                                                                          // existir
        System.out.println("\nFerramentas emprestadas:");

        boolean encontrou = false;
        for (ControleFerramenta controle : controles) {
            for (Ferramenta f : controle.getFerramentas()) {
                if (f.getStatus() == Ferramenta.Status.EM_USO) {
                    System.out.println(
                            "ID: " + f.getId() +
                                    " - Nome: " + f.getNomeFerramenta() +
                                    " | Data Início: " + controle.getDataInicioEmprestimo() +
                                    " | Data Fim: " + controle.getDataFimEmprestimo() +
                                    " | Colaborador: " + controle.getColaborador().getNomeColaborador());
                    encontrou = true;
                }
            }
        }
        if (!encontrou) {
            System.out.println("Nenhuma ferramenta emprestada.");
        }
    }

    // Relatório de ferramentas disponíveis
    public static void ferramentasDisponiveis() {
        List<Ferramenta> ferramentas = Ferramenta.getFerramentas();
        System.out.println("\nFerramentas disponíveis:");
        boolean encontrou = false;
        for (Ferramenta f : ferramentas) {
            if (f.getStatus() == Ferramenta.Status.DISPONIVEL) {
                System.out.println("ID: " + f.getId() + " - " + f.getNomeFerramenta());
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhuma ferramenta disponível.");
        }
    }

    // Relatório de ferramentas indisponíveis
    public static void ferramentasIndisponiveis() {
        List<Ferramenta> ferramentas = Ferramenta.getFerramentas();
        System.out.println("\nFerramentas indisponíveis:");
        boolean encontrou = false;
        for (Ferramenta f : ferramentas) {
            if (f.getStatus() == Ferramenta.Status.INDISPONIVEL) {
                System.out.println("ID: " + f.getId() + " - " + f.getNomeFerramenta());
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhuma ferramenta indisponível.");
        }
    }

    // Relatório de ferramentas em manutenção
    public static void ferramentasManutencao() {
        List<Ferramenta> ferramentas = Ferramenta.getFerramentas();
        System.out.println("\nFerramentas em manutenção:");
        boolean encontrou = false;
        for (Ferramenta f : ferramentas) {
            if (f.getStatus() == Ferramenta.Status.EM_MANUTENCAO) {
                System.out.println("ID: " + f.getId() + " - " + f.getNomeFerramenta());
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhuma ferramenta em manutenção.");
        }
    }
}