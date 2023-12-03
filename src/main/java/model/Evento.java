package model;

public record Evento(
        Integer id,
        String titulo,
        String descripcion,
        String fecha,
        Integer capacidad
) {
}
