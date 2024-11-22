import React, { useState } from 'react';

interface NavProps {
  setActiveModule: React.Dispatch<React.SetStateAction<'alumnos' | 'carreras' | 'materias'>>;
}

const Nav: React.FC<NavProps> = ({ setActiveModule }) => {
  const [isOpen, setIsOpen] = useState(false);

  return (
    <nav className="bg-gray-800 text-white shadow-lg w-full mx-auto">
      <div className="flex justify-between items-center p-4">
        {/* Título del sistema */}
        <div className="text-2xl font-semibold">Gestión Universitaria</div>

        {/* Botón para menú en dispositivos móviles */}
        <div className="lg:hidden">
          <button
            onClick={() => setIsOpen(!isOpen)}
            className="text-white focus:outline-none"
          >
            <svg
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              stroke="currentColor"
              className="w-6 h-6"
            >
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M4 6h16M4 12h16M4 18h16" />
            </svg>
          </button>
        </div>

        {/* Menú principal */}
        <div className={`lg:flex flex-col lg:flex-row ${isOpen ? 'block' : 'hidden'} lg:block space-x-0 lg:space-x-6 mt-4 lg:mt-0`}>
          <button
            className="bg-blue-500 hover:bg-blue-600 text-white font-semibold py-2 px-4 rounded-md mb-2 lg:mb-0 lg:mr-4 transition duration-200"
            onClick={() => setActiveModule('alumnos')}
          >
            Alumnos
          </button>
          <button
            className="bg-green-500 hover:bg-green-600 text-white font-semibold py-2 px-4 rounded-md mb-2 lg:mb-0 lg:mr-4 transition duration-200"
            onClick={() => setActiveModule('carreras')}
          >
            Carreras
          </button>
          <button
            className="bg-yellow-500 hover:bg-yellow-600 text-white font-semibold py-2 px-4 rounded-md transition duration-200"
            onClick={() => setActiveModule('materias')}
          >
            Materias
          </button>
        </div>
      </div>
    </nav>
  );
};

export default Nav;
