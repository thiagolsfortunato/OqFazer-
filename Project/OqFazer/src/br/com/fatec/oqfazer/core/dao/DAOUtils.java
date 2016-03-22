package br.com.fatec.oqfazer.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class DAOUtils {

	public static String completarClausulaValues(Class driverClass, int qtdParametros,
			String sequence) {
		String classname = driverClass.getName();
		StringBuilder clause = new StringBuilder();
		switch (classname) {
			case "org.hsqldb.jdbcDriver":
			case "org.postgresql.Driver":
				clause.append("nextval('" + sequence + "'),");
				break;
			case "oracle.jdbc.driver.OracleDriver":
				clause.append(sequence + ".nextVal,");
				break;
			case "com.mysql.jdbc.Driver":
				// do nothing
				break;
		}

		clause.append(StringUtils.repeat("?", ", ", qtdParametros));

		return "(" + clause.toString() + ")";
	}

	public static String getColunas(Class dbClass, List<String> columnNames) {
		String classname = dbClass.getName();
		String columns = null;
		switch (classname) {
			case "org.hsqldb.jdbcDriver":
			case "org.postgresql.Driver":
			case "oracle.jdbc.driver.OracleDriver":
				columns = StringUtils.join(columnNames, ", ");
				break;
			case "com.mysql.jdbc.Driver":
				columnNames = columnNames.subList(1, columnNames.size());
				columns = StringUtils.join(columnNames, ", ");
				break;
		}
		return "(" + columns + ")";
	}

	public static PreparedStatement criarStatment(String sql, Connection conn,
			Class dbClass, String[] columns) throws SQLException {
		String classname = dbClass.getName();
		switch (classname) {
			case "org.postgresql.Driver":
			case "com.mysql.jdbc.Driver":
				return conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			case "org.hsqldb.jdbcDriver":
			case "oracle.jdbc.driver.OracleDriver":
				return conn.prepareStatement(sql, columns);
		}
		return null;
	}

	public static String preparePlaceHolders(int length) {
		return StringUtils.join(Collections.nCopies(length, "?"), ",");
	}

	public static void setValues(PreparedStatement preparedStatement, List<Long> values)
			throws SQLException {
		for (int i = 0; i < values.size(); i++) {
			preparedStatement.setObject(i + 1, values.get(i));
		}
	}

}
