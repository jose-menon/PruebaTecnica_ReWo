import { useEffect, useState } from 'react';
import AdminLayout from '../../layouts/AdminLayout';
import { prestamoService } from '../../services/prestamoService';
import PrestamoCard from '../../components/prestamos/PrestamoCard';
import Loader from '../../components/common/Loader';

export default function PrestamosAdminPage() {
  const [idUsuario, setIdUsuario] = useState('');
  const [idLibro, setIdLibro] = useState('');
  const [atrasados, setAtrasados] = useState([]);
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(true);

  const load = async () => {
    try {
      setLoading(true);
      setError('');
      const data = await prestamoService.atrasados();
      setAtrasados(Array.isArray(data) ? data : []);
    } catch (err) {
      console.error('Error al cargar préstamos atrasados:', err);
      setError(err?.response?.data?.message || 'No se pudieron cargar préstamos atrasados');
      setAtrasados([]);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    load();
  }, []);

  const handleCreate = async (e) => {
    e.preventDefault();

    try {
      setMessage('');
      setError('');

      await prestamoService.registrar(idUsuario, idLibro);

      setMessage('Préstamo registrado');
      setIdUsuario('');
      setIdLibro('');
      await load();
    } catch (err) {
      console.error('Error al registrar préstamo:', err);
      setMessage('');
      setError(err?.response?.data?.message || 'No se pudo registrar el préstamo');
    }
  };

  const handleDevolver = async (idPrestamo) => {
    try {
      setMessage('');
      setError('');

      await prestamoService.devolver(idPrestamo);

      setMessage('Devolución registrada');
      await load();
    } catch (err) {
      console.error('Error al registrar devolución:', err);
      setMessage('');
      setError(err?.response?.data?.message || 'No se pudo registrar la devolución');
    }
  };

  return (
    <AdminLayout>
      <h2 className="mb-4">Préstamos</h2>

      {message && <div className="alert alert-success">{message}</div>}
      {error && <div className="alert alert-danger">{error}</div>}

      <div className="row g-4">
        <div className="col-12 col-xl-4">
          <form className="card card-soft" onSubmit={handleCreate}>
            <div className="card-body row g-3">
              <div className="col-12">
                <input
                  className="form-control"
                  placeholder="ID Usuario"
                  value={idUsuario}
                  onChange={(e) => setIdUsuario(e.target.value)}
                  required
                />
              </div>

              <div className="col-12">
                <input
                  className="form-control"
                  placeholder="ID Libro"
                  value={idLibro}
                  onChange={(e) => setIdLibro(e.target.value)}
                  required
                />
              </div>

              <div className="col-12">
                <button className="btn btn-primary w-100">
                  Registrar préstamo
                </button>
              </div>
            </div>
          </form>
        </div>

        <div className="col-12 col-xl-8">
          <h5 className="mb-3">Préstamos atrasados</h5>

          {loading ? (
            <Loader />
          ) : atrasados.length === 0 ? (
            <div className="alert alert-secondary mb-0">
              No hay préstamos atrasados.
            </div>
          ) : (
            <div className="row g-3">
              {atrasados.map((p) => (
                <div
                  className="col-12 col-md-6"
                  key={p.idPrestamo ?? p.id_prestamo}
                >
                  <PrestamoCard
                    prestamo={p}
                    onDevolver={handleDevolver}
                  />
                </div>
              ))}
            </div>
          )}
        </div>
      </div>
    </AdminLayout>
  );
}