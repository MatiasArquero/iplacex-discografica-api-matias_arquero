package org.iplacex.discografia.artistas;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;


@Document(collection = "artistas")
public class Artista {

    @Id
    public String id;

    @Field(name = "nombre", targetType = FieldType.STRING)
    public String nombre;

    @Field(name = "estilos", targetType = FieldType.ARRAY)
    public List<String> estilos;

    @Field(name = "anioFundacion", targetType = FieldType.INT32)
    public int anioFundacion;

    @Field(name = "estaActivo", targetType = FieldType.BOOLEAN)
    public boolean estaActivo;
}
