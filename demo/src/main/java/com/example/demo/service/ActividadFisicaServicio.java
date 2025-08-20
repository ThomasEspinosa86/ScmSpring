    package com.example.demo.service;

    import com.example.demo.Dto.ActividadFisicaDTO;
    import com.example.demo.repository.ActividadFisicaRepositorio;
    import com.example.demo.repository.MascotaRepositorio;
    import com.example.demo.repository.VeterinarioRepositorio;
    import com.example.demo.modelo.ActividadFisica;
    import com.example.demo.modelo.Mascota;
    import com.example.demo.modelo.Veterinario;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.Optional;

    @Service
    public class ActividadFisicaServicio {

        @Autowired
        private ActividadFisicaRepositorio actividadFisicaRepositorio;

        @Autowired
        private MascotaRepositorio mascotaRepositorio;

        @Autowired
        private VeterinarioRepositorio veterinarioRepositorio;

        public ActividadFisica crearActividadFisica(ActividadFisicaDTO actividadFisicaDTO) {
            Mascota mascota = mascotaRepositorio.findById(actividadFisicaDTO.getIdMascota())
                    .orElseThrow(() -> new IllegalArgumentException("Mascota no encontrada."));
            Veterinario veterinario = veterinarioRepositorio.findById(actividadFisicaDTO.getIdVeterinario())
                    .orElseThrow(() -> new IllegalArgumentException("Veterinario no encontrado."));

            ActividadFisica nuevaActividad = new ActividadFisica();
            nuevaActividad.setDescripcion(actividadFisicaDTO.getDescripcion());
            nuevaActividad.setTipoActividad(actividadFisicaDTO.getTipoActividad());
            nuevaActividad.setMascota(mascota);
            nuevaActividad.setVeterinario(veterinario);
            return actividadFisicaRepositorio.save(nuevaActividad);
        }

        public List<ActividadFisica> obtenerTodas() {
            return actividadFisicaRepositorio.findAll();
        }

        public Optional<ActividadFisica> obtenerPorId(Integer id) {
            return actividadFisicaRepositorio.findById(id);
        }

        public List<ActividadFisica> obtenerPorTipo(String tipo) {
            return actividadFisicaRepositorio.findByTipoActividad(tipo);
        }

        public ActividadFisica actualizarActividadFisica(Integer id, ActividadFisicaDTO actividadFisicaDTO) {
            return actividadFisicaRepositorio.findById(id).map(actividad -> {
                actividad.setDescripcion(actividadFisicaDTO.getDescripcion());
                actividad.setTipoActividad(actividadFisicaDTO.getTipoActividad());

                actividadFisicaRepositorio.save(actividad);
                return actividad;
            }).orElse(null);
        }

        public void eliminarActividadFisica(Integer id) {
            actividadFisicaRepositorio.deleteById(id);
        }
    }
