import { useEffect, useState } from 'react'

const EMPTY = { descripcion: '', importeInfraccion: '' }

export default function Infracciones() {
  const [lista, setLista] = useState([])
  const [loading, setLoading] = useState(true)
  const [showForm, setShowForm] = useState(false)
  const [form, setForm] = useState(EMPTY)
  const [msg, setMsg] = useState(null)
  const [editando, setEditando] = useState(null)  // { id, importe }

  const cargar = () => {
    setLoading(true)
    fetch('/api/infracciones').then(r => r.json()).then(d => { setLista(d); setLoading(false) }).catch(() => setLoading(false))
  }

  const eliminar = async id => {
    if (!confirm(`¿Eliminar infracción #${id}?`)) return
    const res = await fetch(`/api/infracciones/${id}`, { method: 'DELETE' })
    const txt = await res.text()
    setMsg({ ok: res.ok, txt })
    if (res.ok) cargar()
  }

  const guardarImporte = async e => {
    e.preventDefault()
    setMsg(null)
    const res = await fetch(`/api/infracciones/${editando.id}/importe`, {
      method: 'PATCH',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ importeInfraccion: +editando.importe }),
    })
    const txt = await res.text()
    setMsg({ ok: res.ok, txt })
    if (res.ok) { setEditando(null); cargar() }
  }

  useEffect(() => { cargar() }, [])

  const campo = e => setForm(f => ({ ...f, [e.target.name]: e.target.value }))

  const guardar = async e => {
    e.preventDefault()
    setMsg(null)
    const res = await fetch('/api/infracciones', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ descripcion: form.descripcion, importeInfraccion: +form.importeInfraccion }),
    })
    const txt = await res.text()
    if (res.ok) { setMsg({ ok: true, txt }); setForm(EMPTY); setShowForm(false); cargar() }
    else setMsg({ ok: false, txt })
  }

  return (
    <>
      <div className="page-header">
        <div><h2>Infracciones</h2><p>Catálogo de infracciones viales</p></div>
        <button className="btn btn-primary" onClick={() => { setShowForm(s => !s); setMsg(null) }}>
          {showForm ? 'Cancelar' : '+ Nueva'}
        </button>
      </div>

      {msg && <div className={`alert ${msg.ok ? 'alert-ok' : 'alert-err'}`}>{msg.txt}</div>}

      {showForm && (
        <div className="card" style={{ marginBottom: 20 }}>
          <div className="card-header"><h3>Registrar infracción</h3></div>
          <form onSubmit={guardar}>
            <div className="form-body">
              <div className="f span-2"><label>Descripción</label><input name="descripcion" value={form.descripcion} onChange={campo} placeholder="Exceso de velocidad en zona urbana" required /></div>
              <div className="f"><label>Importe ($)</label><input name="importeInfraccion" type="number" step="0.01" value={form.importeInfraccion} onChange={campo} placeholder="15000" required /></div>
              <div className="f" />
            </div>
            <div className="form-footer">
              <button className="btn btn-primary" type="submit">Guardar</button>
              <button className="btn btn-secondary" type="button" onClick={() => setShowForm(false)}>Cancelar</button>
            </div>
          </form>
        </div>
      )}

      {editando && (
        <div className="card" style={{ marginBottom: 20 }}>
          <div className="card-header"><h3>Editar importe — infracción #{editando.id}</h3></div>
          <form onSubmit={guardarImporte}>
            <div className="form-body">
              <div className="f">
                <label>Nuevo importe ($)</label>
                <input
                  type="number"
                  step="0.01"
                  value={editando.importe}
                  onChange={e => setEditando(ed => ({ ...ed, importe: e.target.value }))}
                  required
                  autoFocus
                />
              </div>
              <div className="f" />
            </div>
            <div className="form-footer">
              <button className="btn btn-primary" type="submit">Actualizar</button>
              <button className="btn btn-secondary" type="button" onClick={() => setEditando(null)}>Cancelar</button>
            </div>
          </form>
        </div>
      )}

      <div className="card">
        <div className="card-header">
          <h3>Listado</h3>
          <span style={{ color: 'var(--texto-s)', fontSize: 12 }}>{lista.length} registros</span>
        </div>
        {loading ? <div className="loading">Cargando...</div> : (
          <table>
            <thead><tr><th>ID</th><th>Descripción</th><th>Importe</th><th></th></tr></thead>
            <tbody>
              {lista.length === 0
                ? <tr className="empty"><td colSpan={4}>Sin registros aún</td></tr>
                : lista.map(i => (
                  <tr key={i.id}>
                    <td><span className="badge badge-gray">#{i.id}</span></td>
                    <td>{i.descripcionInfraccion ?? i.descripcion}</td>
                    <td><strong>${i.importeInfraccion?.toLocaleString('es-AR')}</strong></td>
                    <td style={{ display: 'flex', gap: 6 }}>
                      <button
                        className="btn-accion btn-pagar"
                        onClick={() => { setEditando({ id: i.id, importe: i.importeInfraccion }); setMsg(null); setShowForm(false) }}
                      >
                        Editar importe
                      </button>
                      <button className="btn-accion btn-cancelar" onClick={() => eliminar(i.id)}>Eliminar</button>
                    </td>
                  </tr>
                ))
              }
            </tbody>
          </table>
        )}
      </div>
    </>
  )
}
