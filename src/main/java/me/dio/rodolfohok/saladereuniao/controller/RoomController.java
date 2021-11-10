package me.dio.rodolfohok.saladereuniao.controller;

import lombok.AllArgsConstructor;
import me.dio.rodolfohok.saladereuniao.exception.ResourceNotFoundException;
import me.dio.rodolfohok.saladereuniao.model.Room;
import me.dio.rodolfohok.saladereuniao.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoomController {

  private RoomRepository roomRepository;

  @GetMapping("/rooms")
  public List<Room> getAllRooms() {
    return roomRepository.findAll();
  }

  @GetMapping("/rooms/{id}")
  public ResponseEntity<Room> getRoomById(@PathVariable(name = "id") Long roomId) throws ResourceNotFoundException {
    Room room = roomRepository.findById(roomId)
        .orElseThrow(() -> new ResourceNotFoundException("Room not found: " + roomId));
    return ResponseEntity.ok().body(room);
  }

  @PostMapping("/rooms")
  public Room createRoom(@Valid @RequestBody Room room){
    return roomRepository.save(room);
  }

  @PutMapping("/rooms/{id}")
  public ResponseEntity<Room> updateRoom(@PathVariable(name = "id") Long roomId, @Valid @RequestBody Room room)
      throws ResourceNotFoundException{
    Room entityRoom = roomRepository.findById(roomId)
        .orElseThrow(() -> new ResourceNotFoundException("Room not found: " + roomId));
    entityRoom.setName(room.getName());
    entityRoom.setDate(room.getDate());
    entityRoom.setStartHour(room.getStartHour());
    entityRoom.setEndHour(room.getEndHour());
    Room updateRoom = roomRepository.save(entityRoom);
    return ResponseEntity.ok().body(updateRoom);
  }

  @DeleteMapping("/rooms/{id}")
  public Map<String, Boolean> deleteRoom(@PathVariable(name = "id") Long roomId) throws ResourceNotFoundException {
    Room room = roomRepository.findById(roomId)
        .orElseThrow(() -> new ResourceNotFoundException("Room not found: " + roomId));
    roomRepository.delete(room);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", true);
    return response;
  }
}
