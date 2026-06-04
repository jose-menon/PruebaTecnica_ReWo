import { useEffect, useState } from 'react';
import AdminLayout from '../../layouts/AdminLayout';
import { prestamoService } from '../../services/prestamoService';
import { usuarioService } from '../../services/usuarioService';
import { libroService } from '../../services/libroService';
import PrestamoCard from '../../components/prestamos/PrestamoCard';
import Loader from '../../components/common/Loader';

export default function PrestamosAdminPage() {
  const [idUsuario, setIdUsuario] = useState('');
  const [idLibro, setIdLibro] = useState('');

  const [usuarios, setUsuarios] = useState([]);
  const [libros, setLibros] = useState([]);

  const [atrasados, setAtrasados] = useState([]);
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(true);

  const load = async () => {
    try {
      setLoading(true);
      setError('');

      const [prestamosData, usuariosData, librosData] = await Promise.all([
        prestamoService.atrasados(),
        usuarioService.listar(),
        libroService.disponibles()
      ]);

      setAtrasados(Array.isArray(prestamosData) ? prestamosData : []);
      setUsuarios(Array.isArray(usuariosData) ? usuariosData : []);
      setLibros(Array.isArray(librosData) ? librosData : []);
    } catch (err) {
      console.error('Error al cargar datos:', err);
      setError(err?.response?.data?.message || 'No se pudieron cargar los datos');
      setAtrasados([]);
      setUsuarios([]);
      setLibros([]);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    load();
  }, []);

  const handleCreate = async (e) => {
    e.preventDefault();

    try {
      setMessage('');
      setError('');

      await prestamoService.registrar(Number(idUsuario), Number(idLibro));

      setMessage('Préstamo registrado correctamente');
      setIdUsuario('');
      setIdLibro('');

      await load();
    } catch (err) {
      console.error('Error al registrar préstamo:', err);
      setMessage('');
      setError(err?.response?.data?.message || 'No se pudo registrar el préstamo');
    }
  };

  const handleDevolver = async (idPrestamo) => {
    try {
      setMessage('');
      setError('');

      await prestamoService.devolver(idPrestamo);

      setMessage('Devolución registrada correctamente');
      await load();
    } catch (err) {
      console.error('Error al registrar devolución:', err);
      setMessage('');
      setError(err?.response?.data?.message || 'No se pudo registrar la devolución');
    }
  };

  return (
    <AdminLayout>
      <h2 className="mb-4">Préstamos</h2>

      {message && <div className="alert alert-success">{message}</div>}
      {error && <div className="alert alert-danger">{error}</div>}

      <div className="row g-4">
        <div className="col-12 col-xl-4">
          <form className="card card-soft" onSubmit={handleCreate}>
            <div className="card-body row g-3">
              <div className="col-12">
                <label className="form-label">Usuario</label>
                <select
                  className="form-select"
                  value={idUsuario}
                  onChange={(e) => setIdUsuario(e.target.value)}
                  required
                >
                  <option value="">Seleccionar usuario</option>

                  {usuarios.map((u) => {
                    const usuarioId = u.idUsuario ?? u.id_usuario;

                    return (
                      <option key={usuarioId} value={usuarioId}>
                        {u.nombreUsuario}
                        {u.apellidoUsuario ? ` ${u.apellidoUsuario}` : ''}
                        {u.email ? ` - ${u.email}` : ''}
                      </option>
                    );
                  })}
                </select>
              </div>

              <div className="col-12">
                <label className="form-label">Libro disponible</label>
                <select
                  className="form-select"
                  value={idLibro}
                  onChange={(e) => setIdLibro(e.target.value)}
                  required
                >
                  <option value="">Seleccionar libro</option>

                  {libros.map((l) => {
                    const libroId = l.idLibro ?? l.id_libro;

                    return (
                      <option key={libroId} value={libroId}>
                        {l.titulo} - {l.autor}
                        {l.ejemplaresDisponibles !== undefined
                          ? ` (${l.ejemplaresDisponibles} disponibles)`
                          : ''}
                      </option>
                    );
                  })}
                </select>
              </div>

              <div className="col-12">
                <button
                  className="btn btn-primary w-100"
                  disabled={!idUsuario || !idLibro}
                >
                  Registrar préstamo
                </button>
              </div>
            </div>
          </form>
        </div>

        <div className="col-12 col-xl-8">
          <h5 className="mb-3">Préstamos atrasados</h5>

          {loading ? (
            <Loader />
          ) : atrasados.length === 0 ? (
            <div className="alert alert-secondary mb-0">
              No hay préstamos atrasados.
            </div>
          ) : (
            <div className="row g-3">
              {atrasados.map((p) => (
                <div
                  className="col-12 col-md-6"
                  key={p.idPrestamo ?? p.id_prestamo}
                >
                  <PrestamoCard prestamo={p} onDevolver={handleDevolver} />
                </div>
              ))}
            </div>
          )}
        </div>
      </div>
    </AdminLayout>
  );
}