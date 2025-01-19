import axios from 'axios';
import { Alumno } from '../types/Alumno';

// URL base de la API
const API_URL = import.meta.env.VITE_API_URL + "/universidad";

// Función para obtener todos los alumnos
export const obtenerAlumnos = async (): Promise<Alumno[]> => {
    try {
        const response = await axios.get(`${API_URL}/alumnos`);
        return response.data;
    } catch (error) {
        console.error("Error obteniendo los alumnos", error);
        throw error;
    }
};

// Función para obtener un alumno por ID
export const obtenerAlumnoPorId = async (id: number): Promise<Alumno> => {
    try {
        const response = await axios.get(`${API_URL}/alumnos/${id}`);
        return response.data;
    } catch (error) {
        console.error(`Error obteniendo el alumno con id ${id}`, error);
        throw error;
    }
};

// Función para crear un nuevo alumno
export const crearAlumno = async (alumno: Alumno): Promise<Alumno> => {
    try {
        const response = await axios.post(`${API_URL}/alumnos`, alumno);
        return response.data;
    } catch (error) {
        console.error("Error creando el alumno", error);
        throw error;
    }
};

// Función para editar un alumno
export const editarAlumno = async (alumno: Alumno): Promise<Alumno> => {
    try {
        const response = await axios.put(`${API_URL}/alumnos/${alumno.id}`, alumno);
        return response.data;
    } catch (error) {
        console.error("Error editando el alumno", error);
        throw error;
    }
};

// Función para dar de baja lógica a un alumno
export const darDeBajaAlumno = async (id: number): Promise<void> => {
    try {
        await axios.put(`${API_URL}/alumnos/${id}/baja`);
    } catch (error) {
        console.error(`Error al dar de baja al alumno con id ${id}`, error);
        throw error;
    }
};
