import { useEffect, useState } from 'react';
import AdminLayout from '../../layouts/AdminLayout';
import { libroService } from '../../services/libroService';
import { prestamoService } from '../../services/prestamoService';

export default function DashboardAdminPage() {
  const [stats, setStats] = useState({ libros: 0, agotados: 0, atrasados: 0 });

  useEffect(() => {
    async function load() {
      try {
        const [libros, agotados, atrasados] = await Promise.all([
          libroService.listar(),
          libroService.agotados(),
          prestamoService.atrasados()
        ]);
        setStats({ libros: libros.length, agotados: agotados.length, atrasados: atrasados.length });
      } catch {
        setStats({ libros: 0, agotados: 0, atrasados: 0 });
      }
    }
    load();
  }, []);

  const items = [
    ['Libros', stats.libros, 'bi-book', 'text-primary'],
    ['Agotados', stats.agotados, 'bi-exclamation-circle', 'text-danger'],
    ['Atrasados', stats.atrasados, 'bi-clock-history', 'text-warning']
  ];

  return (
    <AdminLayout>
      <h2 className="mb-4">Dashboard administrador</h2>
      <div className="row g-3">
        {items.map(([label, value, icon, color]) => (
          <div className="col-12 col-md-6 col-xl-4" key={label}>
            <div className="card card-soft h-100">
              <div className="card-body d-flex justify-content-between align-items-center">
                <div>
                  <p className="text-secondary mb-1">{label}</p>
                  <h3 className="mb-0">{value}</h3>
                </div>
                <i className={`bi ${icon} fs-1 ${color}`} />
              </div>
            </div>
          </div>
        ))}
      </div>
    </AdminLayout>
  );
}
