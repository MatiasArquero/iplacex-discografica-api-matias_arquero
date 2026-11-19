package org.iplacex.discografia.discos;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Document (collection = "discos")
public class Disco {
    @Id
    public String id;

    @Field(name = "idArtista", targetType = FieldType.STRING)
    public String idArtista;

    @Field(name = "nombre", targetType = FieldType.STRING)
    public String nombre;

    @Field(name="anioLanzamiento", targetType= FieldType.INT32)
    public Integer anioLanzamiento;

    @Field(name = "canciones", targetType = FieldType.ARRAY)
    public List<String> canciones;
    
}
