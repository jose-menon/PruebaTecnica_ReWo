import api from './api';

export const reporteService = {
  porCategoria: async () => (await api.get('/api/reportes/categoria')).data,
  porEdad: async () => (await api.get('/api/reportes/edad')).data
};
