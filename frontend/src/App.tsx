import { useState } from "react";
import Nav from "./components/Nav";
import AlumnoList from "./modules/alumnos/AlumnoList";
import './tailwind.css';   // Estilos globales de Tailwind u otros


const App: React.FC = () => {
  const [activeModule, setActiveModule] = useState<
    "alumnos" | "carreras" | "materias"
  >("alumnos");

  const renderModule = () => {
    switch (activeModule) {
      case "alumnos":
        return <AlumnoList />;
      // case 'carreras':
      //   return <CarreraList />;
      // case 'materias':
      //   return <MateriaList />;
      default:
        return null;
    }
  };

  return (
    <div className="min-h-screen flex flex-col">
      <header className=" text-white bg-black">
        <Nav setActiveModule={setActiveModule} />
      </header>
      <main className="flex-1 p-4">{renderModule()}</main>
      <footer className="bg-gray-800 text-white p-4">
        <p>Footer</p>
      </footer>
    </div>
  );
};

export default App;
