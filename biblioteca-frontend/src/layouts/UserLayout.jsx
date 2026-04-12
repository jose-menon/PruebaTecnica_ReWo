import { NavLink } from 'react-router-dom';
import MainLayout from './MainLayout';

const links = [
  ['dashboard', 'Resumen', 'bi-grid'],
  ['historial', 'Historial', 'bi-clock-history'],
  ['prestamos', 'Préstamos vigentes', 'bi-journal-check'],
  ['reservas', 'Reservas', 'bi-bookmark-heart'],
  ['multas', 'Multas', 'bi-exclamation-circle']
];

export default function UserLayout({ children }) {
  return (
    <MainLayout>
      <div className="container py-4">
        <div className="row g-4">
          <div className="col-12 col-xl-3">
            <div className="card card-soft sidebar-card">
              <div className="card-body">
                <h5 className="mb-3">Mi cuenta</h5>
                <div className="d-grid gap-2">
                  {links.map(([path, label, icon]) => (
                    <NavLink key={path} to={`/usuario/${path}`} className="sidebar-link">
                      <i className={`bi ${icon}`} /> {label}
                    </NavLink>
                  ))}
                </div>
              </div>
            </div>
          </div>
          <div className="col-12 col-xl-9">{children}</div>
        </div>
      </div>
    </MainLayout>
  );
}
