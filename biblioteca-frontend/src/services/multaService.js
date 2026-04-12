import api from './api';

export const multaService = {
  buscarPorId: async (id) => (await api.get(`/api/multas/${id}`)).data,
  porPrestamo: async (idPrestamo) => (await api.get(`/api/multas/prestamo/${idPrestamo}`)).data,
  porUsuario: async (idUsuario) => (await api.get(`/api/multas/usuario/${idUsuario}`)).data
};
