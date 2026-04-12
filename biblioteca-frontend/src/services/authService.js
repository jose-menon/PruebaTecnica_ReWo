import api from './api';

export async function loginRequest(payload) {
  const response = await api.post('/auth/login', payload);
  return response.data;
}
