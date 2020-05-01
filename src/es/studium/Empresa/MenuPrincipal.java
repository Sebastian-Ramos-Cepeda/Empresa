package es.studium.Empresa;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MenuPrincipal extends Frame implements WindowListener, ActionListener
{

	private static final long serialVersionUID = 1L;
	MenuBar menuBar = new MenuBar();
	Menu empleados = new Menu("Empleados");
	MenuItem nuevoEmpleado = new MenuItem("Nuevo");
	MenuItem consultaEmpleado = new MenuItem("Consulta");
	MenuItem eliminarEmpleado = new MenuItem("Eliminar");
	MenuItem modificarEmpleado = new MenuItem("Modificar");

	public MenuPrincipal() 
	{
		setTitle("Empresa");
		setLayout(new FlowLayout());
		
		nuevoEmpleado.addActionListener(this);
		consultaEmpleado.addActionListener(this);
		eliminarEmpleado.addActionListener(this);
		modificarEmpleado.addActionListener(this);
		
		empleados.add(nuevoEmpleado);
		empleados.add(consultaEmpleado);
		empleados.add(eliminarEmpleado);
		empleados.add(modificarEmpleado);
		
		menuBar.add(empleados);
		
		setMenuBar(menuBar);
		addWindowListener(this);
		setSize(400,300);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) 
	{
		new MenuPrincipal();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		Object pulsado = arg0.getSource();
		if(pulsado.equals(consultaEmpleado)) 
		{
			new ConsultaEmpleado();
		}
		else if (pulsado.equals(nuevoEmpleado)) 
		{
			new NuevoEmpleado();
		}
		else if (pulsado.equals(eliminarEmpleado)) 
		{
			new BajaEmpleado();
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
		System.exit(0);
		
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
