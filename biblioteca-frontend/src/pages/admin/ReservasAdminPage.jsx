import { useState } from 'react';
import AdminLayout from '../../layouts/AdminLayout';
import { reservaService } from '../../services/reservaService';

export default function ReservasAdminPage() {
  const [idLibro, setIdLibro] = useState('');
  const [reservas, setReservas] = useState([]);
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  const handleBuscar = async () => {
    if (!idLibro.trim()) {
      setMessage('');
      setError('Debe ingresar un ID de libro');
      setReservas([]);
      return;
    }

    try {
      setLoading(true);
      setMessage('');
      setError('');

      const data = await reservaService.porLibro(Number(idLibro));
      console.log('Reservas encontradas:', data);

      setReservas(Array.isArray(data) ? data : []);
    } catch (err) {
      console.error('Error al buscar reservas:', err);
      setMessage('');
      setError(err?.response?.data?.message || 'No se pudieron cargar las reservas');
      setReservas([]);
    } finally {
      setLoading(false);
    }
  };

  const handleAtender = async () => {
    if (!idLibro.trim()) {
      setMessage('');
      setError('Debe ingresar un ID de libro');
      return;
    }

    try {
      setLoading(true);
      setMessage('');
      setError('');

      await reservaService.atenderPrimera(Number(idLibro));
      setMessage('Primera reserva atendida');
      await handleBuscar();
    } catch (err) {
      console.error('Error al atender reserva:', err);
      setMessage('');
      setError(err?.response?.data?.message || 'No se pudo atender la reserva');
    } finally {
      setLoading(false);
    }
  };

  return (
    <AdminLayout>
      <h2 className="mb-4">Reservas</h2>

      {message && <div className="alert alert-success">{message}</div>}
      {error && <div className="alert alert-danger">{error}</div>}

      <div className="card card-soft">
        <div className="card-body row g-3 align-items-end">
          <div className="col-12 col-md-6 col-xl-4">
            <label className="form-label">ID Libro</label>
            <input
              type="number"
              className="form-control"
              value={idLibro}
              onChange={(e) => setIdLibro(e.target.value)}
              placeholder="Ingrese el ID del libro"
              min="1"
            />
          </div>

          <div className="col-12 col-md-3">
            <button
              className="btn btn-primary w-100"
              onClick={handleBuscar}
              disabled={loading}
            >
              Buscar reservas
            </button>
          </div>

          <div className="col-12 col-md-3">
            <button
              className="btn btn-outline-success w-100"
              onClick={handleAtender}
              disabled={loading}
            >
              Atender primera
            </button>
          </div>
        </div>
      </div>

      <div className="row g-3 mt-1">
        {loading ? (
          <div className="col-12">
            <div className="alert alert-info mb-0">Cargando reservas...</div>
          </div>
        ) : reservas.length === 0 ? (
          <div className="col-12">
            <div className="alert alert-secondary mb-0">
              No se encontraron reservas para ese libro.
            </div>
          </div>
        ) : (
          reservas.map((r) => (
            <div className="col-12 col-md-6 col-xl-4" key={r.idReserva ?? r.id_reserva}>
              <div className="card card-soft h-100">
                <div className="card-body">
                  <h5>{r.libro?.titulo || 'Libro sin título'}</h5>
                  <p className="mb-1">
                    Usuario: {r.reservaUsuario?.nombre || r.reservaUsuario?.nombreUsuario || '—'} {r.reservaUsuario?.apellido || r.reservaUsuario?.apellidoUsuario || ''}
                  </p>
                  <p className="mb-0 text-secondary">
                    Estado: {r.estado || 'Sin estado'}
                  </p>
                </div>
              </div>
            </div>
          ))
        )}
      </div>
    </AdminLayout>
  );
}