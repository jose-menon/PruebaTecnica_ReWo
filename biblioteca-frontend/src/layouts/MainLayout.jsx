import Footer from '../components/common/Footer';
import Navbar from '../components/common/Navbar';

export default function MainLayout({ children }) {
  return (
    <div className="app-shell">
      <Navbar />
      <main className="page-content">{children}</main>
      <Footer />
    </div>
  );
}
