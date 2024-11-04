package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/carros", "root", "jonas123");
             Scanner scanner = new Scanner(System.in)) {

            VeiculoDAO veiculoDAO = new VeiculoDAOImpl(conexao);

            while (true) {
                System.out.println("\nEscolha uma operação:");
                System.out.println("1. Adicionar veículo");
                System.out.println("2. Buscar veículo por placa");
                System.out.println("3. Listar todos os veículos");
                System.out.println("4. Atualizar veículo");
                System.out.println("5. Remover veículo");
                System.out.println("0. Sair");
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do scanner

                switch (opcao) {
                    case 1:
                        adicionarVeiculo(scanner, veiculoDAO);
                        break;
                    case 2:
                        buscarVeiculo(scanner, veiculoDAO);
                        break;
                    case 3:
                        listarVeiculos(veiculoDAO);
                        break;
                    case 4:
                        atualizarVeiculo(scanner, veiculoDAO);
                        break;
                    case 5:
                        removerVeiculo(scanner, veiculoDAO);
                        break;
                    case 0:
                        System.out.println("Encerrando o sistema.");
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void adicionarVeiculo(Scanner scanner, VeiculoDAO veiculoDAO) {
        System.out.println("Tipo (Nacional/Importado): ");
        String tipo = scanner.nextLine();
        System.out.println("Placa: ");
        String placa = scanner.nextLine();
        System.out.println("Marca: ");
        String marca = scanner.nextLine();
        System.out.println("Cor: ");
        String cor = scanner.nextLine();

        try {
            Veiculo veiculo = tipo.equalsIgnoreCase("Nacional") ? new VeiculoNacional(placa, marca, cor) : new VeiculoImportado(placa, marca, cor);
            veiculoDAO.adicionarVeiculo(veiculo);
            System.out.println("Veículo adicionado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao adicionar veículo: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro no banco de dados ao adicionar veículo.");
            e.printStackTrace();
        }
    }

    private static void buscarVeiculo(Scanner scanner, VeiculoDAO veiculoDAO) {
        System.out.println("Digite a placa do veículo: ");
        String placa = scanner.nextLine();
        try {
            Veiculo veiculo = veiculoDAO.buscarVeiculo(placa);
            if (veiculo != null) {
                System.out.println("Marca: " + veiculo.getMarca() + ", Cor: " + veiculo.getCor() + ", Tipo: " + veiculo.getTipo());
            } else {
                System.out.println("Veículo não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar veículo.");
            e.printStackTrace();
        }
    }

    private static void listarVeiculos(VeiculoDAO veiculoDAO) {
        try {
            List<Veiculo> veiculos = veiculoDAO.listarVeiculos();
            if (veiculos.isEmpty()) {
                System.out.println("Nenhum veículo encontrado.");
            } else {
                System.out.println("\nLista de Veículos:");
                for (Veiculo veiculo : veiculos) {
                    System.out.println("Placa: " + veiculo.getPlaca() + ", Marca: " + veiculo.getMarca() +
                            ", Cor: " + veiculo.getCor() + ", Tipo: " + veiculo.getTipo());
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar veículos.");
            e.printStackTrace();
        }
    }

    private static void atualizarVeiculo(Scanner scanner, VeiculoDAO veiculoDAO) {
        System.out.println("Digite a placa do veículo que deseja atualizar: ");
        String placa = scanner.nextLine();
        try {
            Veiculo veiculoExistente = veiculoDAO.buscarVeiculo(placa);
            if (veiculoExistente != null) {
                System.out.println("Digite a nova marca: ");
                String novaMarca = scanner.nextLine();
                System.out.println("Digite a nova cor: ");
                String novaCor = scanner.nextLine();

                veiculoExistente.setMarca(novaMarca);
                veiculoExistente.setCor(novaCor);
                veiculoDAO.atualizarVeiculo(novaCor, veiculoExistente);
                System.out.println("Veículo atualizado com sucesso!");
            } else {
                System.out.println("Veículo com a placa informada não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar veículo.");
            e.printStackTrace();
        }
    }

    private static void removerVeiculo(Scanner scanner, VeiculoDAO veiculoDAO) {
        System.out.println("Digite a placa do veículo que deseja remover: ");
        String placa = scanner.nextLine();
        try {
            Veiculo veiculo = veiculoDAO.buscarVeiculo(placa);
            if (veiculo != null) {
                veiculoDAO.removerVeiculo(placa);
                System.out.println("Veículo removido com sucesso!");
            } else {
                System.out.println("Veículo não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao remover veículo.");
            e.printStackTrace();
        }
    }
}
