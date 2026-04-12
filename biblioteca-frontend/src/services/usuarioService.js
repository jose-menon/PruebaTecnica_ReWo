import api from './api';

export const usuarioService = {
  listar: async () => (await api.get('/api/usuarios')).data,
  buscarPorId: async (id) => (await api.get(`/api/usuarios/${id}`)).data,
  buscarPorEmail: async (email) => (await api.get('/api/usuarios/email', { params: { email } })).data,
  crear: async (payload) => (await api.post('/api/usuarios', payload)).data,
  actualizar: async (id, payload) => (await api.put(`/api/usuarios/${id}`, payload)).data,
  eliminar: async (id) => (await api.delete(`/api/usuarios/${id}`)).data
};
