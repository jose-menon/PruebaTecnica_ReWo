# Biblioteca Frontend

## Instalar

```bash
npm install
npm run dev
```

## Backend esperado

- Spring Boot corriendo en `http://localhost:8001`
- Endpoint `POST /auth/login`
- Endpoints `/api/libros`, `/api/usuarios`, `/api/prestamos`, `/api/reservas`, `/api/multas`, `/api/reportes`

## Importante

Para habilitar completamente el portal de usuario, conviene que `/auth/login` devuelva también `idUsuario`.

Ejemplo recomendado:

```json
{
  "mensaje": "Login exitoso",
  "email": "juan@gmail.com",
  "rol": "USUARIO",
  "idUsuario": 2
}
```

## Responsive

- Mobile first
- Bootstrap 5
- Tablet: 768px+
- Desktop: 1200px+

## PWA

Incluye `manifest.json` base como punto de partida.
