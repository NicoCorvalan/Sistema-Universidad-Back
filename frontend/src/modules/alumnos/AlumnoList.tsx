import React from "react";
import { Alumno } from "../../types/Alumno";
import { Table, TableBody, TableCell, TableHeader, TableRow } from "../../components/ui/table"; // Asegúrate de que la ruta sea correcta

interface AlumnoListProps {
  alumnos: Alumno[];
  onBaja: (id: number) => void;
  search: string;
  setSearch: React.Dispatch<React.SetStateAction<string>>;
  currentPage: number;
  alumnosPerPage: number;
  paginate: (pageNumber: number) => void;
}

const AlumnoList: React.FC<AlumnoListProps> = ({
  alumnos,
  onBaja,
  search,
  setSearch,
  currentPage,
  alumnosPerPage,
  paginate,
}) => {
  // Filtrar los alumnos por nombre o DNI
  const filteredAlumnos = alumnos.filter(
    (alumno) =>
      alumno.nombre.toLowerCase().includes(search.toLowerCase()) ||
      alumno.dni.includes(search)
  );

  // Obtener los alumnos a mostrar en la página actual
  const indexOfLastAlumno = currentPage * alumnosPerPage;
  const indexOfFirstAlumno = indexOfLastAlumno - alumnosPerPage;
  const currentAlumnos = filteredAlumnos.slice(indexOfFirstAlumno, indexOfLastAlumno);

  return (
    <div className="overflow-x-auto p-4 w-9/12 mx-auto">
      <h2 className="text-2xl font-semibold mb-4">Lista de Alumnos</h2>

      {/* Barra de búsqueda */}
      <input
        type="text"
        placeholder="Buscar por nombre o DNI"
        className="mb-4 p-2 border border-gray-300 rounded-md w-full"
        value={search}
        onChange={(e) => setSearch(e.target.value)}
      />

      {/* Tabla de alumnos */}
      <Table aria-label="Lista de Alumnos" className="min-w-full table-auto border-collapse">
        <TableHeader>
          <TableRow>
            <TableCell className="px-4 py-2 border font-semibold">ID</TableCell>
            <TableCell className="px-4 py-2 border font-semibold">Nombre</TableCell>
            <TableCell className="px-4 py-2 border font-semibold">Apellido</TableCell>
            <TableCell className="px-4 py-2 border font-semibold">DNI</TableCell>
            <TableCell className="px-4 py-2 border font-semibold">Estado</TableCell>
            <TableCell className="px-4 py-2 border font-semibold">Carrera</TableCell>
            <TableCell className="px-4 py-2 border font-semibold">Acciones</TableCell>
          </TableRow>
        </TableHeader>
        <TableBody>
          {currentAlumnos.map((alumno) => (
            <TableRow key={alumno.id}>
              <TableCell className="px-4 py-2 border">{alumno.id}</TableCell>
              <TableCell className="px-4 py-2 border">{alumno.nombre}</TableCell>
              <TableCell className="px-4 py-2 border">{alumno.apellido}</TableCell>
              <TableCell className="px-4 py-2 border">{alumno.dni}</TableCell>
              <TableCell className="px-4 py-2 border">{alumno.estado}</TableCell>
              <TableCell className="px-4 py-2 border">{alumno.carreraNombre}</TableCell>
              <TableCell className="px-4 py-2 border">
                <button
                  onClick={() => onBaja(alumno.id)}
                  className="bg-red-500 text-white py-1 px-3 rounded hover:bg-red-600 transition"
                >
                  Baja
                </button>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
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
