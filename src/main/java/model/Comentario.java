package model;

public record Comentario(
        Integer id,
        Integer autor,
        Integer evento,
        String texto,
        String fecha

) {
}
