import { useEffect, useState } from 'react';
import UserLayout from '../../layouts/UserLayout';
import { useAuth } from '../../hooks/useAuth';
import { reservaService } from '../../services/reservaService';
import ReservaCard from '../../components/reservas/ReservaCard';

export default function ReservasPage() {
  const { user } = useAuth();
  const [items, setItems] = useState([]);
  const [error, setError] = useState('');

  const load = async () => {
    if (!user?.idUsuario) {
      setItems([]);
      return;
    }

    try {
      setError('');
      const data = await reservaService.porUsuario(user.idUsuario);
      console.log('Reservas recibidas:', data);

      setItems(Array.isArray(data) ? data : []);
    } catch (err) {
      console.error('Error al cargar reservas:', err);
      setError('No se pudieron cargar las reservas.');
      setItems([]);
    }
  };

  useEffect(() => {
    load();
  }, [user]);

  const handleCancelar = async (id_reserva) => {
    try {
      await reservaService.cancelar(id_reserva);
      load();
    } catch (err) {
      console.error('Error al cancelar reserva:', err);
      setError('No se pudo cancelar la reserva.');
    }
  };

  return (
    <UserLayout>
      <h2 className="mb-4">Mis reservas</h2>

      {error && (
        <div className="alert alert-danger">{error}</div>
      )}

      {!error && items.length === 0 && (
        <div className="alert alert-secondary">
          No tenés reservas registradas.
        </div>
      )}

      <div className="row g-3">
        {items.map((r) => (
          <div className="col-12 col-md-6" key={r.id_reserva}>
            <ReservaCard reserva={r} onCancelar={handleCancelar} />
          </div>
        ))}
      </div>
    </UserLayout>
  );
}