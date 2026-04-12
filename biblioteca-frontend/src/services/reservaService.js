import api from './api';

export const reservaService = {
  registrar: async (idUsuario, idLibro) => (await api.post('/api/reservas', null, { params: { idUsuario, idLibro } })).data,
  porUsuario: async (idUsuario) => (await api.get(`/api/reservas/usuario/${idUsuario}`)).data,
  porLibro: async (idLibro) => (await api.get(`/api/reservas/libro/${idLibro}`)).data,
  cancelar: async (id_reserva) => (await api.put(`/api/reservas/cancelar/${id_reserva}`)).data,
  atenderPrimera: async (idLibro) => (await api.put(`/api/reservas/atender/${idLibro}`)).data
};
