package com.example.cristiam.mini_agendae;

/**
 * Creado por Cristiam
 */

public class SQLite {

    /*
     script que crea la tabla
     */
    public static String getCriateLivro(){
        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("CREATE TABLE IF NOT EXISTS TAREA( ");
        sqlBuilder.append(" ID         INTEGER      NOT NULL ");
        sqlBuilder.append("             PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append(" TITULO        VARCHAR(40), ");
        sqlBuilder.append(" DESCRIPCION       VARCHAR(30), ");
        sqlBuilder.append(" HORA       VARCHAR(30), ");
        sqlBuilder.append(" LUGAR        VARCHAR(30) ");
        sqlBuilder.append("); ");

        return sqlBuilder.toString();
    }

}
