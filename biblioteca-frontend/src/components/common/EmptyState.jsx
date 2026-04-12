export default function EmptyState({ title = 'Sin resultados', description = 'No hay información para mostrar.' }) {
  return (
    <div className="empty-state text-center p-4 p-md-5">
      <i className="bi bi-inbox display-6 text-secondary" />
      <h5 className="mt-3">{title}</h5>
      <p className="text-secondary mb-0">{description}</p>
    </div>
  );
}
