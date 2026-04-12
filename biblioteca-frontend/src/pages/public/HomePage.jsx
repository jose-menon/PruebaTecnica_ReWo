import { Link } from 'react-router-dom';
import MainLayout from '../../layouts/MainLayout';

export default function HomePage() {
  return (
    <MainLayout>
      <div className="container py-4 py-md-5">
        <section className="hero-section p-4 p-md-5 mb-4">
          <div className="row align-items-center g-4">
            <div className="col-12 col-lg-7">
              <span className="badge bg-light text-primary mb-3">100% responsive · React + Bootstrap</span>
              <h1 className="display-6 fw-bold">Biblioteca Pública Digital</h1>
              <p className="lead mb-4">
                Consultá el catálogo, administrá préstamos, gestioná reservas y visualizá reportes desde cualquier dispositivo.
              </p>
              <div className="d-flex flex-wrap gap-2">
                <Link to="/catalogo" className="btn btn-light btn-lg touch-btn">Ver catálogo</Link>
                <Link to="/login" className="btn btn-outline-light btn-lg touch-btn">Ingresar</Link>
              </div>
            </div>
            <div className="col-12 col-lg-5">
              <div className="card card-soft text-dark">
                <div className="card-body p-4">
                  <h5>Funcionalidades cubiertas</h5>
                  <ul className="mb-0 text-secondary">
                    <li>Catálogo de libros por autor, ISBN y categoría</li>
                    <li>Préstamos y devoluciones</li>
                    <li>Reservas de libros agotados</li>
                    <li>Multas por retraso</li>
                    <li>Panel administrador y panel usuario</li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </section>
      </div>
    </MainLayout>
  );
}
