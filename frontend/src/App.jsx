import { BrowserRouter, Routes, Route, NavLink } from 'react-router-dom'
import Dashboard from './pages/Dashboard'
import Actas from './pages/Actas'
import Autoridades from './pages/Autoridades'
import Infracciones from './pages/Infracciones'

function Sidebar() {
  const link = ({ isActive }) => 'nav-link' + (isActive ? ' active' : '')
  return (
    <aside className="sidebar">
      <div className="sidebar-logo">
        🚦 Multas
        <span>Sistema vial</span>
      </div>
      <nav className="sidebar-nav">
        <NavLink to="/" end className={link}>📊 Dashboard</NavLink>
        <NavLink to="/actas" className={link}>📄 Actas</NavLink>
        <NavLink to="/autoridades" className={link}>👮 Autoridades</NavLink>
        <NavLink to="/infracciones" className={link}>⚠️ Infracciones</NavLink>
      </nav>
    </aside>
  )
}

export default function App() {
  return (
    <BrowserRouter>
      <div className="layout">
        <Sidebar />
        <main className="main">
          <Routes>
            <Route path="/" element={<Dashboard />} />
            <Route path="/actas" element={<Actas />} />
            <Route path="/autoridades" element={<Autoridades />} />
            <Route path="/infracciones" element={<Infracciones />} />
          </Routes>
        </main>
      </div>
    </BrowserRouter>
  )
}
