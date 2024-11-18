import React, { useEffect, useState } from 'react';
import { obtenerAlumnos, crearAlumno, editarAlumno, eliminarAlumno } from './services/alumnoService';

interface Alumno {
  id: number;
  nombre: string;
}

const App: React.FC = () => {
  const [alumnos, setAlumnos] = useState<Alumno[]>([]);
  const [nombre, setNombre] = useState<string>('');

  useEffect(() => {
    obtenerAlumnos().then(data => setAlumnos(data)).catch(error => console.error(error));
  }, []);

  const handleCrearAlumno = async () => {
    const nuevoAlumno = { nombre };
    try {
      const alumnoCreado = await crearAlumno(nuevoAlumno);
      setAlumnos(prevAlumnos => [...prevAlumnos, alumnoCreado]);
      setNombre('');
    } catch (error) {
      console.error('Error creando el alumno', error);
    }
  };

  const handleEliminarAlumno = async (id: number) => {
    try {
      await eliminarAlumno(id);
      setAlumnos(prevAlumnos => prevAlumnos.filter(alumno => alumno.id !== id));
    } catch (error) {
      console.error('Error eliminando el alumno', error);
    }
  };

  return (
    <div>
      <h1>Lista de Alumnos</h1>
      
      <div>
        <input
          type="text"
          value={nombre}
          onChange={(e) => setNombre(e.target.value)}
          placeholder="Nombre del alumno"
        />
        <button onClick={handleCrearAlumno} disabled={!nombre}>Crear Alumno</button>
      </div>

      <ul>
        {alumnos.map((alumno) => (
          <li key={alumno.id}>
            {alumno.nombre}
            <button onClick={() => handleEliminarAlumno(alumno.id)}>Eliminar</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default App;