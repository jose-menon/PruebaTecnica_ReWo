import { NavLink } from 'react-router-dom';
import MainLayout from './MainLayout';

const links = [
  ['dashboard', 'Dashboard', 'bi-speedometer2'],
  ['libros', 'Libros', 'bi-book'],
  ['usuarios', 'Usuarios', 'bi-people'],
  ['prestamos', 'Préstamos', 'bi-arrow-left-right'],
  ['reservas', 'Reservas', 'bi-bookmark'],
  ['multas', 'Multas', 'bi-cash-coin'],
  ['reportes', 'Reportes', 'bi-bar-chart']
];

export default function AdminLayout({ children }) {
  return (
    <MainLayout>
      <div className="container py-4">
        <div className="row g-4">
          <div className="col-12 col-xl-3">
            <div className="card card-soft sidebar-card">
              <div className="card-body">
                <h5 className="mb-3">Administración</h5>
                <div className="d-grid gap-2">
                  {links.map(([path, label, icon]) => (
                    <NavLink key={path} to={`/admin/${path}`} className="sidebar-link">
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
