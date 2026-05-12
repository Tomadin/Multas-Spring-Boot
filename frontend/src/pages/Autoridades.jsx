import { useEffect, useState } from 'react'

const EMPTY = { nombre: '', apellido: '', dni: '', genero: 'M', idPlaca: '', idLegajo: '' }

export default function Autoridades() {
  const [lista, setLista] = useState([])
  const [loading, setLoading] = useState(true)
  const [showForm, setShowForm] = useState(false)
  const [form, setForm] = useState(EMPTY)
  const [msg, setMsg] = useState(null)

  const cargar = () => {
    setLoading(true)
    fetch('/api/autoridades').then(r => r.json()).then(d => { setLista(d); setLoading(false) }).catch(() => setLoading(false))
  }

  const eliminar = async dni => {
    if (!confirm(`¿Eliminar autoridad con DNI ${dni}?`)) return
    const res = await fetch(`/api/autoridades/${dni}`, { method: 'DELETE' })
    const txt = await res.text()
    setMsg({ ok: res.ok, txt })
    if (res.ok) cargar()
  }

  useEffect(() => { cargar() }, [])

  const campo = e => setForm(f => ({ ...f, [e.target.name]: e.target.value }))

  const guardar = async e => {
    e.preventDefault()
    setMsg(null)
    const res = await fetch('/api/autoridades', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ ...form, dni: +form.dni, idPlaca: +form.idPlaca, idLegajo: +form.idLegajo }),
    })
    const txt = await res.text()
    if (res.ok) { setMsg({ ok: true, txt }); setForm(EMPTY); setShowForm(false); cargar() }
    else setMsg({ ok: false, txt })
  }

  return (
    <>
      <div className="page-header">
        <div><h2>Autoridades</h2><p>Agentes habilitados para labrar actas</p></div>
        <button className="btn btn-primary" onClick={() => { setShowForm(s => !s); setMsg(null) }}>
          {showForm ? 'Cancelar' : '+ Nueva'}
        </button>
      </div>

      {msg && <div className={`alert ${msg.ok ? 'alert-ok' : 'alert-err'}`}>{msg.txt}</div>}

      {showForm && (
        <div className="card" style={{ marginBottom: 20 }}>
          <div className="card-header"><h3>Registrar autoridad</h3></div>
          <form onSubmit={guardar}>
            <div className="form-body cols-3">
              <div className="f"><label>Nombre</label><input name="nombre" value={form.nombre} onChange={campo} required /></div>
              <div className="f"><label>Apellido</label><input name="apellido" value={form.apellido} onChange={campo} required /></div>
              <div className="f"><label>DNI</label><input name="dni" type="number" value={form.dni} onChange={campo} required /></div>
              <div className="f">
                <label>Género</label>
                <select name="genero" value={form.genero} onChange={campo}>
                  <option value="M">Masculino</option>
                  <option value="F">Femenino</option>
                  <option value="X">No binario</option>
                </select>
              </div>
              <div className="f"><label>N° Placa</label><input name="idPlaca" type="number" value={form.idPlaca} onChange={campo} required /></div>
              <div className="f"><label>N° Legajo</label><input name="idLegajo" type="number" value={form.idLegajo} onChange={campo} required /></div>
            </div>
            <div className="form-footer">
              <button className="btn btn-primary" type="submit">Guardar</button>
              <button className="btn btn-secondary" type="button" onClick={() => setShowForm(false)}>Cancelar</button>
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
            <thead><tr><th>DNI</th><th>Nombre</th><th>Apellido</th><th>Género</th><th>Placa</th><th>Legajo</th><th></th></tr></thead>
            <tbody>
              {lista.length === 0
                ? <tr className="empty"><td colSpan={7}>Sin registros aún</td></tr>
                : lista.map(a => (
                  <tr key={a.dni}>
                    <td><span className="badge badge-blue">{a.dni}</span></td>
                    <td>{a.nombre}</td>
                    <td>{a.apellido}</td>
                    <td>{a.genero}</td>
                    <td>{a.idPlaca}</td>
                    <td>{a.idLegajo}</td>
                    <td><button className="btn-accion btn-cancelar" onClick={() => eliminar(a.dni)}>Eliminar</button></td>
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
