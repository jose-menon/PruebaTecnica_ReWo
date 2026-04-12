import { useEffect, useState } from 'react';
import AdminLayout from '../../layouts/AdminLayout';
import { usuarioService } from '../../services/usuarioService';
import Loader from '../../components/common/Loader';

const initialForm = {
  nombre: '',
  apellido: '',
  email: '',
  password: '',
  edad: 18,
  rol: { idRol: 2 }
};

export default function UsuariosAdminPage() {
  const [usuarios, setUsuarios] = useState([]);
  const [loading, setLoading] = useState(true);
  const [form, setForm] = useState(initialForm);
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  const load = async () => {
    try {
      setLoading(true);
      setError('');
      const data = await usuarioService.listar();
      setUsuarios(Array.isArray(data) ? data : []);
    } catch (err) {
      console.error('Error al listar usuarios:', err);
      setError(
        err?.response?.data?.message ||
        err?.response?.data?.error ||
        'No se pudo cargar usuarios'
      );
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    load();
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;

    if (name === 'idRol') {
      setForm((prev) => ({
        ...prev,
        rol: { idRol: Number(value) }
      }));
      return;
    }

    setForm((prev) => ({
      ...prev,
      [name]: name === 'edad' ? Number(value) : value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      setError('');
      setMessage('');

      const payload = {
        nombreUsuario: form.nombre,
        apellidoUsuario: form.apellido,
        email: form.email,
        password: form.password,
        edad: form.edad,
        rolUsuario: {
          id_rol: form.rol.idRol
        }
      };
      await usuarioService.crear(payload);

      setMessage('Usuario creado correctamente');
      setForm(initialForm);
      await load();
    } catch (err) {
      console.error('Error al crear usuario:', err);
      setMessage('');
      setError(
        err?.response?.data?.message ||
        err?.response?.data?.error ||
        'No se pudo crear usuario'
      );
    }
  };

  const handleDelete = async (id) => {
    const confirmacion = window.confirm('¿Eliminar usuario?');
    if (!confirmacion) return;

    try {
      setError('');
      setMessage('');
      await usuarioService.eliminar(id);
      setMessage('Usuario eliminado correctamente');
      await load();
    } catch (err) {
      console.error('Error al eliminar usuario:', err);
      setError(
        err?.response?.data?.message ||
        err?.response?.data?.error ||
        'No se pudo eliminar usuario'
      );
    }
  };

  return (
    <AdminLayout>
      <h2 className="mb-4">Gestión de usuarios</h2>

      {message && <div className="alert alert-success">{message}</div>}
      {error && <div className="alert alert-danger">{error}</div>}

      <div className="row g-4">
        <div className="col-12 col-xl-4">
          <form className="card card-soft" onSubmit={handleSubmit}>
            <div className="card-body row g-3">
              <div className="col-12">
                <input
                  name="nombre"
                  value={form.nombre}
                  onChange={handleChange}
                  className="form-control"
                  placeholder="Nombre"
                  required
                />
              </div>

              <div className="col-12">
                <input
                  name="apellido"
                  value={form.apellido}
                  onChange={handleChange}
                  className="form-control"
                  placeholder="Apellido"
                  required
                />
              </div>

              <div className="col-12">
                <input
                  type="email"
                  name="email"
                  value={form.email}
                  onChange={handleChange}
                  className="form-control"
                  placeholder="Email"
                  required
                />
              </div>

              <div className="col-12">
                <input
                  type="password"
                  name="password"
                  value={form.password}
                  onChange={handleChange}
                  className="form-control"
                  placeholder="Contraseña"
                  required
                />
              </div>

              <div className="col-6">
                <input
                  type="number"
                  min="1"
                  name="edad"
                  value={form.edad}
                  onChange={handleChange}
                  className="form-control"
                  placeholder="Edad"
                  required
                />
              </div>

              <div className="col-6">
                <select
                  name="idRol"
                  value={form.rol.idRol}
                  onChange={handleChange}
                  className="form-select"
                >
                  <option value="1">ADMIN</option>
                  <option value="2">USUARIO</option>
                </select>
              </div>

              <div className="col-12">
                <button type="submit" className="btn btn-primary w-100">
                  Crear usuario
                </button>
              </div>
            </div>
          </form>
        </div>

        <div className="col-12 col-xl-8">
          {loading ? (
            <Loader />
          ) : (
            <div className="table-responsive bg-white card-soft p-2">
              <table className="table align-middle mb-0">
                <thead>
                  <tr>
                    <th>Nombre</th>
                    <th>Email</th>
                    <th>Edad</th>
                    <th>Rol</th>
                    <th className="text-end">Acciones</th>
                  </tr>
                </thead>
                <tbody>
                  {usuarios.length > 0 ? (
                    usuarios.map((u) => (
                      <tr key={u.idUsuario}>
                        <td>
                          {u.nombreUsuario} {u.apellidoUsuario}
                        </td>
                        <td>{u.email}</td>
                        <td>{u.edad}</td>
                        <td>{u.rolUsuario?.nombreRol || 'Sin rol'}</td>
                        <td className="text-end">
                          <button
                            type="button"
                            className="btn btn-outline-danger btn-sm"
                            onClick={() => handleDelete(u.idUsuario)}
                          >
                            Eliminar
                          </button>
                        </td>
                      </tr>
                    ))
                  ) : (
                    <tr>
                      <td colSpan="5" className="text-center py-4">
                        No hay usuarios cargados
                      </td>
                    </tr>
                  )}
                </tbody>
              </table>
            </div>
          )}
        </div>
      </div>
    </AdminLayout>
  );
}