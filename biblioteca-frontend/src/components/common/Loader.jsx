export default function Loader({ text = 'Cargando...' }) {
  return (
    <div className="d-flex flex-column align-items-center justify-content-center py-5">
      <div className="spinner-border text-primary" role="status" />
      <p className="mt-3 mb-0 text-secondary">{text}</p>
    </div>
  );
}
