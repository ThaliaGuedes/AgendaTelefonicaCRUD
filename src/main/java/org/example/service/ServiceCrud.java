package org.example.service;

import java.sql.*;
import java.util.Scanner;

public class ServiceCrud {

    public static void criarTabelaContatos(Connection connection) throws SQLException {

        String sql = "CREATE TABLE IF NOT EXISTS contatos (" +
                "id SERIAL PRIMARY KEY," +
                "nome VARCHAR(255) NOT NULL," +
                "telefone VARCHAR(15) NOT NULL" +
                ")";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public static void adicionarContato(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Digite o nome do contato:");
        String nome = scanner.nextLine();

        System.out.println("Digite o telefone do contato:");
        String telefone = scanner.nextLine();

        String sql = "INSERT INTO contatos (nome, telefone) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, telefone);
            preparedStatement.executeUpdate();
        }

        System.out.println("Contato adicionado com sucesso.");
    }

    public static void listarContatos(Connection connection) throws SQLException {
        String sql = "SELECT id, nome, telefone FROM contatos";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            System.out.println("Lista de contatos:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");
                System.out.println("ID: " + id + ", Nome: " + nome + ", Telefone: " + telefone);
            }
        }
    }

    public static void atualizarContato(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Digite o ID do contato que deseja atualizar:");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite o novo nome:");
        String novoNome = scanner.nextLine();

        System.out.println("Digite o novo telefone:");
        String novoTelefone = scanner.nextLine();

        String sql = "UPDATE contatos SET nome = ?, telefone = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, novoNome);
            preparedStatement.setString(2, novoTelefone);
            preparedStatement.setInt(3, id);
            int rowsAtualizadas = preparedStatement.executeUpdate();
            if (rowsAtualizadas > 0) {
                System.out.println("Contato atualizado com sucesso.");
            } else {
                System.out.println("Contato não encontrado.");
            }
        }
    }

    public static void excluirContato(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Digite o ID do contato que deseja excluir:");
        int id = scanner.nextInt();
        scanner.nextLine();

        String sql = "DELETE FROM contatos WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int rowsExcluidas = preparedStatement.executeUpdate();
            if (rowsExcluidas > 0) {
                System.out.println("Contato excluído com sucesso.");
            } else {
                System.out.println("Contato não encontrado.");
            }
        }
    }
}
