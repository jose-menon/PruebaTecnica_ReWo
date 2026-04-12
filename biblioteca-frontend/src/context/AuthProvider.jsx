import { useEffect, useMemo, useState } from 'react';
import { loginRequest } from '../services/authService';
import { clearAuth, getAuth, saveAuth } from '../utils/storage';
import { AuthContext } from './AuthContext';

export function AuthProvider({ children }) {
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        try {
            const stored = getAuth();

            if (stored?.email && stored?.password) {
                setUser(stored);
            } else {
                clearAuth();
            }
        } catch (error) {
            console.error('Error al recuperar la sesión:', error);
            clearAuth();
            setUser(null);
        } finally {
            setLoading(false);
        }
    }, []);

    const login = async ({ email, password }) => {
        const data = await loginRequest({ email, password });

        const authPayload = {
            idUsuario: data.idUsuario ?? null,
            email: data.email,
            password,
            rol: data.rol ?? null,
            mensaje: data.mensaje ?? null,
        };

        saveAuth(authPayload);
        setUser(authPayload);

        return authPayload;
    };

    const logout = () => {
        clearAuth();
        setUser(null);
    };

    const value = useMemo(
        () => ({
            user,
            loading,
            isAuthenticated: !!user,
            login,
            logout
        }),
        [user, loading]
    );

    return (
        <AuthContext.Provider value={value}>
            {children}
        </AuthContext.Provider>
    );
}