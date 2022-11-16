package nl.novi.HwLes10ANWMTechItEasy.Controllers;

import nl.novi.HwLes10ANWMTechItEasy.Exceptions.IndexOutOfBoundsException;
import nl.novi.HwLes10ANWMTechItEasy.Exceptions.RecordNotFoundException;
import nl.novi.HwLes10ANWMTechItEasy.Models.Television;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class TelevisionController {
    private ArrayList<Television> televisionDataBase = new ArrayList<>();

    public TelevisionController() {
        //Prefill televisions collection met twee dummy's
        Television t1 = new Television();
        t1.setBrand("Samsung");
        t1.setType("QD OLED 55S95B");

        Television t2 = new Television();
        t2.setBrand("Sony");
        t2.setType("Bravia KD-43X85KP");

        televisionDataBase.add(t1);
        televisionDataBase.add(t2);
    }

    @GetMapping("/televisions")
    public ResponseEntity<Object> getTelevisions() {
        return ResponseEntity.ok(televisionDataBase);
    }

    @GetMapping("/televisions/{id}")
    public ResponseEntity<Object> getTelevision(@PathVariable int id) {
        if (id > 0 && id < televisionDataBase.size()) {
            return ResponseEntity.ok(televisionDataBase.get(id));
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @PostMapping("/televisions")
    public ResponseEntity<Object> createTelevision(@RequestBody Television television) {
        for (int i = 0; i < televisionDataBase.size(); i++) {
            if (televisionDataBase.get(i).getType().equals(television.getType())) {
                return new ResponseEntity<>("This television already exists", HttpStatus.CONFLICT);
            }
        }
        televisionDataBase.add(television);
        return ResponseEntity.created(null).body("Television was added");
    }

    @PutMapping("/televisions/{id}")
    public ResponseEntity<Object> updateTelevision(@PathVariable int id, @RequestBody Television television) {
        if (id > 0 && id < televisionDataBase.size()) {
            televisionDataBase.set(id, television);
            return ResponseEntity.ok(televisionDataBase.get(id));
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @DeleteMapping("/televisions")
    public ResponseEntity<Object> deleteTelevision(@RequestBody String type) {
        for (int i = 0; i < televisionDataBase.size(); i++) {
            if (televisionDataBase.get(i).getType().equals(type)) {
                televisionDataBase.remove(i);
                return ResponseEntity.ok("Television removed");
            }
        }
        throw new RecordNotFoundException("Television not found");
    }
}
