import React, { useEffect, useState } from "react";
import { obtenerAlumnos } from "../../services/alumnoService";
import { Alumno } from "../../types/Alumno";
import AlumnoList from "./AlumnoList";

const AlumnoPage: React.FC = () => {
  const [alumnos, setAlumnos] = useState<Alumno[]>([]);
  const [search, setSearch] = useState<string>("");
  const [currentPage, setCurrentPage] = useState<number>(1);
  const [alumnosPerPage] = useState<number>(5);

  useEffect(() => {
    obtenerAlumnos().then(setAlumnos).catch(console.error);
  }, []);

  const handleBaja = (id: number) => {
    setAlumnos((prev) =>
      prev.map((alumno) =>
        alumno.id === id ? { ...alumno, estado: "inactivo" } : alumno
      )
    );
  };

  const paginate = (pageNumber: number) => setCurrentPage(pageNumber);

  return (
    <AlumnoList
      alumnos={alumnos}
      onBaja={handleBaja}
      search={search}
      setSearch={setSearch}
      currentPage={currentPage}
      alumnosPerPage={alumnosPerPage}
      paginate={paginate}
    />
  );
};

export default AlumnoPage;
