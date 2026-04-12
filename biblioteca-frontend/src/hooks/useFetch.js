import { useEffect, useState } from 'react';

export function useFetch(asyncFn, deps = []) {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    let mounted = true;

    async function load() {
      try {
        setLoading(true);
        setError('');
        const result = await asyncFn();
        if (mounted) setData(result);
      } catch (err) {
        if (mounted) setError(err?.response?.data?.message || 'Ocurrió un error al cargar los datos');
      } finally {
        if (mounted) setLoading(false);
      }
    }

    load();

    return () => {
      mounted = false;
    };
  }, deps);

  return { data, loading, error, setData };
}
