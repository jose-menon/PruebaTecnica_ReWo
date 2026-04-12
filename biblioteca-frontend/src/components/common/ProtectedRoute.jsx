import { Navigate } from 'react-router-dom';
import Loader from './Loader';
import { useAuth } from '../../hooks/useAuth';

export default function ProtectedRoute({ children, allowedRoles = [] }) {
  const { user, loading, isAuthenticated } = useAuth();

  if (loading) return <Loader text="Verificando acceso..." />;
  if (!isAuthenticated) return <Navigate to="/login" replace />;
  if (allowedRoles.length && !allowedRoles.includes(user?.rol)) return <Navigate to="/" replace />;

  return children;
}
