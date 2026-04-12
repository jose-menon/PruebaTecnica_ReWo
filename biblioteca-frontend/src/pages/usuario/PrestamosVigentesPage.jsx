import { useEffect, useState } from 'react';
import UserLayout from '../../layouts/UserLayout';
import { useAuth } from '../../hooks/useAuth';
import { prestamoService } from '../../services/prestamoService';
import PrestamoCard from '../../components/prestamos/PrestamoCard';

export default function PrestamosVigentesPage() {
  const { user } = useAuth();
  const [items, setItems] = useState([]);

  useEffect(() => {
    async function load() {
      if (!user?.idUsuario) return;
      try { setItems(await prestamoService.vigentesUsuario(user.idUsuario)); } catch { setItems([]); }
    }
    load();
  }, [user]);

  return (
    <UserLayout>
      <h2 className="mb-4">Préstamos vigentes</h2>
      <div className="row g-3">{items.map((p) => <div className="col-12 col-md-6" key={p.idPrestamo}><PrestamoCard prestamo={p} /></div>)}</div>
    </UserLayout>
  );
}
