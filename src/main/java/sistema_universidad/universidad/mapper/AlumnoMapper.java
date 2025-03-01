package sistema_universidad.universidad.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sistema_universidad.universidad.dto.AlumnoDTO;
import sistema_universidad.universidad.model.Alumno;
import sistema_universidad.universidad.model.Carrera;

@Mapper(componentModel = "spring")
public interface AlumnoMapper {

    @Mapping(target = "carrera", source = "carrera.nombre")  // Solo mapear el nombre de la carrera
    AlumnoDTO alumnoToAlumnoDTO(Alumno alumno);

    @Mapping(target = "carrera", expression = "java(mapNombreACarrera(alumnoDTO.getCarrera()))")  // Conversión a carrera a partir del nombre
    Alumno alumnoDTOToAlumno(AlumnoDTO alumnoDTO);

    default Carrera mapNombreACarrera(String nombreCarrera) {
        if (nombreCarrera == null) {
            return null;
        }
        // Deberías resolver esto en tu servicio
        return null;  // Aquí no es necesario realizar la búsqueda, ya que el servicio debería manejar la lógica
    }
}