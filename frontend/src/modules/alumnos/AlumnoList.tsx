// src/modules/alumnos/AlumnoList.tsx
import React, { useState, useEffect } from "react";
import { obtenerAlumnos } from "../../services/alumnoService";
import { Alumno } from "../../types/Alumno"; // Importa la interfaz Alumno
import {
  Table,
  TableBody,
  TableCaption,
  TableCell,
  TableFooter,
  TableHead,
  TableHeader,
  TableRow,
} from "../../components/ui/table";

const AlumnoList: React.FC = () => {
  const [alumnos, setAlumnos] = useState<Alumno[]>([]);
  const [search, setSearch] = useState<string>("");
  const [currentPage, setCurrentPage] = useState<number>(1);
  const [alumnosPerPage] = useState<number>(5);

  useEffect(() => {
    obtenerAlumnos()
      .then((data) => setAlumnos(data))
      .catch((error) => console.error(error));
  }, []);

  // Filtra los alumnos según el texto de búsqueda
  const filteredAlumnos = alumnos.filter((alumno) =>
    alumno.nombre.toLowerCase().includes(search.toLowerCase()) ||
    alumno.dni.includes(search)
  );

  // Obtiene los alumnos a mostrar en la página actual
  const indexOfLastAlumno = currentPage * alumnosPerPage;
  const indexOfFirstAlumno = indexOfLastAlumno - alumnosPerPage;
  const currentAlumnos = filteredAlumnos.slice(indexOfFirstAlumno, indexOfLastAlumno);

  // Cambia la página cuando el usuario hace clic en un número
  const paginate = (pageNumber: number) => setCurrentPage(pageNumber);

  const handleEliminar = (id: number) => {
    console.log(`Eliminar alumno con ID: ${id}`);
    setAlumnos(alumnos.filter((alumno) => alumno.id !== id));
  };

  return (
    <div className="overflow-x-auto p-4 w-9/12 mx-auto">
      <h2 className="text-2xl font-semibold mb-4">Lista de Alumnos</h2>

      {/* Buscador */}
      <input
        type="text"
        placeholder="Buscar por nombre o DNI"
        className="mb-4 p-2 border border-gray-300 rounded-md w-full"
        value={search}
        onChange={(e) => setSearch(e.target.value)}
      />

      <Table aria-label="Lista de Alumnos" className="min-w-full table-auto border-collapse">
        <TableCaption>Lista de alumnos registrados</TableCaption>
        <TableHeader>
          <TableRow>
            <TableHead className="w-[100px]">ID</TableHead>
            <TableHead>Nombre</TableHead>
            <TableHead>Apellido</TableHead>
            <TableHead>DNI</TableHead>
            <TableHead>Carrera</TableHead>
            <TableHead>Acciones</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {currentAlumnos.map((alumno) => (
            <TableRow key={alumno.id}>
              <TableCell>{alumno.id}</TableCell>
              <TableCell>{alumno.nombre}</TableCell>
              <TableCell>{alumno.apellido}</TableCell>
              <TableCell>{alumno.dni}</TableCell>
              <TableCell>{alumno.carreraNombre}</TableCell>
              <TableCell>
                <button
                  onClick={() => handleEliminar(alumno.id)}
                  className="bg-red-500 text-white px-4 py-2 rounded transition duration-200"
                >
                  Eliminar
                </button>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
        <TableFooter>
          <TableRow>
            <TableCell colSpan={5}>Total de alumnos</TableCell>
            <TableCell className="text-right">{filteredAlumnos.length}</TableCell>
          </TableRow>
        </TableFooter>
      </Table>

      {/* Paginación */}
      <div className="flex justify-center mt-4">
        <button
          onClick={() => paginate(currentPage - 1)}
          disabled={currentPage === 1}
          className="px-4 py-2 mx-1 bg-gray-300 rounded-md disabled:opacity-50"
        >
          Anterior
        </button>
        <span className="px-4 py-2 mx-1">{currentPage}</span>
        <button
          onClick={() => paginate(currentPage + 1)}
          disabled={currentPage * alumnosPerPage >= filteredAlumnos.length}
          className="px-4 py-2 mx-1 bg-gray-300 rounded-md disabled:opacity-50"
        >
          Siguiente
        </button>
      </div>
    </div>
  );
};

export default AlumnoList;
