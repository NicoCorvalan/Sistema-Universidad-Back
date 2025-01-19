import { useState, useEffect } from "react";
import {
  obtenerAlumnos,
  crearAlumno,
  editarAlumno,
  darDeBajaAlumno,
} from "../services/alumnoService";
import { Alumno } from "../types/Alumno";

export const useAlumnos = () => {
  const [alumnos, setAlumnos] = useState<Alumno[]>([]);
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string>("");

  useEffect(() => {
    const fetchAlumnos = async () => {
      try {
        setLoading(true);
        const data = await obtenerAlumnos();
        setAlumnos(data as Alumno[]); // Fuerza la conversión a Alumno[]
      } catch (error) {
        setError("Error al cargar los alumnos");
      } finally {
        setLoading(false);
      }
    };

    fetchAlumnos();
  }, []);

  const addAlumno = async (alumno: Alumno) => {
    try {
      const newAlumno = await crearAlumno(alumno);
      setAlumnos((prevAlumnos) => [...prevAlumnos, newAlumno]);
    } catch (error) {
      setError("Error al crear el alumno");
    }
  };

  const updateAlumno = async (alumno: Alumno) => {
    try {
      const updatedAlumno = await editarAlumno(alumno);
      setAlumnos((prevAlumnos) =>
        prevAlumnos.map((a) => (a.id === updatedAlumno.id ? updatedAlumno : a))
      );
    } catch (error) {
      setError("Error al editar el alumno");
    }
  };

  const deleteAlumno = async (id: number) => {
    try {
      await darDeBajaAlumno(id);
      setAlumnos((prevAlumnos) => prevAlumnos.filter((a) => a.id !== id));
    } catch (error) {
      setError("Error al eliminar el alumno");
    }
  };

  return {
    alumnos,
    loading,
    error,
    addAlumno,
    updateAlumno,
    deleteAlumno,
  };
};
