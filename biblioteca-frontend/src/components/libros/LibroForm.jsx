import { useEffect, useState } from 'react';

const getCategoriaId = (cat) => cat?.idCategoria ?? cat?.id_categoria ?? '';
const getCategoriaNombre = (cat) =>
  cat?.nombreCategoria ?? cat?.nombre_categoria ?? 'Sin nombre';

const initialState = {
  titulo: '',
  autor: '',
  isbn: '',
  ejemplaresTotales: 1,
  ejemplaresDisponibles: 1,
  categoria: { id_categoria: '' }
};

export default function LibroForm({
  initialData,
  onSubmit,
  submitLabel = 'Guardar',
  categorias = []
}) {
  const [form, setForm] = useState(initialState);

  useEffect(() => {
    const primeraCategoriaId =
      categorias.length > 0 ? getCategoriaId(categorias[0]) : '';

    if (initialData) {
      setForm({
        titulo: initialData.titulo || '',
        autor: initialData.autor || '',
        isbn: initialData.isbn || '',
        ejemplaresTotales: initialData.ejemplaresTotales ?? 1,
        ejemplaresDisponibles: initialData.ejemplaresDisponibles ?? 1,
        categoria: {
          id_categoria: getCategoriaId(initialData.categoria)
        }
      });
    } else {
      setForm({
        ...initialState,
        categoria: {
          id_categoria: primeraCategoriaId
        }
      });
    }
  }, [initialData?.idLibro, categorias.length]);

  const handleChange = (e) => {
    const { name, value } = e.target;

    if (name === 'id_categoria') {
      setForm((prev) => ({
        ...prev,
        categoria: {
          id_categoria: value === '' ? '' : Number(value)
        }
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

    const idCategoria = form.categoria?.id_categoria;

    onSubmit({
      ...form,
      categoria: {
        id_categoria: idCategoria,
        idCategoria: idCategoria
      }
    });
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
          <label className="form-label">Categoría</label>
          <select
            className="form-select"
            name="id_categoria"
            value={form.categoria?.id_categoria || ''}
            onChange={handleChange}
            required
          >
            <option value="">Seleccionar categoría</option>

            {categorias.map((cat) => {
              const id = getCategoriaId(cat);
              const nombre = getCategoriaNombre(cat);

              return (
                <option key={id} value={id}>
                  {nombre}
                </option>
              );
            })}
          </select>
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
          <button className="btn btn-primary touch-btn">
            {submitLabel}
          </button>
        </div>
      </div>
    </form>
  );
}