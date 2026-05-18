import { useState } from 'react'
import { BrowserRouter, Routes, Route, NavLink } from 'react-router-dom'
import { LayoutDashboard, FileText, ShieldCheck, AlertTriangle, TrafficCone, ChevronLeft, ChevronRight } from 'lucide-react'
import Dashboard from './pages/Dashboard'
import Actas from './pages/Actas'
import Autoridades from './pages/Autoridades'
import Infracciones from './pages/Infracciones'

function Sidebar({ collapsed, onToggle }) {
  const link = ({ isActive }) => 'nav-link' + (isActive ? ' active' : '')
  return (
    <aside className={'sidebar' + (collapsed ? ' sidebar-collapsed' : '')}>
      <div className="sidebar-logo">
        <TrafficCone size={20} />
        {!collapsed && <span className="sidebar-logo-text">Multas<small>Sistema vial</small></span>}
      </div>
      <nav className="sidebar-nav">
        <NavLink to="/" end className={link} title="Dashboard">
          <LayoutDashboard size={16} />
          {!collapsed && 'Dashboard'}
        </NavLink>
        <NavLink to="/actas" className={link} title="Actas">
          <FileText size={16} />
          {!collapsed && 'Actas'}
        </NavLink>
        <NavLink to="/autoridades" className={link} title="Autoridades">
          <ShieldCheck size={16} />
          {!collapsed && 'Autoridades'}
        </NavLink>
        <NavLink to="/infracciones" className={link} title="Infracciones">
          <AlertTriangle size={16} />
          {!collapsed && 'Infracciones'}
        </NavLink>
      </nav>
      <button className="sidebar-toggle" onClick={onToggle} title={collapsed ? 'Expandir' : 'Contraer'}>
        {collapsed ? <ChevronRight size={16} /> : <ChevronLeft size={16} />}
      </button>
    </aside>
  )
}

export default function App() {
  const [collapsed, setCollapsed] = useState(false)
  return (
    <BrowserRouter>
      <div className={'layout' + (collapsed ? ' layout-collapsed' : '')}>
        <Sidebar collapsed={collapsed} onToggle={() => setCollapsed(c => !c)} />
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
