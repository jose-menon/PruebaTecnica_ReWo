import { formatDate } from '../../utils/formatDate';

export default function ReservaCard({ reserva, onCancelar }) {
  const nombre = reserva.reservaUsuario?.nombre || reserva.reservaUsuario?.nombreUsuario || '';
  const apellido = reserva.reservaUsuario?.apellido || reserva.reservaUsuario?.apellidoUsuario || '';

  return (
    <div className="card card-soft h-100">
      <div className="card-body">
        <div className="d-flex justify-content-between gap-2 mb-2">
          <h5 className="mb-0">{reserva.libro?.titulo || 'Libro'}</h5>
          <span className="status-badge bg-warning-subtle text-warning-emphasis">
            {reserva.estado || 'Sin estado'}
          </span>
        </div>

        <p className="mb-1 text-secondary">
          Fecha reserva: {reserva.fechaReserva ? formatDate(reserva.fechaReserva) : 'No disponible'}
        </p>

        <p className="mb-3 text-secondary">
          Usuario: {(nombre || apellido) ? `${nombre} ${apellido}` : 'No disponible'}
        </p>

        {onCancelar && reserva.estado === 'ACTIVA' && (
          <button
            className="btn btn-outline-danger touch-btn"
            onClick={() => onCancelar(reserva.id_reserva)}
          >
            Cancelar reserva
          </button>
        )}
      </div>
    </div>
  );
}