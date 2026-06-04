import { useEffect, useState } from 'react';
import EmptyState from '../../components/common/EmptyState';
import Loader from '../../components/common/Loader';
import LibroForm from '../../components/libros/LibroForm';
import LibroTable from '../../components/libros/LibroTable';
import AdminLayout from '../../layouts/AdminLayout';
import { libroService } from '../../services/libroService';
import { categoriaService } from '../../services/categoriaService';

export default function LibrosAdminPage() {
  const [libros, setLibros] = useState([]);
  const [categorias, setCategorias] = useState([]);
  const [loading, setLoading] = useState(true);
  const [editing, setEditing] = useState(null);
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  const load = async () => {
    try {
      setLoading(true);
      setError('');

      const [librosData, categoriasData] = await Promise.all([
        libroService.listar(),
        categoriaService.listar()
      ]);

      setLibros(Array.isArray(librosData) ? librosData : []);
      setCategorias(Array.isArray(categoriasData) ? categoriasData : []);
    } catch (err) {
      setError(err?.response?.data?.message || 'No se pudo cargar libros');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    load();
  }, []);

  const handleSubmit = async (payload) => {
    try {
      setError('');
      setMessage('');

      if (editing?.idLibro) {
        await libroService.actualizar(editing.idLibro, payload);
        setMessage('Libro actualizado');
      } else {
        await libroService.crear(payload);
        setMessage('Libro creado');
      }

      setEditing(null);
      await load();
    } catch (err) {
      setError(err?.response?.data?.message || 'No se pudo guardar el libro');
    }
  };

  const handleDelete = async (id) => {
    if (!window.confirm('¿Eliminar libro?')) return;

    try {
      setError('');
      setMessage('');

      await libroService.eliminar(id);
      setMessage('Libro eliminado');
      await load();
    } catch (err) {
      setError(err?.response?.data?.message || 'No se pudo eliminar el libro');
    }
  };

  return (
    <AdminLayout>
      <div className="d-flex justify-content-between align-items-center mb-4">
        <h2 className="mb-0">Gestión de libros</h2>
      </div>

      {message && <div className="alert alert-success">{message}</div>}
      {error && <div className="alert alert-danger">{error}</div>}

      <div className="row g-4">
        <div className="col-12 col-xl-5">
          <LibroForm
            initialData={editing}
            categorias={categorias}
            onSubmit={handleSubmit}
            submitLabel={editing ? 'Actualizar' : 'Crear libro'}
          />
        </div>

        <div className="col-12 col-xl-7">
          {loading ? (
            <Loader />
          ) : libros.length === 0 ? (
            <EmptyState />
          ) : (
            <LibroTable
              libros={libros}
              onEdit={setEditing}
              onDelete={handleDelete}
            />
          )}
        </div>
      </div>
    </AdminLayout>
  );
}