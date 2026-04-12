

//configuracion para despliegue
import axios from 'axios';
import { getAuth } from '../utils/storage';

// =========================
// BASE URL DINÁMICA
// =========================
const API_URL = 'https://pruebatecnica-rewo-1.onrender.com';

if (!API_URL) {
  throw new Error("Falta definir VITE_API_URL en variables de entorno");
}

// =========================
// INSTANCIA AXIOS
// =========================
const api = axios.create({
  baseURL: API_URL,
  headers: {
    'Content-Type': 'application/json'
  },
  timeout: 10000
});

// =========================
// INTERCEPTOR REQUEST
// =========================
api.interceptors.request.use((config) => {
  const auth = getAuth();

  // 🔐 OPCIÓN 1 (ACTUAL): BASIC AUTH
  if (auth?.email && auth?.password) {
    config.auth = {
      username: auth.email,
      password: auth.password
    };
  }

  // 🔐 OPCIÓN 2 (RECOMENDADA FUTURO): JWT
  if (auth?.token) {
    config.headers.Authorization = `Bearer ${auth.token}`;
  }

  return config;
});

// =========================
// INTERCEPTOR RESPONSE
// =========================
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response) {
      console.error("Error API:", error.response.data);

      // Manejo global de errores
      if (error.response.status === 401) {
        console.warn("No autorizado - posible sesión expirada");
      }

      if (error.response.status === 500) {
        console.error("Error interno del servidor");
      }
    } else {
      console.error("Error de conexión:", error.message);
    }

    return Promise.reject(error);
  }
);

export default api;