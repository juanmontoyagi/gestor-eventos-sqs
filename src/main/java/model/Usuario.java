package model;

public record Usuario(
        Integer id,
        String nombre,
        String correo,
        Integer edad,
        Boolean genero
) {
}
