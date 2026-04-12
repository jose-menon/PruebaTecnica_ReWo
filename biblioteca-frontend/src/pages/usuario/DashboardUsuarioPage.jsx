import { useEffect, useState } from 'react';
import UserLayout from '../../layouts/UserLayout';
import { useAuth } from '../../hooks/useAuth';
import { prestamoService } from '../../services/prestamoService';
import { reservaService } from '../../services/reservaService';
import { multaService } from '../../services/multaService';

export default function DashboardUsuarioPage() {
  const { user } = useAuth();
  const [stats, setStats] = useState({ vigentes: 0, reservas: 0, multas: 0 });

  useEffect(() => {
    async function load() {
      if (!user?.idUsuario) return;
      try {
        const [vigentes, reservas, multas] = await Promise.all([
          prestamoService.vigentesUsuario(user.idUsuario),
          reservaService.porUsuario(user.idUsuario),
          multaService.porUsuario(user.idUsuario)
        ]);
        setStats({ vigentes: vigentes.length, reservas: reservas.length, multas: multas.length });
      } catch {
        setStats({ vigentes: 0, reservas: 0, multas: 0 });
      }
    }
    load();
  }, [user]);

  const cards = [
    ['Préstamos vigentes', stats.vigentes, 'bi-journal-check'],
    ['Reservas', stats.reservas, 'bi-bookmark-heart'],
    ['Multas', stats.multas, 'bi-exclamation-circle']
  ];

  return (
    <UserLayout>
      <h2 className="mb-4">Mi panel</h2>
      {!user?.idUsuario && <div className="alert alert-warning">Tu backend debería devolver idUsuario en /auth/login para habilitar el portal completo.</div>}
      <div className="row g-3">
        {cards.map(([label, value, icon]) => (
          <div className="col-12 col-md-6 col-xl-4" key={label}>
            <div className="card card-soft h-100">
              <div className="card-body d-flex justify-content-between align-items-center">
                <div><p className="text-secondary mb-1">{label}</p><h3 className="mb-0">{value}</h3></div>
                <i className={`bi ${icon} fs-1 text-primary`} />
              </div>
            </div>
          </div>
        ))}
      </div>
    </UserLayout>
  );
}
