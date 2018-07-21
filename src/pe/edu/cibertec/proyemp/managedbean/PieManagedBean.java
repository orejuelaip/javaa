package pe.edu.cibertec.proyemp.managedbean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.primefaces.model.chart.PieChartModel;

import pe.edu.cibertec.proyemp.model.Departamento;
import pe.edu.cibertec.proyemp.model.Empleado;
import pe.edu.cibertec.proyemp.service.EmpleadoService;

@ManagedBean
public class PieManagedBean implements Serializable{
	
	private PieChartModel pieModel;
	
	@ManagedProperty(value="#{empleadoService}")
	private EmpleadoService empleadoService;
	
	@PostConstruct
	public void init(){
		
		construirPie2();
		
	}

	private void construirPie2() {
		
		pieModel = new PieChartModel();
		
		List<Object[]> data = 
				empleadoService
				.getEmpleadoRepository()
				.obtenerCantidadEmpleadosXDep();
		
		for (Object[] row : data) {
			String nombre = row[0].toString();
			Integer cantidad = new Integer(row[1].toString());
			pieModel.set(nombre, cantidad);
		}
		
		 pieModel.setTitle("Empleados x Departamento");
		 pieModel.setLegendPosition("w");
		 pieModel.setShowDataLabels(true);
	}

	private void construirPie() {
		
		List<Empleado> empleados = 
				empleadoService
				.getEmpleadoRepository().findAll();
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		for (Empleado empleado : empleados) {
			Departamento departamento = empleado.getDepartamento();
			String nombreDepartamento = departamento.getNombre();
			
			Integer cantidad = map.get(nombreDepartamento);
			
			if(cantidad == null){
				map.put(nombreDepartamento, 1);
			}else{
				map.put(nombreDepartamento, cantidad + 1);
			}
			//map.put(nombreDepartamento, cantidad == null ? new Integer(1) : ++cantidad);
		}
		
		pieModel = new PieChartModel();
		
		Set<String> nombres = map.keySet();
		
		for (String nomDepartamento : nombres) {
			pieModel.set(nomDepartamento, 
					map.get(nomDepartamento));
		}
		
		 pieModel.setTitle("Empleados x Departamento");
		 pieModel.setLegendPosition("w");
		 pieModel.setShowDataLabels(true);
	}

	public PieChartModel getPieModel() {
		return pieModel;
	}

	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}

	public EmpleadoService getEmpleadoService() {
		return empleadoService;
	}

	public void setEmpleadoService(EmpleadoService empleadoService) {
		this.empleadoService = empleadoService;
	}
	
	

}
