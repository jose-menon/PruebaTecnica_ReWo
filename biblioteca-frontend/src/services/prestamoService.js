import api from './api';

export const prestamoService = {
  registrar: async (idUsuario, idLibro) => (await api.post('/api/prestamos', null, { params: { idUsuario, idLibro } })).data,
  devolver: async (idPrestamo) => (await api.put(`/api/prestamos/devolucion/${idPrestamo}`)).data,
  buscarPorId: async (idPrestamo) => (await api.get(`/api/prestamos/${idPrestamo}`)).data,
  historialUsuario: async (idUsuario) => (await api.get(`/api/prestamos/usuario/${idUsuario}/historial`)).data,
  vigentesUsuario: async (idUsuario) => (await api.get(`/api/prestamos/usuario/${idUsuario}/vigentes`)).data,
  atrasados: async () => (await api.get('/api/prestamos/atrasados')).data
};
