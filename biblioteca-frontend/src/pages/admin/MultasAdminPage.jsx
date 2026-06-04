import { useEffect, useState } from 'react';
import AdminLayout from '../../layouts/AdminLayout';
import { multaService } from '../../services/multaService';
import MultaCard from '../../components/multas/MultaCard';
import { usuarioService } from '../../services/usuarioService';

export default function MultasAdminPage() {
  const [idUsuario, setIdUsuario] = useState('');
  const [usuarios, setUsuarios] = useState([]);
  const [multas, setMultas] = useState([]);
  const [error, setError] = useState('');

  const handleBuscar = async () => {
    if (!idUsuario) {
      setError('Seleccione un usuario');
      setMultas([]);
      return;
    }

    try {
      setError('');

      const data = await multaService.porUsuario(Number(idUsuario));

      console.log('Multas admin recibidas:', data);

      setMultas(Array.isArray(data) ? data : []);
    } catch (err) {
      console.error('Error al cargar multas:', err);
      setError(
        err?.response?.data?.message ||
        'No se pudieron cargar las multas'
      );
      setMultas([]);
    }
  };
  const cargarUsuarios = async () => {
    try {
      const data = await usuarioService.listar();
      setUsuarios(Array.isArray(data) ? data : []);
    } catch (err) {
      console.error('Error al cargar usuarios:', err);
      setError('No se pudieron cargar los usuarios');
      setUsuarios([]);
    }
  };

  useEffect(() => {
    cargarUsuarios();
  }, []);
  return (
    <AdminLayout>
      <h2 className="mb-4">Multas</h2>

      {error && <div className="alert alert-danger">{error}</div>}

      <div className="card card-soft mb-4">
        <div className="card-body row g-3 align-items-end">
          <div className="col-12 col-md-6 col-xl-4">
            <label className="form-label">Usuario</label>

            <select
              className="form-select"
              value={idUsuario}
              onChange={(e) => setIdUsuario(e.target.value)}
            >
              <option value="">Seleccionar usuario</option>

              {usuarios.map((u) => {
                const usuarioId = u.idUsuario ?? u.id_usuario;

                return (
                  <option
                    key={usuarioId}
                    value={usuarioId}
                  >
                    {u.nombreUsuario}
                    {u.apellidoUsuario ? ` ${u.apellidoUsuario}` : ''}
                    {u.email ? ` - ${u.email}` : ''}
                  </option>
                );
              })}
            </select>
          </div>

          <div className="col-12 col-md-3">
            <button
              className="btn btn-primary w-100"
              onClick={handleBuscar}
            >
              Buscar multas
            </button>
          </div>
        </div>
      </div>

      {multas.length === 0 && !error && (
        <div className="alert alert-secondary">
          No hay multas para mostrar.
        </div>
      )}

      <div className="row g-3">
        {multas.map((m) => (
          <div
            className="col-12 col-md-6 col-xl-4"
            key={m.idMulta ?? m.id_multa}
          >
            <MultaCard multa={m} />
          </div>
        ))}
      </div>
    </AdminLayout>
  );
}