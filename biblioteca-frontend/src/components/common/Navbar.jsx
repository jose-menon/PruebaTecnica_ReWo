import { Link, NavLink, useNavigate } from 'react-router-dom';
import { useAuth } from '../../hooks/useAuth';

export default function Navbar() {
  const { user, isAuthenticated, logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  return (
    <nav className="navbar navbar-expand-lg bg-white border-bottom sticky-top">
      <div className="container">
        <Link className="navbar-brand fw-bold text-primary" to="/">
          <i className="bi bi-book-half me-2" />Biblioteca Digital
        </Link>

        <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mainNavbar">
          <span className="navbar-toggler-icon" />
        </button>

        <div className="collapse navbar-collapse" id="mainNavbar">
          <ul className="navbar-nav me-auto mb-2 mb-lg-0">
            <li className="nav-item">
              <NavLink className="nav-link" to="/catalogo">Catálogo</NavLink>
            </li>
            {user?.rol === 'ADMIN' && (
              <li className="nav-item">
                <NavLink className="nav-link" to="/admin/dashboard">Panel admin</NavLink>
              </li>
            )}
            {user?.rol === 'USUARIO' && (
              <li className="nav-item">
                <NavLink className="nav-link" to="/usuario/dashboard">Mi panel</NavLink>
              </li>
            )}
          </ul>

          <div className="d-flex align-items-center gap-2 mobile-stack">
            {isAuthenticated ? (
              <>
                <span className="badge text-bg-primary">{user?.rol}</span>
                <span className="small text-secondary">{user?.email}</span>
                <button className="btn btn-outline-danger btn-sm touch-btn" onClick={handleLogout}>
                  Salir
                </button>
              </>
            ) : (
              <Link className="btn btn-primary touch-btn" to="/login">Ingresar</Link>
            )}
          </div>
        </div>
      </div>
    </nav>
  );
}
