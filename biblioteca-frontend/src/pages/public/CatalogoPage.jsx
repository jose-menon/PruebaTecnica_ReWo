import { useEffect, useState } from 'react';
import EmptyState from '../../components/common/EmptyState';
import Loader from '../../components/common/Loader';
import LibroCard from '../../components/libros/LibroCard';
import LibroFilters from '../../components/libros/LibroFilters';
import MainLayout from '../../layouts/MainLayout';
import { useAuth } from '../../hooks/useAuth';
import { useResponsive } from '../../hooks/useResponsive';
import { libroService } from '../../services/libroService';
import { prestamoService } from '../../services/prestamoService';
import { reservaService } from '../../services/reservaService';

export default function CatalogoPage() {
  const { user } = useAuth();
  const { isMobile } = useResponsive();
  const [libros, setLibros] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [message, setMessage] = useState('');
  const [filters, setFilters] = useState({ texto: '', categoria: '' });

  const loadLibros = async () => {
    try {
      setLoading(true);
      setError('');
      const data = await libroService.listar();
      setLibros(data);
    } catch (err) {
      setError(err?.response?.data?.message || 'No se pudo cargar el catálogo');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadLibros();
  }, []);

  const handleSearch = async () => {
    try {
      setLoading(true);
      setMessage('');
      const data = filters.categoria
        ? await libroService.buscarCategoria(filters.categoria)
        : filters.texto
          ? await libroService.buscarCatalogo(filters.texto)
          : await libroService.listar();
      setLibros(data);
    } catch (err) {
      setError(err?.response?.data?.message || 'No se pudo buscar');
    } finally {
      setLoading(false);
    }
  };

  const handleReset = () => {
    setFilters({ texto: '', categoria: '' });
    loadLibros();
  };

  const handleAction = async (libro) => {
    if (!user?.idUsuario) {
      setError('Debés iniciar sesión para realizar esta acción.');
      return;
    }

    try {
      setError('');
      setMessage('');

      if (libro.ejemplaresDisponibles > 0) {
        await prestamoService.registrar(user.idUsuario, libro.idLibro);
        setMessage(`Préstamo registrado correctamente para "${libro.titulo}".`);
      } else {
        await reservaService.registrar(user.idUsuario, libro.idLibro);
        setMessage(`Reserva registrada correctamente para "${libro.titulo}".`);
      }

      await loadLibros();
    } catch (err) {
      const data = err?.response?.data;

      console.log('ERROR DATA COMPLETO:', JSON.stringify(data, null, 2));

      const backendMessage =
        data?.message ||
        data?.mensaje ||
        data?.error ||
        data?.detail ||
        data?.title ||
        JSON.stringify(data);

      const msg = String(backendMessage).toLowerCase();

      if (msg.includes('prestamo activo')) {
        setError(
          `Ya tenés prestado "${libro.titulo}". Debés devolverlo antes de solicitar otro préstamo.`
        );
      } else if (msg.includes('reserva activa')) {
        setError(
          `Ya tenés una reserva activa para "${libro.titulo}".`
        );
      } else {
        setError(String(backendMessage) || 'No se pudo completar la operación');
      }
    }
  }

  return (
    <MainLayout>
      <div className="container py-4">
        <div className="d-flex flex-wrap justify-content-between align-items-center gap-2 mb-3">
          <div>
            <h2 className="mb-1">Catálogo de libros</h2>
            <p className="text-secondary mb-0">Utilicé los filtros para una busqueda más rápida.</p>
          </div>
        </div>

        <LibroFilters
          filters={filters}
          onChange={(e) => setFilters((prev) => ({ ...prev, [e.target.name]: e.target.value }))}
          onSearch={handleSearch}
          onReset={handleReset}
        />

        {message && <div className="alert alert-success">{message}</div>}
        {error && <div className="alert alert-danger">{error}</div>}

        {loading ? (
          <Loader text="Cargando catálogo..." />
        ) : libros.length === 0 ? (
          <EmptyState title="No hay libros" description="No se encontraron coincidencias con tu búsqueda." />
        ) : (
          <div className={`row g-3 ${isMobile ? '' : ''}`}>
            {libros.map((libro) => (
              <div className="col-12 col-md-6 col-xl-4" key={libro.idLibro}>
                <LibroCard
                  libro={libro}
                  onAction={user ? handleAction : null}
                  canReserve={libro.ejemplaresDisponibles <= 0}
                />
              </div>
            ))}
          </div>
        )}
      </div>
    </MainLayout>
  );
}
