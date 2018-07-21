package pe.edu.cibertec.proyemp.managedbean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.google.common.collect.Lists;

import pe.edu.cibertec.proyemp.model.Departamento;
import pe.edu.cibertec.proyemp.service.DepartamentoService;

@ManagedBean
@SessionScoped
public class DepartamentoManagedBean {
	
	private Departamento departamento = new Departamento();
	
	private List<Departamento> departamentos 
			= new ArrayList<Departamento>();
	
	@ManagedProperty(value="#{departamentoService}")
	private DepartamentoService departamentoService;

	
	public String guardar(){
		departamentoService.getDepartamentoRepository().save(departamento);
		departamento = new Departamento();
		return null;
	}
	
	public DepartamentoService getDepartamentoService() {
		return departamentoService;
	}

	public void setDepartamentoService(DepartamentoService departamentoService) {
		this.departamentoService = departamentoService;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Departamento> getDepartamentos() {
		
		departamentos = Lists.newArrayList(
				departamentoService.
				getDepartamentoRepository().
				findAll());
		
//		Iterable<Departamento> itDepartamentos 
//		= departamentoService.getDepartamentoRepository().findAll();
//		
//		Iterator<Departamento> it = itDepartamentos.iterator();
//		
//		departamentos.clear();
//		
//		while(it.hasNext()){
//			Departamento dep = it.next();
//			departamentos.add(dep);
//		}
		
		
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}
	
	
	

}
