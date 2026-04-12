export function isAdmin(user) {
  return user?.rol === 'ADMIN';
}

export function isUsuario(user) {
  return user?.rol === 'USUARIO';
}
