package org.iplacex.discografia.discos;

import java.util.List;
import java.util.Optional;

import org.iplacex.discografia.artistas.IArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class DiscoController {

    @Autowired
    private IDiscoRepository discoRepo;

    @Autowired
    private IArtistaRepository artistaRepo;

    @CrossOrigin(methods = RequestMethod.POST)
    @PostMapping(value = "/disco", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Disco> HandlePostDiscoRequest(@RequestBody Disco disco) {

        boolean buscarIdArtista = artistaRepo.existsById(disco.idArtista);

        if (buscarIdArtista) {
            Disco nuevoDisco = discoRepo.save(disco);
            return new ResponseEntity<>(nuevoDisco, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @CrossOrigin(methods = RequestMethod.GET)
    @GetMapping(value = "/discos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Disco>> HandleGetDiscosRequest() {
        List<Disco> discos = discoRepo.findAll();

        return new ResponseEntity<>(discos, HttpStatus.OK);
    }

    @CrossOrigin(methods = RequestMethod.GET)
    @GetMapping(value = "/disco/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Disco> HandleGetDiscoRequest(@PathVariable String id) {
        Optional<Disco> busquedaPorId = discoRepo.findById(id);

        if (busquedaPorId.isPresent()) {
            return new ResponseEntity<>(busquedaPorId.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @CrossOrigin(methods = RequestMethod.GET)
    @GetMapping(value = "/artista/{id}/discos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Disco>> HandleGetDiscosByArtistaRequest(@PathVariable("id") String idArtista) {

        List<Disco> discosPorArtista = discoRepo.findDiscosByIdArtista(idArtista);

        return new ResponseEntity<>(discosPorArtista, HttpStatus.OK);
    }
}
