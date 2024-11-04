package utils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAOImpl implements VeiculoDAO {
	 private final Connection conexao;

	    public VeiculoDAOImpl(Connection conexao) {
	        this.conexao = conexao;
	    }

	    @Override
	    public void adicionarVeiculo(Veiculo veiculo) throws SQLException {
	        String sql = "INSERT INTO veiculos (placa, marca, cor, tipo) VALUES (?, ?, ?, ?)";
	        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	            stmt.setString(1, veiculo.getPlaca());
	            stmt.setString(2, veiculo.getMarca());
	            stmt.setString(3, veiculo.getCor());
	            stmt.setString(4, veiculo.getTipo());
	            stmt.executeUpdate();
	        }
	    }

	    @Override
	    public Veiculo buscarVeiculo(String placa) throws SQLException {
	        String sql = "SELECT * FROM veiculos WHERE placa = ?";
	        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	            stmt.setString(1, placa);
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                String marca = rs.getString("marca");
	                String cor = rs.getString("cor");
	                String tipo = rs.getString("tipo");
	                return tipo.equals("Nacional") ? new VeiculoNacional(placa, marca, cor) : new VeiculoImportado(placa, marca, cor);
	            }
	        }
	        return null;
	    }

	    @Override
	    public List<Veiculo> listarVeiculos() throws SQLException {
	        List<Veiculo> veiculos = new ArrayList<>();
	        String sql = "SELECT * FROM veiculos";
	        try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
	            while (rs.next()) {
	                String placa = rs.getString("placa");
	                String marca = rs.getString("marca");
	                String cor = rs.getString("cor");
	                String tipo = rs.getString("tipo");
	                Veiculo veiculo = tipo.equals("Nacional") ? new VeiculoNacional(placa, marca, cor) : new VeiculoImportado(placa, marca, cor);
	                veiculos.add(veiculo);
	            }
	        }
	        return veiculos;
	    }

	    @Override
	    public void atualizarVeiculo(String placa, Veiculo veiculo) throws SQLException {
	        String sql = "UPDATE veiculos SET marca = ?, cor = ? WHERE placa = ?";
	        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	            stmt.setString(1, veiculo.getMarca());
	            stmt.setString(2, veiculo.getCor());
	            stmt.setString(3, placa);
	            stmt.executeUpdate();
	        }
	    }

	    @Override
	    public void removerVeiculo(String placa) throws SQLException {
	        String sql = "DELETE FROM veiculos WHERE placa = ?";
	        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
	            stmt.setString(1, placa);
	            stmt.executeUpdate();
	        }
	    }
}
