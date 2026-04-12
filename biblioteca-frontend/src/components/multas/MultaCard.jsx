export default function MultaCard({ multa }) {
  const idMulta = multa.idMulta ?? multa.id_multa ?? '—';
  const monto = typeof multa.monto === 'number'
    ? multa.monto.toFixed(2)
    : multa.monto ?? '0.00';

  const diasRetraso = multa.diasRetraso ?? multa.dias_retraso ?? '—';
  const idPrestamo =
    multa.prestamo?.idPrestamo ??
    multa.prestamo?.id_prestamo ??
    '—';

  return (
    <div className="card card-soft h-100">
      <div className="card-body">
        <div className="d-flex justify-content-between gap-2 mb-2">
          <h5 className="mb-0">Multa #{idMulta}</h5>
          <span className="status-badge bg-danger-subtle text-danger">
            ${monto}
          </span>
        </div>

        <p className="mb-1 text-secondary">
          Días de retraso: {diasRetraso}
        </p>

        <p className="mb-0 text-secondary">
          Préstamo ID: {idPrestamo}
        </p>
      </div>
    </div>
  );
}