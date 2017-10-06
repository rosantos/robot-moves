package com.github.rosantos.robotmoves.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.github.rosantos.robotmoves.entity.Position;

/**
 * @author Robson Ortega dos Santos
 * 
 *         Serviço que recebe os comandos enviados remotamente ao Robo
 *
 */
@RestController
@RequestMapping("/rest")
public class RobotMovesController {

  @RequestMapping(method = RequestMethod.POST, path = "/mars/{commands}")
  ResponseEntity<?> execute(@PathVariable String commands) {
    RobotMoves robotMoves = new RobotMoves();
    Position position = null;

    try {
      position = robotMoves.execute(commands);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(String.format("400 Bad Request: %s", e.getMessage()));
    }

    return ResponseEntity.ok(position.toString());

  }

}
