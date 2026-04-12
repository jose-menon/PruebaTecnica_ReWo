import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import MainLayout from '../../layouts/MainLayout';
import { useAuth } from '../../hooks/useAuth';

export default function LoginPage() {
  const { login } = useAuth();
  const navigate = useNavigate();
  const [form, setForm] = useState({ email: '', password: '' });
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => setForm((prev) => ({ ...prev, [e.target.name]: e.target.value }));

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setLoading(true);
    try {
      const auth = await login(form);
      navigate(auth.rol === 'ADMIN' ? '/admin/dashboard' : '/usuario/dashboard');
    } catch (err) {
      setError(err?.response?.data?.message || 'Credenciales inválidas');
    } finally {
      setLoading(false);
    }
  };

  return (
    <MainLayout>
      <div className="container py-5">
        <div className="row justify-content-center">
          <div className="col-12 col-md-8 col-lg-6 col-xl-5">
            <div className="card card-soft">
              <div className="card-body p-4 p-md-5">
                <div className="text-center mb-4">
                  <i className="bi bi-shield-lock display-6 text-primary" />
                  <h2 className="mt-3">Ingresar</h2>
                  <p className="text-secondary mb-0">Usá tu email y contraseña del backend.</p>
                </div>
                {error && <div className="alert alert-danger">{error}</div>}
                <form onSubmit={handleSubmit} className="row g-3">
                  <div className="col-12">
                    <label className="form-label">Email</label>
                    <input type="email" name="email" className="form-control form-control-lg" value={form.email} onChange={handleChange} required />
                  </div>
                  <div className="col-12">
                    <label className="form-label">Contraseña</label>
                    <input type="password" name="password" className="form-control form-control-lg" value={form.password} onChange={handleChange} required />
                  </div>
                  <div className="col-12">
                    <button className="btn btn-primary btn-lg w-100 touch-btn" disabled={loading}>
                      {loading ? 'Ingresando...' : 'Iniciar sesión'}
                    </button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </MainLayout>
  );
}
