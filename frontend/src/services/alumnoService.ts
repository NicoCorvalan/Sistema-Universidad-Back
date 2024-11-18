import axios from 'axios';

// URL de la API que configuraste en el backend
const API_URL = import.meta.env.VITE_API_URL + "/universidad";

// Función para obtener todos los alumnos
export const obtenerAlumnos = async () => {
    try {
        const response = await axios.get(`${API_URL}/alumnos`);
        return response.data;
    } catch (error) {
        console.error("Error obteniendo los alumnos", error);
        throw error;
    }
};

// Función para obtener un alumno por ID
export const obtenerAlumnoPorId = async (id: number) => {
    try {
        const response = await axios.get(`${API_URL}/alumnos/${id}`);
        return response.data;
    } catch (error) {
        console.error(`Error obteniendo el alumno con id ${id}`, error);
        throw error;
    }
};

// Función para crear un nuevo alumno
export const crearAlumno = async (alumno: any) => {
    try {
        const response = await axios.post(`${API_URL}/alumnos`, alumno);
        return response.data;
    } catch (error) {
        console.error("Error creando el alumno", error);
        throw error;
    }
};


// Función para editar un alumno
export const editarAlumno = async (alumno: any) => {
    try {
        const response = await axios.put(`${API_URL}/alumnos`, alumno);
        return response.data;
    } catch (error) {
        console.error("Error editando el alumno", error);
        throw error;
    }
};

// Función para eliminar un alumno por ID
export const eliminarAlumno = async (id: number) => {
    try {
        const response = await axios.delete(`${API_URL}/alumnos/${id}`);
        return response.data;
    } catch (error) {
        console.error(`Error eliminando el alumno con id ${id}`, error);
        throw error;
    }
};
