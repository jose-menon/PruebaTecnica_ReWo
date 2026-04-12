const AUTH_KEY = 'biblioteca_auth';

export function saveAuth({ idUsuario, email, password, rol }) {
  try {
    if (!email || !password) {
      throw new Error('Credenciales incompletas');
    }

    const authData = {
      idUsuario,
      email,
      password,
      rol
    };

    localStorage.setItem(AUTH_KEY, JSON.stringify(authData));
  } catch (error) {
    console.error('Error en saveAuth:', error.message);
  }
}

export function getAuth() {
  const raw = localStorage.getItem(AUTH_KEY);
  return raw ? JSON.parse(raw) : null;
}

export function clearAuth() {
  localStorage.removeItem(AUTH_KEY);
}
