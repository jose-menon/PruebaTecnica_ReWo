import { useState } from 'react';
import AdminLayout from '../../layouts/AdminLayout';
import { multaService } from '../../services/multaService';
import MultaCard from '../../components/multas/MultaCard';

export default function MultasAdminPage() {
  const [idUsuario, setIdUsuario] = useState('');
  const [multas, setMultas] = useState([]);
  const [error, setError] = useState('');

  const handleBuscar = async () => {
    try {
      setMultas(await multaService.porUsuario(idUsuario));
      setError('');
    } catch (err) {
      setError(err?.response?.data?.message || 'No se pudieron cargar las multas');
    }
  };

  return (
    <AdminLayout>
      <h2 className="mb-4">Multas</h2>
      {error && <div className="alert alert-danger">{error}</div>}
      <div className="card card-soft mb-4">
        <div className="card-body row g-3 align-items-end">
          <div className="col-12 col-md-6 col-xl-4"><input className="form-control" placeholder="ID Usuario" value={idUsuario} onChange={(e) => setIdUsuario(e.target.value)} /></div>
          <div className="col-12 col-md-3"><button className="btn btn-primary w-100" onClick={handleBuscar}>Buscar multas</button></div>
        </div>
      </div>
      <div className="row g-3">
        {multas.map((m) => <div className="col-12 col-md-6 col-xl-4" key={m.idMulta}><MultaCard multa={m} /></div>)}
      </div>
    </AdminLayout>
  );
}
