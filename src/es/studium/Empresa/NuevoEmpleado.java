package es.studium.Empresa;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;

public class NuevoEmpleado extends Frame implements WindowListener, ActionListener
{

	private static final long serialVersionUID = 1L;
	Label lblDepartamento = new Label ("Departamento");
	Label lblExtension = new Label ("Extension");
	Label lblFechaNacimiento = new Label ("Fecha nacimiento");
	Label lblFechaIngreso = new Label ("Fecha ingreso");
	Label lblSalario = new Label ("Salario");
	Label lblCominison= new Label ("Comision");
	Label lblHijos = new Label ("Hijos");
	Label lblNombre = new Label ("Nombre");
	TextField txtDepartamento = new TextField(10);
	TextField txtExtension = new TextField(10);
	TextField txtFechaNacimiento = new TextField(10);
	TextField txtFechaIngreso = new TextField(10);
	TextField txtSalario = new TextField(10);
	TextField txtCominison = new TextField(10);
	TextField txtHijos = new TextField(10);
	TextField txtNombre = new TextField(10);
	Button btnAceptar = new Button("Aceptar");
	Button btnLimpiar = new Button("Limpiar");
	BaseDatos bd = new BaseDatos();
	Connection conexion = null;
	Dialog dlgMensaje = new Dialog(this, "Mensaje", true);
	Label mensaje = new Label("");
	
	public NuevoEmpleado() 
	{
		setTitle("Nuevo empleado");
		setLayout(new GridLayout(9,2));
		add(lblDepartamento);
		add(txtDepartamento);
		add(lblExtension);
		add(txtExtension);
		add(lblFechaNacimiento);
		add(txtFechaNacimiento);
		add(lblFechaIngreso);
		add(txtFechaIngreso);
		add(lblSalario);
		add(txtSalario);
		add(lblCominison);
		add(txtCominison);
		add(lblHijos);
		add(txtHijos);
		add(lblNombre);
		add(txtNombre);
		btnAceptar.addActionListener(this);
		btnLimpiar.addActionListener(this);
		add(btnAceptar);
		add(btnLimpiar);		
		addWindowListener(this);
		setSize(400,300);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		if(btnLimpiar.equals(arg0.getSource()))
		{
			txtDepartamento.selectAll();
			txtDepartamento.setText("");
			txtExtension.selectAll();
			txtExtension.setText("");
			txtFechaNacimiento.selectAll();
			txtFechaNacimiento.setText("");
			txtFechaIngreso.selectAll();
			txtFechaIngreso.setText("");
			txtSalario.selectAll();
			txtSalario.setText("");
			txtCominison.selectAll();
			txtCominison.setText("");
			txtHijos.selectAll();
			txtHijos.setText("");
			txtNombre.selectAll();
			txtNombre.setText("");
		}
		else
		{
			//Conectar BD
			conexion = bd.conectar();
			//Hacer INSERT
			String[] fechaAmericana = txtFechaNacimiento.getText().split("/");
			String[] fechaAmericana2 = txtFechaIngreso.getText().split("/");
			String sentencia = "INSERT INTO empleados VALUES(null,'"+txtDepartamento.getText()+"','"+txtExtension.getText()+"','"+fechaAmericana[2]+"-"+fechaAmericana[1]+"-"+fechaAmericana[0]+"','"+fechaAmericana2[2]+"-"+fechaAmericana2[1]+"-"+fechaAmericana2[0]+"','"+txtSalario.getText()+"','"+txtCominison.getText()+"','"+txtHijos.getText()+"','"+txtNombre.getText()+"')";
			System.out.println(sentencia);
			//Feedback
			if(bd.crearEmpleado(conexion, sentencia)==1)
			{
				//Todo bien
				mensaje.setText("Nuevo empleado añadido");
				dlgMensaje.setTitle("Feedback");
				dlgMensaje.setSize(40,140);
				dlgMensaje.setLayout(new FlowLayout());
				dlgMensaje.addWindowListener(this);
				dlgMensaje.add(mensaje);
				dlgMensaje.setLocationRelativeTo(null);
				dlgMensaje.setVisible(true);
				
			}
			else
			{
				// Error
				mensaje.setText("Error al añadir un nuevo empleado");
				dlgMensaje.setTitle("Feedback");
				dlgMensaje.setSize(40,140);
				dlgMensaje.setLayout(new FlowLayout());
				dlgMensaje.addWindowListener(this);
				dlgMensaje.add(mensaje);
				dlgMensaje.setLocationRelativeTo(null);
				dlgMensaje.setVisible(true);
				
			}
			bd.desconectar(conexion);
			//Desconectar
		}
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		
		if(dlgMensaje.isActive())
		{
			dlgMensaje.setVisible(false);
		}
		else
		{
			setVisible(false);
		}
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
