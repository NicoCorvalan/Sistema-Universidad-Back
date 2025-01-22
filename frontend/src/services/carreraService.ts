import axios from 'axios';
import { Carrera } from '../types/Carrera';

const API_URL = import.meta.env.VITE_API_URL + '/universidad/carreras';

// Función para obtener todas las carreras
export const fetchCarreras = async (): Promise<Carrera[]> => {
  try {
    const response = await axios.get(API_URL);
    return response.data;
  } catch (error) {
    console.error('Error al obtener las carreras', error);
    throw error;
  }
};

