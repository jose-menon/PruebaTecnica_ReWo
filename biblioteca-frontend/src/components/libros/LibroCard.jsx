import { Link } from 'react-router-dom';

export default function LibroCard({ libro, onAction, actionLabel = 'Prestar', canReserve = false }) {
  return (
    <div className="card card-soft h-100">
      <div className="card-body d-flex gap-3">
        <div className="book-cover flex-shrink-0">
          <i className="bi bi-book" />
        </div>
        <div className="flex-grow-1">
          <div className="d-flex flex-wrap justify-content-between gap-2 mb-2">
            <h5 className="mb-0">{libro.titulo}</h5>
            <span className={`status-badge ${libro.ejemplaresDisponibles > 0 ? 'bg-success-subtle text-success' : 'bg-danger-subtle text-danger'}`}>
              {libro.ejemplaresDisponibles > 0 ? 'Disponible' : 'Agotado'}
            </span>
          </div>
          <p className="mb-1 text-secondary">Autor: {libro.autor}</p>
          <p className="mb-1 text-secondary">ISBN: {libro.isbn}</p>
          <p className="mb-3 text-secondary">Categoría: {libro.categoria?.nombreCategoria || '—'}</p>
          <div className="d-flex flex-wrap gap-2">
            <Link to={`/catalogo/${libro.idLibro}`} className="btn btn-outline-primary btn-sm touch-btn">Ver detalle</Link>
            {onAction && (
              <button className="btn btn-primary btn-sm touch-btn" onClick={() => onAction(libro)}>
                {canReserve ? 'Reservar' : actionLabel}
              </button>
            )}
          </div>
        </div>
      </div>
    </div>
  );
}
