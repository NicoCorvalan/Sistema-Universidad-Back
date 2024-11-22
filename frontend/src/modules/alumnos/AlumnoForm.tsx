import React, { useState } from 'react';

interface AlumnoFormProps {
  onCrear: (nombre: string) => void;
}

const AlumnoForm: React.FC<AlumnoFormProps> = ({ onCrear }) => {
  const [nombre, setNombre] = useState<string>('');

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (nombre) {
      onCrear(nombre);
      setNombre('');
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        value={nombre}
        onChange={(e) => setNombre(e.target.value)}
        placeholder="Nombre del alumno"
      />
      <button type="submit" disabled={!nombre}>Crear Alumno</button>
    </form>
  );
};

export default AlumnoForm;
