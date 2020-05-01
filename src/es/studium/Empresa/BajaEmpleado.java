package es.studium.Empresa;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;

public class BajaEmpleado extends Frame implements WindowListener, ActionListener
{
	private static final long serialVersionUID = 1L;
	Choice listado = new Choice();
	Button btnAceptar = new Button("Aceptar");
	Button btnLimpiar = new Button("Limpiar");
	BaseDatos bd = new BaseDatos();
	Connection conexion = null;
	Dialog dlgMensaje = new Dialog(this, "¿Estas seguro?", true);
	Label mensaje = new Label("¿Estas seguro/a de eliminar este empleado?");
	Button btnSi = new Button("Si");
	Button btnNo = new Button("No");
	String[] cadena;
	Dialog dlgMensajeFeedback = new Dialog(this, "Mensaje", true);
	Label mensajeFeedback = new Label("");
	
	public BajaEmpleado()
	{
		setTitle("¿Eliminar algun empledao?");
		setLayout(new FlowLayout());
		//Rellenar Choice
		listado.add("Seleccionar un cliente...");
		add(listado);
		//Conectar BD
		conexion = bd.conectar();
		cadena = bd.consultarEmpleadosChoice(conexion).split("#");
		for(int i = 0; i<cadena.length; i++)
		{
			listado.add(cadena[i]);
		}
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
	public void actionPerformed(ActionEvent e) 
	{
		int idEmpleadoBorrar = 0;
		if(btnLimpiar.equals(e.getSource()))
		{
			listado.select(0);
		}
		else if(btnAceptar.equals(e.getSource()))
		{
			String[] tabla = listado.getSelectedItem().split("-");
			idEmpleadoBorrar = Integer.parseInt(tabla[0]);
			dlgMensaje.setSize(40,140);
			dlgMensaje.setLayout(new FlowLayout());
			dlgMensaje.addWindowListener(this);
			dlgMensaje.add(mensaje);
			btnSi.addActionListener(this);
			btnNo.addActionListener(this);
			dlgMensaje.add(btnSi);
			dlgMensaje.add(btnNo);
			dlgMensaje.setLocationRelativeTo(null);
			dlgMensaje.setVisible(true);
			
		}
		else if ((btnSi.equals(e.getSource())))
		{
			//Conectar BD
			conexion = bd.conectar();
			//Ejecutar DELETE
			if(bd.borrarEmpleado(conexion,idEmpleadoBorrar)==0)
				{
					//Todo bien
					mensajeFeedback.setText("Baja empleado correcta");
					dlgMensajeFeedback.setTitle("Feedback");
					dlgMensajeFeedback.setSize(40,140);
					dlgMensajeFeedback.setLayout(new FlowLayout());
					dlgMensajeFeedback.addWindowListener(this);
					dlgMensajeFeedback.add(mensaje);
					dlgMensajeFeedback.setLocationRelativeTo(null);
					dlgMensajeFeedback.setVisible(true);
					
				}
				else
				{
					// Error
					mensajeFeedback.setText("Error al eliminar empleado");
					dlgMensajeFeedback.setTitle("Feedback");
					dlgMensajeFeedback.setSize(40,140);
					dlgMensajeFeedback.setLayout(new FlowLayout());
					dlgMensajeFeedback.addWindowListener(this);
					dlgMensajeFeedback.add(mensaje);
					dlgMensajeFeedback.setLocationRelativeTo(null);
					dlgMensajeFeedback.setVisible(true);
					
				}
			bd.desconectar(conexion);
		}
		else
		{
			dlgMensaje.setVisible(false);
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		
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
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
