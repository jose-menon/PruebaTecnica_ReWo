export default function LibroFilters({ filters, onChange, onSearch, onReset }) {
  return (
    <div className="card card-soft mb-4">
      <div className="card-body">
        <div className="row g-3 align-items-end">
          <div className="col-12 col-md-6 col-xl-4">
            <label className="form-label">Buscar en catálogo</label>
            <input name="texto" value={filters.texto} onChange={onChange} className="form-control form-control-lg" placeholder="Título, autor o ISBN" />
          </div>
          <div className="col-12 col-md-6 col-xl-3">
            <label className="form-label">Categoría</label>
            <input name="categoria" value={filters.categoria} onChange={onChange} className="form-control form-control-lg" placeholder="Ej. Tecnología" />
          </div>
          <div className="col-12 col-md-6 col-xl-2">
            <button className="btn btn-primary w-100 btn-lg touch-btn" onClick={onSearch}>Buscar</button>
          </div>
          <div className="col-12 col-md-6 col-xl-2">
            <button className="btn btn-outline-secondary w-100 btn-lg touch-btn" onClick={onReset}>Limpiar</button>
          </div>
        </div>
      </div>
    </div>
  );
}
