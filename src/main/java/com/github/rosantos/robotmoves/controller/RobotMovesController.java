package com.github.rosantos.robotmoves.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.github.rosantos.robotmoves.entity.Position;

@RestController
@RequestMapping("/rest/mars")
public class RobotMovesController {
  
  @RequestMapping(method = RequestMethod.POST)
  ResponseEntity<?> execute(@PathVariable String commands){
    RobotMoves robotMoves = new RobotMoves();
    Position result = null;
    try {
      result = robotMoves.execute(commands);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
    return ResponseEntity.ok(result.toString());
    
  }
  
}
