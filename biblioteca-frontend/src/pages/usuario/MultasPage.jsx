import { useEffect, useState } from 'react';
import UserLayout from '../../layouts/UserLayout';
import { useAuth } from '../../hooks/useAuth';
import { multaService } from '../../services/multaService';
import MultaCard from '../../components/multas/MultaCard';

export default function MultasPage() {
  const { user } = useAuth();
  const [items, setItems] = useState([]);
  const [error, setError] = useState('');

  useEffect(() => {
    async function load() {
      if (!user?.idUsuario) {
        setItems([]);
        return;
      }

      try {
        setError('');
        const data = await multaService.porUsuario(user.idUsuario);
        console.log('Multas recibidas:', data);
        setItems(Array.isArray(data) ? data : []);
      } catch (err) {
        console.error('Error al cargar multas:', err);
        setError('No se pudieron cargar las multas.');
        setItems([]);
      }
    }

    load();
  }, [user]);

  return (
    <UserLayout>
      <h2 className="mb-4">Mis multas</h2>

      {error && (
        <div className="alert alert-danger">{error}</div>
      )}

      {!error && items.length === 0 && (
        <div className="alert alert-secondary">
          No tenés multas registradas.
        </div>
      )}

      <div className="row g-3">
        {items.map((m) => (
          <div
            className="col-12 col-md-6"
            key={m.idMulta ?? m.id_multa}
          >
            <MultaCard multa={m} />
          </div>
        ))}
      </div>
    </UserLayout>
  );
}