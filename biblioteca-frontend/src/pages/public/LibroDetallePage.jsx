import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import Loader from '../../components/common/Loader';
import MainLayout from '../../layouts/MainLayout';
import { libroService } from '../../services/libroService';

export default function LibroDetallePage() {
  const { id } = useParams();
  const [libro, setLibro] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    async function load() {
      try {
        setLibro(await libroService.buscarPorId(id));
      } catch (err) {
        setError(err?.response?.data?.message || 'No se pudo cargar el libro');
      } finally {
        setLoading(false);
      }
    }
    load();
  }, [id]);

  return (
    <MainLayout>
      <div className="container py-4">
        {loading ? <Loader /> : error ? <div className="alert alert-danger">{error}</div> : (
          <div className="card card-soft">
            <div className="card-body p-4">
              <div className="row g-4 align-items-start">
                <div className="col-12 col-md-3">
                  <div className="book-cover mx-auto" style={{ width: 120, height: 160, fontSize: '2rem' }}>
                    <i className="bi bi-book" />
                  </div>
                </div>
                <div className="col-12 col-md-9">
                  <h2>{libro.titulo}</h2>
                  <p className="text-secondary mb-1"><strong>Autor:</strong> {libro.autor}</p>
                  <p className="text-secondary mb-1"><strong>ISBN:</strong> {libro.isbn}</p>
                  <p className="text-secondary mb-1"><strong>Categoría:</strong> {libro.categoria?.nombre || '—'}</p>
                  <p className="text-secondary mb-0"><strong>Stock:</strong> {libro.ejemplaresDisponibles}/{libro.ejemplaresTotales}</p>
                </div>
              </div>
            </div>
          </div>
        )}
      </div>
    </MainLayout>
  );
}
