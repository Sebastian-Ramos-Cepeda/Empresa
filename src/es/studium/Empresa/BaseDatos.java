package es.studium.Empresa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;





public class BaseDatos 
{
	String driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/empresa?useSSL=false&serverTimezone=UTC";
	String login = "root";
	String password = "Studium2019";
	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	
			
	public Connection conectar() 
	{
		try 
		{
			Class.forName(driver);
			connection = DriverManager.getConnection(url, login, password);
		}
		catch (ClassNotFoundException cnfe)
		{
			System.out.println("Error 1-"+cnfe.getMessage());
		}
		catch(SQLException sqle) 
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return connection;
	}
	
	public String consultarEmpleados(Connection c) 
	{
		String resultado = "";
		String[] fechaEuropea;
		String[] fechaEuropea2;
		try 
		{
			String sentencia = "SELECT * FROM empleados";
			statement = c.createStatement();
			rs = statement.executeQuery(sentencia);
			while (rs.next())
			{
				fechaEuropea = rs.getString("fechaNacimientoEmpleado").split("-");
				fechaEuropea2 = rs.getString("fechaIngresoEmpleado").split("-");
				resultado = resultado + rs.getInt("idEmpleado") + " --- " +
						rs.getInt("idDepartamentoFK") + " --- " +
						rs.getInt("extensionEmpleado") + " --- " +
						fechaEuropea[2]+"\\"+fechaEuropea[1]+"\\"+fechaEuropea[0] + " --- " +
						fechaEuropea2[2]+"\\"+fechaEuropea2[1]+"\\"+fechaEuropea2[0] + " --- " +
						rs.getInt("salarioEmpleado") + " --- " +
						rs.getInt("comisionEmpleado") + " --- " +
						rs.getInt("hijosEmpleado") + " --- " +
						rs.getString("nombreEmpleado")+"\n"+"\n";
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}
	
	public int crearEmpleado(Connection c, String sentencia) 
	{
		int resultado = 1;
		try
		{
			statement = c.createStatement();
			if(statement.executeUpdate(sentencia)==0)
			{
				resultado = 0;
			}
			else
			{
				resultado = 1;
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}
	
	public String consultarEmpleadosChoice(Connection c) 
	{
		String resultado = "";
		String[] fechaEuropea;
		String[] fechaEuropea2;
		try 
		{
			String sentencia = "SELECT * FROM empleados";
			statement = c.createStatement();
			rs = statement.executeQuery(sentencia);
			while (rs.next())
			{
				fechaEuropea = rs.getString("fechaNacimientoEmpleado").split("-");
				fechaEuropea2 = rs.getString("fechaIngresoEmpleado").split("-");
				resultado = resultado + rs.getInt("idEmpleado") + " --- " +
						rs.getInt("idDepartamentoFK") + " --- " +
						rs.getInt("extensionEmpleado") + " --- " +
						fechaEuropea[2]+"\\"+fechaEuropea[1]+"\\"+fechaEuropea[0] + " --- " +
						fechaEuropea2[2]+"\\"+fechaEuropea2[1]+"\\"+fechaEuropea2[0] + " --- " +
						rs.getInt("salarioEmpleado") + " --- " +
						rs.getInt("comisionEmpleado") + " --- " +
						rs.getInt("hijosEmpleado") + " --- " +
						rs.getString("nombreEmpleado")+"#";
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}
	
	public int borrarEmpleado(Connection c, int idEmpleado)
	{
		int resultado;
		try 
		{
			String sentencia = "DELETE FROM empleados WHERE idEmpleado = "+ idEmpleado;
			statement = c.createStatement();
			if((statement.executeUpdate(sentencia))==1)
			{
				resultado = 0;
			}
			else
			{
				resultado = 1;
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return (resultado);
	}
	
	public void desconectar(Connection c) 
	{
		try
		{
			c.close();
		}
		catch (SQLException e)
		{
			System.out.println("Error 3-"+e.getMessage());
		}
	}

}
