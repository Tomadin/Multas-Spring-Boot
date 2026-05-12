import { useEffect, useState } from 'react'

export default function Dashboard() {
  const [data, setData] = useState({ actas: '...', infracciones: '...', autoridades: '...' })

  useEffect(() => {
    Promise.all([
      fetch('/api/actas').then(r => r.json()),
      fetch('/api/infracciones').then(r => r.json()),
      fetch('/api/autoridades').then(r => r.json()),
    ]).then(([actas, infracciones, autoridades]) => {
      setData({ actas: actas.length, infracciones: infracciones.length, autoridades: autoridades.length })
    }).catch(() => {})
  }, [])

  return (
    <>
      <div className="page-header">
        <div>
          <h2>Dashboard</h2>
          <p>Resumen del sistema de multas viales</p>
        </div>
      </div>

      <div className="stats-grid">
        <div className="stat-card">
          <div className="num">{data.actas}</div>
          <div className="lbl">Actas registradas</div>
        </div>
        <div className="stat-card rojo">
          <div className="num">{data.infracciones}</div>
          <div className="lbl">Infracciones</div>
        </div>
        <div className="stat-card verde">
          <div className="num">{data.autoridades}</div>
          <div className="lbl">Autoridades</div>
        </div>
      </div>

      <div className="card">
        <div className="card-header"><h3>Accesos rápidos</h3></div>
        <div style={{ padding: 20, display: 'flex', gap: 12 }}>
          <a href="/actas" className="btn btn-primary">📄 Nueva Acta</a>
          <a href="/autoridades" className="btn btn-secondary">👮 Nueva Autoridad</a>
          <a href="/infracciones" className="btn btn-secondary">⚠️ Nueva Infracción</a>
        </div>
      </div>
    </>
  )
}
