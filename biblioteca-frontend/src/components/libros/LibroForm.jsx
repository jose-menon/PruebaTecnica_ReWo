import { useEffect, useState } from 'react';

const initialState = {
  titulo: '',
  autor: '',
  isbn: '',
  ejemplaresTotales: 1,
  ejemplaresDisponibles: 1,
  categoria: { id_categoria: 1 }
};

export default function LibroForm({ initialData, onSubmit, submitLabel = 'Guardar' }) {
  const [form, setForm] = useState(initialData || initialState);

  useEffect(() => {
    setForm(initialData || initialState);
  }, [initialData]);

  const handleChange = (e) => {
    const { name, value } = e.target;

    if (name === 'id_categoria') {
      setForm((prev) => ({
        ...prev,
        categoria: { id_categoria: Number(value) }
      }));
      return;
    }

    setForm((prev) => ({
      ...prev,
      [name]: ['ejemplaresTotales', 'ejemplaresDisponibles'].includes(name)
        ? Number(value)
        : value
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit(form);
  };

  return (
    <form onSubmit={handleSubmit} className="card card-soft">
      <div className="card-body row g-3">
        <div className="col-12 col-md-6">
          <label className="form-label">Título</label>
          <input
            className="form-control"
            name="titulo"
            value={form.titulo}
            onChange={handleChange}
            required
          />
        </div>

        <div className="col-12 col-md-6">
          <label className="form-label">Autor</label>
          <input
            className="form-control"
            name="autor"
            value={form.autor}
            onChange={handleChange}
            required
          />
        </div>

        <div className="col-12 col-md-6">
          <label className="form-label">ISBN</label>
          <input
            className="form-control"
            name="isbn"
            value={form.isbn}
            onChange={handleChange}
            required
          />
        </div>

        <div className="col-12 col-md-6">
          <label className="form-label">ID Categoría</label>
          <input
            type="number"
            min="1"
            className="form-control"
            name="id_categoria"
            value={form.categoria?.id_categoria || 1}
            onChange={handleChange}
            required
          />
        </div>

        <div className="col-6 col-md-3">
          <label className="form-label">Totales</label>
          <input
            type="number"
            min="1"
            className="form-control"
            name="ejemplaresTotales"
            value={form.ejemplaresTotales}
            onChange={handleChange}
            required
          />
        </div>

        <div className="col-6 col-md-3">
          <label className="form-label">Disponibles</label>
          <input
            type="number"
            min="0"
            className="form-control"
            name="ejemplaresDisponibles"
            value={form.ejemplaresDisponibles}
            onChange={handleChange}
            required
          />
        </div>

        <div className="col-12">
          <button className="btn btn-primary touch-btn">{submitLabel}</button>
        </div>
      </div>
    </form>
  );
}