export default function LibroTable({ libros, onEdit, onDelete }) {
  return (
    <div className="table-responsive bg-white card-soft p-2">
      <table className="table align-middle mb-0">
        <thead>
          <tr>
            <th>Título</th>
            <th>Autor</th>
            <th>ISBN</th>
            <th>Categoría</th>
            <th>Disponibles</th>
            <th className="text-end">Acciones</th>
          </tr>
        </thead>
        <tbody>
          {libros.map((libro) => (
            <tr key={libro.idLibro}>
              <td>{libro.titulo}</td>
              <td>{libro.autor}</td>
              <td>{libro.isbn}</td>
              <td>{libro.categoria?.nombreCategoria || '—'}</td>
              <td>{libro.ejemplaresDisponibles}/{libro.ejemplaresTotales}</td>
              <td className="text-end">
                <div className="btn-group">
                  <button className="btn btn-outline-primary btn-sm" onClick={() => onEdit(libro)}>Editar</button>
                  <button className="btn btn-outline-danger btn-sm" onClick={() => onDelete(libro.idLibro)}>Eliminar</button>
                </div>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
