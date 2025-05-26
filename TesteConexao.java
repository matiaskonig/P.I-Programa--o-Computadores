import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TesteConexao {
    public static void main(String[] args) {
        try {
            // Carregar o driver JDBC do PostgreSQL
            Class.forName("org.postgresql.Driver");
            
            Connection conexao = DatabaseConnection.getConnection();
            
            // Verificações mais detalhadas
            if (conexao != null) {
                System.out.println("Conexão estabelecida com sucesso!");
                
                // Informações sobre a conexão
                System.out.println("URL do Banco: " + conexao.getMetaData().getURL());
                System.out.println("Usuário: " + conexao.getMetaData().getUserName());
                System.out.println("Produto do Banco de Dados: " + conexao.getMetaData().getDatabaseProductName());
                System.out.println("Versão do Banco de Dados: " + conexao.getMetaData().getDatabaseProductVersion());
                
                // Teste de consulta simples
                try (Statement stmt = conexao.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT current_date")) {
                    if (rs.next()) {
                        System.out.println("Data atual do banco: " + rs.getDate(1));
                    }
                }
                
                conexao.close();
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC não encontrado:");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erro de conexão:");
            System.err.println("Código do erro: " + e.getErrorCode());
            System.err.println("Estado SQL: " + e.getSQLState());
            System.err.println("Mensagem: " + e.getMessage());
            e.printStackTrace();
        }
    }
}