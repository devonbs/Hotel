package br.com.devon.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class ConexaoDAO {

	private static final String DRIVER = "org.postgresql.Driver";
	private static final String DBURL = "jdbc:postgresql://localhost:5432/Hotel";
	private static final String USER = "postgres";
	private static final String SENHA = "481516";
	private static Connection conn = null;

	static String connectionUrl = "jdbc:postgresql://localhost:5432/Hotel;user=postgres;password=481516";

	// inicio da conexão com banco
	public static Connection createConnection() {

		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(connectionUrl);
			System.out.println("Conexão OK!");

		} catch (ClassNotFoundException ex) {
			System.out.println("Problemas no Driver de Conexão");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return conn;
	}

	public void Close() {
		try {
			conn.close();
		} catch (SQLException ex) {
			Logger.getLogger(ConexaoDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
