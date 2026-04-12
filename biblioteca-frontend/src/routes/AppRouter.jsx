import { Navigate, Route, Routes } from 'react-router-dom';
import ProtectedRoute from '../components/common/ProtectedRoute';
import HomePage from '../pages/public/HomePage';
import CatalogoPage from '../pages/public/CatalogoPage';
import LibroDetallePage from '../pages/public/LibroDetallePage';
import LoginPage from '../pages/auth/LoginPage';
import DashboardAdminPage from '../pages/admin/DashboardAdminPage';
import LibrosAdminPage from '../pages/admin/LibrosAdminPage';
import UsuariosAdminPage from '../pages/admin/UsuariosAdminPage';
import PrestamosAdminPage from '../pages/admin/PrestamosAdminPage';
import ReservasAdminPage from '../pages/admin/ReservasAdminPage';
import MultasAdminPage from '../pages/admin/MultasAdminPage';
import ReportesPage from '../pages/admin/ReportesPage';
import DashboardUsuarioPage from '../pages/usuario/DashboardUsuarioPage';
import HistorialPage from '../pages/usuario/HistorialPage';
import PrestamosVigentesPage from '../pages/usuario/PrestamosVigentesPage';
import ReservasPage from '../pages/usuario/ReservasPage';
import MultasPage from '../pages/usuario/MultasPage';

export default function AppRouter() {
  return (
    <Routes>
      <Route path="/" element={<HomePage />} />
      <Route path="/catalogo" element={<CatalogoPage />} />
      <Route path="/catalogo/:id" element={<LibroDetallePage />} />
      <Route path="/login" element={<LoginPage />} />

      <Route path="/admin/dashboard" element={<ProtectedRoute allowedRoles={['ADMIN']}><DashboardAdminPage /></ProtectedRoute>} />
      <Route path="/admin/libros" element={<ProtectedRoute allowedRoles={['ADMIN']}><LibrosAdminPage /></ProtectedRoute>} />
      <Route path="/admin/usuarios" element={<ProtectedRoute allowedRoles={['ADMIN']}><UsuariosAdminPage /></ProtectedRoute>} />
      <Route path="/admin/prestamos" element={<ProtectedRoute allowedRoles={['ADMIN']}><PrestamosAdminPage /></ProtectedRoute>} />
      <Route path="/admin/reservas" element={<ProtectedRoute allowedRoles={['ADMIN']}><ReservasAdminPage /></ProtectedRoute>} />
      <Route path="/admin/multas" element={<ProtectedRoute allowedRoles={['ADMIN']}><MultasAdminPage /></ProtectedRoute>} />
      <Route path="/admin/reportes" element={<ProtectedRoute allowedRoles={['ADMIN']}><ReportesPage /></ProtectedRoute>} />

      <Route path="/usuario/dashboard" element={<ProtectedRoute allowedRoles={['USUARIO']}><DashboardUsuarioPage /></ProtectedRoute>} />
      <Route path="/usuario/historial" element={<ProtectedRoute allowedRoles={['USUARIO']}><HistorialPage /></ProtectedRoute>} />
      <Route path="/usuario/prestamos" element={<ProtectedRoute allowedRoles={['USUARIO']}><PrestamosVigentesPage /></ProtectedRoute>} />
      <Route path="/usuario/reservas" element={<ProtectedRoute allowedRoles={['USUARIO']}><ReservasPage /></ProtectedRoute>} />
      <Route path="/usuario/multas" element={<ProtectedRoute allowedRoles={['USUARIO']}><MultasPage /></ProtectedRoute>} />

      <Route path="*" element={<Navigate to="/" replace />} />
    </Routes>
  );
}
