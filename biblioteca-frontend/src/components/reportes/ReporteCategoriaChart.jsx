export default function ReporteCategoriaChart({ data = [] }) {
  const max = Math.max(...data.map((i) => i.totalPrestamos), 1);

  return (
    <div className="card card-soft">
      <div className="card-body">
        <h5 className="mb-3">Uso por categoría</h5>
        <div className="d-grid gap-3">
          {data.map((item) => (
            <div key={item.categoria}>
              <div className="d-flex justify-content-between small mb-1">
                <span>{item.categoria}</span>
                <strong>{item.totalPrestamos}</strong>
              </div>
              <div className="progress" style={{ height: '14px' }}>
                <div className="progress-bar" style={{ width: `${(item.totalPrestamos / max) * 100}%` }} />
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}
