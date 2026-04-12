import { formatDate } from '../../utils/formatDate';

export default function PrestamoCard({ prestamo, onDevolver }) {
  return (
    <div className="card card-soft h-100">
      <div className="card-body">
        <div className="d-flex justify-content-between gap-2 mb-2">
          <h5 className="mb-0">{prestamo.libro?.titulo || 'Libro'}</h5>
          <span className="status-badge bg-primary-subtle text-primary">{prestamo.estado}</span>
        </div>
        <p className="mb-1 text-secondary">Usuario: {prestamo.usuarioPrestamo?.nombreUsuario} {prestamo.usuarioPrestamo?.apellidoUsuario}</p>
        <p className="mb-1 text-secondary">Préstamo: {formatDate(prestamo.fechaPrestamo)}</p>
        <p className="mb-3 text-secondary">Devolución prevista: {formatDate(prestamo.fechaDevolucionPrevista)}</p>
        {onDevolver && prestamo.estado !== 'DEVUELTO' && (
          <button className="btn btn-success touch-btn" onClick={() => onDevolver(prestamo.idPrestamo)}>
            Registrar devolución
          </button>
        )}
      </div>
    </div>
  );
}
