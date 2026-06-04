import api from './api';

export const categoriaService = {
    listar: async () => {
        const response = await api.get('/api/categorias');
        return response.data;
    }
};