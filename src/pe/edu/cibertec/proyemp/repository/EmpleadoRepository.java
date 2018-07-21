package pe.edu.cibertec.proyemp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.cibertec.proyemp.model.Empleado;

@Repository
public interface EmpleadoRepository
	extends JpaRepository<Empleado, Long>{

	
	@Query("select e from Empleado e where e.nombre like %:nom%")
	List<Empleado> obtenerEmpleadosPorNombre(
			@Param("nom") String nombre);

	
	@Query("select e.departamento.nombre, count(e.nombre) "
			+ " from Empleado e group by e.departamento.nombre")
	List<Object[]> obtenerCantidadEmpleadosXDep();
	
}
