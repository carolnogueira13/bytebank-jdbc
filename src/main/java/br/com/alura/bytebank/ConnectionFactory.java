package br.com.alura.bytebank;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection recuperarConexao(){
        try{
            return createDataSource().getConnection();


        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    /*
    Método para criar um pool de conexoes
    Atraves de um pool de conexoes, voce criar inicialmente um numero fixo de
    conexoes, nesse caso 10. E cada vez que abre uma nova conexao retirar a conexao do pool
    para usa-la e conforme fecha a conexao ela volta para o pool
     */
    private HikariDataSource createDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(System.getenv("DATASOURCE_URL"));
        config.setUsername(System.getenv("DATASOURCE_USERNAME"));
        config.setPassword(System.getenv("DATASOURCE_PASSWORD"));
        config.setMaximumPoolSize(10);

        return new HikariDataSource(config);
    }

}
