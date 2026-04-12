import api from './api';

export const libroService = {
  listar: async () => (await api.get('/api/libros')).data,
  buscarPorId: async (id) => (await api.get(`/api/libros/${id}`)).data,
  buscarCatalogo: async (texto) => (await api.get('/api/libros/catalogo', { params: { texto } })).data,
  buscarTitulo: async (texto) => (await api.get('/api/libros/titulo', { params: { texto } })).data,
  buscarAutor: async (texto) => (await api.get('/api/libros/autor', { params: { texto } })).data,
  buscarCategoria: async (nombre) => (await api.get('/api/libros/categoria', { params: { nombre } })).data,
  disponibles: async () => (await api.get('/api/libros/disponibles')).data,
  agotados: async () => (await api.get('/api/libros/agotados')).data,
  crear: async (payload) => (await api.post('/api/libros', payload)).data,
  actualizar: async (id, payload) => (await api.put(`/api/libros/${id}`, payload)).data,
  eliminar: async (id) => (await api.delete(`/api/libros/${id}`)).data
};
