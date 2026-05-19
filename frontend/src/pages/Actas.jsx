import { useEffect, useState } from 'react'

const COLORES = ['Blanco', 'Negro', 'Gris', 'Plateado', 'Rojo', 'Azul', 'Verde', 'Amarillo', 'Naranja', 'Marrón', 'Bordó', 'Violeta']

const TIPOS_RUTA = [
  { nombre: 'Nacional',      desc: 'Ruta nacional' },
  { nombre: 'Provincial',    desc: 'Ruta provincial' },
  { nombre: 'Internacional', desc: 'Ruta internacional' },
]

const ORGANIZACIONES = [
  // Nacionales
  { nombre: 'Gendarmería Nacional Argentina', localidad: 'Nacional' },
  { nombre: 'Prefectura Naval Argentina', localidad: 'Nacional' },
  { nombre: 'Policía Federal Argentina', localidad: 'Nacional' },
  { nombre: 'Dirección Nacional de Vialidad', localidad: 'Nacional' },
  // Mendoza
  { nombre: 'Policía de Mendoza', localidad: 'Mendoza' },
  { nombre: 'Dirección Provincial de Vialidad - Mendoza', localidad: 'Mendoza' },
  { nombre: 'Municipalidad de Mendoza', localidad: 'Mendoza' },
  { nombre: 'Municipalidad de Godoy Cruz', localidad: 'Mendoza' },
  { nombre: 'Municipalidad de Las Heras', localidad: 'Mendoza' },
  { nombre: 'Municipalidad de Guaymallén', localidad: 'Mendoza' },
  { nombre: 'Municipalidad de Luján de Cuyo', localidad: 'Mendoza' },
  { nombre: 'Municipalidad de Maipú', localidad: 'Mendoza' },
]

const EMPTY = {
  lugarDeConstatacion: '', observaciones: '',
  fechaDeLabrado: '', fechaVtoPagoVolun: '', horaDeLabrado: '',
  vehiculo: { color: '', dominio: '', anioPatentamiento: '', marca: { marcaAuto: '', modelo: { modeloAuto: '' } } },
  licencia: { numeroLicencia: '', puntosInicialesLicencia: '', fechaDeVto: '', conductor: { nombre: '', apellido: '', dni: '', genero: 'M', domicilio: '' } },
  autoridadDeConstatacion: null,
  organizacionEstatal: { nombreOrganizacion: '', localidad: '' },
  ruta: { nombreRuta: '', kmRuta: '', tipoRuta: { descTipoRuta: '', nombreTipoDeRuta: '' } },
  estadoDelActa: { descripcionEstadoActa: 'Acta generada', nombreEstadoActa: 'PENDIENTE' },
  infracciones: [],
}

export default function Actas() {
  const [lista, setLista] = useState([])
  const [loading, setLoading] = useState(true)
  const [showForm, setShowForm] = useState(false)
  const [form, setForm] = useState(EMPTY)
  const [msg, setMsg] = useState(null)

  const [autoridades, setAutoridades] = useState([])
  const [infracciones, setInfracciones] = useState([])
  const [infraccionSeleccionada, setInfraccionSeleccionada] = useState('')
  const [busqueda, setBusqueda] = useState('')
  const [filtroEstado, setFiltroEstado] = useState('')

  const cargar = () => {
    setLoading(true)
    fetch('/api/actas').then(r => r.json()).then(d => { setLista(d); setLoading(false) }).catch(() => setLoading(false))
  }

  useEffect(() => {
    cargar()
    fetch('/api/autoridades').then(r => r.json()).then(setAutoridades).catch(() => {})
    fetch('/api/infracciones').then(r => r.json()).then(setInfracciones).catch(() => {})
  }, [])

  const set = (path, val) => {
    setForm(prev => {
      const next = JSON.parse(JSON.stringify(prev))
      const keys = path.split('.')
      let o = next
      keys.slice(0, -1).forEach(k => o = o[k])
      o[keys[keys.length - 1]] = val
      return next
    })
  }

  const seleccionarAutoridad = dni => {
    const a = autoridades.find(a => String(a.dni) === String(dni))
    setForm(f => ({ ...f, autoridadDeConstatacion: a || null }))
  }

  const seleccionarOrganizacion = nombre => {
    const org = ORGANIZACIONES.find(o => o.nombre === nombre)
    if (org) setForm(f => ({ ...f, organizacionEstatal: { nombreOrganizacion: org.nombre, localidad: org.localidad } }))
    else setForm(f => ({ ...f, organizacionEstatal: { nombreOrganizacion: '', localidad: '' } }))
  }

  const agregarInfraccion = () => {
    const inf = infracciones.find(i => String(i.id) === String(infraccionSeleccionada))
    if (!inf) return
    const yaAgregada = form.infracciones.some(i => i.id === inf.id)
    if (yaAgregada) return
    setForm(f => ({ ...f, infracciones: [...f.infracciones, { id: inf.id, descripcion: inf.descripcionInfraccion ?? inf.descripcion, importeInfraccion: inf.importeInfraccion }] }))
    setInfraccionSeleccionada('')
  }

  const guardar = async e => {
    e.preventDefault()
    setMsg(null)
    if (!form.autoridadDeConstatacion) return setMsg({ ok: false, txt: 'Debe seleccionar una autoridad de constatación' })
    const body = {
      lugarDeConstatacion: form.lugarDeConstatacion,
      observaciones: form.observaciones,
      fechaDeLabrado: form.fechaDeLabrado,
      fechaVtoPagoVolun: form.fechaVtoPagoVolun,
      horaDeLabrado: form.horaDeLabrado ? `${form.fechaDeLabrado}T${form.horaDeLabrado}:00` : null,
      autoridadId: form.autoridadDeConstatacion.dni,
      infracciones: form.infracciones.map(i => ({ descripcion: i.descripcion, importeInfraccion: i.importeInfraccion })),
      vehiculo: {
        color: form.vehiculo.color,
        dominio: form.vehiculo.dominio,
        anioPatentamiento: +form.vehiculo.anioPatentamiento,
        marcaNombre: form.vehiculo.marca.marcaAuto,
        modeloNombre: form.vehiculo.marca.modelo.modeloAuto,
      },
      estadoDelActa: { descripcion: 'Acta generada', nombre: 'PENDIENTE' },
      organizacionEstatal: form.organizacionEstatal,
      ruta: {
        nombreRuta: form.ruta.nombreRuta,
        kmRuta: form.ruta.kmRuta,
        nombreTipoRuta: form.ruta.tipoRuta.nombreTipoDeRuta,
        descTipoRuta: form.ruta.tipoRuta.descTipoRuta,
      },
      licencia: {
        numeroLicencia: +form.licencia.numeroLicencia,
        fechaDeVto: form.licencia.fechaDeVto,
        puntosInicialesLicencia: +form.licencia.puntosInicialesLicencia,
        conductorNombre: form.licencia.conductor.nombre,
        conductorApellido: form.licencia.conductor.apellido,
        conductorDni: +form.licencia.conductor.dni,
        conductorGenero: form.licencia.conductor.genero,
        conductorDomicilio: form.licencia.conductor.domicilio,
      },
    }
    const res = await fetch('/api/actas', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(body),
    })
    const txt = await res.text()
    if (res.ok) { setMsg({ ok: true, txt }); setForm(EMPTY); setInfraccionSeleccionada(''); setShowForm(false); cargar() }
    else setMsg({ ok: false, txt })
  }

  const estadoBadge = e => {
    if (e === 'PAGADO')    return 'badge badge-green'
    if (e === 'CANCELADO') return 'badge badge-red'
    if (e === 'VENCIDO')   return 'badge badge-red'
    return 'badge badge-yellow'
  }

  const cambiarEstado = async (id, nuevoEstado) => {
    const res = await fetch(`/api/actas/${id}/estado?estado=${nuevoEstado}`, { method: 'PATCH' })
    if (res.ok) cargar()
    else {
      const txt = await res.text()
      setMsg({ ok: false, txt })
    }
  }

  const listaFiltrada = lista.filter(a => {
    const matchPatente = !busqueda || (a.vehiculoDominio ?? '').toLowerCase().includes(busqueda.toLowerCase())
    const matchEstado = !filtroEstado || (a.estado ?? 'PENDIENTE') === filtroEstado
    return matchPatente && matchEstado
  })

  return (
    <>
      <div className="page-header">
        <div><h2>Actas</h2><p>Actas de constatación de infracciones</p></div>
        <button className="btn btn-primary" onClick={() => { setShowForm(s => !s); setMsg(null) }}>
          {showForm ? 'Cancelar' : '+ Nueva Acta'}
        </button>
      </div>

      {msg && <div className={`alert ${msg.ok ? 'alert-ok' : 'alert-err'}`}>{msg.txt}</div>}

      {showForm && (
        <div className="card" style={{ marginBottom: 20 }}>
          <div className="card-header"><h3>Registrar acta</h3></div>
          <form onSubmit={guardar}>
            <div className="form-body cols-3">

              {/* DATOS DEL ACTA */}
              <p className="section-title">Datos del acta</p>
              <div className="f span-3">
                <label>Lugar de constatación</label>
                <input value={form.lugarDeConstatacion} onChange={e => set('lugarDeConstatacion', e.target.value)} placeholder="Av. Corrientes 1500" required />
              </div>
              <div className="f"><label>Fecha de labrado</label><input type="date" value={form.fechaDeLabrado} onChange={e => set('fechaDeLabrado', e.target.value)} max={new Date().toISOString().split('T')[0]} required /></div>
              <div className="f"><label>Hora</label><input type="time" value={form.horaDeLabrado} onChange={e => set('horaDeLabrado', e.target.value)} /></div>
              <div className="f"><label>Venc. pago voluntario</label><input type="date" value={form.fechaVtoPagoVolun} onChange={e => set('fechaVtoPagoVolun', e.target.value)} /></div>
              <div className="f">
                <label>Estado</label>
                <span className="badge badge-blue" style={{ alignSelf: 'flex-start', marginTop: 4 }}>PENDIENTE</span>
              </div>
              <div className="f span-2"><label>Observaciones</label><textarea value={form.observaciones} onChange={e => set('observaciones', e.target.value)} /></div>

              {/* AUTORIDAD */}
              <p className="section-title">Autoridad de constatación</p>
              <div className="f span-3">
                <label>Seleccionar autoridad</label>
                <select value={form.autoridadDeConstatacion?.dni ?? ''} onChange={e => seleccionarAutoridad(e.target.value)} required>
                  <option value="">— Seleccione una autoridad —</option>
                  {autoridades.map(a => (
                    <option key={a.dni} value={a.dni}>{a.nombre} {a.apellido} — DNI {a.dni} — Placa {a.idPlaca}</option>
                  ))}
                </select>
                {autoridades.length === 0 && <small style={{ color: 'var(--rojo)', marginTop: 4 }}>No hay autoridades registradas. Registrá una primero.</small>}
              </div>

              {/* ORGANIZACIÓN */}
              <p className="section-title">Organización estatal</p>
              <div className="f span-3">
                <label>Organización</label>
                <select value={form.organizacionEstatal.nombreOrganizacion} onChange={e => seleccionarOrganizacion(e.target.value)} required>
                  <option value="">— Seleccione una organización —</option>
                  {ORGANIZACIONES.map(o => (
                    <option key={o.nombre} value={o.nombre}>{o.nombre} ({o.localidad})</option>
                  ))}
                </select>
              </div>

              {/* INFRACCIONES */}
              <p className="section-title">Infracciones del acta</p>
              <div className="f span-2">
                <label>Agregar infracción</label>
                <select value={infraccionSeleccionada} onChange={e => setInfraccionSeleccionada(e.target.value)}>
                  <option value="">— Seleccione una infracción —</option>
                  {infracciones.map(i => (
                    <option key={i.id} value={i.id}>{i.descripcionInfraccion ?? i.descripcion} — ${i.importeInfraccion?.toLocaleString('es-AR')}</option>
                  ))}
                </select>
                {infracciones.length === 0 && <small style={{ color: 'var(--rojo)', marginTop: 4 }}>No hay infracciones registradas. Registrá una primero.</small>}
              </div>
              <div className="f" style={{ justifyContent: 'flex-end' }}>
                <label>&nbsp;</label>
                <button type="button" className="btn btn-secondary" onClick={agregarInfraccion}>+ Agregar</button>
              </div>
              {form.infracciones.length > 0 && (
                <div className="f span-3" style={{ gap: 6 }}>
                  {form.infracciones.map((inf, i) => (
                    <div key={i} style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', padding: '7px 12px', background: 'var(--gris-0)', border: '1px solid var(--gris-2)', borderRadius: 6 }}>
                      <span style={{ fontSize: 13 }}>{inf.descripcion}</span>
                      <div style={{ display: 'flex', alignItems: 'center', gap: 10 }}>
                        <span className="badge badge-blue">${inf.importeInfraccion?.toLocaleString('es-AR')}</span>
                        <button type="button" onClick={() => setForm(f => ({ ...f, infracciones: f.infracciones.filter((_, j) => j !== i) }))} style={{ background: 'none', border: 'none', color: 'var(--rojo)', cursor: 'pointer', fontSize: 18, lineHeight: 1 }}>×</button>
                      </div>
                    </div>
                  ))}
                </div>
              )}

              {/* VEHÍCULO */}
              <p className="section-title">Vehículo</p>
              <div className="f"><label>Dominio</label><input value={form.vehiculo.dominio} onChange={e => set('vehiculo.dominio', e.target.value)} placeholder="AB123CD" required /></div>
              <div className="f">
                <label>Color</label>
                <select value={form.vehiculo.color} onChange={e => set('vehiculo.color', e.target.value)} required>
                  <option value="">— Color —</option>
                  {COLORES.map(c => <option key={c} value={c}>{c}</option>)}
                </select>
              </div>
              <div className="f"><label>Año</label><input type="number" value={form.vehiculo.anioPatentamiento} onChange={e => set('vehiculo.anioPatentamiento', e.target.value)} placeholder="2020" required /></div>
              <div className="f"><label>Marca</label><input value={form.vehiculo.marca.marcaAuto} onChange={e => set('vehiculo.marca.marcaAuto', e.target.value)} placeholder="Ford" required /></div>
              <div className="f"><label>Modelo</label><input value={form.vehiculo.marca.modelo.modeloAuto} onChange={e => set('vehiculo.marca.modelo.modeloAuto', e.target.value)} placeholder="Focus" required /></div>

              {/* CONDUCTOR */}
              <p className="section-title">Conductor</p>
              <div className="f"><label>Nombre</label><input value={form.licencia.conductor.nombre} onChange={e => set('licencia.conductor.nombre', e.target.value)} required /></div>
              <div className="f"><label>Apellido</label><input value={form.licencia.conductor.apellido} onChange={e => set('licencia.conductor.apellido', e.target.value)} required /></div>
              <div className="f"><label>DNI</label><input type="number" value={form.licencia.conductor.dni} onChange={e => set('licencia.conductor.dni', e.target.value)} required /></div>
              <div className="f">
                <label>Género</label>
                <select value={form.licencia.conductor.genero} onChange={e => set('licencia.conductor.genero', e.target.value)}>
                  <option value="M">Masculino</option><option value="F">Femenino</option><option value="X">No binario</option>
                </select>
              </div>
              <div className="f span-2"><label>Domicilio</label><input value={form.licencia.conductor.domicilio} onChange={e => set('licencia.conductor.domicilio', e.target.value)} /></div>

              {/* LICENCIA */}
              <p className="section-title">Licencia</p>
              <div className="f"><label>N° Licencia</label><input type="number" value={form.licencia.numeroLicencia} onChange={e => set('licencia.numeroLicencia', e.target.value)} required /></div>
              <div className="f"><label>Puntos</label><input type="number" value={form.licencia.puntosInicialesLicencia} onChange={e => set('licencia.puntosInicialesLicencia', e.target.value)} required /></div>
              <div className="f"><label>Vencimiento</label><input type="date" value={form.licencia.fechaDeVto} onChange={e => set('licencia.fechaDeVto', e.target.value)} /></div>

              {/* RUTA */}
              <p className="section-title">Ruta</p>
              <div className="f"><label>Nombre</label><input value={form.ruta.nombreRuta} onChange={e => set('ruta.nombreRuta', e.target.value)} placeholder="Ruta 9" required /></div>
              <div className="f"><label>KM</label><input type="number" min="0" value={form.ruta.kmRuta} onChange={e => set('ruta.kmRuta', e.target.value)} placeholder="150" required /></div>
              <div className="f">
                <label>Tipo de ruta</label>
                <select value={form.ruta.tipoRuta.nombreTipoDeRuta}
                  onChange={e => {
                    const t = TIPOS_RUTA.find(t => t.nombre === e.target.value)
                    set('ruta.tipoRuta.nombreTipoDeRuta', t?.nombre ?? '')
                    set('ruta.tipoRuta.descTipoRuta', t?.desc ?? '')
                  }} required>
                  <option value="">— Tipo —</option>
                  {TIPOS_RUTA.map(t => <option key={t.nombre} value={t.nombre}>{t.nombre}</option>)}
                </select>
              </div>
            </div>

            <div className="form-footer">
              <button className="btn btn-primary" type="submit">Guardar acta</button>
              <button className="btn btn-secondary" type="button" onClick={() => setShowForm(false)}>Cancelar</button>
            </div>
          </form>
        </div>
      )}

      <div className="card">
        <div className="card-header">
          <h3>Listado</h3>
          <span style={{ color: 'var(--texto-s)', fontSize: 12 }}>{listaFiltrada.length} registros</span>
        </div>
        <div style={{ padding: '10px 20px', borderBottom: '1px solid var(--gris-2)', display: 'flex', gap: 12 }}>
          <input
            placeholder="Buscar por patente..."
            value={busqueda}
            onChange={e => setBusqueda(e.target.value)}
            style={{ maxWidth: 220 }}
          />
          <select value={filtroEstado} onChange={e => setFiltroEstado(e.target.value)} style={{ maxWidth: 180 }}>
            <option value="">Todos los estados</option>
            <option value="PENDIENTE">Pendiente</option>
            <option value="PAGADO">Pagado</option>
            <option value="CANCELADO">Cancelado</option>
          </select>
        </div>
        {loading ? <div className="loading">Cargando...</div> : (
          <table>
            <thead><tr><th>#</th><th>Fecha</th><th>Lugar</th><th>Vehículo</th><th>Conductor</th><th>Autoridad</th><th>Estado</th><th>Infracciones</th><th>Total</th><th>Acciones</th></tr></thead>
            <tbody>
              {listaFiltrada.length === 0
                ? <tr className="empty"><td colSpan={10}>Sin registros aún</td></tr>
                : listaFiltrada.map(a => {
                  const estado = a.estado ?? 'PENDIENTE'
                  const pendiente = estado === 'PENDIENTE'
                  const total = (a.infracciones ?? []).reduce((sum, i) => sum + (i.importeInfraccion ?? 0), 0)
                  return (
                    <tr key={a.idActa}>
                      <td><span className="badge badge-gray">#{a.idActa}</span></td>
                      <td>{a.fechaDeLabrado ? new Date(a.fechaDeLabrado).toLocaleDateString('es-AR') : '-'}</td>
                      <td>{a.lugarDeConstatacion}</td>
                      <td>{a.vehiculoDominio ?? '-'}</td>
                      <td>{a.conductor ?? '-'}</td>
                      <td>{a.autoridad ?? '-'}</td>
                      <td><span className={estadoBadge(estado)}>{estado}</span></td>
                      <td>{a.infracciones?.length ?? 0}</td>
                      <td><strong>${total.toLocaleString('es-AR')}</strong></td>
                      <td>
                        {pendiente && (
                          <div style={{ display: 'flex', gap: 6 }}>
                            <button className="btn-accion btn-pagar" onClick={() => cambiarEstado(a.idActa, 'PAGADO')}>Pagar</button>
                            <button className="btn-accion btn-cancelar" onClick={() => cambiarEstado(a.idActa, 'CANCELADO')}>Cancelar</button>
                          </div>
                        )}
                      </td>
                    </tr>
                  )
                })
              }
            </tbody>
          </table>
        )}
      </div>
    </>
  )
}
