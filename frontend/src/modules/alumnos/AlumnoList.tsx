import React, { useState } from "react";
import { Alumno } from "../../types/Alumno";
import {
  Table,
  TableBody,
  TableCell,
  TableHeader,
  TableRow,
} from "../../components/ui/table";

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
  const [showModal, setShowModal] = useState(false); // Controlar la visibilidad del modal

  // Filtrar alumnos por búsqueda
  const filteredAlumnos = alumnos.filter(
    (alumno) =>
      alumno.nombre.toLowerCase().includes(search.toLowerCase()) ||
      alumno.dni.includes(search)
  );

  // Obtener alumnos de la página actual
  const indexOfLastAlumno = currentPage * alumnosPerPage;
  const indexOfFirstAlumno = indexOfLastAlumno - alumnosPerPage;
  const currentAlumnos = filteredAlumnos.slice(
    indexOfFirstAlumno,
    indexOfLastAlumno
  );

  return (
    <div className="p-4 w-11/12 mx-auto">
      <div className="flex justify-between items-center mb-4">
        <h2 className="text-3xl font-semibold">Gestión de Alumnos</h2>
        <button
          onClick={() => setShowModal(true)}
          className="bg-blue-600 text-white px-4 py-2 rounded-md shadow-md hover:bg-blue-700 transition"
        >
          Agregar Alumno
        </button>
      </div>

      {/* Barra de búsqueda */}
      <input
        type="text"
        placeholder="Buscar por nombre o DNI"
        className="mb-4 p-3 border border-gray-300 rounded-md w-full focus:outline-blue-500"
        value={search}
        onChange={(e) => setSearch(e.target.value)}
      />

      {/* Tabla de alumnos */}
      <Table
        aria-label="Lista de Alumnos"
        className="min-w-full border border-gray-200 shadow-lg"
      >
        <TableHeader>
          <TableRow>
            <TableCell className="px-4 py-2 bg-gray-100 font-bold">
              ID
            </TableCell>
            <TableCell className="px-4 py-2 bg-gray-100 font-bold">
              Nombre
            </TableCell>
            <TableCell className="px-4 py-2 bg-gray-100 font-bold">
              Apellido
            </TableCell>
            <TableCell className="px-4 py-2 bg-gray-100 font-bold">
              DNI
            </TableCell>
            <TableCell className="px-4 py-2 bg-gray-100 font-bold">
              Estado
            </TableCell>
            <TableCell className="px-4 py-2 bg-gray-100 font-bold">
              Carrera
            </TableCell>
            <TableCell className="px-4 py-2 bg-gray-100 font-bold">
              Acciones
            </TableCell>
          </TableRow>
        </TableHeader>
        <TableBody>
          {currentAlumnos.map((alumno) => (
            <TableRow key={alumno.id} className="hover:bg-gray-100">
              <TableCell className="px-4 py-2 border">{alumno.id}</TableCell>
              <TableCell className="px-4 py-2 border">
                {alumno.nombre}
              </TableCell>
              <TableCell className="px-4 py-2 border">
                {alumno.apellido}
              </TableCell>
              <TableCell className="px-4 py-2 border">{alumno.dni}</TableCell>
              <TableCell className="px-4 py-2 border">
                {alumno.estado}
              </TableCell>
              <TableCell className="px-4 py-2 border">
                {alumno.carreraNombre}
              </TableCell>
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
      <div className="flex justify-center mt-4 space-x-2">
        <button
          onClick={() => paginate(currentPage - 1)}
          disabled={currentPage === 1}
          className="px-4 py-2 bg-gray-300 rounded-md disabled:opacity-50 hover:bg-gray-400"
        >
          Anterior
        </button>
        <span className="px-4 py-2 bg-gray-200 rounded-md">{currentPage}</span>
        <button
          onClick={() => paginate(currentPage + 1)}
          disabled={currentPage * alumnosPerPage >= filteredAlumnos.length}
          className="px-4 py-2 bg-gray-300 rounded-md disabled:opacity-50 hover:bg-gray-400"
        >
          Siguiente
        </button>
      </div>

      {/* Modal */}
      {showModal && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center">
          <div className="bg-white p-6 rounded-lg shadow-lg w-full max-w-md max-h-[90vh] overflow-y-auto">
            <h3 className="text-xl font-bold mb-4">Agregar Alumno</h3>
            <form>
              <div className="mb-4">
                <label className="block mb-1 font-medium">Nombre</label>
                <input
                  type="text"
                  className="w-full p-2 border border-gray-300 rounded focus:outline-blue-500"
                />
              </div>
              <div className="mb-4">
                <label className="block mb-1 font-medium">Apellido</label>
                <input
                  type="text"
                  className="w-full p-2 border border-gray-300 rounded focus:outline-blue-500"
                />
              </div>
              <div className="mb-4">
                <label className="block mb-1 font-medium">DNI</label>
                <input
                  type="text"
                  className="w-full p-2 border border-gray-300 rounded focus:outline-blue-500"
                />
              </div>
              <div className="mb-4">
                <label className="block mb-1 font-medium">Carrera</label>
                <select className="w-full p-2 border border-gray-300 rounded focus:outline-blue-500">
                  <option value="">Seleccionar Carrera</option>
                  {/* Aquí agregarías las opciones de carrera */}
                </select>
              </div>
              <div className="mb-4">
                <label className="block mb-1 font-medium">Teléfono</label>
                <input
                  type="text"
                  className="w-full p-2 border border-gray-300 rounded focus:outline-blue-500"
                />
              </div>
              <div className="mb-4">
                <label className="block mb-1 font-medium">
                  Número de Legajo
                </label>
                <input
                  type="text"
                  className="w-full p-2 border border-gray-300 rounded focus:outline-blue-500"
                />
              </div>
              <div className="mb-4">
                <label className="block mb-1 font-medium">Estado</label>
                <select className="w-full p-2 border border-gray-300 rounded focus:outline-blue-500">
                  <option value="ACTIVO">Activo</option>
                  <option value="INACTIVO">Inactivo</option>
                </select>
              </div>
              <div className="flex justify-end space-x-2">
                <button
                  type="button"
                  onClick={() => setShowModal(false)}
                  className="px-4 py-2 bg-gray-300 rounded-md hover:bg-gray-400"
                >
                  Cancelar
                </button>
                <button
                  type="submit"
                  className="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700"
                >
                  Guardar
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
};

export default AlumnoList;
