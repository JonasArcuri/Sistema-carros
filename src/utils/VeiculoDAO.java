package utils;
import java.sql.SQLException;
import java.util.List;

public interface VeiculoDAO {
	void adicionarVeiculo(Veiculo veiculo) throws SQLException;
    Veiculo buscarVeiculo(String placa) throws SQLException;
    List<Veiculo> listarVeiculos() throws SQLException;
    void atualizarVeiculo(String placa, Veiculo veiculo) throws SQLException;
    void removerVeiculo(String placa) throws SQLException;
}
