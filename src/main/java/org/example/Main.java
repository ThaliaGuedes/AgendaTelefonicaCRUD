package org.example;

import org.example.connection.ConexaoComBanco;
import org.example.service.ServiceCrud;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) throws SQLException {
        Connection conexao = ConexaoComBanco.fazerConexao();
        ServiceCrud.criarTabelaContatos(conexao);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Escolha uma ação:");
            System.out.println("1. Adicionar contato");
            System.out.println("2. Listar contatos");
            System.out.println("3. Atualizar contato");
            System.out.println("4. Excluir contato");
            System.out.println("5. Sair");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (escolha) {
                    case 1:
                        ServiceCrud.adicionarContato(conexao, scanner);
                        break;
                    case 2:
                        ServiceCrud.listarContatos(conexao);
                        break;
                    case 3:
                        ServiceCrud.atualizarContato(conexao, scanner);
                        break;
                    case 4:
                        ServiceCrud.excluirContato(conexao, scanner);
                        break;
                    case 5:
                        System.out.println("Saindo da agenda telefônica.");
                        conexao.close();
                        System.exit(0);
                    default:
                        System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                        break;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
