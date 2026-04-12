import { useEffect, useState } from 'react';
import AdminLayout from '../../layouts/AdminLayout';
import { reporteService } from '../../services/reporteService';
import ReporteCategoriaChart from '../../components/reportes/ReporteCategoriaChart';
import ReporteEdadChart from '../../components/reportes/ReporteEdadChart';

export default function ReportesPage() {
  const [categoria, setCategoria] = useState([]);
  const [edad, setEdad] = useState([]);

  useEffect(() => {
    async function load() {
      try {
        const [c, e] = await Promise.all([reporteService.porCategoria(), reporteService.porEdad()]);
        setCategoria(c);
        setEdad(e);
      } catch {
        setCategoria([]);
        setEdad([]);
      }
    }
    load();
  }, []);

  return (
    <AdminLayout>
      <h2 className="mb-4">Reportes</h2>
      <div className="row g-4">
        <div className="col-12 col-xl-6"><ReporteCategoriaChart data={categoria} /></div>
        <div className="col-12 col-xl-6"><ReporteEdadChart data={edad} /></div>
      </div>
    </AdminLayout>
  );
}
